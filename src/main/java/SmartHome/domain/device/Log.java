package SmartHome.domain.device;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Retrieves the formatted log entry as a string. The log entry includes the timestamp
 * and message, formatted to provide a clear and concise record of events. The timestamp
 * is formatted according to the "dd-MM-yyyy HH:mm:ss" pattern.
 *
 * @return A string representing the formatted log entry, combining the timestamp and
 * message separated by " - ", followed by a newline character.
 */
public class Log {
   private final String _time;
   private final String _message;

   /**
    * Constructs a log entry with the specified timestamp and message.
    *
    * @param time    The timestamp of the log entry.
    * @param message The message to be recorded in the log entry.
    * @throws IllegalArgumentException If the message is null or empty, or if the time is null.
    */
   protected Log(LocalDateTime time, String message) {
      if (message == null || message.trim().isEmpty())
         throw new IllegalArgumentException("Message cannot be null or empty");
      if (time == null)
         throw new IllegalArgumentException("Time cannot be null");

      DateTimeFormatter logTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
      this._time = time.format(logTime);
      this._message = message;
   }

   /**
    * Retrieves the formatted log entry as a string.
    *
    * @return The formatted log entry, including timestamp and message.
    */
   public String getLog() {
      return _time + " - " + _message + "\n";
   }
}
