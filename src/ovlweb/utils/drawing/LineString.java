/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils.drawing;

import ovlweb.utils.interfaces.IKMLShape;
import ovlweb.utils.interfaces.IlKMLPlacemark;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Date;
import static ovl.utils.KMlUtils.icsf2KmlColorConvert;
import ovlweb.utils.Bearing;
import ovlweb.utils.LatLonPointImpl;

/**
 *
 * @author KKEITH
 */
public class LineString implements IKMLShape, IlKMLPlacemark {

    public final String GRAPHIC_NAME = "LINESTRING";
    public final String GRAPHIC_STD = "MIL-STD-2525C";
    public final String GRAPHIC_PATH = "None";
    public final String GRAPHIC_HIERARCHY = "None";
    public final String GRAPHIC_CODE = "---------------";
    public final int GRAPHIC_POINTS = 2;
    LatLonPointImpl point1;
    LatLonPointImpl point2;
    String nameReference;

    public LineString(LatLonPointImpl p1, LatLonPointImpl p2) {
        point1 = new LatLonPointImpl(p1);
        point2 = new LatLonPointImpl(p2);
        nameReference = "";
    }

    public LatLonPointImpl getPoint1() {
        return point1;
    }

    public LatLonPointImpl getPoint2() {
        return this.point2;
    }

    public double getAzimuth() {
        Bearing b = new Bearing();
        Bearing.calculateBearing(point1, point2, b);
        return b.getAngle();
    }

    public double getBackAzimuth() {
        Bearing b = new Bearing();
        Bearing.calculateBearing(point1, point2, b);
        return b.getBackAzimuth();
    }

    public double getDistance() {
        Bearing b = new Bearing();
        Bearing.calculateBearing(point1, point2, b);
        return b.getDistance();
    }

    public LatLonPointImpl getMiddlePoint() {
        if (point1 != null && point2 != null) {
            LatLonPointImpl returnPoint = new LatLonPointImpl();
            Bearing.findPoint(point1, getAzimuth(), getDistance() / 2, returnPoint);
            return returnPoint;
        }
        return null;
    }

    public LatLonPointImpl getPointAlongLineMinus(double distance) {
        if (point1 != null && point2 != null && distance > 0) {
            LatLonPointImpl returnPoint = new LatLonPointImpl();
            Bearing.findPoint(point1, getAzimuth(), getDistance() - distance, returnPoint);
            return returnPoint;
        }
        return null;
    }

    public LatLonPointImpl getPointAlongLinePlus(double distance) {
        if (point1 != null && point2 != null && distance > 0) {
            LatLonPointImpl returnPoint = new LatLonPointImpl();
            Bearing b = new Bearing();
            Bearing.calculateBearing(point1, point2, b);
            Bearing.findPoint(point1, b.getAngle(), b.getDistance() + distance, returnPoint);
            return returnPoint;
        }
        return null;
    }

    public ArrayList<LatLonPointImpl> getLinePoints() {
        ArrayList<LatLonPointImpl> returnPoints = new ArrayList<LatLonPointImpl>();
        returnPoints.add(point1);
        returnPoints.add(getMiddlePoint());
        returnPoints.add(point2);
        return returnPoints;
    }

    public ArrayList<LineString> getGapLines() {
        if (this.getPoint1() != null && this.getPoint2() != null) {
            ArrayList<LineString> returnLines = new ArrayList<LineString>();
            LatLonPointImpl p1 = new LatLonPointImpl();
            LatLonPointImpl p2 = new LatLonPointImpl();
            Bearing.findPoint(getPoint1(), getAzimuth(), (getDistance() / 2) - (getDistance() / 10), p1);
            Bearing.findPoint(getPoint2(), getBackAzimuth(), (getDistance() / 2) - (getDistance() / 10), p2);
            returnLines.add(new LineString(new LatLonPointImpl(getPoint1()), p1));
            returnLines.add(new LineString(p2, new LatLonPointImpl(getPoint2())));
            return returnLines;
        }
        return null;
    }

    public ArrayList<LineString> getSimpleArrowTip() {
        ArrayList<LineString> returnLines = new ArrayList<LineString>();
        LatLonPointImpl p1 = new LatLonPointImpl();
        LatLonPointImpl p2 = new LatLonPointImpl();

        if (getAzimuth() > 180) {
            Bearing.findPoint(point2, getBackAzimuth() - 45, (getDistance() / 5), p1);
            Bearing.findPoint(point2, getBackAzimuth() + 45, (getDistance() / 5), p2);
            LineString ls1 = new LineString(point2, p1);
            LineString ls2 = new LineString(point2, p2);
            returnLines.add(ls1);
            returnLines.add(ls2);
        } else {
            Bearing.findPoint(point2, getBackAzimuth() + 45, (getDistance() / 5), p1);
            Bearing.findPoint(point2, getBackAzimuth() - 45, (getDistance() / 5), p2);
            LineString ls1 = new LineString(point2, p1);
            LineString ls2 = new LineString(point2, p2);
            returnLines.add(ls1);
            returnLines.add(ls2);
        }

        return returnLines;
    }

    public ArrayList<LineString> getXTip(double width) {
        ArrayList<LineString> returnLines = new ArrayList<LineString>();
        LatLonPointImpl pt1 = new LatLonPointImpl();
        LatLonPointImpl pt2 = new LatLonPointImpl();
        LatLonPointImpl pt3 = new LatLonPointImpl();
        LatLonPointImpl pt4 = new LatLonPointImpl();

        double bearing = getAzimuth();
        if (bearing <= 180) {
            Bearing.findPoint(getPoint2(), bearing + 45, width, pt1);
            Bearing.findPoint(getPoint2(), bearing - 45, width, pt2);
            Bearing.findPoint(getPoint2(), bearing + 135, width, pt3);
            Bearing.findPoint(getPoint2(), bearing - 135, width, pt4);
            LineString ls1 = new LineString(pt1, pt4);
            LineString ds1 = new LineString(pt2, pt3);
            returnLines.add(ls1);
            returnLines.add(ds1);
        } else {
            Bearing.findPoint(getPoint2(), bearing - 45, width, pt1);
            Bearing.findPoint(getPoint2(), bearing + 45, width, pt2);
            Bearing.findPoint(getPoint2(), bearing - 135, width, pt3);
            Bearing.findPoint(getPoint2(), bearing + 135, width, pt4);
            LineString ls1 = new LineString(pt1, pt4);
            LineString ds1 = new LineString(pt2, pt3);
            returnLines.add(ls1);
            returnLines.add(ds1);
        }
        return returnLines;
    }

    public ArrayList<LineString> getSimpleArrowTip(double length) {
        ArrayList<LineString> returnLines = new ArrayList<LineString>();
        LatLonPointImpl p1 = new LatLonPointImpl();
        LatLonPointImpl p2 = new LatLonPointImpl();

        if (getAzimuth() > 180) {
            Bearing.findPoint(point2, getBackAzimuth() - 45, (length / 5), p1);
            Bearing.findPoint(point2, getBackAzimuth() + 45, (length / 5), p2);
            LineString ls1 = new LineString(point2, p1);
            LineString ls2 = new LineString(point2, p2);
            returnLines.add(ls1);
            returnLines.add(ls2);
        } else {
            Bearing.findPoint(point2, getBackAzimuth() + 45, (length / 5), p1);
            Bearing.findPoint(point2, getBackAzimuth() - 45, (length / 5), p2);
            LineString ls1 = new LineString(point2, p1);
            LineString ls2 = new LineString(point2, p2);
            returnLines.add(ls1);
            returnLines.add(ls2);
        }

        return returnLines;
    }

    public boolean isVertical() {
        if (getPoint1().getLongitude() == getPoint2().getLongitude()) {
            if (getPoint1().getLatitude() < getPoint2().getLatitude()) {
                return true;
            }
            if (getPoint1().getLatitude() > getPoint2().getLatitude()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPointOnLine(LatLonPointImpl p) {
        LineString ls = new LineString(p, getPoint2());
        if (ls.getAzimuth() == getAzimuth()) {
            if (p.getLongitude() > getPoint1().getLongitude() || p.getLatitude() > getPoint1().getLatitude()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<LatLonPointImpl> getSpacedLinePoints(int num) {
        ArrayList<LatLonPointImpl> returnPoints = new ArrayList<LatLonPointImpl>();
        LatLonPointImpl drawPoint = new LatLonPointImpl();

        for (int i = 1; i < num; i++) {
            Bearing.findPoint(getPoint1(), getAzimuth(), (getDistance() / num) * i, drawPoint);
            returnPoints.add(new LatLonPointImpl(drawPoint));
        }
        if (returnPoints.size() > 1) {
            return returnPoints;
        } else {
            return null;
        }
    }

    public LatLonPointImpl getIntersectionPoint2D(LineString ls) {
        Line2D.Double line1 = new Line2D.Double(ls.getPoint1().getLatitude(), ls.getPoint1().getLongitude(), ls.getPoint2().getLatitude(), ls.getPoint2().getLongitude());
        Line2D.Double line2 = new Line2D.Double(getPoint1().getLatitude(), getPoint1().getLongitude(), getPoint2().getLatitude(), getPoint2().getLongitude());
        Point2D.Double pt = getLineIntersection(line1, line2);
        return new LatLonPointImpl(pt.x, pt.y);
    }

    private Point2D.Double getLineIntersection(Line2D.Double l1, Line2D.Double l2) {
        Point2D.Double intersection = new Point2D.Double();

        if (!l1.intersectsLine(l2)) {
            return null;
        }

        double x1 = l1.getX1(), y1 = l1.getY1(),
                x2 = l1.getX2(), y2 = l1.getY2(),
                x3 = l2.getX1(), y3 = l2.getY1(),
                x4 = l2.getX2(), y4 = l2.getY2();

        intersection.x = det(det(x1, y1, x2, y2), x1 - x2,
                det(x3, y3, x4, y4), x3 - x4)
                / det(x1 - x2, y1 - y2, x3 - x4, y3 - y4);
        intersection.y = det(det(x1, y1, x2, y2), y1 - y2,
                det(x3, y3, x4, y4), y3 - y4)
                / det(x1 - x2, y1 - y2, x3 - x4, y3 - y4);

        return intersection;
    }

    private double det(double a, double b, double c, double d) {
        return a * d - b * c;
    }

    public double pointToLineDistance(LatLonPointImpl A, LatLonPointImpl B, LatLonPointImpl P) {
        double normalLength = Math.sqrt((B.getLongitude() - A.getLongitude()) * (B.getLongitude() - A.getLongitude()) + (B.getLatitude() - A.getLatitude()) * (B.getLatitude() - A.getLatitude()));
        return Math.abs((P.getLongitude() - A.getLongitude()) * (B.getLatitude() - A.getLatitude()) - (P.getLatitude() - A.getLatitude()) * (B.getLongitude() - A.getLongitude())) / normalLength;
    }

    @Override
    public String toString() {
        return "Point1:" + point1.toString() + " Point2:" + point2.toString();
    }

    @Override
    public String getKMLShape() {
        if (point1 != null && point2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("<LineString>\n");
            sb.append("<extrude>0</extrude>\n");
            sb.append("<tessellate>1</tessellate>\n");
            sb.append("<coordinates>\n");
            sb.append(point1.getLongitude()).append(",").append(point1.getLatitude()).append(",0 ").append(point2.getLongitude()).append(",").append(point2.getLatitude()).append(",0\n");
            sb.append("</coordinates>\n");
            sb.append("</LineString>\n");
            return sb.toString();
        }
        return "";
    }

    @Override
    public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth) {
        if (point1 != null && point2 != null) {
            StringBuilder cKML = new StringBuilder();

            cKML.append("<Placemark>\n");
            String lineColorRed = String.valueOf(linecolor.getRed());
            String lineColorGreen = String.valueOf(linecolor.getGreen());
            String lineColorBlue = String.valueOf(linecolor.getBlue());
            String lineColorString = lineColorRed + " " + lineColorGreen + " " + lineColorBlue;

            //Styles
            cKML.append("<Style>\n");
            cKML.append("<LineStyle>\n");
            cKML.append("<color>").append(icsf2KmlColorConvert(lineColorString)).append("</color>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<width>").append(linewidth).append("</width>\n");
            cKML.append("</LineStyle>\n");
            cKML.append("<PolyStyle>\n");
            cKML.append("<color>").append(icsf2KmlColorConvert(lineColorString)).append("</color>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<fill>0</fill>\n");
            cKML.append("</PolyStyle>\n");
            cKML.append("<LabelStyle>\n");
            cKML.append("<color>").append(icsf2KmlColorConvert(lineColorString)).append("</color>\n");
            cKML.append("</LabelStyle>\n");

            //Balloon Style
            cKML.append("<BalloonStyle>\n");
            cKML.append("<text>$[description]</text>\n");
            cKML.append("</BalloonStyle>\n");

            //Point Style
            cKML.append("<IconStyle>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<Icon><href></href></Icon>\n");
            cKML.append("</IconStyle>\n");
            cKML.append("</Style>\n");

            cKML.append("<name>Tactical Graphic:" + GRAPHIC_NAME + "</name>\n");

            //Description Table
            cKML.append("<description>\n");
            cKML.append("<![CDATA[");
            cKML.append("<table border=\"1\" width=\"300\">");
            cKML.append("<tr>\n");
            cKML.append("<td> Name:</td>\n");
            cKML.append("<td>" + GRAPHIC_NAME + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> KML Created:</td>\n");
            cKML.append("<td>").append(new Date().toString()).append("</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Symbol Code:</td>\n");
            cKML.append("<td>" + GRAPHIC_CODE + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Path:</td>\n");
            cKML.append("<td>" + GRAPHIC_PATH + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Hierarchy:</td>\n");
            cKML.append("<td>" + GRAPHIC_HIERARCHY + "</td>\n");
            cKML.append("</tr>\n");
            cKML.append("<tr>\n");
            cKML.append("<td> Description:</td>\n");
            cKML.append("<td>").append(description).append("</td>\n");
            cKML.append("</tr>\n");

            cKML.append("<tr>\n");
            cKML.append("<td> Line Points:</td>\n");
            cKML.append("<td> Point 1:</td>\n");
            cKML.append("<td> Lat: ").append(point1.getLatitude()).append("<br> Lon: ").append(point1.getLongitude()).append("</td>\n");
            cKML.append("<td> Middle Point:</td>\n");
            cKML.append("<td> Lat: ").append(getMiddlePoint().getLatitude()).append("<br> Lon: ").append(getMiddlePoint().getLongitude()).append("</td>\n");
            cKML.append("<td> Point 2:</td>\n");
            cKML.append("<td> Lat: ").append(getMiddlePoint().getLatitude()).append("<br> Lon: ").append(getMiddlePoint().getLongitude()).append("</td>\n");
            cKML.append("</tr>\n");

            cKML.append("<tr>\n");
            cKML.append("<td> Line Distance:</td>\n");
            cKML.append("<td>").append(getDistance()).append("</td>\n");
            cKML.append("</tr>\n");

            cKML.append("<tr>\n");
            cKML.append("<td> Line Orientation:</td>\n");
            cKML.append("<td>").append(getAzimuth()).append("</td>\n");
            cKML.append("</tr>\n");

//            cKML.append("<tr>\n");
//            cKML.append("<td> Image:</td>\n");
//            if (GRAPHIC_NAME.contains(" ")) {
//                String GRAPHICNAME = GRAPHIC_NAME.replaceAll(" ", "");
//                cKML.append("<td>" + "<img src='http://" + OVLWebServer.getServerAddress() + ":" + OVLWebServer.getServicePort() + "/Resources/" + GRAPHICNAME + "/'" + "/></td>\n");
//            } else {
//                cKML.append("<td>" + "<img src='http://" + OVLWebServer.getServerAddress() + ":" + OVLWebServer.getServicePort() + "/Resources/" + GRAPHIC_NAME + "/'" + "/></td>\n");
//            }
//            cKML.append("</tr>\n");

            cKML.append("</table>\n");
            cKML.append("<p> OVLWeb Created by: <br> Marty Dudel DSN: 314-370-7977 <br> Kenneth Keith DSN: 314-370-8080");
            cKML.append("]]>\n");
            cKML.append("</description>\n");

            //LineString
            cKML.append("<MultiGeometry>\n");
            cKML.append(this.getKMLShape());
            cKML.append("</MultiGeometry>\n");
            cKML.append("</Placemark>\n");

            //LineString Label
            cKML.append("<Placemark>\n");
            cKML.append("<Style>\n");
            cKML.append("<IconStyle>\n");
            cKML.append("<colorMode>normal</colorMode>\n");
            cKML.append("<Icon><href></href></Icon>\n");
            cKML.append("</IconStyle>\n");
            cKML.append("</Style>\n");
            cKML.append("<name>").append(label).append("</name>\n");
            cKML.append("<Point>\n");
            cKML.append("<altitudeMode>clampToGround</altitudeMode>\n");
            cKML.append("<coordinates>\n");
            cKML.append(getMiddlePoint().getLongitude()).append(",").append(getMiddlePoint().getLatitude()).append(",0\n");
            cKML.append("</coordinates>\n");
            cKML.append("</Point>\n");
            cKML.append("</Placemark>\n");
            return cKML.toString();
        }
        return "";
    }
}
