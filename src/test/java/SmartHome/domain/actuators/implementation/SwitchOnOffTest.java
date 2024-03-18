package SmartHome.domain.actuators.implementation;



import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.actuators.implementation.BlindRollerActuator;
import SmartHome.domain.actuators.implementation.SwitchOnOffActuator;
import SmartHome.domain.values.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * The SwitchOnOffTest class contains unit tests for testing the functionality of the SwitchOnOffActuator class.
 * It tests the switching on and off of the actuator, as well as the retrieval of the actuator's state, type, and name.
 */
public class SwitchOnOffTest {

    ActuatorCatalogue mockCatalogue;
    String name;
    ValueFactory valueFactoryDouble;

    /**
     * Sets up the environment before each test method.
     * Initializes a new ActuatorCatalogue instance, a name, and a ValueFactory instance.
     */
    @BeforeEach
    void setup() {
        name = "onOff";
        valueFactoryDouble = mock(ValueFactory.class);
        mockCatalogue = mock(ActuatorCatalogue.class);
        when(mockCatalogue.getActuatorFunctionality(ActuatorFunctionality.On_Off)).thenReturn(ActuatorFunctionality.On_Off);
    }

    /**
     * Test case to verify if an IllegalArgumentException is thrown when a null ActuatorCatalogue is passed to the BlindRollerActuator constructor.
     */
    @Test
    void actuatorCatalogueNull (){
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new BlindRollerActuator(null, name, valueFactoryDouble));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());
    }
    /**
     * Test case to verify if the actuator can be successfully switched on.
     */
    @Test
    void switchOn() {
        // arrange
        SwitchOnOffActuator switchTest = new SwitchOnOffActuator(mockCatalogue, name, valueFactoryDouble);

        // act
        boolean result = switchTest.switchActuator();

        // assert
        assertTrue(result);
    }
    /**
     * Test case to verify if the actuator can be successfully switched off.
     */
    @Test
    void switchOff() {
        // arrange
        SwitchOnOffActuator switchTest = new SwitchOnOffActuator(mockCatalogue, name, valueFactoryDouble);
        switchTest.switchActuator();

        // act
        boolean result = switchTest.switchActuator();

        // assert
        assertFalse(result);
    }

    /**
     * Test case to verify if the actuator is off.
     */
    @Test
    void isSwitchOff() {
        // arrange
        SwitchOnOffActuator switchTest = new SwitchOnOffActuator(mockCatalogue, name, valueFactoryDouble);

        // act
        boolean result = switchTest.isOn();

        // assert
        assertFalse(result);
    }

    /**
     * Test case to verify if the actuator is on.
     */
    @Test
    void isSwitchOn() {
        // arrange
        SwitchOnOffActuator switchTest = new SwitchOnOffActuator(mockCatalogue, name, valueFactoryDouble);
        switchTest.switchActuator();

        // act
        boolean result = switchTest.isOn();

        // assert
        assertTrue(result);
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a null ActuatorCatalogue is passed to the SwitchOnOffActuator constructor.
     */
    @Test
    void constructorNullCatalogue() {
        // arrange
        String expected = "Catalogue cannot be null";

        // act + assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new SwitchOnOffActuator(null, name, valueFactoryDouble));

        // assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the actuator type is correctly retrieved.
     */
    @Test
    void getType() {
        // arrange
        SwitchOnOffActuator switchTest = new SwitchOnOffActuator(mockCatalogue, name, valueFactoryDouble);
        ActuatorFunctionality expected = ActuatorFunctionality.On_Off;

        // act
        ActuatorFunctionality result = switchTest.getActuatorFunctionality();

        // assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the actuator name is correctly retrieved.
     */
    @Test
    void getName() {
        // Arrange
        SwitchOnOffActuator switchOnOffActuator = new SwitchOnOffActuator(mockCatalogue, name, valueFactoryDouble);

        // Assert
        assertEquals(name, switchOnOffActuator.getName());
    }
    /**
     * Test case to verify if the actuator reading is correctly retrieved.
     */
    @Test
    void getReading() {
        // arrange
        SwitchOnOffActuator switchTest = new SwitchOnOffActuator(mockCatalogue, name, valueFactoryDouble);
        switchTest.switchActuator();

        // act
        String result = String.valueOf(switchTest.isOn());

        // assert
        assertEquals("true", result);
    }
    /**
     * Test case to verify if the initial state of the switch actuator is off.
     * The state of the switch actuator is retrieved using the getReading method.
     *
     * @return The state of the switch actuator as a string (should be "false").
     */
    @Test
    void getReadingFalse() {
        // Act
        SwitchOnOffActuator switchOnOffActuator = new SwitchOnOffActuator(mockCatalogue, name, valueFactoryDouble);
        String result = switchOnOffActuator.getReading();

        // Assert
        assertEquals("false", result, "The initial state of the switch should be off (false)");
    }
    /**
     * Test case to verify if the state of the switch actuator is on after switching.
     * The state of the switch actuator is retrieved using the getReading method.
     *
     * @return The state of the switch actuator as a string (should be "true").
     */
    @Test
    void getReadingTrue() {
        // Act
        SwitchOnOffActuator switchOnOffActuator = new SwitchOnOffActuator(mockCatalogue, name, valueFactoryDouble);
        switchOnOffActuator.switchActuator();
        String result = switchOnOffActuator.getReading();

        // Assert
        assertEquals("true", result, "After switching, the state of the switch should be on (true)");
    }
}
