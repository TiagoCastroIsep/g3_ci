package SmartHome.dto;

import SmartHome.domain.house.GPS;
import SmartHome.domain.house.Location;
import SmartHome.domain.house.GPSFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Javadoc for LocationMapperTest class.
 */
class LocationMapperTest {

    private LocationMapper locationMapper;
    private Location location;
    private GPS gps;
    private Map<Location, GPS> locations;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        locationMapper = new LocationMapper();
        location = new Location("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", 41.178553, -8.608035);
        gps = new GPSFactory().createGPS(41.178553, -8.608035);
        locations = new HashMap<>();
        locations.put(location, gps);
    }

    /**
     * Test method to check the mapping of street, door number, and zip code from domain to DTO.
     */
    @Test
    public void testDomain2DTOStreetDoorNumberZip() {
        // Act
        LocationDTO result = locationMapper.domain2DTO(location);

        // Assert
        assertEquals(location.getStreet(), result._street);
        assertEquals(location.getDoorNumber(), result._doorNumber);
        assertEquals(location.getZip(), result._zip);
    }

    /**
     * Test method to check the mapping of city, country, latitude, and longitude from domain to DTO.
     */
    @Test
    public void testDomain2DTOCityCountryLatLong() {
        // Act
        LocationDTO result = locationMapper.domain2DTO(location);

        // Assert
        assertEquals(location.getCity(), result._city);
        assertEquals(location.getCountry(), result._country);
        assertEquals(gps.getLatitude(), result._gps.latitude);
        assertEquals(gps.getLongitude(), result._gps.longitude);
    }

    /**
     * Test method to check the behavior when a null Location object is provided for mapping.
     */
    @Test
    public void testDomain2DTOWithNullLocation() {
        // Act
        Location newLocation = null;
        LocationDTO result = locationMapper.domain2DTO(newLocation);

        // Assert
        assertNull(result);
    }

    /**
     * Test method to check the mapping of street, door number, and zip code from domain to DTO when provided with a map.
     */
    @Test
    public void testDomain2DTOWithMapStreetDoorNumberZip() {
        //Arrange
        Map<LocationDTO, Location> locationMap = LocationMapper.domain2DTO(locations);

        // Act
        LocationDTO locationDTO = locationMap.keySet().iterator().next();

        // Assert
        assertEquals(location.getStreet(), locationDTO._street);
        assertEquals(location.getDoorNumber(), locationDTO._doorNumber);
        assertEquals(location.getZip(), locationDTO._zip);
    }

    /**
     * Test method to check the mapping of city, country, latitude, and longitude from domain to DTO when provided with a map.
     */
    @Test
    public void testDomain2DTOWithMapCityCountryLatLong() {
        //Arrange
        Map<LocationDTO, Location> locationMap = LocationMapper.domain2DTO(locations);

        // Act
        LocationDTO locationDTO = locationMap.keySet().iterator().next();

        // Assert
        assertEquals(location.getCity(), locationDTO._city);
        assertEquals(location.getCountry(), locationDTO._country);
        assertEquals(gps.getLatitude(), locationDTO._gps.latitude);
        assertEquals(gps.getLongitude(), locationDTO._gps.longitude);
    }
}

