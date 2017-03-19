/* 
 * -----------------------------------------------------------------------------
 *        UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED
 *                  (C) Copyright 2015, USAREUR G3 MCSD
 *                        Kenny Keith, Marty Dudel
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
public class ArcKML {

    double lon;         //LON (x) degrees decimal
    double lat;         //LAT (y) degrees decimal
    double alt;         // Altitude
    double brgstart;    // Angle/bearing of fan (from North, ex E=90.0)
    // brg is in degrees. The clockwise START of the fan
    double brgstop;     // Stop bearing of fan (degrees) in a clockwise direction
    double radius;    // Radius of arc from center point (km)
    int steps;          // Number of points for inner and outer radius
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;

    public ArcKML(double latCenter,
            double lonCenter,
            double altitude,
            double radius,
            double brgStart,
            double brgEnd,
            int PointNum) {
        lat = latCenter;
        lon = lonCenter;
        alt = altitude;
        this.radius = radius;
        brgstart = brgStart;
        brgstop = brgEnd;
        steps = PointNum;
    }

    public ArcKML(double latCenter,
            double lonCenter,
            double radius,
            double brgStart,
            double brgEnd,
            int PointNum) {
        lat = latCenter;
        lon = lonCenter;
        alt = 0.0;
        this.radius = radius;
        brgstart = brgStart;
        brgstop = brgEnd;
        steps = PointNum;
    }

    public String getKMLCoords() {
        String arcCoords = getKMLCoords(true);
        return arcCoords;
    }

    public String getKMLCoords(boolean withAltitude) {
        StringBuilder sbArcCoords = new StringBuilder();
        //String arcCoords = "";
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
        LatLonPointImpl pt0 = new LatLonPointImpl();  //fan corner points 1..4
        LatLonPointImpl pt1 = new LatLonPointImpl();

        Bearing.findPoint(myCenter, brgstart, radius, pt0);
        Bearing.findPoint(myCenter, brgstop, radius, pt1);

        if (withAltitude) {
            sbArcCoords.append(pt1.getLongitude()).append(",").append(pt1.getLatitude()).append(",").append(alt).append(" \n");
        } else {
            sbArcCoords.append(pt1.getLongitude()).append(",").append(pt1.getLatitude()).append(" \n");
        }
        // TODO: Ensure arcs cross 0
        // Get points on  radius from pt1 to pt0
        for (int i = 1; i < steps; i += 1) {
            az = brgstop - (delbrg / steps) * i;
            Bearing.findPoint(myCenter, az, radius, pt1);
            if (withAltitude) {
                sbArcCoords.append(pt1.getLongitude()).append(",").append(pt1.getLatitude()).append(",").append(alt).append(" \n");
            } else {
                sbArcCoords.append(pt1.getLongitude()).append(",").append(pt1.getLatitude()).append(" \n");
            }
        }
        if (withAltitude) {
            sbArcCoords.append(pt0.getLongitude()).append(",").append(pt0.getLatitude()).append(",").append(alt).append(" \n");
        } else {
            sbArcCoords.append(pt0.getLongitude()).append(",").append(pt0.getLatitude()).append(" \n");
        }
        return sbArcCoords.toString();
    }//getKMLCoords

    public String getKML(String GraphicTitle,
            String GraphicColor,
            String lineWidth) {
        if (GraphicColor.length() < 1) {
            GraphicColor = "ff0055ff";
        }

        if (lineWidth.length() < 1) {
            lineWidth = "5";
        }
        StringBuilder kml = new StringBuilder();
        kml.append("<Placemark><Style><LineStyle><color>");
        kml.append(GraphicColor);
        kml.append("</color><width>");
        kml.append(lineWidth);
        kml.append("</width></LineStyle>");
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
        kml.append("<LineString><tessellate>1</tessellate><coordinates>");
        kml.append(getKMLCoords());
        kml.append("</coordinates></LineString></Placemark>");
        return kml.toString();
    }//getKML

    public String getKML(String GraphicTitle,
            String description,
            String GraphicColor,
            String lineWidth) {
        if (GraphicColor.length() < 1) {
            GraphicColor = "ff0055ff";
        }

        if (lineWidth.length() < 1) {
            lineWidth = "5";
        }
        StringBuilder kml = new StringBuilder();
        kml.append("<Placemark><Style><LineStyle><color>");
        kml.append(GraphicColor);
        kml.append("</color><width>");
        kml.append(lineWidth);
        kml.append("</width></LineStyle>");
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
        kml.append("<LineString><tessellate>1</tessellate><coordinates>");
        kml.append(getKMLCoords());
        kml.append("</coordinates></LineString></Placemark>");
        return kml.toString();
    }//getKML
}
