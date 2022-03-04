package model;

public class Medium implements HeaterState {
    @Override
    public void turnUp(Heater heater) {
        heater.setState(new Max());
        heater.timeout();
    }

    @Override
    public void turnDown(Heater heater) {
        heater.setState(new Low());
    }

    @Override
    public String status(Heater heater) {
        return "Medium";
    }

    @Override
    public void timeout(Heater heater) {
        //do nothing
    }
}
