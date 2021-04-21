package org.home.polukeev.view.validator;


import org.home.polukeev.view.editors.FilterEditor;
import org.home.polukeev.view.editors.FilterEditorListener;

import javax.swing.event.ChangeEvent;

/**
 * Created by onodee on 17.02.2016.
 */
abstract public class ValidatorBase<T> implements FilterEditorListener {
    protected FilterEditor<T> filterEditor;
    protected ValidatorController validatorController;

    protected ValidatorBase() {
    }

    protected ValidatorBase(FilterEditor<T> filterEditor, ValidatorController validatorController) {
        this.filterEditor = filterEditor;
        this.validatorController = validatorController;
        filterEditor.addFilterEditorListener(this);
        validatorController.register(this);
        validatorController.doControl(this, validate(filterEditor.getNewValue()));
    }

    protected abstract boolean validate(T value);

    @Override
    public void editingStopped(ChangeEvent e) {
        validatorController.doControl(this, validate(filterEditor.getNewValue()));
    }
}
