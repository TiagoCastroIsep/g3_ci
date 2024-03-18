package SmartHome.domain.room;

import SmartHome.domain.device.Device;
import SmartHome.domain.device.DeviceFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room within a house, characterized by a unique name, specific dimensions, and a collection of devices.
 * A room may belong to a specific floor and can contain zero or more devices managed through a device factory.
 * This class also supports dimension management through a dimensions factory.
 */
public class Room {
    private String _name;
    private String _houseFloor;
    private Dimensions _dimensions;
    private List<Device> _devices = new ArrayList<>();
    private final DeviceFactory _deviceFactory;
    private final DimensionsFactory _dimensionsFactory;

    /**
     * Constructs a new Room instance with the given characteristics and dependencies.
     *
     * @param name              The name of the room, must be non-null and non-empty.
     * @param houseFloor        The floor of the house this room belongs to, must be non-null and non-empty.
     * @param height            The height of the room, must be positive.
     * @param width             The width of the room, must be positive.
     * @param length            The length of the room, must be positive.
     * @param deviceFactory     The factory used to create new devices.
     * @param dimensionsFactory The factory used to create room dimensions.
     * @throws IllegalArgumentException If any parameter is invalid.
     */
    public Room(String name, String houseFloor, double height,double width, double length, DeviceFactory deviceFactory, DimensionsFactory dimensionsFactory) {
        if (! validateArguments(name, houseFloor))
            throw new IllegalArgumentException("Invalid arguments passed to constructor.");
        this._name = name;
        this._houseFloor = houseFloor;
        this._deviceFactory = deviceFactory;
        this._dimensionsFactory = dimensionsFactory;
        this._dimensions = _dimensionsFactory.createDimensions(height, width, length);
    }

    /**
     * Validates the provided arguments for room creation.
     *
     * @param name The name to validate.
     * @param houseFloor The house floor to validate.
     * @return True if all arguments are valid, otherwise false.
     */
    private boolean validateArguments(String name, String houseFloor) {
        if (name == null || name.trim().isEmpty()) return false;
        if (houseFloor == null || houseFloor.trim().isEmpty()) return false;
        return true;
    }

    /**
     * Retrieves the name of the room.
     *
     * @return The name of the room.
     */
    public String getName() {
        return this._name;
    }

    /**
     * Retrieves the floor of the room.
     *
     * @return The floor of the room.
     */
    public String getFloor() {
        return this._houseFloor;
    }

    /**
     * Retrieves the dimensions of the room.
     *
     * @return The dimensions of the room.
     */
    public Dimensions getDimensions() {
        return this._dimensions;
    }

   /**
    * Creates a new device and adds it to the list of devices.
    *
    * @param name  The name of the device to be created.
    * @param model The model of the device to be created.
    * @return true if the device was successfully created and added, false if the device already exists.
    //* @throws Ill if the device cannot be instantiated.
    */
   public boolean addDevice(String name, String model) {
      if (!deviceExists(name)) {
         Device device = _deviceFactory.createDevice(name, model);
         _devices.add(device);
         return true;
      }
      return false;
   }

   /**
    * Checks if a device with the specified name exists in the list of devices.
    *
    * @param name The name of the device to check.
    * @return True if the device exists, false otherwise.
    */
   private boolean deviceExists(String name) {
      for (Device device : _devices)
         if (device.getName().equalsIgnoreCase(name)) return true;
       return false;
   }

    /**
     * Retrieves a device by name from the room.
     *
     * @param name The name of the device to retrieve.
     * @return The device with the specified name, or null if no such device exists.
     */
   public Device getDevice(String name) {
      for (Device device : _devices)
         if (device.getName().equalsIgnoreCase(name)) return device;
       return null;
   }

    /**
     * Gets the list of all devices in the room.
     *
     * @return A list of devices in the room, which is empty if no devices are present.
     */
   public List<Device> getDevices() {
       return new ArrayList<>(_devices);
   }

    /**
     * Provides a string representation of the room, including its name, floor, dimensions, and devices.
     *
     * @return A string representation of the room.
     */
    @Override
    public String toString() {
        return "Room{" +
                "_name='" + _name + '\'' +
                ", _houseFloor='" + _houseFloor + '\'' +
                ", _dimensions={heigth=" + _dimensions.getHeight() + ", " +
                "width=" + _dimensions.getWidth() + ", " +
                "length=" + _dimensions.getLength() + "}" +
                ", _devices=" + _devices +
                '}';
    }
}
