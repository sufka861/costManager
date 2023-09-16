package il.ac.shenkar.java.costmanager.domain.util;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The `DateLabelFormatter` class is used to format and parse date values for a JFormattedTextField.
 */
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final String pattern = "yyyy-MM-dd"; // Date pattern to be used for formatting and parsing
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);

    /**
     * Parses a string into a Calendar object.
     *
     * @param text The string representation of a date.
     * @return A Calendar object representing the parsed date.
     * @throws ParseException If there's an issue with parsing the date.
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormatter.parse(text));
        return calendar;
    }

    /**
     * Formats a Calendar object into a string.
     *
     * @param value The Calendar object representing a date.
     * @return The formatted string representation of the date.
     */
    @Override
    public String valueToString(Object value) {
        if (value instanceof Calendar) {
            return dateFormatter.format(((Calendar) value).getTime());
        }
        return "";
    }
}
