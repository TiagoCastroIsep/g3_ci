package SmartHome.controller;

import SmartHome.domain.house.House;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The Ctrl07Test class contains unit tests for testing the functionality of the Ctrl07 class.
 * It tests retrieving existing rooms and devices within those rooms from a house,
 * as well as adding a sensor to an existing device in a room.
 */
class Ctrl07Test {

    private House house;
    private RoomDTO roomDTO;

    /**
     * Sets up the environment before each test method.
     * Initializes a new House instance and a Repository instance.
     */
    @BeforeEach
    void setUp() {
        house = new House(new LocationFactory(), new RoomFactory());
    }

    /**
     * Test case to verify if getRooms returns correct rooms.
     * It verifies whether the returned list of rooms contains the expected rooms.
     */
    @Test
    void getRoomsReturnsCorrectRooms() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        Ctrl07 ctrl07 = new Ctrl07(house);

        // Add rooms to the house
        house.addRoom("Kitchen", "1", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("Living Room", "1", 4, 5, 7, new DeviceFactory(), new DimensionsFactory());

        // Act
        List<RoomDTO> rooms = ctrl07.getExistingRooms();

        // Assert
        assertTrue(rooms.stream().anyMatch(room -> room.name.equals("Kitchen")));
        assertTrue(rooms.stream().anyMatch(room -> room.name.equals("Living Room")));
    }

    /**
     * Test case to verify if an empty list of rooms is returned.
     * It verifies whether the returned list of rooms is empty when there are no rooms in the house.
     */
    @Test
    void getListOfRooms() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        Ctrl07 ctrl07 = new Ctrl07(house);

        // Act
        List<RoomDTO> result = ctrl07.getExistingRooms();

        // Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Test case to verify if an empty list of devices in a room is returned.
     * It verifies whether the returned list of devices is empty when there are no devices in the room.
     */
    @Test
    void getEmptyListOfDevicesInRoom() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);

        // Act
        List<DeviceDTO> result = ctrl07.getDevicesInRoom(roomDTO);

        // Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Test case to verify if a list of devices in a room is returned.
     * It verifies whether the returned list of devices contains the expected devices.
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
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);

        // Act
        List<DeviceDTO> result = ctrl07.getDevicesInRoom(roomDTO);

        // Assert
        assertTrue(result.stream().anyMatch(r -> r.name.equals("Stove")));
    }

    /**
     * Test case to verify adding a humidity sensor to a device.
     * It verifies whether a humidity sensor can be successfully added to a device.
     */
    @Test
    void addHumiditySensorToDevice() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "DeviceModel");

        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Stove")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "HumiditySensor", "SensorName1");

        // Assert
        assertTrue(result);
    }

    /**
     * Test case to verify adding a Temperature sensor to a device.
     * It verifies whether a temperature sensor can be successfully added to a device.
     */
    @Test
    void addTemperatureSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Stove")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "TemperatureSensor", "SensorName1");

        // Assert
        assertTrue(result);
    }

    /**
     * Test case to verify adding a scale sensor to a device.
     * It verifies whether a scale sensor can be successfully added to a device.
     */
    @Test
    void addScaleSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Oven", "SmartOven");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Oven")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "ScaleSensor", "OvenScaleSensor");

        // Assert
        assertTrue(result);
    }

    /**
     * Test case to verify adding a null sensor to a device.
     * It verifies whether a null sensor cannot be added to a device.
     */
    @Test
    void addNullSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Stove")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, null, "SensorName1");

        // Assert
        assertFalse(result);
    }

    /**
     * Test case to verify getting sensor functionalities for a device.
     * It verifies whether the returned list of sensor functionalities is not empty.
     */
    @Test
    void getSensorFunctionalities() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;

        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Living Room", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Living Room").addDevice("TV", "On_Off");

        Ctrl07 ctrl07 = new Ctrl07(house);

        // Act
        List<RoomDTO> rooms = ctrl07.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Living Room")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = devices.stream().filter(device -> device.name.equals("TV")).findFirst().orElse(null);
        List<SensorFunctionality> functionalities = ctrl07.getSensorFunctionalities(deviceDTO);

        // Assert
        assertFalse(functionalities.isEmpty());
    }

    /**
     * Test case to verify comparing sensor functionalities for two devices.
     * It verifies whether the sensor functionalities of two devices are equal.
     */
    @Test
    void compareSensorFunctionalitiesForTwoDevices() {
        // Arrange
        double height = 2;
        double length = 3;
        double width = 6;

        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Living Room", "2", height, length, width, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Living Room").addDevice("TV", "On_Off");
        house.getRoom("Living Room").addDevice("Radio", "On_Off");

        Ctrl07 ctrl07 = new Ctrl07(house);

        // Act
        List<RoomDTO> rooms = ctrl07.getExistingRooms();
        roomDTO = rooms.stream().filter(room -> room.name.equals("Living Room")).findFirst().orElse(null);
        List<DeviceDTO> devices = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO1 = devices.stream().filter(device -> device.name.equals("TV")).findFirst().orElse(null);
        DeviceDTO deviceDTO2 = devices.stream().filter(device -> device.name.equals("Radio")).findFirst().orElse(null);
        List<SensorFunctionality> functionalities1 = ctrl07.getSensorFunctionalities(deviceDTO1);
        List<SensorFunctionality> functionalities2 = ctrl07.getSensorFunctionalities(deviceDTO2);

        // Assert
        assertEquals(functionalities1, functionalities2);
    }

    /**
     * Verifies that a wind sensor can be successfully added to a device within a room. This test
     * sets up a "Garden" room with a single device named "D1", and then attempts to add a wind
     * sensor to this device. The test checks that the method {@code addSensorToDevice} returns
     * {@code true}, indicating successful addition of the wind sensor.
     */
    @Test
    void addWindSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Garden", "1", 4, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Garden").addDevice("D1", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Garden")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("D1")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "WindSensor", "SensorWind");

        // Assert
        assertTrue(result);
    }

    /**
     * Verifies that an instant power consumption sensor can be successfully added to a device within a
     * room. This test sets up a "Garden" room with a single device named "D1", and then attempts to add
     * an instant power consumption sensor to this device. The test checks that the method
     * {@code addSensorToDevice} returns {@code true}, indicating successful addition of the instant
     * power consumption sensor.
     */
    @Test
    void addInstantPowerConsumptionSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Garden", "1", 4, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Garden").addDevice("D1", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Garden")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("D1")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "InstantPowerConsumptionSensor", "SensorIPC");

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the addSensorToDevice() functionality of the Ctrl07 class when adding a sunrise sensor to a device.
     *
     * <p>This test ensures that the {@code addSensorToDevice()} method correctly adds a sunrise sensor to a specified device in a room.</p>
     */
    @Test
    void addSunriseSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Window", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Window")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "SunriseSensor", "SunriseSensor1");

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the addition of a Sunset Sensor to a device.
     *
     * <p>This test ensures that a Sunset Sensor can be successfully added to a device in a room.</p>
     */
    @Test
    void addSunsetSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Window", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Window")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "SunsetSensor", "SunriseSensor1");

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the addition of an average power consumption Sensor to a device.
     *
     * <p>This test ensures that a Sunset Sensor can be successfully added to a device in a room.</p>
     */
    @Test
    void addAveragePowerConsumptionSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Stove")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "AveragePowerConsumptionSensor", "Smart Meter");

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the addition of an electric energy consumption Sensor to a device.
     *
     * <p>This test ensures that a Sunset Sensor can be successfully added to a device in a room.</p>
     */

    @Test
    void addElectricEnergyConsumptionSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Stove")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "ElectricEnergyConsumptionSensor", "Smart Plug");

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the addition of a binary switch Sensor to a device.
     *
     * <p>This test ensures that a Sunset Sensor can be successfully added to a device in a room.</p>
     */
    @Test
    void addBinarySwitchToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Stove")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "BinarySwitch", "Smart Plug");

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the addition of a dew point Sensor to a device.
     *
     * <p>This test ensures that a Sunset Sensor can be successfully added to a device in a room.</p>
     */
    @Test
    void addDewPointSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Stove")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "DewPointSensor", "Smart Plug");

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the addition of a solar irradiance Sensor to a device.
     *
     * <p>This test ensures that a Sunset Sensor can be successfully added to a device in a room.</p>
     */
    @Test
    void addSolarIrradianceSensorToDevice() {
        // Arrange
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Kitchen", "2", 2, 3, 6, new DeviceFactory(), new DimensionsFactory());
        house.getRoom("Kitchen").addDevice("Stove", "DeviceModel");
        Ctrl07 ctrl07 = new Ctrl07(house);
        List<RoomDTO> roomDTOS = ctrl07.getExistingRooms();
        roomDTO = roomDTOS.stream().filter(r -> r.name.equals("Kitchen")).findFirst().orElse(null);
        List<DeviceDTO> deviceDTOS = ctrl07.getDevicesInRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOS.stream().filter(r -> r.name.equals("Stove")).findFirst().orElse(null);

        // Act
        boolean result = ctrl07.addSensorToDevice(deviceDTO, "SolarIrradianceSensor", "Smart Plug");

        // Assert
        assertTrue(result);
    }

}