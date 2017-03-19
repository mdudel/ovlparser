/*
 * -----------------------------------------------------------------------------
 *        UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED
 *                  (C) Copyright 2015, USAREUR G3 MCSD
 *                          ALL RIGHTS RESERVED
 *                  THIS NOTICE DOES NOT IMPLY PUBLICATION
 * -----------------------------------------------------------------------------
 */
package graphics;

import visad.VisADException;
import visad.bom.Radar3DCoordinateSystem;

/**
 *
 * Handles Range, Azimuth, Elevation from a known point
 *
 * @author marty
 */
public class RangeAzimuthElevation3D {

    private int LAT = 0;
    private int LON = 1;
    private int ALT = 2;
    double lon = 0;       // Longitude (degrees decimal) of source (x)
    double lat = 0;       // Latitude (degrees decimal) of source (y)
    double alt = 0;       // Altitude (meters) of source (z)
    double range = 0;     // Range (meters) to target point
    double azimuth = 0;   // Azimuth (degrees decimal, north = 0) of target point
    double elevation = 0; // Elevation from horizontal (degrees decimal) of target point 
    String altitudeMode = "relativeToGround";   // KML string value representing 
    // the altitudeMode. Valid values are:  absolute
    //                                      clampToGround
    //                                      clampToSeaFloor
    //                                      relativeToGround
    //                                      relativeToSeaFloor    

    /**
     * Constructs a RangeAzimuthElevation3D class and sets the reference(center)
     * location to the parameters. The center latitude, longitude, and altitude
     * are degrees decimal. The target end point is not defined in this
     * constructor.
     *
     * Ex: RangeAzimuthElevation3D rae = new RangeAzimuthElevation3D(11.0, 12.0,
     * 0.0);
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     */
    RangeAzimuthElevation3D(
            double latCenter,
            double lonCenter,
            double altCenter) {
        lon = lonCenter;
        lat = latCenter;
        alt = altCenter;
    }//constructor

    /**
     * Constructs a RangeAzimuthElevation3D class and sets the reference(center)
     * location and target end point from the passed parameters. The center
     * latitude, longitude, and altitude are degrees decimal. The target range
     * is in meters, azimuth is degrees decimal (North = 0.0), and elevation is
     * degrees decimal (Horizontal = 0.0 and straight up is 90.0)
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param rngTgt Range to target point in meters
     * @param azTgt Azimuth to target point in decimal degrees
     * @param elTgt Elevation to target point in decimal degrees
     */
    RangeAzimuthElevation3D(
            double latCenter,
            double lonCenter,
            double altCenter,
            double rngTgt,
            double azTgt,
            double elTgt) {
        lon = lonCenter;
        lat = latCenter;
        alt = altCenter;
        range = rngTgt;
        azimuth = azTgt;
        elevation = elTgt;
    }//constructor

    /**
     * Sets the KML string value for <altitudeMode>am</altitudeMode>
     *
     * @param am KML string value representing the altitudeMode. Valid values
     * are: absolute clampToGround clampToSeaFloor relativeToGround
     * relativeToSeaFloor
     */
    public void setAltitudeMode(String am) {
        if (am.toUpperCase().equals("ABSOLUTE")) {
            altitudeMode = "absolute";
        } else if (am.toUpperCase().equals("CLAMPTOGROUND")) {
            altitudeMode = "clampToGround";
        } else if (am.toUpperCase().equals("CLAMPTOSEAFLOOR")) {
            altitudeMode = "clampToSeaFloor";
        } else if (am.toUpperCase().equals("RELATIVETOGROUND")) {
            altitudeMode = "relativeToGround";
        } else if (am.toUpperCase().equals("RELATIVETOSEAFLOOR")) {
            altitudeMode = "relativeToSeaFloor";
        } else {
            altitudeMode = "clampToGround";
        }
    }

    /**
     * Sets the KML string value for <altitudeMode>absolute</altitudeMode>
     */
    public void setAltitudeMode_absolute() {
        this.altitudeMode = "absolute";
    }

    /**
     * Sets the KML string value for <altitudeMode>clampToGround</altitudeMode>
     */
    public void setAltitudeMode_clampToGround() {
        this.altitudeMode = "clampToGround";
    }

    /**
     * Sets the KML string value for
     * <altitudeMode>clampToSeaFloor</altitudeMode>
     */
    public void setAltitudeMode_clampToSeaFloor() {
        this.altitudeMode = "clampToSeaFloor";
    }

    /**
     * Sets the KML string value for
     * <altitudeMode>relativeToGround</altitudeMode>
     */
    public void setAltitudeMode_relativeToGround() {
        this.altitudeMode = "relativeToGround";
    }

    /**
     * Sets the KML string value for
     * <altitudeMode>relativeToSeaFloor</altitudeMode>
     */
    public void setAltitudeMode_relativeToSeaFloor() {
        this.altitudeMode = "relativeToSeaFloor";
    }

    /**
     * Sets the target end point. The target range is in meters, azimuth is
     * degrees decimal (North = 0.0), and elevation is degrees decimal
     * (Horizontal = 0.0 and straight up is 90.0)
     *
     * @param rngTgt Range to target point in meters
     * @param azTgt Azimuth to target point in decimal degrees
     * @param elTgt Elevation to target point in decimal degrees
     */
    public void setTargetRAE(
            double rngTgt,
            double azTgt,
            double elTgt) {
        range = rngTgt;
        azimuth = azTgt;
        elevation = elTgt;
    }//setRAE

    /**
     * Sets the target elevation (decimal degrees). Horizontal = 0.0 and
     * straight up = 90.0
     *
     * @param elTgt Elevation to target point in decimal degrees
     */
    public void setTargetElevation(double elTgt) {
        elevation = elTgt;
    }//setRAE

    /**
     * Sets the target azimuth (decimal degrees). North = 0.0
     *
     * @param azTgt Azimuth to target point in decimal degrees
     */
    public void setTargetAzimuth(double azTgt) {
        azimuth = azTgt;
    }//setAzimuth 

    /**
     * Sets the target range (meters)
     *
     * @param rngTgt Range to target point in meters
     */
    public void setTargetRange(double rngTgt) {
        range = rngTgt;
    }//setRange    

    /**
     * Returns the KML coordinate string in the form of LON,LAT,ALT for a target
     * point at a specific range, azimuth, elevation from the reference (center)
     * point passed as a parameter. The center latitude, longitude, and altitude
     * are degrees decimal. The target range is in meters, azimuth is degrees
     * decimal (North = 0.0), and elevation is degrees decimal (Horizontal = 0.0
     * and straight up is 90.0)
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param rngTgt Range to target point in meters
     * @param azTgt Azimuth to target point in decimal degrees
     * @param elTgt Elevation to target point in decimal degrees
     * @return String representation of target point in KML coordinates
     */
    public String getKmlTgtCoords(
            double latCenter,
            double lonCenter,
            double altCenter,
            double rngTgt,
            double azTgt,
            double elTgt) {
        String coordinates = "";
        try {
            Radar3DCoordinateSystem r3d = new Radar3DCoordinateSystem((float) latCenter, (float) lonCenter, (float) altCenter);
            double[][] tgtRAE = {{rngTgt}, {azTgt}, {elTgt}};
            double[][] lla = r3d.toReference(tgtRAE);
            coordinates = lla[LON][0] + "," + lla[LAT][0] + "," + lla[ALT][0];
        } catch (VisADException e) {
            System.out.println(e.getMessage());
            coordinates = "";
        }
        return coordinates;
    }//getKmlTgtCoords

    /**
     * Returns the KML coordinate string in the for of LON,LAT,ALT for a target
     * point at a specific range, azimuth, elevation from the reference (center)
     * point of the class. The center latitude, longitude, and altitude are
     * degrees decimal. The target range is in meters, azimuth is degrees
     * decimal (North = 0.0), and elevation is degrees decimal (Horizontal = 0.0
     * and straight up is 90.0)
     *
     * @param rngTgt Range to target point in meters
     * @param azTgt Azimuth to target point in decimal degrees
     * @param elTgt Elevation to target point in decimal degrees
     * @return String representation of target point in KML coordinates
     */
    public String getKmlTgtCoords(
            double rngTgt,
            double azTgt,
            double elTgt) {
        String coordinates = "";
        try {
            Radar3DCoordinateSystem r3d = new Radar3DCoordinateSystem((float) lat, (float) lon, (float) alt);
            double[][] tgtRAE = {{rngTgt}, {azTgt}, {elTgt}};
            double[][] lla = r3d.toReference(tgtRAE);
            coordinates = lla[LON][0] + "," + lla[LAT][0] + "," + lla[ALT][0];
        } catch (VisADException e) {
            System.out.println(e.getMessage());
            coordinates = "";
        }
        return coordinates;
    }//getKmlTgtCoords

    /**
     * Returns the KML coordinate string in the for of LON,LAT,ALT for a target
     * point at a specific range, azimuth, elevation from the reference (center)
     * point of the class.
     *
     * @return String representation of target point in KML coordinates
     */
    public String getKmlTgtCoords() {
        String coordinates = "";
        try {
            Radar3DCoordinateSystem r3d = new Radar3DCoordinateSystem((float) lat, (float) lon, (float) alt);
            double[][] tgtRAE = {{range}, {azimuth}, {elevation}};
            double[][] lla = r3d.toReference(tgtRAE);
            coordinates = lla[LON][0] + "," + lla[LAT][0] + "," + lla[ALT][0];
        } catch (VisADException e) {
            System.out.println(e.getMessage());
            coordinates = "";
        }
        return coordinates;
    }//getKmlTgtCoords   

    /**
     * Returns a String KML Placemark fragment representing a line from the
     * reference (center) point of the class to the target point of the class.
     *
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlLineString(
            String width,
            String r,
            String g,
            String b,
            String a) {
        String color = a + b + g + r;
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>"
                + lon + "," + lat + "," + alt + " \n"
                + getKmlTgtCoords() + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlLineString

    /**
     * Returns a String KML Placemark fragment representing a line from the
     * passed reference (center) point to the passed target point.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param rngTgt Range to target point in meters
     * @param azTgt Azimuth to target point in decimal degrees
     * @param elTgt Elevation to target point in decimal degrees
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlLineString(
            double latCenter,
            double lonCenter,
            double altCenter,
            double rngTgt,
            double azTgt,
            double elTgt,
            String width,
            String r,
            String g,
            String b,
            String a) {
        String color = a + b + g + r;
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>"
                + latCenter + "," + lonCenter + "," + altCenter + " \n"
                + getKmlTgtCoords(latCenter, lonCenter, altCenter, rngTgt, azTgt, elTgt) + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlLineString  

    /**
     * Returns a String KML Placemark fragment representing a line from the
     * reference (center) point of the class to the passed target point.
     *
     * @param rngTgt Range to target point in meters
     * @param azTgt Azimuth to target point in decimal degrees
     * @param elTgt Elevation to target point in decimal degrees
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlLineString(
            double rngTgt,
            double azTgt,
            double elTgt,
            String width,
            String r,
            String g,
            String b,
            String a) {
        String color = a + b + g + r;
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>"
                + lon + "," + lat + "," + alt + " \n"
                + getKmlTgtCoords(lat, lon, alt, rngTgt, azTgt, elTgt) + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlLineString      

    /**
     * Returns a String KML Placemark fragment representing a section of the
     * line from the passed reference point along an azimuth and elevation
     * beginning at the start range to the end range.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param startRange Beginning range in meters
     * @param endRange Ending range in meters
     * @param azTgt Azimuth to target point in decimal degrees
     * @param elTgt Elevation to target point in decimal degrees
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlLineStringSegment(
            double latCenter,
            double lonCenter,
            double altCenter,
            double startRange,
            double endRange,
            double azTgt,
            double elTgt,
            String width,
            String r,
            String g,
            String b,
            String a) {
        String color = a + b + g + r;
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>\n"
                + getKmlTgtCoords(latCenter, lonCenter, altCenter, startRange, azTgt, elTgt) + " \n"
                + getKmlTgtCoords(latCenter, lonCenter, altCenter, endRange, azTgt, elTgt) + " \n"
                + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlLineStringSegment   

    /**
     * Returns a String KML Placemark fragment representing a section of the
     * line from a previously defined reference point along an azimuth and
     * elevation beginning at the start range to the end range.
     *
     * @param startRange Beginning range in meters
     * @param endRange Ending range in meters
     * @param azTgt Azimuth to target point in decimal degrees
     * @param elTgt Elevation to target point in decimal degrees
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlLineStringSegment(
            double startRange,
            double endRange,
            double azTgt,
            double elTgt,
            String width,
            String r,
            String g,
            String b,
            String a) {
        String color = a + b + g + r;
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>\n"
                + getKmlTgtCoords(lat, lon, alt, startRange, azTgt, elTgt) + " \n"
                + getKmlTgtCoords(lat, lon, alt, endRange, azTgt, elTgt) + " \n"
                + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlLineStringSegment    

    /**
     * Returns a String KML Placemark fragment representing a section of the
     * line beginning at a point defined by a range, azimuth,elevation from the
     * passed reference (center) point and ending at another point defined by
     * range, azimuth, elevation.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param sourceRange Range of 1st point in meters
     * @param sourceAz Azimuth of 1st point in decimal degrees
     * @param sourceEl Elevation of 1st point in decimal degrees
     * @param targetRange Range of 2nd point in meters
     * @param targetAz Azimuth of 2nd point in decimal degrees
     * @param targetEl Elevation of 2nd point in decimal degrees
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlConnectedLineStringSegment(
            double latCenter,
            double lonCenter,
            double altCenter,
            double sourceRange,
            double sourceAz,
            double sourceEl,
            double targetRange,
            double targetAz,
            double targetEl,
            String width,
            String r,
            String g,
            String b,
            String a) {
        String color = a + b + g + r;
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>\n"
                + getKmlTgtCoords(latCenter, lonCenter, altCenter, sourceRange, sourceAz, sourceEl) + " \n"
                + getKmlTgtCoords(latCenter, lonCenter, altCenter, targetRange, targetAz, targetEl) + " \n"
                + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlConnectedLineStringSegment   

    /**
     * Returns a String KML Placemark fragment representing a section of the
     * line beginning at a point defined by a range, azimuth,elevation from the
     * a defined reference (center) point and ending at another point defined by
     * range, azimuth, elevation.
     *
     * @param sourceRange Range of 1st point in meters
     * @param sourceAz Azimuth of 1st point in decimal degrees
     * @param sourceEl Elevation of 1st point in decimal degrees
     * @param targetRange Range of 2nd point in meters
     * @param targetAz Azimuth of 2nd point in decimal degrees
     * @param targetEl Elevation of 2nd point in decimal degrees
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlConnectedLineStringSegment(
            double sourceRange,
            double sourceAz,
            double sourceEl,
            double targetRange,
            double targetAz,
            double targetEl,
            String width,
            String r,
            String g,
            String b,
            String a) {
        String color = a + b + g + r;
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>\n"
                + getKmlTgtCoords(lat, lon, alt, sourceRange, sourceAz, sourceEl) + " \n"
                + getKmlTgtCoords(lat, lon, alt, targetRange, targetAz, targetEl) + " \n"
                + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlConnectedLineStringSegment   

    /**
     * Returns a String KML Placemark fragment representing an arc with constant
     * elevation at a given range and specified start and and azimuth with
     * respect to the reference (center) point passed in.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param arcRange Range of arc in meters
     * @param startAz Starting azimuth of arc in decimal degrees
     * @param endAz Ending azimuth of arc in decimal degrees
     * @param arcEl Elevation of arc in decimal degrees
     * @param stepSize Integer of points in arc
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlConnectedLineStringArc(
            double latCenter,
            double lonCenter,
            double altCenter,
            double arcRange,
            double startAz,
            double endAz,
            double arcEl,
            int stepSize,
            String width,
            String r,
            String g,
            String b,
            String a) {
        double azstep = ((endAz - startAz) / stepSize);
        String color = a + b + g + r;
        StringBuilder coords = new StringBuilder();
        for (int i = 0; i <= stepSize; i++) {
            coords.append(getKmlTgtCoords(latCenter, lonCenter, altCenter, arcRange, startAz, arcEl)).append(" \n");
            startAz = startAz + azstep;
        }//azimuth
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>\n"
                + coords.toString()
                + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlConnectedLineStringArc    

    /**
     * Returns a String KML Placemark fragment representing an arc with constant
     * elevation at a given range and specified start and and end azimuth with
     * respect to the reference (center) point previously defined in the class.
     *
     * @param arcRange Range of arc in meters
     * @param startAz Starting azimuth of arc in decimal degrees
     * @param endAz Ending azimuth of arc in decimal degrees
     * @param arcEl Elevation of arc in decimal degrees
     * @param stepSize Integer of points in arc
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlConnectedLineStringArc(
            double arcRange,
            double startAz,
            double endAz,
            double arcEl,
            int stepSize,
            String width,
            String r,
            String g,
            String b,
            String a) {
        double azstep = ((endAz - startAz) / stepSize);
        String color = a + b + g + r;
        StringBuilder coords = new StringBuilder();
        for (int i = 0; i <= stepSize; i++) {
            coords.append(getKmlTgtCoords(lat, lon, alt, arcRange, startAz, arcEl)).append(" \n");
            startAz = startAz + azstep;
        }//azimuth
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>\n"
                + coords.toString()
                + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlConnectedLineStringArc 

    /**
     * Returns a String KML Placemark fragment representing an arc sector of a
     * sphere centered on the passed point and with a constant range bounded by
     * an azimuth and elevation constraint. The azimuth constraint is bounded by
     * a start and end azimuth as well as a number of points for azimuth
     * resolution. The elevation constraint is bounded by a start and end
     * elevation as well as a number of points for elevation resolution.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param arcRange Range of arc in meters
     * @param startAz Starting azimuth of sector in decimal degrees
     * @param endAz Ending azimuth of sector in decimal degrees
     * @param azStepSize Integer number of points in sector along the azimuth
     * @param startEl Starting elevation of sector in decimal degrees
     * @param endEl Ending elevation of sector in decimal degrees
     * @param elStepSize Integer number of points in sector along elevation
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlLineStringSector(
            double latCenter,
            double lonCenter,
            double altCenter,
            double arcRange,
            double startAz,
            double endAz,
            int azStepSize,
            double startEl,
            double endEl,
            int elStepSize,
            String width,
            String r,
            String g,
            String b,
            String a) {
        double azstep = ((endAz - startAz) / azStepSize);
        double elstep = ((endEl - startEl) / elStepSize);
        double az = startAz;
        double el = startEl;
        String color = a + b + g + r;
        StringBuilder coords = new StringBuilder();
        StringBuilder sbStartEl = new StringBuilder();
        StringBuilder sbEndEl = new StringBuilder();
        try {
            Radar3DCoordinateSystem r3d = new Radar3DCoordinateSystem((float) latCenter, (float) lonCenter, (float) altCenter);
            for (int e = 0; e < elStepSize; e++) {
                for (int i = 0; i <= azStepSize; i++) {
                    double[][] rae = {{arcRange, arcRange}, {az, az}, {el, (el + elstep)}};
                    double[][] mylla = r3d.toReference(rae);
                    sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                    sbStartEl.append(mylla[LON][1]).append(",").append(mylla[LAT][1]).append(",").append(mylla[ALT][1]).append("\n");
                    sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                    sbEndEl.insert(0, mylla[LON][1] + "," + mylla[LAT][1] + "," + mylla[ALT][1] + "\n");
                    az = az + azstep;
                }//azimuth
                az = startAz;
                el = el + elstep;
                coords.append(sbStartEl.toString());
                coords.append(sbEndEl.toString());
                sbStartEl = new StringBuilder();
                sbEndEl = new StringBuilder();
            }//elevation
        } catch (VisADException e) {
            System.out.println(e.getMessage());
            coords = new StringBuilder();
        }
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>\n"
                + coords.toString()
                + "\n</coordinates>\n</LineString>\n</Placemark>";
        return placemark;
    }//getKmlLineStringSector    

    /**
     * /**
     * Returns a String KML Placemark fragment representing an arc sector of a
     * sphere centered on the passed point and with a constant range bounded by
     * an azimuth and elevation constraint. The azimuth constraint is bounded by
     * a start and end azimuth as well as a number of points for azimuth
     * resolution. The elevation constraint is bounded by a start and end
     * elevation as well as a number of points for elevation resolution.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param arcRange Range of arc in meters
     * @param startAz Starting azimuth of sector in decimal degrees
     * @param endAz Ending azimuth of sector in decimal degrees
     * @param azStepSize Integer number of points in sector along the azimuth
     * @param startEl Starting elevation of sector in decimal degrees
     * @param endEl Ending elevation of sector in decimal degrees
     * @param elStepSize Integer number of points in sector along elevation
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @param showVertices boolean if true shows vertices from center
     * (reference) point to bounds
     * @return String KML Placemark
     */
    public String getKmlLineStringSector(
            double latCenter,
            double lonCenter,
            double altCenter,
            double arcRange,
            double startAz,
            double endAz,
            int azStepSize,
            double startEl,
            double endEl,
            int elStepSize,
            String width,
            String r,
            String g,
            String b,
            String a,
            boolean showVertices) {
        double azstep = ((endAz - startAz) / azStepSize);
        double elstep = ((endEl - startEl) / elStepSize);
        double az = startAz;
        double el = startEl;
        String color = a + b + g + r;
        StringBuilder coords = new StringBuilder();
        StringBuilder sbStartEl = new StringBuilder();
        StringBuilder sbEndEl = new StringBuilder();
        try {
            Radar3DCoordinateSystem r3d = new Radar3DCoordinateSystem((float) latCenter, (float) lonCenter, (float) altCenter);
            for (int e = 0; e < elStepSize; e++) {
                for (int i = 0; i <= azStepSize; i++) {
                    double[][] rae = {{arcRange, arcRange}, {az, az}, {el, (el + elstep)}};
                    double[][] mylla = r3d.toReference(rae);
                    sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                    sbStartEl.append(mylla[LON][1]).append(",").append(mylla[LAT][1]).append(",").append(mylla[ALT][1]).append("\n");
                    sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                    sbEndEl.insert(0, mylla[LON][1] + "," + mylla[LAT][1] + "," + mylla[ALT][1] + "\n");
                    az = az + azstep;
                }//azimuth
                az = startAz;
                el = el + elstep;
                coords.append(sbStartEl.toString());
                coords.append(sbEndEl.toString());
                sbStartEl = new StringBuilder();
                sbEndEl = new StringBuilder();
            }//elevation
        } catch (VisADException e) {
            System.out.println(e.getMessage());
            coords = new StringBuilder();
        }
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>\n"
                + coords.toString()
                + "\n</coordinates>\n</LineString>\n</Placemark>";
        if (showVertices) {
            placemark = placemark + "\n"
                    + getKmlLineString(latCenter, lonCenter, altCenter, arcRange, startAz, startEl, width, r, g, b, a) + "\n"
                    + getKmlLineString(latCenter, lonCenter, altCenter, arcRange, endAz, startEl, width, r, g, b, a) + "\n"
                    + getKmlLineString(latCenter, lonCenter, altCenter, arcRange, startAz, endEl, width, r, g, b, a) + "\n"
                    + getKmlLineString(latCenter, lonCenter, altCenter, arcRange, endAz, endEl, width, r, g, b, a) + "\n";
        }
        return placemark;
    }//getKmlLineStringSector   

    /**
     * Returns a String KML Placemark fragment representing an arc sector of a
     * sphere centered on reference (center) point of the class with a constant
     * range bounded by an azimuth and elevation constraint. The azimuth
     * constraint is bounded by a start and end azimuth as well as a number of
     * points for azimuth resolution. The elevation constraint is bounded by a
     * start and end elevation as well as a number of points for elevation
     * resolution.
     *
     * @param arcRange Range of arc in meters
     * @param startAz Starting azimuth of sector in decimal degrees
     * @param endAz Ending azimuth of sector in decimal degrees
     * @param azStepSize Integer number of points in sector along the azimuth
     * @param startEl Starting elevation of sector in decimal degrees
     * @param endEl Ending elevation of sector in decimal degrees
     * @param elStepSize Integer number of points in sector along elevation
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @param showVertices boolean if true shows vertices from center
     * (reference) point to bounds
     * @return String KML Placemark
     */
    public String getKmlLineStringSector(
            double arcRange,
            double startAz,
            double endAz,
            int azStepSize,
            double startEl,
            double endEl,
            int elStepSize,
            String width,
            String r,
            String g,
            String b,
            String a,
            boolean showVertices) {
        double azstep = ((endAz - startAz) / azStepSize);
        double elstep = ((endEl - startEl) / elStepSize);
        double az = startAz;
        double el = startEl;
        String color = a + b + g + r;
        StringBuilder coords = new StringBuilder();
        StringBuilder sbStartEl = new StringBuilder();
        StringBuilder sbEndEl = new StringBuilder();
        try {
            Radar3DCoordinateSystem r3d = new Radar3DCoordinateSystem((float) lat, (float) lon, (float) alt);
            for (int e = 0; e < elStepSize; e++) {
                for (int i = 0; i <= azStepSize; i++) {
                    double[][] rae = {{arcRange, arcRange}, {az, az}, {el, (el + elstep)}};
                    double[][] mylla = r3d.toReference(rae);
                    sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                    sbStartEl.append(mylla[LON][1]).append(",").append(mylla[LAT][1]).append(",").append(mylla[ALT][1]).append("\n");
                    sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                    sbEndEl.insert(0, mylla[LON][1] + "," + mylla[LAT][1] + "," + mylla[ALT][1] + "\n");
                    az = az + azstep;
                }//azimuth
                az = startAz;
                el = el + elstep;
                coords.append(sbStartEl.toString());
                coords.append(sbEndEl.toString());
                sbStartEl = new StringBuilder();
                sbEndEl = new StringBuilder();
            }//elevation
        } catch (VisADException e) {
            System.out.println(e.getMessage());
            coords = new StringBuilder();
        }
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle></Style>\n"
                + "<LineString>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<coordinates>\n"
                + coords.toString()
                + "\n</coordinates>\n</LineString>\n</Placemark>";
        if (showVertices) {
            placemark = placemark + "\n"
                    + getKmlLineString(lat, lon, alt, arcRange, startAz, startEl, width, r, g, b, a) + "\n"
                    + getKmlLineString(lat, lon, alt, arcRange, endAz, startEl, width, r, g, b, a) + "\n"
                    + getKmlLineString(lat, lon, alt, arcRange, startAz, endEl, width, r, g, b, a) + "\n"
                    + getKmlLineString(lat, lon, alt, arcRange, endAz, endEl, width, r, g, b, a) + "\n";
        }
        return placemark;
    }//getKmlLineStringSector

    /**
     * Returns a String KML Placemark fragment representing a wedge in space
     * centered on the passed reference (center) point. The wedge is bounded in
     * range, azimuth, and elevation.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param startRange Start range of wedge in meters
     * @param endRange End range of wedge in meters
     * @param startAz Starting azimuth of sector in decimal degrees
     * @param endAz Ending azimuth of sector in decimal degrees
     * @param azStepSize Integer number of points in sector along the azimuth
     * @param startEl Starting elevation of sector in decimal degrees
     * @param endEl Ending elevation of sector in decimal degrees
     * @param elStepSize Integer number of points in sector along elevation
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @return String KML Placemark
     */
    public String getKmlLineStringWedge(
            double latCenter,
            double lonCenter,
            double altCenter,
            double startRange,
            double endRange,
            double startAz,
            double endAz,
            int azStepSize,
            double startEl,
            double endEl,
            int elStepSize,
            String width,
            String r,
            String g,
            String b,
            String a) {
        double azstep = ((endAz - startAz) / azStepSize);
        double elstep = ((endEl - startEl) / elStepSize);
        double az = startAz;
        double el = startEl;
        String color = a + b + g + r;
        StringBuilder coords = new StringBuilder();
        StringBuilder sbStartEl = new StringBuilder();
        StringBuilder sbEndEl = new StringBuilder();
        coords.append(getKmlLineStringSector(latCenter, lonCenter, altCenter,
                startRange, startAz, endAz, azStepSize, startEl, endEl, elStepSize,
                width, r, g, b, a));
        coords.append(getKmlLineStringSector(latCenter, lonCenter, altCenter,
                endRange, startAz, endAz, azStepSize, startEl, endEl, elStepSize,
                width, r, g, b, a));
        coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, startAz, startEl, width, r, g, b, a));
        coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, endAz, startEl, width, r, g, b, a));
        coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, startAz, endEl, width, r, g, b, a));
        coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, endAz, endEl, width, r, g, b, a));
        return coords.toString();
    }//getKmlLineStringWedge  

    /**
     * Returns a String KML Placemark fragment representing a wedge in space
     * centered on the passed reference (center) point. The wedge is bounded in
     * range, azimuth, and elevation.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param startRange Start range of wedge in meters
     * @param endRange End range of wedge in meters
     * @param startAz Starting azimuth of sector in decimal degrees
     * @param endAz Ending azimuth of sector in decimal degrees
     * @param azStepSize Integer number of points in sector along the azimuth
     * @param startEl Starting elevation of sector in decimal degrees
     * @param endEl Ending elevation of sector in decimal degrees
     * @param elStepSize Integer number of points in sector along elevation
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF)
     * @param g String representing the hex GREEN value (00..FF)
     * @param b String representing the hex BLUE value (00..FF)
     * @param a String representing the hex ALPHA/transparency value (00..FF)
     * @param showBearing boolean indicating if bearing lines are depicted
     * between arcs
     * @param showAzBearing boolean indicating if all bearing lines are depicted
     * between arcs
     * @return String KML Placemark
     */
    public String getKmlLineStringWedge(
            double latCenter,
            double lonCenter,
            double altCenter,
            double startRange,
            double endRange,
            double startAz,
            double endAz,
            int azStepSize,
            double startEl,
            double endEl,
            int elStepSize,
            String width,
            String r,
            String g,
            String b,
            String a,
            boolean showBearing,
            boolean showAzBearing) {
        double azstep = ((endAz - startAz) / azStepSize);
        double elstep = ((endEl - startEl) / elStepSize);
        double az = startAz;
        double el = startEl;
        String color = a + b + g + r;
        StringBuilder coords = new StringBuilder();
        StringBuilder sbStartEl = new StringBuilder();
        StringBuilder sbEndEl = new StringBuilder();
        coords.append(getKmlLineStringSector(latCenter, lonCenter, altCenter,
                startRange, startAz, endAz, azStepSize, startEl, endEl, elStepSize,
                width, r, g, b, a));
        coords.append(getKmlLineStringSector(latCenter, lonCenter, altCenter,
                endRange, startAz, endAz, azStepSize, startEl, endEl, elStepSize,
                width, r, g, b, a));
        if (showBearing) {
            if (showAzBearing) {
                azstep = ((endAz - startAz) / azStepSize);
                az = startAz;
                for (int i = 0; i <= azStepSize; i++) {
                    coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, az, startEl, width, r, g, b, a));
                    coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, az, endEl, width, r, g, b, a));
                    az = az + azstep;
                }//walk the azimuth
            } else {
                coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, startAz, startEl, width, r, g, b, a));
                coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, endAz, startEl, width, r, g, b, a));
                coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, startAz, endEl, width, r, g, b, a));
                coords.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, endAz, endEl, width, r, g, b, a));

            }
        }
        return coords.toString();
    }//getKmlLineStringWedge 

    /**
     * Returns a String KML Placemark fragment representing a polygon of a
     * section of a sphere bounded at a given range bounded by azimuth and
     * elevation limits centered on the passed center (reference) point.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param arcRange Range of polysector in meters
     * @param startAz Starting azimuth of sector in decimal degrees
     * @param endAz Ending azimuth of sector in decimal degrees
     * @param azStepSize Integer number of points in sector along the azimuth
     * @param startEl Starting elevation of sector in decimal degrees
     * @param endEl Ending elevation of sector in decimal degrees
     * @param width String representing the width of the line
     * @param rLine String representing the hex RED value (00..FF) of the line
     * @param gLine String representing the hex GREEN value (00..FF) of the line
     * @param bLine String representing the hex BLUE value (00..FF) of the line
     * @param aLine String representing the hex ALPHA/transparency value
     * (00..FF) of the line
     * @param rSurface String representing the hex RED value (00..FF) of the
     * poly surface
     * @param gSurface String representing the hex GREEN value (00..FF) of the
     * poly surface
     * @param bSurface String representing the hex BLUE value (00..FF) of the
     * poly surface
     * @param aSurface String representing the hex ALPHA/transparency value
     * (00..FF) of the poly surface
     * @param showVertices boolean if true shows vertices from center
     * (reference) point to bounds
     * @return String KML Placemark
     */
    public String getKmlPolygonSector(
            double latCenter,
            double lonCenter,
            double altCenter,
            double arcRange,
            double startAz,
            double endAz,
            int azStepSize,
            double startEl,
            double endEl,
            String width,
            String rLine,
            String gLine,
            String bLine,
            String aLine,
            String rSurface,
            String gSurface,
            String bSurface,
            String aSurface,
            boolean showVertices) {
        double azstep = ((endAz - startAz) / azStepSize);
        //double elstep = ((endEl - startEl) / elStepSize);
        double az = startAz;
        //double el = startEl;
        String color = aLine + bLine + gLine + rLine;
        String polyColor = aSurface + bSurface + gSurface + rSurface;
        StringBuilder coords = new StringBuilder();
        StringBuilder sbStartEl = new StringBuilder();
        StringBuilder sbEndEl = new StringBuilder();
        try {
            Radar3DCoordinateSystem r3d = new Radar3DCoordinateSystem((float) latCenter, (float) lonCenter, (float) altCenter);
            for (int i = 0; i <= azStepSize; i++) {
                double[][] rae = {{arcRange, arcRange}, {az, az}, {startEl, endEl}};
                double[][] mylla = r3d.toReference(rae);
                sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                sbStartEl.append(mylla[LON][1]).append(",").append(mylla[LAT][1]).append(",").append(mylla[ALT][1]).append("\n");
                sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                sbEndEl.insert(0, mylla[LON][1] + "," + mylla[LAT][1] + "," + mylla[ALT][1] + "\n");
                az = az + azstep;
            }//azimuth
            coords.append(sbStartEl.toString());
            coords.append(sbEndEl.toString());
        } catch (VisADException e) {
            System.out.println(e.getMessage());
            coords = new StringBuilder();
        }
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle>\n"
                + "<PolyStyle><color>" + polyColor + "</color></PolyStyle></Style>\n"
                + "<Polygon>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<outerBoundaryIs>\n<LinearRing>\n<coordinates>\n"
                + coords.toString()
                + "\n</coordinates>\n</LinearRing>\n</outerBoundaryIs>\n</Polygon>\n</Placemark>";
        if (showVertices) {
            placemark = placemark + "\n"
                    + getKmlLineString(latCenter, lonCenter, altCenter, arcRange, startAz, startEl, width, rLine, gLine, bLine, aLine) + "\n"
                    + getKmlLineString(latCenter, lonCenter, altCenter, arcRange, endAz, startEl, width, rLine, gLine, bLine, aLine) + "\n"
                    + getKmlLineString(latCenter, lonCenter, altCenter, arcRange, startAz, endEl, width, rLine, gLine, bLine, aLine) + "\n"
                    + getKmlLineString(latCenter, lonCenter, altCenter, arcRange, endAz, endEl, width, rLine, gLine, bLine, aLine) + "\n";
        }
        return placemark;
    }//getKmlPolygonSector  

    /**
     * Returns a String KML Placemark fragment representing a polygon of a
     * section of a sphere bounded at a given range bounded by azimuth and
     * elevation limits centered on the center (reference) point of the class.
     *
     * @param arcRange Range of polysector in meters
     * @param startAz Starting azimuth of sector in decimal degrees
     * @param endAz Ending azimuth of sector in decimal degrees
     * @param azStepSize Integer number of points in sector along the azimuth
     * @param startEl Starting elevation of sector in decimal degrees
     * @param endEl Ending elevation of sector in decimal degrees
     * @param width String representing the width of the line
     * @param rLine String representing the hex RED value (00..FF) of the line
     * @param gLine String representing the hex GREEN value (00..FF) of the line
     * @param bLine String representing the hex BLUE value (00..FF) of the line
     * @param aLine String representing the hex ALPHA/transparency value
     * (00..FF) of the line
     * @param rSurface String representing the hex RED value (00..FF) of the
     * poly surface
     * @param gSurface String representing the hex GREEN value (00..FF) of the
     * poly surface
     * @param bSurface String representing the hex BLUE value (00..FF) of the
     * poly surface
     * @param aSurface String representing the hex ALPHA/transparency value
     * (00..FF) of the poly surface
     * @param showVertices boolean if true shows vertices from center
     * (reference) point to bounds
     * @return String KML Placemark
     */
    public String getKmlPolygonSector(
            double arcRange,
            double startAz,
            double endAz,
            int azStepSize,
            double startEl,
            double endEl,
            String width,
            String rLine,
            String gLine,
            String bLine,
            String aLine,
            String rSurface,
            String gSurface,
            String bSurface,
            String aSurface,
            boolean showVertices) {
        double azstep = ((endAz - startAz) / azStepSize);
        //double elstep = ((endEl - startEl) / elStepSize);
        double az = startAz;
        //double el = startEl;
        String color = aLine + bLine + gLine + rLine;
        String polyColor = aSurface + bSurface + gSurface + rSurface;
        StringBuilder coords = new StringBuilder();
        StringBuilder sbStartEl = new StringBuilder();
        StringBuilder sbEndEl = new StringBuilder();
        try {
            Radar3DCoordinateSystem r3d = new Radar3DCoordinateSystem((float) lat, (float) lon, (float) alt);
            for (int i = 0; i <= azStepSize; i++) {
                double[][] rae = {{arcRange, arcRange}, {az, az}, {startEl, endEl}};
                double[][] mylla = r3d.toReference(rae);
                sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                sbStartEl.append(mylla[LON][1]).append(",").append(mylla[LAT][1]).append(",").append(mylla[ALT][1]).append("\n");
                sbStartEl.append(mylla[LON][0]).append(",").append(mylla[LAT][0]).append(",").append(mylla[ALT][0]).append("\n");
                sbEndEl.insert(0, mylla[LON][1] + "," + mylla[LAT][1] + "," + mylla[ALT][1] + "\n");
                az = az + azstep;
            }//azimuth
            coords.append(sbStartEl.toString());
            coords.append(sbEndEl.toString());
        } catch (VisADException e) {
            System.out.println(e.getMessage());
            coords = new StringBuilder();
        }
        String placemark = "<Placemark>\n<name>Range Azimuth Elevation</name>\n"
                + "<Style><LineStyle><color>" + color + "</color><width>" + width + "</width></LineStyle>\n"
                + "<PolyStyle><color>" + polyColor + "</color></PolyStyle></Style>\n"
                + "<Polygon>\n"
                + "<tessellate>1</tessellate>\n<altitudeMode>" + this.altitudeMode + "</altitudeMode>\n<outerBoundaryIs>\n<LinearRing>\n<coordinates>\n"
                + coords.toString()
                + "\n</coordinates>\n</LinearRing>\n</outerBoundaryIs>\n</Polygon>\n</Placemark>";
        if (showVertices) {
            placemark = placemark + "\n"
                    + getKmlLineString(lat, lon, alt, arcRange, startAz, startEl, width, rLine, gLine, bLine, aLine) + "\n"
                    + getKmlLineString(lat, lon, alt, arcRange, endAz, startEl, width, rLine, gLine, bLine, aLine) + "\n"
                    + getKmlLineString(lat, lon, alt, arcRange, startAz, endEl, width, rLine, gLine, bLine, aLine) + "\n"
                    + getKmlLineString(lat, lon, alt, arcRange, endAz, endEl, width, rLine, gLine, bLine, aLine) + "\n";
        }
        return placemark;
    }//getKmlPolygonSector  

    /**
     * Returns a String KML Placemark fragment representing a polygon of a
     * section of a sphere bounded at a given range bounded by azimuth and
     * elevation limits centered on the passed center (reference) point.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param startRange
     * @param endRange
     * @param startAz Starting azimuth of sector in decimal degrees
     * @param endAz Ending azimuth of sector in decimal degrees
     * @param azStepSize Integer number of points in sector along the azimuth
     * @param startEl Starting elevation of sector in decimal degrees
     * @param endEl Ending elevation of sector in decimal degrees
     * @param width String representing the width of the line
     * @param rLine String representing the hex RED value (00..FF) of the line
     * @param gLine String representing the hex GREEN value (00..FF) of the line
     * @param bLine String representing the hex BLUE value (00..FF) of the line
     * @param aLine String representing the hex ALPHA/transparency value
     * (00..FF) of the line
     * @param rSurface String representing the hex RED value (00..FF) of the
     * poly surface
     * @param gSurface String representing the hex GREEN value (00..FF) of the
     * poly surface
     * @param bSurface String representing the hex BLUE value (00..FF) of the
     * poly surface
     * @param aSurface String representing the hex ALPHA/transparency value
     * (00..FF) of the poly surface
     * @return String KML Placemark fragment representing a polygon of a
     * section of a sphere bounded at a given range bounded by azimuth and
     * elevation limits centered on the passed center (reference) point.
     */
    public String getKmlPolygonWedge(
            double latCenter,
            double lonCenter,
            double altCenter,
            double startRange,
            double endRange,
            double startAz,
            double endAz,
            int azStepSize,
            double startEl,
            double endEl,
            String width,
            String rLine,
            String gLine,
            String bLine,
            String aLine,
            String rSurface,
            String gSurface,
            String bSurface,
            String aSurface) {

        StringBuilder placemark = new StringBuilder();
        placemark.append(getKmlPolygonSector(
                latCenter, lonCenter, altCenter,
                startRange, startAz, endAz, azStepSize, startEl, endEl,
                width,
                rLine, gLine, bLine, aLine,
                rSurface, gSurface, bSurface, aSurface, false));
        placemark.append("\n");
        placemark.append(getKmlPolygonSector(
                latCenter, lonCenter, altCenter,
                endRange, startAz, endAz, azStepSize, startEl, endEl,
                width,
                rLine, gLine, bLine, aLine,
                rSurface, gSurface, bSurface, aSurface, false));
        return placemark.toString();
    }//getKmlPolygonWedge    

    /**
     * Returns a String KML Placemark fragment representing a polygon of a
     * section of a sphere bounded at a given range bounded by azimuth and
     * elevation limits centered on the passed center (reference) point.
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param startRange
     * @param endRange
     * @param startAz Starting azimuth of sector in decimal degrees
     * @param endAz Ending azimuth of sector in decimal degrees
     * @param azStepSize Integer number of points in sector along the azimuth
     * @param startEl Starting elevation of sector in decimal degrees
     * @param endEl Ending elevation of sector in decimal degrees
     * @param width String representing the width of the line
     * @param rLine String representing the hex RED value (00..FF) of the line
     * @param gLine String representing the hex GREEN value (00..FF) of the line
     * @param bLine String representing the hex BLUE value (00..FF) of the line
     * @param aLine String representing the hex ALPHA/transparency value
     * (00..FF) of the line
     * @param rSurface String representing the hex RED value (00..FF) of the
     * poly surface
     * @param gSurface String representing the hex GREEN value (00..FF) of the
     * poly surface
     * @param bSurface String representing the hex BLUE value (00..FF) of the
     * poly surface
     * @param aSurface String representing the hex ALPHA/transparency value
     * (00..FF) of the poly surface
     * @param showBearing boolean indicating if bearing lines are depicted
     * between arcs
     * @param showAzBearing boolean indicating if all bearing lines are depicted
     * between arcs 
     * @return String KML Placemark fragment representing a polygon of a
     * section of a sphere bounded at a given range bounded by azimuth and
     * elevation limits centered on the passed center (reference) point.
     */
    public String getKmlPolygonWedge(
            double latCenter,
            double lonCenter,
            double altCenter,
            double startRange,
            double endRange,
            double startAz,
            double endAz,
            int azStepSize,
            double startEl,
            double endEl,
            String width,
            String rLine,
            String gLine,
            String bLine,
            String aLine,
            String rSurface,
            String gSurface,
            String bSurface,
            String aSurface,
            boolean showBearing,
            boolean showAzBearing) {

        StringBuilder placemark = new StringBuilder();
        placemark.append(getKmlPolygonSector(
                latCenter, lonCenter, altCenter,
                startRange, startAz, endAz, azStepSize, startEl, endEl,
                width,
                rLine, gLine, bLine, aLine,
                rSurface, gSurface, bSurface, aSurface, false));
        placemark.append("\n");
        placemark.append(getKmlPolygonSector(
                latCenter, lonCenter, altCenter,
                endRange, startAz, endAz, azStepSize, startEl, endEl,
                width,
                rLine, gLine, bLine, aLine,
                rSurface, gSurface, bSurface, aSurface, false));
        placemark.append("\n");
        if (showBearing) {
            if (showAzBearing) {
                double azstep = ((endAz - startAz) / azStepSize);
                double az = startAz;
                for (int i = 0; i <= azStepSize; i++) {
                    placemark.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, az, startEl, width, rLine, gLine, bLine, aLine));
                    placemark.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, az, endEl, width, rLine, gLine, bLine, aLine));
                    az = az + azstep;
                }//walk the azimuth
            } else {
                placemark.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, startAz, startEl, width, rLine, gLine, bLine, aLine));
                placemark.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, endAz, startEl, width, rLine, gLine, bLine, aLine));
                placemark.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, startAz, endEl, width, rLine, gLine, bLine, aLine));
                placemark.append(getKmlLineStringSegment(latCenter, lonCenter, altCenter, startRange, endRange, endAz, endEl, width, rLine, gLine, bLine, aLine));

            }
        }
        return placemark.toString();
    }//getKmlPolygonWedge      

    /**
     * Returns a String KML Folder fragment representing a dome centered on the
     * passed center (reference) point with a range passed as a parameter. Note
     * this dome is NOT a sphere and only depicts a semi-sphere from elevation
     * angle 0 to 90 (straight up).
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param range Range of dome radius in meters
     * @param azStepSize Integer number of points in dome along the azimuth
     * @param elStepSize Integer number of points in dome from horizontal to
     * 90deg
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF) of the line
     * @param g String representing the hex GREEN value (00..FF) of the line
     * @param b String representing the hex BLUE value (00..FF) of the line
     * @param a String representing the hex ALPHA/transparency value (00..FF) of
     * the line
     * @return
     */
    public String getKmlLineStringDome(
            double latCenter,
            double lonCenter,
            double altCenter,
            double range,
            int azStepSize,
            int elStepSize,
            String width,
            String r,
            String g,
            String b,
            String a) {

        double startEl = 0.0;
        double endEl = 90.0;
        double startAz = 0.0;
        double endAz = 360.0;
        StringBuilder placemark = new StringBuilder();
        placemark.append("<Folder>\n<name>DOME LINESTRING</name>\n");
        placemark.append(getKmlLineStringSector(
                latCenter, lonCenter, altCenter, range,
                startAz, endAz, azStepSize,
                startEl, endEl, elStepSize,
                width,
                r, g, b, a,
                false));
        placemark.append("\n</Folder>\n");

        return placemark.toString();
    }//getKmlLineStringDome

    /**
     * Returns a String KML Folder fragment representing a dome centered on the
     * class center (reference) point with a range passed as a parameter. Note
     * this dome is NOT a sphere and only depicts a semi-sphere from elevation
     * angle 0 to 90 (straight up).
     *
     * @param range Range of dome radius in meters
     * @param azStepSize Integer number of points in dome along the azimuth
     * @param elStepSize Integer number of points in dome from horizontal to
     * 90deg
     * @param width String representing the width of the line
     * @param r String representing the hex RED value (00..FF) of the line
     * @param g String representing the hex GREEN value (00..FF) of the line
     * @param b String representing the hex BLUE value (00..FF) of the line
     * @param a String representing the hex ALPHA/transparency value (00..FF) of
     * the line
     * @return
     */
    public String getKmlLineStringDome(
            double range,
            int azStepSize,
            int elStepSize,
            String width,
            String r,
            String g,
            String b,
            String a) {

        double startEl = 0.0;
        double endEl = 90.0;
        double startAz = 0.0;
        double endAz = 360.0;
        StringBuilder placemark = new StringBuilder();
        placemark.append("<Folder>\n<name>DOME LINESTRING</name>\n");
        placemark.append(getKmlLineStringSector(
                this.lat, this.lon, this.alt, range,
                startAz, endAz, azStepSize,
                startEl, endEl, elStepSize,
                width,
                r, g, b, a,
                false));
        placemark.append("\n</Folder>\n");

        return placemark.toString();
    }//getKmlLineStringDome    

    /**
     * Returns a String KML Folder representing a series of polygons of a
     * semi-sphere centered on the passed center (reference) point with a range
     * passed as a parameter. Note this dome is NOT a sphere and only depicts a
     * semi-sphere from elevation angle 0 to 90 (straight up).
     *
     * @param latCenter Latitude of the reference point in decimal degrees
     * @param lonCenter Longitude of the reference point in decimal degrees
     * @param altCenter Altitude of the reference point in meters
     * @param range Range of dome radius in meters
     * @param azStepSize Integer number of points in dome along the azimuth
     * @param elStepSize Integer number of points in dome from horizontal to
     * 90deg
     *
     * @param width String representing the width of the line
     * @param rLine String representing the hex RED value (00..FF) of the line
     * @param gLine String representing the hex GREEN value (00..FF) of the line
     * @param bLine String representing the hex BLUE value (00..FF) of the line
     * @param aLine String representing the hex ALPHA/transparency value
     * (00..FF) of the line
     * @param rSurface String representing the hex RED value (00..FF) of the
     * poly surface
     * @param gSurface String representing the hex GREEN value (00..FF) of the
     * poly surface
     * @param bSurface String representing the hex BLUE value (00..FF) of the
     * poly surface
     * @param aSurface String representing the hex ALPHA/transparency value
     * (00..FF) of the poly surface
     * @return
     */
    public String getKmlPolygonDome(
            double latCenter,
            double lonCenter,
            double altCenter,
            double range,
            int azStepSize,
            int elStepSize,
            String width,
            String rLine,
            String gLine,
            String bLine,
            String aLine,
            String rSurface,
            String gSurface,
            String bSurface,
            String aSurface) {

        StringBuilder placemark = new StringBuilder();
        double startEl = 0.0;
        double endEl = 90.0;
        double startAz = 0.0;
        double endAz = 360.0;
        double elstep = ((endEl - startEl) / elStepSize);
        double el = startEl;

        placemark.append("<Folder>\n<name>DOME POLYGON</name>\n");
        for (int e = 0; e < elStepSize; e++) {
            double thisEndEl = el + elstep;
            placemark.append(getKmlPolygonSector(
                    latCenter, lonCenter, altCenter,
                    range, startAz, endAz, azStepSize, el, thisEndEl,
                    width,
                    rLine, gLine, bLine, aLine,
                    rSurface, gSurface, bSurface, aSurface, false));
            el = thisEndEl;
            placemark.append("\n");

        }//elevation        
        placemark.append("\n</Folder>\n");

        return placemark.toString();
    }//getKmlPolygonDome  

    /**
     * Returns a String KML Folder representing a series of polygons of a
     * semi-sphere centered on the class center (reference) point with a range
     * passed as a parameter. Note this dome is NOT a sphere and only depicts a
     * semi-sphere from elevation angle 0 to 90 (straight up).
     *
     * @param range Range of dome radius in meters
     * @param azStepSize Integer number of points in dome along the azimuth
     * @param elStepSize Integer number of points in dome from horizontal to
     * 90deg
     *
     * @param width String representing the width of the line
     * @param rLine String representing the hex RED value (00..FF) of the line
     * @param gLine String representing the hex GREEN value (00..FF) of the line
     * @param bLine String representing the hex BLUE value (00..FF) of the line
     * @param aLine String representing the hex ALPHA/transparency value
     * (00..FF) of the line
     * @param rSurface String representing the hex RED value (00..FF) of the
     * poly surface
     * @param gSurface String representing the hex GREEN value (00..FF) of the
     * poly surface
     * @param bSurface String representing the hex BLUE value (00..FF) of the
     * poly surface
     * @param aSurface String representing the hex ALPHA/transparency value
     * (00..FF) of the poly surface
     * @return
     */
    public String getKmlPolygonDome(
            double range,
            int azStepSize,
            int elStepSize,
            String width,
            String rLine,
            String gLine,
            String bLine,
            String aLine,
            String rSurface,
            String gSurface,
            String bSurface,
            String aSurface) {

        StringBuilder placemark = new StringBuilder();
        double startEl = 0.0;
        double endEl = 90.0;
        double startAz = 0.0;
        double endAz = 360.0;
        double elstep = ((endEl - startEl) / elStepSize);
        double el = startEl;

        placemark.append("<Folder>\n<name>DOME POLYGON</name>\n");
        for (int e = 0; e < elStepSize; e++) {
            double thisEndEl = el + elstep;
            placemark.append(getKmlPolygonSector(
                    this.lat, this.lon, this.alt,
                    range, startAz, endAz, azStepSize, el, thisEndEl,
                    width,
                    rLine, gLine, bLine, aLine,
                    rSurface, gSurface, bSurface, aSurface, false));
            el = thisEndEl;
            placemark.append("\n");

        }//elevation        
        placemark.append("\n</Folder>\n");

        return placemark.toString();
    }//getKmlPolygonDome     
}
