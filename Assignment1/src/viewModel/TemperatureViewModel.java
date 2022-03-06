package viewModel;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.SimpleBeanInfo;
import java.text.DecimalFormat;

public class TemperatureViewModel implements PropertyChangeListener {
    private DoubleProperty outdoorTempProperty;
    private DoubleProperty nearTempProperty, farTempProperty;
    private StringProperty errorLabelProperty;
    private double maxTemp;
    private double minTemp;
    private Model model;
    public static final DecimalFormat df = new DecimalFormat("0.00");

    public TemperatureViewModel(Model model){
        this.model = model;
        this.minTemp = 0;
        this.maxTemp = 25;
        model.getOutsideThermometer().addListener(this);
        model.getNearThermometer().addListener(this);
        model.getFarThermometer().addListener(this);
        System.out.println("Added the listener");

        outdoorTempProperty = new SimpleDoubleProperty(model.getOutsideTemperature());
        nearTempProperty = new SimpleDoubleProperty(model.getNearTemperature());
        farTempProperty = new SimpleDoubleProperty(model.getFarTemperature());
        errorLabelProperty = new SimpleStringProperty("");
    }

    public void setMinTemp(double min){
        this.minTemp = min;
    }

    public void setMaxTemp(double max){
        this.maxTemp = max;
    }

    public void reset(){
        errorLabelProperty.set("");
    }

    public DoubleProperty getOutdoorTempProperty() {
        return outdoorTempProperty;
    }

    public DoubleProperty getNearTempProperty() {
        return nearTempProperty;
    }

    public DoubleProperty getFarTempProperty() {
        return farTempProperty;
    }

    public StringProperty getErrorLabelProperty(){
        return errorLabelProperty;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getNewValue());
        Platform.runLater(()-> {
            switch (evt.getPropertyName())
            {
                case "tempChange":
                    nearTempProperty.set(Double.parseDouble(df.format(evt.getOldValue())));
                    farTempProperty.set(Double.parseDouble(df.format(evt.getNewValue())));

                    if (nearTempProperty.get() > maxTemp || farTempProperty.get()> maxTemp){
                        errorLabelProperty.set("Your indoor temp is too is too high.");
                    }
                    else if(nearTempProperty.get() < minTemp || farTempProperty.get() < minTemp){
                        errorLabelProperty.set("Your indoor temp is too is too small.");
                    }

                    break;

                case "outdoorTempChange":
                    System.out.println("got it");
                    outdoorTempProperty.set(Double.parseDouble(df.format(evt.getNewValue())));
                    break;
            }

            //System.out.println("Went through property change");

        });
    }
}
