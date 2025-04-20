package net.phptravels.utils;

import org.openqa.selenium.WebElement;

public class SlowMotionUtils {

    public static void slowClick(WebElement element, int delayMillis) {
        try {
            element.click();
            Thread.sleep(delayMillis);
        } catch (InterruptedException ignored) {
            System.out.println("Clicking interrupted.");
        }
    }

    public static void slowSendKeys(WebElement element, String keys, int delayMillis) {
        try {
            element.sendKeys(keys);
            Thread.sleep(delayMillis);
        } catch (InterruptedException ignored) {
            System.out.println("Typing interrupted.");
        }

    }
}