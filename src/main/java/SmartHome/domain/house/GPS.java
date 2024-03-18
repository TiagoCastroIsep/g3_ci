package SmartHome.domain.house;

/**
 * Represents a geographical location defined by latitude and longitude coordinates. This class ensures
 * coordinates are within global bounds for latitude (-90 to 90 degrees) and longitude (-180 to 180 degrees).
 * It provides methods to retrieve and update the GPS coordinates, ensuring values remain valid.
 */
public class GPS {
    private double _latitude;
    private double _longitude;

    /**
     * Constructs a GPS instance with specified latitude and longitude, ensuring both values are within
     * valid geographical bounds. Validates the initial coordinates to prevent creation of an instance
     * with invalid global positions.
     *
     * @param latitude The latitude of the GPS location, should be in the range of -90 to 90 degrees.
     * @param longitude The longitude of the GPS location, should be in the range of -180 to 180 degrees.
     * @throws IllegalArgumentException if either latitude or longitude are out of their valid range,
     *         indicating an attempt to set coordinates outside the acceptable global positions, or if
     *         either value is not a number (NaN).
     */
    public GPS(double latitude, double longitude) {
        if (! validateArguments(latitude, longitude))
            throw new IllegalArgumentException("Latitude must be between -90 and 90, and Longitude must be between -180 and 180.");
        this._latitude = latitude;
        this._longitude = longitude;
    }

    /**
     * Validates the provided latitude and longitude to ensure they fall within the acceptable global
     * bounds for GPS coordinates. Additionally checks for NaN values to ensure numerical validity.
     *
     * @param latitude The latitude to validate, must be between -90 and 90 degrees inclusive.
     * @param longitude The longitude to validate, must be between -180 and 180 degrees inclusive.
     * @return true if both latitude and longitude are within valid ranges and are not NaN; false otherwise.
     */
    private boolean validateArguments(double latitude, double longitude) {
        if (latitude < - 90 || latitude > 90) return false;
        if (longitude < - 180 || longitude > 180) return false;
        if (Double.isNaN(longitude) || Double.isNaN(latitude)) return false;
        return true;
    }

    /**
     * Retrieves the current latitude of this GPS location.
     *
     * @return The latitude value of this GPS instance.
     */
    public double getLatitude() {
        return _latitude;
    }

    /**
     * Retrieves the current longitude of this GPS location.
     *
     * @return The longitude value of this GPS instance.
     */
    public double getLongitude() {
        return _longitude;
    }

    /**
     * Configures the GPS location with a new set of latitude and longitude values. Validates the new coordinates
     * to ensure they remain within global bounds. This method allows for dynamic updates to the GPS position
     * while maintaining integrity of the geographical data.
     *
     * @param latitude The new latitude value to set, should be in the range of -90 to 90 degrees.
     * @param longitude The new longitude value to set, should be in the range of -180 to 180 degrees.
     * @return true if the GPS location is successfully updated with valid coordinates; false if the provided
     *         values are out of range or not valid numbers, leaving the original coordinates unchanged.
     */
    public boolean configureGps(double latitude, double longitude) {
        if (! validateArguments(latitude, longitude)) return false;
        _latitude = latitude;
        _longitude = longitude;
        return true;
    }

    /**
     * Provides a string representation of the GPS instance, formatting latitude and longitude coordinates
     * in a readable manner. This can be useful for logging, debugging, or displaying the GPS location.
     *
     * @return A string in the format "GPS{latitude=<latitude>, longitude=<longitude>}" representing the
     *         current GPS coordinates.
     */
    @Override
    public String toString() {
        return "GPS{" +
              "latitude=" + _latitude +
              ", longitude=" + _longitude +
              '}';
    }
}