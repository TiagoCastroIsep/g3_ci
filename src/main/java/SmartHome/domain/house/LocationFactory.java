package SmartHome.domain.house;

import SmartHome.domain.house.Location;

/**
 * A factory class responsible for creating instances of the Location class.
 * It provides a method to create a Location object with specified address details and coordinates.
 */
public class LocationFactory {

    /**
     * Creates a new Location object with the given address details and coordinates.
     *
     * @param street      The street name for the location.
     * @param doorNumber  The door number or building number for the location.
     * @param zip         The ZIP or postal code for the location.
     * @param city        The city or locality for the location.
     * @param country     The country for the location.
     * @param latitude    The latitude coordinate for the location.
     * @param longitude   The longitude coordinate for the location.
     * @return A new instance of the Location class.
     */
    public Location createLocation(String street, String doorNumber, String zip, String city, String country, double latitude, double longitude){
        return new Location(street, doorNumber, zip, city, country,latitude, longitude);
    }
}
