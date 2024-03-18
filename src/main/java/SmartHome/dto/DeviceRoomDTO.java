package SmartHome.dto;

/**
 * Represents a data transfer object (DTO) that encapsulates the relationship between a device and a room.
 * This class is intended for use cases where a simplified representation of the device and room association
 * is needed, such as in user interfaces or API responses.
 */
public class DeviceRoomDTO {
    public final String device;
    public final String room;

    /**
     * Constructs a new DeviceRoomDTO with specified device and room names.
     *
     * @param device The name of the device.
     * @param room   The name of the room where the device is located.
     */
    public DeviceRoomDTO(String device, String room) {
        this.device = device;
        this.room = room;
    }

    /**
     * Retrieves the name of the device.
     *
     * @return The name of the device.
     */
    public String getDevice() {
        return device;
    }

    /**
     * Retrieves the name of the room.
     *
     * @return The name of the room.
     */
    public String getRoom() {
        return room;
    }

    /**
     * Provides a string representation of the DeviceRoomDTO, including the device and room names.
     *
     * @return A string representation of this DTO, detailing the device and room association.
     */
    @Override
    public String toString() {
        return "DeviceRoomDTO{" +
                "_device='" + device + '\'' +
                ", _room='" + room + '\'' +
                '}';
    }
}
