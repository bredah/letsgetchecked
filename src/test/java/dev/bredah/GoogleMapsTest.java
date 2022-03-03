package dev.bredah;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import dev.bredah.base.BaseWeb;
import dev.bredah.page.google.GoogleMapsPage;
import dev.bredah.utils.Helper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Google")
@Feature("Maps")
class GoogleMapsTest extends BaseWeb {

    private final String dataCountry = "Ireland";
    private final String dataCity = "Dublin";

    @Test
    @Description("Find a country through the name of a city")
    void shouldFindCountry() {
        var googleMap = new GoogleMapsPage();
        googleMap.acceptAgreement();
        googleMap.find(dataCity);

        assertEquals(dataCity, googleMap.getMainInfo());
        assertTrue(Helper.stringContainsItemFromList(dataCountry, googleMap.getMoreInfo()),
                String.format("Not display the country: %s", dataCountry));

        googleMap.getRoute();
        String[] directionToContent = googleMap.getDirectionToContent().split(",");

        assertTrue(Helper.stringContainsItemFromList(dataCountry, directionToContent),
                String.format("Not display the city: %s", dataCity));

        assertTrue(Helper.stringContainsItemFromList(dataCountry, directionToContent),
                String.format("Not display the country: %s", dataCountry));
    }

    @Test
    @Description("Just a scenario that fails to display the screenshot")
    void shouldFindCountryContainsError() {
        var wrongCountryName = "Irelan";
        var googleMap = new GoogleMapsPage();
        googleMap.acceptAgreement();
        googleMap.find(dataCity);

        assertEquals(dataCity, googleMap.getMainInfo());
        assertTrue(Helper.stringContainsItemFromList(wrongCountryName, googleMap.getMoreInfo()),
                String.format("Not display the country: %s", wrongCountryName));
    }
}
