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

import ArmyC2.C2SD.Utilities.RendererSettings;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import missilegraphics.Bearing;
import ovl.schema.gccs.MilbobjectType;
import ovl.utils.Modifiers;
import ovlweb.utils.LatLonPointImpl;
import sec.web.renderer.SECRenderer;

/**
 *
 * @author marty
 */
public class MultipointKML {

    String symbolCode;
    StringBuilder firstPoint = new StringBuilder();
    StringBuilder controlPoints = new StringBuilder();
    String bbox = "-180.0,-90.0,180.0,90.0";
    double bboxPadding = 0.0; // Padding in KM in all directions
    double scale = 1000000.0;
    boolean closed = false;
    Modifiers modifiers = new Modifiers();
    private ArrayList<LatLonPointImpl> positionPoints = new ArrayList<LatLonPointImpl>();
    MilbobjectType milbobject;
    // Let's user choose between 2525Bch2 and 2525C.
    private final int ms2525Bch2 = RendererSettings.Symbology_2525Bch2_USAS_13_14;// 0;
    private final int ms2525C = RendererSettings.Symbology_2525C;// 1;
    private int symStd = ms2525C;
    double labelLat = 0.0;
    double labelLon = 0.0;
    int formatJSON = 1;
    int formatKML = 0;
    private final double nm2m = 1852.0;
    boolean useBBOX = true;
    boolean displayLabels = true;
    boolean addTodoTag = false;
    StringBuilder toDoNotes = new StringBuilder();

    public MultipointKML(MilbobjectType milbobject) {
        this.milbobject = milbobject;
        if (null != milbobject.getMILID()) {
            symbolCode = milbobject.getMILID().toUpperCase();
            modifiers.setModifier("A", symbolCode);
        }

        if (null != milbobject.getSIZE()) {
            //System.out.println("milbobject.getSIZE() => " + milbobject.getSIZE());
            Double size = milbobject.getSIZE().doubleValue() * nm2m * 2;
            if ((size / 1000) > bboxPadding) {
                bboxPadding = size / 1000;
            }
            // OVL units are nautical miles, and size is usually the half-width
            modifiers.setModifier("AM", size.toString());
        }//SIZE
        if (null != milbobject.getRADIUS()) {
            if (milbobject.getRADIUS().doubleValue() > 0.0) {
                //System.out.println("milbobject.getSIZE() => " + milbobject.getSIZE());
                Double size = milbobject.getRADIUS().doubleValue() * nm2m;
                if ((size / 1000) > bboxPadding) {
                    bboxPadding = size / 1000;
                }
                // OVL units are nautical miles, and size is usually the half-width
                modifiers.setModifier("AM", size.toString());
            }
        }//RADIUS

        if (null != milbobject.getC()) {
            modifiers.setModifier("C", milbobject.getC().toPlainString());
        }
        if (null != milbobject.getH()) {
            modifiers.setModifier("H", milbobject.getH());
        }
        if (null != milbobject.getH1()) {
            modifiers.setModifier("H1", milbobject.getH1());
        }
        if (null != milbobject.getH2()) {
            modifiers.setModifier("H2", milbobject.getH2());
        }
        if (null != milbobject.getN()) {
            modifiers.setModifier("N", milbobject.getN());
        }
        if (null != milbobject.getQ()) {
            modifiers.setModifier("Q", milbobject.getQ().toPlainString());
        }

        if (null != milbobject.getT()) {
            modifiers.setModifier("T", milbobject.getT());
        }
        if (null != milbobject.getT1()) {
            modifiers.setModifier("T1", milbobject.getT1());
        }
        if (null != milbobject.getV()) {
            modifiers.setModifier("V", milbobject.getV());
        }
        if (null != milbobject.getW()) {
            modifiers.setModifier("W", milbobject.getW());
        }
        if (null != milbobject.getW1()) {
            modifiers.setModifier("W1", milbobject.getW1());
        }
        if (null != milbobject.getX()) {
            modifiers.setModifier("X", milbobject.getX().toPlainString());
        }
        if (null != milbobject.getY()) {
            modifiers.setModifier("Y", milbobject.getY());
        }
        if (null != milbobject.getCLOSED()) {
            this.closed = milbobject.getCLOSED();
        }
        this.setPositionPoints();

        // SPECIAL CASES WHERE OVL IS FUBAR
        if ((symbolCode.contains("FPATR-")) || (symbolCode.contains("FAATR-"))) {
            // Special case for broken OVL format for RECT TGT AREA
            Double width = 0.0;
            if (null != milbobject.getRADIUS()) {
                width = milbobject.getRADIUS().doubleValue() * nm2m;
            }//RADIUS
            ovlweb.utils.Bearing b = new ovlweb.utils.Bearing();
            ovlweb.utils.Bearing.calculateBearing(positionPoints.get(1), positionPoints.get(0), b);
            Double length = b.getDistance() * nm2m;
            Double az = b.getBackAzimuth();
            String myAM = width.toString() + "," + length.toString();
            modifiers.setModifier("AM", myAM);
            modifiers.setModifier("AN", az.toString());

        }
        // GCCS-J has incorrect code for FSCL:G*FPLF--------X should be G*FPLCF---****X
        if (symbolCode.contains("FPLF-")) {
            symbolCode = symbolCode.replace("FPLF-", "FPLCF");
        }
        if (symbolCode.contains("FALF-")) {
            symbolCode = symbolCode.replace("FALF-", "FALCF");
        }
        // GCCS-J has incorrect code for NFL:G*FPLN--------X should be G*FPLCN---****X
        if (symbolCode.contains("FPLN-")) {
            symbolCode = symbolCode.replace("FPLN-", "FPLCN");
        }
        if (symbolCode.contains("FPLN-")) {
            symbolCode = symbolCode.replace("FPLN-", "FALCN");
        }
        // GCCS-J has incorrect code for RFL: GOFPLR--------X should be G*FPLCR---****X
        if (symbolCode.contains("FPLR-")) {
            symbolCode = symbolCode.replace("FPLR-", "FPLCR");
        }
        if (symbolCode.contains("FALR-")) {
            symbolCode = symbolCode.replace("FALR-", "FALCR");
        }
        // GCCS-J has incorrect code for LINEAR TARGET: G*FPLL--------X should be G*FPLT----****X
        if (symbolCode.contains("FPLL-")) {
            symbolCode = symbolCode.replace("FPLL-", "FPLT-");
        }
        if (symbolCode.contains("FALL-")) {
            symbolCode = symbolCode.replace("FALL-", "FALT-");
        }
        // GCCS-J has incorrect code for LINEAR SMOKE TARGET: GSFPLS--------X should be G*FPLTS---****X
        if (symbolCode.contains("FPLS-")) {
            symbolCode = symbolCode.replace("FPLS-", "FPLTS");
        }
        if (symbolCode.contains("FALS-")) {
            symbolCode = symbolCode.replace("FALS-", "FALTS");
        }
        // GCCS-J has incorrect code for FPF: G*FPLP--------X should be G*FPLTF---****X
        if (symbolCode.contains("FPLP-")) {
            symbolCode = symbolCode.replace("FPLP-", "FPLTF");
        }
        if (symbolCode.contains("FALP-")) {
            symbolCode = symbolCode.replace("FALS-", "FALTF");
        }
        // GCCS-J has incorrect code for NFA: G*FPAN--------X should be G*FPACNI--****X
        if (symbolCode.contains("FPAN--")) {
            symbolCode = symbolCode.replace("FPAN--", "FPACNI");
        }
        if (symbolCode.contains("FAAN--")) {
            symbolCode = symbolCode.replace("FAAN--", "FAACNI");
        }
        // GCCS-J has incorrect code for PAA:  G*FPAP--------X should be G*FPACPR--****X
        if (symbolCode.contains("FPAP--")) {
            symbolCode = symbolCode.replace("FPAP--", "FPACPR");
            // PAA may be missing the AM modifier
            ovlweb.utils.Bearing b = new ovlweb.utils.Bearing();
            ovlweb.utils.Bearing.calculateBearing(positionPoints.get(1), positionPoints.get(0), b);
            Double length = b.getDistance() * nm2m / 2.0;
            String myAM = length.toString();
            modifiers.setModifier("AM", myAM);
        }
        if (symbolCode.contains("FAAP--")) {
            symbolCode = symbolCode.replace("FAAP--", "FAACPR");
            // PAA may be missing the AM modifier
            ovlweb.utils.Bearing b = new ovlweb.utils.Bearing();
            ovlweb.utils.Bearing.calculateBearing(positionPoints.get(1), positionPoints.get(0), b);
            Double length = b.getDistance() * nm2m / 2.0;
            String myAM = length.toString();
            modifiers.setModifier("AM", myAM);
        }
        // GCCS-J has incorrect code for G*FPPS--------X should be G*FPPCF---****X
        if (symbolCode.contains("FPPS-")) {
            symbolCode = symbolCode.replace("FPPS-", "FPPCF");
        }
        if (symbolCode.contains("FAPS-")) {
            symbolCode = symbolCode.replace("FAPS-", "FAPCF");
        }
        if (symbolCode.contains("MPNM")) {
            this.toDoNotes.append("<br>NOTES<br>");
            this.toDoNotes.append("symbolCode ").append(symbolCode).append("<br>");
            this.toDoNotes.append("milbobject.getRADIUS() ").append(milbobject.getRADIUS()).append("<br>");
            this.toDoNotes.append("positionPoints ").append(positionPoints).append("<br>");
            addTodoTag = true;
        }
        // GCCS-J has incorrect azimuth tag for G*FPAXS-------X and needs the AM,AN modifiers
        if (symbolCode.contains("FAAXS--")
                || symbolCode.contains("FPAXS--")) {
            StringBuilder myAM = new StringBuilder();
            StringBuilder myAN = new StringBuilder();
//            if (null != milbobject.getRADIUS()) {
//                if (milbobject.getRADIUS().doubleValue() > 0.0) {
//                    Double az = milbobject.getRADIUS().doubleValue();
//                    myAN.append((int) Math.round(az));
//                    myAN.append(",");
//
//                }
//            }//RADIUS 
            int startPoint = 1;
            for (int i = startPoint; i < positionPoints.size(); i = i + 2) {
                ovlweb.utils.Bearing b = new ovlweb.utils.Bearing();
                // Inner radius and start az
                ovlweb.utils.Bearing.calculateBearing(positionPoints.get(i + 1), positionPoints.get(0), b);
                Double lengthInner = b.getDistance() * 1000; //convert km to m
                Double azStart = b.getBackAzimuth();
                // Outer radius and end az
                ovlweb.utils.Bearing.calculateBearing(positionPoints.get(i), positionPoints.get(0), b);
                Double lengthOuter = b.getDistance() * 1000; //convert km to m
                Double azEnd = b.getBackAzimuth();
                if (i > startPoint) {
                    myAM.append(",");
                    myAM.append((int) Math.round(lengthOuter));
                    myAN.append(",");
                    myAN.append((int) Math.round(azStart)).append(",").append((int) Math.round(azEnd));
                    //myAN.insert(0, ",");
                } else {
                    myAM.append((int) Math.round(lengthInner)).append(",").append((int) Math.round(lengthOuter));
                    //myAN.append((int) Math.round(azEnd)).append(",").append((int) Math.round(azStart)).append(",");
                    myAN.append((int) Math.round(azStart)).append(",").append((int) Math.round(azEnd));
                    //myAN.insert(0, ((int) Math.round(az)));
                }

            }
            controlPoints = new StringBuilder();
            controlPoints.append(firstPoint);
            modifiers.setModifier("AM", "[" + myAM + "]");
            modifiers.setModifier("AN", "[" + myAN.toString() + "]");
            //addTodoTag = true;
        }
        // GCCS-J has incorrect azimuth tag for G*FPAXC-------X and needs the AM,AN modifiers
        if (symbolCode.contains("FPAXC--")
                || symbolCode.contains("FAAXC--")) {
            StringBuilder myAM = new StringBuilder();
            StringBuilder myAN = new StringBuilder();
            if (null != milbobject.getRADIUS()) {
                if (milbobject.getRADIUS().doubleValue() > 0.0) {
                    Double az = milbobject.getRADIUS().doubleValue();
                    myAN.append((int) Math.round(az));
                    myAN.append(",");

                }
            }//RADIUS 
            int startIndex = 1;
            for (int i = startIndex; i < positionPoints.size(); i++) {
                ovlweb.utils.Bearing b = new ovlweb.utils.Bearing();
                ovlweb.utils.Bearing.calculateBearing(positionPoints.get(i), positionPoints.get(0), b);
                Double length = b.getDistance() * 1000; //convert km to m
                Double az = b.getBackAzimuth();
                if (i > startIndex) {
                    myAM.append(",");
                    myAN.append(",");
                }
                myAM.append((int) Math.round(length));
                myAN.append((int) Math.round(az));
            }
            //myAM = new StringBuilder("");
//            myAM = new StringBuilder("1111,2222,3333,4444,5555,6666,7777");
            //myAN = new StringBuilder("0,360,0,360,0,360,0,360,0,360,0,360");
            this.toDoNotes.append("<br>NOTES<br>");
            this.toDoNotes.append("symbolCode ").append(symbolCode).append("<br>");
            this.toDoNotes.append("milbobject.getRADIUS() ").append(milbobject.getRADIUS()).append("<br>");
            this.toDoNotes.append("positionPoints ").append(positionPoints).append("<br>");
            modifiers.setModifier("AM", "[" + myAM + "]");
            modifiers.setModifier("AN", "[" + myAN.toString() + "]");
            addTodoTag = false;
        }

    }

    public double getLabelLat() {
        return labelLat;
    }

    public double getLabelLon() {
        return labelLon;
    }

    public void setDisplayLabels(boolean displayLabels) {
        this.displayLabels = displayLabels;
    }

    public void setUseBBOX(boolean useBBOX) {
        this.useBBOX = useBBOX;
    }

    public void set2525Bch2() {
        this.symStd = this.ms2525Bch2;
    }

    public void set2525C() {
        this.symStd = this.ms2525C;
    }

    public void setSymbolCode(String symbolCode) {
        this.symbolCode = symbolCode;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    private void setPositionPoints() {
        //Build LatLonPoints for each BigDecimal Pair
        Double minLat = 0.0, maxLat = 0.0, minLon = 0.0, maxLon = 0.0;
        int pCount = 0;
        double avgLat = 0.0, avgLon = 0.0;
        for (JAXBElement<List<BigDecimal>> pair : milbobject.getPOSITION()) {
            String[] point = pair.getValue().toString().replace("[", "").replace("]", "").split(",");
            double lat = Double.parseDouble(point[0]);
            double lon = Double.parseDouble(point[1].replace(" ", ""));
            LatLonPointImpl P1 = new LatLonPointImpl(lat, lon);
            positionPoints.add(P1);
            controlPoints.append(lon).append(",").append(lat).append(" ");
            if (pCount == 0) {
                firstPoint.append(lon).append(",").append(lat).append(" ");
            }
            if (pCount == 0) {
                minLat = lat;
                maxLat = lat;
                minLon = lon;
                maxLon = lon;
            } else {
                if (lat > maxLat) {
                    maxLat = lat;
                }
                if (lon > maxLon) {
                    maxLon = lon;
                }
                if (lat < minLat) {
                    minLat = lat;
                }
                if (lon < minLon) {
                    minLon = lon;
                }
            }
            avgLat = avgLat + lat;
            avgLon = avgLon + lon;
            pCount++;
        }
        if (this.closed) {
            controlPoints.append(firstPoint);
        }
        if (bboxPadding > 0) {
            // Padd the bbox, this usually occurs when the control points have a width defined
            // so we add the width (or radius) to the bbox to account for rendereing the graphic
            missilegraphics.LatLonPointImpl p = new missilegraphics.LatLonPointImpl(minLat, minLon);
            missilegraphics.LatLonPointImpl minP = new missilegraphics.LatLonPointImpl();
            Bearing.findPoint(p, -90, this.bboxPadding, minP);
            minLon = minP.getLongitude();
            Bearing.findPoint(p, -180, this.bboxPadding, minP);
            minLat = minP.getLatitude();
            p = new missilegraphics.LatLonPointImpl(maxLat, maxLon);
            missilegraphics.LatLonPointImpl maxP = new missilegraphics.LatLonPointImpl();
            Bearing.findPoint(p, 90, this.bboxPadding, maxP);
            maxLon = maxP.getLongitude();
            Bearing.findPoint(p, 0, this.bboxPadding, maxP);
            maxLat = maxP.getLatitude();
        } else {
            // pad the bbox 
            double delLat = Math.abs(maxLat - minLat) * 0.1;
            double delLon = Math.abs(maxLon - minLon) * 0.1;
            minLon = minLon - (delLon);
            minLat = minLat - (delLat);
            maxLon = maxLon + (delLon);
            maxLat = maxLat + (delLat);
        }

        bbox = minLon.toString() + "," + minLat.toString() + "," + maxLon.toString() + "," + maxLat;
        labelLat = (avgLat / pCount);
        labelLon = (avgLon / pCount);
        if (null != milbobject.getLABELPOSITION()) {
            if (milbobject.getLABELPOSITION().size() > 0) {
                labelLat = milbobject.getLABELPOSITION().get(0).doubleValue();
                labelLon = milbobject.getLABELPOSITION().get(1).doubleValue();
            }
        }
        //System.out.println("BBOX: " + bbox);
        //System.out.println("CONTROL POINTS: \n" + controlPoints.toString());
    }//setPositionPoints

    public String getBboxKML(String lineColor, String lineWidth) {
        String name = "BBOX";
        if (null != milbobject.getNAME()) {
            name = "BBOX: " + milbobject.getNAME();
        }
        String coords[] = bbox.split(",");
        RectKML bboxKml = new RectKML(Double.parseDouble(coords[1]),
                Double.parseDouble(coords[0]),
                Double.parseDouble(coords[3]),
                Double.parseDouble(coords[2]),
                lineWidth, name);
        return bboxKml.getKML(name, lineColor, lineWidth, "00000000");
    }

    public String getKML(String name, String description) {
//        System.out.println(name + " MODIFIERS: " + modifiers.toString());
//        System.out.println("     SCALE: " + scale);
//        System.out.println("     BBOX PADDING (KM): " + this.bboxPadding);
//        System.out.println("     BBOX: " + bbox + "\n");
        String bb = bbox;
        if (this.useBBOX) {
            bb = bbox;
        } else {
            bb = "";
        }
        if (this.addTodoTag) {
            description = "<b>TODO: FIX THIS GRAPHIC</b><br><br>"
                    + "CONTROL POINTS: " + this.controlPoints.toString() + "<br>"
                    + "MODIFIERS: " + this.modifiers.toString() + "<br>"
                    + description;
            if (!this.toDoNotes.toString().isEmpty()) {
                description = description + this.toDoNotes.toString();
            }
        }
        String desc = description + "<br>" + this.modifiers.toHTMLTable();
        String kml = SECRenderer.getInstance().RenderMultiPointSymbol("ID", name, desc,
                symbolCode, controlPoints.toString(), "clampToGround", scale, bb, modifiers.toString(), formatKML, symStd);
        if (!this.displayLabels) {
            kml = kml.replaceAll("<scale>1.0</scale></LabelStyle>", "<scale>0</scale></LabelStyle>");
        }
        if (kml.startsWith("{\"type\":\"error\"")) {
            kml = "<description>" + kml + "\n" + modifiers.toString() + "</description>";
        }
        return kml;
    }

}
