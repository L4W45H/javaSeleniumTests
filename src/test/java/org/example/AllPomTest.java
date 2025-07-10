package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AllPomTest {
    private WebDriver driver;
    private final String BASE_URL = "https://testautomationpractice.blogspot.com/";

    @BeforeAll
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testDragAndDrop() {
        DragAndDropPage page = new DragAndDropPage(driver);
        page.gotoPage(BASE_URL + "/drag_and_drop");
        page.dragAndDrop();
    }

    @Test
    public void testSelect6thOptionInScrollingDropdown() {
        ScrollingDropdownPage page = new ScrollingDropdownPage(driver);
        page.gotoPage(BASE_URL);
        page.openDropdown();
        page.selectOptionByIndex(5);
    }

    @Test
    public void testFillAndVerifyShadowDom() {
        ShadowDomPage page = new ShadowDomPage(driver);
        page.gotoPage(BASE_URL + "/shadow_dom");
        page.fillShadowInput("Hello Shadow DOM!");
        String value = page.getShadowInputValue();
        Assertions.assertEquals("Hello Shadow DOM!", value);
    }

    @Test
    public void testMoveSlider() {
        SliderPage page = new SliderPage(driver);
        page.gotoPage(BASE_URL + "/slider");
        page.moveSlider(0.2, 0.9);
        String value = page.getSliderValue();
        System.out.println("Slider value: " + value);
    }

    @Test
    public void testUploadFile() {
        UploadPage page = new UploadPage(driver);
        page.gotoPage(BASE_URL + "/upload");
        page.uploadFile("/home/l4w45h-linux/Downloads/discord-0.0.98.deb");
    }

    @Test
    public void testPaginationTableAndTotalPrice() {
        PaginationWebTablePage page = new PaginationWebTablePage(driver);
        page.gotoPage(BASE_URL + "/pagination_table");
        Map<String, Object> result = page.getAllProductsAndTotalPrice();
        List<?> allProducts = (List<?>) result.get("allProducts");
        double totalPrice = (double) result.get("totalPrice");
        System.out.println("All products: " + allProducts);
        System.out.println("Total price: " + Math.round(totalPrice));
    }

    @Test
    public void testSVGElementsScreenshot() throws IOException {
        SVGElementsPage page = new SVGElementsPage(driver);
        page.gotoPage(BASE_URL + "/svg_elements");
        List<?> svgs = page.getAllSVGElements();
        Assertions.assertTrue(svgs.size() > 0);
        // Screenshot comparison logic can be added here if needed
    }

} 