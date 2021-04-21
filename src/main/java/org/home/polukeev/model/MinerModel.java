package org.home.polukeev.model;

import org.apache.log4j.Logger;
import org.home.polukeev.CommonConstants;
import org.home.polukeev.model.Repository.GameDataImpl;
import org.home.polukeev.model.Repository.Options;
import org.home.polukeev.model.Repository.StatSession;
import org.home.polukeev.model.Repository.Statistics;
import org.home.polukeev.model.runnableUtils.StatisticsLoader;
import org.home.polukeev.model.runnableUtils.StatisticsSaver;
import org.home.polukeev.model.runnableUtils.Timer;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by onodee on 15.02.2016.
 * Реализация Miner модели
 */
public class MinerModel extends AbstractModel {
    private static final Logger LOGER = Logger.getLogger(MinerModel.class);

    private GameDataImpl gameData;
    private Options options;
    private Statistics statistics;
    private Timer timer = new Timer(this, true);
    private Thread threadTimer;
    private ArrayList cascadeList;
    private int isEndGame = -1;
    private boolean isFirstTurn = true;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstants.SUMMARY_DATE_FORMAT);


    public MinerModel(Options options) {
        setNewGame(options);
        loadFileStatistics();
    }

    private void loadFileStatistics(){
        StatisticsLoader loader = new StatisticsLoader();
        Thread statLoaderThread = new Thread(loader);
        statLoaderThread.start();
        while (statLoaderThread.isAlive())
            try {
                statLoaderThread.join(1000);
            } catch (InterruptedException e) {
                LOGER.error(e.getMessage());
            }
        if (loader.getStatistics() != null)
            statistics = loader.getStatistics();
        else statistics = new Statistics();
    }

    private boolean saveFileStatistics(){
        StatisticsSaver saver = new StatisticsSaver(statistics);
        Thread statSaverThread = new Thread(saver);
        statSaverThread.start();
        while (statSaverThread.isAlive()){
            try {
                statSaverThread.join(1000);
            } catch (InterruptedException e) {
                LOGER.error(e.getMessage());
            }
        }
        return true;
    }

    private void checkGameEnd() {
        if (gameData.getCloseCellCount() == gameData.getMineCount()) endGame(true);
    }

    private void endGame(boolean isWinner) {
        stopTimer();
        if (isWinner) {
            isEndGame = 1;
            setNewStatistics();
        }
        else isEndGame = 0;
    }

    private void checkIsZero(int y, int x) {
        if (!gameData.isOpen(y, x)) {
            if (gameData.getOpenCellStatus(y, x) == 0) cascadeList.add(new int[]{y, x});
            gameData.setOpen(y, x);
        }
    }

    private void openCascadeZeroCell(int rootY, int rootX) {
        cascadeList = new ArrayList();
        int y, x;
        int[] tempInt;
        cascadeList.add(new int[]{rootY, rootX});
        for (int i = 0; i < cascadeList.size(); i++) {
            tempInt = (int[]) cascadeList.get(i);
            y = tempInt[0] + 1;
            x = tempInt[1];
            if (y < options.getRowCount()) checkIsZero(y, x);
            y = tempInt[0] - 1;
            if (y >= 0) checkIsZero(y, x);
            y = tempInt[0];
            x = tempInt[1] + 1;
            if (x < options.getColCount()) checkIsZero(y, x);
            x = tempInt[1] - 1;
            if (x >= 0) checkIsZero(y, x);
        }
    }

    private void calcCellData() {
        Random random = new Random();
        int mineCount = gameData.getMineCount();
        int m;
        int n;
        while (mineCount != 0) {
            m = random.nextInt(getRow());
            n = random.nextInt(getCol());
            if (!gameData.isRigged(m, n)) {
                gameData.setRigged(m, n);
                gameData.setOpenCellStatus(m, n, 9);
                mineCount--;
                m--;
                n--;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int mi = m + i;
                        int nj = n + j;
                        if (mi != -1 && nj != -1 && mi < getRow() && nj < getCol()) {
                            if (!gameData.isRigged(mi, nj)) {
                                gameData.setOpenCellStatus(mi, nj, gameData.getOpenCellStatus(mi, nj) + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setMove(int y, int x) {
        gameData.setCursor(y, x);
    }

    public void setOpenCell(int y, int x) {
        if (x >= 0 && x < options.getColCount() && y >= 0 && y < options.getRowCount()) {
            if (isFirstTurn) {
                startTimer();
                isFirstTurn = false;
            }
            if (!gameData.isOpen(y, x)) {
                gameData.setOpen(y, x);
                if (gameData.getOpenCellStatus(y, x) == 0) openCascadeZeroCell(y, x);
                if (gameData.isRigged(y, x)) endGame(false);
                else checkGameEnd();
            }
        }
    }

    public void setMarkCell(int y, int x) {
        if (x >= 0 && x < options.getColCount() && y >= 0 && y < options.getRowCount()) {
            if (!gameData.isOpen(y, x)) {
                if (!gameData.isMarked(y, x)) gameData.setMark(y, x, true);
                else gameData.setMark(y, x, false);
            }
        }
    }

    private void startTimer() {
        timer.setRun(true);
        threadTimer = new Thread(timer);
        threadTimer.start();
    }

    private void stopTimer() {
        if (threadTimer != null) timer.setRun(false);
    }

    public void setTimeCount() {
        gameData.setTimeCount();
        reload();
    }

    private void setNewGame(Options options) {
        stopTimer();
        this.options = options;
        isEndGame = -1;
        isFirstTurn = true;
        gameData = new GameDataImpl(options);
        calcCellData();
    }

    public void closeApp(){
        boolean isSave = false;
        while (!isSave){
            isSave = saveFileStatistics();
        }
        System.exit(0);
    }

    public void setNewGame(int row, int col, int mines) {
        setNewGame(new Options(row, col, mines));
    }

    public void setNewGame() {
        setNewGame(options);
    }

    public int getOpenCellStatus(int y, int x) {
        return gameData.getOpenCellStatus(y, x);
    }

    public int getMarkedMineCount() {
        return gameData.getMarkedMineCount();
    }

    public int getMineCount() {
        return options.getMineCount();
    }

    public int isEndGame() {
        return isEndGame;
    }

    public int getRow() {
        return options.getRowCount();
    }

    public int getCol() {
        return options.getColCount();
    }

    public boolean isOpen(int y, int x) {
        return gameData.isOpen(y, x);
    }

    public boolean isMarked(int y, int x) {
        return gameData.isMarked(y, x);
    }

    public boolean isRigged(int y, int x) {
        return gameData.isRigged(y, x);
    }

    public boolean isCursor(int y, int x) {
        return gameData.isCursor(y, x);
    }

    public int getTimeCount() {
        return gameData.getTimeCount();
    }

    public void setNewStatistics(){
        statistics.setNewRecord(new StatSession(getRow(), getTimeCount(), dateFormat.format(new Date())));
    }

    public Statistics getStatistics(){
        return statistics;
    }

    @Override
    public void reload() {

        fireModelEventChanged();
    }

    protected void fireModelEventChanged() {
        final ModelEvent modelEvent = new ModelEvent(this);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (ModelListener listener : getListeners())
                    listener.modelChanged(modelEvent);
            }
        });
    }
}
