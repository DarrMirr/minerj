package org.home.polukeev.view.validator;

import org.home.polukeev.view.editors.TextEditor;

import javax.swing.event.ChangeEvent;
import java.awt.*;

public class RegexValidator extends ValidatorBase<String> {

    private String regex;

    public RegexValidator(TextEditor filterEditor, ValidatorController validatorController, String regex) {
        super();
        this.filterEditor = filterEditor;
        this.validatorController = validatorController;
        this.regex = regex;
        filterEditor.addFilterEditorListener(this);
        validatorController.register(this);
        validatorController.doControl(this, validate(filterEditor.getNewValue()));
    }

    @Override
    public boolean validate(String value) {
        return value != null && value.matches(regex);
    }

    @Override
    public void editingStopped(ChangeEvent e) {
        boolean isValid = validate(filterEditor.getNewValue());
        Color textColor = isValid ? Color.BLACK : Color.RED;
        filterEditor.getComponent().setForeground(textColor);
        validatorController.doControl(this, isValid);
    }
}
