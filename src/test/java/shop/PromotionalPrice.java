package shop;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class PromotionalPrice extends TestsSettings{


    @Test
    public void promotionalPriceCheck() {

        categories.findElement(By.cssSelector("[href=\"/shop/dreli/\"]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("bottom-sticky")));

        WebElement products = driver.findElement(By.id("bottom-sticky"));

        List<WebElement> webElements = products.findElements(By.className("card__info"));

        checkPrises(webElements, howMany);

    }

    private void checkPrises(List<WebElement> webElements, int howMany){

        for(int i = 0; i < howMany; i++) {

            Random random = new Random();

            int randomDrill = random.nextInt(20);

            WebElement drill = webElements.get(randomDrill);

            WebElement oldPrice = drill.findElement(By.className("card__price-old"));

            WebElement factualPrice = drill.findElement(By.className("card__price-row"));

            WebElement drillName = drill.findElement(By.className("card__name"));

            StringBuilder builderOld = new StringBuilder(oldPrice.getText());
            StringBuilder builderNew = new StringBuilder(factualPrice.getText());

            double priceOld = Double.parseDouble(builderOld.substring(0, builderOld.indexOf(" ")));
            double priceNew = Double.parseDouble(builderNew.toString().replaceAll("грн", ""));

            if (priceOld > 0 && priceNew > 0 && priceNew < priceOld){

                String accepted = drillName.getText() + " old price: "
                        + priceOld + " new price: " + priceNew + ". Test passed";

                System.out.println(accepted);

            }else{
                System.out.println(drillName.getText() + priceOld + " old price"
                        + priceNew + " new price" + " Test failure");
            }

        }
    }
}
