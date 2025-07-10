package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationWebTablePage extends BasePage {
    private By tableSelector = By.cssSelector("#productTable");
    private By paginationSelector = By.cssSelector("#pagination li a");

    public PaginationWebTablePage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage(String url) {
        driver.get(url);
    }

    public Map<String, Object> getAllProductsAndTotalPrice() {
        List<Map<String, String>> allProducts = new ArrayList<>();
        double totalPrice = 0;
        waitForVisibility(paginationSelector);
        List<WebElement> pages = driver.findElements(paginationSelector);
        int numPages = pages.size();
        for (int i = 1; i <= numPages; i++) {
            driver.findElement(By.cssSelector("#pagination li:nth-child(" + i + ") a")).click();
            waitForVisibility(tableSelector);
            List<WebElement> rows = driver.findElements(By.cssSelector("#productTable tbody tr"));
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                String id = cells.get(0).getText();
                String name = cells.get(1).getText();
                String priceText = cells.get(2).getText();
                double price = Double.parseDouble(priceText.replace("$", ""));
                Map<String, String> product = new HashMap<>();
                product.put("id", id);
                product.put("name", name);
                product.put("price", String.valueOf(price));
                allProducts.add(product);
                totalPrice += price;
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("allProducts", allProducts);
        result.put("totalPrice", totalPrice);
        return result;
    }
} 