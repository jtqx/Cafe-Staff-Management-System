package CreateWorkSlot;

import java.time.LocalTime;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.CafeApp;

public class CreateWorkSlotB {

    public Scene getCreateWorkSlotScene() {
        
        GridPane createWorkSlotPane = new GridPane();
		createWorkSlotPane.setAlignment(Pos.CENTER);
		createWorkSlotPane.setHgap(3);
		createWorkSlotPane.setVgap(3);
        createWorkSlotPane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("Create workslot");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		createWorkSlotPane.add(welcomeBox, 1, 0);
        
        // date
        DatePicker datePicker = new DatePicker();
        // layout
        createWorkSlotPane.add(new Label("Date: "), 0, 4);
        createWorkSlotPane.add(datePicker, 1, 4);
        
        // start time
        Spinner startHour = new Spinner<>(0, 23, 0);
        startHour.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        Spinner startMinute = new Spinner<>(0, 59, 0);
        startMinute.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        // start time layout
        HBox startTimeHBox = new HBox(5);
        startTimeHBox.getChildren().addAll(startHour, new Label(":"), startMinute);
        createWorkSlotPane.add(new Label("Start Time: "), 0, 5);
        createWorkSlotPane.add(startTimeHBox, 1, 5);
        
        // end time
        Spinner endHour = new Spinner<>(0, 23, 0);
        endHour.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        Spinner endMinute = new Spinner<>(0, 59, 0);
        endMinute.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        // end time layout
        HBox endTimeHBox = new HBox(5);
        endTimeHBox.getChildren().addAll(endHour, new Label(":"), endMinute);
        createWorkSlotPane.add(new Label("End Time: "), 0, 6);
        createWorkSlotPane.add(endTimeHBox, 1, 6);

        // max staff allowed
        TextField maxCashier = new TextField(); 
        maxCashier.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        TextField maxChef = new TextField(); 
        maxChef.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        TextField maxWaiter = new TextField(); 
        maxWaiter.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        // max staff layout
        createWorkSlotPane.add(new Label("Max Cashier amount: "), 0, 7);
        createWorkSlotPane.add(maxCashier, 1, 7);
        createWorkSlotPane.add(new Label("Max Chef amount: "), 0, 8);
        createWorkSlotPane.add(maxChef, 1, 8);
        createWorkSlotPane.add(new Label("Max Waiter amount: "), 0, 9);
        createWorkSlotPane.add(maxWaiter, 1, 9);

        // create work slot button
        Button createSlotBtn = new Button("Create");
        createSlotBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        createSlotBtn.setPrefWidth(80); 
        createSlotBtn.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        createWorkSlotPane.add(hbox, 1, 11);
        hbox.setAlignment(Pos.CENTER_RIGHT);

        createSlotBtn.setOnAction(event -> {
            CreateWorkSlotC controller = new CreateWorkSlotC();
            String startTimeStr = startHour.getValue().toString() + ":" + startMinute.getValue().toString() + ":00";
            String endTimeStr = endHour.getValue().toString() + ":" + endMinute.getValue().toString() + ":00";
            LocalTime startTime = LocalTime.of((int)startHour.getValue(), (int)startMinute.getValue());
            LocalTime endTime = LocalTime.of((int)endHour.getValue(), (int)endMinute.getValue());

            // check if the timing already exist
            if(controller.checkIfExist(datePicker.getValue().toString(), startTimeStr, endTimeStr)) {
                Alert alert = new Alert(AlertType.ERROR, "Timing already exists");
                alert.showAndWait();
                return;
            }

            if(datePicker.getValue() != null && endTime.isAfter(startTime)) {
                // try to create work slot
                boolean result = controller.CreateWorkSlot(datePicker.getValue().toString(), startTimeStr, endTimeStr, maxCashier.getText(), maxChef.getText(), maxWaiter.getText(), "1");
                if(result) {
                    // show success alert
                    Alert alert = new Alert(AlertType.INFORMATION, "Success!");
                    alert.showAndWait();
                    // go back to landing scene
                    CafeApp.changeScene(CafeApp.getLandingScene());
                }
                else {
                    Alert alert = new Alert(AlertType.ERROR, "Invalid staff amount!");
                    alert.showAndWait();
                }
            }
            else {
                // show error alert
                Alert alert = new Alert(AlertType.ERROR, "Invalid input!");
                alert.showAndWait();
            }
        });

        // back button
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
        createWorkSlotPane.add(backBtn, 1, 11);
        hbox.getChildren().addAll(backBtn, createSlotBtn);
        backBtn.setOnAction(event -> { 
            // go back to landing scene
            CafeApp.changeScene(CafeApp.getLandingScene());
        });

        Scene scene = new Scene(createWorkSlotPane, 500, 500);
        return scene;
    }

}
