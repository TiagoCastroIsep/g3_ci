package SmartHome.domain.house;

import SmartHome.domain.house.GPS;

/**
 * A factory class responsible for creating instances of the GPS class.
 * It provides a method to create a GPS object with specified latitude and longitude coordinates.
 */
public class GPSFactory {

    /**
     * Creates a new GPS object with the given latitude and longitude coordinates.
     *
     * @param latitude  The latitude coordinate for the GPS location.
     * @param longitude The longitude coordinate for the GPS location.
     * @return A new instance of the GPS class.
     */
    public GPS createGPS(double latitude, double longitude){
        return new GPS(latitude, longitude);
    }
}
