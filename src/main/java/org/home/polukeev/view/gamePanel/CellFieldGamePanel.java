package org.home.polukeev.view.gamePanel;

import org.home.polukeev.CommonConstants;
import org.home.polukeev.model.MinerModel;
import org.home.polukeev.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by onodee on 09.02.2016.
 * Игровая панель с ячейками минного поля
 */
public class CellFieldGamePanel extends JPanel{
    private static final int CELL_WIDTH = 40;
    private static final int CELL_HEIGHT = 40;
    private MinerModel model;
    private BufferedImage buffer = null;
    private Graphics2D g2d;
    private MouseAdapter mouseAdapter = new CellFieldGamePanelListener();
    private int dX;
    private int dY;

    public CellFieldGamePanel(Model model) {
        if (model instanceof MinerModel) this.model = (MinerModel)model;
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        calcCellSize();
    }

    private void rebuildBuffer(){
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        g2d = buffer.createGraphics();
        paintField();
    }

    private void calcCellSize(){
        if (model.getTimeCount() == 0){
            setSize(CELL_WIDTH * model.getCol(), CELL_HEIGHT * model.getRow());
        }
    }

    private void setNewFont(){
        g2d.setFont(CommonConstants.FONT_22);
        dX = (CELL_WIDTH - g2d.getFontMetrics().stringWidth("0")) / 2;
        dY = CELL_HEIGHT / 2 + (CELL_HEIGHT / 5);
    }

    protected void paintCloseCell(int x, int y, boolean isCursor){
        g2d.setColor(Color.lightGray);
        g2d.fillRect(x, y, CELL_WIDTH, CELL_HEIGHT);
        if (isCursor)g2d.setColor(Color.CYAN);
        else g2d.setColor(Color.blue);
        g2d.fillRect(x + 3, y + 3, CELL_WIDTH - 3, CELL_HEIGHT - 3);
    }

    protected void paintOpenCell(Integer cellStatus, int x, int y) {
        g2d.setColor(Color.lightGray);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(x, y, CELL_WIDTH, CELL_HEIGHT);
        if (cellStatus != 0){
            if (cellStatus <= 2) g2d.setColor(Color.blue);
            else g2d.setColor(Color.RED);
            setNewFont();
            g2d.drawString(cellStatus.toString(), x + dX, y + dY);
        }
    }

    protected void paintMarkCell(int x, int y){
        paintCloseCell(x, y, false);
        g2d.setColor(Color.WHITE);
        setNewFont();
        g2d.drawString("?", x + dX, y + dY);
    }

    protected void paintRiggedCell(int x, int y){
        paintOpenCell(0, x, y);
        g2d.setColor(Color.RED);
        setNewFont();
        g2d.drawString("M", x + dX, y + dY);
    }

    protected void paintField(){
        calcCellSize();
        for (int m = 0, y = 0; m < model.getRow(); y += CELL_HEIGHT, m++)
            for (int n = 0, x = 0; n < model.getCol(); x += CELL_WIDTH, n++){
                if (model.isOpen(m, n)){
                    if (model.isRigged(m, n)) paintRiggedCell(x, y);
                    else paintOpenCell(model.getOpenCellStatus(m, n), x, y);
                } else {
                    if (model.isMarked(m, n)) paintMarkCell(x, y);
                    else paintCloseCell(x, y, model.isCursor(m, n));
                }
            }

    }

    protected void paintComponent(Graphics g){
        rebuildBuffer();
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);
    }

    class CellFieldGamePanelListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) model.setOpenCell(e.getY() / CELL_HEIGHT, e.getX() / CELL_WIDTH);
            else if (e.getButton() == 3) model.setMarkCell(e.getY() / CELL_HEIGHT, e.getX() / CELL_WIDTH);
            model.reload();
        }

        @Override
        public void mouseMoved(MouseEvent e){
            model.setMove(e.getY() / CELL_HEIGHT, e.getX() / CELL_WIDTH);
            model.reload();
        }
    }
}
