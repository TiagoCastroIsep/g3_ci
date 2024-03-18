package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.Sensor;
import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.values.ValueFactory;
import org.shredzone.commons.suncalc.SunTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * Represents a sensor for calculating sunset times.
 *
 * <p>The SunsetSensor class implements the Sensor interface and provides functionality for calculating sunset times.</p>
 */

public class SunsetSensor implements Sensor {
    private String _name;
    private final SensorFunctionality _sensorFunctionality = SensorFunctionality.Sunset;
    private LocalTime _currentTime;

    /**
     * Constructs a new SunsetSensor with the given parameters.
     *
     * <p>Creates a new instance of SunsetSensor with the provided SensorCatalogue, name, and ValueFactory.</p>
     *
     * @param catalogue The SensorCatalogue containing functionality information.
     * @param name      The name of the SunsetSensor.
     * @param valueFactory The ValueFactory used for generating values.
     * @throws IllegalArgumentException if the catalogue parameter is null.
     */

    public SunsetSensor(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null) throw new IllegalArgumentException("Catalogue cannot be null");
        this._name = name;
    }

    /**
     * Retrieves the name of the SunsetSensor.
     *
     * <p>Returns the name of this SunsetSensor instance.</p>
     *
     * @return The name of the SunsetSensor.
     */

    public String getName() {
        return _name;
    }

    /**
     * Retrieves the functionality of the SunsetSensor.
     *
     * <p>Returns the functionality of this SunsetSensor instance, which is always SensorFunctionality.Sunset.</p>
     *
     * @return The functionality of the SunsetSensor.
     */

    public SensorFunctionality getSensorFunctionality() {
        return _sensorFunctionality;
    }

    /**
     * Calculates the sunset time for the specified date and location coordinates.
     *
     * <p>Calculates and retrieves the sunset time for the specified date and location coordinates (latitude and longitude).</p>
     *
     * @param localDate The date for which the sunset time is to be calculated.
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The calculated sunset time.
     */

    public LocalTime calculateSunset(LocalDate localDate, double latitude, double longitude) {
        SunTimes sunTimes = SunTimes.compute()
                .on(localDate)
                .at(latitude, longitude)
                .execute();

        ZonedDateTime sunsetDate = sunTimes.getSet();

        LocalTime sunsetTime = LocalTime.now();
        if (sunsetDate != null) {
            sunsetTime = sunsetDate.toLocalTime();
        }

        this._currentTime = sunsetTime;
        return sunsetTime;
    }

    /**
     * Retrieves the current reading of the SunsetSensor.
     *
     * <p>Returns the current reading of the SunsetSensor, which is the current sunset time if available, otherwise returns null.</p>
     *
     * @return The current reading of the SunsetSensor, or null if the current time is not available.
     */

    public String getReading() {
        if (this._currentTime != null) return _currentTime.toString();
        return null;
    }
}


