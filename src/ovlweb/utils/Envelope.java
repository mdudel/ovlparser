/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils;

import ovlweb.utils.drawing.LineString;
import ovlweb.utils.drawing.Polygon;
import ovlweb.utils.drawing.tacticalgraphics.AbstractTacticalGraphic;

/**
 *
 * @author Kenneth Keith <kkeith@mitre.org>
 */
public class Envelope {
    private double top;
    private double right;
    private double bottom;
    private double left;

    public Envelope(AbstractTacticalGraphic graphic) {
        if (graphic.getGraphicLines().size() > 0) {
            for (LineString line : graphic.getGraphicLines()) {
                if (line.getPoint1().getLatitude() >= top) {
                    top = line.getPoint1().getLatitude();
                }
                if (line.getPoint2().getLatitude() >= top) {
                    top = line.getPoint1().getLatitude();
                }
                if (line.getPoint1().getLatitude() <= bottom) {
                    bottom = line.getPoint1().getLatitude();
                }
                if (line.getPoint2().getLatitude() <= bottom) {
                    bottom = line.getPoint1().getLatitude();
                }
                if (line.getPoint1().getLongitude() <= left) {
                    left = line.getPoint1().getLongitude();
                }
                if (line.getPoint2().getLongitude() <= left) {
                    left = line.getPoint1().getLongitude();
                }
                if (line.getPoint1().getLongitude() >= right) {
                    right = line.getPoint1().getLongitude();
                }
                if (line.getPoint2().getLongitude() >= right) {
                    right = line.getPoint1().getLongitude();
                }
            }
        }
    }

    public Envelope(double left, double bottom, double right, double top) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public Envelope(LatLonPointImpl topleft, LatLonPointImpl bottomright) {
        this.top = topleft.getLatitude();
        this.bottom = bottomright.getLatitude();
        this.left = topleft.getLongitude();
        this.right = bottomright.getLongitude();
    }

    public Envelope(Envelope e) {
        this.top = e.getTop();
        this.bottom = e.getBottom();
        this.left = e.getLeft();
        this.right = e.getRight();
    }

    public Envelope() {
    }

    public Envelope getEnvelope() {
        return this;
    }

    public double getMaxY() {
        return top;
    }

    public double getMaxX() {
        return right;
    }

    public double getMinY() {
        return bottom;
    }

    public double getMinX() {
        return left;
    }

    public double getBottom() {
        return bottom;
    }

    public double getWidthDistanceKM() {
        LineString ls = new LineString(new LatLonPointImpl(top, left), new LatLonPointImpl(top, right));
        return ls.getDistance();
    }

    public final double getWidth() {
        return right - left;
    }

    public final double getHeight() {
        return top - bottom;
    }

    public double getHeightDistanceKM() {
        LineString ls = new LineString(new LatLonPointImpl(bottom, left), new LatLonPointImpl(top, left));
        return ls.getDistance();
    }

    public LatLonPointImpl getBottomleft() {
        return new LatLonPointImpl(bottom, left);
    }

    public LatLonPointImpl getBottomright() {
        return new LatLonPointImpl(bottom, right);
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getTop() {
        return top;
    }

    public LatLonPointImpl getTopleft() {
        return new LatLonPointImpl(top, left);
    }

    public LatLonPointImpl getTopright() {
        return new LatLonPointImpl(top, right);
    }

    public boolean contains(Envelope e) {
        if (e.getBottom() < bottom) {
            return false;
        }
        if (e.getTop() > top) {
            return false;
        }
        if (e.getLeft() < left) {
            return false;
        }
        if (e.getRight() > right) {
            return false;
        }
        return true;
    }

    public boolean contains(LatLonPointImpl pt) {
        if (pt.getLatitude() < bottom) {
            return false;
        }
        if (pt.getLatitude() > top) {
            return false;
        }
        if (pt.getLongitude() < left) {
            return false;
        }
        if (pt.getLongitude() > right) {
            return false;
        }
        return true;
    }

    public boolean contains(LineString ls) {
        if (ls.getPoint1().getLatitude() < bottom) {
            return false;
        }
        if (ls.getPoint2().getLatitude() < bottom) {
            return false;
        }

        if (ls.getPoint1().getLatitude() > top) {
            return false;
        }
        if (ls.getPoint2().getLatitude() > top) {
            return false;
        }

        if (ls.getPoint1().getLongitude() < left) {
            return false;
        }
        if (ls.getPoint2().getLongitude() < left) {
            return false;
        }

        if (ls.getPoint1().getLongitude() > right) {
            return false;
        }
        if (ls.getPoint2().getLongitude() > right) {
            return false;
        }
        return true;
    }

    public boolean contains(Polygon p) {
        boolean contained = false;
        if (p.getGraphicLines().size() > 0) {
            for (LineString ls : p.getGraphicLines()) {
                if (ls.getPoint1().getLongitude() > left && ls.getPoint1().getLatitude() < top && ls.getPoint1().getLatitude() > bottom && ls.getPoint1().getLongitude() < right) {
                    if (ls.getPoint2().getLongitude() > left && ls.getPoint2().getLatitude() < top && ls.getPoint2().getLatitude() > bottom && ls.getPoint2().getLongitude() < right) {
                        contained = true;
                    }
                } else {
                    return contained;
                }
            }
        }
        return contained;
    }

    public final Envelope intersection(Envelope e) {
        double x1 = Math.max(left, e.getLeft());

        double x2 = Math.min(right, e.getRight());

        double y1 = Math.min(top, e.getTop());

        double y2 = Math.max(bottom, e.getTop());

        if ((x2 > x1) && (y1 > y2)) {
            return new Envelope(x2, y1, x1, y2);
        } else {
            return null;
        }
    }

    public final boolean intersects(Envelope e) {
        return !((e.getRight() <= left) || (e.getBottom() >= top) || (e.getLeft() >= right) || (e.getTop() <= bottom));
    }

    public void expand(Envelope e) {
        if (e.getBottom() < bottom) {
            bottom = e.getBottom();
        }
        if (e.getTop() > top) {
            top = e.getTop();
        }
        if (e.getLeft() < left) {
            left = e.getLeft();
        }
        if (e.getRight() > right) {
            right = e.getRight();
        }
    }

    public void expand(double degrees) {
        bottom += degrees;
        top += degrees;
        left += degrees;
        right += degrees;
    }

    public LatLonPointImpl getCenter(){
        double lat = (top-bottom)/2;
        double lon = (right-left)/2;
        return new LatLonPointImpl(lat,lon);
    }
    
    @Override
    public String toString() {
        String returnString = "[Envelope Bounds] Left:" + left + " Bottom:" + bottom + " Right:" + right + " Top:" + top;
        return returnString;
    }
}
