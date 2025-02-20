package com.rci.wyndham.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * Utility classes for general use.
 * @author fernando.alves.
 */
public class WVRAPUtil {

    /**
     * Formats a date in the given format and time zone.
     * @param date the date
     * @param format the format
     * @param timeZone the time zone
     * @return the date as a formatted string
     */
    public static String formatDate(Date date, String format, String timeZone) {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(format);
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormatGmt.format(date);
    }

    /**
     * Checks if any of the list of given strings is empty.
     * @param values the list of string
     * @return true if yes
     */
    public static boolean anyIsEmpty(String ... values) {
        if(ArrayUtils.isNotEmpty(values)) {
            for (String value : values) {
                if(StringUtils.isEmpty(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String formatExpiryDate(String expiryDate) {
        return StringUtils.replace(expiryDate, "-", "");
    }
}

