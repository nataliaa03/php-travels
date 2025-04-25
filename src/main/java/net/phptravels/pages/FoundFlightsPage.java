package net.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.phptravels.utils.WebDriverUtils.waitForElement;

public class FoundFlightsPage {
    WebDriver driver;
    private final By flightContainerInfo = By.cssSelector(".flights-container div");
    private final By flights = By.cssSelector(".flight");
    private final By flightsCount = By.cssSelector(".flex .j_listABTit strong");
    private final By flightRow = By.cssSelector(".row .flight .row");
    private final By cityCodes = By.cssSelector("div .d-flex");
    private final By tripDuration = By.cssSelector(".row .flight .row > div:nth-of-type(2)");
    private final By flightSegments = By.cssSelector(".row .flight div[ng-repeat^='segment']");
    private final By flightAirline = By.cssSelector(".fw-medium");

    public FoundFlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getFlights() {
        return driver.findElements(flights);
    }

    public WebElement getFlightByIndex(int index) {
        return getFlights().get(index);
    }

    public int getFlightsCount() {
        return Integer.parseInt(driver.findElement(flightsCount).getText());
    }

    public String getFlightFromCity(int flightIndex) {
        return getFlightByIndex(flightIndex)
                .findElement(flightRow)
                .findElements(cityCodes)
                .get(0)
                .getText();
    }

    public String getFlightToCity(int flightIndex) {
        return getFlightByIndex(flightIndex)
                .findElement(flightRow)
                .findElements(cityCodes)
                .get(1)
                .getText();
    }

    public String getTripDuration(int flightIndex) {
        return
                driver
                        .findElements(tripDuration)
                        .get(flightIndex)
                        .getText();
    }

    public String getFlightAirline(int flightIndex, int index) {
        if (index == 0 || index == 1) {
            return getFlightByIndex(flightIndex)
                    .findElements(flightSegments)
                    .get(index)
                    .findElement(flightAirline)
                    .getText();
        } else {
            throw new IllegalArgumentException("Index of an airline for one flight must by 0 or 1.");
        }
    }

    public FoundFlightsPage waitForFLightsList() {
        waitForElement(driver, flights);
        return this;
    }

    public String getFlightsInfo() {
        return driver.findElement(flightContainerInfo).getText();
    }

    public FoundFlightsPage waitForFlightsResults() {
        waitForElement(driver, flightContainerInfo);
        return this;
    }
}
