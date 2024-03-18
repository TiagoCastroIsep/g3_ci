package SmartHome.domain.values;

import SmartHome.domain.values.implementation.*;

/**
 * Implementation of the ValueFactory interface that creates instances of various value types used in the Smart Home system.
 * This class provides concrete implementations for creating values such as range actuators, temperature, energy, speed, and more.
 */

public class ValueFactoryImpl implements ValueFactory {

    /**
     * Creates a new RangeActuatorIntValue with the specified lower and upper limits.
     *
     * @param lowerLimit the lower limit of the range
     * @param upperLimit the upper limit of the range
     * @return a new RangeActuatorIntValue with the specified limits
     */
    @Override
    public Value createRangeActuatorInt(int lowerLimit, int upperLimit) {
        return new RangeActuatorIntValue(lowerLimit, upperLimit);
    }

    /**
     * Creates a new RangeActuatorFractionalValue with the specified lower and upper limits.
     *
     * @param lowerLimit the lower limit of the range
     * @param upperLimit the upper limit of the range
     * @return a new RangeActuatorFractionalValue with the specified limits
     */
    @Override
    public Value createRangeActuatorDecimal(double lowerLimit, double upperLimit) {
        return new RangeActuatorFractionalValue(lowerLimit, upperLimit);
    }
    /**
     * Creates a new WValue instance.
     *
     * @return a new WValue instance
     */
    @Override
    public WValue createWValue() {
        return new WValue();
    }
    /**
     * Creates a new PercentageValue instance.
     *
     * @return a new PercentageValue instance
     */
    @Override
    public PercentageValue createPercentage() {
        return new PercentageValue();
    }

    /**
     * Creates a new CelsiusValue instance.
     *
     * @return a new CelsiusValue instance
     */
    @Override
    public CelsiusValue createCelsiusTemperature() {
        return new CelsiusValue();
    }
    /**
     * Creates a new WhValue instance.
     *
     * @return a new WhValue instance
     */
    @Override
    public WhValue createWhValue() {
        return new WhValue();
    }
    /**
     * Creates a new KmhCardinalValue instance.
     *
     * @return a new KmhCardinalValue instance
     */
    @Override
    public KmhCardinalValue createKmhCardinalValue() {
        return new KmhCardinalValue();
    }
    /**
     * Creates a new Wm2Value instance.
     *
     * @return a new Wm2Value instance
     */
    @Override
    public Wm2Value createWm2Value() {
        return new Wm2Value();
    }
}
