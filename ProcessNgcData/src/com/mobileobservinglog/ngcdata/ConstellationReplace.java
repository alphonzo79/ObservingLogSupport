package com.mobileobservinglog.ngcdata;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ConstellationReplace {
	static TreeMap<String, String> associations = new TreeMap<String, String>();
    static Set<String> knownValues = new TreeSet<String>();
	
	static{
		associations.put("And", "Andromeda");
		associations.put("Ant", "Antlia");
		associations.put("Aps", "Apus");
		associations.put("Aqr", "Aquarius");
		associations.put("Aql", "Aquila");
		associations.put("Ara", "Ara");
		associations.put("Ari", "Aries");
		associations.put("Aur", "Auriga");
		associations.put("Boo", "Bootes");
        associations.put("Boötes", "Bootes");
		associations.put("Cae", "Caelum");
		associations.put("Cam", "Camelopardalis");
		associations.put("Cnc", "Cancer");
		associations.put("Cvn", "Canes Venatici");
        associations.put("CVn", "Canes Venatici");
		associations.put("CMa", "Canis Major");
		associations.put("CMi", "Canis Minor");
		associations.put("Cap", "Capricornus");
		associations.put("Car", "Carina");
		associations.put("Cas", "Cassiopeia");
		associations.put("Cen", "Centaurus");
		associations.put("Cep", "Cepheus");
		associations.put("Cet", "Cetus");
		associations.put("Cha", "Chamaeleon");
		associations.put("Cir", "Circinus");
		associations.put("Col", "Columba");
		associations.put("Com", "Coma Berenices");
		associations.put("CrA", "Corona Australis");
		associations.put("CrB", "Corona Borealis");
		associations.put("Crv", "Corvus");
		associations.put("Crt", "Crater");
		associations.put("Cru", "Crux");
		associations.put("Cyg", "Cygnus");
		associations.put("Del", "Delphinus");
		associations.put("Dor", "Dorado");
		associations.put("Dra", "Draco");
		associations.put("Equ", "Equuleus");
		associations.put("Eri", "Eridanus");
		associations.put("For", "Fornax");
		associations.put("Gem", "Gemini");
		associations.put("Gru", "Grus");
		associations.put("Her", "Hercules");
		associations.put("Hor", "Horologium");
		associations.put("Hya", "Hydra");
		associations.put("Hyi", "Hydrus");
		associations.put("Ind", "Indus");
		associations.put("Lac", "Lacerta");
		associations.put("Leo", "Leo");
		associations.put("LMi", "Leo Minor");
		associations.put("Lep", "Lepus");
		associations.put("Lib", "Libra");
		associations.put("Lup", "Lupus");
		associations.put("Lyn", "Lynx");
		associations.put("Lyr", "Lyra");
		associations.put("Men", "Mensa");
		associations.put("Mic", "Microscopium");
		associations.put("Mon", "Monoceros");
		associations.put("Mus", "Musca");
		associations.put("Nor", "Norma");
		associations.put("Oct", "Octans");
		associations.put("Oph", "Ophiuchus");
		associations.put("Ori", "Orion");
		associations.put("Pav", "Pavo");
		associations.put("Peg", "Pegasus");
		associations.put("Per", "Perseus");
		associations.put("Phe", "Phoenix");
		associations.put("Pic", "Pictor");
		associations.put("Psc", "Pisces");
		associations.put("PsA", "Piscis Austrinus");
		associations.put("Pup", "Puppis");
		associations.put("Pyx", "Pyxis");
		associations.put("Ret", "Reticulum");
		associations.put("Sge", "Sagitta");
		associations.put("Sgr", "Sagittarius");
		associations.put("Sco", "Scorpius");
		associations.put("Scl", "Sculptor");
		associations.put("Sct", "Scutum");
		associations.put("SerCp", "Serpens Caput");
		associations.put("SerCd", "Serpens Cauda");
		associations.put("Sex", "Sextans");
		associations.put("Tau", "Taurus");
		associations.put("Tel", "Telescopium");
		associations.put("Tri", "Triangulum");
		associations.put("TrA", "Triangulum Australe");
		associations.put("Tuc", "Tucana");
		associations.put("UMa", "Ursa Major");
		associations.put("UMi", "Ursa Minor");
		associations.put("Vel", "Vela");
		associations.put("Vir", "Virgo");
		associations.put("Vol", "Volans");
		associations.put("Vul", "Vulpecula");
		associations.put("...", "N/A");

        knownValues.addAll(associations.values());
	}
	
	public static String getAssociation(String input) {
		return associations.get(input);
	}

    public static boolean isKnown(String input) {
        return knownValues.contains(input);
    }
}
