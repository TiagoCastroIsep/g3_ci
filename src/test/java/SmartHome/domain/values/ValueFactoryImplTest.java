package SmartHome.domain.values;

import SmartHome.domain.values.Value;
import SmartHome.domain.values.ValueFactoryImpl;
import SmartHome.domain.values.implementation.KmhCardinalValue;
import SmartHome.domain.values.implementation.WValue;
import SmartHome.domain.values.implementation.WhValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for the {@link ValueFactoryImpl} class to ensure it correctly creates instances of different {@link Value} types.
 */
class ValueFactoryImplTest {

    /**
     * Verifies that the factory can create a range actuator with integer limits.
     */
    @Test
    void createRangeActuatorInt() {
        int lowerLimit = -1;
        int upperLimit = 1;
        Value val = new ValueFactoryImpl().createRangeActuatorInt(lowerLimit, upperLimit);
        assertInstanceOf(Value.class, val);
    }

    /**
     * Verifies that the factory can create a range actuator with fractional (double) limits.
     */
    @Test
    void createRangeActuatorFractional() {
        double lowerLimit = -1.0;
        double upperLimit = 1.0;
        Value val = new ValueFactoryImpl().createRangeActuatorDecimal(lowerLimit, upperLimit);
        assertInstanceOf(Value.class, val);
    }

    /**
     * Tests the creation of a {@link WValue} instance.
     */
    @Test
    void createWValueTest() {
        // Arrange
        ValueFactoryImpl valueFactory = new ValueFactoryImpl();

        // Act
        WValue result = valueFactory.createWValue();

        // Assert
        assertNotNull(result);
        assertInstanceOf(Value.class, result);
    }

    /**
     * Tests the creation of a {@link WhValue} instance.
     */
    @Test
    void createWhValueTestTrue() {
        // Arrange
        ValueFactoryImpl valueFactory = new ValueFactoryImpl();

        // Act
        WhValue result = valueFactory.createWhValue();

        // Assert
        assertInstanceOf(Value.class, result);
    }

    /**
     * Tests the creation of a {@link KmhCardinalValue} instance.
     */
    @Test
    void createKmhCardinalValueTestTrue() {
        // Arrange
        ValueFactoryImpl valueFactory = new ValueFactoryImpl();

        // Act
        KmhCardinalValue result = valueFactory.createKmhCardinalValue();

        // Assert
        assertInstanceOf(Value.class, result);
    }

    /**
     * Verifies the factory's ability to create a percentage value.
     */
    @Test
    void createPercentage() {
        // Arrange
        ValueFactoryImpl valueFactory = new ValueFactoryImpl();

        //Act
        Value result = valueFactory.createPercentage();

        // Assert
        assertInstanceOf(Value.class, result);
    }

    /**
     * Verifies the factory's ability to create a temperature value in Celsius.
     */
    @Test
    void createCelsiusTemperature() {
        // Arrange
        ValueFactoryImpl valueFactory = new ValueFactoryImpl();

        //Act
        Value result = valueFactory.createCelsiusTemperature();

        // Assert
        assertInstanceOf(Value.class, result);
    }

    /**
     * Tests the creation of a value representing power per square meter (W/m^2).
     */
    @Test
    void createWm2Value() {
        // Arrange
        ValueFactoryImpl valueFactory = new ValueFactoryImpl();

        //Act
        Value result = valueFactory.createWm2Value();

        // Assert
        assertInstanceOf(Value.class, result);
    }
}