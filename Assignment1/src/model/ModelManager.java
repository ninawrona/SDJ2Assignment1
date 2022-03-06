package model;

public class ModelManager implements Model
{
  private Thermometer nearThermometer;
  private OutsideThermometer outsideThermometer;
  private Thermometer farThermometer;
  private Heater heater;

  public ModelManager(Thermometer nearThermometer, Thermometer farThermometer,
      OutsideThermometer outsideThermometer)
  {
    this.nearThermometer = nearThermometer;
    this.farThermometer = farThermometer;
    this.outsideThermometer = outsideThermometer;

    heater = new Heater();
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
  }

  @Override public void turnDown()
  {
    heater.turnDown();
    nearThermometer.setHeaterLevel(getHeaterState());
    farThermometer.setHeaterLevel(getHeaterState());
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

}
