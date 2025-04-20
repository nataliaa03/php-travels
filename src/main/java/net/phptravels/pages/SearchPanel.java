package net.phptravels.pages;

import lombok.extern.slf4j.Slf4j;
import net.phptravels.utils.SlowMotionUtils;
import org.openqa.selenium.*;

import java.util.List;

import static net.phptravels.utils.WebDriverUtils.clickOnElement;
import static net.phptravels.utils.WebDriverUtils.waitForElement;

@Slf4j
public class SearchPanel {
    WebDriver driver;
    private final By flightTab = By.cssSelector("button[data-bs-target='#tab-flights']");
    private final By searchIcon = By.cssSelector("form #flights-search");


    public SearchPanel(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFlightTab() {
        waitForElement(driver, flightTab);
        return driver.findElement(flightTab);
    }

    public boolean searchCity(String dest, String code, String name) { //dest: "from" or "to"
        By cityButtonLabel = By.xpath(String.format("//button[text()='%s']", code));
        try {
            fillCity(dest, code);
            clickOnElement(driver, cityButtonLabel);
        } catch (NoSuchElementException | TimeoutException e) {
            log.info("Cannot find city by code. Searching by name.");
            fillCity(dest, name);
            try {
                clickOnElement(driver, cityButtonLabel);
            } catch (NoSuchElementException | TimeoutException ex) {
                log.warn("City not found.");
                return false;
            }
        }
        return true;
    }

    public boolean selectAlternativeCityFromDropdown() {
        List<WebElement> cities = driver.findElements(By.cssSelector(".d-flex .btn"));
        try {
            clickOnElement(driver, cities.get(0));
        } catch (NoSuchElementException | TimeoutException e) {
            log.info("Cannot find alternative city.");
            return false;
        }
        return true;
    }

    public void clickSearchFlight() {
        driver.findElement(searchIcon).click();
    }

    private void fillCity(String dest, String value) {
        By cityTextField = By.cssSelector(String.format("input[name='%s']", dest));
        WebElement field = driver.findElement(cityTextField);
        field.clear();
        SlowMotionUtils.slowSendKeys(field, value, 500);
        //field.sendKeys(value);
    }
}
