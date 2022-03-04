package model;

import static java.lang.Thread.sleep;

public class Max implements HeaterState{

    @Override
    public void turnUp(Heater heater) {
        //do nothing
    }

    @Override
    public void turnDown(Heater heater) {
        heater.setState(new Medium());
    }

    @Override
    public String status(Heater heater) {
        return "Max";
    }

    @Override
    public void timeout(Heater heater) {
        try {
            sleep(5000);
            heater.setState(new Medium());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
