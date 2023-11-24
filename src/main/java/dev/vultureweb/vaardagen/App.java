package dev.vultureweb.vaardagen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    var button = new Button("Hello");
    var root = new StackPane();
    root.getChildren().add(button);
    var scene = new Scene(root,800,600);
    stage.setScene(scene);
    stage.show();
  }
}
