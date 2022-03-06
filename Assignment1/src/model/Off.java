package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Off implements HeaterState {
    private PropertyChangeSupport property = new PropertyChangeSupport(this);


    @Override
    public void turnUp(Heater heater) {
        heater.setState(new Low());
        property.firePropertyChange("up", null, heater.getState().status());
    }

    @Override
    public void turnDown(Heater heater) {
        property.firePropertyChange("down", null, heater.getState().status());
        throw new IllegalStateException("Off is the lowest setting!");
    }

    @Override
    public String status() {
        return "Off";
    }

    @Override public void addListener(PropertyChangeListener listener)
    {
        property.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener)
    {
        property.removePropertyChangeListener(listener);
    }

}
