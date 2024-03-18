package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.ValueFactoryImpl;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceMapper;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static SmartHome.domain.constants.Constants.ACTUATOR_CONFIG;

/**
 * Controller class for managing device-related operations.
 * This class provides methods to retrieve information about existing rooms and devices within those rooms,
 * as well as adding an actuator to an existing device in a room.
 * The controller interacts with the House domain model, uses the RoomMapper and DeviceMapper for DTO conversion,
 * and maintains mappings between RoomDTOs and corresponding Room objects, as well as DeviceDTOs and Device objects.
 */
public class Ctrl12 {
    private final House _house;
    private Map<RoomDTO, Room> _roomsDTOAndRooms = new HashMap<>();
    private Map<DeviceDTO, Device> _devicesDTOAndDevices = new HashMap<>();
    /**
     * Constructs a new Ctrl12 instance with the specified house.
     *
     * @param _house the house from which rooms and devices will be retrieved and to which actuators will be added
     */
    public Ctrl12(House _house) {
        this._house = _house;
    }

    /**
     * Retrieves a list of existing rooms from the house and converts them into RoomDTOs.
     * The mapping between RoomDTOs and corresponding Room objects is maintained for future reference.
     *
     * @return a list of RoomDTOs representing the existing rooms in the house
     */

    public List<RoomDTO> getExistingRooms() {
        List<Room> rooms = _house.getRooms();

        _roomsDTOAndRooms = RoomMapper.Domain2DTO(rooms);

        return _roomsDTOAndRooms.keySet().stream().toList();
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
        if (room == null) {
            return new ArrayList<>();
        }
        List<Device> devices = room.getDevices();
        _devicesDTOAndDevices = DeviceMapper.Domain2DTO(devices);

        return _devicesDTOAndDevices.keySet().stream().toList();
    }

    /**
     * Retrieves a list of actuator functionalities for the specified device using the DeviceDTO.
     *
     * @param deviceDTO the data transfer object containing the details of the target device
     * @return a list of ActuatorFunctionality representing the actuator functionalities of the specified device
     */
    public List<ActuatorFunctionality> getActuatorFunctionalities(DeviceDTO deviceDTO) {
        Device _device = _devicesDTOAndDevices.get(deviceDTO);
        return _device.getActuatorFunctionalities();
    }

    /**
     * Adds an actuator to the specified device in a room.
     * The actuator model must belong to an existing type in the actuator catalogue.
     * The mapping between DeviceDTOs and corresponding Device objects is used to identify the target device.
     *
     * @param deviceDTO the data transfer object containing the details of the target device
     * @param actuatorModel the model of the actuator to be added
     * @param name the name of the actuator to be added
     * @return true if the actuator was successfully added, false otherwise
     */

    public boolean addActuatorToDevice(DeviceDTO deviceDTO, String actuatorModel, String name) {
        try {
            Device _device = _devicesDTOAndDevices.get(deviceDTO);
            ActuatorCatalogue _actuatorCatalogue = new ActuatorCatalogue(ACTUATOR_CONFIG);
            ValueFactory valueFactory = new ValueFactoryImpl();
            return _device.addActuator(actuatorModel, name, _actuatorCatalogue, valueFactory) != null;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
