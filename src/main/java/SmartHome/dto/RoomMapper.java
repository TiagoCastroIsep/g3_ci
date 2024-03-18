package SmartHome.dto;

import SmartHome.domain.room.Dimensions;
import SmartHome.domain.room.Room;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Facilitates the conversion between {@link Room} domain objects and their corresponding
 * {@link RoomDTO} data transfer objects. This mapper simplifies interactions between
 * the domain layer and external layers by providing a straightforward method
 * for transforming complex domain objects into simplified DTOs.
 */
public class RoomMapper {

    /**
     * Converts a single {@link Room} domain object into a {@link RoomDTO} object.
     * This method extracts relevant details from the Room and its associated Dimensions
     * to populate the DTO fields.
     *
     * @param room The Room object to convert.
     * @return A RoomDTO object containing the simplified details of the Room.
     */
    public static RoomDTO Domain2DTO(Room room)
    {
        Dimensions dimensions = room.getDimensions();
        return new RoomDTO(room, dimensions);
    }

    /**
     * Converts a list of {@link Room} domain objects into a map linking {@link RoomDTO} objects
     * to their original Room objects. This is useful for operations requiring a reference back
     * to the domain objects after working with their DTO representations.
     *
     * @param rooms A list of Room objects to convert.
     * @return A map where each key is a RoomDTO object and each value is the original Room domain object.
     */
    public static Map<RoomDTO, Room> Domain2DTO(List<Room> rooms)
    {
        Map<RoomDTO, Room> roomsDTOAndRooms = new LinkedHashMap<>();

        rooms.forEach(room -> {
            RoomDTO roomDTO = RoomMapper.Domain2DTO(room);
            roomsDTOAndRooms.put(roomDTO, room);
        });

        return roomsDTOAndRooms;
    }
}
