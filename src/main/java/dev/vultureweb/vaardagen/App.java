package dev.vultureweb.vaardagen;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    var url = App.class.getResource("vaardagen.fxml");
    if(url == null) {
      throw new RuntimeException("Could not find UX definition");
    }
    Parent node =  FXMLLoader.load(url);
    var scene = new Scene(node,800,600);
    stage.setScene(scene);
    stage.show();
  }
}
