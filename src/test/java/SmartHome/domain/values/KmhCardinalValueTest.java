package SmartHome.domain.values;

import SmartHome.domain.sensors.implementation.WindDirection;
import SmartHome.domain.values.implementation.KmhCardinalValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link KmhCardinalValue} class, focusing on the correct handling of wind speed
 * and direction values.
 */
class KmhCardinalValueTest {

    /**
     * Verifies that a valid wind speed is correctly set and retrieved.
     */
    @Test
    void validWindSpeed() {
        // Arrange
        String speed = "20.0";
        KmhCardinalValue kmhCardinalValue = new KmhCardinalValue();
        kmhCardinalValue.setValue(speed);
        String expected = "20.0 km/h";

        // Act & Assert
        assertEquals(expected, kmhCardinalValue.getWindSpeed());
    }

    /**
     * Tests the setting and getting of a valid wind direction.
     */
    @Test
    void validWindDirection() {
        // Arrange
        WindDirection direction = WindDirection.SW;
        KmhCardinalValue kmhCardinalValue = new KmhCardinalValue();
        kmhCardinalValue.setDirection(direction);

        // Act & Assert
        assertEquals(String.valueOf(direction), String.valueOf(kmhCardinalValue.getWindDirection()));
        assertTrue(kmhCardinalValue.setDirection(direction));
    }

    /**
     * Verifies that setting a null wind direction is handled correctly and returns false.
     */
    @Test
    void invalidWindDirection() {
        // Arrange
        WindDirection direction = null;
        KmhCardinalValue kmhCardinalValue = new KmhCardinalValue();

        // Act & Assert
        assertFalse(kmhCardinalValue.setDirection(direction));
    }

    /**
     * Checks if the composite value of speed and direction is correctly returned as a string.
     */
    @Test
    void getValue() {
        // Arrange
        String speed = "15.0";
        WindDirection direction = WindDirection.NE;
        String measurementUnit = new KmhCardinalValue().getMeasurementUnit();
        KmhCardinalValue kmhCardinalValue = new KmhCardinalValue();
        kmhCardinalValue.setValue(speed);
        kmhCardinalValue.setDirection(direction);

        // Act
        String expected = speed + " " + measurementUnit + " pointing to: " + direction;
        String result = kmhCardinalValue.toString();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests that an invalid (negative) wind speed is correctly identified and rejected.
     */
    @Test
    void invalidWindSpeed() {
        // Arrange
        KmhCardinalValue kmhCardinalValue = new KmhCardinalValue();
        String invalidSpeed = "-10.0";

        // Act & Assert
        assertFalse(kmhCardinalValue.setValue(invalidSpeed));
    }

    /**
     * Confirms that setting a wind speed to zero is valid and correctly updates the speed value.
     */
    @Test
    void setSpeedZero() {
        // Arrange
        KmhCardinalValue kmhCardinalValue = new KmhCardinalValue();

        // Act
        kmhCardinalValue.setValue("0");

        // Assert
        assertEquals("0 km/h", kmhCardinalValue.getWindSpeed());
    }

    /**
     * Verifies that setting a null value for wind speed does not throw an exception
     * and is handled gracefully.
     */
    @Test
    void setNullValue() {
        // Arrange
        KmhCardinalValue kmhCardinalValue = new KmhCardinalValue();

        // Act
        kmhCardinalValue.setValue(null);

        // Assert
        assertEquals(null, kmhCardinalValue.toString());
    }
}
