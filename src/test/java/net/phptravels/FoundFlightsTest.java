package net.phptravels;

import lombok.extern.slf4j.Slf4j;
import net.phptravels.pages.FoundFlightsPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

@Slf4j
public class FoundFlightsTest extends FlightsCommon {
    @Test
    public void shouldResultsShowCorrectData() {
        var flights = searchBasicFlight();
        if (flights != null) {
            FoundFlightsPage flightsPage = new FoundFlightsPage(driver);
            int actualFlightsFoundCount = flightsPage.getFlightsCount();
            String cityFrom = flightsPage.getFlightFromCity(0);
            String cityTo = flightsPage.getFlightToCity(0);
            String tripDuration = flightsPage.getTripDuration(0);
            String airLine1 = flightsPage.getFlightAirline(0, 0);
            String airLine2 = flightsPage.getFlightAirline(0, 1);

            SoftAssertions assertions = new SoftAssertions();

            assertions.assertThat(actualFlightsFoundCount).isGreaterThan(0);
            assertions.assertThat(cityFrom).isEqualTo(getFromCity1Code());
            assertions.assertThat(cityTo).isEqualTo(getToCity1Code());
            assertions.assertThat(tripDuration).matches("Trip Duration: \\d{1,2}:\\d{2}\nLayover time: \\d+h \\d+m");
            assertions.assertThat(airLine1).matches("[A-Za-z ]+\\RFlight No\\. \\d+ - [A-Z]{3} - [A-Z]{3}");
            assertions.assertThat(airLine2).matches("[A-Za-z ]+\\RFlight No\\. \\d+ - [A-Z]{3} - [A-Z]{3}");

            assertions.assertAll();
        } else {
            log.info("FLights not found. Test skipped.");
        }
    }
}
