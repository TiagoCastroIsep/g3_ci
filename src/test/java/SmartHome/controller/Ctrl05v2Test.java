package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Ctrl05v2Test class contains unit tests for testing the functionality of the Ctrl05v2 class.
 * It tests adding devices to rooms and retrieving existing rooms from a house.
 */
class Ctrl05v2Test {

    House house;

    /**
     * Sets up the test environment before each test method.
     * Initializes a new House instance and a Repository instance.
     */
    @BeforeEach
    public void setup() {
        house = new House(new LocationFactory(), new RoomFactory());
    }

    /**
     * Test method for adding a device to a room.
     * It verifies whether a device can be successfully added to a room.
     */
    @Test
    void addDeviceToRoom() {
        // arrange
        house.addRoom("Bedroom", "2", 2.4, 2, 1.4, new DeviceFactory(), new DimensionsFactory());
        Ctrl05v2 ctrl05 = new Ctrl05v2(house);
        DeviceDTO deviceDTO = new DeviceDTO(new Device("d1", "dm1"));
        List<RoomDTO> rooms = ctrl05.getExistingRooms();
        RoomDTO roomDTO = rooms.stream().filter(room -> room.name.equals("Bedroom")).findFirst().orElse(null);

        // act
        boolean result = ctrl05.addDeviceToRoom(roomDTO, deviceDTO);

        // assert
        assertTrue(result);
    }

    /**
     * Test method for adding a duplicated device to a room.
     * It verifies whether a duplicated device cannot be added to a room.
     */
    @Test
    void addDuplicatedDeviceToRoom() {
        // arrange
        house.addRoom("Bedroom", "2", 2.4, 2, 1.4, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Bedroom").addDevice("d1", "dm1");
        Ctrl05v2 ctrl05 = new Ctrl05v2(house);
        DeviceDTO deviceDTO = new DeviceDTO(new Device("d1", "dm1"));
        List<RoomDTO> rooms = ctrl05.getExistingRooms();
        RoomDTO roomDTO = rooms.stream().filter(room -> room.name.equals("Bedroom")).findFirst().orElse(null);

        // act
        boolean result = ctrl05.addDeviceToRoom(roomDTO, deviceDTO);

        // assert
        assertFalse(result);
    }

    /**
     * Test method for getting non-empty rooms.
     * It verifies whether the returned list of existing rooms is not empty.
     */
    @Test
    void getNonEmptyRooms() {
        // arrange
        Ctrl05v2 ctrl05 = new Ctrl05v2(house);
        house.addRoom("Bedroom", "2", 2.4, 2, 1.4, new DeviceFactory(), new DimensionsFactory());
        Room room = house.getRoom("Bedroom");
        RoomDTO roomDTO = RoomMapper.Domain2DTO(room);

        // act
        List<RoomDTO> roomsDTO = ctrl05.getExistingRooms();

        // assert
        assertTrue(roomsDTO.stream().anyMatch(r -> r.name.equals(roomDTO.name)));
    }

    /**
     * Test method for getting empty rooms.
     * It verifies whether the returned list of existing rooms is empty.
     */
    @Test
    void getEmptyRooms() {
        // arrange
        Ctrl05v2 ctrl05 = new Ctrl05v2(house);

        // act
        List<RoomDTO> roomsDTO = ctrl05.getExistingRooms();

        // assert
        assertTrue(roomsDTO.isEmpty());
    }
}
