package ria.com.riatest.util;

import java.text.DateFormat;
import java.util.Locale;

public class DateConverter {

    public static String convertDate(long date) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        return dateFormat.format(date * 1000);
    }
}
