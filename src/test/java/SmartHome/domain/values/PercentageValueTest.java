package SmartHome.domain.values;

import SmartHome.domain.values.implementation.PercentageValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link PercentageValue} class, ensuring it accurately processes and validates percentage values.
 */
class PercentageValueTest {

    /**
     * Verifies that a valid percentage value is correctly accepted and properly formatted.
     */
    @Test
    void validPercentageValue() {
        // Arrange
        String validValue = "50";
        PercentageValue value = new PercentageValue();

        // Act
        boolean result = value.setValue(validValue);

        // Assert
        assertEquals(validValue + " %", value.toString());
        assertTrue(result);
    }

    /**
     * Tests that negative percentage values are correctly identified as invalid.
     */
    @Test
    void invalidPercentageValueNegative() {
        // Arrange
        String invalidValue = "-50";
        PercentageValue value = new PercentageValue();

        // Act
        boolean result = value.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Ensures that percentage values over 100% are rejected as invalid.
     */
    @Test
    void invalidPercentageValueOver100() {
        // Arrange
        String invalidValue = "150";
        PercentageValue value = new PercentageValue();

        // Act
        boolean result = value.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Confirms that a percentage value of 0% is considered valid and correctly formatted.
     */
    @Test
    void validZeroPercentageValue() {
        // Arrange
        String validValue = "0";
        PercentageValue value = new PercentageValue();

        // Act
        boolean result = value.setValue(validValue);

        // Assert
        assertEquals(validValue + " %", value.toString());
        assertTrue(result);
    }

    /**
     * Validates that a percentage value of 100% is correctly accepted and formatted.
     */
    @Test
    void valid100PercentageValue() {
        // Arrange
        String validValue = "100";
        PercentageValue value = new PercentageValue();

        // Act
        boolean result = value.setValue(validValue);

        // Assert
        assertEquals(validValue + " %", value.toString());
        assertTrue(result);
    }

    /**
     * Verifies that null inputs for percentage values are correctly identified as invalid.
     */
    @Test
    void invalidPercentageNull() {
        // Arrange
        String invalidValue = null;
        PercentageValue value = new PercentageValue();

        // Act
        boolean result = value.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Confirms that empty string inputs for percentage values are rejected as invalid.
     */
    @Test
    void invalidPercentageEmptyString() {
        // Arrange
        String invalidValue = "  ";
        PercentageValue value = new PercentageValue();

        // Act
        boolean result = value.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests that non-numeric string inputs for percentage values are correctly identified as invalid.
     */
    @Test
    void invalidPercentageString() {
        // Arrange
        String invalidValue = "abc";
        PercentageValue value = new PercentageValue();

        // Act
        boolean result = value.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Ensures that a valid percentage value can be set even after an invalid value was attempted.
     */
    @Test
    void validPercentageValueAfterInvalid() {
        // Arrange
        String invalidValue = "abc";
        String validValue = "50";
        PercentageValue value = new PercentageValue();

        // Act
        boolean result = value.setValue(invalidValue);
        boolean result2 = value.setValue(validValue);

        // Assert
        assertFalse(result);
        assertTrue(result2);
    }

    /**
     * Verifies that an invalid percentage value does not overwrite a previously set valid value.
     */
    @Test
    void invalidPercentageDoesNotChangeValue() {
        // Arrange
        String originalValue = "50";
        String invalidValue = "abc";
        PercentageValue value = new PercentageValue();

        // Act
        value.setValue(originalValue);
        value.setValue(invalidValue);

        // Assert
        assertEquals("50 %", value.toString());
    }

    /**
     * Tests that the measurement unit for the percentage value is correctly returned as "%".
     */
    @Test
    void validGetMeasurementUnit() {
        // Arrange
        PercentageValue value = new PercentageValue();

        // Act
        String result = value.getMeasurementUnit();

        // Assert
        assertEquals("%", result);
    }
}
