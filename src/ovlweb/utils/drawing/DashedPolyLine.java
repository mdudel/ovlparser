/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils.drawing;

import ovlweb.utils.interfaces.IKMLShape;
import ovlweb.utils.interfaces.IlKMLPlacemark;
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
public class DashedPolyLine implements IKMLShape, IlKMLPlacemark {
    public final String GRAPHIC_NAME = "DASHEDPOLYLINE";
    public final String GRAPHIC_STD = "MIL-STD-2525C";
    public final String GRAPHIC_PATH = "None";
    public final String GRAPHIC_HIERARCHY = "None";
    public final String GRAPHIC_CODE = "---------------";
    public final int GRAPHIC_POINTS = 2;
    private ArrayList<DashedLine> dashSegments = new ArrayList<DashedLine>();
    private ArrayList<LatLonPointImpl> polyPoints = new ArrayList<LatLonPointImpl>();

    public DashedPolyLine(LatLonPointImpl... points) {
        for (LatLonPointImpl p : points) {
            polyPoints.add(new LatLonPointImpl(p));
        }
        processDashedPolyLine();
    }

    public DashedPolyLine(ArrayList<LatLonPointImpl> points) {
        for (LatLonPointImpl p : points) {
            polyPoints.add(new LatLonPointImpl(p));
        }
        processDashedPolyLine();
    }

    private void processDashedPolyLine() {
        for (LatLonPointImpl p : polyPoints) {
            System.out.println(polyPoints.indexOf(p) + "/" + (polyPoints.size() - 1));
            if (polyPoints.indexOf(p) != (polyPoints.size() - 1)) {
                DashedLine dl = new DashedLine(p, polyPoints.get(polyPoints.indexOf(p) + 1));
                dashSegments.add(dl);
            }
        }
    }

    public double getPolyDistance() {
        ArrayList<LineString> lines = new ArrayList<LineString>();
        double distance = 0;
        for (LatLonPointImpl p : polyPoints) {
            if (polyPoints.indexOf(p) != polyPoints.size() - 1) {
                LineString ls = new LineString(p, polyPoints.get(polyPoints.indexOf(p) + 1));
                lines.add(ls);
            }
        }
        if (!lines.isEmpty()) {
            for (LineString ls : lines) {
                distance += ls.getDistance();
            }
        }
        return distance;
    }

    public double getAzimuth(LatLonPointImpl point1, LatLonPointImpl point2) {
        Bearing b = new Bearing();
        Bearing.calculateBearing(point1, point2, b);
        return b.getAngle();
    }

    public double getBackAzimuth(LatLonPointImpl point1, LatLonPointImpl point2) {
        Bearing b = new Bearing();
        Bearing.calculateBearing(point1, point2, b);
        return b.getBackAzimuth();
    }

    public double getDistance(LatLonPointImpl point1, LatLonPointImpl point2) {
        Bearing b = new Bearing();
        Bearing.calculateBearing(point1, point2, b);
        return b.getDistance();
    }

    public LatLonPointImpl getMiddlePoint(LatLonPointImpl point1, LatLonPointImpl point2) {
        if (point1 != null && point2 != null) {
            LatLonPointImpl returnPoint = new LatLonPointImpl();
            Bearing b = new Bearing();
            Bearing.findPoint(point1, this.getAzimuth(point1, point2), this.getDistance(point1, point2) / 2, returnPoint);
            return returnPoint;
        }
        return null;
    }

    public LatLonPointImpl getPointAlongLineMinus(LatLonPointImpl point1, LatLonPointImpl point2, double distance) {
        if (point1 != null && point2 != null) {
            LatLonPointImpl returnPoint = new LatLonPointImpl();
            Bearing b = new Bearing();
            Bearing.calculateBearing(point1, point2, b);
            Bearing.findPoint(point1, b.getAngle(), b.getDistance() - distance, returnPoint);
            return returnPoint;
        }
        return null;
    }

    public LatLonPointImpl getPointAlongLinePlus(LatLonPointImpl point1, LatLonPointImpl point2, double distance) {
        if (point1 != null && point2 != null) {
            LatLonPointImpl returnPoint = new LatLonPointImpl();
            Bearing b = new Bearing();
            Bearing.calculateBearing(point1, point2, b);
            Bearing.findPoint(point1, b.getAngle(), b.getDistance() + distance, returnPoint);
            return returnPoint;
        }
        return null;
    }

    @Override
    public String getKMLShape() {
        if (dashSegments.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (DashedLine ds : dashSegments) {
                sb.append(ds.getKMLShape());
            }
            return sb.toString();
        }
        return "";
    }

    @Override
    public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth) {
        if (!dashSegments.isEmpty()) {
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

            if (!polyPoints.isEmpty()) {
                cKML.append("<tr>\n");
                cKML.append("<td> Line Points:</td>\n");
                for (LatLonPointImpl p : polyPoints) {
                    cKML.append("<td> Lat: ").append(p.getLatitude()).append("<br> Lon: ").append(p.getLongitude()).append("</td>\n");
                }
                cKML.append("</tr>\n");
            }

            cKML.append("<tr>\n");
            cKML.append("<td> Line Distance:</td>\n");
            cKML.append("<td>").append(getPolyDistance()).append("KM"+"</td>\n");
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
            cKML.append(polyPoints.get(0).getLongitude()).append(",").append(polyPoints.get(0).getLatitude()).append(",0\n");
            cKML.append("</coordinates>\n");
            cKML.append("</Point>\n");
            cKML.append("</Placemark>\n");
            return cKML.toString();
        }
        return "";
    }
}
