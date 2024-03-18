package SmartHome.domain.house;

import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.dto.DeviceRoomDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Organizes devices within rooms based on their sensor functionalities. This class allows for the
 * grouping of devices by their specific sensor functionality and also identifies devices without any
 * sensor functionalities. Utilizes {@link Room} and {@link DeviceRoomDTO} to structure the grouping.
 */
public class DevicesByFunctionality {
    private final List<Room> _rooms;
    private final SensorFunctionality[] _sensorFunctionalities = SensorFunctionality.values();
    private List<DeviceRoomDTO> _devicesRoom;
    private final List<DeviceRoomDTO> _devicesRoomNoFunc = new ArrayList<>();
    private final Map<String, List<DeviceRoomDTO>> _map = new HashMap<>();

    /**
     * Constructs a new DevicesByFunctionality instance with a list of rooms. Each room is analyzed
     * for devices and their sensor functionalities to enable grouping.
     *
     * @param _rooms A list of {@link Room} objects that contain devices to be grouped.
     */
    public DevicesByFunctionality(List<Room> _rooms) {
        this._rooms = _rooms;
    }

    /**
     * Processes and groups devices by their sensor functionalities across all provided rooms. Devices
     * without specific functionalities are also grouped separately. This method iterates through each
     * room and device to organize them accordingly.
     *
     * @return A map with sensor functionality names as keys and lists of {@link DeviceRoomDTO} objects as values,
     * representing the devices grouped by functionality. Returns {@code null} if no devices are found or
     * if rooms are empty.
     */
    public Map<String, List<DeviceRoomDTO>> getGroupedResult() {
        if (_rooms.isEmpty()) return null;

        for (SensorFunctionality sensorFunctionality : _sensorFunctionalities) {
            _devicesRoom = new ArrayList<>();
            for (Room room : _rooms) {
                List<Device> devices = room.getDevices();
                if (devices.isEmpty()) return null;
                devicesLoop(room, devices, sensorFunctionality);
            }
        }
        addDevicesWithoutFunctionalities();

        return _map;
    }

    /**
     * Iterates over devices in a room to group them by a specific sensor functionality. This internal
     * method is part of the grouping process and handles the device-level iteration.
     *
     * @param room The current room being processed.
     * @param devices A list of devices in the current room.
     * @param sensorFunctionality The sensor functionality to group devices by.
     */
    private void devicesLoop(Room room, List<Device> devices, SensorFunctionality sensorFunctionality) {
        for (Device device : devices) {
            List<Sensor> sensors = device.getSensors();

            sensorsLoop(room, device, sensors, sensorFunctionality);
        }
    }

    /**
     * Iterates over sensors in a device to find and group the device if its sensors match the specified
     * functionality. This method assists in building the final grouping of devices by functionality.
     *
     * @param room The room in which the device is located.
     * @param device The device being examined for sensor functionality.
     * @param sensors A list of sensors in the current device.
     * @param sensorFunctionality The specific sensor functionality to match against.
     */
    private void sensorsLoop(Room room, Device device, List<Sensor> sensors, SensorFunctionality sensorFunctionality) {
        for (Sensor sensor : sensors) {
            if (sensor.getSensorFunctionality().name().equals(sensorFunctionality.name())) {
                _devicesRoom.add(new DeviceRoomDTO(device.getName(), room.getName()));
                _map.put(sensorFunctionality.name(), _devicesRoom);
            }
        }
    }

    /**
     * Identifies and groups devices that lack specific sensor functionalities. This method complements
     * the primary grouping functionality by ensuring devices without sensor features are also categorized.
     */
    private void addDevicesWithoutFunctionalities() {
        for (Room room : _rooms) {
            List<Device> devices = room.getDevices();
            if (devices.isEmpty()) continue;
            for (Device device : devices) {
                List<Sensor> sensors = device.getSensors();
                if (sensors.isEmpty()) {
                    _devicesRoomNoFunc.add(new DeviceRoomDTO(device.getName(), room.getName()));
                    _map.put("Without functionality", _devicesRoomNoFunc);
                }
            }
        }
    }
}
