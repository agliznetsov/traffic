package agi.traffic.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TrafficApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TrafficModel model = new TrafficModel();
        TrafficView trafficView = new TrafficView(model);
        TrafficController controller = new TrafficController(model, trafficView);

        BorderPane pane = new BorderPane();
        pane.setLeft(new ScrollPane(new TrafficControlPanel(model, controller)));
        pane.setCenter(trafficView);

        Scene scene = new Scene(pane, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Traffic Simulator");
        primaryStage.show();

        Platform.runLater(controller::init);
    }

    public static void main(String[] args) {
        launch(TrafficApplication.class, args);
    }
}
