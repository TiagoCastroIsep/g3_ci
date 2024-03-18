package SmartHome.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class provides unit tests for the DeviceRoomDTO class.
 */
class DeviceRoomDTOTest {

    /**
     * Tests the creation of a valid DeviceRoomDTO object.
     */
    @Test
    void createValidDeviceRoomDTO() {
        // Arrange
        String device = "d1";
        String room = "r1";
        String expected = "DeviceRoomDTO{" +
                "_device='" + device + '\'' +
                ", _room='" + room + '\'' +
                '}';

        // Act
        DeviceRoomDTO deviceRoomDTO = new DeviceRoomDTO(device, room);

        // Assert
        assertEquals(expected, deviceRoomDTO.toString());
    }

    /**
     * Tests the retrieval of the device from a DeviceRoomDTO object.
     */
    @Test
    void getDevice() {
        // Arrange
        String device = "d1";
        String room = "r1";
        String expected = "d1";

        // Act
        DeviceRoomDTO deviceRoomDTO = new DeviceRoomDTO(device, room);

        // Assert
        assertEquals(expected, deviceRoomDTO.getDevice());
    }

    /**
     * Tests the retrieval of the room from a DeviceRoomDTO object.
     */
    @Test
    void getRoom() {
        // Arrange
        String device = "d1";
        String room = "r1";
        String expected = "r1";

        // Act
        DeviceRoomDTO deviceRoomDTO = new DeviceRoomDTO(device, room);

        // Assert
        assertEquals(expected, deviceRoomDTO.getRoom());
    }
}