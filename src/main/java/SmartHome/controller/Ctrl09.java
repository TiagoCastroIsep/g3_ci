package SmartHome.controller;

import SmartHome.domain.house.DevicesByFunctionality;
import SmartHome.domain.house.House;
import SmartHome.dto.DeviceRoomDTO;

import java.util.List;
import java.util.Map;


/**
 * Controller class for managing device-related operations.
 * This class provides methods to categorize devices in the house based on their functionalities.
 * The controller interacts with the House domain model.
 */
public class Ctrl09 {
    private final House _house;
    /**
     * Constructs a new Ctrl09 instance with the specified house.
     *
     * @param _house the house from which devices will be categorized
     */
    public Ctrl09(House _house) {
        this._house = _house;
    }


    /**
     * Categorizes devices in the house based on their functionalities.
     * The result is a map where room names are keys, and the corresponding values are lists of DeviceRoomDTOs representing devices in each room.
     *
     * @return a map where room names are keys, and the corresponding values are lists of DeviceRoomDTOs representing devices in each room
     */
    public Map<String, List<DeviceRoomDTO>> getDevicesByRoomAndFunctionality() {
        return _house.getDevicesByRoomAndFunctionality(new DevicesByFunctionality(_house.getRooms()));
    }
}
