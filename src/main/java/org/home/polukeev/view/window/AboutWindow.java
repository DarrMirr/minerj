package org.home.polukeev.view.window;

import org.home.polukeev.CommonConstants;
import org.home.polukeev.view.SwingView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by onodee on 13.02.2016.
 * Класс окна "О программе"
 */
public class AboutWindow extends JDialog{
    private SwingView owner;
    private JButton buttonOk = new JButton(CommonConstants.BUTTON_OK);
    private JPanel mainPanel = new JPanel();

    public AboutWindow(SwingView owner){
        super(owner, CommonConstants.WINDOW_ABOUT, true);
        this.owner = owner;
        setSize(300, 180);
        setResizable(false);

        mainPanel.setBorder(new EmptyBorder(6, 12, 6, 12));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        createNewLabel(CommonConstants.PROGRAM_NAME, CommonConstants.FONT_32);
        createNewLabel(CommonConstants.PROGRAM_AUTHOR, CommonConstants.FONT_17);
        createNewLabel(CommonConstants.PROGRAM_DATE, CommonConstants.FONT_17);

        buttonOk.setFont(CommonConstants.FONT_17);
        buttonOk.setPreferredSize(CommonConstants.BUTTON_SIZE);
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel jPanel = new JPanel();
        jPanel.add(buttonOk);
        add(jPanel, BorderLayout.SOUTH);
    }

    private void createNewLabel(String name, Font font){
        JLabel jLabel = new JLabel(name);
        jLabel.setFont(font);
        mainPanel.add(jLabel);
    }

    @Override
    public void setVisible(boolean b) {
        setLocationRelativeTo(owner);
        super.setVisible(b);
    }
}
