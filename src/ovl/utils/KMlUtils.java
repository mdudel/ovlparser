/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovl.utils;

/**
 *
 * @author marty
 */
public class KMlUtils {

    int r;
    int g;
    int b;
    String kmlColor;
    String rgb;

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        if (null == rgb) {
            r = 0;
            g = 0;
            b = 0;
            rgb = "0 0 0";
            return;
        }
        if (rgb.length() > 0) {
            // Check for malformed CPOF OVL
            if (rgb.indexOf(",") > 0) {
                rgb = rgb.replaceAll(",", " ");
            }
            String[] col = rgb.split(" ");
            r = Integer.parseInt(col[0]);
            g = Integer.parseInt(col[1]);
            b = Integer.parseInt(col[2]);
        } else {
            r = 0;
            g = 0;
            b = 0;
            rgb = "0 0 0";
        }
        this.rgb = rgb;
    }

    public KMlUtils() {
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getKmlColor() {
        kmlColor = "ff" + String.format("%02x%02x%02x", b, g, r);
        return kmlColor;
    }

    public String getRhex() {
        String hex = String.format("%02", r);
        return hex;
    }

    public String getBhex() {
        String hex = String.format("%02", b);
        return hex;
    }

    public String getGhex() {
        String hex = String.format("%02", g);
        return hex;
    }

    public String getAlphaHex(int percent) {
        Double alpha = 255 * (percent / 100.0);
        String hex = String.format("%02", alpha.intValue());
        return hex;
    }

    public String getKmlTitle(String title, String color, double lat, double lon) {
        StringBuilder kmlTitle = new StringBuilder();
        this.setRgb(color);
        kmlTitle.append(" <Placemark> <Style> <IconStyle> <scale>0</scale> <Icon> <href>http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png</href> </Icon> <hotSpot x=\"20\" y=\"2\" xunits=\"pixels\" yunits=\"pixels\"/> </IconStyle> <LabelStyle> <color>");
        kmlTitle.append(this.getKmlColor()).append("</color> <scale>0.8</scale> </LabelStyle> </Style> <name>");
        kmlTitle.append(title).append("</name><Point>");
        kmlTitle.append("<coordinates>").append(lon).append(",").append(lat).append("</coordinates>");
        kmlTitle.append("</Point></Placemark>");

        return kmlTitle.toString();
    }

    public static String icsf2KmlColorConvert(String value) {
        String[] values = value.split(" ");
        String r = rgbToHex(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
        return r;
    }

    public static String rgbToHex(int r, int g, int b) {
        return new StringBuilder().append("FF").append(toHex(b)).append(toHex(g)).append(toHex(r)).toString();
    }

    public static String toHex(int v) {
        if (v == 0) {
            return "00";
        }
        v = Math.max(0, Math.min(v, 255));
        String r1 = String.valueOf("0123456789ABCDEF".charAt((v - v % 16) / 16));
        String r2 = String.valueOf("0123456789ABCDEF".charAt(v % 16));
        return new StringBuilder().append(r1).append(r2).toString();
    }

}
