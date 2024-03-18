package SmartHome.domain.house;

import SmartHome.domain.house.GPS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 Tests the GPS class.
 */
class GPSTest {
    GPS gps;
    String errorMessage = "Latitude must be between -90 and 90, and Longitude must be between -180 and 180.";

    /**
     Sets up the GPS object before each test.
     */
    @BeforeEach
    void setUp() {
        // Arrange
        gps = new GPS(41.17, - 8.60);
    }

    /**
     * Verifies that providing to the constructor an invalid latitude value, higher than the accepted range, results in an Exception.
     * This test ensures the GPS class correctly validates latitude constraints.
     */
    @Test
    void invalidHigherLatitude() {
        // Arrange
        double invalidLatitude = 91.0;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GPS(invalidLatitude, - 8.6));
        String actualResult = exception.getMessage();

        // Assert
        assertEquals(errorMessage, actualResult);
    }

    /**
     * Verifies that providing to the constructor an invalid latitude value, lower than the accepted range, results in an Exception.
     * This test ensures the GPS class correctly validates latitude constraints.
     */
    @Test
    void invalidLowerLatitude() {
        // Arrange
        double invalidLatitude = -91.0;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GPS(invalidLatitude, - 8.6));
        String actualResult = exception.getMessage();

        // Assert
        assertEquals(errorMessage, actualResult);
    }

    /**
     * Verifies that providing to the constructor an invalid longitude value, higher than the accepted range, results in an Exception.
     * This test ensures the GPS class correctly validates longitude constraints.
     */
    @Test
    void invalidHigherLongitude() {
        // Arrange
        double invalidLongitude = 181.0;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GPS(41.53, invalidLongitude));
        String actualResult = exception.getMessage();

        // Assert
        assertEquals(errorMessage, actualResult);
    }

    /**
     * Verifies that providing to the constructor an invalid longitude value, lower than the accepted range, results in an Exception.
     * This test ensures the GPS class correctly validates longitude constraints.
     */
    @Test
    void invalidLowerLongitude() {
        // Arrange
        double invalidLongitude = -181.0;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GPS(41.53, invalidLongitude));
        String actualResult = exception.getMessage();

        // Assert
        assertEquals(errorMessage, actualResult);
    }

    /**
     * Tests that the GPS constructor correctly handles boundary latitude values.
     * This test confirms that maximum and minimum valid latitude values are correctly handled by the GPS class.
     */
    @Test
    void verifyLatitudeBoundaries() {
        // Arrange
        String expectedHigherBoundary = "GPS{latitude=90.0, longitude=-8.635}";
        String expectedLowerBoundary = "GPS{latitude=-90.0, longitude=-8.635}";

        // Act
        GPS gpsHigherBoundary = new GPS(90, - 8.635);
        GPS gpsLowerBoundary = new GPS(- 90, - 8.635);

        // Assert
        assertAll(
              () -> assertEquals(expectedHigherBoundary, gpsHigherBoundary.toString()),
              () -> assertEquals(expectedLowerBoundary, gpsLowerBoundary.toString())
        );
    }

    /**
     * Tests that the GPS constructor correctly handles boundary longitude values.
     * This test confirms that maximum and minimum valid longitude values are correctly handled by the GPS class.
     */
    @Test
    void verifyLongitudeBoundary() {
        // Arrange
        String expectedHigherBoundary = "GPS{latitude=41.178553, longitude=180.0}";
        String expectedLowerBoundary = "GPS{latitude=41.178553, longitude=-180.0}";

        // Act
        GPS gpsHigherBoundary = new GPS(41.178553, 180);
        GPS gpsLowerBoundary = new GPS(41.178553, - 180);

        // Assert
        assertAll(
              () -> assertEquals(expectedHigherBoundary, gpsHigherBoundary.toString()),
              () -> assertEquals(expectedLowerBoundary, gpsLowerBoundary.toString())
        );
    }

    /**
     * Verifies that providing an invalid latitude value (like Double.NaN) to the GPS constructor
     * results in an IllegalArgumentException with the expected error message.
     * This test ensures the GPS class correctly validates latitude constraints.
     */
    @Test
    void invalidDoubleNaNLatitude() {
        // Arrange
        double invalidLatitude = Double.NaN;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GPS(invalidLatitude, -8.6));
        String actualResult = exception.getMessage();

        // Assert
        assertEquals(errorMessage, actualResult);
    }

    /**
     * Verifies that providing an invalid latitude value (like Double.NaN) to the GPS constructor
     * results in an IllegalArgumentException with the expected error message.
     * This test ensures the GPS class correctly validates latitude constraints.
     */
    @Test
    void invalidDoubleNaNLongitude() {
        // Arrange
        double invalidLongitude = Double.NaN;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GPS(41.755, invalidLongitude));
        String actualResult = exception.getMessage();

        // Assert
        assertEquals(errorMessage, actualResult);
    }

    /**
     * Tests the getLatitude() method of GPS to ensure it returns the correct latitude.
     */
    @Test
    void getValidLatitude() {
        // Arrange
        double latitude = 41.17;

        // Act
        double result = gps.getLatitude();

        // Assert
        assertEquals(latitude, result);
    }

    /**
     * Tests the getLongitude() method of GPS to ensure it returns the correct longitude.
     */
    @Test
    void getValidLongitude() {
        // Arrange
        double longitude = - 8.60;
        // Act
        double result = gps.getLongitude();

        // Assert
        assertEquals(longitude, result);
    }

    /**
     * Verifies that the configureGps method correctly configures the GPS object with valid latitude and longitude values.
     * configureGPS should return true.
     */
    @Test
    void configureGpsWithBothValidArguments() {
        // Arrange
        double latitude = 41.17;
        double longitude = -8.60;

        // Act
        boolean result = gps.configureGps(latitude, longitude);

        // Assert
        assertTrue(result);
    }

    /**
     * Verifies that the configureGps method returns false when provided with both invalid latitude and longitude values.
     */
    @Test
    void configureGpsWithBothInvalidArguments() {
        // Arrange
        double latitude = 441.17;
        double longitude = -258.60;

        // Act
        boolean result = gps.configureGps(latitude, longitude);

        // Assert
        assertFalse(result);
    }

    /**
     * Verifies that the configureGps method returns false if provided with an invalid latitude.
     */
    @Test
    void configureGpsWithInvalidLatitude() {
        // Arrange
        double latitude = 441.17;
        double longitude = -8.60;

        // Act
        boolean result = gps.configureGps(latitude, longitude);

        // Assert
        assertFalse(result);
    }

    /**
     * Verifies that the configureGps method returns false if provided with an invalid longitude.
     */
    @Test
    void configureGpsWithInvalidLongitude() {
        // Arrange
        double latitude = 41.17;
        double longitude = -258.60;

        // Act
        boolean result = gps.configureGps(latitude, longitude);

        // Assert
        assertFalse(result);
    }
}
