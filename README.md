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
