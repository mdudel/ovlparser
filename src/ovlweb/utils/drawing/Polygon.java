/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils.drawing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import ovlweb.utils.LatLonPointImpl;
import ovlweb.utils.drawing.tacticalgraphics.AbstractTacticalGraphic;

/**
 *
 * @author Kenneth Keith <kkeith@mitre.org>
 */
public final class Polygon extends AbstractTacticalGraphic {

    public final String GRAPHIC_NAME = "POLYGON";
    public final String GRAPHIC_STD = "MIL-STD-2525C";
    public final String GRAPHIC_PATH = "TACGRP.TSK.FLWASS";
    public final String GRAPHIC_HIERARCHY = "2.X.1.12";
    public final String GRAPHIC_CODE = "G*TPA-----****X";
    public final int GRAPHIC_POINTS = 2;
    private ArrayList<LatLonPointImpl> polygonPoints = new ArrayList<LatLonPointImpl>();
    private ArrayList<LineString> polygonLines = new ArrayList<LineString>();

    public Polygon(LatLonPointImpl... points) {
        polygonPoints.addAll(Arrays.asList(points));
        computeNormalGraphic();
    }

    public Polygon(ArrayList<LatLonPointImpl> points) {
        polygonPoints.addAll(points);
        computeNormalGraphic();
    }

    @Override
    public void computeSimpleGraphic(LatLonPointImpl point, double bearing, double width, double height) {
    }

    public String getPolygonOutline(){
        if(polygonLines.size()>0){
            StringBuilder returnPolygon = new StringBuilder();
            for(LineString ls:polygonLines){
                returnPolygon.append(ls.getKMLShape());
            }
            return returnPolygon.toString();
        }
        return "";
    }
    
    @Override
    public void computeNormalGraphic() {
        if (polygonPoints.get(0) == polygonPoints.get(polygonPoints.size() - 1)) {
            for (LatLonPointImpl p : polygonPoints) {
                if (polygonPoints.indexOf(p) != polygonPoints.size() - 1) {
                    LineString ls = new LineString(p, polygonPoints.get(polygonPoints.indexOf(p) + 1));
                    polygonLines.add(ls);
                }
            }
        }else{
            polygonPoints.add(new LatLonPointImpl(polygonPoints.get(0)));
            for (LatLonPointImpl p : polygonPoints) {
                if (polygonPoints.indexOf(p) != polygonPoints.size() - 1) {
                    LineString ls = new LineString(p, polygonPoints.get(polygonPoints.indexOf(p) + 1));
                    polygonLines.add(ls);
                }
            }
        }
    }

    @Override
    public String getKMLPlacemark(String label, String description, Color linecolor, int linewidth) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getKMLShape() {
        if(polygonLines.size()>0){
            StringBuilder returnPolygon = new StringBuilder();
            returnPolygon.append("<Polygon>\n");
            returnPolygon.append("<extrude>0</extrude>\n");
            returnPolygon.append("<outerBoundaryIs>");
            returnPolygon.append("<LinearRing>\n");
            returnPolygon.append("<coordinates>\n");
            for(LatLonPointImpl p:polygonPoints){
                returnPolygon.append(p.getLongitude()+","+p.getLatitude()+",0\n");
            }
            returnPolygon.append("</coordinates>\n");
            returnPolygon.append("</LinearRing>\n");
            returnPolygon.append("</outerBoundaryIs>");
            returnPolygon.append("</Polygon>\n");
            return returnPolygon.toString();
        }
        return "";
    }
}
