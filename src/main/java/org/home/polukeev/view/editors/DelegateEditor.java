package org.home.polukeev.view.editors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelegateEditor<C extends JComponent, T> implements FilterEditor<T> {

    protected FilterEditorSupport filterEditorSupport = new FilterEditorSupport();
    protected EditorDelegate<T> delegate;
    protected C editorComponent;

    protected DelegateEditor() {
    }

    @Override
    public Component getComponent() {
        return editorComponent;
    }

    @Override
    public Component[] getComponentArray() {
        return new Component[]{editorComponent};
    }

    @Override
    public T getNewValue() {
        return delegate.getEditorValue();
    }

    @Override
    public void setNewValue(T value) {
        delegate.setValue(value);
    }

    @Override
    public void addFilterEditorListener(FilterEditorListener l) {
        filterEditorSupport.addFilterEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(FilterEditorListener l) {
        filterEditorSupport.removeFilterEditorListener(l);
    }

    @Override
    public void setReadOnly(boolean b) {
        editorComponent.setEnabled(!b);
    }

    protected abstract class EditorDelegate<T> implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            filterEditorSupport.fireEditingStopped();
        }

        protected abstract T getEditorValue();

        protected abstract void setValue(T value);

    }
}
