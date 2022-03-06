package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static java.lang.Thread.sleep;

public class Max implements HeaterState{
    private Thread t1;
    private PropertyChangeSupport property;


    public Max(Heater heater)
    {
        this.property = new PropertyChangeSupport(this);
        t1 = new Thread(() ->{
            try {
                sleep(5000);
                heater.turnDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }) ;
        t1.start();

    }
    @Override
    public void turnUp(Heater heater) {
        throw new IllegalStateException("Max is the highest setting!");
    }

    @Override
    public void turnDown(Heater heater) {
        t1.interrupt();
        heater.setState(new Medium());
        property.firePropertyChange("turnDown", null, heater.getState().status());
    }

    @Override
    public String status() {
        return "Max";
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
