package SmartHome.domain.values.implementation;

import SmartHome.domain.values.Value;

/**
 * This class implements the Value interface and represents a range actuator value with integer precision.
 */
public class RangeActuatorIntValue implements Value {
    private final int _lowerLimit;
    private final int _upperLimit;
    private int _measurement = 0;
    private final String _measurementUnits;
    /**
     * Constructs a RangeActuatorIntValue with the specified lower and upper limits.
     *
     * @param lowerLimit the lower limit of the range
     * @param upperLimit the upper limit of the range
     */
    public RangeActuatorIntValue(int lowerLimit, int upperLimit) {
        this._lowerLimit = lowerLimit;
        this._upperLimit = upperLimit;
        this._measurementUnits = "Integer";
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
     * Sets the current value to the parsed integer value of the provided string if it is within the specified range.
     *
     * @param measured the string to be parsed as an integer
     * @return true if the string was successfully parsed and the value was set, false otherwise
     */
    @Override
    public boolean setValue(String measured) {
        int measurementValue = Integer.parseInt(measured);
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
        return _measurementUnits;
    }
}
