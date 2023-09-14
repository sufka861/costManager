package il.ac.shenkar.java.costmanager.domain.util;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final String pattern = "yyyy-MM-dd";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormatter.parse(text));
        return calendar;
    }

    @Override
    public String valueToString(Object value) {
        if (value instanceof Calendar) {
            return dateFormatter.format(((Calendar) value).getTime());
        }
        return "";
    }
}
