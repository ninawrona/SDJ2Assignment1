package model;

public interface Model {
    void turnUp();
    void turnDown();
    double getNearTemperature();
    double getFarTemperature();
    double getOutsideTemperature();
    HeaterState getHeaterState();
    Thermometer getNearThermometer();
    Thermometer getFarThermometer();
    OutsideThermometer getOutsideThermometer();
}
