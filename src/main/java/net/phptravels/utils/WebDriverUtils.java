package net.phptravels.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverUtils {

    public static void waitForElement(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElement(WebDriver driver, WebElement el) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(el));
    }

    public static void clickOnElement(WebDriver driver, By by) {
        waitForElement(driver, by);
        driver.findElement(by).click();
    }

    public static void clickOnElement(WebDriver driver, WebElement el) {
        waitForElement(driver, el);
        el.click();
    }
}
