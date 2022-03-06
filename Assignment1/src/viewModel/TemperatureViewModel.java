package viewModel;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements PropertyChangeListener
{
  private DoubleProperty outdoorTempProperty;
  private DoubleProperty nearTempProperty, farTempProperty;
  private StringProperty errorLabelProperty;
  private double minTemperature, maxTemperature;
  private Model model;

  public TemperatureViewModel(Model model)
  {
    this.model = model;
    model.getOutsideThermometer().addListener(this);
    model.getNearThermometer().addListener(this);
    model.getFarThermometer().addListener(this);

    this.maxTemperature = 999;
    this.minTemperature = -999;

    outdoorTempProperty = new SimpleDoubleProperty(1);
    nearTempProperty = new SimpleDoubleProperty(model.getNearTemperature());
    farTempProperty = new SimpleDoubleProperty(model.getFarTemperature());
    errorLabelProperty = new SimpleStringProperty("");
  }

  public void reset()
  {
    errorLabelProperty.set("");
  }

  public void setMinTemperature(double minTemperature)
  {
    this.minTemperature = minTemperature;
  }

  public void setMaxTemperature(double maxTemperature)
  {
    this.maxTemperature = maxTemperature;
  }

  public DoubleProperty getOutdoorTempProperty()
  {
    return outdoorTempProperty;
  }

  public DoubleProperty getNearTempProperty()
  {
    return nearTempProperty;
  }

  public DoubleProperty getFarTempProperty()
  {
    return farTempProperty;
  }

  public StringProperty getErrorLabelProperty()
  {
    return errorLabelProperty;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "tempChange":
          nearTempProperty.set((Double) evt.getOldValue());
          farTempProperty.set((Double) evt.getNewValue());
          break;

        case "outdoorTempChange":
          outdoorTempProperty.set((Double) evt.getNewValue());
          break;
      }
      if (maxTemperature < nearTempProperty.get())
      {
        errorLabelProperty.set("WARNING: 'NEAR' is too high");
      }
      else if (maxTemperature < farTempProperty.get())
      {
        errorLabelProperty.set("WARNING:'FAR' is too high");
      }

      else if (minTemperature > nearTempProperty.get())
      {
        errorLabelProperty.set("WARNING:'NEAR' is too low");
      }
      else if (minTemperature > farTempProperty.get())
      {
        errorLabelProperty.set("WARNING:'FAR' is too low");
      }
    });
  }
}
