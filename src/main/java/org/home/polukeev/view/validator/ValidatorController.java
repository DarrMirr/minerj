package org.home.polukeev.view.validator;

/**
 * Created by onodee on 17.02.2016.
 */
public interface ValidatorController {

    public void doControl(ValidatorBase validator, boolean valid);

    public void register(ValidatorBase validator);

    public void unregister(ValidatorBase validator);
}
