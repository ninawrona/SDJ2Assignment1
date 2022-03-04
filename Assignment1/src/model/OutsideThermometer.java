package model;

public class OutsideThermometer implements Runnable{

    private String id;
    private double t;

    public OutsideThermometer(double t){
        this.id = "Outside";
        this.t = t;
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
        return t;
    }

    @Override
    public void run() {
        while (true){
            t=externalTemperature(t, -20,20);
            System.out.println(id + " " + t);
            try {
                Thread.sleep(8000);
            }
            catch(InterruptedException e){
                //do nothing
            }
        }
    }

    public static void main(String[] args) {
        OutsideThermometer outsideThermometer = new OutsideThermometer(15);
        Thread thread = new Thread(outsideThermometer, "Thermometer1");

        thread.start();
    }
}
