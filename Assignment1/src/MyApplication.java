import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import model.OutsideThermometer;
import model.Thermometer;
import view.ViewHandler;
import viewModel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    OutsideThermometer outsideThermometer = new OutsideThermometer(15);
    Thread thread1 = new Thread(outsideThermometer, "Thermometer1");
    Thermometer thermometer = new Thermometer(15, 2,
        outsideThermometer.getOutsideTemp());
    Thread thread2 = new Thread(thermometer, "Thermometer1");

    Model model = new ModelManager(thermometer, thermometer, outsideThermometer);
    ViewModelFactory factory = new ViewModelFactory(model);
    ViewHandler handler = new ViewHandler(factory);


    thread1.setDaemon(true);
    thread2.setDaemon(true);

    thread1.start();
    thread2.start();

    handler.start(primaryStage);
  }


}
