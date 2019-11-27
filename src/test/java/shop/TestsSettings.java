package shop;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class TestsSettings {

    public WebDriver driver;

    public WebDriverWait wait;

    public int howMany = 3;

    public WebElement categories;


    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver78");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);

        driver.get("https://27.ua/shop/");

        WebElement shopCategories = driver.findElement(By.className("shop-categories__container"));

        WebElement toolsAndInstruments = shopCategories.findElement(By.cssSelector("[href=\"/shop/elektroinstrumenty/\"]"));

        toolsAndInstruments.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("shop-categories")));

        this.categories = driver.findElement(By.className("shop-categories__container"));

    }

    @After
    public void close(){
        driver.quit();
    }
}
