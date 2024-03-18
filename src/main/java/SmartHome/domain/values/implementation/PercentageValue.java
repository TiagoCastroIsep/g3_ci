package SmartHome.domain.values.implementation;

import SmartHome.domain.values.Value;

/**
 * This class implements the Value interface and represents a percentage value.
 */
public class PercentageValue implements Value {
    private int _currentValue = 0;
    private final String _measurementUnit = "%";
    /**
     * Checks if the provided value is a valid percentage (between 0 and 100 inclusive).
     *
     * @param value the value to be checked
     * @return true if the value is a valid percentage, false otherwise
     */
    private boolean isValidPercentage(int value) {
        return value >= 0 && value <= 100;
    }
    /**
     * Sets the current value to the parsed integer value of the provided string if it is a valid percentage.
     *
     * @param measured the string to be parsed as an integer
     * @return true if the string was successfully parsed and the value was set, false otherwise
     */
    @Override
    public boolean setValue(String measured) {
        try {
            int value = Integer.parseInt(measured);
            if (isValidPercentage(value)) {
                this._currentValue = value;
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
    /**
     * Sets the current value to the parsed integer value of the provided string if it is a valid percentage.
     *
     * @param measured the string to be parsed as an integer
     * @return true if the string was successfully parsed and the value was set, false otherwise
     */
    @Override
    public String getMeasurementUnit() {
        return this._measurementUnit;
    }

    /**
     * Returns a string representation of the current value and its measurement unit.
     *
     * @return a string in the format of "{value} %"
     */
    public String toString() {
        return this._currentValue + " " + this._measurementUnit;
    }

}
