package org.home.polukeev.view.window;

import org.home.polukeev.CommonConstants;
import org.home.polukeev.model.MinerModel;
import org.home.polukeev.model.Repository.StatSession;
import org.home.polukeev.view.SwingView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by onodee on 19.02.2016.
 * Класс отрисовки окна статистики
 */
public class StatisticsWindow extends JDialog {
    private SwingView owner;
    private MinerModel model;
    private JButton okButton = new JButton(CommonConstants.BUTTON_OK);
    private JTabbedPane tabStat = new JTabbedPane();
    private ArrayList<JLabel> labels_Newbie = new ArrayList<JLabel>();
    private ArrayList<JLabel> labels_Middle = new ArrayList<JLabel>();
    private ArrayList<JLabel> labels_Expert = new ArrayList<JLabel>();
    private JPanel panel_statNewbie = new JPanel(new GridLayout(CommonConstants.STAT_TOP_COUNT, 1));
    private JPanel panel_statMiddle = new JPanel(new GridLayout(CommonConstants.STAT_TOP_COUNT, 1));
    private JPanel panel_statExpert = new JPanel(new GridLayout(CommonConstants.STAT_TOP_COUNT, 1));

    public StatisticsWindow(SwingView owner) {
        super(owner, CommonConstants.WINDOW_STAT, true);
        this.owner = owner;
        setSize(300, 400);
        setResizable(false);
        setMinimumSize(getSize());
        if (owner.getModel() instanceof MinerModel) model = (MinerModel)owner.getModel();

        panel_statNewbie.setBorder(new EmptyBorder(0, 10, 0, 10));
        panel_statMiddle.setBorder(new EmptyBorder(0, 10, 0, 10));
        panel_statExpert.setBorder(new EmptyBorder(0, 10, 0, 10));

        addStatToPanel(labels_Newbie, panel_statNewbie);
        addStatToPanel(labels_Middle, panel_statMiddle);
        addStatToPanel(labels_Expert, panel_statExpert);

        modelChanged();

        tabStat.addTab(CommonConstants.STAT_NEWBIE, panel_statNewbie);
        tabStat.addTab(CommonConstants.STAT_MIDDLE, panel_statMiddle);
        tabStat.addTab(CommonConstants.STAT_EXPERT, panel_statExpert);

        tabStat.setFont(CommonConstants.FONT_17);

        add(tabStat, BorderLayout.CENTER);

        JPanel jPanel = new JPanel();
        okButton.setFont(CommonConstants.FONT_17);
        okButton.setPreferredSize(CommonConstants.BUTTON_SIZE);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jPanel.add(okButton, BorderLayout.SOUTH);

        add(jPanel, BorderLayout.SOUTH);

        pack();
    }

    private void addStatToPanel(ArrayList<JLabel> labelsList, JPanel panelList){
        JLabel jLabel;
        for (int i = 0; i < CommonConstants.STAT_TOP_COUNT; i++){
            jLabel = new JLabel();
            jLabel.setFont(CommonConstants.FONT_17);
            labelsList.add(jLabel);
            panelList.add(jLabel);
        }
    }

    private void update(ArrayList<StatSession> statList, ArrayList<JLabel> labelsList){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < statList.size(); i++){
            stringBuilder.append(i + 1).append(". ").append(statList.get(i).getTimer()).append(" ").append(CommonConstants.SUMMARY_SEC).append(" ").append(statList.get(i).getDate());
            labelsList.get(i).setText(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
    }

    public void modelChanged(){
        update(model.getStatistics().getStatistics10(), labels_Newbie);
        update(model.getStatistics().getStatistics16(), labels_Middle);
        update(model.getStatistics().getStatistics30(), labels_Expert);
    }

    @Override
    public void setVisible(boolean b) {
        setLocationRelativeTo(owner);
        super.setVisible(b);
    }
}
