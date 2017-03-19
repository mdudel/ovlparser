 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlparser;

import ArmyC2.C2SD.Utilities.MilStdAttributes;
import interfaces.OvlGraphicElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import ovl.schema.gccs.MODEL;
import sec.web.renderer.SECRenderer;
import sec.web.renderer.utilities.PNGInfo;

/**
 *
 * @author marty
 */
public class OvlParser {

    static String helpText = "\n\tOvlParser"
            + "\n\tConverts an OVL file to a KMZ file"
            + "\n"
            + "\n\tOptions"
            + "\n\t\t? or help: Print this message"
            + "\n\t\tIN=path/to/input/filename (required)"
            + "\n\t\tOUT=path/to/output/filename (optional)"
            + "\n\t\tTITLE=title of KML document (optional)";

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        // [x] CIRCLE [?] TEXT LABEL [x] ARROW [x] BRG BOX [x] ELLIPSE 
        // [x] ELLIPTICAL ARC [x] ARC [x] POLYGON [x] RECTANGLE [x] SECTOR 
        // [x] SYMBOL [?] UNIT [x] CORRIDOR [ ] BITMAP [x] UIE & MOOTW 
        // [x] METOC [x] TACTICAL GRAPHICS 
        StringBuilder ovl2kml = new StringBuilder();
        String kmlName = "OVL to KML Conversion";
        String inFile = "";
        String outFile = "";
        String imageType = "png";
        boolean inFileSet = false;
        boolean outFileSet = false;

        try {
            if (args.length == 0) {
                System.out.println(helpText);
                System.exit(0);
            }
            for (String s : args) {
                if (s.toUpperCase().equals("HELP") || s.equals("?")) {
                    System.out.println(helpText);
                    System.exit(0);
                }
                String[] arg = s.split("=");
                if (arg[0].toUpperCase().startsWith("IN")) {
                    inFile = arg[1];
                    System.out.println("INPUT FILE SET TO: " + inFile);
                    inFileSet = true;
                }
                if (arg[0].toUpperCase().startsWith("OUT")) {
                    outFile = arg[1];
                    System.out.println("OUTPUT FILE SET TO: " + outFile);
                }
                if (arg[0].toUpperCase().startsWith("T")) {
                    kmlName = arg[1];
                    System.out.println("KML DOCUMENT TITLE SET TO: " + kmlName);
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR reading arguments: " + e.getMessage());
        }// try catch reading arguments    

        if (!inFileSet) {
            System.out.println("An input file is required.");
            System.exit(0);
        }

        ovl2kml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        ovl2kml.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:kml=\"http://www.opengis.net/kml/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\n");
        ovl2kml.append("<Document>\n");
        ovl2kml.append("<name>").append(kmlName).append("</name>\n");

        HashMap<String, Map<String, String>> singlePoints = new HashMap<String, Map<String, String>>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MODEL.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            MODEL model = (MODEL) jaxbUnmarshaller.unmarshal(new File(inFile));
            Ovl2KmlVisitor ovl = new Ovl2KmlVisitor(100); //pass point count as parameter
            ovl.setIconURL("");
            ovl.setImageType(imageType);
            for (Object obj : model.getPlottableObjects()) {
                if (obj instanceof OvlGraphicElement) {
                    OvlGraphicElement e = (OvlGraphicElement) obj;
                    e.accept(ovl);
                }// if ovl object
            }//for ovl object
            ovl2kml.append(ovl.getKml()).append("\n");
            singlePoints.putAll(ovl.getSinglePoints());
        } catch (JAXBException ex) {
            ovl2kml.append("<description>\n");
            ovl2kml.append("EXCEPTION:\n").append(ex.toString());
            System.out.println("Error parsing " + inFile + "\n" + ex.getMessage());
            ovl2kml.append("</description>\n");
        }//try catch block

        ovl2kml.append("</Document>\n");
        ovl2kml.append("</kml>\n");

        if (!outFileSet) {
            outFile = inFile + ".kmz";
        }
        File fout = new File(outFile);
        if (!fout.exists()) {
            try {
                fout.createNewFile();
                FileOutputStream fos = new FileOutputStream(fout);
                ZipOutputStream zoS = new ZipOutputStream(fos);
                ZipEntry ze = new ZipEntry("doc.kml");
                zoS.putNextEntry(ze);
                PrintStream ps = new PrintStream(zoS);
                // write doc.kml into the KMZ file
                ps.println(ovl2kml.toString());
                ps.flush();
                zoS.closeEntry(); // close KML entry    
                // write the simgle point symbols
                for (Map.Entry<String, Map<String, String>> entry : singlePoints.entrySet()) {
                    SECRenderer sr = SECRenderer.getInstance();
                    Map<String, String> params = entry.getValue();
                    PNGInfo pi;
                    pi = sr.getMilStdSymbolImage(entry.getKey(), params);
                    if (null == pi) {
                        System.out.println("Error creating " + entry.getKey());
                        System.out.println("Parameters: " + params);
                        params.put(MilStdAttributes.FillColor, "00ffffff");
                        pi = sr.getMilStdSymbolImage("SUGP-----------", params);
                    }
                    ZipEntry zEnt = new ZipEntry(entry.getKey() + "." + imageType);
                    zoS.putNextEntry(zEnt);
                    zoS.write(pi.getImageAsByteArray());
                    zoS.flush();
                    zoS.closeEntry();
                }
                zoS.close();

            } catch (IOException ex) {
                System.out.println("Error writing " + outFile + "\n" + ex.getMessage());
            }
        }

        //System.out.println(ovl2kml);
    }//main
}
