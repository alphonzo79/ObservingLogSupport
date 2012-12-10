package com.mobileobservinglog.ngcdata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SeleniumIC {
    FirefoxDriver driver;

    public SeleniumIC() {
        driver = new FirefoxDriver();
    }

    public void doit() {
        ArrayList<String> outputLines = new ArrayList<String>();
        ArrayList<Integer> warnings = new ArrayList<Integer>();
        int start = 1;
        int end = 5;
        String nullString = "NULL";
        String catalogName = String.format("IC %d-%d", start, end);
        String outputFilename = "ic_1_catalog_database_values.xml";

        driver.get("http://www.ngcicproject.org/pubdb.htm");
        for(int i = start; i <= end; i++) {
            String objectName = "IC " + i;
            System.out.println("Gathering number " + i);
            driver.findElement(By.name("ngcicobject")).clear();
            driver.findElement(By.name("ngcicobject")).sendKeys(objectName);
            driver.findElement(By.name("objectdesignation")).click();

            String ra = driver.findElement(By.xpath("//b[contains(text(), 'Right Ascension (2000)')]/following-sibling::font/b")).getText();
            if(ra.length() < 1) {
                ra = "N/A";
                System.out.println("!!!!WARNING: Right Ascension was empty!!!!");
                warnings.add(i);
            }

            String dec = driver.findElement(By.xpath("//b[contains(text(), 'Declination (2000)')]/following-sibling::font/b")).getText();
            if(dec.length() < 1) {
                dec = "N/A";
                System.out.println("!!!!WARNING: Right Ascension was empty!!!!");
                warnings.add(i);
            } else {
                dec = ProcessLineUtils.processDeclination(dec);
            }

            String type = driver.findElement(By.xpath("//b[contains(text(), 'Object Type')]/following-sibling::font/b")).getText();
            if(!ObjectTypeProcessor.isKnown(type)) {
                String temp = ObjectTypeProcessor.getAssociation(type);
                if(temp != null) {
                    type = temp;
                } else {
                    System.out.println("!!!!WARNING: Object Type was Unrecognized!!!!");
                    warnings.add(i);
                }
            }

            String constellation = driver.findElement(By.xpath("//b[contains(text(), 'Constellation')]/following-sibling::font/b")).getText();
            if(!ConstellationReplace.isKnown(constellation)) {
                String temp = ConstellationReplace.getAssociation(constellation);
                if(temp != null) {
                    constellation = temp;
                } else {
                    System.out.println("!!!!WARNING: Constellation was Unrecognized!!!!");
                    warnings.add(i);
                }
            }

            String magnitude = driver.findElement(By.xpath("//b[contains(text(), 'Visual Magnitude')]/following-sibling::font/b")).getText();
            try {
                float test = Float.parseFloat(magnitude);
            } catch (NumberFormatException e) {
                System.out.println("!!!!WARNING: Magnitude was Unrecognized!!!!");
                warnings.add(i);
                magnitude = nullString;
            }
            if(magnitude.contains("--")) {
                System.out.println("!!!!WARNING: Magnitude was Empty!!!!");
                warnings.add(i);
                magnitude = nullString;
            }

            String alsoCataloged = driver.findElement(By.xpath("//b[contains(text(), 'Also Cataloged')]/../following-sibling::td/font/b")).getText();
            if(alsoCataloged.toLowerCase().contains("no other") || alsoCataloged.length() < 1) {
                alsoCataloged = nullString;
            }

            String description = driver.findElement(By.xpath("//b[contains(text(), 'Summary Description')]/following-sibling::font/b")).getText();
            if(description.length() < 1 || description.contains("--")) {
                description = nullString;
            } else {
                description = ProcessLineUtils.processDescription(description);
            }

            String size = driver.findElement(By.xpath("//b[contains(text(), 'Object Size')]/following-sibling::font/b")).getText();
            if(size.length() < 1 || size.contains("--")) {
                System.out.println("!!!!WARNING: Object Size was Empty!!!!");
                warnings.add(i);
                size = nullString;
            } else {
                size = ProcessLineUtils.processSize(size);
            }

            String season = ProcessLineUtils.determineSeason(constellation);

            String outputLine = nullString + ";" + objectName + ";" + nullString + ";" + type + ";" + magnitude + ";" + size + ";" + "N/A" + ";" + constellation + ";" +
                    season + ";" + ra + ";" + dec + ";" + description + ";" + catalogName + ";" + alsoCataloged + ";" + "/ngc/normal/ngc" + i +
                    ".gif;/ngc/night/ngc" + i + ".gif;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL";

            outputLines.add(outputLine);
            driver.navigate().back();
        }

        if(warnings.size() > 0) {
            System.out.println("WARNINGS: ");
            for(Integer warning : warnings) {
                System.out.println(warning);
            }
        }

        saveFile(outputLines, outputFilename);
    }

    public void saveFile(ArrayList<String> outputLines, String filename) {
        String output = "C:\\Users\\joe.rowley\\Documents\\ObservingLog\\ObservingLog\\res\\values\\" + filename;
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
            writer.write("    <string name=\"" + filename + "\">\"");
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
}
