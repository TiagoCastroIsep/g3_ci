package SmartHome.controller;

import SmartHome.domain.house.House;
import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.dto.LocationDTO;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link Ctrl01} focusing on the configuration of house locations.
 * Each test verifies correct handling of location configuration under various conditions.
 */
class Ctrl01Test {
    LocationFactory locationFactory;
    RoomFactory roomFactory;

    /**
     * Test for configuring a valid house location.
     * This test checks if the method 'configureLocation' correctly configures a valid house location.
     */
    @Test
    void validHouseLocation() {
        // Arrange
        locationFactory = new LocationFactory();
        roomFactory = new RoomFactory();
        House house = new House(locationFactory, roomFactory);
        Ctrl01 ctrl01 = new Ctrl01(house);
        double latitude = 41.178553;
        double longitude = -8.608035;

        // Act
        LocationDTO locationDTO = ctrl01.configureLocation("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", latitude, longitude);

        // Assert
        assertEquals("Rua Dr. António Bernardino de Almeida", locationDTO._street);
        assertEquals("431", locationDTO._doorNumber);
        assertEquals("4200-072", locationDTO._zip);
        assertEquals("Porto", locationDTO._city);
        assertEquals("Portugal", locationDTO._country);
        assertEquals(41.178553, locationDTO._gps.latitude);
        assertEquals(-8.608035, locationDTO._gps.longitude);
    }

    /**
     * Test for configuring a house location with an empty street.
     * This test checks if the method 'configureLocation' throws an IllegalArgumentException when an empty street is passed.
     */
    @Test
    void emptyStreetHouseLocation() {
        // Arrange
        locationFactory = new LocationFactory();
        roomFactory = new RoomFactory();
        House house = new House(locationFactory, roomFactory);
        Ctrl01 ctrl01 = new Ctrl01(house);
        double latitude = 41.178553;
        double longitude = -8.608035;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ctrl01.configureLocation("", "431", "4200-072", "Porto", "Portugal", latitude, longitude));

        // Assert
        String expectedMessage = "Invalid arguments passed to constructor";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for configuring a house location with an empty door number.
     * This test checks if the method 'configureLocation' throws an IllegalArgumentException when an empty door number is passed.
     */
    @Test
    void emptyDoorNumberHouseLocation() {
        // Arrange
        locationFactory = new LocationFactory();
        roomFactory = new RoomFactory();
        House house = new House(locationFactory, roomFactory);
        Ctrl01 ctrl01 = new Ctrl01(house);
        double latitude = 41.178553;
        double longitude = -8.608035;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ctrl01.configureLocation("Rua Dr. António Bernardino de Almeida", "", "4200-072", "Porto", "Portugal", latitude, longitude));

        // Assert
        String expectedMessage = "Invalid arguments passed to constructor";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for configuring a house location with an empty zip code.
     * This test checks if the method 'configureLocation' throws an IllegalArgumentException when an empty zip code is passed.
     */
    @Test
    void emptyZipHouseLocation() {
        // Arrange
        locationFactory = new LocationFactory();
        roomFactory = new RoomFactory();
        House house = new House(locationFactory, roomFactory);
        Ctrl01 ctrl01 = new Ctrl01(house);
        double latitude = 41.178553;
        double longitude = -8.608035;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ctrl01.configureLocation("Rua Dr. António Bernardino de Almeida", "431", "", "Porto", "Portugal", latitude, longitude));

        // Assert
        String expectedMessage = "Invalid arguments passed to constructor";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for configuring a house location with an empty city.
     * This test checks if the method 'configureLocation' throws an IllegalArgumentException when an empty city is passed.
     */
    @Test
    void emptyCityHouseLocation() {
        // Arrange
        locationFactory = new LocationFactory();
        roomFactory = new RoomFactory();
        House house = new House(locationFactory, roomFactory);
        Ctrl01 ctrl01 = new Ctrl01(house);
        double latitude = 41.178553;
        double longitude = -8.608035;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ctrl01.configureLocation("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "", "Portugal", latitude, longitude));

        // Assert
        String expectedMessage = "Invalid arguments passed to constructor";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for configuring a house location with a null country.
     * This test checks if the method 'configureLocation' throws an IllegalArgumentException when a null country is passed.
     */
    @Test
    void nullCountryHouseLocation() {
        // Arrange
        locationFactory = new LocationFactory();
        roomFactory = new RoomFactory();
        House house = new House(locationFactory, roomFactory);
        Ctrl01 ctrl01 = new Ctrl01(house);
        double latitude = 41.178553;
        double longitude = -8.608035;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ctrl01.configureLocation("Rua Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", null, latitude, longitude));

        // Assert
        String expectedMessage = "Invalid arguments passed to constructor";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for configuring a house location with a null GPS latitude.
     * This test checks if the method 'configureLocation' throws an IllegalArgumentException when a null GPS latitude is passed.
     */
    @Test
    void nullGpsHouseLocation1() {
        // Arrange
        locationFactory = new LocationFactory();
        roomFactory = new RoomFactory();
        House house = new House(locationFactory, roomFactory);
        Ctrl01 ctrl01 = new Ctrl01(house);
        double latitude = NaN;
        double longitude = -8.608035;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ctrl01.configureLocation("Rua Dr. António Bernardino de Almeida", "431",
                        "4200-072", "Porto", "Portugal", latitude, longitude));

        // Assert
        assertInstanceOf(IllegalArgumentException.class, exception);
    }
}