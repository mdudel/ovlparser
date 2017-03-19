/*
 * -----------------------------------------------------------------------------
 *        UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED
 *                  (C) Copyright 2015, USAREUR G3 MCSD
 *                          ALL RIGHTS RESERVED
 *                  THIS NOTICE DOES NOT IMPLY PUBLICATION
 * -----------------------------------------------------------------------------
 */
package graphics;

/**
 *
 * @author marty
 */
public class LineKML {

    String lon1;     //LON (x) START
    String lat1;     //LAT (y)
    String lon2;     //LON (x) END
    String lat2;     //LAT (y)
    String width;
    String color;
    String title;

    public LineKML(String lineTitle,
            String startLat,
            String startLon,
            String endLat,
            String endLon,
            String lineWidth,
            String lineColor
    ) {
        title = lineTitle;
        lon1 = startLon;
        lat1 = startLat;
        lon2 = endLon;
        lat2 = endLat;
        width = lineWidth;
        color = lineColor;
    }//lineKML

    public LineKML(String lineTitle,
            Double startLat,
            Double startLon,
            Double endLat,
            Double endLon,
            String lineWidth,
            String lineColor
    ) {
        title = lineTitle;
        lon1 = startLon.toString();
        lat1 = startLat.toString();
        lon2 = endLon.toString();
        lat2 = endLat.toString();
        width = lineWidth;
        color = lineColor;
    }//lineKML

    public String getKML() {
        StringBuilder kml = new StringBuilder();
        kml.append("<Placemark>\n<Style><LineStyle><color>");
        kml.append(color);
        kml.append("</color><width>");
        kml.append(width);
        kml.append("</width></LineStyle></Style>\n<name>");
        kml.append(title);
        kml.append("</name>\n<LineString><tessellate>1</tessellate>\n<coordinates>\n");
        kml.append(lon1);
        kml.append(",");
        kml.append(lat1);
        kml.append(",0.0 ");
        kml.append(lon2);
        kml.append(",");
        kml.append(lat2);
        kml.append(",0.0\n</coordinates>\n</LineString></Placemark>\n");
        return kml.toString();
    }
}
