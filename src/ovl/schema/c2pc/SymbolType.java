//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.23 at 09:10:17 AM UTC 
//


package ovl.schema.c2pc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for symbolType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="symbolType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}NAME" minOccurs="0"/>
 *         &lt;element ref="{}DESCRIPTION" minOccurs="0"/>
 *         &lt;element ref="{}REMARKS" minOccurs="0"/>
 *         &lt;element ref="{}MAX_SCALE" minOccurs="0"/>
 *         &lt;element ref="{}MIN_SCALE" minOccurs="0"/>
 *         &lt;element ref="{}SCALE_ACTIVATED" minOccurs="0"/>
 *         &lt;element ref="{}LABEL_TYPE" minOccurs="0"/>
 *         &lt;element ref="{}VISIBILITY" minOccurs="0"/>
 *         &lt;element ref="{}PRIORITY" minOccurs="0"/>
 *         &lt;element name="LINE_COLOR" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="FILL_COLOR" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="FILL_TYPE" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="LINE_TYPE" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="LINE_STYLE" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="LINE_WIDTH" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="LINE_GEOM" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element ref="{}BLINK" minOccurs="0"/>
 *         &lt;element name="SIZE" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element ref="{}URL" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}CAT_THREAT"/>
 *         &lt;element ref="{}POSITION"/>
 *         &lt;element ref="{}SYMBOL_SIZE" minOccurs="0"/>
 *         &lt;element name="SYMBOL_CODE" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "symbolType", propOrder = {
    "name",
    "description",
    "remarks",
    "maxscale",
    "minscale",
    "scaleactivated",
    "labeltype",
    "visibility",
    "priority",
    "linecolor",
    "fillcolor",
    "filltype",
    "linetype",
    "linestyle",
    "linewidth",
    "linegeom",
    "blink",
    "size",
    "url",
    "catthreat",
    "position",
    "symbolsize",
    "symbolcode"
})
public class SymbolType {

    @XmlElement(name = "NAME")
    protected String name;
    @XmlElement(name = "DESCRIPTION")
    protected String description;
    @XmlElement(name = "REMARKS")
    protected String remarks;
    @XmlElement(name = "MAX_SCALE", defaultValue = "1")
    protected Integer maxscale;
    @XmlElement(name = "MIN_SCALE", defaultValue = "2147483647")
    protected Integer minscale;
    @XmlElement(name = "SCALE_ACTIVATED", defaultValue = "false")
    protected Boolean scaleactivated;
    @XmlElement(name = "LABEL_TYPE", defaultValue = "None")
    protected String labeltype;
    @XmlElement(name = "VISIBILITY", defaultValue = "true")
    protected Boolean visibility;
    @XmlElement(name = "PRIORITY", defaultValue = "50")
    protected Integer priority;
    @XmlElement(name = "LINE_COLOR")
    protected Object linecolor;
    @XmlElement(name = "FILL_COLOR")
    protected Object fillcolor;
    @XmlElement(name = "FILL_TYPE")
    protected Object filltype;
    @XmlElement(name = "LINE_TYPE")
    protected Object linetype;
    @XmlElement(name = "LINE_STYLE")
    protected Object linestyle;
    @XmlElement(name = "LINE_WIDTH")
    protected Object linewidth;
    @XmlElement(name = "LINE_GEOM")
    protected Object linegeom;
    @XmlElement(name = "BLINK", defaultValue = "false")
    protected Boolean blink;
    @XmlElement(name = "SIZE")
    protected Object size;
    @XmlElement(name = "URL")
    protected List<String> url;
    @XmlElement(name = "CAT_THREAT", required = true)
    protected String catthreat;
    @XmlList
    @XmlElement(name = "POSITION", required = true)
    protected List<BigDecimal> position;
    @XmlElement(name = "SYMBOL_SIZE", defaultValue = "21")
    protected BigInteger symbolsize;
    @XmlElement(name = "SYMBOL_CODE")
    protected Object symbolcode;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAME() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAME(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCRIPTION() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCRIPTION(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREMARKS() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREMARKS(String value) {
        this.remarks = value;
    }

    /**
     * Gets the value of the maxscale property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMAXSCALE() {
        return maxscale;
    }

    /**
     * Sets the value of the maxscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMAXSCALE(Integer value) {
        this.maxscale = value;
    }

    /**
     * Gets the value of the minscale property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMINSCALE() {
        return minscale;
    }

    /**
     * Sets the value of the minscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMINSCALE(Integer value) {
        this.minscale = value;
    }

    /**
     * Gets the value of the scaleactivated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSCALEACTIVATED() {
        return scaleactivated;
    }

    /**
     * Sets the value of the scaleactivated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSCALEACTIVATED(Boolean value) {
        this.scaleactivated = value;
    }

    /**
     * Gets the value of the labeltype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLABELTYPE() {
        return labeltype;
    }

    /**
     * Sets the value of the labeltype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLABELTYPE(String value) {
        this.labeltype = value;
    }

    /**
     * Gets the value of the visibility property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVISIBILITY() {
        return visibility;
    }

    /**
     * Sets the value of the visibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVISIBILITY(Boolean value) {
        this.visibility = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPRIORITY() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPRIORITY(Integer value) {
        this.priority = value;
    }

    /**
     * Gets the value of the linecolor property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLINECOLOR() {
        return linecolor;
    }

    /**
     * Sets the value of the linecolor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLINECOLOR(Object value) {
        this.linecolor = value;
    }

    /**
     * Gets the value of the fillcolor property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFILLCOLOR() {
        return fillcolor;
    }

    /**
     * Sets the value of the fillcolor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFILLCOLOR(Object value) {
        this.fillcolor = value;
    }

    /**
     * Gets the value of the filltype property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFILLTYPE() {
        return filltype;
    }

    /**
     * Sets the value of the filltype property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFILLTYPE(Object value) {
        this.filltype = value;
    }

    /**
     * Gets the value of the linetype property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLINETYPE() {
        return linetype;
    }

    /**
     * Sets the value of the linetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLINETYPE(Object value) {
        this.linetype = value;
    }

    /**
     * Gets the value of the linestyle property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLINESTYLE() {
        return linestyle;
    }

    /**
     * Sets the value of the linestyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLINESTYLE(Object value) {
        this.linestyle = value;
    }

    /**
     * Gets the value of the linewidth property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLINEWIDTH() {
        return linewidth;
    }

    /**
     * Sets the value of the linewidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLINEWIDTH(Object value) {
        this.linewidth = value;
    }

    /**
     * Gets the value of the linegeom property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLINEGEOM() {
        return linegeom;
    }

    /**
     * Sets the value of the linegeom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLINEGEOM(Object value) {
        this.linegeom = value;
    }

    /**
     * Gets the value of the blink property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBLINK() {
        return blink;
    }

    /**
     * Sets the value of the blink property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBLINK(Boolean value) {
        this.blink = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSIZE() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSIZE(Object value) {
        this.size = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the url property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getURL() {
        if (url == null) {
            url = new ArrayList<String>();
        }
        return this.url;
    }

    /**
     * Gets the value of the catthreat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCATTHREAT() {
        return catthreat;
    }

    /**
     * Sets the value of the catthreat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCATTHREAT(String value) {
        this.catthreat = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the position property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPOSITION().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigDecimal }
     * 
     * 
     */
    public List<BigDecimal> getPOSITION() {
        if (position == null) {
            position = new ArrayList<BigDecimal>();
        }
        return this.position;
    }

    /**
     * Gets the value of the symbolsize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSYMBOLSIZE() {
        return symbolsize;
    }

    /**
     * Sets the value of the symbolsize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSYMBOLSIZE(BigInteger value) {
        this.symbolsize = value;
    }

    /**
     * Gets the value of the symbolcode property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSYMBOLCODE() {
        return symbolcode;
    }

    /**
     * Sets the value of the symbolcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSYMBOLCODE(Object value) {
        this.symbolcode = value;
    }

}
