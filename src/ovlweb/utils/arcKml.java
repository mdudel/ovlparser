/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils;

/**
 *
 * @author KKEITH
 */
public class arcKml {

    double lon;         //LON (x) degrees decimal
    double lat;         //LAT (y) degrees decimal
    double alt;         // Altitude
    double brgstart;    // Angle/bearing of fan (from North, ex E=90.0)
    // brg is in degrees. The clockwise START of the fan
    double brgstop;     // Stop bearing of fan (degrees) in a clockwise direction
    double maxRange;    // Outer radius of fan from center (km)
    int steps;          // Number of points for inner and outer radius
    double deg2rad = Math.PI / 180.0;
    double rad2deg = 180.0 / Math.PI;

    public arcKml(double latCenter, double lonCenter, double altitude, double MaxRange, double brgStart, double brgEnd, int PointNum) {
        lat = latCenter;
        lon = lonCenter;
        alt = altitude;
        maxRange = MaxRange;
        brgstart = brgStart;
        brgstop = brgEnd;
        steps = PointNum;
    }

    public String getKMLCoords() {
        String fanCoords = "";
        double delbrg = brgstop - brgstart;
        
        int stepsize = (int) (delbrg / steps);
        double az = 0.0;

        LatLonPointImpl myCenter = new LatLonPointImpl(lat, lon); // center
        LatLonPointImpl pt1 = new LatLonPointImpl(); 
        
        for (int i = 0; i < steps; i += 1) {
            az = brgstop - (delbrg / steps) * i;
            Bearing.findPoint(myCenter, az, maxRange, pt1);
            fanCoords += pt1.getLongitude() + "," + pt1.getLatitude() + "," + alt + "\n";
        }
        return fanCoords;
    }
}
