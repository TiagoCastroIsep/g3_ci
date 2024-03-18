package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.ElectricEnergyConsumptionSensor;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.ValueFactoryImpl;
import SmartHome.domain.values.implementation.WhValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ElectricEnergyConsumptionSensor}. These tests validate the functionality
 * of the ElectricEnergyConsumptionSensor, focusing on its ability to handle energy consumption readings correctly.
 */
class ElectricEnergyConsumptionSensorTest {
    ElectricEnergyConsumptionSensor electricEnergyConsumptionSensorMock;
    SensorCatalogue mockCatalogue;
    final String SENSOR_NAME = "Electric Energy Consumption Sensor";
    ValueFactory mockFactory;
    WhValue mockValue;

    /**
     * Sets up mocks and initializes the ElectricEnergyConsumptionSensor instance for testing.
     */
    @BeforeEach
    void setUp() {
        mockFactory = mock(ValueFactoryImpl.class);
        mockCatalogue = mock(SensorCatalogue.class);

        mockValue = mock(WhValue.class);
        when(mockFactory.createWhValue()).thenReturn(mockValue);

        electricEnergyConsumptionSensorMock = new ElectricEnergyConsumptionSensor(mockCatalogue, SENSOR_NAME, mockFactory);
    }

    /**
     * Validates the successful creation of an ElectricEnergyConsumptionSensor with valid parameters.
     */
    @Test
    void validSensorCreation() {
        // Arrange
        SensorFunctionality sensorFunctionality = SensorFunctionality.Energy_Consumption;
        when(mockCatalogue.getSensorFunctionality(sensorFunctionality)).thenReturn(sensorFunctionality);

        // Act
        electricEnergyConsumptionSensorMock = new ElectricEnergyConsumptionSensor(mockCatalogue, SENSOR_NAME, mockFactory);

        // Assert
        assertEquals(sensorFunctionality, electricEnergyConsumptionSensorMock.getSensorFunctionality());
    }

    /**
     * Ensures the constructor throws an IllegalArgumentException when a null SensorCatalogue is provided.
     */
    @Test
    void invalidConstructorWithNullCatalogue() {
        // Arrange
        SensorCatalogue sensorCatalogue = null;

        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new ElectricEnergyConsumptionSensor(sensorCatalogue, SENSOR_NAME, mockFactory));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());

    }

    /**
     * Confirms that the getName method correctly returns the sensor's name.
     */
    @Test
    void validGetSensorName() {
        // Arrange
        electricEnergyConsumptionSensorMock = new ElectricEnergyConsumptionSensor(mockCatalogue, SENSOR_NAME, mockFactory);

        // Act
        String result = electricEnergyConsumptionSensorMock.getName();

        // Assert
        assertEquals(SENSOR_NAME, result);
    }

    /**
     * Tests that the getMeasurementUnit method returns the correct measurement unit for energy consumption.
     */
    @Test
    void getCorrectMeasurementUnit(){
        // Arrange
        WhValue energyValue= mock(WhValue.class);
        when(mockFactory.createWhValue()).thenReturn(energyValue);
        when(energyValue.getMeasurementUnit()).thenReturn("Wh");
        String expected = "Wh";
        electricEnergyConsumptionSensorMock = new ElectricEnergyConsumptionSensor(mockCatalogue, SENSOR_NAME, mockFactory);

        // Act
        String result = electricEnergyConsumptionSensorMock.getMeasurementUnit();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that energy consumption is accurately calculated over a specified period with valid readings.
     */
    @Test
    void getEnergyConsumption(){
        // Arrange
        WhValue reading1 = new WhValue();
        reading1.setValue("20");
        LocalTime readingTime1 = LocalTime.of(11, 10, 0);

        WhValue reading2 = new WhValue();
        reading2.setValue("30");
        LocalTime readingTime2 = LocalTime.of(12, 10, 0);

        LocalTime start = LocalTime.of(11,10,0);
        LocalTime end = LocalTime.of(12,10,0);

        // Act
        boolean isReadingValid1 = electricEnergyConsumptionSensorMock.addReading(reading1, readingTime1);
        boolean isReadingValid2 = electricEnergyConsumptionSensorMock.addReading(reading2, readingTime2);
        String totalConsumption = electricEnergyConsumptionSensorMock.getReading(start, end);

        // Assert
        assertEquals("10.0 Wh", totalConsumption);
        assertTrue(isReadingValid1);
        assertTrue(isReadingValid2);
    }

    /**
     * Tests the calculation of energy consumption over another period to ensure accuracy with different readings.
     */
    @Test
    void getEnergyConsumption2(){
        // Arrange
        WhValue reading1 = new WhValue();
        reading1.setValue("30");
        LocalTime readingTime1 = LocalTime.of(14, 0, 30);

        WhValue reading2 = new WhValue();
        reading2.setValue("60");
        LocalTime readingTime2 = LocalTime.of(17, 50, 10);

        LocalTime start = LocalTime.of(14, 0, 30);
        LocalTime end = LocalTime.of(17, 50, 10);

        // Act
        boolean isReadingValid1 = electricEnergyConsumptionSensorMock.addReading(reading1, readingTime1);
        boolean isReadingValid2 = electricEnergyConsumptionSensorMock.addReading(reading2, readingTime2);
        String totalConsumption = electricEnergyConsumptionSensorMock.getReading(start, end);

        // Assert
        assertEquals("30.0 Wh", totalConsumption);
        assertTrue(isReadingValid1);
        assertTrue(isReadingValid2);
    }

    /**
     * Validates that attempting to calculate energy consumption with no readings returns an appropriate error message.
     */
    @Test
    void getEnergyConsumptionNoReadings(){
        // Arrange
        LocalTime start = LocalTime.of(14, 0, 30);
        LocalTime end = LocalTime.of(17, 50, 10);

        // Act
        String totalConsumption = electricEnergyConsumptionSensorMock.getReading(start, end);

        // Assert
        assertEquals("There should be exactly two readings", totalConsumption);
    }

    /**
     * Confirms that an "Invalid time period" error is returned when the end time is before the start time.
     */
    @Test
    void getEnergyConsumptionInvalidPeriod(){
        // Arrange
        WhValue reading1 = new WhValue();
        reading1.setValue("30");
        LocalTime readingTime1 = LocalTime.of(14, 0, 0);

        WhValue reading2 = new WhValue();
        reading2.setValue("60");
        LocalTime readingTime2 = LocalTime.of(13, 0, 0);

        LocalTime start = LocalTime.of(14, 0, 0);
        LocalTime end = LocalTime.of(13, 0, 0);

        // Act
        boolean isReadingValid1 = electricEnergyConsumptionSensorMock.addReading(reading1, readingTime1);
        boolean isReadingValid2 = electricEnergyConsumptionSensorMock.addReading(reading2, readingTime2);
        String totalConsumption = electricEnergyConsumptionSensorMock.getReading(start, end);

        // Assert
        assertEquals("Invalid time period", totalConsumption);
        assertTrue(isReadingValid1);
        assertTrue(isReadingValid2);
    }

    /**
     * Ensures that adding a null reading to the sensor is correctly rejected.
     */
    @Test
    void addNullReading() {
        // Arrange
        WhValue reading = null;
        LocalTime time = LocalTime.of(14, 0, 0);

        // Act
        boolean result = electricEnergyConsumptionSensorMock.addReading(reading, time);

        // Assert
        assertFalse(result);

    }
}
