package org.houxg.monkeyhey.util.logger;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by houxg on 2015/1/3.
 */
public class FileLogger implements LogNode {

    private final static String LOG_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/Logs";


    @Override
    public void log(int priority, String tag, String content) {
        String log = Log.getStandarLog(priority, tag, content);
        StringBuilder builder = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        builder.append(getHHmmss(calendar))
                .append("\t")
                .append(log);
        String fileName = getyyyyMMdd(calendar) + "_log.txt";
        writeToFile(LOG_DIR, fileName, builder.toString(), true);
    }

    private static String getHHmmss(Calendar calendar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":");
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if (minute < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(minute).append(":");
        if (second < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(second);
        return stringBuilder.toString();
    }

    private static String getyyyyMMdd(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.YEAR)).append("-");
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month).append("-");
        if (day < 10) {
            builder.append("0");
        }
        builder.append(day);
        return builder.toString();
    }

    private static boolean writeToFile(String path, String fileName, String content, boolean isAppend) {
        File dir = new File(path);
        File file = new File(path + "/" + fileName);
        if (!dir.isDirectory()) {
            if (!dir.mkdirs()) {
                return false;
            }
        }

        if (!file.isFile()) {
            try {
                if (!file.createNewFile()) {
                    return false;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(file, isAppend);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
