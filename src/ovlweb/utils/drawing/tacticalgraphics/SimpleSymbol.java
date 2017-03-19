/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils.drawing.tacticalgraphics;

/**
 *
 * @author KKEITH
 */
public class SimpleSymbol {
    String symbolCode;
    String scheme;
    String identity;
    String dimension;
    String status;
    String function;
    String modifier;
    String country;
    String oob;

    public SimpleSymbol(String symbolcode,String scheme, String identity, String dimension, String status, String function, String modifier, String country, String oob) {
        this.symbolCode=symbolcode;
        this.scheme = scheme;
        this.identity = identity;
        this.dimension = dimension;
        this.status = status;
        this.function = function;
        this.modifier = modifier;
        this.country = country;
        this.oob = oob;
    }

    public String getSymbolCode() {
        return symbolCode;
    }

    public String getCountry() {
        return country;
    }

    public String getDimension() {
        return dimension;
    }

    public String getFunction() {
        return function;
    }

    public String getIdentity() {
        return identity;
    }

    public String getModifier() {
        return modifier;
    }

    public String getOob() {
        return oob;
    }

    public String getScheme() {
        return scheme;
    }

    public String getStatus() {
        return status;
    }
    
    
}
