package SmartHome.domain.house;

/**
 * Represents a geographic location with detailed address information and GPS coordinates.
 * Allows for the creation and dynamic updating of location details, ensuring all data conforms
 * to expected formats and ranges.
 */
public class Location {
    private String _street;
    private String _doorNumber;
    private String _zip;
    private String _city;
    private String _country;
    private final GPS _gps;

    /**
     * Constructs a new Location instance with specified address and GPS details. Each parameter
     * is validated for correctness, such as non-null and non-empty strings for address components,
     * and valid ranges for latitude (-90 to 90) and longitude (-180 to 180).
     *
     * @param street     The street name of the location.
     * @param doorNumber The door number at the specified street.
     * @param zip        The postal zip code of the location.
     * @param city       The city in which the location is situated.
     * @param country    The country of the location.
     * @param latitude   The latitude coordinate, must be between -90 and 90.
     * @param longitude  The longitude coordinate, must be between -180 and 180.
     * @throws IllegalArgumentException If any parameters are invalid, including null or empty strings, or GPS coordinates out of range.
     */
    public Location(String street, String doorNumber, String zip, String city, String country, double latitude, double longitude) {
        if (! validateArguments(street, doorNumber, zip, city, country))
            throw (new IllegalArgumentException("Invalid arguments passed to constructor."));
        this._street = street;
        this._doorNumber = doorNumber;
        this._zip = zip;
        this._city = city;
        this._country = country;
        this._gps = new GPSFactory().createGPS(latitude, longitude);
    }

    /**
     * Validates the constructor arguments to ensure they conform to the expected values and constraints.
     *
     * @param street     The street name to validate.
     * @param doorNumber The door number to validate.
     * @param zip        The zip code to validate.
     * @param city       The city name to validate.
     * @param country    The country name to validate.
     * @throws IllegalArgumentException If any validation fails due to invalid input.
     */
    private boolean validateArguments(String street, String doorNumber, String zip, String city, String country) {
        if (street == null || street.trim().isEmpty()) return false;
        if (doorNumber == null || doorNumber.trim().isEmpty()) return false;
        if (zip == null || zip.trim().isEmpty()) return false;
        if (city == null || city.trim().isEmpty()) return false;
        if (country == null || country.trim().isEmpty()) return false;
        return true;
    }

    /**
     * Retrieves the street name of this location.
     *
     * @return The street name as a String.
     */
    public String getStreet() {
        return this._street;
    }

    /**
     * Retrieves the door number of this location's address.
     *
     * @return The door number as an integer.
     */
    public String getDoorNumber() {
        return this._doorNumber;
    }

    /**
     * Retrieves the postal zip code of this location.
     *
     * @return The zip code as a String.
     */
    public String getZip() {
        return this._zip;
    }

    /**
     * Retrieves the name of the city where this location is situated.
     *
     * @return The city name as a String.
     */
    public String getCity() {
        return this._city;
    }

    /**
     * Retrieves the name of the country where this location is situated.
     *
     * @return The country name as a String.
     */
    public String getCountry() {
        return this._country;
    }

    /**
     * Retrieves the GPS coordinates of this location.
     *
     * @return A GPS instance representing the location's coordinates.
     */
    public GPS getGps() {
        return _gps;
    }

    /**
     * Updates the location's address and GPS coordinates. Validates new input in the same manner as the constructor.
     * Returns true if the update is successful, false if any validation fails.
     *
     * @param street     The new street name.
     * @param doorNumber The new door number.
     * @param zipCode    The new zip code.
     * @param city       The new city.
     * @param country    The new country.
     * @param latitude   The new latitude, must be between -90 and 90.
     * @param longitude  The new longitude, must be between -180 and 180.
     * @return true if the location details are successfully updated, false if any validation check fails.
     */
    public boolean configureLocation(String street, String doorNumber, String zipCode, String city, String country, double latitude, double longitude) {
        if (! validateArguments(street, doorNumber, zipCode, city, country)) return false;
        if (! _gps.configureGps(latitude, longitude)) return false;
        this._street = street;
        this._doorNumber = doorNumber;
        this._zip = zipCode;
        this._city = city;
        this._country = country;
        _gps.configureGps(latitude, longitude);
        return true;
    }

    /**
     * Returns a string representation of the Location object. This representation includes
     * the street, door number, zip code, city, country, and GPS coordinates (latitude and longitude)
     * of the location.
     *
     * @return A string that represents the location, formatted as:
     * "Location{street='[street]', doorNumber='[doorNumber]', zip='[zip]', city='[city]', country='[country]', latitude=[latitude], longitude=[longitude]}".
     */
    @Override
    public String toString() {
        return "Location{" + "street='" + _street + '\'' + ", doorNumber='" + _doorNumber + '\'' + ", zip='" + _zip + '\'' + ", city='" + _city + '\'' + ", country='" + _country + '\'' + ", latitude=" + _gps.getLatitude() + ", longitude=" + _gps.getLongitude() + '}';
    }
}