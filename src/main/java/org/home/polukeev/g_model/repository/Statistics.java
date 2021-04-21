package org.home.polukeev.g_model.repository;

import org.home.polukeev.CommonConstants;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by onodee on 18.02.2016.
 * Класс хранящий статистику игры
 */
public class Statistics implements Serializable {
    private static final long serialVersionUID = 2037869285175957858L;
    private ArrayList<StatSession> statistics10 = new ArrayList<StatSession>();
    private ArrayList<StatSession> statistics16 = new ArrayList<StatSession>();
    private ArrayList<StatSession> statistics30 = new ArrayList<StatSession>();

    public void setNewRecord(StatSession statSession) {
        if (statSession.getCol() == 10)
            setNewRecord(statistics10, statSession);
        if (statSession.getCol() == 16)
            setNewRecord(statistics16, statSession);
        if (statSession.getCol() == 30)
            setNewRecord(statistics30, statSession);
    }

    private void setNewRecord(ArrayList<StatSession> statList, StatSession statSession) {
        if (statList.size() == CommonConstants.STAT_TOP_COUNT) {
            putInList(statList, statSession);
        } else {
            statList.add(statSession);
            sort(statList);
        }
    }

    private void sort(ArrayList<StatSession> statList) {
        int N = statList.size();
        StatSession tempInfo;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (statList.get(j).getTimer() < statList.get(min).getTimer()) min = j;
            tempInfo = statList.get(i);
            statList.set(i, statList.get(min));
            statList.set(min, tempInfo);
        }
    }

    private void putInList(ArrayList<StatSession> statList, StatSession statSession) {
        int min;
        min = statSession.getTimer();
        for (int i = statList.size() - 1; i >= 0; i--) {
            if (statList.get(i).getTimer() < min) {
                statList.add(i + 1, statSession);
                statList.remove(CommonConstants.STAT_TOP_COUNT);
                break;
            }
        }
    }

    public ArrayList<StatSession> getStatistics10() {
        return statistics10;
    }

    public ArrayList<StatSession> getStatistics16() {
        return statistics16;
    }

    public ArrayList<StatSession> getStatistics30() {
        return statistics30;
    }
}
