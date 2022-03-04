package model;

public class Low implements HeaterState{
    @Override
    public void turnUp(Heater heater) {
        heater.setState(new Medium());
    }

    @Override
    public void turnDown(Heater heater) {
        heater.setState(new Off());
    }

    @Override
    public String status() {
        return "Low";
    }

}
