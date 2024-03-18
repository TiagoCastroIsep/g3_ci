package SmartHome.controller;

import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.device.Device;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.house.House;
import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * The Ctrl12Test class contains unit tests for testing the functionality of the Ctrl12 class.
 * It tests retrieving rooms and devices within those rooms from a house,
 * as well as adding an actuator to an existing device in a room.
 */
class Ctrl12Test {
    private House house;
    private RoomDTO roomDTO;

    private String actuatorClassName;

    /**
     * This method sets up a new house, repository and roomDTO before each test. It also sets up the dimensions of the room.
     */
    @BeforeEach
    void setUp() {
        actuatorClassName = "BlindRollerActuator";
    }

    /**
     * Test of getRooms method, of class Ctrl12. Should return a list of rooms with 2 rooms.
     */
    @Test
    void getRoomsReturnsCorrectRooms() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Add rooms to the house

        house.addRoom("Kitchen", "1", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("Living Room", "1", 4, 5, 7, new DeviceFactory(), new DimensionsFactory());

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();

        // Assert
        assertTrue(rooms.stream().anyMatch(room -> room.name.equals("Kitchen")));
        assertTrue(rooms.stream().anyMatch(room -> room.name.equals("Living Room")));
    }

    /**
     * Test of getRooms method, of class Ctrl12. Should return an empty list of rooms.
     */
    @Test
    void getListOfRooms() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> result = ctrl12.getExistingRooms();

        // Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Test of getDevicesInRoom method, of class Ctrl12. Should return an empty list of devices.
     */
    @Test
    void getEmptyListOfDevicesInRoom() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List <DeviceDTO> result = ctrl12.getDevicesInRoom(roomDTO);

        // Assert
        assert(result.isEmpty());
    }

    /**
     * Test of getDevicesInRoom method, of class Ctrl12. Should return a list of devices.
     */
    @Test
    void getListOfDevicesInRoom() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;

        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "Temperature");
        LocationFactory locationFactory = new LocationFactory();
        house = new House(locationFactory, new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("BlindRoller", "BlindRollerActuator");

        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);

        // Assert
        assertTrue(devices.stream().anyMatch(device -> device.name.equals("BlindRoller")));
    }
    /**
     * Test of getActuatorsInDevice method, of class Ctrl12. Should return a list of actuators in a device.
     */
    @Test
    void getActuatorsInDevice() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;

        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "Temperature");
        LocationFactory locationFactory = new LocationFactory();
        house = new House(locationFactory, new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("BlindRoller", "BlindRollerActuator");
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);
        boolean result =devices.stream().anyMatch(device -> device.name.equals("BlindRoller"));

        // Assert
        assertTrue(result);
        assertNotNull(roomDTO);
    }

    /**
     * Test of addActuatorToDevice method, of class Ctrl12. Should return true.
     */
    @Test
    void addBlindRollerActuatorToDevice() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("BlindRoller", actuatorClassName);
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO= devices.stream().filter(device -> device.name.equals("BlindRoller")).findFirst().orElse(null);
        boolean result = ctrl12.addActuatorToDevice(deviceDTO, "BlindRollerActuator", "BlindRoller");

        // Assert
        assertTrue(result);
    }

    /**
     * Test of addActuatorToDevice method, of class Ctrl12. Should return false.
     */
    @Test
    void addNonExistentActuatorToDevice() {
        // Arrange
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("BRoller", actuatorClassName);
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO= devices.stream().filter(device -> device.name.equals("BRoller")).findFirst().orElse(null);
        boolean result = ctrl12.addActuatorToDevice(deviceDTO, "BRollerActuator", "ActuatorName1");

        // Assert
        assertFalse(result);
    }
    /**
     * Test of addActuatorToDevice method, of class Ctrl12. Should return false.
     */
    @Test
    void addActuatorToDevice_DeviceNotInMap_ReturnsFalse() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        Ctrl12 ctrl12 = new Ctrl12(house);
        Device device = new Device("NonExistentDevice", "NonExistentModel");
        DeviceDTO deviceDTO = new DeviceDTO(device);

        // Act
        boolean result = ctrl12.addActuatorToDevice(deviceDTO, "BlindRollerActuator", "ActuatorName1");

        // Assert
        assertFalse(result);
    }
    /**
     * Test of addActuatorToDevice method, of class Ctrl12. Should return false.
     */
    @Test
    void addActuatorToDeviceThrowsNullPointerException() {
        // Arrange
        DeviceDTO nullDeviceDTO = null;
        String actuatorModel = "ActuatorModel";
        String name = "ActuatorName";
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        boolean result = ctrl12.addActuatorToDevice(nullDeviceDTO, actuatorModel, name);

        // Assert
        assertFalse(result);
    }
    /**
     * Test of getListOfDevicesInRoom method, of class Ctrl12. Should return a list of devices in a room.
     */
    @Test
    void getListOfDevicesInRoom_DifferentDeviceName() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;

        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Fridge", "On_Off");

        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);

        // Assert
        assertTrue(devices.stream().anyMatch(device -> device.name.equals("Fridge")));
    }

    /**
     * Test of getListOfDevicesInRoom method, of class Ctrl12. Should return a list of devices in a room.
     */
    @Test
    void getListOfDevicesInDifferentRoom() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;

        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Living Room", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Living Room").addDevice("TV", "On_Off");

        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Living Room")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);

        // Assert
        assertTrue(devices.stream().anyMatch(device -> device.name.equals("TV")));
    }
    /**
     * This test verifies the behavior of the {@code getListOfDevicesOfRangeActuator} method in the {@code Ctrl12Test} class.
     * It tests retrieving devices of type "Range" within a room from a house.
     *
     * The test follows these steps:
     *
     * Arrange: A new house and room are created, and a device of type "Range" is added to the room.
     * Act: The existing rooms are retrieved, and the room with the name "Living Room" is selected. Then, the devices in this room are retrieved.
     * Assert: The test asserts that the retrieved devices contain a device with the name "TV".
     *
     *
     * @test This is a unit test aimed to test the {@code getListOfDevicesOfRangeActuator} method of the {@code Ctrl12Test} class.
     */
    @Test
    void getListOfDevicesOfRangeActuator() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;

        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Living Room", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Living Room").addDevice("TV", "Range");

        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Living Room")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);

        // Assert
        assertTrue(devices.stream().anyMatch(device -> device.name.equals("TV")));
    }
    /**
     * Test of getActuatorFunctionalitiesForDeviceInRoom method, of class Ctrl12. Should return a list of actuator functionalities for a device in a room.
     */
    @Test
    void getActuatorFunctionalitiesForDeviceInRoom() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;

        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Living Room", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Living Room").addDevice("TV", "On_Off");

        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Living Room")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = devices.stream().filter(device -> device.name.equals("TV")).findFirst().orElse(null);
        List<ActuatorFunctionality> functionalities = ctrl12.getActuatorFunctionalities(deviceDTO);

        // Assert
        assertFalse(functionalities.isEmpty());
    }
    /**
     * Test of compareActuatorFunctionalitiesForTwoDevices method, of class Ctrl12. Should return true if the actuator functionalities for two devices are equal.
     */
    @Test
    void compareActuatorFunctionalitiesForTwoDevices() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;

        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Living Room", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Living Room").addDevice("TV", "On_Off");
        house.getRoom("Living Room").addDevice("Radio", "On_Off");

        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Living Room")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO1 = devices.stream().filter(device -> device.name.equals("TV")).findFirst().orElse(null);
        DeviceDTO deviceDTO2 = devices.stream().filter(device -> device.name.equals("Radio")).findFirst().orElse(null);
        List<ActuatorFunctionality> functionalities1 = ctrl12.getActuatorFunctionalities(deviceDTO1);
        List<ActuatorFunctionality> functionalities2 = ctrl12.getActuatorFunctionalities(deviceDTO2);

        // Assert
        assertEquals(functionalities1, functionalities2);
    }
    /**
     * Test of addRangeActuatorIntToDevice method, of class Ctrl12. Should return true.
     */
    @Test
    void addRangeActuatorIntToDevice() {
        // Arrange
        String actuatorModel = "RangeActuatorInt";
        String actuatorName = "RAI1";
        double height = 2;
        double length = 3;
        double width = 6;
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("D1", "DM1");
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO= devices.stream().filter(device -> device.name.equals("D1")).findFirst().orElse(null);
        boolean result = ctrl12.addActuatorToDevice(deviceDTO, actuatorModel, actuatorName);

        // Assert
        assertTrue(result);
    }
    /**
     * Test of addRangeActuatorDecimalToDevice method, of class Ctrl12. Should return true.
     */
    @Test
    void addRangeActuatorDecimalToDevice() {
        // Arrange
        String actuatorModel = "RangeActuatorDecimal";
        String actuatorName = "RAD1";
        double height = 2;
        double length = 3;
        double width = 6;
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("D1", "DM1");
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO= devices.stream().filter(device -> device.name.equals("D1")).findFirst().orElse(null);
        boolean result = ctrl12.addActuatorToDevice(deviceDTO, actuatorModel, actuatorName);

        // Assert
        assertTrue(result);
    }

    /**
     * Test of addSwitchOnOffActuatorToDevice method, of class Ctrl12. Should return true.
     */
    @Test
    void addSwitchOnOffActuatorToDevice() {
        // Arrange
        String actuatorModel = "SwitchOnOffActuator";
        String actuatorName = "SWOOA";
        double height = 2;
        double length = 3;
        double width = 6;
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("D1", "DM1");
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO= devices.stream().filter(device -> device.name.equals("D1")).findFirst().orElse(null);
        boolean result = ctrl12.addActuatorToDevice(deviceDTO, actuatorModel, actuatorName);

        // Assert
        assertTrue(result);
    }
    @Test
    void addSameActuatorToDevice() {
        // Arrange
        String actuatorModel = "SwitchOnOffActuator";
        String actuatorName = "SWOOA";
        double height = 2;
        double length = 3;
        double width = 6;
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("D1", "DM1");
        Ctrl12 ctrl12 = new Ctrl12(house);

        // Act
        List<RoomDTO> rooms = ctrl12.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl12.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO= devices.stream().filter(device -> device.name.equals("D1")).findFirst().orElse(null);
        boolean result1 = ctrl12.addActuatorToDevice(deviceDTO, actuatorModel, actuatorName);
        boolean result2 = ctrl12.addActuatorToDevice(deviceDTO, actuatorModel, actuatorName);

        // Assert
        assertTrue(result1); // Assert that the actuator was added the first time
        assertFalse(result2); // Assert that the actuator was not added the second time
    }
}
