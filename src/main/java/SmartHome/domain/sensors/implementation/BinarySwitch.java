package SmartHome.domain.sensors.implementation;

import SmartHome.domain.actuators.implementation.SwitchOnOffActuator;
import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.ValueFactory;

/**
 * Represents a binary switch sensor that reads the status of a SwitchOnOffActuator.
 */

public class BinarySwitch implements Sensor {
    private final SensorFunctionality _sensorFunctionality = SensorFunctionality.Binary_Switch;
    private String _name;
    private SwitchOnOffActuator _binarySwitch;


    /**
     * Constructs a BinarySwitch sensor with the provided parameters.
     *
     * @param catalogue    The sensor catalogue to which this sensor belongs.
     * @param name         The name of the sensor.
     * @param valueFactory The factory for creating sensor values.
     * @throws IllegalArgumentException if the catalogue, name, or valueFactory is null or empty.
     */
    public BinarySwitch(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null)
            throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this._name = name;
    }

    /**
     * Configures the sensor with a SwitchOnOffActuator.
     *
     * @param actuator The SwitchOnOffActuator to be associated with the sensor.
     * @return true if the sensor is successfully configured, false otherwise.
     */
    public boolean configureSensor(SwitchOnOffActuator actuator) {
        if (actuator == null) return false;
        this._binarySwitch = actuator;
        return true;
    }

    /**
     * Reads the status of the associated SwitchOnOffActuator.
     *
     * @return true if the associated actuator is ON, false if OFF.
     */
    public boolean readStatus() {
        return this._binarySwitch.isOn();
    }

    /**
     * Gets the name of the sensor.
     *
     * @return The name of the sensor.
     */
    @Override
    public String getName() {
        return this._name;
    }

    /**
     * Gets the functionality of the sensor.
     *
     * @return The functionality of the sensor.
     */
    @Override
    public SensorFunctionality getSensorFunctionality() {
        return this._sensorFunctionality;
    }
}
