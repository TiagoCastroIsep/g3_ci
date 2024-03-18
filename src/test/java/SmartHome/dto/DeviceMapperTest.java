package SmartHome.dto;

import SmartHome.domain.device.Device;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the DeviceMapper class.
 */
class DeviceMapperTest {

    /**
     * Test the conversion of Device domain object to DeviceDTO.
     */
    @Test
    void getDeviceDTOFromDomain2DTO() {
        // Arrange
        Device device = new Device("d1", "dm1");

        // Act
        DeviceDTO expected = new DeviceDTO(device);

        // Assert
        assertEquals(expected.name, device.getName());
        assertEquals(expected.deviceModel, device.getDeviceModel());
    }

    /**
     * Test the conversion of a list of Device domain objects to a Map of DeviceDTOs to Devices.
     */
    @Test
    void getMapFromDomain2DTO() {
        // Arrange
        List<Device> devices = new ArrayList<>();
        Device device = new Device("d1", "dm1");
        devices.add(device);

        // Act
        Map<DeviceDTO, Device> result = DeviceMapper.Domain2DTO(devices);
        int expected = 1;

        // Assert
        assertTrue(result.containsValue(device));
        assertEquals(expected, result.size());
    }
}
