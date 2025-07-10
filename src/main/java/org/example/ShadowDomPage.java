package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDomPage extends BasePage {
    private By shadowHost = By.cssSelector("#shadow_host");
    private String inputSelector = "input[type='text']";

    public ShadowDomPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage(String url) {
        driver.get(url);
    }

    public void fillShadowInput(String value) {
        waitForVisibility(shadowHost);
        WebElement host = driver.findElement(shadowHost);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].shadowRoot.querySelector(arguments[1]).value = arguments[2]; arguments[0].shadowRoot.querySelector(arguments[1]).dispatchEvent(new Event('input', { bubbles: true }));",
            host, inputSelector, value
        );
    }

    public String getShadowInputValue() {
        waitForVisibility(shadowHost);
        WebElement host = driver.findElement(shadowHost);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(
            "return arguments[0].shadowRoot.querySelector(arguments[1]).value;",
            host, inputSelector
        );
    }
} 