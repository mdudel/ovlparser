/*
 * -----------------------------------------------------------------------------
 *        UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED UNCLASSIFIED
 *                  (C) Copyright 2015, USAREUR G3 MCSD
 *                          ALL RIGHTS RESERVED
 *                  THIS NOTICE DOES NOT IMPLY PUBLICATION
 * -----------------------------------------------------------------------------
 */
package graphics;

import missilegraphics.Bearing;
import missilegraphics.LatLonPointImpl;

/**
 *
 * @author marty
 */
public class LobKML {

    double lon;         //LON (x) degrees decimal of reference point
    double lat;         //LAT (y) degrees decimal of reference point
    double alt;         // Altitude
    double brg;         // Angle/bearing of bearing box (from North, ex E=90.0)
    double brgerror;    // Bearing error
    double range;       // Range Estimate (km)
    double dblRange;    // Range Estimate * 2.0 (km)
    
    double estimatedLat;   //LON (x) degrees decimal of estimated point in AOU
    double estimatedLon;   //LAT (y) degrees decimal of estimated point in AOU    

    public LobKML(double lat, double lon, double alt, double brg, double brgerror, double range) {
        this.lon = lon;
        this.lat = lat;
        this.alt = alt;
        this.brg = brg;
        this.brgerror = brgerror;
        this.range = range;
        this.dblRange = range * 2.0;
        
        LatLonPointImpl p0 = new LatLonPointImpl(lat, lon); // point from which lob is defined
        LatLonPointImpl pLob = new LatLonPointImpl();
        Bearing.findPoint(p0, brg, range, pLob);
        estimatedLat = pLob.getLatitude();
        estimatedLon = pLob.getLongitude();
    }// LobKML

    public String getKMLCoords() {
        StringBuilder lob = new StringBuilder();
        LatLonPointImpl p0 = new LatLonPointImpl(lat, lon); // pount from which lob is defined

        LatLonPointImpl pLobErr0 = new LatLonPointImpl();
        Bearing.findPoint(p0, (brg - brgerror), dblRange, pLobErr0);

        LatLonPointImpl pLob = new LatLonPointImpl();
        Bearing.findPoint(p0, brg, dblRange, pLob);

        LatLonPointImpl pLobErr1 = new LatLonPointImpl();
        Bearing.findPoint(p0, (brg + brgerror), dblRange, pLobErr1);

        lob.append(pLobErr0.getLongitude()).append(",").append(pLobErr0.getLatitude()).append(",").append(alt).append(" \n");
        lob.append(p0.getLongitude()).append(",").append(p0.getLatitude()).append(",").append(alt).append(" \n");
        lob.append(pLob.getLongitude()).append(",").append(pLob.getLatitude()).append(",").append(alt).append(" \n");
        lob.append(p0.getLongitude()).append(",").append(p0.getLatitude()).append(",").append(alt).append(" \n");
        lob.append(pLobErr1.getLongitude()).append(",").append(pLobErr1.getLatitude()).append(",").append(alt).append(" \n");

        return lob.toString();
    }

    public String getKmlLobCoords() {
        StringBuilder coords = new StringBuilder();
        coords.append(estimatedLon).append(",").append(estimatedLat).append(",").append(alt).append(" \n");
        return coords.toString();
    }
    
    public String getEstimatedLatString(){
        StringBuilder sb = new StringBuilder();
        sb.append(estimatedLat);
        return sb.toString();
    }
    
    public String getEstimatedLonString(){
        StringBuilder sb = new StringBuilder();
        sb.append(estimatedLon);
        return sb.toString();
    }     

    public double getEstimatedLat() {
        return estimatedLat;
    }

    public double getEstimatedLon() {
        return estimatedLon;
    }

}
