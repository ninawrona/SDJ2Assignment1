package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface HeaterState extends UnnamedPropertyChangeSubject {
    public void turnUp(Heater heater);
    public void turnDown(Heater heater);
    public String status();
    //public void timeout();
    @Override public void addListener(PropertyChangeListener listener);
    @Override public void removeListener(PropertyChangeListener listener);
}
