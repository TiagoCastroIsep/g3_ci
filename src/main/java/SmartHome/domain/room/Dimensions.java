package SmartHome.domain.room;

/**
 * Represents the dimensions of a given object, with specific height, width and length values.
 * Provides functionality to create and update dimensions within valid ranges.
 */
public class Dimensions {
    private double height;
    private double width;
    private double length;

    /**
     * Constructs a new Dimensions instance with specified height, width and length.
     * Validates the provided values to ensure they are non-negative.
     *
     * @param height The height of the object, must be non-negative.
     * @param width  The width of the object, must be non-negative.
     * @param length The length of the object, must be non-negative.
     * @throws IllegalArgumentException If height, width or length values are negative.
     */
    public Dimensions(double height, double width, double length) {
        validateArguments(height, width, length);
        this.height=height;
        this.width=width;
        this.length=length;
    }

    /**
     * Validates the height, width and length arguments to ensure they are non-negative.
     *
     * @param height The height to validate.
     * @param width  The width to validate.
     * @param length The length to validate.
     * @throws IllegalArgumentException If height, width or length values are negative.
     */
    private void validateArguments(double height, double width, double length) {
        if (Double.isNaN(height) || height <=0) throw new IllegalArgumentException("Invalid arguments passed to constructor.");
        if (Double.isNaN(width) ||width <=0) throw new IllegalArgumentException("Invalid arguments passed to constructor.");
        if (Double.isNaN(length) ||length <=0) throw new IllegalArgumentException("Invalid arguments passed to constructor.");
    }

    /**
     * Retrieves the height value.
     *
     * @return Height of the object.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Retrieves the width value.
     *
     * @return Width of the object.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Retrieves the length value.
     *
     * @return Length of the object.
     */
    public double getLength() {
        return length;
    }
    /**
     * Returns a string representation of the Dimensions object.
     *
     * @return A string representation of the Dimensions object in the format of "Dimensions{height=value, width=value, length=value}"
     */
    @Override
    public String toString() {
        return "Dimensions{" +
                "height=" + height +
                ", width=" + width +
                ", length=" + length +
                '}';
    }

}
