package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.domain.values.ValueFactoryImpl;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceMapper;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static SmartHome.domain.constants.Constants.SENSOR_CONFIG;

/**
 * Controller class for managing room and device-related operations.
 * This class provides methods to retrieve information about existing rooms and devices within those rooms,
 * as well as adding a sensor to an existing device in a room.
 * The controller interacts with the House domain model, uses the RoomMapper and DeviceMapper for DTO conversion,
 * and maintains mappings between RoomDTOs and corresponding Room objects, as well as DeviceDTOs and Device objects.
 */

public class Ctrl07 {

    private final House _house;
    private Map<RoomDTO, Room> _roomsDTOAndRooms;
    private Map<DeviceDTO, Device> _devicesDTOAndDevices;

    /**
     * Constructs a new Ctrl07 instance with the specified house.
     *
     * @param _house the house from which rooms and devices will be retrieved
     */

    public Ctrl07(House _house) {
        this._house = _house;
    }

    /**
     * Retrieves a list of existing rooms from the house and converts them into RoomDTOs.
     * The mapping between RoomDTOs and corresponding Room objects is maintained for future reference.
     *
     * @return a list of RoomDTOs representing the existing rooms in the house
     */
    public List<RoomDTO> getExistingRooms()
    {
        List<Room> rooms = _house.getRooms();

        _roomsDTOAndRooms = RoomMapper.Domain2DTO(rooms);

        return _roomsDTOAndRooms.keySet().stream().collect(Collectors.toList());
    }

    /**
     * Retrieves a list of devices in the specified room using the RoomDTO.
     * The devices are converted into DeviceDTOs using the DeviceMapper class.
     * The mapping between DeviceDTOs and corresponding Device objects is maintained for future reference.
     *
     * @param roomDTO the data transfer object containing the details of the target room
     * @return a list of DeviceDTOs representing the devices in the specified room
     */

    public List<DeviceDTO> getDevicesInRoom(RoomDTO roomDTO) {
        Room room = _roomsDTOAndRooms.get(roomDTO);
        List<Device> devices = room.getDevices();
        _devicesDTOAndDevices = DeviceMapper.Domain2DTO(devices);

        return _devicesDTOAndDevices.keySet().stream().collect(Collectors.toList());
    }

    /**
     * Retrieves a list of sensor functionalities for the specified device using the DeviceDTO.
     *
     * @param deviceDTO the data transfer object containing the details of the target device
     * @return a list of SensorFunctionality representing the sensor functionalities of the specified device
     */

    public List<SensorFunctionality> getSensorFunctionalities(DeviceDTO deviceDTO) {
        Device _device = _devicesDTOAndDevices.get(deviceDTO);
        return _device.getSensorFunctionalities();
    }
    /**
     * Adds a sensor to the specified device in a room.
     * The sensor model must belong to an existing type in the sensor catalogue.
     * The mapping between DeviceDTOs and corresponding Device objects is used to identify the target device.
     *
     * @param deviceDTO the data transfer object containing the details of the target device
     * @param sensorModel the model of the sensor to be added
     * @param name the name of the sensor to be added
     * @return true if the sensor was successfully added, false otherwise
     */
    public boolean addSensorToDevice(DeviceDTO deviceDTO, String sensorModel, String name) {
        try {
            Device _device = _devicesDTOAndDevices.get(deviceDTO);
            SensorCatalogue _sensorCatalogue = new SensorCatalogue(SENSOR_CONFIG);
            return _device.addSensor(sensorModel, name, _sensorCatalogue, new ValueFactoryImpl()) != null;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
