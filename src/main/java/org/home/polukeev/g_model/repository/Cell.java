package org.home.polukeev.g_model.repository;

/**
 * Created by onodee on 06.02.2016.
 * Класс ячейки игрового поля
 */
public class Cell {
    private boolean isOpen = false;
    private boolean isMarked = false;
    private boolean isRigged;

    private int openCellStatus;

    public Cell() {}

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public boolean isRigged() {
        return isRigged;
    }

    public void setRigged(boolean isRigged) {
        this.isRigged = isRigged;
    }

    public int getOpenCellStatus() {
        return openCellStatus;
    }

    public void setOpenCellStatus(int openCellStatus) {
        this.openCellStatus = openCellStatus;
    }

}
