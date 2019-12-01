package shop;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import shop.pages.ElectronicToolPage;


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

        ElectronicToolPage electronicToolPage = PageFactory.initElements(driver, ElectronicToolPage.class);

        electronicToolPage.open();

        electronicToolPage.goToTheElectronicTool();

    }

    @After
    public void close(){
        driver.quit();
    }
}
