package viewModel;

import model.Model;

public class ViewModelFactory {
    private TemperatureViewModel temperatureViewModel;
    private HeaterViewModel heaterViewModel;

    public ViewModelFactory(Model model){
        this.heaterViewModel = new HeaterViewModel(model);
        this.temperatureViewModel = new TemperatureViewModel(model);
    }

    public TemperatureViewModel getTemperatureViewModel(){
        return temperatureViewModel;
    }

    public HeaterViewModel getHeaterViewModel(){
        return heaterViewModel;
    }
}
