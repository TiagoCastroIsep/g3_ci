package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;

/**
 * Represents a scale sensor.
 */
public class ScaleSensor implements Sensor {
    private String _name;
    private final SensorFunctionality _sensorFunctionality = SensorFunctionality.Scale;
    private Value _currentValue;
    private final ValueFactory _valueFactory;

    /**
     * Constructs a ScaleSensor with the provided parameters.
     *
     * @param catalogue    The sensor catalogue to which this sensor belongs.
     * @param name         The name of the sensor.
     * @param valueFactory The factory for creating sensor values.
     * @throws IllegalArgumentException if the catalogue, name, or valueFactory is null or empty.
     */
    public ScaleSensor(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null) throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this._name = name;
        this._valueFactory = valueFactory;
    }

    /**
     * Gets the current reading of the scale sensor.
     *
     * @return The current reading of the scale sensor as a string.
     */
    public String getReading() {
        if (_currentValue == null) _currentValue = _valueFactory.createPercentage();
        return _currentValue.toString();
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
            this._currentValue = valueFactory.createPercentage();
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
