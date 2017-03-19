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
public class brgboxKML {

    double lon;         //LON (x) degrees decimal
    double lat;         //LAT (y) degrees decimal
    double alt;         // Altitude
    double len;         // Length
    double halfwidth;   // Halfwidth
    double halflength;  // Length / 2
    double brg;         // Angle/beraing of bearing box (from North, ex E=90.0)
    // brg is in degrees.
    double alpha;   // angle from bearing axis to corner point, radians
    double alphadeg; //alpha in degrees
    double r;       // distance from center point to corner point (2D)
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;

    @Deprecated
    public brgboxKML(double latCenter,
            double lonCenter,
            double altitude,
            double HalfWidth,
            double Length,
            double bearing) {
        lon = lonCenter;
        lat = latCenter;
        halfwidth = HalfWidth;
        halflength = Length / 2.0;
        brg = bearing;
        alt = altitude;
        r = Math.sqrt((halfwidth * halfwidth) + (halflength * halflength));
        alpha = Math.atan(halfwidth / halflength);
        alphadeg = alpha * rad2deg;
        //System.out.println("Bearing Box: ");
        //System.out.println("Center: "+lat+", "+lon);
        //System.out.println("Halfwidth: "+halfwidth+" Length: "+Length);
        //System.out.println("BrgBox bearing: "+brg);
        //System.out.println("Angle to point 1: "+alphadeg);

    }//brgboxKML

    @Deprecated
    public String getKMLCoords() {
        String brgboxCoords = getKMLCoords(true);
        return brgboxCoords;
    }

    @Deprecated
    public String getKMLCoords(boolean withAltitude) {
        String brgboxCoords = "";
        LatLonPointImpl myCenter = new LatLonPointImpl(lat, lon); // center
        LatLonPointImpl pt1 = new LatLonPointImpl();  //corner points 1..4
        LatLonPointImpl pt2 = new LatLonPointImpl();
        LatLonPointImpl pt3 = new LatLonPointImpl();
        LatLonPointImpl pt4 = new LatLonPointImpl();
        Bearing.findPoint(myCenter, (brg - alphadeg), r, pt1);
        Bearing.findPoint(myCenter, (brg + alphadeg), (-1.0 * r), pt2);
        Bearing.findPoint(myCenter, (brg - alphadeg), (-1.0 * r), pt3);
        Bearing.findPoint(myCenter, (brg + alphadeg), r, pt4);
        if (withAltitude) {
            brgboxCoords = pt1.getLongitude() + ", " + pt1.getLatitude() + ", " + alt + " \n";
            brgboxCoords = brgboxCoords + pt2.getLongitude() + ", " + pt2.getLatitude() + ", " + alt + " \n";
            brgboxCoords = brgboxCoords + pt3.getLongitude() + ", " + pt3.getLatitude() + ", " + alt + " \n";
            brgboxCoords = brgboxCoords + pt4.getLongitude() + ", " + pt4.getLatitude() + ", " + alt + " \n";
            brgboxCoords = brgboxCoords + pt1.getLongitude() + ", " + pt1.getLatitude() + ", " + alt + "\n";
        } else {
            brgboxCoords = pt1.getLongitude() + ", " + pt1.getLatitude() + " \n";
            brgboxCoords = brgboxCoords + pt2.getLongitude() + ", " + pt2.getLatitude() + " ";
            brgboxCoords = brgboxCoords + pt3.getLongitude() + ", " + pt3.getLatitude() + " ";
            brgboxCoords = brgboxCoords + pt4.getLongitude() + ", " + pt4.getLatitude() + " ";
            brgboxCoords = brgboxCoords + pt1.getLongitude() + ", " + pt1.getLatitude();
        }
        return brgboxCoords;
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
