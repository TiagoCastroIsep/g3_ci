package SmartHome.dto;

import SmartHome.domain.house.GPS;
import SmartHome.domain.house.Location;

/**
 * Represents a data transfer object (DTO) for location information. It encapsulates details about a location,
 * including street address, postal code, city, country, and GPS coordinates. This DTO is designed for transferring
 * location data between software layers or systems, facilitating the separation of concerns by not exposing
 * domain model intricacies.
 */
public class LocationDTO {
    public String _street;
    public String _doorNumber;
    public String _zip;
    public String _city;
    public String _country;
    public GpsDTO _gps;

    /**
     * Constructs a new LocationDTO based on a {@link Location} and {@link GPS} objects. This constructor
     * extracts relevant location information and initializes the DTO with simplified GPS data for easy
     * data handling and transfer.
     *
     * @param location The {@link Location} object containing detailed location information.
     * @param gps The {@link GPS} object providing geographical coordinates for the location.
     */
    public LocationDTO(Location location, GPS gps) {
        this._street = location.getStreet();
        this._doorNumber = location.getDoorNumber();
        this._zip = location.getZip();
        this._city = location.getCity();
        this._country = location.getCountry();
        this._gps = new GpsDTO(gps);
    }

    /**
     * Provides a string representation of the LocationDTO, including street address, postal code, city, country,
     * and GPS coordinates.
     *
     * @return A string representation of the LocationDTO, detailing all encapsulated location information.
     */
    @Override
    public String toString() {
        return "LocationDTO{" +
                "_street='" + _street + '\'' +
                ", _doorNumber='" + _doorNumber + '\'' +
                ", _zip='" + _zip + '\'' +
                ", _city='" + _city + '\'' +
                ", _country='" + _country + '\'' +
                ", _latitude=" + _gps.latitude +
                ", _longitude=" + _gps.longitude +
                '}';
    }
}

