package viewModel;

import javafx.beans.property.StringProperty;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HeaterViewModel implements PropertyChangeListener{
    private StringProperty errorProperty;
    private StringProperty stateProperty;
    private Model model;

    public HeaterViewModel(Model model){
        this.model = model;
        errorProperty.set("");
        stateProperty.set(model.getHeaterState().toString());
    }

    public void reset(){
        errorProperty.set("");
    }

    public void turnUp(){
        model.turnUp();
    }

    public void turnDown(){
        model.turnDown();
    }

    public String getStatus(){
        return model.getHeaterState().toString();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
