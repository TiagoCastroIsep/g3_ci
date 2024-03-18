package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;

/**
 * Represents a solar irradiance sensor.
 */
public class SolarIrradianceSensor implements Sensor {
    private String _name;
    private final SensorFunctionality _sensorFunctionality = SensorFunctionality.SolarIrradiance;
    private Value _currentValue;
    private final ValueFactory _valueFactory;

    /**
     * Constructs a SolarIrradianceSensor with the provided parameters.
     *
     * @param catalogue    The sensor catalogue to which this sensor belongs.
     * @param name         The name of the sensor.
     * @param valueFactory The factory for creating sensor values.
     * @throws IllegalArgumentException if the catalogue, name, or valueFactory is null or empty.
     */
    public SolarIrradianceSensor(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null)
            throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this._name = name;
        this._valueFactory = valueFactory;
    }

    /**
     * Gets the current reading of the solar irradiance sensor.
     *
     * @return The current reading of the solar irradiance sensor as a string, or null if the reading is not available.
     */
    public String getReading() {
        if (_currentValue == null) _currentValue = _valueFactory.createWm2Value();
        if (this._currentValue != null) return _currentValue.toString();
        return null;
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
            this._currentValue = valueFactory.createWm2Value();
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
