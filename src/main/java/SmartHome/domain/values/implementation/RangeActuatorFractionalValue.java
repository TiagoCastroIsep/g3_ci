package SmartHome.domain.values.implementation;

import SmartHome.domain.values.Value;

/**
 * This class implements the Value interface and represents a range actuator value with fractional precision.
 */
public class RangeActuatorFractionalValue implements Value {
    private final double _lowerLimit;
    private final double _upperLimit;
    private double _measurement = 0;
    private final String _measurementUnit;
    /**
     * Constructs a RangeActuatorFractionalValue with the specified lower and upper limits.
     *
     * @param lowerLimit the lower limit of the range
     * @param upperLimit the upper limit of the range
     */
    public RangeActuatorFractionalValue(double lowerLimit, double upperLimit) {
        this._lowerLimit = lowerLimit;
        this._upperLimit = upperLimit;
        this._measurementUnit = "Double precision";
    }
    /**
     * Returns a string representation of the current value.
     *
     * @return a string representation of the current value
     */
    @Override
    public String toString() {
        return _measurement + "";
    }
    /**
     * Sets the current value to the parsed double value of the provided string if it is within the specified range.
     *
     * @param measured the string to be parsed as a double
     * @return true if the string was successfully parsed and the value was set, false otherwise
     */
    @Override
    public boolean setValue(String measured) {
        double measurementValue = Double.parseDouble(measured);
        if (measurementValue < _lowerLimit) return false;
        if (measurementValue > _upperLimit) return false;
        _measurement = measurementValue;
        return true;
    }
    /**
     * Returns the measurement unit of the value.
     *
     * @return the measurement unit of the value
     */
    @Override
    public String getMeasurementUnit() {
        return _measurementUnit;
    }
}
