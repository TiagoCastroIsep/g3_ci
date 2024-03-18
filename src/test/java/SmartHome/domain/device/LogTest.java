package SmartHome.domain.device;

import SmartHome.domain.device.Log;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {

    /**
     * Tests the creation of a Log object with a null message.
     * It should throw an IllegalArgumentException exception.
     */
    @Test
    public void createValidLog() {
        // Arrange
        LocalDateTime time = LocalDateTime.now();
        String message = "Device deactivated";
        Log log = new Log(time, message);

        // Act
        String result = log.getLog();

        //Assert
        assertTrue(result.contains(message));
    }

    /**
     * Tests the creation of a Log object with a null message.
     * It should throw an IllegalArgumentException exception.
     */
    @Test
    public void createLogNullMessage() {
        // Arrange
        LocalDateTime time = LocalDateTime.now();
        String message = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Log(time, message));
        assertEquals("Message cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the creation of a Log object with an empty message.
     * It should throw an IllegalArgumentException exception.
     */

    @Test
    public void createLogEmptyMessage() {
        // Arrange
        LocalDateTime time = LocalDateTime.now();
        String message = "";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Log(time, message));
        assertEquals("Message cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the creation of a Log object with a null time.
     * It should throw an IllegalArgumentException exception.
     */

    @Test
    void createLogNullTime() {
        // Arrange
        LocalDateTime time = null;
        String message = "Test message";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Log(time, message));
        assertEquals("Time cannot be null", exception.getMessage());
    }
}