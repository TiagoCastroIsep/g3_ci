package SmartHome.domain.sensors.implementation;

import SmartHome.domain.sensors.SensorCatalogue;
import SmartHome.domain.sensors.SensorFunctionality;
import SmartHome.domain.sensors.implementation.SunsetSensor;
import SmartHome.domain.values.ValueFactory;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link SunsetSensor} class.
 *
 * This class contains unit tests to verify the functionality of the {@link SunsetSensor} class.
 * It tests various aspects such as sensor name, sensor functionality, sunrise calculation, and reading retrieval.
 */
class SunsetSensorTest {
    SensorCatalogue catalogue;
    String SUNSET_NAME = "SunsetSensor";
    ValueFactory valueFactoryDouble;

    /**
     * Sets up the necessary dependencies before each test method is executed.
     *
     * <p>This method initializes the ValueFactory mock object and creates a new SensorCatalogue instance using a PropertyListConfiguration.</p>
     */

    @BeforeEach
    void setUp() {
        valueFactoryDouble = mock(ValueFactory.class);
        catalogue = new SensorCatalogue(new PropertyListConfiguration());
    }

    /**
     * Test case to verify the behavior of the SunsetSensor constructor when null catalogue is provided.
     *
     * <p><b>Test:</b> invalidConstructorWithNullCatalogue</p>
     * <p><b>Purpose:</b> To ensure that an IllegalArgumentException is thrown when a null catalogue is provided to the SunsetSensor constructor.</p>
     * <p><b>Steps:</b>
     * <ol>
     *     <li>Arrange: Set the catalogue to null.</li>
     *     <li>Act: Call the SunsetSensor constructor with the null catalogue.</li>
     *     <li>Assert: Verify that an IllegalArgumentException with the message "Catalogue cannot be null" is thrown.</li>
     * </ol>
     * </p>
     */

    @Test
    void invalidConstructorWithNullCatalogue() {

        // Arrange
        SensorCatalogue catalogue = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new SunsetSensor(catalogue, SUNSET_NAME, valueFactoryDouble));

        // Assert
        assertEquals("Catalogue cannot be null", exception.getMessage());
    }

    /**
     * Test case to verify the functionality of the getName() method in the SunsetSensor class.
     *
     * <p><b>Test:</b> testGetName</p>
     * <p><b>Purpose:</b> To ensure that the getName() method returns the correct name set during initialization.</p>
     * <p><b>Steps:</b>
     * <ol>
     *     <li>Arrange: Create a mock SensorCatalogue object.</li>
     *     <li>Arrange: Mock the behavior of getSensorFunctionality() method of the SensorCatalogue to return SensorFunctionality.Sunset.</li>
     *     <li>Arrange: Create a new SunsetSensor object with the mocked SensorCatalogue, a predefined SUNSET_NAME, and a value factory.</li>
     *     <li>Act: Call the getName() method of the SunsetSensor object.</li>
     *     <li>Assert: Verify that the returned name matches the predefined SUNSET_NAME.</li>
     * </ol>
     * </p>
     */

    @Test
    void getName() {

        // Arrange
        SensorCatalogue catalogue = mock(SensorCatalogue.class);
        when(catalogue.getSensorFunctionality(SensorFunctionality.Sunset)).thenReturn(SensorFunctionality.Sunset);
        SunsetSensor sensor = new SunsetSensor(catalogue, SUNSET_NAME, valueFactoryDouble);

        // Act
        String name = sensor.getName();

        // Assert
        assertEquals("SunsetSensor", name);
    }

    /**
     * Test case to verify the functionality of the getSensorFunctionality() method in the SunsetSensor class.
     *
     * <p><b>Test:</b> testGetSensorFunctionality</p>
     * <p><b>Purpose:</b> To ensure that the getSensorFunctionality() method returns the correct functionality set during initialization.</p>
     * <p><b>Steps:</b>
     * <ol>
     *     <li>Arrange: Create a mock SensorCatalogue object.</li>
     *     <li>Arrange: Mock the behavior of getSensorFunctionality() method of the SensorCatalogue to return SensorFunctionality.Sunset.</li>
     *     <li>Arrange: Create a new SunsetSensor object with the mocked SensorCatalogue, a predefined SUNSET_NAME, and a value factory.</li>
     *     <li>Act: Call the getSensorFunctionality() method of the SunsetSensor object.</li>
     *     <li>Assert: Verify that the returned functionality matches SensorFunctionality.Sunset.</li>
     * </ol>
     * </p>
     */

    @Test
    void getSensorFunctionality() {

        // Arrange
        SensorCatalogue catalogue = mock(SensorCatalogue.class);
        when(catalogue.getSensorFunctionality(SensorFunctionality.Sunset)).thenReturn(SensorFunctionality.Sunset);
        SunsetSensor sensor = new SunsetSensor(catalogue, SUNSET_NAME, valueFactoryDouble);

        // Act
        SensorFunctionality functionality = sensor.getSensorFunctionality();

        // Assert
        assertEquals(SensorFunctionality.Sunset, functionality);
    }

    /**
     * Test case to verify the functionality of the getReading() method in the SunsetSensor class.
     *
     * <p><b>Test:</b> testReadingToString</p>
     * <p><b>Purpose:</b> To ensure that the getReading() method returns the correct sunset time string after calculating sunset for a specific date, latitude, and longitude.</p>
     * <p><b>Steps:</b>
     * <ol>
     *     <li>Arrange: Create a new SunsetSensor object with the provided catalogue, SUNSET_NAME, and value factory.</li>
     *     <li>Arrange: Set up test parameters including a test date, latitude, longitude, and the expected current time string.</li>
     *     <li>Act: Call the calculateSunset() method of the SunsetSensor object with the test parameters.</li>
     *     <li>Act: Call the getReading() method of the SunsetSensor object to retrieve the calculated sunset time string.</li>
     *     <li>Assert: Verify that the retrieved sunset time string matches the expected current time string.</li>
     * </ol>
     * </p>
     */

    @Test
    void readingToString() {

        // Arrange
        SunsetSensor sensor = new SunsetSensor(catalogue, SUNSET_NAME, valueFactoryDouble);
        LocalDate testDate = LocalDate.of(2024, 2, 29);
        double latitude = 41.1579;
        double longitude = - 8.6291;
        String currentTime = "18:24:53";

        // Act
        sensor.calculateSunset(testDate, latitude, longitude);
        String result = sensor.getReading();

        // Assert
        assertEquals(currentTime, result);
    }

    /**
     * Test case to verify the behavior of the getReading() method in the SunsetSensor class when called before calculating sunset.
     *
     * <p><b>Test:</b> testInvalidReading</p>
     * <p><b>Purpose:</b> To ensure that calling the getReading() method before calculating sunset returns null.</p>
     * <p><b>Steps:</b>
     * <ol>
     *     <li>Arrange: Create a new SunsetSensor object with the provided catalogue, SUNSET_NAME, and value factory.</li>
     *     <li>Act: Call the getReading() method of the SunsetSensor object.</li>
     *     <li>Assert: Verify that the result is null.</li>
     * </ol>
     * </p>
     */

    @Test
    void invalidReading() {

        // Arrange
        SunsetSensor sensor = new SunsetSensor(catalogue, SUNSET_NAME, valueFactoryDouble);

        // Act
        String result = sensor.getReading();

        // Assert
        assertNull(result);
    }

    /**
     * Test case to verify the functionality of the calculateSunset() method in the SunsetSensor class for the city of Porto.
     *
     * <p><b>Test:</b> testCalculateSunsetForPorto</p>
     * <p><b>Purpose:</b> To ensure that the calculateSunset() method returns the expected sunset time for the city of Porto on a specific date.</p>
     * <p><b>Steps:</b>
     * <ol>
     *     <li>Arrange: Set up test parameters including a test date, latitude, and longitude for the city of Porto.</li>
     *     <li>Arrange: Create a new SunsetSensor object with the provided catalogue, SUNSET_NAME, and value factory.</li>
     *     <li>Act: Call the calculateSunset() method of the SunsetSensor object with the test parameters.</li>
     *     <li>Assert: Verify that the calculated sunset time is not null.</li>
     *     <li>Assert: Verify that the calculated sunset time matches the expected sunset time for the city of Porto on the specific date.</li>
     * </ol>
     * </p>
     */

    @Test
    void calculateSunsetForPorto() {

        // Arrange
        LocalDate testDate = LocalDate.of(2024, 2, 29);
        double latitude = 41.1579;
        double longitude = - 8.6291;
        SunsetSensor calculator = new SunsetSensor(catalogue, SUNSET_NAME, valueFactoryDouble);

        // Act
        LocalTime sunsetTime = calculator.calculateSunset(testDate, latitude, longitude);

        // Assert
        assertNotNull(sunsetTime);
        assertEquals("18:24:53", sunsetTime.toString());
    }

    /**
     * Test case to verify the functionality of the calculateSunset() method in the SunsetSensor class for the city of Porto on a different date.
     *
     * <p><b>Test:</b> testCalculateSunsetForDifferentDateForPorto</p>
     * <p><b>Purpose:</b> To ensure that the calculateSunset() method returns the expected sunset time for the city of Porto on a different date.</p>
     * <p><b>Steps:</b>
     * <ol>
     *     <li>Arrange: Set up test parameters including a different test date (June 21st), latitude, and longitude for the city of Porto.</li>
     *     <li>Arrange: Create a new SunsetSensor object with the provided catalogue, SUNSET_NAME, and value factory.</li>
     *     <li>Act: Call the calculateSunset() method of the SunsetSensor object with the test parameters.</li>
     *     <li>Assert: Verify that the calculated sunset time is not null.</li>
     *     <li>Assert: Verify that the calculated sunset time matches the expected sunset time for the city of Porto on the different date.</li>
     * </ol>
     * </p>
     */

    @Test
    void calculateSunsetForDifferentDateForPorto() {

        // Arrange
        LocalDate testDate = LocalDate.of(2024, 6, 21);
        double latitude = 41.1579;
        double longitude = - 8.6291;
        SunsetSensor calculator = new SunsetSensor(catalogue, SUNSET_NAME, valueFactoryDouble);

        // Act
        LocalTime sunsetTime = calculator.calculateSunset(testDate, latitude, longitude);

        // Assert
        assertNotNull(sunsetTime);
        assertEquals("21:10:55", sunsetTime.toString());
    }

}
