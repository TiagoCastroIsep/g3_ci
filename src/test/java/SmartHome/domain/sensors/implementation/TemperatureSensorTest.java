package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.TemperatureSensor;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.CelsiusValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the TemperatureSensor class.
 * It tests the functionality of the TemperatureSensor class methods such as sensor creation,
 * setting and getting the sensor name, getting the measurement unit, and getting sensor readings.
 * It uses the JUnit and Mockito frameworks for testing and mocking dependencies.
 */
class TemperatureSensorTest {
    private ValueFactory valueFactoryDouble;

    /**
     * This method is executed before each test.
     * It sets up the necessary mocks for the tests.
     */
    @BeforeEach
    void setup() {
        valueFactoryDouble = mock(ValueFactory.class);
    }

    /**
     * Tests the valid creation of a TemperatureSensor object.
     * It mocks the SensorCatalogue and SensorFunctionality for the test.
     * The test verifies that the sensor functionality returned by the getSensorFunctionality method of the TemperatureSensor class is as expected.
     */
    @Test
    void validSensorCreation() {
        // Arrange
        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        SensorFunctionality sensorFunctionality = SensorFunctionality.Temperature;
        when(sensorCatalogue.getSensorFunctionality(sensorFunctionality)).thenReturn(sensorFunctionality);

        //Act
        TemperatureSensor sensor = new TemperatureSensor(sensorCatalogue, "Temperature", valueFactoryDouble);

        // Assert
        assertEquals(sensorFunctionality, sensor.getSensorFunctionality());
    }

    /**
     * Tests the valid construction of a TemperatureSensor object.
     * It mocks the SensorCatalogue for the test.
     * The test verifies that the TemperatureSensor object is successfully created.
     */
    @Test
    void validConstructor() {
        // Arrange
        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        String name = "TemperatureSensor";

        // Act
        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorCatalogue, name, valueFactoryDouble);

        // Assert
        assertNotNull(temperatureSensor);
    }

    /**
     * Tests the construction of a TemperatureSensor object with a null SensorCatalogue.
     * It verifies that the constructor throws an IllegalArgumentException when the SensorCatalogue is null.
     */
    @Test
    void invalidConstructorWithNullCatalogue() {
        // Arrange
        SensorCatalogue sensorCatalogue = null;
        String name = "TemperatureSensor";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new TemperatureSensor(sensorCatalogue, name, valueFactoryDouble));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());

    }

    /**
     * Tests the setName method of the TemperatureSensor class with a valid name.
     * It mocks the SensorCatalogue for the test.
     * The test verifies that the setName method returns true when a valid name is provided.
     */
    @Test
    void setValidNameTest() {
        // Arrange
        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorCatalogue, "TemperatureSensor", valueFactoryDouble);
        String sensorName = "SensorName";
        boolean expected = true;

        // Act
        boolean actual = temperatureSensor.setName(sensorName);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the setName method of the TemperatureSensor class with a null name.
     * It mocks the SensorCatalogue for the test.
     * The test verifies that the setName method returns false when a null name is provided.
     */
    @Test
    void setNullNameTest() {
        // Arrange
        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorCatalogue, "Temperature", valueFactoryDouble);
        String sensorName = null;
        boolean expected = false;

        // Act
        boolean actual = temperatureSensor.setName(sensorName);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the setName method of the TemperatureSensor class with an empty name.
     * It mocks the SensorCatalogue for the test.
     * The test verifies that the setName method returns false when an empty name is provided.
     */
    @Test
    void setEmptyNameTest() {
        // Arrange
        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorCatalogue, "Temperature", valueFactoryDouble);
        String sensorName = "";
        boolean expected = false;

        // Act
        boolean actual = temperatureSensor.setName(sensorName);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the getName method of the TemperatureSensor class.
     * It mocks the SensorCatalogue for the test.
     * The test verifies that the getName method returns the correct name after it has been set.
     */
    @Test
    void getNameTest() {
        // Arrange
        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorCatalogue, "Temperature", valueFactoryDouble);
        String sensorName = "SensorName";
        temperatureSensor.setName(sensorName);
        String expected = "SensorName";

        // Act
        String actual = temperatureSensor.getName();

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the getMeasurementUnit method of the TemperatureSensor class.
     * It mocks the SensorCatalogue and ValueFactory for the test.
     * The test verifies that the getMeasurementUnit method returns the correct measurement unit after it has been set.
     */
    @Test
    void getMeasurementUnitTest() {
        // Arrange
        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        ValueFactory mockFactory = mock(ValueFactory.class);
        CelsiusValue mockCelsiusValue = mock(CelsiusValue.class);
        when(mockFactory.createCelsiusTemperature()).thenReturn(mockCelsiusValue);
        when(mockCelsiusValue.getMeasurementUnit()).thenReturn("ºC");
        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorCatalogue, "Temperature", valueFactoryDouble);
        String expected = "ºC";

        // Act
        String actual = temperatureSensor.getMeasurementUnit(mockFactory);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the getReading method of the TemperatureSensor class when no reading is available.
     * It mocks the SensorCatalogue and ValueFactory for the test.
     * The test verifies that the getReading method returns null when no reading is available.
     */
    @Test
    void noReadingAvailableReturnsNull(){
        // Arrange
        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorCatalogue, "Temperature", valueFactoryDouble);
        CelsiusValue valueDouble = mock(CelsiusValue.class);
        when(valueFactoryDouble.createCelsiusTemperature()).thenReturn(valueDouble);
        when(valueDouble.toString()).thenReturn(null);

        // Act
        String reading = temperatureSensor.getReading();

        // Assert
        assertNull(reading);
    }

    /**
     * Tests the getReading method of the TemperatureSensor class when a reading is available.
     * It mocks the SensorCatalogue and ValueFactory for the test.
     * The test verifies that the getReading method returns the correct reading when a reading is available.
     */
    @Test
    void getReadings(){
        // Arrange
        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorCatalogue, "Temperature", valueFactoryDouble);
        CelsiusValue valueDouble = mock(CelsiusValue.class);
        when(valueFactoryDouble.createCelsiusTemperature()).thenReturn(valueDouble);
        when(valueDouble.toString()).thenReturn("22ºC");

        // Act
        String reading = temperatureSensor.getReading();

        // Assert
        assertEquals("22ºC", reading);
    }
}
