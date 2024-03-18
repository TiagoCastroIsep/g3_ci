package SmartHome.domain.house;

import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.domain.house.GPS;
import SmartHome.domain.house.House;
import SmartHome.domain.house.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for the {@link Location} class.
 * Ensures proper initialization and configuration of Location instances,
 * including validation of input parameters and GPS coordination handling.
 */
class LocationTest {
    private String _street;
    private String _doorNumber;
    private String _zip;
    private String _city;
    private String _country;
    private double _latitude;
    private double _longitude;
    private GPS _gps;

    @BeforeEach
    public void setUp() {
        _street = "Rua Dr. António Bernardino de Almeida";
        _doorNumber = "431";
        _zip = "4200-072";
        _city = "Porto";
        _country = "Portugal";
        _latitude = 41.178553;
        _longitude = -8.608035;
        _gps = new GPS(41.178553, -8.608035);
    }

    //-----------------------ISOLATION TESTS-----------------------------//

    /**
     * Verifies that a valid Location instance can be correctly instantiated with non-null and
     * non-empty parameters.
     */
    @Test
    void whenValidLocation_thenNewLocationInstantiated() {
        // Arrange
        Location locationDouble = mock(Location.class);
        when(locationDouble.getStreet()).thenReturn(_street);
        when(locationDouble.getDoorNumber()).thenReturn(_doorNumber);
        when(locationDouble.getZip()).thenReturn(_zip);
        when(locationDouble.getCity()).thenReturn(_city);
        when(locationDouble.getCountry()).thenReturn(_country);
        when(locationDouble.getGps()).thenReturn(_gps);
        LocationFactory locationFactory = new LocationFactory();
        RoomFactory roomFactory = new RoomFactory();
        House house = new House(locationFactory, roomFactory);
        house.configureLocation(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Act
        Location location = house.getLocation();

        // Assert
        assertEquals(location.getStreet(), _street);
        assertEquals(location.getDoorNumber(), _doorNumber);
        assertEquals(location.getZip(), _zip);
        assertEquals(location.getCity(), _city);
    }

    /**
     * Tests the instantiation of a Location object with valid parameters, checking specifically
     * for correct handling of country and GPS data.
     */
    @Test
    void whenValidLocation_thenNewLocationInstantiatedCountryGPS() {
        // Arrange
        Location locationDouble = mock(Location.class);
        when(locationDouble.getStreet()).thenReturn(_street);
        when(locationDouble.getDoorNumber()).thenReturn(_doorNumber);
        when(locationDouble.getZip()).thenReturn(_zip);
        when(locationDouble.getCity()).thenReturn(_city);
        when(locationDouble.getCountry()).thenReturn(_country);
        when(locationDouble.getGps()).thenReturn(_gps);
        LocationFactory locationFactory = new LocationFactory();
        RoomFactory roomFactory = new RoomFactory();
        House house = new House(locationFactory, roomFactory);
        house.configureLocation(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Act
        Location location = house.getLocation();

        // Assert
        assertEquals(location.getCountry(), _country);
        assertEquals(location.getGps().toString(), _gps.toString());
    }

    /**
     * Validates the behavior of Location configuration when an invalid (null) street parameter is provided.
     */
    @Test
    void whenConfigureLocationWithInvalidStreet_thenReturnsMockLocation() {
        // Arrange
        Location locationDouble = mock(Location.class);
        when(locationDouble.configureLocation(null, _doorNumber, _zip, _city, _country, _latitude, _longitude)).thenReturn(false);

        LocationFactory locationFactory = mock(LocationFactory.class);
        when(locationFactory.createLocation(null, _doorNumber, _zip, _city, _country, _latitude, _longitude)).thenReturn(locationDouble);

        RoomFactory roomFactory = mock(RoomFactory.class);

        House house = new House(locationFactory, roomFactory);

        // Act
        Location result = house.configureLocation(null, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Assert
        assertEquals(locationDouble, result);
    }

    /**
     * Validates the behavior of Location configuration when an invalid (null) door number parameter is provided.
     */
    @Test
    void whenConfigureLocationWithInvalidDoorNumber_thenReturnsMockLocation() {
        // Arrange
        Location locationDouble = mock(Location.class);
        when(locationDouble.configureLocation(_street, null, _zip, _city, _country, _latitude, _longitude)).thenReturn(false);

        LocationFactory locationFactory = mock(LocationFactory.class);
        when(locationFactory.createLocation(_street, null, _zip, _city, _country, _latitude, _longitude)).thenReturn(locationDouble);

        RoomFactory roomFactory = mock(RoomFactory.class);

        House house = new House(locationFactory, roomFactory);

        // Act
        Location result = house.configureLocation(_street, null, _zip, _city, _country, _latitude, _longitude);

        // Assert
        assertEquals(locationDouble, result);
    }

    /**
     * Validates the behavior of Location configuration when an invalid (null) zip parameter is provided.
     */
    @Test
    void whenConfigureLocationWithInvalidZip_thenReturnsMockLocation() {
        // Arrange
        Location locationDouble = mock(Location.class);
        when(locationDouble.configureLocation(_street, _doorNumber, null, _city, _country, _latitude, _longitude)).thenReturn(false);

        LocationFactory locationFactory = mock(LocationFactory.class);
        when(locationFactory.createLocation(_street, _doorNumber, null, _city, _country, _latitude, _longitude)).thenReturn(locationDouble);

        RoomFactory roomFactory = mock(RoomFactory.class);

        House house = new House(locationFactory, roomFactory);

        // Act
        Location result = house.configureLocation(_street, _doorNumber, null, _city, _country, _latitude, _longitude);

        // Assert
        assertEquals(locationDouble, result);
    }

    /**
     * Validates the behavior of Location configuration when an invalid (null) city parameter is provided.
     */
    @Test
    void whenConfigureLocationWithInvalidCity_thenReturnsMockLocation() {
        // Arrange
        Location locationDouble = mock(Location.class);
        when(locationDouble.configureLocation(_street, _doorNumber, _zip, null, _country, _latitude, _longitude)).thenReturn(false);

        LocationFactory locationFactory = mock(LocationFactory.class);
        when(locationFactory.createLocation(_street, _doorNumber, _zip, null, _country, _latitude, _longitude)).thenReturn(locationDouble);

        RoomFactory roomFactory = mock(RoomFactory.class);

        House house = new House(locationFactory, roomFactory);

        // Act
        Location result = house.configureLocation(_street, _doorNumber, _zip, null, _country, _latitude, _longitude);

        // Assert
        assertEquals(locationDouble, result);
    }

    /**
     * Validates the behavior of Location configuration when an invalid (null) country parameter is provided.
     */
    @Test
    void whenConfigureLocationWithInvalidCountry_thenReturnsMockLocation() {
        // Arrange
        Location locationDouble = mock(Location.class);
        when(locationDouble.configureLocation(_street, _doorNumber, _zip, _city, null, _latitude, _longitude)).thenReturn(false);

        LocationFactory locationFactory = mock(LocationFactory.class);
        when(locationFactory.createLocation(_street, _doorNumber, _zip, _city, null, _latitude, _longitude)).thenReturn(locationDouble);

        RoomFactory roomFactory = mock(RoomFactory.class);

        House house = new House(locationFactory, roomFactory);

        // Act
        Location result = house.configureLocation(_street, _doorNumber, _zip, _city, null, _latitude, _longitude);

        // Assert
        assertEquals(locationDouble, result);
    }

    /**
     * Validates that configuring a Location with an out-of-range latitude value correctly
     * results in failure to configure.
     */
    @Test
    void whenConfigureLocationWithInvalidLatitude_thenReturnsMockLocation() {
        // Arrange
        Location locationDouble = mock(Location.class);
        when(locationDouble.configureLocation(_street, _doorNumber, _zip, _city, _country, -200, _longitude)).thenReturn(false);

        LocationFactory locationFactory = mock(LocationFactory.class);
        when(locationFactory.createLocation(_street, _doorNumber, _zip, _city, _country, -200, _longitude)).thenReturn(locationDouble);

        RoomFactory roomFactory = mock(RoomFactory.class);

        House house = new House(locationFactory, roomFactory);

        // Act
        Location result = house.configureLocation(_street, _doorNumber, _zip, _city, _country, -200, _longitude);

        // Assert
        assertEquals(locationDouble, result);
    }

    /**
     * Validates that configuring a Location with an out-of-range longitude value correctly
     * results in failure to configure.
     */
    @Test
    void whenConfigureLocationWithInvalidLongitude_thenReturnsMockLocation() {
        // Arrange
        Location locationDouble = mock(Location.class);
        when(locationDouble.configureLocation(_street, _doorNumber, _zip, _city, _country, _latitude, -200)).thenReturn(false);

        LocationFactory locationFactory = mock(LocationFactory.class);
        when(locationFactory.createLocation(_street, _doorNumber, _zip, _city, _country, _latitude, -200)).thenReturn(locationDouble);

        RoomFactory roomFactory = mock(RoomFactory.class);

        House house = new House(locationFactory, roomFactory);

        // Act
        Location result = house.configureLocation(_street, _doorNumber, _zip, _city, _country, _latitude, -200);

        // Assert
        assertEquals(locationDouble, result);
    }

    /**
     * Tests the successful configuration of a Location instance with valid parameters.
     */
    @Test
    void configureLocationTest() {
        // Arrange

        GPS gpsMock = mock(GPS.class);
        when(gpsMock.configureGps(_latitude, _longitude)).thenReturn(true);

        Location location = new Location(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Act
        boolean result = location.configureLocation(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests the string representation of a Location instance for accuracy.
     */
    @Test
    void toStringTest() {
        // Arrange
        GPS gpsMock = mock(GPS.class);
        when(gpsMock.getLatitude()).thenReturn(_latitude);
        when(gpsMock.getLongitude()).thenReturn(_longitude);

        Location location = new Location(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        String expected = "Location{" + "street='" + _street + '\'' + ", doorNumber='" + _doorNumber + '\'' + ", zip='" + _zip + '\'' + ", city='" + _city + '\'' + ", country='" + _country + '\'' + ", latitude=" + _latitude + ", longitude=" + _longitude + '}';

        // Act
        String result = location.toString();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Ensures that constructing a Location with an invalid (null) street parameter
     * throws an IllegalArgumentException.
     */
    @Test
    void locationConstructorThrowsIllegalArgumentException() {
        // Arrange
        String invalidStreet = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Location(invalidStreet, _doorNumber, _zip, _city, _country, _latitude, _longitude);
        });
    }

    /**
     * Verifies that configuring a Location with invalid street inputs (null, empty, or blank strings)
     * correctly handles and rejects such inputs.
     *
     * @param street The street input to test.
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void configureLocationInvalidStreet(String street) {
        // Arrange
        Location location = new Location(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Act + Assert
        assertFalse(location.configureLocation(street, _doorNumber, _zip, _city, _country, _latitude, _longitude));
    }

    /**
     * Verifies that configuring a Location with invalid door number inputs (null, empty, or blank strings)
     * correctly handles and rejects such inputs.
     *
     * @param doorNumber The door number input to test.
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void configureLocationInvalidDoorNumber(String doorNumber) {
        // Arrange
        Location location = new Location(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Act + Assert
        assertFalse(location.configureLocation(_street, doorNumber, _zip, _city, _country, _latitude, _longitude));
    }

    /**
     * Verifies that configuring a Location with invalid zip inputs (null, empty, or blank strings)
     * correctly handles and rejects such inputs.
     *
     * @param zip The zip input to test.
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void configureLocationInvalidZip(String zip) {
        // Arrange
        Location location = new Location(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Act + Assert
        assertFalse(location.configureLocation(_street, _doorNumber, zip, _city, _country, _latitude, _longitude));
    }

    /**
     * Verifies that configuring a Location with invalid city inputs (null, empty, or blank strings)
     * correctly handles and rejects such inputs.
     *
     * @param city The city input to test.
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void configureLocationInvalidCity(String city) {
        // Arrange
        Location location = new Location(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Act + Assert
        assertFalse(location.configureLocation(_street, _doorNumber, _zip, city, _country, _latitude, _longitude));
    }

    /**
     * Verifies that configuring a Location with invalid country inputs (null, empty, or blank strings)
     * correctly handles and rejects such inputs.
     *
     * @param country The country input to test.
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void configureLocationInvalidCountry(String country) {
        // Arrange
        Location location = new Location(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);

        // Act + Assert
        assertFalse(location.configureLocation(_street, _doorNumber, _zip, _city, country, _latitude, _longitude));
    }

    /**
     * Tests that attempt to configure a Location with an invalid latitude value (outside the valid range)
     * results in the expected failure to update the Location's GPS coordinates.
     * This scenario checks the system's handling of latitude values that are not within the geographic coordinate system's bounds.
     */
    @Test
    void configureGpsInvalidLatitude() {
        // Arrange
        double lat = -91;
        Location location = new Location(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);
        GPS gpsDouble = mock(GPS.class);
        when(gpsDouble.configureGps(lat, _longitude)).thenReturn(false);

        // Act + Assert
        assertFalse(location.configureLocation(_street, _doorNumber, _zip, _city, _country, lat, _longitude));
    }

    /**
     * Tests that attempting to configure a Location with an invalid longitude value (outside the valid range)
     * results in the expected failure to update the Location's GPS coordinates.
     * This scenario ensures that longitude values are validated against the geographic coordinate system's limits.
     */
    @Test
    void configureGpsInvalidLongitude() {
        // Arrange
        double lng = -181;
        Location location = new Location(_street, _doorNumber, _zip, _city, _country, _latitude, _longitude);
        GPS gpsDouble = mock(GPS.class);
        when(gpsDouble.configureGps(_latitude, lng)).thenReturn(false);

        // Act + Assert
        assertFalse(location.configureLocation(_street, _doorNumber, _zip, _city, _country, _latitude, lng));
    }
}