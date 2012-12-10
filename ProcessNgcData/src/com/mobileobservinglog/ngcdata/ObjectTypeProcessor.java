package com.mobileobservinglog.ngcdata;

import java.util.ArrayList;
import java.util.TreeMap;

public class ObjectTypeProcessor {
	static TreeMap<String, String> associations = new TreeMap<String, String>();
    static ArrayList<String> knownTypes = new ArrayList<String>();
	
	static {
		associations.put("*", "Single Star");
		associations.put("**", "Double Star");
		associations.put("***", "Triple Star");
		associations.put("Ast", "Asterism");
		associations.put("Gxy", "Galaxy");
		associations.put("GxyCld", "Bright Cloud in a Galaxy");
		associations.put("GC", "Globular Cluster");
		associations.put("HIIRgn", "HII Region");
		associations.put("Neb", "Nebula");
		associations.put("NF", "Not Found");
		associations.put("OC", "Open Cluster");
		associations.put("PN", "Planetary Nebula");
		associations.put("SNR", "Supernova Remnant");
		associations.put("MWSC", "Milky Way Star Cloud");
		associations.put("OC+Neb", "Open Cluster with Nebulosity");
		associations.put("...", "N/A");
        associations.put("Double star", "Double Star");

        knownTypes.addAll(associations.values());
	}
	
	public static String getAssociation(String input) {
		return associations.get(input);
	}

    public static boolean isKnown(String input) {
        return knownTypes.contains(input);
    }
}
