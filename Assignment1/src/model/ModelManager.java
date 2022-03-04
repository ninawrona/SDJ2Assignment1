package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private Thermometer nearThermometer;
  private OutsideThermometer outsideThermometer;
  private Thermometer farThermometer;
  private Heater heater;
  private PropertyChangeSupport property;

  public ModelManager(Thermometer nearThermometer, Thermometer farThermometer,
      OutsideThermometer outsideThermometer)
  {
    this.nearThermometer = nearThermometer;
    this.farThermometer = farThermometer;
    this.outsideThermometer = outsideThermometer;

    heater = new Heater();
    property = new PropertyChangeSupport(this);
  }

  public Thermometer getNearThermometer()
  {
    return nearThermometer;
  }

  public Thermometer getFarThermometer()
  {
    return farThermometer;
  }

  public OutsideThermometer getOutsideThermometer()
  {
    return outsideThermometer;
  }

  @Override public void turnUp()
  {
    heater.turnUp();
    nearThermometer.setHeaterLevel(getHeaterState());
    farThermometer.setHeaterLevel(getHeaterState());
    property.firePropertyChange("up", null, getHeaterState());
  }

  @Override public void turnDown()
  {
    heater.turnDown();
    nearThermometer.setHeaterLevel(getHeaterState());
    farThermometer.setHeaterLevel(getHeaterState());
    property.firePropertyChange("down", null, getHeaterState());
  }

  @Override public double getFarTemperature()
  {
    return farThermometer.getFar();
  }

  @Override public double getNearTemperature()
  {
    return nearThermometer.getNear();
  }

  @Override public double getOutsideTemperature()
  {
    return outsideThermometer.getOutsideTemp();
  }

  @Override public HeaterState getHeaterState()
  {
    return heater.getState();
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
