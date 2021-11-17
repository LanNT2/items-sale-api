package rikkeisoft.com.itemsale.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static String instantToString(Instant instantDate, String format) {
        LocalDateTime date = LocalDateTime.ofInstant(instantDate, ZoneOffset.UTC).plusHours(7);
        String formattedDate = DateTimeFormatter.ofPattern(format).format(date);
        return formattedDate;
    }
}
