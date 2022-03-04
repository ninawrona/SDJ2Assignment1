package model;

public class Off implements HeaterState {


    @Override
    public void turnUp(Heater heater) {
        heater.setState(new Low());
    }

    @Override
    public void turnDown(Heater heater) {
        throw new IllegalStateException("Off is the lowest setting!");
    }

    @Override
    public String status() {
        return "Off";
    }

}
