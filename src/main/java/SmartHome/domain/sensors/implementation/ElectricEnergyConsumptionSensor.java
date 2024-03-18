package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.WhValue;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a sensor that measures electric energy consumption.
 */
public class ElectricEnergyConsumptionSensor implements Sensor {
    private final String _name;
    private final SensorFunctionality _sensorFunctionality = SensorFunctionality.Energy_Consumption;
    private Value _currentValue;
    private Map<LocalTime, WhValue> _data = new LinkedHashMap<>();

    /**
     * Constructs an ElectricEnergyConsumptionSensor with the provided parameters.
     *
     * @param catalogue    The sensor catalogue to which this sensor belongs.
     * @param name         The name of the sensor.
     * @param valueFactory The factory for creating sensor values.
     * @throws IllegalArgumentException if the catalogue, name, or valueFactory is null or empty.
     */
    public ElectricEnergyConsumptionSensor(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null) throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (valueFactory == null) throw new IllegalArgumentException("ValueFactory cannot be null");
        this._name = name;
        this._currentValue = valueFactory.createWhValue();
    }

    /**
     * Gets the reading at a given time.
     *
     * @param time The time at which the reading is requested.
     * @return The reading value at the specified time.
     */
    private double getReadingInAGivenTime(LocalTime time) {
        double readingValue = 0;
        for (Map.Entry<LocalTime, WhValue> entry : _data.entrySet()) {
            if (entry.getKey().equals(time)) {
                WhValue value = entry.getValue();
                String startnumericvalue = removeMeasurementUnit(value);
                readingValue = Double.parseDouble(startnumericvalue);
            }
        }
        return readingValue;
    }

    /**
     * Gets the reading of the sensor over a specified time period.
     *
     * @param start The start time of the period.
     * @param end   The end time of the period.
     * @return The energy consumption reading over the specified time period.
     */
    public String getReading(LocalTime start, LocalTime end) {
        if (_data.size() != 2) {
            return "There should be exactly two readings";
        }
        if (start == null || end == null || start.isAfter(end)) {
            return "Invalid time period";
        }
        return getEnergyConsumptionOverAPeriod(start, end);
    }

    /**
     * Gets the total energy consumption over a period of time.
     *
     * @param startTime The start time of the period.
     * @param endTime   The end time of the period.
     * @return The total energy consumption over the specified period.
     */
    private String getEnergyConsumptionOverAPeriod(LocalTime startTime, LocalTime endTime) {
        String unit = " Wh";

        double startReadingValue = getReadingInAGivenTime(startTime);
        double endReadingValue = getReadingInAGivenTime(endTime);

        double totalConsumption = endReadingValue - startReadingValue;
        return totalConsumption + unit;
    }

    /**
     * Adds a reading to the sensor's data.
     *
     * @param reading The energy consumption reading to be added.
     * @param time    The time at which the reading was recorded.
     * @return True if the reading is successfully added, false otherwise.
     */
    public boolean addReading(WhValue reading, LocalTime time) {
        try {
            if (Double.parseDouble(removeMeasurementUnit(reading)) == 0.0) return false;
            _data.put(time, reading);

        } catch (NullPointerException e) {
            return false;
        }
        return _data.containsValue(reading);
    }

    /**
     * Removes the measurement unit from the reading.
     *
     * @param reading The reading with the measurement unit.
     * @return The reading without the measurement unit.
     */
    private String removeMeasurementUnit(WhValue reading) {
        String value = reading.toString();
        String unit = reading.getMeasurementUnit();
        return value.trim().replace(unit, "");
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
     * @return The measurement unit of the sensor.
     */
    public String getMeasurementUnit() {
        return this._currentValue.getMeasurementUnit();
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
