package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.InstantPowerConsumptionSensor;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.WValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link InstantPowerConsumptionSensor} class to ensure its correct behavior
 * for creating sensors, fetching measurements, and handling sensor functionalities.
 */
class InstantPowerConsumptionSensorTest {
    private SensorCatalogue mockedSensorCatalogue;
    private ValueFactory mockedValueFactory;
    private WValue mockedWValue;
    private final String SENSOR_NAME = "Instant Power Consumption Sensor";
    private ValueFactory valueFactoryDouble;

    /**
     * Prepares the test environment before each test method.
     * Initializes mocked versions of {@link SensorCatalogue}, {@link ValueFactory}, and {@link WValue}.
     * Sets up common behaviors for these mocks, including configuring the mocked {@link SensorCatalogue}
     * to return a specific {@link SensorFunctionality} and the mocked {@link ValueFactory} to return
     * a mocked {@link WValue} instance. This setup ensures a consistent test environment for verifying
     * the behavior of {@link InstantPowerConsumptionSensor} instances.
     */
    @BeforeEach
    void setUp() {
        mockedSensorCatalogue = mock(SensorCatalogue.class);
        mockedValueFactory = mock(ValueFactory.class);
        mockedWValue = mock(WValue.class);

        when(mockedSensorCatalogue.getSensorFunctionality(SensorFunctionality.Power_Consumption)).thenReturn(SensorFunctionality.Power_Consumption);
        when(mockedValueFactory.createWValue()).thenReturn(mockedWValue);
        when(mockedWValue.getMeasurementUnit()).thenReturn("W");

        valueFactoryDouble = mock(ValueFactory.class);
    }

    /**
     * Tests that the constructor throws an IllegalArgumentException when provided with a null SensorCatalogue.
     */
    @Test
    void invalidConstructorWithNullCatalogue() {
        // Arrange
        SensorCatalogue sensorCatalogue = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new InstantPowerConsumptionSensor(sensorCatalogue, SENSOR_NAME, valueFactoryDouble));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());
    }

    /**
     * Verifies that the correct measurement unit is returned for the InstantPowerConsumptionSensor.
     */
    @Test
    void getMeasurementUnitTest() {
        // Arrange
        when(mockedValueFactory.createWValue()).thenReturn(mockedWValue);
        when(mockedWValue.getMeasurementUnit()).thenReturn("W");
        InstantPowerConsumptionSensor instantPowerConsumptionSensor =
                new InstantPowerConsumptionSensor(mockedSensorCatalogue, SENSOR_NAME, valueFactoryDouble);
        String expected = "W";

        // Act
        String actual = instantPowerConsumptionSensor.getMeasurementUnit(mockedValueFactory);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests that no reading is available (returns null) when the sensor's value factory produces a null value string.
     */
    @Test
    void noReadingAvailableReturnsNull(){
        // Arrange
        InstantPowerConsumptionSensor instantPowerConsumptionSensor =
                new InstantPowerConsumptionSensor(mockedSensorCatalogue, SENSOR_NAME, valueFactoryDouble);
        when(valueFactoryDouble.createWValue()).thenReturn(mockedWValue);
        when(mockedWValue.toString()).thenReturn(null);

        // Act
        String reading = instantPowerConsumptionSensor.getReading();

        // Assert
        assertNull(reading);
    }

    /**
     * Verifies that the sensor's name is correctly returned by getName method.
     */
    @Test
    void getName() {
        // Arrange
        InstantPowerConsumptionSensor instantPowerConsumptionSensor =
                new InstantPowerConsumptionSensor(mockedSensorCatalogue, SENSOR_NAME, valueFactoryDouble);

        // Act + Assert
        assertEquals(SENSOR_NAME, instantPowerConsumptionSensor.getName());
    }

    /**
     * Confirms that the sensor correctly identifies its functionality as Power Consumption.
     */
    @Test
    void getSensorFunctionality() {
        // Arrange
        InstantPowerConsumptionSensor instantPowerConsumptionSensor =
                new InstantPowerConsumptionSensor(mockedSensorCatalogue, SENSOR_NAME, valueFactoryDouble);

        // Act + Assert
        assertEquals(SensorFunctionality.Power_Consumption, instantPowerConsumptionSensor.getSensorFunctionality());
    }
}
