package dev.vultureweb.vaardagen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {

  @FXML
  private DatePicker departureDatePicker;

  @FXML
  private DatePicker arrivalDatePicker;

  private final Callback<DatePicker, DateCell> departureDateCellFactory = new Callback<>() {
    @Override
    public DateCell call(DatePicker datePicker) {
      return new DateCell() {
        @Override
        public void updateItem(LocalDate localDate, boolean empty) {
          super.updateItem(localDate, empty);
          if(localDate.isBefore(LocalDate.now())) {
            setDisable(true);
            setStyle("-fx-background-color: #BBBBBB");
          }
        }
      };
    }
  };
  private final Callback<DatePicker, DateCell> arrivalDateCellFactory = new Callback<>() {
    @Override
    public DateCell call(DatePicker datePicker) {
      return new DateCell() {
        @Override
        public void updateItem(LocalDate localDate, boolean empty) {
          super.updateItem(localDate, empty);
          if(localDate.isBefore(departureDatePicker.getValue())) {
            setDisable(true);
            setStyle("-fx-background-color: #BBBBBB");
          }
        }
      };
    }
  };

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    departureDatePicker.setDayCellFactory(departureDateCellFactory);
    departureDatePicker.setValue(LocalDate.now());
    arrivalDatePicker.setDayCellFactory(arrivalDateCellFactory);
  }

}
