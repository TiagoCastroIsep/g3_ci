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
 * Represents a sensor that detects sunrise events.
 *
 * <p>The SunriseSensor class implements the Sensor interface and provides functionality to monitor sunrise events.
 * Instances of this class maintain information about the sensor's name, functionality, and current time.</p>
 */

public class SunriseSensor implements Sensor {
    private final String _name;
    private final SensorFunctionality _sensorFunctionality = SensorFunctionality.Sunrise;
    private LocalTime _currentTime;

    /**
     * Constructs a new SunriseSensor with the specified sensor catalogue, name, and value factory.
     *
     * <p>The SunriseSensor class represents a sensor that detects sunrise events. This constructor initializes
     * the sensor with the provided catalogue, name, and value factory. It validates the input parameters to ensure
     * that the catalogue is not null and the name is not null or empty.</p>
     *
     * @param catalogue The sensor catalogue to which this sensor belongs.
     * @param name The name of the sensor.
     * @param valueFactory The value factory used to create sensor values.
     * @throws IllegalArgumentException If the catalogue is null or the name is null or empty.
     */

    public SunriseSensor(SensorCatalogue catalogue, String name, ValueFactory valueFactory) {
        if (catalogue == null) throw new IllegalArgumentException("Catalogue cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this._name = name;
    }

    /**
     * Retrieves the name of the sunrise sensor.
     *
     * <p>Returns the name of this sunrise sensor as a String.</p>
     *
     * @return The name of the sunrise sensor.
     */

    public String getName() {
        return _name;
    }

    /**
     * Retrieves the functionality of the sensor.
     *
     * <p>Returns the functionality of this sensor, which is the type of functionality it provides.</p>
     *
     * @return The functionality of the sensor.
     */

    public SensorFunctionality getSensorFunctionality() {
        return _sensorFunctionality;
    }

    /**
     * Calculates the time of sunrise for a given date and geographical coordinates.
     *
     * <p>Calculates the time of sunrise for the specified date and location represented by the latitude and longitude coordinates.</p>
     *
     * @param localDate The date for which to calculate the sunrise time.
     * @param latitude The latitude of the location in decimal degrees.
     * @param longitude The longitude of the location in decimal degrees.
     * @return The time of sunrise for the specified date and location.
     */

    public LocalTime calculateSunrise(LocalDate localDate, double latitude, double longitude) {
        SunTimes sunTimes = SunTimes.compute()
                .on(localDate)
                .at(latitude, longitude)
                .execute();

        ZonedDateTime sunriseDate = sunTimes.getRise();

        LocalTime sunriseTime = LocalTime.now();
        if (sunriseDate != null) {
            sunriseTime = sunriseDate.toLocalTime();
        }

        this._currentTime = sunriseTime;
        return sunriseTime;
    }

    /**
     * Gets the reading from the sensor, representing the time of sunrise.
     *
     * <p>Returns the time of sunrise calculated by the sensor, or {@code null} if the calculation has not been performed.</p>
     *
     * @return The time of sunrise as a string representation in ISO-8601 format (HH:mm:ss), or {@code null} if not available.
     */

    public String getReading() {
        if (this._currentTime != null) return _currentTime.toString();
        return null;
    }
}


