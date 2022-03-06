package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface HeaterState extends UnnamedPropertyChangeSubject
{
  void turnUp(Heater heater);
  void turnDown(Heater heater);
  String status();
  @Override void addListener(PropertyChangeListener listener);
  @Override void removeListener(PropertyChangeListener listener);
}
