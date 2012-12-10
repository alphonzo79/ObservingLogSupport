package main.java.data_gatherer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class SeleniumDriver {
     WebDriver driver;

    public SeleniumDriver() {
        driver = (WebDriver)new FirefoxDriver();
    }

    public void getObjectData(String page) {
        driver.get(page);
        List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class, 'wikitable')]/tbody/tr[count(../th = 0)]"));
    }
}
