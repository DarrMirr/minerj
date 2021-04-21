package org.home.polukeev.view;

import org.home.polukeev.CommonConstants;
import org.home.polukeev.model.MinerModel;
import org.home.polukeev.model.Model;
import org.home.polukeev.model.ModelEvent;
import org.home.polukeev.view.enums.MenuItemText;
import org.home.polukeev.view.gamePanel.CellFieldGamePanel;
import org.home.polukeev.view.gamePanel.TimerGamePanel;
import org.home.polukeev.view.window.AboutWindow;
import org.home.polukeev.view.window.OptionWindow;
import org.home.polukeev.view.window.StatisticsWindow;
import org.home.polukeev.view.window.SummaryWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by onodee on 15.02.2016.
 * Реализация View используя Swing
 */
public class SwingView extends JFrame implements View {
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private AboutWindow aboutWindow = new AboutWindow(this);
    private OptionWindow optionWindow = new OptionWindow(this);
    private SummaryWindow summaryWindow = new SummaryWindow(this);
    private StatisticsWindow statisticsWindow;
    private CellFieldGamePanel cellFieldGamePanel;
    private TimerGamePanel timerGamePanel;
    private JMenuBar jMenuBar = new JMenuBar();
    private MinerModel model;
    private ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("logo.png"));

    public SwingView(final Model model) {
        super(CommonConstants.PROGRAM_NAME);
        if (model instanceof MinerModel) this.model = (MinerModel) model;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(icon.getImage());
        model.addModelListener(this);
        addWindowListener(new SwingWindowsListener());

        statisticsWindow = new StatisticsWindow(this);

        initMenuBar();

        timerGamePanel = new TimerGamePanel(model);
        cellFieldGamePanel = new CellFieldGamePanel(model);

        add(timerGamePanel, BorderLayout.NORTH);
        add(cellFieldGamePanel, BorderLayout.CENTER);

        changeResolution();
    }

    private void initMenuBar() {
        jMenuBar.add(getNewMenu(CommonConstants.MENU_BAR_FILE, KeyEvent.VK_F));
        jMenuBar.add(getNewMenu(CommonConstants.MENU_BAR_HELP, KeyEvent.VK_H));

        String menuText;
        int menuKeyEvent, menuCount;
        for (MenuItemText menuItemText : MenuItemText.values()) {
            menuText = menuItemText.getText();
            menuKeyEvent = menuItemText.getKeyEvent();
            menuCount = menuItemText.getMenuCount();
            addNemMenuItem(menuText, menuKeyEvent, jMenuBar.getMenu(menuCount));
        }

        setJMenuBar(jMenuBar);
    }

    private JMenu getNewMenu(String name, int mnemonic) {
        JMenu jMenu = new JMenu(name);
        jMenu.setMnemonic(mnemonic);
        jMenu.setFont(CommonConstants.FONT_17);
        jMenuBar.add(jMenu);
        return jMenu;
    }

    private void addNemMenuItem(String name, int mnemonic, JMenu menu) {
        JMenuItem jMenuItem = new JMenuItem(name, mnemonic);
        jMenuItem.setFont(CommonConstants.FONT_17);
        jMenuItem.addActionListener(new SwingViewListener());
        menu.add(jMenuItem);
    }

    private void changeResolution(){
        if (model.getTimeCount() == 0){
            int width = cellFieldGamePanel.getWidth() + 10;
            int height = cellFieldGamePanel.getHeight() + 60 + 80;
            setSize(width, height);
            setLocation((SCREEN_SIZE.width - getWidth()) / 2, (SCREEN_SIZE.height - getHeight()) / 2);
        }
    }

    @Override
    public void modelChanged(ModelEvent event) {
        Object source = event.getSource();
        if (source instanceof MinerModel) {
            model = (MinerModel) source;
            cellFieldGamePanel.repaint();
            timerGamePanel.repaint();
            statisticsWindow.modelChanged();
            changeResolution();
            if (model.isEndGame() == 1) summaryWindow.initFrame(true);
            if (model.isEndGame() == 0) summaryWindow.initFrame(false);
        }
    }

    @Override
    public Model getModel() {
        return model;
    }

    private class SwingWindowsListener extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            model.closeApp();
        }
    }

    private class SwingViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (MenuItemText menuItemText : MenuItemText.values())
                if (e.getActionCommand().equals(menuItemText.getText())) {
                    switch (menuItemText.ordinal()){
                        case 0: {
                            model.setNewGame();
                            model.reload();
                            break;
                        }
                        case 1: {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    statisticsWindow.setVisible(true);
                                }
                            });
                            break;
                        }
                        case 2: {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    optionWindow.setVisible(true);
                                }
                            });
                            break;
                        }
                        case 3: {
                            model.closeApp();
                            break;
                        }
                        case 4: {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    aboutWindow.setVisible(true);
                                }
                            });
                            break;
                        }
                    }
                }
        }
    }
}
