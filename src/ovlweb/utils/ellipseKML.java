package ovlweb.utils;

import java.lang.Math.*;

public class ellipseKML {
    double lon;     //LON (x)
    double lat;     //LAT (y)
    double alt;     // Altitude
    double a;       // Semimajor axis
    double b;       // Semiminor axis
    double angle;   // Angle of the ellipse
    int steps;       // Number of points
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;

    public ellipseKML(double latCenter, double lonCenter, double altitude, double semiMajor, double semiMinor, double bearing, int pointNum) {
        lat = latCenter;
        lon = lonCenter;
        alt = altitude;
        a = semiMajor;
        b = semiMinor;
        angle = bearing;
        steps = pointNum;
    }

    private double range(double theta) {
        // Theta must be in radians
        double r = 0.0;
        // r(theta) = (a * b) /   ( ((b * cos(theta))^2) + ((a * sin(theta))^2) )^0.5
        r = (a * b) / Math.sqrt(Math.pow((b * Math.cos(theta)), 2) + Math.pow((a * Math.sin(theta)), 2));
        return r;
    }

    public String getKMLCoords() {
        String polarCoords = "";
        String rectCoords = "";
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
                dtheta -= 360.0;
            }
            polarCoords = polarCoords + "r(" + dtheta + ") = " + r + "\n";
            // Get rect coords (expect r in km)
            Bearing.findPoint(myCenter, dtheta, r, myPoint);
            // Get the first point
            if (i == 0) {
                firstPoint = myPoint.getLongitude() + "," + myPoint.getLatitude() + "," + alt + "\n";
            }
            rectCoords = rectCoords + myPoint.getLongitude() + "," + myPoint.getLatitude() + "," + alt + "\n";
        }
        rectCoords = rectCoords + firstPoint;
        polarCoords = polarCoords + "\n" + rectCoords;
        return rectCoords;
    }

    
    
    //getKMLCoords
    public String getKML(String EllipseTitle, String EllipseColor, String lineWidth, String EllipseFillColor) {
        if (EllipseColor.length() < 1) {
            EllipseColor = "ff0055ff";
        }
        if (EllipseFillColor.length() < 1) {
            EllipseFillColor = "4000aaff";
        }
        if (lineWidth.length() < 1) {
            lineWidth = "5";
        }
        String ellipseKML = "<Placemark><Style><LineStyle><color>";
        ellipseKML = ellipseKML + EllipseColor;
        ellipseKML = ellipseKML + "</color><width>" + lineWidth;
        ellipseKML = ellipseKML + "</width></LineStyle><PolyStyle><color>" + EllipseFillColor;
        ellipseKML = ellipseKML + "</color></PolyStyle>"
                + "	<BalloonStyle>"
                + "	<text>$[description]</text>"
                + "	</BalloonStyle>"
                + "</Style>";
        ellipseKML = ellipseKML + "<name><![CDATA[" + EllipseTitle;
        ellipseKML = ellipseKML + "]]></name>";
        ellipseKML = ellipseKML + "<description><![CDATA[" + EllipseTitle + "]]></description>";
        ellipseKML = ellipseKML + "<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>";
        ellipseKML = ellipseKML + getKMLCoords();
        ellipseKML = ellipseKML + "</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>";
        return ellipseKML;
    }//getKML
}
