package SmartHome.domain.values.implementation;

import SmartHome.domain.values.Value;

/**
 * This class implements the Value interface and represents a solar irradiance value in Watts per square meter (W/m2).
 */
public class Wm2Value implements Value {
    private double _currentValue = 0;
    private final String _measurementUnit;
    /**
     * Constructs a Wm2Value with the measurement unit set to "W/m2".
     */
    public Wm2Value() {
        _measurementUnit = "W/m2";
    }
    /**
     * Returns a string representation of the current value and its measurement unit.
     *
     * @return a string in the format of "{value} W/m2"
     */
    @Override
    public String toString() {
        return _currentValue + " " + _measurementUnit;
    }
    /**
     * Sets the current value to the parsed double value of the provided string.
     *
     * @param measured the string to be parsed as a double
     * @return true if the string was successfully parsed and the value was set, false otherwise
     */
    @Override
    public boolean setValue(String measured) {
        try {
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