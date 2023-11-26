package pl.pwr.ite.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.pwr.ite.utils.service.StationRepository;
import pl.pwr.ite.utils.service.WebClient;
import pl.pwr.ite.utils.service.impl.StationRepositoryImpl;
import pl.pwr.ite.utils.service.impl.WebClientImpl;

import java.io.IOException;

import static java.util.stream.Collectors.groupingBy;

public class AirConditionApplication extends Application {

    private final WebClient webClient = WebClientImpl.getInstance();
    private final StationRepository stationRepository = StationRepositoryImpl.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AirConditionApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 750);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        stationRepository.loadData();
    }

    public static void main(String[] args) {
        launch();
    }
}