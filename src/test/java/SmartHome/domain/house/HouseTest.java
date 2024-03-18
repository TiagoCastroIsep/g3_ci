package SmartHome.domain.house;

import SmartHome.domain.device.Device;
import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.room.Room;
import SmartHome.domain.room.RoomFactory;
import SmartHome.dto.DeviceRoomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the House class.
 */
class HouseTest {
    private House house;
    private LocationFactory locationFactoryMock;
    private RoomFactory roomFactoryMock;
    private DeviceFactory deviceFactoryMock;
    private DimensionsFactory dimensionsFactoryMock;
    private Location locationMock;
    private Room roomMock;
    private GPS gpsMock;

    /**
     * Sets up the environment before each test method is executed.
     * Initializes mock objects for repository, location factory, room factory, dimensions factory,
     * device factory, location, GPS, and room. Creates a new instance of the House class
     * with the mocked dependencies.
     */
    @BeforeEach
    void setUp() {
        locationFactoryMock = mock(LocationFactory.class);
        roomFactoryMock = mock(RoomFactory.class);
        house = new House(locationFactoryMock, roomFactoryMock);
    }

    /**
     * Test method to verify the configuration of a valid location.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Set up the necessary parameters for a valid location, including street, door number,
     *       ZIP code, city, country, latitude, and longitude. Mock the behavior of the location factory to
     *       return a mocked location object with the specified parameters. Mock the behavior of the mocked
     *       location object to return the expected values when queried.</li>
     *   <li>Act: Call the configureLocation method of the house instance with the arranged parameters.</li>
     *   <li>Assert: Verify that the returned location object matches the expected values for street, door number,
     *       ZIP code, city, country, latitude, and longitude.</li>
     * </ul>
     */
    @Test
    void defineValidLocation() {

        // Arrange
        locationMock = mock(Location.class);
        gpsMock = mock(GPS.class);
        String strStreet = "Rua de S. Tomé, s/n";
        String doorNumber = "1";
        String strZip = "4200";
        String strCity = "Porto";
        String strCountry = "Portugal";
        double latitude = 41.178553;
        double longitude = -8.608035;
        when(locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude)).thenReturn(locationMock);
        when(locationMock.getStreet()).thenReturn(strStreet);
        when(locationMock.getDoorNumber()).thenReturn(doorNumber);
        when(locationMock.getZip()).thenReturn(strZip);
        when(locationMock.getCity()).thenReturn(strCity);
        when(locationMock.getCountry()).thenReturn(strCountry);
        when(locationMock.getGps()).thenReturn(gpsMock);
        when(gpsMock.getLatitude()).thenReturn(latitude);
        when(gpsMock.getLongitude()).thenReturn(longitude);

        // Act
        Location location = house.configureLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude);

        // Assert
        assertEquals(location.getStreet(), strStreet);
        assertEquals(location.getDoorNumber(), doorNumber);
        assertEquals(location.getZip(), strZip);
        assertEquals(location.getCity(), strCity);
        assertEquals(location.getCountry(), strCountry);
        assertEquals(location.getGps().getLatitude(), latitude);
        assertEquals(location.getGps().getLongitude(), longitude);
    }

    /**
     * Test method to verify the retrieval of the current location set in the house.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Set up the necessary mock objects including a mocked location factory, room factory,
     *       and repository. Create a new house instance with the mocked dependencies. Define the parameters
     *       for a location including street, door number, ZIP code, city, country, latitude, and longitude. Mock
     *       the behavior of the location factory to return a mocked location object with the specified parameters.</li>
     *   <li>Act: Configure the location in the house instance using the arranged parameters. Retrieve the location
     *       using the getLocation method of the house instance.</li>
     *   <li>Assert: Verify that the retrieved location is not null and matches the mocked location object.</li>
     * </ul>
     */
    @Test
    void getLocation() {

        // Arrange
        locationMock = mock(Location.class);
        LocationFactory locationFactoryMock = mock(LocationFactory.class);
        RoomFactory roomFactoryMock = mock(RoomFactory.class);
        House house = new House(locationFactoryMock, roomFactoryMock);
        String street = "Main Street";
        String doorNumber = "123";
        String zipCode = "12345";
        String city = "Big City";
        String country = "Country";
        double latitude = 40.7128;
        double longitude = -74.0060;
        Location locationMock = new Location(street, doorNumber, zipCode, city, country, latitude, longitude);
        when(locationFactoryMock.createLocation(street, doorNumber, zipCode, city, country, latitude, longitude))
                .thenReturn(locationMock);

        // Act
        house.configureLocation(street, doorNumber, zipCode, city, country, latitude, longitude);
        Location location = house.getLocation();

        // Assert
        assertNotNull(location);
        assertEquals(locationMock, location);
    }

    /**
     * Test method to verify the handling of an invalid street parameter when configuring a location.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Set up the necessary parameters for an invalid location, including an empty street,
     *       door number, ZIP code, city, country, latitude, and longitude. Mock the behavior of the location
     *       factory to throw an IllegalArgumentException with the expected message when attempting to create
     *       a location with the arranged parameters.</li>
     *   <li>Act: Call the createLocation method of the location factory with the arranged parameters and
     *       expect an IllegalArgumentException to be thrown.</li>
     *   <li>Assert: Verify that the thrown exception message matches the expected message.</li>
     * </ul>
     */
    @Test
    void defineInvalidStreetLocation() {

        // Arrange
        locationMock = mock(Location.class);
        String strStreet = "";
        String doorNumber = "1";
        String strZip = "4200";
        String strCity = "Porto";
        String strCountry = "Portugal";
        double latitude = 41.178553;
        double longitude = -8.608035;
        String expected = "Invalid arguments passed to constructor";
        when(locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test method to verify the handling of an invalid door number parameter when configuring a location.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Set up the necessary parameters for an invalid location, including a valid street,
     *       an empty door number, ZIP code, city, country, latitude, and longitude. Mock the behavior of the location
     *       factory to throw an IllegalArgumentException with the expected message when attempting to create
     *       a location with the arranged parameters.</li>
     *   <li>Act: Call the createLocation method of the location factory with the arranged parameters and
     *       expect an IllegalArgumentException to be thrown.</li>
     *   <li>Assert: Verify that the thrown exception message matches the expected message.</li>
     * </ul>
     */
    @Test
    void defineInvalidDoorNumberLocation() {

        // Arrange
        locationMock = mock(Location.class);
        String strStreet = "Rua de S. Tomé, s/n";
        String doorNumber = "";
        String strZip = "4200";
        String strCity = "Porto";
        String strCountry = "Portugal";
        double latitude = 41.178553;
        double longitude = -8.608035;
        String expected = "Invalid arguments passed to constructor";
        when(locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test method to verify the handling of an invalid ZIP code parameter when configuring a location.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Set up the necessary parameters for an invalid location, including a valid street,
     *       door number, an empty ZIP code, city, country, latitude, and longitude. Mock the behavior of the location
     *       factory to throw an IllegalArgumentException with the expected message when attempting to create
     *       a location with the arranged parameters.</li>
     *   <li>Act: Call the createLocation method of the location factory with the arranged parameters and
     *       expect an IllegalArgumentException to be thrown.</li>
     *   <li>Assert: Verify that the thrown exception message matches the expected message.</li>
     * </ul>
     */
    @Test
    void defineInvalidZipLocation() {

        // Arrange
        locationMock = mock(Location.class);
        String strStreet = "Rua de S. Tomé, s/n";
        String doorNumber = "1";
        String strZip = "";
        String strCity = "Porto";
        String strCountry = "Portugal";
        double latitude = 41.178553;
        double longitude = -8.608035;
        String expected = "Invalid arguments passed to constructor";
        when(locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test method to verify the handling of an invalid city parameter when configuring a location.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Set up the necessary parameters for an invalid location, including a valid street,
     *       door number, ZIP code, an empty city, country, latitude, and longitude. Mock the behavior of the location
     *       factory to throw an IllegalArgumentException with the expected message when attempting to create
     *       a location with the arranged parameters.</li>
     *   <li>Act: Call the createLocation method of the location factory with the arranged parameters and
     *       expect an IllegalArgumentException to be thrown.</li>
     *   <li>Assert: Verify that the thrown exception message matches the expected message.</li>
     * </ul>
     */
    @Test
    void defineInvalidCityLocation() {

        // Arrange
        locationMock = mock(Location.class);
        String strStreet = "Rua de S. Tomé, s/n";
        String doorNumber = "1";
        String strZip = "4200";
        String strCity = "";
        String strCountry = "Portugal";
        double latitude = 41.178553;
        double longitude = -8.608035;
        String expected = "Invalid arguments passed to constructor";
        when(locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test method to verify the handling of an invalid country parameter when configuring a location.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Set up the necessary parameters for an invalid location, including a valid street,
     *       door number, ZIP code, city, an empty country, latitude, and longitude. Mock the behavior of the location
     *       factory to throw an IllegalArgumentException with the expected message when attempting to create
     *       a location with the arranged parameters.</li>
     *   <li>Act: Call the createLocation method of the location factory with the arranged parameters and
     *       expect an IllegalArgumentException to be thrown.</li>
     *   <li>Assert: Verify that the thrown exception message matches the expected message.</li>
     * </ul>
     */
    @Test
    void defineInvalidCountryLocation() {

        // Arrange
        locationMock = mock(Location.class);
        String strStreet = "Rua de S. Tomé, s/n";
        String doorNumber = "1";
        String strZip = "4200";
        String strCity = "Porto";
        String strCountry = "";
        double latitude = 41.178553;
        double longitude = -8.608035;
        String expected = "Invalid arguments passed to constructor";
        when(locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test method to verify the handling of invalid gps coordinates when configuring a location.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Set up the necessary parameters for an invalid location, including a valid street,
     *       door number, ZIP code, city, country, invalid latitude, and invalid longitude. Mock the behavior of the location
     *       factory to throw an IllegalArgumentException with the expected message when attempting to create
     *       a location with the arranged parameters.</li>
     *   <li>Act: Call the createLocation method of the location factory with the arranged parameters and
     *       expect an IllegalArgumentException to be thrown.</li>
     *   <li>Assert: Verify that the thrown exception message matches the expected message.</li>
     * </ul>
     */

    @Test
    void defineInvalidGpsCoordinates() {

        // Arrange
        locationMock = mock(Location.class);
        String strStreet = "Rua de S. Tomé, s/n";
        String doorNumber = "1";
        String strZip = "4200";
        String strCity = "Porto";
        String strCountry = "Portugal";
        double latitude = 441.431;
        double longitude = -218.608035;
        String expected = "Invalid arguments passed to constructor";
        when(locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude)).thenThrow(new IllegalArgumentException(expected));

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> locationFactoryMock.createLocation(strStreet, doorNumber, strZip, strCity, strCountry, latitude, longitude));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test method to verify the retrieval of devices grouped by room and functionality.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Create two rooms with different names and dimensions and add devices to each room. Mock the behavior
     *       of the room factory to return the created rooms when invoked with specific parameters. Mock the behavior of the
     *       devices by functionality object to return a mapping of device functionality to device-room DTOs.</li>
     *   <li>Act: Call the getDevicesByRoomAndFunctionality method of the House class with the mocked devices by functionality
     *       object.</li>
     *   <li>Assert: Verify that the returned map contains the expected number of entries and that each entry's list of
     *       device-room DTOs contains the expected number of items with the correct device and room information.</li>
     * </ul>
     */
    @Test
    void getDevicesByRoomAndFunctionality() {

        // Arrange
        Room room1 = new Room("Living Room", "1A", 10, 10, 10, mock(DeviceFactory.class), mock(DimensionsFactory.class));
        Room room2 = new Room("Kitchen", "1B", 8, 8, 8, mock(DeviceFactory.class), mock(DimensionsFactory.class));
        Device device1 = new Device("Lamp", "Model1");
        Device device2 = new Device("Thermostat", "Model2");
        room1.addDevice(device1.getName(), device1.getDeviceModel());
        room2.addDevice(device2.getName(), device2.getDeviceModel());
        when(roomFactoryMock.createRoom("Living Room", "1A", 10, 10, 10, mock(DeviceFactory.class), mock(DimensionsFactory.class))).thenReturn(room1);
        when(roomFactoryMock.createRoom("Kitchen", "1B", 8, 8, 8, mock(DeviceFactory.class), mock(DimensionsFactory.class))).thenReturn(room2);
        DevicesByFunctionality devicesByFunctionality = mock(DevicesByFunctionality.class);
        when(devicesByFunctionality.getGroupedResult()).thenReturn(Collections.singletonMap("Temperature", Collections.singletonList(new DeviceRoomDTO("Thermostat", "Kitchen"))));

        // Act
        Map<String, List<DeviceRoomDTO>> result = house.getDevicesByRoomAndFunctionality(devicesByFunctionality);

        // Assert
        assertEquals(1, result.size());
        assertEquals(1, result.get("Temperature").size());
        assertEquals("Thermostat", result.get("Temperature").get(0).getDevice());
        assertEquals("Kitchen", result.get("Temperature").get(0).getRoom());
    }

    /**
     * Test method to verify the retrieval of devices grouped by room and functionality when multiple rooms have devices
     * with the same functionality.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Create two rooms with different names and dimensions and add devices to each room. Mock the behavior
     *       of the room factory to return the created rooms when invoked with specific parameters. Mock the behavior of the
     *       devices by functionality object to return a mapping of device functionality to device-room DTOs, where multiple
     *       rooms have devices with the same functionality.</li>
     *   <li>Act: Call the getDevicesByRoomAndFunctionality method of the House class with the mocked devices by functionality
     *       object.</li>
     *   <li>Assert: Verify that the returned map contains the expected number of entries and that each entry's list of
     *       device-room DTOs contains the expected number of items with the correct device and room information.</li>
     * </ul>
     */
    @Test
    void getDevicesByRoomAndFunctionality_sameFuncionalityAndDifferentsRooms() {

        // Arrange
        Room room1 = new Room("Living Room", "1A", 10, 10, 10, mock(DeviceFactory.class), mock(DimensionsFactory.class));
        Room room2 = new Room("Kitchen", "1B", 8, 8, 8, mock(DeviceFactory.class), mock(DimensionsFactory.class));
        Device device1 = new Device("Lamp", "Model1");
        Device device2 = new Device("Thermostat", "Model2");
        room1.addDevice(device1.getName(), device1.getDeviceModel());
        room2.addDevice(device2.getName(), device2.getDeviceModel());
        when(roomFactoryMock.createRoom("Living Room", "1A", 10, 10, 10, mock(DeviceFactory.class), mock(DimensionsFactory.class))).thenReturn(room1);
        when(roomFactoryMock.createRoom("Kitchen", "1B", 8, 8, 8, mock(DeviceFactory.class), mock(DimensionsFactory.class))).thenReturn(room2);
        DevicesByFunctionality devicesByFunctionality = mock(DevicesByFunctionality.class);
        when(devicesByFunctionality.getGroupedResult()).thenReturn(Collections.singletonMap("Temperature", Arrays.asList(
                new DeviceRoomDTO("Thermostat", "Kitchen"),
                new DeviceRoomDTO("Thermostat", "Bedroom"),
                new DeviceRoomDTO("Thermostat", "Office")
        )));

        // Act
        Map<String, List<DeviceRoomDTO>> result = house.getDevicesByRoomAndFunctionality(devicesByFunctionality);

        // Assert
        assertEquals(1, result.size());
        assertEquals(3, result.get("Temperature").size());
        assertEquals("Thermostat", result.get("Temperature").get(0).getDevice());
        assertEquals("Kitchen", result.get("Temperature").get(0).getRoom());
        assertEquals("Thermostat", result.get("Temperature").get(1).getDevice());
        assertEquals("Bedroom", result.get("Temperature").get(1).getRoom());
        assertEquals("Thermostat", result.get("Temperature").get(2).getDevice());
        assertEquals("Office", result.get("Temperature").get(2).getRoom());
    }

    /**
     * Test method to verify the addition of a room to the house when valid parameters are provided.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Prepare the necessary data for adding a room, including the room's name, floor, dimensions, and
     *       associated mock objects for the room, device factory, and dimensions factory. Mock the behavior of the room
     *       factory to return a mock room when invoked with specific parameters.</li>
     *   <li>Act: Call the addRoom method of the House class with the provided parameters and mock objects.</li>
     *   <li>Assert: Verify that the method returns true, indicating successful addition of the room, and that the returned
     *       list of rooms contains the expected room object.</li>
     * </ul>
     */
    @Test
    void addRoom_True() {

        // Arrange
        deviceFactoryMock = mock(DeviceFactory.class);
        dimensionsFactoryMock = mock(DimensionsFactory.class);
        roomMock = mock(Room.class);
        String roomName = "Living Room";
        String houseFloor = "1A";
        double height = 10;
        double width = 10;
        double length = 10;
        when(roomFactoryMock.createRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock)).thenReturn(roomMock);
        when(roomMock.getName()).thenReturn(roomName);

        // Act
        boolean result = house.addRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock);
        List<Room> result2 = house.getRooms();

        // Assert
        assertTrue(result);
        assertEquals(List.of(roomMock), result2);
    }

    /**
     * Test method to verify that adding a room with a duplicate name and floor combination fails as expected.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Prepare the necessary data for adding a room, including the room's name, floor, dimensions, and
     *       associated mock objects for the room, device factory, and dimensions factory. Mock the behavior of the room
     *       factory to return a mock room when invoked with specific parameters. Add a room with the same name and floor
     *       to the house to simulate a duplicate room.</li>
     *   <li>Act: Call the addRoom method of the House class with the parameters of the duplicate room.</li>
     *   <li>Assert: Verify that the method returns false, indicating failure to add the duplicate room.</li>
     * </ul>
     */
    @Test
    void addRoom_DuplicateRoom() {

        // Arrange
        deviceFactoryMock = mock(DeviceFactory.class);
        dimensionsFactoryMock = mock(DimensionsFactory.class);
        roomMock = mock(Room.class);
        String roomName = "Living Room";
        String houseFloor = "1A";
        double height = 10;
        double width = 10;
        double length = 10;
        when(roomFactoryMock.createRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock)).thenReturn(roomMock);
        when(roomMock.getName()).thenReturn(roomName);
        house.addRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock);

        // Act
        boolean result = house.addRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock);

        // Assert
        assertFalse(result);
    }

    /**
     * Test method to verify that adding a room with invalid dimensions fails as expected.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Prepare the necessary data for adding a room, including the room's name, floor, dimensions, and
     *       associated mock objects for the room, device factory, and dimensions factory. Mock the behavior of the room
     *       factory to throw an IllegalArgumentException when invoked with negative height or width.</li>
     *   <li>Act: Call the addRoom method of the House class with the parameters of the room with invalid dimensions.</li>
     *   <li>Assert: Verify that the method returns false, indicating failure to add the room due to invalid dimensions.</li>
     * </ul>
     */
    @Test
    void addRoom_InvalidDimensions() {

        // Arrange
        deviceFactoryMock = mock(DeviceFactory.class);
        dimensionsFactoryMock = mock(DimensionsFactory.class);
        roomMock = mock(Room.class);
        String roomName = "Kitchen";
        String houseFloor = "0";
        double height = -5;
        double width = -10;
        double length = 10;
        when(roomFactoryMock.createRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock)).thenThrow(IllegalArgumentException.class);

        // Act
        boolean result = house.addRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock);

        // Assert
        assertFalse(result);
    }

    /**
     * Test method to verify that retrieving a room by a valid name returns the expected room object.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Prepare the necessary data for adding a room, including the room's name, floor, dimensions, and
     *       associated mock objects for the room, device factory, and dimensions factory. Add the room to the house. Mock
     *       the behavior of the room factory to return a mock room when invoked with the specified parameters.</li>
     *   <li>Act: Call the getRoom method of the House class with the name of the room.</li>
     *   <li>Assert: Verify that the method returns the expected room object, matching the one added to the house.</li>
     * </ul>
     */
    @Test
    void getRoom_ValidName() {

        // Arrange
        deviceFactoryMock = mock(DeviceFactory.class);
        dimensionsFactoryMock = mock(DimensionsFactory.class);
        roomMock = mock(Room.class);
        String roomName = "Living Room";
        String houseFloor = "1A";
        double height = 10;
        double width = 10;
        double length = 10;
        when(roomFactoryMock.createRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock)).thenReturn(roomMock);
        when(roomMock.getName()).thenReturn(roomName);


        // Act
        house.addRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock);
        Room result = house.getRoom(roomName);

        // Assert
        assertEquals(roomMock, result);
    }

    /**
     * Test method to verify that attempting to retrieve a room by a name that does not exist returns null.
     *
     * <p>Scenario:
     * <ul>
     *   <li>Arrange: Prepare the necessary data for adding a room, including the room's name, floor, dimensions, and
     *       associated mock objects for the room, device factory, and dimensions factory. Add the room to the house. Mock
     *       the behavior of the room factory to return a mock room when invoked with the specified parameters.</li>
     *   <li>Act: Call the getRoom method of the House class with a name that does not exist.</li>
     *   <li>Assert: Verify that the method returns null, indicating that no room with the provided name exists in the house.</li>
     * </ul>
     */
    @Test
    void getRoom_NotExist() {

        // Arrange
        deviceFactoryMock = mock(DeviceFactory.class);
        dimensionsFactoryMock = mock(DimensionsFactory.class);
        roomMock = mock(Room.class);
        String roomName = "Living Room";
        String houseFloor = "1A";
        double height = 10;
        double width = 10;
        double length = 10;
        when(roomFactoryMock.createRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock)).thenReturn(roomMock);
        when(roomMock.getName()).thenReturn(roomName);
        house.addRoom(roomName, houseFloor, height, width, length, deviceFactoryMock, dimensionsFactoryMock);

        // Act
        Room result = house.getRoom(roomName + "Invalid");

        // Assert
        assertNull(result);
    }
}
