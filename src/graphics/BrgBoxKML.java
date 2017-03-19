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
public class BrgBoxKML {

    double lon;         //LON (x) degrees decimal of reference point
    double lat;         //LAT (y) degrees decimal of reference point
    double alt;         // Altitude
    double len;         // Length (km)
    double halfwidth;   // Halfwidth (km)
    double halflength;  // Length / 2
    double length;
    double brg;         // Angle/bearing of bearing box (from North, ex E=90.0)
    // brg is in degrees.
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;

    double centerLat;   //LON (x) degrees decimal of estimated point in AOU
    double centerLon;   //LAT (y) degrees decimal of estimated point in AOU

    public BrgBoxKML(double latReference,
            double lonReference,
            double altitude,
            double HalfWidth,
            double Length,
            double bearing) {
        lon = lonReference;
        lat = latReference;
        halfwidth = HalfWidth;
        halflength = Length / 2.0;
        length = Length;
        brg = bearing;
        alt = altitude;
        LatLonPointImpl referencePoint = new LatLonPointImpl(lat, lon); 
        LatLonPointImpl cp = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, brg, halflength, cp);
        centerLat = cp.getLatitude();
        centerLon = cp.getLongitude();
    }//brgboxKML

    public BrgBoxKML(double latReference,
            double lonReference,
            double HalfWidth,
            double Length,
            double bearing) {
        lon = lonReference;
        lat = latReference;
        halfwidth = HalfWidth;
        halflength = Length / 2.0;
        length = Length;
        brg = bearing;
        alt = 0.0;
        LatLonPointImpl referencePoint = new LatLonPointImpl(lat, lon); 
        LatLonPointImpl cp = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, brg, halflength, cp);
        centerLat = cp.getLatitude();
        centerLon = cp.getLongitude();
    }//brgboxKML    

    public String getKMLCoords() {
        StringBuilder sbBrgBoxCoords = new StringBuilder();
        LatLonPointImpl referencePoint = new LatLonPointImpl(lat, lon); // center

        LatLonPointImpl ptL1 = new LatLonPointImpl();
        LatLonPointImpl ptL2 = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, brg, length, ptL1);
        Bearing.findPoint(referencePoint, brg, (-1.0 * halflength), ptL2);

        LatLonPointImpl ptC1 = new LatLonPointImpl();
        Bearing.findPoint(ptL1, (brg - 90.0), halfwidth, ptC1);
        LatLonPointImpl ptC2 = new LatLonPointImpl();
        Bearing.findPoint(ptL1, (brg + 90.0), halfwidth, ptC2);
        LatLonPointImpl ptC3 = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, (brg + 90.0), halfwidth, ptC3);
        LatLonPointImpl ptC4 = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, (brg - 90.0), halfwidth, ptC4);

        sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(",").append(alt).append(" \n");
        sbBrgBoxCoords.append(ptC2.getLongitude()).append(",").append(ptC2.getLatitude()).append(",").append(alt).append(" \n");
        sbBrgBoxCoords.append(ptC3.getLongitude()).append(",").append(ptC3.getLatitude()).append(",").append(alt).append(" \n");
        sbBrgBoxCoords.append(getKMLlobCoords());
        sbBrgBoxCoords.append(ptC4.getLongitude()).append(",").append(ptC4.getLatitude()).append(",").append(alt).append(" \n");
        sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(",").append(alt).append(" \n");
        //System.out.println(brgboxCoords);
        return sbBrgBoxCoords.toString();
    }

    public String getKMLCoords(boolean withAltitude) {
        StringBuilder sbBrgBoxCoords = new StringBuilder();
        LatLonPointImpl referencePoint = new LatLonPointImpl(lat, lon); // center

        LatLonPointImpl ptL1 = new LatLonPointImpl();
        LatLonPointImpl ptL2 = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, brg, length, ptL1);
        Bearing.findPoint(referencePoint, brg, (-1.0 * halflength), ptL2);

        LatLonPointImpl ptC1 = new LatLonPointImpl();
        Bearing.findPoint(ptL1, (brg - 90.0), halfwidth, ptC1);
        LatLonPointImpl ptC2 = new LatLonPointImpl();
        Bearing.findPoint(ptL1, (brg + 90.0), halfwidth, ptC2);
        LatLonPointImpl ptC3 = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, (brg + 90.0), halfwidth, ptC3);
        LatLonPointImpl ptC4 = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, (brg - 90.0), halfwidth, ptC4);
        if (withAltitude) {
            sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(ptC2.getLongitude()).append(",").append(ptC2.getLatitude()).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(ptC3.getLongitude()).append(",").append(ptC3.getLatitude()).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(getKMLlobCoords());
            sbBrgBoxCoords.append(ptC4.getLongitude()).append(",").append(ptC4.getLatitude()).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(",").append(alt).append(" \n");
        } else {
            sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(" \n");
            sbBrgBoxCoords.append(ptC2.getLongitude()).append(",").append(ptC2.getLatitude()).append(" \n");
            sbBrgBoxCoords.append(ptC3.getLongitude()).append(",").append(ptC3.getLatitude()).append(" \n");
            sbBrgBoxCoords.append(getKMLlobCoords());
            sbBrgBoxCoords.append(ptC4.getLongitude()).append(",").append(ptC4.getLatitude()).append(" \n");
            sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(" \n");
        }
        //System.out.println(brgboxCoords);
        return sbBrgBoxCoords.toString();
    }

    public String getSimpleKMLCoords() {
        return getSimpleKMLCoords(false);
    }

    public String getSimpleKMLCoords(boolean withAltitude) {
        StringBuilder sbBrgBoxCoords = new StringBuilder();
        LatLonPointImpl referencePoint = new LatLonPointImpl(lat, lon); // center

        LatLonPointImpl ptL1 = new LatLonPointImpl();
        LatLonPointImpl ptL2 = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, brg, halflength, ptL1);
        Bearing.findPoint(referencePoint, brg, (-1.0 * halflength), ptL2);

        LatLonPointImpl ptC1 = new LatLonPointImpl();
        Bearing.findPoint(ptL1, (brg - 90.0), halfwidth, ptC1);
        LatLonPointImpl ptC2 = new LatLonPointImpl();
        Bearing.findPoint(ptL1, (brg + 90.0), halfwidth, ptC2);
        LatLonPointImpl ptC3 = new LatLonPointImpl();
        Bearing.findPoint(ptL2, (brg + 90.0), halfwidth, ptC3);
        LatLonPointImpl ptC4 = new LatLonPointImpl();
        Bearing.findPoint(ptL2, (brg - 90.0), halfwidth, ptC4);
        if (withAltitude) {
            sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(ptC2.getLongitude()).append(",").append(ptC2.getLatitude()).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(ptC3.getLongitude()).append(",").append(ptC3.getLatitude()).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(ptC4.getLongitude()).append(",").append(ptC4.getLatitude()).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(",").append(alt).append(" \n");
        } else {
            sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(" \n");
            sbBrgBoxCoords.append(ptC2.getLongitude()).append(",").append(ptC2.getLatitude()).append(" \n");
            sbBrgBoxCoords.append(ptC3.getLongitude()).append(",").append(ptC3.getLatitude()).append(" \n");
            sbBrgBoxCoords.append(ptC4.getLongitude()).append(",").append(ptC4.getLatitude()).append(" \n");
            sbBrgBoxCoords.append(ptC1.getLongitude()).append(",").append(ptC1.getLatitude()).append(" \n");
        }
        //System.out.println(brgboxCoords);
        return sbBrgBoxCoords.toString();
    }

    public String getKMLlobCoords() {
        StringBuilder lob = new StringBuilder();
        LatLonPointImpl referencePoint = new LatLonPointImpl(lat, lon); // center
        LatLonPointImpl ptL1 = new LatLonPointImpl();
        Bearing.findPoint(referencePoint, brg, halflength, ptL1);
        lob.append(referencePoint.getLongitude()).append(",").append(referencePoint.getLatitude()).append(",").append(alt).append(" \n");
        lob.append(ptL1.getLongitude()).append(",").append(ptL1.getLatitude()).append(",").append(alt).append(" \n");
        lob.append(referencePoint.getLongitude()).append(",").append(referencePoint.getLatitude()).append(",").append(alt).append(" \n");
        return lob.toString();
    }

    public String getBrbBoxCenter() {
        StringBuilder coords = new StringBuilder();
        coords.append(centerLon).append(",").append(centerLat).append(",0 \n");
        return coords.toString();
    }

    public String getCenterLatString() {
        StringBuilder sb = new StringBuilder();
        sb.append(centerLat);
        return sb.toString();
    }

    public String getCenterLonString() {
        StringBuilder sb = new StringBuilder();
        sb.append(centerLon);
        return sb.toString();
    }

    public double getCenterLat() {
        return centerLat;
    }

    public double getCenterLon() {
        return centerLon;
    }

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
        //String graphicKML = "<Placemark><Style><LineStyle><color>";
        kml.append(GraphicColor);
        //graphicKML = graphicKML + GraphicColor;
        kml.append("</color><width>");
        kml.append(lineWidth);
        //graphicKML = graphicKML + "</color><width>" + lineWidth;
        kml.append("</width></LineStyle><PolyStyle><color>");
        kml.append(GraphicFillColor);
        //graphicKML = graphicKML + "</width></LineStyle><PolyStyle><color>" + GraphicFillColor;
        kml.append("</color></PolyStyle>");
        kml.append("	<BalloonStyle>");
        kml.append("	<text>$[description]</text>");
        kml.append("	</BalloonStyle>");
        kml.append("</Style>");
//        graphicKML = graphicKML + "</color></PolyStyle>"
//                + "	<BalloonStyle>"
//                + "	<text>$[description]</text>"
//                + "	</BalloonStyle>"
//                + "</Style>";
        kml.append("<name><![CDATA[");
        kml.append(GraphicTitle);
        //graphicKML = graphicKML + "<name><![CDATA[" + GraphicTitle;
        kml.append("]]></name>");
        //graphicKML = graphicKML + "]]></name>";
        kml.append("<description><![CDATA[");
        kml.append(GraphicTitle);
        kml.append("]]></description>");
        //graphicKML = graphicKML + "<description><![CDATA[" + GraphicTitle + "]]></description>";
        kml.append("<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>");
        //graphicKML = graphicKML + "<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>";
        kml.append(getKMLCoords());
        //graphicKML = graphicKML + getKMLCoords();
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>");
        //graphicKML = graphicKML + "</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>";
        return kml.toString();
    }//getKML

    public String getSimpleBoxAsKML(String GraphicTitle,
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
        kml.append(getSimpleKMLCoords());
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>");
        return kml.toString();
    }

    public String getSimpleBoxAsKML(String GraphicTitle,
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
        kml.append(getSimpleKMLCoords());
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>");
        return kml.toString();
    }//getKML
}
