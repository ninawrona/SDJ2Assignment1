package model;

public class Thermometer implements Runnable{

    private String idNear;
    private String idFar;
    private double tNear;
    private double tFar;
    private int p; //p - power
    private double t0; //-outsideTemperature

    public Thermometer(double t, int power, double outsideTemp){
        this.idNear = "Near";
        this.idFar = "Far";
        this.tNear = t;
        this.tFar = t;
        this.p = power;
        this.t0 = outsideTemp;
    }

    /*** Calculating the internal temperature in one of two locations.
     * *This includes a term from a heater (depending on location and *
     * heaters power), and a term from an outdoor heat loss.* Values are only valid in the outdoor temperature range [-20; 20]*
     * and when s, the number of seconds between each measurements are* between 4 and 8 seconds.**
     * @param t  the last measured temperature* @param p  the heaters power {0, 1, 2 or 3} where 0 is turned off, *
     * 1 is low, 2 is medium and 3 is high* @param d  the distance between heater andmeasurements {1 or 7}*
     * where 1 is close to the heater and 7 is in theopposite corner* @param t0 the outdoor temperature (valid in the range [-20; 20])*
     * @param s the number of seconds since last measurement[4; 8]*
     * @return the temperature
     * */
    public double temperature(double t, int p, int d, double t0, int s) {
        double tMax = Math.min(11 * p + 10, 11 * p + 10 + t0);
        tMax = Math.max(Math.max(t, tMax), t0);
        double heaterTerm = 0;
        if (p > 0) {
            double den = Math.max((tMax * (20 - 5 * p) * (d + 5)), 0.1);
            heaterTerm = 30 * s * Math.abs(tMax - t) / den;
        }
        double outdoorTerm = (t - t0) * s / 250.0;
        t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);
        return t;
    }

    public void run() {
        while (true){
            tNear=temperature(tNear, p,1, t0, 6);
            tFar=temperature(tFar, p,7, t0, 6);
            System.out.println(idNear + " " + tNear);
            System.out.println(idFar + " " + tFar);
            try {
                Thread.sleep(4000);
            }
            catch(InterruptedException e){
                //do nothing
            }
        }
    }

    public double getNear(){
        return tNear;
    }

    public double getFar(){
        return tFar;
    }

    public static void main(String[] args) {
        OutsideThermometer outsideThermometer = new OutsideThermometer(15);
        Thread thread1 = new Thread(outsideThermometer, "Thermometer1");
        Thermometer thermometer = new Thermometer( 15,2, outsideThermometer.getOutsideTemp());
        Thread thread2 = new Thread(thermometer, "Thermometer1");

        thread1.start();
        thread2.start();
    }

}
