/*
 * -----------------------------------------------------------------------------
 *        UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED
 *                  (C) Copyright 2015, USAREUR G3 MCSD
 *                          ALL RIGHTS RESERVED
 *                  THIS NOTICE DOES NOT IMPLY PUBLICATION
 * -----------------------------------------------------------------------------
 */
package graphics;

/**
 *
 * @author marty Rewrite of original ellipseKML using SrtingBuilder vs String
 * concats
 */
import missilegraphics.Bearing;
import missilegraphics.LatLonPointImpl;

public class EllipseKML {

    double lon;     //LON (x)
    double lat;     //LAT (y)
    double alt;     // Altitude
    double a;       // Semimajor axis (in km)
    double b;       // Semiminor axis (in km)
    double angle;   // Angle of the ellipse
    int steps;       // Number of points
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;

    public EllipseKML(double latCenter,
            double lonCenter,
            double altitude,
            double semiMajor,
            double semiMinor,
            double bearing,
            int pointNum) {
        lat = latCenter;
        lon = lonCenter;
        alt = altitude;
        a = semiMajor;
        b = semiMinor;
        angle = bearing;
        steps = pointNum;
        //System.out.println("Center: "+lat+", "+lon);
        //System.out.println("Semi Major: "+a+" Semi Minor: "+b);
        //System.out.println("Ellipse bearing: "+angle);
    }

    private double range(double theta) {
        // Theta must be in radians
        double r = 0.0;
        // r(theta) = (a * b) /   ( ((b * cos(theta))^2) + ((a * sin(theta))^2) )^0.5
        r = (a * b) / Math.sqrt(Math.pow((b * Math.cos(theta)), 2) + Math.pow((a * Math.sin(theta)), 2));
        return r;
    }

    public String getKMLCoords() {
        StringBuilder sbRectCoords = new StringBuilder();
        //String polarCoords="";
        //String rectCoords = "";
        String firstPoint = "";
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
            //polarCoords = polarCoords + "r("+dtheta+") = "+r+"\n";
            // Get rect coords (expect r in km)
            Bearing.findPoint(myCenter, dtheta, r, myPoint);
            // Get the first point
            if (i == 0) {
                firstPoint = myPoint.getLongitude() + ","
                        + myPoint.getLatitude() + "," + alt + "\n";
            }
            sbRectCoords.append(myPoint.getLongitude()).append(",");
            sbRectCoords.append(myPoint.getLatitude()).append(",");
            sbRectCoords.append(alt).append("\n");
            //rectCoords = rectCoords + myPoint.getLongitude() + "," + myPoint.getLatitude() + "," + alt + "\n";
        }
        //rectCoords = rectCoords + firstPoint;
        sbRectCoords.append(firstPoint);
        //polarCoords = polarCoords + "\n" + rectCoords;
        return sbRectCoords.toString();
    }

    public String getKMLCoords(boolean withAltitude) {
        StringBuilder sbRectCoords = new StringBuilder();
        //String polarCoords = "";
        //String rectCoords = "";
        String firstPoint = "";
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
            //polarCoords = polarCoords + "r(" + dtheta + ") = " + r + "\n";
            // Get rect coords (expect r in km)
            Bearing.findPoint(myCenter, dtheta, r, myPoint);
            // Get the first point
            if (i == 0) {
                if (withAltitude) {
                    firstPoint = myPoint.getLongitude() + ","
                            + myPoint.getLatitude() + "," + alt + "\n";
                } else {
                    firstPoint = myPoint.getLongitude() + ","
                            + myPoint.getLatitude();
                }

            }
            if (withAltitude) {
                sbRectCoords.append(myPoint.getLongitude()).append(",");
                sbRectCoords.append(myPoint.getLatitude()).append(",");
                sbRectCoords.append(alt).append("\n");
                //rectCoords = rectCoords + myPoint.getLongitude() + "," + myPoint.getLatitude() + "," + alt + " \n";
            } else {
                sbRectCoords.append(myPoint.getLongitude()).append(",");
                sbRectCoords.append(myPoint.getLatitude()).append(" \n");
                //rectCoords = rectCoords + myPoint.getLongitude() + "," + myPoint.getLatitude() + " ";
            }

        }
        //rectCoords = rectCoords + firstPoint;
        sbRectCoords.append(firstPoint);
        //polarCoords = polarCoords + "\n" + rectCoords;
        return sbRectCoords.toString();
    }//getKMLCoords

    public String getKML(String EllipseTitle,
            String EllipseColor,
            String lineWidth,
            String EllipseFillColor) {
        if (EllipseColor.length() < 1) {
            EllipseColor = "ff0055ff";
        }
        if (EllipseFillColor.length() < 1) {
            EllipseFillColor = "4000aaff";
        }
        if (lineWidth.length() < 1) {
            lineWidth = "5";
        }
        StringBuilder kml = new StringBuilder();
        kml.append("<Placemark><Style><LineStyle><color>");
        //String ellipseKML = "<Placemark><Style><LineStyle><color>";
        kml.append(EllipseColor);
        //ellipseKML = ellipseKML + EllipseColor;
        kml.append("</color><width>");
        kml.append(lineWidth);
        //ellipseKML = ellipseKML + "</color><width>" + lineWidth;
        kml.append("</width></LineStyle><PolyStyle><color>");
        kml.append(EllipseFillColor);
        //ellipseKML = ellipseKML + "</width></LineStyle><PolyStyle><color>" + EllipseFillColor;
        kml.append("</color></PolyStyle>");
        kml.append("	<BalloonStyle>");
        kml.append("	<text>$[description]</text>");
        kml.append("	</BalloonStyle>");
        kml.append("</Style>");
//        ellipseKML = ellipseKML + "</color></PolyStyle>"
//                + "	<BalloonStyle>"
//                + "	<text>$[description]</text>"
//                + "	</BalloonStyle>"
//                + "</Style>";
        kml.append("<name><![CDATA[");
        kml.append(EllipseTitle);
        //ellipseKML = ellipseKML + "<name><![CDATA[" + EllipseTitle;
        kml.append("]]></name>");
        //ellipseKML = ellipseKML + "]]></name>";
        kml.append("<description><![CDATA[");
        kml.append(EllipseTitle);
        kml.append("]]></description>");
        //ellipseKML = ellipseKML + "<description><![CDATA[" + EllipseTitle + "]]></description>";
        kml.append("<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>");
        //ellipseKML = ellipseKML + "<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>";
        kml.append(getKMLCoords());
        //ellipseKML = ellipseKML + getKMLCoords();
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>");
        //ellipseKML = ellipseKML + "</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>";
        return kml.toString();
    }//getKML

    public String getKML(String EllipseTitle,
            String description,
            String EllipseColor,
            String lineWidth,
            String EllipseFillColor) {
        if (EllipseColor.length() < 1) {
            EllipseColor = "ff0055ff";
        }
        if (EllipseFillColor.length() < 1) {
            EllipseFillColor = "4000aaff";
        }
        if (lineWidth.length() < 1) {
            lineWidth = "5";
        }
        StringBuilder kml = new StringBuilder();
        kml.append("<Placemark><Style><LineStyle><color>");
        kml.append(EllipseColor);
        kml.append("</color><width>");
        kml.append(lineWidth);
        kml.append("</width></LineStyle><PolyStyle><color>");
        kml.append(EllipseFillColor);
        kml.append("</color></PolyStyle>");
        kml.append("	<BalloonStyle>");
        kml.append("	<text>$[description]</text>");
        kml.append("	</BalloonStyle>");
        kml.append("</Style>");

        kml.append("<name><![CDATA[");
        kml.append(EllipseTitle);
        kml.append("]]></name>");

        kml.append("<description><![CDATA[");
        kml.append("<b>").append(EllipseTitle).append("</b><br>");
        kml.append(description);
        kml.append("]]></description>");
        kml.append("<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>");
        kml.append(getKMLCoords());
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>");

        return kml.toString();
    }//getKML    
}
