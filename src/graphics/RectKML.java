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
public class RectKML {

    double lon1;     //LON (x) START
    double lat1;     //LAT (y)
    double lon2;     //LON (x) END
    double lat2;     //LAT (y)
    double alt = 0.0;
    String width;
    String title;

    public RectKML(double lat1, double lon1,  double lat2,double lon2, String width, String title) {
        this.lon1 = lon1;
        this.lat1 = lat1;
        this.lon2 = lon2;
        this.lat2 = lat2;
        this.width = width;
        this.title = title;
    }//RectKML

    public RectKML(double lat1, double lon1,  double lat2,double lon2, double altitude, String width, String title) {
        this.lon1 = lon1;
        this.lat1 = lat1;
        this.lon2 = lon2;
        this.lat2 = lat2;
        this.alt = altitude;
        this.width = width;
        this.title = title;
    }//RectKML

    public String getKMLCoords() {
        return getKMLCoords(false);
    }

    public String getKMLCoords(boolean withAltitude) {
        StringBuilder sbBrgBoxCoords = new StringBuilder();

        if (withAltitude) {
            sbBrgBoxCoords.append(lon1).append(",").append(lat1).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(lon1).append(",").append(lat2).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(lon2).append(",").append(lat2).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(lon2).append(",").append(lat1).append(",").append(alt).append(" \n");
            sbBrgBoxCoords.append(lon1).append(",").append(lat1).append(",").append(alt).append(" \n");
        } else {
            sbBrgBoxCoords.append(lon1).append(",").append(lat1).append(" \n");
            sbBrgBoxCoords.append(lon1).append(",").append(lat2).append(" \n");
            sbBrgBoxCoords.append(lon2).append(",").append(lat2).append(" \n");
            sbBrgBoxCoords.append(lon2).append(",").append(lat1).append(" \n");
            sbBrgBoxCoords.append(lon1).append(",").append(lat1).append(" \n");
        }
        //System.out.println(brgboxCoords);
        return sbBrgBoxCoords.toString();
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
