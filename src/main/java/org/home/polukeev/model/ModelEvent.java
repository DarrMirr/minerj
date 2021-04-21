package org.home.polukeev.model;

public class ModelEvent  {
    private Model source;

    public ModelEvent(Model source) {
        this.source = source;
    }

    public Model getSource() {
        return source;
    }

}
