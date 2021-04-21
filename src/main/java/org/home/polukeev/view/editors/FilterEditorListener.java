package org.home.polukeev.view.editors;

import javax.swing.event.ChangeEvent;
import java.util.EventListener;

/**
 * Created by onodee on 17.02.2016.
 */
public interface FilterEditorListener extends EventListener {

    public void editingStopped(ChangeEvent e);
}
