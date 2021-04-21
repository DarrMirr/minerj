package org.home.polukeev.model.Repository;

/**
 * Created by onodee on 06.02.2016.
 * Класс описания опций игры
 */
public class Options {
    private final static int rowDefault = 10;
    private final static int colDefault = 10;
    private final static int minDefault = 10;
    private int rowCount;
    private int colCount;
    private int mineCount;

    public Options() {
        this(rowDefault, colDefault, minDefault);
    }

    public Options(int rowCount, int colCount, int mineCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.mineCount = mineCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public int getMineCount() {
        return mineCount;
    }
}
