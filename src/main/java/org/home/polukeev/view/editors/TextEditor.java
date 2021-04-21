package org.home.polukeev.view.editors;

import org.home.polukeev.CommonConstants;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

public class TextEditor extends DelegateEditor<JTextField, String> implements KeyListener {
    private static final Pattern defaultPattern = Pattern.compile(CommonConstants.REGEX_TEXT);

    protected String startValue;
    protected Pattern pattern;

    public TextEditor() {
        this(defaultPattern);
    }

    public TextEditor(Pattern pattern) {
        this.pattern = pattern;
        editorComponent = new JTextField(3);
        editorComponent.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            private void update() {
                filterEditorSupport.fireEditingStopped();
            }
        });

        editorComponent.addKeyListener(this);

        delegate = new EditorDelegate<String>() {
            @Override
            protected String getEditorValue() {
                return editorComponent.getText();
            }

            @Override
            protected void setValue(String value) {
                editorComponent.setText(value);
            }
        };
    }

    @Override
    public void setNewValue(String value) {
        if (defaultPattern.matcher(value).matches()) {
            startValue = value;
            super.setNewValue(value);
            editorComponent.setCaretPosition(0);
        } else {
            String errorMessage = String.format("Wrong value \"%s\": length should be no more 255", value);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String checkValue = editorComponent.getText() + e.getKeyChar();
        if (!pattern.matcher(checkValue).matches()) {
            e.consume();  // Ignore this key
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
