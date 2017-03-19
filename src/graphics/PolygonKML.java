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
import ovl.schema.gccs.PolygonType;
import ovlweb.utils.LatLonPointImpl;

/**
 *
 * @author marty
 */
public class PolygonKML {

    private final ArrayList<LatLonPointImpl> positionPoints = new ArrayList<LatLonPointImpl>();
    private final PolygonType polygon;
    boolean closed = false;
    StringBuilder kmlCoords = new StringBuilder();
    double altitude;
    boolean isAltitude = false;
    double labelLat = 0.0;
    double labelLon = 0.0;

    public PolygonKML(PolygonType polygon) {
        this.polygon = polygon;
        if (null != polygon.isCLOSED()) {
            closed = polygon.isCLOSED();
        }

        this.setPositionPoints();
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
        isAltitude = true;
    }

    private void setPositionPoints() {
        //Build LatLonPoints for each BigDecimal Pair
        int pCount = 0;
        double avgLat = 0.0, avgLon = 0.0;
        StringBuilder firstPoint = new StringBuilder();
        for (JAXBElement<List<BigDecimal>> pair : polygon.getPOSITION()) {
            String[] point = pair.getValue().toString().replace("[", "").replace("]", "").split(",");
            double lat = Double.parseDouble(point[0]);
            double lon = Double.parseDouble(point[1].replace(" ", ""));
            LatLonPointImpl P1 = new LatLonPointImpl(lat, lon);
            positionPoints.add(P1);
            if (isAltitude) {
                if (pCount == 0) {
                    labelLat = lat;
                    labelLon = lon;
                    firstPoint.append(lon).append(",").append(lat).append(",").append(altitude).append(" \n");
                }
                kmlCoords.append(lon).append(",").append(lat).append(",").append(altitude).append(" \n");
            } else {
                if (pCount == 0) {
                    labelLat = lat;
                    labelLon = lon;
                    firstPoint.append(lon).append(",").append(lat).append(" \n");
                }
                kmlCoords.append(lon).append(",").append(lat).append(" \n");
            }
            avgLat = avgLat + lat;
            avgLon = avgLon + lon;
            pCount++;
        }
        if (closed) {
            kmlCoords.append(firstPoint);
        }
        labelLat = (avgLat / pCount);
        labelLon = (avgLon / pCount);
    }//setPositionPoints

    public double getLabelLat() {
        return labelLat;
    }

    public double getLabelLon() {
        return labelLon;
    }

    public String getKMLCoords() {
        return this.kmlCoords.toString();
    }

    public String getKML(String polyName,
            String lineColor,
            String lineWidth) {
        return getKML(polyName, "", lineColor, lineWidth, "00000000");
    }

    public String getKML(String polyName,
            String lineColor,
            String lineWidth,
            String fillColor) {
        return getKML(polyName, "", lineColor, lineWidth, fillColor);
    }

    public String getKML(String polyName,
            String description,
            String lineColor,
            String lineWidth,
            String fillColor) {
        if (lineColor.length() < 1) {
            lineColor = "ff0055ff";
        }
        if (fillColor.length() < 1) {
            fillColor = "4000aaff";
        }
        if (lineWidth.length() < 1) {
            lineWidth = "5";
        }
        StringBuilder kml = new StringBuilder();
        kml.append("<Placemark><Style><LineStyle><color>");
        kml.append(lineColor);
        kml.append("</color><width>");
        kml.append(lineWidth);
        kml.append("</width></LineStyle>");
        kml.append("<PolyStyle><color>");
        kml.append(fillColor);
        kml.append("</color></PolyStyle>");
        kml.append("	<BalloonStyle>");
        kml.append("	<text>$[description]</text>");
        kml.append("	</BalloonStyle>");
        kml.append("</Style>");

        kml.append("<name><![CDATA[");
        kml.append(polyName);
        kml.append("]]></name>");

        kml.append("<description><![CDATA[");
        kml.append("<b>").append(polyName).append("</b><br>");
        kml.append(description);
        kml.append("]]></description>");

        kml.append("<Polygon><tessellate>1</tessellate><outerBoundaryIs><LinearRing><coordinates>");
        kml.append(getKMLCoords());
        kml.append("</coordinates></LinearRing></outerBoundaryIs></Polygon>\n");

        kml.append("</Placemark>\n");

        return kml.toString();
    }//getKML 
}
