package SmartHome.domain.room;

import SmartHome.domain.device.Device;
import SmartHome.domain.device.DeviceFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Float.NaN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomTest {

    /**
     * Test case to verify the behavior of the constructor of the Room class when valid arguments are provided.
     * <p>
     * The test arranges mock objects for DeviceFactory and DimensionsFactory using Mockito's mock() method,
     * along with a mocked Dimensions object. It initializes roomName, houseFloor, height, width, and length with valid values.
     * The behavior of dimensionsFactoryDouble.createDimensions() method is defined to return dimensionsDouble when called with
     * the specified height, width, and length.
     * <p>
     * The constructor of the Room class is invoked to create a new Room object with the provided valid arguments.
     * The name of the created room is then retrieved using getName() method, and it's compared against the expected room name
     * to ensure correctness.
     */
    @Test
    void constructorWithValidArguments() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String roomName = "Room1";
        String houseFloor = "Floor1";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);

        Room newRoom = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        String expected = roomName;

        // Act
        String result = newRoom.getName();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test case to verify the behavior of the constructor of the Room class when a null room name is provided.
     * <p>
     * The test arranges mock objects for DeviceFactory and DimensionsFactory using Mockito's mock() method,
     * along with a mocked Dimensions object. It initializes the roomName variable to null and sets other required parameters.
     * The behavior of dimensionsFactoryDouble.createDimensions() method is defined to return dimensionsDouble when called with
     * the specified height, width, and length.
     * <p>
     * The constructor of the Room class is invoked within a lambda expression using assertThrows(),
     * expecting it to throw an IllegalArgumentException due to the null room name.
     * The caught exception's message is then compared against the expected value to ensure correctness.
     */
    @Test
    void constructorWithNullName() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String roomName = null;
        String houseFloor = "Floor1";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        String expected = "Invalid arguments passed to constructor.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble));

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }


    /**
     * Test case to verify the behavior of the constructor of the Room class when a null house floor is provided.
     * <p>
     * The test arranges mock objects for DeviceFactory and DimensionsFactory using Mockito's mock() method,
     * along with a mocked Dimensions object. It initializes roomName with a valid value and sets houseFloor to null.
     * Additionally, height, width, and length are provided with valid values.
     * The behavior of dimensionsFactoryDouble.createDimensions() method is defined to return dimensionsDouble when called with
     * the specified height, width, and length.
     * <p>
     * The constructor of the Room class is invoked within a lambda expression using assertThrows(),
     * expecting it to throw an IllegalArgumentException due to the null house floor.
     * The caught exception's message is then compared against the expected value to ensure correctness.
     */
    @Test
    void constructorWithNullFloor() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String roomName = "Room1";
        String houseFloor = null;
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        String expected = "Invalid arguments passed to constructor.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble));

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }


    /**
     * Test case to verify the behavior of the constructor of the Room class when the height provided is NaN (Not a Number).
     * <p>
     * The test arranges mock objects for DeviceFactory and DimensionsFactory using Mockito's mock() method.
     * It initializes roomName and houseFloor with valid values and sets the height to NaN.
     * Additionally, width and length are provided with valid values.
     * The behavior of dimensionsFactoryDouble.createDimensions() method is defined to throw an IllegalArgumentException
     * with the specified message when called with a NaN height.
     * <p>
     * The constructor of the Room class is invoked within a lambda expression using assertThrows(),
     * expecting it to throw an IllegalArgumentException due to the NaN height.
     * The caught exception's message is then compared against the expected value to ensure correctness.
     */
    @Test
    void constructorWithNaNHeight() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        String roomName = "Room1";
        String houseFloor = "Floor1";
        double height = NaN;
        double width = 5;
        double length = 6;
        String expected = "Invalid arguments passed to constructor.";
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble));

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }


    /**
     * Test case to verify the behavior of the constructor of the Room class when the width provided is NaN (Not a Number).
     * <p>
     * The test arranges mock objects for DeviceFactory and DimensionsFactory using Mockito's mock() method.
     * It initializes roomName and houseFloor with valid values and sets the width to NaN.
     * Additionally, height and length are provided with valid values.
     * The behavior of dimensionsFactoryDouble.createDimensions() method is defined to throw an IllegalArgumentException
     * with the specified message when called with a NaN width.
     * <p>
     * The constructor of the Room class is invoked within a lambda expression using assertThrows(),
     * expecting it to throw an IllegalArgumentException due to the NaN width.
     * The caught exception's message is then compared against the expected value to ensure correctness.
     */
    @Test
    void constructorWithNaNWidth() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        String roomName = "Room1";
        String houseFloor = "Floor1";
        double height = 4;
        double width = NaN;
        double length = 6;
        String expected = "Invalid arguments passed to constructor.";
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble));

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }


    /**
     * Test case to verify the behavior of the constructor of the Room class when the length provided is NaN (Not a Number).
     * <p>
     * The test arranges mock objects for DeviceFactory and DimensionsFactory using Mockito's mock() method.
     * It initializes roomName and houseFloor with valid values and sets the length to NaN.
     * Additionally, height and width are provided with valid values.
     * The behavior of dimensionsFactoryDouble.createDimensions() method is defined to throw an IllegalArgumentException
     * with the specified message when called with a NaN length.
     * <p>
     * The constructor of the Room class is invoked within a lambda expression using assertThrows(),
     * expecting it to throw an IllegalArgumentException due to the NaN length.
     * The caught exception's message is then compared against the expected value to ensure correctness.
     */
    @Test
    void constructorWithNaNLength() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        String roomName = "Room1";
        String houseFloor = "Floor1";
        double height = 4;
        double width = 5;
        double length = NaN;
        String expected = "Invalid arguments passed to constructor.";
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble));

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }


    /**
     * Test case to verify the getName() method of the Room class.
     * <p>
     * The test arranges mock objects for DeviceFactory and DimensionsFactory using Mockito's mock() method,
     * along with a mocked Dimensions object. It initializes name, houseFloor, height, width, and length with valid values
     * and sets up the behavior of dimensionsFactoryDouble.createDimensions() method to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The getName() method of the Room object is called to retrieve its name, and it's compared against the expected value.
     * <p>
     * The test asserts that the actual name retrieved from the Room object matches the expected name.
     */
    @Test
    void getNameTest() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String name = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(name, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        String expected = "Room1";

        //Act
        String actual = room1.getName();

        //Assert
        assertTrue(expected.equals(actual));
    }


    /**
     * Test case to verify the getFloor() method of the Room class.
     * <p>
     * The test arranges mock objects for DeviceFactory and DimensionsFactory using Mockito's mock() method,
     * along with a mocked Dimensions object. It initializes name, houseFloor, height, width, and length with valid values
     * and sets up the behavior of dimensionsFactoryDouble.createDimensions() method to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The getFloor() method of the Room object is called to retrieve its floor, and it's compared against the expected value.
     * <p>
     * The test asserts that the actual floor retrieved from the Room object matches the expected floor.
     */
    @Test
    void getFloorTest() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String name = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(name, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        String expected = "1A";

        // Act
        String actual = room1.getFloor();

        // Assert
        assertEquals(expected, actual);
    }


    /**
     * Test case to verify the getDimensions() method of the Room class.
     * <p>
     * The test arranges mock objects for DeviceFactory and DimensionsFactory using Mockito's mock() method,
     * along with a mocked Dimensions object. It initializes name, houseFloor, height, width, and length with valid values
     * and sets up the behavior of dimensionsFactoryDouble.createDimensions() method to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The getHeight(), getWidth(), and getLength() methods of the mocked Dimensions object are then defined to return
     * the respective height, width, and length values.
     * <p>
     * The getDimensions() method of the Room object is called to retrieve its dimensions, and the height, width, and length
     * are extracted from the returned Dimensions object.
     * <p>
     * The test asserts that the actual dimensions retrieved from the Room object match the expected dimensions.
     */
    @Test
    void getDimensionsTest() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String name = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(name, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        when(dimensionsDouble.getHeight()).thenReturn(height);
        when(dimensionsDouble.getWidth()).thenReturn(width);
        when(dimensionsDouble.getLength()).thenReturn(length);
        double[] expected = {height, width, length};
        double[] actual = new double[3];

        // Act
        actual[0] = room1.getDimensions().getHeight();
        actual[1] = room1.getDimensions().getWidth();
        actual[2] = room1.getDimensions().getLength();

        // Assert
        assertArrayEquals(expected, actual);
    }


    /**
     * Test the addDevice() method of the Room class.
     * <p>
     * In the arrangement phase, mock objects are created for DeviceFactory, DimensionsFactory, and Dimensions
     * using Mockito's mock() method, along with a mock object for Device.
     * The roomName and houseFloor are initialized with valid values, and the dimensions are set up.
     * The behavior of the dimensionsFactoryDouble.createDimensions() method is configured to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The name and model of the device are set up, and the behavior of the deviceFactoryDouble.createDevice() method is defined
     * to return deviceDouble when called with these parameters.
     * <p>
     * The addDevice() method of the Room class is then called to add a device with the provided name and model.
     * <p>
     * The test ensures that the addDevice() method returns true, indicating that the device was successfully added to the room.
     */
    @Test
    void addDeviceTest() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        Device deviceDouble = mock(Device.class);
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        String deviceName = "D1";
        String deviceModel = "M1";
        when(deviceFactoryDouble.createDevice(deviceName, deviceModel)).thenReturn(deviceDouble);

        // Act
        boolean result = room1.addDevice(deviceName, deviceModel);

        // Assert
        assertTrue(result);
    }


    /**
     * Test the addDevice() method of the Room class when attempting to add a duplicate device.
     * <p>
     * In the arrangement phase, mock objects are created for DeviceFactory, DimensionsFactory, and Dimensions
     * using Mockito's mock() method, along with a mock object for Device.
     * The roomName and houseFloor are initialized with valid values, and the dimensions are set up.
     * The behavior of the dimensionsFactoryDouble.createDimensions() method is configured to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The name and model of the device are set up, and the behavior of the deviceFactoryDouble.createDevice() method is defined
     * to return deviceDouble when called with these parameters.
     * <p>
     * A device with the same name as the one to be added is already added to the room, and the behavior of the
     * deviceDouble.getName() method is configured to return the same name.
     * <p>
     * The addDevice() method of the Room class is then called to add a device with the same name and model as the existing one.
     * <p>
     * The test ensures that the addDevice() method returns false, indicating that the device was not added due to duplication.
     */
    @Test
    void addDeviceDuplicate() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        Device deviceDouble = mock(Device.class);
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        String deviceName = "D1";
        String deviceModel = "M1";
        when(deviceFactoryDouble.createDevice(deviceName, deviceModel)).thenReturn(deviceDouble);
        when(deviceDouble.getName()).thenReturn(deviceName);
        room1.addDevice(deviceName, deviceModel);

        // Act
        boolean result = room1.addDevice(deviceName, deviceModel);

        // Assert
        assertFalse(result);
    }


    /**
     * Parameterized test to verify the behavior of the addDevice() method of the Room class
     * when a null or empty device name is provided.
     * <p>
     * This test is parameterized using JUnit 5's ParameterizedTest annotation along with NullAndEmptySource
     * and ValueSource annotations to provide null and empty strings as input.
     * <p>
     * In the arrangement phase, mock objects are created for DeviceFactory, DimensionsFactory, and Dimensions
     * using Mockito's mock() method.
     * The roomName and houseFloor are initialized with valid values, and the dimensions are set up.
     * The behavior of the dimensionsFactoryDouble.createDimensions() method is configured to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The deviceModel is set up with a valid value, and the behavior of the deviceFactoryDouble.createDevice() method is defined
     * to throw an IllegalArgumentException with the specified message when called with a null or empty device name.
     * <p>
     * The addDevice() method of the Room class is then called with the null or empty device name.
     * <p>
     * The test ensures that an IllegalArgumentException is thrown with the expected message
     * when attempting to add a device with a null or empty name.
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void addDeviceNullAndEmptyName(String devName) {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        String deviceModel = "M1";
        String expected = "Invalid arguments passed to constructor.";
        when(deviceFactoryDouble.createDevice(devName, deviceModel)).thenThrow(new IllegalArgumentException(expected));

        // Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> room1.addDevice(devName, deviceModel));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Parameterized test to verify the behavior of the addDevice() method of the Room class
     * when a null or empty device model is provided.
     * <p>
     * This test is parameterized using JUnit 5's ParameterizedTest annotation along with NullAndEmptySource
     * and ValueSource annotations to provide null and empty strings as input.
     * <p>
     * In the arrangement phase, mock objects are created for DeviceFactory, DimensionsFactory, and Dimensions
     * using Mockito's mock() method.
     * The roomName and houseFloor are initialized with valid values, and the dimensions are set up.
     * The behavior of the dimensionsFactoryDouble.createDimensions() method is configured to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The deviceName is set up with a valid value, and the behavior of the deviceFactoryDouble.createDevice() method is defined
     * to throw an IllegalArgumentException with the specified message when called with a null or empty device model.
     * <p>
     * The addDevice() method of the Room class is then called with the deviceName and the null or empty model.
     * <p>
     * The test ensures that an IllegalArgumentException is thrown with the expected message
     * when attempting to add a device with a null or empty model.
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void addDeviceNullAndEmptyModel(String model) {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        String deviceName = "D1";
        String expected = "Invalid arguments passed to constructor.";
        when(deviceFactoryDouble.createDevice(deviceName, model)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> room1.addDevice(deviceName, model));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test the getDevices() method of the Room class when the room has no devices.
     * <p>
     * In the arrangement phase, mock objects are created for DeviceFactory, DimensionsFactory, and Dimensions
     * using Mockito's mock() method.
     * The roomName and houseFloor are initialized with valid values, and the dimensions are set up.
     * The behavior of the dimensionsFactoryDouble.createDimensions() method is configured to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The expected result is an empty list of devices.
     * <p>
     * The getDevices() method of the Room class is called to retrieve the list of devices in the room.
     * <p>
     * The test ensures that the list of devices returned by getDevices() is empty.
     */
    @Test
    void getDeviceListEmpty() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        List<Device> expected = Collections.emptyList();

        // Act
        List<Device> result = room1.getDevices();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test the getDevices() method of the Room class when the room has one or more devices.
     * <p>
     * In the arrangement phase, mock objects are created for DeviceFactory, DimensionsFactory, Dimensions, and Device
     * using Mockito's mock() method.
     * The roomName and houseFloor are initialized with valid values, and the dimensions are set up.
     * The behavior of the dimensionsFactoryDouble.createDimensions() method is configured to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * A device is created and added to the room.
     * <p>
     * The expected result is a list containing the added device.
     * <p>
     * The getDevices() method of the Room class is called to retrieve the list of devices in the room.
     * <p>
     * The test ensures that the list of devices returned by getDevices() contains the added device.
     */
    @Test
    void getDeviceList() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        Device deviceDouble = mock(Device.class);
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        String deviceName = "D1";
        String deviceModel = "M1";
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        when(deviceFactoryDouble.createDevice(deviceName, deviceModel)).thenReturn(deviceDouble);
        room1.addDevice(deviceName, deviceModel);
        List<Device> expected = new ArrayList<>();
        expected.add(deviceDouble);

        // Act
        List<Device> result = room1.getDevices();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test the getDevice() method of the Room class to retrieve a device by its name.
     * <p>
     * In the arrangement phase, mock objects are created for DeviceFactory, DimensionsFactory, Dimensions, and Device
     * using Mockito's mock() method.
     * The roomName and houseFloor are initialized with valid values, and the dimensions are set up.
     * The behavior of the dimensionsFactoryDouble.createDimensions() method is configured to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * A device is created and added to the room with a specific name and model.
     * <p>
     * The getDevice() method of the Room class is called with the device's name to retrieve the device.
     * <p>
     * The test ensures that the device returned by getDevice() matches the added device.
     */
    @Test
    void getDeviceByName() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        Device deviceDouble = mock(Device.class);
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        String deviceName = "D1";
        String deviceModel = "M1";
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        when(deviceFactoryDouble.createDevice(deviceName, deviceModel)).thenReturn(deviceDouble);
        room1.addDevice(deviceName, deviceModel);
        when(deviceDouble.getName()).thenReturn(deviceName);

        // Act
        Device result = room1.getDevice(deviceName);

        // Assert
        assertEquals(deviceDouble, result);
    }


    /**
     * Test the getDevice() method of the Room class when trying to retrieve a non-existent device by its name.
     * <p>
     * In the arrangement phase, mock objects are created for DeviceFactory, DimensionsFactory, and Dimensions
     * using Mockito's mock() method.
     * The roomName and houseFloor are initialized with valid values, and the dimensions are set up.
     * The behavior of the dimensionsFactoryDouble.createDimensions() method is configured to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The getDevice() method of the Room class is called with the name of a non-existent device.
     * <p>
     * The test ensures that null is returned when trying to retrieve a device that does not exist in the room.
     */
    @Test
    void getDeviceByNameNonExistentDevice() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String roomName = "Room1";
        String houseFloor = "1A";
        double height = 4;
        double width = 5;
        double length = 6;
        String deviceName = "D1";
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        Room room1 = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);

        // Act
        Device result = room1.getDevice(deviceName);

        // Assert
        assertNull(result);
    }


    /**
     * Test the toString() method of the Room class to ensure the correct string representation of the room object.
     * <p>
     * In the arrangement phase, mock objects are created for DeviceFactory, DimensionsFactory, Dimensions, and Device
     * using Mockito's mock() method.
     * The roomName and houseFloor are initialized with valid values, and the dimensions are set up.
     * The behavior of the dimensionsFactoryDouble.createDimensions() method is configured to return dimensionsDouble.
     * A Room object is then instantiated using these parameters.
     * <p>
     * The expected string representation of the room object is constructed manually based on the provided room attributes.
     * <p>
     * The toString() method of the Room class is called.
     * <p>
     * The test ensures that the string returned by toString() matches the expected string representation of the room object.
     */
    @Test
    void toStringTest() {

        // Arrange
        DeviceFactory deviceFactoryDouble = mock(DeviceFactory.class);
        DimensionsFactory dimensionsFactoryDouble = mock(DimensionsFactory.class);
        Dimensions dimensionsDouble = mock(Dimensions.class);
        String roomName = "Room1";
        String houseFloor = "Floor1";
        double height = 4;
        double width = 5;
        double length = 6;
        when(dimensionsFactoryDouble.createDimensions(height, width, length)).thenReturn(dimensionsDouble);
        when(dimensionsDouble.getHeight()).thenReturn(height);
        when(dimensionsDouble.getWidth()).thenReturn(width);
        when(dimensionsDouble.getLength()).thenReturn(length);
        Room newRoom = new Room(roomName, houseFloor, height, width, length, deviceFactoryDouble, dimensionsFactoryDouble);
        String expected = "Room{_name='Room1', _houseFloor='Floor1', _dimensions={heigth=4.0, width=5.0, length=6.0}, _devices=[]}";

        // Act
        String result = newRoom.toString();

        // Assert
        assertEquals(expected, result);
    }
}
