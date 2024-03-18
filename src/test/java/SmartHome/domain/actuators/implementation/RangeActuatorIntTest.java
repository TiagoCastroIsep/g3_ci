package SmartHome.domain.actuators.implementation;

import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.actuators.implementation.RangeActuatorInt;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * The RangeActuatorIntTest class contains unit tests for testing the functionality of the RangeActuatorInt class.
 * It tests the creation of the actuator, configuration of the actuator, getting the name, measurement, functionality, lower limit, upper limit, and setting the measurement.
 */
class RangeActuatorIntTest {
    private ActuatorCatalogue actuatorCatalogueDouble;
    private String name;
    private int lowerLimit;
    private int upperLimit;
    private RangeActuatorInt rangeActuatorInt;
    private Value rangeActuatorIntValueDouble;
    private ValueFactory rangeActuatorIntValueFactoryDouble;
    /**
     * Sets up the environment before each test method.
     * Initializes a new RangeActuatorInt instance, an ActuatorCatalogue instance, a name, lower limit, upper limit, and a ValueFactory instance.
     */
    @BeforeEach
    void setup() {
        actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
        name = "rangeAct";
        lowerLimit = -1;
        upperLimit = 1;

        /** START - creating mocks **/
        rangeActuatorIntValueDouble = mock(Value.class);
        rangeActuatorIntValueFactoryDouble = mock(ValueFactory.class);
        when(rangeActuatorIntValueFactoryDouble.createRangeActuatorInt(lowerLimit, upperLimit)).thenReturn(rangeActuatorIntValueDouble);
        /** END - creating mocks **/

        rangeActuatorInt = new RangeActuatorInt(actuatorCatalogueDouble, name, rangeActuatorIntValueFactoryDouble);

        rangeActuatorInt.configureActuator(name, lowerLimit, upperLimit, rangeActuatorIntValueFactoryDouble);
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a null ActuatorCatalogue is passed to the RangeActuatorInt constructor.
     */
    @Test
    void actuatorCatalogueNull() {
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new RangeActuatorInt(null, name, rangeActuatorIntValueFactoryDouble));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());
    }
    /**
     * Test case to verify if a RangeActuatorInt can be successfully created.
     */
    @Test
    void createRangeActuatorIntSuccessfully() {
        RangeActuatorInt rangeActuatorInt = new RangeActuatorInt(actuatorCatalogueDouble, name, rangeActuatorIntValueFactoryDouble);
    }
    /**
     * Test case to verify if a RangeActuatorInt can be successfully configured.
     */
    @Test
    void configureRangeActuatorInSuccessfully() {
        assertTrue(rangeActuatorInt.configureActuator(name, lowerLimit, upperLimit, rangeActuatorIntValueFactoryDouble));
    }
    /**
     * Test case to verify if a RangeActuatorInt cannot be configured with a null or empty name.
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void configureRangeActuatorIntNullNameOrEmpty(String name) {
        assertFalse(rangeActuatorInt.configureActuator(name, lowerLimit, upperLimit, rangeActuatorIntValueFactoryDouble));
    }

    /**
     * Test case to verify if the name of a RangeActuatorInt can be successfully retrieved.
     */
    @Test
    void getName() {
        // Act
        String result = rangeActuatorInt.getName();
        String expected = name;

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the measurement of a RangeActuatorInt can be successfully retrieved.
     */
    @Test
    void getMeasurementValidStringValue() {
        // Arrange
        when(rangeActuatorIntValueDouble.toString()).thenReturn("0");

        // Act
        String result = rangeActuatorInt.getReading();
        String expected = "0";

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the functionality of a RangeActuatorInt can be successfully retrieved.
     */
    @Test
    void getActuatorFunctionality() {
        // Act
        ActuatorFunctionality result = rangeActuatorInt.getActuatorFunctionality();
        ActuatorFunctionality expected = ActuatorFunctionality.Range;

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the lower limit of a RangeActuatorInt can be successfully retrieved.
     */
    @Test
    void getLowerLimit() {
        // Act
        int result = rangeActuatorInt.getLowerLimit();
        int expected = -1;

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the upper limit of a RangeActuatorInt can be successfully retrieved.
     */
    @Test
    void getUpperLimit() {
        // Act
        int result = rangeActuatorInt.getUpperLimit();
        int expected = 1;

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the measurement of a RangeActuatorInt can be successfully set within the boundary.
     */
    @Test
    void setMeasurementValueInsideBoundary() {
        when(rangeActuatorIntValueDouble.setValue("0")).thenReturn(true);

        // Assert
        assertTrue(rangeActuatorInt.setMeasurement("0"));
    }
    /**
     * Test case to verify if the measurement of a RangeActuatorInt cannot be set beyond the upper boundary.
     */
    @Test
    void setMeasurementValueBiggerThanBoundary() {
        when(rangeActuatorIntValueDouble.setValue("2")).thenReturn(false);
        // Assert
        assertFalse(rangeActuatorInt.setMeasurement("2"));
    }
    /**
     * Test case to verify if the measurement of a RangeActuatorInt cannot be set beyond the lower boundary.
     */
    @Test
    void setMeasurementValueSmallerThanBoundary() {
        when(rangeActuatorIntValueDouble.setValue("-2")).thenReturn(false);

        // Assert
        assertFalse(rangeActuatorInt.setMeasurement("-2"));
    }
    /**
     * Test case to verify if the measurement of a RangeActuatorInt cannot be set with a non-numeric string.
     */
    @Test
    void setMeasurementValueStringDontConvertToNumber() {
        when(rangeActuatorIntValueDouble.setValue("A")).thenReturn(false);

        // Assert
        assertFalse(rangeActuatorInt.setMeasurement("A"));
    }
    /**
     * Test case to verify if the measurement units of a RangeActuatorInt can be successfully retrieved.
     */
    @Test
    void getMeasurementUnitsSuccessfully() {
        when(rangeActuatorIntValueDouble.getMeasurementUnit()).thenReturn("Integer");

        // Arrange
        String result = rangeActuatorInt.getMeasurementUnits();
        String expected = "Integer";

        // Act
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if setting the measurement of a RangeActuatorInt with a non-numeric string throws a NumberFormatException.
     */
    @Test
    void setMeasurementThrowsNumberFormatException() {
        // Arrange
        String invalidMeasurement = "notANumber";
        when(rangeActuatorIntValueDouble.setValue(invalidMeasurement)).thenThrow(NumberFormatException.class);

        // Act
        boolean result = rangeActuatorInt.setMeasurement(invalidMeasurement);

        // Assert
        assertFalse(result);
    }
    /**
     * Test case to verify if setting the measurement of a RangeActuatorInt without configuring the actuator fails.
     */
    @Test
    void setMeasurementWithoutActuatorConfigured() {
        // Arrange
        RangeActuatorInt rangeAI = new RangeActuatorInt(actuatorCatalogueDouble, "ra1", rangeActuatorIntValueFactoryDouble);

        // Assert
        assertFalse(rangeAI.setMeasurement("1"));
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a null  name is passed to the RangeActuatorInt constructor.
     */
    @Test
    void constructorNullName() {
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new RangeActuatorInt(actuatorCatalogueDouble, null, rangeActuatorIntValueFactoryDouble));

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a empty name is passed to the RangeActuatorInt constructor.
     */
    @Test
    void constructorEmptyName() {
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new RangeActuatorInt(actuatorCatalogueDouble, "", rangeActuatorIntValueFactoryDouble));

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    /**
     * Test case to verify if an IllegalArgumentException is thrown when a null ValueFactory is passed to the RangeActuatorInt constructor.
     */
    @Test
    void constructorNullValueFactory() {
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new RangeActuatorInt(actuatorCatalogueDouble, name, null));

        // Assert
        assertEquals("ValueFactory can't be null", exception.getMessage());
    }
}