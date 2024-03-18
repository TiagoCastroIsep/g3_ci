package SmartHome.dto;

import SmartHome.domain.house.GPS;
import SmartHome.domain.house.Location;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Facilitates the conversion between {@link Location} domain objects and their corresponding
 * {@link LocationDTO} data transfer objects. This class provides methods for both single
 * object and batch conversions, supporting encapsulation of location details for transfer or
 * presentation purposes.
 */
public class LocationMapper {

    /**
     * Converts a single {@link Location} domain object to a {@link LocationDTO}.
     * If the provided location is {@code null}, returns {@code null}.
     *
     * @param location The {@link Location} object to be converted.
     * @return A {@link LocationDTO} representing the provided location or {@code null} if the input is {@code null}.
     */
    public LocationDTO domain2DTO(Location location) {
        if (location == null) return null;
        GPS gps = location.getGps();
        return new LocationDTO(location,gps);
    }

    /**
     * Converts a map of {@link Location} to {@link GPS} pairs into a map where each key is a
     * {@link LocationDTO} and its corresponding value is the original {@link Location} domain object.
     * This method is useful for when a batch of locations needs to be converted, preserving the
     * association between the DTO representation and the domain model.
     *
     * @param locations A map with {@link Location} as keys and {@link GPS} as values to be converted.
     * @return A map where keys are {@link LocationDTO} objects and values are the original {@link Location} objects.
     */
    public static Map<LocationDTO, Location> domain2DTO(Map<Location, GPS> locations) {
        Map<LocationDTO, Location> locationsDTOAndLocations = new LinkedHashMap<>();

        locations.forEach((location, gps) -> {
            LocationDTO locationDTO = new LocationDTO(location, gps);
            locationsDTOAndLocations.put(locationDTO, location);
        });

        return locationsDTOAndLocations;
    }
}