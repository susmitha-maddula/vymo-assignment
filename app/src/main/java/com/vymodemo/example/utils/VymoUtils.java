package com.vymodemo.example.utils;

import java.util.Calendar;
import java.util.Date;

public class VymoUtils {

  public static String getCalendarTime(Date date) {
    String message = null;
    if (date == null) {
      return "";
    }
    long SECOND = 1;
    long MINUTE = 60 * SECOND;
    long HOUR = 60 * MINUTE;
    long DAY = 24 * HOUR;
    long MONTH = 30 * DAY;
    long YEAR = 12 * MONTH;

    Calendar cal = Calendar.getInstance();

    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date);

    long delta = (cal.getTimeInMillis() - cal2.getTimeInMillis()) / 1000;
    if (delta < 0) {
      message = "In the future!";

    } else if (delta < 1 * MINUTE) {
      message = (delta == 1) ? "One second ago" : delta + " seconds ago";

    } else if (delta < 2 * MINUTE) {
      message = "a minute ago";

    } else if (delta < 45 * MINUTE) {
      message = (delta / MINUTE == 1) ? "One minute ago" : delta / MINUTE
          + " minutes ago";

    } else if (delta < 90 * MINUTE) {
      message = "an hour ago";

    } else if (delta < 24 * HOUR) {
      message = (delta / HOUR == 1) ? "One hour ago" : delta / HOUR
          + " hours ago";

    } else if (delta < 48 * HOUR) {
      message = "yesterday";

    } else if (delta < 30 * DAY) {
      message = (delta / DAY == 1) ? "One day ago" : delta / DAY
          + " days ago";

    } else if (delta < 12 * MONTH) {
      message = (delta / MONTH <= 1) ? "one month ago" : delta / MONTH
          + " months ago";

    } else {
      message = (delta / YEAR <= 1) ? "one year ago" : delta / YEAR
          + " years ago";

    }
    return message;
  }
}
