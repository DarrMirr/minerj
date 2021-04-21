package org.home.polukeev.view.window;

import org.apache.log4j.Logger;
import org.home.polukeev.CommonConstants;
import org.home.polukeev.model.MinerModel;
import org.home.polukeev.view.SwingView;
import org.home.polukeev.view.editors.TextEditor;
import org.home.polukeev.view.enums.OptionLabel;
import org.home.polukeev.view.enums.OptionText;
import org.home.polukeev.view.validator.RegexValidator;
import org.home.polukeev.view.validator.ValidatorBase;
import org.home.polukeev.view.validator.ValidatorController;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by onodee on 12.02.2016.
 * Класс окна Опций
 */
public class OptionWindow extends JDialog implements ValidatorController {
    private static final Logger LOGGER = Logger.getLogger(OptionWindow.class);
    private SwingView owner;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton radioButton_Other;
    private TextEditor rowsTextEditor;
    private TextEditor columnsTextEditor;
    private TextEditor minesTextEditor;
    private JPanel rightPanel = new JPanel(new GridBagLayout());
    private JPanel leftPanel = new JPanel(new GridBagLayout());
    private JButton buttonOk = new JButton(CommonConstants.BUTTON_OK);
    private JButton buttonCancel = new JButton(CommonConstants.BUTTON_CANCEL);
    private Map<ValidatorBase, Boolean> validators = new HashMap();
    private boolean valid = true;

    public OptionWindow(SwingView owner) {
        super(owner, CommonConstants.WINDOW_OPTION, true);
        this.owner = owner;
        setSize(450, 350);
        setResizable(false);

        JPanel commonPanel = new JPanel(new GridBagLayout());
        commonPanel.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 0, 12), new TitledBorder(CommonConstants.OPTION_TEXT_BORDER)));
        add(commonPanel, BorderLayout.CENTER);

        commonPanel.add(leftPanel);

        initGuiComponents();
    }

    private void addNewLabel(int gridx, int gridy, String text) {
        JLabel jLabel = new JLabel(text);
        jLabel.setFont(CommonConstants.FONT_17);
        rightPanel.add(jLabel, new GridBagConstraints(gridx, gridy, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
    }

    private void addNewTextArea(int gridx, int gridy, String text) {
        JTextArea jTextArea = new JTextArea(text);
        jTextArea.setEditable(false);
        jTextArea.setBackground(new Color(0, 0, 0, 0));
        jTextArea.setHighlighter(null);
        jTextArea.setFont(CommonConstants.FONT_17);
        leftPanel.add(jTextArea, new GridBagConstraints(gridx, gridy, 1, 1, 10, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 20), 0, 0));
    }

    private void addNewRadioButton(int gridx, int gridy, int gridHeight) {
        final JRadioButton jRadioButton = new JRadioButton();
        if (buttonGroup.getButtonCount() == 0) jRadioButton.setSelected(true);
        jRadioButton.setMnemonic(buttonGroup.getButtonCount());
        buttonGroup.add(jRadioButton);
        if (buttonGroup.getButtonCount() == 4) {
            radioButton_Other = jRadioButton;
            radioButton_Other.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    setEnabledComponents(radioButton_Other.isSelected());
                }
            });
        }
        leftPanel.add(jRadioButton, new GridBagConstraints(gridx, gridy, 1, gridHeight, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 5, 5, 0), 0, 0));
    }

    private void initGuiComponents(){
        for (OptionText optionText : OptionText.values()) {
            addNewRadioButton(0, optionText.ordinal(), 1);
            addNewTextArea(1, optionText.ordinal(), optionText.getText());
        }

        addNewRadioButton(3, 0, 3);
        for (OptionLabel optionLabel : OptionLabel.values()) {
            addNewLabel(1, optionLabel.ordinal(), optionLabel.getText());
        }

        leftPanel.add(rightPanel, new GridBagConstraints(4, 0, 1, 3, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

        initEditors();

        initButton();
    }

    private void initEditors(){
        rowsTextEditor = new TextEditor(Pattern.compile(CommonConstants.REGEX_ROW_COL));
        new RegexValidator(rowsTextEditor, this, CommonConstants.REGEX_ROW_COL);
        columnsTextEditor = new TextEditor(Pattern.compile(CommonConstants.REGEX_ROW_COL));
        new RegexValidator(columnsTextEditor, this, CommonConstants.REGEX_ROW_COL);
        minesTextEditor = new TextEditor(Pattern.compile(CommonConstants.REGEX_MINES));
        new RegexValidator(minesTextEditor, this, CommonConstants.REGEX_MINES);

        rightPanel.add(rowsTextEditor.getComponent(), new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        rightPanel.add(columnsTextEditor.getComponent(), new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        rightPanel.add(minesTextEditor.getComponent(), new GridBagConstraints(2, 3, 1, 1, 0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        setEnabledComponents(false);
    }

    private void initButton() {
        JPanel jPanel = new JPanel();
        jPanel.add(buttonOk);
        buttonOk.setFont(CommonConstants.FONT_17);
        buttonOk.addActionListener(new OptionWindowListener());
        buttonOk.setPreferredSize(CommonConstants.BUTTON_SIZE);
        jPanel.add(Box.createHorizontalStrut(100));

        jPanel.add(buttonCancel);
        buttonCancel.setFont(CommonConstants.FONT_17);
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonCancel.setPreferredSize(CommonConstants.BUTTON_SIZE);
        add(jPanel, BorderLayout.SOUTH);
    }

    private void setEnabledComponents(boolean isEnable){
        for (Component Component : rightPanel.getComponents())
            Component.setEnabled(isEnable);
        buttonOk.setEnabled(!isEnable);
    }

    @Override
    public void setVisible(boolean b) {
        setLocationRelativeTo(owner);
        super.setVisible(b);
    }

    @Override
    public void doControl(ValidatorBase validator, boolean valid) {
        validators.put(validator, valid);
        this.valid = !validators.containsValue(Boolean.FALSE);
        buttonOk.setEnabled(this.valid);
    }

    @Override
    public void register(ValidatorBase validator) {
        validators.put(validator, Boolean.FALSE);
    }

    @Override
    public void unregister(ValidatorBase validator) {
        validators.remove(validator);
    }

    private class OptionWindowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MinerModel model;
            if (owner.getModel() instanceof MinerModel) {
                model = (MinerModel) owner.getModel();
                int p = buttonGroup.getSelection().getMnemonic();
                int row = 9;
                int col = 9;
                int mines = 9;
                switch (p) {
                    case 0: {
                        row = col = mines = 10;
                        break;
                    }
                    case 1: {
                        row = col = 16;
                        mines = 40;
                        break;
                    }
                    case 2: {
                        row = 16;
                        col = 30;
                        mines = 99;
                        break;
                    }
                    case 3: {
                        try {
                            row = Integer.valueOf(rowsTextEditor.getNewValue());
                            col = Integer.valueOf(columnsTextEditor.getNewValue());
                            mines = Integer.valueOf(minesTextEditor.getNewValue());
                        } catch (NumberFormatException numException) {
                            LOGGER.error(numException.getMessage());
                        }
                        break;
                    }
                }
                model.setNewGame(row, col, mines);
                model.reload();
            }
            dispose();
        }
    }
}
