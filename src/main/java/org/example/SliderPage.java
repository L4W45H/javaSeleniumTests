package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SliderPage extends BasePage {
    private By slider = By.cssSelector("#slider-range");
    private By amount = By.cssSelector("#amount");

    public SliderPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage(String url) {
        driver.get(url);
    }

    public void moveSlider(double min, double max) {
        waitForVisibility(slider);
        WebElement sliderElement = driver.findElement(slider);
        WebElement handleMin = sliderElement.findElements(By.cssSelector("span")).get(0);
        WebElement handleMax = sliderElement.findElements(By.cssSelector("span")).get(1);
        int width = sliderElement.getSize().getWidth();
        int xOffsetMin = (int) (width * min);
        int xOffsetMax = (int) (width * max);
        Actions actions = new Actions(driver);
        actions.clickAndHold(handleMin).moveByOffset(xOffsetMin - handleMin.getLocation().getX(), 0).release().perform();
        actions.clickAndHold(handleMax).moveByOffset(xOffsetMax - handleMax.getLocation().getX(), 0).release().perform();
    }

    public String getSliderValue() {
        waitForVisibility(amount);
        return driver.findElement(amount).getAttribute("value");
    }
} 