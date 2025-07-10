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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SVGElementsPage extends BasePage {
    public SVGElementsPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage(String url) {
        driver.get(url);
    }

    public List<WebElement> getAllSVGElements() {
        waitForVisibility(By.cssSelector(".svg-container svg"));
        return driver.findElements(By.cssSelector(".svg-container svg"));
    }

    public boolean compareSVGsWithGoldenImages(String screenshotDir, String goldenDir, List<String> goldenNames, double maxDifferencePercent) throws IOException {
        List<WebElement> svgs = getAllSVGElements();
        int count = Math.min(goldenNames.size(), svgs.size());
        for (int i = 0; i < count; i++) {
            File screenshot = svgs.get(i).getScreenshotAs(OutputType.FILE);
            File screenshotDest = new File(screenshotDir, "svg_element_" + (i+1) + ".png");
            Files.copy(screenshot.toPath(), screenshotDest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            File goldenFile = new File(goldenDir, goldenNames.get(i));
            BufferedImage imgScreenshot = ImageIO.read(screenshotDest);
            BufferedImage imgGolden = ImageIO.read(goldenFile);
            double diffPercent = imageDifferencePercent(imgScreenshot, imgGolden);
            if (diffPercent > maxDifferencePercent) {
                System.out.println("SVG " + (i+1) + " differs by " + diffPercent + "% (allowed: " + maxDifferencePercent + "%)");
                return false;
            }
        }
        return true;
    }

    private double imageDifferencePercent(BufferedImage imgA, BufferedImage imgB) {
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            return 100.0;
        }
        int width = imgA.getWidth();
        int height = imgA.getHeight();
        int diffPixels = 0;
        int totalPixels = width * height;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    diffPixels++;
                }
            }
        }
        return (diffPixels * 100.0) / totalPixels;
    }
} 