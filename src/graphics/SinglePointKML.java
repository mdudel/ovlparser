/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

/**
 *
 * @author marty
 */
public class SinglePointKML {

    public String symbolCode = "SUZP-----------";
    public String category = "UNK";
    private String catCode = "U"; //POS 3 of 2525 Symbol Code
    public String threat = "UNK";
    private String thrCode = "U"; //POS 2 of 2525 Symbol Code
    public String codingScheme = "S"; // POS 1 0f 2525 Symbol Code
    public String status = "P"; // POS 4 of 2525 Symbol Code
    public Double lat = 0.0;
    public Double lon = 0.0;
    public Double alt = 0.0;
    boolean isAltSet = false;
    public String name = "";
    public String description = "";
    public String iconUrl = "";
    public String labelColor = "ffffffff";
    public Double iconSize = 0.6;
    boolean debug = true;
    boolean isLabel = true;
    String imageType = "";

    public void setImageType(String imageType) {
        if (imageType.length() > 0) {
            this.imageType = "." + imageType;
        }
    }

    public SinglePointKML() {
    }

    public void setIsLabel(boolean isLabel) {
        this.isLabel = isLabel;
    }

    public String getSymbolCode() {
        return symbolCode;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public void setIconSize(Double iconSize) {
        this.iconSize = iconSize;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLatitude(Double lat) {
        this.lat = lat;
    }

    public void setLongitude(Double lon) {
        this.lon = lon;
    }

    public void setAltitude(Double alt) {
        this.alt = alt;
        this.isAltSet = true;
    }

    public void setPosition(String position) {
        String[] pos = position.split(" ");
        this.lat = Double.parseDouble(pos[0]);
        this.lon = Double.parseDouble(pos[1]);
        if (pos.length > 2) {
            this.setAltitude(Double.parseDouble(pos[2]));
        }
    }

    public void setSymbolCode(String symbolCode) {
        this.symbolCode = symbolCode;
    }

    /**
     * setCategory(String category) P- SPACE A- AIR G- GROUND S- SEA SURFACE U-
     * SEA SUBSURFACE F- SOF X- OTHER (No frame) Z- UNKNOWN
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category.toUpperCase();
        if (this.category.equals("SPACE")
                || this.category.equals("SPC")
                || this.category.equals("P")) {
            this.catCode = "P";
        }
        if (this.category.equals("AIR")
                || this.category.equals("A")) {
            this.catCode = "A";
        }
        if (this.category.equals("GROUND")
                || this.category.equals("LND")
                || this.category.equals("G")) {
            this.catCode = "G";
        }
        if (this.category.equals("SEA SURFACE")
                || this.category.equals("SUR")
                || this.category.equals("NAV")
                || this.category.equals("MER")
                || this.category.equals("FSH")
                || this.category.equals("S")) {
            this.catCode = "S";
        }
        if (this.category.equals("SEA SUBSURFACE")
                || this.category.equals("SUB")
                || this.category.equals("U")) {
            this.catCode = "U";
        }
        if (this.category.equals("SOF")
                || this.category.equals("F")) {
            this.catCode = "F";
        }
        if (this.category.equals("OTHER")
                || this.category.equals("X")) {
            this.catCode = "X";
        }
        if (this.category.equals("UNKNOWN")
                || this.category.equals("UNK")
                || this.category.equals("Z")) {
            this.catCode = "Z";
        }
    }

    /**
     * setThreat(String threat) P- PENDING U- UNKNOWN A- ASSUMED FRIEND F-
     * FRIEND N- NEUTRAL S- SUSPECT H- HOSTILE G- EXERCISE PENDING W- EXERCISE
     * UNKNOWN M- EXERCISE ASSUMED FRIEND D- EXERCISE FRIEND L- EXERCISE NEUTRAL
     * J- JOKER K- FAKER
     *
     * @param threat
     */
    public void setThreat(String threat) {
        this.threat = threat.toUpperCase();
        if (this.threat.equals("PENDING")
                || this.threat.equals("PND")
                || this.threat.equals("P")) {
            this.thrCode = "P";
        }
        if (this.threat.equals("UNKNOWN")
                || this.threat.equals("UNK")
                || this.threat.equals("U")) {
            this.thrCode = "U";
        }
        if (this.threat.equals("ASSUMED FRIEND")
                || this.threat.equals("AFD")
                || this.threat.equals("A")) {
            this.thrCode = "A";
        }
        if (this.threat.equals("FRIEND")
                || this.threat.equals("FRD")
                || this.threat.equals("F")) {
            this.thrCode = "F";
        }
        if (this.threat.equals("NEUTRAL")
                || this.threat.equals("NEU")
                || this.threat.equals("N")) {
            this.thrCode = "N";
        }
        if (this.threat.equals("SUSPECT")
                || this.threat.equals("SUS")
                || this.threat.equals("S")) {
            this.thrCode = "S";
        }
        if (this.threat.equals("HOSTILE")
                || this.threat.equals("HOS")
                || this.threat.equals("H")) {
            this.thrCode = "H";
        }
        if (this.threat.equals("JOKER")
                || this.threat.equals("JOK")
                || this.threat.equals("J")) {
            this.thrCode = "J";
        }
        if (this.threat.equals("FAKER")
                || this.threat.equals("FAK")
                || this.threat.equals("K")) {
            this.thrCode = "K";
        }
        // EXERCISE CODES
        if (this.threat.equals("EXERCISE UNKNOWN")
                || this.threat.equals("W")) {
            this.thrCode = "W";
        }
        if (this.threat.equals("EXERCISE PENDING")
                || this.threat.equals("G")) {
            this.thrCode = "G";
        }
        if (this.threat.equals("EXERCISE ASSUMED FRIEND")
                || this.threat.equals("M")) {
            this.thrCode = "M";
        }
        if (this.threat.equals("EXERCISE FRIEND")
                || this.threat.equals("D")) {
            this.thrCode = "D";
        }
        if (this.threat.equals("EXERCISE NEUTRAL")
                || this.threat.equals("L")) {
            this.thrCode = "L";
        }
    }

    /**
     * S: Warfighting G: Graphics W: Weather
     *
     * @param codingScheme
     */
    public void setCodingScheme(String codingScheme) {
        this.codingScheme = codingScheme.toUpperCase();
    }

    /**
     *
     * A- ANTICIPATED/PLANNED P- PRESENT (Units only) C- PRESENT/FULLY CAPABLE
     * D- PRESENT/DAMAGED X- PRESENT/DESTROYED CAPACITY F- PRESENT/FULL TO
     * CAPACITY
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status.toUpperCase();
    }

    public void setSymbolCode(String category, String threat) {
        this.setCategory(category);
        this.setThreat(threat);
        // build the 2525 symbol code
        StringBuilder sc = new StringBuilder();
        sc.append(this.codingScheme).append(this.thrCode).append(this.catCode).append(this.status);
        if (this.category.equals("NAV")) {
            sc.append("C----------");
        } else if (this.category.equals("MER")) {
            sc.append("XM---------");
        } else if (this.category.equals("FSH")) {
            sc.append("XF---------");
        } else {
            sc.append("-----------");
        }
        this.symbolCode = sc.toString();
    }

    public String getKML() {
        StringBuilder dbug = new StringBuilder();
        dbug.append("<b>NAME: ").append(this.name).append("</b><br>");
        dbug.append("SYMBOLCODE: ").append(this.symbolCode).append("<br>");
        dbug.append("CAT: ").append(this.category).append("<br>");
        dbug.append("THREAT: ").append(this.threat).append("<br>");
        dbug.append("LAT: ").append(this.lat).append("<br>");
        dbug.append("LON: ").append(this.lon).append("<br>");
        dbug.append("ICONSIZE: ").append(this.iconSize).append("<br>");
        dbug.append("USE LABEL: ").append(this.isLabel).append("<br>");
        dbug.append("LABELCOLOR: ").append(this.labelColor).append("<br>");
        dbug.append("DESC: ").append(this.description).append("<br>");
        StringBuilder kml = new StringBuilder();
        kml.append("<Placemark>\n");
        kml.append("<Style>\n");
        kml.append("<IconStyle>\n");
        kml.append("<scale>").append(this.iconSize).append("</scale>\n");
        kml.append("<Icon>\n");
        kml.append("<href>").append(this.iconUrl).append(this.symbolCode + this.imageType).append("</href>\n");
        kml.append("</Icon>\n");
        kml.append("</IconStyle>\n");
        kml.append("<LabelStyle>\n");
        kml.append("<color>").append(this.labelColor).append("</color>\n");
        kml.append("<scale>").append(this.iconSize).append("</scale>\n");
        kml.append("</LabelStyle>\n");
        kml.append("<BalloonStyle>\n");
        kml.append("<text>$[description]</text>\n");
        kml.append("</BalloonStyle>\n");
        kml.append("</Style>\n");
        kml.append("<name>");
        if (this.isLabel) {
            kml.append(this.name);
        }
        kml.append("</name>\n");
        kml.append("<Snippet maxLines=\"0\"></Snippet>\n");
        kml.append("<description><![CDATA[").append(this.description);
        if (debug) {
            kml.append("<br>").append(dbug);
        }
        kml.append("]]></description>\n");
        kml.append("<gx:balloonVisibility>1</gx:balloonVisibility>\n");
        kml.append("<Point>\n");
        kml.append("<extrude>1</extrude>\n");
        kml.append("<coordinates>").append(this.lon.toString()).append(",").append(this.lat.toString());
        if (this.isAltSet) {
            kml.append(",").append(this.alt.toString());
        }
        kml.append("</coordinates>\n");
        kml.append("</Point>\n");
        kml.append("</Placemark>\n");
        return kml.toString();
    }

    public String getKML(String name, String description) {
        this.setName(name);
        this.setDescription(description);
        StringBuilder kml = new StringBuilder();
        return this.getKML();
    }
}
