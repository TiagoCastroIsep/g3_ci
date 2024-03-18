package SmartHome.domain.values;

import SmartHome.domain.values.implementation.RangeActuatorIntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for {@link RangeActuatorIntValue}, focusing on its ability to handle integer values
 * within a defined range accurately.
 */
class RangeActuatorIntValueTest {
    private int lowerLimit;
    private int upperLimit;

    /**
     * Sets up the test environment with predefined lower and upper limits for the range actuator.
     */
    @BeforeEach
    void setup() {
        lowerLimit = -1;
        upperLimit = 1;
    }

    /**
     * Verifies the successful creation of a {@link RangeActuatorIntValue} object with specific lower and upper limits.
     */
    @Test
    void createRangeActuatorIntValueSuccessfully() {
        RangeActuatorIntValue rangeActuatorIntValue = new RangeActuatorIntValue(lowerLimit, upperLimit);
    }

    /**
     * Tests that a value within the specified range can be set successfully.
     */
    @Test
    void setValueRangeActuatorIntValue() {
        // Arrange
        String measured = "0";

        RangeActuatorIntValue rangeActuatorIntValue = new RangeActuatorIntValue(lowerLimit, upperLimit);

        // Assert
        assertTrue(rangeActuatorIntValue.setValue(measured));
    }

    /**
     * Verifies that the getter method returns the correct value that was set.
     */
    @Test
    void getValue() {
        // Arrange
        RangeActuatorIntValue rangeActuatorIntValue = new RangeActuatorIntValue(lowerLimit, upperLimit);
        rangeActuatorIntValue.setValue("0");

        // Act
        String result = rangeActuatorIntValue.toString();
        String expected = "0";

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Confirms that setting a value exactly at the boundary limits is successfully accepted.
     */
    @Test
    void setMeasurementValueInsideBoundary() {
        // Arrange
        RangeActuatorIntValue rangeActuatorIntValue = new RangeActuatorIntValue(lowerLimit, upperLimit);

        // Assert
        assertTrue(rangeActuatorIntValue.setValue("0"));
    }

    /**
     * Ensures that a value exactly equal to the upper boundary is correctly accepted.
     */
    @Test
    void setMeasurementValueEqualsToUpperToBoundary() {
        // Arrange
        RangeActuatorIntValue rangeActuatorIntValue = new RangeActuatorIntValue(lowerLimit, upperLimit);

        // Assert
        assertTrue(rangeActuatorIntValue.setValue("1"));
    }

    /**
     * Confirms that a value exactly equal to the lower boundary is correctly accepted.
     */
    @Test
    void setMeasurementValueEqualsToLowerToBoundary() {
        // Arrange
        RangeActuatorIntValue rangeActuatorIntValue = new RangeActuatorIntValue(lowerLimit, upperLimit);

        // Assert
        assertTrue(rangeActuatorIntValue.setValue("-1"));
    }

    /**
     * Tests that setting a value beyond the specified upper limit is correctly rejected.
     */
    @Test
    void setMeasurementValueBiggerThanBoundary() {
        // Arrange
        RangeActuatorIntValue rangeActuatorIntValue = new RangeActuatorIntValue(lowerLimit, upperLimit);

        // Assert
        assertFalse(rangeActuatorIntValue.setValue("2"));
    }

    /**
     * Tests that setting a value below the specified lower limit is correctly rejected.
     */
    @Test
    void setMeasurementValueSmallerThanBoundary() {
        // Arrange
        RangeActuatorIntValue rangeActuatorIntValue = new RangeActuatorIntValue(lowerLimit, upperLimit);

        // Assert
        assertFalse(rangeActuatorIntValue.setValue("-2"));
    }

    /**
     * Verifies the measurement unit reported by {@link RangeActuatorIntValue} is as expected.
     */
    @Test
    void getMeasurementUnit() {
        // Arrange
        RangeActuatorIntValue rangeActuatorIntValue = new RangeActuatorIntValue(lowerLimit, upperLimit);

        // Act
        String result = rangeActuatorIntValue.getMeasurementUnit();
        String expected = "Integer";

        // Assert
        assertEquals(expected, result);
    }
}