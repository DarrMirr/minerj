package org.home.polukeev.model;

/**
 * Created by onodee on 15.02.2016.
 * интерфейс модели
 */
public interface Model {

    void reload();

    void addModelListener(ModelListener modelListener);

    void removeModelListener(ModelListener modelListener);

}
