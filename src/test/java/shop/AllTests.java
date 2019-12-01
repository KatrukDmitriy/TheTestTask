package shop;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import shop.pages.DrillsPage;
import shop.pages.GrindersPage;
import shop.pages.PerforatorsPage;
import shop.pages.ScrewdriversPage;

import java.util.List;
import java.util.Random;

public class AllTests extends TestsSettings{

    @Test
    public void promotionalPriceCheck() {

        DrillsPage drillsPage = PageFactory.initElements(driver, DrillsPage.class);

        drillsPage.open();

        List<WebElement> webElements = drillsPage.getAllProducts();

        drillsPage.checkRandomPrices(howMany, webElements);
    }

    @Test
    public void checkTruePrices() {

        GrindersPage grindersPage = PageFactory.initElements(driver, GrindersPage.class);

        grindersPage.open();

        List<WebElement> webElements = grindersPage.getAllProducts();

        for(int i = 0; i < howMany; i++) {

            Random random = new Random();

            int randomGrinder = random.nextInt(20);

            WebElement grinder = webElements.get(randomGrinder);

            grindersPage.checkTruePrice(grinder);
        }
    }

    @Test
    public void checkPrices() {

        PerforatorsPage perforatorsPage = PageFactory.initElements(driver, PerforatorsPage.class);

        perforatorsPage.open();

        for (int j = 0; j < howMany; j++) {

            int currentPage = j + 1;

            List<WebElement> perforators = perforatorsPage.getAllProducts();

            perforatorsPage.checkPrises(perforators);

            if (j < howMany - 1) {

                perforatorsPage.goToNextPage(currentPage);
            }
        }
    }

    @Test
    public void checkPercent() {

        ScrewdriversPage screwdriversPage = PageFactory.initElements(driver, ScrewdriversPage.class);

        screwdriversPage.open();

        for (int j = 0; j < howMany; j++) {

            int currentPage = j + 1;

            List<WebElement> screwdrivers = screwdriversPage.getAllProducts();

            screwdriversPage.checkPromotionMinusTwenty(screwdrivers);

            if (j < howMany - 1) {

                screwdriversPage.goToNextPage(currentPage);

            }
        }
    }
}
