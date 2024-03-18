package SmartHome.domain.actuators.implementation;

import SmartHome.domain.actuators.Actuator;
import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;

/**
 * Represents a blind roller actuator in a smart home system.
 * This actuator is designed to control blind rollers or similar devices.
 */
public class BlindRollerActuator implements Actuator {
    /**
     * The name of the blind roller actuator.
     */
    private String _name;
    /**
     * The functionality of the blind roller actuator.
     */
    private final ActuatorFunctionality _actuatorFunctionality = ActuatorFunctionality.BlindRoller;
    /**
     * The current value associated with the blind roller actuator.
     */
    private Value _currentValue;
    /**
     * The factory used for creating values associated with the blind roller actuator.
     */
    private final ValueFactory _valueFactory;

    /**
     * Constructs a BlindRollerActuator with a given actuator catalogue, name, and value factory.
     *
     * @param actuatorCatalogue The catalogue of actuators.
     * @param name              The name of the blind roller actuator.
     * @param valueFactory      The factory for creating values associated with the actuator.
     * @throws IllegalArgumentException if the actuator catalogue is null, or the name is null or empty.
     */

    public BlindRollerActuator(ActuatorCatalogue actuatorCatalogue, String name, ValueFactory valueFactory) {
        if (actuatorCatalogue == null)
            throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        this._name = name;
        this._valueFactory = valueFactory;
    }

    /**
     * Assigns the current value to the blind roller actuator using the provided value factory.
     *
     * @param valueFactory The factory for creating values associated with the actuator.
     * @return The current value associated with the blind roller actuator.
     */

    public Value assignCurrValue(ValueFactory valueFactory) {
        if (_currentValue == null) _currentValue = valueFactory.createPercentage();
        return _currentValue;
    }

    /**
     * Gets the name of the blind roller actuator.
     *
     * @return The name of the blind roller actuator.
     */

    @Override
    public String getName() {
        return this._name;
    }

    /**
     * Gets the functionality of the blind roller actuator.
     *
     * @return The functionality of the blind roller actuator.
     */

    @Override
    public ActuatorFunctionality getActuatorFunctionality() {
        return this._actuatorFunctionality;
    }

    /**
     * Gets the measurement unit associated with the blind roller actuator.
     *
     * @param valueFactory The factory for creating values associated with the actuator.
     * @return The measurement unit associated with the blind roller actuator.
     */

    public String getMeasurementUnit(ValueFactory valueFactory) {
        if (_currentValue == null) _currentValue = valueFactory.createPercentage();
        return this._currentValue.getMeasurementUnit();
    }

    /**
     * Gets the reading or value represented by the blind roller actuator.
     *
     * @return The reading or value represented by the blind roller actuator.
     */

    public String getReading() {
        if (_currentValue == null) _currentValue = _valueFactory.createPercentage();
        return _currentValue.toString();
    }
}

