package SmartHome.domain.actuators;

import SmartHome.domain.values.ValueFactory;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * This class represents a catalogue of actuators.
 * It provides methods to retrieve actuators from the catalogue.
 */
public class ActuatorCatalogue {

    /**
     * A list of actuator names in the catalogue.
     */
    private final List<String> _catalogueActuators;

    /**
     * A list of actuator functionalities in the catalogue.
     */
    private final List<ActuatorFunctionality> _actuatorFunctionalities;

    /**
     * Constructs an ActuatorCatalogue with a given configuration.
     *
     * @param config the configuration object containing the actuator names
     * @throws IllegalArgumentException if the provided configuration is invalid
     */
    public ActuatorCatalogue(Configuration config) {
        if (!isValidConstructorArguments(config)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        String[] actuators = config.getStringArray("actuator");
        this._catalogueActuators = List.of(actuators);
        this._actuatorFunctionalities =List.of(ActuatorFunctionality.values());;
    }

    /**
     * Constructs an ActuatorCatalogue by reading configuration from the given file.
     *
     * @param fileName The name of the configuration file.
     * @throws IllegalArgumentException if an error occurs while reading the configuration file
     */
    public ActuatorCatalogue(String fileName) {
        if (!isValidConstructorArguments(fileName)) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File(fileName));
            String[] actuators = config.getStringArray("actuator");
            this._catalogueActuators = List.of(actuators);
            this._actuatorFunctionalities = List.of(ActuatorFunctionality.values());
        } catch (Exception exception) {
            String errorMessage = "Error occurred while reading the configuration file '" + fileName + "': " + exception.getMessage();
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Validates the constructor arguments for a Configuration object.
     *
     * @param config The Configuration object to validate.
     * @return true if the arguments are valid, false otherwise.
     */

    private boolean isValidConstructorArguments(Configuration config) {
        if(config == null) return false;
        else return true;
    }

    /**
     * Validates the constructor arguments for a file path.
     *
     * @param filePathname The file path to validate.
     * @return true if the arguments are valid, false otherwise.
     */

    private boolean isValidConstructorArguments(String filePathname) {
        if(filePathname == null || filePathname.isEmpty() || filePathname.isBlank()) return false;
        else return true;
    }

    /**
     * Retrieves a list of actuator functionalities in the catalogue.
     *
     * @return A new list containing the functionalities of all actuators in the catalogue.
     */

    public List<ActuatorFunctionality> getActuatorModels() {return new ArrayList<>(this._actuatorFunctionalities);}

    /**
     * Retrieves a list of actuator names in the catalogue.
     *
     * @return A new list containing the names of all actuators in the catalogue.
     */
    public List<String> getActuators() {
        return new ArrayList<>(_catalogueActuators);
    }

    /**
     * Retrieves an actuator functionality from the catalogue by its functionality.
     *
     * @param functionality The functionality of the actuator to retrieve.
     * @return The ActuatorFunctionality object corresponding to the given functionality, or null if not found.
     */
    public ActuatorFunctionality getActuatorFunctionality(ActuatorFunctionality functionality) {
        Optional<ActuatorFunctionality> matchingFunctionality =
                _actuatorFunctionalities.stream()
                        .filter(f -> f.equals(functionality))
                        .findFirst();
        return matchingFunctionality.orElse(null);
    }

    /**
     * Retrieves an actuator from the catalogue by its name and path.
     *
     * @param actuatorModel The name of the actuator to retrieve.
     * @param actuatorPath  The path of the actuator to retrieve.
     * @param name          The name of the actuator.
     * @param valueFactory  The value factory.
     * @return The Actuator object corresponding to the given name and path, or null if not found.
     */

    public Actuator getActuator(String actuatorModel, String actuatorPath, String name, ValueFactory valueFactory) {
        Optional<String> actuator = this.getActuators().stream().filter(s -> s.contains(actuatorModel)).findFirst();

        if (actuator.isPresent()) {
            try {
                String fullPath = actuatorPath + actuatorModel;
                return (Actuator) Class.forName(fullPath)
                        .getConstructor(ActuatorCatalogue.class, String.class, ValueFactory.class)
                        .newInstance(this, name, valueFactory);
            } catch (ClassNotFoundException | InstantiationException | NoSuchMethodException |
                     InvocationTargetException | IllegalArgumentException | IllegalAccessException exception) {
                return null;
            }
        } else return null;
    }
}

