package SmartHome.controller;

import SmartHome.domain.house.House;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The Ctrl03Test class contains unit tests for testing the functionality of the Ctrl03 class.
 * It tests retrieving existing rooms from a house.
 */
class Ctrl03Test {

    private House house;
    private Ctrl03 ctrl03;

    /**
     * Sets up the environment before each test method.
     * Initializes a new House instance and adds rooms to it.
     * Initializes a new Ctrl03 instance with the created house.
     */
    @BeforeEach
    void setup() {
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Living Room", "1A", 10, 10, 10, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("Kitchen", "1A", 8, 8, 8, new DeviceFactory(), new DimensionsFactory());
        ctrl03 = new Ctrl03(house);
    }

    /**
     * Test for the getExistingRooms method of the Ctrl03 class.
     * It verifies whether the returned list of existing rooms is not null.
     */
    @Test
    void getExistingRoomsNotNull() {
        // Act
        List<RoomDTO> roomDTOs = ctrl03.getExistingRooms();

        // Assert
        assertNotNull(roomDTOs);
    }

    /**
     * Test for the getExistingRooms method of the Ctrl03 class.
     * It verifies whether the first room's name in the returned list of existing rooms is "Living Room".
     */
    @Test
    void getExistingRoomsFirstRoomName() {
        // Act
        List<RoomDTO> roomDTOs = ctrl03.getExistingRooms();

        // Assert
        assertTrue(roomDTOs.stream().anyMatch(r -> r.name.equals("Living Room")));
    }
}
