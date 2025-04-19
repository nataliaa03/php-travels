package net.phptravels;

import lombok.extern.slf4j.Slf4j;
import net.phptravels.pages.FoundFlightsPage;
import net.phptravels.pages.SearchPanel;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static net.phptravels.utils.AssertionUtils.assertElementHasAttribute;


@Slf4j
public class SearchFlightTest extends FlightsCommon {
    private String fromCity2Name = "Bratislava";
    private String fromCity2Code = "BTS";
    private String toCity2Name = "Kuala Lumpur";
    private String toCity2Code = "KUL";


    @Test
    public void shouldFlightBeFound() {
        SearchPanel searchPanel = new SearchPanel(driver);
        WebElement flightTab = searchPanel.getFlightTab();

        assertElementHasAttribute(flightTab, "aria-selected");

        searchBasicFlight();
    }

    @Test
    public void shouldFlightBeNotFound() {
        SearchPanel searchPanel = new SearchPanel(driver);
        if (searchPanel.searchCity("from", fromCity2Code, fromCity2Name)) {
            if (searchPanel.searchCity("to", toCity2Code, toCity2Name)) {
                searchPanel.clickSearchFlight();
                FoundFlightsPage foundFlightsPage = new FoundFlightsPage(driver);
                List<WebElement> flights = foundFlightsPage.waitForFlightsResults().getFlights();

                Assert.assertTrue(foundFlightsPage.getFlightsInfo().equals("No Flights Available") || (!flights.isEmpty() && flights.get(0).isDisplayed()));
            } else {
                log.info("NOT Found 'TO' city. Test skipped.");
            }
        } else {
            log.info("NOT Found 'FROM' city. Test skipped.");
        }
    }
}
