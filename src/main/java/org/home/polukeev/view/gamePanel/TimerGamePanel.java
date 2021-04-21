package org.home.polukeev.view.gamePanel;

import org.home.polukeev.CommonConstants;
import org.home.polukeev.model.MinerModel;
import org.home.polukeev.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by onodee on 10.02.2016.
 * Панель отображения таймера и меток мин
 */
public class TimerGamePanel extends JPanel{
    private MinerModel model;
    private BufferedImage buffer = null;
    private Graphics2D g2d;
    private StringBuilder paintString;

    public TimerGamePanel(Model model) {
        if (model instanceof MinerModel) this.model = (MinerModel) model;
        add(Box.createVerticalStrut(60));
    }

    private void rebuildBuffer() {
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        g2d = buffer.createGraphics();
        paintField();
    }

    protected void paintField() {
        paintTimer();
        paintMarkMineCount();
        g2d.setColor(Color.BLACK);
        g2d.setFont(CommonConstants.FONT_22);
        int x = (getWidth() - g2d.getFontMetrics().stringWidth(paintString.toString())) / 2;
        int y = (getHeight() - g2d.getFontMetrics().getHeight()) / 2 + 20;
        g2d.drawString(paintString.toString(), x, y);
    }

    protected void paintTimer() {
        paintString = new StringBuilder(CommonConstants.GAME_PANEL_TIMER);
        paintString.append(" ").append(model.getTimeCount()).append(" ");
    }

    protected void paintMarkMineCount() {
        paintString.append(CommonConstants.GAME_PANEL_MINES);
        paintString.append(" ").append(model.getMarkedMineCount());
    }

    @Override
    protected void paintComponent(Graphics g) {
        rebuildBuffer();
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);
    }
}
