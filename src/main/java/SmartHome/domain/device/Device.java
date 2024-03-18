package SmartHome.domain.device;

import SmartHome.domain.actuators.Actuator;
import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;

import java.util.ArrayList;
import java.util.List;

import static SmartHome.domain.constants.Constants.*;

/**
 * Represents a smart home device, managing its operational state, sensors, and actuators. It interacts
 * with a {@link Repository} to persist device information and utilizes {@link SensorCatalogue} and
 * {@link ActuatorCatalogue} for device capabilities. This class supports adding sensors and actuators,
 * toggling device active state, and querying device status and functionalities.
 */
public class Device {
    private final String _name;
    private final String _deviceModel;
    private final List<Sensor> _sensors;
    private final List<Actuator> _actuators;
    private boolean _isActive;
    private final ActuatorCatalogue _actuatorCatalogue = new ActuatorCatalogue(ACTUATOR_CONFIG);
    private final SensorCatalogue _sensorCatalogue = new SensorCatalogue(SENSOR_CONFIG);

    /**
     * Initializes a new Device with specified characteristics, setting it to an inactive state by default.
     * Validates the provided name and device model to ensure they are not null or empty.
     *
     * @param name        The human-readable name for the device.
     * @param deviceModel The model identifier of the device, representing its type and capabilities.
     * @throws IllegalArgumentException If either the name or deviceModel are null, empty, or otherwise invalid.
     */
    public Device(String name, String deviceModel) {
        if (!validateArguments(name, deviceModel)) {
            throw new IllegalArgumentException("Invalid arguments passed to constructor.");
        }
        this._name = name;
        this._deviceModel = deviceModel;
        this._isActive = false;
        this._sensors = new ArrayList<>();
        this._actuators = new ArrayList<>();
    }

    /**
     * Validates the provided name and device model to ensure they are not null or empty.
     *
     * @param name The human-readable name for the device.
     * @param deviceModel The model identifier of the device, representing its type and capabilities.
     * @return {@code true} if both the name and device model are valid, or {@code false} if either is invalid.
     */
    private boolean validateArguments(String name, String deviceModel) {
        if (name == null || name.trim().isEmpty()) return false;
        if (deviceModel == null || deviceModel.trim().isEmpty()) return false;
        return true;
    }

    /**
     * Switches the device's active state to either on or off, depending on the {@code isActive} parameter.
     * If the current state is the same as the desired state, no change is made.
     *
     * @param isActive The desired state of the device: {@code true} to activate or {@code false} to deactivate.
     * @return {@code true} if the device's state was changed as a result of this call, or {@code false} if the
     *         desired state was already the current state.
     */
    public boolean switchDevice(boolean isActive) {
        if (isActive == this._isActive) return false;
        this._isActive = isActive;
        return true;
    }

    /**
     * Retrieves the name of the device.
     *
     * @return The name of the device as a {@link String}.
     */
    public String getName() {
        return _name;
    }

    /**
     * Checks if the device is currently active.
     *
     * @return {@code true} if the device is active, or {@code false} otherwise.
     */
    public boolean getIsActive() {
        return _isActive;
    }

    /**
     * Retrieves the model identifier of the device.
     *
     * @return The device model as a {@link String}.
     */
    public String getDeviceModel() {
        return _deviceModel;
    }

    /**
     * Retrieves the sensor with the specified name, if it exists.
     *
     * @param name The name of the sensor to retrieve.
     * @return The sensor with the specified name, or {@code null} if no such sensor exists.
     */
    public Sensor getSensor(String name) {
        for (Sensor sensor : _sensors)
            if ((sensor.getName().equalsIgnoreCase(name))) return sensor;
        return null;
    }

    /**
     * Retrieves the actuator with the specified name, if it exists.
     *
     * @param name The name of the actuator to retrieve.
     * @return The actuator with the specified name, or {@code null} if no such actuator exists.
     */
    public Actuator getActuator(String name) {
        for (Actuator actuator : _actuators)
            if ((actuator.getName().equalsIgnoreCase(name))) return actuator;
        return null;
    }

    /**
     * Adds a sensor to the device with the specified name and model, if it does not already exist.
     *
     * @param strModel The model identifier of the sensor to add.
     * @param name The name of the sensor to add.
     * @param sensorCatalogue The catalogue of sensors to retrieve the sensor from.
     * @param valueFactory The factory for creating sensor values.
     * @return The sensor that was added, or {@code null} if a sensor with the specified name already exists.
     */
    public Sensor addSensor(String strModel, String name, SensorCatalogue sensorCatalogue, ValueFactory valueFactory) {
        Sensor sensor = sensorCatalogue.getSensor(strModel, SENSOR_PATH, name, valueFactory);
        if (sensor == null)
            return null;

        if (!sensorExists(name)) {
            this._sensors.add(sensor);
            return sensor;
        }
        return null;
    }

    /**
     * Adds an actuator to the device with the specified name and model, if it does not already exist.
     *
     * @param actModel The model identifier of the actuator to add.
     * @param name The name of the actuator to add.
     * @param actuatorCatalogue The catalogue of actuators to retrieve the actuator from.
     * @param valueFactory The factory for creating actuator values.
     * @return The actuator that was added, or {@code null} if an actuator with the specified name already exists.
     */
    public Actuator addActuator(String actModel, String name, ActuatorCatalogue actuatorCatalogue, ValueFactory valueFactory) {
        Actuator actuator = actuatorCatalogue.getActuator(actModel, ACTUATOR_PATH, name, valueFactory);
        if (actuator == null)
            return null;

        if (!actuatorExists(name)) {
            this._actuators.add(actuator);
            return actuator;
        }
        return null;
    }

    /**
     * Provides a list of all sensors currently associated with this device. This includes both
     * active and inactive sensors.
     *
     * @return A new list containing all sensors of the device. Modifications to this list will not
     *         affect the device's internal sensors collection.
     */
    public List<Sensor> getSensors() {
        return new ArrayList<>(_sensors);
    }

    /**
     * Provides a list of all actuators currently associated with this device. Similar to sensors,
     * this includes actuators in any state.
     *
     * @return A new list containing all actuators of the device. Modifications to this list will not
     *         affect the device's internal actuators collection.
     */
    public List<Actuator> getActuators() {
        return new ArrayList<>(_actuators);
    }

    /**
     * Checks if a sensor with the specified name exists within the device's collection of sensors.
     * This method is case-insensitive.
     *
     * @param name The name of the sensor to search for.
     * @return {@code true} if a sensor with the specified name exists; {@code false} otherwise.
     */
    protected boolean sensorExists(String name) {
        for (Sensor sensor : _sensors)
            if ((sensor.getName().equalsIgnoreCase(name)))
                return true;
        return false;
    }

    /**
     * Checks if an actuator with the specified name exists within the device's collection of actuators.
     * This method is case-insensitive.
     *
     * @param name The name of the actuator to search for.
     * @return {@code true} if an actuator with the specified name exists; {@code false} otherwise.
     */
    protected boolean actuatorExists(String name) {
        for (Actuator actuator : _actuators)
            if ((actuator.getName().equalsIgnoreCase(name)))
                return true;
        return false;
    }

    /**
     * Retrieves a list of functionalities supported by the sensors associated with this device. This
     * leverages the {@link SensorCatalogue} to enumerate possible sensor functionalities based on the
     * device's configuration and capabilities.
     *
     * @return A list of {@link SensorFunctionality} instances representing the supported sensor functionalities.
     */
    public List<SensorFunctionality> getSensorFunctionalities() {
        return _sensorCatalogue.getSensorFunctionalities();
    }

    /**
     * Retrieves a list of functionalities supported by the actuators associated with this device. Utilizes
     * the {@link ActuatorCatalogue} to list potential actuator functionalities, reflecting the device's
     * configuration and actuator capabilities.
     *
     * @return A list of {@link ActuatorFunctionality} instances representing the supported actuator functionalities.
     */
    public List<ActuatorFunctionality> getActuatorFunctionalities() {
        return _actuatorCatalogue.getActuatorModels();
    }
}
