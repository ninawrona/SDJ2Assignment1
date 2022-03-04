package model;

public class Medium implements HeaterState {
    @Override
    public void turnUp(Heater heater) {
        heater.setState(new Max(heater));
    }

    @Override
    public void turnDown(Heater heater) {
        heater.setState(new Low());
    }

    @Override
    public String status() {
        return "Medium";
    }

}
