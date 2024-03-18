package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.AveragePowerConsumptionSensor;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.WValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the AveragePowerConsumptionSensor class.
 * It tests the functionality of the sensor under different conditions.
 */
class AveragePowerConsumptionSensorTest {
    AveragePowerConsumptionSensor averagePowerConsumptionSensorMock;
    SensorCatalogue mockCatalogue;
    final String SENSOR_NAME = "Average Power Consumption Sensor";
    ValueFactory mockFactory;
    WValue mockValue;

    /**
     * Prepares the test environment by creating mock objects and initializing the sensor instance.
     */
    @BeforeEach
    void setUp() {
        mockCatalogue = mock(SensorCatalogue.class);
        mockFactory = mock(ValueFactory.class);

        mockValue = mock(WValue.class);
        when(mockFactory.createWValue()).thenReturn(mockValue);

        when(mockValue.setValue("20")).thenReturn(true);
        averagePowerConsumptionSensorMock = new AveragePowerConsumptionSensor(mockCatalogue, SENSOR_NAME, mockFactory);
    }

    /**
     * Tests the creation of a valid AveragePowerConsumptionSensor instance.
     * It verifies that the sensor is correctly created with the expected functionality when provided with valid parameters.
     */
    @Test
    void validSensorCreation() {
        //Arrange
        SensorFunctionality sensorFunctionality = SensorFunctionality.Power_Consumption;
        when(mockCatalogue.getSensorFunctionality(sensorFunctionality)).thenReturn(sensorFunctionality);

        //Act
        averagePowerConsumptionSensorMock = new AveragePowerConsumptionSensor(mockCatalogue, SENSOR_NAME, mockFactory);


        //Assert
        assertEquals(sensorFunctionality, averagePowerConsumptionSensorMock.getSensorFunctionality());
    }

    /**
     * Tests the constructor of the AveragePowerConsumptionSensor class when provided with a null SensorCatalogue.
     * It verifies that the constructor correctly throws an IllegalArgumentException with the expected message.
     */
    @Test
    void invalidConstructorWithNullCatalogue() {
        // Arrange
        SensorCatalogue sensorCatalogue = null;

        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new AveragePowerConsumptionSensor(sensorCatalogue, SENSOR_NAME, mockFactory));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());

    }

    /**
     * Tests the constructor of the AveragePowerConsumptionSensor class when provided with a null name.
     * It verifies that the constructor correctly throws an IllegalArgumentException with the expected message.
     */
    @Test
    void invalidConstructorWithNullName() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new AveragePowerConsumptionSensor(mockCatalogue, null, mockFactory)
        );

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the constructor of the AveragePowerConsumptionSensor class when provided with an empty name.
     * It verifies that the constructor correctly throws an IllegalArgumentException with the expected message.
     */
    @Test
    void invalidConstructorWithEmptyName() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new AveragePowerConsumptionSensor(mockCatalogue, "   ", mockFactory)
        );

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the constructor of the AveragePowerConsumptionSensor class when provided with a null ValueFactory.
     * It verifies that the constructor correctly throws an IllegalArgumentException with the expected message.
     */
    @Test
    void invalidConstructorWithNullFactory() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new AveragePowerConsumptionSensor(mockCatalogue, SENSOR_NAME, null)
        );

        // Assert
        assertEquals("ValueFactory cannot be null", exception.getMessage());
    }

    /**
     * Tests the getName method of the AveragePowerConsumptionSensor class.
     * It verifies that the method correctly returns the name of the sensor.
     */
    @Test
    void validGetName() {
        //Act
        String result = averagePowerConsumptionSensorMock.getName();

        //Assert
        assertEquals(SENSOR_NAME, result);
    }

    /**
     * Tests the getReading method of the AveragePowerConsumptionSensor class with a single valid reading.
     * It verifies that the method correctly calculates and returns the average power consumption.
     */
    @Test
    void validGetAverage() {
        //Arrange
        WValue reading1 = new WValue();
        reading1.setValue("20");
        LocalTime readingTime1 = LocalTime.of(11, 10, 0);
        LocalTime start = LocalTime.of(10,0,0);
        LocalTime end = LocalTime.of(11,11,0);

        //Act
        averagePowerConsumptionSensorMock.addReading(reading1, readingTime1);
        String average = averagePowerConsumptionSensorMock.getReading(start, end);

        //Assert
        assertEquals("20.0W", average);
    }

    /**
     * Tests the getReading method of the AveragePowerConsumptionSensor class with two valid readings.
     * It verifies that the method correctly calculates and returns the average power consumption.
     */
    @Test
    void validGetAverageWithTwoReadings() {
        //Arrange
        WValue reading1 = new WValue();
        reading1.setValue("20");
        LocalTime readingTime1 = LocalTime.of(11, 10, 0);

        WValue reading2 = new WValue();
        reading2.setValue("30");
        LocalTime readingTime2 = LocalTime.of(12, 10, 0);

        LocalTime start = LocalTime.of(10,0,0);
        LocalTime end = LocalTime.of(12,11,0);

        //Act
        boolean isReadingValid1 = averagePowerConsumptionSensorMock.addReading(reading1, readingTime1);
        boolean isReadingValid2 = averagePowerConsumptionSensorMock.addReading(reading2, readingTime2);
        String average = averagePowerConsumptionSensorMock.getReading(start, end);

        //Assert
        assertEquals("25.0W", average);
        assertTrue(isReadingValid1);
        assertTrue(isReadingValid2);
    }

    /**
     * Tests the getReading method of the AveragePowerConsumptionSensor class with several valid readings.
     * It verifies that the method correctly calculates and returns the average power consumption.
     */
    @Test
    void validGetAverageSeveralReadings() {
        //Arrange
        WValue reading1 = new WValue();
        reading1.setValue("20");
        LocalTime readingTime1 = LocalTime.of(11, 10, 0);

        WValue reading2 = new WValue();
        reading2.setValue("30");
        LocalTime readingTime2 = LocalTime.of(12, 10, 0);

        WValue reading3 = new WValue();
        reading2.setValue("40");
        LocalTime readingTime3 = LocalTime.of(13, 10, 0);

        LocalTime start = LocalTime.of(10,0,0);
        LocalTime end = LocalTime.of(14,0,0);

        //Act
        boolean isReadingValid1 = averagePowerConsumptionSensorMock.addReading(reading1, readingTime1);
        boolean isReadingValid2 = averagePowerConsumptionSensorMock.addReading(reading2, readingTime2);
        boolean isReadingValid3 = averagePowerConsumptionSensorMock.addReading(reading3, readingTime3);

        String average = averagePowerConsumptionSensorMock.getReading(start, end);

        //Assert
        assertEquals("30.0W", average);
        assertTrue(isReadingValid1);
        assertTrue(isReadingValid2);
        assertTrue(isReadingValid3);
    }

    /**
     * Tests the getReading method of the AveragePowerConsumptionSensor class when the end time is before the start time.
     * It verifies that the method correctly returns "No readings to show".
     */
    @Test
    void invalidGetAverageEndTimeBeforeStartTime() {
        //Arrage
        LocalTime start = LocalTime.of(14,0,0);
        LocalTime end = LocalTime.of(13,0,0);

        //Act
        String average = averagePowerConsumptionSensorMock.getReading(start, end);

        //Assert
        assertEquals("No readings to show", average);
    }
}
