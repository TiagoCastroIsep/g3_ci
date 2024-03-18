package SmartHome.domain.actuators.implementation;

import SmartHome.domain.actuators.Actuator;
import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.values.ValueFactory;

/**
 * Represents a switch actuator for turning on and off in a smart home system.
 * This actuator allows controlling the state of the switch (on or off).
 */
public class SwitchOnOffActuator implements Actuator {

    /**
     * The current state of the switch actuator.
     */
    private boolean _isOn;

    /**
     * The name of the switch actuator.
     */
    private String _name;

    /**
     * The functionality of the switch actuator for turning on and off.
     */
    private final ActuatorFunctionality _actuatorFunctionality = ActuatorFunctionality.On_Off;

    /**
     * Constructs a SwitchOnOffActuator with a given actuator catalogue, name, and value factory.
     *
     * @param actuatorCatalogue The catalogue of actuators.
     * @param name              The name of the switch actuator.
     * @param valueFactory      The factory for creating values associated with the actuator.
     * @throws IllegalArgumentException if the actuator catalogue is null or if the name is null or empty.
     */
    public SwitchOnOffActuator(ActuatorCatalogue actuatorCatalogue, String name, ValueFactory valueFactory) {
        if (actuatorCatalogue == null)
            throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this._name = name;
        this._isOn = false;
    }

    /**
     * Switches the state of the switch actuator (on to off or off to on).
     *
     * @return The updated state of the switch actuator (true if on, false if off).
     */
    public boolean switchActuator() {
        if (!this._isOn) {
            this._isOn = true;
        } else {
            this._isOn = false;
        }
        return this._isOn;
    }

    /**
     * Checks if the switch actuator is currently in the on state.
     *
     * @return true if the switch actuator is on, false if off.
     */
    public boolean isOn() {
        return this._isOn;
    }

    /**
     * Gets the name of the switch actuator.
     *
     * @return The name of the switch actuator.
     */
    @Override
    public String getName() {
        return this._name;
    }

    /**
     * Gets the functionality of the switch actuator.
     *
     * @return The functionality of the switch actuator.
     */
    @Override
    public ActuatorFunctionality getActuatorFunctionality() {
        return this._actuatorFunctionality;
    }

    /**
     * Gets the reading or value represented by the switch actuator.
     *
     * @return The reading or value represented by the switch actuator (true if on, false if off).
     */
    public String getReading() {
        return String.valueOf(_isOn);
    }
}
