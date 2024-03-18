package SmartHome.domain.room;

/**
 * A factory class responsible for creating instances of the Dimensions class.
 * It provides a method to create dimensions with specified height, width, and length.
 */
import SmartHome.domain.room.Dimensions;

public class DimensionsFactory {

    /**
     * Creates new dimensions with the given height, width, and length.
     *
     * @param height The height of the dimensions.
     * @param width  The width of the dimensions.
     * @param length The length of the dimensions.
     * @return A new instance of the Dimensions class.
     */
    public Dimensions createDimensions(double height, double width, double length){
        return new Dimensions(height, width, length);
    }
}
