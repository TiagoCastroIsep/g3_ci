package SmartHome.dto;

import SmartHome.domain.house.GPSFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GpsDTOTest {

    /**
     * Tests the getLongitude() method of GpsDTO to ensure it returns the correct latitude.
     */
    @Test
    void getValidLatitude() {
        // Arrange
        double expectedLatitude = 41.17;
        double longitude = - 8.60;
        GpsDTO gpsDTO = new GpsDTO(new GPSFactory().createGPS(expectedLatitude, longitude));

        // Act
        double actualLatitude = gpsDTO.latitude;

        // Assert
        assertEquals(expectedLatitude, actualLatitude);
    }

    /**
     * Tests the getLongitude() method of GpsDTO to ensure it returns the correct longitude.
     */
    @Test
    void getValidLongitude() {
        // Arrange
        double latitude = 41.17;
        double expectedLongitude = - 8.60;
        GpsDTO gpsDTO = new GpsDTO(new GPSFactory().createGPS(latitude, expectedLongitude));

        // Act
        double actualLongitude = gpsDTO.longitude;

        // Assert
        assertEquals(expectedLongitude, actualLongitude);
    }

    /**
     * Verifies if the toString() methos returns the correct values for both latitude and longitude.
     */
    @Test
    void DtoToString() {
        // Arrange
        double latitude = 12.0;
        double longitude = 22.0;

        // Act
        GpsDTO gpsDTO = new GpsDTO(new GPSFactory().createGPS(latitude, longitude));
        String expected = "GpsDTO{latitude=12.0, longitude=22.0}";

        // Assert
        assertEquals(expected, gpsDTO.toString());
    }
}
