package net.phptravels;

import lombok.extern.slf4j.Slf4j;
import net.phptravels.pages.FoundFlightsPage;
import net.phptravels.pages.SearchPanel;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@Slf4j
public class FlightsCommon extends BaseTest {
    private String fromCity1Code = "LON";
    private String fromCity1Name = "London";
    private String toCity1Name = "New York";
    private String toCity1Code = "NYC";

    public WebElement searchBasicFlight() {
        SearchPanel searchPanel = new SearchPanel(driver);
        WebElement flightEl;
        if (searchPanel.searchCity("from", fromCity1Code, fromCity1Name)) {
            log.info("Found 'FROM' city");
            if (searchPanel.searchCity("to", toCity1Code, toCity1Name)) {
                log.info("Found 'TO' city");
                searchPanel.clickSearchFlight();
                FoundFlightsPage foundFlightsPage = new FoundFlightsPage(driver);
                flightEl = foundFlightsPage.waitForFLightsList().getFlights().get(0);

                Assert.assertTrue(flightEl.isDisplayed());
            } else {
                log.info("NOT Found 'TO' city. Test skipped.");
                return null;
            }
        } else {
            log.info("NOT Found 'FROM' city. Test skipped.");
            return null;
        }
        return flightEl;
    }

    public String getFromCity1Code() {
        return fromCity1Code;
    }

    public String getToCity1Code() {
        return toCity1Code;
    }
}
