package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class OutsideThermometer implements Runnable,
    UnnamedPropertyChangeSubject
{

    //private String id;
    private double temperature;

    private PropertyChangeSupport property;

    public OutsideThermometer(double temperature){
      //  this.id = "Outside";
        this.temperature = temperature;
        property = new PropertyChangeSupport(this);
    }


    /*** Calculating the external temperature.
     * Values are only valid if the temperature is being measured *
     * approximately every 10th second.** @param t0  thelast measured external temperature*
     * @param min a lower limit (may temporally be deceeded)* @param max an upper limit (may temporally be exceeded)*
     * @return an updated external temperature
     * */
    public double externalTemperature(double t0, double min, double max) {
        double left = t0 - min;
        double right = max - t0;
        int sign = Math.random() * (left + right) > left ? 1 : -1;
        t0 += sign * Math.random();
        return t0;
    }

    public double getOutsideTemp(){

        BigDecimal outsideBD = new BigDecimal(temperature).setScale(2, RoundingMode.HALF_UP);
        temperature = outsideBD.doubleValue();
        return temperature;
    }

    @Override
    public void run() {
        while (true){
            temperature =externalTemperature(temperature, -20,20);
            property.firePropertyChange("outdoorTempChange", 1, getOutsideTemp());
            //System.out.println(id + " " + t); TODO
            try {
                Thread.sleep(8000);
            }
            catch(InterruptedException e){
                //do nothing
            }
        }
    }

    @Override public void addListener(PropertyChangeListener listener)
    {
        property.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener)
    {
        property.removePropertyChangeListener(listener);
    }

    /* USED FOR TESTING
    public static void main(String[] args) {
        OutsideThermometer outsideThermometer = new OutsideThermometer(15);
        Thread thread = new Thread(outsideThermometer, "Thermometer1");

        thread.start();
    }

     */
}
