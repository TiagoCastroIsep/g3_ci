package SmartHome.domain.values.implementation;


import SmartHome.domain.sensors.implementation.WindDirection;
import SmartHome.domain.values.Value;

/**
 * Class that represents the value of a Wind Sensor.
 */
public class KmhCardinalValue implements Value {
    private String _speed = "";
    private WindDirection _direction;
    private final String _measurementUnit;

    /**
     * Constructs a KmhCardinalValue with the measurement unit set to "km/h".
     */

    public KmhCardinalValue() {
        _measurementUnit = "km/h";
    }
    /**
     * Returns the wind speed with its measurement unit.
     *
     * @return a string in the format of "{speed} km/h".
     */
    public String getWindSpeed() {
        return _speed + " " + _measurementUnit;
    }
    /**
     * Returns the wind direction.
     *
     * @return the wind direction as a string.
     */

    public String getWindDirection() {
        return String.valueOf(_direction);
    }
    /**
     * Sets the wind speed to the parsed double value of the provided string if it is greater than or equal to 0.
     *
     * @param measured the string to be parsed as a double.
     * @return true if the string was successfully parsed and the value was set, false otherwise.
     */
    public boolean setValue(String measured) {
        try {
            if (Double.parseDouble(measured) >= 0.0){
            this._speed = measured;
            } else {
                return false;
            }
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
    /**
     * Sets the wind direction.
     *
     * @param direction the wind direction to be set.
     * @return true if the direction is successfully set, false otherwise.
     */
    public boolean setDirection(WindDirection direction) {
        if (direction == null) return false;
        this._direction = direction;
        return true;
    }
    /**
     * Returns the measurement unit of the value.
     *
     * @return the measurement unit of the value.
     */

    @Override
    public String getMeasurementUnit() {
        return _measurementUnit;
    }
    /**
     * Returns a string representation of the wind speed, its measurement unit, and the wind direction.
     *
     * @return a string in the format of "{speed} km/h pointing to: {direction}" if the speed and direction are not null, null otherwise.
     */
    @Override
    public String toString() {
        if (_speed == null || _direction == null) return null;
        return _speed + " " + _measurementUnit + " pointing to: " + _direction;
    }

}