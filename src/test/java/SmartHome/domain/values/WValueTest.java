package SmartHome.domain.values;

import SmartHome.domain.values.implementation.WValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link WValue} class, ensuring it correctly handles setting and validating
 * power consumption values.
 */
class WValueTest {

    /**
     * Verifies that a valid power consumption value is correctly set and represented as a string.
     */
    @Test
    void validInstantPowerConsumptionValue() {
        // Arrange
        String value = "10.0";

        // Act
        WValue instantPowerConsumptionValue = new WValue();
        instantPowerConsumptionValue.setValue(value);

        // Assert
        assertEquals(value + " " + instantPowerConsumptionValue.getMeasurementUnit(), instantPowerConsumptionValue.toString());
    }

    /**
     * Tests that an invalid (negative) power consumption value is correctly rejected.
     */
    @Test
    void invalidInstantPowerConsumptionValue() {
        // Arrange
        String value = "-10.0";

        // Act
        WValue instantPowerConsumptionValue = new WValue();
        boolean result = instantPowerConsumptionValue.setValue(value);

        // Assert
        assertFalse(result);
    }

    /**
     * Confirms that setting a negative power value is rejected as invalid.
     */
    @Test
    void invalidPower_Negative() {
        // Arrange
        WValue instantPowerConsumptionValue = new WValue();
        String negativeValue = "-10.0";

        // Act
        boolean result = instantPowerConsumptionValue.setValue(negativeValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Verifies that setting a positive power value is accepted as valid.
     */
    @Test
    void isValidPowerPositive() {
        // Arrange
        WValue instantPowerConsumptionValue = new WValue();
        String positiveValue = "10.0";

        // Act
        boolean result = instantPowerConsumptionValue.setValue(positiveValue);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests that setting a power value of zero is considered valid.
     */
    @Test
    void isValidPowerZero() {
        // Arrange
        WValue instantPowerConsumptionValue = new WValue();
        String zeroValue = "0.0";

        // Act
        boolean result = instantPowerConsumptionValue.setValue(zeroValue);

        // Assert
        assertTrue(result);
    }

    /**
     * Confirms that setting a valid power value updates the value correctly and is represented
     * as expected in the string output.
     */
    @Test
    void setValueValid() {
        // Arrange
        WValue instantPowerConsumptionValue = new WValue();
        String newValue = "20.0";
        String measurementUnit = instantPowerConsumptionValue.getMeasurementUnit();

        // Act
        instantPowerConsumptionValue.setValue(newValue);

        // Assert
        assertEquals(newValue + " " + measurementUnit, instantPowerConsumptionValue.toString());
    }

    /**
     * Tests that setting an invalid (negative) power value is rejected.
     */
    @Test
    void setValueInvalid() {
        // Arrange
        WValue instantPowerConsumptionValue = new WValue();
        String invalidValue = "-20.0";

        // Act
        boolean result = instantPowerConsumptionValue.setValue(invalidValue);

        // Assert
        assertFalse(result);
    }

    /**
     * Verifies that attempting to set a non-numeric power value results in rejection.
     */
    @Test
    void setValueNonNumeric() {
        // Arrange
        double value = 10.0;
        WValue instantPowerConsumptionValue = new WValue();

        // Act
        boolean result = instantPowerConsumptionValue.setValue("abc");

        // Assert
        assertFalse(result);
    }
}