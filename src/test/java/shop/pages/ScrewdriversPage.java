package shop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ScrewdriversPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ScrewdriversPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(className = "shop-categories__container")
    private WebElement categoriesElectronicTool;

    private By screwdriversPage = By.cssSelector("[href=\"/shop/shurupoverty/\"]");

    private By shopCategories = By.id("bottom-sticky");

    private By cardInfo =  By.className("card__info");

    private By percent = By.tagName("span");

    private By cardName = By.className("card__name");

    @FindBy(id = "paged")
    private WebElement pages;

    public void open(){
        categoriesElectronicTool.findElement(screwdriversPage).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(shopCategories));
    }

    public List<WebElement> getAllProducts(){
        List<WebElement> productList = driver.findElement(shopCategories).findElements(cardInfo);
        return productList;
    }

    public void checkPromotionMinusTwenty(List<WebElement> screwdrivers){
        for (int i = 0; i < screwdrivers.size(); i++) {

            WebElement screwdriver = screwdrivers.get(i);

            WebElement screwdriverName = screwdriver.findElement(cardName);

            WebElement action = screwdriver.findElement(percent);

            String percentStr = action.getText();

            if (percentStr.contains("20")) {

                int percent = Integer.parseInt(percentStr.replaceAll("\\W", ""));

                System.out.println(screwdriverName.getText());
            }
        }
    }

    public void goToNextPage(int currentPage){
        int numberOfPage = currentPage + 1;

        pages.findElement(By.cssSelector("[href=\"/shop/shurupoverty/?PAGEN_1=" + numberOfPage + "\"]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(shopCategories));
    }
}
