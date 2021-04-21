package org.home.polukeev.g_model.repository;

import java.io.Serializable;

/**
 * Created by onodee on 18.02.2016.
 * Класс статистики одной игровой сессии
 */
public class StatSession implements Serializable {
    private static final long serialVersionUID = 2037869285175957858L;

    private int col;
    private int timer;
    private String date;

    public StatSession(int col, int timer, String date) {
        this.col = col;
        this.timer = timer;
        this.date = date;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
