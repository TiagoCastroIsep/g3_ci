package SmartHome.dto;

import SmartHome.domain.room.Dimensions;
import SmartHome.domain.room.Room;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains unit tests for the RoomDTO class.
 */
class RoomDTOTest {

    /**
     * Test to verify the correct retrieval of the name of the room from RoomDTO.
     */
    @Test
    void testGetName() {
        // Arrange
        Dimensions dimensions = new Dimensions(5, 6, 4);
        String _name = "Room1";
        String _houseFloor = "1A";
        Room room = new Room(_name, _houseFloor, 1, 2, 3, new DeviceFactory(), new DimensionsFactory());
        RoomDTO roomDTO = new RoomDTO(room, dimensions);
        String expected = "Room1";

        // Act
        String result = roomDTO.name;

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify the correct retrieval of the floor of the room from RoomDTO.
     */
    @Test
    void testGetFloor() {
        // Arrange
        Dimensions dimensions = new Dimensions(5, 6, 4);
        String _name = "Room1";
        String _houseFloor = "1A";
        Room room = new Room(_name, _houseFloor, 1, 2, 3, new DeviceFactory(), new DimensionsFactory());
        RoomDTO roomDTO = new RoomDTO(room, dimensions);
        String expected = "1A";

        // Act
        String result = roomDTO.floor;

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify the correct retrieval of the dimensions of the room from RoomDTO.
     */
    @Test
    void testGetDimensions() {
        // Arrange
        Dimensions dimensions = new Dimensions(5, 6, 4);
        String _name = "Room1";
        String _houseFloor = "1A";
        Room room = new Room(_name, _houseFloor, 1, 2, 3, new DeviceFactory(), new DimensionsFactory());
        RoomDTO roomDTO = new RoomDTO(room, dimensions);
        double[] expected = {5,6,4};
        double[] result = new double[3];

        // Act
        result[0] = roomDTO.height;
        result[1] = roomDTO.width;
        result[2] = roomDTO.length;

        // Assert
        assertArrayEquals(expected, result);
    }
}

