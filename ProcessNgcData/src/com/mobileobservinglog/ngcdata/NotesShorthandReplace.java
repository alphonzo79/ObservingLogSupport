package com.mobileobservinglog.ngcdata;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotesShorthandReplace {
	static TreeMap<String, String> associations = new TreeMap<String, String>();
	
	static{
		associations.put("^Ab", "about");
		associations.put("^alm", "almost");
		associations.put("^am", "among");
		associations.put("^AN", "annular/ring nebula");
		associations.put("^and", "and");
		associations.put("^app", "appended");
		associations.put("^att", "attached");
		associations.put("^b(?!e|iN|n|s|p|f)", "brighter");
		associations.put("^be(t)?", "between");
		associations.put("^biN", "binuclear Nucleus");
		associations.put("^bn", "brightest towards the north side");
		associations.put("^bs", "brightest towards the south side");
		associations.put("^bp", "brightest towards the preceding side");
		associations.put("^bf", "brightest towards the following side");
		associations.put("^B", "bright");
		associations.put("^c(?![ho])", "considerably");
		associations.put("^ch", "chevelure");
		associations.put("^co(?!m|nt)", "coarse/coarsely");
		associations.put("^com", "cometic");
		associations.put("^cont", "in contact");
		associations.put("^C(?!\\.G|l|\\.)", "compressed");
		associations.put("^C.G.H.", "Cape of Good Hope");
		associations.put("^Cl", "cluster");
		associations.put("^d(?!ef|if|iffic|ist)", "diameter");
		associations.put("^def", "defined");
		associations.put("^dif(?!fic)", "diffused");
		associations.put("^diffic", "difficult");
		associations.put("^dist", "distance");
		associations.put("^D(?!EGREES\\))", "double");
        associations.put("^\\(DEGREES\\)", "°");
		associations.put("^e(?!e|r|xc|nd)", "extremely");
		associations.put("^ee", "most extremely");
        associations.put("^end", "end");
		associations.put("^er", "easily resolvable");
		associations.put("^exc", "excentric");
		associations.put("^E", "extended");
		associations.put("^f", "following");
		associations.put("^F", "faint");
		associations.put("^g(?!r)", "gradually");
		associations.put("^GC", "globular cluster");
		associations.put("^gr", "group");
		associations.put("^i(?!nv|F|n)", "irregular");
        associations.put("^in(?!v)", "in");
		associations.put("^inv", "involved/involving");
		associations.put("^iF", "irregular figure");
		associations.put("^l(?!ast)", "little/long");
		associations.put("^last", "last");
		associations.put("^L", "large");
		associations.put("^m(?!m|n)", "much");
		associations.put("^mm", "mixed magnitudes");
		associations.put("^mn", "milky nebulosity");
		associations.put("^M", "middle/in the middle");
		associations.put("^n(?!d|eb|f|p|r|ot|o description)", "north");
		associations.put("^neb", "nebula");
		associations.put("^nf", "north following");
        associations.put("^no description", "no description");
		associations.put("^np", "north preceding");
		associations.put("^nr", "near");
        associations.put("^not", "not");
		associations.put("^N(?!o description)", "Nucleus/(to a nucleus)");
        associations.put("^No description", "No description");
		associations.put("^of", "of");
        associations.put("^or", "or");
		associations.put("^p(?=[FBLgmsSC])", "pretty"); //before F, B, L, s
		associations.put("^p(?![FBLgmsSC])", "preceding");
		associations.put("^P(?!N)", "poor");
		associations.put("^PN", "planetary nebula");
		associations.put("^quad", "quadrilateral");
		associations.put("^quar", "quartile");
		associations.put("^r(?!d|r|rr)", "resolvable (mottled, not resolved)");
		associations.put("^rd", "rd");
		associations.put("^rr(?!r)", "partially resolved, some stars seen");
		associations.put("^rrr", "well resolved, clearly consisting of stars");
		associations.put("^R(?!R|i)", "round");
		associations.put("^RR", "exactly round");
		associations.put("^Ri", "rich");
		associations.put("^s(?!p|f|c|t|ev|usp|h|tell|m)", "suddenly/south");
		associations.put("^sp", "south preceding");
		associations.put("^sf", "south following");
		associations.put("^sc", "scattered");
		associations.put("^st(?!ell|ar)", "stars");
        associations.put("^star", "star");
		associations.put("^sev", "several");
		associations.put("^susp", "suspected");
		associations.put("^sh", "shaped");
		associations.put("^stell", "stellar");
		associations.put("^S", "small");
		associations.put("^sm", "smaller");
		associations.put("^triN", "trinuclear");
		associations.put("^trap", "trapezium");
		associations.put("^v(?!v|ar)", "very");
		associations.put("^vv", "very, very");
		associations.put("^var", "variable");
		associations.put("^\\*(?!\\*|\\*\\*)", "star"); //*10: a star of the 10th magnitude
		associations.put("^\\*\\*(?!\\*)", "double star");
		associations.put("^\\*\\*\\*", "triple star");
		associations.put("^!(?!\\!|\\!\\!)", "remarkable");
		associations.put("^!!(?!\\!)", "very remarkable");
		associations.put("^!!!", "a magnificent or otherwise interesting object");
        associations.put("^\\.\\.\\.(?=\\d)", "th magnitude to ");
        associations.put("^\\.\\.\\.(?!\\d)", "th magnitude downward ");
		//associations.put("^st 9...", "stars from the 9th magnitude downwards");
		//associations.put("^st 9...13", "stars from the 9th to 13th magnitude"); //Check use of these, is it variable?
	}
		
	/**
	 * Iterate through the associations map and replace any of the sequences we find. It's very inefficient, but we're only going to use this once so I'm not 
	 * going to optimize it any more. This way it's more easily managed.
	 * @param input
	 * @return
	 */
	public static String parseShorthand(String input) {
		String retVal = "";
		
		String foundPattern = "";
		for(String key : associations.keySet()) {
			Pattern pattern = Pattern.compile(key);
			Matcher match = pattern.matcher(input);
			if(match.find()) {
				foundPattern = key;
				break;
			}
		}
		
		if(foundPattern.length() > 0) {
			retVal = " " + associations.get(foundPattern);
			input = input.replaceFirst(foundPattern, "");
			if(input.length() > 0) {
				retVal = retVal + parseShorthand(input);
			}
		} else if(input.length() > 1) {
			retVal = input.charAt(0) + parseShorthand(input.substring(1));
		} else {
			retVal = Character.toString(input.charAt(0));
		}
		
		retVal = retVal.replaceAll("  ", " ");
		
		return retVal;
	}
}
