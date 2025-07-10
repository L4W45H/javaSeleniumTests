package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadPage extends BasePage {
    private By fileInput = By.cssSelector("#singleFileInput");

    public UploadPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage(String url) {
        driver.get(url);
    }

    public void uploadFile(String filePath) {
        waitForVisibility(fileInput);
        driver.findElement(fileInput).sendKeys(filePath);
    }
} 