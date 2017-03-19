/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ovlweb.utils;

/**
 *
 * @author marty.ad.dudel
 */
public class lineKML {
    String lon1;     //LON (x) START
    String lat1;     //LAT (y)
    String lon2;     //LON (x) END
    String lat2;     //LAT (y)
    String width;
    String color;
    String title;

    public lineKML(String lineTitle,
            String startLat,
            String startLon,
            String endLat,
            String endLon,
            String lineWidth,
            String lineColor
            ){
        title = lineTitle;
        lon1 = startLon;
        lat1 = startLat;
        lon2 = endLon;
        lat2 = endLat;
        width = lineWidth;
        color = lineColor;
    }//lineKML

    public String getKML(){
        String myKML = "<Placemark><Style><LineStyle><color>"+color;
        myKML = myKML + "</color><width>"+width+"</width></LineStyle></Style><name>"+title;
        myKML = myKML + "</name><LineString><tessellate>1</tessellate><coordinates>";
        myKML = myKML + lon1+","+lat1+",0.0,"+lon2+","+lat2+",0.0</coordinates></LineString></Placemark>";
        return myKML;
    }
}//class
