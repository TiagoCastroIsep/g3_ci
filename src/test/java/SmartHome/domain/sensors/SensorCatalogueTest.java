package SmartHome.domain.sensors;

import SmartHome.domain.values.ValueFactory;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;

import static SmartHome.domain.constants.Constants.SENSOR_CONFIG;
import static SmartHome.domain.constants.Constants.SENSOR_PATH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Test suite for the {@link SensorCatalogue} class to ensure it correctly initializes,
 * retrieves, and handles sensor functionalities under various scenarios.
 */
class SensorCatalogueTest {

    /**
     * Tests the initialization of an empty sensor catalogue, expecting no sensors to be present.
     */
    @Test
    void initializeEmptyCatalogue() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        SensorCatalogue sensorCatalogue = new SensorCatalogue(config);

        // Assert
        assertTrue(sensorCatalogue.getSensors().isEmpty());
    }

    /**
     * Tests the initialization of a preconfigured sensor catalogue, expecting specific sensors
     * (e.g., TemperatureSensor and HumiditySensor) to be present.
     */
    @Test
    void initializePreconfiguredCatalogue() {
        // Arrange
        SensorCatalogue sensorCatalogue = new SensorCatalogue(SENSOR_CONFIG);

        // Assert
        assertAll(
              () -> assertTrue(sensorCatalogue.getSensors().contains("TemperatureSensor")),
              () -> assertTrue(sensorCatalogue.getSensors().contains("HumiditySensor")));
    }

    /**
     * Tests retrieval of sensor functionalities from a preconfigured catalogue, verifying that
     * the correct functionalities are returned for known sensor types.
     */
    @Test
    void getSensorFunctionalityFromPreconfiguredCatalogue() {
        // Arrange
        SensorCatalogue sensorCatalogue = new SensorCatalogue(SENSOR_CONFIG);

        // Act
        SensorFunctionality temperatureFunctionality = sensorCatalogue.getSensorFunctionality(SensorFunctionality.Temperature);
        SensorFunctionality dewPointFunctionality = sensorCatalogue.getSensorFunctionality(SensorFunctionality.DewPoint);
        SensorFunctionality windFunctionality = sensorCatalogue.getSensorFunctionality(SensorFunctionality.Wind);

        // Assert
        assertAll(
              () -> assertEquals(temperatureFunctionality, SensorFunctionality.Temperature),
              () -> assertEquals(dewPointFunctionality, SensorFunctionality.DewPoint),
              () -> assertEquals(windFunctionality, SensorFunctionality.Wind)
        );
    }

    /**
     * Tests retrieval of sensor functionalities from an empty catalogue, verifying that
     * retrieving functionalities for any sensor type results in default or null behavior.
     */
    @Test
    void getSensorFunctionalityFromEmptyCatalogue() {
        // Arrange
        SensorCatalogue sensorCatalogue = new SensorCatalogue(new PropertyListConfiguration());

        // Act
        SensorFunctionality humidityFunctionality = sensorCatalogue.getSensorFunctionality(SensorFunctionality.Humidity);
        SensorFunctionality solarIrradianceFunctionality = sensorCatalogue.getSensorFunctionality(SensorFunctionality.SolarIrradiance);

        // Assert
        assertAll(
              () -> assertEquals(solarIrradianceFunctionality, SensorFunctionality.SolarIrradiance),
              () -> assertEquals(humidityFunctionality, SensorFunctionality.Humidity)
        );
    }

    /**
     * Tests that requesting a sensor functionality using a null or invalid identifier
     * returns null, indicating the functionality was not found.
     */
    @Test
    void notFoundSensorFunctionality() {
        // Arrange
        SensorCatalogue sensorCatalogue = new SensorCatalogue(new PropertyListConfiguration());

        // Act
        SensorFunctionality notFoundFunctionality = sensorCatalogue.getSensorFunctionality(null);

        // Assert
        assertNull(notFoundFunctionality);
    }

    /**
     * Verifies that attempting to initialize a sensor catalogue with an invalid or erroneous
     * configuration path throws an IllegalArgumentException.
     */
    @Test
    void notFoundPreconfiguredCatalogue() {
        // Arrange
        String errorMessage = "Error occurred while reading the configuration file";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorCatalogue("config.error"));
        String message = exception.getMessage();

        //Assert
        assertTrue(message.contains(errorMessage));
    }

    /**
     * Verifies that initializing a sensor catalogue with an empty configuration path
     * throws an IllegalArgumentException.
     */
    @Test
    void emptyPathPreconfiguredCatalogue() {
        // Arrange
        String errorMessage = "Invalid arguments";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorCatalogue(""));
        String message = exception.getMessage();

        //Assert
        assertTrue(message.contains(errorMessage));
    }

    /**
     * Tests that passing a null configuration object when initializing a sensor catalogue
     * throws an IllegalArgumentException.
     */
    @Test
    void nullPreconfiguredCatalogue() {
        // Arrange
        String errorMessage = "Invalid arguments";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorCatalogue((Configuration) null));
        String message = exception.getMessage();

        //Assert
        assertTrue(message.contains(errorMessage));
    }

    /**
     * Tests that searching for a non-existent sensor in an empty catalogue returns null,
     * indicating the sensor could not be found.
     */
    @Test
    void notFoundFromEmptyCatalogue() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        SensorCatalogue sensorCatalogue = new SensorCatalogue(config);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        //Act
        Sensor sensorNotFound = sensorCatalogue.getSensor("HumiditySensor", SENSOR_PATH,
                "HumiditySensor", valueFactoryDouble);

        //Assert
        assertNull(sensorNotFound);
    }

    /**
     * Verifies that attempting to retrieve a sensor class that does not exist (e.g., a class name error)
     * results in a null response, indicating the class could not be instantiated.
     */
    @Test
    void newSensorHasClassNotFoundException() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        SensorCatalogue sensorCatalogue = new SensorCatalogue(config);
        config.addProperty("sensor", "BlindRollerActuator");
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        //Act
        Sensor sensorNotFound = sensorCatalogue.getSensor("BlindRollerActuator", SENSOR_PATH,
                "BlindRollerActuator", valueFactoryDouble);

        //Assert
        assertNull(sensorNotFound);
    }

    /**
     * Tests that attempting to create a sensor without a valid constructor results in a null response,
     * indicating the sensor could not be instantiated due to method signature issues.
     */
    @Test
    void newSensorHasNoSuchMethodException() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        SensorCatalogue sensorCatalogue = new SensorCatalogue(config);
        config.addProperty("sensor", "Sensor");
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        //Act
        Sensor sensorNotFound = sensorCatalogue.getSensor("Sensor", SENSOR_PATH,
                "Sensor", valueFactoryDouble);

        //Assert
        assertNull(sensorNotFound);
    }

    /**
     * Verifies that specifying an incorrect path for sensor class retrieval results in a null response,
     * indicating the sensor class could not be found.
     */
    @Test
    void newSensorWrongPath() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        SensorCatalogue sensorCatalogue = new SensorCatalogue(config);
        config.addProperty("sensor", "DewPointSensor");
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        //Act
        Sensor sensorNotFound = sensorCatalogue.getSensor("DewPointSensor", "grupo3.domain.",
                "DewPointSensor", valueFactoryDouble);

        //Assert
        assertNull(sensorNotFound);
    }

    /**
     * Tests that providing an empty path for sensor class retrieval results in a null response,
     * indicating the sensor class could not be found or instantiated.
     */
    @Test
    void newSensorEmptyPath() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        SensorCatalogue sensorCatalogue = new SensorCatalogue(config);
        config.addProperty("sensor", "WindSensor");
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        //Act
        Sensor sensorNotFound = sensorCatalogue.getSensor("Sensor", "", "WindSensor", valueFactoryDouble);

        //Assert
        assertNull(sensorNotFound);
    }

    /**
     * Verifies that a temperature sensor can be successfully retrieved from a preconfigured catalogue,
     * ensuring the sensor is available and correctly configured.
     */
    @Test
    void getTemperatureSensorFromPreconfiguredCatalogue() {
        // Arrange
        SensorCatalogue sensorCatalogue = new SensorCatalogue(SENSOR_CONFIG);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Sensor sensor = sensorCatalogue.getSensor("TemperatureSensor", SENSOR_PATH,
                "TemperatureSensor", valueFactoryDouble);

        // Assert
        assertNotNull(sensor);
    }

    /**
     * Tests the retrieval of a humidity sensor from a preconfigured catalogue, ensuring the
     * sensor is correctly identified and instantiated.
     */
    @Test
    void getHumiditySensorFromPreconfiguredCatalogue() {
        // Arrange
        SensorCatalogue sensorCatalogue = new SensorCatalogue(SENSOR_CONFIG);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Sensor sensor = sensorCatalogue.getSensor("HumiditySensor", SENSOR_PATH,
                "HumiditySensor", valueFactoryDouble);

        // Assert
        assertNotNull(sensor);
    }

    /**
     * Verifies that sensors can still be successfully retrieved from a dynamically updated or newly
     * configured sensor catalogue, demonstrating flexibility in sensor catalogue configuration.
     */
    @Test
    void getSensorFromNewConfiguration() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        config.addProperty("sensor", "HumiditySensor");
        SensorCatalogue sensorCatalogue = new SensorCatalogue(config);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Sensor sensor = sensorCatalogue.getSensor("HumiditySensor", SENSOR_PATH,
                "HumiditySensor", valueFactoryDouble);

        // Assert
        assertNotNull(sensor);
    }

    /**
     * Tests that attempting to create a sensor with an invalid or non-sensor type (e.g., using a location type)
     * results in a null response, indicating the type is not suitable for sensor instantiation.
     */
    @Test
    void sensorIsNotValid() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        config.addProperty("sensor", "Location");
        SensorCatalogue sensorCatalogue = new SensorCatalogue(config);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Sensor sensor = sensorCatalogue.getSensor("Location", "pt.ipp.isep.dei.grupo3.domain",
                "Location", valueFactoryDouble);

        // Assert
        assertNull(sensor);
    }
}
