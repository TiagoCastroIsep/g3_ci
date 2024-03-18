package SmartHome.domain.values;

/**
 * An interface representing a factory for creating various types of values used in the Smart Home system.
 * Implementations of this interface provide methods to create specific types of values, such as range actuators,
 * temperature values, energy values, and more.
 */

public interface ValueFactory {
    Value createRangeActuatorInt(int lowerLimit, int upperLimit);
    Value createRangeActuatorDecimal(double lowerLimit, double upperLimit);
    Value createWValue();
    Value createPercentage();
    Value createCelsiusTemperature();
    Value createWhValue();
    Value createKmhCardinalValue();
    Value createWm2Value();
}