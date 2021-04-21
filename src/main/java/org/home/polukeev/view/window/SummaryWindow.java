package org.home.polukeev.view.window;

import org.home.polukeev.CommonConstants;
import org.home.polukeev.model.MinerModel;
import org.home.polukeev.view.SwingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by onodee on 12.02.2016.
 * Класс окна результатов игры
 */
public class SummaryWindow extends JDialog {
    private SwingView owner;
    private MinerModel model;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstants.SUMMARY_DATE_FORMAT);
    private JPanel summaryPanel = new JPanel(new GridBagLayout());
    private ArrayList<JLabel> labelsList = new ArrayList<JLabel>();
    private JButton buttonNewGame = new JButton(CommonConstants.BUTTON_PLAY_AGAIN);
    private JButton buttonExit = new JButton(CommonConstants.BUTTON_EXIT);



    public SummaryWindow(final SwingView owner){
        super(owner, true);
        this.owner = owner;
        setSize(320, 220);
        setResizable(false);

        add(summaryPanel, BorderLayout.CENTER);

        for(int i = 0; i < 4; i++){
            if (i == 0)addNewLabel(CommonConstants.FONT_32, i, 2, GridBagConstraints.CENTER);
            else addNewLabel(CommonConstants.FONT_17, i, 1, GridBagConstraints.WEST);
        }

        initButton();
    }

    public void initFrame(boolean isWinner){
        if (owner.getModel() instanceof MinerModel) {
            model = (MinerModel) owner.getModel();
            Integer timer = model.getTimeCount();
            Integer mineCount = model.getMineCount();
            JLabel summaryLabel = labelsList.get(0);
            JLabel timeLabel = labelsList.get(1);
            JLabel dateLabel = labelsList.get(2);
            JLabel mineLabel = labelsList.get(3);
            if (isWinner) {
                setTitle(CommonConstants.SUMMARY_WINNER);
                summaryLabel.setText(CommonConstants.SUMMARY_WINNER);
            }
            else {
                setTitle(CommonConstants.SUMMARY_LOSER);
                summaryLabel.setText(CommonConstants.SUMMARY_LOSER);
            }
            timeLabel.setText(CommonConstants.SUMMARY_TIME.concat(" ").concat(timer.toString()).concat(" ").concat(CommonConstants.SUMMARY_SEC));
            dateLabel.setText(CommonConstants.SUMMARY_DATE.concat(dateFormat.format(new Date())));
            mineLabel.setText(CommonConstants.SUMMARY_MINE_COUNT.concat(mineCount.toString()));
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setVisible(true);
                }
            });
        }
    }

    private void addNewLabel(Font font, int gridy, int weightx, int align){
        JLabel jLabel = new JLabel();
        jLabel.setFont(font);
        labelsList.add(jLabel);
        summaryPanel.add(jLabel, new GridBagConstraints(0, gridy, weightx, 1, 1, 0, align, GridBagConstraints.NONE, new Insets(0, 10, 0, 5), 0, 0));
    }

    private void initButton(){
        buttonNewGame.setFont(CommonConstants.FONT_17);
        buttonNewGame.setPreferredSize(CommonConstants.BUTTON_SIZE_SUMMARY);
        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setNewGame();
                model.reload();
                dispose();
            }
        });
        buttonExit.setFont(CommonConstants.FONT_17);
        buttonExit.setPreferredSize(CommonConstants.BUTTON_SIZE_SUMMARY);
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.closeApp();
            }
        });
        summaryPanel.add(buttonNewGame, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 10, 10), 0, 0));
        summaryPanel.add(buttonExit, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 10, 10, 10), 0, 0));
    }

    @Override
    public void setVisible(boolean b) {
        setLocationRelativeTo(owner);
        super.setVisible(b);
    }

}
