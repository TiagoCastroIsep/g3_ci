package SmartHome.domain.sensors;

import SmartHome.domain.values.ValueFactory;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Catalogue of sensors in the Smart Home system. It provides methods to retrieve sensors from the catalogue.
 */
public class SensorCatalogue {
    private final List<String> _catalogueSensors;
    private final List<SensorFunctionality> _sensorFunctionalities;

    /**
     * Constructs a SensorCatalogue with a given configuration.
     *
     * @param config the configuration object containing the sensor names.
     * @throws IllegalArgumentException if an error occurs or the configuration is invalid.
     */
    public SensorCatalogue(Configuration config) {
        if (!isValidConstructorArguments(config)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        String[] sensors = config.getStringArray("sensor");
        this._catalogueSensors = List.of(sensors);
        this._sensorFunctionalities = List.of(SensorFunctionality.values());
    }

    /**
     * Constructs a SensorCatalogue by reading configuration from the given file.
     *
     * @param fileName The name of the configuration file.
     * @throws IllegalArgumentException if an error occurs while reading the configuration.
     */
    public SensorCatalogue(String fileName) {
        if (!isValidConstructorArguments(fileName)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File(fileName));
            String[] sensors = config.getStringArray("sensor");
            this._catalogueSensors = List.of(sensors);
            this._sensorFunctionalities = List.of(SensorFunctionality.values());
        } catch (Exception exception) {
            String errorMessage = "Error occurred while reading the configuration file '" + fileName + "': " + exception.getMessage();
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Checks if the constructor arguments for configuration are valid.
     *
     * @param config the configuration object.
     * @return true if the arguments are valid, false otherwise.
     */
    private boolean isValidConstructorArguments(Configuration config) {
        return config != null;
    }

    /**
     * Checks if the constructor arguments for file path are valid.
     *
     * @param filePathname The file path.
     * @return true if the arguments are valid, false otherwise.
     */
    private boolean isValidConstructorArguments(String filePathname) {
        return filePathname != null && ! filePathname.isEmpty() && ! filePathname.isBlank();
    }

    /**
     * Retrieves a list of sensors in the catalogue.
     *
     * @return A new list containing the names of all sensors in the catalogue.
     */
    public List<String> getSensors() {
        return new ArrayList<>(_catalogueSensors);
    }

    /**
     * Retrieves a sensor functionality from the catalogue by its name.
     *
     * @param functionality The functionality to retrieve.
     * @return The SensorFunctionality object corresponding to the given name, or null if not found.
     */
    public SensorFunctionality getSensorFunctionality(SensorFunctionality functionality) {
        Optional<SensorFunctionality> matchingFunctionality =
              _sensorFunctionalities.stream()
                    .filter(f -> f.equals(functionality))
                    .findFirst();
        return matchingFunctionality.orElse(null);
    }

    public List<SensorFunctionality> getSensorFunctionalities() {return new ArrayList<>(this._sensorFunctionalities);}

    /**
     * Retrieves a sensor from the catalogue by its model, path, name, and value factory.
     *
     * @param sensorModel  The model of the sensor to retrieve.
     * @param sensorPath   The path of the sensor to retrieve.
     * @param name          The name of the sensor.
     * @param valueFactory  The value factory.
     * @return The Sensor object corresponding to the given model, path, and name, or null if not found.
     */
    public Sensor getSensor(String sensorModel, String sensorPath, String name, ValueFactory valueFactory) {
        Optional<String> sensor =
              this.getSensors().stream()
                    .filter(s -> s.contains(sensorModel))
                    .findFirst();

        if (sensor.isPresent()) {
            try {
                String fullPath = sensorPath + sensorModel;
                return (Sensor) Class.forName(fullPath)
                        .getConstructor(SensorCatalogue.class, String.class, ValueFactory.class)
                        .newInstance(this, name, valueFactory);
            } catch (ClassNotFoundException | InstantiationException |
                     NoSuchMethodException |
                     InvocationTargetException | IllegalArgumentException |
                     IllegalAccessException exception) {
                return null;
            }
        } else return null;
    }
}