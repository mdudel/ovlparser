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
public class TextKML extends SinglePointKML {

    public Double labelSize = 1.0;
    public String textLabel = "";

    public TextKML() {
        this.setIconSize(0.0);
    }

    public Double getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(Double labelSize) {
        this.labelSize = labelSize;
    }

    public String getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(String textLabel) {
        this.textLabel = textLabel;
    }

    @Override
    public String getKML() {
        StringBuilder dbug = new StringBuilder();
        dbug.append("<b>NAME: ").append(this.name).append("</b><br>");
        dbug.append("TEXT: ").append(this.textLabel).append("<br>");
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
        kml.append("<href>http://maps.google.com/mapfiles/kml/shapes/square.png</href>\n");
        kml.append("</Icon>\n");
        kml.append("</IconStyle>\n");
        kml.append("<LabelStyle>\n");
        kml.append("<color>").append(this.labelColor).append("</color>\n");
        kml.append("<scale>").append(this.labelSize).append("</scale>\n");
        kml.append("</LabelStyle>\n");
        kml.append("<BalloonStyle>\n");
        kml.append("<text>$[description]</text>\n");
        kml.append("</BalloonStyle>\n");
        kml.append("</Style>\n");
        kml.append("<name>");
        if (this.isLabel) {
            kml.append(this.name.trim() + " ");
        }
        kml.append(this.textLabel);
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
    }// getKML

    @Override
    public String getKML(String name, String description) {
        this.setName(name);
        this.setDescription(description);
        StringBuilder kml = new StringBuilder();
        return this.getKML();
    }// getKML

}
