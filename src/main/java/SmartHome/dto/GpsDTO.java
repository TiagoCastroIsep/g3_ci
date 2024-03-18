package SmartHome.dto;
import SmartHome.domain.house.GPS;

/**
 * Represents a data transfer object (DTO) for GPS coordinates. This class encapsulates
 * latitude and longitude values for simplified handling and transfer of GPS data, such as in
 * user interfaces or API responses.
 */
public class GpsDTO {
    public final double latitude;
    public final double longitude;

    /**
     * Constructs a new GpsDTO instance based on a {@link GPS} domain object.
     * This constructor extracts the latitude and longitude from the provided GPS object,
     * enabling easy conversion of domain-specific GPS data to a simplified DTO format.
     *
     * @param gps The {@link GPS} domain object from which to extract latitude and longitude.
     */
    public GpsDTO(GPS gps) {
        this.latitude = gps.getLatitude();
        this.longitude = gps.getLongitude();
    }

    /**
     * Provides a string representation of the GpsDTO, including the latitude and longitude values.
     *
     * @return A string representation of the GpsDTO, formatted to display latitude and longitude.
     */
    @Override
    public String toString() {
        return "GpsDTO{" +
              "latitude=" + latitude +
              ", longitude=" + longitude +
              '}';
    }
}
