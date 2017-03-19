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
import ovlweb.utils.interfaces.IKMLShape;
import ovlweb.utils.interfaces.IlKMLPlacemark;

/**
 *
 * @author Kenneth Keith <kkeith@mitre.org>
 */
public class PolyLine implements IKMLShape, IlKMLPlacemark{
    public final String GRAPHIC_NAME = "POLYLINE";
    public final String GRAPHIC_STD = "MIL-STD-2525C";
    public final String GRAPHIC_PATH = "None";
    public final String GRAPHIC_HIERARCHY = "None";
    public final String GRAPHIC_CODE = "---------------";
    public final int GRAPHIC_POINTS = 2;
    private ArrayList<LineString> lineSegments = new ArrayList<LineString>();
    private ArrayList<LatLonPointImpl> polyPoints = new ArrayList<LatLonPointImpl>();
    private double masterDistance = 0;

    public PolyLine(LatLonPointImpl... points) {
        for (LatLonPointImpl p : points) {
            polyPoints.add(new LatLonPointImpl(p));
        }
        processDashedPolyLine();
    }

    public PolyLine(ArrayList<LatLonPointImpl> points) {
        for (LatLonPointImpl p : points) {
            polyPoints.add(new LatLonPointImpl(p));
        }
        processDashedPolyLine();
    }

    private void processDashedPolyLine() {
        for (LatLonPointImpl p : polyPoints) {
            System.out.println(polyPoints.indexOf(p)+"/"+(polyPoints.size() - 1));
            if (polyPoints.indexOf(p) != (polyPoints.size() - 1)) {
                DashedLine dl = new DashedLine(p, polyPoints.get(polyPoints.indexOf(p)+1));
                lineSegments.add(dl);
            }
        }
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

    public String getKMLRepresentation() {
        if (lineSegments.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (LineString ds : lineSegments) {
                sb.append(ds.getKMLShape());
            }
            return sb.toString();
        }
        return "";
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
    
    public ArrayList<LineString> getPolyLineSegments(){
        return new ArrayList<LineString>(lineSegments);
    }
    
    @Override
    public String getKMLShape() {
        if (lineSegments.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (LineString ds : lineSegments) {
                sb.append(ds.getKMLShape());
            }
            return sb.toString();
        }
        return "";
    }

     @Override
    public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth) {
        if (!lineSegments.isEmpty()) {
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
