package com.mobileobservinglog.ngcdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
	public static void main(String[] args) {
//		Program program = new Program();
//		program.processNGCTextFile();

        SeleniumIC tool = new SeleniumIC();
        tool.doit();

//        Program program = new Program();
//        program.findIc();
	}
	
	//"C:\\Users\\joe.rowley\\Documents\\ObservingLog\\ObservingLog\\NonCodeResources\\Catalogs\\NGCCatalog\\NGC1.txt"
	private void processNGCTextFile() {
		String root = "C:\\Users\\joe.rowley\\Documents\\ObservingLog\\ObservingLog\\NonCodeResources\\Catalogs\\NGCCatalog\\";
		FileReader fr = null;
		ArrayList<String> outputLines = new ArrayList<String>();
		try {
			fr = new FileReader(root + "NGC8.txt");
		} catch (FileNotFoundException e) {
			System.out.println("The file path was not found!");
			e.printStackTrace();
			return;
		}
		
		BufferedReader reader = new BufferedReader(fr);
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
//				outputLines.add(processLine(line, "NGC 1-1000"));
                outputLines.add(processLine(line, "NGC 7001-7840"));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String output = "C:\\Users\\joe.rowley\\Documents\\ObservingLog\\ObservingLog\\res\\values\\" + "ngc_8_catalog_database_values.xml";
		FileWriter fw = null;
		try {
			fw = new FileWriter(output);
		} catch (IOException e) {
			System.out.println("Caught IO exception when trying to get the file write");
			e.printStackTrace();
		}
		BufferedWriter writer = new BufferedWriter(fw);
		try {
			writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			writer.newLine();
			writer.newLine();
			writer.write("<!--");
			writer.newLine();
			writer.write("Copyright (c) 2012 Joe Rowley");
			writer.newLine();
			writer.write("Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files " +
					"(the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, " +
					"distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the " +
					"following conditions:");
			writer.newLine();		
			writer.write("The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.");
			writer.newLine();
			writer.write("THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF " +
					"MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY " +
					"CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE " +
					"OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.");
			writer.newLine();
			writer.write(" -->");
			writer.newLine();
			writer.newLine();
			writer.write("<resources>");
			writer.newLine();
			writer.write("<!-- We will parse by row, then split on the semicolon. Then we will use those individual values to put together sql statements.");
			writer.newLine();
			writer.write("Columns are _id, designation, commonName, type, magnitude, size, distance, constellation, season, rightAscension, declination, " +
					"objectDescription, catalog, otherCatalogs, imageResource, nightImageResource, logged, logDate, logTime, logLocation, telescope, eyepiece, " +
					"seeing, transparency, favorite, findingMethod, viewingNotes -->");
			writer.newLine();
			writer.write("    <string name=\"ngc_8_catalog_default_values\">\"");
			for(String thisLine : outputLines) {
				writer.write(thisLine);
				writer.newLine();
			}
			writer.write("\"</string>");
			writer.newLine();
			writer.write("</resources>");
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Columns: 0) NGC number, 1) LineNumber, 2) GeneralCatalog Number, 3) JohnHerschel Designation, 4) WilliamHerscel Designation, 5) RightAscension, 6) Declination, 
	//7) Constellation, 8) Summary Description, 9) Discoverer, 10) Year, 11) Telescope Type, 12) Diameter, 13) Object Type, 14) Object Classification, 15) Size, 16) PA, 
	//17) Visual Magnitude, 18) Blue Magnitude, 19) Surface Brightness, 20) NGC Equivelent, 21) IC Equvelent, 22) Also Cataloged As, 23) ...Others
	private String processLine(String line, String catalogName) {
		String[] columns = line.split("\\|");
		int objNum = Integer.parseInt(columns[0].trim());
		String objectName = "NGC " + objNum;
		if(objNum % 5 == 0) {
			System.out.println("Working on " + objNum);
		}
		String rightAsen = ProcessLineUtils.checkForEmpty(columns[5].trim());
		String dec = ProcessLineUtils.processDeclination(ProcessLineUtils.checkForEmpty(columns[6].trim()));
		String constellation = ProcessLineUtils.processConstellation(columns[7].trim());
		String description = ProcessLineUtils.processDescription(columns[8].trim());
		String type = ProcessLineUtils.processObjectType(columns[13].trim());
		String size = ProcessLineUtils.processSize(ProcessLineUtils.checkForEmpty(columns[15].trim()));
		String magnitude = ProcessLineUtils.checkForEmpty(columns[17].trim());
		String alsoCataloged = ProcessLineUtils.filterOtherCatalogs(columns[22].trim());
		String season = ProcessLineUtils.determineSeason(constellation);
		String nullString = "NULL";
		
		String outputLine = nullString + ";" + objectName + ";" + nullString + ";" + type + ";" + magnitude + ";" + size + ";" + "N/A" + ";" + constellation + ";" + 
				season + ";" + rightAsen + ";" + dec + ";" + description + ";" + catalogName + ";" + alsoCataloged + ";" + "/ngc/normal/ngc" + objNum + 
				".gif;/ngc/night/ngc" + objNum + ".gif;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL";
//				NULL;M2;NULL;Globular Cluster;7.5;16' � 16';37,500 ly;Aquarius;Fall;21h 33m 27s;-00� 49' 24\";NULL;Messier Catalog;NGC 7089;
//				  /messier/normal/M2.gif;/messier/night/M2.gif;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL

		
		return outputLine;
	}

    private void findIc() {
        String root = "C:\\Users\\joe.rowley\\Documents\\ObservingLog\\ObservingLog\\NonCodeResources\\Catalogs\\NGCCatalog\\";
        FileReader fr = null;
        try {
            fr = new FileReader(root + "DPublic_HCNGC_stripped.txt");
        } catch (FileNotFoundException e) {
            System.out.println("The file path was not found!");
            e.printStackTrace();
            return;
        }

        BufferedReader reader = new BufferedReader(fr);
        String line = null;
        TreeMap<Integer, String> found = new TreeMap<Integer,String>();
        try {
            while((line = reader.readLine()) != null) {
				String[] columns = line.split("\\|");
                int objNum = Integer.parseInt(columns[0].trim());
                try {
                    int icNum = Integer.parseInt(columns[21]);
                    found.put(icNum, "NGC " + objNum);
                } catch (NumberFormatException e) {
                    //do Nothing
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output = "C:\\Users\\joe.rowley\\Documents\\ObservingLog\\ObservingLog\\NonCodeResources\\Catalogs\\ICCatalog\\" + "foundICRelations.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(output);
        } catch (IOException e) {
            System.out.println("Caught IO exception when trying to get the file write");
            e.printStackTrace();
        }
        BufferedWriter writer = new BufferedWriter(fw);
        try {
            for(Integer ic : found.keySet()) {
                writer.write(ic + ": " + found.get(ic));
                writer.newLine();
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
