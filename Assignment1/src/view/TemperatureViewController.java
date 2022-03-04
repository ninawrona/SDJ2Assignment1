package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewModel.TemperatureViewModel;

public class TemperatureViewController
{
  @FXML private Label outputTemp;
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
      //
  }




}
