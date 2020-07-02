package id.sekar.mvvmroom.utils;

import android.text.format.DateFormat;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String getDayMonth(Date date) {

        String day = (String) DateFormat.format("dd", date);
        String monthString = (String) DateFormat.format("MMM", date);
        return day + monthString;
    }
}
