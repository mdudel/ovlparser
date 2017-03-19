# ovlparser

OvlParser

Convert GCCS-J/A or CPOF OVL (overlay) files to KMZ

Options:

        OvlParser
        Converts an OVL file to a KMZ file

        Options
                ? or help: Print this message
                IN=path/to/input/filename (required)
                OUT=path/to/output/filename (optional)
                TITLE=title of KML document (optional)

Example:

java -jar "C:\OvlParser\dist\OvlParser.jar" IN=C:\OvlParser\ovl\MOBILITY.ovl

java -jar "C:\OvlParser\dist\OvlParser.jar" IN=C:\OvlParser\ovl\MOBILITY.ovl TITLE="MOB OVL2KML TEST"

This code relies heavily upon the mil-sym-java project: https://github.com/missioncommand/mil-sym-java

See OvlParser.java for using the code: https://github.com/mdudel/ovlparser/blob/master/src/ovlparser/OvlParser.java

        String inFile = "pathToOvlFile";
        StringBuilder ovl2kml = new StringBuilder();
        
        // Append KML doc header
        ovl2kml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        ovl2kml.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:kml=\"http://www.opengis.net/kml/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\n");
        ovl2kml.append("<Document>\n");
        ovl2kml.append("<name>").append("KML DOCUMENT NAME").append("</name>\n");
        
        // Build the KML body by converting each OVL object to a KML placemark
        HashMap<String, Map<String, String>> singlePoints = new HashMap<String, Map<String, String>>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MODEL.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            MODEL model = (MODEL) jaxbUnmarshaller.unmarshal(new File(inFile));
            Ovl2KmlVisitor ovl = new Ovl2KmlVisitor(100); //pass point count for circles/ellipses
            ovl.setIconURL("");
            ovl.setImageType("png");
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

        // Append the KML doc footer
        ovl2kml.append("</Document>\n");
        ovl2kml.append("</kml>\n");
        
        String outFile = "pathToKmzFile";
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
                // write the single point symbols as pngs
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
        
        
        
