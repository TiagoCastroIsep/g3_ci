package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;

/**
 * Represents a temperature sensor.
 */
public class TemperatureSensor implements Sensor {
    private String _name;
    private final SensorFunctionality _sensorFunctionality = SensorFunctionality.Temperature;
    private Value _currentValue;
    private ValueFactory _valueFactory;

    /**
     * Constructs a TemperatureSensor with the provided parameters.
     *
     * @param catalogue    The sensor catalogue to which this sensor belongs.
     * @param name         The name of the sensor.
     * @param valueFactory The factory for creating sensor values.
     * @throws IllegalArgumentException if the catalogue, name, or valueFactory is null or empty.
     */
    public TemperatureSensor(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null)
            throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this._name = name;
        this._valueFactory = valueFactory;
    }

    /**
     * Gets the current reading of the temperature sensor.
     *
     * @return The current reading of the temperature sensor as a string.
     */
    public String getReading() {
        if (_currentValue == null) _currentValue = _valueFactory.createCelsiusTemperature();
        return _currentValue.toString();
    }

    /**
     * Sets the name of the sensor.
     *
     * @param name The new name for the sensor.
     * @return true if the name is successfully set, false otherwise.
     */
    public boolean setName(String name) {
        if (name == null || name.isEmpty()) return false;
        this._name = name;
        return true;
    }

    /**
     * Gets the name of the sensor.
     *
     * @return The name of the sensor.
     */
    @Override
    public String getName() {
        return this._name;
    }

    /**
     * Gets the measurement unit of the sensor.
     *
     * @param valueFactory The factory for creating sensor values.
     * @return The measurement unit of the sensor.
     */
    public String getMeasurementUnit(ValueFactory valueFactory) {
        if (this._currentValue == null)
            this._currentValue = valueFactory.createCelsiusTemperature();
        return _currentValue.getMeasurementUnit();
    }

    /**
     * Gets the functionality of the sensor.
     *
     * @return The functionality of the sensor.
     */
    @Override
    public SensorFunctionality getSensorFunctionality() {
        return this._sensorFunctionality;
    }
}
