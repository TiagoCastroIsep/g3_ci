package SmartHome.domain.values;

import SmartHome.domain.values.implementation.CelsiusValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link CelsiusValue} class to ensure it correctly handles temperature values in Celsius.
 */
class CelsiusValueTest {

    /**
     * Verifies that the CelsiusValue object is instantiated successfully.
     */
    @Test
    void constructorTest() {
        // Arrange

        // Act
        CelsiusValue celsiusValue = new CelsiusValue();

        // Assert
        assertNotNull(celsiusValue);
    }

    /**
     * Confirms that the measurement unit for the temperature value is correctly reported as degrees Celsius (ºC).
     */
    @Test
    void getMeasurementUnitTest() {
        // Arrange
        CelsiusValue celsiusValue = new CelsiusValue();
        String expected = "ºC";

        // Act
        String actual = celsiusValue.getMeasurementUnit();

        // Assert
        assertEquals(actual, expected);
    }

    /**
     * Tests that a positive temperature value in Celsius is accepted and correctly formatted.
     */
    @Test
    void positiveCelsiusValue() {
        // Arrange
        String validValue = "23.0";
        CelsiusValue celsiusValue = new CelsiusValue();

        // Act
        boolean result = celsiusValue.setValue(validValue);

        // Assert
        assertEquals(validValue + " ºC", celsiusValue.toString());
        assertTrue(result);
    }

    /**
     * Verifies that a negative temperature value in Celsius is accepted and correctly formatted.
     */
    @Test
    void negativeCelsiusValue() {
        // Arrange
        String validValue = "-10.0";
        CelsiusValue celsiusValue = new CelsiusValue();

        // Act
        boolean result = celsiusValue.setValue(validValue);

        // Assert
        assertEquals(validValue + " ºC", celsiusValue.toString());
        assertTrue(result);
    }

    /**
     * Ensures that setting a null value for the temperature is rejected.
     */
    @Test
    void invalidNullCelsiusValue() {
        // Arrange
        String invalidValue = null;
        CelsiusValue celsiusValue = new CelsiusValue();

        // Act
        boolean result = celsiusValue.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Checks that an empty string is not accepted as a valid temperature value.
     */
    @Test
    void invalidEmptyStringCelsiusValue() {
        // Arrange
        String invalidValue = "  ";
        CelsiusValue celsiusValue = new CelsiusValue();

        // Act
        boolean result = celsiusValue.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Confirms that non-numeric strings are rejected when setting a temperature value.
     */
    @Test
    void invalidStringCelsiusValue() {
        // Arrange
        String invalidValue = "temp ";
        CelsiusValue celsiusValue = new CelsiusValue();

        // Act
        boolean result = celsiusValue.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Verifies that an invalid (non-numeric) temperature value does not overwrite a previously valid value.
     */
    @Test
    void invalidCelsiusDoesNotChangeValue() {
        // Arrange
        String originalValue = "30";
        String invalidValue = "abc";
        CelsiusValue celsiusValue = new CelsiusValue();

        // Act
        celsiusValue.setValue(originalValue);
        celsiusValue.setValue(invalidValue);

        // Assert
        assertEquals("30.0 ºC", celsiusValue.toString());
    }
}
