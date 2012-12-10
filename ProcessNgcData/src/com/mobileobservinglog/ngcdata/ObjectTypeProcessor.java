package com.mobileobservinglog.ngcdata;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ObjectTypeProcessor {
	static TreeMap<String, String> associations = new TreeMap<String, String>();
    static Set<String> knownTypes = new TreeSet<String>();
	
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
        associations.put("Single star", "Single Star");
        associations.put("Triple star", "Triple Star");
        associations.put("Cluster w/nebulosity", "Open Cluster with Nebulosity");
        associations.put("Open cluster w/nebulosity", "Open Cluster with Nebulosity");
        associations.put("Bright nebula", "Nebula");
        associations.put("Bright or Diffuse Nebula", "Nebula");
        associations.put("Unknown / Unverified", "Not Found");
        associations.put("Open cluster", "Open Cluster");
        associations.put("Open cluster + Nebula", "Open Cluster with Nebulosity");
        associations.put("Planetary nebula", "Planetary Nebula");
        associations.put("Multi-Galaxy System", "Multi-Galaxy System");
        associations.put("Globular cluster", "Globular Cluster");
        associations.put("Diff. neb. in galaxy", "Diffused Nebula in Galaxy");
        associations.put("Open cluster in SMC", "Open Cluster in SMC");
        associations.put("Bright nebula in SMC", "Bright Nebula in SMC");
        associations.put("Bright nebula in LMC", "Bright Nebula in LMC");
        associations.put("Clus. w/neb. in LMC", "Cluster with Nebulosity in LMC");
        associations.put("Glob. cluster in LMC", "Globular Cluster in LMC");
        associations.put("Cluster of galaxies", "Cluster of Galaxies");

        knownTypes.addAll(associations.values());
	}
	
	public static String getAssociation(String input) {
		return associations.get(input);
	}

    public static boolean isKnown(String input) {
        return knownTypes.contains(input);
    }
}
