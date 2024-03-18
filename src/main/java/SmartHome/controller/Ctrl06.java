package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceMapper;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomMapper;

import java.util.List;
import java.util.Map;

/**
 * Controller class for managing room and device-related operations.
 * This class provides methods to retrieve information about existing rooms and devices within those rooms.
 * The controller interacts with the House domain model, uses the RoomMapper and DeviceMapper for DTO conversion,
 * and maintains mappings between RoomDTOs and corresponding Room objects, as well as DeviceDTOs and Device objects.
 */
public class Ctrl06 {
    private final House _house;
    private Map<RoomDTO, Room> _roomsDTOAndRooms;
    private Map<DeviceDTO, Device> _devicesDTOAndDevices;
    /**
     * Constructs a new Ctrl06 instance with the specified house.
     *
     * @param _house the house from which rooms and devices will be retrieved
     */
    public Ctrl06(House _house) {
        this._house = _house;
    }

    /**
     * Retrieves a list of existing rooms from the associated house
     * and converts them into RoomDTOs using the RoomMapper class.
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
        List<Device> devices = room.getDevices();
        _devicesDTOAndDevices = DeviceMapper.Domain2DTO(devices);

        return _devicesDTOAndDevices.keySet().stream().toList();
    }
}
