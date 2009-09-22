/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs.log;
import cccs.utility.CalendarUtilities;
import cccs.utility.FileManager;
import cccs.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author George Hardigg
 */
public class TaskLogManager {
    private static File getFile(Calendar calendar){
        return new File("Log\\" + CalendarUtilities.getFFYearAndDate(calendar));
    }

    public static void saveFile(TaskLog daily_log, Calendar calendar){
        FileManager.saveFile(FormGeneratorApp.login, getFile(calendar), daily_log);
    }

    public static TaskLog loadFile(Calendar calendar){
        TaskLog daily_log = null;
        if((daily_log = (TaskLog)FileManager.loadFile(FormGeneratorApp.login, getFile(calendar), TaskLog.class)) == null){
            daily_log = new TaskLog();
            saveFile(daily_log, calendar);
        }

        return daily_log;
    }
}
