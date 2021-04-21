package org.home.polukeev.g_model.runnableUtils;

import org.apache.log4j.Logger;
import org.home.polukeev.g_model.repository.Statistics;

import java.io.*;

/**
 * Created by onodee on 19.02.2016.
 * Класс записи статистики игры на диск
 */
public class StatisticsSaver implements Runnable {
    private static final Logger LOGER = Logger.getLogger(StatisticsSaver.class);

    private Statistics statistics;
    private ObjectOutputStream outputStream;
    private FileOutputStream fos;
    private File file = new File("MinerJ.stat");

    public StatisticsSaver(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public void run() {
        try {
            fos = new FileOutputStream(file);
            outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(statistics);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            writeLog(e.getMessage());
        } catch (IOException e) {
            writeLog(e.getMessage());
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                writeLog(e.getMessage());
            }
        }
    }

    private void writeLog(String text) {
        LOGER.error(text);
    }
}
