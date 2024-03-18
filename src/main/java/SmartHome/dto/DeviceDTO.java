package SmartHome.dto;

import SmartHome.domain.device.Device;

/**
 * A Data Transfer Object (DTO) for {@link Device} entities. This class is designed to encapsulate
 * the essential information of a Device object in a simplified and immutable format, making it ideal
 * for transferring data across layers or systems. It includes the device's name, model, and active state.
 */
public class DeviceDTO {
    public final String name;
    public final String deviceModel;
    public final boolean isActive;

    /**
     * Constructs a new DeviceDTO from a {@link Device} object, extracting and copying relevant
     * information such as the device's name, model, and active state.
     *
     * @param device The {@link Device} from which to extract information.
     */
    public DeviceDTO(Device device) {
        this.name = device.getName();
        this.deviceModel = device.getDeviceModel();
        this.isActive = device.getIsActive();
    }
}
