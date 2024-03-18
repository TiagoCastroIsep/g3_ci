package SmartHome.dto;

import SmartHome.domain.device.Device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides utility methods for converting {@link Device} domain objects to their corresponding
 * {@link DeviceDTO} representations and aggregating them into a map. This class is useful for
 * data transfer operations, particularly when working with APIs or interfaces that require
 * or return simplified versions of domain entities.
 */
public class DeviceMapper {

    /**
     * Converts a single {@link Device} domain object to a {@link DeviceDTO}.
     *
     * @param device The device domain object to convert.
     * @return A new {@link DeviceDTO} object containing the relevant information from the provided device.
     */
    static public DeviceDTO Domain2DTO(Device device) {
        return new DeviceDTO(device);
    }

    /**
     * Converts a list of {@link Device} domain objects to a map, where each key is a {@link DeviceDTO}
     * and its corresponding value is the original {@link Device} domain object. This is useful for
     * maintaining a reference between the original domain objects and their DTO representations.
     *
     * @param devices A list of device domain objects to convert.
     * @return A map with {@link DeviceDTO} objects as keys and their corresponding {@link Device}
     * domain objects as values.
     */
    static public Map<DeviceDTO, Device> Domain2DTO(List<Device> devices) {
        Map<DeviceDTO, Device> devicesDTOAndDevices = new HashMap<>();

        devices.forEach(device -> {
            DeviceDTO deviceDTO = DeviceMapper.Domain2DTO(device);
            devicesDTOAndDevices.put(deviceDTO, device);
        });

        return devicesDTOAndDevices;
    }
}
