package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.ValueFactory;
import SmartHome.domain.values.implementation.WValue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents a sensor for measuring average power consumption over a period of time.
 */
public class AveragePowerConsumptionSensor implements Sensor {
    private String _name;
    private SensorFunctionality _sensorFunctionality = SensorFunctionality.Power_Consumption;
    private Map<LocalTime, WValue> _data = new LinkedHashMap<>();

    /**
     * Constructs an AveragePowerConsumptionSensor with the provided parameters.
     *
     * @param catalogue    The sensor catalogue to which this sensor belongs.
     * @param name         The name of the sensor.
     * @param valueFactory The factory for creating sensor values.
     * @throws IllegalArgumentException if the catalogue, name, or valueFactory is null or empty.
     */
    public AveragePowerConsumptionSensor(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null) throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (valueFactory == null) throw new IllegalArgumentException("ValueFactory cannot be null");
        this._name = name;
    }

    /**
     * Gets the average power consumption reading over a specified period of time.
     *
     * @param start The start time of the period.
     * @param end   The end time of the period.
     * @return The average power consumption reading, or "No readings to show" if no data is available.
     */
    public String getReading(LocalTime start, LocalTime end) {
        List<WValue> readingsInAGivenPeriod = getReadingsInAPeriodOfTime(start, end);
        if (readingsInAGivenPeriod.isEmpty()) return "No readings to show";
        return getAveragePowerConsumption(readingsInAGivenPeriod);
    }


    /**
     * Calculates the average power consumption from a list of readings.
     *
     * @param readings The list of power consumption readings.
     * @return The average power consumption value.
     */
    private String getAveragePowerConsumption(List<WValue> readings) {
        double totalPower = 0;
        String unit = "";
        for (WValue reading : readings) {
            String value = removeMeasurementUnit(reading);
            double numericValue = Double.parseDouble(value);
            totalPower += numericValue;
            unit = reading.getMeasurementUnit();
        }

        double averageReading = totalPower / readings.size();
        return averageReading + unit;
    }

    /**
     * Retrieves the readings recorded during a specified period of time.
     *
     * @param start The start time of the period.
     * @param end   The end time of the period.
     * @return A list of power consumption readings within the specified time period.
     */
    private List<WValue> getReadingsInAPeriodOfTime(LocalTime start, LocalTime end) {
        List<WValue> readingsInAGivenPeriod = new ArrayList<>();
        for (Map.Entry<LocalTime, WValue> set : _data.entrySet()) {
            if (set.getKey().isAfter(start) && set.getKey().isBefore(end)) {
                addReadingToList(set.getValue(), readingsInAGivenPeriod);
            }
        }
        return readingsInAGivenPeriod;
    }

    /**
     * Adds a reading to the list if it meets certain conditions.
     *
     * @param instantValue The reading value to add.
     * @param readings     The list of readings.
     */
    private void addReadingToList(WValue instantValue, List<WValue> readings) {
        if(Double.parseDouble(removeMeasurementUnit(instantValue)) != 0.0
                && instantValue.toString() != null) {
            readings.add(instantValue);
        }
    }

    /**
     * Removes the measurement unit from a reading.
     *
     * @param reading The reading value.
     * @return The reading value without the measurement unit.
     */
    private String removeMeasurementUnit(WValue reading) {
        String value = reading.toString();
        String unit = reading.getMeasurementUnit();
        return value.trim().replace(unit, "");
    }

    /**
     * Adds a power consumption reading to the sensor's data.
     *
     * @param reading The power consumption reading.
     * @param time    The time at which the reading was recorded.
     * @return true if the reading was added successfully, false otherwise.
     */
    public boolean addReading(WValue reading, LocalTime time) {
        if(Double.parseDouble(removeMeasurementUnit(reading)) == 0.0
                && reading.toString() == null) return false;
        _data.put(time, reading);
        return  true;
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
     * Gets the functionality of the sensor.
     *
     * @return The functionality of the sensor.
     */
    @Override
    public SensorFunctionality getSensorFunctionality() {
        return this._sensorFunctionality;
    }
}
