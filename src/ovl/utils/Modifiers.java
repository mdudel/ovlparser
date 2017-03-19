/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovl.utils;

import java.util.ArrayList;

/**
 *
 * @author marty
 */
public class Modifiers {

    public String A_SYMBOL_ICON = "";
    public String B_ECHELON = "";
    public String C_QUANTITY = "";
    public String H_ADDITIONAL_INFO_1 = "";
    public String H1_ADDITIONAL_INFO_2 = "";
    public String H2_ADDITIONAL_INFO_3 = "";
    public String N_HOSTILE = "";
    public String Q_DIRECTION_OF_MOVEMENT = "";
    public String S_OFFSET_INDICATOR = "";
    public String T_UNIQUE_DESIGNATION_1 = "";
    public String T1_UNIQUE_DESIGNATION_2 = "";
    public String V_EQUIP_TYPE = "";
    public String W_DTG_1 = "";
    public String W1_DTG_2 = "";
    public ArrayList<Double> X_ALTITUDE_DEPTH = new ArrayList();
    public String Y_LOCATION = "";
    public ArrayList<Double> AM_DISTANCE = new ArrayList();
    public ArrayList<Double> AN_AZIMUTH = new ArrayList();

    public String A = "";
    public String B = "";
    public String C = "";
    public String H = "";
    public String H1 = "";
    public String H2 = "";
    public String N = "";
    public String Q = "";
    public String S = "";
    public String T = "";
    public String T1 = "";
    public String V = "";
    public String W = "";
    public String W1 = "";
    public String X = ""; // A string representing a double array [double1,double2, etc.]
    public String Y = "";
    public String AM = ""; // A string representing a double array [double1,double2, ... ]
    public String AN = ""; // A string representing a double array [double1,double2, ... ]
    public String FillColor = "";
    public String LineColor = "";
    public String TextColor = "";
    public String symbolFillIconSize = "";
    public String symbolLineIds = "";
    public String symbolFillIds = "";
    public String altMode = "";
    public String textBackgroundColor = "";
    public String lineWidth = "";

    /**
     *
     */
    public Modifiers() {
    }

    public void setModifier(String modifier, String value) {
        if (modifier.equalsIgnoreCase("A")) {
            this.A = value;
        }
        if (modifier.equalsIgnoreCase("quantity")) {
            this.C = value;
        } else if (modifier.equalsIgnoreCase("C")) {
            this.C = value;
        }
        if (modifier.equalsIgnoreCase("additionalInfo1")) {
            this.H = value;
        } else if (modifier.equalsIgnoreCase("H")) {
            this.H = value;
        }
        if (modifier.equalsIgnoreCase("additionalInfo1")) {
            this.H = value;
        } else if (modifier.equalsIgnoreCase("H")) {
            this.H = value;
        }
        if (modifier.equalsIgnoreCase("additionalInfo2")) {
            this.H1 = value;
        } else if (modifier.equalsIgnoreCase("H1")) {
            this.H1 = value;
        }
        if (modifier.equalsIgnoreCase("additionalInfo3")) {
            this.H2 = value;
        } else if (modifier.equalsIgnoreCase("H2")) {
            this.H2 = value;
        }
        if (modifier.equalsIgnoreCase("hostile")) {
            this.N = value;
        } else if (modifier.equalsIgnoreCase("N")) {
            this.N = value;
        }

        if (modifier.equalsIgnoreCase("uniqueDesignation1")) {
            this.T = value;
        } else if (modifier.equalsIgnoreCase("T")) {
            this.T = value;
        }

        if (modifier.equalsIgnoreCase("uniqueDesignation2")) {
            this.T1 = value;
        } else if (modifier.equalsIgnoreCase("T1")) {
            this.T1 = value;
        }

        if (modifier.equalsIgnoreCase("equipmentType")) {
            this.V = value;
        } else if (modifier.equalsIgnoreCase("V")) {
            this.V = value;
        }

        if (modifier.equalsIgnoreCase("dateTimeGroup1")) {
            this.W = value;
        } else if (modifier.equalsIgnoreCase("W")) {
            this.W = value;
        }

        if (modifier.equalsIgnoreCase("dateTimeGroup2")) {
            this.W1 = value;
        } else if (modifier.equalsIgnoreCase("W1")) {
            this.W1 = value;
        }

        if (modifier.equalsIgnoreCase("altitudeDepth")) {
            this.X = value;
        } else if (modifier.equalsIgnoreCase("X")) {
            this.X = value;
        }

        if (modifier.equalsIgnoreCase("distance")) {

            if (!value.startsWith("[")) {
                value = "[" + value;
            }
            if (!value.endsWith("]")) {
                value = value + "]";
            }
            this.AM = value;
        } else if (modifier.equalsIgnoreCase("AM")) {
            if (!value.startsWith("[")) {
                value = "[" + value;
            }
            if (!value.endsWith("]")) {
                value = value + "]";
            }
            this.AM = value;
        }

        if (modifier.equalsIgnoreCase("azimuth")) {
            if (!value.startsWith("[")) {
                value = "[" + value;
            }
            if (!value.endsWith("]")) {
                value = value + "]";
            }
            this.AN = value;
        } else if (modifier.equalsIgnoreCase("AN")) {
            if (!value.startsWith("[")) {
                value = "[" + value;
            }
            if (!value.endsWith("]")) {
                value = value + "]";
            }
            this.AN = value;
        }

        if (modifier.equalsIgnoreCase("fillColor")) {
            FillColor = value;
        }
        if (modifier.equalsIgnoreCase("lineColor")) {
            LineColor = value;
        }

        if (modifier.equalsIgnoreCase("textColor")) {
            TextColor = value;
        }
        if (modifier.equalsIgnoreCase("textBackgroundColor")) {
            textBackgroundColor = value;
        }

        if (modifier.equalsIgnoreCase("lineThickness")) {
            lineWidth = value;
        }

//if (modifier.equalsIgnoreCase("useDashArray")) {
//useDashArray = value;
//}
//else if (modifier.equalsIgnoreCase("USEDASHARRAY")) {
//useDashArray =  value;
//}
        if (modifier.equalsIgnoreCase("ALTMODE")) {
            altMode = value;
        }

        if (modifier.equalsIgnoreCase("symbolFillIds")) {
            this.symbolFillIds = value;
        }
        if (modifier.equalsIgnoreCase("symbolLineIds")) {
            this.symbolLineIds = value;
        }
        if (modifier.equalsIgnoreCase("symbolFillIconSize")) {
            this.symbolFillIconSize = value;
        }
        if (modifier.equalsIgnoreCase("directionOfMovement")) {
            this.Q = value;
        } else if (modifier.equalsIgnoreCase("Q")) {
            this.Q = value;
        }

    }//setModifiers

    public String toHTML() {
        StringBuilder html = new StringBuilder();
        html.append("<b>MODIFIERS</b><br>");

        html.append("A SYMBOL ICON: ").append(this.A).append("<br>");
        html.append("AM DISTANCE: ").append(this.AM).append("<br>");
        html.append("AN AZIMUTH: ").append(this.AN).append("<br>");
        html.append("B ECHELON: ").append(this.B).append("<br>");
        html.append("C QUANTITY: ").append(this.C).append("<br>");
        html.append("H ADDITIONAL INFO 1: ").append(this.H).append("<br>");
        html.append("H1 ADDITIONAL INFO 2: ").append(this.H1).append("<br>");
        html.append("H2 ADDITIONAL INFO 3: ").append(this.H2).append("<br>");
        html.append("N HOSTILE: ").append(this.N).append("<br>");
        html.append("Q DIRECTION OF MOVEMENT: ").append(this.Q).append("<br>");
        html.append("S OFFSET INDICATOR: ").append(this.S).append("<br>");
        html.append("T UNIQUE DESIGNATION 1: ").append(this.T).append("<br>");
        html.append("T1 UNIQUE DESIGNATION 2: ").append(this.T1).append("<br>");
        html.append("V EQUIP TYPE: ").append(this.V).append("<br>");
        html.append("W DTG START: ").append(this.W).append("<br>");
        html.append("W1 DTG END: ").append(this.W1).append("<br>");
        html.append("X ALTITUDE/DEPTH: ").append(this.X).append("<br>");
        html.append("Y LOCATION: ").append(this.Y).append("<br>");

        return html.toString();
    }

    public String toHTMLTable() {
        StringBuilder html = new StringBuilder();
        html.append("<table border=\"1\" cellspacing=\"0\" >\n");
        html.append("<tr><td><b>MODIFIER</b></td><td><b>VALUE</b></td></tr>\n");

        html.append("<tr><td>A SYMBOL ICON</td><td>").append(this.A).append("</td></tr>\n");
        html.append("<tr><td>AM DISTANCE</td><td>").append(this.AM).append("</td></tr>\n");
        html.append("<tr><td>AN AZIMUTH</td><td>").append(this.AN).append("</td></tr>\n");
        html.append("<tr><td>B ECHELON</td><td>").append(this.B).append("</td></tr>\n");
        html.append("<tr><td>C QUANTITY</td><td>").append(this.C).append("</td></tr>\n");
        html.append("<tr><td>H ADDITIONAL INFO 1</td><td>").append(this.H).append("</td></tr>\n");
        html.append("<tr><td>H1 ADDITIONAL INFO 2</td><td>").append(this.H1).append("</td></tr>\n");
        html.append("<tr><td>H2 ADDITIONAL INFO 3</td><td>").append(this.H2).append("</td></tr>\n");
        html.append("<tr><td>N HOSTILE</td><td>").append(this.N).append("</td></tr>\n");
        html.append("<tr><td>Q DIRECTION OF MOVEMENT</td><td>").append(this.Q).append("</td></tr>\n");
        html.append("<tr><td>S OFFSET INDICATOR</td><td>").append(this.S).append("</td></tr>\n");
        html.append("<tr><td>T UNIQUE DESIGNATION 1</td><td>").append(this.T).append("</td></tr>\n");
        html.append("<tr><td>T1 UNIQUE DESIGNATION 2</td><td>").append(this.T1).append("</td></tr>\n");
        html.append("<tr><td>V EQUIP TYPE</td><td>").append(this.V).append("</td></tr>\n");
        html.append("<tr><td>W DTG START</td><td>").append(this.W).append("</td></tr>\n");
        html.append("<tr><td>W1 DTG END</td><td>").append(this.W1).append("</td></tr>\n");
        html.append("<tr><td>X ALTITUDE/DEPTH</td><td>").append(this.X).append("</td></tr>\n");
        html.append("<tr><td>Y LOCATION</td><td>").append(this.Y).append("</td></tr>\n");

        html.append("</table>\n");
        return html.toString();
    }

    /**
     *
     * modifiers - A JSON string representing all the possible symbol modifiers
     * represented in the MIL-STD-2525C. Format of the string will be
     * {"modifiers": {"attributeName":"value"[,"attributeNamen":"valuen"]...}}
     * The quotes are literal in the above notation. Example: {"modifiers":
     * {"quantity":"4","speed":"300","azimuth":[100,200]}}
     *
     * @return String JSONmodifiers
     */
    @Override
    public String toString() {
        int knt = 0;
        StringBuilder mods = new StringBuilder();
        mods.append("{\"modifiers\": {");
        // Add each modifier if defined
        if (this.AM.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"AM\":").append(this.AM);
            knt++;
        }//AM
        if (this.AN.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"AN\":").append(this.AN);
            knt++;
        }    //AN    
        if (this.H.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"H\":\"").append(this.H + "\"");
            knt++;
        }//H
        if (this.H1.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"H1\":\"").append(this.H1 + "\"");
            knt++;
        }//H1
        if (this.H2.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"H2\":\"").append(this.H2 + "\"");
            knt++;
        }//H2
        if (this.T.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"T\":\"").append(this.T + "\"");
            knt++;
        }//T
        if (this.T1.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"T1\":\"").append(this.T1 + "\"");
            knt++;
        }//T1
        if (this.LineColor.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"LineColor\":\"").append(this.LineColor + "\"");
            knt++;
        }//LineColor
        if (this.FillColor.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"FillColor\":\"").append(this.FillColor + "\"");
            knt++;
        }//FillColor
        if (this.W.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"W\":\"").append(this.W + "\"");
            knt++;
        }//W
        if (this.W1.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"W1\":\"").append(this.W1 + "\"");
            knt++;
        }//W1
        if (this.X.length() > 0) {
            if (knt > 0) {
                mods.append(",");
            }
            mods.append("\"X\":\"").append(this.X + "\"");
            knt++;
        }//X        
        mods.append("}}");
        if (knt == 0) {
            return "";
        }
        return mods.toString();
    }
}
