package model;

public class Heater {
    HeaterState state;

    public Heater(){
        this.state = new Off();
    }

    public HeaterState getState(){
        return state;
    }

    public void turnUp(){
        state.turnUp(this);
    }

    public void turnDown(){
        state.turnDown(this);
    }

    public String status(){
        return state.status();
    }

    public void setState(HeaterState state){
        this.state = state;
    }
}
