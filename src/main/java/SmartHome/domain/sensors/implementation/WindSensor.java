package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;

/**
 * Represents a wind sensor that captures wind speed and direction.
 * This sensor is part of a larger sensor catalogue and utilizes a value factory for creating its measurement values.
 */
public class WindSensor implements Sensor {
    private String _name;
    private final SensorFunctionality _sensorFunctionality = SensorFunctionality.Wind;
    private Value _currentValue;
    private final ValueFactory _valueFactory;


    /**
     * Constructs a new WindSensor with the specified name and value factory.
     * @param catalogue    The sensor catalogue this sensor is associated with.
     * @param name         The unique name of the sensor.
     * @param valueFactory Factory for creating sensor-specific value objects.
     * @throws IllegalArgumentException if any argument is null or if the name is empty.
     */
    public WindSensor(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null) throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this._name = name;
        this._valueFactory = valueFactory;
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
            this._currentValue = valueFactory.createKmhCardinalValue();
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

    /**
     * Gets the current reading of the wind sensor.
     *
     * @return The current reading of the wind sensor as a string.
     */
    public String getReading () {
        if (_currentValue == null) _currentValue = _valueFactory.createKmhCardinalValue();
        return _currentValue.toString();
    }
}
