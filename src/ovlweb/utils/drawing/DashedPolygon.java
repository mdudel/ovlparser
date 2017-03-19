/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils.drawing;

import java.util.ArrayList;
import ovlweb.utils.Bearing;
import ovlweb.utils.LatLonPointImpl;

/**
 *
 * @author KKEITH
 */
public class DashedPolygon {

    ArrayList<DashedLine> dashSegments = new ArrayList<DashedLine>();
    ArrayList<LatLonPointImpl> polyPoints = new ArrayList<LatLonPointImpl>();

    public DashedPolygon(LatLonPointImpl... points) {
        for (LatLonPointImpl p : points) {
            polyPoints.add(new LatLonPointImpl(p));
        }
        processDashedPolyLine();
    }

    public DashedPolygon(ArrayList<LatLonPointImpl> points) {
        for (LatLonPointImpl p : points) {
            polyPoints.add(new LatLonPointImpl(p));
        }
        processDashedPolyLine();
    }

    private void processDashedPolyLine() {
        for (LatLonPointImpl p : polyPoints) {
            if (polyPoints.indexOf(p) != (polyPoints.size() - 1)) {
                DashedLine ls = new DashedLine(p, polyPoints.get(polyPoints.indexOf(p)+1));
                dashSegments.add(ls);
            } else {
                DashedLine ls = new DashedLine(p, polyPoints.get(0));
                dashSegments.add(ls);
            }
        }
    }

    public double getAzimuth(LatLonPointImpl point1, LatLonPointImpl point2) {
        Bearing b = new Bearing();
        Bearing.calculateBearing(point1, point2, b);
        return b.getAngle();
    }

    public double getBackAzimuth(LatLonPointImpl point1, LatLonPointImpl point2) {
        Bearing b = new Bearing();
        Bearing.calculateBearing(point1, point2, b);
        return b.getBackAzimuth();
    }

    public double getDistance(LatLonPointImpl point1, LatLonPointImpl point2) {
        Bearing b = new Bearing();
        Bearing.calculateBearing(point1, point2, b);
        return b.getDistance();
    }

    public LatLonPointImpl getMiddlePoint(LatLonPointImpl point1, LatLonPointImpl point2) {
        if (point1 != null && point2 != null) {
            LatLonPointImpl returnPoint = new LatLonPointImpl();
            Bearing b = new Bearing();
            Bearing.findPoint(point1, this.getAzimuth(point1, point2), this.getDistance(point1, point2) / 2, returnPoint);
            return returnPoint;
        }
        return null;
    }

    public LatLonPointImpl getPointAlongLineMinus(LatLonPointImpl point1, LatLonPointImpl point2, double distance) {
        if (point1 != null && point2 != null) {
            LatLonPointImpl returnPoint = new LatLonPointImpl();
            Bearing b = new Bearing();
            Bearing.calculateBearing(point1, point2, b);
            Bearing.findPoint(point1, b.getAngle(), b.getDistance() - distance, returnPoint);
            return returnPoint;
        }
        return null;
    }

    public LatLonPointImpl getPointAlongLinePlus(LatLonPointImpl point1, LatLonPointImpl point2, double distance) {
        if (point1 != null && point2 != null) {
            LatLonPointImpl returnPoint = new LatLonPointImpl();
            Bearing b = new Bearing();
            Bearing.calculateBearing(point1, point2, b);
            Bearing.findPoint(point1, b.getAngle(), b.getDistance() + distance, returnPoint);
            return returnPoint;
        }
        return null;
    }

    public String getKMLRepresentation() {
        if (dashSegments.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (DashedLine ls : dashSegments) {
                sb.append(ls.getKMLShape());
            }
            return sb.toString();
        }
        return "";
    }
}
