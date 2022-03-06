package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.HeaterState;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HeaterViewModel implements PropertyChangeListener
{
  private StringProperty errorProperty;
  private StringProperty stateProperty;
  private Model model;

  public HeaterViewModel(Model model)
  {
    this.model = model;
    model.getHeaterState().addListener(this);

    errorProperty = new SimpleStringProperty("");
    stateProperty = new SimpleStringProperty(model.getHeaterState().status());
  }

  public void reset()
  {
    errorProperty.set("");
  }

  public void turnUp()
  {
    try
    {
      model.turnUp();

    }
    catch (IllegalStateException e)
    {
      errorProperty.set(e.getMessage());
    }
  }

  public void turnDown()
  {
    try
    {
      model.turnDown();
    }
    catch (IllegalStateException e)
    {
      errorProperty.set(e.getMessage());
    }

  }

  public String getStatus()
  {
    return model.getHeaterState().status();
  }

  public StringProperty getState()
  {
    return stateProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      model.getHeaterState().addListener(this);
      stateProperty.set(getStatus());
    });
  }
}