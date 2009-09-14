/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs;
import java.util.*;
import java.io.*;

/**
 *
 * @author George Hardigg
 */
public class DailyLogManager {
    private static File getFile(Calendar calendar){
        return new File("Log\\" + CalendarUtilities.getYearAndDate(calendar));
    }

    public static void saveFile(DailyLog daily_log, Calendar calendar){
        FileManager.saveFile(FormGeneratorApp.login, getFile(calendar), daily_log);
    }

    public static DailyLog loadFile(Calendar calendar){
        DailyLog daily_log = null;
        if((daily_log = (DailyLog)FileManager.loadFile(FormGeneratorApp.login, getFile(calendar), DailyLog.class)) == null){
            daily_log = new DailyLog();
            saveFile(daily_log, calendar);
        }

        return daily_log;
    }
}
