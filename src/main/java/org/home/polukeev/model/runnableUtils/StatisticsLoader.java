package org.home.polukeev.model.runnableUtils;


import org.apache.log4j.Logger;
import org.home.polukeev.model.Repository.Statistics;

import java.io.*;

/**
 * Created by onodee on 19.02.2016.
 * Класс загрузки статистики игры с диска
 */
public class StatisticsLoader implements Runnable{
    private static final Logger LOGER = Logger.getLogger(StatisticsLoader.class);
    private Statistics statistics;
    private ObjectInputStream inputStream;

    @Override
    public void run() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream("MinerJ.stat"));
            statistics = (Statistics)inputStream.readObject();
        } catch (FileNotFoundException e) {
            writeLog(e.getMessage());
        } catch (IOException e) {
            writeLog(e.getMessage());
        } catch (ClassNotFoundException e) {
            writeLog(e.getMessage());
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                writeLog(e.getMessage());
            }
        }
    }

    private void writeLog(String text){
        LOGER.error(text);
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
