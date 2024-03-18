package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for managing device-related operations.
 * This class provides methods to retrieve information about existing devices within the house,
 * and deactivate a specific device.
 * The controller interacts with the House domain model and uses the DeviceMapper for DTO conversion.
 * It maintains a mapping between DeviceDTOs and corresponding Device objects.
 */

public class Ctrl08 {
    House _house;
    private Map<DeviceDTO, Device> _devicesDTOAndDevices;
    /**
     * Constructs a new Ctrl08 instance with the specified house.
     *
     * @param house the house from which devices will be retrieved and deactivated
     */
    public Ctrl08 (House house) {
        this._house = house;
    }

    /**
     * Retrieves a list of devices from the associated house and converts them into DeviceDTOs.
     * The mapping between DeviceDTOs and corresponding Device objects is maintained for future reference.
     *
     * @return a map of DeviceDTOs and corresponding Device objects representing the devices in the house
     */

    public Map<DeviceDTO, Device> getDevicesFromHouse() {
        List<Room> rooms = _house.getRooms();
        for (Room room : rooms) {
            List<Device> devices = room.getDevices();
            this._devicesDTOAndDevices = DeviceMapper.Domain2DTO(devices);
        }

        if (this._devicesDTOAndDevices == null || this._devicesDTOAndDevices.isEmpty())
            return Collections.emptyMap();
        return  new HashMap<>(this._devicesDTOAndDevices);
    }

    /**
     * Retrieves the DeviceDTO based on the provided device name.
     *
     * @param name the name of the device
     * @return the DeviceDTO corresponding to the provided name, or null if no such device exists
     */

    private DeviceDTO getDeviceDto(String name) {
        for (Map.Entry<DeviceDTO, Device> entry : this._devicesDTOAndDevices.entrySet()) {
            String currentDeviceDTO = entry.getKey().name;
            if (currentDeviceDTO.equalsIgnoreCase(name))
                return entry.getKey();
        }
        return null;
    }

    /**
     * Deactivates a device based on the provided DeviceDTO.
     * The mapping between DeviceDTOs and corresponding Device objects is used to identify the target device.
     *
     * @param deviceToDeactivate the DeviceDTO of the device to be deactivated
     * @return true if the device was successfully deactivated, false otherwise
     */
    public boolean deactivate(DeviceDTO deviceToDeactivate) {
        try {
            DeviceDTO deviceDto = getDeviceDto(deviceToDeactivate.name);
            Device device = this._devicesDTOAndDevices.get(deviceDto);
            return device.switchDevice(false);
        } catch (NullPointerException e) {
            return false;
        }
    }
}