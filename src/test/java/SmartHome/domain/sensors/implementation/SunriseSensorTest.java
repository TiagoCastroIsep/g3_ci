package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.SunriseSensor;
import SmartHome.domain.values.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests to verify the functionality of the {@link SunriseSensor} class.
 * It tests various aspects such as sensor name, sensor functionality, sunrise calculation, and reading retrieval.
 */
class SunriseSensorTest {
    SensorCatalogue sensorCatalogue;
    String SUNRISE_NAME = "SunriseSensor";
    ValueFactory valueFactoryDouble;

    /**
     * Sets up a mock environment for Sunrise Sensor tests.
     * This includes creating mocks for SensorCatalogue and ValueFactory.
     */
    @BeforeEach
    public void setUp() {
        valueFactoryDouble = mock(ValueFactory.class);
        sensorCatalogue = mock(SensorCatalogue.class);
    }

    /**
     * Ensures that attempting to create a Sunrise Sensor with a null SensorCatalogue
     * throws an IllegalArgumentException, enforcing the requirement for a non-null catalogue.
     */
    @Test
    void invalidConstructorWithNullCatalogue() {

        // Arrange
        SensorCatalogue sensorCatalogue = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new SunriseSensor(sensorCatalogue, SUNRISE_NAME, valueFactoryDouble));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());
    }


    /**
     * Confirms that the Sunrise Sensor returns null for its reading
     * when no sunrise time has been calculated, indicating no available data.
     */
    @Test
    void getName() {

        // Arrange
        when(sensorCatalogue.getSensorFunctionality(SensorFunctionality.Sunrise)).thenReturn(SensorFunctionality.Sunrise);
        SunriseSensor sensor = new SunriseSensor(sensorCatalogue, SUNRISE_NAME, valueFactoryDouble);

        // Act
        String name = sensor.getName();

        // Assert
        assertEquals(SUNRISE_NAME, name);
    }

    /**
     * Verifies that the getSensorFunctionality() method returns the expected SensorFunctionality,
     * which should be SensorFunctionality.Sunrise for the SunriseSensor class.
     */
    @Test
    void getSensorFunctionality() {

        // Arrange
        SunriseSensor sensor = new SunriseSensor(sensorCatalogue, SUNRISE_NAME, valueFactoryDouble);

        // Act
        SensorFunctionality functionality = sensor.getSensorFunctionality();

        // Assert
        assertEquals(SensorFunctionality.Sunrise, functionality);
    }

    /**
     * Verifies that the {@code getReading()} method returns the correct sunrise time string after calculating sunrise for a specific date, latitude, and longitude.
     */
    @Test
    void readingToString() {

        // Arrange
        SunriseSensor sensor = new SunriseSensor(sensorCatalogue, SUNRISE_NAME, valueFactoryDouble);
        LocalDate testDate = LocalDate.of(2024, 2, 29); // Data de teste
        double latitude = 41.1579;
        double longitude = -8.6291;
        String currentTime = "07:09:49";

        // Act
        sensor.calculateSunrise(testDate, latitude, longitude);
        String result = sensor.getReading();

        // Assert
        assertEquals(currentTime, result);
    }

    /**
     * Verifies that attempting to retrieve a reading before calculating sunrise returns null.
     */
    @Test
    void invalidReading() {

        // Arrange
        SunriseSensor sensor = new SunriseSensor(sensorCatalogue, SUNRISE_NAME, valueFactoryDouble);

        // Act
        String result = sensor.getReading();

        // Assert
        assertNull(result);
    }

    /**
     * Tests calculating sunrise time for the city of Porto on a specific date.
     * Verifies that the calculated sunrise time matches the expected sunrise time.
     */
    @Test
    void calculateSunriseForPorto() {

        // Arrange
        LocalDate testDate = LocalDate.of(2024, 2, 29); // Data de teste
        double latitude = 41.1579;
        double longitude = -8.6291;
        SunriseSensor calculator = new SunriseSensor(sensorCatalogue, SUNRISE_NAME, valueFactoryDouble);

        // Act
        LocalTime sunriseTime = calculator.calculateSunrise(testDate, latitude, longitude);

        // Assert
        assertNotNull(sunriseTime);
        assertEquals("07:09:49", sunriseTime.toString());
    }
}