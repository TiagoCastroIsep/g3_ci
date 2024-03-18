package SmartHome.domain.actuators.implementation;

import SmartHome.domain.actuators.Actuator;
import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;

/**
 * Represents a range actuator for integer values in a smart home system.
 * This actuator allows configuration of lower and upper limits and provides an integer measurement.
 */

public class RangeActuatorInt implements Actuator {

    /**
     * The functionality of the range actuator for integer values.
     */
    private final ActuatorFunctionality _actuatorFunctionality = ActuatorFunctionality.Range;

    /**
     * The name of the range actuator.
     */
    private String _name;


    /**
     * The measurement value associated with the range actuator.
     */
    private Value _measurement;

    /**
     * The lower limit of the range for the integer value.
     */
    private int _lowerLimit = -1;

    /**
     * The upper limit of the range for the integer value.
     */
    private int _upperLimit = 1;

    /**
     * Constructs a RangeActuatorInt with a given actuator catalogue, name, and value factory.
     *
     * @param actuatorCatalogue The catalogue of actuators.
     * @param name              The name of the range actuator.
     * @param valueFactory      The factory for creating values associated with the actuator.
     * @throws IllegalArgumentException if the actuator catalogue is null.
     */

    public RangeActuatorInt(ActuatorCatalogue actuatorCatalogue, String name, ValueFactory valueFactory) {
        if (actuatorCatalogue == null)
            throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (valueFactory == null)
            throw new IllegalArgumentException("ValueFactory can't be null");
        this._name = name;
        _measurement = valueFactory.createRangeActuatorInt(_lowerLimit, _upperLimit);
    }

    /**
     * Configures the range actuator with the provided parameters.
     *
     * @param name          The name of the range actuator.
     * @param lowerLimit    The lower limit of the range for the integer value.
     * @param upperLimit    The upper limit of the range for the integer value.
     * @param valueFactory  The factory for creating values associated with the actuator.
     * @return true if the configuration is successful, false otherwise.
     */

    public boolean configureActuator(String name, int lowerLimit, int upperLimit, ValueFactory valueFactory) {
        if (name == null || name.trim().isEmpty()) return false;
        this._name = name;
        this._lowerLimit = lowerLimit;
        this._upperLimit = upperLimit;
        _measurement = valueFactory.createRangeActuatorInt(lowerLimit, upperLimit);
        return true;
    }

    /**
     * Gets the name of the range actuator.
     *
     * @return The name of the range actuator.
     */
    @Override
    public String getName() {
        return _name;
    }


    /**
     * Gets the reading or value represented by the range actuator.
     *
     * @return The reading or value represented by the range actuator.
     * @throws IllegalArgumentException if the actuator is not configured yet.
     */
    public String getReading() {
        if (_measurement == null) throw new IllegalArgumentException("Actuator not configured yet.");
        return _measurement.toString();
    }

    /**
     * Gets the functionality of the range actuator.
     *
     * @return The functionality of the range actuator.
     */
    @Override
    public ActuatorFunctionality getActuatorFunctionality() {
        return _actuatorFunctionality;
    }

    /**
     * Gets the lower limit of the range for the integer value.
     *
     * @return The lower limit of the range for the integer value.
     */
    public int getLowerLimit() {
        return _lowerLimit;
    }

    /**
     * Gets the upper limit of the range for the integer value.
     *
     * @return The upper limit of the range for the integer value.
     */
    public int getUpperLimit() {
        return _upperLimit;
    }

    /**
     * Sets the measurement value of the range actuator.
     *
     * @param measured The new measurement value as a string.
     * @return true if the measurement is successfully set, false otherwise.
     * @throws IllegalArgumentException if the actuator is not configured yet.
     */

    public boolean setMeasurement(String measured) {
        if (_measurement == null) throw new IllegalArgumentException("Actuator not configured yet.");
        try {
            return _measurement.setValue(measured);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gets the measurement units associated with the range actuator.
     *
     * @return The measurement units associated with the range actuator.
     */
    public String getMeasurementUnits() {
        return _measurement.getMeasurementUnit();
    }
}
