package shop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElectronicToolPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ElectronicToolPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(className = "shop-categories__container")
    private WebElement element;

    private By electronicToolButton = By.cssSelector("[href=\"/shop/elektroinstrumenty/\"]");

    private By shopCategories = By.className("shop-categories");


    public void open() {
        driver.get("https://27.ua/shop/");
    }

    public void goToTheElectronicTool() {
        element.findElement(electronicToolButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(shopCategories));
    }

}
