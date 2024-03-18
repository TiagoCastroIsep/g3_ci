package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.DewPointSensor;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.CelsiusValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the DewPointSensor class.
 * It tests the constructor, getName, getMeasurementUnit, getReading, and getReadings methods.
 * It also tests the constructor's behavior when given null or empty parameters.
 */
class DewPointSensorTest {
    SensorCatalogue mockCatalogue;
    final String SENSOR_NAME = "Dew Point Sensor";
    ValueFactory mockFactory;
    CelsiusValue mockCelsiusValue;

    /**
     * This method is executed before each test.
     * It sets up the mock objects for the SensorCatalogue, ValueFactory, and CelsiusValue.
     */
    @BeforeEach
    void setUp() {
        mockCatalogue = mock(SensorCatalogue.class);
        mockFactory = mock(ValueFactory.class);
        mockCelsiusValue = mock(CelsiusValue.class);
    }

    /**
     * Tests the constructor of the DewPointSensor class with valid parameters.
     * It verifies that the sensor is correctly created with the expected functionality.
     */
    @Test
    void validSensorCreation() {
        // Arrange
        SensorFunctionality sensorFunctionality = SensorFunctionality.DewPoint;
        when(mockCatalogue.getSensorFunctionality(sensorFunctionality)).thenReturn(sensorFunctionality);

        //Act
        DewPointSensor dewPointSensor = new DewPointSensor(mockCatalogue, SENSOR_NAME, mockFactory);

        // Assert
        assertEquals(sensorFunctionality, dewPointSensor.getSensorFunctionality());
    }

    /**
     * Tests the getName method of the DewPointSensor class.
     * It verifies that the method correctly returns the name of the sensor.
     */
    @Test
    void validSensorName() {
        // Arrange
        DewPointSensor dewPointSensor = new DewPointSensor(mockCatalogue, SENSOR_NAME, mockFactory);

        // Act
        String result = dewPointSensor.getName();

        // Assert
        assertEquals(SENSOR_NAME, result);
    }

    /**
     * Tests the constructor of the DewPointSensor class with null catalogue.
     * It verifies that the constructor correctly throws an IllegalArgumentException with the message "Catalogue cannot be null".
     */
    @Test
    void invalidConstructorWithNullCatalogue() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new DewPointSensor(null, SENSOR_NAME, mockFactory)
        );

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());
    }

    /**
     * Tests the constructor of the DewPointSensor class with null name.
     * It verifies that the constructor correctly throws an IllegalArgumentException with the message "Name cannot be null or empty".
     */
    @Test
    void invalidConstructorWithNullName() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new DewPointSensor(mockCatalogue, null, mockFactory)
        );

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the constructor of the DewPointSensor class with an empty name.
     * It verifies that the constructor correctly throws an IllegalArgumentException with the message "Name cannot be null or empty".
     */
    @Test
    void invalidConstructorWithEmptyName() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new DewPointSensor(mockCatalogue, "   ", mockFactory)
        );

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the constructor of the DewPointSensor class with null ValueFactory.
     * It verifies that the constructor correctly throws an IllegalArgumentException with the message "ValueFactory cannot be null".
     */
    @Test
    void invalidConstructorWithNullFactory() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new DewPointSensor(mockCatalogue, SENSOR_NAME, null)
        );

        // Assert
        assertEquals("ValueFactory cannot be null", exception.getMessage());
    }

    /**
     * Tests the getMeasurementUnit method of the DewPointSensor class.
     * It verifies that the method correctly returns the measurement unit of the sensor.
     */
    @Test
    void getMeasurementUnitTest() {
        // Arrange
        when(mockFactory.createCelsiusTemperature()).thenReturn(mockCelsiusValue);
        when(mockCelsiusValue.getMeasurementUnit()).thenReturn("ºC");
        DewPointSensor dewPointSensor = new DewPointSensor(mockCatalogue, SENSOR_NAME, mockFactory);
        String expected = "ºC";

        // Act
        String actual = dewPointSensor.getMeasurementUnit();

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the getReading method of the DewPointSensor class.
     * It verifies that the method correctly returns the reading of the sensor.
     */
    @Test
    void getReading() {
        // Arrange
        when(mockFactory.createCelsiusTemperature()).thenReturn(mockCelsiusValue);
        when(mockCelsiusValue.toString()).thenReturn("10.0");
        DewPointSensor dewPointSensor = new DewPointSensor(mockCatalogue, SENSOR_NAME, mockFactory);
        String expected = "10.0";

        // Act
        String actual = dewPointSensor.getReading();

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the getReading method of the DewPointSensor class when no readings are available.
     * It verifies that the method correctly returns null.
     */
    @Test
    void noReadingsAvailable() {
        // Arrange
        DewPointSensor dewPointSensor = new DewPointSensor(mockCatalogue, SENSOR_NAME, mockFactory);
        when(mockFactory.createCelsiusTemperature()).thenReturn(mockCelsiusValue);
        when(mockCelsiusValue.toString()).thenReturn(null);

        // Act
        String result = dewPointSensor.getReading();

        // Assert
        assertNull(result);
    }

    /**
     * Tests the getReading method of the DewPointSensor class.
     * It verifies that the method correctly returns the reading of the sensor in Celsius.
     */
    @Test
    void getReadings() {
        // Arrange
        DewPointSensor dewPointSensor = new DewPointSensor(mockCatalogue, SENSOR_NAME, mockFactory);
        when(mockFactory.createCelsiusTemperature()).thenReturn(mockCelsiusValue);
        when(mockCelsiusValue.toString()).thenReturn("12ºC");

        // Act
        String result = dewPointSensor.getReading();

        // Assert
        assertEquals("12ºC", result);
    }
}