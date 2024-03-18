package SmartHome.controller;

import SmartHome.domain.house.House;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.dto.RoomDTO;

/**
 * Controller class for managing room addition to the house.
 * This class provides a method to add a new room to the associated house.
 *
 */

public class Ctrl02 {
    private final House house;
    /**
     * Constructs a new Ctrl02 instance with the specified house.
     *
     * @param house the house to which rooms will be added
     */
    public Ctrl02(House house) {
        this.house = house;
    }

    /**
     * Adds a new room to the associated house based on the provided RoomDTO.
     * The RoomDTO should include details such as name, floor, height, width, and length.
     * Factories (DeviceFactory and DimensionsFactory) are used to create devices and dimensions for the new room.
     *
     * @param roomDTO the data transfer object containing the details of the room to be added
     * @return true if the room was successfully added, false otherwise
     */

    public boolean addRoomToHouse(RoomDTO roomDTO) {
        if (roomDTO != null) {
            return house.addRoom(roomDTO.name, roomDTO.floor, roomDTO.height, roomDTO.width, roomDTO.length, new DeviceFactory(), new DimensionsFactory());
        }
        return false;
    }
}