package org.home.polukeev.g_model;

/**
 * Created by onodee on 15.04.2016.
 */
public interface GameData {

    public boolean isCursor(int y, int x);

    public boolean isRigged(int y, int x);

    public boolean isOpen(int y, int x);

    public boolean isMarked(int y, int x);

    public int getTimeCount();

    public int getOpenCellStatus(int y, int x);

    public int getMarkedMineCount();
}
