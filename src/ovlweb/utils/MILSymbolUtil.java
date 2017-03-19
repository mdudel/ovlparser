/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import ovlweb.utils.drawing.tacticalgraphics.SimpleSymbol;

/**
 *
 * @author KKEITH
 */
public class MILSymbolUtil {

    public static String CatThreatToSymbol(String catthreat) {
        StringBuilder symbol = new StringBuilder();
        String[] ct = catthreat.split("_");

        symbol.append("S");
        if (ct[1].equalsIgnoreCase("PND")) {
            symbol.append("P");
        }
        if (ct[1].equalsIgnoreCase("UNK")) {
            symbol.append("U");
        }
        if (ct[1].equalsIgnoreCase("AFD")) {
            symbol.append("A");
        }
        if (ct[1].equalsIgnoreCase("FRD")) {
            symbol.append("F");
        }
        if (ct[1].equalsIgnoreCase("NEU")) {
            symbol.append("N");
        }
        if (ct[1].equalsIgnoreCase("SUS")) {
            symbol.append("S");
        }
        if (ct[1].equalsIgnoreCase("HOS")) {
            symbol.append("H");
        }
        if (ct[1].equalsIgnoreCase("JOK")) {
            symbol.append("J");
        }
        if (ct[1].equalsIgnoreCase("FAK")) {
            symbol.append("K");
        }
        if (ct[0].equalsIgnoreCase("SPC")) {
            symbol.append("P");
        }
        if (ct[0].equalsIgnoreCase("AIR")) {
            symbol.append("A");
        }
        if (ct[0].equalsIgnoreCase("LND")) {
            symbol.append("G");
        }
        if (ct[0].equalsIgnoreCase("SUR")) {
            symbol.append("S");
        }
        if (ct[0].equalsIgnoreCase("SUB")) {
            symbol.append("U");
        }
        if (ct[0].equalsIgnoreCase("SOF")) {
            symbol.append("F");
        }
        if (ct[0].equalsIgnoreCase("OTH")) {
            symbol.append("X");
        }
        if (ct[0].equalsIgnoreCase("UNK")) {
            symbol.append("Z");
        }
        symbol.append("P------*****");
        return symbol.toString();
    }

    public static String getAffiliationCode(String paramString) {
        if ("Pending".equals(paramString)) {
            return "P";
        }
        if ("Unknown".equals(paramString)) {
            return "U";
        }
        if ("Assumed Friend".equals(paramString)) {
            return "A";
        }
        if ("Friend".equals(paramString)) {
            return "F";
        }
        if ("Neutral".equals(paramString)) {
            return "N";
        }
        if ("Suspect".equals(paramString)) {
            return "S";
        }
        if ("Hostile".equals(paramString)) {
            return "H";
        }
        if ("Joker".equals(paramString)) {
            return "J";
        }
        if ("Faker".equals(paramString)) {
            return "K";
        }
        if ("None".equals(paramString)) {
            return "O";
        }
        return "O";
    }

    public static String getAffiliationFromLineColor(String color) {
        if (color.contentEquals("255 0 0")) {
            return "H";
        }
        if (color.contentEquals("0 255 255")) {
            return "F";
        }
        if (color.contentEquals("0 255 0")) {
            return "N";
        }
        if (color.contentEquals("255 255 0")) {
            return "U";
        }
        return "U";
    }

    public static String getAffiliationString(String paramString) {
        if ("P".equals(paramString)) {
            return "Pending";
        }
        if ("U".equals(paramString)) {
            return "Unknown";
        }
        if ("A".equals(paramString)) {
            return "Assumed Friend";
        }
        if ("F".equals(paramString)) {
            return "Friend";
        }
        if ("N".equals(paramString)) {
            return "Neutral";
        }
        if ("S".equals(paramString)) {
            return "Suspect";
        }
        if ("H".equals(paramString)) {
            return "Hostile";
        }
        if ("J".equals(paramString)) {
            return "Joker";
        }
        if ("K".equals(paramString)) {
            return "Faker";
        }
        return "None";
    }

    private static boolean checkBattleDimension(String paramString, char paramChar1, char paramChar2) {
        int i = 0;
        if (isMilitaryIDString(paramString)) {
            i = (paramString.charAt(0) == paramChar1) && (paramString.charAt(2) == paramChar2) ? 1 : 0;
        }
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isTacticalGraphic(String paramString) {
        return (isMilitaryIDString(paramString)) && (paramString.charAt(0) == 'G');
    }

    public static boolean isAffiliationHostile(String paramString) {
        return checkAffiliation(paramString, "H");
    }

    public static boolean isAffiliationSuspect(String paramString) {
        return checkAffiliation(paramString, "S");
    }

    public static boolean isAffiliationPending(String paramString) {
        return checkAffiliation(paramString, "P");
    }

    public static boolean isAffiliationUnknown(String paramString) {
        return checkAffiliation(paramString, "U");
    }

    public static boolean isAffiliationNeutral(String paramString) {
        return checkAffiliation(paramString, "N");
    }

    public static boolean isMilitaryIDString(String paramString) {
        return (paramString != null) && (paramString.length() == 15);
    }

    public static boolean isAffiliationJoker(String paramString) {
        return checkAffiliation(paramString, "J");
    }

    public static boolean isAffiliationFaker(String paramString) {
        return checkAffiliation(paramString, "K");
    }

    public static boolean isAffiliationUnset(String paramString) {
        boolean i = false;
        if (isMilitaryIDString(paramString)) {
            i = paramString.charAt(1) == '-' ? true : false;
        }
        return i;
    }

    public static boolean isAffiliationFriend(String paramString) {
        int i = 0;
        if ((isMilitaryIDString(paramString)) && ((paramString.substring(1, 2).equals("F")) || (paramString.substring(1, 2).equals("A")))) {
            i = 1;
        }
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isAffiliationNone(String paramString) {
        return checkAffiliation(paramString, "O");
    }

    private static boolean checkAffiliation(String paramString1, String paramString2) {
        boolean bool = false;
        if (isMilitaryIDString(paramString1)) {
            bool = paramString1.substring(1, 2).equals(paramString2);
        }
        return bool;
    }

    public static String getFunctionString(int code) {
        if (code == 2400) {
            return "EMPTY_UNIT".replace("_", " ");
        }
        if (code == 120) {
            return "AIRBORNE".replace("_", " ");
        }
        if (code == 121) {
            return "RECONNAISSANCE AIRBORNE".replace("_", " ");
        }
        if (code == 122) {
            return "ARMOR AIRBORNE".replace("_", " ");
        }
        if (code == 123) {
            return "ARTILLERY AIRBORNE".replace("_", " ");
        }
        if (code == 124) {
            return "INFANTRY AIRBORNE".replace("_", " ");
        }
        if (code == 125) {
            return "SIGNAL AIRBORNE".replace("_", " ");
        }
        if (code == 130) {
            return "ARMORED VEHICLE".replace("_", " ");
        }
        if (code == 131) {
            return "INFANTRY MECHANIZED".replace("_", " ");
        }
        if (code == 132) {
            return "CAVALRY ARMORED".replace("_", " ");
        }
        if (code == 133) {
            return "ARTILLERY".replace("_", " ");
        }
        if (code == 134) {
            return "ARMORED INFANTRY".replace("_", " ");
        }
        if (code == 135) {
            return "MECHANIZED_ENGINEER".replace("_", " ");
        }
        if (code == 140) {
            return "INFANTRY".replace("_", " ");
        }
        if (code == 141) {
            return "RECONNAISSANCE MARINE".replace("_", " ");
        }
        if (code == 142) {
            return "INFANTRY MOUNTAIN".replace("_", " ");
        }
        if (code == 143) {
            return "INFANTRY AIRBORNE".replace("_", " ");
        }
        if (code == 150) {
            return "AMPHIBIOUS".replace("_", " ");
        }
        if (code == 151) {
            return "AMPHIBIOUS".replace("_", " ");
        }
        if (code == 152) {
            return "AMPHIBIOUS".replace("_", " ");
        }
        if (code == 153) {
            return "AMPHIBIOUS".replace("_", " ");
        }
        if (code == 160) {
            return "ARTILLERY".replace("_", " ");
        }
        if (code == 161) {
            return "ARTILLERY ROCKET".replace("_", " ");
        }
        if (code == 162) {
            return "AIR DEFENSE".replace("_", " ");
        }
        if (code == 163) {
            return "ANTITANK".replace("_", " ");
        }
        if (code == 164) {
            return "INFANTRY FIGHTING VEHICLE".replace("_", " ");
        }
        if (code == 170) {
            return "ROCKET".replace("_", " ");
        }
        if (code == 171) {
            return "SURFACE-TO-SURFACE_MISSILE".replace("_", " ");
        }
        if (code == 172) {
            return "SURFACE-TO-AIR_MISSILE".replace("_", " ");
        }
        if (code == 180) {
            return "AIR_FORCE_GENERAL_AVIATION".replace("_", " ");
        }
        if (code == 181) {
            return "MARINE_AIRCRAFT_ATTACK".replace("_", " ");
        }
        if (code == 182) {
            return "MARINE_AIRCRAFT_ALL_WEATHER_ATTACK".replace("_", " ");
        }
        if (code == 183) {
            return "MARINE_AIRCRAFT_FIGHTER".replace("_", " ");
        }
        if (code == 184) {
            return "MARINE_AIRCRAFT_TRANSPORT/REFUELER".replace("_", " ");
        }
        if (code == 185) {
            return "MARINE_AIRCRAFT_OBSERVATION".replace("_", " ");
        }
        if (code == 186) {
            return "MARINE_HELICOPTER".replace("_", " ");
        }
        if (code == 187) {
            return "MARINE_HELICOPTER_LIGHT_ATTACK".replace("_", " ");
        }
        if (code == 188) {
            return "MARINE_HELICOPTER_MEDIUM".replace("_", " ");
        }
        if (code == 189) {
            return "MARINE_HELICOPTER_HEAVY".replace("_", " ");
        }
        if (code == 190) {
            return "ARMY_HELICOPTER".replace("_", " ");
        }
        if (code == 191) {
            return "ARMY_FIXED_WING".replace("_", " ");
        }
        if (code == 193) {
            return "ARMY_GENERAL_AVIATION".replace("_", " ");
        }
        if (code == 194) {
            return "AIR_FORCE_FIGHTER".replace("_", " ");
        }
        if (code == 195) {
            return "AIR_FORCE_BOMBER".replace("_", " ");
        }
        if (code == 196) {
            return "AIR_FORCE_TRANSPORT".replace("_", " ");
        }
        if (code == 198) {
            return "UNMANNED_AIR_RECONNAISSANCE".replace("_", " ");
        }
        if (code == 199) {
            return "MARINES".replace("_", " ");
        }
        if (code == 200) {
            return "AIRMOBILE".replace("_", " ");
        }
        if (code == 201) {
            return "AIRMOBILE_WITH_ORGANIC_AIRCRAFT".replace("_", " ");
        }
        if (code == 203) {
            return "RECONNAISSANCE_OR_CAVALRY".replace("_", " ");
        }
        if (code == 205) {
            return "SIGNAL_COMMUNICATIONS".replace("_", " ");
        }
        if (code == 207) {
            return "ELECTRONIC_WARFARE".replace("_", " ");
        }
        if (code == 210) {
            return "NUCLEAR_BIOLOGICAL_CHEMICAL".replace("_", " ");
        }
        if (code == 215) {
            return "SHORE_PARTY_UNIT".replace("_", " ");
        }
        if (code == 216) {
            return "AIR_NAVAL_GUNFIRE_LIAISON_COMPANY_UNIT".replace("_", " ");
        }
        if (code == 217) {
            return "BATTALION_LANDING_TEAM".replace("_", " ");
        }
        if (code == 218) {
            return "SPECIAL_FORCES".replace("_", " ");
        }
        if (code == 219) {
            return "REPLACEMENT".replace("_", " ");
        }
        if (code == 220) {
            return "IRREGULAR_FORCES".replace("_", " ");
        }
        if (code == 221) {
            return "MOTORIZED_TEMPORARY".replace("_", " ");
        }
        if (code == 222) {
            return "MOTORIZED_CROSS_COUNTRY".replace("_", " ");
        }
        if (code == 225) {
            return "JOINT_FORCES".replace("_", " ");
        }
        if (code == 226) {
            return "ALLIED_FORCES".replace("_", " ");
        }
        if (code == 230) {
            return "ENGINEERING".replace("_", " ");
        }
        if (code == 231) {
            return "TRANSPORTATION".replace("_", " ");
        }
        if (code == 232) {
            return "ORDNANCE".replace("_", " ");
        }
        if (code == 233) {
            return "SUPPLY".replace("_", " ");
        }
        if (code == 234) {
            return "REPAIR_AND_MAINTENANCE".replace("_", " ");
        }
        if (code == 235) {
            return "TOPOGRAPHIC".replace("_", " ");
        }
        if (code == 236) {
            return "METEOROLOGICAL_AND_OCEANOGRAPHIC".replace("_", " ");
        }
        if (code == 240) {
            return "SERVICE_SUPPORT_GROUP".replace("_", " ");
        }
        if (code == 241) {
            return "BRIGADE_SERVICE_SUPPORT_GROUP".replace("_", " ");
        }
        if (code == 242) {
            return "FORCE_SERVICE_SUPPORT_GROUP".replace("_", " ");
        }
        if (code == 245) {
            return "MEDICAL".replace("_", " ");
        }
        if (code == 246) {
            return "DENTAL".replace("_", " ");
        }
        if (code == 250) {
            return "MILITARY_INTELLIGENCE".replace("_", " ");
        }
        if (code == 251) {
            return "DATA_PROCESSING_UNIT".replace("_", " ");
        }
        if (code == 255) {
            return "PSYCHOLOGICAL_WARFARE".replace("_", " ");
        }
        if (code == 256) {
            return "ARMY_SECURITY_AGENCY".replace("_", " ");
        }
        if (code == 257) {
            return "CIVIL_AFFAIRS".replace("_", " ");
        }
        if (code == 258) {
            return "MILITARY_POLICE".replace("_", " ");
        }
        if (code == 260) {
            return "SERVICE".replace("_", " ");
        }
        if (code == 261) {
            return "FINANCE".replace("_", " ");
        }
        if (code == 262) {
            return "PERSONNEL_SERVICE_AND_ADMIN".replace("_", " ");
        }
        if (code == 263) {
            return "POSTAL".replace("_", " ");
        }
        if (code == 264) {
            return "QUARTERMASTER".replace("_", " ");
        }
        if (code == 265) {
            return "VETERINARY".replace("_", " ");
        }
        if (code == 280) {
            return "AIR_DEFENSE_RADAR".replace("_", " ");
        }
        if (code == 281) {
            return "ARTILLERY_LOCATING_RADAR".replace("_", " ");
        }
        if (code == 282) {
            return "GROUND_SENSOR/GROUND_SURVEILLANCE_RADAR".replace("_", " ");
        }
        if (code == 285) {
            return "AUTOMATIC_DATA_PROCESSING_CENTRAL".replace("_", " ");
        }
        if (code == 287) {
            return "DIRECTION_FINDING".replace("_", " ");
        }
        if (code == 288) {
            return "INTERCEPT".replace("_", " ");
        }
        if (code == 290) {
            return "ELECTRONIC_WARFARE_CENTER".replace("_", " ");
        }
        if (code == 292) {
            return "JAMMING".replace("_", " ");
        }
        if (code == 295) {
            return "TARGET_DESIGNATOR".replace("_", " ");
        }
        if (code == 296) {
            return "COORDINATING_POINT".replace("_", " ");
        }
        if (code == 297) {
            return "NAV_REFERENCE".replace("_", " ");
        }
        if (code == 300) {
            return "SIGNAL_COMMUNICATIONS_CENTER".replace("_", " ");
        }
        if (code == 301) {
            return "RADIO_RELAY_STATION".replace("_", " ");
        }
        if (code == 302) {
            return "RADIO_WIRELESS_STATION".replace("_", " ");
        }
        if (code == 303) {
            return "RADIO_WIRELESS_STATION_DUMMY".replace("_", " ");
        }
        if (code == 305) {
            return "TELEPHONE/TELEPHONE_SWITCHING_CENTER_NOT_AT_HQ".replace("_", " ");
        }
        if (code == 306) {
            return "TELEPRINTER_CENTER".replace("_", " ");
        }
        if (code == 308) {
            return "AIR_TRAFFIC_CONTROL".replace("_", " ");
        }
        if (code == 309) {
            return "NAVIGATIONAL_AIDS".replace("_", " ");
        }
        if (code == 312) {
            return "SPECIAL_ELECTRONIC_DEVICES".replace("_", " ");
        }
        if (code == 315) {
            return "NON-COMMUNICATION_ELECTRONICS_SPACE".replace("_", " ");
        }
        if (code == 320) {
            return "UNKNOWN_ELECTRONICS".replace("_", " ");
        }
        if (code == 325) {
            return "LISTENING_OR_OBSERVATION_POST".replace("_", " ");
        }
        if (code == 326) {
            return "ARTILLERY_LP/OP".replace("_", " ");
        }
        if (code == 327) {
            return "INFANTRY_LP/OP".replace("_", " ");
        }
        if (code == 328) {
            return "RECON_LP/OP".replace("_", " ");
        }
        if (code == 335) {
            return "AIRFIELD".replace("_", " ");
        }
        if (code == 336) {
            return "LANDING_STRIP".replace("_", " ");
        }
        if (code == 337) {
            return "HELICOPTER_LANDING_AREA".replace("_", " ");
        }
        if (code == 350) {
            return "SUPPLY_POINT".replace("_", " ");
        }
        if (code == 351) {
            return "AMMUNITION_SUPPLY_POINT".replace("_", " ");
        }
        if (code == 352) {
            return "AMMUNITION_SUPPLY_POINT_ARTILLERY".replace("_", " ");
        }
        if (code == 353) {
            return "AMMUN_SUPPLY_PT_ROCKET_GUIDED_MIS".replace("_", " ");
        }
        if (code == 354) {
            return "AMMUNITION_SUPPLY_POINT_SMALL_ARMS".replace("_", " ");
        }
        if (code == 355) {
            return "AMMUNITION_SUPPLY_POINT_SPECIAL".replace("_", " ");
        }
        if (code == 356) {
            return "AMMUNITION_SUPPLY_POINT_CONVENTIONAL".replace("_", " ");
        }
        if (code == 357) {
            return "AMMUNITION_SUPPLY_POINT_AIR_DEFENSE".replace("_", " ");
        }
        if (code == 358) {
            return "AMMUNITION_SUPPLY_POINT_NUCLEAR".replace("_", " ");
        }
        if (code == 360) {
            return "POL".replace("_", " ");
        }
        if (code == 375) {
            return "MILITARY_INSTALLATION_FACILITY".replace("_", " ");
        }
        if (code == 376) {
            return "NAVAL_INSTALLATION".replace("_", " ");
        }
        if (code == 377) {
            return "FORT_STRONGPOINT_BUNKER".replace("_", " ");
        }
        if (code == 378) {
            return "FORTIFIED_AREA_DEFENSE_SYSTEM".replace("_", " ");
        }
        if (code == 379) {
            return "CBR_FACILITY".replace("_", " ");
        }
        if (code == 381) {
            return "PRISONER_OF_WAR_CAMP_MILITARY_PRISON".replace("_", " ");
        }
        if (code == 384) {
            return "TERRORIST_FACILITY".replace("_", " ");
        }
        if (code == 387) {
            return "SUSPECTED_NARCOTICS_TRAFFICKING_FACILITY".replace("_", " ");
        }
        if (code == 390) {
            return "BRIDGE".replace("_", " ");
        }
        if (code == 391) {
            return "RAILWAY_BRIDGE".replace("_", " ");
        }
        if (code == 392) {
            return "CHOKEPOINT".replace("_", " ");
        }
        if (code == 393) {
            return "TUNNEL".replace("_", " ");
        }
        if (code == 394) {
            return "RAILWAY_TUNNEL".replace("_", " ");
        }
        if (code == 395) {
            return "FERRY".replace("_", " ");
        }
        if (code == 396) {
            return "FORD".replace("_", " ");
        }
        if (code == 400) {
            return "BEACH_SUITABLE_FOR_LANDING".replace("_", " ");
        }
        if (code == 401) {
            return "BEACH_NOT_SUITABLE_FOR_LANDING".replace("_", " ");
        }
        if (code == 405) {
            return "MEDICAL_INSTALLATION_FACILITY".replace("_", " ");
        }
        if (code == 410) {
            return "CIVILIAN_INSTALLATION_FACILITY".replace("_", " ");
        }
        if (code == 411) {
            return "DAM".replace("_", " ");
        }
        if (code == 412) {
            return "POWER_PLANT".replace("_", " ");
        }
        if (code == 413) {
            return "NUCLEAR_POWER_PLANT".replace("_", " ");
        }
        if (code == 415) {
            return "TRANSPORTATION_AREA".replace("_", " ");
        }
        if (code == 420) {
            return "URBAN_AREA".replace("_", " ");
        }
        if (code == 430) {
            return "PRODUCTION_FACILITY".replace("_", " ");
        }
        if (code == 431) {
            return "AMMUNITION_EXPLOSIVES_PRODUCTION".replace("_", " ");
        }
        if (code == 432) {
            return "CBR_PRODUCTION/STORAGE".replace("_", " ");
        }
        if (code == 450) {
            return "EXPIRED_SONOBUOY".replace("_", " ");
        }
        if (code == 451) {
            return "SONOBUOY_ACTIVE".replace("_", " ");
        }
        if (code == 452) {
            return "SONOBUOY_LOFAR".replace("_", " ");
        }
        if (code == 453) {
            return "SONOBUOY_DICASS".replace("_", " ");
        }
        if (code == 565) {
            return "TERRORIST_INCIDENT".replace("_", " ");
        }
        if (code == 570) {
            return "BARRIER".replace("_", " ");
        }
        if (code == 571) {
            return "TANK_OBSTACLE".replace("_", " ");
        }
        if (code == 575) {
            return "HAZARD".replace("_", " ");
        }
        if (code == 576) {
            return "HAZARD_MINE".replace("_", " ");
        }
        if (code == 577) {
            return "HAZARD_NAVIGATIONAL".replace("_", " ");
        }
        if (code == 580) {
            return "BOMBING_RANGE".replace("_", " ");
        }
        if (code == 582) {
            return "DESTROYED/DEMOLISHED_AREA".replace("_", " ");
        }
        if (code == 585) {
            return "MAN_IN_WATER".replace("_", " ");
        }
        if (code == 586) {
            return "DITCHED_AIRCRAFT".replace("_", " ");
        }
        if (code == 587) {
            return "DISTRESSED_VESSEL".replace("_", " ");
        }
        if (code == 595) {
            return "HURRICANE_TYPHOON-NORTHERN_HEMISPHERE".replace("_", " ");
        }
        if (code == 596) {
            return "HURRICANE_TYPHOON-SOUTHERN_HEMISPHERE".replace("_", " ");
        }
        if (code == 597) {
            return "TROPICAL_STORM-NORTHERN_HEMISPHERE".replace("_", " ");
        }
        if (code == 598) {
            return "TROPICAL_STORM-SOUTHERN_HEMISPHERE".replace("_", " ");
        }
        if (code == 599) {
            return "TROPICAL_DEPRESSION".replace("_", " ");
        }
        if (code == 600) {
            return "SMOKE".replace("_", " ");
        }
        if (code == 601) {
            return "HAZE".replace("_", " ");
        }
        if (code == 602) {
            return "DUST/SAND".replace("_", " ");
        }
        if (code == 603) {
            return "BLOWING_DUST/SAND".replace("_", " ");
        }
        if (code == 604) {
            return "DUST/SAND_STORM".replace("_", " ");
        }
        if (code == 605) {
            return "GROUND_FOG".replace("_", " ");
        }
        if (code == 606) {
            return "FOG".replace("_", " ");
        }
        if (code == 607) {
            return "DRIZZLE_INTERMITTENT".replace("_", " ");
        }
        if (code == 608) {
            return "DRIZZLE_LIGHT".replace("_", " ");
        }
        if (code == 609) {
            return "DRIZZLE_MODERATE".replace("_", " ");
        }
        if (code == 610) {
            return "DRIZZLE_HEAVY".replace("_", " ");
        }
        if (code == 611) {
            return "FREEZING_DRIZZLE".replace("_", " ");
        }
        if (code == 612) {
            return "RAIN_INTERMITTENT".replace("_", " ");
        }
        if (code == 613) {
            return "RAIN_LIGHT".replace("_", " ");
        }
        if (code == 614) {
            return "RAIN_MODERATE".replace("_", " ");
        }
        if (code == 615) {
            return "RAIN_HEAVY".replace("_", " ");
        }
        if (code == 616) {
            return "FREEZING_RAIN".replace("_", " ");
        }
        if (code == 617) {
            return "RAIN_&_SNOW_MIXED".replace("_", " ");
        }
        if (code == 618) {
            return "SNOW_LIGHT".replace("_", " ");
        }
        if (code == 619) {
            return "SNOW_MODERATE".replace("_", " ");
        }
        if (code == 620) {
            return "SNOW_HEAVY".replace("_", " ");
        }
        if (code == 621) {
            return "RAIN_SHOWERS_LIGHT".replace("_", " ");
        }
        if (code == 622) {
            return "RAIN_SHOWERS_MODERATE".replace("_", " ");
        }
        if (code == 623) {
            return "RAIN_SHOWERS_HEAVY".replace("_", " ");
        }
        if (code == 624) {
            return "SHOWERS_OF_RAIN_&_SNOW_MIXED".replace("_", " ");
        }
        if (code == 625) {
            return "SNOW_SHOWERS".replace("_", " ");
        }
        if (code == 626) {
            return "SNOW_SHOWERS_HEAVY".replace("_", " ");
        }
        if (code == 627) {
            return "THUNDERSTORM".replace("_", " ");
        }
        if (code == 628) {
            return "THUNDERSTORM_WITH_RAIN_SHOWERS".replace("_", " ");
        }
        if (code == 629) {
            return "CLEAR_ICING_LIGHT".replace("_", " ");
        }
        if (code == 630) {
            return "CLEAR_ICING_MODERATE".replace("_", " ");
        }
        if (code == 631) {
            return "CLEAR_ICING_SEVERE".replace("_", " ");
        }
        if (code == 632) {
            return "MIXED_ICING_LIGHT".replace("_", " ");
        }
        if (code == 633) {
            return "MIXED_ICING_MODERATE".replace("_", " ");
        }
        if (code == 634) {
            return "MIXED_ICING_SEVERE".replace("_", " ");
        }
        if (code == 635) {
            return "RIME_ICING_LIGHT".replace("_", " ");
        }
        if (code == 636) {
            return "RIME_ICING_MODERATE".replace("_", " ");
        }
        if (code == 637) {
            return "RIME_ICING_SEVERE".replace("_", " ");
        }
        if (code == 638) {
            return "CLEAR_AIR_TURBULENCE_LIGHT".replace("_", " ");
        }
        if (code == 639) {
            return "CLEAR_AIR_TURBULENCE_MODERATE".replace("_", " ");
        }
        if (code == 640) {
            return "CLEAR_AIR_TURBULENCE_SEVERE".replace("_", " ");
        }
        if (code == 641) {
            return "SHEAR/INSTABILITY_LINE".replace("_", " ");
        }
        if (code == 642) {
            return "TROUGH_AXIS".replace("_", " ");
        }
        if (code == 643) {
            return "CLEAR_AIR_TURBULENCE_AREA_OUTLINE".replace("_", " ");
        }
        if (code == 644) {
            return "ICING_AREA_OUTLINE".replace("_", " ");
        }
        if (code == 645) {
            return "THUNDERSTORM_CONVECTIVE_AREA_OUTLINE".replace("_", " ");
        }
        if (code == 646) {
            return "VECTOR".replace("_", " ");
        }
        if (code == 650) {
            return "OTHER_LINK_VALUES".replace("_", " ");
        }
        if (code == 651) {
            return "ASW_BEARING_LINE".replace("_", " ");
        }
        if (code == 652) {
            return "ASW_ACOUSTIC_FIX".replace("_", " ");
        }
        if (code == 653) {
            return "OTHER_ASW_VALUES".replace("_", " ");
        }
        if (code == 654) {
            return "DATUM".replace("_", " ");
        }
        if (code == 655) {
            return "CONTACT_STATUS".replace("_", " ");
        }
        if (code == 656) {
            return "FIX".replace("_", " ");
        }
        if (code == 657) {
            return "ECM_FIX".replace("_", " ");
        }
        if (code == 658) {
            return "EMERGENCY".replace("_", " ");
        }
        return "EMPTY_UNIT".replace("_", " ");
    }

    public static String getEchelonString(int code) {
        if (code == 1012) {
            return "TASK FORCE (COMPANY)";
        }
        if (code == 1013) {
            return "TASK FORCE (BATTALION)";
        }
        if (code == 1014) {
            return "TASK FORCE (GROUP)";
        }
        if (code == 1015) {
            return "TASK FORCE (BRIGADE)";
        }
        if (code == 1016) {
            return "TASK FORCE (DIVISION)";
        }
        if (code == 1017) {
            return "TASK FORCE (CORPS)";
        }
        if (code == 1018) {
            return "HEADQUARTERS OR COMMAND POST";
        }
        if (code == 1021) {
            return "KINGPIN (SONOBUOY)";
        }
        if (code == 1022) {
            return "PATTERN CENTER (SONOBUOY)";
        }
        if (code == 1030) {
            return "ARMY";
        }
        if (code == 1031) {
            return "ARMY GROUP/FRONT";
        }
        if (code == 1032) {
            return "REGION";
        }
        if (code == 1033) {
            return "TASK FORCE (ARMY)";
        }
        if (code == 1034) {
            return "TASK FORCE (ARMY GROUP/FRONT)";
        }
        if (code == 1035) {
            return "TASK FORCE (REGION)";
        }
        return null;
    }

    public static String getUnitEchelonString(int code) {
        if (code == 820) {
            return "NONE";
        }
        if (code == 1000) {
            return "SECTION";
        }
        if (code == 1001) {
            return "PLATOON OR DETACHMENT";
        }
        if (code == 1003) {
            return "COMPANY, BATTERY OR TROOP";
        }
        if (code == 1004) {
            return "BATTALION OR SQUADRON";
        }
        if (code == 1005) {
            return "GROUP OR REGIMENT";
        }
        if (code == 1006) {
            return "BRIGADE";
        }
        if (code == 1007) {
            return "DIVISION OR WING";
        }
        if (code == 1008) {
            return "CORPS OR FORCE";
        }
        if (code == 1009) {
            return "TASK FORCE (SQUAD)";
        }
        if (code == 1010) {
            return "TASK FORCE (SECTION)";
        }
        if (code == 1011) {
            return "TASK FORCE (PLATOON)";
        }
        if (code == 1012) {
            return "TASK FORCE (COMPANY)";
        }
        if (code == 1013) {
            return "TASK FORCE (BATTALION)";
        }
        if (code == 1014) {
            return "TASK FORCE (GROUP)";
        }
        if (code == 1015) {
            return "TASK FORCE (BRIGADE)";
        }
        if (code == 1016) {
            return "TASK FORCE (DIVISION)";
        }
        if (code == 1017) {
            return "TASK FORCE (CORPS)";
        }
        if (code == 1018) {
            return "HEADQUARTERS OR COMMAND POST";
        }
        if (code == 1021) {
            return "KINGPIN (SONOBUOY)";
        }
        if (code == 1022) {
            return "PATTERN CENTER (SONOBUOY)";
        }
        if (code == 1030) {
            return "ARMY";
        }
        if (code == 1031) {
            return "ARMY GROUP/FRONT";
        }
        if (code == 1032) {
            return "REGION";
        }
        if (code == 1033) {
            return "TASK FORCE (ARMY)";
        }
        if (code == 1034) {
            return "TASK FORCE (ARMY GROUP/FRONT)";
        }
        if (code == 1035) {
            return "TASK FORCE (REGION)";
        }
        return "NONE";
    }

    public static String getEchelonIdSegment(int echelonCode) {
        if (echelonCode == 820) {
            return "--";
        }
        if (echelonCode == 1000) {
            return "-C";
        }
        if (echelonCode == 1001) {
            return "-D";
        }
        if (echelonCode == 1003) {
            return "-E";
        }
        if (echelonCode == 1004) {
            return "-F";
        }
        if (echelonCode == 1005) {
            return "-G";
        }
        if (echelonCode == 1006) {
            return "-H";
        }
        if (echelonCode == 1007) {
            return "-I";
        }
        if (echelonCode == 1008) {
            return "-J";
        }
        if (echelonCode == 1030) {
            return "-K";
        }
        if (echelonCode == 1034) {
            return "-L";
        }
        if (echelonCode == 1032) {
            return "-M";
        }
        if (echelonCode == 1009) {
            return "BB";
        }
        if (echelonCode == 1010) {
            return "BC";
        }
        if (echelonCode == 1011) {
            return "BD";
        }
        if (echelonCode == 1012) {
            return "BE";
        }
        if (echelonCode == 1013) {
            return "BF";
        }
        if (echelonCode == 1014) {
            return "BG";
        }
        if (echelonCode == 1015) {
            return "BH";
        }
        if (echelonCode == 1016) {
            return "BI";
        }
        if (echelonCode == 1017) {
            return "BJ";
        }
        if (echelonCode == 1033) {
            return "BK";
        }
        if (echelonCode == 1034) {
            return "BL";
        }
        if (echelonCode == 1035) {
            return "BM";
        }
        return "--";
    }

    public static String getEchelonIdSegment(String echelonString) {
        if (echelonString.equals("NONE")) {
            return "--";
        }
        if (echelonString.equals("SECTION")) {
            return "-C";
        }
        if (echelonString.equals("PLATOON OR DETACHMENT")) {
            return "-D";
        }
        if (echelonString.equals("COMPANY,BATTERY OR TROOP")) {
            return "-E";
        }
        if (echelonString.equals("BATTALION OR SQUADRON")) {
            return "-F";
        }
        if (echelonString.equals("GROUP OR REGIMENT")) {
            return "-G";
        }
        if (echelonString.equals("BRIGADE")) {
            return "-H";
        }
        if (echelonString.equals("DIVISION OR WING")) {
            return "-I";
        }
        if (echelonString.equals("CORPS OR FORCE")) {
            return "-J";
        }
        if (echelonString.equals("ARMY")) {
            return "-K";
        }
        if (echelonString.equals("ARMY GROUP/FRONT")) {
            return "-L";
        }
        if (echelonString.equals("REGION")) {
            return "-M";
        }
        if (echelonString.equals("TASK FORCE (SQUAD)")) {
            return "BB";
        }
        if (echelonString.equals("TASK FORCE (SECTION)")) {
            return "BC";
        }
        if (echelonString.equals("TASK FORCE (PLATOON)")) {
            return "BD";
        }
        if (echelonString.equals("TASK FORCE (COMPANY)")) {
            return "BE";
        }
        if (echelonString.equals("TASK FORCE (BATTALION)")) {
            return "BF";
        }
        if (echelonString.equals("TASK FORCE (GROUP)")) {
            return "BG";
        }
        if (echelonString.equals("TASK FORCE (BRIGADE)")) {
            return "BH";
        }
        if (echelonString.equals("TASK FORCE (DIVISION)")) {
            return "BI";
        }
        if (echelonString.equals("TASK FORCE (CORPS)")) {
            return "BJ";
        }
        if (echelonString.equals("TASK FORCE (ARMY)")) {
            return "BK";
        }
        if (echelonString.equals("TASK FORCE (ARMY GROUP/FRONT)")) {
            return "BL";
        }
        if (echelonString.equals("TASK FORCE (REGION)")) {
            return "BM";
        }
        return "--";
    }

    public static String getBasicSymbolFromSymbolCode(int symbolcode) {
        HashMap<Integer, String> symbolcodes = new HashMap<Integer, String>();
        symbolcodes.put(2400, "SFGP------*****");
        symbolcodes.put(120, "SFGP----A-*****");
        symbolcodes.put(121, "SFGPUCRA--*****");
        symbolcodes.put(122, "SFGPUCATA-*****");
        symbolcodes.put(123, "SFGPUCFHA-*****");
        symbolcodes.put(124, "SFGPUCIA--*****");
        symbolcodes.put(125, "SFGPUUS---*****");
        symbolcodes.put(130, "SFGPUCA---*****");
        symbolcodes.put(131, "SFGPUCIZ--*****");
        symbolcodes.put(132, "SFGPUCRVA-*****");
        symbolcodes.put(133, "SFGPUCFHE-*****");
        symbolcodes.put(134, "SFGPUCII--*****");
        symbolcodes.put(135, "SFGPUCECT-*****");
        symbolcodes.put(140, "SFGPUCI---*****");
        symbolcodes.put(141, "SFZP------*****");
        symbolcodes.put(142, "SFGPUCIO--*****");
        symbolcodes.put(143, "SFGPUCIA--*****");
        symbolcodes.put(150, "SFGPU-----MY***");
        symbolcodes.put(151, "SFGPU-----B-***");
        symbolcodes.put(152, "SFGPUC----MY***");
        symbolcodes.put(153, "SFGPCALA--*****");
        symbolcodes.put(160, "SFGPUCF---*****");
        symbolcodes.put(161, "SFGPUCFR--*****");
        symbolcodes.put(162, "SFGPEWMA--*****");
        symbolcodes.put(163, "SFGPUCAA--*****");
        symbolcodes.put(164, "SFGPUCFM--*****");
        symbolcodes.put(170, "SFGPUCFR--*****");
        symbolcodes.put(171, "SFGPUCM---*****");
        symbolcodes.put(172, "SFGPWMSA--*****");
        symbolcodes.put(180, "SFAPUCV---*****");
        symbolcodes.put(181, "SFGP------*****");
        symbolcodes.put(182, "SFGP------*****");
        symbolcodes.put(183, "SFGP------*****");
        symbolcodes.put(184, "SFGP------*****");
        symbolcodes.put(185, "SFGP------*****");
        symbolcodes.put(186, "SFGP------*****");
        symbolcodes.put(187, "SFGP------*****");
        symbolcodes.put(188, "SFGP------*****");
        symbolcodes.put(189, "SFGP------*****");
        symbolcodes.put(190, "SFGPUCV---*****");
        symbolcodes.put(191, "SFAPMF----*****");
        symbolcodes.put(193, "SFAPMF----*****");
        symbolcodes.put(194, "SFAPMFF---*****");
        symbolcodes.put(195, "SFAPMFB---*****");
        symbolcodes.put(196, "SFAPMFC---*****");
        symbolcodes.put(198, "SFAPMFQR--*****");
        symbolcodes.put(199, "SFGP------*****");
        symbolcodes.put(200, "SFAP------*****");
        symbolcodes.put(201, "SFGP------*****");
        symbolcodes.put(203, "SFAPMFQR--*****");
        symbolcodes.put(205, "SFGPUUSC--*****");
        symbolcodes.put(207, "SFGPMFQJ--*****");
        symbolcodes.put(210, "SFGPUUAN--*****");
        symbolcodes.put(215, "SFGPUULS--*****");
        symbolcodes.put(216, "SFSPU-----*****");
        symbolcodes.put(217, "SFGP*****");
        symbolcodes.put(218, "S*F*GS----*****");
        symbolcodes.put(219, "SUGPUSAX--*****");
        symbolcodes.put(220, "SFZP------*****");
        symbolcodes.put(221, "SFGPUCSGM-*****");
        symbolcodes.put(222, "SFGPEVUR--*****");
        symbolcodes.put(225, "SFZP------*****");
        symbolcodes.put(226, "SFZP------*****");
        symbolcodes.put(230, "SFGPIMN---H****");
        symbolcodes.put(231, "SUGPIT----H****");
        symbolcodes.put(232, "S*G*USXO--*****");
        symbolcodes.put(233, "S*G*USAQ--*****");
        symbolcodes.put(234, "EUFPLH----H****");
        symbolcodes.put(235, "WO--ISS--------");
        symbolcodes.put(236, "E*N*AG----*****");
        symbolcodes.put(240, "S*G*US-----G***");
        symbolcodes.put(241, "S*G*US-----H***");
        symbolcodes.put(242, "S*G*US-----J***");
        symbolcodes.put(245, "S*G*USM---*****");
        symbolcodes.put(246, "S*G*USMD--*****");
        symbolcodes.put(250, "S*G*UUM---*****");
        symbolcodes.put(251, "S*G*UUS---*****");
        symbolcodes.put(255, "S*G*USMP--*****");
        symbolcodes.put(256, "OFIPF-----*****");
        symbolcodes.put(257, "S*G*GC----*****");
        symbolcodes.put(258, "S*G*UULS--*****");
        symbolcodes.put(260, "S*G*US----*****");
        symbolcodes.put(261, "SFZP------*****");
        symbolcodes.put(262, "S*G*USAS--*****");
        symbolcodes.put(263, "S*G*USAO--*****");
        symbolcodes.put(264, "S*G*USAQ--*****");
        symbolcodes.put(265, "S*G*USMV--*****");
        symbolcodes.put(280, "S*G*ESR---*****");
        symbolcodes.put(281, "S*G*UCFTR-*****");
        symbolcodes.put(282, "S*G*ES----*****");
        symbolcodes.put(285, "S*G*UUS---*****");
        symbolcodes.put(287, "S*G*UUMSED*****");
        symbolcodes.put(288, "S*G*UUMSEI*****");
        symbolcodes.put(290, "S*G*UUMSE-*****");
        symbolcodes.put(292, "S*G*UUMSEJ*****");
        symbolcodes.put(295, "G*GPDPT---****X");
        symbolcodes.put(296, "G*GPGPPO--****X");
        symbolcodes.put(297, "G*GPGPRN--****X");
        symbolcodes.put(300, "SFGPUUS---*****");
        symbolcodes.put(301, "S*G*UUSRW-*****");
        symbolcodes.put(302, "S*G*UUSRW-*****");
        symbolcodes.put(303, "S*G*UUSRW-*****");
        symbolcodes.put(305, "S*G*UUSQ--*****");
        symbolcodes.put(306, "S*G*UUSRT-*****");
        symbolcodes.put(308, "I*G*SRAT----***");
        symbolcodes.put(309, "SFZP------*****");
        symbolcodes.put(312, "SFZP------*****");
        symbolcodes.put(315, "SFZP------*****");
        symbolcodes.put(320, "SFZP------*****");
        symbolcodes.put(325, "SFZP------*****");
        symbolcodes.put(326, "SFZP------*****");
        symbolcodes.put(327, "SFZP------*****");
        symbolcodes.put(328, "SFZP------*****");
        symbolcodes.put(335, "SFZP------*****");
        symbolcodes.put(336, "SFZP------*****");
        symbolcodes.put(337, "E*F*LF----H****");
        symbolcodes.put(350, "G*S*PS----****X");
        symbolcodes.put(351, "G*S*PS----*****");
        symbolcodes.put(352, "G*S*PS----*****");
        symbolcodes.put(353, "G*S*PS----*****");
        symbolcodes.put(354, "G*S*PS----*****");
        symbolcodes.put(355, "G*S*PS----*****");
        symbolcodes.put(356, "G*S*PS----*****");
        symbolcodes.put(357, "G*S*PS----*****");
        symbolcodes.put(358, "G*S*PS----*****");
        symbolcodes.put(360, "SFZP------*****");
        symbolcodes.put(375, "S*GPIB----H****");
        symbolcodes.put(376, "S*GPIB----H****");
        symbolcodes.put(377, "SFZP------*****");
        symbolcodes.put(378, "SFZP------*****");
        symbolcodes.put(379, "SFZP------*****");
        symbolcodes.put(381, "SFZP------*****");
        symbolcodes.put(384, "SFZP------*****");
        symbolcodes.put(387, "SFZP------*****");
        symbolcodes.put(390, "S*G*EVEB--*****");
        symbolcodes.put(391, "S*G*EVEB--*****");
        symbolcodes.put(392, "SFZP------*****");
        symbolcodes.put(393, "E*F*LP----H****");
        symbolcodes.put(394, "E*F*LP----H****");
        symbolcodes.put(395, "E*F*LE----H****");
        symbolcodes.put(396, "WOS-MF----P----");
        symbolcodes.put(400, "WO-DHCB-----A--");
        symbolcodes.put(401, "WO-DHCB-----A--");
        symbolcodes.put(405, "E*O*AC----H****");
        symbolcodes.put(410, "EFFPI-----H****");
        symbolcodes.put(411, "E*F*MB----H****");
        symbolcodes.put(412, "EFFPEA----H****");
        symbolcodes.put(413, "EFFPEA----H****");
        symbolcodes.put(415, "SFZP------*****");
        symbolcodes.put(420, "SFZP------*****");
        symbolcodes.put(430, "EUFPCF----H****");
        symbolcodes.put(431, "EUFPCB----H****");
        symbolcodes.put(432, "EUFPCA----H****");
        symbolcodes.put(450, "G*G*GPUYX-****X");
        symbolcodes.put(451, "G*G*GPUY--****X");
        symbolcodes.put(452, "G*G*GPUY--****X");
        symbolcodes.put(453, "G*G*GPUY--****X");
        symbolcodes.put(565, "SFZP------*****");
        symbolcodes.put(570, "EUFPMB----H****");
        symbolcodes.put(571, "G*M*OA----****X");
        symbolcodes.put(575, "G*O*H-----****X");
        symbolcodes.put(576, "G*O*HM----****X");
        symbolcodes.put(577, "G*O*HN----****X");
        symbolcodes.put(580, "SFZP------*****");
        symbolcodes.put(582, "SFZP------*****");
        symbolcodes.put(585, "SFZP------*****");
        symbolcodes.put(586, "G*O*ED----****X");
        symbolcodes.put(587, "G*O*EV----****X");
        symbolcodes.put(595, "WAS-WSTSS-P----");
        symbolcodes.put(596, "WAS-WSTSS-P----");
        symbolcodes.put(597, "WAS-WSTSS-P----");
        symbolcodes.put(598, "WAS-WSTSS-P----");
        symbolcodes.put(599, "WAS-WSTSD-P----");
        symbolcodes.put(600, "WAS-WSFU--P----");
        symbolcodes.put(601, "WAS-WSHZ--P----");
        symbolcodes.put(602, "WAS-WSDB--P----");
        symbolcodes.put(603, "SFZP------*****");
        symbolcodes.put(604, "WAS-WSDSLMP----");
        symbolcodes.put(605, "WAS-WSFG-------");
        symbolcodes.put(606, "WAS-WSFG-------");
        symbolcodes.put(607, "WAS-WSS--------");
        symbolcodes.put(608, "WAS-WSD-LIP----");
        symbolcodes.put(609, "WAS-WSD-MIP----");
        symbolcodes.put(610, "WAS-WSD-HIP----");
        symbolcodes.put(611, "WAS-WSDF-------");
        symbolcodes.put(612, "WAS-WSR-LIP----");
        symbolcodes.put(613, "WAS-WSR-LIP----");
        symbolcodes.put(614, "WAS-WSR-MIP----");
        symbolcodes.put(615, "WAS-WSR-HIP----");
        symbolcodes.put(616, "SFZP------*****");
        symbolcodes.put(617, "WAS-WSM--------");
        symbolcodes.put(618, "WAS-WSS-LCP----");
        symbolcodes.put(619, "WAS-WSS-MCP----");
        symbolcodes.put(620, "WAS-WSS-HCP----");
        symbolcodes.put(621, "WAS-WSR-LCP----");
        symbolcodes.put(622, "WAS-WSR-MCP----");
        symbolcodes.put(623, "WAS-WSR-HCP----");
        symbolcodes.put(624, "WAS-WSM--------");
        symbolcodes.put(625, "WAS-WSSS-------");
        symbolcodes.put(626, "WAS-WSSSMHP----");
        symbolcodes.put(627, "WAS-WST-NPP----");
        symbolcodes.put(628, "WAS-WSTMR-P----");
        symbolcodes.put(629, "WAS-ICL---P----");
        symbolcodes.put(630, "WAS-ICM---P----");
        symbolcodes.put(631, "WAS-ICS---P----");
        symbolcodes.put(632, "WAS-IML---P----");
        symbolcodes.put(633, "WAS-IMM---P----");
        symbolcodes.put(634, "WAS-IMS---P----");
        symbolcodes.put(635, "WAS-IRL---P----");
        symbolcodes.put(636, "WAS-IRM---P----");
        symbolcodes.put(637, "WAS-IRS---P----");
        symbolcodes.put(638, "WAS-TL----P----");
        symbolcodes.put(639, "WAS-TM----P----");
        symbolcodes.put(640, "WAS-TS----P----");
        symbolcodes.put(641, "WA-DPXIL---L---");
        symbolcodes.put(642, "WA--PX---------");
        symbolcodes.put(643, "WAS-CCCSCSP----");
        symbolcodes.put(644, "WA-DBAI-----A--");
        symbolcodes.put(645, "WA-DBAT-----A--");
        symbolcodes.put(646, "SFZP------*****");
        symbolcodes.put(650, "SFZP------*****");
        symbolcodes.put(651, "G*G*GPCUA-****X");
        symbolcodes.put(652, "G*G*GPCUA-****X");
        symbolcodes.put(653, "G*G*GPCUA-****X");
        symbolcodes.put(654, "G*G*GPUUD-****X");
        symbolcodes.put(655, "SFZP------*****");
        symbolcodes.put(656, "G*O*BO----****X");
        symbolcodes.put(657, "G*O*BO----****X");
        symbolcodes.put(658, "G*O*E-----****X");

        if (symbolcodes.containsKey(symbolcode)) {
            return symbolcodes.get(symbolcode);
        } else {
            return symbolcodes.get(2400);
        }
    }

    public static SimpleSymbol getSymbolFromCodes(int symbolCode, int echeloncode, String color) {
        String symbol = getBasicSymbolFromSymbolCode(symbolCode);
        symbol = symbol.substring(0, 1) + MILSymbolUtil.getAffiliationFromLineColor(color) + symbol.substring(2, symbol.length());
        String scheme = MILSymbolUtil.getCodeScheme(symbol.substring(0, 1));
        String identity = MILSymbolUtil.getAffiliationFromLineColor(color);
        String dimension = MILSymbolUtil.getDimensionString(symbol.substring(2, 3));
        String status = MILSymbolUtil.getStatus(symbol.substring(4, 5));
        String function = MILSymbolUtil.getFunctionId(symbol.substring(5, 10));
        String modifier = MILSymbolUtil.getEchelonIdSegment(getSymbolId(echeloncode));
        String country = MILSymbolUtil.getCountryString(symbol.substring(13, 14));
        String oob = MILSymbolUtil.getOrderOfBattle(symbol.substring(14, symbol.length()));
        return new SimpleSymbol(symbol, scheme, identity, dimension, status, function, modifier, country, oob);
    }

    public static String getSymbolId(int code) {
        if (code == 2400) {
            return "S*G*U-----*****";
        }
        if (code == 120) {
            return "S*A*MFD---*****";
        }
        if (code == 121) {
            return "S*G*UCRA--*****";
        }
        if (code == 122) {
            return "S*G*UCATA-*****";
        }
        if (code == 123) {
            return "S*G*UCFHA-*****";
        }
        if (code == 124) {
            return "S*G*UCIA--*****";
        }
        if (code == 125) {
            return "S*G*UUS---*****";
        }
        if (code == 130) {
            return "S*G*EVA---*****";
        }
        if (code == 131) {
            return "S*G*UCIZ--*****";
        }
        if (code == 132) {
            return "S*G*UCRVA-*****";
        }
        if (code == 133) {
            return "S*G*UCF---*****";
        }
        if (code == 134) {
            return "S*G*EVAI--*****";
        }
        if (code == 135) {
            return "S*G*UCECT-*****";
        }
        if (code == 140) {
            return "S*G*UCI---*****";
        }
        if (code == 141) {
            return "S*G*UCRR--*****";
        }
        if (code == 142) {
            return "S*G*UCIO--*****";
        }
        if (code == 143) {
            return "S*G*UCIA--*****";
        }
        if (code == 150) {
            return "S*G*UCATW-*****";
        }
        if (code == 151) {
            return "S*G*UCATW-*****";
        }
        if (code == 152) {
            return "S*G*UCATW-*****";
        }
        if (code == 153) {
            return "S*G*UCATW-*****";
        }
        if (code == 160) {
            return "S*G*UCF---*****";
        }
        if (code == 161) {
            return "S*G*UCFR--*****";
        }
        if (code == 162) {
            return "S*G*UCD---*****";
        }
        if (code == 163) {
            return "S*G*EWMT--*****";
        }
        if (code == 164) {
            return "S*G*UCII--*****";
        }
        if (code == 170) {
            return "S*G*UCFR--*****";
        }
        if (code == 171) {
            return "S*A*WMSS--*****";
        }
        if (code == 172) {
            return "S*A*WMSA--*****";
        }
        if (code == 180) {
            return "S*G*UCV---*****";
        }
        if (code == 181) {
            return "S*A*MFA---*****";
        }
        if (code == 182) {
            return "S*A*MFA---*****";
        }
        if (code == 183) {
            return "S*A*MFF---*****";
        }
        if (code == 184) {
            return "S*A*MFC---*****";
        }
        if (code == 185) {
            return "S*A*MFQR--*****";
        }
        if (code == 186) {
            return "S*A*MH----*****";
        }
        if (code == 187) {
            return "S*A*MHA---*****";
        }
        if (code == 188) {
            return "S*A*MHUM--*****";
        }
        if (code == 189) {
            return "S*A*MHUH--*****";
        }
        if (code == 190) {
            return "S*A*MH----*****";
        }
        if (code == 191) {
            return "S*A*MF----*****";
        }
        if (code == 193) {
            return "S*G*UCV---*****";
        }
        if (code == 194) {
            return "S*A*MFF---*****";
        }
        if (code == 195) {
            return "S*A*MFB---*****";
        }
        if (code == 196) {
            return "S*A*MFC---*****";
        }
        if (code == 198) {
            return "S*A*MFQ---*****";
        }
        if (code == 199) {
            return "S*G*UCRR--*****";
        }
        if (code == 200) {
            return "S*A*------*****";
        }
        if (code == 201) {
            return "S*A*------*****";
        }
        if (code == 203) {
            return "S*G*UCRV--*****";
        }
        if (code == 205) {
            return "S*G*UUS---*****";
        }
        if (code == 207) {
            return "S*G*UUMSE-*****";
        }
        if (code == 210) {
            return "G*M*N-----****X";
        }
        if (code == 215) {
            return "S*G*UULS--*****";
        }
        if (code == 216) {
            return "S*S*GU----*****";
        }
        if (code == 217) {
            return "S*G*UUP---*****";
        }
        if (code == 218) {
            return "S*F*------*****";
        }
        if (code == 219) {
            return "S*G*USAX--*****";
        }
        if (code == 220) {
            return "S*G*UCS---*****";
        }
        if (code == 221) {
            return "S*G*UCIM--*****";
        }
        if (code == 222) {
            return "S*G*EVUX--*****";
        }
        if (code == 225) {
            return "S*G*U-----*****";
        }
        if (code == 226) {
            return "S*G*U-----*****";
        }
        if (code == 230) {
            return "S*G*UCE---*****";
        }
        if (code == 231) {
            return "S*G*UST---*****";
        }
        if (code == 232) {
            return "S*G*USXO--*****";
        }
        if (code == 233) {
            return "S*G*USS---*****";
        }
        if (code == 234) {
            return "S*G*USX---*****";
        }
        if (code == 235) {
            return "S*G*UCE---*****";
        }
        if (code == 236) {
            return "S*G*UUMMO-*****";
        }
        if (code == 240) {
            return "S*G*US----*****";
        }
        if (code == 241) {
            return "S*G*US----*****";
        }
        if (code == 242) {
            return "S*G*US----*****";
        }
        if (code == 245) {
            return "S*G*USM---*****";
        }
        if (code == 246) {
            return "S*G*USMD--*****";
        }
        if (code == 250) {
            return "S*G*UUM---*****";
        }
        if (code == 251) {
            return "I*P*SC------***";
        }
        if (code == 255) {
            return "O*O*Y-----*****";
        }
        if (code == 256) {
            return "O*I*F- ----*****";
        }
        if (code == 257) {
            return "S*F*GC----*****";
        }
        if (code == 258) {
            return "S*G*UULM--*****";
        }
        if (code == 260) {
            return "S*G*US----*****";
        }
        if (code == 261) {
            return "S*G*USAF--*****";
        }
        if (code == 262) {
            return "S*G*USAS--*****";
        }
        if (code == 263) {
            return "S*G*USAO--*****";
        }
        if (code == 264) {
            return "S*G*USAQ--*****";
        }
        if (code == 265) {
            return "S*G*USMV--*****";
        }
        if (code == 280) {
            return "S*G*UCFTR-*****";
        }
        if (code == 281) {
            return "S*G*UCFTR-*****";
        }
        if (code == 282) {
            return "S*G*UUMRG-*****";
        }
        if (code == 285) {
            return "I*P*SC------***";
        }
        if (code == 287) {
            return "S*G*UUMSED*****";
        }
        if (code == 288) {
            return "S*G*UUMSEI*****";
        }
        if (code == 290) {
            return "S*G*UUMSE-*****";
        }
        if (code == 292) {
            return "S*A*MHJ---*****";
        }
        if (code == 295) {
            return "G*G*DPT---****X";
        }
        if (code == 296) {
            return "G*G*GPPO--****X";
        }
        if (code == 297) {
            return "G*G*GPRN--****X";
        }
        if (code == 300) {
            return "S*G*UUS---*****";
        }
        if (code == 301) {
            return "S*G*UUSRW-*****";
        }
        if (code == 302) {
            return "S*G*UUSRW-*****";
        }
        if (code == 303) {
            return "S*G*UUSRW-*****";
        }
        if (code == 305) {
            return "S*G*UUSW--*****";
        }
        if (code == 306) {
            return "S*G*UUSW--*****";
        }
        if (code == 308) {
            return "I*G*SRAT ----***";
        }
        if (code == 309) {
            return "WO--HA---------";
        }
        if (code == 312) {
            return "S*G*UUSC--*****";
        }
        if (code == 315) {
            return "S*G*UUSC--*****";
        }
        if (code == 320) {
            return "S*G*UUSC--*****";
        }
        if (code == 325) {
            return "G*G*DPO---****X";
        }
        if (code == 326) {
            return "G*G*DPO---****X";
        }
        if (code == 327) {
            return "G*G*DPOR--****X";
        }
        if (code == 328) {
            return "G*G*DPOR--****X";
        }
        if (code == 335) {
            return "S*G*IBA---H****";
        }
        if (code == 336) {
            return "S*G*IBA---H****";
        }
        if (code == 337) {
            return "E*F*LF----H****";
        }
        if (code == 350) {
            return "G*S*PS----****X";
        }
        if (code == 351) {
            return "S*G*USS5--*****";
        }
        if (code == 352) {
            return "S*G*USS5--*****";
        }
        if (code == 353) {
            return "S*G*USS5--*****";
        }
        if (code == 354) {
            return "S*G*USS5--*****";
        }
        if (code == 355) {
            return "S*G*USS5--*****";
        }
        if (code == 356) {
            return "S*G*USS5--*****";
        }
        if (code == 357) {
            return "S*G*USS5--*****";
        }
        if (code == 358) {
            return "S*G*USS5--*****";
        }
        if (code == 360) {
            return "S*G*USS3--*****";
        }
        if (code == 375) {
            return "S*G*IB----H****";
        }
        if (code == 376) {
            return "S*G*IB----H****";
        }
        if (code == 377) {
            return "G*M*SF----****X";
        }
        if (code == 378) {
            return "G*G*GAF---****X";
        }
        if (code == 379) {
            return "S*G*IUEN--H****";
        }
        if (code == 381) {
            return "G*S*AE----****X";
        }
        if (code == 384) {
            return "O*P*C-----*****";
        }
        if (code == 387) {
            return "E*F*IA----H****";
        }
        if (code == 390) {
            return "S*G*EVEB--*****";
        }
        if (code == 391) {
            return "S*G*EVEB--*****";
        }
        if (code == 392) {
            return "S*G*EVEB--*****";
        }
        if (code == 393) {
            return "E*F*LP----H****";
        }
        if (code == 394) {
            return "E*F*LP----H****";
        }
        if (code == 395) {
            return "S*S*XMF---*****";
        }
        if (code == 396) {
            return "WOS-MF----P----";
        }
        if (code == 400) {
            return "WO-DHCB-----A--";
        }
        if (code == 401) {
            return "WO--BS---------";
        }
        if (code == 405) {
            return "S*G*USMM--*****";
        }
        if (code == 410) {
            return "E*F*IA----H****";
        }
        if (code == 411) {
            return "S*G*IUED--H****";
        }
        if (code == 412) {
            return "S*G*IUE---H****";
        }
        if (code == 413) {
            return "S*G*IUEN--H****";
        }
        if (code == 415) {
            return "G*G*GAA---****X";
        }
        if (code == 420) {
            return "G*G*GAG---****X";
        }
        if (code == 430) {
            return "S*G*IMN---H****";
        }
        if (code == 431) {
            return "S*G*IME---H****";
        }
        if (code == 432) {
            return "S*G*IMC---H****";
        }
        if (code == 450) {
            return "G*G*GPUYX-*****";
        }
        if (code == 451) {
            return "G*G*GPUY--****X";
        }
        if (code == 452) {
            return "G*G*GPUY--****X";
        }
        if (code == 453) {
            return "G*G*GPUY--****X";
        }
        if (code == 565) {
            return "E*I*B-----*****";
        }
        if (code == 570) {
            return "WOS-ITBB--P----";
        }
        if (code == 571) {
            return "G*M*O-----****X";
        }
        if (code == 575) {
            return "G*O*H-----****X";
        }
        if (code == 576) {
            return "S*G*IRM---H****";
        }
        if (code == 577) {
            return "G*O*H-----****X";
        }
        if (code == 580) {
            return "S*A*WB----*****";
        }
        if (code == 582) {
            return "G*F*A-----****X";
        }
        if (code == 585) {
            return "G*O*EP----****X";
        }
        if (code == 586) {
            return "G*O*ED----****X";
        }
        if (code == 587) {
            return "G*O*EV----****X";
        }
        if (code == 595) {
            return "WAS-WSTSH-P----";
        }
        if (code == 596) {
            return "WAS-WSTSH-P----";
        }
        if (code == 597) {
            return "WAS-WSTSS-P----";
        }
        if (code == 598) {
            return "WAS-WSTSS-P----";
        }
        if (code == 599) {
            return "WAS-WSTSD-P----";
        }
        if (code == 600) {
            return "S*G*UUACS-*****";
        }
        if (code == 601) {
            return "WAS-WSHZ--P----";
        }
        if (code == 602) {
            return "WA-DBAD-----A--";
        }
        if (code == 603) {
            return "WAS-WSDB--P----";
        }
        if (code == 604) {
            return "WAS-WSDSLMP----";
        }
        if (code == 605) {
            return "WAS-WSFG-------";
        }
        if (code == 606) {
            return "WAS-WSFG-------";
        }
        if (code == 607) {
            return "WAS-WSD-LIP----";
        }
        if (code == 608) {
            return "WAS-WSD-LIP----";
        }
        if (code == 609) {
            return "WAS-WSD-MIP----";
        }
        if (code == 610) {
            return "WAS-WSD-HIP----";
        }
        if (code == 611) {
            return "WAS-WSDF-------";
        }
        if (code == 612) {
            return "WAS-WSR-LIP----";
        }
        if (code == 613) {
            return "WAS-WSR-LCP----";
        }
        if (code == 614) {
            return "WAS-WSR-MCP----";
        }
        if (code == 615) {
            return "WAS-WSR-HCP----";
        }
        if (code == 616) {
            return "WAS-WSRF-------";
        }
        if (code == 617) {
            return "WAS-WSM--------";
        }
        if (code == 618) {
            return "WAS-WSS-LCP----";
        }
        if (code == 619) {
            return "WAS-WSS-MCP----";
        }
        if (code == 620) {
            return "WAS-WSS-HCP----";
        }
        if (code == 621) {
            return "WAS-WSRSL-P----";
        }
        if (code == 622) {
            return "WAS-WSRSMHP----";
        }
        if (code == 623) {
            return "WAS-WSRSMHP----";
        }
        if (code == 624) {
            return "WAS-WSMSL-P----";
        }
        if (code == 625) {
            return "WAS-WSSSMHP----";
        }
        if (code == 626) {
            return "WAS-WSSSMHP----";
        }
        if (code == 627) {
            return "WAS-WST-NPP----";
        }
        if (code == 628) {
            return "WAS-WSTMR-P----";
        }
        if (code == 629) {
            return "WAS-ICL---P----";
        }
        if (code == 630) {
            return "WAS-ICM---P----";
        }
        if (code == 631) {
            return "WAS-ICS---P----";
        }
        if (code == 632) {
            return "WAS-IML---P----";
        }
        if (code == 633) {
            return "WAS-IMM---P----";
        }
        if (code == 634) {
            return "WAS-IMS---P----";
        }
        if (code == 635) {
            return "WAS-IRL---P----";
        }
        if (code == 636) {
            return "WAS-IRM---P----";
        }
        if (code == 637) {
            return "WAS-IRS---P----";
        }
        if (code == 638) {
            return "WAS-TL----P----";
        }
        if (code == 639) {
            return "WAS-TM----P----";
        }
        if (code == 640) {
            return "WAS-TS----P----";
        }
        if (code == 641) {
            return "WA-DPXIL---L---";
        }
        if (code == 642) {
            return "WA-DPXT----L---";
        }
        if (code == 643) {
            return "WA--T----------";
        }
        if (code == 644) {
            return "WA--I----------";
        }
        if (code == 645) {
            return "WA-DBAT-----A--";
        }
        if (code == 646) {
            return "G*O*B-----****X";
        }
        if (code == 650) {
            return "G*O*------****X";
        }
        if (code == 651) {
            return "G*O*B-----****X";
        }
        if (code == 652) {
            return "G*O*FA----****X";
        }
        if (code == 653) {
            return "G*G*GPCA--****X";
        }
        if (code == 654) {
            return "G*G*GPUUD-****X";
        }
        if (code == 655) {
            return "G*G*GPPC--****X";
        }
        if (code == 656) {
            return "G*O*F-----****X";
        }
        if (code == 657) {
            return "G*O*FE----****X";
        }
        if (code == 658) {
            return "G*O*E-----****X";
        }
        return "S*G*U-----*****";
    }

    public static String getRGBStringFromThreat(String threat) {
        HashMap<String, String> threatColors = new HashMap<String, String>();
        threatColors.put("HOS", "255 0 0");
        threatColors.put("SUS", "255 0 0");
        threatColors.put("JOK", "255 0 0");
        threatColors.put("FAK", "255 0 0");
        threatColors.put("FRD", "0 255 255");
        threatColors.put("AFD", "0 255 255");
        threatColors.put("NEU", "0 255 0");
        threatColors.put("UNK", "255 255 0");

        if (threatColors.containsKey(threat)) {
            for (Entry<String, String> e : threatColors.entrySet()) {
                if (e.getKey().contains(threat)) {
                    return e.getValue();
                }
            }
        }
        return "255 255 0";
    }

    public static String getCodeScheme(String codescheme) {

        if (codescheme.contentEquals("S")) {
            return "WARFIGHTING";
        }

        if (codescheme.contentEquals("G")) {
            return "TACTICAL GRAPHIC";
        }

        if (codescheme.contentEquals("O")) {
            return "STABILITY OPERATIONS";
        }

        if (codescheme.contentEquals("W")) {
            return "METOC";
        }

        if (codescheme.contentEquals("I")) {
            return "SIGNAL INTELLIGENCE";
        }

        if (codescheme.contentEquals("E")) {
            return "EMERGENCY MANAGEMENT";
        }

        return "WARFIGHTING";
    }

    public static String getAffiliation(String paramString) {

        if (paramString.contentEquals("P")) {
            return "PENDING";
        }
        if (paramString.contentEquals("U")) {
            return "UNKNOWN";
        }
        if (paramString.contentEquals("A")) {
            return "ASSUMED FRIEND";
        }
        if (paramString.contentEquals("F")) {
            return "FRIEND";
        }
        if (paramString.contentEquals("N")) {
            return "NEUTRAL";
        }
        if (paramString.contentEquals("S")) {
            return "SUSPECT";
        }
        if (paramString.contentEquals("H")) {
            return "HOSTILE";
        }
        if (paramString.contentEquals("G")) {
            return "EXERCISE PENDING";
        }
        if (paramString.contentEquals("W")) {
            return "EXERCISE UNKNOWN";
        }
        if (paramString.contentEquals("M")) {
            return "EXERCISE ASSUMED FRIEND";
        }
        if (paramString.contentEquals("D")) {
            return "EXERCISE FRIEND";
        }
        if (paramString.contentEquals("L")) {
            return "EXERCISE NEUTRAL";
        }
        if (paramString.contentEquals("J")) {
            return "JOKER";
        }
        if (paramString.contentEquals("K")) {
            return "FAKER";
        }
        return "UNKNOWN";
    }

    public static String getDimensionString(String dimension) {

        if (dimension.contentEquals("P")) {
            return "SPACE";
        }
        if (dimension.contentEquals("A")) {
            return "AIR";
        }
        if (dimension.contentEquals("G")) {
            return "GROUND";
        }
        if (dimension.contentEquals("S")) {
            return "SEA SURFACE";
        }
        if (dimension.contentEquals("U")) {
            return "SEA SUBSURFACE";
        }
        if (dimension.contentEquals("F")) {
            return "SOF";
        }
        if (dimension.contentEquals("X")) {
            return "OTHER";
        }
        if (dimension.contentEquals("Z")) {
            return "UNKNOWN";
        }

        if (dimension.contentEquals("O")) {
            return "OTHER";
        }

        if (dimension.contentEquals("S")) {
            return "COMBAT SERVICE SUPPORT";
        }

        if (dimension.contentEquals("F")) {
            return "FIRE SUPPORT";
        }

        if (dimension.contentEquals("M")) {
            return "MOBILITY / SURVIVABILITY";
        }

        if (dimension.contentEquals("G")) {
            return "C2 & GENERAL MANUEVER";
        }

        if (dimension.contentEquals("T")) {
            return "TASKS";
        }

        return "UNKNOWN";
    }

    public static String getStatus(String status) {

        if (status.contentEquals("A")) {
            return "ANTICIPATED/PLANNED";
        }
        if (status.contentEquals("P")) {
            return "PRESENT";
        }
        if (status.contentEquals("C")) {
            return "PRESENT/FULLY CAPABLE";
        }
        if (status.contentEquals("D")) {
            return "PRESENT/DAMAGED";
        }
        if (status.contentEquals("X")) {
            return "PRESENT/DESTROYED";
        }
        if (status.contentEquals("F")) {
            return "PRESENT/FULL TO CAPACITY";
        }

        return "UNKNOWN";
    }

    public static String getFunctionId(String function) {

        if (function.contentEquals("------")) {
            return "UNKNOWN";
        }
        if (function.contentEquals("S-----")) {
            return "SATELLITE ";
        }
        if (function.contentEquals("V-----")) {
            return "CREWED SPACE VEHICLE";
        }
        if (function.contentEquals("T-----")) {
            return "SPACE STATION";
        }
        if (function.contentEquals("L-----")) {
            return "SPACE LAUNCH VEHICLE";
        }
        if (function.contentEquals("------")) {
            return "AIR TRACK";
        }
        if (function.contentEquals("M-----")) {
            return "MILITARY ";
        }
        if (function.contentEquals("MF----")) {
            return "FIXED WING";
        }
        if (function.contentEquals("MFB---")) {
            return "BOMBER ";
        }
        if (function.contentEquals("MFF---")) {
            return "FIGHTER ";
        }
        if (function.contentEquals("MFFI--")) {
            return "INTERCEPTOR ";
        }
        if (function.contentEquals("MFT---")) {
            return "TRAINER ";
        }
        if (function.contentEquals("MFA---")) {
            return "ATTACK/STRIKE ";
        }
        if (function.contentEquals("MFL---")) {
            return "V/STOL ";
        }
        if (function.contentEquals("MFK---")) {
            return "TANKER ";
        }
        if (function.contentEquals("MFKB--")) {
            return "TANKER BOOM-ONLY";
        }
        if (function.contentEquals("MFKD--")) {
            return "TANKER DROGUE-ONLY";
        }
        if (function.contentEquals("MFC---")) {
            return "CARGO AIRLIFT (TRANSPORT)";
        }
        if (function.contentEquals("MFCL--")) {
            return "CARGO AIRLIFT (LIGHT)";
        }
        if (function.contentEquals("MFCM--")) {
            return "CARGO AIRLIFT (MEDIUM)";
        }
        if (function.contentEquals("MFCH--")) {
            return "CARGO AIRLIFT (HEAVY)";
        }
        if (function.contentEquals("MFJ---")) {
            return "ELECTRONIC COUNTERMEASURES (ECM/JAMMER)";
        }
        if (function.contentEquals("MFO---")) {
            return "MEDICAL EVACUATION (MEDEVAC)";
        }
        if (function.contentEquals("MFR---")) {
            return "RECONNAISSANCE ";
        }
        if (function.contentEquals("MFRW--")) {
            return "AIRBORNE EARLY WARNING (AEW) ";
        }
        if (function.contentEquals("MFRZ--")) {
            return "ELECTRONIC SURVEILLANCE MEASURES";
        }
        if (function.contentEquals("MFRX--")) {
            return "PHOTOGRAPHIC ";
        }
        if (function.contentEquals("MFP---")) {
            return "PATROL ";
        }
        if (function.contentEquals("MFPN--")) {
            return "ANTISURFACE WARFARE (ASUW)";
        }
        if (function.contentEquals("MFPM--")) {
            return "MINE COUNTERMEASURES";
        }
        if (function.contentEquals("MFU---")) {
            return "UTILITY ";
        }
        if (function.contentEquals("MFUL--")) {
            return "UTILITY (LIGHT)";
        }
        if (function.contentEquals("MFUM--")) {
            return "UTILITY (MEDIUM)";
        }
        if (function.contentEquals("MFUH--")) {
            return "UTILITY (HEAVY)";
        }
        if (function.contentEquals("MFY---")) {
            return "COMMUNICATIONS ";
        }
        if (function.contentEquals("MFH---")) {
            return "COMBAT SEARCH AND RESCUE (CSAR)";
        }
        if (function.contentEquals("MFD---")) {
            return "AIRBORNE COMMAND POST (C2) ";
        }
        if (function.contentEquals("MFQ---")) {
            return "DRONE (RPV/UA)";
        }
        if (function.contentEquals("MFQA--")) {
            return "ATTACK ";
        }
        if (function.contentEquals("MFQB--")) {
            return "BOMBER ";
        }
        if (function.contentEquals("MFQC--")) {
            return "CARGO ";
        }
        if (function.contentEquals("MFQD--")) {
            return "AIRBORNE COMMAND POST";
        }
        if (function.contentEquals("MFQF--")) {
            return "FIGHTER ";
        }
        if (function.contentEquals("MFQH--")) {
            return "SEARCH & RESCUE (CSAR) ";
        }
        if (function.contentEquals("MFQJ--")) {
            return "ELECTRONIC COUNTERMEASURES (JAMMER)";
        }
        if (function.contentEquals("MFQK--")) {
            return "TANKER ";
        }
        if (function.contentEquals("MFQL--")) {
            return "V/STOL ";
        }
        if (function.contentEquals("MFQM--")) {
            return "SPECIAL OPERATIONS FORCES (SOF) ";
        }
        if (function.contentEquals("MFQI--")) {
            return "MINE COUNTERMEASURES";
        }
        if (function.contentEquals("MFQN--")) {
            return "ANTISURFACE WARFARE (ASUW)";
        }
        if (function.contentEquals("MFQP--")) {
            return "PATROL ";
        }
        if (function.contentEquals("MFQR--")) {
            return "RECONNAISSANCE ";
        }
        if (function.contentEquals("MFQRW-")) {
            return "AIRBORNE EARLY WARNING (AEW) ";
        }
        if (function.contentEquals("MFQRZ-")) {
            return "ELECTRONIC SURVEILLANCE MEASURES";
        }
        if (function.contentEquals("MFQRX-")) {
            return "PHOTOGRAPHIC ";
        }
        if (function.contentEquals("MFQS--")) {
            return "ANTISUBMARINE WARFARE (ASW)";
        }
        if (function.contentEquals("MFQT--")) {
            return "TRAINER ";
        }
        if (function.contentEquals("MFQU--")) {
            return "UTILITY ";
        }
        if (function.contentEquals("MFQY--")) {
            return "COMMUNICATIONS ";
        }
        if (function.contentEquals("MFQO--")) {
            return "MEDEVAC ";
        }
        if (function.contentEquals("MFS---")) {
            return "ANTISUBMARINE WARFARE (ASW) CARRIER BASED";
        }
        if (function.contentEquals("MFM---")) {
            return "SPECIAL OPERATIONS FORCES (SOF) ";
        }
        if (function.contentEquals("MH----")) {
            return "ROTARY WING";
        }
        if (function.contentEquals("MHA---")) {
            return "ATTACK ";
        }
        if (function.contentEquals("MHS---")) {
            return "ANTISUBMARINE WARFARE/MPA";
        }
        if (function.contentEquals("MHU---")) {
            return "UTILITY ";
        }
        if (function.contentEquals("MHUL--")) {
            return "UTILITY (LIGHT)";
        }
        if (function.contentEquals("MHUM--")) {
            return "UTILITY (MEDIUM)";
        }
        if (function.contentEquals("MHUH--")) {
            return "UTILITY (HEAVY)";
        }
        if (function.contentEquals("MHI---")) {
            return "MINE COUNTERMEASURES";
        }
        if (function.contentEquals("MHH---")) {
            return "COMBAT SEARCH AND RESCUE (CSAR)";
        }
        if (function.contentEquals("MHR---")) {
            return "RECONNAISSANCE ";
        }
        if (function.contentEquals("MHQ---")) {
            return "DRONE (RPV/UA)";
        }
        if (function.contentEquals("MHC---")) {
            return "CARGO AIRLIFT (TRANSPORT)";
        }
        if (function.contentEquals("MHCL--")) {
            return "CARGO AIRLIFT (LIGHT)";
        }
        if (function.contentEquals("MHCM--")) {
            return "CARGO AIRLIFT (MEDIUM)";
        }
        if (function.contentEquals("MHCH--")) {
            return "CARGO AIRLIFT (HEAVY)";
        }
        if (function.contentEquals("MHT---")) {
            return "TRAINER ";
        }
        if (function.contentEquals("MHO---")) {
            return "MEDEVAC ";
        }
        if (function.contentEquals("MHM---")) {
            return "SPECIAL OPERATIONS FORCES (SOF) ";
        }
        if (function.contentEquals("MHD---")) {
            return "AIRBORNE COMMAND POST (C2) ";
        }
        if (function.contentEquals("MHK---")) {
            return "TANKER ";
        }
        if (function.contentEquals("MHJ---")) {
            return "ELECTRONIC COUNTERMEASURES (ECM/JAMMER)";
        }
        if (function.contentEquals("ML----")) {
            return "LIGHTER THAN AIR";
        }
        if (function.contentEquals("MV----")) {
            return "VERY IMPORTANT PERSON (VIP) ";
        }
        if (function.contentEquals("ME----")) {
            return "ESCORT ";
        }
        if (function.contentEquals("W-----")) {
            return "WEAPON ";
        }
        if (function.contentEquals("WM----")) {
            return "MISSILE IN FLIGHT";
        }
        if (function.contentEquals("WMS---")) {
            return "SURFACE LAUNCHED MISSILE";
        }
        if (function.contentEquals("WMSS--")) {
            return "SURFACE-TO-SURFACE MISSILE (SSM)";
        }
        if (function.contentEquals("WMSA--")) {
            return "SURFACE-TO-AIR MISSILE (SAM)";
        }
        if (function.contentEquals("WMSU--")) {
            return "SURFACE-TO-SUBSURFACE MISSILE";
        }
        if (function.contentEquals("WMSB--")) {
            return "ANTIBALLISTIC MISSILE (ABM)";
        }
        if (function.contentEquals("WMA---")) {
            return "AIR LAUNCHED MISSILE";
        }
        if (function.contentEquals("WMAS--")) {
            return "AIR-TO-SURFACE MISSILE (ASM)";
        }
        if (function.contentEquals("WMAA--")) {
            return "AIR-TO-AIR MISSILE (AAM)";
        }
        if (function.contentEquals("WMAP--")) {
            return "AIR-TO-SPACE MISSILE";
        }
        if (function.contentEquals("WMU---")) {
            return "SUBSURFACE-TO-SURFACE MISSILE (S/SSM)";
        }
        if (function.contentEquals("WMCM--")) {
            return "CRUISE MISSILE";
        }
        if (function.contentEquals("WMB---")) {
            return "BALLISTIC MISSILE";
        }
        if (function.contentEquals("WB----")) {
            return "BOMB ";
        }
        if (function.contentEquals("WD----")) {
            return "DECOY ";
        }
        if (function.contentEquals("C-----")) {
            return "CIVIL AIRCRAFT";
        }
        if (function.contentEquals("CF----")) {
            return "FIXED WING";
        }
        if (function.contentEquals("CH----")) {
            return "ROTARY WING";
        }
        if (function.contentEquals("CL----")) {
            return "LIGHTER THAN AIR";
        }
        if (function.contentEquals("U-----")) {
            return "UNIT ";
        }
        if (function.contentEquals("UC----")) {
            return "COMBAT ";
        }
        if (function.contentEquals("UCD---")) {
            return "AIR DEFENSE";
        }
        if (function.contentEquals("UCDS--")) {
            return "SHORT RANGE";
        }
        if (function.contentEquals("UCDSC-")) {
            return "CHAPARRAL ";
        }
        if (function.contentEquals("UCDSS-")) {
            return "STINGER ";
        }
        if (function.contentEquals("UCDSV-")) {
            return "VULCAN ";
        }
        if (function.contentEquals("UCDM--")) {
            return "AIR DEFENSE MISSILE";
        }
        if (function.contentEquals("UCDML-")) {
            return "AIR DEFENSE MISSILE LIGHT ";
        }
        if (function.contentEquals("UCDMLA")) {
            return "AIR DEFENSE MISSILE MOTORIZED (AVENGER)";
        }
        if (function.contentEquals("UCDMM-")) {
            return "AIR DEFENSE MISSILE MEDIUM ";
        }
        if (function.contentEquals("UCDMH-")) {
            return "AIR DEFENSE MISSILE HEAVY ";
        }
        if (function.contentEquals("UCDH--")) {
            return "H/MAD ";
        }
        if (function.contentEquals("UCDHH-")) {
            return "HAWK ";
        }
        if (function.contentEquals("UCDHP-")) {
            return "PATRIOT ";
        }
        if (function.contentEquals("UCDG--")) {
            return "GUN UNIT";
        }
        if (function.contentEquals("UCDC--")) {
            return "COMPOSITE ";
        }
        if (function.contentEquals("UCDT--")) {
            return "TARGETING UNIT";
        }
        if (function.contentEquals("UCDO--")) {
            return "THEATER MISSILE DEFENSE UNIT ";
        }
        if (function.contentEquals("UCA---")) {
            return "ARMOR ";
        }
        if (function.contentEquals("UCAT--")) {
            return "ARMOR TRACK";
        }
        if (function.contentEquals("UCATA-")) {
            return "ARMOR TRACK AIRBORNE";
        }
        if (function.contentEquals("UCATW-")) {
            return "ARMOR TRACK AMPHIBIOUS";
        }
        if (function.contentEquals("UCATWR")) {
            return "ARMOR TRACK AMPHIBIOUS RECOVERY ";
        }
        if (function.contentEquals("UCATL-")) {
            return "ARMOR TRACK  LIGHT ";
        }
        if (function.contentEquals("UCATM-")) {
            return "ARMOR TRACK  MEDIUM ";
        }
        if (function.contentEquals("UCATH-")) {
            return "ARMOR TRACK  HEAVY ";
        }
        if (function.contentEquals("UCATR-")) {
            return "ARMOR TRACK  RECOVERY ";
        }
        if (function.contentEquals("UCAW--")) {
            return "ARMOR  WHEELED";
        }
        if (function.contentEquals("UCAWS-")) {
            return "ARMOR  WHEELED AIR ASSAULT";
        }
        if (function.contentEquals("UCAWA-")) {
            return "ARMOR  WHEELED AIRBORNE ";
        }
        if (function.contentEquals("UCAWW-")) {
            return "ARMOR  WHEELED AMPHIBIOUS ";
        }
        if (function.contentEquals("UCAWWR")) {
            return "ARMOR  WHEELED AMPHIBIOUS RECOVERY";
        }
        if (function.contentEquals("UCAWL-")) {
            return "ARMOR  WHEELED LIGHT ";
        }
        if (function.contentEquals("UCAWM-")) {
            return "ARMOR  WHEELED MEDIUM ";
        }
        if (function.contentEquals("UCAWH-")) {
            return "ARMOR  WHEELED HEAVY ";
        }
        if (function.contentEquals("UCAWR-")) {
            return "ARMOR  WHEELED RECOVERY ";
        }
        if (function.contentEquals("UCAA--")) {
            return "ANTIARMOR ";
        }
        if (function.contentEquals("UCAAD-")) {
            return "ANTIARMOR DISMOUNTED";
        }
        if (function.contentEquals("UCAAL-")) {
            return "ANTIARMOR LIGHT";
        }
        if (function.contentEquals("UCAAM-")) {
            return "ANTIARMOR AIRBORNE";
        }
        if (function.contentEquals("UCAAS-")) {
            return "ANTIARMOR AIR ASSAULT";
        }
        if (function.contentEquals("UCAAU-")) {
            return "ANTIARMOR MOUNTAIN";
        }
        if (function.contentEquals("UCAAC-")) {
            return "ANTIARMOR ARCTIC";
        }
        if (function.contentEquals("UCAAA-")) {
            return "ANTIARMOR ARMORED";
        }
        if (function.contentEquals("UCAAAT")) {
            return "ANTIARMOR ARMORED TRACKED";
        }
        if (function.contentEquals("UCAAAW")) {
            return "ANTIARMOR ARMORED WHEELED";
        }
        if (function.contentEquals("UCAAAS")) {
            return "ANTIARMOR ARMORED AIR ASSAULT ";
        }
        if (function.contentEquals("UCAAO-")) {
            return "ANTIARMOR MOTORIZED";
        }
        if (function.contentEquals("UCAAOS")) {
            return "ANTIARMOR MOTORIZED AIR ASSAULT ";
        }
        if (function.contentEquals("UCV---")) {
            return "AVIATION ";
        }
        if (function.contentEquals("UCVF--")) {
            return "FIXED WING";
        }
        if (function.contentEquals("UCVFU-")) {
            return "UTILITY FIXED WING";
        }
        if (function.contentEquals("UCVFA-")) {
            return "ATTACK FIXED WING";
        }
        if (function.contentEquals("UCVFR-")) {
            return "RECON FIXED WING";
        }
        if (function.contentEquals("UCVR--")) {
            return "ROTARY WING";
        }
        if (function.contentEquals("UCVRA-")) {
            return "ATTACK ROTARY WING";
        }
        if (function.contentEquals("UCVRS-")) {
            return "SCOUT ROTARY WING";
        }
        if (function.contentEquals("UCVRW-")) {
            return "ANTISUBMARINE WARFARE ROTARY WING ";
        }
        if (function.contentEquals("UCVRU-")) {
            return "UTILITY ROTARY WING";
        }
        if (function.contentEquals("UCVRUL")) {
            return "LIGHT UTILITY ROTARY WING ";
        }
        if (function.contentEquals("UCVRUM")) {
            return "MEDIUM UTILITY ROTARY WING ";
        }
        if (function.contentEquals("UCVRUH")) {
            return "HEAVY UTILITY ROTARY WING ";
        }
        if (function.contentEquals("UCVRUC")) {
            return "C2 ROTARY WING";
        }
        if (function.contentEquals("UCVRUE")) {
            return "MEDEVAC ROTARY WING";
        }
        if (function.contentEquals("UCVRM-")) {
            return "MINE COUNTERMEASURE ROTARY WING ";
        }
        if (function.contentEquals("UCVS--")) {
            return "SEARCH AND RESCUE";
        }
        if (function.contentEquals("UCVC--")) {
            return "COMPOSITE ";
        }
        if (function.contentEquals("UCVV--")) {
            return "VERTICAL AND/OR SHORT TAKEOFF AND LANDING AIRCRAFT  ";
        }
        if (function.contentEquals("UCVU--")) {
            return "UNMANNED AIRCRAFT";
        }
        if (function.contentEquals("UCVUF-")) {
            return "UNMANNED AIRCRAFT FIXED WING ";
        }
        if (function.contentEquals("UCVUR-")) {
            return "UNMANNED AIRCRAFT ROTARY WING ";
        }
        if (function.contentEquals("UCI---")) {
            return "INFANTRY ";
        }
        if (function.contentEquals("UCIL--")) {
            return "INFANTRY LIGHT";
        }
        if (function.contentEquals("UCIM--")) {
            return "INFANTRY MOTORIZED";
        }
        if (function.contentEquals("UCIO--")) {
            return "INFANTRY MOUNTAIN";
        }
        if (function.contentEquals("UCIA--")) {
            return "INFANTRY AIRBORNE";
        }
        if (function.contentEquals("UCIS--")) {
            return "INFANTRY AIR ASSAULT";
        }
        if (function.contentEquals("UCIZ--")) {
            return "INFANTRY MECHANIZED";
        }
        if (function.contentEquals("UCIN--")) {
            return "INFANTRY NAVAL";
        }
        if (function.contentEquals("UCII--")) {
            return "INFANTRY FIGHTING VEHICLE";
        }
        if (function.contentEquals("UCIC--")) {
            return "INFANTRY ARCTIC";
        }
        if (function.contentEquals("UCE---")) {
            return "ENGINEER ";
        }
        if (function.contentEquals("UCEC--")) {
            return "ENGINEER COMBAT";
        }
        if (function.contentEquals("UCECS-")) {
            return "ENGINEER COMBAT AIR ASSAULT ";
        }
        if (function.contentEquals("UCECA-")) {
            return "ENGINEER COMBAT AIRBORNE";
        }
        if (function.contentEquals("UCECC-")) {
            return "ENGINEER COMBAT ARCTIC";
        }
        if (function.contentEquals("UCECL-")) {
            return "ENGINEER COMBAT LIGHT (SAPPER) ";
        }
        if (function.contentEquals("UCECM-")) {
            return "ENGINEER COMBAT MEDIUM";
        }
        if (function.contentEquals("UCECH-")) {
            return "ENGINEER COMBAT HEAVY";
        }
        if (function.contentEquals("UCECT-")) {
            return "ENGINEER COMBAT MECHANIZED (TRACK) ";
        }
        if (function.contentEquals("UCECW-")) {
            return "ENGINEER COMBAT MOTORIZED";
        }
        if (function.contentEquals("UCECO-")) {
            return "ENGINEER COMBAT MOUNTAIN";
        }
        if (function.contentEquals("UCECR-")) {
            return "ENGINEER COMBAT RECON";
        }
        if (function.contentEquals("UCEN--")) {
            return "ENGINEER CONSTRUCTION";
        }
        if (function.contentEquals("UCENN-")) {
            return "ENGINEER NAVAL CONSTRUCTION";
        }
        if (function.contentEquals("UCF---")) {
            return "FIELD ARTILLERY";
        }
        if (function.contentEquals("UCFH--")) {
            return "HOWITZER/GUN ";
        }
        if (function.contentEquals("UCFHE-")) {
            return "SELF-PROPELLED ";
        }
        if (function.contentEquals("UCFHS-")) {
            return "AIR ASSAULT";
        }
        if (function.contentEquals("UCFHA-")) {
            return "AIRBORNE ";
        }
        if (function.contentEquals("UCFHC-")) {
            return "ARCTIC ";
        }
        if (function.contentEquals("UCFHO-")) {
            return "MOUNTAIN ";
        }
        if (function.contentEquals("UCFHL-")) {
            return "LIGHT ";
        }
        if (function.contentEquals("UCFHM-")) {
            return "MEDIUM ";
        }
        if (function.contentEquals("UCFHH-")) {
            return "HEAVY ";
        }
        if (function.contentEquals("UCFHX-")) {
            return "AMPHIBIOUS ";
        }
        if (function.contentEquals("UCFR--")) {
            return "ROCKET ";
        }
        if (function.contentEquals("UCFRS-")) {
            return "SINGLE ROCKET LAUNCHER";
        }
        if (function.contentEquals("UCFRSS")) {
            return "SINGLE ROCKET SELF-PROPELLED";
        }
        if (function.contentEquals("UCFRSR")) {
            return "SINGLE ROCKET TRUCK";
        }
        if (function.contentEquals("UCFRST")) {
            return "SINGLE ROCKET TOWED";
        }
        if (function.contentEquals("UCFRM-")) {
            return "MULTIPLE ROCKET LAUNCHER";
        }
        if (function.contentEquals("UCFRMS")) {
            return "MULTIPLE ROCKET SELF-PROPELLED";
        }
        if (function.contentEquals("UCFRMR")) {
            return "MULTIPLE ROCKET TRUCK";
        }
        if (function.contentEquals("UCFRMT")) {
            return "MULTIPLE ROCKET TOWED";
        }
        if (function.contentEquals("UCFT--")) {
            return "TARGET ACQUISITION";
        }
        if (function.contentEquals("UCFTR-")) {
            return "RADAR ";
        }
        if (function.contentEquals("UCFTS-")) {
            return "SOUND ";
        }
        if (function.contentEquals("UCFTF-")) {
            return "FLASH (OPTICAL)";
        }
        if (function.contentEquals("UCFTC-")) {
            return "COLT/FIST ";
        }
        if (function.contentEquals("UCFTCD")) {
            return "DISMOUNTED COLT/FIST";
        }
        if (function.contentEquals("UCFTCM")) {
            return "TRACKED COLT/FIST";
        }
        if (function.contentEquals("UCFTA-")) {
            return "ANGLICO ";
        }
        if (function.contentEquals("UCFM--")) {
            return "MORTAR ";
        }
        if (function.contentEquals("UCFMS-")) {
            return "SELF-PROPELLED (SP) TRACKED MORTAR ";
        }
        if (function.contentEquals("UCFMW-")) {
            return "SP WHEELED MORTAR";
        }
        if (function.contentEquals("UCFMT-")) {
            return "TOWED MORTAR";
        }
        if (function.contentEquals("UCFMTA")) {
            return "TOWED AIRBORNE MORTAR";
        }
        if (function.contentEquals("UCFMTS")) {
            return "TOWED AIR ASSAULT MORTAR ";
        }
        if (function.contentEquals("UCFMTC")) {
            return "TOWED ARCTIC MORTAR";
        }
        if (function.contentEquals("UCFMTO")) {
            return "TOWED MOUNTAIN MORTAR";
        }
        if (function.contentEquals("UCFML-")) {
            return "AMPHIBIOUS MORTAR";
        }
        if (function.contentEquals("UCFS--")) {
            return "ARTILLERY SURVEY";
        }
        if (function.contentEquals("UCFSS-")) {
            return "AIR ASSAULT";
        }
        if (function.contentEquals("UCFSA-")) {
            return "AIRBORNE ";
        }
        if (function.contentEquals("UCFSL-")) {
            return "LIGHT ";
        }
        if (function.contentEquals("UCFSO-")) {
            return "MOUNTAIN ";
        }
        if (function.contentEquals("UCFO--")) {
            return "METEOROLOGICAL ";
        }
        if (function.contentEquals("UCFOS-")) {
            return "AIR ASSAULT METEOROLOGICAL";
        }
        if (function.contentEquals("UCFOA-")) {
            return "AIRBORNE METEOROLOGICAL";
        }
        if (function.contentEquals("UCFOL-")) {
            return "LIGHT METEOROLOGICAL";
        }
        if (function.contentEquals("UCFOO-")) {
            return "MOUNTAIN METEOROLOGICAL";
        }
        if (function.contentEquals("UCR---")) {
            return "RECONNAISSANCE ";
        }
        if (function.contentEquals("UCRH--")) {
            return "RECONNAISSANCE HORSE";
        }
        if (function.contentEquals("UCRV--")) {
            return "RECONNAISSANCE CAVALRY";
        }
        if (function.contentEquals("UCRVA-")) {
            return "RECONNAISSANCE CAVALRY ARMORED";
        }
        if (function.contentEquals("UCRVM-")) {
            return "RECONNAISSANCE CAVALRY MOTORIZED";
        }
        if (function.contentEquals("UCRVG-")) {
            return "RECONNAISSANCE CAVALRY GROUND";
        }
        if (function.contentEquals("UCRVO-")) {
            return "RECONNAISSANCE CAVALRY AIR";
        }
        if (function.contentEquals("UCRC--")) {
            return "RECONNAISSANCE ARCTIC";
        }
        if (function.contentEquals("UCRS--")) {
            return "RECONNAISSANCE AIR ASSAULT";
        }
        if (function.contentEquals("UCRA--")) {
            return "RECONNAISSANCE AIRBORNE";
        }
        if (function.contentEquals("UCRO--")) {
            return "RECONNAISSANCE MOUNTAIN";
        }
        if (function.contentEquals("UCRL--")) {
            return "RECONNAISSANCE LIGHT";
        }
        if (function.contentEquals("UCRR--")) {
            return "RECONNAISSANCE MARINE";
        }
        if (function.contentEquals("UCRRD-")) {
            return "RECONNAISSANCE MARINE DIVISION";
        }
        if (function.contentEquals("UCRRF-")) {
            return "RECONNAISSANCE MARINE FORCE";
        }
        if (function.contentEquals("UCRRL-")) {
            return "RECONNAISSANCE MARINE LIGHT ARMORED ";
        }
        if (function.contentEquals("UCRX--")) {
            return "RECONNAISSANCE LONG RANGE SURVEILLANCE (LRS)";
        }
        if (function.contentEquals("UCM---")) {
            return "MISSILE (SURF-SURF)";
        }
        if (function.contentEquals("UCMT--")) {
            return "MISSILE (SURF-SURF) TACTICAL";
        }
        if (function.contentEquals("UCMS--")) {
            return "MISSILE (SURF-SURF) STRATEGIC";
        }
        if (function.contentEquals("UCS---")) {
            return "INTERNAL SECURITY FORCES";
        }
        if (function.contentEquals("UCSW--")) {
            return "RIVERINE ";
        }
        if (function.contentEquals("UCSG--")) {
            return "GROUND ";
        }
        if (function.contentEquals("UCSGD-")) {
            return "DISMOUNTED GROUND";
        }
        if (function.contentEquals("UCSGM-")) {
            return "MOTORIZED GROUND";
        }
        if (function.contentEquals("UCSGA-")) {
            return "MECHANIZED GROUND";
        }
        if (function.contentEquals("UCSM--")) {
            return "WHEELED MECHANIZED";
        }
        if (function.contentEquals("UCSR--")) {
            return "RAILROAD ";
        }
        if (function.contentEquals("UCSA--")) {
            return "AVIATION ";
        }
        if (function.contentEquals("UU----")) {
            return "COMBAT SUPPORT";
        }
        if (function.contentEquals("UUA---")) {
            return "COMBAT SUPPORT CBRN";
        }
        if (function.contentEquals("UUAC--")) {
            return "CHEMICAL ";
        }
        if (function.contentEquals("UUACC-")) {
            return "SMOKE/DECON ";
        }
        if (function.contentEquals("UUACCK")) {
            return "MECHANIZED SMOKE/DECON";
        }
        if (function.contentEquals("UUACCM")) {
            return "MOTORIZED SMOKE/DECON";
        }
        if (function.contentEquals("UUACS-")) {
            return "SMOKE ";
        }
        if (function.contentEquals("UUACSM")) {
            return "MOTORIZED SMOKE";
        }
        if (function.contentEquals("UUACSA")) {
            return "ARMOR SMOKE";
        }
        if (function.contentEquals("UUACR-")) {
            return "CHEMICAL RECON";
        }
        if (function.contentEquals("UUACRW")) {
            return "CHEMICAL WHEELED ARMORED VEHICLE ";
        }
        if (function.contentEquals("UUACRS")) {
            return "CHEMICAL WHEELED ARMORED VEHICLE RECONNAISSANCE";
        }
        if (function.contentEquals("UUAN--")) {
            return "NUCLEAR ";
        }
        if (function.contentEquals("UUAB--")) {
            return "BIOLOGICAL ";
        }
        if (function.contentEquals("UUABR-")) {
            return "RECON EQUIPPED";
        }
        if (function.contentEquals("UUAD--")) {
            return "DECONTAMINATION ";
        }
        if (function.contentEquals("UUM---")) {
            return "MILITARY INTELLIGENCE";
        }
        if (function.contentEquals("UUMA--")) {
            return "AERIAL EXPLOITATION";
        }
        if (function.contentEquals("UUMS--")) {
            return "SIGNAL INTELLIGENCE (SIGINT)";
        }
        if (function.contentEquals("UUMSE-")) {
            return "ELECTRONIC WARFARE";
        }
        if (function.contentEquals("UUMSEA")) {
            return "ARMORED WHEELED VEHICLE";
        }
        if (function.contentEquals("UUMSED")) {
            return "DIRECTION FINDING";
        }
        if (function.contentEquals("UUMSEI")) {
            return "INTERCEPT ";
        }
        if (function.contentEquals("UUMSEJ")) {
            return "JAMMING ";
        }
        if (function.contentEquals("UUMSET")) {
            return "THEATER ";
        }
        if (function.contentEquals("UUMSEC")) {
            return "CORPS ";
        }
        if (function.contentEquals("UUMC--")) {
            return "COUNTERINTELLIGENCE ";
        }
        if (function.contentEquals("UUMR--")) {
            return "SURVEILLANCE ";
        }
        if (function.contentEquals("UUMRG-")) {
            return "GROUND SURVEILLANCE RADAR";
        }
        if (function.contentEquals("UUMRS-")) {
            return "SENSOR ";
        }
        if (function.contentEquals("UUMRSS")) {
            return "SENSOR SCM";
        }
        if (function.contentEquals("UUMRX-")) {
            return "GROUND STATION MODULE";
        }
        if (function.contentEquals("UUMMO-")) {
            return "METEOROLOGICAL ";
        }
        if (function.contentEquals("UUMO--")) {
            return "OPERATIONS ";
        }
        if (function.contentEquals("UUMT--")) {
            return "TACTICAL EXPLOIT";
        }
        if (function.contentEquals("UUMQ--")) {
            return "INTERROGATION ";
        }
        if (function.contentEquals("UUMJ--")) {
            return "JOINT INTELLIGENCE CENTER";
        }
        if (function.contentEquals("UUL---")) {
            return "LAW ENFORCEMENT UNIT";
        }
        if (function.contentEquals("UULS--")) {
            return "SHORE PATROL";
        }
        if (function.contentEquals("UULM--")) {
            return "MILITARY POLICE";
        }
        if (function.contentEquals("UULC--")) {
            return "CIVILIAN LAW ENFORCEMENT";
        }
        if (function.contentEquals("UULF--")) {
            return "SECURITY POLICE (AIR)";
        }
        if (function.contentEquals("UULD--")) {
            return "CENTRAL INTELLIGENCE DIVISION (CID) ";
        }
        if (function.contentEquals("UUS---")) {
            return "SIGNAL UNIT";
        }
        if (function.contentEquals("UUSA--")) {
            return "AREA ";
        }
        if (function.contentEquals("UUSC--")) {
            return "COMMUNICATION CONFIGURED PACKAGE";
        }
        if (function.contentEquals("UUSCL-")) {
            return "LARGE COMMUNICATION CONFIGURED PACKAGE (LCCP)";
        }
        if (function.contentEquals("UUSO--")) {
            return "COMMAND OPERATIONS";
        }
        if (function.contentEquals("UUSF--")) {
            return "FORWARD COMMUNICATIONS";
        }
        if (function.contentEquals("UUSM--")) {
            return "MULTIPLE SUBSCRIBER ELEMENT";
        }
        if (function.contentEquals("UUSMS-")) {
            return "SMALL EXTENSION NODE";
        }
        if (function.contentEquals("UUSML-")) {
            return "LARGE EXTENSION NODE";
        }
        if (function.contentEquals("UUSMN-")) {
            return "NODE CENTER";
        }
        if (function.contentEquals("UUSR--")) {
            return "RADIO UNIT";
        }
        if (function.contentEquals("UUSRS-")) {
            return "TACTICAL SATELLITE";
        }
        if (function.contentEquals("UUSRT-")) {
            return "TELETYPE CENTER";
        }
        if (function.contentEquals("UUSRW-")) {
            return "RELAY ";
        }
        if (function.contentEquals("UUSS--")) {
            return "SIGNAL SUPPORT";
        }
        if (function.contentEquals("UUSW--")) {
            return "TELEPHONE SWITCH";
        }
        if (function.contentEquals("UUSX--")) {
            return "ELECTRONIC RANGING";
        }
        if (function.contentEquals("UUI---")) {
            return "INFORMATION WARFARE UNIT";
        }
        if (function.contentEquals("UUP---")) {
            return "LANDING SUPPORT";
        }
        if (function.contentEquals("UUE---")) {
            return "EXPLOSIVE ORDNANCE DISPOSAL";
        }
        if (function.contentEquals("US----")) {
            return "COMBAT SERVICE SUPPORT";
        }
        if (function.contentEquals("USA---")) {
            return "ADMINISTRATIVE (ADMIN)";
        }
        if (function.contentEquals("USAT--")) {
            return "ADMIN THEATER";
        }
        if (function.contentEquals("USAC--")) {
            return "ADMIN CORPS";
        }
        if (function.contentEquals("USAJ--")) {
            return "JUDGE ADVOCATE GENERAL (JAG) ";
        }
        if (function.contentEquals("USAJT-")) {
            return "JAG THEATER";
        }
        if (function.contentEquals("USAJC-")) {
            return "JAG CORPS";
        }
        if (function.contentEquals("USAO--")) {
            return "POSTAL ";
        }
        if (function.contentEquals("USAOT-")) {
            return "POSTAL THEATER";
        }
        if (function.contentEquals("USAOC-")) {
            return "POSTAL CORPS";
        }
        if (function.contentEquals("USAF--")) {
            return "FINANCE ";
        }
        if (function.contentEquals("USAFT-")) {
            return "FINANCE THEATER";
        }
        if (function.contentEquals("USAFC-")) {
            return "FINANCE CORPS";
        }
        if (function.contentEquals("USAS--")) {
            return "PERSONNEL SERVICES";
        }
        if (function.contentEquals("USAST-")) {
            return "PERSONNEL THEATER";
        }
        if (function.contentEquals("USASC-")) {
            return "PERSONNEL CORPS";
        }
        if (function.contentEquals("USAM--")) {
            return "MORTUARY/GRAVES REGISTRY";
        }
        if (function.contentEquals("USAMT-")) {
            return "MORTUARY/GRAVES REGISTRY THEATER";
        }
        if (function.contentEquals("USAMC-")) {
            return "MORTUARY/GRAVES REGISTRY CORPS";
        }
        if (function.contentEquals("USAR--")) {
            return "RELIGIOUS/CHAPLAIN ";
        }
        if (function.contentEquals("USART-")) {
            return "RELIGIOUS/CHAPLAIN THEATER";
        }
        if (function.contentEquals("USARC-")) {
            return "RELIGIOUS/CHAPLAIN CORPS";
        }
        if (function.contentEquals("USAP--")) {
            return "PUBLIC AFFAIRS";
        }
        if (function.contentEquals("USAPT-")) {
            return "PUBLIC AFFAIRS THEATER";
        }
        if (function.contentEquals("USAPC-")) {
            return "PUBLIC AFFAIRS CORPS";
        }
        if (function.contentEquals("USAPB-")) {
            return "PUBLIC AFFAIRS BROADCAST";
        }
        if (function.contentEquals("USAPBT")) {
            return "PUBLIC AFFAIRS BROADCAST THEATER ";
        }
        if (function.contentEquals("USAPBC")) {
            return "PUBLIC AFFAIRS BROADCAST CORPS ";
        }
        if (function.contentEquals("USAPM-")) {
            return "PUBLIC AFFAIRS JOINT INFORMATION BUREAU (JIB)   ";
        }
        if (function.contentEquals("USAPMT")) {
            return "PUBLIC AFFAIRS JIB THEATER ";
        }
        if (function.contentEquals("USAPMC")) {
            return "PUBLIC AFFAIRS JIB CORPS ";
        }
        if (function.contentEquals("USAX--")) {
            return "REPLACEMENT HOLDING UNIT (RHU) ";
        }
        if (function.contentEquals("USAXT-")) {
            return "RHU THEATER";
        }
        if (function.contentEquals("USAXC-")) {
            return "RHU CORPS";
        }
        if (function.contentEquals("USAL--")) {
            return "LABOR ";
        }
        if (function.contentEquals("USALT-")) {
            return "LABOR THEATER";
        }
        if (function.contentEquals("USALC-")) {
            return "LABOR CORPS";
        }
        if (function.contentEquals("USAW--")) {
            return "MORALE  WELFARE  RECREATION (MWR)   ";
        }
        if (function.contentEquals("USAWT-")) {
            return "MWR THEATER";
        }
        if (function.contentEquals("USAWC-")) {
            return "MWR CORPS";
        }
        if (function.contentEquals("USAQ--")) {
            return "QUARTERMASTER (SUPPLY)";
        }
        if (function.contentEquals("USAQT-")) {
            return "QUARTERMASTER (SUPPLY) THEATER";
        }
        if (function.contentEquals("USAQC-")) {
            return "QUARTERMASTER (SUPPLY) CORPS";
        }
        if (function.contentEquals("USM---")) {
            return "MEDICAL ";
        }
        if (function.contentEquals("USMT--")) {
            return "MEDICAL THEATER";
        }
        if (function.contentEquals("USMC--")) {
            return "MEDICAL CORPS";
        }
        if (function.contentEquals("USMM--")) {
            return "MEDICAL TREATMENT FACILITY";
        }
        if (function.contentEquals("USMMT-")) {
            return "MEDICAL TREATMENT FACILITY THEATER ";
        }
        if (function.contentEquals("USMMC-")) {
            return "MEDICAL TREATMENT FACILITY CORPS ";
        }
        if (function.contentEquals("USMV--")) {
            return "MEDICAL VETERINARY";
        }
        if (function.contentEquals("USMVT-")) {
            return "MEDICAL VETERINARY THEATER";
        }
        if (function.contentEquals("USMVC-")) {
            return "MEDICAL VETERINARY CORPS";
        }
        if (function.contentEquals("USMD--")) {
            return "MEDICAL DENTAL";
        }
        if (function.contentEquals("USMDT-")) {
            return "MEDICAL DENTAL THEATER";
        }
        if (function.contentEquals("USMDC-")) {
            return "MEDICAL DENTAL CORPS";
        }
        if (function.contentEquals("USMP--")) {
            return "MEDICAL PSYCHOLOGICAL";
        }
        if (function.contentEquals("USMPT-")) {
            return "MEDICAL PSYCHOLOGICAL THEATER";
        }
        if (function.contentEquals("USMPC-")) {
            return "MEDICAL PSYCHOLOGICAL CORPS";
        }
        if (function.contentEquals("USS---")) {
            return "SUPPLY ";
        }
        if (function.contentEquals("USST--")) {
            return "SUPPLY THEATER";
        }
        if (function.contentEquals("USSC--")) {
            return "SUPPLY CORPS";
        }
        if (function.contentEquals("USS1--")) {
            return "SUPPLY CLASS I";
        }
        if (function.contentEquals("USS1T-")) {
            return "SUPPLY CLASS I THEATER ";
        }
        if (function.contentEquals("USS1C-")) {
            return "SUPPLY CLASS I CORPS ";
        }
        if (function.contentEquals("USS2--")) {
            return "SUPPLY CLASS II";
        }
        if (function.contentEquals("USS2T-")) {
            return "SUPPLY CLASS II THEATER ";
        }
        if (function.contentEquals("USS2C-")) {
            return "SUPPLY CLASS II CORPS ";
        }
        if (function.contentEquals("USS3--")) {
            return "SUPPLY CLASS III";
        }
        if (function.contentEquals("USS3T-")) {
            return "SUPPLY CLASS III THEATER ";
        }
        if (function.contentEquals("USS3C-")) {
            return "SUPPLY CLASS III CORPS ";
        }
        if (function.contentEquals("USS3A-")) {
            return "SUPPLY CLASS III AVIATION ";
        }
        if (function.contentEquals("USS3AT")) {
            return "SUPPLY CLASS III AVIATION THEATER";
        }
        if (function.contentEquals("USS3AC")) {
            return "SUPPLY CLASS III AVIATION CORPS";
        }
        if (function.contentEquals("USS4--")) {
            return "SUPPLY CLASS IV";
        }
        if (function.contentEquals("USS4T-")) {
            return "SUPPLY CLASS IV THEATER ";
        }
        if (function.contentEquals("USS4C-")) {
            return "SUPPLY CLASS IV CORPS ";
        }
        if (function.contentEquals("USS5--")) {
            return "SUPPLY CLASS V";
        }
        if (function.contentEquals("USS5T-")) {
            return "SUPPLY CLASS V THEATER ";
        }
        if (function.contentEquals("USS5C-")) {
            return "SUPPLY CLASS V CORPS ";
        }
        if (function.contentEquals("USS6--")) {
            return "SUPPLY CLASS VI";
        }
        if (function.contentEquals("USS6T-")) {
            return "SUPPLY CLASS VI THEATER ";
        }
        if (function.contentEquals("USS6C-")) {
            return "SUPPLY CLASS VI CORPS ";
        }
        if (function.contentEquals("USS7--")) {
            return "SUPPLY CLASS VII";
        }
        if (function.contentEquals("USS7T-")) {
            return "SUPPLY CLASS VII THEATER ";
        }
        if (function.contentEquals("USS7C-")) {
            return "SUPPLY CLASS VII CORPS ";
        }
        if (function.contentEquals("USS8--")) {
            return "SUPPLY CLASS VIII";
        }
        if (function.contentEquals("USS8T-")) {
            return "SUPPLY CLASS VIII THEATER ";
        }
        if (function.contentEquals("USS8C-")) {
            return "SUPPLY CLASS VIII CORPS ";
        }
        if (function.contentEquals("USS9--")) {
            return "SUPPLY CLASS IX";
        }
        if (function.contentEquals("USS9T-")) {
            return "SUPPLY CLASS IX THEATER ";
        }
        if (function.contentEquals("USS9C-")) {
            return "SUPPLY CLASS IX CORPS ";
        }
        if (function.contentEquals("USSX--")) {
            return "SUPPLY CLASS X";
        }
        if (function.contentEquals("USSXT-")) {
            return "SUPPLY CLASS X THEATER ";
        }
        if (function.contentEquals("USSXC-")) {
            return "SUPPLY CLASS X CORPS ";
        }
        if (function.contentEquals("USSL--")) {
            return "SUPPLY LAUNDRY/BATH";
        }
        if (function.contentEquals("USSLT-")) {
            return "SUPPLY LAUNDRY/BATH THEATER";
        }
        if (function.contentEquals("USSLC-")) {
            return "SUPPLY LAUNDRY/BATH CORPS";
        }
        if (function.contentEquals("USSW--")) {
            return "SUPPLY WATER";
        }
        if (function.contentEquals("USSWT-")) {
            return "SUPPLY WATER THEATER";
        }
        if (function.contentEquals("USSWC-")) {
            return "SUPPLY WATER CORPS";
        }
        if (function.contentEquals("USSWP-")) {
            return "SUPPLY WATER PURIFICATION";
        }
        if (function.contentEquals("USSWPT")) {
            return "SUPPLY WATER PURIFICATION THEATER ";
        }
        if (function.contentEquals("USSWPC")) {
            return "SUPPLY WATER PURIFICATION CORPS ";
        }
        if (function.contentEquals("UST---")) {
            return "TRANSPORTATION ";
        }
        if (function.contentEquals("USTT--")) {
            return "TRANSPORTATION THEATER";
        }
        if (function.contentEquals("USTC--")) {
            return "TRANSPORTATION CORPS";
        }
        if (function.contentEquals("USTM--")) {
            return "MOVEMENT CONTROL CENTER (MCC) ";
        }
        if (function.contentEquals("USTMT-")) {
            return "MCC THEATER";
        }
        if (function.contentEquals("USTMC-")) {
            return "MCC CORPS";
        }
        if (function.contentEquals("USTR--")) {
            return "RAILHEAD ";
        }
        if (function.contentEquals("USTRT-")) {
            return "RAILHEAD THEATER";
        }
        if (function.contentEquals("USTRC-")) {
            return "RAILHEAD CORPS";
        }
        if (function.contentEquals("USTS--")) {
            return "SPOD/SPOE ";
        }
        if (function.contentEquals("USTST-")) {
            return "SPOD/SPOE THEATER";
        }
        if (function.contentEquals("USTSC-")) {
            return "SPOD/SPOE CORPS";
        }
        if (function.contentEquals("USTA--")) {
            return "APOD/APOE ";
        }
        if (function.contentEquals("USTAT-")) {
            return "APOD/APOE THEATER";
        }
        if (function.contentEquals("USTAC-")) {
            return "APOD/APOE CORPS";
        }
        if (function.contentEquals("USTI--")) {
            return "MISSILE ";
        }
        if (function.contentEquals("USTIT-")) {
            return "MISSILE THEATER";
        }
        if (function.contentEquals("USTIC-")) {
            return "MISSILE CORPS";
        }
        if (function.contentEquals("USX---")) {
            return "MAINTENANCE ";
        }
        if (function.contentEquals("USXT--")) {
            return "MAINTENANCE THEATER";
        }
        if (function.contentEquals("USXC--")) {
            return "MAINTENANCE CORPS";
        }
        if (function.contentEquals("USXH--")) {
            return "MAINTENANCE HEAVY";
        }
        if (function.contentEquals("USXHT-")) {
            return "MAINTENANCE HEAVY THEATER";
        }
        if (function.contentEquals("USXHC-")) {
            return "MAINTENANCE HEAVY CORPS";
        }
        if (function.contentEquals("USXR--")) {
            return "MAINTENANCE RECOVERY";
        }
        if (function.contentEquals("USXRT-")) {
            return "MAINTENANCE RECOVERY THEATER";
        }
        if (function.contentEquals("USXRC-")) {
            return "MAINTENANCE RECOVERY CORPS";
        }
        if (function.contentEquals("USXO--")) {
            return "ORDNANCE ";
        }
        if (function.contentEquals("USXOT-")) {
            return "ORDNANCE THEATER";
        }
        if (function.contentEquals("USXOC-")) {
            return "ORDNANCE CORPS";
        }
        if (function.contentEquals("USXOM-")) {
            return "ORDNANCE MISSILE";
        }
        if (function.contentEquals("USXOMT")) {
            return "ORDNANCE MISSILE THEATER";
        }
        if (function.contentEquals("USXOMC")) {
            return "ORDNANCE MISSILE CORPS";
        }
        if (function.contentEquals("USXE--")) {
            return "ELECTRO-OPTICAL ";
        }
        if (function.contentEquals("USXET-")) {
            return "ELECTRO-OPTICAL THEATER";
        }
        if (function.contentEquals("USXEC-")) {
            return "ELECTRO-OPTICAL CORPS";
        }
        if (function.contentEquals("UH----")) {
            return "SPECIAL C2 HEADQUARTERS COMPONENT ";
        }
        if (function.contentEquals("E-----")) {
            return "GROUND TRACK EQUIPMENT";
        }
        if (function.contentEquals("EW----")) {
            return "WEAPON ";
        }
        if (function.contentEquals("EWM---")) {
            return "MISSILE LAUNCHER";
        }
        if (function.contentEquals("EWMA--")) {
            return "AIR DEFENSE (AD) MISSILE LAUNCHER";
        }
        if (function.contentEquals("EWMAS-")) {
            return "SHORT RANGE AD MISSILE LAUNCHER";
        }
        if (function.contentEquals("EWMASR")) {
            return "TRANSPORTER LAUNCHER AND RADAR (TLAR)";
        }
        if (function.contentEquals("EWMASE")) {
            return "TRANSPORTER ERECTOR LAUNCHER AND RADAR (TELAR)   ";
        }
        if (function.contentEquals("EWMAI-")) {
            return "INTERMEDIATE RANGE AD MISSILE LAUNCHER";
        }
        if (function.contentEquals("EWMAIR")) {
            return "TRANSPORTER LAUNCHER AND RADAR (TLAR)";
        }
        if (function.contentEquals("EWMAIE")) {
            return "TRANSPORTER ERECTOR LAUNCHER AND RADAR (TELAR)   ";
        }
        if (function.contentEquals("EWMAL-")) {
            return "LONG RANGE AD MISSILE LAUNCHER";
        }
        if (function.contentEquals("EWMALR")) {
            return "TRANSPORTER LAUNCHER AND RADAR (TLAR)";
        }
        if (function.contentEquals("EWMALE")) {
            return "TRANSPORTER ERECTOR LAUNCHER AND RADAR (TELAR)   ";
        }
        if (function.contentEquals("EWMAT-")) {
            return "AD MISSILE LAUNCHER THEATER ";
        }
        if (function.contentEquals("EWMATR")) {
            return "TRANSPORTER LAUNCHER AND RADAR (TLAR)";
        }
        if (function.contentEquals("EWMATE")) {
            return "TRANSPORTER ERECTOR LAUNCHER AND RADAR (TELAR)   ";
        }
        if (function.contentEquals("EWMS--")) {
            return "SURF-SURF (SS) MISSILE LAUNCHER ";
        }
        if (function.contentEquals("EWMSS-")) {
            return "SHORT RANGE SS MISSILE LAUNCHER";
        }
        if (function.contentEquals("EWMSI-")) {
            return "INTERMEDIATE RANGE SS MISSILE LAUNCHER";
        }
        if (function.contentEquals("EWMSL-")) {
            return "LONG RANGE SS MISSILE LAUNCHER";
        }
        if (function.contentEquals("EWMT--")) {
            return "MISSILE LAUNCHER ANTITANK (AT) ";
        }
        if (function.contentEquals("EWMTL-")) {
            return "MISSILE LAUNCHER AT LIGHT ";
        }
        if (function.contentEquals("EWMTM-")) {
            return "MISSILE LAUNCHER AT MEDIUM ";
        }
        if (function.contentEquals("EWMTH-")) {
            return "MISSILE LAUNCHER AT HEAVY ";
        }
        if (function.contentEquals("EWS---")) {
            return "SINGLE ROCKET LAUNCHER";
        }
        if (function.contentEquals("EWSL--")) {
            return "SINGLE ROCKET LAUNCHER LIGHT ";
        }
        if (function.contentEquals("EWSM--")) {
            return "SINGLE ROCKET LAUNCHER MEDIUM ";
        }
        if (function.contentEquals("EWSH--")) {
            return "SINGLE ROCKET LAUNCHER HEAVY ";
        }
        if (function.contentEquals("EWX---")) {
            return "MULTIPLE ROCKET LAUNCHER";
        }
        if (function.contentEquals("EWXL--")) {
            return "MULTIPLE ROCKET LAUNCHER LIGHT ";
        }
        if (function.contentEquals("EWXM--")) {
            return "MULTIPLE ROCKET LAUNCHER MEDIUM ";
        }
        if (function.contentEquals("EWXH--")) {
            return "MULTIPLE ROCKET LAUNCHER HEAVY ";
        }
        if (function.contentEquals("EWT---")) {
            return "ANTITANK ROCKET LAUNCHER";
        }
        if (function.contentEquals("EWTL--")) {
            return "ANTITANK ROCKET LAUNCHER LIGHT ";
        }
        if (function.contentEquals("EWTM--")) {
            return "ANTITANK ROCKET LAUNCHER MEDIUM ";
        }
        if (function.contentEquals("EWTH--")) {
            return "ANTITANK ROCKET LAUNCHER HEAVY ";
        }
        if (function.contentEquals("EWR---")) {
            return "RIFLE/AUTOMATIC WEAPON";
        }
        if (function.contentEquals("EWRR--")) {
            return "RIFLE ";
        }
        if (function.contentEquals("EWRL--")) {
            return "LIGHT MACHINE GUN";
        }
        if (function.contentEquals("EWRH--")) {
            return "HEAVY MACHINE GUN";
        }
        if (function.contentEquals("EWZ---")) {
            return "GRENADE LAUNCHER";
        }
        if (function.contentEquals("EWZL--")) {
            return "GRENADE LAUNCHER LIGHT";
        }
        if (function.contentEquals("EWZM--")) {
            return "GRENADE LAUNCHER MEDIUM";
        }
        if (function.contentEquals("EWZH--")) {
            return "GRENADE LAUNCHER HEAVY";
        }
        if (function.contentEquals("EWO---")) {
            return "MORTAR ";
        }
        if (function.contentEquals("EWOL--")) {
            return "MORTAR LIGHT";
        }
        if (function.contentEquals("EWOM--")) {
            return "MORTAR MEDIUM";
        }
        if (function.contentEquals("EWOH--")) {
            return "MORTAR HEAVY";
        }
        if (function.contentEquals("EWH---")) {
            return "HOWITZER ";
        }
        if (function.contentEquals("EWHL--")) {
            return "HOWITZER LIGHT";
        }
        if (function.contentEquals("EWHLS-")) {
            return "HOWITZER LIGHT SELF-PROPELLED";
        }
        if (function.contentEquals("EWHM--")) {
            return "HOWITZER MEDIUM";
        }
        if (function.contentEquals("EWHMS-")) {
            return "HOWITZER MEDIUM SELF-PROPELLED";
        }
        if (function.contentEquals("EWHH--")) {
            return "HOWITZER HEAVY";
        }
        if (function.contentEquals("EWHHS-")) {
            return "HOWITZER HEAVY SELF-PROPELLED";
        }
        if (function.contentEquals("EWG---")) {
            return "ANTITANK GUN";
        }
        if (function.contentEquals("EWGL--")) {
            return "ANTITANK GUN LIGHT";
        }
        if (function.contentEquals("EWGM--")) {
            return "ANTITANK GUN MEDIUM";
        }
        if (function.contentEquals("EWGH--")) {
            return "ANTITANK GUN HEAVY";
        }
        if (function.contentEquals("EWGR--")) {
            return "ANTITANK GUN RECOILLESS";
        }
        if (function.contentEquals("EWD---")) {
            return "DIRECT FIRE GUN";
        }
        if (function.contentEquals("EWDL--")) {
            return "DIRECT FIRE GUN LIGHT ";
        }
        if (function.contentEquals("EWDLS-")) {
            return "DIRECT FIRE GUN LIGHT SELF-PROPELLED";
        }
        if (function.contentEquals("EWDM--")) {
            return "DIRECT FIRE GUN MEDIUM ";
        }
        if (function.contentEquals("EWDMS-")) {
            return "DIRECT FIRE GUN MEDIUM SELF-PROPELLED";
        }
        if (function.contentEquals("EWDH--")) {
            return "DIRECT FIRE GUN HEAVY ";
        }
        if (function.contentEquals("EWDHS-")) {
            return "DIRECT FIRE GUN HEAVY SELF-PROPELLED";
        }
        if (function.contentEquals("EWA---")) {
            return "AIR DEFENSE GUN";
        }
        if (function.contentEquals("EWAL--")) {
            return "AIR DEFENSE GUN LIGHT ";
        }
        if (function.contentEquals("EWAM--")) {
            return "AIR DEFENSE GUN MEDIUM ";
        }
        if (function.contentEquals("EWAH--")) {
            return "AIR DEFENSE GUN HEAVY ";
        }
        if (function.contentEquals("EV----")) {
            return "GROUND VEHICLE";
        }
        if (function.contentEquals("EVA---")) {
            return "ARMORED VEHICLE";
        }
        if (function.contentEquals("EVAT--")) {
            return "TANK ";
        }
        if (function.contentEquals("EVATL-")) {
            return "TANK LIGHT";
        }
        if (function.contentEquals("EVATLR")) {
            return "TANK LIGHT RECOVERY";
        }
        if (function.contentEquals("EVATM-")) {
            return "TANK MEDIUM";
        }
        if (function.contentEquals("EVATMR")) {
            return "TANK MEDIUM RECOVERY";
        }
        if (function.contentEquals("EVATH-")) {
            return "TANK HEAVY";
        }
        if (function.contentEquals("EVATHR")) {
            return "TANK HEAVY RECOVERY";
        }
        if (function.contentEquals("EVAA--")) {
            return "ARMORED PERSONNEL CARRIER";
        }
        if (function.contentEquals("EVAAR-")) {
            return "ARMORED PERSONNEL CARRIER RECOVERY ";
        }
        if (function.contentEquals("EVAI--")) {
            return "ARMORED INFANTRY";
        }
        if (function.contentEquals("EVAC--")) {
            return "C2V/ACV ";
        }
        if (function.contentEquals("EVAS--")) {
            return "COMBAT SERVICE SUPPORT VEHICLE ";
        }
        if (function.contentEquals("EVAL--")) {
            return "LIGHT ARMORED VEHICLE";
        }
        if (function.contentEquals("EVU---")) {
            return "UTILITY VEHICLE";
        }
        if (function.contentEquals("EVUB--")) {
            return "BUS ";
        }
        if (function.contentEquals("EVUS--")) {
            return "SEMI ";
        }
        if (function.contentEquals("EVUSL-")) {
            return "SEMI LIGHT";
        }
        if (function.contentEquals("EVUSM-")) {
            return "SEMI MEDIUM";
        }
        if (function.contentEquals("EVUSH-")) {
            return "SEMI HEAVY";
        }
        if (function.contentEquals("EVUL--")) {
            return "LIMITED CROSS-COUNTRY TRUCK";
        }
        if (function.contentEquals("EVUX--")) {
            return "CROSS-COUNTRY TRUCK";
        }
        if (function.contentEquals("EVUR--")) {
            return "WATER CRAFT";
        }
        if (function.contentEquals("EVUT--")) {
            return "TOW TRUCK";
        }
        if (function.contentEquals("EVUTL-")) {
            return "TOW TRUCK LIGHT";
        }
        if (function.contentEquals("EVUTH-")) {
            return "TOW TRUCK HEAVY";
        }
        if (function.contentEquals("EVUA--")) {
            return "AMBULANCE ";
        }
        if (function.contentEquals("EVUAA-")) {
            return "ARMORED AMBULANCE";
        }
        if (function.contentEquals("EVE---")) {
            return "ENGINEER VEHICLE";
        }
        if (function.contentEquals("EVEB--")) {
            return "BRIDGE ";
        }
        if (function.contentEquals("EVEE--")) {
            return "EARTHMOVER ";
        }
        if (function.contentEquals("EVEC--")) {
            return "CONSTRUCTION VEHICLE";
        }
        if (function.contentEquals("EVEM--")) {
            return "MINE LAYING VEHICLE";
        }
        if (function.contentEquals("EVEMV-")) {
            return "ARMORED CARRIER WITH VOLCANO ";
        }
        if (function.contentEquals("EVEML-")) {
            return "TRUCK MOUNTED WITH VOLCANO ";
        }
        if (function.contentEquals("EVEA--")) {
            return "MINE CLEARING VEHICLE";
        }
        if (function.contentEquals("EVEAA-")) {
            return "ARMORED MOUNTED MINE CLEARING VEHICLE";
        }
        if (function.contentEquals("EVEAT-")) {
            return "TRAILER MOUNTED MINE CLEARING VEHICLE";
        }
        if (function.contentEquals("EVED--")) {
            return "DOZER ";
        }
        if (function.contentEquals("EVEDA-")) {
            return "ARMORED DOZER";
        }
        if (function.contentEquals("EVES--")) {
            return "ARMORED ASSAULT";
        }
        if (function.contentEquals("EVER--")) {
            return "ARMORED ENGINEER RECON VEHICLE (AERV)";
        }
        if (function.contentEquals("EVEH--")) {
            return "BACKHOE ";
        }
        if (function.contentEquals("EVEF--")) {
            return "FERRY TRANSPORTER";
        }
        if (function.contentEquals("EVT---")) {
            return "TRAIN LOCOMOTIVE";
        }
        if (function.contentEquals("EVC---")) {
            return "CIVILIAN VEHICLE";
        }
        if (function.contentEquals("EVCA--")) {
            return "AUTOMOBILE ";
        }
        if (function.contentEquals("EVCAL-")) {
            return "COMPACT AUTOMOBILE";
        }
        if (function.contentEquals("EVCAM-")) {
            return "MIDSIZE AUTOMOBILE";
        }
        if (function.contentEquals("EVCAH-")) {
            return "SEDAN AUTOMOBILE";
        }
        if (function.contentEquals("EVCO--")) {
            return "OPEN-BED TRUCK";
        }
        if (function.contentEquals("EVCOL-")) {
            return "PICKUP OPEN-BED TRUCK";
        }
        if (function.contentEquals("EVCOM-")) {
            return "SMALL OPEN-BED TRUCK";
        }
        if (function.contentEquals("EVCOH-")) {
            return "LARGE OPEN-BED TRUCK";
        }
        if (function.contentEquals("EVCM--")) {
            return "MULTIPLE PASSENGER VEHICLE";
        }
        if (function.contentEquals("EVCML-")) {
            return "VAN MULTIPLE PASSENGER VEHICLE ";
        }
        if (function.contentEquals("EVCMM-")) {
            return "SMALL BUS MULTIPLE PASSENGER VEHICLE";
        }
        if (function.contentEquals("EVCMH-")) {
            return "LARGE BUS MULTIPLE PASSENGER VEHICLE";
        }
        if (function.contentEquals("EVCU--")) {
            return "UTILITY VEHICLE";
        }
        if (function.contentEquals("EVCUL-")) {
            return "SPORT UTILITY VEHICLE (SUV)  UTILITY VEHICLE  ";
        }
        if (function.contentEquals("EVCUM-")) {
            return "SMALL BOX TRUCK  UTILITY VEHICLE   ";
        }
        if (function.contentEquals("EVCUH-")) {
            return "LARGE BOX TRUCK  UTILITY VEHICLE   ";
        }
        if (function.contentEquals("EVCJ--")) {
            return "JEEP TYPE VEHICLE";
        }
        if (function.contentEquals("EVCJL-")) {
            return "SMALL/LIGHT JEEP TYPE VEHICLE ";
        }
        if (function.contentEquals("EVCJM-")) {
            return "MEDIUM JEEP TYPE VEHICLE ";
        }
        if (function.contentEquals("EVCJH-")) {
            return "LARGE/HEAVY JEEP TYPE VEHICLE ";
        }
        if (function.contentEquals("EVCT--")) {
            return "TRACTOR TRAILER TRUCK WITH BOX TRAILER   ";
        }
        if (function.contentEquals("EVCTL-")) {
            return "SMALL/LIGHT BOX TRAILER  TRACTOR TRAILER TRUCK  ";
        }
        if (function.contentEquals("EVCTM-")) {
            return "MEDIUM BOX TRAILER  TRACTOR TRAILER TRUCK  ";
        }
        if (function.contentEquals("EVCTH-")) {
            return "LARGE/HEAVY BOX TRAILER  TRACTOR TRAILER TRUCK  ";
        }
        if (function.contentEquals("EVCF--")) {
            return "TRACTOR TRAILER TRUCK WITH FLATBED TRAILER   ";
        }
        if (function.contentEquals("EVCFL-")) {
            return "SMALL/LIGHT FLATBED TRAILER  TRACTOR TRAILER TRUCK  ";
        }
        if (function.contentEquals("EVCFM-")) {
            return "MEDIUM FLATBED TRAILER  TRACTOR TRAILER TRUCK  ";
        }
        if (function.contentEquals("EVCFH-")) {
            return "LARGE/HEAVY FLATBED TRAILER  TRACTOR TRAILER TRUCK  ";
        }
        if (function.contentEquals("EVM---")) {
            return "PACK ANIMAL(S)";
        }
        if (function.contentEquals("EVS---")) {
            return "MISSILE SUPPORT VEHICLE";
        }
        if (function.contentEquals("EVST--")) {
            return "MISSILE SUPPORT VEHICLE TRANSLOADER ";
        }
        if (function.contentEquals("EVSR--")) {
            return "MISSILE SUPPORT VEHICLE TRANSPORTER ";
        }
        if (function.contentEquals("EVSC--")) {
            return "MISSILE SUPPORT VEHICLE CRANE/LOADING DEVICE";
        }
        if (function.contentEquals("EVSP--")) {
            return "MISSILE SUPPORT VEHICLE PROPELLANT TRANSPORTER";
        }
        if (function.contentEquals("EVSW--")) {
            return "MISSILE SUPPORT VEHICLE WARHEAD TRANSPORTER";
        }
        if (function.contentEquals("ES----")) {
            return "SENSOR ";
        }
        if (function.contentEquals("ESR---")) {
            return "RADAR ";
        }
        if (function.contentEquals("ESE---")) {
            return "EMPLACED SENSOR";
        }
        if (function.contentEquals("EX----")) {
            return "SPECIAL EQUIPMENT";
        }
        if (function.contentEquals("EXI---")) {
            return "IMPROVISED EXPLOSIVE DEVICE";
        }
        if (function.contentEquals("EXL---")) {
            return "LASER ";
        }
        if (function.contentEquals("EXN---")) {
            return "CBRN EQUIPMENT";
        }
        if (function.contentEquals("EXF---")) {
            return "FLAME THROWER";
        }
        if (function.contentEquals("EXM---")) {
            return "LAND MINES";
        }
        if (function.contentEquals("EXMC--")) {
            return "CLAYMORE ";
        }
        if (function.contentEquals("EXML--")) {
            return "LESS THAN LETHAL";
        }
        if (function.contentEquals("I-----")) {
            return "INSTALLATION ";
        }
        if (function.contentEquals("IR----")) {
            return "RAW MATERIAL PRODUCTION/STORAGE";
        }
        if (function.contentEquals("IRM---")) {
            return "MINE ";
        }
        if (function.contentEquals("IRP---")) {
            return "PETROLEUM/GAS/OIL ";
        }
        if (function.contentEquals("IRN---")) {
            return "CBRN ";
        }
        if (function.contentEquals("IRNB--")) {
            return "BIOLOGICAL ";
        }
        if (function.contentEquals("IRNC--")) {
            return "CHEMICAL ";
        }
        if (function.contentEquals("IRNN--")) {
            return "NUCLEAR ";
        }
        if (function.contentEquals("IP----")) {
            return "PROCESSING FACILITY";
        }
        if (function.contentEquals("IPD---")) {
            return "DECONTAMINATION ";
        }
        if (function.contentEquals("IE----")) {
            return "EQUIPMENT MANUFACTURE";
        }
        if (function.contentEquals("IU----")) {
            return "SERVICE  RESEARCH  UTILITY FACILITY   ";
        }
        if (function.contentEquals("IUR---")) {
            return "TECHNOLOGICAL RESEARCH FACILITY";
        }
        if (function.contentEquals("IUT---")) {
            return "TELECOMMUNICATIONS FACILITY";
        }
        if (function.contentEquals("IUE---")) {
            return "ELECTRIC POWER FACILITY";
        }
        if (function.contentEquals("IUEN--")) {
            return "NUCLEAR PLANT";
        }
        if (function.contentEquals("IUED--")) {
            return "DAM ";
        }
        if (function.contentEquals("IUEF--")) {
            return "FOSSIL FUEL";
        }
        if (function.contentEquals("IUP---")) {
            return "PUBLIC WATER SERVICES";
        }
        if (function.contentEquals("IM----")) {
            return "MILITARY MATERIEL FACILITY";
        }
        if (function.contentEquals("IMF---")) {
            return "NUCLEAR ENERGY";
        }
        if (function.contentEquals("IMFA--")) {
            return "ATOMIC ENERGY REACTOR";
        }
        if (function.contentEquals("IMFP--")) {
            return "NUCLEAR MATERIAL PRODUCTION";
        }
        if (function.contentEquals("IMFPW-")) {
            return "WEAPONS GRADE";
        }
        if (function.contentEquals("IMFS--")) {
            return "NUCLEAR MATERIAL STORAGE";
        }
        if (function.contentEquals("IMA---")) {
            return "AIRCRAFT PRODUCTION & ASSEMBLY ";
        }
        if (function.contentEquals("IME---")) {
            return "AMMUNITION AND EXPLOSIVES PRODUCTION ";
        }
        if (function.contentEquals("IMG---")) {
            return "ARMAMENT PRODUCTION";
        }
        if (function.contentEquals("IMV---")) {
            return "MILITARY VEHICLE PRODUCTION";
        }
        if (function.contentEquals("IMN---")) {
            return "ENGINEERING EQUIPMENT PRODUCTION";
        }
        if (function.contentEquals("IMNB--")) {
            return "BRIDGE ";
        }
        if (function.contentEquals("IMC---")) {
            return "CHEMICAL & BIOLOGICAL WARFARE PRODUCTION";
        }
        if (function.contentEquals("IMS---")) {
            return "SHIP CONSTRUCTION";
        }
        if (function.contentEquals("IMM---")) {
            return "MISSILE & SPACE SYSTEM PRODUCTION";
        }
        if (function.contentEquals("IG----")) {
            return "GOVERNMENT LEADERSHIP";
        }
        if (function.contentEquals("IB----")) {
            return "MILITARY BASE/FACILITY";
        }
        if (function.contentEquals("IBA---")) {
            return "AIRPORT/AIRBASE ";
        }
        if (function.contentEquals("IBN---")) {
            return "SEAPORT/NAVAL BASE";
        }
        if (function.contentEquals("IT----")) {
            return "TRANSPORT FACILITY";
        }
        if (function.contentEquals("IX----")) {
            return "MEDICAL FACILITY";
        }
        if (function.contentEquals("IXH---")) {
            return "HOSPITAL ";
        }
        if (function.contentEquals("C-----")) {
            return "COMBATANT ";
        }
        if (function.contentEquals("CL----")) {
            return "LINE ";
        }
        if (function.contentEquals("CLCV--")) {
            return "CARRIER ";
        }
        if (function.contentEquals("CLBB--")) {
            return "BATTLESHIP ";
        }
        if (function.contentEquals("CLCC--")) {
            return "CRUISER ";
        }
        if (function.contentEquals("CLDD--")) {
            return "DESTROYER ";
        }
        if (function.contentEquals("CLFF--")) {
            return "FRIGATE/CORVETTE ";
        }
        if (function.contentEquals("CLLL--")) {
            return "LITTORAL COMBATANT";
        }
        if (function.contentEquals("CLLLAS")) {
            return "ANTISUBMARINE WARFARE MISSION PACKAGE ";
        }
        if (function.contentEquals("CLLLMI")) {
            return "MINE WARFARE MISSION PACKAGE ";
        }
        if (function.contentEquals("CLLLSU")) {
            return "SURFACE WARFARE (SUW) MISSION PACKAGE";
        }
        if (function.contentEquals("CA----")) {
            return "AMPHIBIOUS WARFARE SHIP";
        }
        if (function.contentEquals("CALA--")) {
            return "ASSAULT VESSEL";
        }
        if (function.contentEquals("CALS--")) {
            return "LANDING SHIP";
        }
        if (function.contentEquals("CALSM-")) {
            return "LANDING SHIP MEDIUM";
        }
        if (function.contentEquals("CALST-")) {
            return "LANDING SHIP TANK";
        }
        if (function.contentEquals("CALC--")) {
            return "LANDING CRAFT";
        }
        if (function.contentEquals("CM----")) {
            return "MINE WARFARE VESSEL";
        }
        if (function.contentEquals("CMML--")) {
            return "MINELAYER ";
        }
        if (function.contentEquals("CMMS--")) {
            return "MINESWEEPER ";
        }
        if (function.contentEquals("CMMH--")) {
            return "MINEHUNTER ";
        }
        if (function.contentEquals("CMMA--")) {
            return "MCM SUPPORT";
        }
        if (function.contentEquals("CP----")) {
            return "PATROL ";
        }
        if (function.contentEquals("CPSB--")) {
            return "ANTISUBMARINE WARFARE";
        }
        if (function.contentEquals("CPSU--")) {
            return "ANTISURFACE WARFARE";
        }
        if (function.contentEquals("CPSUM-")) {
            return "ANTISHIP MISSILE PATROL CRAFT ";
        }
        if (function.contentEquals("CPSUT-")) {
            return "TORPEDO PATROL CRAFT";
        }
        if (function.contentEquals("CPSUG-")) {
            return "GUN PATROL CRAFT";
        }
        if (function.contentEquals("CH----")) {
            return "HOVERCRAFT ";
        }
        if (function.contentEquals("G-----")) {
            return "NAVY GROUP";
        }
        if (function.contentEquals("GT----")) {
            return "NAVY TASK FORCE";
        }
        if (function.contentEquals("GG----")) {
            return "NAVY TASK GROUP";
        }
        if (function.contentEquals("GU----")) {
            return "NAVY TASK UNIT";
        }
        if (function.contentEquals("GC----")) {
            return "CONVOY ";
        }
        if (function.contentEquals("CD----")) {
            return "SURFACE DECOY";
        }
        if (function.contentEquals("CU----")) {
            return "UNMANNED SURFACE VEHICLE";
        }
        if (function.contentEquals("CUM---")) {
            return "MINE COUNTERMEASURES SURFACE DRONE ";
        }
        if (function.contentEquals("CUS---")) {
            return "ANTISUBMARINE WARFARE SURFACE DRONE ";
        }
        if (function.contentEquals("CUN---")) {
            return "ANTISURFACE WARFARE SURFACE DRONE ";
        }
        if (function.contentEquals("CUR---")) {
            return "REMOTE MULTIMISSION VEHICLE";
        }
        if (function.contentEquals("N-----")) {
            return "NONCOMBATANT ";
        }
        if (function.contentEquals("NR----")) {
            return "UNDERWAY REPLENISHMENT";
        }
        if (function.contentEquals("NF----")) {
            return "FLEET SUPPORT";
        }
        if (function.contentEquals("NI----")) {
            return "INTELLIGENCE ";
        }
        if (function.contentEquals("NS----")) {
            return "SERVICE & SUPPORT HARBOR ";
        }
        if (function.contentEquals("NM----")) {
            return "HOSPITAL SHIP";
        }
        if (function.contentEquals("NH----")) {
            return "HOVERCRAFT ";
        }
        if (function.contentEquals("X-----")) {
            return "NON-MILITARY ";
        }
        if (function.contentEquals("XM----")) {
            return "MERCHANT ";
        }
        if (function.contentEquals("XMC---")) {
            return "CARGO ";
        }
        if (function.contentEquals("XMR---")) {
            return "ROLL ON/ROLL OFF";
        }
        if (function.contentEquals("XMO---")) {
            return "OILER/TANKER ";
        }
        if (function.contentEquals("XMTU--")) {
            return "TUG ";
        }
        if (function.contentEquals("XMF---")) {
            return "FERRY ";
        }
        if (function.contentEquals("XMP---")) {
            return "PASSENGER ";
        }
        if (function.contentEquals("XMH---")) {
            return "HAZARDOUS MATERIALS (HAZMAT)";
        }
        if (function.contentEquals("XMTO--")) {
            return "TOWING VESSEL";
        }
        if (function.contentEquals("XF----")) {
            return "FISHING ";
        }
        if (function.contentEquals("XFDF--")) {
            return "DRIFTER ";
        }
        if (function.contentEquals("XFDR--")) {
            return "DREDGE ";
        }
        if (function.contentEquals("XFTR--")) {
            return "TRAWLER ";
        }
        if (function.contentEquals("XR----")) {
            return "LEISURE CRAFT";
        }
        if (function.contentEquals("XL----")) {
            return "LAW ENFORCEMENT VESSEL";
        }
        if (function.contentEquals("XH----")) {
            return "HOVERCRAFT ";
        }
        if (function.contentEquals("XA----")) {
            return "FAST RECREACTIONAL CRAFT";
        }
        if (function.contentEquals("XAR---")) {
            return "RIGID-HULL INFLATABLE BOAT";
        }
        if (function.contentEquals("XAS---")) {
            return "SPEED BOAT";
        }
        if (function.contentEquals("XP----")) {
            return "PERSONAL WATERCRAFT";
        }
        if (function.contentEquals("O-----")) {
            return "OWN TRACK";
        }
        if (function.contentEquals("S-----")) {
            return "SUBMARINE ";
        }
        if (function.contentEquals("SF----")) {
            return "SURFACED SUBMARINE";
        }
        if (function.contentEquals("SB----")) {
            return "BOTTOMED ";
        }
        if (function.contentEquals("SR----")) {
            return "CERTAIN SUBMARINE";
        }
        if (function.contentEquals("SX----")) {
            return "NONSUBMARINE ";
        }
        if (function.contentEquals("SN----")) {
            return "NUCLEAR PROPULSION";
        }
        if (function.contentEquals("SNF---")) {
            return "SURFACED NUCLEAR PROPULSION SUBMARINE ";
        }
        if (function.contentEquals("SNA---")) {
            return "ATTACK SUBMARINE (SSN)";
        }
        if (function.contentEquals("SNM---")) {
            return "MISSILE SUBMARINE (TYPE UNKNOWN) ";
        }
        if (function.contentEquals("SNG---")) {
            return "GUIDED MISSILE SUBMARINE (SSGN) ";
        }
        if (function.contentEquals("SNB---")) {
            return "BALLISTIC MISSILE SUBMARINE (SSBN) ";
        }
        if (function.contentEquals("SC----")) {
            return "CONVENTIONAL PROPULSION";
        }
        if (function.contentEquals("SCF---")) {
            return "SURFACED CONVENTIONAL PROPULSION SUBMARINE ";
        }
        if (function.contentEquals("SCA---")) {
            return "ATTACK SUBMARINE (SS)";
        }
        if (function.contentEquals("SCM---")) {
            return "MISSILE SUBMARINE (TYPE UNKNOWN) ";
        }
        if (function.contentEquals("SCG---")) {
            return "GUIDED MISSILE SUBMARINE (SSG) ";
        }
        if (function.contentEquals("SCB---")) {
            return "BALLISTIC MISSILE SUBMARINE (SSB) ";
        }
        if (function.contentEquals("SO----")) {
            return "OTHER SUBMERSIBLE";
        }
        if (function.contentEquals("SOF---")) {
            return "SURFACED OTHER SUBMERSIBLE";
        }
        if (function.contentEquals("SU----")) {
            return "UNMANNED UNDERWATER VEHICLE (UUV) ";
        }
        if (function.contentEquals("SUM---")) {
            return "MINE WARFARE SUBSURFACE DRONE ";
        }
        if (function.contentEquals("SUS---")) {
            return "ANTISUBMARINE WARFARE SUBSURFACE DRONE ";
        }
        if (function.contentEquals("SUN---")) {
            return "ANTISURFACE WARFARE SUBSURFACE DRONE ";
        }
        if (function.contentEquals("S1----")) {
            return "POSSIBLE SUBMARINE 1";
        }
        if (function.contentEquals("S2----")) {
            return "POSSIBLE SUBMARINE 2";
        }
        if (function.contentEquals("S3----")) {
            return "POSSIBLE SUBMARINE 3";
        }
        if (function.contentEquals("S4----")) {
            return "POSSIBLE SUBMARINE 4";
        }
        if (function.contentEquals("SL----")) {
            return "PROBABLE SUBMARINE";
        }
        if (function.contentEquals("SK----")) {
            return "SNORKELING SUBMARINE";
        }
        if (function.contentEquals("W-----")) {
            return "UNDERWATER WEAPON";
        }
        if (function.contentEquals("WT----")) {
            return "TORPEDO ";
        }
        if (function.contentEquals("WM----")) {
            return "SEA MINE";
        }
        if (function.contentEquals("WMD---")) {
            return "SEA MINE NEUTRALIZED";
        }
        if (function.contentEquals("WMG---")) {
            return "SEA MINE (GROUND)";
        }
        if (function.contentEquals("WMGD--")) {
            return "SEA MINE (GROUND) NEUTRALIZED ";
        }
        if (function.contentEquals("WMGX--")) {
            return "GROUND (BOTTOM) EXERCISE MINE ";
        }
        if (function.contentEquals("WMGE--")) {
            return "GROUND (BOTTOM) MINE-LIKE ECHO (MILEC)";
        }
        if (function.contentEquals("WMGC--")) {
            return "GROUND (BOTTOM) MINE-LIKE CONTACT (MILCO)";
        }
        if (function.contentEquals("WMGR--")) {
            return "GROUND (BOTTOM) NEGATIVE REACQUISITION ";
        }
        if (function.contentEquals("WMGO--")) {
            return "GROUND (BOTTOM) NON-MINE MINE-LIKE CONTACT";
        }
        if (function.contentEquals("WMM---")) {
            return "SEA MINE (MOORED)";
        }
        if (function.contentEquals("WMMD--")) {
            return "SEA MINE (MOORED) NEUTRALIZED ";
        }
        if (function.contentEquals("WMMX--")) {
            return "MOORED EXERCISE MINE";
        }
        if (function.contentEquals("WMME--")) {
            return "MOORED MINE-LIKE ECHO";
        }
        if (function.contentEquals("WMMC--")) {
            return "MOORED MINE-LIKE CONTACT";
        }
        if (function.contentEquals("WMMR--")) {
            return "MOORED NEGATIVE REACQUISITION";
        }
        if (function.contentEquals("WMMO--")) {
            return "MOORED NON-MINE MINE-LIKE OBJECT ";
        }
        if (function.contentEquals("WMF---")) {
            return "SEA MINE (FLOATING)";
        }
        if (function.contentEquals("WMFD--")) {
            return "SEA MINE (FLOATING) NEUTRALIZED ";
        }
        if (function.contentEquals("WMFX--")) {
            return "FLOATING EXERCISE MINE";
        }
        if (function.contentEquals("WMFE--")) {
            return "FLOATING MINE-LIKE ECHO (MILEC) ";
        }
        if (function.contentEquals("WMFC--")) {
            return "FLOATING MINE-LIKE CONTACT (MILCO) ";
        }
        if (function.contentEquals("WMFR--")) {
            return "FLOATING NEGATIVE REACQUISITION";
        }
        if (function.contentEquals("WMFO--")) {
            return "FLOATING NON-MINE MINE-LIKE CONTACT ";
        }
        if (function.contentEquals("WMO---")) {
            return "SEA MINE (OTHER POSITION) ";
        }
        if (function.contentEquals("WMOD--")) {
            return "SEA MINE (OTHER POSITION) NEUTRALIZED";
        }
        if (function.contentEquals("WMX---")) {
            return "GENERAL EXERCISE MINE";
        }
        if (function.contentEquals("WME---")) {
            return "GENERAL MINE-LIKE ECHO (MILEC) ";
        }
        if (function.contentEquals("WMA---")) {
            return "GENERAL MINE ANCHOR";
        }
        if (function.contentEquals("WMC---")) {
            return "GENERAL MINE-LIKE CONTACT (MILCO) ";
        }
        if (function.contentEquals("WMR---")) {
            return "GENERAL NEGATIVE REACQUISITION";
        }
        if (function.contentEquals("WMB---")) {
            return "GENERAL OBSTRUCTOR";
        }
        if (function.contentEquals("WMBD--")) {
            return "GENERAL NEUTRALIZED OBSTRUCTOR";
        }
        if (function.contentEquals("WMN---")) {
            return "GENERAL NON-MINE MINE-LIKE OBJECT ";
        }
        if (function.contentEquals("WMS---")) {
            return "RISING MINE";
        }
        if (function.contentEquals("WMSX--")) {
            return "RISING EXERCISE MINE";
        }
        if (function.contentEquals("WMSD--")) {
            return "RISING NEUTRALIZED MINE";
        }
        if (function.contentEquals("WD----")) {
            return "UNDERWATER DECOY";
        }
        if (function.contentEquals("WDM---")) {
            return "SEA MINE DECOY";
        }
        if (function.contentEquals("WDMG--")) {
            return "GROUND (BOTTOM) DECOY";
        }
        if (function.contentEquals("WDMM--")) {
            return "MOORED DECOY";
        }
        if (function.contentEquals("N-----")) {
            return "NON-SUBMARINE ";
        }
        if (function.contentEquals("ND----")) {
            return "DIVER ";
        }
        if (function.contentEquals("E-----")) {
            return "ENVIRONMENTAL REPORT LOCATION";
        }
        if (function.contentEquals("V-----")) {
            return "DIVE REPORT LOCATION";
        }
        if (function.contentEquals("X-----")) {
            return "UNEXPLODED ORDNANCE AREA";
        }
        if (function.contentEquals("------")) {
            return "SPECIAL OPERATIONS FORCES (SOF) UNIT";
        }
        if (function.contentEquals("A-----")) {
            return "SOF UNIT AVIATION";
        }
        if (function.contentEquals("AF----")) {
            return "SOF UNIT FIXED WING ";
        }
        if (function.contentEquals("AFA---")) {
            return "SOF UNIT ATTACK";
        }
        if (function.contentEquals("AFK---")) {
            return "SOF UNIT REFUEL";
        }
        if (function.contentEquals("AFU---")) {
            return "SOF UNIT UTILITY";
        }
        if (function.contentEquals("AFUL--")) {
            return "SOF UNIT UTILITY (LIGHT) ";
        }
        if (function.contentEquals("AFUM--")) {
            return "SOF UNIT UTILITY (MEDIUM) ";
        }
        if (function.contentEquals("AFUH--")) {
            return "SOF UNIT UTILITY (HEAVY) ";
        }
        if (function.contentEquals("AV----")) {
            return "SOF UNIT V/STOL";
        }
        if (function.contentEquals("AH----")) {
            return "SOF UNIT ROTARY WING ";
        }
        if (function.contentEquals("AHH---")) {
            return "SOF UNIT COMBAT SEARCH AND RESCUE   ";
        }
        if (function.contentEquals("AHA---")) {
            return "SOF UNIT ATTACK";
        }
        if (function.contentEquals("AHU---")) {
            return "SOF UNIT UTILITY";
        }
        if (function.contentEquals("AHUL--")) {
            return "SOF UNIT UTILITY (LIGHT) ";
        }
        if (function.contentEquals("AHUM--")) {
            return "SOF UNIT UTILITY (MEDIUM) ";
        }
        if (function.contentEquals("AHUH--")) {
            return "SOF UNIT UTILITY (HEAVY) ";
        }
        if (function.contentEquals("N-----")) {
            return "SOF UNIT SOF UNIT NAVAL";
        }
        if (function.contentEquals("NS----")) {
            return "SOF UNIT SEAL";
        }
        if (function.contentEquals("NU----")) {
            return "SOF UNIT UNDERWATER DEMOLITION TEAM";
        }
        if (function.contentEquals("NB----")) {
            return "SOF UNIT SPECIAL BOAT ";
        }
        if (function.contentEquals("NN----")) {
            return "SOF UNIT SPECIAL SSNR ";
        }
        if (function.contentEquals("G-----")) {
            return "SOF UNIT GROUND";
        }
        if (function.contentEquals("GS----")) {
            return "SOF UNIT SPECIAL FORCES ";
        }
        if (function.contentEquals("GR----")) {
            return "SOF UNIT RANGER";
        }
        if (function.contentEquals("GP----")) {
            return "SOF UNIT PSYCHOLOGICAL OPERATIONS (PSYOP)";
        }
        if (function.contentEquals("GPA---")) {
            return "SOF UNIT FIXED WING AVIATION";
        }
        if (function.contentEquals("GC----")) {
            return "SOF UNIT CIVIL AFFAIRS ";
        }
        if (function.contentEquals("B-----")) {
            return "SOF UNIT SUPPORT";
        }
        if (function.contentEquals("A-----")) {
            return "ARSON/FIRE";
        }
        if (function.contentEquals("M-----")) {
            return "KILLING (GENERAL)";
        }
        if (function.contentEquals("MA----")) {
            return "MURDER";
        }
        if (function.contentEquals("MB----")) {
            return "EXECUTION";
        }
        if (function.contentEquals("MC----")) {
            return "ASSASSINATION";
        }
        if (function.contentEquals("B-----")) {
            return "BOMB/BOMBING";
        }
        if (function.contentEquals("Y-----")) {
            return "BOOBY TRAP";
        }
        if (function.contentEquals("D-----")) {
            return "DRIVE-BY SHOOTING";
        }
        if (function.contentEquals("S-----")) {
            return "SNIPING";
        }
        if (function.contentEquals("P-----")) {
            return "POISONING";
        }
        if (function.contentEquals("E-----")) {
            return "EXPLOSION";
        }
        if (function.contentEquals("EI----")) {
            return "IED EXPLOSION";
        }
        if (function.contentEquals("B-----")) {
            return "BLACK LIST LOCATION";
        }
        if (function.contentEquals("G-----")) {
            return "GRAY LIST LOCATION";
        }
        if (function.contentEquals("W-----")) {
            return "WHITE LIST LOCATION";
        }
        if (function.contentEquals("M-----")) {
            return "MASS GRAVE LOCATION";
        }
        if (function.contentEquals("P-----")) {
            return "PATROLLING";
        }
        if (function.contentEquals("R-----")) {
            return "RECRUITMENT";
        }
        if (function.contentEquals("RW----")) {
            return "RECRUITMENT (WILLING)";
        }
        if (function.contentEquals("RC----")) {
            return "RECRUITMENT (COERCED/IMPRESSED)";
        }
        if (function.contentEquals("D-----")) {
            return "DEMONSTRATION";
        }
        if (function.contentEquals("M-----")) {
            return "MINE LAYING";
        }
        if (function.contentEquals("Y-----")) {
            return "PSYCHOLOGICAL OPERATIONS (PSYOP)";
        }
        if (function.contentEquals("YT----")) {
            return "PSYOP (TV AND RADIO PROPAGANDA)";
        }
        if (function.contentEquals("YW----")) {
            return "PSYOP (WRITTEN PROPAGANDA)";
        }
        if (function.contentEquals("YH----")) {
            return "HOUSE-TO-HOUSE PROPAGANDA";
        }
        if (function.contentEquals("F-----")) {
            return "FORAGING/SEARCHING";
        }
        if (function.contentEquals("S-----")) {
            return "SPY";
        }
        if (function.contentEquals("O-----")) {
            return "FOOD DISTRIBUTION";
        }
        if (function.contentEquals("E-----")) {
            return "EXTORTION";
        }
        if (function.contentEquals("H-----")) {
            return "HIJACKING";
        }
        if (function.contentEquals("HT----")) {
            return "HIJACKING (VEHICLE)";
        }
        if (function.contentEquals("HA----")) {
            return "HIJACKING (AIRPLANE)";
        }
        if (function.contentEquals("HV----")) {
            return "HIJACKING (BOAT)";
        }
        if (function.contentEquals("K-----")) {
            return "KIDNAPPING";
        }
        if (function.contentEquals("KA----")) {
            return "ATTEMPTED";
        }
        if (function.contentEquals("A-----")) {
            return "ARREST";
        }
        if (function.contentEquals("U-----")) {
            return "DRUG OPERATION";
        }
        if (function.contentEquals("C-----")) {
            return "COMPOSITE LOSS";
        }
        if (function.contentEquals("CA----")) {
            return "COMBAT";
        }
        if (function.contentEquals("CB----")) {
            return "ACCIDENT";
        }
        if (function.contentEquals("CC----")) {
            return "OTHER";
        }
        if (function.contentEquals("R-----")) {
            return "REFUGEES";
        }
        if (function.contentEquals("S-----")) {
            return "SAFE HOUSE";
        }
        if (function.contentEquals("G-----")) {
            return "GRAFFITI";
        }
        if (function.contentEquals("V-----")) {
            return "VANDALISM/LOOT/RANSACK/PLUNDER/SACK";
        }
        if (function.contentEquals("I-----")) {
            return "KNOWN INSURGENT VEHICLE";
        }
        if (function.contentEquals("D-----")) {
            return "DRUG VEHICLE";
        }
        if (function.contentEquals("F-----")) {
            return "INTERNAL SECURITY FORCE";
        }
        if (function.contentEquals("------")) {
            return "INDIVIDUAL";
        }
        if (function.contentEquals("A-----")) {
            return "LEADER";
        }
        if (function.contentEquals("B-----")) {
            return "TARGETED";
        }
        if (function.contentEquals("C-----")) {
            return "TERRORIST";
        }
        if (function.contentEquals("A-----")) {
            return "DISPLACED PERSONS  REFUGEES  AND EVACUEES";
        }
        if (function.contentEquals("B-----")) {
            return "NONGOVERNMENTAL ORGANIZATION (NGO)";
        }
        if (function.contentEquals("C-----")) {
            return "TERRORIST";
        }
        if (function.contentEquals("D-----")) {
            return "RELIGIOUS";
        }
        if (function.contentEquals("E-----")) {
            return "FOREIGN FIGHTERS";
        }
        if (function.contentEquals("F-----")) {
            return "GANG";
        }
        if (function.contentEquals("A-----")) {
            return "ATTEMPTED  ";
        }
        if (function.contentEquals("P-----")) {
            return "PRESSURE SYSTEMS";
        }
        if (function.contentEquals("PL----")) {
            return "LOW PRESSURE CENTER";
        }
        if (function.contentEquals("PC----")) {
            return "CYCLONE CENTER";
        }
        if (function.contentEquals("PLT---")) {
            return "TROPOPAUSE LOW";
        }
        if (function.contentEquals("PH----")) {
            return "HIGH PRESSURE CENTER";
        }
        if (function.contentEquals("PA----")) {
            return "ANTICYCLONE CENTER";
        }
        if (function.contentEquals("PHT---")) {
            return "TROPOPAUSE HIGH";
        }
        if (function.contentEquals("PF----")) {
            return "FRONTAL SYSTEMS";
        }
        if (function.contentEquals("PFC---")) {
            return "COLD FRONT";
        }
        if (function.contentEquals("PFCU--")) {
            return "UPPER COLD FRONT";
        }
        if (function.contentEquals("PFC-FG")) {
            return "COLD FRONTOGENESIS";
        }
        if (function.contentEquals("PFC-FY")) {
            return "COLD FRONTOLYSIS";
        }
        if (function.contentEquals("PFW---")) {
            return "WARM FRONT";
        }
        if (function.contentEquals("PFWU--")) {
            return "UPPER WARM FRONT";
        }
        if (function.contentEquals("PFW-FG")) {
            return "WARM FRONTOGENESIS";
        }
        if (function.contentEquals("PFW-FY")) {
            return "WARM FRONTOLYSIS";
        }
        if (function.contentEquals("PFO---")) {
            return "OCCLUDED FRONT";
        }
        if (function.contentEquals("PFOU--")) {
            return "UPPER OCCLUDED FRONT";
        }
        if (function.contentEquals("PFO-FY")) {
            return "OCCLUDED FRONTOLYSIS ";
        }
        if (function.contentEquals("PFS---")) {
            return "STATIONARY FRONT";
        }
        if (function.contentEquals("PFSU--")) {
            return "UPPER STATIONARY FRONT";
        }
        if (function.contentEquals("PFS-FG")) {
            return "STATIONARY FRONTOGENESIS";
        }
        if (function.contentEquals("PFS-FY")) {
            return "STATIONARY FRONTOLYSIS";
        }
        if (function.contentEquals("PX----")) {
            return "LINES";
        }
        if (function.contentEquals("PXT---")) {
            return "TROUGH AXIS";
        }
        if (function.contentEquals("PXR---")) {
            return "RIDGE AXIS";
        }
        if (function.contentEquals("PXSQ--")) {
            return "SEVERE SQUALL LINE";
        }
        if (function.contentEquals("PXIL--")) {
            return "INSTABILITY LINE";
        }
        if (function.contentEquals("PXSH--")) {
            return "SHEAR LINE";
        }
        if (function.contentEquals("PXITCZ")) {
            return "INTER-TROPICAL CONVERGANCE ZONE";
        }
        if (function.contentEquals("PXCV--")) {
            return "CONVERGANCE LINE";
        }
        if (function.contentEquals("PXITD-")) {
            return "INTER-TROPICAL DISCONTINUITY";
        }
        if (function.contentEquals("T-----")) {
            return "TURBULENCE";
        }
        if (function.contentEquals("TL----")) {
            return "TURBULENCE - LIGHT";
        }
        if (function.contentEquals("TM----")) {
            return "TURBULENCE - MODERATE";
        }
        if (function.contentEquals("TS----")) {
            return "TURBULENCE - SEVERE";
        }
        if (function.contentEquals("TE----")) {
            return "TURBULENCE - EXTREME";
        }
        if (function.contentEquals("I-----")) {
            return "ICING";
        }
        if (function.contentEquals("IC----")) {
            return "CLEAR ICING";
        }
        if (function.contentEquals("ICL---")) {
            return "CLEAR ICING - LIGHT";
        }
        if (function.contentEquals("ICM---")) {
            return "CLEAR ICING - MODERATE";
        }
        if (function.contentEquals("ICS---")) {
            return "CLEAR ICING - SEVERE";
        }
        if (function.contentEquals("IR----")) {
            return "RIME ICING ";
        }
        if (function.contentEquals("IRL---")) {
            return "RIME ICING - LIGHT";
        }
        if (function.contentEquals("IRM---")) {
            return "RIME ICING - MODERATE";
        }
        if (function.contentEquals("IRS---")) {
            return "RIME ICING - SEVERE";
        }
        if (function.contentEquals("IM----")) {
            return "MIXED ICING";
        }
        if (function.contentEquals("IML---")) {
            return "MIXED ICING - LIGHT";
        }
        if (function.contentEquals("IMM---")) {
            return "MIXED ICING - MODERATE";
        }
        if (function.contentEquals("IMS---")) {
            return "MIXED ICING - SEVERE";
        }
        if (function.contentEquals("W-----")) {
            return "WINDS";
        }
        if (function.contentEquals("WC----")) {
            return "CALM WINDS";
        }
        if (function.contentEquals("WP----")) {
            return "WIND PLOT";
        }
        if (function.contentEquals("WJ----")) {
            return "JET STREAM";
        }
        if (function.contentEquals("WS----")) {
            return "STREAM LINE";
        }
        if (function.contentEquals("CC----")) {
            return "CLOUD COVERAGE";
        }
        if (function.contentEquals("CCCS--")) {
            return "CLOUD COVERAGE SYMBOLS";
        }
        if (function.contentEquals("CCCSCS")) {
            return "CLEAR SKY";
        }
        if (function.contentEquals("CCCSFC")) {
            return "FEW COVERAGE";
        }
        if (function.contentEquals("CCCSSC")) {
            return "SCATTERED COVERAGE";
        }
        if (function.contentEquals("CCCSBC")) {
            return "BROKEN COVERAGE";
        }
        if (function.contentEquals("CCCSOC")) {
            return "OVERCAST COVERAGE";
        }
        if (function.contentEquals("CCCSOB")) {
            return "SKY TOTALLY OR PARTIALLY OBSCURED";
        }
        if (function.contentEquals("WS----")) {
            return "WEATHER SYMBOLS";
        }
        if (function.contentEquals("WSR---")) {
            return "RAIN";
        }
        if (function.contentEquals("WSR-LI")) {
            return "RAIN - INTERMITTENT LIGHT";
        }
        if (function.contentEquals("WSR-LC")) {
            return "RAIN - CONTINUOUS LIGHT";
        }
        if (function.contentEquals("WSR-MI")) {
            return "RAIN - INTERMITTENT MODERATE ";
        }
        if (function.contentEquals("WSR-MC")) {
            return "RAIN - CONTINUOUS MODERATE";
        }
        if (function.contentEquals("WSR-HI")) {
            return "RAIN - INTERMITTENT HEAVY";
        }
        if (function.contentEquals("WSR-HC")) {
            return "RAIN - CONTINUOUS HEAVY";
        }
        if (function.contentEquals("WSRF--")) {
            return "FREEZING RAIN";
        }
        if (function.contentEquals("WSRFL-")) {
            return "FREEZING RAIN - LIGHT";
        }
        if (function.contentEquals("WSRFMH")) {
            return "FREEZING RAIN - MODERATE/HEAVY";
        }
        if (function.contentEquals("WSRS--")) {
            return "RAIN SHOWERS";
        }
        if (function.contentEquals("WSRSL-")) {
            return "RAIN SHOWERS - LIGHT";
        }
        if (function.contentEquals("WSRSMH")) {
            return "RAIN SHOWERS - MODERATE/HEAVY";
        }
        if (function.contentEquals("WSRST-")) {
            return "RAIN SHOWERS - TORRENTIAL";
        }
        if (function.contentEquals("WSD---")) {
            return "DRIZZLE";
        }
        if (function.contentEquals("WSD-LI")) {
            return "DRIZZLE - INTERMITTENT LIGHT";
        }
        if (function.contentEquals("WSD-LC")) {
            return "DRIZZLE - CONTINUOUS LIGHT";
        }
        if (function.contentEquals("WSD-MI")) {
            return "DRIZZLE - INTERMITTENT MODERATE";
        }
        if (function.contentEquals("WSD-MC")) {
            return "DRIZZLE - CONTINUOUS MODERATE";
        }
        if (function.contentEquals("WSD-HI")) {
            return "DRIZZLE - INTERMITTENT HEAVY";
        }
        if (function.contentEquals("WSD-HC")) {
            return "DRIZZLE - CONTINUOUS HEAVY";
        }
        if (function.contentEquals("WSDF--")) {
            return "FREEZING DRIZZLE";
        }
        if (function.contentEquals("WSDFL-")) {
            return "FREEZING DRIZZLE - LIGHT";
        }
        if (function.contentEquals("WSDFMH")) {
            return "FREEZING DRIZZLE - MODERATE/HEAVY";
        }
        if (function.contentEquals("WSM---")) {
            return "RAIN AND SNOW MIXED";
        }
        if (function.contentEquals("WSM-L-")) {
            return "RAIN OR DRIZZLE AND SNOW - LIGHT";
        }
        if (function.contentEquals("WSM-MH")) {
            return "RAIN OR DRIZZLE AND SNOW - MODERATE/HEAVY";
        }
        if (function.contentEquals("WSMSL-")) {
            return "RAIN AND SNOW SHOWERS - LIGHT";
        }
        if (function.contentEquals("WSMSMH")) {
            return "RAIN AND SNOW SHOWERS - MODERATE/HEAVY ";
        }
        if (function.contentEquals("WSS---")) {
            return "SNOW";
        }
        if (function.contentEquals("WSS-LI")) {
            return "SNOW - INTERMITTENT LIGHT";
        }
        if (function.contentEquals("WSS-LC")) {
            return "SNOW - CONTINUOUS LIGHT";
        }
        if (function.contentEquals("WSS-MI")) {
            return "SNOW - INTERMITTENT MODERATE";
        }
        if (function.contentEquals("WSS-MC")) {
            return "SNOW - CONTINUOUS MODERATE";
        }
        if (function.contentEquals("WSS-HI")) {
            return "SNOW - INTERMITTENT HEAVY";
        }
        if (function.contentEquals("WSS-HC")) {
            return "SNOW - CONTINUOUS HEAVY";
        }
        if (function.contentEquals("WSSBLM")) {
            return "BLOWING SNOW - LIGHT/MODERATE";
        }
        if (function.contentEquals("WSSBH-")) {
            return "BLOWING SNOW - HEAVY";
        }
        if (function.contentEquals("WSSG--")) {
            return "SNOW GRAINS";
        }
        if (function.contentEquals("WSSS--")) {
            return "SNOW SHOWERS";
        }
        if (function.contentEquals("WSSSL-")) {
            return "SNOW SHOWERS - LIGHT";
        }
        if (function.contentEquals("WSSSMH")) {
            return "SNOW SHOWERS - MODERATE/HEAVY";
        }
        if (function.contentEquals("WSGR--")) {
            return "HAIL";
        }
        if (function.contentEquals("WSGRL-")) {
            return "HAIL - LIGHT NOT ASSOCIATED WITH THUNDER";
        }
        if (function.contentEquals("WSGRMH")) {
            return "HAIL - MODERATE/HEAVY NOT ASSOCIATED WITH THUNDER";
        }
        if (function.contentEquals("WSIC--")) {
            return "ICE CRYSTALS (DIAMOND DUST)";
        }
        if (function.contentEquals("WSPL--")) {
            return "ICE PELLETS (SLEET)";
        }
        if (function.contentEquals("WSPLL-")) {
            return "ICE PELLETS - LIGHT";
        }
        if (function.contentEquals("WSPLM-")) {
            return "ICE PELLETS - MODERATE";
        }
        if (function.contentEquals("WSPLH-")) {
            return "ICE PELLETS - HEAVY";
        }
        if (function.contentEquals("WST---")) {
            return "STORMS";
        }
        if (function.contentEquals("WST-NP")) {
            return "THUNDERSTORM - NO PRECIPITATION";
        }
        if (function.contentEquals("WSTMR-")) {
            return "THUNDERSTORM LIGHT TO MODERATE WITH RAIN/SNOW - NO HAIL ";
        }
        if (function.contentEquals("WSTHR-")) {
            return "THUNDERSTORM HEAVY WITH RAIN/SNOW - NO HAIL";
        }
        if (function.contentEquals("WSTMH-")) {
            return "THUNDERSTORM LIGHT TO MODERATE - WITH HAIL";
        }
        if (function.contentEquals("WSTHH-")) {
            return "THUNDERSTORM HEAVY - WITH HAIL";
        }
        if (function.contentEquals("WST-FC")) {
            return "FUNNEL CLOUD (TORNADO/WATERSPOUT)";
        }
        if (function.contentEquals("WST-SQ")) {
            return "SQUALL";
        }
        if (function.contentEquals("WST-LG")) {
            return "LIGHTNING";
        }
        if (function.contentEquals("WSFG--")) {
            return "FOG";
        }
        if (function.contentEquals("WSFGPS")) {
            return "FOG - SHALLOW PATCHES";
        }
        if (function.contentEquals("WSFGCS")) {
            return "FOG - SHALLOW CONTINUOUS";
        }
        if (function.contentEquals("WSFGP-")) {
            return "FOG - PATCHY";
        }
        if (function.contentEquals("WSFGSV")) {
            return "FOG - SKY VISIBLE";
        }
        if (function.contentEquals("WSFGSO")) {
            return "FOG - SKY OBSCURED";
        }
        if (function.contentEquals("WSFGFV")) {
            return "FOG - FREEZING  SKY VISIBLE   ";
        }
        if (function.contentEquals("WSFGFO")) {
            return "FOG - FREEZING  SKY NOT VISIBLE   ";
        }
        if (function.contentEquals("WSBR--")) {
            return "MIST";
        }
        if (function.contentEquals("WSFU--")) {
            return "SMOKE";
        }
        if (function.contentEquals("WSHZ--")) {
            return "HAZE";
        }
        if (function.contentEquals("WSD---")) {
            return "DUST OR SAND";
        }
        if (function.contentEquals("WSDSLM")) {
            return "DUST/SAND STORM - LIGHT TO MODERATE";
        }
        if (function.contentEquals("WSDSS-")) {
            return "DUST/SAND STORM - SEVERE";
        }
        if (function.contentEquals("WSDD--")) {
            return "DUST DEVIL";
        }
        if (function.contentEquals("WSDB--")) {
            return "BLOWING DUST OR SAND";
        }
        if (function.contentEquals("WSTS--")) {
            return "TROPICAL STORM SYSTEMS";
        }
        if (function.contentEquals("WSTSD-")) {
            return "TROPICAL DEPRESSION";
        }
        if (function.contentEquals("WSTSS-")) {
            return "TROPICAL STORM ";
        }
        if (function.contentEquals("WSTSH-")) {
            return "HURRICANE/TYPHOON";
        }
        if (function.contentEquals("WSTSWA")) {
            return "TROPICAL STORM WIND AREAS AND DATE/TIME LABELS";
        }
        if (function.contentEquals("WSVE--")) {
            return "VOLCANIC ERUPTION";
        }
        if (function.contentEquals("WSVA--")) {
            return "VOLCANIC ASH";
        }
        if (function.contentEquals("WST-LV")) {
            return "TROPOPAUSE LEVEL";
        }
        if (function.contentEquals("WSF-LV")) {
            return "FREEZING LEVEL";
        }
        if (function.contentEquals("WSUKP-")) {
            return "PRECIPITATION OF UNKNOWN TYPE AND INTENSITY";
        }
        if (function.contentEquals("BA----")) {
            return "BOUNDED AREAS OF WEATHER";
        }
        if (function.contentEquals("BAIF--")) {
            return "INSTRUMENT FLIGHT RULE (IFR)";
        }
        if (function.contentEquals("BAMV--")) {
            return "MARGINAL VISUAL FLIGHT RULE (MVFR)";
        }
        if (function.contentEquals("BATB--")) {
            return "TURBULENCE";
        }
        if (function.contentEquals("BAI---")) {
            return "ICING";
        }
        if (function.contentEquals("BALPC-")) {
            return "LIQUID PRECIPITATION - CONVECTIVE";
        }
        if (function.contentEquals("BAFP--")) {
            return "FREEZING/FROZEN PRECIPITATION";
        }
        if (function.contentEquals("BAT---")) {
            return "THUNDERSTORMS";
        }
        if (function.contentEquals("BAFG--")) {
            return "FOG";
        }
        if (function.contentEquals("BAD---")) {
            return "DUST OR SAND";
        }
        if (function.contentEquals("BAFF--")) {
            return "OPERATOR-DEFINED FREEFORM";
        }
        if (function.contentEquals("IP----")) {
            return "ISOPLETHS";
        }
        if (function.contentEquals("IPIB--")) {
            return "ISOBAR - SURFACE";
        }
        if (function.contentEquals("IPCO--")) {
            return "CONTOUR - UPPER AIR";
        }
        if (function.contentEquals("IPIS--")) {
            return "ISOTHERM";
        }
        if (function.contentEquals("IPIT--")) {
            return "ISOTACH ";
        }
        if (function.contentEquals("IPID--")) {
            return "ISODROSOTHERM";
        }
        if (function.contentEquals("IPTH--")) {
            return "THICKNESS";
        }
        if (function.contentEquals("IPFF--")) {
            return "OPERATOR-DEFINED FREEFORM";
        }
        if (function.contentEquals("G-----")) {
            return "STATE OF THE GROUND";
        }
        if (function.contentEquals("GN----")) {
            return "WITHOUT SNOW OR MEASURABLE ICE COVER";
        }
        if (function.contentEquals("GND-NC")) {
            return "SURFACE DRY WITHOUT CRACKS OR APPRECIABLE DUST OR LOOSE SAND";
        }
        if (function.contentEquals("GNM---")) {
            return "SURFACE MOIST";
        }
        if (function.contentEquals("GNW-SW")) {
            return "SURFACE WET  STANDING WATER IN SMALL OR LARGE POOLS   ";
        }
        if (function.contentEquals("GNFL--")) {
            return "SURFACE FLOODED";
        }
        if (function.contentEquals("GNFZ--")) {
            return "SURFACE FROZEN";
        }
        if (function.contentEquals("GNG-TI")) {
            return "GLAZE (THIN ICE) ON GROUND";
        }
        if (function.contentEquals("GNLDN-")) {
            return "LOOSE   DRY   DUST   OR   SAND   NOT   COVERING   GROUND COMPLETELY";
        }
        if (function.contentEquals("GNLDTC")) {
            return "THIN   LOOSE   DRY   DUST   OR   SAND   COVERING   GROUND COMPLETELY";
        }
        if (function.contentEquals("GNLDMC")) {
            return "MODERATE/THICK  LOOSE  DRY  DUST  OR  SAND  COVERING GROUND COMPLETELY";
        }
        if (function.contentEquals("GNDEWC")) {
            return "EXTREMELY DRY WITH CRACKS";
        }
        if (function.contentEquals("GS----")) {
            return "WITH SNOW OR MEASURABLE ICE COVER";
        }
        if (function.contentEquals("GSI---")) {
            return "PREDOMINATELY ICE COVERED";
        }
        if (function.contentEquals("GSSCL-")) {
            return "COMPACT OR WET SNOW (WITH OR WITHOUT ICE) COVERING LESS THAN ONE-HALF OF GROUND";
        }
        if (function.contentEquals("GSSCH-")) {
            return "COMPACT OR WET SNOW (WITH OR WITHOUT ICE) COVERING AT LEAST ONE-HALF GROUND ";
        }
        if (function.contentEquals("GSSCCE")) {
            return "EVEN LAYER OF COMPACT OR WET SNOW COVERING GROUND COMPLETELY";
        }
        if (function.contentEquals("GSSCCU")) {
            return "UNEVEN LAYER OF COMPACT OR WET SNOW COVERING GROUND COMPLETELY";
        }
        if (function.contentEquals("GSSLL-")) {
            return "LOOSE DRY SNOW COVERING LESS THAN ONE-HALF OF GROUND";
        }
        if (function.contentEquals("GSSLH-")) {
            return "LOOSE DRY SNOW COVERING AT LEAST ONE-HALF GROUND  BUT GROUND NOT COMPLETELY COVERED";
        }
        if (function.contentEquals("GSSLCE")) {
            return "EVEN   LAYER  OF   LOOSE  DRY   SNOW  COVERING  GROUND COMPLETELY";
        }
        if (function.contentEquals("GSSLCU")) {
            return "UNEVEN  LAYER  OF  LOOSE  DRY  SNOW  COVERING  GROUND COMPLETELY";
        }
        if (function.contentEquals("GSSDC-")) {
            return "SNOW COVERING GROUND COMPLETELY; DEEP DRIFTS";
        }
        if (function.contentEquals("I-----")) {
            return "ICE SYSTEMS";
        }
        if (function.contentEquals("IB----")) {
            return "ICEBERGS";
        }
        if (function.contentEquals("IBM---")) {
            return "MANY ICEBERGS";
        }
        if (function.contentEquals("IBBS--")) {
            return "BELTS AND STRIPS";
        }
        if (function.contentEquals("IBG---")) {
            return "ICEBERG - GENERAL";
        }
        if (function.contentEquals("IBMG--")) {
            return "MANY ICEBERGS - GENERAL";
        }
        if (function.contentEquals("IBBB--")) {
            return "BERGY BIT";
        }
        if (function.contentEquals("IBBBM-")) {
            return "MANY BERGY BITS";
        }
        if (function.contentEquals("IBGL--")) {
            return "GROWLER";
        }
        if (function.contentEquals("IBGLM-")) {
            return "MANY GROWLERS";
        }
        if (function.contentEquals("IBF---")) {
            return "FLOEBERG";
        }
        if (function.contentEquals("IBII--")) {
            return "ICE ISLAND";
        }
        if (function.contentEquals("IC----")) {
            return "ICE CONCENTRATION ";
        }
        if (function.contentEquals("ICWB--")) {
            return "BERGY WATER";
        }
        if (function.contentEquals("ICWR--")) {
            return "WATER WITH RADAR TARGETS";
        }
        if (function.contentEquals("ICIF--")) {
            return "ICE FREE";
        }
        if (function.contentEquals("ID----")) {
            return "DYNAMIC PROCESSES";
        }
        if (function.contentEquals("IDC---")) {
            return "CONVERGENCE";
        }
        if (function.contentEquals("IDD---")) {
            return "DIVERGENCE";
        }
        if (function.contentEquals("IDS---")) {
            return "SHEARING OR SHEAR ZONE";
        }
        if (function.contentEquals("IDID--")) {
            return "ICE DRIFT (DIRECTION)";
        }
        if (function.contentEquals("II----")) {
            return "SEA ICE";
        }
        if (function.contentEquals("IITM--")) {
            return "ICE THICKNESS (OBSERVED)";
        }
        if (function.contentEquals("IITE--")) {
            return "ICE THICKNESS (ESTIMATED)";
        }
        if (function.contentEquals("IIP---")) {
            return "MELT PUDDLES OR FLOODED ICE";
        }
        if (function.contentEquals("IL----")) {
            return "LIMITS";
        }
        if (function.contentEquals("ILOV--")) {
            return "LIMIT OF VISUAL OBSERVATION";
        }
        if (function.contentEquals("ILUC--")) {
            return "LIMIT OF UNDERCAST";
        }
        if (function.contentEquals("ILOR--")) {
            return "LIMIT OF RADAR OBSERVATION";
        }
        if (function.contentEquals("ILIEO-")) {
            return "OBSERVED ICE EDGE OR BOUNDARY";
        }
        if (function.contentEquals("ILIEE-")) {
            return "ESTIMATED ICE EDGE OR BOUNDARY";
        }
        if (function.contentEquals("ILIER-")) {
            return "ICE EDGE OR BOUNDARY FROM RADAR";
        }
        if (function.contentEquals("IO----")) {
            return "OPENINGS IN THE ICE";
        }
        if (function.contentEquals("IOC---")) {
            return "CRACKS";
        }
        if (function.contentEquals("IOCS--")) {
            return "CRACKS AT A SPECIFIC LOCATION";
        }
        if (function.contentEquals("IOL---")) {
            return "LEAD";
        }
        if (function.contentEquals("IOLF--")) {
            return "FROZEN LEAD";
        }
        if (function.contentEquals("ISC---")) {
            return "SNOW COVER ";
        }
        if (function.contentEquals("ISS---")) {
            return "SASTRUGI (WITH ORIENTATION)";
        }
        if (function.contentEquals("IT----")) {
            return "TOPOGRAPHICAL FEATURES";
        }
        if (function.contentEquals("ITRH--")) {
            return "RIDGES OR HUMMOCKS";
        }
        if (function.contentEquals("ITR---")) {
            return "RAFTING";
        }
        if (function.contentEquals("ITBB--")) {
            return "JAMMED BRASH BARRIER";
        }
        if (function.contentEquals("H-----")) {
            return "HYDROGRAPHY";
        }
        if (function.contentEquals("HD----")) {
            return "DEPTH";
        }
        if (function.contentEquals("HDS---")) {
            return "SOUNDINGS";
        }
        if (function.contentEquals("HDDL--")) {
            return "DEPTH CURVE";
        }
        if (function.contentEquals("HDDC--")) {
            return "DEPTH CONTOUR";
        }
        if (function.contentEquals("HDDA--")) {
            return "DEPTH AREA";
        }
        if (function.contentEquals("HC----")) {
            return "COASTAL HYDROGRAPHY";
        }
        if (function.contentEquals("HCC---")) {
            return "COASTLINE";
        }
        if (function.contentEquals("HCI---")) {
            return "ISLAND";
        }
        if (function.contentEquals("HCB---")) {
            return "BEACH";
        }
        if (function.contentEquals("HCW---")) {
            return "WATER";
        }
        if (function.contentEquals("HCF---")) {
            return "FORESHORE";
        }
        if (function.contentEquals("HCF---")) {
            return "FORESHORE";
        }
        if (function.contentEquals("HCF---")) {
            return "FORESHORE";
        }
        if (function.contentEquals("HP----")) {
            return "PORTS AND HARBORS";
        }
        if (function.contentEquals("HPB---")) {
            return "PORTS";
        }
        if (function.contentEquals("HPB-O-")) {
            return "BERTHS (ONSHORE)";
        }
        if (function.contentEquals("HPB-A-")) {
            return "BERTHS (ANCHOR)";
        }
        if (function.contentEquals("HPBA--")) {
            return "ANCHORAGE";
        }
        if (function.contentEquals("HPBA--")) {
            return "ANCHORAGE ";
        }
        if (function.contentEquals("HPBA--")) {
            return "ANCHORAGE";
        }
        if (function.contentEquals("HPCP--")) {
            return "CALL IN POINT";
        }
        if (function.contentEquals("HPBP--")) {
            return "PIER/WHARF/QUAY";
        }
        if (function.contentEquals("HPF---")) {
            return "FISHING";
        }
        if (function.contentEquals("HPFH--")) {
            return "FISHING HARBOR";
        }
        if (function.contentEquals("HPFS--")) {
            return "FISH STAKES/TRAPS/WEIRS";
        }
        if (function.contentEquals("HPFS--")) {
            return "FISH STAKES/TRAPS/WEIRS";
        }
        if (function.contentEquals("HPFF--")) {
            return "FISH STAKES/TRAPS/WEIRS";
        }
        if (function.contentEquals("HPM---")) {
            return "FACILITIES";
        }
        if (function.contentEquals("HPMD--")) {
            return "DRYDOCK";
        }
        if (function.contentEquals("HPML--")) {
            return "LANDING PLACE";
        }
        if (function.contentEquals("HPMO--")) {
            return "OFFSHORE LOADING FACILITY";
        }
        if (function.contentEquals("HPMO--")) {
            return "OFFSHORE LOADING FACILITY";
        }
        if (function.contentEquals("HPMO--")) {
            return "OFFSHORE LOADING FACILITY";
        }
        if (function.contentEquals("HPMRA-")) {
            return "RAMP (ABOVE WATER)";
        }
        if (function.contentEquals("HPMRB-")) {
            return "RAMP (BELOW WATER)";
        }
        if (function.contentEquals("HPM-R-")) {
            return "LANDING RING";
        }
        if (function.contentEquals("HPM-FC")) {
            return "FERRY CROSSING";
        }
        if (function.contentEquals("HPM-CC")) {
            return "CABLE FERRY CROSSING";
        }
        if (function.contentEquals("HPD---")) {
            return "DOLPHIN";
        }
        if (function.contentEquals("HPP---")) {
            return "SHORELINE PROTECTION";
        }
        if (function.contentEquals("HPSPA-")) {
            return "BREAKWATER/GROIN/JETTY (ABOVE WATER)";
        }
        if (function.contentEquals("HPSPB-")) {
            return "BREAKWATER/GROIN/JETTY (BELOW WATER)";
        }
        if (function.contentEquals("HPSPS-")) {
            return "SEAWALL";
        }
        if (function.contentEquals("HA----")) {
            return "AIDS TO NAVIGATION ";
        }
        if (function.contentEquals("HABA--")) {
            return "BEACON";
        }
        if (function.contentEquals("HABB--")) {
            return "BUOY DEFAULT";
        }
        if (function.contentEquals("HABM--")) {
            return "MARKER";
        }
        if (function.contentEquals("HABP--")) {
            return "PERCHES/STAKES";
        }
        if (function.contentEquals("HABP--")) {
            return "PERCHES/STAKES";
        }
        if (function.contentEquals("HABP--")) {
            return "PERCHES/STAKES";
        }
        if (function.contentEquals("HAL---")) {
            return "LIGHT";
        }
        if (function.contentEquals("HALLA-")) {
            return "LEADING LINE";
        }
        if (function.contentEquals("HALV--")) {
            return "LIGHT VESSEL/LIGHTSHIP";
        }
        if (function.contentEquals("HALH--")) {
            return "LIGHTHOUSE";
        }
        if (function.contentEquals("HH----")) {
            return "DANGERS/HAZARDS";
        }
        if (function.contentEquals("HHRS--")) {
            return "ROCK SUBMERGERED";
        }
        if (function.contentEquals("HHRA--")) {
            return "ROCK AWASHED";
        }
        if (function.contentEquals("HHD---")) {
            return "UNDERWATER DANGER/HAZARD";
        }
        if (function.contentEquals("HHDF--")) {
            return "FOUL GROUND";
        }
        if (function.contentEquals("HHDF--")) {
            return "FOUL GROUND";
        }
        if (function.contentEquals("HHDF--")) {
            return "FOUL GROUND";
        }
        if (function.contentEquals("HHDK--")) {
            return "KELP/SEAWEED";
        }
        if (function.contentEquals("HHDK--")) {
            return "KELP/SEAWEED";
        }
        if (function.contentEquals("HHDK--")) {
            return "KELP/SEAWEED";
        }
        if (function.contentEquals("HHDMD-")) {
            return "MINE-NAVAL";
        }
        if (function.contentEquals("HHDMDB")) {
            return "MINE-NAVAL (DOUBTFUL)";
        }
        if (function.contentEquals("HHDMDF")) {
            return "MINE-NAVAL (DEFINITE)";
        }
        if (function.contentEquals("HHDS--")) {
            return "SNAGS/STUMPS";
        }
        if (function.contentEquals("HHDWA-")) {
            return "WRECK ";
        }
        if (function.contentEquals("HHDWA-")) {
            return "WRECK (UNCOVERS)";
        }
        if (function.contentEquals("HHDWB-")) {
            return "WRECK (SUBMERGED)";
        }
        if (function.contentEquals("HHDB--")) {
            return "BREAKERS";
        }
        if (function.contentEquals("HHDR--")) {
            return "REEF";
        }
        if (function.contentEquals("HHDE--")) {
            return "EDDIES/OVERFALLS/TIDE RIPS";
        }
        if (function.contentEquals("HHDD--")) {
            return "DISCOLORED WATER";
        }
        if (function.contentEquals("BF----")) {
            return "BOTTOM FEATURES";
        }
        if (function.contentEquals("BFC---")) {
            return "BOTTOM CHARACTERISTICS";
        }
        if (function.contentEquals("BFC-S-")) {
            return "SAND";
        }
        if (function.contentEquals("BFC-M-")) {
            return "MUD";
        }
        if (function.contentEquals("BFC-CL")) {
            return "CLAY";
        }
        if (function.contentEquals("BFC-SI")) {
            return "SILT";
        }
        if (function.contentEquals("BFC-ST")) {
            return "STONES";
        }
        if (function.contentEquals("BFC-G-")) {
            return "GRAVEL";
        }
        if (function.contentEquals("BFC-P-")) {
            return "PEBBLES";
        }
        if (function.contentEquals("BFC-CB")) {
            return "COBBLES";
        }
        if (function.contentEquals("BFC-R-")) {
            return "ROCK";
        }
        if (function.contentEquals("BFC-CO")) {
            return "CORAL";
        }
        if (function.contentEquals("BFC-SH")) {
            return "SHELL";
        }
        if (function.contentEquals("BFQ---")) {
            return "QUALIFYING TERMS";
        }
        if (function.contentEquals("BFQ-F-")) {
            return "FINE";
        }
        if (function.contentEquals("BFQ-M-")) {
            return "MEDIUM";
        }
        if (function.contentEquals("BFQ-C-")) {
            return "COARSE";
        }
        if (function.contentEquals("TCC---")) {
            return "TIDE AND CURRENT";
        }
        if (function.contentEquals("TCCW--")) {
            return "WATER TURBULENCE ";
        }
        if (function.contentEquals("TCCCFE")) {
            return "CURRENT FLOW - EBB";
        }
        if (function.contentEquals("TCCCFF")) {
            return "CURRENT FLOW - FLOOD";
        }
        if (function.contentEquals("TCCTD-")) {
            return "TIDE DATA POINT";
        }
        if (function.contentEquals("TCCTG-")) {
            return "TIDE GAUGE";
        }
        if (function.contentEquals("O-----")) {
            return "OCEANOGRAPHY";
        }
        if (function.contentEquals("OB----")) {
            return "BIOLUMINESCENCE";
        }
        if (function.contentEquals("OBVA--")) {
            return "VDR LEVEL 1-2";
        }
        if (function.contentEquals("OBVB--")) {
            return "VDR LEVEL 2-3";
        }
        if (function.contentEquals("OBVC--")) {
            return "VDR LEVEL 3-4";
        }
        if (function.contentEquals("OBVD--")) {
            return "VDR LEVEL 4-5";
        }
        if (function.contentEquals("OBVE--")) {
            return "VDR LEVEL 5-6";
        }
        if (function.contentEquals("OBVF--")) {
            return "VDR LEVEL 6-7";
        }
        if (function.contentEquals("OBVG--")) {
            return "VDR LEVEL 7-8";
        }
        if (function.contentEquals("OBVH--")) {
            return "VDR LEVEL 8-9";
        }
        if (function.contentEquals("OBVI--")) {
            return "VDR LEVEL 9-10";
        }
        if (function.contentEquals("BS----")) {
            return "BEACH SLOPE";
        }
        if (function.contentEquals("BSF---")) {
            return "FLAT";
        }
        if (function.contentEquals("BSG---")) {
            return "GENTLE";
        }
        if (function.contentEquals("BSM---")) {
            return "MODERATE";
        }
        if (function.contentEquals("BST---")) {
            return "STEEP";
        }
        if (function.contentEquals("G-----")) {
            return "GEOPHYSICS/ACOUSTICS";
        }
        if (function.contentEquals("GM----")) {
            return "MINE WARFARE BOTTOM DESCRIPTORS";
        }
        if (function.contentEquals("GMS---")) {
            return "MIW-BOTTOM SEDIMENTS";
        }
        if (function.contentEquals("GMSR--")) {
            return "SOLID ROCK";
        }
        if (function.contentEquals("GMSC--")) {
            return "CLAY ";
        }
        if (function.contentEquals("GMSSVS")) {
            return "VERY COARSE SAND";
        }
        if (function.contentEquals("GMSSC-")) {
            return "COARSE SAND";
        }
        if (function.contentEquals("GMSSM-")) {
            return "MEDIUM SAND";
        }
        if (function.contentEquals("GMSSF-")) {
            return "FINE SAND";
        }
        if (function.contentEquals("GMSSVF")) {
            return "VERY FINE SAND";
        }
        if (function.contentEquals("GMSIVF")) {
            return "VERY FINE SILT";
        }
        if (function.contentEquals("GMSIF-")) {
            return "FINE SILT";
        }
        if (function.contentEquals("GMSIM-")) {
            return "MEDIUM SILT";
        }
        if (function.contentEquals("GMSIC-")) {
            return "COARSE SILT";
        }
        if (function.contentEquals("GMSB--")) {
            return "BOULDERS";
        }
        if (function.contentEquals("GMS-CO")) {
            return "COBBLES  OYSTER SHELLS   ";
        }
        if (function.contentEquals("GMS-PH")) {
            return "PEBBLES  SHELLS   ";
        }
        if (function.contentEquals("GMS-SH")) {
            return "SAND AND SHELLS";
        }
        if (function.contentEquals("GML---")) {
            return "LAND";
        }
        if (function.contentEquals("GMN---")) {
            return "NO DATA";
        }
        if (function.contentEquals("GMR---")) {
            return "BOTTOM ROUGHNESS";
        }
        if (function.contentEquals("GMRS--")) {
            return "SMOOTH";
        }
        if (function.contentEquals("GMRM--")) {
            return "MODERATE";
        }
        if (function.contentEquals("GMRR--")) {
            return "ROUGH";
        }
        if (function.contentEquals("GMC---")) {
            return "CLUTTER (BOTTOM)";
        }
        if (function.contentEquals("GMCL--")) {
            return "LOW";
        }
        if (function.contentEquals("GMCM--")) {
            return "MEDIUM";
        }
        if (function.contentEquals("GMCH--")) {
            return "HIGH";
        }
        if (function.contentEquals("GMIB--")) {
            return "IMPACT BURIAL";
        }
        if (function.contentEquals("GMIBA-")) {
            return "0 ";
        }
        if (function.contentEquals("GMIBB-")) {
            return "0-10%";
        }
        if (function.contentEquals("GMIBC-")) {
            return "10-20%";
        }
        if (function.contentEquals("GMIBD-")) {
            return "20-75%";
        }
        if (function.contentEquals("GMIBE-")) {
            return ">75%";
        }
        if (function.contentEquals("GMBC--")) {
            return "MIW BOTTOM CATEGORY";
        }
        if (function.contentEquals("GMBCA-")) {
            return "A";
        }
        if (function.contentEquals("GMBCB-")) {
            return "B";
        }
        if (function.contentEquals("GMBCC-")) {
            return "C";
        }
        if (function.contentEquals("GMBT--")) {
            return "MIW BOTTOM TYPE";
        }
        if (function.contentEquals("GMBTA-")) {
            return "A1";
        }
        if (function.contentEquals("GMBTB-")) {
            return "A2";
        }
        if (function.contentEquals("GMBTC-")) {
            return "A3";
        }
        if (function.contentEquals("GMBTD-")) {
            return "B1";
        }
        if (function.contentEquals("GMBTE-")) {
            return "B2";
        }
        if (function.contentEquals("GMBTF-")) {
            return "B3";
        }
        if (function.contentEquals("GMBTG-")) {
            return "C1";
        }
        if (function.contentEquals("GMBTH-")) {
            return "C2";
        }
        if (function.contentEquals("GMBTI-")) {
            return "C3";
        }
        if (function.contentEquals("L-----")) {
            return "LIMITS";
        }
        if (function.contentEquals("L-ML--")) {
            return "MARITIME LIMIT BOUNDARY";
        }
        if (function.contentEquals("L-MA--")) {
            return "MARITIME AREA";
        }
        if (function.contentEquals("L-RA--")) {
            return "RESTRICTED AREA";
        }
        if (function.contentEquals("L-SA--")) {
            return "SWEPT AREA";
        }
        if (function.contentEquals("L-TA--")) {
            return "TRAINING AREA";
        }
        if (function.contentEquals("L-O---")) {
            return "OPERATOR-DEFINED ";
        }
        if (function.contentEquals("M-----")) {
            return "MAN-MADE STRUCTURES";
        }
        if (function.contentEquals("MCA---")) {
            return "SUBMARINE CABLE";
        }
        if (function.contentEquals("MCC---")) {
            return "SUBMERGED CRIB";
        }
        if (function.contentEquals("MCD---")) {
            return "CANAL";
        }
        if (function.contentEquals("MF----")) {
            return "FORD";
        }
        if (function.contentEquals("ML----")) {
            return "LOCK";
        }
        if (function.contentEquals("MOA---")) {
            return "OIL/GAS RIG";
        }
        if (function.contentEquals("MOA---")) {
            return "OIL/GAS RIG FIELD";
        }
        if (function.contentEquals("MPA---")) {
            return "PIPELINES/PIPE";
        }
        if (function.contentEquals("MPA---")) {
            return "PILE/PILING/POST";
        }
        if (function.contentEquals("S-----")) {
            return "SIGNAL INTERCEPT ";
        }
        if (function.contentEquals("SC----")) {
            return "COMMUNICATIONS ";
        }
        if (function.contentEquals("SCD---")) {
            return "SATELLITE DOWNLINK ";
        }
        if (function.contentEquals("SR----")) {
            return "RADAR ";
        }
        if (function.contentEquals("SRD---")) {
            return "DATA TRANSMISSION ";
        }
        if (function.contentEquals("SRE---")) {
            return "EARTH SURVEILLANCE ";
        }
        if (function.contentEquals("SRI---")) {
            return "IFF (TRANSPONDER) ";
        }
        if (function.contentEquals("SRM---")) {
            return "MULTIFUNCTION ";
        }
        if (function.contentEquals("SRT---")) {
            return "TARGET ACQUISITION ";
        }
        if (function.contentEquals("SRS---")) {
            return "SPACE ";
        }
        if (function.contentEquals("SRU---")) {
            return "UNKNOWN ";
        }
        if (function.contentEquals("S-----")) {
            return "SIGNAL INTERCEPT ";
        }
        if (function.contentEquals("SC----")) {
            return "COMMUNICATIONS ";
        }
        if (function.contentEquals("SCC---")) {
            return "CELLULAR/MOBILE ";
        }
        if (function.contentEquals("SCO---")) {
            return "OMNI-LINE OF SIGHT (LOS)  ";
        }
        if (function.contentEquals("SCP---")) {
            return "POINT-TO-POINT LINE OF SIGHT (LOS) ";
        }
        if (function.contentEquals("SCS---")) {
            return "SATELLITE UPLINK ";
        }
        if (function.contentEquals("SR----")) {
            return "RADAR ";
        }
        if (function.contentEquals("SRAI--")) {
            return "AIRBORNE INTERCEPT ";
        }
        if (function.contentEquals("SRAS --")) {
            return "AIRBORNE SEARCH & BOMBING ";
        }
        if (function.contentEquals("SRC---")) {
            return "CONTROLLED INTERCEPT ";
        }
        if (function.contentEquals("SRD---")) {
            return "DATA TRANSMISSION ";
        }
        if (function.contentEquals("SRE---")) {
            return "EARLY WARNING ";
        }
        if (function.contentEquals("SRF---")) {
            return "FIRE CONTROL ";
        }
        if (function.contentEquals("SRI---")) {
            return "IFF (TRANSPONDER) ";
        }
        if (function.contentEquals("SRMA--")) {
            return "MISSILE ACQUISITION ";
        }
        if (function.contentEquals("SRMD--")) {
            return "MISSILE DOWNLINK ";
        }
        if (function.contentEquals("SRMG--")) {
            return "MISSILE GUIDANCE ";
        }
        if (function.contentEquals("SRMT--")) {
            return "MISSILE TRACKING ";
        }
        if (function.contentEquals("SRMF --")) {
            return "MULTIFUNCTION ";
        }
        if (function.contentEquals("SRTI--")) {
            return "TARGET ILLUMINATOR ";
        }
        if (function.contentEquals("SRTA --")) {
            return "TARGET ACQUISITION ";
        }
        if (function.contentEquals("SRTT--")) {
            return "TARGET TRACKING ";
        }
        if (function.contentEquals("SRU---")) {
            return "UNKNOWN ";
        }
        if (function.contentEquals("S-----")) {
            return "SIGNAL INTERCEPT ";
        }
        if (function.contentEquals("SC----")) {
            return "COMMUNICATIONS ";
        }
        if (function.contentEquals("SCC---")) {
            return "CELLULAR/MOBILE ";
        }
        if (function.contentEquals("SCO---")) {
            return "OMNI-LINE OF SIGHT (LOS) ";
        }
        if (function.contentEquals("SCP---")) {
            return "POINT-TO-POINT LINE OF SIGHT (LOS) ";
        }
        if (function.contentEquals("SCS---")) {
            return "SATELLITE UPLINK ";
        }
        if (function.contentEquals("SCT---")) {
            return "TROPOSPHERIC SCATTER ";
        }
        if (function.contentEquals("SR----")) {
            return "RADAR ";
        }
        if (function.contentEquals("SRAT--")) {
            return "AIR TRAFFIC CONTROL  ";
        }
        if (function.contentEquals("SRAA--")) {
            return "ANTIAIRCRAFT ";
        }
        if (function.contentEquals("SRB---")) {
            return "BATTLEFIELD SURVEILLANCE ";
        }
        if (function.contentEquals("SRCS--")) {
            return "COASTAL SURVEILLANCE ";
        }
        if (function.contentEquals("SRCA--")) {
            return "CONTROLLED APPROACH ";
        }
        if (function.contentEquals("SRD---")) {
            return "DATA TRANSMISSION ";
        }
        if (function.contentEquals("SRE---")) {
            return "EARLY WARNING ";
        }
        if (function.contentEquals("SRF---")) {
            return "FIRE CONTROL ";
        }
        if (function.contentEquals("SRH---")) {
            return "HEIGHT FINDING ";
        }
        if (function.contentEquals("SRI---")) {
            return "IDENTIFICATION FRIEND/FOE (INTERROGATOR) ";
        }
        if (function.contentEquals("SRMM--")) {
            return "METEOROLOGICAL (MILITARY) ";
        }
        if (function.contentEquals("SRMA--")) {
            return "MISSILE ACQUISITION ";
        }
        if (function.contentEquals("SRMG--")) {
            return "MISSILE GUIDANCE ";
        }
        if (function.contentEquals("SRMT--")) {
            return "MISSILE TRACKING ";
        }
        if (function.contentEquals("SRMF--")) {
            return "MULTIFUNCTION ";
        }
        if (function.contentEquals("SRS---")) {
            return "SHELL TRACKING ";
        }
        if (function.contentEquals("SRTA--")) {
            return "TARGET ACQUISITION ";
        }
        if (function.contentEquals("SRTI--")) {
            return "TARGET ILLUMINATOR ";
        }
        if (function.contentEquals("SRTT--")) {
            return "TARGET TRACKING ";
        }
        if (function.contentEquals("SRU---")) {
            return "UNKNOWN ";
        }
        if (function.contentEquals("S-----")) {
            return "SIGNAL INTERCEPT ";
        }
        if (function.contentEquals("SC----")) {
            return "COMMUNICATIONS ";
        }
        if (function.contentEquals("SCC---")) {
            return "CELLULAR/MOBILE ";
        }
        if (function.contentEquals("SCO---")) {
            return "OMNI-LINE OF SIGHT (LOS) ";
        }
        if (function.contentEquals("SCP---")) {
            return "POINT-TO-POINT LINE OF SIGHT (LOS)  ";
        }
        if (function.contentEquals("SCS---")) {
            return "SATELLITE UPLINK ";
        }
        if (function.contentEquals("SR----")) {
            return "RADAR ";
        }
        if (function.contentEquals("SRAT--")) {
            return "AIR TRAFFIC CONTROL ";
        }
        if (function.contentEquals("SRAA--")) {
            return "ANTIAIRCRAFT ";
        }
        if (function.contentEquals("SRCA--")) {
            return "CONTROLLED APPROACH ";
        }
        if (function.contentEquals("SRCI--")) {
            return "CONTROLLED INTERCEPT ";
        }
        if (function.contentEquals("SRD---")) {
            return "DATA TRANSMISSION ";
        }
        if (function.contentEquals("SRE---")) {
            return "EARLY WARNING ";
        }
        if (function.contentEquals("SRF---")) {
            return "FIRE CONTROL ";
        }
        if (function.contentEquals("SRH---")) {
            return "HEIGHT FINDING ";
        }
        if (function.contentEquals("SRI---")) {
            return "IDENTIFICATION FRIEND/FOE (INTERROGATOR) ";
        }
        if (function.contentEquals("SRMM--")) {
            return "METEOROLOGICAL (MILITARY) ";
        }
        if (function.contentEquals("SRMA--")) {
            return "MISSILE ACQUISITION ";
        }
        if (function.contentEquals("SRMG--")) {
            return "MISSILE GUIDANCE ";
        }
        if (function.contentEquals("SRMT--")) {
            return "MISSILE TRACKING ";
        }
        if (function.contentEquals("SRMF--")) {
            return "MULTIFUNCTION ";
        }
        if (function.contentEquals("SRS---")) {
            return "SURFACE SEARCH ";
        }
        if (function.contentEquals("SRTA--")) {
            return "TARGET ACQUISITION ";
        }
        if (function.contentEquals("SRTI--")) {
            return "TARGET ILLUMINATOR ";
        }
        if (function.contentEquals("SRTT--")) {
            return "TARGET TRACKING ";
        }
        if (function.contentEquals("SRU---")) {
            return "UNKNOWN ";
        }
        if (function.contentEquals("S-----")) {
            return "SIGNAL INTERCEPT ";
        }
        if (function.contentEquals("SC----")) {
            return "COMMUNICATIONS ";
        }
        if (function.contentEquals("SCO---")) {
            return "OMNI-LINE OF SIGHT (LOS)  ";
        }
        if (function.contentEquals("SCP---")) {
            return "POINT-TO-POINT LINE OF SIGHT (LOS) ";
        }
        if (function.contentEquals("SCS---")) {
            return "SATELLITE UPLINK ";
        }
        if (function.contentEquals("SR----")) {
            return "RADAR ";
        }
        if (function.contentEquals("SRD---")) {
            return "DATA TRANSMISSION ";
        }
        if (function.contentEquals("SRE---")) {
            return "EARLY WARNING ";
        }
        if (function.contentEquals("SRM---")) {
            return "MULTIFUNCTION ";
        }
        if (function.contentEquals("SRS---")) {
            return "SURFACE SEARCH ";
        }
        if (function.contentEquals("SRT---")) {
            return "TARGET ACQUISITION ";
        }
        if (function.contentEquals("SRU---")) {
            return "UNKNOWN  ";
        }
        if (function.contentEquals("A-----")) {
            return "CIVIL DISTURBANCE INCIDENT ";
        }
        if (function.contentEquals("D-----")) {
            return "CIVIL DEMONSTRATION ";
        }
        if (function.contentEquals("AC----")) {
            return "CIVIL DISPLACED POPULATION ";
        }
        if (function.contentEquals("AC----")) {
            return "CIVIL RIOTING ";
        }
        if (function.contentEquals("B-----")) {
            return "CRIMINAL ACTIVITY INCIDENT ";
        }
        if (function.contentEquals("BA----")) {
            return "BOMB THREAT ";
        }
        if (function.contentEquals("B-----")) {
            return "BOMB ";
        }
        if (function.contentEquals("BC----")) {
            return "EXPLOSION ";
        }
        if (function.contentEquals("BD----")) {
            return "LOOTING ";
        }
        if (function.contentEquals("P-----")) {
            return "POISONING ";
        }
        if (function.contentEquals("BF----")) {
            return "SHOOTING ";
        }
        if (function.contentEquals("C-----")) {
            return "FIRE INCIDENT ";
        }
        if (function.contentEquals("CA----")) {
            return "HOT SPOT ";
        }
        if (function.contentEquals("CB----")) {
            return "NON-RESIDENTIAL FIRE ";
        }
        if (function.contentEquals("CC----")) {
            return "ORIGIN (OF FIRE) ";
        }
        if (function.contentEquals("CD----")) {
            return "RESIDENTIAL FIRE ";
        }
        if (function.contentEquals("CE----")) {
            return "SCHOOL FIRE ";
        }
        if (function.contentEquals("CF----")) {
            return "SMOKE ";
        }
        if (function.contentEquals("CG----")) {
            return "SPECIAL NEEDS FIRE ";
        }
        if (function.contentEquals("CH----")) {
            return "WILD FIRE ";
        }
        if (function.contentEquals("D-----")) {
            return "HAZARDOUS MATERIAL INCIDENT ";
        }
        if (function.contentEquals("DA----")) {
            return "CHEMICAL AGENT ";
        }
        if (function.contentEquals("DB----")) {
            return "CORROSIVE MATERIAL ";
        }
        if (function.contentEquals("DC----")) {
            return "HAZARDOUS WHEN WET ";
        }
        if (function.contentEquals("DD----")) {
            return "EXPLOSIVE ";
        }
        if (function.contentEquals("DE----")) {
            return "FLAMMABLE GAS ";
        }
        if (function.contentEquals("DF----")) {
            return "FLAMMABLE LIQUID ";
        }
        if (function.contentEquals("DG----")) {
            return "FLAMMABLE SOLID ";
        }
        if (function.contentEquals("DH----")) {
            return "NON-FLAMMABLE GAS ";
        }
        if (function.contentEquals("DI----")) {
            return "ORGANIC PEROXIDE ";
        }
        if (function.contentEquals("DJ----")) {
            return "OXIDIZER ";
        }
        if (function.contentEquals("DK----")) {
            return "RADIOACTIVE MATERIAL ";
        }
        if (function.contentEquals("DL----")) {
            return "SPONTANEOUSLY COMBUSTIBLE ";
        }
        if (function.contentEquals("DM----")) {
            return "TOXIC GAS ";
        }
        if (function.contentEquals("DN----")) {
            return "TOXIC AND INFECTIOUS ";
        }
        if (function.contentEquals("DO----")) {
            return "UNEXPLODED ORDNANCE ";
        }
        if (function.contentEquals("E-----")) {
            return "AIR INCIDENT ";
        }
        if (function.contentEquals("EA----")) {
            return "AIR ACCIDENT ";
        }
        if (function.contentEquals("HA----")) {
            return "AIR HIJACKING ";
        }
        if (function.contentEquals("F-----")) {
            return "MARINE INCIDENT ";
        }
        if (function.contentEquals("FA----")) {
            return "MARINE ACCIDENT ";
        }
        if (function.contentEquals("HV----")) {
            return "MARINE HIJACKING ";
        }
        if (function.contentEquals("G-----")) {
            return "RAIL INCIDENT ";
        }
        if (function.contentEquals("GA----")) {
            return "RAIL ACCIDENT ";
        }
        if (function.contentEquals("GB----")) {
            return "RAIL HIJACKING ";
        }
        if (function.contentEquals("H-----")) {
            return "VEHICLE INCIDENT ";
        }
        if (function.contentEquals("HA----")) {
            return "VEHICLE ACCIDENT ";
        }
        if (function.contentEquals("HT----")) {
            return "VEHICLE HIJACKING ";
        }
        if (function.contentEquals("A-----")) {
            return "GEOLOGIC ";
        }
        if (function.contentEquals("AA----")) {
            return "AFTERSHOCK ";
        }
        if (function.contentEquals("AB----")) {
            return "AVALANCHE ";
        }
        if (function.contentEquals("AC----")) {
            return "EARTHQUAKE EPICENTER ";
        }
        if (function.contentEquals("AD----")) {
            return "LANDSLIDE ";
        }
        if (function.contentEquals("AE----")) {
            return "SUBSIDENCE ";
        }
        if (function.contentEquals("WSVE--")) {
            return "VOLCANIC ERUPTION ";
        }
        if (function.contentEquals("AG----")) {
            return "VOLCANIC THREAT ";
        }
        if (function.contentEquals("B-----")) {
            return "HYDRO-METEOROLOGICAL ";
        }
        if (function.contentEquals("WSD-LI")) {
            return "DRIZZLE ";
        }
        if (function.contentEquals("BB----")) {
            return "DROUGHT ";
        }
        if (function.contentEquals("BC----")) {
            return "FLOOD ";
        }
        if (function.contentEquals("WSFGSO")) {
            return "FOG ";
        }
        if (function.contentEquals("WSGRL-")) {
            return "HAIL ";
        }
        if (function.contentEquals("BF----")) {
            return "INVERSION ";
        }
        if (function.contentEquals("WSR-LI")) {
            return "RAIN ";
        }
        if (function.contentEquals("WSDSLM")) {
            return "SAND DUST STORM ";
        }
        if (function.contentEquals("WSS-LI")) {
            return "SNOW ";
        }
        if (function.contentEquals("WSTMH-")) {
            return "THUNDER STORM ";
        }
        if (function.contentEquals("WST-FC")) {
            return "TORNADO ";
        }
        if (function.contentEquals("WSTSS-")) {
            return "TROPICAL CYCLONE ";
        }
        if (function.contentEquals("BM----")) {
            return "TSUNAMI ";
        }
        if (function.contentEquals("C-----")) {
            return "INFESTATION ";
        }
        if (function.contentEquals("CA----")) {
            return "BIRD INFESTATION ";
        }
        if (function.contentEquals("CB----")) {
            return "INSECT INFESTATION ";
        }
        if (function.contentEquals("CC----")) {
            return "MICROBIAL INFESTATION ";
        }
        if (function.contentEquals("CD----")) {
            return "REPTILE INFESTATION ";
        }
        if (function.contentEquals("CE----")) {
            return "RODENT INFESTATION ";
        }
        if (function.contentEquals("A-----")) {
            return "EMERGENCY MEDICAL OPERATION ";
        }
        if (function.contentEquals("AA----")) {
            return "EMERGENCY MEDICAL OPERATION UNIT ";
        }
        if (function.contentEquals("AB----")) {
            return "EMERGENCY MEDICAL OPERATION EQUIPMENT ";
        }
        if (function.contentEquals("AC----")) {
            return "EMERGENCY MEDICAL OPERATION INSTALLATION ";
        }
        if (function.contentEquals("AD----")) {
            return "EMT STATION LOCATION ";
        }
        if (function.contentEquals("AE----")) {
            return "AMBULANCE ";
        }
        if (function.contentEquals("AF----")) {
            return "MEDICAL EVACUATION HELICOPTER ";
        }
        if (function.contentEquals("AG----")) {
            return "HEALTH DEPARTMENT FACILITY ";
        }
        if (function.contentEquals("IXH---")) {
            return "HOSPITAL ";
        }
        if (function.contentEquals("NM----")) {
            return "HOSPITAL SHIP ";
        }
        if (function.contentEquals("AJ----")) {
            return "MEDICAL FACILITIES OUT PATIENT ";
        }
        if (function.contentEquals("AK----")) {
            return "MORGUE ";
        }
        if (function.contentEquals("AL----")) {
            return "PHARMACY ";
        }
        if (function.contentEquals("AM----")) {
            return "TRIAGE ";
        }
        if (function.contentEquals("B-----")) {
            return "EMERGENCY OPERATION ";
        }
        if (function.contentEquals("BA----")) {
            return "EMERGENCY OPERATION UNIT ";
        }
        if (function.contentEquals("BB----")) {
            return "EMERGENCY OPERATION EQUIPMENT ";
        }
        if (function.contentEquals("BC----")) {
            return "EMERGENCY OPERATION INSTALLATION ";
        }
        if (function.contentEquals("BD----")) {
            return "EMERGENCY COLLECTION EVACUATION POINT ";
        }
        if (function.contentEquals("BE----")) {
            return "EMERGENCY INCIDENT COMMAND CENTER ";
        }
        if (function.contentEquals("BF----")) {
            return "EMERGENCY OPERATIONS CENTER ";
        }
        if (function.contentEquals("BG----")) {
            return "EMERGENCY PUBLIC INFORMATION CENTER ";
        }
        if (function.contentEquals("BH----")) {
            return "EMERGENCY SHELTER ";
        }
        if (function.contentEquals("BI----")) {
            return "EMERGENCY STAGING AREA ";
        }
        if (function.contentEquals("BJ----")) {
            return "EMERGENCY TEAM ";
        }
        if (function.contentEquals("BK----")) {
            return "EMERGENCY WATER DISTRIBUTION CENTER ";
        }
        if (function.contentEquals("BL----")) {
            return "EMERGENCY FOOD DISTRIBUTION CENTER ";
        }
        if (function.contentEquals("C-----")) {
            return "FIRE FIGHTING OPERATION ";
        }
        if (function.contentEquals("CA----")) {
            return "FIRE FIGHTING OPERATION UNIT ";
        }
        if (function.contentEquals("CB----")) {
            return "FIRE FIGHTING OPERATION EQUIPMENT ";
        }
        if (function.contentEquals("CC----")) {
            return "FIRE HYDRANT ";
        }
        if (function.contentEquals("CD----")) {
            return "OTHER WATER SUPPLY LOCATION ";
        }
        if (function.contentEquals("CE----")) {
            return "FIRE STATION ";
        }
        if (function.contentEquals("D-----")) {
            return "LAW ENFORCEMENT OPERATION ";
        }
        if (function.contentEquals("DA----")) {
            return "LAW ENFORCEMENT OPERATION UNIT ";
        }
        if (function.contentEquals("DB----")) {
            return "LAW ENFORCEMENT OPERATION EQUIPMENT ";
        }
        if (function.contentEquals("DC----")) {
            return "LAW ENFORCEMENT OPERATION INSTALLATION ";
        }
        if (function.contentEquals("DD----")) {
            return "ATF ";
        }
        if (function.contentEquals("DDA---")) {
            return "ATF UNIT ";
        }
        if (function.contentEquals("DDB---")) {
            return "ATF EQUIPMENT ";
        }
        if (function.contentEquals("DDC---")) {
            return "ATF INSTALLATION ";
        }
        if (function.contentEquals("DE----")) {
            return "BORDER PATROL ";
        }
        if (function.contentEquals("DEA---")) {
            return "BORDER PATROL UNIT ";
        }
        if (function.contentEquals("DEB---")) {
            return "BORDER PATROL EQUIPMENT ";
        }
        if (function.contentEquals("DEC---")) {
            return "BORDER PATROL INSTALLATION ";
        }
        if (function.contentEquals("DF----")) {
            return "CUSTOMS SERVICE ";
        }
        if (function.contentEquals("DFA---")) {
            return "CUSTOMS SERVICE UNIT ";
        }
        if (function.contentEquals("DFB---")) {
            return "CUSTOMS SERVICE EQUIPMENT ";
        }
        if (function.contentEquals("DFC---")) {
            return "CUSTOMS SERVICE INSTALLATION ";
        }
        if (function.contentEquals("DG----")) {
            return "DEA ";
        }
        if (function.contentEquals("DGA---")) {
            return "DEA UNIT ";
        }
        if (function.contentEquals("DGB---")) {
            return "DEA EQUIPMENT ";
        }
        if (function.contentEquals("DGC---")) {
            return "DEA INSTALLATION ";
        }
        if (function.contentEquals("DH----")) {
            return "DOJ ";
        }
        if (function.contentEquals("DHA---")) {
            return "DOJ UNIT ";
        }
        if (function.contentEquals("DHB---")) {
            return "DOJ EQUIPMENT ";
        }
        if (function.contentEquals("DHC---")) {
            return "DOJ INSTALLATION ";
        }
        if (function.contentEquals("DI----")) {
            return "FBI ";
        }
        if (function.contentEquals("DIA---")) {
            return "FBI UNIT ";
        }
        if (function.contentEquals("DIB---")) {
            return "FBI EQUIPMENT ";
        }
        if (function.contentEquals("DIC---")) {
            return "FBI INSTALLATION ";
        }
        if (function.contentEquals("DJ----")) {
            return "POLICE ";
        }
        if (function.contentEquals("UULC--")) {
            return "POLICE UNIT ";
        }
        if (function.contentEquals("DJB---")) {
            return "POLICE EQUIPMENT ";
        }
        if (function.contentEquals("DJC---")) {
            return "POLICE INSTALLATION ";
        }
        if (function.contentEquals("DK----")) {
            return "PRISON ";
        }
        if (function.contentEquals("DL----")) {
            return "SECRET SERVICE ";
        }
        if (function.contentEquals("DLA---")) {
            return "SECRET SERVICE UNIT ";
        }
        if (function.contentEquals("DLB---")) {
            return "SECRET SERVICE EQUIPMENT ";
        }
        if (function.contentEquals("DLC---")) {
            return "SECRET SERVICE INSTALLATION ";
        }
        if (function.contentEquals("DMA---")) {
            return "TSA UNIT ";
        }
        if (function.contentEquals("DMB---")) {
            return "TSA EQUIPMENT ";
        }
        if (function.contentEquals("DMC---")) {
            return "TSA INSTALLATION ";
        }
        if (function.contentEquals("DN----")) {
            return "COAST GUARD ";
        }
        if (function.contentEquals("DNA---")) {
            return "COAST GUARD UNIT ";
        }
        if (function.contentEquals("XL----")) {
            return "COAST GUARD EQUIPMENT ";
        }
        if (function.contentEquals("DNC---")) {
            return "COAST GUARD INSTALLATION ";
        }
        if (function.contentEquals("DO----")) {
            return "US MARSHALS SERVICE ";
        }
        if (function.contentEquals("DOA---")) {
            return "US MARSHALS SERVICE UNIT ";
        }
        if (function.contentEquals("DOB---")) {
            return "US MARSHALS SERVICE EQUIPMENT ";
        }
        if (function.contentEquals("DOC---")) {
            return "US MARSHALS SERVICE INSTALLATION ";
        }
        if (function.contentEquals("ES----")) {
            return "SENSOR ";
        }
        if (function.contentEquals("EA----")) {
            return "BIOLOGICAL SENSOR ";
        }
        if (function.contentEquals("EB----")) {
            return "CHEMICAL SENSOR ";
        }
        if (function.contentEquals("EC----")) {
            return "INTRUSION SENSOR ";
        }
        if (function.contentEquals("ED----")) {
            return "NUCLEAR SENSOR ";
        }
        if (function.contentEquals("EE----")) {
            return "RADIOLOGICAL SENSOR ";
        }
        if (function.contentEquals("A-----")) {
            return "AGRICULTURE AND FOOD INFRASTRUCTURE ";
        }
        if (function.contentEquals("AA----")) {
            return "AGRICULTURAL LABORATORY ";
        }
        if (function.contentEquals("AB----")) {
            return "ANIMAL FEEDLOT ";
        }
        if (function.contentEquals("AC----")) {
            return "COMMERCIAL FOOD DISTRIBUTION CENTER ";
        }
        if (function.contentEquals("AD----")) {
            return "FARM/RANCH ";
        }
        if (function.contentEquals("AE----")) {
            return "FOOD PRODUCTION CENTER ";
        }
        if (function.contentEquals("AF----")) {
            return "FOOD RETAIL ";
        }
        if (function.contentEquals("AG----")) {
            return "GRAIN STORAGE ";
        }
        if (function.contentEquals("B-----")) {
            return "BANKING FINANCE AND INSURANCE INFRASTRUCTURE ";
        }
        if (function.contentEquals("BA----")) {
            return "ATM ";
        }
        if (function.contentEquals("BB----")) {
            return "BANK ";
        }
        if (function.contentEquals("BC----")) {
            return "BULLION STORAGE ";
        }
        if (function.contentEquals("BD----")) {
            return "FEDERAL RESERVE BANK ";
        }
        if (function.contentEquals("BE----")) {
            return "FINANCIAL EXCHANGE ";
        }
        if (function.contentEquals("BF----")) {
            return "FINANCIAL SERVICES OTHER ";
        }
        if (function.contentEquals("C-----")) {
            return "COMMERCIAL INFRASTRUCTURE ";
        }
        if (function.contentEquals("CA----")) {
            return "CHEMICAL PLANT ";
        }
        if (function.contentEquals("CB----")) {
            return "FIREARMS MANUFACTURER ";
        }
        if (function.contentEquals("CC----")) {
            return "FIREARMS RETAILER ";
        }
        if (function.contentEquals("CD----")) {
            return "HAZARDOUS MATERIAL PRODUCTION ";
        }
        if (function.contentEquals("CE----")) {
            return "HAZARDOUS MATERIAL STORAGE ";
        }
        if (function.contentEquals("CF----")) {
            return "INDUSTRIAL SITE ";
        }
        if (function.contentEquals("CG----")) {
            return "LANDFILL ";
        }
        if (function.contentEquals("CH----")) {
            return "PHARMACEUTICAL MANUFACTURER ";
        }
        if (function.contentEquals("CI----")) {
            return "CONTAMINATED HAZARDOUS WASTE SITE ";
        }
        if (function.contentEquals("CJ----")) {
            return "TOXIC RELEASE INVENTORY ";
        }
        if (function.contentEquals("D-----")) {
            return "EDUCATIONAL FACILITIES INFRASTRUCTURE ";
        }
        if (function.contentEquals("DA----")) {
            return "COLLEGE UNIVERSITY ";
        }
        if (function.contentEquals("DB----")) {
            return "SCHOOL ";
        }
        if (function.contentEquals("IUE---")) {
            return "ENERGY FACILITIES INFRASTRUCTURE ";
        }
        if (function.contentEquals("EA----")) {
            return "GENERATION STATION ";
        }
        if (function.contentEquals("EB----")) {
            return "NATURAL GAS FACILITY ";
        }
        if (function.contentEquals("IUEN--")) {
            return "NUCLEAR FACILITY ";
        }
        if (function.contentEquals("IRP---")) {
            return "PETROLEUM FACILITY ";
        }
        if (function.contentEquals("EE----")) {
            return "PROPANE FACILITY ";
        }
        if (function.contentEquals("F-----")) {
            return "GOVERNMENT SITE INFRASTRUCTURE ";
        }
        if (function.contentEquals("G-----")) {
            return "MILITARY INFRASTRUCTURE ";
        }
        if (function.contentEquals("GA----")) {
            return "MILITARY ARMORY ";
        }
        if (function.contentEquals("IB----")) {
            return "MILITARY BASE ";
        }
        if (function.contentEquals("H-----")) {
            return "POSTAL SERVICE INFRASTRUCTURE ";
        }
        if (function.contentEquals("HA----")) {
            return "POSTAL DISTRIBUTION CENTER ";
        }
        if (function.contentEquals("HB----")) {
            return "POST OFFICE ";
        }
        if (function.contentEquals("I-----")) {
            return "PUBLIC VENUES INFRASTRUCTURE ";
        }
        if (function.contentEquals("IA----")) {
            return "ENCLOSED FACILITY ";
        }
        if (function.contentEquals("IB----")) {
            return "OPEN FACILITY ";
        }
        if (function.contentEquals("IC----")) {
            return "RECREATIONAL AREA ";
        }
        if (function.contentEquals("ID----")) {
            return "RELIGIOUS INSTITUTION ";
        }
        if (function.contentEquals("J-----")) {
            return "SPECIAL NEEDS INFRASTRUCTURE ";
        }
        if (function.contentEquals("JA----")) {
            return "ADULT DAY CARE ";
        }
        if (function.contentEquals("JB----")) {
            return "CHILD DAY CARE ";
        }
        if (function.contentEquals("JC----")) {
            return "ELDER CARE ";
        }
        if (function.contentEquals("K-----")) {
            return "TELECOMMUNICATIONS INFRASTRUCTURE ";
        }
        if (function.contentEquals("IUT---")) {
            return "TELECOMMUNICATIONS FACILITY ";
        }
        if (function.contentEquals("KB----")) {
            return "TELECOMMUNICATIONS TOWER ";
        }
        if (function.contentEquals("IT----")) {
            return "TRANSPORTATION INFRASTRUCTURE ";
        }
        if (function.contentEquals("LA----")) {
            return "AIR TRAFFIC CONTROL FACILITY ";
        }
        if (function.contentEquals("IBA---")) {
            return "AIRPORT ";
        }
        if (function.contentEquals("BCB---")) {
            return "BRIDGE ";
        }
        if (function.contentEquals("LD----")) {
            return "BUS STATION ";
        }
        if (function.contentEquals("LE----")) {
            return "FERRY TERMINAL ";
        }
        if (function.contentEquals("LF----")) {
            return "HELICOPTER LANDING SITE ";
        }
        if (function.contentEquals("ML----")) {
            return "LOCK ";
        }
        if (function.contentEquals("LH----")) {
            return "MAINTENANCE FACILITY ";
        }
        if (function.contentEquals("IBN---")) {
            return "PORT ";
        }
        if (function.contentEquals("LJ----")) {
            return "RAIL STATION ";
        }
        if (function.contentEquals("LK----")) {
            return "REST STOP ";
        }
        if (function.contentEquals("HPBA--")) {
            return "SHIP ANCHORAGE ";
        }
        if (function.contentEquals("LM----")) {
            return "TOLL FACILITY ";
        }
        if (function.contentEquals("PO----")) {
            return "TRAFFIC CONTROL POINT ";
        }
        if (function.contentEquals("LO----")) {
            return "TRAFFIC INSPECTION FACILITY ";
        }
        if (function.contentEquals("LP----")) {
            return "TUNNEL ";
        }
        if (function.contentEquals("IUP---")) {
            return "WATER SUPPLY INFRASTRUCTURE ";
        }
        if (function.contentEquals("MA----")) {
            return "CONTROL VALVE ";
        }
        if (function.contentEquals("MB----")) {
            return "DAM ";
        }
        if (function.contentEquals("MC----")) {
            return "DISCHARGE OUTFALL ";
        }
        if (function.contentEquals("MD----")) {
            return "GROUND WATER WELL ";
        }
        if (function.contentEquals("ME----")) {
            return "PUMPING STATION ";
        }
        if (function.contentEquals("MF----")) {
            return "RESERVOIR ";
        }
        if (function.contentEquals("MG----")) {
            return "STORAGE TOWER ";
        }
        if (function.contentEquals("MH----")) {
            return "SURFACE WATER INTAKE ";
        }
        if (function.contentEquals("MI----")) {
            return "WASTEWATER TREATMENT FACILITY ";
        }
        return "UNKNOWN";
    }

    public static String getEchelonString(String paramString) {
        if (paramString.contentEquals("--")) {
            return "UNKNOWN";
        }
        if (paramString.contentEquals("-A")) {
            return "TEAM/CREW";
        }
        if (paramString.contentEquals("-B")) {
            return " SQUAD ";
        }
        if (paramString.contentEquals("-C")) {
            return "SECTION";
        }
        if (paramString.contentEquals("-D")) {
            return " PLATOON/DETACHMENT ";
        }
        if (paramString.contentEquals("-E")) {
            return " COMPANY/BATTERY/TROOP";
        }
        if (paramString.contentEquals("-F")) {
            return " BATTALION/SQUADRON ";
        }
        if (paramString.contentEquals("-G")) {
            return " REGIMENT/GROUP";
        }
        if (paramString.contentEquals("-H")) {
            return " BRIGADE ";
        }
        if (paramString.contentEquals("-I")) {
            return " DIVISION";
        }
        if (paramString.contentEquals("-J")) {
            return " CORPS/MEF ";
        }
        if (paramString.contentEquals("-K")) {
            return " ARMY";
        }
        if (paramString.contentEquals("-L")) {
            return " ARMY GROUP/FRONT ";
        }
        if (paramString.contentEquals("-M")) {
            return " REGION";
        }
        if (paramString.contentEquals("-N")) {
            return " COMMAND";
        }
        if (paramString.contentEquals("A-")) {
            return "HEADQUARTERS (HQ) ";
        }
        if (paramString.contentEquals("AA")) {
            return "HQ TEAM/CREW";
        }
        if (paramString.contentEquals("AB")) {
            return " HQ SQUAD ";
        }
        if (paramString.contentEquals("AC")) {
            return "HQ SECTION";
        }
        if (paramString.contentEquals("AD")) {
            return " HQ PLATOON/DETACHMENT ";
        }
        if (paramString.contentEquals("AE")) {
            return " HQ COMPANY/BATTERY/TROOP";
        }
        if (paramString.contentEquals("AF")) {
            return " HQ BATTALION/SQUADRON ";
        }
        if (paramString.contentEquals("AG")) {
            return " HQ REGIMENT/GROUP";
        }
        if (paramString.contentEquals("AH")) {
            return " HQ BRIGADE ";
        }
        if (paramString.contentEquals("AI")) {
            return " HQ DIVISION";
        }
        if (paramString.contentEquals("AJ")) {
            return " HQ CORPS/MEF ";
        }
        if (paramString.contentEquals("AK")) {
            return " HQ ARMY";
        }
        if (paramString.contentEquals("AL")) {
            return " HQ ARMY GROUP/FRONT ";
        }
        if (paramString.contentEquals("AM")) {
            return " HQ REGION";
        }
        if (paramString.contentEquals("AN")) {
            return " HQ COMMAND";
        }
        if (paramString.contentEquals("B-")) {
            return "TASK FORCE (TF) HQ ";
        }
        if (paramString.contentEquals("BA")) {
            return "TF HQ TEAM/CREW";
        }
        if (paramString.contentEquals("BB")) {
            return " TF HQ SQUAD ";
        }
        if (paramString.contentEquals("BC")) {
            return "TF HQ SECTION";
        }
        if (paramString.contentEquals("BD")) {
            return " TF HQ PLATOON/DETACHMENT ";
        }
        if (paramString.contentEquals("BE")) {
            return " TF HQ COMPANY/BATTERY/TROOP";
        }
        if (paramString.contentEquals("BF")) {
            return " TF HQ BATTALION/SQUADRON ";
        }
        if (paramString.contentEquals("BG")) {
            return " TF HQ REGIMENT/GROUP";
        }
        if (paramString.contentEquals("BH")) {
            return " TF HQ BRIGADE ";
        }
        if (paramString.contentEquals("BI")) {
            return " TF HQ DIVISION";
        }
        if (paramString.contentEquals("BJ")) {
            return " TF HQ CORPS/MEF ";
        }
        if (paramString.contentEquals("BK")) {
            return " TF HQ ARMY";
        }
        if (paramString.contentEquals("BL")) {
            return " TF HQ ARMY GROUP/FRONT ";
        }
        if (paramString.contentEquals("BM")) {
            return " TF HQ REGION";
        }
        if (paramString.contentEquals("BN")) {
            return " TF HQ COMMAND";
        }
        if (paramString.contentEquals("C-")) {
            return "FEINT DUMMY (FD) HQ ";
        }
        if (paramString.contentEquals("CA")) {
            return "FD HQ TEAM/CREW";
        }
        if (paramString.contentEquals("CB")) {
            return " FD HQ SQUAD ";
        }
        if (paramString.contentEquals("CC")) {
            return "FD HQ SECTION";
        }
        if (paramString.contentEquals("CD")) {
            return " FD HQ PLATOON/DETACHMENT ";
        }
        if (paramString.contentEquals("CE")) {
            return " FD HQ COMPANY/BATTERY/TROOP";
        }
        if (paramString.contentEquals("CF")) {
            return " FD HQ BATTALION/SQUADRON ";
        }
        if (paramString.contentEquals("CG")) {
            return " FD HQ REGIMENT/GROUP";
        }
        if (paramString.contentEquals("CH")) {
            return " FD HQ BRIGADE ";
        }
        if (paramString.contentEquals("CI")) {
            return " FD HQ DIVISION";
        }
        if (paramString.contentEquals("CJ")) {
            return " FD HQ CORPS/MEF ";
        }
        if (paramString.contentEquals("CK")) {
            return " FD HQ ARMY";
        }
        if (paramString.contentEquals("CL")) {
            return " FD HQ ARMY GROUP/FRONT ";
        }
        if (paramString.contentEquals("CM")) {
            return " FD HQ REGION";
        }
        if (paramString.contentEquals("CN")) {
            return " FD HQ COMMAND";
        }
        if (paramString.contentEquals("D-")) {
            return "FEINT DUMMY/TASK FORCE (FD/TF) HQ ";
        }
        if (paramString.contentEquals("DA")) {
            return "FD/TF HQ TEAM/CREW";
        }
        if (paramString.contentEquals("DB")) {
            return " FD/TF HQ SQUAD ";
        }
        if (paramString.contentEquals("DC")) {
            return "FD/TF HQ SECTION";
        }
        if (paramString.contentEquals("DD")) {
            return " FD/TF HQ PLATOON/DETACHMENT ";
        }
        if (paramString.contentEquals("DE")) {
            return " FD/TF HQ COMPANY/BATTERY/TROOP";
        }
        if (paramString.contentEquals("DF")) {
            return " FD/TF HQ BATTALION/SQUADRON ";
        }
        if (paramString.contentEquals("DG")) {
            return " FD/TF HQ REGIMENT/GROUP";
        }
        if (paramString.contentEquals("DH")) {
            return " FD/TF HQ BRIGADE ";
        }
        if (paramString.contentEquals("DI")) {
            return " FD/TF HQ DIVISION";
        }
        if (paramString.contentEquals("DJ")) {
            return " FD/TF HQ CORPS/MEF ";
        }
        if (paramString.contentEquals("DK")) {
            return " FD/TF HQ ARMY";
        }
        if (paramString.contentEquals("DL")) {
            return " FD/TF HQ ARMY GROUP/FRONT ";
        }
        if (paramString.contentEquals("DM")) {
            return " FD/TF HQ REGION";
        }
        if (paramString.contentEquals("DN")) {
            return " FD/TF HQ COMMAND";
        }
        if (paramString.contentEquals("E-")) {
            return "TASK FORCE (TF) ";
        }
        if (paramString.contentEquals("EA")) {
            return "TF TEAM/CREW";
        }
        if (paramString.contentEquals("EB")) {
            return " TF SQUAD ";
        }
        if (paramString.contentEquals("EC")) {
            return "TF SECTION";
        }
        if (paramString.contentEquals("ED")) {
            return " TF PLATOON/DETACHMENT ";
        }
        if (paramString.contentEquals("EE")) {
            return " TF COMPANY/BATTERY/TROOP";
        }
        if (paramString.contentEquals("EF")) {
            return " TF BATTALION/SQUADRON ";
        }
        if (paramString.contentEquals("EG")) {
            return " TF REGIMENT/GROUP";
        }
        if (paramString.contentEquals("EH")) {
            return " TF BRIGADE ";
        }
        if (paramString.contentEquals("EI")) {
            return " TF DIVISION";
        }
        if (paramString.contentEquals("EJ")) {
            return "TF CORPS/MEF ";
        }
        if (paramString.contentEquals("EK")) {
            return " TF ARMY";
        }
        if (paramString.contentEquals("EL")) {
            return " TF ARMY GROUP/FRONT ";
        }
        if (paramString.contentEquals("EM")) {
            return " TF REGION";
        }
        if (paramString.contentEquals("EN")) {
            return " TF COMMAND";
        }
        if (paramString.contentEquals("F-")) {
            return "FEINT DUMMY (FD) ";
        }
        if (paramString.contentEquals("FA")) {
            return "FD TEAM/CREW";
        }
        if (paramString.contentEquals("FB")) {
            return " FD SQUAD ";
        }
        if (paramString.contentEquals("FC")) {
            return "FD SECTION";
        }
        if (paramString.contentEquals("FD")) {
            return " FD PLATOON/DETACHMENT ";
        }
        if (paramString.contentEquals("FE")) {
            return " FD COMPANY/BATTERY/TROOP";
        }
        if (paramString.contentEquals("FF")) {
            return " FD BATTALION/SQUADRON ";
        }
        if (paramString.contentEquals("FG")) {
            return " FD REGIMENT/GROUP";
        }
        if (paramString.contentEquals("FH")) {
            return " FD BRIGADE ";
        }
        if (paramString.contentEquals("FI")) {
            return " FD DIVISION";
        }
        if (paramString.contentEquals("FJ")) {
            return " FD CORPS/MEF ";
        }
        if (paramString.contentEquals("FK")) {
            return " FD ARMY";
        }
        if (paramString.contentEquals("FL")) {
            return " FD ARMY GROUP/FRONT ";
        }
        if (paramString.contentEquals("FM")) {
            return " FD REGION";
        }
        if (paramString.contentEquals("FN")) {
            return " FD COMMAND";
        }
        if (paramString.contentEquals("G-")) {
            return "FEINT DUMMY/TASK FORCE (FD/TF) ";
        }
        if (paramString.contentEquals("GA")) {
            return "FD/TF TEAM/CREW";
        }
        if (paramString.contentEquals("GA")) {
            return "FD/TF SQUAD ";
        }
        if (paramString.contentEquals("GC")) {
            return "FD/TF SECTION";
        }
        if (paramString.contentEquals("GD")) {
            return "FD/TF PLATOON/DETACHMENT ";
        }
        if (paramString.contentEquals("GE")) {
            return " FD/TF COMPANY/BATTERY/TROOP";
        }
        if (paramString.contentEquals("GF")) {
            return " FD/TF BATTALION/SQUADRON ";
        }
        if (paramString.contentEquals("GG")) {
            return " FD/TF REGIMENT/GROUP";
        }
        if (paramString.contentEquals("GH")) {
            return " FD/TF BRIGADE ";
        }
        if (paramString.contentEquals("GI")) {
            return " FD/TF DIVISION";
        }
        if (paramString.contentEquals("GJ")) {
            return " FD/TF CORPS/MEF ";
        }
        if (paramString.contentEquals("GK")) {
            return " FD/TF ARMY";
        }
        if (paramString.contentEquals("GL")) {
            return " FD/TF ARMY GROUP/FRONT ";
        }
        if (paramString.contentEquals("GM")) {
            return " FD/TF REGION";
        }
        if (paramString.contentEquals("GN")) {
            return " FD/TF COMMAND";
        }
        if (paramString.contentEquals("H-")) {
            return "INSTALLATION ";
        }
        if (paramString.contentEquals("HB")) {
            return " FEINT DUMMY INSTALLATION";
        }
        if (paramString.contentEquals("MO")) {
            return " MOBILITY WHEELED/LIMITED CROSS COUNTRY ";
        }
        if (paramString.contentEquals("MP")) {
            return " MOBILITY CROSS COUNTRY";
        }
        if (paramString.contentEquals("MQ")) {
            return " MOBILITY TRACKED ";
        }
        if (paramString.contentEquals("MR")) {
            return " MOBILITY WHEELED AND TRACKED COMBINATION";
        }
        if (paramString.contentEquals("MS")) {
            return " MOBILITY TOWED ";
        }
        if (paramString.contentEquals("MT")) {
            return " MOBILITY RAIL";
        }
        if (paramString.contentEquals("MU")) {
            return " MOBILITY OVER THE SNOW ";
        }
        if (paramString.contentEquals("MV")) {
            return " MOBILITY SLED";
        }
        if (paramString.contentEquals("MW")) {
            return " MOBILITY PACK ANIMALS ";
        }
        if (paramString.contentEquals("MX")) {
            return "MOBILITY BARGE";
        }
        if (paramString.contentEquals("MY")) {
            return " MOBILITY AMPHIBIOUS";
        }
        if (paramString.contentEquals("NS")) {
            return " TOWED ARRAY (SHORT) ";
        }
        return "UNKNOWN";
    }

    public static String getCountryString(String countrycode) {

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

    public static String getOrderOfBattle(String paramString) {
        if (paramString.contentEquals("A")) {
            return "AIR OB";
        }
        if (paramString.contentEquals("E")) {
            return "ELECTRONIC OB";
        }
        if (paramString.contentEquals("C")) {
            return "CIVILIAN OB";
        }
        if (paramString.contentEquals("G")) {
            return "GROUND OB";
        }
        if (paramString.contentEquals("N")) {
            return "MARITIME OB";
        }
        if (paramString.contentEquals("S")) {
            return "STRATEGIC FORCE RELATED";
        }
        return "UNKNOWN";
    }
    
}
