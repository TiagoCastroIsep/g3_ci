package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.house.LocationFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.dto.DeviceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
/**
 * The Ctrl08Test class contains unit tests for testing the functionality of the Ctrl08 class.
 * It tests deactivating a device and retrieving devices from a house.
 */
public class Ctrl08Test {
    House house;
    Room bedroom;
    /**
     * Sets up the environment before each test method.
     * Initializes a new House instance, a Repository instance, and a Room instance.
     */
    @BeforeEach
    public void setUp() {
        house = new House(new LocationFactory(), new RoomFactory());
        house.addRoom("Bedroom", "2", 2.4, 2, 1.4, new DeviceFactory(), new DimensionsFactory());
        bedroom = house.getRoom("Bedroom");
    }
    /**
     * Test case to verify if a device can be successfully deactivated.
     * It verifies whether the device is successfully deactivated when it is active.
     */
    @Test
    void validDeactivateDevice() {
        //Arrange
        Ctrl08 ctrl08 = new Ctrl08(house);

        bedroom.addDevice("Heater", "GATX");
        bedroom.getDevice("Heater").switchDevice(true);

        DeviceDTO deviceDTO = new DeviceDTO(bedroom.getDevice("Heater"));
        ctrl08.getDevicesFromHouse();

        //Act
        boolean result = ctrl08.deactivate(deviceDTO);

        //Assert
        assertTrue(result);
    }
    /**
     * Test case to verify if a deactivated device can be deactivated again.
     * It verifies whether the device cannot be deactivated again when it is already deactivated.
     */
    @Test
    void invalidDeactivateDeviceDeactivated() {
        //Arrange
        Ctrl08 ctrl08 = new Ctrl08(house);

        bedroom.addDevice("Heater", "GATX");
        bedroom.getDevice("Heater").switchDevice(false);

        Device device = new Device("Cat Water Fountain", "LEIA");
        DeviceDTO deviceDTO = new DeviceDTO(device);
        //Act
        ctrl08.getDevicesFromHouse();
        boolean result = ctrl08.deactivate(deviceDTO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify if a null device can be deactivated.
     * It verifies whether a null device cannot be deactivated.
     */
    @Test
    void invalidDeactivateNullDevice() {
        //Arrange
        Ctrl08 ctrl08 = new Ctrl08(house);

        //Act
        boolean result = ctrl08.deactivate(null);

        //Assert
        assertFalse(result);
    }
    /**
     * Test case to verify if the list of devices from the house is correctly retrieved.
     * It verifies whether the returned list of devices contains the expected devices.
     */
    @Test
    void validGetDevicesFromHouse() {
        //Arrange
        Ctrl08 ctrl08 = new Ctrl08(house);
        String heater = "Heater";
        String catWaterFountain = "Cat Water Fountain";

        bedroom.addDevice(heater, "GATX");
        bedroom.addDevice(catWaterFountain, "LEIA");

        //Act
        Map<DeviceDTO, Device> devicesMap = ctrl08.getDevicesFromHouse();
        Optional<DeviceDTO> firstDevice = devicesMap.keySet().stream().filter(deviceDTO -> deviceDTO.name.equals(heater)).findFirst();
        Optional<DeviceDTO> secondDevice = devicesMap.keySet().stream().filter(deviceDTO -> deviceDTO.name.equals(catWaterFountain)).findFirst();

        //Assert
        assertAll(
              () -> assertTrue(firstDevice.isPresent()),
              () -> assertEquals(heater, firstDevice.get().name),
              () -> assertTrue(secondDevice.isPresent()),
              () -> assertEquals(catWaterFountain, secondDevice.get().name)
        );
    }
    /**
     * Test case to verify if an empty list of devices from the house is returned.
     * It verifies whether the returned list of devices is empty when there are no devices in the house.
     */
    @Test
    void emptyGetDevicesFromHouse() {
        //Arrange
        Ctrl08 ctrl08 = new Ctrl08(house);

        //Act
        boolean isEmpty = ctrl08.getDevicesFromHouse().isEmpty();

        //Assert
        assertTrue(isEmpty);
    }
}
