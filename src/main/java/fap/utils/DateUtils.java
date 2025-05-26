package src.main.java.fap.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Utility class for formatting dates.
 * It provides a static method to convert a LocalDate into a string
 */
public class DateUtils {

    /*
     * Formats a LocalDate object as a string in the format "yyyy-MM-dd"
     * For example, if the date is January 5, 2025, it will return "2025-01-05"
     * The parameter is the LocalDate object to format and it returns the formatted date as a string
     */
    public static String format(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
