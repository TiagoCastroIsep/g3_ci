package SmartHome.domain.house;

import SmartHome.domain.device.Device;
import SmartHome.domain.house.DevicesByFunctionality;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.dto.DeviceRoomDTO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * This class contains the test cases to verify the functionality of the DevicesByFunctionality class.
 * The test methods included here ensure the correct operation of the methods in the DevicesByFunctionality class.
 */
class DevicesByFunctionalityTest {

    /**
     * Test method to verify the functionality of the getGroupedResult() method in the DevicesByFunctionality class.
     * This method tests the grouping of devices by their functionality and verifies the correctness of the result.
     * It specifically tests the scenario where the method is called with predefined mocked objects.
     */
    @Test
    void getGroupedResult() {
        Room roomDouble1 = mock(Room.class);
        Room roomDouble2 = mock(Room.class);
        List<Room> roomsDouble = List.of(roomDouble1, roomDouble2);
        Device deviceDouble1 = mock(Device.class);
        Device deviceDouble2 = mock(Device.class);
        Device deviceDouble3 = mock(Device.class);
        Sensor sensorDouble1 = mock(Sensor.class);
        Sensor sensorDouble2 = mock(Sensor.class);
        DeviceRoomDTO deviceRoomDtoDouble1 = mock(DeviceRoomDTO.class);
        DeviceRoomDTO deviceRoomDtoDouble2 = mock(DeviceRoomDTO.class);
        when(roomDouble1.getName()).thenReturn("r1");
        when(roomDouble2.getName()).thenReturn("r2");
        when(deviceDouble1.getName()).thenReturn("d1");
        when(deviceDouble1.getName()).thenReturn("d2");
        when(deviceRoomDtoDouble1.getRoom()).thenReturn("r1");
        when(deviceRoomDtoDouble1.getDevice()).thenReturn("d1");
        when(deviceRoomDtoDouble2.getRoom()).thenReturn("r2");
        when(deviceRoomDtoDouble2.getDevice()).thenReturn("d2");
        when(roomDouble1.getDevices()).thenReturn(List.of(deviceDouble1, deviceDouble2));
        when(roomDouble2.getDevices()).thenReturn(List.of(deviceDouble3));
        when(deviceDouble1.getSensors()).thenReturn(List.of(sensorDouble1, sensorDouble2));
        when(sensorDouble1.getSensorFunctionality()).thenReturn(SensorFunctionality.Temperature);
        when(sensorDouble2.getSensorFunctionality()).thenReturn(SensorFunctionality.Humidity);
        try (MockedConstruction<DeviceRoomDTO> deviceRoomDTOMockedConstruction = mockConstruction(DeviceRoomDTO.class,
                (mock, context) -> {
                    when(mock.getRoom()).thenReturn("r2");
                    when(mock.getDevice()).thenReturn("d2");
                })) {
            DevicesByFunctionality devicesByFunctionality = new DevicesByFunctionality(roomsDouble);
            Map<String, List<DeviceRoomDTO>> result = devicesByFunctionality.getGroupedResult();
            List<DeviceRoomDTO> constructedDeviceRoomDto = deviceRoomDTOMockedConstruction.constructed();
            Map<String, List<DeviceRoomDTO>> expected = new HashMap<>();
            expected.put("Temperature", constructedDeviceRoomDto.subList(0, 1));
            expected.put("Humidity", constructedDeviceRoomDto.subList(1, 2));
            expected.put("Without functionality", constructedDeviceRoomDto.subList(2, 4));

            assertEquals(expected, result);
        }
    }

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