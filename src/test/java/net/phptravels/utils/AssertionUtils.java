package net.phptravels.utils;

import org.awaitility.Durations;
import org.openqa.selenium.WebElement;

import static org.awaitility.Awaitility.await;
import static org.testng.AssertJUnit.assertEquals;

public class AssertionUtils {
    public static void assertElementHasAttribute(WebElement el, String attr) {
        await()
                .pollInSameThread()
                .pollInterval(Durations.ONE_SECOND)
                .atMost(Durations.TWO_SECONDS)
                .untilAsserted(() -> {
                    assertEquals("true", el.getAttribute(attr));
                });
    }
}
