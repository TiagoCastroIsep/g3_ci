package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.SolarIrradianceSensor;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.Wm2Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SolarIrradianceSensor}. These tests validate the functionality
 * of the SolarIrradianceSensor, focusing on its ability to handle solar irradiance readings correctly.
 */
class SolarIrradianceSensorTest {
    private final String NAME = "Solar Irradiance Sensor";
    private SensorCatalogue mockCatalogue;
    private ValueFactory mockFactory;
    private Wm2Value mockWm2Value;

    /**
     * Sets up a mock environment for Solar Irradiance Sensor tests.
     * This includes creating mocks for SensorCatalogue, ValueFactory, and Wm2Value.
     */
    @BeforeEach
    void setUp() {
        mockCatalogue = mock(SensorCatalogue.class);
        mockFactory = mock(ValueFactory.class);
        mockWm2Value = mock(Wm2Value.class);
    }

    /**
     * Verifies that a Solar Irradiance Sensor can be successfully created with valid arguments,
     * ensuring the sensor functionality is correctly identified and associated.
     */
    @Test
    void validSensorCreation() {
        // Arrange
        SensorFunctionality sensorFunctionality = SensorFunctionality.SolarIrradiance;
        when(mockCatalogue.getSensorFunctionality(sensorFunctionality)).thenReturn(sensorFunctionality);

        //Act
        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(mockCatalogue, NAME, mockFactory);

        // Assert
        assertEquals(sensorFunctionality, solarIrradianceSensor.getSensorFunctionality());
    }

    /**
     * Ensures that attempting to create a Solar Irradiance Sensor with a null SensorCatalogue
     * throws an IllegalArgumentException, enforcing the requirement for a non-null catalogue.
     */
    @Test
    void invalidConstructorWithNullCatalogue() {
        // Act
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new SolarIrradianceSensor(null, NAME, mockFactory)
        );

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());

    }

    /**
     * Tests that the Solar Irradiance Sensor correctly reports its measurement unit,
     * validating the integration with the ValueFactory and Wm2Value classes.
     */
    @Test
    void getMeasurementUnitTest() {
        // Arrange
        when(mockFactory.createWm2Value()).thenReturn(mockWm2Value);
        when(mockWm2Value.getMeasurementUnit()).thenReturn("ºC");
        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(mockCatalogue, NAME, mockFactory);
        String expected = "ºC";

        // Act
        String actual = solarIrradianceSensor.getMeasurementUnit(mockFactory);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Confirms that the Solar Irradiance Sensor returns null for its reading
     * when no measurements have been recorded, indicating no available data.
     */
    @Test
    void noReadingAvailableReturnsNull() {
        // Arrange
        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(mockCatalogue, NAME, mockFactory);

        // Act
        String reading = solarIrradianceSensor.getReading();

        // Assert
        assertNull(reading);
    }

    /**
     * Validates that the getName method of the Solar Irradiance Sensor
     * correctly returns the name assigned during construction.
     */
    @Test
    void getName() {
        // Arrange
        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(mockCatalogue, NAME, mockFactory);

        // Act
        String name = solarIrradianceSensor.getName();

        // Assert
        assertEquals(NAME, name);
    }

    /**
     * Verifies the functionality of obtaining a reading from the Solar Irradiance Sensor,
     * ensuring the sensor correctly fetches and formats the current measurement.
     */
    @Test
    void getReading() {
        // Arrange
        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(mockCatalogue, NAME, mockFactory);
        when(mockFactory.createWm2Value()).thenReturn(mockWm2Value);
        when(mockWm2Value.toString()).thenReturn("20 Wm2");

        // Act
        String reading = solarIrradianceSensor.getReading();

        // Assert
        assertEquals("20 Wm2", reading);
    }
}