package model;

public interface HeaterState {
    public void turnUp(Heater heater);
    public void turnDown(Heater heater);
    public String status();
    //public void timeout();
}
