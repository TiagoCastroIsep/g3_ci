package SmartHome.domain.device;

import SmartHome.domain.actuators.Actuator;
import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.device.Device;
import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static SmartHome.domain.constants.Constants.ACTUATOR_PATH;
import static SmartHome.domain.constants.Constants.SENSOR_PATH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the Device class.
 * It tests various aspects of the Device class behavior.
 */
class DeviceTest {
    private String name;
    private String deviceModel;
    private Device myDevice;

    /**
     * Sets up the necessary objects and dependencies before each test method execution.
     * This method is annotated with {@code @BeforeEach} to ensure it is executed before each test method.
     * It initializes a mocked instance of Repository, assigns values to name and deviceModel fields,
     * and creates an instance of the Device class for testing.
     */
    @BeforeEach
    public void setup() {
        name = "Heater";
        deviceModel = "Xiaomi";
        myDevice = new Device(name, deviceModel);
    }

    /**
     * Test method to verify the behaviour of the Device constructor when a null name is provided.
     *
     * <p>This test ensures that the constructor throws an {@code IllegalArgumentException}
     * when invoked with a null name argument.</p>
     */
    @Test
    void constructorWithNullNameRepository() {

        // Arrange
        name = null;
        String expected = "Invalid arguments passed to constructor.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Device(name, deviceModel));
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Test method to verify the behaviour of the Device constructor when an empty name is provided.
     *
     * <p>This test ensures that the constructor throws an {@code IllegalArgumentException}
     * when invoked with an empty name argument.</p>
     */
    @Test
    void constructorWithEmptyNameRepository() {

        // Arrange
        name = "";
        String expected = "Invalid arguments passed to constructor.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Device(name, deviceModel));
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Test method to verify the behaviour of the Device constructor when a null device model is provided.
     *
     * <p>This test ensures that the constructor throws an {@code IllegalArgumentException}
     * when invoked with a null device model argument.</p>
     */
    @Test
    void constructorWithNullDeviceModelRepository() {

        // Arrange
        deviceModel = null;
        String expected = "Invalid arguments passed to constructor.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Device(name, deviceModel));
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Test method to verify the behaviour of the Device constructor when an empty device model is provided.
     *
     * <p>This test ensures that the constructor throws an {@code IllegalArgumentException}
     * when invoked with an empty device model argument.</p>
     */
    @Test
    void constructorWithEmptyDeviceModelRepository() {

        // Arrange
        deviceModel = "";
        String expected = "Invalid arguments passed to constructor.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Device(name, deviceModel));
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Test method to verify the getName() functionality of the Device class.
     *
     * <p>This test ensures that the {@code getName()} method returns the correct name of the device.</p>
     */
    @Test
    void getName() {

        // Arrange
        String expected = "Heater";

        // Act
        String result = myDevice.getName();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test method to verify the getDeviceModel() functionality of the Device class.
     *
     * <p>This test ensures that the {@code getDeviceModel()} method returns the correct device model.</p>
     */
    @Test
    void getDeviceModel() {

        // Arrange
        String expected = "Xiaomi";

        // Act
        String result = myDevice.getDeviceModel();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test method to verify that the Device object is not null.
     *
     * <p>This test ensures that the Device object created during setup is not null.</p>
     */
    @Test
    void objectNotNull() {

        // Act
        boolean result = myDevice == null;

        // Assert
        assertFalse(result);
    }

    /**
     * Test method to verify that two Device objects have different names.
     *
     * <p>This test ensures that when two Device objects are created with different names,
     * their names are not equal.</p>
     */
    @Test
    void objectDifferentName() {

        // Arrange
        Device newDevice = new Device("AirConditioner", deviceModel);
        String expected = newDevice.getName();

        // Act
        String result = myDevice.getName();

        // Assert
        assertFalse(expected.equals(result));
    }

    /**
     * Test method to verify that two Device objects have different device models.
     *
     * <p>This test ensures that when two Device objects are created with different device models,
     * their device models are not equal.</p>
     */
    @Test
    void objectDifferentDeviceModel() {

        // Arrange
        Device newDevice = new Device(name, "Samsung");
        String expected = newDevice.getDeviceModel();

        // Act
        String result = myDevice.getDeviceModel();

        // Assert
        assertFalse(expected.equals(result));
    }

    /**
     * Test method to verify the getIsActive() functionality of the Device class.
     *
     * <p>This test ensures that the {@code getIsActive()} method returns the correct value indicating
     * whether the device is active or not.</p>
     */
    @Test
    void getIsActive() {

        // Arrange
        myDevice.switchDevice(true);
        boolean expected = true;

        // Act
        boolean result = myDevice.getIsActive();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test method to verify the switchDevice() functionality of the Device class when switching from false to true.
     *
     * <p>This test ensures that the {@code switchDevice()} method correctly switches the device state
     * from inactive (false) to active (true) and returns true to indicate the state change.</p>
     */
    @Test
    void switchDeviceFromFalseToTrue() {

        // Act
        boolean result = myDevice.switchDevice(true);

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the switchDevice() functionality of the Device class when switching from false to false.
     *
     * <p>This test ensures that the {@code switchDevice()} method correctly handles the scenario where
     * the device state remains inactive (false) and returns false to indicate no state change.</p>
     */
    @Test
    void switchDeviceFromFalseToFalse() {

        // Act
        boolean result = myDevice.switchDevice(false);

        // Assert
        assertFalse(result);
    }

    /**
     * Test method to verify the switchDevice() functionality of the Device class when switching from true to false.
     *
     * <p>This test ensures that the {@code switchDevice()} method correctly switches the device state
     * from active (true) to inactive (false) and returns true to indicate the state change.</p>
     */
    @Test
    void switchDeviceFromTrueToFalse() {

        // Arrange
        myDevice.switchDevice(true);

        // Act
        boolean result = myDevice.switchDevice(false);

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the switchDevice() functionality of the Device class when switching from true to true.
     *
     * <p>This test ensures that the {@code switchDevice()} method correctly handles the scenario where
     * the device state remains active (true) and returns false to indicate no state change.</p>
     */
    @Test
    void switchDeviceFromTrueToTrue() {

        // Arrange
        myDevice.switchDevice(true);

        // Act
        boolean result = myDevice.switchDevice(true);

        // Assert
        assertFalse(result);
    }

    /**
     * Test method to verify the addSensor() functionality of the Device class when adding a valid sensor.
     *
     * <p>This test ensures that the {@code addSensor()} method correctly adds a sensor to the device,
     * verifies that the sensor is added to the repository, and returns the added sensor with the correct name.</p>
     */
    @Test
    void addValidSensor() {

        // Arrange
        String sensorName = "TemperatureSensor";
        String sensorModel = "TemperatureSensor";
        Sensor sensorDouble = mock(Sensor.class);
        when(sensorDouble.getName()).thenReturn(sensorName);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);
        when(sensorCatalogueDouble.getSensor(sensorName, SENSOR_PATH, sensorName, valueFactoryDouble)).thenReturn(sensorDouble);

        // Act
        Sensor sensor = myDevice.addSensor(sensorModel, sensorName, sensorCatalogueDouble, valueFactoryDouble);

        // Assert
        assertEquals(sensorName, sensor.getName());
    }

    /**
     * Test method to verify the addSensor() functionality of the Device class when adding a duplicated sensor.
     *
     * <p>This test ensures that the {@code addSensor()} method correctly handles the scenario where
     * a sensor with the same name already exists in the device, and returns null without adding the duplicated sensor.</p>
     */
    @Test
    void addDuplicatedSensor() {

        // Arrange
        String sensorName = "TemperatureSensor";
        String sensorModel = "TemperatureSensor";
        Sensor sensorDouble = mock(Sensor.class);
        when(sensorDouble.getName()).thenReturn(sensorName);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);
        when(sensorCatalogueDouble.getSensor(sensorName, SENSOR_PATH, sensorName, valueFactoryDouble)).thenReturn(sensorDouble);
        myDevice.addSensor(sensorModel, sensorName, sensorCatalogueDouble, valueFactoryDouble);

        // Act
        Sensor sensor = myDevice.addSensor(sensorModel, sensorName, sensorCatalogueDouble, valueFactoryDouble);

        // Assert
        assertNull(sensor);
    }

    /**
     * Test method to verify the addSensor() functionality of the Device class when adding an invalid sensor.
     *
     * <p>This test ensures that the {@code addSensor()} method correctly handles the scenario where
     * an invalid sensor (with invalid name and model) is attempted to be added, and returns null without adding the sensor.</p>
     */
    @Test
    void addInvalidSensor() {

        // Arrange
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Sensor sensor = myDevice.addSensor("123", "123", sensorCatalogueDouble, valueFactoryDouble);

        // Assert
        assertNull(sensor);
    }

    /**
     * Test method to verify the getSensor() functionality of the Device class when retrieving a valid sensor.
     *
     * <p>This test ensures that the {@code getSensor()} method correctly retrieves a sensor by its name from the device.</p>
     */
    @Test
    void validGetSensor() {

        // Arrange
        String sensorName = "TemperatureSensor";
        String sensorModel = "TemperatureSensor";
        Sensor sensorDouble = mock(Sensor.class);
        when(sensorDouble.getName()).thenReturn(sensorName);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);
        when(sensorCatalogueDouble.getSensor(sensorName, SENSOR_PATH, sensorName, valueFactoryDouble)).thenReturn(sensorDouble);
        Sensor expected = myDevice.addSensor(sensorModel, sensorName, sensorCatalogueDouble, valueFactoryDouble);

        // Act
        Sensor result = myDevice.getSensor(sensorName);

        // Assert
        assertEquals(expected.getName(), result.getName());
    }

    /**
     * Test method to verify the getSensor() functionality of the Device class when attempting to retrieve an invalid sensor.
     *
     * <p>This test ensures that the {@code getSensor()} method returns null when attempting to retrieve a sensor by its name
     * that does not exist in the device.</p>
     */
    @Test
    void invalidGetSensor() {

        // Arrange
        String sensorName = "TemperatureSensor";
        Sensor sensorDouble = mock(Sensor.class);
        when(sensorDouble.getName()).thenReturn(sensorName);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);
        when(sensorCatalogueDouble.getSensor(sensorName, SENSOR_PATH, sensorName, valueFactoryDouble)).thenReturn(sensorDouble);

        // Act
        Sensor result = myDevice.getSensor(sensorName);

        // Assert
        assertNull(result);
    }

    /**
     * Test method to verify the sensorExists() functionality of the Device class when a valid sensor exists.
     *
     * <p>This test ensures that the {@code sensorExists()} method correctly returns true
     * when a sensor with the given name exists in the device.</p>
     */
    @Test
    void validSensorExists() {

        // Arrange
        String sensorName = "TemperatureSensor";
        String sensorModel = "TemperatureSensor";
        Sensor sensorDouble = mock(Sensor.class);
        when(sensorDouble.getName()).thenReturn(sensorName);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);
        when(sensorCatalogueDouble.getSensor(sensorName, SENSOR_PATH, sensorName, valueFactoryDouble)).thenReturn(sensorDouble);
        myDevice.addSensor(sensorModel, sensorName, sensorCatalogueDouble, valueFactoryDouble);

        // Act
        boolean result = myDevice.sensorExists(sensorName);

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the sensorExists() functionality of the Device class when an invalid sensor is queried.
     *
     * <p>This test ensures that the {@code sensorExists()} method correctly returns false
     * when a sensor with the given name does not exist in the device.</p>
     */
    @Test
    void invalidSensorExists() {

        // Arrange
        String sensorName = "TemperatureSensor";
        String sensorModel = "TemperatureSensor";
        Sensor sensorDouble = mock(Sensor.class);
        when(sensorDouble.getName()).thenReturn(sensorName);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);
        when(sensorCatalogueDouble.getSensor(sensorName, SENSOR_PATH, sensorName, valueFactoryDouble)).thenReturn(sensorDouble);
        myDevice.addSensor(sensorModel, sensorName, sensorCatalogueDouble, valueFactoryDouble);

        // Act
        boolean result = myDevice.sensorExists("HumiditySensor");

        // Assert
        assertFalse(result);
    }

    /**
     * Test method to verify the getSensors() functionality of the Device class.
     *
     * <p>This test ensures that the {@code getSensors()} method correctly retrieves all sensors
     * added to the device and returns them as a list.</p>
     */
    @Test
    void getSensors() {

        // Arrange
        String sensorName = "TemperatureSensor";
        String sensorModel = "TemperatureSensor";
        Sensor sensorDouble = mock(Sensor.class);
        when(sensorDouble.getName()).thenReturn(sensorName);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);
        when(sensorCatalogueDouble.getSensor(sensorName, SENSOR_PATH, sensorName, valueFactoryDouble)).thenReturn(sensorDouble);
        myDevice.addSensor(sensorModel, sensorName, sensorCatalogueDouble, valueFactoryDouble);

        // Act
        Sensor sensor = myDevice.getSensor(sensorName);
        List<Sensor> sensors = myDevice.getSensors();

        // Assert
        assertEquals(1, sensors.size());
        assertEquals("TemperatureSensor", sensors.get(0).getName());
        assertTrue(sensors.contains(sensor));
    }

    /**
     * Test method to verify the addActuator() functionality of the Device class when adding a valid actuator.
     *
     * <p>This test ensures that the {@code addActuator()} method correctly adds an actuator to the device,
     * verifies that the actuator is added to the repository, and returns the added actuator with the correct name.</p>
     */
    @Test
    void addValidActuator() {

        // Arrange
        String actuatorName = "BlindRollerActuator";
        String actuatorModel = "BlindRollerActuator";
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        ActuatorCatalogue actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
        ValueFactory valueFactory = mock(ValueFactory.class);
        when(actuatorCatalogueDouble.getActuator(actuatorModel, ACTUATOR_PATH, actuatorName, valueFactory)).thenReturn(actuatorDouble);

        // Act
        Actuator actuator = myDevice.addActuator(actuatorModel, actuatorName, actuatorCatalogueDouble, valueFactory);

        // Assert
        assertEquals(actuatorName, actuator.getName());
    }

    /**
     * Test method to verify the addActuator() functionality of the Device class when adding a duplicated actuator.
     *
     * <p>This test ensures that the {@code addActuator()} method correctly handles the scenario where
     * an actuator with the same name already exists in the device, and returns null without adding the duplicated actuator.</p>
     */
    @Test
    void addDuplicatedActuator() {

        // Arrange
        String actuatorName = "BlindRollerActuator";
        String actuatorModel = "BlindRollerActuator";
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        ActuatorCatalogue actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
        ValueFactory valueFactory = mock(ValueFactory.class);
        when(actuatorCatalogueDouble.getActuator(actuatorName, ACTUATOR_PATH, name, valueFactory)).thenReturn(actuatorDouble);
        myDevice.addActuator(actuatorModel, actuatorName, actuatorCatalogueDouble, valueFactory);

        // Act
        Actuator actuator = myDevice.addActuator(actuatorModel, actuatorName, actuatorCatalogueDouble, valueFactory);

        // Assert
        assertNull(actuator);
    }

    /**
     * Test method to verify the addActuator() functionality of the Device class when adding an invalid actuator.
     *
     * <p>This test ensures that the {@code addActuator()} method correctly handles the scenario where
     * an invalid actuator (with invalid name and model) is attempted to be added, and returns null without adding the actuator.</p>
     */
    @Test
    void addInvalidActuator() {

        // Arrange
        String actuatorName = "BlinderRollerActuator";
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        ActuatorCatalogue actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
        ValueFactory valueFactory = mock(ValueFactory.class);
        when(actuatorCatalogueDouble.getActuator(actuatorName, ACTUATOR_PATH, name, valueFactory)).thenReturn(actuatorDouble);

        // Act
        Actuator actuator = myDevice.addActuator("123", "123", actuatorCatalogueDouble, valueFactory);

        // Assert
        assertNull(actuator);
    }

    /**
     * Test method to verify the getActuator() functionality of the Device class when retrieving a valid actuator.
     *
     * <p>This test ensures that the {@code getActuator()} method correctly retrieves an actuator by its name from the device.</p>
     */
    @Test
    void validGetActuator() {

        // Arrange
        String actuatorName = "BlindRollerActuator";
        String actuatorModel = "BlindRollerActuator";
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        ActuatorCatalogue actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
        ValueFactory valueFactory = mock(ValueFactory.class);
        when(actuatorCatalogueDouble.getActuator(actuatorModel, ACTUATOR_PATH, actuatorName, valueFactory)).thenReturn(actuatorDouble);
        myDevice.addActuator(actuatorModel, actuatorName, actuatorCatalogueDouble, valueFactory);

        // Act
        Actuator result = myDevice.getActuator(actuatorName);

        // Assert
        assertEquals(actuatorName, result.getName());
    }

    /**
     * Test method to verify the getActuator() functionality of the Device class when attempting to retrieve an invalid actuator.
     *
     * <p>This test ensures that the {@code getActuator()} method returns null when attempting to retrieve an actuator
     * by its name that does not exist in the device.</p>
     */
    @Test
    void invalidGetActuator() {

        // Arrange
        String actuatorName = "BlinderRollerActuator";

        // Act
        Actuator result = myDevice.getActuator(actuatorName);

        // Assert
        assertNull(result);
    }

    /**
     * Test method to verify the actuatorExists() functionality of the Device class when a valid actuator exists.
     *
     * <p>This test ensures that the {@code actuatorExists()} method correctly returns true
     * when an actuator with the given name exists in the device.</p>
     */
    @Test
    void validActuatorExists() {

        // Arrange
        String actuatorName = "BlindRollerActuator";
        String actuatorModel = "BlindRollerActuator";
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        ActuatorCatalogue actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
        ValueFactory valueFactory = mock(ValueFactory.class);
        when(actuatorCatalogueDouble.getActuator(actuatorModel, ACTUATOR_PATH, actuatorName, valueFactory)).thenReturn(actuatorDouble);
        myDevice.addActuator(actuatorModel, actuatorName, actuatorCatalogueDouble, valueFactory);

        // Act
        boolean result = myDevice.actuatorExists(actuatorName);

        // Assert
        assertTrue(result);
    }

    /**
     * Test method to verify the actuatorExists() functionality of the Device class when an invalid actuator is queried.
     *
     * <p>This test ensures that the {@code actuatorExists()} method correctly returns false
     * when an actuator with the given name does not exist in the device.</p>
     */
    @Test
    void invalidActuatorExists() {

        // Arrange
        String actuatorName = "BlinderRollerActuator";
        String actuatorModel = "BlinderRollerActuator";
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        ActuatorCatalogue actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
        ValueFactory valueFactory = mock(ValueFactory.class);
        when(actuatorCatalogueDouble.getActuator(actuatorModel, ACTUATOR_PATH, name, valueFactory)).thenReturn(actuatorDouble);
        myDevice.addActuator(actuatorModel, actuatorName, actuatorCatalogueDouble, valueFactory);

        // Act
        boolean result = myDevice.actuatorExists("123");

        // Assert
        assertFalse(result);
    }

    /**
     * Test method to verify the getActuators() functionality of the Device class.
     *
     * <p>This test ensures that the {@code getActuators()} method correctly retrieves all actuators
     * added to the device and returns them as a list.</p>
     */
    @Test
    void getActuators() {

        // Arrange
        String actuatorModel = "BlindRollerActuator";
        String actuatorName = "BlindRollerActuator";
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorDouble.getName()).thenReturn(actuatorModel);
        ActuatorCatalogue actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
        ValueFactory valueFactory = mock(ValueFactory.class);
        when(actuatorCatalogueDouble.getActuator(actuatorModel, ACTUATOR_PATH, actuatorName, valueFactory)).thenReturn(actuatorDouble);
        myDevice.addActuator(actuatorModel, actuatorModel, actuatorCatalogueDouble, valueFactory);

        // Act
        Actuator actuator = myDevice.getActuator(actuatorModel);
        List<Actuator> actuators = myDevice.getActuators();

        // Assert
        assertEquals("BlindRollerActuator", actuators.get(0).getName());
        assertTrue(actuators.contains(actuator));
    }

    /**
     * Test method to verify the getActuatorFunctionalities() functionality of the Device class.
     *
     * <p>This test ensures that the {@code getActuatorFunctionalities()} method correctly retrieves
     * the list of actuator functionalities supported by the device.</p>
     */
    @Test
    void getActuatorFunctionalities() {

        // Arrange
        ActuatorFunctionality blindRoller = ActuatorFunctionality.BlindRoller;

        // Act
        List<ActuatorFunctionality> result = myDevice.getActuatorFunctionalities();

        // Assert
        assertTrue(result.contains(blindRoller));
    }

    /**
     * Test method to verify the getSensorFunctionalities() functionality of the Device class.
     *
     * <p>This test ensures that the {@code getSensorFunctionalities()} method correctly retrieves
     * the list of sensor functionalities supported by the device.</p>
     */
    @Test
    void getSensorFunctionalities() {

        // Arrange
        SensorFunctionality windDirection = SensorFunctionality.Wind;

        // Act
        List<SensorFunctionality> result = myDevice.getSensorFunctionalities();

        // Assert
        assertTrue(result.contains(windDirection));
    }
    /**
     * This test verifies the behavior of the {@code addActuator} method in the {@code Device} class
     * when trying to add an actuator with a name that already exists in the device.
     *
     * The test follows these steps:
     *
     * Arrange: A mock actuator with a specific name is created and added to the device for the first time.
     * Act: The same actuator is attempted to be added to the device again.
     * Assert: The test asserts that the {@code addActuator} method returns null, indicating that the actuator was not added because an actuator with the same name already exists.
     *
     *
     * @test This is a test aimed to test the {@code addActuator} method of the {@code Device} class.
     */
    @Test
    void addActuatorDuplicatedName() {
        // Arrange
        Device device = new Device("DeviceName", "DeviceModel");
        ActuatorCatalogue actuatorCatalogueDouble = mock(ActuatorCatalogue.class);
       ValueFactory valueFactoryDouble = mock(ValueFactory.class);
        String actuatorName = "ActuatorName";
        String actuatorModel = "ActuatorModel";
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        when(actuatorCatalogueDouble.getActuator(actuatorModel, ACTUATOR_PATH, actuatorName, valueFactoryDouble)).thenReturn(actuatorDouble);

        // Act
        device.addActuator(actuatorModel, actuatorName, actuatorCatalogueDouble, valueFactoryDouble); // Add the actuator for the first time
        Actuator result = device.addActuator(actuatorModel, actuatorName, actuatorCatalogueDouble, valueFactoryDouble); // Try to add the same actuator again

        // Assert
        assertNull(result, "Adding an actuator with a duplicated name should return null");
    }
}