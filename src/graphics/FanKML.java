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

public class FanKML {

    double lon;         //LON (x) degrees decimal
    double lat;         //LAT (y) degrees decimal
    double alt;         // Altitude
    double brgstart;    // Angle/bearing of fan (from North, ex E=90.0)
    // brg is in degrees. The clockwise START of the fan
    double brgstop;     // Stop bearing of fan (degrees) in a clockwise direction
    double minRange;    // Inner radius of fan from center point (km)
    double maxRange;    // Outer radius of fan from center (km)
    int steps;          // Number of points for inner and outer radius
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;

    public FanKML(double latCenter,
            double lonCenter,
            double altitude,
            double MinRange,
            double MaxRange,
            double brgStart,
            double brgEnd,
            int PointNum) {
        lat = latCenter;
        lon = lonCenter;
        alt = altitude;
        minRange = MinRange;
        maxRange = MaxRange;
        brgstart = brgStart;
        brgstop = brgEnd;
        steps = PointNum;
    }

    public String getKMLCoords() {
        String fanCoords = getKMLCoords(true);
        return fanCoords;
    }

    public String getKMLCoords(boolean withAltitude) {
        StringBuilder sbFanCoords = new StringBuilder();
        //String fanCoords = "";
        double delbrg = brgstop - brgstart;
        if (delbrg < 0) {
            delbrg += 360;
        }
        if (delbrg > 360) {
            delbrg -= 360;
        }
        int stepsize = (int) (delbrg) / steps;
        double az = 0.0;

        LatLonPointImpl myCenter = new LatLonPointImpl(lat, lon); // center
        LatLonPointImpl pt1 = new LatLonPointImpl();  //fan corner points 1..4
        LatLonPointImpl pt2 = new LatLonPointImpl();
        LatLonPointImpl pt3 = new LatLonPointImpl();
        LatLonPointImpl pt4 = new LatLonPointImpl();

        Bearing.findPoint(myCenter, brgstart, maxRange, pt1);
        if (withAltitude) {
            sbFanCoords.append(pt1.getLongitude()).append(",").append(pt1.getLatitude()).append(",").append(alt).append(" \n");
        } else {
            sbFanCoords.append(pt1.getLongitude()).append(",").append(pt1.getLatitude()).append(" \n");
        }
        Bearing.findPoint(myCenter, brgstart, minRange, pt2);
        if (withAltitude) {
            sbFanCoords.append(pt2.getLongitude()).append(",").append(pt2.getLatitude()).append(",").append(alt).append(" \n");
        } else {
            sbFanCoords.append(pt2.getLongitude()).append(",").append(pt2.getLatitude()).append(" \n");
        }
        // Get points on inner radius from pt2 to pt3
        for (int i = 1; i < steps; i += 1) {
            az = brgstart + (delbrg / steps) * i;
            Bearing.findPoint(myCenter, az, minRange, pt3);
            if (withAltitude) {
                sbFanCoords.append(pt3.getLongitude()).append(",").append(pt3.getLatitude()).append(",").append(alt).append(" \n");
            } else {
                sbFanCoords.append(pt3.getLongitude()).append(",").append(pt3.getLatitude()).append(" \n");
            }
        }
        Bearing.findPoint(myCenter, brgstop, minRange, pt3);
        if (withAltitude) {
            sbFanCoords.append(pt3.getLongitude()).append(",").append(pt3.getLatitude()).append(",").append(alt).append(" \n");
        } else {
            sbFanCoords.append(pt3.getLongitude()).append(",").append(pt3.getLatitude()).append(" \n");
        }
        Bearing.findPoint(myCenter, brgstop, maxRange, pt4);
        if (withAltitude) {
            sbFanCoords.append(pt4.getLongitude()).append(",").append(pt4.getLatitude()).append(",").append(alt).append(" \n");
        } else {
            sbFanCoords.append(pt4.getLongitude()).append(",").append(pt4.getLatitude()).append(" \n");
        }
        // Get points on outer radius from pt4 to pt1
        for (int i = 1; i < steps; i += 1) {
            az = brgstop - (delbrg / steps) * i;
            Bearing.findPoint(myCenter, az, maxRange, pt4);
            if (withAltitude) {
                sbFanCoords.append(pt4.getLongitude()).append(",").append(pt4.getLatitude()).append(",").append(alt).append(" \n");
            } else {
                sbFanCoords.append(pt4.getLongitude()).append(",").append(pt4.getLatitude()).append(" \n");
            }
        }
        if (withAltitude) {
            sbFanCoords.append(pt1.getLongitude()).append(",").append(pt1.getLatitude()).append(",").append(alt).append(" \n");
        } else {
            sbFanCoords.append(pt1.getLongitude()).append(",").append(pt1.getLatitude()).append(" \n");
        }
        return sbFanCoords.toString();
    }//getKMLCoords

    public String getKML(String GraphicTitle,
            String GraphicColor,
            String lineWidth,
            String GraphicFillColor) {
        if (GraphicColor.length() < 1) {
            GraphicColor = "ff0055ff";
        }
        if (GraphicFillColor.length() < 1) {
            GraphicFillColor = "4000aaff";
        }
        if (lineWidth.length() < 1) {
            lineWidth = "5";
        }
        StringBuilder kml = new StringBuilder();
        kml.append("<Placemark><Style><LineStyle><color>");
        kml.append(GraphicColor);
        kml.append("</color><width>");
        kml.append(lineWidth);
        kml.append("</width></LineStyle><PolyStyle><color>");
        kml.append(GraphicFillColor);
        kml.append("</color></PolyStyle>");
        kml.append("	<BalloonStyle>");
        kml.append("	<text>$[description]</text>");
        kml.append("	</BalloonStyle>");
        kml.append("</Style>");
        kml.append("<name><![CDATA[");
        kml.append(GraphicTitle);
        kml.append("]]></name>");
        kml.append("<description><![CDATA[");
        kml.append(GraphicTitle);
        kml.append("]]></description>");
        kml.append("<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>");
        kml.append(getKMLCoords());
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>");
        return kml.toString();
    }//getKML

    public String getKML(String GraphicTitle,
            String description,
            String GraphicColor,
            String lineWidth,
            String GraphicFillColor) {
        if (GraphicColor.length() < 1) {
            GraphicColor = "ff0055ff";
        }
        if (GraphicFillColor.length() < 1) {
            GraphicFillColor = "4000aaff";
        }
        if (lineWidth.length() < 1) {
            lineWidth = "5";
        }
        StringBuilder kml = new StringBuilder();
        kml.append("<Placemark><Style><LineStyle><color>");
        kml.append(GraphicColor);
        kml.append("</color><width>");
        kml.append(lineWidth);
        kml.append("</width></LineStyle><PolyStyle><color>");
        kml.append(GraphicFillColor);
        kml.append("</color></PolyStyle>");
        kml.append("	<BalloonStyle>");
        kml.append("	<text>$[description]</text>");
        kml.append("	</BalloonStyle>");
        kml.append("</Style>");
        kml.append("<name><![CDATA[");
        kml.append(GraphicTitle);
        kml.append("]]></name>");
        kml.append("<description><![CDATA[");
        kml.append("<b>").append(GraphicTitle).append("</b><br>");
        kml.append(description);
        kml.append("]]></description>");
        kml.append("<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>");
        kml.append(getKMLCoords());
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>");
        return kml.toString();
    }//getKML
}
