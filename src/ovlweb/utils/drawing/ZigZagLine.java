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
 * @author Kenneth Keith <kkeith@mitre.org>
 */
public class ZigZagLine extends LineString {

    private ArrayList<LineString> ziggies = new ArrayList<LineString>();
    private ArrayList<LineString> verticalLines = new ArrayList<LineString>();
    private ArrayList<LatLonPointImpl> zigPoints = new ArrayList<LatLonPointImpl>();

    public ZigZagLine(LatLonPointImpl p1, LatLonPointImpl p2) {
        super(p1, p2);
        LatLonPointImpl refPoint = new LatLonPointImpl(getPoint1());
        LatLonPointImpl drawPoint = new LatLonPointImpl();


        zigPoints.addAll(getSpacedLinePoints(10));

        for (LatLonPointImpl p : zigPoints) {
            LatLonPointImpl top = new LatLonPointImpl();
            LatLonPointImpl bottom = new LatLonPointImpl();
            if (getAzimuth() < 180) {
                Bearing.findPoint(p, getAzimuth() - 90, getDistance() / 4, top);
                Bearing.findPoint(p, getAzimuth() + 90, getDistance() / 4, bottom);
            } else {
                Bearing.findPoint(p, getAzimuth() + 90, getDistance() / 4, top);
                Bearing.findPoint(p, getAzimuth() - 90, getDistance() / 4, bottom);
            }
            verticalLines.add(new LineString(top, bottom));
        }

        boolean topPoint = false;
        
        for (LineString s : verticalLines) {
            if (verticalLines.indexOf(s) == 0) {
                LineString zig = new LineString(getPoint1(), s.getPoint2());
                ziggies.add(zig);
                refPoint = s.getPoint2();
                continue;
            }

            if (verticalLines.indexOf(s) == verticalLines.size() - 1) {
                LineString zig2 = new LineString(verticalLines.get(verticalLines.size()-2).getPoint1(), s.getPoint2());
                ziggies.add(zig2);
                LineString zig3 = new LineString(s.getPoint2(), getPoint2());
                ziggies.add(zig3);
                continue;
            }
            if(topPoint){
                LineString zig = new LineString(s.getPoint2(), verticalLines.get(verticalLines.indexOf(s) - 1).getPoint1());
                ziggies.add(zig);
                topPoint=false;
            } else {
                LineString zig = new LineString(s.getPoint1(), verticalLines.get(verticalLines.indexOf(s) - 1).getPoint2());
                ziggies.add(zig);
                topPoint=true;
            }
        }
    }

    public ArrayList<LineString> getVerticalLines() {
        ArrayList<LineString> returnlines = new ArrayList<LineString>();
        if (returnlines.addAll(verticalLines)) {
            return returnlines;
        } else {
            return null;
        }
    }

    public ArrayList<LatLonPointImpl> getZigPoints() {
        ArrayList<LatLonPointImpl> returnpoints = new ArrayList<LatLonPointImpl>();
        if (returnpoints.addAll(zigPoints)) {
            return returnpoints;
        } else {
            return null;
        }
    }

    public ArrayList<LineString> getZigZagLines() {
        ArrayList<LineString> returnlines = new ArrayList<LineString>();
        if (returnlines.addAll(ziggies)) {
            return returnlines;
        } else {
            return null;
        }
    }

    @Override
    public String getKMLShape() {
        if (ziggies.size() > 1) {
            StringBuilder sb = new StringBuilder();
            for (LineString s : ziggies) {
                sb.append("<LineString>\n");
                sb.append("<extrude>0</extrude>\n");
                sb.append("<tessellate>1</tessellate>\n");
                sb.append("<coordinates>\n");
                sb.append(s.getPoint1().getLongitude() + "," + s.getPoint1().getLatitude() + ",0 " + s.getPoint2().getLongitude() + "," + s.getPoint2().getLatitude() + ",0\n");
                sb.append("</coordinates>\n");
                sb.append("</LineString>\n");
                return sb.toString();
            }
        }
        return "";
    }
}
