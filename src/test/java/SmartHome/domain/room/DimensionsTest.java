package SmartHome.domain.room;

import SmartHome.domain.room.Dimensions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 This class serves as a test suite for the Dimensions class, focusing on volume-related operations.
 */
class DimensionsTest {
    Dimensions dimensionsForVolume;

    /**
     Sets up the test environment before each test method execution by initializing the dimensionsForVolume object
     with specific dimensions.
     */
    @BeforeEach
    public void setUp() {
        dimensionsForVolume = new Dimensions(2.8, 2, 3.6);
    }


    /**
     Tests the constructor of the Dimensions class with a valid height parameter.
     */
    @Test
    void validHeightConstructor() {
        // Arrange
        double expectedResult = 2.8;

        // Act
        double actualResult = dimensionsForVolume.getHeight();

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    /**
     Tests the constructor of the Dimensions class with a valid width parameter.
     */
    @Test
    void validWidthConstructor() {
        // Arrange
        double expectedResult = 2;

        // Act
        double actualResult = dimensionsForVolume.getWidth();

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    /**
     Tests the constructor of the Dimensions class with a valid length parameter.
     */
    @Test
    void validLengthConstructor() {
        // Arrange
        double expectedResult = 3.6;

        // Act
        double actualResult = dimensionsForVolume.getLength();

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    /**
     Tests the constructor of the Dimensions class when an invalid height parameter is provided.
     It verifies that an IllegalArgumentException is thrown with the appropriate error message.
     */
    @Test
    void invalidHeightThrowsException() {
        // Arrange
        double invalidHeight = 0;
        String expectedResult = "Invalid arguments passed to constructor.";

        // Act
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> new Dimensions(invalidHeight, 2, 3.6));
        String actualResult = exception.getMessage();

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    /**
     Tests the constructor of the Dimensions class when an invalid width parameter is provided.
     It verifies that an IllegalArgumentException is thrown with the appropriate error message.
     */
    @Test
    void invalidWidthThrowsException() {
        // Arrange
        double invalidWidth = 0;
        String expectedResult = "Invalid arguments passed to constructor.";

        // Act
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> new Dimensions(2.8, invalidWidth, 3.6));
        String actualResult = exception.getMessage();

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    /**
     Tests the constructor of the Dimensions class when an invalid length parameter is provided.
     It verifies that an IllegalArgumentException is thrown with the appropriate error message.
     */
    @Test
    void invalidLengthThrowsException() {
        // Arrange
        double invalidLength = 0;
        String expectedResult = "Invalid arguments passed to constructor.";

        // Act
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> new Dimensions(2.8, 2, invalidLength));
        String actualResult = exception.getMessage();

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    /**
     Tests the constructor of the Dimensions class with a valid boundary height parameter.
     It verifies that no exception is thrown when valid boundary height is provided.
     */
    @Test
    void validBoundaryHeight(){
        // Arrange
        double validBoundaryHeight = 1;

        // Act
        assertDoesNotThrow(() -> new Dimensions(validBoundaryHeight, 2, 3.6));
    }


    /**
     Tests the constructor of the Dimensions class with a valid boundary width parameter.
     It verifies that no exception is thrown when a valid boundary width is provided.
     */
    @Test
    void validBoundaryWidth(){
        // Arrange
        double validBoundaryWidth = 1;

        // Act + Assert
        assertDoesNotThrow(() -> new Dimensions(2.8, validBoundaryWidth, 3.6));
    }


    /**
     Tests the constructor of the Dimensions class with a valid boundary length parameter.
     It verifies that no exception is thrown when a valid boundary length is provided.
     */
    @Test
    void testvalidBoundaryLength(){
        // Arrange
        double validBoundaryLength = 1;

        // Act + Assert
        assertDoesNotThrow(() -> new Dimensions(2.8, 2, validBoundaryLength));
    }


    /**
     Tests the toString() method of the Dimensions class when used to represent volume dimensions.
     It verifies that the string representation matches the expected format.
     */
    @Test
    void testValidToStringForVolume() {
        // Arrange
        String expectedResult = "Dimensions{" +
                "height=2.8, width=2.0, length=3.6}";

        // Act
        String actualResult = dimensionsForVolume.toString();

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    /**
     Tests the constructor of the Dimensions class with valid boundary values.
     It verifies that the created dimensions object has the expected values.
     */
    @Test
    void testValidBoundaries() {
        // Arrange
        double validBoundaryHeight = 1;
        double validBoundaryWidth = 1;
        double validBoundaryLength = 1;
        String expectedResult = "Dimensions{" +
                "height=1.0, width=1.0, length=1.0}";

        // Act
        Dimensions dimensions = new Dimensions(validBoundaryHeight, validBoundaryWidth, validBoundaryLength);
        String actualResult = dimensions.toString();

        // Assert
        assertEquals(expectedResult, actualResult);
    }
}
