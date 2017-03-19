/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils.drawing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import static ovl.utils.KMlUtils.icsf2KmlColorConvert;
import ovlweb.utils.Bearing;
import ovlweb.utils.LatLonPointImpl;

/**
 *
 * @author KKEITH
 */
public class DashedLine extends LineString {

    public final String GRAPHIC_NAME = "DASHEDLINE";
    public final String GRAPHIC_STD = "MIL-STD-2525C";
    public final String GRAPHIC_PATH = "None";
    public final String GRAPHIC_HIERARCHY = "None";
    public final String GRAPHIC_CODE = "---------------";
    public final int GRAPHIC_POINTS = 1;
    ArrayList<LineString> dashSegments = new ArrayList<LineString>();
    LatLonPointImpl point1;
    LatLonPointImpl point2;

    public DashedLine(LatLonPointImpl p1, LatLonPointImpl p2) {
        super(p1, p2);
        point1 = new LatLonPointImpl(p1);
        point2 = new LatLonPointImpl(p2);
        processDashedLine();
    }

    private void processDashedLine() {
        LineString ls = new LineString(point1, point1);
        //Line Reference Points
        ArrayList<LatLonPointImpl> referencePoints = new ArrayList<LatLonPointImpl>();

        //Total Line Distance
        double lineDistance = getDistance();

        //Line Orientation
        double lineOrientation = getAzimuth();

        //Line Segmentation
        double lineSegment = lineDistance / 10;

        //Middle Points
        for (int i = 0; i < lineDistance; i += lineSegment) {
            LatLonPointImpl p = new LatLonPointImpl();
            Bearing.findPoint(point1, lineOrientation, i, p);
            referencePoints.add(new LatLonPointImpl(p));
        }

        //Dashed LineStrings
        for (int i = 0; i <= referencePoints.size() - 1; i++) {
            if (i != referencePoints.size() - 1) {
                Bearing b = new Bearing();
                LatLonPointImpl p = new LatLonPointImpl();
                Bearing.calculateBearing(referencePoints.get(i), referencePoints.get(i + 1), b);
                Bearing.findPoint(referencePoints.get(i), b.getAngle(), b.getDistance() / 2, p);
                dashSegments.add(new LineString(referencePoints.get(i), p));
            } else {
                dashSegments.add(new LineString(referencePoints.get(i), point2));
            }
        }
    }

    public ArrayList<LineString> getDashedLines() {
        if (dashSegments.size() > 0) {
            return this.dashSegments;
        }
        return null;
    }
    
    @Override
    public ArrayList<LineString> getGapLines() {
        if (this.getPoint1() != null && this.getPoint2() != null) {
            ArrayList<LineString> returnLines = new ArrayList<LineString>();
            LatLonPointImpl p1 = new LatLonPointImpl();
            LatLonPointImpl p2 = new LatLonPointImpl();
            Bearing.findPoint(getPoint1(), getAzimuth(), (getDistance() / 2) - (getDistance() / 10), p1);
            Bearing.findPoint(getPoint2(), getBackAzimuth(), (getDistance() / 2) - (getDistance() / 10), p2);
            returnLines.addAll((new DashedLine(new LatLonPointImpl(getPoint1()), p1)).getDashedLines());
            returnLines.addAll((new DashedLine(p2, new LatLonPointImpl(getPoint2())).getDashedLines()));
            return returnLines;
        }
        return null;
    }

    @Override
    public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth) {
        if (point1 != null && point2 != null) {
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

            cKML.append("<tr>\n");
            cKML.append("<td> Center Point:</td>\n");
            cKML.append("<td> Lat: ").append(getMiddlePoint().getLatitude()).append("<br> Lon: ").append(getMiddlePoint().getLongitude()).append("</td>\n");
            cKML.append("</tr>\n");

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

            //LineString
            cKML.append("<MultiGeometry>\n");
            if (dashSegments.size() > 0) {
                for (LineString ls : dashSegments) {
                    cKML.append(ls.getKMLShape());
                }
            }
            cKML.append(this.getKMLShape());
            cKML.append("</MultiGeometry>\n");
            cKML.append("</Placemark>\n");

            //LineString Label
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
            cKML.append(getMiddlePoint().getLongitude()).append(",").append(getMiddlePoint().getLatitude()).append(",0\n");
            cKML.append("</coordinates>\n");
            cKML.append("</Point>\n");
            cKML.append("</Placemark>\n");
            return cKML.toString();
        }
        return "";
    }

    @Override
    public String getKMLShape() {
        if (dashSegments.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (LineString ls : dashSegments) {
                sb.append("<LineString>\n");
                sb.append("<extrude>0</extrude>\n");
                sb.append("<tessellate>1</tessellate>\n");
                sb.append("<coordinates>\n");
                sb.append(ls.getPoint1().getLongitude()).append(",").append(ls.getPoint1().getLatitude()).append(",0 ").append(ls.getPoint2().getLongitude()).append(",").append(ls.getPoint2().getLatitude()).append(",0\n");
                sb.append("</coordinates>\n");
                sb.append("</LineString>\n");
            }
            return sb.toString();
        }
        return "";
    }
}
