package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Medium implements HeaterState
{

  private PropertyChangeSupport property = new PropertyChangeSupport(this);

  @Override public void turnUp(Heater heater)
  {
    heater.setState(new Max(heater));
    property.firePropertyChange("turnUp", null, heater.getState().status());

  }

  @Override public void turnDown(Heater heater)
  {
    heater.setState(new Low());
    property.firePropertyChange("turnUp", null, heater.getState().status());

  }

  @Override public String status()
  {
    return "Medium";
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
