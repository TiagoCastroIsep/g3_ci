package SmartHome.domain.actuators;

public interface Actuator {
    String getName();
    /**
     * Gets the functionality of the actuator.
     *
     * @return The functionality of the actuator, such as A or B.
     */
    ActuatorFunctionality getActuatorFunctionality();
}
