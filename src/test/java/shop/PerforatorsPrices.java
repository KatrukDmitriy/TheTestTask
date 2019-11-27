package shop;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PerforatorsPrices extends TestsSettings {

    @Test
    public void checkPrices() {

        categories.findElement(By.cssSelector("[href=\"/shop/perforatory/\"]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("bottom-sticky")));

        checkPrises(howMany);
    }

    private void checkPrises(int howMany){

        for (int j = 0; j < howMany; j++) {

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("bottom-sticky")));

            WebElement products = driver.findElement(By.id("bottom-sticky"));

            List<WebElement> perforators = products.findElements(By.className("card__info"));

            for (int i = 0; i < perforators.size(); i++) {

                WebElement perforator = perforators.get(i);

                WebElement factualPrice = perforator.findElement(By.className("card__price-row"));

                WebElement perforatorName = perforator.findElement(By.className("card__name"));

                StringBuilder builderNew = new StringBuilder(factualPrice.getText());

                double priceNew = Double.parseDouble(builderNew.toString().replaceAll("грн", ""));

                if (!(priceNew > 0)) {

                    String failure = perforatorName.getText() + " don`t have price";

                    System.out.println(failure);

                }
            }

            WebElement pages = driver.findElement(By.id("paged"));

            if (j < howMany - 1) {

                int numberOfPage = j + 2;

                pages.findElement(By.cssSelector("[href=\"/shop/perforatory/?PAGEN_1=" + numberOfPage + "\"]")).click();
            }
        }
    }
}
