package SmartHome.domain.values;

/**
 * Represents a value within the smart home system, encapsulating measurement data along with its unit.
 * This interface defines the contract for manipulating and retrieving the value and its measurement unit.
 */
public interface Value {

    /**
     * Returns a string representation of the current value. This representation should include
     * the value and may also include the measurement unit, depending on the implementation.
     *
     * @return A string representation of the value.
     */
    String toString();

    /**
     * Sets the value based on a provided string representation of a measurement. Implementations
     * should validate the format and correctness of the measured value according to their specific requirements.
     *
     * @param measured The string representation of the measured value to be set.
     * @return True if the value was successfully set; false otherwise.
     */
    boolean setValue(String measured);

    /**
     * Retrieves the measurement unit associated with this value. The specific unit depends
     * on the type of value (e.g., degrees Celsius for temperature, meters for distance).
     *
     * @return A string representing the measurement unit of the value.
     */
    String getMeasurementUnit();
}