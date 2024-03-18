package SmartHome.domain.sensors;


/**
 * Interface representing a sensor in the Smart Home system.
 * Sensors provide information about the environment and are associated with specific functionalities.
 */
public interface Sensor {

    /**
     * Gets the name of the sensor.
     *
     * @return The name of the sensor.
     */
    String getName();

    /**
     * Gets the functionality of the sensor.
     *
     * @return The functionality of the sensor, such as measuring temperature, humidity, etc.
     */
    SensorFunctionality getSensorFunctionality();

}