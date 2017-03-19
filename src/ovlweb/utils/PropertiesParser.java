/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author KKEITH
 */
public class PropertiesParser {

    Properties reference = new Properties();

    public PropertiesParser(String file) {
        File path = new File(file);
        
        try {
            reference.load(new FileInputStream(path));
        } catch (Exception e) {
            System.out.println("Error loading Properties file: " + e);
        }
    }

    public PropertiesParser(File file) {
        reference = new Properties();

        try {
            reference.load(new FileInputStream(file));
        } catch (Exception e) {
            System.out.println("Error loading Properties file: " + e);
        }

    }

    public String getIpAddress(){
        if(reference.containsKey("IPAddress")){
            return reference.getProperty("IPAddress");
        }
        return "127.0.0.1";
    }
    
    public int getServicePort(){
        if(reference.containsKey("ServicePort")){
            int port = Integer.decode(reference.getProperty("ServicePort"));
            if(port>0){
                return port;
            } else {
                return 9081;
            }
        }
        return 9081;
    }
    
    public ArrayList<String> getOvlFolders() {
        ArrayList<String> ovlFolders = new ArrayList<String>();
        
        if(reference.containsKey("OverlayFolders")){
            String[] folders = reference.getProperty("OverlayFolders").split(";");
            for(String S:folders){
                ovlFolders.add(S);
            }
            return ovlFolders;
        }
        return null;
    }
}
