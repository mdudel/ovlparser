/* -----------------------------------------------------------------------------
 *       UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED
 *                 (C) Copyright 2015, USAREUR G3 MCSD
 *       Martin Dudel, Rei Takeda, Ralph Forjan, Kenneth Keith, Chris Watts
 *                      Honorable Mention: Rich Kenner
 *                         ALL RIGHTS RESERVED
 *                 THIS NOTICE DOES NOT IMPLY PUBLICATION
 * -------------------------------------------------------------------------- */
package missilegraphics;

/**
 *
 * @author marty
 */
@Deprecated
public class fanKML {

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
    @Deprecated
    public fanKML(double latCenter,
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
    @Deprecated
    public String getKMLCoords() {
        String fanCoords = getKMLCoords(true);
        return fanCoords;
    }

    @Deprecated
    public String getKMLCoords(boolean withAltitude) {
        String fanCoords = "";
        double delbrg = brgstop - brgstart;
        int stepsize = (int) (delbrg) / steps;
        double az = 0.0;

        LatLonPointImpl myCenter = new LatLonPointImpl(lat, lon); // center
        LatLonPointImpl pt1 = new LatLonPointImpl();  //fan corner points 1..4
        LatLonPointImpl pt2 = new LatLonPointImpl();
        LatLonPointImpl pt3 = new LatLonPointImpl();
        LatLonPointImpl pt4 = new LatLonPointImpl();

        Bearing.findPoint(myCenter, brgstart, maxRange, pt1);
        if (withAltitude) {
            fanCoords = pt1.getLongitude() + ", " + pt1.getLatitude() + ", " + alt + " \n";
        } else {
            fanCoords = pt1.getLongitude() + ", " + pt1.getLatitude() + " ";
        }
        Bearing.findPoint(myCenter, brgstart, minRange, pt2);
        if (withAltitude) {
            fanCoords = fanCoords
                    + pt2.getLongitude() + ", " + pt2.getLatitude() + ", " + alt + " \n";
        } else {
            fanCoords = fanCoords
                    + pt2.getLongitude() + ", " + pt2.getLatitude() + " ";
        }
        // Get points on inner radius from pt2 to pt3
        for (int i = 1; i < steps; i += 1) {
            az = brgstart + (delbrg / steps) * i;
            Bearing.findPoint(myCenter, az, minRange, pt3);
            if (withAltitude) {
                fanCoords = fanCoords
                        + pt3.getLongitude() + ", " + pt3.getLatitude() + ", " + alt + " \n";
            } else {
                fanCoords = fanCoords
                        + pt3.getLongitude() + ", " + pt3.getLatitude() + " ";
            }
        }
        Bearing.findPoint(myCenter, brgstop, minRange, pt3);
        if (withAltitude) {
            fanCoords = fanCoords
                    + pt3.getLongitude() + ", " + pt3.getLatitude() + ", " + alt + " \n";
        } else {
            fanCoords = fanCoords
                    + pt3.getLongitude() + ", " + pt3.getLatitude() + " ";
        }
        Bearing.findPoint(myCenter, brgstop, maxRange, pt4);
        if (withAltitude) {
            fanCoords = fanCoords
                    + pt4.getLongitude() + ", " + pt4.getLatitude() + ", " + alt + " \n";
        } else {
            fanCoords = fanCoords
                    + pt4.getLongitude() + ", " + pt4.getLatitude() + " ";
        }
        // Get points on outer radius from pt4 to pt1
        for (int i = 1; i < steps; i += 1) {
            az = brgstop - (delbrg / steps) * i;
            Bearing.findPoint(myCenter, az, maxRange, pt4);
            if (withAltitude) {
                fanCoords = fanCoords
                        + pt4.getLongitude() + ", " + pt4.getLatitude() + ", " + alt + " \n";
            } else {
                fanCoords = fanCoords
                        + pt4.getLongitude() + ", " + pt4.getLatitude() + " ";
            }
        }
        if (withAltitude) {
            fanCoords = fanCoords
                    + pt1.getLongitude() + ", " + pt1.getLatitude() + ", " + alt + "\n";
        } else {
            fanCoords = fanCoords
                    + pt1.getLongitude() + ", " + pt1.getLatitude();
        }
        return fanCoords;
    }//getKMLCoords
    @Deprecated
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
        String graphicKML = "<Placemark><Style><LineStyle><color>";
        graphicKML = graphicKML + GraphicColor;
        graphicKML = graphicKML + "</color><width>" + lineWidth;
        graphicKML = graphicKML + "</width></LineStyle><PolyStyle><color>" + GraphicFillColor;
        graphicKML = graphicKML + "</color></PolyStyle>"
                + "	<BalloonStyle>"
                + "	<text>$[description]</text>"
                + "	</BalloonStyle>"
                + "</Style>";
        graphicKML = graphicKML + "<name><![CDATA[" + GraphicTitle;
        graphicKML = graphicKML + "]]></name>";
        graphicKML = graphicKML + "<description><![CDATA[" + GraphicTitle + "]]></description>";
        graphicKML = graphicKML + "<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>";
        graphicKML = graphicKML + getKMLCoords();
        graphicKML = graphicKML + "</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>";
        return graphicKML;
    }//getKML
}
