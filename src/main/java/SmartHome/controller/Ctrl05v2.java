package SmartHome.controller;

import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This controller includes methods to retrieve existing rooms and add devices to specific rooms.
 *
 * The controller interacts with the House domain model, uses the RoomMapper for DTO conversion,
 * and maintains a mapping between RoomDTOs and corresponding Room objects.
 *
 */

public class Ctrl05v2 {

    private final House _house;

    private Map<RoomDTO, Room> _roomsDTOAndRooms = new HashMap<>();
    /**
     * Constructs a new Ctrl05v2 instance with the specified house.
     *
     * @param _house the house from which rooms will be retrieved and to which devices will be added
     */
    public Ctrl05v2(House _house) {
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
     * Adds a new device to the specified room based on the provided DeviceDTO.
     * The mapping between RoomDTOs and corresponding Room objects is used to identify the target room.
     *
     * @param roomDTO the data transfer object containing the details of the target room
     * @param deviceDTO the data transfer object containing the details of the device to be added
     * @return true if the device was successfully added, false otherwise
     */
    public boolean addDeviceToRoom(RoomDTO roomDTO, DeviceDTO deviceDTO) {
        Room room = _roomsDTOAndRooms.get(roomDTO);
        return room.addDevice(deviceDTO.name, deviceDTO.deviceModel);
    }

}
