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
    public String status(Heater heater) {
        return "Low";
    }

    @Override
    public void timeout(Heater heater) {
        //do nothing
    }
}
