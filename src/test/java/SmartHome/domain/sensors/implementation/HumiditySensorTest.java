package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.DewPointSensor;
import SmartHome.domain.sensors.implementation.HumiditySensor;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.PercentageValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the HumiditySensor class.
 * It tests the constructor, getName, getMeasurementUnit, and getReading methods.
 * It also tests the behavior of the HumiditySensor when no readings are available.
 */
public class HumiditySensorTest {
    SensorCatalogue sensorCatalogueMock;
    final String SENSOR_NAME = "Humidity Sensor";
    ValueFactory valueFactoryMock;

    /**
     * This method is executed before each test.
     * It sets up the necessary mocks for the HumiditySensor tests.
     */
    @BeforeEach
    void setUp() {
        sensorCatalogueMock = mock(SensorCatalogue.class);
        valueFactoryMock = mock(ValueFactory.class);
    }

    /**
     * Tests the creation of a valid HumiditySensor.
     * It verifies that the sensor is correctly created with the provided parameters.
     */
    @Test
    void validSensorCreation() {
        // Arrange
        SensorFunctionality sensorFunctionality = SensorFunctionality.Humidity;
        when(sensorCatalogueMock.getSensorFunctionality(sensorFunctionality)).thenReturn(sensorFunctionality);

        //Act
        HumiditySensor humiditySensor = new HumiditySensor(sensorCatalogueMock, SENSOR_NAME, valueFactoryMock);

        // Assert
        assertEquals(sensorFunctionality, humiditySensor.getSensorFunctionality());
    }

    /**
     * Tests the constructor of the HumiditySensor class with valid parameters.
     * It verifies that the HumiditySensor is correctly instantiated.
     */
    @Test
    void validConstructor() {
        // Act
        HumiditySensor humiditySensor = new HumiditySensor(sensorCatalogueMock, SENSOR_NAME, valueFactoryMock);

        // Assert
        assertNotNull(humiditySensor);
    }

    /**
     * Tests the constructor of the HumiditySensor class with a null catalogue.
     * It verifies that the constructor throws an IllegalArgumentException when the catalogue is null.
     */
    @Test
    void invalidConstructorWithNullCatalogue() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new HumiditySensor(null, SENSOR_NAME, valueFactoryMock));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());

    }

    /**
     * Tests the constructor of the HumiditySensor class with a null name.
     * It verifies that the constructor throws an IllegalArgumentException when the name is null.
     */
    @Test
    void invalidConstructorWithNullName() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new DewPointSensor(sensorCatalogueMock, null, valueFactoryMock)
        );

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the constructor of the HumiditySensor class with an empty name.
     * It verifies that the constructor throws an IllegalArgumentException when the name is empty.
     */
    @Test
    void invalidConstructorWithEmptyName() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new HumiditySensor(sensorCatalogueMock, "   ", valueFactoryMock)
        );

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the constructor of the HumiditySensor class with a null ValueFactory.
     * It verifies that the constructor throws an IllegalArgumentException when the ValueFactory is null.
     */
    @Test
    void invalidConstructorWithNullFactory() {
        // Act
        IllegalArgumentException exception = assertThrows(
              IllegalArgumentException.class,
              () -> new HumiditySensor(sensorCatalogueMock, SENSOR_NAME, null)
        );

        // Assert
        assertEquals("ValueFactory cannot be null", exception.getMessage());
    }

    /**
     * Tests the getName method of the HumiditySensor class.
     * It verifies that the method correctly returns the name of the sensor.
     */
    @Test
    void getNameTest() {
        // Arrange
        HumiditySensor humiditySensor = new HumiditySensor(sensorCatalogueMock, SENSOR_NAME, valueFactoryMock);

        // Act
        String actual = humiditySensor.getName();

        // Assert
        assertEquals(SENSOR_NAME, actual);
    }

    /**
     * Tests the getMeasurementUnit method of the HumiditySensor class.
     * It verifies that the method correctly returns the measurement unit of the sensor.
     */
    @Test
    void getMeasurementUnitTest() {
        // Arrange
        PercentageValue mockPercentageValue = mock(PercentageValue.class);
        when(valueFactoryMock.createPercentage()).thenReturn(mockPercentageValue);
        when(mockPercentageValue.getMeasurementUnit()).thenReturn("%");
        HumiditySensor humiditySensor = new HumiditySensor(sensorCatalogueMock, SENSOR_NAME, valueFactoryMock);
        String expected = "%";

        // Act
        String actual = humiditySensor.getMeasurementUnit();

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the getReading method of the HumiditySensor class.
     * It verifies that the method correctly returns the current humidity reading of the sensor.
     */
    @Test
    void getReading() {
        // Arrange
        PercentageValue mockPercentageValue = mock(PercentageValue.class);
        when(valueFactoryMock.createPercentage()).thenReturn(mockPercentageValue);
        when(mockPercentageValue.toString()).thenReturn("10.0");
        HumiditySensor humiditySensor = new HumiditySensor(sensorCatalogueMock, SENSOR_NAME, valueFactoryMock);
        String expected = "10.0";

        // Act
        String actual = humiditySensor.getReading();

        // Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests the getReading method of the HumiditySensor class when no readings are available.
     * It verifies that the method correctly returns null when there are no readings.
     */
    @Test
    void noReadingsAvailable() {
        // Arrange
        HumiditySensor humiditySensor = new HumiditySensor(sensorCatalogueMock, SENSOR_NAME, valueFactoryMock);
        PercentageValue valueDouble = mock(PercentageValue.class);
        when(valueFactoryMock.createPercentage()).thenReturn(valueDouble);
        when(valueDouble.toString()).thenReturn(null);
        // Act
        String result = humiditySensor.getReading();

        // Assert
        assertNull(result);
    }

    /**
     * Tests the getReading method of the HumiditySensor class.
     * It verifies that the method correctly returns the current humidity reading of the sensor in percentage.
     */
    @Test
    void getReadings() {
        // Arrange
        HumiditySensor humiditySensor = new HumiditySensor(sensorCatalogueMock, SENSOR_NAME, valueFactoryMock);
        PercentageValue valueDouble = mock(PercentageValue.class);
        when(valueFactoryMock.createPercentage()).thenReturn(valueDouble);
        when(valueDouble.toString()).thenReturn("50 %");
        // Act
        String result = humiditySensor.getReading();

        // Assert
        assertEquals("50 %", result);
    }
}
