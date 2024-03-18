package SmartHome.domain.house;

import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.room.Room;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.RoomFactory;
import SmartHome.dto.DeviceRoomDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The House class represents a house with multiple rooms located in a specific location.
 * It manages the creation and management of rooms within the house, leveraging factories
 * for room and location creation to ensure consistency and validation of data.
 */
public class House {
    private Location _location;
    private final LocationFactory _locationFactory;
    private final RoomFactory _roomFactory;
    private final List<Room> _rooms = new ArrayList<>();

    /**
     * Constructs a new House object with the specified repository, location factory, and room factory.
     *
     * @param locationFactory The factory for creating locations.
     * @param roomFactory The factory for creating rooms.
     */
    public House(LocationFactory locationFactory, RoomFactory roomFactory) {
        this._locationFactory = locationFactory;
        this._roomFactory = roomFactory;
    }

    /**
     * Configures the location of the house with the provided details.
     *
     * @param street The street where the house is located.
     * @param doorNumber The door number of the house.
     * @param zipCode The ZIP code of the area.
     * @param city The city where the house is located.
     * @param country The country where the house is located.
     * @param latitude The latitude coordinate of the house's location.
     * @param longitude The longitude coordinate of the house's location.
     * @return The configured location object.
     */
    public Location configureLocation(String street, String doorNumber, String zipCode,
                                      String city, String country, double latitude, double longitude) {
        this._location = _locationFactory.createLocation(street, doorNumber, zipCode, city, country, latitude, longitude);
        return this._location;
    }

    /**
     * Retrieves the location of the house.
     *
     * @return The location of the house.
     */
    public Location getLocation() {
        return _location;
    }

    /**
     * Adds a new room to the house with the specified details. This method ensures that no two rooms
     * have the same name within the house. It utilizes the RoomFactory for room creation, which may
     * throw an IllegalArgumentException if room dimensions are invalid.
     *
     * @param name The name of the room. Unique names are required to successfully add a room.
     * @param houseFloor The floor of the house where the room is located. Floor names should follow house conventions.
     * @param height The height of the room in meters.
     * @param width The width of the room in meters.
     * @param length The length of the room in meters.
     * @param deviceFactory The factory for creating devices within the room.
     * @param dimensionsFactory The factory for creating dimensions for the room.
     * @return {@code true} if the room is successfully added; {@code false} if a room with the same name already exists or if parameters are invalid.
     */
    public boolean addRoom(String name, String houseFloor, double height, double width, double length, DeviceFactory deviceFactory, DimensionsFactory dimensionsFactory) {
        if (roomExists(name)) return false;

        try {
            Room myRoom = _roomFactory.createRoom(name, houseFloor, height, width, length, deviceFactory, dimensionsFactory);
            _rooms.add(myRoom);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Checks if a room with the specified name already exists in the house.
     *
     * @param name The name of the room to check.
     * @return {@code true} if a room with the specified name exists, {@code false} otherwise.
     */
    private boolean roomExists(String name) {
        for (Room room : _rooms)
            if (room.getName().equalsIgnoreCase(name))
                return true;
        return false;
    }

    /**
     * Retrieves a list of rooms in the house.
     *
     * @return A new list containing all rooms in the house.
     */
    public List<Room> getRooms() {
        return new ArrayList<>(_rooms);
    }

    /**
     * Retrieves a room with the specified name from the house. Case-insensitive comparison is used to
     * match the room name.
     *
     * @param name The name of the room to retrieve. The search is case-insensitive.
     * @return The Room object matching the specified name; {@code null} if no such room exists within the house.
     */
    public Room getRoom(String name) {
        for (Room room : _rooms)
            if (room.getName().equalsIgnoreCase(name))
                return room;
        return null;
    }

    /**
     * Retrieves a mapping of devices grouped by room and functionality based on the provided DevicesByFunctionality object.
     *
     * @param devicesByFunctionality The DevicesByFunctionality object containing the grouped result.
     * @return A mapping of devices grouped by room and functionality.
     */
    public Map<String, List<DeviceRoomDTO>> getDevicesByRoomAndFunctionality(DevicesByFunctionality devicesByFunctionality) {
        return devicesByFunctionality.getGroupedResult();
    }
}
