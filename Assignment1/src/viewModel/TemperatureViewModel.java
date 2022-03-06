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

public class TemperatureViewModel implements PropertyChangeListener {
    private DoubleProperty outdoorTempProperty;
    private DoubleProperty nearTempProperty, farTempProperty;
    private StringProperty errorLabelProperty;
    private Model model;

    public TemperatureViewModel(Model model){
        this.model = model;
        model.getOutsideThermometer().addListener(this);
        model.getNearThermometer().addListener(this);
        model.getFarThermometer().addListener(this);

        outdoorTempProperty = new SimpleDoubleProperty(1);
        nearTempProperty = new SimpleDoubleProperty(model.getNearTemperature());
        farTempProperty = new SimpleDoubleProperty(model.getFarTemperature());
        errorLabelProperty = new SimpleStringProperty("");
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
        //System.out.println(evt.getNewValue());
        Platform.runLater(()-> {
            switch (evt.getPropertyName())
            {
                case "tempChange":
                    nearTempProperty.set((Double) evt.getOldValue());
                    farTempProperty.set((Double) evt.getNewValue());
                    //System.out.println("INDOOR CHANGE");
                    break;

                case "outdoorTempChange":
                    outdoorTempProperty.set((Double) evt.getNewValue());
                    //System.out.println("OUTDOOR CHANGE");
                    break;
            }



        });
    }
}
