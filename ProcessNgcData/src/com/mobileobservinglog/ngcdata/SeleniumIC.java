package com.mobileobservinglog.ngcdata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SeleniumIC {
    FirefoxDriver driver;

    public SeleniumIC() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void doit() {
        ArrayList<String> outputLines = new ArrayList<String>();
        ArrayList<Integer> magnitudeError = new ArrayList<Integer>();
        ArrayList<Integer> typeError = new ArrayList<Integer>();
        ArrayList<Integer> constellationError = new ArrayList<Integer>();
        ArrayList<Integer> sizeError = new ArrayList<Integer>();
        ArrayList<Integer> rightAscen = new ArrayList<Integer>();
        ArrayList<Integer> declination = new ArrayList<Integer>();
        ArrayList<Integer> driverError = new ArrayList<Integer>();
        int start = 5001;
        int end = 5386;
        String nullString = "NULL";
        String catalogName = String.format("IC %d-%d", start, end);
        String outputFilename = "ic_6_catalog_database_values.xml";

        driver.get("http://www.ngcicproject.org/pubdb.htm");
        for(int i = start; i <= end; i++) {
            String objectName = "IC " + i;
            System.out.println("Gathering number " + i);
            driver.findElement(By.name("ngcicobject")).clear();
            driver.findElement(By.name("ngcicobject")).sendKeys(objectName);
            driver.findElement(By.name("objectdesignation")).click();

            if(driver.getPageSource().contains("For some reason, there wasn't any data in the database for")) {
                System.out.println("!!!!WARNING: WebDriverException Caught!!!!");
                driverError.add(i);
                String outputLine = nullString + ";" + objectName + ";" + nullString + ";" + nullString + ";" + nullString + ";" + nullString + ";" + nullString + ";" + nullString + ";" +
                        nullString + ";" + nullString + ";" + nullString + ";" + nullString + ";" + catalogName + ";" + nullString + ";" + nullString +
                        nullString + ";NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL";

                outputLines.add(outputLine);
                driver.navigate().back();
                continue;
            }

            try{
                String ra = driver.findElement(By.xpath("//b[contains(text(), 'Right Ascension (2000)')]/following-sibling::font/b")).getText();
                if(ra.length() < 1) {
                    ra = "N/A";
                    System.out.println("!!!!WARNING: Right Ascension was empty!!!!");
                    rightAscen.add(i);
                }

                String dec = driver.findElement(By.xpath("//b[contains(text(), 'Declination (2000)')]/following-sibling::font/b")).getText();
                if(dec.length() < 1) {
                    dec = "N/A";
                    System.out.println("!!!!WARNING: Right Ascension was empty!!!!");
                    declination.add(i);
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
                        typeError.add(i);
                    }
                }

                String constellation = driver.findElement(By.xpath("//b[contains(text(), 'Constellation')]/following-sibling::font/b")).getText();
                if(!ConstellationReplace.isKnown(constellation)) {
                    String temp = ConstellationReplace.getAssociation(constellation);
                    if(temp != null) {
                        constellation = temp;
                    } else {
                        System.out.println("!!!!WARNING: Constellation was Unrecognized!!!!");
                        constellationError.add(i);
                    }
                }

                String magnitude = driver.findElement(By.xpath("//b[contains(text(), 'Visual Magnitude')]/following-sibling::font/b")).getText();
                try {
                    float test = Float.parseFloat(magnitude);
                } catch (NumberFormatException e) {
                    System.out.println("!!!!WARNING: Magnitude was Unrecognized!!!!");
                    magnitudeError.add(i);
                    magnitude = nullString;
                }
                if(magnitude.contains("--")) {
                    System.out.println("!!!!WARNING: Magnitude was Empty!!!!");
                    magnitudeError.add(i);
                    magnitude = nullString;
                }

                String alsoCataloged = driver.findElement(By.xpath("//b[contains(text(), 'Also Cataloged')]/../following-sibling::td/font/b")).getText();
                if(alsoCataloged.toLowerCase().contains("no other") || alsoCataloged.length() < 1) {
                    alsoCataloged = nullString;
                }

                String description = driver.findElement(By.xpath("//b[contains(text(), 'Summary Description')]/following-sibling::font/b")).getText();
                if(description.length() < 1 || description.contains("...")) {
                    description = nullString;
                } else {
                    description = ProcessLineUtils.processDescription(description);
                }

                String size = driver.findElement(By.xpath("//b[contains(text(), 'Object Size')]/following-sibling::font/b")).getText();
                if(size.length() < 1 || size.contains("--")) {
                    System.out.println("!!!!WARNING: Object Size was Empty!!!!");
                    sizeError.add(i);
                    size = nullString;
                } else {
                    size = ProcessLineUtils.processSize(size);
                }

                String season = ProcessLineUtils.determineSeason(constellation);

                String outputLine = nullString + ";" + objectName + ";" + nullString + ";" + type + ";" + magnitude + ";" + size + ";" + "N/A" + ";" + constellation + ";" +
                        season + ";" + ra + ";" + dec + ";" + description + ";" + catalogName + ";" + alsoCataloged + ";" + "/ic/normal/ic" + i +
                        ".gif;/ic/night/ic" + i + ".gif;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL";

                outputLines.add(outputLine);
            } catch (WebDriverException e) {
                System.out.println("!!!!WARNING: WebDriverException Caught!!!!");
                driverError.add(i);
                String outputLine = nullString + ";" + objectName + ";" + nullString + ";" + nullString + ";" + nullString + ";" + nullString + ";" + nullString + ";" + nullString + ";" +
                        nullString + ";" + nullString + ";" + nullString + ";" + nullString + ";" + catalogName + ";" + nullString + ";" + nullString +
                        nullString + ";NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL;NULL";

                outputLines.add(outputLine);
            }
            driver.navigate().back();
        }

        saveErrors(magnitudeError, typeError, sizeError, rightAscen, declination, driverError, constellationError, "Errors" + System.currentTimeMillis());

        saveFile(outputLines, outputFilename);

        driver.close();
        System.out.println();
        System.out.println("Saved Files");
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

    public void saveErrors(ArrayList<Integer> mag, ArrayList<Integer> type, ArrayList<Integer> size, ArrayList<Integer> ra,
                           ArrayList<Integer> dec, ArrayList<Integer> driver, ArrayList<Integer> constellation, String filename) {
        String output = "C:\\Users\\joe.rowley\\Documents\\ObservingLog\\ObservingLog\\NonCodeResources\\Catalogs\\ICCatalog\\" + filename;
        FileWriter fw = null;
        try {
            fw = new FileWriter(output);
        } catch (IOException e) {
            System.out.println("Caught IO exception when trying to get the file write");
            e.printStackTrace();
        }
        BufferedWriter writer = new BufferedWriter(fw);

        try {
            if(mag.size() > 0) {
                System.out.println();
                System.out.println("MAGNITUDE WARNINGS: ");
                writer.write("MAGNITUDE WARNINGS: ");
                writer.newLine();
                for(Integer warning : mag) {
                    System.out.print(warning + "; ");
                    writer.write(Integer.toString(warning));
                    writer.newLine();
                }
            }
            if(type.size() > 0) {
                writer.newLine();
                writer.newLine();
                System.out.println();
                System.out.println("TYPE WARNINGS: ");
                writer.write("TYPE WARNINGS: ");
                writer.newLine();
                for(Integer warning : type) {
                    System.out.print(warning + "; ");
                    writer.write(Integer.toString(warning));
                    writer.newLine();
                }
            }
            if(size.size() > 0) {
                writer.newLine();
                writer.newLine();
                System.out.println();
                System.out.println("SIZE WARNINGS: ");
                writer.write("SIZE WARNINGS: ");
                writer.newLine();
                for(Integer warning : size) {
                    System.out.print(warning + "; ");
                    writer.write(Integer.toString(warning));
                    writer.newLine();
                }
            }
            if(ra.size() > 0) {
                writer.newLine();
                writer.newLine();
                System.out.println();
                System.out.println("RA WARNINGS: ");
                writer.write("RA WARNINGS: ");
                writer.newLine();
                for(Integer warning : ra) {
                    System.out.print(warning + "; ");
                    writer.write(Integer.toString(warning));
                    writer.newLine();
                }
            }
            if(dec.size() > 0) {
                writer.newLine();
                writer.newLine();
                System.out.println();
                System.out.println("DECLINATION WARNINGS: ");
                writer.write("DECLINATION WARNINGS: ");
                writer.newLine();
                for(Integer warning : dec) {
                    System.out.print(warning + "; ");
                    writer.write(Integer.toString(warning));
                    writer.newLine();
                }
            }
            if(constellation.size() > 0) {
                writer.newLine();
                writer.newLine();
                System.out.println();
                System.out.println("CONSTELLATION WARNINGS: ");
                writer.write("CONSTELLATION WARNINGS: ");
                writer.newLine();
                for(Integer warning : constellation) {
                    System.out.print(warning + "; ");
                    writer.write(Integer.toString(warning));
                    writer.newLine();
                }
            }
            if(driver.size() > 0) {
                writer.newLine();
                writer.newLine();
                System.out.println();
                System.out.println("DRIVER WARNINGS: ");
                writer.write("DRIVER WARNINGS: ");
                writer.newLine();
                for(Integer warning : driver) {
                    System.out.print(warning + "; ");
                    writer.write(Integer.toString(warning));
                    writer.newLine();
                }
            }
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
