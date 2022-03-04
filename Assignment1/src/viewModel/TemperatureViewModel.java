package viewModel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements PropertyChangeListener {
    private DoubleProperty outdoorTempProperty;
    private DoubleProperty nearTempProperty, farTempProperty;
    private StringProperty errorLabelProperty;
    private Model model;

    public TemperatureViewModel(Model model){
        this.model = model;
        errorLabelProperty.set("");
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

    }
}
