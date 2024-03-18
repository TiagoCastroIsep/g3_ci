package SmartHome.dto;

import SmartHome.domain.house.GPS;
import SmartHome.domain.house.Location;
import SmartHome.domain.house.GPSFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class represents a set of unit tests for the LocationDTO class.
 */
class LocationDTOTest {
    private Location location; // The Location object used for testing.
    private GPS gps; // The GPS object used for testing.

    /**
     * Sets up the testing environment before each test method is executed.
     */
    @BeforeEach
    void setUp() {
        location = new Location("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", 41.178553, -8.608035);
        gps = new GPSFactory().createGPS(41.178553, -8.608035);
    }

    /**
     * Tests the retrieval of street information from LocationDTO.
     */
    @Test
    void testGetStreet() {
        // Act
        LocationDTO locationDTO = new LocationDTO(location, gps);
        String result = locationDTO._street;

        // Assert
        assertEquals(location.getStreet(), result);
    }

    /**
     * Tests the retrieval of door number information from LocationDTO.
     */
    @Test
    void testGetDoorNumber() {
        // Act
        LocationDTO locationDTO = new LocationDTO(location, gps);
        String result = locationDTO._doorNumber;

        // Assert
        assertEquals(location.getDoorNumber(), result);
    }

    /**
     * Tests the retrieval of zip code information from LocationDTO.
     */
    @Test
    void testGetZip() {
        // Act
        LocationDTO locationDTO = new LocationDTO(location, gps);
        String result = locationDTO._zip;

        // Assert
        assertEquals(location.getZip(), result);
    }

    /**
     * Tests the retrieval of city information from LocationDTO.
     */
    @Test
    void testGetCity() {
        // Act
        LocationDTO locationDTO = new LocationDTO(location, gps);
        String result = locationDTO._city;

        // Assert
        assertEquals(location.getCity(), result);
    }

    /**
     * Tests the retrieval of country information from LocationDTO.
     */
    @Test
    void testGetCountry() {
        // Act
        LocationDTO locationDTO = new LocationDTO(location, gps);
        String result = locationDTO._country;

        // Assert
        assertEquals(location.getCountry(), result);
    }

    /**
     * Tests the retrieval of latitude information from LocationDTO.
     */
    @Test
    void testGetLatitude() {
        // Act
        LocationDTO locationDTO = new LocationDTO(location, gps);
        double result = locationDTO._gps.latitude;

        // Assert
        assertEquals(gps.getLatitude(), result);
    }

    /**
     * Tests the retrieval of longitude information from LocationDTO.
     */
    @Test
    void testGetLongitude() {
        // Act
        LocationDTO locationDTO = new LocationDTO(location, gps);
        double result = locationDTO._gps.longitude;

        // Assert
        assertEquals(gps.getLongitude(), result);
    }

    /**
     * Tests the generation of a string representation of LocationDTO.
     */
    @Test
    void testToString() {
        // Arrange
        LocationDTO locationDTO = new LocationDTO(location, gps);
        String expectedResult = "LocationDTO{" +
                "_street='" + location.getStreet() + '\'' +
                ", _doorNumber='" + location.getDoorNumber() + '\'' +
                ", _zip='" + location.getZip() + '\'' +
                ", _city='" + location.getCity() + '\'' +
                ", _country='" + location.getCountry() + '\'' +
                ", _latitude=" + gps.getLatitude() +
                ", _longitude=" + gps.getLongitude() +
                '}';

        // Act
        String result = locationDTO.toString();

        // Assert
        assertEquals(expectedResult, result);
    }
}