package org.home.polukeev.model.runnableUtils;

import org.home.polukeev.model.MinerModel;

/**
 * Created by onodee on 17.02.2016.
 * Класс отчитывающий секунды
 */
public class Timer implements Runnable {
    private boolean isRun;
    private MinerModel model;

    public Timer(MinerModel model, boolean isRun) {
        this.model = model;
        this.isRun = isRun;
    }


    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean isRun) {
        this.isRun = isRun;
    }

    @Override
    public void run() {
        while (isRun){
            try {
                model.setTimeCount();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
