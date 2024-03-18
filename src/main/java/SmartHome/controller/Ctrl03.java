package SmartHome.controller;

import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller class for managing room-related operations.
 * This class provides methods to retrieve information about existing rooms.
 *
 */

public class Ctrl03 {

    private final House _house;

    /**
     * Mapping between RoomDTOs and corresponding Room objects.
     */
    private Map<RoomDTO, Room> _roomsDTOAndRooms = new LinkedHashMap<>();
    /**
     * Constructs a new Ctrl03 instance with the specified house.
     *
     * @param _house the house from which rooms will be retrieved
     */
    public Ctrl03(House _house) {
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

        return _roomsDTOAndRooms.keySet().stream().collect(Collectors.toList());
    }
}
