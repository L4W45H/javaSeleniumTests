package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SVGElementsPage extends BasePage {
    public SVGElementsPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage(String url) {
        driver.get(url);
    }

    public List<WebElement> getAllSVGElements() {
        waitForVisibility(By.cssSelector("svg"));
        return driver.findElements(By.cssSelector("svg"));
    }

    public List<File> screenshotAllSVGs(String dirPath) throws IOException {
        List<WebElement> svgs = getAllSVGElements();
        List<File> screenshots = new ArrayList<>();
        int i = 1;
        for (WebElement svg : svgs) {
            File screenshot = svg.getScreenshotAs(OutputType.FILE);
            File dest = new File(dirPath, "svg_element_" + i + ".png");
            Files.copy(screenshot.toPath(), dest.toPath());
            screenshots.add(dest);
            i++;
        }
        return screenshots;
    }
} 