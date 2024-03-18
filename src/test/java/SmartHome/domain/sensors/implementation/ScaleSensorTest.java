package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.ScaleSensor;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.PercentageValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the ScaleSensor class.
 * It tests the functionality of the ScaleSensor class methods such as sensor creation,
 * getting the correct measurement unit, getting valid percentage, handling of no readings,
 * and getting the name of the sensor.
 */
public class ScaleSensorTest {
    SensorCatalogue mockCatalogue;
    ScaleSensor scaleSensorMock;
    final String NAME = "Percentage Sensor";
    ValueFactory mockFactory;
    PercentageValue mockPercentageValue;

    /**
     * This method is executed before each test.
     * It sets up the test environment by initializing the mocked versions of SensorCatalogue, ValueFactory, and PercentageValue.
     * It also creates a new instance of ScaleSensor with the mocked objects.
     */
    @BeforeEach
    void setUp() {
        mockCatalogue = mock(SensorCatalogue.class);
        mockFactory = mock(ValueFactory.class);
        mockPercentageValue = mock(PercentageValue.class);
        scaleSensorMock = new ScaleSensor(mockCatalogue, NAME, mockFactory);
    }

    /**
     * Tests the valid creation of a ScaleSensor.
     * It verifies that the sensor is correctly created with the provided SensorCatalogue, name, and ValueFactory.
     * It also checks that the sensor's functionality is correctly set to Scale.
     */
    @Test
    void validSensorCreation() {
        // Arrange
        SensorFunctionality sensorFunctionality = SensorFunctionality.Scale;
        when(mockCatalogue.getSensorFunctionality(sensorFunctionality)).thenReturn(sensorFunctionality);

        //Act
        scaleSensorMock = new ScaleSensor(mockCatalogue, NAME, mockFactory);

        // Assert
        assertEquals(sensorFunctionality, scaleSensorMock.getSensorFunctionality());
    }

    /**
     * Tests that the constructor throws an IllegalArgumentException when provided with a null SensorCatalogue.
     */
    @Test
    void invalidConstructorWithNullCatalogue() {
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new ScaleSensor(null, NAME, mockFactory));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());
    }

    /**
     * Tests the getMeasurementUnit method of the ScaleSensor class.
     * It verifies that the method correctly returns the measurement unit of the sensor in percentage.
     */
    @Test
    void getCorrectMeasurementUnit() {
        //Arrange
        when(mockFactory.createPercentage()).thenReturn(mockPercentageValue);
        when(mockPercentageValue.getMeasurementUnit()).thenReturn("%");
        String expected = "%";

        //Act
        String result = scaleSensorMock.getMeasurementUnit(mockFactory);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the getReading method of the ScaleSensor class.
     * It verifies that the method correctly returns a valid percentage reading of the sensor.
     */
    @Test
    void getValidPercentage() {
        //Arrange
        String name = "Percent";
        ScaleSensor scaleSensor = new ScaleSensor(mockCatalogue, name, mockFactory);
        when(mockFactory.createPercentage()).thenReturn(mockPercentageValue);
        when(mockPercentageValue.toString()).thenReturn("20 %");
        String expected = "20 %";

        //Act
        String result = scaleSensor.getReading();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the correct measurement unit is returned for the ScaleSensor.
     * It mocks the creation of a PercentageValue and sets its measurement unit to "%".
     * Then it checks if the getMeasurementUnit method of the ScaleSensor class returns the correct measurement unit.
     */
    @Test
    void getMeasurementUnitTest() {
        // Arrange
        when(mockFactory.createPercentage()).thenReturn(mockPercentageValue);
        when(mockPercentageValue.getMeasurementUnit()).thenReturn("%");
        ScaleSensor scaleSensor = new ScaleSensor(mockCatalogue, "Percentage", mockFactory);
        String expected = "%";

        // Act
        String actual = scaleSensor.getMeasurementUnit(mockFactory);

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the getReading method of the ScaleSensor class when no reading is available.
     * It verifies that the method correctly returns null when the sensor has no reading.
     */
    @Test
    void noReadingAvailableReturnsNull(){
        // Arrange
        ScaleSensor scaleSensor = new ScaleSensor(mockCatalogue, "Percentage", mockFactory);
        when(mockFactory.createPercentage()).thenReturn(mockPercentageValue);
        when(mockPercentageValue.toString()).thenReturn(null);

        // Act
        String reading = scaleSensor.getReading();

        // Assert
        assertNull(reading);
    }

    /**
     * Verifies that the sensor's name is correctly returned by getName method.
     */
    @Test
    void getName() {
        // Arrange
        ScaleSensor scaleSensor = new ScaleSensor(mockCatalogue, "Percentage", mockFactory);

        // Act
        String name = scaleSensor.getName();

        // Assert
        assertEquals("Percentage", name);
    }
}
