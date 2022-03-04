package model;

import static java.lang.Thread.sleep;

public class Max implements HeaterState{
    private Thread t1;
    private Heater heater;

    public Max(Heater heater)
    {
        this.heater = heater;
        t1 = new Thread(() ->{
            try {
                System.out.println("doing the thread");
                sleep(5000);
                heater.setState(new Medium());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }) ;
        t1.start();

    }
    @Override
    public void turnUp(Heater heater) {
        throw new IllegalStateException("Max is the highest setting!");

    }

    @Override
    public void turnDown(Heater heater) {
        t1.interrupt();
        heater.setState(new Medium());

    }

    @Override
    public String status() {
        return "Max";
    }
}
