package SmartHome.controller;

import SmartHome.domain.room.Dimensions;
import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The Ctrl02Test class contains unit tests for testing the functionality of the Ctrl02 class.
 * It tests adding rooms to a house and validates the constructors of the Room class.
 */
class Ctrl02Test {
    private House house;
    private final String ERROR_MESSAGE = "Invalid arguments passed to constructor.";

    /**
     * Sets up the environment before each test method.
     * Initializes a new House instance with a repository, location factory, and room factory.
     */
    @BeforeEach
    public void setUp() {
        house = new House(new LocationFactory(), new RoomFactory());
    }

    /**
     * Tests the addRoomToHouse method of the Ctrl02 class.
     * It verifies whether a room can be successfully added to the house.
     */
    @Test
    void addRoomToHouse() {
        // Arrange
        Dimensions dimensions = new Dimensions(1, 1, 1);
        Room room = new Room("Kitchen", "f1", 1, 1, 1, new DeviceFactory(), new DimensionsFactory());
        RoomDTO roomDTO = new RoomDTO(room, dimensions);
        Ctrl02 ctrl02 = new Ctrl02(house);

        // Act
        boolean result = ctrl02.addRoomToHouse(roomDTO);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests adding a null room to the house.
     * It verifies the behavior when attempting to add a null room to the house.
     */
    @Test
    void addRoomToHouseNull() {
        // Arrange
        Ctrl02 ctrl02 = new Ctrl02(house);

        // Act
        boolean result = ctrl02.addRoomToHouse(null);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests the Room constructor with a null name parameter.
     * It verifies that an IllegalArgumentException is thrown when creating a room with a null name.
     */
    @Test
    void roomConstructorNullName() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Room(null, "f1", 1, 1, 1, new DeviceFactory(), new DimensionsFactory());
        });
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(ERROR_MESSAGE));
    }

    /**
     * Tests the Room constructor with a null floor parameter.
     * It verifies that an IllegalArgumentException is thrown when creating a room with a null floor.
     */
    @Test
    void roomConstructorNullFloor() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Room("Bedroom", null, 1, 1, 1, new DeviceFactory(), new DimensionsFactory());
        });
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(ERROR_MESSAGE));
    }

    /**
     * Tests the Room constructor with a NaN height parameter.
     * It verifies that an IllegalArgumentException is thrown when creating a room with NaN height.
     */
    @Test
    void roomConstructorNaNHeight() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Room(null, "f1", NaN, 1, 1, new DeviceFactory(), new DimensionsFactory());
        });
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(ERROR_MESSAGE));
    }

    /**
     * Tests the Room constructor with a NaN width parameter.
     * It verifies that an IllegalArgumentException is thrown when creating a room with NaN width.
     */
    @Test
    void roomConstructorNaNWight() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Room(null, "f1", 1, NaN, 1, new DeviceFactory(), new DimensionsFactory());
        });

        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(ERROR_MESSAGE));
    }

    /**
     * Tests the Room constructor with a NaN length parameter.
     * It verifies that an IllegalArgumentException is thrown when creating a room with NaN length.
     */
    @Test
    void roomConstructorNaNLength() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Room(null, "f1", 1, 1, NaN, new DeviceFactory(), new DimensionsFactory());
        });
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(ERROR_MESSAGE));
    }

    /**
     * Tests the Room constructor with a null Repository parameter.
     * It verifies that an IllegalArgumentException is thrown when creating a room with a null Repository.
     */
    @Test
    void roomConstructorNullRepository() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Room(null, "f1", 1, 1, 1,
                    null, new DimensionsFactory());
        });
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(ERROR_MESSAGE));
    }

    /**
     * Tests the Room constructor with a null DeviceFactory parameter.
     * It verifies that an IllegalArgumentException is thrown when creating a room with a null DeviceFactory.
     */
    @Test
    void roomConstructorNullDeviceFactory() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Room(null, "f1", 1, 1, 1, null, new DimensionsFactory());
        });
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(ERROR_MESSAGE));
    }

    /**
     * Tests the Room constructor with a null DimensionsFactory parameter.
     * It verifies that an IllegalArgumentException is thrown when creating a room with a null DimensionsFactory.
     */
    @Test
    void roomConstructorNullDimensionsFactory() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Room(null, "f1", 1, 1, 1, new DeviceFactory(), null);
        });
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(ERROR_MESSAGE));
    }

    @Test
    void addRoom_RoomDuplicated() {
        // Arrange
        Ctrl02 ctrl02 = new Ctrl02(house);
        DeviceFactory deviceFactory = new DeviceFactory();
        DimensionsFactory dimensionsFactory = new DimensionsFactory();
        Dimensions dimensions = new Dimensions(1, 1, 1);
        Room room = new Room("r1", "f1", 1, 1, 1, deviceFactory, dimensionsFactory);
        RoomDTO roomDTO = new RoomDTO(room, dimensions);
        ctrl02.addRoomToHouse(roomDTO);

        // Act
        boolean result = ctrl02.addRoomToHouse(roomDTO);

        // Assert
        assertFalse(result);
    }
}
