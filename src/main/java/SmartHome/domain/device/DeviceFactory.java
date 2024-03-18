package SmartHome.domain.device;

import SmartHome.domain.device.Device;

/**
 * A factory class responsible for creating instances of the Device class.
 * It provides a method to create a device with a specified name, device model, and repository.
 */
public class DeviceFactory {

    /**
     * Creates a new device with the given name, device model, and repository.
     *
     * @param name         The name of the device.
     * @param deviceModel  The model of the device.
     * @return A new instance of the Device class.
     */
    public Device createDevice(String name, String deviceModel){
        return new Device(name, deviceModel);
    }
}
