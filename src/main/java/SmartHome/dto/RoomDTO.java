package SmartHome.dto;

import SmartHome.domain.room.Dimensions;
import SmartHome.domain.room.Room;

/**
 * A data transfer object (DTO) representing essential details of a {@link Room} in a simplified form.
 * This DTO includes the room's name, its floor within the house, and its dimensions (length, width, and height).
 * It is designed for use in data transfer operations where full domain model details are not necessary or desirable.
 */
public class RoomDTO {
   public final String name;
   public final String floor;
   public final double length;
   public final double width;
   public final double height;

   /**
    * Constructs a new {@code RoomDTO} instance based on a {@link Room} and its {@link Dimensions}.
    * This constructor extracts necessary information from the provided Room and Dimensions objects,
    * simplifying the representation for transfer or display purposes.
    *
    * @param room       The {@link Room} object from which to extract the name and floor information.
    * @param dimensions The {@link Dimensions} object from which to extract room size details.
    */
   public RoomDTO(Room room, Dimensions dimensions) {
      this.name = room.getName();
      this.floor = room.getFloor();
      this.length = dimensions.getLength();
      this.width = dimensions.getWidth();
      this.height = dimensions.getHeight();
   }

   /**
    * Provides a string representation of the {@code RoomDTO}, including the room's name, floor, and dimensions.
    *
    * @return A string representation of the {@code RoomDTO}.
    */
   @Override
   public String toString() {
      return "RoomDTO{" +
              "name='" + name + '\'' +
              ", floor='" + floor + '\'' +
              ", length=" + length +
              ", width=" + width +
              ", height=" + height +
              '}';
   }
}
