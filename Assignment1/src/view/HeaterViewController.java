package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewModel.HeaterViewModel;

public class HeaterViewController
{

  @FXML private Label heaterState;
  @FXML private Label errorLabel;
  private Region root;
  private ViewHandler viewHandler;
  private HeaterViewModel heaterViewModel;


  public void init(ViewHandler viewHandler, HeaterViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.heaterViewModel = viewModel;
    this.root = root;

    heaterState.textProperty().bindBidirectional(heaterViewModel.getState());
    errorLabel.textProperty().bind(heaterViewModel.getErrorProperty());}

  @FXML private void turnDownButton()
  {
    heaterViewModel.turnDown();
  }

  @FXML private void turnUpButton()
  {
    heaterViewModel.turnUp();
  }

  @FXML private void goBackButton()
  {
    viewHandler.openView("Temperature");
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {
    heaterViewModel.reset();
  }
}
