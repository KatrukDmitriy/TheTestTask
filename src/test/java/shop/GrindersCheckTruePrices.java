package shop;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class GrindersCheckTruePrices extends TestsSettings {

    @Test
    public void checkTruePrices() {

        categories.findElement(By.cssSelector("[href=\"/shop/bolgarki/\"]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("bottom-sticky")));

        WebElement products = driver.findElement(By.id("bottom-sticky"));

        List<WebElement> webElements = products.findElements(By.className("card__info"));

        checkPrises(webElements, howMany);
    }

    private void checkPrises(List<WebElement> webElements, int howManyTimes){

        for(int i = 0; i < howManyTimes; i++) {

            Random random = new Random();

            int randomGrinder = random.nextInt(20);

            WebElement grinder = webElements.get(randomGrinder);

            WebElement action = grinder.findElement(By.tagName("span"));

            String percentStr = action.getText();

            if(percentStr.contains("-")) {

                double percent = Double.parseDouble(action.getText().replaceAll("\\W", ""));

                WebElement oldPrice = grinder.findElement(By.className("card__price-old"));

                WebElement factualPrice = grinder.findElement(By.className("card__price-row"));

                WebElement grinderName = grinder.findElement(By.className("card__name"));

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
}
