package SmartHome.domain.values;

import SmartHome.domain.values.implementation.RangeActuatorFractionalValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link RangeActuatorFractionalValue} class, focusing on verifying the correct handling
 * of decimal values within a specified range.
 */
class RangeActuatorDecimalValueTest {
    private double lowerLimit;
    private double upperLimit;

    /**
     * Sets up the test environment with predefined lower and upper limits for the range actuator.
     */
    @BeforeEach
    void setup() {
        lowerLimit = -1.0;
        upperLimit = 1.0;
    }

    /**
     * Tests the creation of a RangeActuatorFractionalValue object with specified lower and upper limits.
     */
    @Test
    void createRangeActuatorFractionalValueSuccessfully() {
        RangeActuatorFractionalValue rangeActuatorFractionalValue = new RangeActuatorFractionalValue(lowerLimit, upperLimit);
    }

    /**
     * Verifies that a value within the range can be successfully set.
     */
    @Test
    void setValueRangeActuatorFractionalValue() {
        // Arrange
        String measured = "0";

        RangeActuatorFractionalValue rangeActuatorFractionalValue = new RangeActuatorFractionalValue(lowerLimit, upperLimit);

        // Act + Assert
        assertTrue(rangeActuatorFractionalValue.setValue(measured));
    }

    /**
     * Ensures that setting a value equal to the upper boundary is accepted.
     */
    @Test
    void setMeasurementValueEqualsToUpperToBoundary() {
        // Arrange
        RangeActuatorFractionalValue rangeActuatorFractionalValue = new RangeActuatorFractionalValue(lowerLimit, upperLimit);

        // Assert
        assertTrue(rangeActuatorFractionalValue.setValue("1"));
    }

    /**
     * Confirms that setting a value equal to the lower boundary is accepted.
     */
    @Test
    void setMeasurementValueEqualsToLowerToBoundary() {
        // Arrange
        RangeActuatorFractionalValue rangeActuatorFractionalValue = new RangeActuatorFractionalValue(lowerLimit, upperLimit);

        // Assert
        assertTrue(rangeActuatorFractionalValue.setValue("-1"));
    }

    /**
     * Tests retrieving the value set for the RangeActuatorFractionalValue.
     */
    @Test
    void getValue() {
        // Arrange
        RangeActuatorFractionalValue rangeActuatorFractionalValue = new RangeActuatorFractionalValue(lowerLimit, upperLimit);
        rangeActuatorFractionalValue.setValue("0");

        // Act
        String result = rangeActuatorFractionalValue.toString();
        String expected = "0.0";

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that a value set within the boundaries is correctly accepted.
     */
    @Test
    void setMeasurementValueInsideBoundary() {
        // Arrange
        RangeActuatorFractionalValue rangeActuatorFractionalValue = new RangeActuatorFractionalValue(lowerLimit, upperLimit);

        // Assert
        assertTrue(rangeActuatorFractionalValue.setValue("0"));
    }

    /**
     * Asserts that setting a value larger than the allowed upper limit is rejected.
     */
    @Test
    void setMeasurementValueBiggerThanBoundary() {
        // Arrange
        RangeActuatorFractionalValue rangeActuatorFractionalValue = new RangeActuatorFractionalValue(lowerLimit, upperLimit);

        // Assert
        assertFalse(rangeActuatorFractionalValue.setValue("2"));
    }

    /**
     * Checks that setting a value smaller than the allowed lower limit is rejected.
     */
    @Test
    void setMeasurementValueSmallerThanBoundary() {
        // Arrange
        RangeActuatorFractionalValue rangeActuatorFractionalValue = new RangeActuatorFractionalValue(lowerLimit, upperLimit);

        // Assert
        assertFalse(rangeActuatorFractionalValue.setValue("-2"));
    }

    /**
     * Tests getting the measurement unit for values managed by RangeActuatorFractionalValue.
     */
    @Test
    void getMeasurementUnit() {
        // Arrange
        RangeActuatorFractionalValue rangeActuatorFractionalValue = new RangeActuatorFractionalValue(lowerLimit, upperLimit);

        // Act
        String result = rangeActuatorFractionalValue.getMeasurementUnit();
        String expected = "Double precision";

        // Assert
        assertEquals(expected, result);
    }
}