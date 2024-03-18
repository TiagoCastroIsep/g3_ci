package SmartHome.domain.values;

import SmartHome.domain.values.implementation.Wm2Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link Wm2Value} class, focusing on ensuring correct behavior for
 * initialization, measurement unit retrieval, and value setting for solar irradiance.
 */
class Wm2ValueTest {

    /**
     * Verifies that the Wm2Value constructor initializes an object successfully.
     */
    @Test
    void constructorTest() {
        // Arrange

        // Act
        Wm2Value solarIrradianceSensor = new Wm2Value();

        // Assert
        assertNotNull(solarIrradianceSensor);
    }

    /**
     * Tests if the correct measurement unit "W/m2" is returned for solar irradiance values.
     */
    @Test
    void getMeasurementUnitTest() {
        // Arrange
        Wm2Value solarIrradianceSensor = new Wm2Value();
        String expected = "W/m2";

        // Act
        String actual = solarIrradianceSensor.getMeasurementUnit();

        // Assert
        assertEquals(actual, expected);
    }

    /**
     * Verifies that a positive solar irradiance value is accepted and correctly represented.
     */
    @Test
    void positiveSolarIrradiance() {
        // Arrange
        String validValue = "23.0";
        Wm2Value solarIrradianceValue = new Wm2Value();

        // Act
        boolean result = solarIrradianceValue.setValue(validValue);

        // Assert
        assertEquals(validValue + " W/m2", solarIrradianceValue.toString());
        assertTrue(result);
    }

    /**
     * Tests that negative solar irradiance values are also considered valid and correctly represented.
     */
    @Test
    void negativeSolarIrradiance() {
        // Arrange
        String validValue = "-10.0";
        Wm2Value solarIrradianceValue = new Wm2Value();

        // Act
        boolean result = solarIrradianceValue.setValue(validValue);

        // Assert
        assertEquals(validValue + " W/m2", solarIrradianceValue.toString());
        assertTrue(result);
    }

    /**
     * Confirms that null values are correctly rejected as invalid solar irradiance inputs.
     */
    @Test
    void invalidNullSolarIrradiance() {
        // Arrange
        String invalidValue = null;
        Wm2Value solarIrradianceValue = new Wm2Value();

        // Act
        boolean result = solarIrradianceValue.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Verifies that empty strings are rejected as invalid values for solar irradiance.
     */
    @Test
    void invalidEmptyStringSolarIrradiance() {
        // Arrange
        String invalidValue = "  ";
        Wm2Value solarIrradianceValue = new Wm2Value();

        // Act
        boolean result = solarIrradianceValue.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests that non-numeric strings are considered invalid inputs for solar irradiance values.
     */
    @Test
    void invalidStringSolarIrradiance() {
        // Arrange
        String invalidValue = "temp ";
        Wm2Value solarIrradianceValue = new Wm2Value();

        // Act
        boolean result = solarIrradianceValue.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Ensures that attempting to set an invalid value (non-numeric string) does not alter the
     * previously set valid solar irradiance value.
     */
    @Test
    void invalidCelsiusDoesNotChangeValue() {
        // Arrange
        String originalValue = "30";
        String invalidValue = "abc";
        Wm2Value solarIrradianceValue = new Wm2Value();

        // Act
        solarIrradianceValue.setValue(originalValue);
        solarIrradianceValue.setValue(invalidValue);

        // Assert
        assertEquals("30.0 W/m2", solarIrradianceValue.toString());
    }
}
