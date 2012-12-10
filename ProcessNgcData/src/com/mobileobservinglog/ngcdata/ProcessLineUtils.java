package com.mobileobservinglog.ngcdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: joe.rowley
 * Date: 12/9/12
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProcessLineUtils {
    public static String processDeclination(String input) {
        String output = input.replace("\"", "\\\""); //We need escape the double quotes in our xml files so they feed into SQLite correctly
        output = output.replace("(DEGREES)", "°");
        return output;
    }

    public static String processConstellation(String input) {
        String output = "";

        output = ConstellationReplace.getAssociation(input);

        if(output.equals("")) {
            System.out.println("Constellation Output was found to be empty");
        }
        return output;
    }

    public static String processDescription(String input) {
        String output = "";

        output = NotesShorthandReplace.parseShorthand(input).trim();
        char first = output.charAt(0);
        first = Character.toUpperCase(first);
        output = first + output.substring(1);

        if(output.equals("")) {
            System.out.println("Description Output was found to be empty");
        }
        return output;
    }

    public static String processObjectType(String input) {
        String output = "";

        output = ObjectTypeProcessor.getAssociation(input);

        if(output.equals("")) {
            System.out.println("Object Type Output was found to be empty");
        }
        return output;
    }

    public static String filterOtherCatalogs(String input) {
        if(input.equals("...")) {
            return "NULL";
        }
        String messierSpacePattern = "^M \\d";
        Pattern pattern = Pattern.compile(messierSpacePattern);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            input = input.replaceFirst("M ", "M");
        }

        return input;
    }

    public static String determineSeason(String input) {
        String output = "";

        output = DetermineSeason.getAssociation(input);

        if(output.equals("")) {
            System.out.println("Season Output was found to be empty");
        }
        return output;
    }

    public static String processSize(String input) {
        String output = "";

        if(input.contains("...")) {
            return "N/A";
        }

        output = input.replaceFirst("\\s?X\\s?", " X ");

        if(output.equals("")) {
            System.out.println("Size Output was found to be empty");
        }
        return output;
    }

    public static String checkForEmpty(String input) {
        if(input.equals("...")) {
            return "N/A";
        } else {
            return input;
        }
    }
}
