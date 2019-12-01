package shop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class DrillsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DrillsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(className = "shop-categories__container")
    private WebElement categoriesElectronicTool;

    private By drillsPage = By.cssSelector("[href=\"/shop/dreli/\"]");

    private By shopCategories = By.id("bottom-sticky");

    private By cardInfo =  By.className("card__info");

    private By priceOld = By.className("card__price-old");

    private By priceActual = By.className("card__price-row");

    private By cardName = By.className("card__name");

    private By percent = By.tagName("span");

    public void open(){
        categoriesElectronicTool.findElement(drillsPage).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(shopCategories));
    }

    public List<WebElement> getAllProducts() {
        List<WebElement> productList = driver.findElement(shopCategories).findElements(cardInfo);
        return productList;
    }

    public void checkRandomPrices(int howMany, List<WebElement> drills) {
        for(int i = 0; i < howMany; i++) {

            Random random = new Random();

            int randomDrill = random.nextInt(drills.size() - 1);

            WebElement drill = drills.get(randomDrill);

            String percentStr = drill.findElement(percent).getText();

            if (percentStr.contains("-")) {

                WebElement oldPrice = drill.findElement(priceOld);

                WebElement factualPrice = drill.findElement(priceActual);

                WebElement drillName = drill.findElement(cardName);

                StringBuilder builderOld = new StringBuilder(oldPrice.getText());
                StringBuilder builderNew = new StringBuilder(factualPrice.getText());

                double priceOld = Double.parseDouble(builderOld.substring(0, builderOld.indexOf(" ")));
                double priceNew = Double.parseDouble(builderNew.toString().replaceAll("грн", ""));

                if (priceOld > 0 && priceNew > 0 && priceNew < priceOld) {

                    String accepted = drillName.getText() + " old price: "
                            + priceOld + " new price: " + priceNew + ". Test passed";

                    System.out.println(accepted);

                } else {
                    System.out.println(drillName.getText() + priceOld + " old price"
                            + priceNew + " new price" + " Test failure");
                }
            }else howMany--;

        }
    }
}
