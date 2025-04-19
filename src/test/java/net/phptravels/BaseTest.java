package net.phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl = "https://phptravels.net";
    protected String urlGrid = "http://localhost:4444/wd/hub";

    @BeforeMethod
    protected void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--no-first-run");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--user-data-dir=/tmp/selenium-profile-" + UUID.randomUUID());

        driver = new RemoteWebDriver(new URL(urlGrid), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseUrl);
    }


    @AfterMethod
    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
