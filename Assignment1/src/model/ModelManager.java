package model;

import java.beans.PropertyChangeListener;

public class ModelManager implements Model{
    private Thermometer nearThermometer;
    private Thermometer outsideThermometer;
    private Thermometer farThermometer;
    private Heater heater;

    public ModelManager(){

    }

    public void turnUp(){

    }

    public void turnDown(){

    }

    @Override
    public double getTemperature() {
        return 0;
    }

    @Override
    public HeaterState getHeaterState() {
        return null;
    }


    @Override
    public void addListener(PropertyChangeListener listener) {

    }

    @Override
    public void removeListener(PropertyChangeListener listener) {

    }
}
