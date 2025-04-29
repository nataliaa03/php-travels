package net.phptravels;

import lombok.extern.slf4j.Slf4j;
import net.phptravels.pages.FoundFlightsPage;
import net.phptravels.pages.SearchPanel;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

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
                if (checkIfFlightsFound()) {
                    log.info("Flights found.");
                    flightEl = foundFlightsPage.waitForFLightsList().getFlights().get(0);
                    Assert.assertTrue(flightEl.isDisplayed());
                } else {
                    log.info("Flights not found. Assertion for flights results skipped.");
                    return null;
                }
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

    public boolean checkIfFlightsFound() {
        FoundFlightsPage foundFlightsPage = new FoundFlightsPage(driver);
        List<WebElement> flights = foundFlightsPage.waitForFlightsResults().getFlights();

        if (!flights.isEmpty() && flights.get(0).isDisplayed()) {
            log.info(String.format("Flights has shown (count: %s)", flights.size()));
            return true;
        }
        if (foundFlightsPage.getFlightsInfo().equals("No Flights Available")) {
            log.info("'No Flights Available' info has shown.");
            return false;
        }
        return false;
    }

    public String getFromCity1Code() {
        return fromCity1Code;
    }

    public String getToCity1Code() {
        return toCity1Code;
    }
}
