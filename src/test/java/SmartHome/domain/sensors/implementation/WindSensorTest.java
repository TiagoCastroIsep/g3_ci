package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.WindSensor;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.KmhCardinalValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for the {@link WindSensor} class, ensuring correct behavior of its construction,
 * functionality retrieval, measurement unit processing, and reading acquisition under various conditions.
 * Utilizes mock objects for dependencies such as {@link SensorCatalogue}, {@link ValueFactory},
 * and {@link KmhCardinalValue} to isolate testing to the {@link WindSensor} class functionality.
 */
class WindSensorTest {

    SensorCatalogue mockCatalogue;
    final String NAME = "Wind Sensor";
    ValueFactory mockFactory;
    KmhCardinalValue mockKmhCardinalValue;

    /**
     * Sets up the test environment before each test method. This includes initializing
     * mock objects for the {@link SensorCatalogue}, {@link ValueFactory}, and {@link KmhCardinalValue}
     * to be used in the tests.
     */
    @BeforeEach
    void setUp() {
        mockCatalogue = mock(SensorCatalogue.class);
        mockFactory = mock(ValueFactory.class);
        mockKmhCardinalValue = mock(KmhCardinalValue.class);
    }

    /**
     * Verifies that a {@link WindSensor} can be successfully created with valid arguments, ensuring
     * that the sensor's functionality is correctly set to {@link SensorFunctionality#Wind}.
     */
    @Test
    void validSensorCreation() {
        // Arrange
        SensorFunctionality sensorFunctionality = SensorFunctionality.Wind;
        when(mockCatalogue.getSensorFunctionality(sensorFunctionality)).thenReturn(sensorFunctionality);

        //Act
        WindSensor windSensor = new WindSensor(mockCatalogue, NAME, mockFactory);

        // Assert
        assertEquals(sensorFunctionality, windSensor.getSensorFunctionality());
    }

    /**
     * Tests the constructor's behavior when provided with a null {@link SensorCatalogue},
     * expecting an {@link IllegalArgumentException} to be thrown, indicating the catalogue
     * cannot be null.
     */
    @Test
    void invalidConstructorWithNullCatalogue() {
        // Arrange & Act
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new WindSensor(null, NAME, mockFactory)
        );

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());
    }

    /**
     * Ensures that the {@link WindSensor#getMeasurementUnit(ValueFactory)} method correctly
     * returns the measurement unit associated with the sensor's current value.
     */
    @Test
    void getMeasurementUnitTest() {
        // Arrange
        when(mockFactory.createKmhCardinalValue()).thenReturn(mockKmhCardinalValue);
        when(mockKmhCardinalValue.getMeasurementUnit()).thenReturn("km/h");
        WindSensor windSensor = new WindSensor(mockCatalogue,NAME, mockFactory);
        String expected = "km/h";

        // Act
        String actual = windSensor.getMeasurementUnit(mockFactory);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests that the {@link WindSensor#getReading()} method returns null when no reading is
     * available, simulating a scenario where the sensor's current value has not been set.
     */
    @Test
    void noReadingAvailableReturnsNull(){
        // Arrange
        WindSensor windSensor = new WindSensor(mockCatalogue, NAME, mockFactory);
        when(mockFactory.createKmhCardinalValue()).thenReturn(mockKmhCardinalValue);
        when(mockKmhCardinalValue.toString()).thenReturn(null);

        // Act
        String reading = windSensor.getReading();

        // Assert
        assertNull(reading);
    }

    /**
     * Verifies that {@link WindSensor#getReading()} returns the correct string representation
     * of the sensor's current value when it is available.
     */
    @Test
    void getReading(){
        // Arrange
        WindSensor windSensor = new WindSensor(mockCatalogue, NAME, mockFactory);
        when(mockFactory.createKmhCardinalValue()).thenReturn(mockKmhCardinalValue);
        when(mockKmhCardinalValue.toString()).thenReturn("14 km/h");

        // Act
        String reading = windSensor.getReading();

        // Assert
        assertEquals("14 km/h", reading);
    }

    /**
     * Confirms that {@link WindSensor#getName()} accurately retrieves the name assigned
     * to the sensor during construction.
     */
    @Test
    void getName(){
        // Arrange
        WindSensor windSensor = new WindSensor(mockCatalogue, NAME, mockFactory);

        // Act
        String name = windSensor.getName();

        // Assert
        assertEquals(NAME, name);
    }
}
