package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject {
    public void turnUp();
    public void turnDown();
    public double getNearTemperature();
    public double getFarTemperature();
    public double getOutsideTemperature();
    public HeaterState getHeaterState();
    Thermometer getNearThermometer();
    Thermometer getFarThermometer();
    OutsideThermometer getOutsideThermometer();

   // public String status();
}
