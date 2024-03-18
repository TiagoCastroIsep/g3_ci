package SmartHome.controller;

import SmartHome.domain.values.ValueFactoryImpl;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.house.House;
import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.domain.sensors.SensorCatalogue;
import org.junit.jupiter.api.Test;

import static SmartHome.domain.constants.Constants.SENSOR_CONFIG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
/**
 * The Ctrl09Test class contains unit tests for testing the functionality of the Ctrl09 class.
 * It tests retrieving devices by room and functionality from a house.
 */
class Ctrl09Test {

    /**
     * Test case to verify if devices can be successfully retrieved by room and functionality.
     * It verifies whether the returned map of devices grouped by functionality and room matches the expected map.
     */
    @Test
    void getDevicesByRoomAndFunctionality() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("r1", "1", 1, 1, 1, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("r2", "1", 1, 1, 1, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("r3", "1", 1, 1, 1, new DeviceFactory(), new DimensionsFactory());

        house.getRoom("r1").addDevice("d1", "m1");
        house.getRoom("r1").addDevice("d2", "m2");
        house.getRoom("r2").addDevice("d3", "m3");
        house.getRoom("r3").addDevice("d4", "m4");

        house.getRoom("r1").getDevice("d1").addSensor("TemperatureSensor", "Temperature", new SensorCatalogue(SENSOR_CONFIG), new ValueFactoryImpl());
        house.getRoom("r1").getDevice("d2").addSensor("HumiditySensor", "Humidity 1", new SensorCatalogue(SENSOR_CONFIG), new ValueFactoryImpl());
        house.getRoom("r2").getDevice("d3").addSensor("HumiditySensor", "Humidity 2", new SensorCatalogue(SENSOR_CONFIG), new ValueFactoryImpl());

        String expected = "{Temperature=[DeviceRoomDTO{_device='d1', _room='r1'}], " +
                "Humidity=[DeviceRoomDTO{_device='d2', _room='r1'}, DeviceRoomDTO{_device='d3', _room='r2'}], " +
                "Without functionality=[DeviceRoomDTO{_device='d4', _room='r3'}]}";

        // Act
        String result = new Ctrl09(house).getDevicesByRoomAndFunctionality().toString();

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if an empty map is returned when there are no rooms in the house.
     * It verifies whether the returned map of devices is null when there are no rooms in the house.
     */
    @Test
    void getDevicesByRoomAndFunctionalityEmptyRoomList() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());

        // Assert
        assertNull(new Ctrl09(house).getDevicesByRoomAndFunctionality());
    }
    /**
     * Test case to verify if an empty map is returned when there are no devices in the rooms.
     * It verifies whether the returned map of devices is null when there are no devices in the rooms.
     */
    @Test
    void getDevicesByRoomAndFunctionalityEmptyDeviceList() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("r1", "1", 1, 1,1, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("r2", "1", 1, 1,1, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("r3", "1", 1, 1,1, new DeviceFactory(), new DimensionsFactory());

        // Assert
        assertNull(new Ctrl09(house).getDevicesByRoomAndFunctionality());
    }
    /**
     * Test case to verify if devices without functionality can be successfully retrieved.
     * It verifies whether the returned map of devices grouped by functionality and room matches the expected map.
     */
    @Test
    void getDevicesByRoomAndFunctionalityAddWithoutFunctionality() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("r1", "1",1, 1, 1, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("r2", "1", 1, 1, 1, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("r3", "1", 1, 1, 1, new DeviceFactory(), new DimensionsFactory());

        house.getRoom("r1").addDevice("d1", "m1");
        house.getRoom("r1").addDevice("d2", "m2");
        house.getRoom("r2").addDevice("d3", "m3");
        house.getRoom("r3").addDevice("d4", "m4");

        String expected = "{Without functionality=[DeviceRoomDTO{_device='d1', _room='r1'}, " +
                "DeviceRoomDTO{_device='d2', _room='r1'}, " +
                "DeviceRoomDTO{_device='d3', _room='r2'}, " +
                "DeviceRoomDTO{_device='d4', _room='r3'}]}";

        // Act
        String result = new Ctrl09(house).getDevicesByRoomAndFunctionality().toString();

        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test case to verify if devices without functionality can be successfully retrieved when the device list is empty.
     * It verifies whether the returned map of devices grouped by functionality and room matches the expected map.
     */
    @Test
    void getDevicesByRoomAndFunctionalityAddWithoutFunctionalityDeviceListEmpty() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("r1", "1", 1, 1, 1, new DeviceFactory(), new DimensionsFactory());
        house.addRoom("r2", "1", 1, 1, 1, new DeviceFactory(), new DimensionsFactory());

        house.getRoom("r1").addDevice("d1", "m1");
        house.getRoom("r1").addDevice("d2", "m2");
        house.getRoom("r2").addDevice("d3", "m3");

        String expected = "{Without functionality=[DeviceRoomDTO{_device='d1', _room='r1'}, " +
                "DeviceRoomDTO{_device='d2', _room='r1'}, " +
                "DeviceRoomDTO{_device='d3', _room='r2'}]}";

        // Act
        String result = new Ctrl09(house).getDevicesByRoomAndFunctionality().toString();

        // Assert
        assertEquals(expected, result);
    }
}