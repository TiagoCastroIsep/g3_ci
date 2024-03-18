package SmartHome.domain.values.implementation;

import SmartHome.domain.values.Value;

/**
 * This class implements the Value interface and represents a energy consumption value in Watt-hours (Wh).
 */
public class WhValue implements Value {

    double _currentValue = 0;
    String _measurementUnit = "Wh";
    /**
     * Constructs a WhValue with the measurement unit set to "Wh".
     */
    public WhValue() {

    }
    /**
     * Checks if the provided value is a valid energy consumption (greater than or equal to 0).
     *
     * @param value the value to be checked
     * @return true if the value is a valid energy consumption, false otherwise
     */
    private boolean isValidEnergyConsumption(double value) {
        return value >= 0;
    }
    /**
     * Returns a string representation of the current value and its measurement unit.
     *
     * @return a string in the format of "{value} Wh"
     */
    @Override
    public String toString() {
        return this._currentValue + " " + this._measurementUnit;
    }
    /**
     * Sets the current value to the parsed double value of the provided string if it is a valid energy consumption.
     *
     * @param measured the string to be parsed as a double
     * @return true if the string was successfully parsed and the value was set, false otherwise
     */
    @Override
    public boolean setValue(String measured) {
        try {
            double value = Double.parseDouble(measured);
            if (isValidEnergyConsumption(value)) {
                this._currentValue = value;
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
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
