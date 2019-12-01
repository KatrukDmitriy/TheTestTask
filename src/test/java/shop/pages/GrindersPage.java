package shop.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class GrindersPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public GrindersPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(className = "shop-categories__container")
    private WebElement categoriesElectronicTool;

    private By grindersPage = By.cssSelector("[href=\"/shop/bolgarki/\"]");

    private By shopCategories = By.id("bottom-sticky");

    private By cardInfo =  By.className("card__info");

    private By priceOld = By.className("card__price-old");

    private By priceActual =  By.className("card__price-row");

    private By cardName =  By.className("card__name");


    public void open(){
        categoriesElectronicTool.findElement(grindersPage).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(shopCategories));
    }

    public List<WebElement> getAllProducts(){
        List<WebElement> productList = driver.findElement(shopCategories).findElements(cardInfo);
        return productList;
    }

    public void checkTruePrice(WebElement grinder){
        WebElement action = grinder.findElement(By.tagName("span"));

        String percentStr = action.getText();

        if(percentStr.contains("-")) {

            double percent = Double.parseDouble(action.getText().replaceAll("\\W", ""));

            WebElement oldPrice = grinder.findElement(priceOld);

            WebElement factualPrice = grinder.findElement(priceActual);

            WebElement grinderName = grinder.findElement(cardName);

            StringBuilder builderOld = new StringBuilder(oldPrice.getText());
            StringBuilder builderNew = new StringBuilder(factualPrice.getText());

            double priceOld = Double.parseDouble(builderOld.substring(0, builderOld.indexOf(" ")));
            double priceNew = Double.parseDouble(builderNew.toString().replaceAll("грн", ""));

            double expectedPrice = priceOld;

            expectedPrice = expectedPrice - (priceOld * percent / 100);

            Assert.assertSame(grinderName.getText(), expectedPrice, priceNew);
        }

    }


}
