/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils.drawing;

import ovlweb.utils.interfaces.IKMLShape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import ovlweb.utils.Bearing;
import ovlweb.utils.LatLonPointImpl;
import ovlweb.utils.drawing.tacticalgraphics.AbstractTacticalGraphic;

/**
 *
 * @author KKEITH
 */
public final class Ellipse extends AbstractTacticalGraphic implements IKMLShape{
    //Graphic Constants
    public final String GRAPHIC_NAME = "ELLIPSE";
    public final String GRAPHIC_STD = "MIL-STD-2525C";
    public final String GRAPHIC_PATH = "None";
    public final String GRAPHIC_HIERARCHY = "None";
    public final String GRAPHIC_CODE = "---------------";
    public final int GRAPHIC_POINTS = 1;
    public final String GRAPHIC_LABEL = "";
    double lon;     //LON (x)
    double lat;     //LAT (y)
    double alt;     // Altitude
    double a;       // Semimajor axis
    double b;       // Semiminor axis
    double angle;   // Angle of the ellipse
    int steps;       // Number of points
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;
    
    public Ellipse(double latCenter, double lonCenter, double altitude, double semiMajor, double semiMinor, double bearing, int pointNum) {
        lat = latCenter;
        lon = lonCenter;
        alt = altitude;
        a = semiMajor;
        b = semiMinor;
        angle = bearing;
        steps = pointNum;
        computeNormalGraphic();
    }

    @Override
    public void computeNormalGraphic() {
        String polarCoords = "";
        String rectCoords = "";
        LatLonPointImpl firstPoint = new LatLonPointImpl();
        LatLonPointImpl myCenter = new LatLonPointImpl(lat, lon);
        LatLonPointImpl myPoint = new LatLonPointImpl();
        int stepsize = 360 / steps;
        double radBrg = angle * deg2rad;
        for (int i = 0; i < 360; i += stepsize) {
            double alpha = (i) * deg2rad;
            double theta = radBrg + alpha;
            double r = range(alpha);
            double dtheta = theta * rad2deg;
            if (dtheta > 360.0) {
                dtheta = dtheta - 360.0;
            }
            polarCoords = polarCoords + "r(" + dtheta + ") = " + r + "\n";
            // Get rect coords (expect r in km)
            Bearing.findPoint(myCenter, dtheta, r, myPoint);
            // Get the first point
            if (i == 0) {
                firstPoint = new LatLonPointImpl(myPoint);
            }
            graphicPoints.add(new LatLonPointImpl(myPoint));
        }
        graphicPoints.add(firstPoint);
        
        for(LatLonPointImpl pt:graphicPoints){
            if(graphicPoints.indexOf(pt)==graphicPoints.size()-1){
                LineString ls = new LineString(pt,graphicPoints.get(0));
                graphicLines.add(ls);
            } else {
                LineString ls = new LineString(pt,graphicPoints.get(graphicPoints.indexOf(pt)+1));
                graphicLines.add(ls);
            }
        }
    }

    @Override
    public void computeSimpleGraphic(LatLonPointImpl point, double bearing, double width, double height) {
        if (graphicPoints.isEmpty()) {
            lat = point.getLatitude();
            lon = point.getLongitude();
            alt = 0;
            a = width;
            b = height;
            angle = bearing;
            steps = 100;

            String polarCoords = "";
            String rectCoords = "";
            LatLonPointImpl firstPoint = new LatLonPointImpl();
            LatLonPointImpl myCenter = new LatLonPointImpl(lat, lon);
            LatLonPointImpl myPoint = new LatLonPointImpl();
            int stepsize = 360 / steps;
            double radBrg = angle * deg2rad;
            for (int i = 0; i < 360; i += stepsize) {
                double alpha = (i) * deg2rad;
                double theta = radBrg + alpha;
                double r = range(alpha);
                double dtheta = theta * rad2deg;
                if (dtheta > 360.0) {
                    dtheta = dtheta - 360.0;
                }
                polarCoords = polarCoords + "r(" + dtheta + ") = " + r + "\n";
                // Get rect coords (expect r in km)
                Bearing.findPoint(myCenter, dtheta, r, myPoint);
                // Get the first point
                if (i == 0) {
                    firstPoint = new LatLonPointImpl(myPoint);
                }
                graphicPoints.add(myPoint);
            }
            graphicPoints.add(firstPoint);
        }
    }

    public ArrayList<LatLonPointImpl> getEllipsePoints() {
        return new ArrayList<LatLonPointImpl>(graphicPoints);
    }

    private double range(double theta) {
        // Theta must be in radians
        double r = 0.0;
        // r(theta) = (a * b) /   ( ((b * cos(theta))^2) + ((a * sin(theta))^2) )^0.5
        r = (a * b) / Math.sqrt(Math.pow((b * Math.cos(theta)), 2) + Math.pow((a * Math.sin(theta)), 2));
        return r;
    }

    public double getSemiMajor() {
        return a;
    }

    public double getAlt() {
        return alt;
    }

    public double getSemiMinor() {
        return b;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }

    public int getSteps() {
        return steps;
    }

    @Override
    public String getKMLShape() {
        StringBuilder returnString = new StringBuilder();
        if (graphicPoints.size() > 1) {
            for (LatLonPointImpl p : graphicPoints) {
                returnString.append("<LineString>\n");
                returnString.append("<extrude>0</extrude>\n");
                returnString.append("<tessellate>1</tessellate>\n");
                returnString.append("<coordinates>\n");
                returnString.append(p.getLongitude()).append(",").append(p.getLatitude()).append(",0\n");
                returnString.append("</coordinates>\n");
                returnString.append("</LineString>\n");
            }
        }
        return returnString.toString();
    }
    
    @Override
    public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth) {
        if (graphicLines.size() > 1) {
            StringBuilder eKML = new StringBuilder();
            
            eKML.append("<Placemark>\n");
            String lineColorRed = String.valueOf(linecolor.getRed());
            String lineColorGreen = String.valueOf(linecolor.getGreen());
            String lineColorBlue = String.valueOf(linecolor.getBlue());
            String lineColorString = lineColorRed + " " + lineColorGreen + " " + lineColorBlue;

            //Styles
            eKML.append("<Style>\n");
            eKML.append("<LineStyle>\n");
            eKML.append("<color>").append(icsf2KmlColorConvert(lineColorString)).append("</color>\n");
            eKML.append("<colorMode>normal</colorMode>\n");
            eKML.append("<width>").append(linewidth).append("</width>\n");
            eKML.append("</LineStyle>\n");
            eKML.append("<PolyStyle>\n");
            eKML.append("<color>").append(icsf2KmlColorConvert(lineColorString)).append("</color>\n");
            eKML.append("<colorMode>normal</colorMode>\n");
            eKML.append("<fill>0</fill>\n");
            eKML.append("</PolyStyle>\n");
            eKML.append("<LabelStyle>\n");
            eKML.append("<color>").append(icsf2KmlColorConvert(lineColorString)).append("</color>\n");
            eKML.append("</LabelStyle>\n");

            //Balloon Style
            eKML.append("<BalloonStyle>\n");
            eKML.append("<text>$[description]</text>\n");
            eKML.append("</BalloonStyle>\n");

            //Point Style
            eKML.append("<IconStyle>\n");
            eKML.append("<colorMode>normal</colorMode>\n");
            eKML.append("<Icon><href></href></Icon>\n");
            eKML.append("</IconStyle>\n");
            eKML.append("</Style>\n");

            eKML.append("<name>Tactical Graphic:"+GRAPHIC_NAME+"</name>\n");

            //Description Table
            eKML.append("<description>\n");
            eKML.append("<![CDATA[");
            eKML.append("<table border=\"1\" width=\"300\">");
            eKML.append("<tr>\n");
            eKML.append("<td> Name:</td>\n");
            eKML.append("<td>" + GRAPHIC_NAME + "</td>\n");
            eKML.append("</tr>\n");
            eKML.append("<tr>\n");
            eKML.append("<td> KML Created:</td>\n");
            eKML.append("<td>").append(new Date().toString()).append("</td>\n");
            eKML.append("</tr>\n");
            eKML.append("<tr>\n");
            eKML.append("<td> Symbol Code:</td>\n");
            eKML.append("<td>" + GRAPHIC_CODE + "</td>\n");
            eKML.append("</tr>\n");
            eKML.append("<tr>\n");
            eKML.append("<tr>\n");
            eKML.append("<td> Path:</td>\n");
            eKML.append("<td>" + GRAPHIC_PATH + "</td>\n");
            eKML.append("</tr>\n");
            eKML.append("<tr>\n");
            eKML.append("<td> Hierarchy:</td>\n");
            eKML.append("<td>" + GRAPHIC_HIERARCHY + "</td>\n");
            eKML.append("</tr>\n");
            eKML.append("<tr>\n");
            eKML.append("<td> Description:</td>\n");
            eKML.append("<td>").append(description).append("</td>\n");
            eKML.append("</tr>\n");

            eKML.append("<tr>\n");
            eKML.append("<td> Center Point:</td>\n");
            eKML.append("<td> Lat: ").append(lat).append("<br> Lon: ").append(lon).append("</td>\n");
            eKML.append("</tr>\n");
            
            eKML.append("<tr>\n");
            eKML.append("<td> Semi-Major:</td>\n");
            eKML.append("<td>").append(this.a).append("</td>\n");
            eKML.append("</tr>\n");
            
            eKML.append("<tr>\n");
            eKML.append("<td> Semi-Minor:</td>\n");
            eKML.append("<td>").append(this.b).append("</td>\n");
            eKML.append("</tr>\n");
            
            eKML.append("<tr>\n");
            eKML.append("<td> Bearing:</td>\n");
            eKML.append("<td>").append(this.angle).append("</td>\n");
            eKML.append("</tr>\n");
            
            eKML.append("<tr>\n");
            eKML.append("<td> Altitude:</td>\n");
            eKML.append("<td>").append(this.alt).append("</td>\n");
            eKML.append("</tr>\n");

//            eKML.append("<tr>\n");
//            eKML.append("<td> Image:</td>\n");
//            if (GRAPHIC_NAME.contains(" ")) {
//                String GRAPHICNAME = GRAPHIC_NAME.replaceAll(" ", "");
//                eKML.append("<td>" + "<img src='http://" + OVLWebServer.getServerAddress() + ":" + OVLWebServer.getServicePort() + "/Resources/" + GRAPHICNAME + "/'" + "/></td>\n");
//            } else {
//                eKML.append("<td>" + "<img src='http://" + OVLWebServer.getServerAddress() + ":" + OVLWebServer.getServicePort() + "/Resources/" + GRAPHIC_NAME + "/'" + "/></td>\n");
//            }
//            eKML.append("</tr>\n");

            eKML.append("</table>\n");
            eKML.append("<p> OVLWeb Created by: <br> Marty Dudel DSN: 314-370-7977 <br> Kenneth Keith DSN: 314-370-8080");
            eKML.append("]]>\n");
            eKML.append("</description>\n");

            //Dome Rings
            eKML.append("<MultiGeometry>\n");
            for(LineString s:graphicLines){
                eKML.append(s.getKMLShape());
            }
            eKML.append("</MultiGeometry>\n");
            eKML.append("</Placemark>\n");

            //Dome Label
            eKML.append("<Placemark>\n");
            eKML.append("<Style>\n");
            eKML.append("<IconStyle>\n");
            eKML.append("<colorMode>normal</colorMode>\n");
            eKML.append("<Icon><href></href></Icon>\n");
            eKML.append("</IconStyle>\n");
            eKML.append("</Style>\n");
            eKML.append("<name>").append(label).append("</name>\n");
            eKML.append("<Point>\n");
            eKML.append("<altitudeMode>clampToGround</altitudeMode>\n");
            eKML.append("<coordinates>\n");
            eKML.append(lon).append(",").append(lat).append(",0\n");
            eKML.append("</coordinates>\n");
            eKML.append("</Point>\n");
            eKML.append("</Placemark>\n");
            return eKML.toString();
        }
        return "";
    }
        
}
