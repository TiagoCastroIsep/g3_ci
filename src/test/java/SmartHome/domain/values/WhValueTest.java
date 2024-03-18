package SmartHome.domain.values;

import SmartHome.domain.values.implementation.WhValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link WhValue} class to ensure it properly handles energy consumption values.
 */
class WhValueTest {

    /**
     * Tests that a valid energy consumption value is correctly accepted and represented as a string.
     */
    @Test
    void validConsumptionValue() {
        // Arrange
        double validAverageValue = 100.5;
        WhValue value = new WhValue();

        // Act
        boolean result = value.setValue(String.valueOf(validAverageValue));

        // Assert
        assertEquals(validAverageValue + " Wh", value.toString());
    }

    /**
     * Verifies that negative energy consumption values are correctly rejected as invalid.
     */
    @Test
    void invalidConsumptionValueNegative() {
        // Arrange
        double invalidAverageValue = -10.0;
        WhValue value = new WhValue();

        // Act
        boolean result = value.setValue(String.valueOf(invalidAverageValue));

        // Assert
        assertFalse(result);
    }

    /**
     * Confirms that an energy consumption value of zero is considered valid and correctly represented.
     */
    @Test
    void validZeroConsumptionValue() {
        // Arrange
        double edgeValue = 0;
        WhValue value = new WhValue();

        // Act
        boolean result = value.setValue(String.valueOf(edgeValue));

        // Assert
        assertEquals(edgeValue + " Wh", value.toString());
    }

    /**
     * Ensures that the measurement unit for energy consumption is correctly reported as watt-hours (Wh).
     */
    @Test
    void validGetUnit() {
        // Arrange
        WhValue value = new WhValue();

        // Act
        String result = value.getMeasurementUnit();

        // Assert
        assertEquals("Wh", result);
    }

    /**
     * Tests that non-numeric strings are correctly identified as invalid for setting energy consumption values.
     */
    @Test
    void setValueNotValidNumber() {
        // Arrange
        WhValue value = new WhValue();
        String measured = "A";

        // Act
        assertFalse(value.setValue(measured));
    }

    /**
     * Verifies that energy consumption values less than zero are correctly identified as invalid.
     */
    @Test
    void setValueNumberLessThanZero() {
        // Arrange
        double invalidAverageValue = -0.1;
        WhValue value = new WhValue();

        // Act
        boolean result = value.setValue(String.valueOf(invalidAverageValue));

        // Assert
        assertFalse(result);
    }

    /**
     * Confirms that energy consumption values greater than zero are correctly accepted as valid.
     */
    @Test
    void setValueNumberMoreThanZero() {
        // Arrange
        double invalidAverageValue = 0.1;
        WhValue value = new WhValue();

        // Act
        boolean result = value.setValue(String.valueOf(invalidAverageValue));

        // Assert
        assertTrue(result);
    }
}
