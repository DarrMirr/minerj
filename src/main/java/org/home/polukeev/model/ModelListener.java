package org.home.polukeev.model;

import java.util.EventListener;

public interface ModelListener extends EventListener {

    void modelChanged(ModelEvent event);
}
