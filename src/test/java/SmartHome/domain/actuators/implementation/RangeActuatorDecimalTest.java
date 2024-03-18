package SmartHome.domain.actuators.implementation;

import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.actuators.implementation.RangeActuatorDecimal;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * The RangeActuatorDecimalTest class contains unit tests for testing the functionality of the RangeActuatorDecimal class.
 * It tests the creation of the actuator, getting the measurement unit, actuator type, name, and reading.
 */
class RangeActuatorDecimalTest {
    private String name;
    double lowerLimit;
    double upperLimit;
    double precision;
    private RangeActuatorDecimal rangeActuatorDecimal;
    private ActuatorCatalogue actuatorCatalogueDouble;
    private Value rangeActuatorFractionalValueDouble;
    private ValueFactory valueFactoryDouble;
    /**
     * Sets up the environment before each test method.
     * Initializes a new RangeActuatorDecimal instance, an ActuatorCatalogue instance, a name, and a ValueFactory instance.
     */
    @BeforeEach
    void setup() {
        actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
        name = "rangeAct";
        lowerLimit = -1.0;
        upperLimit = 1.0;
        precision = 0.01;

        /** START - creating mocks **/
        rangeActuatorFractionalValueDouble = mock(Value.class);
        valueFactoryDouble = mock(ValueFactory.class);
        when(valueFactoryDouble.createRangeActuatorDecimal(lowerLimit, upperLimit)).thenReturn(rangeActuatorFractionalValueDouble);
        /** END - creating mocks **/

        rangeActuatorDecimal = new RangeActuatorDecimal(actuatorCatalogueDouble, name, valueFactoryDouble);

        rangeActuatorDecimal.configureActuator(name, lowerLimit, upperLimit, precision, valueFactoryDouble);
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a null ActuatorCatalogue is passed to the RangeActuatorDecimal constructor.
     */
    @Test
    void actuatorCatalogueNull() {
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new RangeActuatorDecimal(null, name, valueFactoryDouble));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a null or empty name is passed to the RangeActuatorDecimal constructor.
     */
    @Test
    void constructorNullOrEmptyName() {
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new RangeActuatorDecimal(actuatorCatalogueDouble, null, valueFactoryDouble));

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a  empty name is passed to the RangeActuatorDecimal constructor.
     */
    @Test
    void constructorEmptyName() {
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new RangeActuatorDecimal(actuatorCatalogueDouble, "", valueFactoryDouble));

        // Assert
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }
    /**
     * Test case to verify if an IllegalArgumentException is thrown when a null ValueFactory is passed to the RangeActuatorDecimal constructor.
     */
    @Test
    void valueFactoryNull (){
        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new RangeActuatorDecimal(actuatorCatalogueDouble, name, null));

        // Assert
        assertEquals("ValueFactory can't be null", exception.getMessage());
    }
    /**
     * Test case to verify if a RangeActuatorDecimal can be successfully created.
     */
    @Test
    void createRangeActuatorFractionalSuccessfully() {
        RangeActuatorDecimal rangeActuatorDecimal = new RangeActuatorDecimal(actuatorCatalogueDouble, name, valueFactoryDouble);
    }
    /**
     * Test case to verify if a RangeActuatorDecimal can be successfully configured.
     */
    @Test
    void configureRangeActuatorFractionalSuccessfully() {
        assertTrue(rangeActuatorDecimal.configureActuator(name, lowerLimit, upperLimit, precision, valueFactoryDouble));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void configureRangeActuatorFractionalNullName(String name) {
        assertFalse(rangeActuatorDecimal.configureActuator(name, lowerLimit, upperLimit, precision, valueFactoryDouble));
    }
    /**
     * Test case to verify if a RangeActuatorDecimal can be configured with a null or empty name.
     */
    @Test
    void getName() {
        // Act
        String result = rangeActuatorDecimal.getName();
        String expected = name;

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the name of the RangeActuatorDecimal can be successfully retrieved.
     */
    @Test
    void getMeasurementValidStringValue() {
        // Arrange
        when(rangeActuatorFractionalValueDouble.toString()).thenReturn("0.99");

        // Act
        rangeActuatorDecimal.setMeasurement("0.99");
        String result = rangeActuatorDecimal.getReading();
        String expected = "0.99";

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the measurement of the RangeActuatorDecimal can be successfully retrieved.
     */
    @Test
    void getActuatorFunctionality() {
        // Act
        ActuatorFunctionality result = rangeActuatorDecimal.getActuatorFunctionality();
        ActuatorFunctionality expected = ActuatorFunctionality.Range;

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the functionality of the RangeActuatorDecimal can be successfully retrieved.
     */
    @Test
    void getLowerLimit() {
        // Act
        double result = rangeActuatorDecimal.getLowerLimit();
        double expected = -1.0;

        // Assert
        assertEquals(expected, result, 0.01);
    }

    /**
     * Test case to verify if the lower limit of the RangeActuatorDecimal can be successfully retrieved.
     */
    @Test
    void getUpperLimit() {
        // Act
        double result = rangeActuatorDecimal.getUpperLimit();
        double expected = 1.0;

        // Assert
        assertEquals(expected, result, 0.01);
    }
    /**
     * Test case to verify if the upper limit of the RangeActuatorDecimal can be successfully retrieved.
     */
    @Test
    void setMeasurementValueInsideBoundaryUpperLimit() {
        // Arrange
        when(rangeActuatorFractionalValueDouble.setValue("0.99")).thenReturn(true);

        // Assert
        assertTrue(rangeActuatorDecimal.setMeasurement("0.99"));
    }
    /**
     * Test case to verify if the measurement of the RangeActuatorDecimal can be successfully set within the upper limit.
     */
    @Test
    void setMeasurementValueInsideBoundaryLowerLimit() {
        // Arrange
        when(rangeActuatorFractionalValueDouble.setValue("-0.99")).thenReturn(true);
        // Assert
        assertTrue(rangeActuatorDecimal.setMeasurement("-0.99"));
    }
    /**
     * Test case to verify if the measurement of the RangeActuatorDecimal can be successfully set within the lower limit.
     */
    @Test
    void setMeasurementValueBiggerThanBoundary() {
        // Arrange
        when(rangeActuatorFractionalValueDouble.toString()).thenReturn("2.0");

        // Assert
        assertFalse(rangeActuatorDecimal.setMeasurement("2.0"));
    }
    /**
     * Test case to verify if the measurement of the RangeActuatorDecimal can be set beyond the upper limit.
     */
    @Test
    void setMeasurementValueSmallerThanBoundary() {
        // Arrange
        when(rangeActuatorFractionalValueDouble.toString()).thenReturn("-2.0");

        // Assert
        assertFalse(rangeActuatorDecimal.setMeasurement("-2.0"));
    }
    /**
     * Test case to verify if the measurement of the RangeActuatorDecimal can be set beyond the lower limit.
     */
    @Test
    void setMeasurementValueStringNotDontConvertToNumber() {
        // Assert
        assertFalse(rangeActuatorDecimal.setMeasurement("A"));
    }
    /**
     * Test case to verify if the measurement of the RangeActuatorDecimal can be set with a non-numeric string.
     */
    @Test
    void getPrecision() {
        // Act
        double result = rangeActuatorDecimal.getPrecision();
        double expected = 0.01;

        // Assert
        assertEquals(expected, result, 0.001);
    }
    /**
     * Test case to verify if the precision of the RangeActuatorDecimal can be successfully retrieved.
     */
    @Test
    void getPrecisionNegativeValue() {
        // Arrange
        precision = -0.01;
        rangeActuatorDecimal.configureActuator(name, lowerLimit, upperLimit, precision, valueFactoryDouble);

        // Act
        double result = rangeActuatorDecimal.getPrecision();
        double expected = -0.01;

        // Assert
        assertEquals(expected, result, 0.001);
    }
    /**
     * Test case to verify if the precision of the RangeActuatorDecimal can be successfully retrieved when it is a negative value.
     */
    @Test
    void getMeasurementUnitsSuccessfully() {
        // Arrange
        when(rangeActuatorDecimal.getMeasurementUnits()).thenReturn("Double precision");

        // Arrange
        String result = rangeActuatorDecimal.getMeasurementUnits();
        String expected = "Double precision";

        // Act
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if the measurement units of the RangeActuatorDecimal can be successfully retrieved.
     */
    @Test
    void setMeasurementThrowsNumberFormatException() {
        // Arrange
        String invalidMeasurement = "notANumber";
        when(rangeActuatorFractionalValueDouble.setValue(invalidMeasurement)).thenThrow(NumberFormatException.class);

        // Act
        boolean result = rangeActuatorDecimal.setMeasurement(invalidMeasurement);

        // Assert
        assertFalse(result);
    }
    /**
     * Test case to verify if a NumberFormatException is thrown when setting the measurement of the RangeActuatorDecimal with a non-numeric string.
     */
    @Test
    void setMeasurementWithoutActuatorConfigured() {
        // Arrange
        RangeActuatorDecimal rangeAF = new RangeActuatorDecimal(actuatorCatalogueDouble, "ra1", valueFactoryDouble);

        // Assert
        assertFalse(rangeAF.setMeasurement("1.0"));
    }
}