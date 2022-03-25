package model;

import javafx.application.Platform;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static java.lang.Thread.sleep;

public class Max implements HeaterState, UnnamedPropertyChangeSubject {
    private Thread t1;
    private Heater heater;
    private PropertyChangeSupport property;

    public Max(Heater heater)
    {
        this.property = new PropertyChangeSupport(this);
        this.heater = heater;
        t1 = new Thread(() ->{
            try {
                System.out.println("doing the thread");
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
        property.firePropertyChange("up", null, heater.getState().status());
        throw new IllegalStateException("Max is the highest setting!");

    }

    @Override
    public void turnDown(Heater heater) {
        t1.interrupt();
        heater.setState(new Medium());
        property.firePropertyChange("down", null, heater.getState().status());
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
