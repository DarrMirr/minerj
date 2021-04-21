package org.home.polukeev.view.editors;

import java.awt.*;

/**
 * Created by onodee on 17.02.2016.
 */
public interface FilterEditor<T> {

    Component getComponent();

    Component[] getComponentArray();

    T getNewValue();

    void setNewValue(T value);

    public void addFilterEditorListener(FilterEditorListener l);

    public void removeCellEditorListener(FilterEditorListener l);

    void setReadOnly(boolean b);

}
