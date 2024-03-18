package SmartHome.domain.house;

import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.dto.DeviceRoomDTO;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains the test cases to verify the functionality of the DevicesByFunctionality class.
 * The test methods included here ensure the correct operation of the methods in the DevicesByFunctionality class.
 */
class DevicesByFunctionalityTest {

    /**
     * Test method to verify the behavior of the getGroupedResult() method in the DevicesByFunctionality class
     * when an empty list of rooms is provided.
     *
     * <p>This test ensures that the method returns null when invoked with an empty list of rooms,
     * as there are no devices to group by functionality.</p>
     */
    @Test
    void getGroupedResult_RoomListEmpty() {
        // Arrange
        DevicesByFunctionality devicesByFunctionality = new DevicesByFunctionality(List.of());

        // Act
        assertNull(devicesByFunctionality.getGroupedResult());
    }

}