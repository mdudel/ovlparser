/*
 * -----------------------------------------------------------------------------
 *        UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED
 *                  (C) Copyright 2015, USAREUR G3 MCSD
 *                          ALL RIGHTS RESERVED
 *                  THIS NOTICE DOES NOT IMPLY PUBLICATION
 * -----------------------------------------------------------------------------
 */
package graphics;

import missilegraphics.Bearing;
import missilegraphics.LatLonPointImpl;

/**
 *
 * @author marty
 */
public class EllipticalArcKML {

    double lon;     //LON (x)
    double lat;     //LAT (y)
    double alt;     // Altitude
    double a;       // Semimajor axis (in km)
    double b;       // Semiminor axis (in km)
    double angle;   // Angle of the ellipse
    int steps;       // Number of points
    double startBrg;    // The start bearing of the elli-arc (with respect to N)
    double endBrg;      // The end bearing of the elli-arc (with respect to N)    
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;
    StringBuilder dbug = new StringBuilder("<br>");
    double startOffset;
    double endOffset;

    public EllipticalArcKML(double latCenter,
            double lonCenter,
            double altitude,
            double semiMajor,
            double semiMinor,
            double bearing,
            int pointNum,
            double startBrg,
            double endBrg) {
        lat = latCenter;
        lon = lonCenter;
        alt = altitude;
        a = semiMajor;
        b = semiMinor;
        angle = bearing;
        steps = pointNum;
        this.startBrg = startBrg;
        this.endBrg = endBrg;
        startOffset = startBrg - angle;
        endOffset = endBrg - angle;
        dbug.append("START BRG: ").append(startBrg).append("<br>END BRG: ").append(endBrg).append("<br>");
        dbug.append("START OFFSET: ").append(startOffset).append("<br>END OFFSET: ").append(endOffset).append("<br>");
        //System.out.println("Center: "+lat+", "+lon);
        //System.out.println("Semi Major: "+a+" Semi Minor: "+b);
        //System.out.println("Ellipse bearing: "+angle);
    }

    public EllipticalArcKML(double latCenter,
            double lonCenter,
            double semiMajor,
            double semiMinor,
            double bearing,
            int pointNum,
            double startBrg,
            double endBrg) {
        lat = latCenter;
        lon = lonCenter;
        alt = 0.0;
        a = semiMajor;
        b = semiMinor;
        angle = bearing;
        steps = pointNum;
        this.startBrg = startBrg;
        this.endBrg = endBrg;
        startOffset = startBrg - angle;
        endOffset = endBrg - angle;
        dbug.append("START BRG: ").append(startBrg).append("<br>END BRG: ").append(endBrg).append("<br>");
        dbug.append("START OFFSET: ").append(startOffset).append("<br>END OFFSET: ").append(endOffset).append("<br>");
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
        return getKMLCoords(false);
    }

    public String getKMLCoords(boolean withAltitude) {
        StringBuilder sbRectCoords = new StringBuilder();
        LatLonPointImpl myCenter = new LatLonPointImpl(lat, lon);
        LatLonPointImpl myPoint = new LatLonPointImpl();
        int stepsize = 360 / steps;
        double radBrg = angle * deg2rad;
        System.out.println();
        double brg0 = this.startOffset;
        double brg1 = this.endOffset;
        if (brg0 > brg1) {
            brg1 = brg1 + 360.0;
        }
        for (double b = brg0; b <= brg1; b += stepsize) {

            double alpha = b * deg2rad;

            double theta = radBrg + alpha;
            //System.out.println("[" + i + "] Offset " + b + " from " + angle + " calculated at " + (theta * this.rad2deg));
            double r = range(alpha);
            double dtheta = theta * rad2deg;
            if (dtheta > 360.0) {
                dtheta = dtheta - 360.0;
            }

            // Get rect coords (expect r in km)
            Bearing.findPoint(myCenter, dtheta, r, myPoint);

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
        //sbRectCoords.append(firstPoint);
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
        kml.append("<Placemark><Style><LineStyle><color>\n");
        //String ellipseKML = "<Placemark><Style><LineStyle><color>";
        kml.append(EllipseColor);
        //ellipseKML = ellipseKML + EllipseColor;
        kml.append("</color><width>\n");
        kml.append(lineWidth);
        //ellipseKML = ellipseKML + "</color><width>" + lineWidth;
        kml.append("</width></LineStyle><PolyStyle><color>\n");
        kml.append(EllipseFillColor);
        //ellipseKML = ellipseKML + "</width></LineStyle><PolyStyle><color>" + EllipseFillColor;
        kml.append("</color></PolyStyle>\n");
        kml.append("	<BalloonStyle>\n");
        kml.append("	<text>$[description]</text>\n");
        kml.append("	</BalloonStyle>\n");
        kml.append("</Style>\n");
//        ellipseKML = ellipseKML + "</color></PolyStyle>"
//                + "	<BalloonStyle>"
//                + "	<text>$[description]</text>"
//                + "	</BalloonStyle>"
//                + "</Style>";
        kml.append("<name><![CDATA[");
        kml.append(EllipseTitle);
        //ellipseKML = ellipseKML + "<name><![CDATA[" + EllipseTitle;
        kml.append("]]></name>\n");
        //ellipseKML = ellipseKML + "]]></name>";
        kml.append("<description><![CDATA[");
        kml.append(EllipseTitle);
        //kml.append(dbug.toString());
        kml.append("]]></description>\n");
        //ellipseKML = ellipseKML + "<description><![CDATA[" + EllipseTitle + "]]></description>";
        kml.append("<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>\n");
        //ellipseKML = ellipseKML + "<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>";
        kml.append(getKMLCoords());
        //ellipseKML = ellipseKML + getKMLCoords();
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>\n");
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
        kml.append("<Placemark><Style><LineStyle><color>\n");
        kml.append(EllipseColor);
        kml.append("</color><width>\n");
        kml.append(lineWidth);
        kml.append("</width></LineStyle><PolyStyle><color>\n");
        kml.append(EllipseFillColor);
        kml.append("</color></PolyStyle>\n");
        kml.append("	<BalloonStyle>\n");
        kml.append("	<text>$[description]</text>\n");
        kml.append("	</BalloonStyle>\n");
        kml.append("</Style>\n");

        kml.append("<name><![CDATA[");
        kml.append(EllipseTitle);
        kml.append("]]></name>\n");

        kml.append("<description><![CDATA[");
        kml.append("<b>").append(EllipseTitle).append("</b><br>");
        kml.append(description);
        //kml.append(dbug.toString());
        kml.append("]]></description>\n");

        kml.append("<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>\n");
        kml.append(getKMLCoords());
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>\n");

        return kml.toString();
    }//getKML    
}
