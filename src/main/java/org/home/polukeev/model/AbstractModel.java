package org.home.polukeev.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by onodee on 15.02.2016.
 * Абстрактный класс модели
 */
public abstract class AbstractModel implements Model {
    private ModelEvent modelEvent = null;

    private final List<ModelListener> listeners = new ArrayList<ModelListener>();

    @Override
    public void addModelListener(ModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeModelListener(ModelListener listener) {
        listeners.remove(listener);
    }

    public List<ModelListener> getListeners() {
        return listeners;
    }

    protected abstract void fireModelEventChanged();

    public abstract void reload();
}
