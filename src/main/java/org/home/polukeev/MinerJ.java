package org.home.polukeev;

import org.apache.log4j.xml.DOMConfigurator;
import org.home.polukeev.model.MinerModel;
import org.home.polukeev.model.Repository.Options;
import org.home.polukeev.view.SwingView;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by onodee on 06.02.2016.
 * Главный класс программы
 */
public class MinerJ {
    private SwingView swingView;
    private Locale currentLocal;
    public static ResourceBundle messages;

    public static void main(String args[]) {
        MinerJ minerJ = new MinerJ();
        minerJ.initLogging();
        minerJ.setLocal("ru");
        minerJ.startSwingView();
    }


    private void initLogging() {
        try {
            DOMConfigurator.configure(ClassLoader.getSystemResource("log4j.xml"));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void setLocal(String locale) {
        currentLocal = new Locale(locale);
        messages = ResourceBundle.getBundle("local", currentLocal);
    }

    private void startSwingView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingView = new SwingView(new MinerModel(new Options()));
                swingView.setVisible(true);
            }
        });
    }
}

