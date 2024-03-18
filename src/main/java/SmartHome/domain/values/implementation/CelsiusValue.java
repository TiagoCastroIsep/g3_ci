package SmartHome.domain.values.implementation;

import SmartHome.domain.values.Value;

/**
 * This class implements the Value interface and represents a temperature value in Celsius.
 */
public class CelsiusValue implements Value {
    private final String _measurementUnit;
    private double _currentValue = 0;
    /**
     * Constructs a CelsiusValue with the measurement unit set to "ºC".
     */
    public CelsiusValue() {
        _measurementUnit = "ºC";
    }
    /**
     * Returns a string representation of the current value and its measurement unit.
     *
     * @return a string in the format of "{value} ºC".
     */
    @Override
    public String toString() {
        return this._currentValue + " " + this._measurementUnit;
    }
    /**
     * Sets the current value to the parsed double value of the provided string.
     *
     * @param measured the string to be parsed as a double.
     * @return true if the string was successfully parsed and the value was set, false otherwise.
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
     * @return the measurement unit of the value.
     */
    @Override
    public String getMeasurementUnit() {
        return this._measurementUnit;
    }
}
