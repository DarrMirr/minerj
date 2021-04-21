package org.home.polukeev.view.editors;

import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

/**
 * Created by onodee on 17.02.2016.
 */
public class FilterEditorSupport {
    protected EventListenerList listenerList = new EventListenerList();
    transient protected ChangeEvent changeEvent = null;

    public void addFilterEditorListener(FilterEditorListener l) {
        listenerList.add(FilterEditorListener.class, l);
    }

    public void removeFilterEditorListener(FilterEditorListener l) {
        listenerList.remove(FilterEditorListener.class, l);
    }

    public void fireEditingStopped() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == FilterEditorListener.class) {
                if (changeEvent == null)
                    changeEvent = new ChangeEvent(this);
                ((FilterEditorListener) listeners[i + 1]).editingStopped(changeEvent);
            }
        }
    }
}
