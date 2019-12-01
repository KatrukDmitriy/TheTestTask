package shop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PerforatorsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PerforatorsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(className = "shop-categories__container")
    private WebElement categoriesElectronicTool;

    private By perforatorsPage = By.cssSelector("[href=\"/shop/perforatory/\"]");

    private By shopCategories = By.id("bottom-sticky");

    private By cardInfo =  By.className("card__info");

    private By priceActual =  By.className("card__price-row");

    private By cardName =  By.className("card__name");

    @FindBy(id = "paged")
    private WebElement pages;


    public void open(){
        categoriesElectronicTool.findElement(perforatorsPage).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(shopCategories));
    }

    public List<WebElement> getAllProducts(){
        List<WebElement> productList = driver.findElement(shopCategories).findElements(cardInfo);
        return productList;
    }

    public void checkPrises(List<WebElement> perforators){
        for (int i = 0; i < perforators.size(); i++) {

            WebElement perforator = perforators.get(i);

            WebElement factualPrice = perforator.findElement(priceActual);

            WebElement perforatorName = perforator.findElement(cardName);

            StringBuilder builderNew = new StringBuilder(factualPrice.getText());

            double priceNew = Double.parseDouble(builderNew.toString().replaceAll("грн", ""));

            if (!(priceNew > 0)) {

                String failure = perforatorName.getText() + " don`t have price";

                System.out.println(failure);

            }
        }
    }

    public void goToNextPage(int currentPage){
        int numberOfPage = currentPage + 1;

        pages.findElement(By.cssSelector("[href=\"/shop/perforatory/?PAGEN_1=" + numberOfPage + "\"]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(shopCategories));
    }

}
