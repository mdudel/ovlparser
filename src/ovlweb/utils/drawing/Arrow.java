/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils.drawing;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import ovl.schema.gccs.ArrowType;
//import ovlweb.overlay.icsf.ICSFOverlay;

import ovlweb.utils.Bearing;
import ovlweb.utils.LatLonPointImpl;
import ovlweb.utils.drawing.tacticalgraphics.AbstractTacticalGraphic;

/**
 *
 * @author KKEITH
 */
public final class Arrow extends AbstractTacticalGraphic {

    //Graphic Specific
    public final String GRAPHIC_NAME = "ARROW";
    public final String GRAPHIC_STD = "MIL-STD-2525C";
    public final String GRAPHIC_PATH = "None";
    public final String GRAPHIC_HIERARCHY = "None";
    public final String GRAPHIC_CODE = "---------------";
    public final int GRAPHIC_POINTS = 2;
    private ArrowType arrow;
    private StringBuilder aKML = new StringBuilder();
    private ArrayList<LatLonPointImpl> arrowPoints = new ArrayList<LatLonPointImpl>();
    private ArrayList<LineString> returnLines = new ArrayList<LineString>();
    private LatLonPointImpl tipPoint;
    private LatLonPointImpl basePoint;
    private LatLonPointImpl labelPoint;
    private double hDistance;
    private boolean crossed;
    private boolean capped;
    private double arrowWidth = 0.0;
    private final double nm2Km = 1.85200;
    private boolean debug = false;

    public double getLabelLat() {
        double lat = tipPoint.getLatitude();
        return lat;
    }

    public double getLabelLon() {
        double lon = tipPoint.getLongitude();
        return lon;
    }

    public String getStringLabelLat() {
        Double lat = 0.0;
        lat = tipPoint.getLatitude();
        return lat.toString();
    }

    public String getStringLabelLon() {
        Double lon = 0.0;
        lon = tipPoint.getLongitude();
        return lon.toString();
    }

    public Arrow(String name, String description, LatLonPointImpl tip, LatLonPointImpl base, double width, Color color, boolean crossed) {
        if (width > 0.0) {
            arrowWidth = width;
        }

        Bearing aOrientation = new Bearing();
        Bearing.calculateBearing(base, tip, aOrientation);
        double backAzimuth = aOrientation.getBackAzimuth();
        double forwardAzimuth = aOrientation.getAngle();
        double pDistance = aOrientation.getDistance();
        double vDistance = (pDistance / 3) * 2;
        hDistance = (arrowWidth * this.nm2Km) / 2;

        if (crossed) {
            //Point 1
            LatLonPointImpl aP1 = new LatLonPointImpl(base);

            //Point 2
            LatLonPointImpl aP2 = new LatLonPointImpl();
            Bearing.findPoint(base, getCorrectedAzimuth(backAzimuth + 90), hDistance, aP2);

            //Point 3
            LatLonPointImpl aP3 = new LatLonPointImpl();
            Bearing.findPoint(aP2, getCorrectedAzimuth(forwardAzimuth), vDistance, aP3);

            //Point 4
            LatLonPointImpl aP4 = new LatLonPointImpl();
            Bearing.findPoint(aP3, getCorrectedAzimuth(backAzimuth + 90), hDistance, aP4);

            //Point 5
            LatLonPointImpl aP5 = new LatLonPointImpl(tip);

            //Point 6
            LatLonPointImpl aP6 = new LatLonPointImpl();
            Bearing.findPoint(aP1, getCorrectedAzimuth(backAzimuth - 90), hDistance, aP6);

            //Point 7
            LatLonPointImpl aP7 = new LatLonPointImpl();
            Bearing.findPoint(aP6, getCorrectedAzimuth(forwardAzimuth), vDistance, aP7);

            //Point 8
            LatLonPointImpl aP8 = new LatLonPointImpl();
            Bearing.findPoint(aP7, getCorrectedAzimuth(forwardAzimuth + 90), hDistance, aP8);

            arrowPoints.add(aP1); //0
            arrowPoints.add(aP2); //1
            arrowPoints.add(aP3); //2
            arrowPoints.add(aP4); //3
            arrowPoints.add(aP5); //4
            arrowPoints.add(aP8); //5
            arrowPoints.add(aP7); //6
            arrowPoints.add(aP6); //7
            arrowPoints.add(aP1); //8

            aKML.append("<Placemark>\n");
            //Styles
            aKML.append("<Style>\n");
            aKML.append("<LineStyle>\n");
            aKML.append("<color>").append(icsf2KmlColorConvert(String.valueOf(color.getRGB()))).append("</color>\n");
            aKML.append("<colorMode>normal</colorMode>\n");
            //Line Width
            aKML.append("<width>3</width>\n");
            aKML.append("</LineStyle>\n");

            aKML.append("<PolyStyle>\n");
            aKML.append("<color>").append(icsf2KmlColorConvert(String.valueOf(color.getRGB()))).append("</color>\n");
            aKML.append("<colorMode>normal</colorMode>\n");
            aKML.append("</PolyStyle>\n");

            //Balloon Style
            aKML.append("<BalloonStyle>\n");
            aKML.append("<text>$[description]</text>\n");
            aKML.append("</BalloonStyle>\n");

            //Point Style
            aKML.append("<IconStyle>\n");
            aKML.append("<color>").append(icsf2KmlColorConvert(String.valueOf(color.getRGB()))).append("</color>\n");
            aKML.append("<colorMode>normal</colorMode>\n");
            aKML.append("<Icon><href></href></Icon>\n");
            aKML.append("</IconStyle>\n");
            aKML.append("</Style>\n");

            aKML.append("<name>").append(name).append("</name>\n");

            //Description Table
            aKML.append("<description>\n");
            aKML.append("<![CDATA[");
            aKML.append("<table border=\"1\" width=\"300\">");
            aKML.append("<tr>");
            aKML.append("<td> Name:</td>");
            aKML.append("<td>").append(name).append("</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> KML Created:</td>");
            aKML.append("<td>").append(new Date().toString()).append("</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> Description:</td>");
            aKML.append("<td>").append(description).append("</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> Remarks:</td>");
            aKML.append("<td>No Remarks</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> URL:</td>");
            aKML.append("<td>No URL's</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> Width:</td>");
            aKML.append("<td> ").append(arrowWidth).append(" KM</td>");
            aKML.append("</tr>");
            aKML.append("</table>");
            aKML.append("<p> OVLWeb Created by: <br> Marty Dudel DSN: 314-370-7977 <br> Kenneth Keith DSN: 314-370-8080");
            aKML.append("]]>\n");
            aKML.append("</description>\n");

            //Crossed Arrow Shape
            aKML.append("<MultiGeometry>");
            aKML.append("<LineString>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>");
            aKML.append("<coordinates>\n");
            aKML.append(arrowPoints.get(0).getLongitude()).append(",").append(arrowPoints.get(0).getLatitude()).append(",0 ").append(arrowPoints.get(1).getLongitude()).append(",").append(arrowPoints.get(1).getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</LineString>\n");

            aKML.append("<LineString>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>");
            aKML.append("<coordinates>\n");
            aKML.append(arrowPoints.get(1).getLongitude()).append(",").append(arrowPoints.get(1).getLatitude()).append(",0 ").append(arrowPoints.get(6).getLongitude()).append(",").append(arrowPoints.get(6).getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</LineString>\n");

            aKML.append("<LineString>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>");
            aKML.append("<coordinates>\n");
            aKML.append(arrowPoints.get(6).getLongitude()).append(",").append(arrowPoints.get(6).getLatitude()).append(",0 ").append(arrowPoints.get(5).getLongitude()).append(",").append(arrowPoints.get(5).getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</LineString>\n");

            aKML.append("<LineString>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>");
            aKML.append("<coordinates>\n");
            aKML.append(arrowPoints.get(5).getLongitude()).append(",").append(arrowPoints.get(5).getLatitude()).append(",0 ").append(arrowPoints.get(4).getLongitude()).append(",").append(arrowPoints.get(4).getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</LineString>\n");

            aKML.append("<LineString>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>");
            aKML.append("<coordinates>\n");
            aKML.append(arrowPoints.get(4).getLongitude()).append(",").append(arrowPoints.get(4).getLatitude()).append(",0 ").append(arrowPoints.get(3).getLongitude()).append(",").append(arrowPoints.get(3).getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</LineString>\n");

            aKML.append("<LineString>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>");
            aKML.append("<coordinates>\n");
            aKML.append(arrowPoints.get(3).getLongitude()).append(",").append(arrowPoints.get(3).getLatitude()).append(",0 ").append(arrowPoints.get(2).getLongitude()).append(",").append(arrowPoints.get(2).getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</LineString>\n");

            aKML.append("<LineString>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>");
            aKML.append("<coordinates>\n");
            aKML.append(arrowPoints.get(2).getLongitude()).append(",").append(arrowPoints.get(2).getLatitude()).append(",0 ").append(arrowPoints.get(7).getLongitude()).append(",").append(arrowPoints.get(7).getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</LineString>\n");

            aKML.append("<LineString>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>");
            aKML.append("<coordinates>\n");
            aKML.append(arrowPoints.get(7).getLongitude()).append(",").append(arrowPoints.get(7).getLatitude()).append(",0 ").append(arrowPoints.get(0).getLongitude()).append(",").append(arrowPoints.get(0).getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</LineString>\n");

            aKML.append("<Point>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>\n");
            aKML.append("<coordinates>\n");
            aKML.append(tipPoint.getLongitude()).append(",").append(tipPoint.getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</Point>\n");
            aKML.append("</MultiGeometry>");
            aKML.append("</Placemark>\n");

        } else {
            //Point 1
            LatLonPointImpl aP1 = new LatLonPointImpl(basePoint);

            //Point 2
            LatLonPointImpl aP2 = new LatLonPointImpl();
            Bearing.findPoint(basePoint, getCorrectedAzimuth(backAzimuth + 90), hDistance, aP2);

            //Point 3
            LatLonPointImpl aP3 = new LatLonPointImpl();
            Bearing.findPoint(aP2, getCorrectedAzimuth(forwardAzimuth), vDistance, aP3);

            //Point 4
            LatLonPointImpl aP4 = new LatLonPointImpl();
            Bearing.findPoint(aP3, getCorrectedAzimuth(backAzimuth + 90), hDistance, aP4);

            //Point 5
            LatLonPointImpl aP5 = new LatLonPointImpl(tipPoint);

            //Point 6
            LatLonPointImpl aP6 = new LatLonPointImpl();
            Bearing.findPoint(aP1, getCorrectedAzimuth(backAzimuth - 90), hDistance, aP6);

            //Point 7
            LatLonPointImpl aP7 = new LatLonPointImpl();
            Bearing.findPoint(aP6, getCorrectedAzimuth(forwardAzimuth), vDistance, aP7);

            //Point 8
            LatLonPointImpl aP8 = new LatLonPointImpl();
            Bearing.findPoint(aP7, getCorrectedAzimuth(forwardAzimuth + 90), hDistance, aP8);

            arrowPoints.add(aP1); //0
            arrowPoints.add(aP2); //1
            arrowPoints.add(aP3); //2
            arrowPoints.add(aP4); //3
            arrowPoints.add(aP5); //4
            arrowPoints.add(aP8); //5
            arrowPoints.add(aP7); //6
            arrowPoints.add(aP6); //7
            arrowPoints.add(aP1); //8

            aKML.append("<Placemark>\n");
            //Styles
            aKML.append("<Style>\n");
            aKML.append("<LineStyle>\n");
            aKML.append("<color>").append(icsf2KmlColorConvert(String.valueOf(color.getRGB()))).append("</color>\n");
            aKML.append("<colorMode>normal</colorMode>\n");
            //Line Width
            aKML.append("<width>3</width>\n");
            aKML.append("</LineStyle>\n");

            aKML.append("<PolyStyle>\n");
            aKML.append("<color>").append(icsf2KmlColorConvert(String.valueOf(color.getRGB()))).append("</color>\n");
            aKML.append("<colorMode>normal</colorMode>\n");
            aKML.append("</PolyStyle>\n");

            //Balloon Style
            aKML.append("<BalloonStyle>\n");
            aKML.append("<text>$[description]</text>\n");
            aKML.append("</BalloonStyle>\n");

            //Point Style
            aKML.append("<IconStyle>\n");
            aKML.append("<color>").append(icsf2KmlColorConvert(String.valueOf(color.getRGB()))).append("</color>\n");
            aKML.append("<colorMode>normal</colorMode>\n");
            aKML.append("<Icon><href></href></Icon>\n");
            aKML.append("</IconStyle>\n");
            aKML.append("</Style>\n");

            aKML.append("<name>").append(name).append("</name>\n");

            //Description Table
            aKML.append("<description>\n");
            aKML.append("<![CDATA[");
            aKML.append("<table border=\"1\" width=\"300\">");
            aKML.append("<tr>");
            aKML.append("<td> Name:</td>");
            aKML.append("<td>").append(name).append("</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> KML Created:</td>");
            aKML.append("<td>").append(new Date().toString()).append("</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> Description:</td>");
            aKML.append("<td>").append(description).append("</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> Remarks:</td>");
            aKML.append("<td>No Remarks</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> URL:</td>");
            aKML.append("<td>No URL's</td>");
            aKML.append("</tr>");

            aKML.append("<tr>");
            aKML.append("<td> Width:</td>");
            aKML.append("<td> ").append(arrowWidth).append(" KM</td>");
            aKML.append("</tr>");
            aKML.append("</table>");
            aKML.append("<p> OVLWeb Created by: <br> Marty Dudel DSN: 314-370-7977 <br> Kenneth Keith DSN: 314-370-8080");
            aKML.append("]]>\n");
            aKML.append("</description>\n");

            //Arrow Shape
            aKML.append("<MultiGeometry>");
            for (int i = 0; i < arrowPoints.size(); i++) {
                if (i == arrowPoints.size() - 1) {
                    aKML.append("<LineString>\n");
                    aKML.append("<altitudeMode>clampToGround</altitudeMode>");
                    aKML.append("<coordinates>\n");
                    aKML.append(arrowPoints.get(i).getLongitude()).append(",").append(arrowPoints.get(i).getLatitude()).append(",0 ").append(arrowPoints.get(0).getLongitude()).append(",").append(arrowPoints.get(0).getLatitude()).append(",0");
                    aKML.append("</coordinates>\n");
                    aKML.append("</LineString>\n");
                } else {
                    aKML.append("<LineString>\n");
                    aKML.append("<altitudeMode>clampToGround</altitudeMode>");
                    aKML.append("<coordinates>\n");
                    aKML.append(arrowPoints.get(i).getLongitude()).append(",").append(arrowPoints.get(i).getLatitude()).append(",0 ").append(arrowPoints.get(i + 1).getLongitude()).append(",").append(arrowPoints.get(i + 1).getLatitude()).append(",0");
                    aKML.append("</coordinates>\n");
                    aKML.append("</LineString>\n");
                }
            }
            aKML.append("<Point>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>\n");
            aKML.append("<coordinates>\n");
            aKML.append(base.getLongitude()).append(",").append(base.getLatitude()).append(",0");
            aKML.append("</coordinates>\n");
            aKML.append("</Point>\n");
            aKML.append("</MultiGeometry>");
            aKML.append("</Placemark>\n");
        }
    }

    public Arrow(ArrowType arrow) {
        this.arrow = arrow;
        //arrow.getp
        computeNormalGraphic();
    }

    public Arrow(boolean crossed, double width, ArrayList<LatLonPointImpl> points) {
        //LatLonPointImpl Container
        ArrayList<LatLonPointImpl> positionPoints = points;
        tipPoint = positionPoints.get(0);
        basePoint = positionPoints.get(1);

        if (positionPoints.size() >= 3) {
            LineString arrowTipLine;
            LineString arrowBaseLine;
            LatLonPointImpl leftArrowReferencePoint = new LatLonPointImpl();
            LatLonPointImpl rightArrowReferencePoint = new LatLonPointImpl();
            LatLonPointImpl previous = new LatLonPointImpl();
            Bearing aOrientation = new Bearing();
            Bearing.calculateBearing(basePoint, tipPoint, aOrientation);
            double pDistance = aOrientation.getDistance();
            double vDistance = (pDistance / 4) * 2;

            if (width > 0) {
                hDistance = width;
            } else {
                hDistance = (100 * nm2Km);
            }

            if (crossed) {
                if (debug) {
                    for (int i = 0; i < positionPoints.size(); i++) {
                        if (i == positionPoints.size() - 1) {
                            LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                            LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i - 1));
                            LineString line = new LineString(p1, p2);
                            returnLines.add(line);
                        } else {
                            LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                            LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i + 1));
                            LineString line = new LineString(p1, p2);
                            returnLines.add(line);
                        }
                    }
                }

                //Build Arrow Head
                LatLonPointImpl head1 = new LatLonPointImpl(positionPoints.get(0));
                LatLonPointImpl head2 = new LatLonPointImpl(positionPoints.get(1));
                arrowTipLine = new LineString(head1, head2);

                Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() + 45), vDistance, head1);
                LineString hls1 = new LineString(tipPoint, head1);
                returnLines.add(hls1);

                Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() - 45), vDistance, head1);
                LineString hls2 = new LineString(tipPoint, head1);
                returnLines.add(hls2);

                Bearing.findPoint(hls1.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() - 90), hDistance / 1.5, head1);
                LineString ls3 = new LineString(hls1.getPoint2(), head1);
                leftArrowReferencePoint = new LatLonPointImpl(head1);
                returnLines.add(ls3);

                Bearing.findPoint(hls2.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() + 90), hDistance / 1.5, head1);
                LineString hls4 = new LineString(hls2.getPoint2(), head1);
                rightArrowReferencePoint = new LatLonPointImpl(head1);
                returnLines.add(hls4);

                if (positionPoints.size() >= 3) {
                    //Sublist Remaining Points
                    List<LatLonPointImpl> middlePoints = positionPoints.subList(1, positionPoints.size());

                    for (LatLonPointImpl p : middlePoints) {
                        if (middlePoints.indexOf(p) != middlePoints.size() - 1) {
                            if (middlePoints.indexOf(p) == 1) {
                                //Process Base Segment
                                LatLonPointImpl p1 = new LatLonPointImpl();
                                LatLonPointImpl p2 = new LatLonPointImpl();
                                LineString ls1 = new LineString(p, previous);

                                Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() - 90), hDistance, p1);
                                Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() + 90), hDistance, p2);

                                returnLines.add(new LineString(rightArrowReferencePoint, p1));
                                rightArrowReferencePoint = new LatLonPointImpl(p2);
                                returnLines.add(new LineString(leftArrowReferencePoint, p2));
                                leftArrowReferencePoint = new LatLonPointImpl(p1);
                                previous = new LatLonPointImpl(p);
                            } else {
                                //Process Base Segment
                                LatLonPointImpl p1 = new LatLonPointImpl();
                                LatLonPointImpl p2 = new LatLonPointImpl();
                                LineString ls1 = new LineString(p, previous);

                                Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() + 90), hDistance, p1);
                                Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() - 90), hDistance, p2);

                                returnLines.add(new LineString(rightArrowReferencePoint, p1));
                                rightArrowReferencePoint = new LatLonPointImpl(p1);
                                returnLines.add(new LineString(leftArrowReferencePoint, p2));
                                leftArrowReferencePoint = new LatLonPointImpl(p2);
                                previous = new LatLonPointImpl(p);
                            }
                        } else {
                            //Process Arrow Base Cap
                            LatLonPointImpl p1 = new LatLonPointImpl();
                            LatLonPointImpl p2 = new LatLonPointImpl();
                            arrowBaseLine = new LineString(p, previous);

                            Bearing.findPoint(p, getCorrectedAzimuth(arrowBaseLine.getAzimuth() + 90), hDistance, p1);
                            Bearing.findPoint(p, getCorrectedAzimuth(arrowBaseLine.getAzimuth() - 90), hDistance, p2);
                            returnLines.add(new LineString(rightArrowReferencePoint, p1));
                            rightArrowReferencePoint = new LatLonPointImpl(p1);
                            returnLines.add(new LineString(leftArrowReferencePoint, p2));
                            leftArrowReferencePoint = new LatLonPointImpl(p2);
                            returnLines.add(new LineString(p1, p2));
                        }
                    }
                }

            } else {
                if (debug) {
                    for (int i = 0; i < positionPoints.size(); i++) {
                        if (i == positionPoints.size() - 1) {
                            LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                            LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i - 1));
                            LineString line = new LineString(p1, p2);
                            returnLines.add(line);
                        } else {
                            LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                            LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i + 1));
                            LineString line = new LineString(p1, p2);
                            returnLines.add(line);
                        }
                    }
                }

                for (int i = 0; i < positionPoints.size(); i++) {
                    if (i == 0) {
                        //Line 0 - Arrow Tip
                        LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                        LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i + 1));
                        arrowTipLine = new LineString(p1, p2);

                        Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() + 45), vDistance, p1);
                        LineString ls1 = new LineString(tipPoint, p1);
                        returnLines.add(ls1);

                        Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() - 45), vDistance, p1);
                        LineString ls2 = new LineString(tipPoint, p1);
                        returnLines.add(ls2);

                        Bearing.findPoint(ls1.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() - 90), hDistance / 1.5, p1);
                        LineString ls3 = new LineString(ls1.getPoint2(), p1);
                        leftArrowReferencePoint = new LatLonPointImpl(p1);
                        returnLines.add(ls3);

                        Bearing.findPoint(ls2.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() + 90), hDistance / 1.5, p1);
                        LineString ls4 = new LineString(ls2.getPoint2(), p1);
                        rightArrowReferencePoint = new LatLonPointImpl(p1);
                        returnLines.add(ls4);
                    }
                }

                List<LatLonPointImpl> middlePoints = positionPoints.subList(1, positionPoints.size());

                for (LatLonPointImpl p : middlePoints) {
                    if (middlePoints.indexOf(p) != middlePoints.size() - 1) {
                        //Process Base Segment
                        LatLonPointImpl p1 = new LatLonPointImpl();
                        LatLonPointImpl p2 = new LatLonPointImpl();
                        LineString ls1 = new LineString(p, previous);

                        Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() + 90), hDistance, p1);
                        Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() - 90), hDistance, p2);

                        returnLines.add(new LineString(rightArrowReferencePoint, p1));
                        rightArrowReferencePoint = new LatLonPointImpl(p1);
                        returnLines.add(new LineString(leftArrowReferencePoint, p2));
                        leftArrowReferencePoint = new LatLonPointImpl(p2);

                        previous = new LatLonPointImpl(p);
                    } else {
                        //Process Arrow Base Cap
                        LatLonPointImpl p1 = new LatLonPointImpl();
                        LatLonPointImpl p2 = new LatLonPointImpl();
                        arrowBaseLine = new LineString(p, previous);

                        Bearing.findPoint(p, getCorrectedAzimuth(arrowBaseLine.getAzimuth() + 90), hDistance, p1);
                        Bearing.findPoint(p, getCorrectedAzimuth(arrowBaseLine.getAzimuth() - 90), hDistance, p2);
                        returnLines.add(new LineString(rightArrowReferencePoint, p1));
                        rightArrowReferencePoint = new LatLonPointImpl(p1);
                        returnLines.add(new LineString(leftArrowReferencePoint, p2));
                        leftArrowReferencePoint = new LatLonPointImpl(p2);
                        returnLines.add(new LineString(p1, p2));
                    }
                }
            }
        }
    }

    public String getICSFKMLRepresentation() {
        if (returnLines.size() > 0) {
            aKML.append("<Placemark>\n");

            //Styles
            aKML.append("<Style>\n");
            aKML.append("<LineStyle>\n");
            aKML.append("<color>").append(icsf2KmlColorConvert(arrow.getLINECOLOR())).append("</color>\n");
            aKML.append("<colorMode>normal</colorMode>\n");
            //Line Width
            if (arrow.getLINEWIDTH() != null) {
                aKML.append("<width>").append(arrow.getLINEWIDTH().intValue()).append("</width>\n");
            } else {
                aKML.append("<width>3</width>\n");
            }

            aKML.append("</LineStyle>\n");
            aKML.append("<PolyStyle>\n");
            aKML.append("<color>").append(icsf2KmlColorConvert(arrow.getLINECOLOR())).append("</color>\n");
            aKML.append("<colorMode>normal</colorMode>\n");

            //Fill Check
            if (arrow.getFILLTYPE() != null) {
                if (arrow.getFILLTYPE() > 0) {
                    aKML.append("<fill>1</fill>\n");
                } else {
                    aKML.append("<fill>0</fill>\n");
                }
            }

            aKML.append("</PolyStyle>\n");

            //Balloon Style
            aKML.append("<BalloonStyle>\n");
            aKML.append("<text>$[description]</text>\n");
            aKML.append("</BalloonStyle>\n");

            //Point Style
            aKML.append("<IconStyle>\n");
            if (arrow.getLINECOLOR() != null) {
                aKML.append("<color>").append(icsf2KmlColorConvert(arrow.getLINECOLOR())).append("</color>\n");
            }
            aKML.append("<colorMode>normal</colorMode>\n");
            aKML.append("<Icon><href></href></Icon>\n");
            aKML.append("</IconStyle>\n");
            aKML.append("</Style>\n");

            //Visibility Check
            if (arrow.isVISIBILITY() != null) {
                if (arrow.isVISIBILITY()) {
                    aKML.append("<visibility>1</visibility>\n");
                } else {
                    aKML.append("<visibility>0</visibility>\n");
                }
            }

            //Name Check
            if (arrow.getNAME() != null) {
                aKML.append("<name>").append(arrow.getNAME()).append("</name>\n");
            } else {
                aKML.append("<name>Default Arrow Name</name>\n");
            }

            //Description Table
            aKML.append("<description>\n");
            aKML.append("<![CDATA[");
            aKML.append("<table border=\"1\" width=\"300\">");

            //Name
            if (arrow.getNAME() != null) {
                aKML.append("<tr>");
                aKML.append("<td> Name:</td>");
                aKML.append("<td>").append(arrow.getNAME()).append("</td>");
                aKML.append("</tr>");
            } else {
                aKML.append("<tr>");
                aKML.append("<td> Name:</td>");
                aKML.append("<td>Default Arrow Name</td>");
                aKML.append("</tr>");
            }

            //Date Time
            if (arrow.getCREATETIME() != null) {
                String epochString = arrow.getCREATETIME().toString();
                long epoch = Long.parseLong(epochString);
                Date expiry = new Date(epoch * 1000);
                aKML.append("<tr>");
                aKML.append("<td> Arrow Created:</td>");
                StringBuilder append = aKML.append("<td>").append(expiry.toString()).append("</td>");
                aKML.append("</tr>");
            }
            aKML.append("<tr>");
            aKML.append("<td> KML Created:</td>");
            aKML.append("<td>").append(new Date().toString()).append("</td>");
            aKML.append("</tr>");

            if (arrow.getDESCRIPTION() != null) {
                aKML.append("<tr>");
                aKML.append("<td> Description:</td>");
                aKML.append("<td>").append(arrow.getDESCRIPTION()).append("</td>");
                aKML.append("</tr>");
            } else {
                aKML.append("<tr>");
                aKML.append("<td> Description:</td>");
                aKML.append("<td>Arrow Default Description</td>");
                aKML.append("</tr>");
            }

            //Remarks
            if (arrow.getREMARKS() != null) {
                aKML.append("<tr>");
                aKML.append("<td> Remarks:</td>");
                aKML.append("<td>").append(arrow.getREMARKS()).append("</td>");
                aKML.append("</tr>");
            } else {
                aKML.append("<tr>");
                aKML.append("<td> Remarks:</td>");
                aKML.append("<td>Arrow Default Remarks</td>");
                aKML.append("</tr>");
            }

            //URL
            if (arrow.getURL() != null) {
                if (arrow.getURL().size() > 0) {
                    aKML.append("<tr>");
                    aKML.append("<td> URL:</td>");
                    aKML.append("<td> ").append(arrow.getURL().toString().replace("[", "").replace(",", "<br>").replace("]", "")).append("</td>");
                    aKML.append("</tr>");
                } else {
                    aKML.append("<tr>");
                    aKML.append("<td> URL:</td>");
                    aKML.append("<td>None</td>");
                    aKML.append("</tr>");
                }

            } else {
                aKML.append("<tr>");
                aKML.append("<td> URL:</td>");
                aKML.append("<td>Arrow Default URL's</td>");
                aKML.append("</tr>");
            }

            //Width
            if (arrow.getWIDTH() != null) {
                aKML.append("<tr>");
                aKML.append("<td> Width:</td>");
                aKML.append("<td> ").append(arrow.getWIDTH()).append(" KM</td>");
                aKML.append("</tr>");
            } else {
                aKML.append("<tr>");
                aKML.append("<td> Width:</td>");
                aKML.append("<td> ").append(100 * this.nm2Km).append(" KM</td>");
                aKML.append("</tr>");
            }

            aKML.append("</table>");
            aKML.append("<p> OVLWeb Created by: <br> Marty Dudel DSN: 314-370-7977 <br> Kenneth Keith DSN: 314-370-8080");
            aKML.append("]]>\n");
            aKML.append("</description>\n");

            //Arrow Lines
            aKML.append("<MultiGeometry>");
            for (LineString s : returnLines) {
                aKML.append("<LineString>\n");
                aKML.append("<altitudeMode>clampToGround</altitudeMode>");
                aKML.append("<coordinates>\n");
                aKML.append(s.getPoint1().getLongitude()).append(",").append(s.getPoint1().getLatitude()).append(",0 ").append(s.getPoint2().getLongitude()).append(",").append(s.getPoint2().getLatitude()).append(",0");
                aKML.append("</coordinates>\n");
                aKML.append("</LineString>\n");
            }

            //Placemark Point
            aKML.append("<Point>\n");
            aKML.append("<altitudeMode>clampToGround</altitudeMode>\n");
            aKML.append("<coordinates>\n");
            aKML.append(tipPoint.getLongitude()).append(",").append(tipPoint.getLatitude()).append(",0 ");
            aKML.append("</coordinates>\n");
            aKML.append("</Point>\n");
            aKML.append("</MultiGeometry>\n");
            aKML.append("</Placemark>\n");
            return aKML.toString();
        } else {
            return "";
        }
    }

    @Override
    public String getKMLShape() {
        StringBuilder returnString = new StringBuilder();
        if (returnLines.size() > 0) {
            for (LineString ls : returnLines) {
                returnString.append(ls.getKMLShape());
            }
            return returnString.toString();
        }
        return "";
    }

    @Override
    public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth) {
        if (returnLines.size() > 1) {
            StringBuilder cKML = new StringBuilder();

            cKML.append("<Placemark>\n");
            String lineColorRed = String.valueOf(linecolor.getRed());
            String lineColorGreen = String.valueOf(linecolor.getGreen());
            String lineColorBlue = String.valueOf(linecolor.getBlue());
            String lineColorString = lineColorRed + " " + lineColorGreen + " " + lineColorBlue;

            //Styles
            cKML.append("<Style>\n");
            cKML.append("<LineStyle>\n");
            cKML.append("<color>").append(icsf2KmlColorConvert(lineColorString)).append("</color>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<width>").append(linewidth).append("</width>\n");
            cKML.append("</LineStyle>\n");
            cKML.append("<PolyStyle>\n");
            cKML.append("<color>").append(icsf2KmlColorConvert(lineColorString)).append("</color>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<fill>0</fill>\n");
            cKML.append("</PolyStyle>\n");
            cKML.append("<LabelStyle>\n");
            cKML.append("<color>").append(icsf2KmlColorConvert(lineColorString)).append("</color>\n");
            cKML.append("</LabelStyle>\n");

            //Balloon Style
            cKML.append("<BalloonStyle>\n");
            cKML.append("<text>$[description]</text>\n");
            cKML.append("</BalloonStyle>\n");

            //Point Style
            cKML.append("<IconStyle>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<Icon><href></href></Icon>\n");
            cKML.append("</IconStyle>\n");
            cKML.append("</Style>\n");

            cKML.append("<name>Tactical Graphic:" + GRAPHIC_NAME + "</name>\n");

            //Description Table
            cKML.append("<description>\n");
            cKML.append("<![CDATA[");
            cKML.append("<table border=\"1\" width=\"300\">");
            cKML.append("<tr>\n");
            cKML.append("<td> Name:</td>\n");
            cKML.append("<td>" + GRAPHIC_NAME + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> KML Created:</td>\n");
            cKML.append("<td>").append(new Date().toString()).append("</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Symbol Code:</td>\n");
            cKML.append("<td>" + GRAPHIC_CODE + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Path:</td>\n");
            cKML.append("<td>" + GRAPHIC_PATH + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Hierarchy:</td>\n");
            cKML.append("<td>" + GRAPHIC_HIERARCHY + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Description:</td>\n");
            cKML.append("<td>").append(description).append("</td>\n");
            cKML.append("</tr>\n");

            if (!arrowPoints.isEmpty()) {
                cKML.append("<tr>\n");
                cKML.append("<td> Arrow Points:</td>\n");
                for (LatLonPointImpl p : arrowPoints) {
                    cKML.append("<td> Lat: ").append(p.getLatitude()).append("<br> Lon: ").append(p.getLongitude()).append("</td>\n");
                }
                cKML.append("</tr>\n");
            }

//            cKML.append("<tr>\n");
//            cKML.append("<td> Image:</td>\n");
//            if (GRAPHIC_NAME.contains(" ")) {
//                String GRAPHICNAME = GRAPHIC_NAME.replaceAll(" ", "");
//                cKML.append("<td>" + "<img src='http://" + OVLWebServer.getServerAddress() + ":" + OVLWebServer.getServicePort() + "/Resources/" + GRAPHICNAME + "/'" + "/></td>\n");
//            } else {
//                cKML.append("<td>" + "<img src='http://" + OVLWebServer.getServerAddress() + ":" + OVLWebServer.getServicePort() + "/Resources/" + GRAPHIC_NAME + "/'" + "/></td>\n");
//            }
//            cKML.append("</tr>\n");
            cKML.append("</table>\n");
            cKML.append("<p> OVLWeb Created by: <br> Marty Dudel DSN: 314-370-7977 <br> Kenneth Keith DSN: 314-370-8080");
            cKML.append("]]>\n");
            cKML.append("</description>\n");

            //Dome Rings
            cKML.append("<MultiGeometry>\n");
            for (LineString s : returnLines) {
                cKML.append(s.getKMLShape());
            }
            cKML.append("</MultiGeometry>\n");
            cKML.append("</Placemark>\n");

            //Dome Label
            cKML.append("<Placemark>\n");
            cKML.append("<Style>\n");
            cKML.append("<IconStyle>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<Icon><href></href></Icon>\n");
            cKML.append("</IconStyle>\n");
            cKML.append("</Style>\n");
            cKML.append("<name>").append(label).append("</name>\n");
            cKML.append("<Point>\n");
            cKML.append("<altitudeMode>clampToGround</altitudeMode>\n");
            cKML.append("<coordinates>\n");
            cKML.append(arrowPoints.get(0).getLongitude()).append(",").append(arrowPoints.get(0).getLatitude()).append(",0\n");
            cKML.append("</coordinates>\n");
            cKML.append("</Point>\n");
            cKML.append("</Placemark>\n");
            return cKML.toString();
        }
        return "";
    }

    public String getKMLPlacemark(String label, String description, String kmlLineColor, String lineWidth) {
        if (returnLines.size() > 1) {
            StringBuilder cKML = new StringBuilder();

            cKML.append("<Placemark>\n");
            //Styles
            cKML.append("<Style>\n");
            cKML.append("<LineStyle>\n");
            cKML.append("<color>").append(kmlLineColor).append("</color>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<width>").append(lineWidth).append("</width>\n");
            cKML.append("</LineStyle>\n");
            cKML.append("<PolyStyle>\n");
            cKML.append("<color>").append(kmlLineColor).append("</color>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<fill>0</fill>\n");
            cKML.append("</PolyStyle>\n");
            cKML.append("<LabelStyle>\n");
            cKML.append("<color>").append(kmlLineColor).append("</color>\n");
            cKML.append("</LabelStyle>\n");

            //Balloon Style
            cKML.append("<BalloonStyle>\n");
            cKML.append("<text>$[description]</text>\n");
            cKML.append("</BalloonStyle>\n");

            //Point Style
            cKML.append("<IconStyle>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<Icon><href></href></Icon>\n");
            cKML.append("</IconStyle>\n");
            cKML.append("</Style>\n");

            cKML.append("<name>Tactical Graphic:" + GRAPHIC_NAME + "</name>\n");

            //Description Table
            cKML.append("<description>\n");
            cKML.append("<![CDATA[");
            cKML.append("<table border=\"1\" width=\"300\">");
            cKML.append("<tr>\n");
            cKML.append("<td> Name:</td>\n");
            cKML.append("<td>" + GRAPHIC_NAME + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> KML Created:</td>\n");
            cKML.append("<td>").append(new Date().toString()).append("</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Symbol Code:</td>\n");
            cKML.append("<td>" + GRAPHIC_CODE + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Path:</td>\n");
            cKML.append("<td>" + GRAPHIC_PATH + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Hierarchy:</td>\n");
            cKML.append("<td>" + GRAPHIC_HIERARCHY + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Description:</td>\n");
            cKML.append("<td>").append(description).append("</td>\n");
            cKML.append("</tr>\n");

            if (!arrowPoints.isEmpty()) {
                cKML.append("<tr>\n");
                cKML.append("<td> Arrow Points:</td>\n");
                for (LatLonPointImpl p : arrowPoints) {
                    cKML.append("<td> Lat: ").append(p.getLatitude()).append("<br> Lon: ").append(p.getLongitude()).append("</td>\n");
                }
                cKML.append("</tr>\n");
            }

//            cKML.append("<tr>\n");
//            cKML.append("<td> Image:</td>\n");
//            if (GRAPHIC_NAME.contains(" ")) {
//                String GRAPHICNAME = GRAPHIC_NAME.replaceAll(" ", "");
//                cKML.append("<td>" + "<img src='http://" + OVLWebServer.getServerAddress() + ":" + OVLWebServer.getServicePort() + "/Resources/" + GRAPHICNAME + "/'" + "/></td>\n");
//            } else {
//                cKML.append("<td>" + "<img src='http://" + OVLWebServer.getServerAddress() + ":" + OVLWebServer.getServicePort() + "/Resources/" + GRAPHIC_NAME + "/'" + "/></td>\n");
//            }
//            cKML.append("</tr>\n");
            cKML.append("</table>\n");
            cKML.append("<p> OVLWeb Created by: <br> Marty Dudel DSN: 314-370-7977 <br> Kenneth Keith DSN: 314-370-8080");
            cKML.append("]]>\n");
            cKML.append("</description>\n");

            //Dome Rings
            cKML.append("<MultiGeometry>\n");
            for (LineString s : returnLines) {
                cKML.append(s.getKMLShape());
            }
            cKML.append("</MultiGeometry>\n");
            cKML.append("</Placemark>\n");

            //Dome Label
            cKML.append("<Placemark>\n");
            cKML.append("<Style>\n");
            cKML.append("<IconStyle>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<Icon><href></href></Icon>\n");
            cKML.append("</IconStyle>\n");
            cKML.append("</Style>\n");
            cKML.append("<name>").append(label).append("</name>\n");
            cKML.append("<Point>\n");
            cKML.append("<altitudeMode>clampToGround</altitudeMode>\n");
            cKML.append("<coordinates>\n");
            cKML.append(arrowPoints.get(0).getLongitude()).append(",").append(arrowPoints.get(0).getLatitude()).append(",0\n");
            cKML.append("</coordinates>\n");
            cKML.append("</Point>\n");
            cKML.append("</Placemark>\n");
            return cKML.toString();
        }
        return "";
    }

    @Override
    public void computeSimpleGraphic(LatLonPointImpl point, double bearing, double width, double height) {
        //Label Point
        labelPoint = new LatLonPointImpl(point);

        tipPoint = new LatLonPointImpl();
        Bearing.findPoint(point, bearing, width / 2, tipPoint);
        LineString startLine = new LineString(point, tipPoint);

        basePoint = new LatLonPointImpl();
        Bearing.findPoint(point, startLine.getBackAzimuth(), width / 2, basePoint);

        Bearing aOrientation = new Bearing();
        Bearing.calculateBearing(basePoint, tipPoint, aOrientation);
        double pDistance = aOrientation.getDistance();
        double vDistance = (pDistance / 4) * 2;
        hDistance = height;

        //Build Arrow Head
        LatLonPointImpl head1 = new LatLonPointImpl(tipPoint);
        LatLonPointImpl head2 = new LatLonPointImpl(basePoint);
        LineString arrowTipLine = new LineString(head1, head2);

        Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() + 45), vDistance, head1);
        DashedLine hls1 = new DashedLine(tipPoint, head1);
        returnLines.add(hls1);

        Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() - 45), vDistance, head1);
        DashedLine hls2 = new DashedLine(tipPoint, head1);
        returnLines.add(hls2);

        Bearing.findPoint(hls1.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() - 90), (vDistance / 3) * 1.5, head1);
        DashedLine ls3 = new DashedLine(hls1.getPoint2(), head1);
        LatLonPointImpl leftArrowReferencePoint = new LatLonPointImpl(head1);
        returnLines.add(ls3);

        Bearing.findPoint(hls2.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() + 90), (vDistance / 3) * 1.5, head1);
        DashedLine hls4 = new DashedLine(hls2.getPoint2(), head1);
        LatLonPointImpl rightArrowReferencePoint = new LatLonPointImpl(head1);
        returnLines.add(hls4);

        //Arrow Base
        Bearing.findPoint(leftArrowReferencePoint, getCorrectedAzimuth(arrowTipLine.getAzimuth()), startLine.getDistance(), head1);
        ls3 = new DashedLine(leftArrowReferencePoint, head1);
        leftArrowReferencePoint = new LatLonPointImpl(head1);
        returnLines.add(ls3);

        Bearing.findPoint(rightArrowReferencePoint, getCorrectedAzimuth(arrowTipLine.getAzimuth()), startLine.getDistance(), head1);
        hls4 = new DashedLine(rightArrowReferencePoint, head1);
        rightArrowReferencePoint = new LatLonPointImpl(head1);
        returnLines.add(hls4);
    }

    @Override
    public void computeNormalGraphic() {
        //LatLonPointImpl Container
        ArrayList<LatLonPointImpl> positionPoints = new ArrayList<LatLonPointImpl>();

        //Build LatLonPoints for each BigDecimal Pair
        for (JAXBElement<List<BigDecimal>> pair : arrow.getPOSITION()) {
            String[] point = pair.getValue().toString().replace("[", "").replace("]", "").split(",");
            double lat = Double.parseDouble(point[0]);
            double lon = Double.parseDouble(point[1].replace(" ", ""));
            LatLonPointImpl P1 = new LatLonPointImpl(lat, lon);
            positionPoints.add(P1);
        }

        tipPoint = positionPoints.get(0);
        basePoint = positionPoints.get(1);

        if (positionPoints.size() >= 3) {
            LineString arrowTipLine;
            LineString arrowBaseLine;
            LatLonPointImpl leftArrowReferencePoint = new LatLonPointImpl();
            LatLonPointImpl rightArrowReferencePoint = new LatLonPointImpl();
            LatLonPointImpl previous = new LatLonPointImpl();
            Bearing aOrientation = new Bearing();
            Bearing.calculateBearing(basePoint, tipPoint, aOrientation);
            double pDistance = aOrientation.getDistance();
            double vDistance = (pDistance / 4) * 2;
            double hDistance;
            boolean runMiddle = true;

            if (arrow.getWIDTH() != null) {
                hDistance = (arrow.getWIDTH().doubleValue() * nm2Km);
            } else {
                hDistance = (100 * nm2Km);
            }

            if (arrow.isCROSSED()) {
                if (debug) {
                    for (int i = 0; i < positionPoints.size(); i++) {
                        if (i == positionPoints.size() - 1) {
                            LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                            LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i - 1));
                            LineString line = new LineString(p1, p2);
                            returnLines.add(line);
                        } else {
                            LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                            LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i + 1));
                            LineString line = new LineString(p1, p2);
                            returnLines.add(line);
                        }
                    }
                }

                //Build Arrow Head
                LatLonPointImpl head1 = new LatLonPointImpl(positionPoints.get(0));
                LatLonPointImpl head2 = new LatLonPointImpl(positionPoints.get(1));
                arrowTipLine = new LineString(head1, head2);

                Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() + 45), vDistance, head1);
                LineString hls1 = new LineString(tipPoint, head1);
                returnLines.add(hls1);

                Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() - 45), vDistance, head1);
                LineString hls2 = new LineString(tipPoint, head1);
                returnLines.add(hls2);

                Bearing.findPoint(hls1.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() - 90), hDistance / 1.5, head1);
                LineString ls3 = new LineString(hls1.getPoint2(), head1);
                leftArrowReferencePoint = new LatLonPointImpl(head1);
                returnLines.add(ls3);

                Bearing.findPoint(hls2.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() + 90), hDistance / 1.5, head1);
                LineString hls4 = new LineString(hls2.getPoint2(), head1);
                rightArrowReferencePoint = new LatLonPointImpl(head1);
                returnLines.add(hls4);

                if (positionPoints.size() >= 3) {
                    //Sublist Remaining Points
                    List<LatLonPointImpl> middlePoints = positionPoints.subList(1, positionPoints.size());

                    for (LatLonPointImpl p : middlePoints) {
                        if (middlePoints.indexOf(p) != middlePoints.size() - 1) {
                            if (middlePoints.indexOf(p) == 1) {
                                //Process Base Segment
                                LatLonPointImpl p1 = new LatLonPointImpl();
                                LatLonPointImpl p2 = new LatLonPointImpl();
                                LineString ls1 = new LineString(p, previous);

                                Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() - 90), hDistance, p1);
                                Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() + 90), hDistance, p2);

                                returnLines.add(new LineString(rightArrowReferencePoint, p1));
                                rightArrowReferencePoint = new LatLonPointImpl(p2);
                                returnLines.add(new LineString(leftArrowReferencePoint, p2));
                                leftArrowReferencePoint = new LatLonPointImpl(p1);
                                previous = new LatLonPointImpl(p);
                            } else {
                                //Process Base Segment
                                LatLonPointImpl p1 = new LatLonPointImpl();
                                LatLonPointImpl p2 = new LatLonPointImpl();
                                LineString ls1 = new LineString(p, previous);

                                Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() + 90), hDistance, p1);
                                Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() - 90), hDistance, p2);

                                returnLines.add(new LineString(rightArrowReferencePoint, p1));
                                rightArrowReferencePoint = new LatLonPointImpl(p1);
                                returnLines.add(new LineString(leftArrowReferencePoint, p2));
                                leftArrowReferencePoint = new LatLonPointImpl(p2);
                                previous = new LatLonPointImpl(p);
                            }
                        } else {
                            //Process Arrow Base Cap
                            LatLonPointImpl p1 = new LatLonPointImpl();
                            LatLonPointImpl p2 = new LatLonPointImpl();
                            arrowBaseLine = new LineString(p, previous);

                            Bearing.findPoint(p, getCorrectedAzimuth(arrowBaseLine.getAzimuth() + 90), hDistance, p1);
                            Bearing.findPoint(p, getCorrectedAzimuth(arrowBaseLine.getAzimuth() - 90), hDistance, p2);
                            returnLines.add(new LineString(rightArrowReferencePoint, p1));
                            rightArrowReferencePoint = new LatLonPointImpl(p1);
                            returnLines.add(new LineString(leftArrowReferencePoint, p2));
                            leftArrowReferencePoint = new LatLonPointImpl(p2);
                            returnLines.add(new LineString(p1, p2));
                        }
                    }
                }

            } else {
                if (debug) {
                    for (int i = 0; i < positionPoints.size(); i++) {
                        if (i == positionPoints.size() - 1) {
                            LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                            LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i - 1));
                            LineString line = new LineString(p1, p2);
                            returnLines.add(line);
                        } else {
                            LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                            LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i + 1));
                            LineString line = new LineString(p1, p2);
                            returnLines.add(line);
                        }
                    }
                }

                for (int i = 0; i < positionPoints.size(); i++) {
                    if (i == 0) {
                        //Line 0 - Arrow Tip
                        LatLonPointImpl p1 = new LatLonPointImpl(positionPoints.get(i));
                        LatLonPointImpl p2 = new LatLonPointImpl(positionPoints.get(i + 1));
                        arrowTipLine = new LineString(p1, p2);

                        Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() + 45), vDistance, p1);
                        LineString ls1 = new LineString(tipPoint, p1);
                        returnLines.add(ls1);

                        Bearing.findPoint(tipPoint, getCorrectedAzimuth(arrowTipLine.getAzimuth() - 45), vDistance, p1);
                        LineString ls2 = new LineString(tipPoint, p1);
                        returnLines.add(ls2);

                        Bearing.findPoint(ls1.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() - 90), hDistance / 1.5, p1);
                        LineString ls3 = new LineString(ls1.getPoint2(), p1);
                        leftArrowReferencePoint = new LatLonPointImpl(p1);
                        returnLines.add(ls3);

                        Bearing.findPoint(ls2.getPoint2(), getCorrectedAzimuth(arrowTipLine.getAzimuth() + 90), hDistance / 1.5, p1);
                        LineString ls4 = new LineString(ls2.getPoint2(), p1);
                        rightArrowReferencePoint = new LatLonPointImpl(p1);
                        returnLines.add(ls4);
                    }
                }

                List<LatLonPointImpl> middlePoints = positionPoints.subList(1, positionPoints.size());

                for (LatLonPointImpl p : middlePoints) {
                    if (middlePoints.indexOf(p) != middlePoints.size() - 1) {
                        //Process Base Segment
                        LatLonPointImpl p1 = new LatLonPointImpl();
                        LatLonPointImpl p2 = new LatLonPointImpl();
                        LineString ls1 = new LineString(p, previous);

                        Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() + 90), hDistance, p1);
                        Bearing.findPoint(p, getCorrectedAzimuth(ls1.getAzimuth() - 90), hDistance, p2);

                        returnLines.add(new LineString(rightArrowReferencePoint, p1));
                        rightArrowReferencePoint = new LatLonPointImpl(p1);
                        returnLines.add(new LineString(leftArrowReferencePoint, p2));
                        leftArrowReferencePoint = new LatLonPointImpl(p2);

                        previous = new LatLonPointImpl(p);
                    } else {
                        //Process Arrow Base Cap
                        LatLonPointImpl p1 = new LatLonPointImpl();
                        LatLonPointImpl p2 = new LatLonPointImpl();
                        arrowBaseLine = new LineString(p, previous);

                        Bearing.findPoint(p, getCorrectedAzimuth(arrowBaseLine.getAzimuth() + 90), hDistance, p1);
                        Bearing.findPoint(p, getCorrectedAzimuth(arrowBaseLine.getAzimuth() - 90), hDistance, p2);
                        returnLines.add(new LineString(rightArrowReferencePoint, p1));
                        rightArrowReferencePoint = new LatLonPointImpl(p1);
                        returnLines.add(new LineString(leftArrowReferencePoint, p2));
                        leftArrowReferencePoint = new LatLonPointImpl(p2);
                        returnLines.add(new LineString(p1, p2));
                    }
                }
            }
        }
    }
}
