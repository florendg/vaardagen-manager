module dev.vultureweb.vaardagen {
  requires javafx.controls;
  requires javafx.fxml;
  requires dev.vultureweb.vaardagen.manager.api;
  exports dev.vultureweb.vaardagen to javafx.graphics;
  opens dev.vultureweb.vaardagen to javafx.fxml;
}