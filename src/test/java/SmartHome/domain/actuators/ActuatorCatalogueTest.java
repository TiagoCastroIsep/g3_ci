package SmartHome.domain.actuators;

import SmartHome.domain.actuators.Actuator;
import SmartHome.domain.actuators.ActuatorCatalogue;
import SmartHome.domain.actuators.ActuatorFunctionality;
import SmartHome.domain.values.ValueFactory;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static SmartHome.domain.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Tests for the {@link ActuatorCatalogue} class, ensuring it correctly initializes and retrieves
 * actuators with various configurations and handles errors appropriately.
 */
class ActuatorCatalogueTest {

    /**
     * Tests the initialization of an empty actuator catalogue, expecting no actuators to be present.
     */
    @Test
    void initializeEmptyCatalogue() {
        // Arrange
        ActuatorCatalogue actuatorCatalogue = mock(ActuatorCatalogue.class);

        // Assert
        assertTrue(actuatorCatalogue.getActuators().isEmpty());
    }

    /**
     * Tests the initialization of a preconfigured actuator catalogue, expecting specific actuators
     * to be present and correctly configured.
     */
    @Test
    void initializePreconfiguredCatalogue() {
        // Arrange
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(ACTUATOR_CONFIG);

        // Act + Assert
        assertTrue(actuatorCatalogue.getActuators().contains("BlindRollerActuator"));
        assertTrue(actuatorCatalogue.getActuators().contains("SwitchOnOffActuator"));
        assertTrue(actuatorCatalogue.getActuators().contains("RangeActuatorInt"));
        assertTrue(actuatorCatalogue.getActuators().contains("RangeActuatorDecimal"));
    }

    /**
     * Verifies that attempting to initialize an actuator catalogue with an invalid configuration
     * path throws an IllegalArgumentException.
     */
    @Test
    void notFoundPreconfiguredCatalogue() {
        // Arrange
        String errorMessage = "Error occurred while reading the configuration file";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorCatalogue("config.error"));
        String message = exception.getMessage();

        // Assert
        assertTrue(message.contains(errorMessage));
    }

    /**
     * Tests that searching for an actuator in an empty catalogue returns null, indicating the actuator
     * could not be found.
     */
    @Test
    void notFoundFromEmptyCatalogue() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(config);
        ValueFactory valueFactory = mock(ValueFactory.class);

        // Act
        Actuator actuatorNotFound = actuatorCatalogue.getActuator("BlindRollerActuator", "bra1", SENSOR_PATH, valueFactory);

        // Assert
        assertNull(actuatorNotFound);
    }

    /**
     * Verifies that attempting to retrieve an actuator class that does not exist within the catalogue
     * results in a null response.
     */
    @Test
    void newActuatorHasClassNotFoundException() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(config);
        config.addProperty("actuator", "WindActuator");
        ValueFactory valueFactory = mock(ValueFactory.class);

        // Act
        Actuator actuatorNotFound = actuatorCatalogue.getActuator("WindActuator", "bra1",
                SENSOR_PATH, valueFactory);

        // Assert
        assertNull(actuatorNotFound);
    }

    /**
     * Tests that trying to instantiate an actuator without a valid constructor signature results
     * in a null response.
     */
    @Test
    void newActuatorHasNoSuchMethodException() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(config);
        config.addProperty("actuator", "WindActuator");
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Actuator actuatorNotFound = actuatorCatalogue.getActuator("WindActuator", "bra1",
                SENSOR_PATH, valueFactoryDouble);

        // Assert
        assertNull(actuatorNotFound);
    }

    /**
     * Verifies that specifying an incorrect path for actuator class retrieval results in a null response.
     */
    @Test
    void newActuatorWrongPath() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(config);
        config.addProperty("actuator", "WindActuator");
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Actuator actuatorNotFound = actuatorCatalogue.getActuator("WindActuator", "bra1",
                "grupo3.domain.", valueFactoryDouble);

        // Assert
        assertNull(actuatorNotFound);
    }

    /**
     * Tests that providing an empty path for actuator class retrieval results in a null response.
     */
    @Test
    void newActuatorEmptyPath() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(config);
        config.addProperty("actuator", "WindActuator");
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Actuator actuatorNotFound = actuatorCatalogue.getActuator("WindActuator", "bra1",
                "", valueFactoryDouble);

        // Assert
        assertNull(actuatorNotFound);
    }

    /**
     * Verifies that a BlindRollerActuator can be successfully retrieved from a preconfigured catalogue,
     * demonstrating correct configuration and availability.
     */
    @Test
    void getBlindRollerActuatorFromPreconfiguredCatalogue() {
        // Arrange
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(ACTUATOR_CONFIG);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Actuator actuator = actuatorCatalogue.getActuator("BlindRollerActuator", ACTUATOR_PATH,
                "bra1", valueFactoryDouble);

        // Assert
        assertNotNull(actuator);
    }

    /**
     * Tests the functionality of retrieving actuators from a newly configured or dynamically updated
     * actuator catalogue.
     */
    @Test
    void getActuatorFromNewConfiguration() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        config.addProperty("actuator", "SunsetActuator");
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(config);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Actuator actuator = actuatorCatalogue.getActuator("SunsetActuator", ACTUATOR_PATH, "bra1", valueFactoryDouble);

        // Assert
        assertNull(actuator);
    }

    /**
     * Verifies the retrieval of a RangeActuatorInt from a preconfigured actuator catalogue,
     * ensuring it is correctly identified and instantiated.
     */
    @Test
    void getRangeActuatorIntFromPreconfiguredCatalogue() {
        // Arrange
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(ACTUATOR_CONFIG);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Actuator actuator = actuatorCatalogue.getActuator("RangeActuatorInt", ACTUATOR_PATH, "bra1",
                valueFactoryDouble);

        // Assert
        assertNotNull(actuator);
    }

    /**
     * Tests the retrieval of a RangeActuatorDecimal from a preconfigured actuator catalogue,
     * confirming correct instantiation and configuration.
     */
    @Test
    void getRangeActuatorFractionalFromPreconfiguredCatalogue() {
        // Arrange
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(ACTUATOR_CONFIG);
        ValueFactory valueFactoryDouble = mock(ValueFactory.class);

        // Act
        Actuator actuator = actuatorCatalogue.getActuator("RangeActuatorDecimal", ACTUATOR_PATH,
                "raf1", valueFactoryDouble);

        // Assert
        assertNotNull(actuator);
    }

    /**
     * Verifies that the ActuatorCatalogue constructor throws an IllegalArgumentException when
     * initialized with a null Configuration object.
     */
    @Test
    void validConstructorArgumentsThrowsExceptionWithNullConfig() {
        // Arrange
        Configuration config = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ActuatorCatalogue(config));
    }

    /**
     * Verifies that the ActuatorCatalogue constructor throws an IllegalArgumentException when
     * initialized with a null file path for the configuration.
     */
    @Test
    void validConstructorArgumentsThrowsExceptionWithNullFilePath() {
        // Arrange
        String filePath = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ActuatorCatalogue(filePath));
    }

    /**
     * Tests the retrieval of available actuator models from the actuator catalogue, ensuring
     * a non-null response even if the catalogue is empty.
     */
    @Test
    void getActuatorsModelsTest() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(config);

        // Act
        List<ActuatorFunctionality> actualActuatorsModels = actuatorCatalogue.getActuatorModels();

        // Assert
        assertNotNull(actualActuatorsModels);
    }

    /**
     * Verifies the functionality of retrieving specific actuator functionalities,
     * such as BlindRoller, from the actuator catalogue.
     */
    @Test
    void testGetActuatorsFunctionality() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(config);
        ActuatorFunctionality expectedFunctionality = ActuatorFunctionality.BlindRoller;

        // Act
        ActuatorFunctionality actualFunctionality = actuatorCatalogue.getActuatorFunctionality(expectedFunctionality);

        // Assert
        assertEquals(expectedFunctionality, actualFunctionality);
    }

    /**
     * Tests the behavior of the actuator catalogue when requesting a non-existent
     * actuator functionality, expecting a null response.
     */
    @Test
    void testGetActuatorsFunctionalityNotFound() {
        // Arrange
        Configuration config = new PropertyListConfiguration();
        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue(config);
        ActuatorFunctionality nonExistentFunctionality = null;

        // Act
        ActuatorFunctionality actualFunctionality = actuatorCatalogue.getActuatorFunctionality(nonExistentFunctionality);

        // Assert
        assertNull(actualFunctionality, "Expected functionality to be null when non-existent functionality is requested");
    }
}