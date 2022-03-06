package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import viewModel.TemperatureViewModel;

public class TemperatureViewController
{
  @FXML private TextArea minTemp;
  @FXML private TextArea maxTemp;
  @FXML private Label outdoorTemp;
  @FXML private Label nearTemp;
  @FXML private Label farTemp;
  @FXML private Label errorLabel;

  private Region root;
  private ViewHandler viewHandler;
  private TemperatureViewModel temperatureViewModel;

  public void init(ViewHandler viewHandler, TemperatureViewModel temperatureViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.temperatureViewModel = temperatureViewModel;
    this.root = root;

    errorLabel.textProperty().bind(temperatureViewModel.getErrorLabelProperty());
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

  @FXML
  public void setTemperatures() {
    temperatureViewModel.setMinTemp(Double.parseDouble(minTemp.textProperty().get()));
    temperatureViewModel.setMaxTemp(Double.parseDouble(maxTemp.textProperty().get()));
  }
}
