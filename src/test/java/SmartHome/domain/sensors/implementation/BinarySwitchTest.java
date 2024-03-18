package SmartHome.domain.sensors.implementation;

import SmartHome.domain.actuators.implementation.SwitchOnOffActuator;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.BinarySwitch;
import SmartHome.domain.values.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the BinarySwitch class.
 * It tests the functionality of the BinarySwitch class methods such as isOn, isOff, getName, and getType.
 * It also tests the constructor of the BinarySwitch class with null catalogue.
 */
public class BinarySwitchTest {
    final String SENSOR_NAME = "IsOnSensor";
    SensorCatalogue mockCatalogue;
    ValueFactory valueFactoryDouble;

    /**
     * This method is executed before each test.
     * It sets up the mock objects for the SensorCatalogue and ValueFactory.
     * It also configures the SensorFunctionality for the mock SensorCatalogue.
     */
    @BeforeEach
    void setup() {
        mockCatalogue = mock(SensorCatalogue.class);
        valueFactoryDouble = mock(ValueFactory.class);
        when(mockCatalogue.getSensorFunctionality(SensorFunctionality.Binary_Switch)).thenReturn(SensorFunctionality.Binary_Switch);
    }

    /**
     * Tests the readStatus method of the BinarySwitch class when the associated SwitchOnOffActuator is ON.
     * It verifies that the method correctly returns true.
     */
    @Test
    void isOn() {
        // Arrange
        boolean isOn = true;
        SwitchOnOffActuator switchTest = mock(SwitchOnOffActuator.class);
        when(switchTest.isOn()).thenReturn(isOn);
        switchTest.switchActuator();
        BinarySwitch binarySwitch = new BinarySwitch(mockCatalogue, SENSOR_NAME, valueFactoryDouble);
        binarySwitch.configureSensor(switchTest);

        // Act
        boolean result = binarySwitch.readStatus();

        // Assert
        assertTrue(result);
    }

    /**
     * Tests the readStatus method of the BinarySwitch class when the associated SwitchOnOffActuator is OFF.
     * It verifies that the method correctly returns false.
     */
    @Test
    void isOff() {
        // Arrange
        boolean isOn = false;
        SwitchOnOffActuator switchTest = mock(SwitchOnOffActuator.class);
        when(switchTest.isOn()).thenReturn(isOn);
        BinarySwitch binarySwitch = new BinarySwitch(mockCatalogue, SENSOR_NAME, valueFactoryDouble);
        binarySwitch.configureSensor(switchTest);

        // Act
        boolean result = binarySwitch.readStatus();

        // Assert
        assertFalse(result);
    }

    /**
     * Tests the constructor of the BinarySwitch class with null catalogue.
     * It verifies that the constructor correctly throws an IllegalArgumentException with the message "Catalogue cannot be null".
     */
    @Test
    void constructorNullCatalogue() {
        // Arrange
        String expected = "Catalogue cannot be null";

        // Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new BinarySwitch(null, SENSOR_NAME, valueFactoryDouble));
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Tests the getName method of the BinarySwitch class.
     * It verifies that the method correctly returns the name of the sensor.
     */
    @Test
    void getName() {
        // Arrange
        SwitchOnOffActuator switchTest = mock(SwitchOnOffActuator.class);
        BinarySwitch binarySwitch = new BinarySwitch(mockCatalogue, SENSOR_NAME, valueFactoryDouble);
        binarySwitch.configureSensor(switchTest);
        String expected = "IsOnSensor";

        // Act
        String result = binarySwitch.getName();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the getSensorFunctionality method of the BinarySwitch class.
     * It verifies that the method correctly returns the functionality of the sensor.
     */
    @Test
    void getType() {
        // Arrange
        SwitchOnOffActuator switchTest = mock(SwitchOnOffActuator.class);
        BinarySwitch binarySwitch = new BinarySwitch(mockCatalogue, SENSOR_NAME, valueFactoryDouble);
        binarySwitch.configureSensor(switchTest);
        SensorFunctionality expected = SensorFunctionality.Binary_Switch;

        // Act
        SensorFunctionality result = binarySwitch.getSensorFunctionality();

        // Assert
        assertEquals(expected, result);
    }

}
