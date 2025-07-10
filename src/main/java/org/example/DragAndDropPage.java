package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropPage extends BasePage {
    private By draggable = By.cssSelector("#draggable");
    private By droppable = By.cssSelector("#droppable");

    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage(String url) {
        driver.get(url);
    }

    public void dragAndDrop() {
        waitForVisibility(draggable);
        waitForVisibility(droppable);
        WebElement source = driver.findElement(draggable);
        WebElement target = driver.findElement(droppable);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }
} 