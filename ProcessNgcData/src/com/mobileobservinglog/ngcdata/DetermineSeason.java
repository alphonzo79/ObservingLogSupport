package com.mobileobservinglog.ngcdata;

import java.util.TreeMap;

public class DetermineSeason {
	static TreeMap<String, String> associations = new TreeMap<String, String>();
	
	static{
		associations.put("Andromeda", "Winter");
		associations.put("Antlia", "Spring");
		associations.put("Apus", "Summer");
		associations.put("Aquarius", "Fall");
		associations.put("Aquila", "Summer");
		associations.put("Ara", "Winter");
		associations.put("Aries", "Fall");
		associations.put("Auriga", "Winter");
		associations.put("Bootes", "Spring");
		associations.put("Caelum", "Fall");
		associations.put("Camelopardalis", "Fall");
		associations.put("Cancer", "Spring");
		associations.put("Canes Venatici", "Spring");
		associations.put("Canis Major", "Winter");
		associations.put("Canis Minor", "Winter");
		associations.put("Capricornus", "Fall");
		associations.put("Carina", "Winter");
		associations.put("Cassiopeia", "Winter");
		associations.put("Centaurus", "Spring");
		associations.put("Cepheus", "Summer");
		associations.put("Cetus", "Fall");
		associations.put("Chamaeleon", "Spring");
		associations.put("Circinus", "Spring");
		associations.put("Columba", "Winter");
		associations.put("Coma Berenices", "Spring");
		associations.put("Corona Australis", "Summer");
		associations.put("Corona Borealis", "Summer");
		associations.put("Corvus", "Spring");
		associations.put("Crater", "Winter");
		associations.put("Crux", "Spring");
		associations.put("Cygnus", "Fall");
		associations.put("Delphinus", "Summer");
		associations.put("Dorado", "Fall");
		associations.put("Draco", "Summer");
		associations.put("Equuleus", "Summer");
		associations.put("Eridanus", "Fall");
		associations.put("Fornax", "Fall");
		associations.put("Gemini", "Winter");
		associations.put("Grus", "Summer");
		associations.put("Hercules", "Summer");
		associations.put("Horologium", "Fall");
		associations.put("Hydra", "Spring");
		associations.put("Hydrus", "Fall");
		associations.put("Indus", "Summer");
		associations.put("Lacerta", "Summer");
		associations.put("Leo", "Spring");
		associations.put("Leo Minor", "Winter");
		associations.put("Lepus", "Winter");
		associations.put("Libra", "Spring");
		associations.put("Lupus", "Spring");
		associations.put("Lynx", "Winter");
		associations.put("Lyra", "Fall");
		associations.put("Mensa", "Fall");
		associations.put("Microscopium", "Summer");
		associations.put("Monoceros", "Winter");
		associations.put("Musca", "Spring");
		associations.put("Norma", "Spring");
		associations.put("Octans", "Summer");
		associations.put("Ophiuchus", "Summer");
		associations.put("Orion", "Winter");
		associations.put("Pavo", "Spring");
		associations.put("Pegasus", "Fall");
		associations.put("Perseus", "Winter");
		associations.put("Phoenix", "Fall");
		associations.put("Pictor", "Fall");
		associations.put("Pisces", "Fall");
		associations.put("Piscis Austrinus", "Summer");
		associations.put("Puppis", "Winter");
		associations.put("Pyxis", "Spring");
		associations.put("Reticulum", "Fall");
		associations.put("Sagitta", "Summer");
		associations.put("Sagittarius", "Summer");
		associations.put("Scorpius", "Summer");
		associations.put("Sculptor", "Fall");
		associations.put("Scutum", "Summer");
		associations.put("Serpens Caput", "Summer");
		associations.put("Serpens Cauda", "Summer");
		associations.put("Sextans", "Winter");
		associations.put("Taurus", "Winter");
		associations.put("Telescopium", "Summer");
		associations.put("Triangulum", "Winter");
		associations.put("Triangulum Australe", "Spring");
		associations.put("Tucana", "Fall");
		associations.put("Ursa Major", "Spring");
		associations.put("Ursa Minor", "Spring");
		associations.put("Vela", "Winter");
		associations.put("Virgo", "Spring");
		associations.put("Volans", "Fall");
		associations.put("Vulpecula", "Fall");
		associations.put("N/A", "N/A");
	}
	
	public static String getAssociation(String input) {
		return associations.get(input);
	}
}
