/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils;

/**
 *
 * @author KKEITH
 */
public class APP6AGraphicUtil {

    public static String getCodeScheme(String symbolcode) {

        String code = String.valueOf(symbolcode.charAt(0));

        if (code.contentEquals("S")) {
            return "WARFIGHTING";
        }

        if (code.contentEquals("G")) {
            return "C2 SYMBOLOGY MILITARY OPERATIONS";
        }

        if (code.contentEquals("W")) {
            return "METOC";
        }

        if (code.contentEquals("I")) {
            return "SIGNALS INTELLIGENCE";
        }

        if (code.contentEquals("M")) {
            return "MAPPING";
        }
        return "UNKNOWN";
    }

    public static String getAffiliation(String symbolcode) {

        String code = String.valueOf(symbolcode.charAt(1));

        if (code.contentEquals("P")) {
            return "PENDING";
        }
        if (code.contentEquals("U")) {
            return "UNKNOWN";
        }
        if (code.contentEquals("A")) {
            return "ASSUMED FRIEND";
        }
        if (code.contentEquals("F")) {
            return "FRIEND";
        }
        if (code.contentEquals("N")) {
            return "NEUTRAL";
        }
        if (code.contentEquals("S")) {
            return "SUSPECT";
        }
        if (code.contentEquals("H")) {
            return "HOSTILE";
        }
        if (code.contentEquals("G")) {
            return "EXERCISE PENDING";
        }
        if (code.contentEquals("W")) {
            return "EXERCISE UNKNOWN";
        }
        if (code.contentEquals("M")) {
            return "EXERCISE ASSUMED FRIEND";
        }
        if (code.contentEquals("D")) {
            return "EXERCISE FRIEND";
        }
        if (code.contentEquals("L")) {
            return "EXERCISE NEUTRAL";
        }
        if (code.contentEquals("J")) {
            return "JOKER";
        }
        if (code.contentEquals("K")) {
            return "FAKER";
        }

        return "UNKNOWN";
    }

    public static String getBattleDimension(String symbolcode) {
        String code = String.valueOf(symbolcode.charAt(2));

        if (code.contentEquals("P")) {
            return "SPACE";
        }
        if (code.contentEquals("A")) {
            return "AIR";
        }
        if (code.contentEquals("G")) {
            return "GROUND";
        }
        if (code.contentEquals("S")) {
            return "SEA SURFACE";
        }
        if (code.contentEquals("U")) {
            return "SEA SUBSURFACE";
        }
        if (code.contentEquals("F")) {
            return "SOF";
        }
        if (code.contentEquals("X")) {
            return "OTHER";
        }
        if (code.contentEquals("T")) {
            return "TASKS";
        }
        if (code.contentEquals("C")) {
            return "CONTROL MEASURES";
        }
        if (code.contentEquals("O")) {
            return "OPERATIONS OTHER THAN WAR";
        }
        if (code.contentEquals("Z")) {
            return "UNKNOWN";
        }
        return "UNKNOWN";
    }

    public static String getStatus(String symbolcode) {

        String code = String.valueOf(symbolcode.charAt(3));

        if (code.contentEquals("A")) {
            return "ANTICIPATED/PLANNED";
        }
        if (code.contentEquals("P")) {
            return "PRESENT";
        }
        return "UNKNOWN";
    }

    public static String getFunction(String symbolcode) {

        String code = symbolcode.substring(4, 6);

        if (code.contentEquals("------")) {
            return "WARFIGHTING SYMBOL - UNKNOWN";
        }
        if (code.contentEquals("------")) {
            return "SPACE TRACK";
        }
        if (code.contentEquals("S-----")) {
            return "SATELLITE";
        }
        if (code.contentEquals("V-----")) {
            return "CREWED SPACE VEHICLE";
        }
        if (code.contentEquals("T-----")) {
            return "SPACE STATION";
        }
        if (code.contentEquals("------")) {
            return "AIR TRACK";
        }
        if (code.contentEquals("M-----")) {
            return "MILITARY";
        }
        if (code.contentEquals("MF----")) {
            return "FIXED WING";
        }
        if (code.contentEquals("MFB---")) {
            return "BOMBER";
        }
        if (code.contentEquals("MFF---")) {
            return "FIGHTER";
        }
        if (code.contentEquals("MFFI--")) {
            return "INTERCEPTOR";
        }
        if (code.contentEquals("MFT---")) {
            return "TRAINER";
        }
        if (code.contentEquals("MFA---")) {
            return "ATTACK/STRIKE";
        }
        if (code.contentEquals("MFL---")) {
            return "V/STOL";
        }
        if (code.contentEquals("MFK---")) {
            return "TANKER";
        }
        if (code.contentEquals("MFC---")) {
            return "CARGO AIRLIFT (TRANSPORT)";
        }
        if (code.contentEquals("MFCL--")) {
            return "CARGO AIRLIFT (LIGHT)";
        }
        if (code.contentEquals("MFCM--")) {
            return "CARGO AIRLIFT (MEDIUM)";
        }
        if (code.contentEquals("MFCH--")) {
            return "CARGO AIRLIFT (HEAVY)";
        }
        if (code.contentEquals("MFJ---")) {
            return "ELECTRONIC COUNTERMEASURES (ECM/JAMMER)";
        }
        if (code.contentEquals("MFO---")) {
            return "MEDEVAC";
        }
        if (code.contentEquals("MFR---")) {
            return "RECONNAISSANCE";
        }
        if (code.contentEquals("MFRW--")) {
            return "AIRBORNE EARLY WARNING (AEW)";
        }
        if (code.contentEquals("MFRZ--")) {
            return "ELECTRONIC SURVEILLANCE MEASURES";
        }
        if (code.contentEquals("MFRX--")) {
            return "PHOTOGRAPHIC";
        }
        if (code.contentEquals("MFP---")) {
            return "PATROL";
        }
        if (code.contentEquals("MFPN--")) {
            return "ANTISURFACE WARFARE/ASUW";
        }
        if (code.contentEquals("MFPM--")) {
            return "MINE COUNTERMEASURES (MCM)";
        }
        if (code.contentEquals("MFU---")) {
            return "UTILITY";
        }
        if (code.contentEquals("MFUL--")) {
            return "UTILITY (LIGHT)";
        }
        if (code.contentEquals("MFUM--")) {
            return "UTILITY (MEDIUM)";
        }
        if (code.contentEquals("MFUH--")) {
            return "UTILITY (HEAVY)";
        }
        if (code.contentEquals("MFY---")) {
            return "COMMUNICATIONS (C3I)";
        }
        if (code.contentEquals("MFH---")) {
            return "COMBAT SEARCH AND RESCUE (CSAR)";
        }
        if (code.contentEquals("MFD---")) {
            return "AIRBORNE COMMAND POST (C²)";
        }
        if (code.contentEquals("MFQ---")) {
            return "DRONE (RPV/UAV)103";
        }
        if (code.contentEquals("MFS---")) {
            return "ANTISUBMARINE WARFARE (ASW) CARRIER BASED";
        }
        if (code.contentEquals("MFM---")) {
            return "SPECIAL OPERATIONS FORCES (SOF)";
        }
        if (code.contentEquals("MH----")) {
            return "ROTARY WING";
        }
        if (code.contentEquals("MHA---")) {
            return "ATTACK";
        }
        if (code.contentEquals("MHS---")) {
            return "ANTISUBMARINE WARFARE (ASW)/MARITIME PATROL AIRCRAFT (MPA)";
        }
        if (code.contentEquals("MHU---")) {
            return "UTILITY";
        }
        if (code.contentEquals("MHUL--")) {
            return "UTILITY (LIGHT)";
        }
        if (code.contentEquals("MHUM--")) {
            return "UTILITY (MEDIUM)";
        }
        if (code.contentEquals("MHUH--")) {
            return "UTILITY (HEAVY)";
        }
        if (code.contentEquals("MHI---")) {
            return "MINE COUNTERMEASURES (MCM)";
        }
        if (code.contentEquals("MHH---")) {
            return "COMBAT SEARCH AND RESCUE (CSAR)";
        }
        if (code.contentEquals("MHR---")) {
            return "RECONNAISSANCE";
        }
        if (code.contentEquals("MHQ---")) {
            return "DRONE (RPV/UAV)";
        }
        if (code.contentEquals("MHC---")) {
            return "CARGO AIRLIFT (TRANSPORT)";
        }
        if (code.contentEquals("MHCL--")) {
            return "CARGO AIRLIFT (LIGHT)";
        }
        if (code.contentEquals("MHCM--")) {
            return "CARGO AIRLIFT (MEDIUM)";
        }
        if (code.contentEquals("MHCH--")) {
            return "CARGO AIRLIFT (HEAVY)";
        }
        if (code.contentEquals("MHT---")) {
            return "TRAINER";
        }
        if (code.contentEquals("MHO---")) {
            return "MEDEVAC";
        }
        if (code.contentEquals("MHM---")) {
            return "SPECIAL OPERATIONS FORCES (SOF)";
        }
        if (code.contentEquals("MHD---")) {
            return "AIRBORNE COMMAND POST (C²)";
        }
        if (code.contentEquals("MHK---")) {
            return "TANKER";
        }
        if (code.contentEquals("MHJ---")) {
            return "ELECTRONIC COUNTERMEASURES (ECM/JAMMER)";
        }
        if (code.contentEquals("ML----")) {
            return "LIGHTER THAN AIR";
        }
        if (code.contentEquals("W-----")) {
            return "WEAPON";
        }
        if (code.contentEquals("WM----")) {
            return "MISSILE IN FLIGHT";
        }
        if (code.contentEquals("WMS---")) {
            return "SURFACE/LAND LAUNCHED MISSILE";
        }
        if (code.contentEquals("WMSS--")) {
            return "SURFACE TO SURFACE MISSILE (SSM)";
        }
        if (code.contentEquals("WMSA--")) {
            return "SURFACE TO AIR MISSILE (SAM)";
        }
        if (code.contentEquals("WMA---")) {
            return "AIR LAUNCHED MISSILE";
        }
        if (code.contentEquals("WMAS--")) {
            return "AIR TO SURFACE MISSILE (ASM)";
        }
        if (code.contentEquals("WMAA--")) {
            return "AIR TO AIR MISSILE (AAM)";
        }
        if (code.contentEquals("WMU---")) {
            return "SUBSURFACE TO SURFACE MISSILE (S/SSM)";
        }
        if (code.contentEquals("WML---")) {
            return "LAND ATTACK MISSILE";
        }
        if (code.contentEquals("WD----")) {
            return "DECOY";
        }
        if (code.contentEquals("C-----")) {
            return "CIVIL AIRCRAFT";
        }
        if (code.contentEquals("CF----")) {
            return "FIXED WING";
        }
        if (code.contentEquals("CH----")) {
            return "ROTARY WING";
        }
        if (code.contentEquals("CL----")) {
            return "LIGHTER THAN AIR";
        }
        if (code.contentEquals("------")) {
            return "GROUND TRACK";
        }
        if (code.contentEquals("U-----")) {
            return "UNIT";
        }
        if (code.contentEquals("UC----")) {
            return "COMBAT";
        }
        if (code.contentEquals("UCD---")) {
            return "AIR DEFENCE";
        }
        if (code.contentEquals("UCDS--")) {
            return "SHORT RANGE";
        }
        if (code.contentEquals("UCDSC-")) {
            return "CHAPARRAL";
        }
        if (code.contentEquals("UCDSS-")) {
            return "STINGER";
        }
        if (code.contentEquals("UCDSV-")) {
            return "VULCAN";
        }
        if (code.contentEquals("UCDM--")) {
            return "AIR DEFENCE MISSILE";
        }
        if (code.contentEquals("UCDML-")) {
            return "AIR DEFENCE MISSILE LIGHT";
        }
        if (code.contentEquals("UCDMLA")) {
            return "AIR DEFENCE MISSILE MOTORISED (AVENGER)";
        }
        if (code.contentEquals("UCDMM-")) {
            return "AIR DEFENCE MISSILE MEDIUM";
        }
        if (code.contentEquals("UCDMH-")) {
            return "AIR DEFENCE MISSILE HEAVY";
        }
        if (code.contentEquals("UCDH--")) {
            return "H/MAD";
        }
        if (code.contentEquals("UCDHH-")) {
            return "HAWK";
        }
        if (code.contentEquals("UCDHP-")) {
            return "PATRIOT";
        }
        if (code.contentEquals("UCDG--")) {
            return "GUN UNIT";
        }
        if (code.contentEquals("UCDC--")) {
            return "COMPOSITE";
        }
        if (code.contentEquals("UCDT--")) {
            return "TARGETING UNIT";
        }
        if (code.contentEquals("UCDO--")) {
            return "THEATRE MISSILE DEFENCE UNIT";
        }
        if (code.contentEquals("UCA---")) {
            return "ARMOUR";
        }
        if (code.contentEquals("UCAT--")) {
            return "ARMOUR TRACKED";
        }
        if (code.contentEquals("UCATA-")) {
            return "ARMOUR TRACKED AIRBORNE";
        }
        if (code.contentEquals("UCATW-")) {
            return "ARMOUR TRACKED AMPHIBIOUS";
        }
        if (code.contentEquals("UCATWR")) {
            return "ARMOUR TRACKED AMPHIBIOUS RECOVERY";
        }
        if (code.contentEquals("UCATL-")) {
            return "ARMOUR TRACKED  LIGHT";
        }
        if (code.contentEquals("UCATM-")) {
            return "ARMOUR TRACKED  MEDIUM";
        }
        if (code.contentEquals("UCATH-")) {
            return "ARMOUR TRACKED  HEAVY";
        }
        if (code.contentEquals("UCATR-")) {
            return "ARMOUR TRACKED  RECOVERY";
        }
        if (code.contentEquals("UCAW--")) {
            return "ARMOUR  WHEELED";
        }
        if (code.contentEquals("UCAWS-")) {
            return "ARMOUR  WHEELED AIR ASSAULT";
        }
        if (code.contentEquals("UCAWA-")) {
            return "ARMOUR  WHEELED AIRBORNE";
        }
        if (code.contentEquals("UCAWW-")) {
            return "ARMOUR  WHEELED AMPHIBIOUS";
        }
        if (code.contentEquals("UCAWWR")) {
            return "ARMOUR  WHEELED AMPHIBIOUS RECOVERY";
        }
        if (code.contentEquals("UCAWL-")) {
            return "ARMOUR  WHEELED LIGHT";
        }
        if (code.contentEquals("UCAWM-")) {
            return "ARMOUR  WHEELED MEDIUM";
        }
        if (code.contentEquals("UCAWH-")) {
            return "ARMOUR  WHEELED HEAVY";
        }
        if (code.contentEquals("UCAWR-")) {
            return "ARMOUR  WHEELED RECOVERY";
        }
        if (code.contentEquals("UCAA--")) {
            return "ANTI ARMOUR";
        }
        if (code.contentEquals("UCAAD-")) {
            return "ANTI ARMOUR DISMOUNTED";
        }
        if (code.contentEquals("UCAAL-")) {
            return "ANTI ARMOUR LIGHT";
        }
        if (code.contentEquals("UCAAM-")) {
            return "ANTI ARMOUR AIRBORNE";
        }
        if (code.contentEquals("UCAAS-")) {
            return "ANTI ARMOUR AIR ASSAULT";
        }
        if (code.contentEquals("UCAAU-")) {
            return "ANTI ARMOUR MOUNTAIN";
        }
        if (code.contentEquals("UCAAC-")) {
            return "ANTI ARMOUR ARCTIC";
        }
        if (code.contentEquals("UCAAA-")) {
            return "ANTI ARMOUR ARMOURED";
        }
        if (code.contentEquals("UCAAAT")) {
            return "ANTI ARMOUR ARMOURED TRACKED";
        }
        if (code.contentEquals("UCAAAW")) {
            return "ANTI ARMOUR ARMOURED WHEELED";
        }
        if (code.contentEquals("UCAAAS")) {
            return "ANTI ARMOUR ARMOURED AIR ASSAULT";
        }
        if (code.contentEquals("UCAAO-")) {
            return "ANTI ARMOUR MOTORISED";
        }
        if (code.contentEquals("UCAAOS")) {
            return "ANTI ARMOUR MOTORISED AIR ASSAULT";
        }
        if (code.contentEquals("UCV---")) {
            return "AVIATION";
        }
        if (code.contentEquals("UCVF--")) {
            return "FIXED WING";
        }
        if (code.contentEquals("UCVFU-")) {
            return "UTILITY FIXED WING";
        }
        if (code.contentEquals("UCVFA-")) {
            return "ATTACK FIXED WING";
        }
        if (code.contentEquals("UCVFR-")) {
            return "RECCE FIXED WING";
        }
        if (code.contentEquals("UCVR--")) {
            return "ROTARY WING";
        }
        if (code.contentEquals("UCVRA-")) {
            return "ATTACK ROTARY WING";
        }
        if (code.contentEquals("UCVRS-")) {
            return "SCOUT ROTARY WING";
        }
        if (code.contentEquals("UCVRW-")) {
            return "ANTISUBMARINE WARFARE (ASW)";
        }
        if (code.contentEquals("UCVRU-")) {
            return "UTILITY ROTARY WING";
        }
        if (code.contentEquals("UCVRUL")) {
            return "LIGHT UTILITY ROTARY WING";
        }
        if (code.contentEquals("UCVRUM")) {
            return "MEDIUM UTILITY ROTARY WING";
        }
        if (code.contentEquals("UCVRUH")) {
            return "HEAVY UTILITY ROTARY WING";
        }
        if (code.contentEquals("UCVRUC")) {
            return "C² ROTARY WING";
        }
        if (code.contentEquals("UCVRUE")) {
            return "MEDEVAC ROTARY WING";
        }
        if (code.contentEquals("UCVRM-")) {
            return "MINE COUNTERMEASURES (MCM) ROTARY WING";
        }
        if (code.contentEquals("UCVS--")) {
            return "COMBAT SEARCH AND RESCUE (CSAR)";
        }
        if (code.contentEquals("UCVC--")) {
            return "COMPOSITE";
        }
        if (code.contentEquals("UCVV--")) {
            return "VERTICAL/SHORT TAKEOFF AND LANDING (V/STOL)";
        }
        if (code.contentEquals("UCVU--")) {
            return "UNMANNED AERIAL VEHICLE (UAV)";
        }
        if (code.contentEquals("UCVUF-")) {
            return "UAV FIXED WING";
        }
        if (code.contentEquals("UCVUR-")) {
            return "UAV ROTARY WING";
        }
        if (code.contentEquals("UCI---")) {
            return "INFANTRY";
        }
        if (code.contentEquals("UCIL--")) {
            return "INFANTRY LIGHT";
        }
        if (code.contentEquals("UCIM--")) {
            return "INFANTRY MOTORISED";
        }
        if (code.contentEquals("UCIO--")) {
            return "INFANTRY MOUNTAIN";
        }
        if (code.contentEquals("UCIA--")) {
            return "INFANTRY AIRBORNE";
        }
        if (code.contentEquals("UCIS--")) {
            return "INFANTRY AIR ASSAULT";
        }
        if (code.contentEquals("UCIZ--")) {
            return "INFANTRY MECHANISED";
        }
        if (code.contentEquals("UCIN--")) {
            return "INFANTRY NAVAL";
        }
        if (code.contentEquals("UCII--")) {
            return "INFANTRY FIGHTING VEHICLE";
        }
        if (code.contentEquals("UCIC--")) {
            return "INFANTRY ARCTIC";
        }
        if (code.contentEquals("UCE---")) {
            return "ENGINEER";
        }
        if (code.contentEquals("UCEC--")) {
            return "ENGINEER COMBAT";
        }
        if (code.contentEquals("UCECS-")) {
            return "ENGINEER COMBAT AIR ASSAULT";
        }
        if (code.contentEquals("UCECA-")) {
            return "ENGINEER COMBAT AIRBORNE";
        }
        if (code.contentEquals("UCECC-")) {
            return "ENGINEER COMBAT ARCTIC";
        }
        if (code.contentEquals("UCECL-")) {
            return "ENGINEER COMBAT LIGHT (SAPPER)";
        }
        if (code.contentEquals("UCECM-")) {
            return "ENGINEER COMBAT MEDIUM";
        }
        if (code.contentEquals("UCECH-")) {
            return "ENGINEER COMBAT HEAVY";
        }
        if (code.contentEquals("UCECT-")) {
            return "ENGINEER COMBAT MECHANISED";
        }
        if (code.contentEquals("UCECW-")) {
            return "ENGINEER COMBAT MOTORISED";
        }
        if (code.contentEquals("UCECO-")) {
            return "ENGINEER COMBAT MOUNTAIN";
        }
        if (code.contentEquals("UCECR-")) {
            return "ENGINEER COMBAT RECCE";
        }
        if (code.contentEquals("UCEN--")) {
            return "ENGINEER CONSTRUCTION";
        }
        if (code.contentEquals("UCENN-")) {
            return "ENGINEER NAVAL CONSTRUCTION";
        }
        if (code.contentEquals("UCF---")) {
            return "FIELD ARTILLERY";
        }
        if (code.contentEquals("UCFH--")) {
            return "HOWITZER/GUN";
        }
        if (code.contentEquals("UCFHE-")) {
            return "SELF PROPELLED";
        }
        if (code.contentEquals("UCFHS-")) {
            return "AIR ASSAULT";
        }
        if (code.contentEquals("UCFHA-")) {
            return "AIRBORNE";
        }
        if (code.contentEquals("UCFHC-")) {
            return "ARCTIC";
        }
        if (code.contentEquals("UCFHO-")) {
            return "MOUNTAIN";
        }
        if (code.contentEquals("UCFHL-")) {
            return "LIGHT";
        }
        if (code.contentEquals("UCFHM-")) {
            return "MEDIUM";
        }
        if (code.contentEquals("UCFHH-")) {
            return "HEAVY";
        }
        if (code.contentEquals("UCFHX-")) {
            return "AMPHIBIOUS";
        }
        if (code.contentEquals("UCFR--")) {
            return "ROCKET";
        }
        if (code.contentEquals("UCFRS-")) {
            return "SINGLE ROCKET LAUNCHER";
        }
        if (code.contentEquals("UCFRSS")) {
            return "SINGLE ROCKET SELF-PROPELLED";
        }
        if (code.contentEquals("UCFRSR")) {
            return "SINGLE ROCKET TRUCK";
        }
        if (code.contentEquals("UCFRST")) {
            return "SINGLE ROCKET TOWED";
        }
        if (code.contentEquals("UCFRM-")) {
            return "MULTIPLE ROCKET LAUNCHER (MRL)";
        }
        if (code.contentEquals("UCFRMS")) {
            return "MRL SELF-PROPELLED";
        }
        if (code.contentEquals("UCFRMR")) {
            return "MRL TRUCK";
        }
        if (code.contentEquals("UCFRMT")) {
            return "MRL TOWED";
        }
        if (code.contentEquals("UCFT--")) {
            return "TARGET ACQUISITION";
        }
        if (code.contentEquals("UCFTR-")) {
            return "RADAR";
        }
        if (code.contentEquals("UCFTS-")) {
            return "SOUND";
        }
        if (code.contentEquals("UCFTF-")) {
            return "FLASH (OPTICAL)";
        }
        if (code.contentEquals("UCFTC-")) {
            return "COLT/FIST";
        }
        if (code.contentEquals("UCFTCD")) {
            return "DISMOUNTED COLT/FIST";
        }
        if (code.contentEquals("UCFTCM")) {
            return "TRACKED COLT/FIST";
        }
        if (code.contentEquals("UCFTA-")) {
            return "ANGLICO";
        }
        if (code.contentEquals("UCFM--")) {
            return "MORTAR";
        }
        if (code.contentEquals("UCFMS-")) {
            return "SELF-PROPELLED (SP) TRACKED MORTAR";
        }
        if (code.contentEquals("UCFMSW")) {
            return "SP WHEELED MORTAR";
        }
        if (code.contentEquals("UCFMT-")) {
            return "TOWED MORTAR";
        }
        if (code.contentEquals("UCFMTA")) {
            return "TOWED AIRBORNE MORTAR";
        }
        if (code.contentEquals("UCFMTS")) {
            return "TOWED AIR ASSAULT MORTAR";
        }
        if (code.contentEquals("UCFMTC")) {
            return "TOWED ARCTIC MORTAR";
        }
        if (code.contentEquals("UCFMTO")) {
            return "TOWED MOUNTAIN MORTAR";
        }
        if (code.contentEquals("UCFML-")) {
            return "AMPHIBIOUS MORTAR";
        }
        if (code.contentEquals("UCFS--")) {
            return "ARTILLERY SURVEY";
        }
        if (code.contentEquals("UCFSS-")) {
            return "AIR ASSAULT";
        }
        if (code.contentEquals("UCFSA-")) {
            return "AIRBORNE";
        }
        if (code.contentEquals("UCFSL-")) {
            return "LIGHT";
        }
        if (code.contentEquals("UCFSO-")) {
            return "MOUNTAIN";
        }
        if (code.contentEquals("UCFO--")) {
            return "METEOROLOGICAL";
        }
        if (code.contentEquals("UCFOS-")) {
            return "AIR ASSAULT METEOROLOGICAL";
        }
        if (code.contentEquals("UCFOA-")) {
            return "AIRBORNE METEOROLOGICAL";
        }
        if (code.contentEquals("UCFOL-")) {
            return "LIGHT METEOROLOGICAL";
        }
        if (code.contentEquals("UCFOO-")) {
            return "MOUNTAIN METEOROLOGICAL";
        }
        if (code.contentEquals("UCR---")) {
            return "RECONNAISSANCE";
        }
        if (code.contentEquals("UCRH--")) {
            return "RECONNAISSANCE HORSE";
        }
        if (code.contentEquals("UCRV--")) {
            return "RECONNAISSANCE CAVALRY";
        }
        if (code.contentEquals("UCRVA-")) {
            return "RECONNAISSANCE CAVALRY ARMOURED";
        }
        if (code.contentEquals("UCRVM-")) {
            return "RECONNAISSANCE CAVALRY MOTORISED";
        }
        if (code.contentEquals("UCRVG-")) {
            return "RECONNAISSANCE CAVALRY GROUND";
        }
        if (code.contentEquals("UCRVO-")) {
            return "RECONNAISSANCE CAVALRY AIR";
        }
        if (code.contentEquals("UCRC--")) {
            return "RECONNAISSANCE ARCTIC";
        }
        if (code.contentEquals("UCRS--")) {
            return "RECONNAISSANCE AIR ASSAULT";
        }
        if (code.contentEquals("UCRA--")) {
            return "RECONNAISSANCE AIRBORNE";
        }
        if (code.contentEquals("UCRO--")) {
            return "RECONNAISSANCE MOUNTAIN";
        }
        if (code.contentEquals("UCRL--")) {
            return "RECONNAISSANCE LIGHT";
        }
        if (code.contentEquals("UCRR--")) {
            return "RECONNAISSANCE MARINE";
        }
        if (code.contentEquals("UCRRD-")) {
            return "RECONNAISSANCE MARINE DIVISION";
        }
        if (code.contentEquals("UCRRF-")) {
            return "RECONNAISSANCE MARINE FORCE";
        }
        if (code.contentEquals("UCRRL-")) {
            return "RECONNAISSANCE MARINE LIGHT ARMOURED RECONNAISSANCE (LAR)";
        }
        if (code.contentEquals("UCRX--")) {
            return "RECONNAISSANCE LONG RANGE SURVEILLANCE (LRS)";
        }
        if (code.contentEquals("UCM---")) {
            return "MISSILE (SURF-SURF)";
        }
        if (code.contentEquals("UCMT--")) {
            return "MISSILE (SURF-SURF) TACTICAL";
        }
        if (code.contentEquals("UCMS--")) {
            return "MISSILE (SURF-SURF) STRATEGIC";
        }
        if (code.contentEquals("UCS---")) {
            return "INTERNAL SECURITY FORCES";
        }
        if (code.contentEquals("UCSW--")) {
            return "RIVERINE";
        }
        if (code.contentEquals("UCSG--")) {
            return "GROUND";
        }
        if (code.contentEquals("UCSGD-")) {
            return "DISMOUNTED GROUND";
        }
        if (code.contentEquals("UCSGM-")) {
            return "MOTORISED GROUND";
        }
        if (code.contentEquals("UCSGA-")) {
            return "MECHANISED GROUND";
        }
        if (code.contentEquals("UCSM--")) {
            return "WHEELED MECHANISED";
        }
        if (code.contentEquals("UCSR--")) {
            return "RAILROAD";
        }
        if (code.contentEquals("UCSA--")) {
            return "AVIATION";
        }
        if (code.contentEquals("UU----")) {
            return "COMBAT SUPPORT";
        }
        if (code.contentEquals("UUA---")) {
            return "COMBAT SUPPORT NBC";
        }
        if (code.contentEquals("UUAC--")) {
            return "CHEMICAL";
        }
        if (code.contentEquals("UUACC-")) {
            return "SMOKE/DECON";
        }
        if (code.contentEquals("UUACCK")) {
            return "MECHANISED SMOKE/DECON";
        }
        if (code.contentEquals("UUACCM")) {
            return "MOTORISED SMOKE/DECON";
        }
        if (code.contentEquals("UUACS-")) {
            return "SMOKE";
        }
        if (code.contentEquals("UUACSM")) {
            return "MOTORISED SMOKE";
        }
        if (code.contentEquals("UUACSA")) {
            return "ARMOUR SMOKE";
        }
        if (code.contentEquals("UUACR-")) {
            return "CHEMICAL RECCE";
        }
        if (code.contentEquals("UUACRW")) {
            return "CHEMICAL WHEELED ARMOURED";
        }
        if (code.contentEquals("UUAN--")) {
            return "NUCLEAR";
        }
        if (code.contentEquals("UUAB--")) {
            return "BIOLOGICAL";
        }
        if (code.contentEquals("UUABR-")) {
            return "RECCE EQUIPPED";
        }
        if (code.contentEquals("UUAD--")) {
            return "DECONTAMINATION";
        }
        if (code.contentEquals("UUM---")) {
            return "MILITARY INTELLIGENCE";
        }
        if (code.contentEquals("UUMA--")) {
            return "AERIAL EXPLOITATION";
        }
        if (code.contentEquals("UUMS--")) {
            return "SIGNAL INTELLIGENCE (SIGINT)";
        }
        if (code.contentEquals("UUMSE-")) {
            return "ELECTRONIC WARFARE (EW)";
        }
        if (code.contentEquals("UUMSEA")) {
            return "ARMOURED WHEELED VEHICLE";
        }
        if (code.contentEquals("UUMSED")) {
            return "DIRECTION FINDING";
        }
        if (code.contentEquals("UUMSEI")) {
            return "INTERCEPT";
        }
        if (code.contentEquals("UUMSEJ")) {
            return "JAMMING";
        }
        if (code.contentEquals("UUMSET")) {
            return "THEATRE";
        }
        if (code.contentEquals("UUMSEC")) {
            return "CORPS";
        }
        if (code.contentEquals("UUMC--")) {
            return "COUNTER INTELLIGENCE";
        }
        if (code.contentEquals("UUMR--")) {
            return "SURVEILLANCE";
        }
        if (code.contentEquals("UUMRG-")) {
            return "GROUND SURVEILLANCE RADAR";
        }
        if (code.contentEquals("UUMRS-")) {
            return "SENSOR";
        }
        if (code.contentEquals("UUMRSS")) {
            return "SURFACE CONTAMINATION MODULE (SCM)";
        }
        if (code.contentEquals("UUMRX-")) {
            return "GROUND STATION MODULE";
        }
        if (code.contentEquals("UUMMO-")) {
            return "METEOROLOGICAL";
        }
        if (code.contentEquals("UUMO--")) {
            return "OPERATIONS";
        }
        if (code.contentEquals("UUMT--")) {
            return "TACTICAL EXPLOIT";
        }
        if (code.contentEquals("UUMQ--")) {
            return "INTERROGATION";
        }
        if (code.contentEquals("UUMJ--")) {
            return "JOINT INTELLIGENCE CENTRE (JIC)";
        }
        if (code.contentEquals("UUL---")) {
            return "LAW ENFORCEMENT UNIT";
        }
        if (code.contentEquals("UULS--")) {
            return "SHORE PATROL";
        }
        if (code.contentEquals("UULM--")) {
            return "MILITARY POLICE";
        }
        if (code.contentEquals("UULC--")) {
            return "CIVILIAN LAW ENFORCEMENT";
        }
        if (code.contentEquals("UULF--")) {
            return "SECURITY POLICE (AIR)";
        }
        if (code.contentEquals("UULD--")) {
            return "CRIMINAL INVESTIGATION DIVISION (CID)";
        }
        if (code.contentEquals("UUSO--")) {
            return "COMMAND OPERATIONS";
        }
        if (code.contentEquals("UUSF--")) {
            return "FORWARD COMMUNICATIONS";
        }
        if (code.contentEquals("UUSM--")) {
            return "MULTIPLE SUBSCRIBER ELEMENT";
        }
        if (code.contentEquals("UUSMS-")) {
            return "SMALL EXTENSION NODE";
        }
        if (code.contentEquals("UUSML-")) {
            return "LARGE EXTENSION NODE";
        }
        if (code.contentEquals("UUSMN-")) {
            return "NODE CENTRE";
        }
        if (code.contentEquals("UUSR--")) {
            return "RADIO UNIT";
        }
        if (code.contentEquals("UUSRS-")) {
            return "TACTICAL SATELLITE";
        }
        if (code.contentEquals("UUSRT-")) {
            return "TELETYPE CENTRE";
        }
        if (code.contentEquals("UUSRW-")) {
            return "RELAY";
        }
        if (code.contentEquals("UUSS--")) {
            return "SIGNAL SUPPORT";
        }
        if (code.contentEquals("UUSW--")) {
            return "TELEPHONE SWITCH";
        }
        if (code.contentEquals("UUSX--")) {
            return "ELECTRONIC RANGING";
        }
        if (code.contentEquals("UUI---")) {
            return "INFORMATION WARFARE UNIT";
        }
        if (code.contentEquals("UUX---")) {
            return "LANDING SUPPORT";
        }
        if (code.contentEquals("UUE---")) {
            return "EXPLOSIVE ORDNANCE DISPOSAL";
        }
        if (code.contentEquals("US----")) {
            return "COMBAT SERVICE SUPPORT";
        }
        if (code.contentEquals("USA---")) {
            return "ADMINISTRATIVE (ADMIN)";
        }
        if (code.contentEquals("USAT--")) {
            return "ADMIN THEATRE";
        }
        if (code.contentEquals("USAC--")) {
            return "ADMIN CORPS";
        }
        if (code.contentEquals("USAJ--")) {
            return "JUDGE ADVOCATE GENERAL (JAG)";
        }
        if (code.contentEquals("USAJT-")) {
            return "JAG THEATRE";
        }
        if (code.contentEquals("USAJC-")) {
            return "JAG CORPS";
        }
        if (code.contentEquals("USAO--")) {
            return "POSTAL";
        }
        if (code.contentEquals("USAOT-")) {
            return "POSTAL THEATRE";
        }
        if (code.contentEquals("USAOC-")) {
            return "POSTAL CORPS";
        }
        if (code.contentEquals("USAF--")) {
            return "FINANCE";
        }
        if (code.contentEquals("USAFT-")) {
            return "FINANCE THEATRE";
        }
        if (code.contentEquals("USAFC-")) {
            return "FINANCE CORPS";
        }
        if (code.contentEquals("USAS--")) {
            return "PERSONNEL SERVICES";
        }
        if (code.contentEquals("USAST-")) {
            return "PERSONNEL THEATRE";
        }
        if (code.contentEquals("USASC-")) {
            return "PERSONNEL CORPS";
        }
        if (code.contentEquals("USAM--")) {
            return "MORTUARY/GRAVES REGISTRY";
        }
        if (code.contentEquals("USAMT-")) {
            return "MORTUARY/GRAVES REGISTRY THEATRE";
        }
        if (code.contentEquals("USAMC-")) {
            return "MORTUARY/GRAVES REGISTRY CORPS";
        }
        if (code.contentEquals("USAR--")) {
            return "RELIGIOUS/CHAPLAIN";
        }
        if (code.contentEquals("USART-")) {
            return "RELIGIOUS/CHAPLAIN THEATRE";
        }
        if (code.contentEquals("USARC-")) {
            return "RELIGIOUS/CHAPLAIN CORPS";
        }
        if (code.contentEquals("USAP--")) {
            return "PUBLIC AFFAIRS";
        }
        if (code.contentEquals("USAPT-")) {
            return "PUBLIC AFFAIRS THEATRE";
        }
        if (code.contentEquals("USAPC-")) {
            return "PUBLIC AFFAIRS CORPS";
        }
        if (code.contentEquals("USAPB-")) {
            return "PUBLIC AFFAIRS BROADCAST";
        }
        if (code.contentEquals("USAPBT")) {
            return "PUBLIC AFFAIRS BROADCAST THEATRE";
        }
        if (code.contentEquals("USAPBC")) {
            return "PUBLIC AFFAIRS BROADCAST CORPS";
        }
        if (code.contentEquals("USAPM-")) {
            return "PUBLIC AFFAIRS JOINT INFORMATION BUREAU (JIB)";
        }
        if (code.contentEquals("USAPMT")) {
            return "PUBLIC AFFAIRS JIB THEATRE";
        }
        if (code.contentEquals("USAPMC")) {
            return "PUBLIC AFFAIRS JIB CORPS";
        }
        if (code.contentEquals("USAX--")) {
            return "REPLACEMENT HOLDING UNIT (RHU)";
        }
        if (code.contentEquals("USAXT-")) {
            return "RHU THEATRE";
        }
        if (code.contentEquals("USAXC-")) {
            return "RHU CORPS";
        }
        if (code.contentEquals("USAL--")) {
            return "LABOUR";
        }
        if (code.contentEquals("USALT-")) {
            return "LABOUR THEATRE";
        }
        if (code.contentEquals("USALC-")) {
            return "LABOUR CORPS";
        }
        if (code.contentEquals("USAW--")) {
            return "MORALE  WELFARE  RECREATION (MWR)";
        }
        if (code.contentEquals("USAWT-")) {
            return "MWR THEATRE";
        }
        if (code.contentEquals("USAWC-")) {
            return "MWR CORPS";
        }
        if (code.contentEquals("USAQ--")) {
            return "QUARTERMASTER (SUPPLY)";
        }
        if (code.contentEquals("USAQT-")) {
            return "QUARTERMASTER (SUPPLY) THEATRE";
        }
        if (code.contentEquals("USAQC-")) {
            return "QUARTERMASTER (SUPPLY) CORPS";
        }
        if (code.contentEquals("USM---")) {
            return "MEDICAL";
        }
        if (code.contentEquals("USMT--")) {
            return "MEDICAL THEATRE";
        }
        if (code.contentEquals("USMC--")) {
            return "MEDICAL CORPS";
        }
        if (code.contentEquals("USMM--")) {
            return "MEDICAL TREATMENT FACILITY";
        }
        if (code.contentEquals("USMMT-")) {
            return "MEDICAL TREATMENT FACILITY THEATRE";
        }
        if (code.contentEquals("USMMC-")) {
            return "MEDICAL TREATMENT FACILITY CORPS";
        }
        if (code.contentEquals("USMV--")) {
            return "MEDICAL VETERINARY";
        }
        if (code.contentEquals("USMVT-")) {
            return "MEDICAL VETERINARY THEATRE";
        }
        if (code.contentEquals("USMVC-")) {
            return "MEDICAL VETERINARY CORPS";
        }
        if (code.contentEquals("USMD--")) {
            return "MEDICAL DENTAL";
        }
        if (code.contentEquals("USMDT-")) {
            return "MEDICAL DENTAL THEATRE";
        }
        if (code.contentEquals("USMDC-")) {
            return "MEDICAL DENTAL CORPS";
        }
        if (code.contentEquals("USMP--")) {
            return "MEDICAL PSYCHOLOGICAL";
        }
        if (code.contentEquals("USMPT-")) {
            return "MEDICAL PSYCHOLOGICAL THEATRE";
        }
        if (code.contentEquals("USMPC-")) {
            return "MEDICAL PSYCHOLOGICAL CORPS";
        }
        if (code.contentEquals("USS---")) {
            return "SUPPLY";
        }
        if (code.contentEquals("USST--")) {
            return "SUPPLY THEATRE";
        }
        if (code.contentEquals("USSC--")) {
            return "SUPPLY CORPS";
        }
        if (code.contentEquals("USS1--")) {
            return "SUPPLY CLASS I";
        }
        if (code.contentEquals("USS1T-")) {
            return "SUPPLY CLASS I THEATRE";
        }
        if (code.contentEquals("USS1C-")) {
            return "SUPPLY CLASS I CORPS";
        }
        if (code.contentEquals("USS2--")) {
            return "SUPPLY CLASS II";
        }
        if (code.contentEquals("USS2T-")) {
            return "SUPPLY CLASS II THEATRE";
        }
        if (code.contentEquals("USS2C-")) {
            return "SUPPLY CLASS II CORPS";
        }
        if (code.contentEquals("USS3--")) {
            return "SUPPLY CLASS III";
        }
        if (code.contentEquals("USS3T-")) {
            return "SUPPLY CLASS III THEATRE";
        }
        if (code.contentEquals("USS3C-")) {
            return "SUPPLY CLASS III CORPS";
        }
        if (code.contentEquals("USS3A-")) {
            return "SUPPLY CLASS III AVIATION";
        }
        if (code.contentEquals("USS3AT")) {
            return "SUPPLY CLASS III AVIATION THEATRE";
        }
        if (code.contentEquals("USS3AC")) {
            return "SUPPLY CLASS III AVIATION CORPS";
        }
        if (code.contentEquals("USS4--")) {
            return "SUPPLY CLASS IV";
        }
        if (code.contentEquals("USS4T-")) {
            return "SUPPLY CLASS IV THEATRE";
        }
        if (code.contentEquals("USS4C-")) {
            return "SUPPLY CLASS IV CORPS";
        }
        if (code.contentEquals("USS5--")) {
            return "SUPPLY CLASS V";
        }
        if (code.contentEquals("USS5T-")) {
            return "SUPPLY CLASS V THEATRE";
        }
        if (code.contentEquals("USS5C-")) {
            return "SUPPLY CLASS V CORPS";
        }
        if (code.contentEquals("USS6--")) {
            return "SUPPLY CLASS VI";
        }
        if (code.contentEquals("USS6T-")) {
            return "SUPPLY CLASS VI THEATRE";
        }
        if (code.contentEquals("USS6C-")) {
            return "SUPPLY CLASS VI CORPS";
        }
        if (code.contentEquals("USS7--")) {
            return "SUPPLY CLASS VII";
        }
        if (code.contentEquals("USS7T-")) {
            return "SUPPLY CLASS VII THEATRE";
        }
        if (code.contentEquals("USS7C-")) {
            return "SUPPLY CLASS VII CORPS";
        }
        if (code.contentEquals("USS8--")) {
            return "SUPPLY CLASS VIII";
        }
        if (code.contentEquals("USS8T-")) {
            return "SUPPLY CLASS VIII THEATRE";
        }
        if (code.contentEquals("USS8C-")) {
            return "SUPPLY CLASS VIII CORPS";
        }
        if (code.contentEquals("USS9--")) {
            return "SUPPLY CLASS IX";
        }
        if (code.contentEquals("USS9T-")) {
            return "SUPPLY CLASS IX THEATRE";
        }
        if (code.contentEquals("USS9C-")) {
            return "SUPPLY CLASS IX CORPS";
        }
        if (code.contentEquals("USSX--")) {
            return "SUPPLY CLASS X";
        }
        if (code.contentEquals("USSXT-")) {
            return "SUPPLY CLASS X THEATRE";
        }
        if (code.contentEquals("USSXC-")) {
            return "SUPPLY CLASS X CORPS";
        }
        if (code.contentEquals("USSL--")) {
            return "SUPPLY LAUNDRY/BATH";
        }
        if (code.contentEquals("USSLT-")) {
            return "SUPPLY LAUNDRY/BATH THEATRE";
        }
        if (code.contentEquals("USSLC-")) {
            return "SUPPLY LAUNDRY/BATH CORPS";
        }
        if (code.contentEquals("USSW--")) {
            return "SUPPLY WATER";
        }
        if (code.contentEquals("USSWT-")) {
            return "SUPPLY WATER THEATRE";
        }
        if (code.contentEquals("USSWC-")) {
            return "SUPPLY WATER CORPS";
        }
        if (code.contentEquals("USSWP-")) {
            return "SUPPLY WATER PURIFICATION";
        }
        if (code.contentEquals("USSWPT")) {
            return "SUPPLY WATER PURIFICATION THEATRE";
        }
        if (code.contentEquals("USSWPC")) {
            return "SUPPLY WATER PURIFICATION CORPS";
        }
        if (code.contentEquals("UST---")) {
            return "TRANSPORTATION";
        }
        if (code.contentEquals("USTT--")) {
            return "TRANSPORTATION THEATRE";
        }
        if (code.contentEquals("USTC--")) {
            return "TRANSPORTATION CORPS";
        }
        if (code.contentEquals("USTM--")) {
            return "MOVEMENT CONTROL CENTRE (MCC)";
        }
        if (code.contentEquals("USTMT-")) {
            return "MCC THEATRE";
        }
        if (code.contentEquals("USTMC-")) {
            return "MCC CORPS";
        }
        if (code.contentEquals("USTR--")) {
            return "RAILHEAD";
        }
        if (code.contentEquals("USTRT-")) {
            return "RAILHEAD THEATRE";
        }
        if (code.contentEquals("USTRC-")) {
            return "RAILHEAD CORPS";
        }
        if (code.contentEquals("USTS--")) {
            return "SPOD/SPOE";
        }
        if (code.contentEquals("USTST-")) {
            return "SPOD/SPOE THEATRE";
        }
        if (code.contentEquals("USTSC-")) {
            return "SPOD/SPOE CORPS";
        }
        if (code.contentEquals("USTA--")) {
            return "APOD/APOE";
        }
        if (code.contentEquals("USTAT-")) {
            return "APOD/APOE THEATRE";
        }
        if (code.contentEquals("USTAC-")) {
            return "APOD/APOE CORPS";
        }
        if (code.contentEquals("USTI--")) {
            return "MISSILE";
        }
        if (code.contentEquals("USTIT-")) {
            return "MISSILE THEATRE";
        }
        if (code.contentEquals("USTIC-")) {
            return "MISSILE CORPS";
        }
        if (code.contentEquals("USX---")) {
            return "MAINTENANCE";
        }
        if (code.contentEquals("USXT--")) {
            return "MAINTENANCE THEATRE";
        }
        if (code.contentEquals("USXC--")) {
            return "MAINTENANCE CORPS";
        }
        if (code.contentEquals("USXH--")) {
            return "MAINTENANCE HEAVY";
        }
        if (code.contentEquals("USXHT-")) {
            return "MAINTENANCE HEAVY THEATRE";
        }
        if (code.contentEquals("USXHC-")) {
            return "MAINTENANCE HEAVY CORPS";
        }
        if (code.contentEquals("USXR--")) {
            return "MAINTENANCE RECOVERY";
        }
        if (code.contentEquals("USXRT-")) {
            return "MAINTENANCE RECOVERY THEATRE";
        }
        if (code.contentEquals("USXRC-")) {
            return "MAINTENANCE RECOVERY CORPS";
        }
        if (code.contentEquals("USXO--")) {
            return "ORDNANCE";
        }
        if (code.contentEquals("USXOT-")) {
            return "ORDNANCE THEATRE";
        }
        if (code.contentEquals("USXOC-")) {
            return "ORDNANCE CORPS";
        }
        if (code.contentEquals("USXOM-")) {
            return "ORDNANCE MISSILE";
        }
        if (code.contentEquals("USXOMT")) {
            return "ORDNANCE MISSILE THEATRE";
        }
        if (code.contentEquals("USXOMC")) {
            return "ORDNANCE MISSILE CORPS";
        }
        if (code.contentEquals("USXE--")) {
            return "ELECTRO-OPTICAL";
        }
        if (code.contentEquals("USXET-")) {
            return "ELECTRO-OPTICAL THEATRE";
        }
        if (code.contentEquals("USXEC-")) {
            return "ELECTRO-OPTICAL CORPS";
        }
        if (code.contentEquals("UH----")) {
            return "SPECIAL C² HEADQUARTERS COMPONENT";
        }
        if (code.contentEquals("E-----")) {
            return "GROUND TRACK EQUIPMENT";
        }
        if (code.contentEquals("EW----")) {
            return "WEAPON";
        }
        if (code.contentEquals("EWM---")) {
            return "MISSILE LAUNCHER";
        }
        if (code.contentEquals("EWMA--")) {
            return "AIR DEFENCE (AD) MISSILE LAUNCHER";
        }
        if (code.contentEquals("EWMAS-")) {
            return "SHORT RANGE AD MISSILE LAUNCHER";
        }
        if (code.contentEquals("EWMAI-")) {
            return "INTERMEDIATE RANGE AD MISSILE LAUNCHER";
        }
        if (code.contentEquals("EWMAL-")) {
            return "LONG RANGE AD MISSILE LAUNCHER";
        }
        if (code.contentEquals("EWMAT-")) {
            return "AD MISSILE LAUNCHER THEATRE";
        }
        if (code.contentEquals("EWMS--")) {
            return "SURF-SURF (SS) MISSILE LAUNCHER";
        }
        if (code.contentEquals("EWMSS-")) {
            return "SHORT RANGE SS MISSILE LAUNCHER76";
        }
        if (code.contentEquals("EWMSI-")) {
            return "INTERMEDIATE RANGE SS MISSILE LAUNCHER";
        }
        if (code.contentEquals("EWMSL-")) {
            return "LONG RANGE SS MISSILE LAUNCHER";
        }
        if (code.contentEquals("EWMT--")) {
            return "MISSILE LAUNCHER ANTI TANK (AT)";
        }
        if (code.contentEquals("EWMTL-")) {
            return "MISSILE LAUNCHER AT LIGHT";
        }
        if (code.contentEquals("EWMTM-")) {
            return "MISSILE LAUNCHER AT MEDIUM";
        }
        if (code.contentEquals("EWMTH-")) {
            return "MISSILE LAUNCHER AT HEAVY";
        }
        if (code.contentEquals("EWS---")) {
            return "SINGLE ROCKET LAUNCHER";
        }
        if (code.contentEquals("EWSL--")) {
            return "SINGLE ROCKET LAUNCHER LIGHT";
        }
        if (code.contentEquals("EWSM--")) {
            return "SINGLE ROCKET LAUNCHER MEDIUM";
        }
        if (code.contentEquals("EWSH--")) {
            return "SINGLE ROCKET LAUNCHER HEAVY";
        }
        if (code.contentEquals("EWX---")) {
            return "MULTIPLE ROCKET LAUNCHER";
        }
        if (code.contentEquals("EWXL--")) {
            return "MULTIPLE ROCKET LAUNCHER LIGHT";
        }
        if (code.contentEquals("EWXM--")) {
            return "MULTIPLE ROCKET LAUNCHER MEDIUM";
        }
        if (code.contentEquals("EWXH--")) {
            return "MULTIPLE ROCKET LAUNCHER HEAVY";
        }
        if (code.contentEquals("EWT---")) {
            return "ANTITANK ROCKET LAUNCHER";
        }
        if (code.contentEquals("EWTL--")) {
            return "ANTITANK ROCKET LAUNCHER LIGHT";
        }
        if (code.contentEquals("EWTM--")) {
            return "ANTITANK ROCKET LAUNCHER MEDIUM";
        }
        if (code.contentEquals("EWTH--")) {
            return "ANTITANK ROCKET LAUNCHER HEAVY";
        }
        if (code.contentEquals("EWR---")) {
            return "RIFLE/AUTOMATIC WEAPON";
        }
        if (code.contentEquals("EWRR--")) {
            return "RIFLE";
        }
        if (code.contentEquals("EWRL--")) {
            return "LIGHT MACHINE GUN";
        }
        if (code.contentEquals("EWRH--")) {
            return "HEAVY MACHINE GUN";
        }
        if (code.contentEquals("EWZ---")) {
            return "GRENADE LAUNCHER";
        }
        if (code.contentEquals("EWZL--")) {
            return "GRENADE LAUNCHER LIGHT";
        }
        if (code.contentEquals("EWZM--")) {
            return "GRENADE LAUNCHER MEDIUM";
        }
        if (code.contentEquals("EWZH--")) {
            return "GRENADE LAUNCHER HEAVY";
        }
        if (code.contentEquals("EWO---")) {
            return "MORTAR";
        }
        if (code.contentEquals("EWOL--")) {
            return "MORTAR LIGHT";
        }
        if (code.contentEquals("EWOM--")) {
            return "MORTAR MEDIUM";
        }
        if (code.contentEquals("EWOH--")) {
            return "MORTAR HEAVY";
        }
        if (code.contentEquals("EWH---")) {
            return "HOWITZER";
        }
        if (code.contentEquals("EWHL--")) {
            return "HOWITZER LIGHT";
        }
        if (code.contentEquals("EWHLS-")) {
            return "HOWITZER LIGHT SELF-PROPELLED";
        }
        if (code.contentEquals("EWHM--")) {
            return "HOWITZER MEDIUM";
        }
        if (code.contentEquals("EWHMS-")) {
            return "HOWITZER MEDIUM SELF-PROPELLED";
        }
        if (code.contentEquals("EWHH--")) {
            return "HOWITZER HEAVY";
        }
        if (code.contentEquals("EWHHS-")) {
            return "HOWITZER HEAVY SELF-PROPELLED";
        }
        if (code.contentEquals("EWG---")) {
            return "ANTITANK GUN";
        }
        if (code.contentEquals("EWGL--")) {
            return "ANTITANK GUN LIGHT77";
        }
        if (code.contentEquals("EWGM--")) {
            return "ANTITANK GUN MEDIUM";
        }
        if (code.contentEquals("EWGH--")) {
            return "ANTITANK GUN HEAVY";
        }
        if (code.contentEquals("EWGR--")) {
            return "ANTITANK GUN RECOILLESS";
        }
        if (code.contentEquals("EWD---")) {
            return "DIRECT FIRE GUN";
        }
        if (code.contentEquals("EWDL--")) {
            return "DIRECT FIRE GUN LIGHT";
        }
        if (code.contentEquals("EWDLS-")) {
            return "DIRECT FIRE GUN LIGHT SELF-PROPELLED";
        }
        if (code.contentEquals("EWDM--")) {
            return "DIRECT FIRE GUN MEDIUM";
        }
        if (code.contentEquals("EWDMS-")) {
            return "DIRECT FIRE GUN MEDIUM SELFPROPELLED";
        }
        if (code.contentEquals("EWDH--")) {
            return "DIRECT FIRE GUN HEAVY";
        }
        if (code.contentEquals("EWDHS-")) {
            return "DIRECT FIRE GUN HEAVY SELFPROPELLED";
        }
        if (code.contentEquals("EWA---")) {
            return "AIR DEFENCE GUN";
        }
        if (code.contentEquals("EWAL--")) {
            return "AIR DEFENCE GUN LIGHT";
        }
        if (code.contentEquals("EWAM--")) {
            return "AIR DEFENCE GUN MEDIUM";
        }
        if (code.contentEquals("EWAH--")) {
            return "AIR DEFENCE GUN HEAVY";
        }
        if (code.contentEquals("EV----")) {
            return "GROUND VEHICLE";
        }
        if (code.contentEquals("EVA---")) {
            return "ARMOURED VEHICLE";
        }
        if (code.contentEquals("EVAT--")) {
            return "TANK";
        }
        if (code.contentEquals("EVATL-")) {
            return "TANK LIGHT";
        }
        if (code.contentEquals("EVATW-")) {
            return "TANK LIGHT RECOVERY";
        }
        if (code.contentEquals("EVATM-")) {
            return "TANK MEDIUM";
        }
        if (code.contentEquals("EVATX-")) {
            return "TANK MEDIUM RECOVERY";
        }
        if (code.contentEquals("EVATH-")) {
            return "TANK HEAVY";
        }
        if (code.contentEquals("EVATY-")) {
            return "TANK HEAVY RECOVERY";
        }
        if (code.contentEquals("EVAA--")) {
            return "ARMOURED PERSONNEL CARRIER (APC)";
        }
        if (code.contentEquals("EVAAR-")) {
            return "APC RECOVERY";
        }
        if (code.contentEquals("EVAI--")) {
            return "ARMOURED INFANTRY";
        }
        if (code.contentEquals("EVAC--")) {
            return "C²V/ACV";
        }
        if (code.contentEquals("EVAS--")) {
            return "COMBAT SERVICE SUPPORT VEHICLE";
        }
        if (code.contentEquals("EVAL--")) {
            return "LIGHT ARMOURED VEHICLE (LAV)";
        }
        if (code.contentEquals("EVU---")) {
            return "UTILITY VEHICLE";
        }
        if (code.contentEquals("EVUB--")) {
            return "BUS";
        }
        if (code.contentEquals("EVUS--")) {
            return "SEMI";
        }
        if (code.contentEquals("EVUL--")) {
            return "LIMITED CROSS-COUNTRY TRUCK";
        }
        if (code.contentEquals("EVUX--")) {
            return "CROSS-COUNTRY TRUCK";
        }
        if (code.contentEquals("EVUR--")) {
            return "WATER CRAFT";
        }
        if (code.contentEquals("EVE---")) {
            return "ENGINEER VEHICLE";
        }
        if (code.contentEquals("EVEB--")) {
            return "BRIDGE";
        }
        if (code.contentEquals("EVEE--")) {
            return "EARTHMOVER";
        }
        if (code.contentEquals("EVEC--")) {
            return "CONSTRUCTION VEHICLE";
        }
        if (code.contentEquals("EVEM--")) {
            return "MINE LAYING VEHICLE";
        }
        if (code.contentEquals("EVEMA-")) {
            return "ARMOURED VEHICLE MOUNTED";
        }
        if (code.contentEquals("EVEMT-")) {
            return "TRAILER MOUNTED";
        }
        if (code.contentEquals("EVEMV-")) {
            return "ARMOURED CARRIER WITH VOLCANO";
        }
        if (code.contentEquals("EVEML-")) {
            return "TRUCK MOUNTED WITH VOLCANO";
        }
        if (code.contentEquals("EVED--")) {
            return "DOZER";
        }
        if (code.contentEquals("EVST--")) {
            return "TRAIN LOCOMOTIVE80";
        }
        if (code.contentEquals("EVC---")) {
            return "CIVILIAN VEHICLE";
        }
        if (code.contentEquals("ES----")) {
            return "SENSOR";
        }
        if (code.contentEquals("ESR---")) {
            return "RADAR";
        }
        if (code.contentEquals("ESE---")) {
            return "EMPLACED SENSOR";
        }
        if (code.contentEquals("EX----")) {
            return "SPECIAL EQUIPMENT";
        }
        if (code.contentEquals("EXL---")) {
            return "LASER";
        }
        if (code.contentEquals("EXN---")) {
            return "NBC EQUIPMENT";
        }
        if (code.contentEquals("EXF---")) {
            return "FLAME THROWER";
        }
        if (code.contentEquals("EXM---")) {
            return "LAND MINE";
        }
        if (code.contentEquals("EXMC--")) {
            return "CLAYMORE";
        }
        if (code.contentEquals("EXML--")) {
            return "LESS THAN LETHAL";
        }
        if (code.contentEquals("I-----")) {
            return "INSTALLATION";
        }
        if (code.contentEquals("IR----")) {
            return "RAW MATERIAL PRODUCTION/STORAGE";
        }
        if (code.contentEquals("IRM---")) {
            return "MINE";
        }
        if (code.contentEquals("IRP---")) {
            return "PETROLEUM/GAS/OIL";
        }
        if (code.contentEquals("IRN---")) {
            return "NBC";
        }
        if (code.contentEquals("IRNB--")) {
            return "BIOLOGICAL";
        }
        if (code.contentEquals("IRNC--")) {
            return "CHEMICAL";
        }
        if (code.contentEquals("IRNN--")) {
            return "NUCLEAR";
        }
        if (code.contentEquals("IP----")) {
            return "PROCESSING FACILITY";
        }
        if (code.contentEquals("IPD---")) {
            return "DECON";
        }
        if (code.contentEquals("IE----")) {
            return "EQUIPMENT MANUFACTURE";
        }
        if (code.contentEquals("IU----")) {
            return "SERVICE  RESEARCH  UTILITY FACILITY";
        }
        if (code.contentEquals("IUR---")) {
            return "TECHNOLOGICAL RESEARCH FACILITY";
        }
        if (code.contentEquals("IUT---")) {
            return "TELECOMMUNICATIONS FACILITY";
        }
        if (code.contentEquals("IUE---")) {
            return "ELECTRIC POWER FACILITY";
        }
        if (code.contentEquals("IUP---")) {
            return "PUBLIC WATER SERVICES";
        }
        if (code.contentEquals("IM----")) {
            return "MILITARY MATERIEL FACILITY";
        }
        if (code.contentEquals("IMF---")) {
            return "ATOMIC ENERGY PRODUCTION";
        }
        if (code.contentEquals("IMA---")) {
            return "AIRCRAFT PRODUCTION & ASSEMBLY";
        }
        if (code.contentEquals("IME---")) {
            return "AMMUNITION AND EXPLOSIVES PRODUCTION";
        }
        if (code.contentEquals("IMG---")) {
            return "ARMAMENT PRODUCTION";
        }
        if (code.contentEquals("IMV---")) {
            return "MILITARY VEHICLE PRODUCTION";
        }
        if (code.contentEquals("IMN---")) {
            return "ENGINEERING EQUIPMENT PRODUCTION";
        }
        if (code.contentEquals("IMNB--")) {
            return "BRIDGE";
        }
        if (code.contentEquals("IMC---")) {
            return "CHEMICAL & BIOLOGICAL WARFARE PRODUCTION";
        }
        if (code.contentEquals("IMS---")) {
            return "SHIP CONSTRUCTION";
        }
        if (code.contentEquals("IMM---")) {
            return "MISSILE & SPACE SYSTEM PRODUCTION";
        }
        if (code.contentEquals("IG----")) {
            return "GOVERNMENT LEADERSHIP";
        }
        if (code.contentEquals("IB----")) {
            return "MILITARY BASE/FACILITY";
        }
        if (code.contentEquals("IBA---")) {
            return "AIRPORT/AIRBASE";
        }
        if (code.contentEquals("IBN---")) {
            return "SEAPORT/NAVAL BASE";
        }
        if (code.contentEquals("IT----")) {
            return "TRANSPORT FACILITY";
        }
        if (code.contentEquals("IX----")) {
            return "MEDICAL FACILITY";
        }
        if (code.contentEquals("IXH---")) {
            return "HOSPITAL";
        }
        if (code.contentEquals("------")) {
            return "SEA SURFACE TRACK";
        }
        if (code.contentEquals("C-----")) {
            return "COMBATANT";
        }
        if (code.contentEquals("CL----")) {
            return "LINE";
        }
        if (code.contentEquals("CLCV--")) {
            return "CARRIER";
        }
        if (code.contentEquals("CLBB--")) {
            return "BATTLESHIP";
        }
        if (code.contentEquals("CLCC--")) {
            return "CRUISER";
        }
        if (code.contentEquals("CLDD--")) {
            return "DESTROYER";
        }
        if (code.contentEquals("CLFF--")) {
            return "FRIGATE/CORVETTE";
        }
        if (code.contentEquals("CA----")) {
            return "AMPHIBIOUS WARFARE SHIP";
        }
        if (code.contentEquals("CALA--")) {
            return "ASSAULT VESSEL";
        }
        if (code.contentEquals("CALS--")) {
            return "LANDING SHIP";
        }
        if (code.contentEquals("CALC--")) {
            return "LANDING CRAFT";
        }
        if (code.contentEquals("CM----")) {
            return "MINE WARFARE VESSEL";
        }
        if (code.contentEquals("CMML--")) {
            return "MINELAYER";
        }
        if (code.contentEquals("CMMS--")) {
            return "MINESWEEPER";
        }
        if (code.contentEquals("CMMH--")) {
            return "MINEHUNTER";
        }
        if (code.contentEquals("CMMA--")) {
            return "MINE COUNTERMEASURES (MCM)";
        }
        if (code.contentEquals("CMMD--")) {
            return "MCM DRONE";
        }
        if (code.contentEquals("CP----")) {
            return "PATROL";
        }
        if (code.contentEquals("CPSB--")) {
            return "ANTISUBMARINE WARFARE (ASW)";
        }
        if (code.contentEquals("CPSU--")) {
            return "ANTISURFACE WARFARE (ASUW)";
        }
        if (code.contentEquals("CH----")) {
            return "HOVERCRAFT";
        }
        if (code.contentEquals("S-----")) {
            return "STATION";
        }
        if (code.contentEquals("SP----")) {
            return "PICKET";
        }
        if (code.contentEquals("SA----")) {
            return "ASW SHIP";
        }
        if (code.contentEquals("G-----")) {
            return "NAVY GROUP";
        }
        if (code.contentEquals("GT----")) {
            return "NAVY TASK FORCE";
        }
        if (code.contentEquals("GG----")) {
            return "NAVY TASK GROUP";
        }
        if (code.contentEquals("GU----")) {
            return "NAVY TASK UNIT";
        }
        if (code.contentEquals("GC----")) {
            return "CONVOY";
        }
        if (code.contentEquals("N-----")) {
            return "NON-COMBATANT";
        }
        if (code.contentEquals("NR----")) {
            return "UNDERWAY REPLENISHMENT";
        }
        if (code.contentEquals("NF----")) {
            return "FLEET SUPPORT";
        }
        if (code.contentEquals("NI----")) {
            return "INTELLIGENCE";
        }
        if (code.contentEquals("NS----")) {
            return "SERVICE & SUPPORT HARBOUR";
        }
        if (code.contentEquals("NM----")) {
            return "HOSPITAL SHIP";
        }
        if (code.contentEquals("NH----")) {
            return "HOVERCRAFT";
        }
        if (code.contentEquals("NN----")) {
            return "STATION";
        }
        if (code.contentEquals("NNR---")) {
            return "RESCUE";
        }
        if (code.contentEquals("X-----")) {
            return "NON-MILITARY";
        }
        if (code.contentEquals("XM----")) {
            return "MERCHANT";
        }
        if (code.contentEquals("XMC---")) {
            return "CARGO";
        }
        if (code.contentEquals("XMR---")) {
            return "ROLL-ON/ROLL-OFF (RO/RO)";
        }
        if (code.contentEquals("XMO---")) {
            return "OILER/TANKER";
        }
        if (code.contentEquals("XMTU--")) {
            return "TUG";
        }
        if (code.contentEquals("XMF---")) {
            return "FERRY";
        }
        if (code.contentEquals("XMP---")) {
            return "PASSENGER";
        }
        if (code.contentEquals("XMH---")) {
            return "HAZARDOUS MATERIALS (HAZMAT)";
        }
        if (code.contentEquals("XMTO--")) {
            return "TOWING VESSEL";
        }
        if (code.contentEquals("XF----")) {
            return "FISHING";
        }
        if (code.contentEquals("XFDF--")) {
            return "DRIFTER";
        }
        if (code.contentEquals("XFDR--")) {
            return "DREDGE";
        }
        if (code.contentEquals("XFTR--")) {
            return "TRAWLER";
        }
        if (code.contentEquals("XR----")) {
            return "LEISURE CRAFT";
        }
        if (code.contentEquals("XL----")) {
            return "LAW ENFORCEMENT VESSEL";
        }
        if (code.contentEquals("XH----")) {
            return "HOVERCRAFT";
        }
        if (code.contentEquals("O-----")) {
            return "OWN TRACK";
        }
        if (code.contentEquals("E-----")) {
            return "EMERGENCY148";
        }
        if (code.contentEquals("ED----")) {
            return "DITCHED AIRCRAFT";
        }
        if (code.contentEquals("EP----")) {
            return "PERSON IN WATER";
        }
        if (code.contentEquals("EV----")) {
            return "DISTRESSED VESSEL";
        }
        if (code.contentEquals("Z-----")) {
            return "HAZARD148";
        }
        if (code.contentEquals("ZM----")) {
            return "SEA MINE-LIKE";
        }
        if (code.contentEquals("ZN----")) {
            return "NAVIGATIONAL";
        }
        if (code.contentEquals("ZI----")) {
            return "ICEBERG";
        }
        if (code.contentEquals("------")) {
            return "SUBSURFACE TRACK";
        }
        if (code.contentEquals("S-----")) {
            return "SUBMARINE";
        }
        if (code.contentEquals("SN----")) {
            return "NUCLEAR PROPULSION";
        }
        if (code.contentEquals("SC----")) {
            return "CONVENTIONAL PROPULSION";
        }
        if (code.contentEquals("SO----")) {
            return "OTHER SUBMERSIBLE";
        }
        if (code.contentEquals("SS----")) {
            return "STATION";
        }
        if (code.contentEquals("SSA---")) {
            return "ANTISUBMARINE WARFARE (ASW)";
        }
        if (code.contentEquals("W-----")) {
            return "UNDERWATER WEAPON";
        }
        if (code.contentEquals("WT----")) {
            return "TORPEDO";
        }
        if (code.contentEquals("WM----")) {
            return "SEA MINE";
        }
        if (code.contentEquals("WMD---")) {
            return "SEA MINE DEALT";
        }
        if (code.contentEquals("WMG---")) {
            return "SEA MINE (GROUND)";
        }
        if (code.contentEquals("WMGD--")) {
            return "SEA MINE (GROUND) DEALT";
        }
        if (code.contentEquals("WMM---")) {
            return "SEA MINE (MOORED)";
        }
        if (code.contentEquals("WMMD--")) {
            return "SEA MINE (MOORED) DEALT";
        }
        if (code.contentEquals("WMF---")) {
            return "SEA MINE (FLOATING)";
        }
        if (code.contentEquals("WMFD--")) {
            return "SEA MINE (FLOATING) DEALT";
        }
        if (code.contentEquals("WMO---")) {
            return "SEA MINE (IN OTHER POSITION)";
        }
        if (code.contentEquals("WMOD--")) {
            return "SEA MINE (IN OTHER POSITION) DEALT";
        }
        if (code.contentEquals("WD----")) {
            return "UNDERWATER DECOY";
        }
        if (code.contentEquals("WDM---")) {
            return "SEA MINE DECOY";
        }
        if (code.contentEquals("N-----")) {
            return "NON-SUBMARINE";
        }
        if (code.contentEquals("ND----")) {
            return "DIVER";
        }
        if (code.contentEquals("NB----")) {
            return "BOTTOM RETURN/NON-MINE MINE-LIKE";
        }
        if (code.contentEquals("NBS---")) {
            return "SEABED INSTALLATION/MANMADE";
        }
        if (code.contentEquals("NBR---")) {
            return "SEABED ROCK/STONE  OBSTACLE  OTHER";
        }
        if (code.contentEquals("NBW---")) {
            return "WRECK";
        }
        if (code.contentEquals("NM----")) {
            return "MARINE LIFE";
        }
        if (code.contentEquals("NA----")) {
            return "SEA ANOMALY";
        }
        if (code.contentEquals("------")) {
            return "SPECIAL OPERATIONS FORCES (SOF) UNIT";
        }
        if (code.contentEquals("A-----")) {
            return "AVIATION";
        }
        if (code.contentEquals("AF----")) {
            return "FIXED WING";
        }
        if (code.contentEquals("AFA---")) {
            return "ATTACK";
        }
        if (code.contentEquals("AFK---")) {
            return "REFUEL";
        }
        if (code.contentEquals("AFU---")) {
            return "UTILITY";
        }
        if (code.contentEquals("AFUL--")) {
            return "UTILITY (LIGHT)";
        }
        if (code.contentEquals("AFUM--")) {
            return "UTILITY (MEDIUM)";
        }
        if (code.contentEquals("AFUH--")) {
            return "UTILITY (HEAVY)";
        }
        if (code.contentEquals("AV----")) {
            return "V/STOL";
        }
        if (code.contentEquals("AH----")) {
            return "ROTARY WING";
        }
        if (code.contentEquals("AHH---")) {
            return "COMBAT SEARCH AND RESCUE (CSAR)";
        }
        if (code.contentEquals("AHA---")) {
            return "ATTACK";
        }
        if (code.contentEquals("AHU---")) {
            return "UTILITY";
        }
        if (code.contentEquals("AHUL--")) {
            return "UTILITY (LIGHT)";
        }
        if (code.contentEquals("AHUM--")) {
            return "UTILITY (MEDIUM)";
        }
        if (code.contentEquals("AHUH--")) {
            return "UTILITY (HEAVY)";
        }
        if (code.contentEquals("SN----")) {
            return "NAVAL";
        }
        if (code.contentEquals("SNS---")) {
            return "SEA-AIR-LAND (SEAL)";
        }
        if (code.contentEquals("SNU---")) {
            return "UNDERWATER DEMOLITION TEAM";
        }
        if (code.contentEquals("SNB---")) {
            return "SPECIAL BOAT";
        }
        if (code.contentEquals("SNN---")) {
            return "SPECIAL SSNR";
        }
        if (code.contentEquals("G-----")) {
            return "GROUND";
        }
        if (code.contentEquals("GS----")) {
            return "SPECIAL FORCES";
        }
        if (code.contentEquals("GSR---")) {
            return "RANGER";
        }
        if (code.contentEquals("GSP---")) {
            return "PSYCHOLOGICAL OPERATIONS (PSYOP)";
        }
        if (code.contentEquals("GSPA--")) {
            return "FIXED AVIATION157";
        }
        if (code.contentEquals("GCA---")) {
            return "CIVIL AFFAIRS";
        }
        if (code.contentEquals("GB----")) {
            return "SUPPORT";
        }

        return "UNKNOWN";

    }

    public static String getSizeMobility(String symbolcode) {

        String code = symbolcode.substring(10, 11);

        if (code.contentEquals("-A")) {
            return "TEAM/CREW";
        }
        if (code.contentEquals("-B")) {
            return "SQUAD";
        }
        if (code.contentEquals("-C")) {
            return "SECTION";
        }
        if (code.contentEquals("-D")) {
            return "PLATOON";
        }
        if (code.contentEquals("-E")) {
            return "COMPANY";
        }
        if (code.contentEquals("-F")) {
            return "BATTALION";
        }
        if (code.contentEquals("-G")) {
            return "REGIMENT/GROUP";
        }
        if (code.contentEquals("-H")) {
            return "BRIGADE";
        }
        if (code.contentEquals("-I")) {
            return "DIVISION";
        }
        if (code.contentEquals("-J")) {
            return "CORPS/MARINE EXPEDITIONARY FORCE (MEF)";
        }
        if (code.contentEquals("C-")) {
            return "FEINT DUMMY (FD) HQ";
        }
        if (code.contentEquals("-K")) {
            return "ARMY";
        }
        if (code.contentEquals("-L")) {
            return "ARMY GROUP/FRONT";
        }
        if (code.contentEquals("-M")) {
            return "REGION CC";
        }
        if (code.contentEquals("--")) {
            return "NULL CD";
        }
        if (code.contentEquals("CE")) {
            return "FD HQ COMPANY ";
        }
        if (code.contentEquals("A-")) {
            return "HEADQUARTERS (HQ)";
        }
        if (code.contentEquals("AA")) {
            return "HQ TEAM/CREW";
        }
        if (code.contentEquals("AB")) {
            return "HQ SQUAD";
        }
        if (code.contentEquals("AC")) {
            return "HQ SECTION";
        }
        if (code.contentEquals("AD")) {
            return "HQ PLATOON";
        }
        if (code.contentEquals("AE")) {
            return "HQ COMPANY";
        }
        if (code.contentEquals("AF")) {
            return "HQ BATTALION";
        }
        if (code.contentEquals("AG")) {
            return "HQ REGIMENT/GROUP";
        }
        if (code.contentEquals("AH")) {
            return "HQ BRIGADE";
        }
        if (code.contentEquals("AI")) {
            return "HQ DIVISION";
        }
        if (code.contentEquals("AJ")) {
            return "HQ CORPS/MARINE EXPEDITIONARY FORCE (MEF)";
        }
        if (code.contentEquals("DA")) {
            return "FD/TF HQ TEAM/CREW";
        }
        if (code.contentEquals("AK")) {
            return "HQ ARMY";
        }
        if (code.contentEquals("AL")) {
            return "HQ ARMY GROUP/FRONT";
        }
        if (code.contentEquals("AM")) {
            return "HQ REGION";
        }
        if (code.contentEquals("DE")) {
            return "FD/TF HQ COMPANY";
        }
        if (code.contentEquals("B-")) {
            return "TASK FORCE (TF) HQ";
        }
        if (code.contentEquals("BA")) {
            return "TF HQ TEAM/CREW";
        }
        if (code.contentEquals("BB")) {
            return "TF HQ SQUAD";
        }
        if (code.contentEquals("BC")) {
            return "TF HQ SECTION";
        }
        if (code.contentEquals("BD")) {
            return "TF HQ PLATOON";
        }
        if (code.contentEquals("BE")) {
            return "TF HQ COMPANY";
        }
        if (code.contentEquals("DL")) {
            return "FD/TF HQ ARMY GROUP/FRONT";
        }
        if (code.contentEquals("DM")) {
            return "FD/TF HQ REGION";
        }
        if (code.contentEquals("GB")) {
            return "FD/TF SQUAD";
        }
        if (code.contentEquals("E-")) {
            return "TASK FORCE (TF)";
        }
        if (code.contentEquals("EA")) {
            return "TF TEAM/CREW";
        }
        if (code.contentEquals("EB")) {
            return "TF SQUAD";
        }
        if (code.contentEquals("EC")) {
            return "TF SECTION";
        }
        if (code.contentEquals("ED")) {
            return "TF PLATOON";
        }
        if (code.contentEquals("EE")) {
            return "TF COMPANY";
        }
        if (code.contentEquals("EF")) {
            return "TF BATTALION";
        }
        if (code.contentEquals("EG")) {
            return "TF REGIMENT/GROUP";
        }
        if (code.contentEquals("EH")) {
            return "TF BRIGADE";
        }
        if (code.contentEquals("EI")) {
            return "TF DIVISION";
        }
        if (code.contentEquals("EJ")) {
            return "TF CORPS/MARINE EXPEDITIONARY FORCE (MEF)";
        }
        if (code.contentEquals("GM")) {
            return "FD/TF REGION";
        }
        if (code.contentEquals("EK")) {
            return "TF ARMY";
        }
        if (code.contentEquals("EL")) {
            return "TF ARMY GROUP/FRONT ";
        }
        if (code.contentEquals("EM")) {
            return "TF REGION";
        }
        if (code.contentEquals("F-")) {
            return "FEINT DUMMY (FD) ";
        }
        if (code.contentEquals("FA")) {
            return "FD TEAM/CREW";
        }
        if (code.contentEquals("FB")) {
            return "FD SQUAD";
        }
        if (code.contentEquals("FC")) {
            return "FD SECTION";
        }
        if (code.contentEquals("FD")) {
            return "FD PLATOON";
        }
        if (code.contentEquals("FE")) {
            return "FD COMPANY";
        }
        if (code.contentEquals("FF")) {
            return "FD BATTALION";
        }
        if (code.contentEquals("FG")) {
            return "FD REGIMENT/GROUP";
        }
        if (code.contentEquals("FH")) {
            return "FD BRIGADE";
        }
        if (code.contentEquals("FI")) {
            return "FD DIVISION";
        }
        if (code.contentEquals("FJ")) {
            return "FD CORPS/MARINE EXPEDITIONARY FORCE (MEF)";
        }
        if (code.contentEquals("MV")) {
            return "MOBILITY SLED";
        }
        if (code.contentEquals("FK")) {
            return "FD ARMY";
        }
        if (code.contentEquals("FL")) {
            return "FD ARMY GROUP/FRONT ";
        }
        if (code.contentEquals("FM")) {
            return "FD REGION";
        }
        if (code.contentEquals("GD")) {
            return "FD/TF PLATOON";
        }
        if (code.contentEquals("GE")) {
            return "FD/TF COMPANY";
        }
        if (code.contentEquals("GF")) {
            return "FD/TF BATTALION";
        }
        if (code.contentEquals("GG")) {
            return "FD/TF REGIMENT/GROUP";
        }
        if (code.contentEquals("GH")) {
            return "FD/TF BRIGADE";
        }
        if (code.contentEquals("GI")) {
            return "FD/TF DIVISION";
        }
        if (code.contentEquals("GJ")) {
            return "FD/TF CORPS/MARINE EXPEDITIONARY FORCE (MEF)";
        }
        if (code.contentEquals("GK")) {
            return "FD/TF ARMY";
        }
        if (code.contentEquals("GL")) {
            return "FD/TF ARMY GROUP/FRONT";
        }
        if (code.contentEquals("H-")) {
            return "INSTALLATION";
        }
        if (code.contentEquals("K-")) {
            return "NUCLEAR YIELD IN KILOTONS";
        }
        if (code.contentEquals("M-")) {
            return "MOBILITY EQUIPMENT";
        }
        if (code.contentEquals("MO")) {
            return "MOBILITY WHEELED/LIMITED CROSS COUNTRY";
        }
        if (code.contentEquals("MP")) {
            return "MOBILITY CROSS COUNTRY";
        }
        if (code.contentEquals("MQ")) {
            return "MOBILITY TRACKED";
        }
        if (code.contentEquals("MR")) {
            return "MOBILITY WHEELED AND TRACKED COMBINATION";
        }
        if (code.contentEquals("MS")) {
            return "MOBILITY TOWED";
        }
        if (code.contentEquals("MT")) {
            return "MOBILITY RAIL";
        }
        if (code.contentEquals("MU")) {
            return "MOBILITY OVER THE SNOW";
        }
        if (code.contentEquals("MW")) {
            return "MOBILITY PACK ANIMALS ";
        }
        if (code.contentEquals("MY")) {
            return "MOBILITY AMPHIBIOUS";
        }
        if (code.contentEquals("MX")) {
            return "MOBILITY BARGE";
        }
        if (code.contentEquals("BF")) {
            return "TF HQ BATTALION ";
        }
        if (code.contentEquals("BG")) {
            return "TF HQ REGIMENT/GROUP ";
        }
        if (code.contentEquals("BH")) {
            return "TF HQ BRIGADE";
        }
        if (code.contentEquals("BI")) {
            return "TF HQ DIVISION";
        }
        if (code.contentEquals("BJ")) {
            return "TF HQ CORPS/MARINE EXPEDITIONARY FORCE (MEF)";
        }
        if (code.contentEquals("BK")) {
            return "TF HQ ARMY";
        }
        if (code.contentEquals("BL")) {
            return "TF HQ ARMY GROUP/FRONT";
        }
        if (code.contentEquals("BM")) {
            return "TF HQ REGION";
        }
        if (code.contentEquals("CA")) {
            return "FD HQ TEAM/CREW";
        }
        if (code.contentEquals("CB")) {
            return "FD HQ SQUAD";
        }
        if (code.contentEquals("FD")) {
            return "HQ SECTION";
        }
        if (code.contentEquals("FD")) {
            return "HQ PLATOON";
        }
        if (code.contentEquals("HB")) {
            return "FEINT DUMMY INSTALLATION";
        }
        if (code.contentEquals("GA")) {
            return "FD/TF TEAM/CREW";
        }
        if (code.contentEquals("GC")) {
            return "FD/TF SECTION";
        }
        if (code.contentEquals("DF")) {
            return "FD/TF HQ BATTALION ";
        }
        if (code.contentEquals("G-")) {
            return "FEINT DUMMY/TASK FORCE (FD/TF)";
        }
        if (code.contentEquals("DG")) {
            return "FD/TF HQ REGIMENT/GROUP";
        }
        if (code.contentEquals("DH")) {
            return "FD/TF HQ BRIGADE";
        }
        if (code.contentEquals("DI")) {
            return "FD/TF HQ DIVISION";
        }
        if (code.contentEquals("DJ")) {
            return "FD/TF HQ CORPS/MARINE EXPEDITIONARY FORCE (MEF)";
        }
        if (code.contentEquals("DK")) {
            return "FD/TF HQ ARMY";
        }
        if (code.contentEquals("CF")) {
            return "FD HQ BATTALION";
        }
        if (code.contentEquals("CG")) {
            return "FD HQ REGIMENT/GROUP";
        }
        if (code.contentEquals("CH")) {
            return "FD HQ BRIGADE";
        }
        if (code.contentEquals("CI")) {
            return "FD HQ DIVISION";
        }
        if (code.contentEquals("CJ")) {
            return "FD HQ CORPS/MARINE EXPEDITIONARY FORCE (MEF)";
        }
        if (code.contentEquals("CK")) {
            return "FD HQ ARMY";
        }
        if (code.contentEquals("CL")) {
            return "FD HQ ARMY GROUP/FRONT";
        }
        if (code.contentEquals("CM")) {
            return "FD HQ REGION";
        }
        if (code.contentEquals("DC")) {
            return "FD/TF HQ SECTION";
        }
        if (code.contentEquals("DB")) {
            return "FD/TF HQ SQUAD";
        }
        if (code.contentEquals("D-")) {
            return "FEINT DUMMY/TASK FORCE (FD/TF) HQ";
        }
        if (code.contentEquals("DD")) {
            return "FD/TF HQ PLATOON";
        }

        return "UNKNOWN";

    }

    public static String getCountryCode(String symbolcode) {

        String countrycode = symbolcode.substring(12, 13);
                
        if (countrycode.contentEquals("AD")) {
            return "Andorra";
        }
        if (countrycode.contentEquals("AE")) {
            return "United Arab Emirates";
        }
        if (countrycode.contentEquals("AF")) {
            return "Afghanistan";
        }
        if (countrycode.contentEquals("AG")) {
            return "Antigua and Barbuda";
        }
        if (countrycode.contentEquals("AI")) {
            return "Anguilla";
        }
        if (countrycode.contentEquals("AL")) {
            return "Albania";
        }
        if (countrycode.contentEquals("AM")) {
            return "Armenia";
        }
        if (countrycode.contentEquals("AN")) {
            return "Netherlands Antilles";
        }
        if (countrycode.contentEquals("AO")) {
            return "Angola";
        }
        if (countrycode.contentEquals("AQ")) {
            return "Antarctica";
        }
        if (countrycode.contentEquals("AR")) {
            return "Argentina";
        }
        if (countrycode.contentEquals("AS")) {
            return "American Samoa";
        }
        if (countrycode.contentEquals("AT")) {
            return "Austria";
        }
        if (countrycode.contentEquals("AU")) {
            return "Australia";
        }
        if (countrycode.contentEquals("AW")) {
            return "Aruba";
        }
        if (countrycode.contentEquals("AX")) {
            return "Aland Islands";
        }
        if (countrycode.contentEquals("AZ")) {
            return "Azerbaijan";
        }
        if (countrycode.contentEquals("BA")) {
            return "Bosnia and Herzegovina";
        }
        if (countrycode.contentEquals("BB")) {
            return "Barbados";
        }
        if (countrycode.contentEquals("BD")) {
            return "Bangladesh";
        }
        if (countrycode.contentEquals("BE")) {
            return "Belgium";
        }
        if (countrycode.contentEquals("BF")) {
            return "Burkina Faso";
        }
        if (countrycode.contentEquals("BG")) {
            return "Bulgaria";
        }
        if (countrycode.contentEquals("BH")) {
            return "Bahrain";
        }
        if (countrycode.contentEquals("BI")) {
            return "Burundi";
        }
        if (countrycode.contentEquals("BJ")) {
            return "Benin";
        }
        if (countrycode.contentEquals("BM")) {
            return "Bermuda";
        }
        if (countrycode.contentEquals("BN")) {
            return "Brunei Darussalam";
        }
        if (countrycode.contentEquals("BO")) {
            return "Bolivia";
        }
        if (countrycode.contentEquals("BR")) {
            return "Brazil";
        }
        if (countrycode.contentEquals("BS")) {
            return "Bahamas";
        }
        if (countrycode.contentEquals("BT")) {
            return "Bhutan";
        }
        if (countrycode.contentEquals("BV")) {
            return "Bouvet Island";
        }
        if (countrycode.contentEquals("BW")) {
            return "Botswana";
        }
        if (countrycode.contentEquals("BY")) {
            return "Belarus";
        }
        if (countrycode.contentEquals("BZ")) {
            return "Belize";
        }
        if (countrycode.contentEquals("CA")) {
            return "Canada";
        }
        if (countrycode.contentEquals("CC")) {
            return "Cocos (Keeling) Islands";
        }
        if (countrycode.contentEquals("CD")) {
            return "Democratic Republic of the Congo";
        }
        if (countrycode.contentEquals("CF")) {
            return "Central African Republic";
        }
        if (countrycode.contentEquals("CG")) {
            return "Congo";
        }
        if (countrycode.contentEquals("CH")) {
            return "Switzerland";
        }
        if (countrycode.contentEquals("CI")) {
            return "Cote D'Ivoire (Ivory Coast)";
        }
        if (countrycode.contentEquals("CK")) {
            return "Cook Islands";
        }
        if (countrycode.contentEquals("CL")) {
            return "Chile";
        }
        if (countrycode.contentEquals("CM")) {
            return "Cameroon";
        }
        if (countrycode.contentEquals("CN")) {
            return "China";
        }
        if (countrycode.contentEquals("CO")) {
            return "Colombia";
        }
        if (countrycode.contentEquals("CR")) {
            return "Costa Rica";
        }
        if (countrycode.contentEquals("CS")) {
            return "Serbia and Montenegro";
        }
        if (countrycode.contentEquals("CU")) {
            return "Cuba";
        }
        if (countrycode.contentEquals("CV")) {
            return "Cape Verde";
        }
        if (countrycode.contentEquals("CX")) {
            return "Christmas Island";
        }
        if (countrycode.contentEquals("CY")) {
            return "Cyprus";
        }
        if (countrycode.contentEquals("CZ")) {
            return "Czech Republic";
        }
        if (countrycode.contentEquals("DE")) {
            return "Germany";
        }
        if (countrycode.contentEquals("DJ")) {
            return "Djibouti";
        }
        if (countrycode.contentEquals("DK")) {
            return "Denmark";
        }
        if (countrycode.contentEquals("DM")) {
            return "Dominica";
        }
        if (countrycode.contentEquals("DO")) {
            return "Dominican Republic";
        }
        if (countrycode.contentEquals("DZ")) {
            return "Algeria";
        }
        if (countrycode.contentEquals("EC")) {
            return "Ecuador";
        }
        if (countrycode.contentEquals("EE")) {
            return "Estonia";
        }
        if (countrycode.contentEquals("EG")) {
            return "Egypt";
        }
        if (countrycode.contentEquals("EH")) {
            return "Western Sahara";
        }
        if (countrycode.contentEquals("ER")) {
            return "Eritrea";
        }
        if (countrycode.contentEquals("ES")) {
            return "Spain";
        }
        if (countrycode.contentEquals("ET")) {
            return "Ethiopia";
        }
        if (countrycode.contentEquals("FI")) {
            return "Finland";
        }
        if (countrycode.contentEquals("FJ")) {
            return "Fiji";
        }
        if (countrycode.contentEquals("FK")) {
            return "Falkland Islands (Malvinas)";
        }
        if (countrycode.contentEquals("FM")) {
            return "Federated States of Micronesia";
        }
        if (countrycode.contentEquals("FO")) {
            return "Faroe Islands";
        }
        if (countrycode.contentEquals("FR")) {
            return "France";
        }
        if (countrycode.contentEquals("FX")) {
            return "France  Metropolitan";
        }
        if (countrycode.contentEquals("GA")) {
            return "Gabon";
        }
        if (countrycode.contentEquals("GB")) {
            return "Great Britain (UK)";
        }
        if (countrycode.contentEquals("GD")) {
            return "Grenada";
        }
        if (countrycode.contentEquals("GE")) {
            return "Georgia";
        }
        if (countrycode.contentEquals("GF")) {
            return "French Guiana";
        }
        if (countrycode.contentEquals("GH")) {
            return "Ghana";
        }
        if (countrycode.contentEquals("GI")) {
            return "Gibraltar";
        }
        if (countrycode.contentEquals("GL")) {
            return "Greenland";
        }
        if (countrycode.contentEquals("GM")) {
            return "Gambia";
        }
        if (countrycode.contentEquals("GN")) {
            return "Guinea";
        }
        if (countrycode.contentEquals("GP")) {
            return "Guadeloupe";
        }
        if (countrycode.contentEquals("GQ")) {
            return "Equatorial Guinea";
        }
        if (countrycode.contentEquals("GR")) {
            return "Greece";
        }
        if (countrycode.contentEquals("GS")) {
            return "S. Georgia and S. Sandwich Islands";
        }
        if (countrycode.contentEquals("GT")) {
            return "Guatemala";
        }
        if (countrycode.contentEquals("GU")) {
            return "Guam";
        }
        if (countrycode.contentEquals("GW")) {
            return "Guinea-Bissau";
        }
        if (countrycode.contentEquals("GY")) {
            return "Guyana";
        }
        if (countrycode.contentEquals("HK")) {
            return "Hong Kong";
        }
        if (countrycode.contentEquals("HM")) {
            return "Heard Island and McDonald Islands";
        }
        if (countrycode.contentEquals("HN")) {
            return "Honduras";
        }
        if (countrycode.contentEquals("HR")) {
            return "Croatia (Hrvatska)";
        }
        if (countrycode.contentEquals("HT")) {
            return "Haiti";
        }
        if (countrycode.contentEquals("HU")) {
            return "Hungary";
        }
        if (countrycode.contentEquals("ID")) {
            return "Indonesia";
        }
        if (countrycode.contentEquals("IE")) {
            return "Ireland";
        }
        if (countrycode.contentEquals("IL")) {
            return "Israel";
        }
        if (countrycode.contentEquals("IN")) {
            return "India";
        }
        if (countrycode.contentEquals("IO")) {
            return "British Indian Ocean Territory";
        }
        if (countrycode.contentEquals("IQ")) {
            return "Iraq";
        }
        if (countrycode.contentEquals("IR")) {
            return "Iran";
        }
        if (countrycode.contentEquals("IS")) {
            return "Iceland";
        }
        if (countrycode.contentEquals("IT")) {
            return "Italy";
        }
        if (countrycode.contentEquals("JM")) {
            return "Jamaica";
        }
        if (countrycode.contentEquals("JO")) {
            return "Jordan";
        }
        if (countrycode.contentEquals("JP")) {
            return "Japan";
        }
        if (countrycode.contentEquals("KE")) {
            return "Kenya";
        }
        if (countrycode.contentEquals("KG")) {
            return "Kyrgyzstan";
        }
        if (countrycode.contentEquals("KH")) {
            return "Cambodia";
        }
        if (countrycode.contentEquals("KI")) {
            return "Kiribati";
        }
        if (countrycode.contentEquals("KM")) {
            return "Comoros";
        }
        if (countrycode.contentEquals("KN")) {
            return "Saint Kitts and Nevis";
        }
        if (countrycode.contentEquals("KP")) {
            return "Korea (North)";
        }
        if (countrycode.contentEquals("KR")) {
            return "Korea (South)";
        }
        if (countrycode.contentEquals("KW")) {
            return "Kuwait";
        }
        if (countrycode.contentEquals("KY")) {
            return "Cayman Islands";
        }
        if (countrycode.contentEquals("KZ")) {
            return "Kazakhstan";
        }
        if (countrycode.contentEquals("LA")) {
            return "Laos";
        }
        if (countrycode.contentEquals("LB")) {
            return "Lebanon";
        }
        if (countrycode.contentEquals("LC")) {
            return "Saint Lucia";
        }
        if (countrycode.contentEquals("LI")) {
            return "Liechtenstein";
        }
        if (countrycode.contentEquals("LK")) {
            return "Sri Lanka";
        }
        if (countrycode.contentEquals("LR")) {
            return "Liberia";
        }
        if (countrycode.contentEquals("LS")) {
            return "Lesotho";
        }
        if (countrycode.contentEquals("LT")) {
            return "Lithuania";
        }
        if (countrycode.contentEquals("LU")) {
            return "Luxembourg";
        }
        if (countrycode.contentEquals("LV")) {
            return "Latvia";
        }
        if (countrycode.contentEquals("LY")) {
            return "Libya";
        }
        if (countrycode.contentEquals("MA")) {
            return "Morocco";
        }
        if (countrycode.contentEquals("MC")) {
            return "Monaco";
        }
        if (countrycode.contentEquals("MD")) {
            return "Moldova";
        }
        if (countrycode.contentEquals("MG")) {
            return "Madagascar";
        }
        if (countrycode.contentEquals("MH")) {
            return "Marshall Islands";
        }
        if (countrycode.contentEquals("MK")) {
            return "Macedonia";
        }
        if (countrycode.contentEquals("ML")) {
            return "Mali";
        }
        if (countrycode.contentEquals("MM")) {
            return "Myanmar";
        }
        if (countrycode.contentEquals("MN")) {
            return "Mongolia";
        }
        if (countrycode.contentEquals("MO")) {
            return "Macao";
        }
        if (countrycode.contentEquals("MP")) {
            return "Northern Mariana Islands";
        }
        if (countrycode.contentEquals("MQ")) {
            return "Martinique";
        }
        if (countrycode.contentEquals("MR")) {
            return "Mauritania";
        }
        if (countrycode.contentEquals("MS")) {
            return "Montserrat";
        }
        if (countrycode.contentEquals("MT")) {
            return "Malta";
        }
        if (countrycode.contentEquals("MU")) {
            return "Mauritius";
        }
        if (countrycode.contentEquals("MV")) {
            return "Maldives";
        }
        if (countrycode.contentEquals("MW")) {
            return "Malawi";
        }
        if (countrycode.contentEquals("MX")) {
            return "Mexico";
        }
        if (countrycode.contentEquals("MY")) {
            return "Malaysia";
        }
        if (countrycode.contentEquals("MZ")) {
            return "Mozambique";
        }
        if (countrycode.contentEquals("NA")) {
            return "Namibia";
        }
        if (countrycode.contentEquals("NC")) {
            return "New Caledonia";
        }
        if (countrycode.contentEquals("NE")) {
            return "Niger";
        }
        if (countrycode.contentEquals("NF")) {
            return "Norfolk Island";
        }
        if (countrycode.contentEquals("NG")) {
            return "Nigeria";
        }
        if (countrycode.contentEquals("NI")) {
            return "Nicaragua";
        }
        if (countrycode.contentEquals("NL")) {
            return "Netherlands";
        }
        if (countrycode.contentEquals("NO")) {
            return "Norway";
        }
        if (countrycode.contentEquals("NP")) {
            return "Nepal";
        }
        if (countrycode.contentEquals("NR")) {
            return "Nauru";
        }
        if (countrycode.contentEquals("NU")) {
            return "Niue";
        }
        if (countrycode.contentEquals("NZ")) {
            return "New Zealand (Aotearoa)";
        }
        if (countrycode.contentEquals("OM")) {
            return "Oman";
        }
        if (countrycode.contentEquals("PA")) {
            return "Panama";
        }
        if (countrycode.contentEquals("PE")) {
            return "Peru";
        }
        if (countrycode.contentEquals("PF")) {
            return "French Polynesia";
        }
        if (countrycode.contentEquals("PG")) {
            return "Papua New Guinea";
        }
        if (countrycode.contentEquals("PH")) {
            return "Philippines";
        }
        if (countrycode.contentEquals("PK")) {
            return "Pakistan";
        }
        if (countrycode.contentEquals("PL")) {
            return "Poland";
        }
        if (countrycode.contentEquals("PM")) {
            return "Saint Pierre and Miquelon";
        }
        if (countrycode.contentEquals("PN")) {
            return "Pitcairn";
        }
        if (countrycode.contentEquals("PR")) {
            return "Puerto Rico";
        }
        if (countrycode.contentEquals("PS")) {
            return "Palestinian Territory";
        }
        if (countrycode.contentEquals("PT")) {
            return "Portugal";
        }
        if (countrycode.contentEquals("PW")) {
            return "Palau";
        }
        if (countrycode.contentEquals("PY")) {
            return "Paraguay";
        }
        if (countrycode.contentEquals("QA")) {
            return "Qatar";
        }
        if (countrycode.contentEquals("RE")) {
            return "Reunion";
        }
        if (countrycode.contentEquals("RO")) {
            return "Romania";
        }
        if (countrycode.contentEquals("RU")) {
            return "Russian Federation";
        }
        if (countrycode.contentEquals("RW")) {
            return "Rwanda";
        }
        if (countrycode.contentEquals("SA")) {
            return "Saudi Arabia";
        }
        if (countrycode.contentEquals("SB")) {
            return "Solomon Islands";
        }
        if (countrycode.contentEquals("SC")) {
            return "Seychelles";
        }
        if (countrycode.contentEquals("SD")) {
            return "Sudan";
        }
        if (countrycode.contentEquals("SE")) {
            return "Sweden";
        }
        if (countrycode.contentEquals("SG")) {
            return "Singapore";
        }
        if (countrycode.contentEquals("SH")) {
            return "Saint Helena";
        }
        if (countrycode.contentEquals("SI")) {
            return "Slovenia";
        }
        if (countrycode.contentEquals("SJ")) {
            return "Svalbard and Jan Mayen";
        }
        if (countrycode.contentEquals("SK")) {
            return "Slovakia";
        }
        if (countrycode.contentEquals("SL")) {
            return "Sierra Leone";
        }
        if (countrycode.contentEquals("SM")) {
            return "San Marino";
        }
        if (countrycode.contentEquals("SN")) {
            return "Senegal";
        }
        if (countrycode.contentEquals("SO")) {
            return "Somalia";
        }
        if (countrycode.contentEquals("SR")) {
            return "Suriname";
        }
        if (countrycode.contentEquals("ST")) {
            return "Sao Tome and Principe";
        }
        if (countrycode.contentEquals("SU")) {
            return "USSR (former)";
        }
        if (countrycode.contentEquals("SV")) {
            return "El Salvador";
        }
        if (countrycode.contentEquals("SY")) {
            return "Syria";
        }
        if (countrycode.contentEquals("SZ")) {
            return "Swaziland";
        }
        if (countrycode.contentEquals("TC")) {
            return "Turks and Caicos Islands";
        }
        if (countrycode.contentEquals("TD")) {
            return "Chad";
        }
        if (countrycode.contentEquals("TF")) {
            return "French Southern Territories";
        }
        if (countrycode.contentEquals("TG")) {
            return "Togo";
        }
        if (countrycode.contentEquals("TH")) {
            return "Thailand";
        }
        if (countrycode.contentEquals("TJ")) {
            return "Tajikistan";
        }
        if (countrycode.contentEquals("TK")) {
            return "Tokelau";
        }
        if (countrycode.contentEquals("TL")) {
            return "Timor-Leste";
        }
        if (countrycode.contentEquals("TM")) {
            return "Turkmenistan";
        }
        if (countrycode.contentEquals("TN")) {
            return "Tunisia";
        }
        if (countrycode.contentEquals("TO")) {
            return "Tonga";
        }
        if (countrycode.contentEquals("TP")) {
            return "East Timor";
        }
        if (countrycode.contentEquals("TR")) {
            return "Turkey";
        }
        if (countrycode.contentEquals("TT")) {
            return "Trinidad and Tobago";
        }
        if (countrycode.contentEquals("TV")) {
            return "Tuvalu";
        }
        if (countrycode.contentEquals("TW")) {
            return "Taiwan";
        }
        if (countrycode.contentEquals("TZ")) {
            return "Tanzania";
        }
        if (countrycode.contentEquals("UA")) {
            return "Ukraine";
        }
        if (countrycode.contentEquals("UG")) {
            return "Uganda";
        }
        if (countrycode.contentEquals("UK")) {
            return "United Kingdom";
        }
        if (countrycode.contentEquals("UM")) {
            return "United States Minor Outlying Islands";
        }
        if (countrycode.contentEquals("US")) {
            return "United States";
        }
        if (countrycode.contentEquals("UY")) {
            return "Uruguay";
        }
        if (countrycode.contentEquals("UZ")) {
            return "Uzbekistan";
        }
        if (countrycode.contentEquals("VA")) {
            return "Vatican City State (Holy See)";
        }
        if (countrycode.contentEquals("VC")) {
            return "Saint Vincent and the Grenadines";
        }
        if (countrycode.contentEquals("VE")) {
            return "Venezuela";
        }
        if (countrycode.contentEquals("VG")) {
            return "Virgin Islands (British)";
        }
        if (countrycode.contentEquals("VI")) {
            return "Virgin Islands (U.S.)";
        }
        if (countrycode.contentEquals("VN")) {
            return "Viet Nam";
        }
        if (countrycode.contentEquals("VU")) {
            return "Vanuatu";
        }
        if (countrycode.contentEquals("WF")) {
            return "Wallis and Futuna";
        }
        if (countrycode.contentEquals("WS")) {
            return "Samoa";
        }
        if (countrycode.contentEquals("YE")) {
            return "Yemen";
        }
        if (countrycode.contentEquals("YT")) {
            return "Mayotte";
        }
        if (countrycode.contentEquals("YU")) {
            return "Yugoslavia (former)";
        }
        if (countrycode.contentEquals("ZA")) {
            return "South Africa";
        }
        if (countrycode.contentEquals("ZM")) {
            return "Zambia";
        }
        if (countrycode.contentEquals("ZR")) {
            return "Zaire (former)";
        }
        if (countrycode.contentEquals("ZW")) {
            return "Zimbabwe";
        }
        if (countrycode.contentEquals("BIZ")) {
            return "Business";
        }
        if (countrycode.contentEquals("COM")) {
            return "Commercial";
        }
        if (countrycode.contentEquals("EDU")) {
            return "US Educational";
        }
        if (countrycode.contentEquals("GOV")) {
            return "US Government";
        }
        if (countrycode.contentEquals("INT")) {
            return "International";
        }
        if (countrycode.contentEquals("MIL")) {
            return "US Military";
        }
        if (countrycode.contentEquals("NET")) {
            return "Network";
        }
        if (countrycode.contentEquals("ORG")) {
            return "Nonprofit Organization";
        }
        if (countrycode.contentEquals("PRO")) {
            return "Professional Services";
        }
        if (countrycode.contentEquals("AERO")) {
            return "Aeronautic";
        }
        if (countrycode.contentEquals("ARPA")) {
            return "Arpanet Technical Infrastructure";
        }
        if (countrycode.contentEquals("COOP")) {
            return "Cooperative";
        }
        if (countrycode.contentEquals("INFO")) {
            return "Info Domain";
        }
        if (countrycode.contentEquals("NAME")) {
            return "Personal Name";
        }
        if (countrycode.contentEquals("NATO")) {
            return "North Atlantic Treaty Organization";
        }

        return "UNKNOWN";
        
    }

    public static String getOrderOfBattle(String symbolcode) {

        String code = String.valueOf(symbolcode.charAt(14));

        if (code.contentEquals("A")) {
            return "AIR OB";
        }
        if (code.contentEquals("E")) {
            return "ELECTRONIC OB";
        }
        if (code.contentEquals("C")) {
            return "CIVILIAN OB";
        }
        if (code.contentEquals("G")) {
            return "GROUND OB";
        }
        if (code.contentEquals("N")) {
            return "MARITIME OB";
        }
        if (code.contentEquals("S")) {
            return "STRATEGIC FORCE RELATED";
        }
        if (code.contentEquals("X")) {
            return "CONTROL MARKINGS";
        }
        return "UNKNOWN";
    }
}
