package SmartHome.domain.actuators.implementation;

import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.actuators.implementation.BlindRollerActuator;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.ValueFactoryImpl;
import SmartHome.domain.values.implementation.PercentageValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * The BlindRollerActuatorTest class contains unit tests for testing the functionality of the BlindRollerActuator class.
 * It tests the creation of the actuator, getting the measurement unit, actuator type, name, and reading.
 */
class BlindRollerActuatorTest {
    BlindRollerActuator blindRollerActuatorMock;
    ActuatorCatalogue mockCatalogue;
    String name;
    ValueFactory valueFactoryDouble;
    /**
     * Sets up the environment before each test method.
     * Initializes a new BlindRollerActuator instance, an ActuatorCatalogue instance, a name, and a ValueFactory instance.
     */
    @BeforeEach
    void setUp() {
        name = "br1";
        mockCatalogue = mock(ActuatorCatalogue.class);
        valueFactoryDouble = mock(ValueFactory.class);
        blindRollerActuatorMock = new BlindRollerActuator(mockCatalogue, name, valueFactoryDouble);
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a null name is passed to the BlindRollerActuator constructor.
     */
    @Test
    void constructorNullName() {
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new BlindRollerActuator(mockCatalogue, null, valueFactoryDouble));

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a empty name is passed to the BlindRollerActuator constructor.
     */
    @Test
    void constructorEmptyName() {
        // Act
        // Act
       Throwable  exception = assertThrows(IllegalArgumentException.class,
                () -> new BlindRollerActuator(mockCatalogue, "", valueFactoryDouble));

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
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
     * Test case to verify if a BlindRollerActuator can be successfully created.
     */
    @Test
    void validSensorCreation() {
        //Arrange
        ActuatorFunctionality actuatorFunctionality = ActuatorFunctionality.BlindRoller;
        when(mockCatalogue.getActuatorFunctionality(actuatorFunctionality)).thenReturn(actuatorFunctionality);

        // Act
        blindRollerActuatorMock = new BlindRollerActuator(mockCatalogue, name, valueFactoryDouble);

        // Assert
        assertEquals(actuatorFunctionality, blindRollerActuatorMock.getActuatorFunctionality());
    }
    /**
     * Test case to verify if the measurement unit of the BlindRollerActuator can be successfully retrieved.
     */
    @Test
    void getMeasurementUnit() {
        //Arrange
        ValueFactory valueFactory = mock(ValueFactory.class);
        PercentageValue percentageValue = mock(PercentageValue.class);
        when(valueFactory.createPercentage()).thenReturn(percentageValue);
        when(percentageValue.getMeasurementUnit()).thenReturn("%");

        // Act
        String result = blindRollerActuatorMock.getMeasurementUnit(valueFactory);

        // Assert
        assertEquals("%", result);
    }
    /**
     * Test case to verify if the actuator type of the BlindRollerActuator can be successfully retrieved.
     */
    @Test
    void getActuatorType() {
        // Arrange
        BlindRollerActuator blindRollerActuatorMock = mock(BlindRollerActuator.class);
        when(blindRollerActuatorMock.getActuatorFunctionality()).thenReturn(ActuatorFunctionality.BlindRoller);

        // Act
        ActuatorFunctionality type = blindRollerActuatorMock.getActuatorFunctionality();

        // Assert
        assertEquals(ActuatorFunctionality.BlindRoller, type);
    }
    /**
     * Test case to verify if the current value of the BlindRollerActuator can be successfully assigned.
     */
    @Test
    void assignCurrValue() {
        // Arrange
        ValueFactory valueFactory = mock(ValueFactoryImpl.class);
        PercentageValue percentageValue = mock(PercentageValue.class);
        when(valueFactory.createPercentage()).thenReturn(percentageValue);

        // Act
        Value result = blindRollerActuatorMock.assignCurrValue(valueFactory);

        // Assert
        assertEquals(percentageValue, result);
    }

    /**
     * Test case to verify if the name of the BlindRollerActuator can be successfully retrieved.
     */
    @Test
    void getName() {
        // Arrange
        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(mockCatalogue, name, valueFactoryDouble);

        // Assert
        assertEquals(name, blindRollerActuator.getName());
    }
    /**
     * Test case to verify if the reading of the BlindRollerActuator can be successfully retrieved.
     */
    @Test
    void getReading() {
        // Arrange
        PercentageValue percentageValueDouble = mock(PercentageValue.class);
        when(valueFactoryDouble.createPercentage()).thenReturn(percentageValueDouble);
        when(percentageValueDouble.toString()).thenReturn("10 %");
        BlindRollerActuator blindRollerActuator = new BlindRollerActuator(mockCatalogue, name, valueFactoryDouble);

        // Assert
        assertEquals("10 %", blindRollerActuator.getReading());
    }
}