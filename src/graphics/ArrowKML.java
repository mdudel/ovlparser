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

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;

import ovl.schema.gccs.ArrowType;
import ovlweb.utils.Bearing;
import ovlweb.utils.LatLonPoint;
import ovlweb.utils.LatLonPointImpl;
import ovlweb.utils.drawing.LineString;

/**
 *
 * @author marty
 */
public class ArrowKML {

    //LatLonPointImpl Container
    private ArrayList<LatLonPointImpl> positionPoints = new ArrayList<LatLonPointImpl>();
    private ArrowType arrow;
    private LatLonPointImpl tipPoint;
    private LatLonPointImpl basePoint;
    private LatLonPointImpl labelPoint;
    double width;                       // width of arrow in KM
    private static final double NM2KM = 1.852;
    private static final double KM2NM = 0.539957;
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;
    StringBuilder kmlLineCoords = new StringBuilder();
    boolean isMissingPoints = false;
    boolean crossed = false;
    StringBuilder dbug = new StringBuilder();
    StringBuilder dbugKml = new StringBuilder();

    public ArrowKML() {
    }

    public ArrowKML(ArrowType arrow) {
        this.arrow = arrow;
        this.width = (25.0 * NM2KM);
        if (null != arrow.getWIDTH()) {
            this.width = arrow.getWIDTH().doubleValue() * NM2KM;
        }
        if (null != arrow.isCROSSED()) {
            this.crossed = arrow.isCROSSED();
        }
        this.setPositionPoints();
    }

    public double getLabelLat() {
        return this.labelPoint.getLatitude();
    }

    public double getLabelLon() {
        return this.labelPoint.getLongitude();
    }

    public String getStringLabelLat() {
        Double lat = this.labelPoint.getLatitude();
        return lat.toString();
    }

    public String getStringLabelLon() {
        Double lon = this.labelPoint.getLongitude();
        return lon.toString();
    }

    private void setPositionPoints() {
        //Build LatLonPoints for each BigDecimal Pair
        for (JAXBElement<List<BigDecimal>> pair : arrow.getPOSITION()) {
            String[] point = pair.getValue().toString().replace("[", "").replace("]", "").split(",");
            double lat = Double.parseDouble(point[0]);
            double lon = Double.parseDouble(point[1].replace(" ", ""));
            LatLonPointImpl P1 = new LatLonPointImpl(lat, lon);
            positionPoints.add(P1);
        }
        if (null != arrow.getWIDTH()) {
            this.width = arrow.getWIDTH().doubleValue() * NM2KM;
        } else {
            Bearing b = new Bearing();
            Bearing.calculateBearing(positionPoints.get(1), positionPoints.get(0), b);
            if (b.getDistance() < width) {
                width = b.getDistance() / 5.0;
            }
        }
        // Get main points (must have at least two)
        if (positionPoints.size() >= 2) {
            tipPoint = positionPoints.get(0);
            basePoint = positionPoints.get(1);
            labelPoint = tipPoint;
            isMissingPoints = true;
        }
        if (positionPoints.size() == 2) {
            // Simple arrow with two points
            kmlLineCoords = new StringBuilder("");
            LatLonPoint tip = new LatLonPointImpl(tipPoint);
            LatLonPoint base = new LatLonPointImpl(basePoint);
            Bearing az = new Bearing();
            Bearing.calculateBearing(base, tip, az);
            LineString centerLine = new LineString(basePoint, tipPoint);
            labelPoint = centerLine.getMiddlePoint();

            // Get reference point at arrow head (clockwise direction)
            LatLonPointImpl refPoint = centerLine.getPointAlongLineMinus(1.5 * width);
            LatLonPoint ref = new LatLonPointImpl(refPoint);
            LatLonPointImpl refPoint1Outer = new LatLonPointImpl();
            LatLonPointImpl refPoint1Inner = new LatLonPointImpl();
            Bearing.findPoint(ref, (az.getAngle() + 90.0), width, refPoint1Outer);
            Bearing.findPoint(ref, (az.getAngle() + 90.0), (width / 2.0), refPoint1Inner);

            // Get  point at arrow base (clockwise driection)     
            LatLonPointImpl basePoint1 = new LatLonPointImpl();
            LatLonPointImpl basePoint2 = new LatLonPointImpl();
            Bearing.findPoint(base, (az.getAngle() + 90.0), (width / 2.0), basePoint1);
            Bearing.findPoint(base, (az.getAngle() - 90.0), (width / 2.0), basePoint2);

            // Get reference point at arrow head (clockwise direction)
            LatLonPointImpl refPoint2Outer = new LatLonPointImpl();
            LatLonPointImpl refPoint2Inner = new LatLonPointImpl();
            Bearing.findPoint(ref, (az.getAngle() - 90.0), width, refPoint2Outer);
            Bearing.findPoint(ref, (az.getAngle() - 90.0), (width / 2.0), refPoint2Inner);

            // Add coords in a clockwise manner from centerline starting at tip
            kmlLineCoords.append(tip.getLongitude()).append(",").append(tip.getLatitude()).append(" \n");
            kmlLineCoords.append(refPoint1Outer.getLongitude()).append(",").append(refPoint1Outer.getLatitude()).append(" \n");
            kmlLineCoords.append(refPoint1Inner.getLongitude()).append(",").append(refPoint1Inner.getLatitude()).append(" \n");
            if (this.crossed) {
                // Flip the order of the base points
                kmlLineCoords.append(basePoint2.getLongitude()).append(",").append(basePoint2.getLatitude()).append(" \n");
                kmlLineCoords.append(basePoint1.getLongitude()).append(",").append(basePoint1.getLatitude()).append(" \n");

            } else {
                kmlLineCoords.append(basePoint1.getLongitude()).append(",").append(basePoint1.getLatitude()).append(" \n");
                kmlLineCoords.append(basePoint2.getLongitude()).append(",").append(basePoint2.getLatitude()).append(" \n");
            }
            kmlLineCoords.append(refPoint2Inner.getLongitude()).append(",").append(refPoint2Inner.getLatitude()).append(" \n");
            kmlLineCoords.append(refPoint2Outer.getLongitude()).append(",").append(refPoint2Outer.getLatitude()).append(" \n");
            kmlLineCoords.append(tip.getLongitude()).append(",").append(tip.getLatitude()).append(" \n");

        } else if (positionPoints.size() >= 3) {
            // Complex arrow with multiple segments
            kmlLineCoords = new StringBuilder("");
            StringBuilder coordsOut = new StringBuilder();
            StringBuilder coordsIn = new StringBuilder();

            LatLonPoint tip = new LatLonPointImpl(tipPoint);
            LatLonPoint currentBase = new LatLonPointImpl(basePoint);
            Bearing az = new Bearing();
            Bearing.calculateBearing(currentBase, tip, az);
            LineString centerLine = new LineString(basePoint, tipPoint);
            labelPoint = centerLine.getMiddlePoint();

            // Get reference point at arrow head (clockwise direction)
            LatLonPointImpl refPoint = centerLine.getPointAlongLineMinus(1.5 * width);
            LatLonPoint ref = new LatLonPointImpl(refPoint);
            LatLonPointImpl refPoint1Outer = new LatLonPointImpl();
            LatLonPointImpl refPoint1Inner = new LatLonPointImpl();
            Bearing.findPoint(ref, (az.getAngle() + 90.0), width, refPoint1Outer);
            Bearing.findPoint(ref, (az.getAngle() + 90.0), (width / 2.0), refPoint1Inner);

            // Get reference point at arrow head (clockwise direction)
            LatLonPointImpl refPoint2Outer = new LatLonPointImpl();
            LatLonPointImpl refPoint2Inner = new LatLonPointImpl();
            Bearing.findPoint(ref, (az.getAngle() - 90.0), width, refPoint2Outer);
            Bearing.findPoint(ref, (az.getAngle() - 90.0), (width / 2.0), refPoint2Inner);

            // Add coords in a clockwise manner from centerline starting at tip
            coordsOut.append(tip.getLongitude()).append(",").append(tip.getLatitude()).append(" \n");
            coordsOut.append(refPoint1Outer.getLongitude()).append(",").append(refPoint1Outer.getLatitude()).append(" \n");
            coordsOut.append(refPoint1Inner.getLongitude()).append(",").append(refPoint1Inner.getLatitude()).append(" \n");

            LatLonPointImpl currentPoint = refPoint;
            LatLonPointImpl nextPoint;

            LineString currentLineOuter = new LineString(refPoint1Outer, refPoint2Outer);
            LineString currentLineInner = new LineString(refPoint1Inner, refPoint2Inner);
            for (int i = 1; i < positionPoints.size(); i++) {
                // Get current base points at current angle
                LatLonPointImpl nextPointOuter0 = new LatLonPointImpl();
                LatLonPointImpl nextPointInner0 = new LatLonPointImpl();
                Bearing.findPoint(currentPoint, (az.getAngle() + 90.0), (width / 2.0), nextPointOuter0);
                Bearing.findPoint(currentPoint, (az.getAngle() - 90.0), (width / 2.0), nextPointInner0);

                nextPoint = positionPoints.get(i);
                Bearing nextAz = new Bearing();
                Bearing.calculateBearing(nextPoint, currentPoint, nextAz);
                dbug.append(i).append(" delta Az: ").append(nextAz.getAngle() - az.getAngle()).append(" <br>");
                // Get current base points at current next angle
                LatLonPointImpl nextPointOuter1 = new LatLonPointImpl();
                LatLonPointImpl nextPointInner1 = new LatLonPointImpl();
                Bearing.findPoint(currentPoint, (nextAz.getAngle() + 90.0), (width / 2.0), nextPointOuter1);
                Bearing.findPoint(currentPoint, (nextAz.getAngle() - 90.0), (width / 2.0), nextPointInner1);

                // Get next base points
                LatLonPointImpl nextPointOuter2 = new LatLonPointImpl();
                LatLonPointImpl nextPointInner2 = new LatLonPointImpl();
                Bearing.findPoint(nextPoint, (nextAz.getAngle() + 90.0), (width / 2.0), nextPointOuter2);
                Bearing.findPoint(nextPoint, (nextAz.getAngle() - 90.0), (width / 2.0), nextPointInner2);

                LineString nextLineOuter = new LineString(nextPointOuter1, nextPointOuter2);
                LineString nextLineInner = new LineString(nextPointInner1, nextPointInner2);

                // Check for intersections based on angle difference
                if (i > 1) {
                    if ((nextAz.getAngle() - az.getAngle()) < 0.0) {
                        // get Outer line iintersections point
                        dbug.append("Get outer lines intersection point<br>");
                        dbug.append(nextLineOuter.getPoint1().toString(4)).append(" ");
                        dbug.append(nextLineOuter.getPoint2().toString(4));
                        dbug.append("<br>");
                        dbug.append(currentLineOuter.getPoint1().toString(4)).append(" ");
                        dbug.append(currentLineOuter.getPoint2().toString(4));
                        dbug.append("<br>");
                        try {
                            nextPointOuter0 = nextLineOuter.getIntersectionPoint2D(currentLineOuter);
                            nextPointOuter1 = nextPointOuter0;
                        } catch (NullPointerException npe) {
                            dbug.append("<b>COULD NOT GET INTERSECT POINT</b><br>");
                        }

                    } else if ((nextAz.getAngle() - az.getAngle()) > 0.0) {
                        dbug.append("Get inner lines intersection point<br>");
                        dbug.append(nextLineInner.getPoint1().toString(4)).append(" ");
                        dbug.append(nextLineInner.getPoint2().toString(4));
                        dbug.append("<br>");
                        dbug.append(currentLineInner.getPoint1().toString(4)).append(" ");
                        dbug.append(currentLineInner.getPoint2().toString(4));
                        dbug.append("<br>");
                        // get inner line intersection point
                        try {
                            nextPointInner0 = nextLineInner.getIntersectionPoint2D(currentLineInner);
                            nextPointInner1 = nextPointInner0;
                        } catch (NullPointerException npe) {
                            dbug.append("<b>COULD NOT GET INTERSECT POINT</b><br>");
                        }
                    }
                }
                // Build the coordinate strings
                StringBuilder innerCoords = new StringBuilder();

                if (this.crossed && (i == 1)) {
                    // flip popints to cross only in first segment, otherwise the arrow looks BAD
                    coordsOut.append(nextPointInner1.getLongitude()).append(",").append(nextPointInner1.getLatitude()).append(" \n");
                    innerCoords = new StringBuilder();
                    innerCoords.append(nextPointOuter1.getLongitude()).append(",").append(nextPointOuter1.getLatitude()).append(" \n");
                    coordsIn.insert(0, innerCoords);

                } else {
                    coordsOut.append(nextPointOuter0.getLongitude()).append(",").append(nextPointOuter0.getLatitude()).append(" \n");
                    innerCoords = new StringBuilder();
                    innerCoords.append(nextPointInner0.getLongitude()).append(",").append(nextPointInner0.getLatitude()).append(" \n");
                    coordsIn.insert(0, innerCoords);

                    coordsOut.append(nextPointOuter1.getLongitude()).append(",").append(nextPointOuter1.getLatitude()).append(" \n");
                    innerCoords = new StringBuilder();
                    innerCoords.append(nextPointInner1.getLongitude()).append(",").append(nextPointInner1.getLatitude()).append(" \n");
                    coordsIn.insert(0, innerCoords);
                }
                coordsOut.append(nextPointOuter2.getLongitude()).append(",").append(nextPointOuter2.getLatitude()).append(" \n");
                innerCoords = new StringBuilder();
                innerCoords.append(nextPointInner2.getLongitude()).append(",").append(nextPointInner2.getLatitude()).append(" \n");
                coordsIn.insert(0, innerCoords);

                az = nextAz;
                currentPoint = nextPoint;
                currentLineOuter = nextLineOuter;
                currentLineInner = nextLineInner;
                //dbugKml.append(currentLineOuter.getKMLPlacemark("OUTER " + i, "", Color.WHITE, 3));
                //dbugKml.append(currentLineInner.getKMLPlacemark("INNER " + i, "", Color.WHITE, 3));

            }
            coordsIn.append(refPoint2Inner.getLongitude()).append(",").append(refPoint2Inner.getLatitude()).append(" \n");
            coordsIn.append(refPoint2Outer.getLongitude()).append(",").append(refPoint2Outer.getLatitude()).append(" \n");
            coordsIn.append(tip.getLongitude()).append(",").append(tip.getLatitude()).append(" \n");

            kmlLineCoords.append(coordsOut).append(coordsIn);
        } else {
            // Invalid number of points
            kmlLineCoords = new StringBuilder("");
            isMissingPoints = true;
        }
    }

    public String getKMLCoords() {
        return kmlLineCoords.toString();
    }

    public String getKML(String GraphicTitle,
            String GraphicColor,
            String lineWidth) {
        return getKML(GraphicTitle, GraphicTitle, GraphicColor, lineWidth);
    }

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
        //kml.append(dbug.toString());
        kml.append("]]></description>");
        kml.append("<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>");
        kml.append(getKMLCoords());
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>");
        //kml.append(dbugKml);
        return kml.toString();
    }//getKML 
}
