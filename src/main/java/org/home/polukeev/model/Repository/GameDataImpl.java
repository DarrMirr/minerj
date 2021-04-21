package org.home.polukeev.model.Repository;

/**
 * Created by onodee on 06.02.2016.
 * Реализация хранения игровых данных
 */
public class GameDataImpl {
    private Cell[][] gameField;
    private int markedMine;
    private int timeCount;
    private int mineCount;
    private int closeCellCount;
    private int row;
    private int col;
    private int[] cursorCellPosition = {0, 0};

    public GameDataImpl(Options options){
        row = options.getRowCount();
        col = options.getColCount();
        mineCount = markedMine = options.getMineCount();
        timeCount = 0;
        gameField = new Cell[row][col];
        closeCellCount = row * col;
        initGameField();
    }

    private void initGameField(){
        for (int m = 0; m < row; m++){
            for (int n = 0; n < col; n++){
                gameField[m][n] = new Cell();
            }
        }
    }

    public boolean isOpen(int y, int x) {
        return gameField[y][x].isOpen();
    }

    public boolean isMarked(int y, int x) {
        return gameField[y][x].isMarked();
    }

    public boolean isRigged(int y, int x) {
        return gameField[y][x].isRigged();
    }

    public void setRigged(int y, int x){
        gameField[y][x].setRigged(true);
    }

    public int getOpenCellStatus(int y, int x) {
        return gameField[y][x].getOpenCellStatus();
    }

    public void setOpenCellStatus(int y, int x, int status){
        gameField[y][x].setOpenCellStatus(status);
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getTimeCount(){
        return timeCount;
    }

    public void setTimeCount(){
        timeCount++;
    }

    public int getMarkedMineCount() {
        return markedMine;
    }

    public int getCloseCellCount() {
        return closeCellCount;
    }

    public void setOpen(int y, int x) {
        gameField[y][x].setOpen(true);
        closeCellCount--;
    }

    public void setMark(int y, int x, boolean isMark) {
        gameField[y][x].setMarked(isMark);
        if (isMark) markedMine--;
        else markedMine++;
    }

    public void setCursor(int y, int x) {
        cursorCellPosition[0] = y;
        cursorCellPosition[1] = x;
    }

    public boolean isCursor(int y, int x) {
        if (cursorCellPosition[0] == y && cursorCellPosition[1] == x) return true;
        else return false;
    }
}
