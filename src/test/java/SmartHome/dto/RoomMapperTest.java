package SmartHome.dto;

import SmartHome.domain.room.Room;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains unit tests for the RoomMapper class.
 */
class RoomMapperTest {
    private Room room;

    /**
     * Set up method executed before each test case.
     */
    @BeforeEach
    void setUp() {
        String name = "r1";
        String houseFloor = "1A";
        double height = 2.0;
        double length = 3.0;
        double width = 3.0;
        room = new Room(name, houseFloor, height, width, length, new DeviceFactory(), new DimensionsFactory());
    }

    /**
     * Test case to verify the conversion of a Room object to RoomDTO.
     */
    @Test
    void roomToDto() {
        // arrange
        String expected = "RoomDTO{name='r1', floor='1A', length=3.0, width=3.0, height=2.0}";

        // act
        String actual = RoomMapper.Domain2DTO(room).toString();

        // assert
        assertEquals(expected, actual);
    }

    /**
     * Test case to verify the conversion of a list of Room objects to RoomDTOs.
     */
    @Test
    void listRoomToDto() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(room);

        String expected = "{RoomDTO{name='r1', floor='1A', length=3.0, width=3.0, height=2.0}=Room{_name='r1', _houseFloor='1A', _dimensions={heigth=2.0, width=3.0, length=3.0}, _devices=[]}}";
        assertEquals(expected, RoomMapper.Domain2DTO(roomList).toString());
    }
}
