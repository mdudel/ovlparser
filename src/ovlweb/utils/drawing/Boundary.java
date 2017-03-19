package ovlweb.utils.drawing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import ovlweb.utils.LatLonPointImpl;
import ovlweb.utils.drawing.tacticalgraphics.AbstractTacticalGraphic;

/**
 *
 * @author Kenneth Keith <kkeith@mitre.org>
 */
public final class Boundary extends AbstractTacticalGraphic {
    //Graphic Constants

    public final String GRAPHIC_NAME = "BOUNDARY";
    public final String GRAPHIC_STD = "MIL-STD-2525C";
    public final String GRAPHIC_PATH = "None";
    public final String GRAPHIC_HIERARCHY = "None";
    public final String GRAPHIC_CODE = "---------------";
    public final String GRAPHIC_POINTS = "N";

    public Boundary(ArrayList<LatLonPointImpl> points) {
        graphicPoints.addAll(points);
        computeNormalGraphic();
    }

    @Override
    public void computeSimpleGraphic(LatLonPointImpl point, double bearing, double width, double height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void computeNormalGraphic() {
        for (LatLonPointImpl p : graphicPoints) {
            if (graphicPoints.indexOf(p) != graphicPoints.size() - 1) {
                graphicLines.add(new LineString(p, graphicPoints.get(graphicPoints.indexOf(p) + 1)));
            }
        }
    }

    @Override
    public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth) {
        if (graphicLines.size() > 0) {
            StringBuilder cKML = new StringBuilder();
            StringBuilder sliceKML = new StringBuilder();
            StringBuilder ringKML = new StringBuilder();

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

            if (graphicPoints.size() > 1) {
                cKML.append("<tr>\n");
                cKML.append("<td> Circle Points:</td>\n");
                for (LatLonPointImpl p : graphicPoints) {
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

            cKML.append("<MultiGeometry>\n");
            //Corridor Lines
            if (!graphicLines.isEmpty()) {
                for (LineString s : graphicLines) {
                    cKML.append(s.getKMLShape());
                }
            }
            cKML.append("</MultiGeometry>\n");
            cKML.append("</Placemark>\n");
            return cKML.toString();
        }
        return "";
    }
}
