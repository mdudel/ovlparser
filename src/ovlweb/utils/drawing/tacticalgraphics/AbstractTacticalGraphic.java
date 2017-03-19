package ovlweb.utils.drawing.tacticalgraphics;

import ovlweb.utils.interfaces.IlKMLPlacemark;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import ovlweb.utils.Envelope;
import ovlweb.utils.LatLonPointImpl;
import ovlweb.utils.interfaces.IKMLShape;
import ovlweb.utils.drawing.LineString;


/**
 *
 * @author KKEITH
 */
public abstract class AbstractTacticalGraphic implements IlKMLPlacemark, IKMLShape {
    public ArrayList<LatLonPointImpl> graphicPoints = new ArrayList<LatLonPointImpl>();
    public ArrayList<LineString> graphicLines = new ArrayList<LineString>();
    public ArrayList<IKMLShape> graphicShapes = new ArrayList<IKMLShape>();
    public double graphicOrientation = 0;
    public LatLonPointImpl labelPoint = new LatLonPointImpl();

    abstract public void computeSimpleGraphic(LatLonPointImpl point, double bearing, double width, double height);

    abstract public void computeNormalGraphic();

    @Override
    abstract public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth);

    public Envelope getGraphicEnvelope() {
        return new Envelope(this);
    }

    public String icsf2KmlColorConvert(String value) {
        String[] values = value.split(" ");
        String r = rgbToHex(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
        return r;
    }

    public String rgbToHex(int r, int g, int b) {
        return "FF" + toHex(b) + toHex(g) + toHex(r);
    }

    public String toHex(int v) {
        if (v == 0) {
            return "00";
        }
        v = Math.max(0, Math.min(v, 255));
        String r1 = String.valueOf("0123456789ABCDEF".charAt((v - v % 16) / 16));
        String r2 = String.valueOf("0123456789ABCDEF".charAt(v % 16));
        return r1 + r2;

    }

    public double getCorrectedAzimuth(double az) {
        if (az > 360) {
            return az - 360;
        } else {
            return az;
        }
    }

    public ArrayList<LatLonPointImpl> getGraphicPoints() {
        return graphicPoints;
    }

    public ArrayList<LineString> getGraphicLines() {
        return graphicLines;
    }

    public double getGraphicOrientation() {
        return graphicOrientation;
    }

    public ArrayList<IKMLShape> getGraphicShapes() {
        return new ArrayList<IKMLShape>(graphicShapes);
    }

    public LatLonPointImpl getLabelPoint() {
        return labelPoint;
    }

    @Override
    public String getKMLShape() {
        if (graphicLines.size() > 0) {
            StringBuilder returnShape = new StringBuilder();
            for (LineString ls : graphicLines) {
                returnShape.append(ls.getKMLShape());
            }
            return returnShape.toString();
        }
        return "";
    }
   
    public static Point2D LatLonToPixel(Envelope e, LatLonPointImpl pt) {
        //|x1|y1|0
        //|x2|y2|value
        //|x3|y3|1000

        /*
         * (x2-x1)(y3-y1) y2 = -------------- + y1 (x3-x1)
         */

        //Min width in pixels
        double minXWidth = 0;

        //Max width in pixels
        double maxXWidth = e.getWidthDistanceKM();

        //Min X coordinate
        double minX = e.getMinX();

        //Max X coordinate
        double maxX = e.getMaxX();

        double x1 = pt.getLongitude() - minX;
        double x2 = maxXWidth - minXWidth;
        double x3 = maxX - minX;
        double x4 = x1 * x2;
        double x5 = x4 / x3;
        double x = x5 + minXWidth;

        //Min width in pixels
        double minYHeight = 0;

        //Max width in pixels
        double maxYHeight = e.getHeightDistanceKM();

        //Min X coordinate
        double minY = e.getBottom();

        //Max X coordinate
        double maxY = e.getTop();

        double y1 = pt.getLatitude() - minY;
        double y2 = maxYHeight - minYHeight;
        double y3 = maxY - minY;
        double y4 = y1 * y2;
        double y5 = y4 / y3;
        double y = y5 + minYHeight;
        return new Point2D.Double(x, y);
    }
}
