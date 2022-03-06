package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import viewModel.TemperatureViewModel;

public class TemperatureViewController
{
  @FXML private Label outdoorTemp;
  @FXML private Label nearTemp;
  @FXML private Label farTemp;
  @FXML private Label errorLabel;
  @FXML private TextField minimumField, maximumField;
  private Region root;
  private ViewHandler viewHandler;
  private TemperatureViewModel temperatureViewModel;

  public void init(ViewHandler viewHandler, TemperatureViewModel temperatureViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.temperatureViewModel = temperatureViewModel;
    this.root = root;

    errorLabel.textProperty().bindBidirectional(temperatureViewModel.getErrorLabelProperty());
    Bindings.bindBidirectional(farTemp.textProperty(), temperatureViewModel.getFarTempProperty(), new NumberStringConverter());
    Bindings.bindBidirectional(nearTemp.textProperty(), temperatureViewModel.getNearTempProperty(), new NumberStringConverter());
    Bindings.bindBidirectional(outdoorTemp.textProperty(), temperatureViewModel.getOutdoorTempProperty(), new NumberStringConverter());
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {
    temperatureViewModel.reset();
  }

  @FXML private void heaterButton()
  {
      viewHandler.openView("Heater");
  }

  @FXML private void setTemperatures()
  {
    reset();
    if (!(minimumField.textProperty().get().isEmpty()))
    {
      temperatureViewModel.setMinTemperature(Double.parseDouble(minimumField.textProperty().get()));
      temperatureViewModel.setMaxTemperature(Double.parseDouble(maximumField.textProperty().get()));
    }
    else
    {
      errorLabel.textProperty().set("Please fill in values");
    }
  }
}
