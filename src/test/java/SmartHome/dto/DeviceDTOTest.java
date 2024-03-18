package SmartHome.dto;

import SmartHome.domain.device.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class represents a set of unit tests for the DeviceDTO class.
 * It tests various methods to ensure their correctness.
 */
public class DeviceDTOTest {

    // Fields to be used in tests
    private String name;
    private String deviceModel;
    private DeviceDTO deviceDTO;

    /**
     * Set up method to initialize test data before each test case.
     */
    @BeforeEach
    public void setup() {
        // Create a new repository

        // Set initial values for name and deviceModel
        name = "d1";
        deviceModel = "dm1";

        // Create a new device using the provided data and repository
        Device device1 = new Device(name, deviceModel);

        // Create a new DeviceDTO object based on the created device
        deviceDTO = new DeviceDTO(device1);
    }

    /**
     * Test case to verify the getName() method of DeviceDTO.
     * It checks if the getName() method returns the correct name of the device.
     */
    @Test
    void getName() {
        // Arrange
        String expected = "d1";

        // Act
        String result = deviceDTO.name;

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify the getDeviceModel() method of DeviceDTO.
     * It checks if the getDeviceModel() method returns the correct device model.
     */
    @Test
    void getDeviceModel() {
        // Arrange
        String expected = "dm1";

        // Act
        String result = deviceDTO.deviceModel;

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify the isActive field of DeviceDTO with false status.
     * It checks if the isActive field of DeviceDTO is false.
     */
    @Test
    void getIsActiveWithFalseStatus() {
        // Assert
        assertFalse(deviceDTO.isActive);
    }

    /**
     * Test case to verify the isActive field of DeviceDTO with true status.
     * It checks if the isActive field of DeviceDTO is true after switching the device.
     */
    @Test
    void getIsActiveWithTrueStatus() {
        // Arrange
        // Create a new device and switch it on
        Device device = new Device("d1", "dm1");
        device.switchDevice(true);
        // Create a new DeviceDTO object based on the updated device
        deviceDTO = new DeviceDTO(device);

        // Act
        boolean result = deviceDTO.isActive;

        // Assert
        assertTrue(result);
    }
}
