package org.home.polukeev.view;

import org.home.polukeev.model.Model;
import org.home.polukeev.model.ModelListener;

/**
 * Created by onodee on 15.02.2016.
 * интерфейс view
 */
public interface View extends ModelListener {

    Model getModel();
}
