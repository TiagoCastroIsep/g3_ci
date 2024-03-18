package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;

/**
 * Represents an instant power consumption sensor within a smart home system.
 * This sensor is capable of measuring the instantaneous power consumption and provides
 * functionality to retrieve current readings in watts.
 */
public class InstantPowerConsumptionSensor implements Sensor {
    private String _name;
    private Value _currentValue;
    private final SensorFunctionality _sensorFunctionality = SensorFunctionality.Power_Consumption;

    private final ValueFactory _valueFactory;

    /**
     * Constructs an InstantPowerConsumptionSensor with specified name and a value factory
     * for creating power measurement values.
     *
     * @param catalogue    The sensor catalogue to which this sensor is registered. Must not be null.
     * @param name         The unique name of the sensor. Must not be null or empty.
     * @param valueFactory A factory instance for creating sensor value objects. Must not be null.
     * @throws IllegalArgumentException if any of the parameters are null or, in the case of name, empty.
     */
    public InstantPowerConsumptionSensor(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null) throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this._name = name;
        this._valueFactory = valueFactory;
    }

    /**
     * Retrieves the current instant power consumption reading of the sensor.
     *
     * @return A string representing the current instant power consumption reading, including the measurement unit.
     */
    public String getReading() {
        if (this._currentValue == null) _currentValue = _valueFactory.createWValue();
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
     * Retrieves the measurement unit used by the sensor. The unit is determined by the type of Value object created
     * by the associated ValueFactory.
     *
     * @param valueFactory The factory for creating sensor values, not used in this implementation as the factory is provided
     *                     during construction.
     * @return The measurement unit of the sensor's current value.
     */
    public String getMeasurementUnit(ValueFactory valueFactory) {
        if (this._currentValue == null)
            this._currentValue = valueFactory.createWValue();
        return _currentValue.getMeasurementUnit();
    }

    /**
     * Retrieves the designated functionality of this sensor.
     *
     * @return The sensor's functionality, specifically Power Consumption in this instance.
     */
    @Override
    public SensorFunctionality getSensorFunctionality() {
        return this._sensorFunctionality;
    }
}
