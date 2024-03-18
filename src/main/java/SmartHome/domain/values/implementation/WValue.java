package SmartHome.domain.values.implementation;

import SmartHome.domain.values.Value;

/**
 * This class implements the Value interface and represents a power value in Watts (W).
 */
public class WValue implements Value {
    private double _currentValue = 0;
    private final String _measurementUnit;
    /**
     * Constructs a WValue with the measurement unit set to "W".
     */
    public WValue() {
        _measurementUnit = "W";
    }
    /**
     * Returns a string representation of the current value and its measurement unit.
     *
     * @return a string in the format of "{value} W"
     */
    @Override
    public String toString() {
        return this._currentValue + " " + this._measurementUnit;
    }
    /**
     * Sets the current value to the parsed double value of the provided string if it is a valid power value (greater than or equal to 0).
     *
     * @param measured the string to be parsed as a double
     * @return true if the string was successfully parsed and the value was set, false otherwise
     */
    @Override
    public boolean setValue(String measured) {
        try {
            if (Double.parseDouble(measured) < 0) {
                return false;
            }
            this._currentValue = Double.parseDouble(measured);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
    /**
     * Returns the measurement unit of the value.
     *
     * @return the measurement unit of the value
     */
    @Override
    public String getMeasurementUnit() {
        return this._measurementUnit;
    }
}
