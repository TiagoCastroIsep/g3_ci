package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.Room;
import SmartHome.domain.room.RoomFactory;
import SmartHome.domain.house.House;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceMapper;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Ctrl06Test class contains unit tests for testing the functionality of the Ctrl06 class.
 * It tests retrieving existing rooms from a house and devices within those rooms.
 */
class Ctrl06Test {

    // Private member variables for the test class
    private House house;
    private RoomDTO roomDTO;

    /**
     * Sets up the environment before each test method.
     * Initializes a new House instance and a Repository instance.
     */
    @BeforeEach
    void setup() {
        house = new House(new LocationFactory(), new RoomFactory());
    }

    /**
     * Test method to retrieve an empty list of devices in a room DTO.
     * It verifies whether the returned list of devices is empty when there are no devices in the room.
     */
    @Test
    void retrieveEmptyListOfDevicesInRoomDTO() {
        // Arrange
        // Setting up room parameters
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 3;
        double width = 4;
        double length = 5;
        house.addRoom(roomName, houseFloor, height, width, length, new DeviceFactory(), new DimensionsFactory());
        Ctrl06 ctrl06 = new Ctrl06(house);

        // Retrieving the list of existing rooms
        List<RoomDTO> rooms = ctrl06.getExistingRooms();
        roomDTO = rooms.stream().filter(r -> r.name.equals(roomName)).findFirst().orElse(null);

        // Act
        // Retrieving the devices in the specified room DTO
        List<DeviceDTO> devices = ctrl06.getDevicesInRoom(roomDTO);

        // Assert
        // Verifying that the list of devices is empty
        assertTrue(devices.isEmpty());
    }

    /**
     * Test method to retrieve a valid list of devices in a room DTO.
     * It verifies whether the returned list of devices contains the added device.
     */
    @Test
    void retrieveValidListOfDevicesInRoomDTO() {
        // Arrange
        // Setting up room parameters
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 3;
        double width = 4;
        double length = 5;
        house.addRoom(roomName, houseFloor, height, width, length, new DeviceFactory(), new DimensionsFactory());
        Ctrl06 ctrl06 = new Ctrl06(house);

        // Adding a device to the room
        Room room1 = house.getRoom(roomName);
        room1.addDevice("d1", "m1");
        Device d1 = room1.getDevice("d1");
        DeviceDTO d1DTO = DeviceMapper.Domain2DTO(d1);
        List<RoomDTO> rooms = ctrl06.getExistingRooms();
        roomDTO = rooms.stream().filter(r -> r.name.equals(roomName)).findFirst().orElse(null);

        // Act
        // Retrieving the devices in the specified room DTO
        List<DeviceDTO> devices = ctrl06.getDevicesInRoom(roomDTO);

        // Assert
        // Verifying that the list of devices contains the added device
        assertTrue(devices.stream().anyMatch(r -> r.name.equals(d1DTO.name)));
    }

    /**
     * Test method to get the list of rooms.
     * It verifies whether the returned list of rooms contains the specified room DTO.
     */
    @Test
    void getListOfRoomsTest() {
        // Arrange
        // Setting up room parameters
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 3;
        double width = 4;
        double length = 5;
        house.addRoom(roomName, houseFloor, height, width, length, new DeviceFactory(), new DimensionsFactory());
        Ctrl06 ctrl06 = new Ctrl06(house);

        // Retrieving the room and converting it to DTO
        Room room = house.getRoom(roomName);
        RoomDTO roomDTO = RoomMapper.Domain2DTO(room);

        // Act
        // Retrieving the list of existing rooms
        List<RoomDTO> rooms = ctrl06.getExistingRooms();

        // Assert
        // Verifying that the list of rooms contains the specified room DTO
        assertTrue(rooms.stream().anyMatch(r -> r.name.equals(roomDTO.name)));
    }
}

