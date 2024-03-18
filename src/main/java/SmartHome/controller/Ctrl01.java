package SmartHome.controller;

import SmartHome.domain.house.House;
import SmartHome.domain.house.Location;
import SmartHome.dto.LocationDTO;
import SmartHome.dto.LocationMapper;

/**
 * Controller class for managing location configuration.
 * This class provides methods to configure and retrieve location information.
 */

public class Ctrl01 {
    private final House _house;
    /**
     * Constructs a new Ctrl01 instance with the specified house.
     *
     * @param house the house to which rooms will be added
     */
    public Ctrl01(House house) {
        _house = house;
    }

    /**
     * Configures a new location in the associated house.
     *
     * This method is used to configure a new location.
     * @param street The street of the location.
      * @param doorNumber The door number of the location.
     * @param zipCode The zip code of the location.
     * @param city The city of the location.
     * @param country The country of the location.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return A LocationDTO object representing the newly configured location.
     * The configured location is then converted into a LocationDTO using the LocationMapper class.
     *
     */

    public LocationDTO configureLocation(String street, String doorNumber, String zipCode,
                                         String city, String country,
                                         double latitude, double longitude) {
        Location location = _house.configureLocation(street, doorNumber, zipCode, city, country, latitude, longitude);

        return new LocationMapper().domain2DTO(location);
    }
}
