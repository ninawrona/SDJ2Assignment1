package model;

public interface HeaterState {
    public void turnUp(Heater heater);
    public void turnDown(Heater heater);
    public String status(Heater heater);
    public void timeout(Heater heater);
}
