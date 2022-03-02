package dev.bredah;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import dev.bredah.page.google.GoogleMapsPage;
import dev.bredah.utils.Helper;

public class GoogleMapsTest extends BaseWeb {


    @Test
    public void shouldFindCountry() {
        var googleMap = new GoogleMapsPage();
        googleMap.acceptAgreement();
        googleMap.find("Dublin");

        assertEquals("Dublin", googleMap.getMainInfo());
        assertTrue(
            Helper.stringContainsItemFromList("Ireland", googleMap.getMoreInfo()),
            String.format("Not display the country: %s", "Ireland"));
    }
}
