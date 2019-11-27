package shop;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ScrewdriversCheckHit extends TestsSettings {

    @Test
    public void checkHit() {

        categories.findElement(By.cssSelector("[href=\"/shop/shurupoverty/\"]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("bottom-sticky")));

        checkPrises(howMany);

    }

    private void checkPrises(int howMany){

        for (int j = 0; j < howMany; j++) {

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("bottom-sticky")));

            WebElement products = driver.findElement(By.id("bottom-sticky"));

            List<WebElement> screwdrivers = products.findElements(By.className("card__info"));

            for (int i = 0; i < screwdrivers.size(); i++) {

                WebElement screwdriver = screwdrivers.get(i);

                WebElement screwdriverName = screwdriver.findElement(By.className("card__name"));

                WebElement action = screwdriver.findElement(By.tagName("span"));

                String percentStr = action.getText();

                if (percentStr.contains("20")) {

                    int percent = Integer.parseInt(percentStr.replaceAll("\\W", ""));

                    System.out.println(screwdriverName.getText());
                }
            }

            WebElement pages = driver.findElement(By.id("paged"));

            if (j < howMany - 1) {

                int numberOfPage = j + 2;

                pages.findElement(By.cssSelector("[href=\"/shop/shurupoverty/?PAGEN_1=" + numberOfPage + "\"]")).click();
            }
        }
    }
}
