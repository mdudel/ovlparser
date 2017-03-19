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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import ovl.schema.gccs.CorridorType;
import ovlweb.utils.Bearing;
import ovlweb.utils.LatLonPoint;
import ovlweb.utils.LatLonPointImpl;

/**
 *
 * @author marty
 */
public class CorridorKML {

    private ArrayList<LatLonPointImpl> positionPoints = new ArrayList<LatLonPointImpl>();
    private CorridorType corridor;
    double width;                       // width of corridor in KM
    //double altitude;
    //boolean isAltitude = false;
    StringBuilder kmlCoords = new StringBuilder();
    double labelLat = 0.0;
    double labelLon = 0.0;
    private static final double NM2KM = 1.852;
    private static final double KM2NM = 0.539957;
    String kmlName = "CORRIDOR";
    String kmlDescription = "";
    String GraphicColor = "ff14foff";
    String textColor = "ff14foff";
    String lineWidth = "2";
    int pointCount = 64;

    public CorridorKML(CorridorType corridor) {
        this.corridor = corridor;
        if (null != corridor.getWIDTH()) {
            width = corridor.getWIDTH().doubleValue() * NM2KM;
        }
        if (null != corridor.getNAME()) {
            kmlName = corridor.getNAME();
        }
        if (null != corridor.getDESCRIPTION()) {
            this.kmlDescription = corridor.getDESCRIPTION() + "<br>";

        }
        if (null != corridor.getREMARKS()) {
            this.kmlDescription += corridor.getREMARKS().replaceAll("\n", "<br>");

        }
        GraphicColor = "ff14foff";
        lineWidth = "2";
        this.pointCount = 64;
        this.setPositionPoints();
    }

    public CorridorKML(CorridorType corridor, String textColor, String GraphicColor, String lineWidth, int pointCount) {
        this.corridor = corridor;
        if (null != corridor.getWIDTH()) {
            width = corridor.getWIDTH().doubleValue() * NM2KM;
        }
        if (null != corridor.getNAME()) {
            kmlName = corridor.getNAME();
        }
        if (null != corridor.getDESCRIPTION()) {
            this.kmlDescription = corridor.getDESCRIPTION() + "<br>";

        }
        if (null != corridor.getREMARKS()) {
            this.kmlDescription += corridor.getREMARKS().replaceAll("\n", "<br>");

        }
        this.GraphicColor = GraphicColor;
        this.pointCount = pointCount;
        this.textColor = textColor;
        this.lineWidth = lineWidth;
        this.setPositionPoints();
    }

    public CorridorKML(CorridorType corridor, String textColor, String title, String description, String GraphicColor, String lineWidth, int pointCount) {
        this.corridor = corridor;
        if (null != corridor.getWIDTH()) {
            width = corridor.getWIDTH().doubleValue() * NM2KM;
        }
        this.GraphicColor = GraphicColor;
        this.kmlName = title;
        this.kmlDescription = description;
        this.pointCount = pointCount;
        this.textColor = textColor;
        this.lineWidth = lineWidth;
        this.setPositionPoints();
    }

    public void setKmlName(String kmlName) {
        this.kmlName = kmlName;
    }

    public void setKmlDescription(String kmlDescription) {
        this.kmlDescription = kmlDescription;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

    public void setGraphicColor(String GraphicColor) {
        this.GraphicColor = GraphicColor;
    }

    public void setLineWidth(String lineWidth) {
        this.lineWidth = lineWidth;
    }

    private void setPositionPoints() {
        // Clear the coords just in case
        kmlCoords = new StringBuilder("");

        //Build LatLonPoints for each BigDecimal Pair
        int pCount = 0;
        double avgLat = 0.0, avgLon = 0.0;
        EllipseKML ellipse;
        for (JAXBElement<List<BigDecimal>> pair : corridor.getPOSITION()) {
            String[] point = pair.getValue().toString().replace("[", "").replace("]", "").split(",");
            double lat = Double.parseDouble(point[0]);
            double lon = Double.parseDouble(point[1].replace(" ", ""));
            LatLonPointImpl P1 = new LatLonPointImpl(lat, lon);
            positionPoints.add(P1);
            avgLat = avgLat + lat;
            avgLon = avgLon + lon;
            pCount++;
            // Build circle for each point
            ellipse = new EllipseKML(lat, lon, 0.0, (width / 2.0), (width / 2.0), 0.0, this.pointCount);
            this.kmlCoords.append(ellipse.getKML(this.kmlName, this.kmlDescription, this.GraphicColor, lineWidth, "00000000")).append("\n");

        }

        labelLat = (avgLat / pCount);
        labelLon = (avgLon / pCount);

        // Get current point
        LatLonPointImpl currentPoint = positionPoints.get(0);
        LatLonPoint current = new LatLonPointImpl(currentPoint);

        LatLonPointImpl nextPoint;

        for (int i = 1; i < positionPoints.size(); i++) {
            nextPoint = positionPoints.get(i);
            Bearing az = new Bearing();
            Bearing.calculateBearing(nextPoint, currentPoint, az);

            // Get current points
            LatLonPointImpl currentPointOuter = new LatLonPointImpl();
            LatLonPointImpl currentPointInner = new LatLonPointImpl();
            Bearing.findPoint(currentPoint, (az.getAngle() + 90.0), (width / 2.0), currentPointOuter);
            Bearing.findPoint(currentPoint, (az.getAngle() - 90.0), (width / 2.0), currentPointInner);

            // Get next points
            LatLonPointImpl nextPointOuter = new LatLonPointImpl();
            LatLonPointImpl nextPointInner = new LatLonPointImpl();
            Bearing.findPoint(nextPoint, (az.getAngle() + 90.0), (width / 2.0), nextPointOuter);
            Bearing.findPoint(nextPoint, (az.getAngle() - 90.0), (width / 2.0), nextPointInner);

            // Get center line
            LineKML centerLine = new LineKML(this.kmlName,
                    currentPoint.getLatitude(), currentPoint.getLongitude(),
                    nextPoint.getLatitude(), nextPoint.getLongitude(),
                    this.lineWidth, this.GraphicColor);

            // Get inner corridor line
            LineKML innerLine = new LineKML(this.kmlName,
                    currentPointInner.getLatitude(), currentPointInner.getLongitude(),
                    nextPointInner.getLatitude(), nextPointInner.getLongitude(),
                    this.lineWidth, this.GraphicColor);

            // Get outer corridor line
            LineKML outerLine = new LineKML(this.kmlName,
                    currentPointOuter.getLatitude(), currentPointOuter.getLongitude(),
                    nextPointOuter.getLatitude(), nextPointOuter.getLongitude(),
                    this.lineWidth, this.GraphicColor);

            this.kmlCoords.append(centerLine.getKML());
            this.kmlCoords.append(innerLine.getKML());
            this.kmlCoords.append(outerLine.getKML());

            currentPoint = nextPoint;
        }

    }//setPositionPoints

//    public void setAltitude(double altitude) {
//        this.altitude = altitude;
//        isAltitude = true;
//    }
    public double getLabelLat() {
        return labelLat;
    }

    public double getLabelLon() {
        return labelLon;
    }

    public String getKML() {
        return this.kmlCoords.toString();
    }
}
