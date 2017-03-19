/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlparser;

import ArmyC2.C2SD.Utilities.MilStdAttributes;
import graphics.ArcKML;
import graphics.ArrowKML;
import graphics.BrgBoxKML;
import graphics.CorridorKML;
import graphics.EllipseKML;
import graphics.EllipticalArcKML;
import graphics.FanKML;
import graphics.MultipointKML;
import graphics.PolygonKML;
import graphics.RectKML;
import graphics.SinglePointKML;
import graphics.TextKML;
import interfaces.ICommonProperties;
import interfaces.OvlGraphicVisitor;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import ovl.schema.gccs.ArcType;
import ovl.schema.gccs.ArrowType;
import ovl.schema.gccs.BoxType;
import ovl.schema.gccs.CircleType;
import ovl.schema.gccs.CorridorType;
import ovl.schema.gccs.EllipseType;
import ovl.schema.gccs.EllipticalArcType;
import ovl.schema.gccs.MilbobjectType;
import ovl.schema.gccs.PolygonType;
import ovl.schema.gccs.RectangleType;
import ovl.schema.gccs.SectorType;
import ovl.schema.gccs.SymbolType;
import ovl.schema.gccs.TacsymbolType;
import ovl.schema.gccs.TextType;
import ovl.schema.gccs.UnitType;
import ovl.utils.KMlUtils;
import sec.web.renderer.SECRenderer;
import sec.web.renderer.utilities.PNGInfo;

/**
 *
 * @author marty
 */
public class Ovl2KmlVisitor implements OvlGraphicVisitor {

    public static HashMap<String, String> threatColor = new HashMap<String, String>();
    // Use Gloabl class in tmsweb

    private final StringBuilder kml = new StringBuilder();
    private static final double NM2KM = 1.852;
    private static final double NM2M = 1852;
    private static final double KM2NM = 0.539957;
    private int pointCount = 96;
    private HashMap<String, Map<String, String>> singlePoints = new HashMap<String, Map<String, String>>();
    // TODO: Build this dynamically or make it a property
    String iconURL = "http://cop/mil2525/?size=60&amp;symbol=";
    String imageType = "";
    private boolean debug = false;

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Ovl2KmlVisitor() {
        threatColor.put("F", "ffffff00");
        threatColor.put("U", "ff00ffff");
        threatColor.put("N", "ff00ff00");
        threatColor.put("R", "ff0000ff");
        threatColor.put("A", "ffffff00");
        threatColor.put("H", "ff0000ff");
        threatColor.put("S", "ff00aaff");
        threatColor.put("P", "ff00ffff");
        threatColor.put("G", "ff00ffff");
        threatColor.put("W", "ff00ffff");
        threatColor.put("D", "ffffff00");
        threatColor.put("M", "ffffff00");
        threatColor.put("L", "ff00ff00");
        threatColor.put("J", "ff0000ff");
        threatColor.put("K", "ff0000ff");
    }

    public Ovl2KmlVisitor(int pointCount) {
        this.pointCount = pointCount;
    }

    public String getKmlFillColor(ICommonProperties colorHolder) {
        String fc = "00000000";
        KMlUtils kmlUtils = new KMlUtils();
        if (null != colorHolder.getFILLCOLOR()) {
            kmlUtils.setRgb(colorHolder.getFILLCOLOR());
        }
        if (colorHolder.getFILLTYPE() != null) {
            if (colorHolder.getFILLTYPE() == 0) {
                fc = "ff" + kmlUtils.getKmlColor().substring(2);
            } else if (colorHolder.getFILLTYPE() == 50) {
                fc = "7f" + kmlUtils.getKmlColor().substring(2);
            } else if (colorHolder.getFILLTYPE() == 25) {
                fc = "40" + kmlUtils.getKmlColor().substring(2);
            } else if (colorHolder.getFILLTYPE() == 10) {
                fc = "10" + kmlUtils.getKmlColor().substring(2);
            }
        }
        if (null == fc) {
            fc = "00000000";
        }
        return fc;
    }

    public String getKmlLineColor(ICommonProperties colorHolder) {
        KMlUtils kmlUtils = new KMlUtils();
        kmlUtils.setRgb(colorHolder.getLINECOLOR());
        String lc = kmlUtils.getKmlColor();
        if (null == lc) {
            lc = "00000000";
        }
        return lc;
    }

    public String[] getHexRGB(String ovlColor) {
        KMlUtils kmlUtils = new KMlUtils();
        String[] hexRGB = new String[3];
        hexRGB[0] = kmlUtils.getRhex();
        hexRGB[1] = kmlUtils.getGhex();
        hexRGB[2] = kmlUtils.getBhex();
        return hexRGB;
    }

    public String getKmlTextColor(ICommonProperties colorHolder) {
        String tc = colorHolder.getTEXTCOLOR();
        if (null == tc) {
            tc = "00000000";
        }
        return tc;
    }

    public boolean isLabel(ICommonProperties colorHolder) {
        boolean isLabel = false;
        if (colorHolder.getLABELTYPE() != null) {
            if (colorHolder.getLABELTYPE().toUpperCase().equals("DESCRIPTION")) {
                isLabel = true;
            } else if (colorHolder.getLABELTYPE().toUpperCase().equals("NAME")) {
                isLabel = true;
            } else if (colorHolder.getLABELTYPE().toUpperCase().equals("MINSCALE")) {
                //ovlName = Integer.toString(this.getMINSCALE());
                isLabel = false;
            } else if (colorHolder.getLABELTYPE().toUpperCase().equals("MAXSCALE")) {
                //ovlName = Integer.toString(this.getMAXSCALE());
                isLabel = false;
            } else if (colorHolder.getLABELTYPE().toUpperCase().equals("TYPE")) {
                isLabel = true;
            }
        }
        return isLabel;
    }

    public String getKmlLabel(ICommonProperties colorHolder) {
        String ovlName = colorHolder.getNAME();

        if (colorHolder.getLABELTYPE() != null) {
            if (colorHolder.getLABELTYPE().toUpperCase().equals("DESCRIPTION")) {
                ovlName = colorHolder.getDESCRIPTION();
            } else if (colorHolder.getLABELTYPE().toUpperCase().equals("NAME")) {
                ovlName = colorHolder.getNAME();
            } else if (colorHolder.getLABELTYPE().toUpperCase().equals("MINSCALE")) {
                //ovlName = Integer.toString(this.getMINSCALE());
            } else if (colorHolder.getLABELTYPE().toUpperCase().equals("MAXSCALE")) {
                //ovlName = Integer.toString(this.getMAXSCALE());
            } else if (colorHolder.getLABELTYPE().toUpperCase().equals("TYPE")) {
                ovlName = "{TYPE}";
            }
        }
        if (null == ovlName) {
            ovlName = "";
        }
        return ovlName;
    }

    public String getKmlTitlePlacemark(String title, String textColor, double lat, double lon) {
        KMlUtils kmlUtils = new KMlUtils();
        String kmlPm = kmlUtils.getKmlTitle(title, textColor, lat, lon);
        if (null == kmlPm) {
            kmlPm = "";
        }
        return kmlPm;
    }

    public String getKmlLineWidth(ICommonProperties colorHolder) {
        String lineWidth = "2";
        if (colorHolder.getLINEWIDTH() == null) {
            lineWidth = "2";
        } else {
            lineWidth = colorHolder.getLINEWIDTH().toString();
        }
        return lineWidth;
    }

    public String getKmlDescription(ICommonProperties colorHolder) {
        StringBuilder desc = new StringBuilder();
        boolean isDesc = false;
        desc.append("<table>\n<tbody>\n");
        if (null != colorHolder.getDESCRIPTION()) {
            desc.append("<tr>").append("<td>");
            desc.append(colorHolder.getDESCRIPTION().replaceAll("\n", "<br>"));
            desc.append("</td>").append("</tr>\n");
            isDesc = true;
        }
        if (null != colorHolder.getREMARKS()) {
            desc.append("<tr>").append("<td>");
            desc.append(colorHolder.getREMARKS().replaceAll("\n", "<br>"));
            desc.append("</td>").append("</tr>\n");
            isDesc = true;
        }
        desc.append("</tbody></table>\n");
        //System.out.println(desc.toString());
        if (isDesc) {
            return desc.toString();
        }
        return "";
    }

    public List<BigDecimal> getObjectPoints(Object o) {
        List<BigDecimal> points;
        if (o.getClass().equals(CircleType.class)) {
            CircleType circle = (CircleType) o;
            points = circle.getCENTER();
        } else if (o.getClass().equals(EllipseType.class)) {
            EllipseType ellipse = (EllipseType) o;
            points = ellipse.getCENTER();
        }
        return null;
    }

    private void startMsg(Object o) {
        if (!debug) {
            return;
        }
        System.out.println("[START] Parsing ovl object type: " + o.getClass().getName());
    }

    private void endMsg() {
        if (!debug) {
            return;
        }
        System.out.println("[END  ] Parsing ovl object ");
    }

    @Override
    public void visit(CircleType circle) {
        startMsg(circle);
        String lc = this.getKmlLineColor(circle);
        String fc = this.getKmlFillColor(circle);
        String ovlName = this.getKmlLabel(circle);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "CIRCLE";
        }
        String tc = this.getKmlTextColor(circle);

        double lat = circle.getCENTER().get(0).doubleValue();
        double lon = circle.getCENTER().get(1).doubleValue();
        // OVL distance units are nautical miles
        double rad = circle.getRADIUS().doubleValue() * NM2KM;
        String lineWidth = this.getKmlLineWidth(circle);
        String desc = this.getKmlDescription(circle);

        EllipseKML ellipse = new EllipseKML(lat, lon, 0.0, rad, rad, 0.0, this.pointCount);
        kml.append("<Folder><name>CIRCLE: ").append(ovlName).append("</name>");
        kml.append(ellipse.getKML(ovlName, desc, lc, lineWidth, fc));
        if (this.isLabel(circle)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, lat, lon));
        }
        kml.append("</Folder>");
        endMsg();
    }//circle

    @Override
    public void visit(EllipseType ellipse) {
        startMsg(ellipse);
        String lc = this.getKmlLineColor(ellipse);
        String fc = this.getKmlFillColor(ellipse);
        String ovlName = this.getKmlLabel(ellipse);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "ELLIPSE";
        }
        String tc = this.getKmlTextColor(ellipse);
        double lat = ellipse.getCENTER().get(0).doubleValue();
        double lon = ellipse.getCENTER().get(1).doubleValue();
        // OVL distance units are nautical miles
        double smjr = ellipse.getSEMIMAJOR().doubleValue() * NM2KM;
        double smnr = ellipse.getSEMIMINOR().doubleValue() * NM2KM;
        double brg = ellipse.getBEARING().doubleValue();
        String lineWidth = this.getKmlLineWidth(ellipse);
        String desc = this.getKmlDescription(ellipse);
        EllipseKML ellipseKml = new EllipseKML(lat, lon, 0.0, smjr, smnr, brg, this.pointCount);
        kml.append("<Folder><name>ELLIPSE: ").append(ovlName).append("</name>");
        kml.append(ellipseKml.getKML(ovlName, desc, lc, lineWidth, fc));
        if (this.isLabel(ellipse)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, lat, lon));
        }
        kml.append("</Folder>");
        endMsg();
    }//ellipse

    @Override
    public void visit(RectangleType rectum) {
        startMsg(rectum);
        String lc = this.getKmlLineColor(rectum);
        String fc = this.getKmlFillColor(rectum);
        String ovlName = this.getKmlLabel(rectum);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "RECTANGLE";
        }
        String tc = this.getKmlTextColor(rectum);
        String lineWidth = this.getKmlLineWidth(rectum);
        String desc = this.getKmlDescription(rectum);
        double lat1 = rectum.getPOINT1().get(0).doubleValue();
        double lon1 = rectum.getPOINT1().get(1).doubleValue();
        double lat2 = rectum.getPOINT2().get(0).doubleValue();
        double lon2 = rectum.getPOINT2().get(1).doubleValue();
        RectKML rect = new RectKML(lat1, lon1, lat2, lon2, lineWidth, ovlName);
        kml.append("<Folder><name>RECTANGLE: ").append(ovlName).append("</name>");
        kml.append(rect.getKML(ovlName, desc, lc, lineWidth, fc));
        if (this.isLabel(rectum)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, ((lat1 + lat2) / 2.0), ((lon1 + lon2) / 2.0)));
        }
        kml.append("</Folder>");
        endMsg();
    }//rectangle

    @Override
    public void visit(BoxType box) {
        startMsg(box);
        String lc = this.getKmlLineColor(box);
        String fc = this.getKmlFillColor(box);
        String ovlName = this.getKmlLabel(box);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "BOX";
        }
        String tc = this.getKmlTextColor(box);
        String lineWidth = this.getKmlLineWidth(box);
        String desc = this.getKmlDescription(box);
        double lat = box.getCENTER().get(0).doubleValue();
        double lon = box.getCENTER().get(1).doubleValue();
        double brg = box.getBEARING().doubleValue();
        double width = box.getWIDTH().doubleValue() * NM2KM;
        double length = box.getLENGTH().doubleValue() * NM2KM;
        BrgBoxKML brgbox = new BrgBoxKML(lat, lon, (width / 2.0), length, brg);
        kml.append("<Folder><name>BRGBOX: ").append(ovlName).append("</name>");
        kml.append(brgbox.getSimpleBoxAsKML(ovlName, desc, lc, lineWidth, fc));
        if (this.isLabel(box)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, brgbox.getCenterLat(), brgbox.getCenterLon()));
        }
        kml.append("</Folder>");
        endMsg();
    }//box

    @Override
    public void visit(ArcType arc) {
        startMsg(arc);
        String lc = this.getKmlLineColor(arc);
        //String fc = this.getKmlFillColor(arc);
        String ovlName = this.getKmlLabel(arc);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "ARC";
        }
        String tc = this.getKmlTextColor(arc);
        String lineWidth = this.getKmlLineWidth(arc);
        String desc = this.getKmlDescription(arc);
        double lat = arc.getCENTER().get(0).doubleValue();
        double lon = arc.getCENTER().get(1).doubleValue();
        double radius = arc.getRADIUS().doubleValue() * NM2KM;
        double brg0 = arc.getSTARTBEARING().doubleValue();
        double brg1 = arc.getENDBEARING().doubleValue();
        ArcKML a = new ArcKML(lat, lon, radius, brg0, brg1, this.pointCount);
        kml.append("<Folder><name>ARC: ").append(ovlName).append("</name>");
        kml.append(a.getKML(ovlName, desc, lc, lineWidth));
        if (this.isLabel(arc)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, lat, lon));
        }
        kml.append("</Folder>");
        endMsg();
    }//arc

    @Override
    public void visit(SectorType sector) {
        startMsg(sector);
        String lc = this.getKmlLineColor(sector);
        //String fc = this.getKmlFillColor(arc);
        String ovlName = this.getKmlLabel(sector);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "SECTOR";
        }
        String tc = this.getKmlTextColor(sector);
        String fc = this.getKmlFillColor(sector);
        String lineWidth = this.getKmlLineWidth(sector);
        String desc = this.getKmlDescription(sector);
        double lat = sector.getCENTER().get(0).doubleValue();
        double lon = sector.getCENTER().get(1).doubleValue();
        double radiusInner = sector.getINNERRADIUS().doubleValue() * NM2KM;
        double radiusOuter = sector.getOUTERRADIUS().doubleValue() * NM2KM;
        double brg0 = sector.getLEFTBEARING().doubleValue();
        double brg1 = sector.getRIGHTBEARING().doubleValue();
        FanKML fan = new FanKML(lat, lon, 0.0, radiusInner, radiusOuter, brg0, brg1, this.pointCount);
        kml.append("<Folder><name>ARC: ").append(ovlName).append("</name>");
        kml.append(fan.getKML(ovlName, desc, lc, lineWidth, fc));
        if (this.isLabel(sector)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, lat, lon));
        }
        kml.append("</Folder>");
        endMsg();
    }//sector

    @Override
    public void visit(ArrowType arrow) {
        startMsg(arrow);
        String lc = this.getKmlLineColor(arrow);
        String tc = this.getKmlTextColor(arrow);
        String fc = this.getKmlFillColor(arrow);
        String ovlName = this.getKmlLabel(arrow);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "ARROW";
        }
        String lineWidth = this.getKmlLineWidth(arrow);
        String desc = this.getKmlDescription(arrow);
        ArrowKML a = new ArrowKML(arrow);
        kml.append("\n<Folder><name>ARROW: ").append(ovlName).append("</name>\n");
        kml.append(a.getKML(ovlName, desc, lc, lineWidth, fc));
        if (this.isLabel(arrow)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, a.getLabelLat(), a.getLabelLon()));
        }
        kml.append("\n</Folder>");
        endMsg();
    }

    @Override
    public void visit(EllipticalArcType arc) {
        startMsg(arc);
        String lc = this.getKmlLineColor(arc);
        String fc = this.getKmlFillColor(arc);
        String ovlName = this.getKmlLabel(arc);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "ELLIPTICAL ARC";
        }
        String tc = this.getKmlTextColor(arc);
        String lineWidth = this.getKmlLineWidth(arc);
        String desc = this.getKmlDescription(arc);
        double lat = arc.getCENTER().get(0).doubleValue();
        double lon = arc.getCENTER().get(1).doubleValue();
        double brg = arc.getBEARING().doubleValue();
        // OVL distance units are nautical miles
        double smjr = arc.getSEMIMAJOR().doubleValue() * NM2KM;
        double smnr = arc.getSEMIMINOR().doubleValue() * NM2KM;
        double startBrg = arc.getSTARTBEARING().doubleValue();
        double endBrg = arc.getENDBEARING().doubleValue();
        EllipticalArcKML arcKml = new EllipticalArcKML(
                lat, lon,
                smjr, smnr,
                brg,
                this.pointCount,
                startBrg, endBrg);
        kml.append("\n<Folder><name>ELLIPTICAL ARC: ").append(ovlName).append("</name>\n");
        kml.append(arcKml.getKML(ovlName, desc, lc, lineWidth, fc));
        if (this.isLabel(arc)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, lat, lon));
        }
        kml.append("</Folder>\n");
        endMsg();
    }// elliptical arc

    @Override
    public void visit(PolygonType polygon) {
        startMsg(polygon);
        String lc = this.getKmlLineColor(polygon);
        String fc = this.getKmlFillColor(polygon);
        String ovlName = this.getKmlLabel(polygon);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "POLYGON";
        }
        String tc = this.getKmlTextColor(polygon);
        String lineWidth = this.getKmlLineWidth(polygon);
        String desc = this.getKmlDescription(polygon);
        PolygonKML polyKml = new PolygonKML(polygon);
        kml.append("\n<Folder><name>POLYGON: ").append(ovlName).append("</name>\n");
        kml.append(polyKml.getKML(ovlName, desc, lc, lineWidth, fc));
        if (this.isLabel(polygon)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, polyKml.getLabelLat(), polyKml.getLabelLon()));
        }
        kml.append("</Folder>\n");
        endMsg();
    }//polygon

    @Override
    public void visit(CorridorType corridor) {
        startMsg(corridor);
        String lc = this.getKmlLineColor(corridor);
        String ovlName = this.getKmlLabel(corridor);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "CORRIDOR";
        }
        String tc = this.getKmlTextColor(corridor);
        String lineWidth = this.getKmlLineWidth(corridor);
        //CorridorType corridor, String textColor, String GraphicColor, String lineWidth, int pointCount
        CorridorKML corrKml = new CorridorKML(corridor, tc, lc, lineWidth, this.pointCount);
        kml.append("\n<Folder><name>CORRIDOR: ").append(ovlName).append("</name>\n");
        kml.append(corrKml.getKML());
        if (this.isLabel(corridor)) {
            kml.append(this.getKmlTitlePlacemark(ovlName, tc, corrKml.getLabelLat(), corrKml.getLabelLon()));
        }
        kml.append("</Folder>\n");
        endMsg();
    }// corridor

    @Override
    public void visit(MilbobjectType milbobject) {
        startMsg(milbobject);
        String symbolCode = "";
        boolean isMultipoint = false;
        if (null != milbobject.getMILID()) {
            symbolCode = milbobject.getMILID();
        }
        //String lc = this.getKmlLineColor(milbobject);
        String ovlName = this.getKmlLabel(milbobject);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "TACTICAL SYMBOL";
        }
        String lc = this.getKmlLineColor(milbobject);
        String tc = this.getKmlTextColor(milbobject);
        String lineWidth = this.getKmlLineWidth(milbobject);
        String desc = this.getKmlDescription(milbobject);

        int cpCount = milbobject.getPOSITION().size();
        if (cpCount > 1) {
            isMultipoint = true;
        }
        if (null != milbobject.getRADIUS()) {
            isMultipoint = true;
        }

        if (!isMultipoint) {
            // SINGLE POINT ----------------------------------------------------
            // TODO: Finish SINGLE POINT graphics
            KMlUtils kmlUtils = new KMlUtils();
            kmlUtils.setRgb(milbobject.getTEXTCOLOR());
            tc = kmlUtils.getKmlColor();
            SinglePointKML symbolKML = new SinglePointKML();
            symbolKML.setImageType(this.imageType);
            symbolKML.setSymbolCode(symbolCode);
            symbolKML.setName(ovlName);
            symbolKML.setDescription(desc);
            for (JAXBElement<List<BigDecimal>> pair : milbobject.getPOSITION()) {
                String[] point = pair.getValue().toString().replace("[", "").replace("]", "").split(",");
                double lat = Double.parseDouble(point[0]);
                double lon = Double.parseDouble(point[1].replace(" ", ""));
                symbolKML.setLatitude(lat);
                symbolKML.setLongitude(lon);
            }

            symbolKML.setIconUrl(iconURL);
            symbolKML.setLabelColor(tc);
            Map<String, String> params = new HashMap<String, String>();
            params.put(MilStdAttributes.PixelSize, "60");
            params.put(MilStdAttributes.KeepUnitRatio, "true");
            singlePoints.put(symbolCode.replace("*", "-"), params);
            double iconSize = 0.8;
            if (null != milbobject.getSIZE()) {
                iconSize = iconSize + 0.6 * ((milbobject.getSIZE().doubleValue() - 12.0) / 52.0);
            }
            symbolKML.setIconSize(iconSize);
            symbolKML.setIsLabel(this.isLabel(milbobject));
            kml.append("\n<Folder><name>SIMPLE TACTICAL SYMBOL ").append(symbolKML.getSymbolCode()).append(": ").append(ovlName).append("</name>\n");
            kml.append(symbolKML.getKML(ovlName, desc));
//        if (this.isLabel(symbol)) {
//            kml.append(this.getKmlTitlePlacemark(ovlName, tc, symbolKML.lat, symbolKML.lon));
//        }
            //kml.append(multipoint.getBboxKML(lc, "2"));
            kml.append("</Folder>\n");
        } else {
            // MULTI POINT -----------------------------------------------------
            MultipointKML multipoint = new MultipointKML(milbobject);
            multipoint.setDisplayLabels(false);
            multipoint.setUseBBOX(false);
            kml.append("\n<Folder><name>TACTICAL SYMBOL ").append(symbolCode).append(": ").append(ovlName).append("</name>\n");
            kml.append(multipoint.getKML(ovlName, desc));
            if (this.isLabel(milbobject)) {
                // TODO: Add modifiers to KML description/balloon
                kml.append(this.getKmlTitlePlacemark(ovlName, tc, multipoint.getLabelLat(), multipoint.getLabelLon()));
            }
            //kml.append(multipoint.getBboxKML(lc, "2"));
            kml.append("</Folder>\n");
        }
        endMsg();
    }// milbobject

    @Override
    public void visit(SymbolType symbol) {
        startMsg(symbol);
        String cat = "UNK";
        String threat = "UNK";
        if (null != symbol.getCATTHREAT()) {
            String[] catThreat;
            catThreat = symbol.getCATTHREAT().split("_");
            cat = catThreat[0];
            threat = catThreat[1];
        }
        String ovlName = this.getKmlLabel(symbol);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "SIMPLE SYMBOL";
        }
        String tc = "FFFFFFFF";
        KMlUtils kmlUtils = new KMlUtils();
        kmlUtils.setRgb(symbol.getTEXTCOLOR());
        tc = kmlUtils.getKmlColor();
        String desc = this.getKmlDescription(symbol);

        SinglePointKML symbolKML = new SinglePointKML();
        symbolKML.setImageType(this.imageType);
        symbolKML.setSymbolCode(cat, threat);
        symbolKML.setName(ovlName);
        symbolKML.setDescription(desc);
        symbolKML.setLatitude(symbol.getPOSITION().get(0).doubleValue());
        symbolKML.setLongitude(symbol.getPOSITION().get(1).doubleValue());
        symbolKML.setIconUrl(iconURL);
        symbolKML.setLabelColor(tc);
        symbolKML.setIsLabel(this.isLabel(symbol));
        Map<String, String> params = new HashMap<String, String>();
        params.put(MilStdAttributes.PixelSize, "60");
        params.put(MilStdAttributes.KeepUnitRatio, "true");
        singlePoints.put(symbolKML.getSymbolCode(), params);
        if (null != symbol.getSYMBOLSIZE()) {
            double iconSize = 0.6 + (0.6 * ((symbol.getSYMBOLSIZE().doubleValue() - 13.0) / 38.0));
            symbolKML.setIconSize(iconSize);
        }
        kml.append("\n<Folder><name>SIMPLE SYMBOL ").append(symbolKML.getSymbolCode()).append(": ").append(ovlName).append("</name>\n");
        kml.append(symbolKML.getKML(ovlName, desc));
//        if (this.isLabel(symbol)) {
//            kml.append(this.getKmlTitlePlacemark(ovlName, tc, symbolKML.lat, symbolKML.lon));
//        }
        kml.append("</Folder>\n");
        endMsg();
    }// symbol

    @Override
    public void visit(TacsymbolType tacsymbol) {
        startMsg(tacsymbol);
        String symbolCode = "SUZP-----------";
        if (null != tacsymbol.getMILID()) {
            symbolCode = tacsymbol.getMILID();
        }
        String ovlName = this.getKmlLabel(tacsymbol);
        if (ovlName.equals("{TYPE}")) {
            ovlName = "SIMPLE TACTICAL SYMBOL";
        }
        String tc = "FFFFFFFF";
        KMlUtils kmlUtils = new KMlUtils();
        if (null != tacsymbol.getTEXTCOLOR()) {
            kmlUtils.setRgb(tacsymbol.getTEXTCOLOR());
            tc = kmlUtils.getKmlColor();
        }
        String desc = this.getKmlDescription(tacsymbol);

        SinglePointKML symbolKML = new SinglePointKML();
        symbolKML.setImageType(this.imageType);
        symbolKML.setSymbolCode(symbolCode);
        symbolKML.setName(ovlName);
        symbolKML.setDescription(desc);
        symbolKML.setLatitude(tacsymbol.getPOSITION().get(0).doubleValue());
        symbolKML.setLongitude(tacsymbol.getPOSITION().get(1).doubleValue());
        symbolKML.setIconUrl(iconURL);
        symbolKML.setLabelColor(tc);
        symbolKML.setIsLabel(this.isLabel(tacsymbol));
        Map<String, String> params = new HashMap<String, String>();
        params.put(MilStdAttributes.PixelSize, "60");
        params.put(MilStdAttributes.KeepUnitRatio, "true");
        singlePoints.put(symbolCode, params);
        if (null != tacsymbol.getSIZE()) {
            double iconSize = 0.6 + 0.6 * ((tacsymbol.getSIZE().doubleValue() - 12.0) / 52.0);
            symbolKML.setIconSize(iconSize);
        }
        kml.append("\n<Folder><name>SIMPLE TACTICAL SYMBOL ").append(symbolKML.getSymbolCode()).append(": ").append(ovlName).append("</name>\n");
        kml.append(symbolKML.getKML(ovlName, desc));
//        if (this.isLabel(symbol)) {
//            kml.append(this.getKmlTitlePlacemark(ovlName, tc, symbolKML.lat, symbolKML.lon));
//        }
        kml.append("</Folder>\n");
        endMsg();
//        SECRenderer sr = SECRenderer.getInstance();
//        Map<String, String> params = new HashMap<String, String>();
//        params.put(MilStdAttributes.PixelSize, "50");
//        params.put(MilStdAttributes.KeepUnitRatio, "true");
//        PNGInfo pi = sr.getMilStdSymbolImage(symbolCode, params);
//        String name = symbolCode.replace("*", "-");
//        pi.saveImageToFile(name + ".png");
    }// tacsymbol

    @Override
    public void visit(UnitType unit) {
        startMsg(unit);
        // TODO: Finish this
        System.out.println("INFO: UNIT type symbology not implmented, use SYMBOL or TACSYMBOL instead.");
        endMsg();
    }// unit

    @Override
    public void visit(TextType text) {
        startMsg(text);
        String tc = this.getKmlLineColor(text); // The line color hold s the 'text' or font color for some reason
        String ovlName = this.getKmlLabel(text);
        double lat = text.getCENTER().get(0).doubleValue();
        double lon = text.getCENTER().get(1).doubleValue();
        String desc = this.getKmlDescription(text);
        TextKML textKML = new TextKML();
        if (null != text.getTEXT()) {
            textKML.setTextLabel(text.getTEXT());
        }
        textKML.setIconSize(0.0);
        textKML.setLabelColor(tc);
        textKML.setLatitude(lat);
        textKML.setLongitude(lon);
        textKML.setIsLabel(this.isLabel(text));
        textKML.setName(ovlName);
        textKML.setDescription(desc);
        if (null != text.getFONT()) {
            String font = text.getFONT().toUpperCase().replace("<FONT>", "").replace("</FONT>", "");
            String[] fonts = font.split(":");
            if (fonts.length >= 2) {
                double labelSize = 0.6 + (0.6 * (Double.parseDouble(fonts[2]) - 12.0) / 52.0);
                textKML.setLabelSize(labelSize);
            }
        }
        kml.append("\n<Folder><name>TEXT LABEL ").append(ovlName).append("</name>\n");
        kml.append(textKML.getKML());
        kml.append("</Folder>\n");
        endMsg();
    }// text

    public HashMap<String, Map<String, String>> getSinglePoints() {
        return singlePoints;
    }

    public String getKml() {
        return kml.toString();
    }

}
