package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject {
    public void turnUp();
    public void turnDown();
    public double getTemperature();
    public HeaterState getHeaterState();
    public String status();
}
