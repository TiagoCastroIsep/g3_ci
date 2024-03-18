package SmartHome.domain.room;

import SmartHome.domain.device.DeviceFactory;

/**
 * A factory class responsible for creating instances of the Room class.
 * It provides a method to create a Room object with specified attributes and dependencies.
 */
public class RoomFactory {

    /**
     * Creates a new Room object with the given attributes and dependencies.
     *
     * @param name              The name of the room.
     * @param houseFloor        The floor of the house where the room is located.
     * @param height            The height of the room.
     * @param width             The width of the room.
     * @param length            The length of the room.
     * @param deviceFactory     The factory for creating devices in the room.
     * @param dimensionsFactory The factory for creating dimensions for the room.
     * @return A new instance of the Room class.
     */
    public Room createRoom(String name, String houseFloor, double height, double width, double length, DeviceFactory deviceFactory, DimensionsFactory dimensionsFactory){
        return new Room(name, houseFloor, height, width, length, deviceFactory, dimensionsFactory);
    }
}
