/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs;

import java.util.*;

/**
 *
 * @author George Hardigg
 */
public class CalendarUtilities {
    private static String applyFFormatting(String s){
        return s.replace(':', '.').replace('/', '-');
    }

    public static String formatDay(){
        return formatDay(Calendar.getInstance());
    }

    public static String formatDay(Calendar day){
        String dow = "";

        switch(day.get(Calendar.DAY_OF_WEEK)){
            case Calendar.MONDAY:
                dow = "Mon";
                break;
            case Calendar.TUESDAY:
                dow = "Tue";
                break;
            case Calendar.WEDNESDAY:
                dow = "Wed";
                break;
            case Calendar.THURSDAY:
                dow = "Thu";
                break;
            case Calendar.FRIDAY:
                dow = "Fri";
                break;
            case Calendar.SATURDAY:
                dow = "Sat";
                break;
            case Calendar.SUNDAY:
                dow = "Sun";
        }

        return dow;
    }
    
    public static String getFFYearAndDate(){
        return getFFYearAndDate(Calendar.getInstance());
    }

    public static String getFFYearAndDate(Calendar calendar){
        return applyFFormatting(getYearAndDate(calendar));
    }

    private static String getYearAndDate(Calendar calendar){
        return calendar.get(Calendar.YEAR) + "/" +
                formatDate(calendar);
    }

    public static String getFFDateAndTime(){
        return getFFDateAndTime(Calendar.getInstance());
    }

    private static String getFFDateAndTime(Calendar calendar){
        return applyFFormatting(getDateAndTime(calendar));
    }

    private static String getDateAndTime(Calendar calendar){
        return getYearAndDate(calendar) + " " +
                formatTime(calendar);
    }

    public static String formatDate(){
        return formatDate(Calendar.getInstance());
    }

    public static String formatDate(Calendar date){
        return "" + (date.get(Calendar.MONTH) + 1) + '/' + date.get(Calendar.DATE);
    }

    public static String formatTime(){
        return formatTime(Calendar.getInstance());
    }

    public static String formatTime(Calendar time){
        return "" + (time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR))
            + ':' +
            (time.get(Calendar.MINUTE) < 10 ? "0" : "") +
            time.get(Calendar.MINUTE) +
            (time.get(Calendar.AM_PM) == Calendar.AM ? " AM" : " PM");
    }
}
