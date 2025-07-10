package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ScrollingDropdownPage extends BasePage {
    private By dropdown = By.cssSelector("#country");

    public ScrollingDropdownPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage(String url) {
        driver.get(url);
    }

    public void openDropdown() {
        click(dropdown);
    }

    public void selectOptionByIndex(int index) {
        waitForVisibility(dropdown);
        WebElement dropdownElement = driver.findElement(dropdown);
        Select select = new Select(dropdownElement);
        List<WebElement> options = select.getOptions();
        if (index < 0 || index >= options.size()) throw new IllegalArgumentException("Index out of range");
        select.selectByIndex(index);
    }
} 