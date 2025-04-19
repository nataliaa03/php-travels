package net.phptravels.utils;

import org.openqa.selenium.WebElement;

public class SlowMotionUtils {

    public static void slowClick(WebElement element, int delayMillis) throws InterruptedException {
        element.click();
        Thread.sleep(delayMillis);
    }

    public static void slowSendKeys(WebElement element, String keys, int delayMillis) throws InterruptedException {
        element.sendKeys(keys);
        Thread.sleep(delayMillis);
    }
}