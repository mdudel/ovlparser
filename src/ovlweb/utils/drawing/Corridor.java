/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils.drawing;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import ovl.schema.gccs.CorridorType;
import ovlweb.utils.Bearing;
import ovlweb.utils.LatLonPointImpl;
import ovlweb.utils.drawing.tacticalgraphics.AbstractTacticalGraphic;

/**
 *
 * @author KKEITH
 */
public final class Corridor extends AbstractTacticalGraphic {
    //Graphic Constants
    public final String GRAPHIC_NAME = "CORRIDOR";
    public final String GRAPHIC_STD = "MIL-STD-2525C";
    public final String GRAPHIC_PATH = "None";
    public final String GRAPHIC_HIERARCHY = "None";
    public final String GRAPHIC_CODE = "---------------";
    public final int GRAPHIC_POINTS = 2;
    private ArrayList<LatLonPointImpl> circlePoints = new ArrayList<LatLonPointImpl>();
    private ArrayList<Ellipse> corridorCircles = new ArrayList<Ellipse>();
    private CorridorType corridor = null;
    private double width = 0;

    public Corridor(CorridorType corridor) {
        this.corridor = corridor;
        width = corridor.getWIDTH().doubleValue() / 2;
        computeNormalGraphic();
    }

    public Corridor(ArrayList<LatLonPointImpl> points) {
        if (points.size() >= 2 && circlePoints.addAll(points)) {
            computeNormalGraphic();
        }
    }

    public Corridor(LatLonPointImpl... points) {
        if (points.length >= 2) {
            if (circlePoints.addAll(Arrays.asList(points))) {
                computeNormalGraphic();
            }
        }
    }

    @Override
    public void computeNormalGraphic() {
        //Build LatLonPoints for each BigDecimal Pair
        for (JAXBElement<List<BigDecimal>> pair : corridor.getPOSITION()) {
            String[] point = pair.getValue().toString().replace("[", "").replace("]", "").split(",");
            double lat = Double.parseDouble(point[0]);
            double lon = Double.parseDouble(point[1].replace(" ", ""));
            LatLonPointImpl P1 = new LatLonPointImpl(lat, lon);
            circlePoints.add(P1);
        }

        for (LatLonPointImpl p : circlePoints) {
            Ellipse circle = new Ellipse(p.getLatitude(), p.getLongitude(), 0, width, width, 0, 100);
            corridorCircles.add(circle);

            if (circlePoints.indexOf(p) != circlePoints.size() - 1) {
                LatLonPointImpl drawPoint = new LatLonPointImpl();
                LatLonPointImpl drawPoint2 = new LatLonPointImpl();

                //Left Side Line
                Bearing b = new Bearing();
                LatLonPointImpl p1 = new LatLonPointImpl(p);
                LatLonPointImpl p2 = new LatLonPointImpl(circlePoints.get(circlePoints.indexOf(p) + 1));
                Bearing.calculateBearing(p1, p2, b);

                b.getBackAzimuth();
                Bearing.findPoint(p1, b.getAngle() - 90, width, drawPoint);
                Bearing.findPoint(p2, b.getBackAzimuth() + 90, width, drawPoint2);
                graphicLines.add(new LineString(drawPoint, drawPoint2));


                //Right Side Line
                Bearing b2 = new Bearing();
                LatLonPointImpl p3 = new LatLonPointImpl(p);
                LatLonPointImpl p4 = new LatLonPointImpl(circlePoints.get(circlePoints.indexOf(p) + 1));
                Bearing.calculateBearing(p3, p4, b);

                b.getBackAzimuth();
                Bearing.findPoint(p3, b.getAngle() + 90, width, drawPoint);
                Bearing.findPoint(p4, b.getBackAzimuth() - 90, width, drawPoint2);
                graphicLines.add(new LineString(drawPoint, drawPoint2));

                //Front Line
                Bearing b3 = new Bearing();
                LatLonPointImpl p5 = new LatLonPointImpl(p);
                LatLonPointImpl p6 = new LatLonPointImpl(circlePoints.get(circlePoints.indexOf(p) + 1));
                Bearing.calculateBearing(p5, p6, b3);

                b.getBackAzimuth();
                Bearing.findPoint(p5, b.getAngle(), width, drawPoint);
                Bearing.findPoint(p6, b.getBackAzimuth(), width, drawPoint2);
                graphicLines.add(new LineString(drawPoint, drawPoint2));
            }
        }
        for(Ellipse e:corridorCircles){
            graphicLines.addAll(e.getGraphicLines());
        }
    }

    @Override
    public void computeSimpleGraphic(LatLonPointImpl point, double bearing, double width, double height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth) {
        if (corridorCircles.size() > 1) {
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

            if (circlePoints.size() > 1) {
                cKML.append("<tr>\n");
                cKML.append("<td> Circle Points:</td>\n");
                for (LatLonPointImpl p : circlePoints) {
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

            //Corridor Circles
            if (!corridorCircles.isEmpty()) {
                for (Ellipse e : corridorCircles) {
                    cKML.append(e.getKMLShape());
                }
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
            cKML.append(circlePoints.get(0).getLongitude()).append(",").append(circlePoints.get(0).getLatitude()).append(",0\n");
            cKML.append("</coordinates>\n");
            cKML.append("</Point>\n");
            cKML.append("</Placemark>\n");
            return cKML.toString();
        }
        return "";
    }

    @Override
    public String getKMLShape() {
        if (graphicLines.isEmpty()) {
            return "";
        } else {
            StringBuilder returnString = new StringBuilder();
            for (LineString ls : graphicLines) {
                returnString.append(ls.getKMLShape());
            }
            for(Ellipse e:corridorCircles){
                returnString.append(e.getKMLShape());
            }
            return returnString.toString();
        }
    }

    public ArrayList<Ellipse> getCircles() {
        return new ArrayList<Ellipse>(corridorCircles);
    }

    public ArrayList<LineString> getCorridorLines() {
        return new ArrayList<LineString>(graphicLines);
    }

    public ArrayList<LatLonPointImpl> getCirclePoints() {
        return new ArrayList<LatLonPointImpl>(circlePoints);
    }

    public double getWidth() {
        return width;
    }
}
