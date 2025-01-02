package UpdateWorkSlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import ViewWorkSlot.ViewWorkSlotB;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.CafeApp;

public class UpdateWorkSlotB {

    public Scene getUpdateWorkSlotScene(Map<String, String> workslot) {
        GridPane updateSlotPane = new GridPane();
		updateSlotPane.setAlignment(Pos.CENTER);
		updateSlotPane.setHgap(3);
		updateSlotPane.setVgap(3);
        updateSlotPane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("Update workslot");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		updateSlotPane.add(welcomeBox, 2, 0);
        
        // display date of workslot
        int row = 1;
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(workslot.get("date"));
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        updateSlotPane.add(new Label("Date: "), 1, row);
        updateSlotPane.add(new Label(orgDate.format(formattedDate)), 2, row);

        // display time duration of workslot
        row = 2;
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(workslot.get("startTime"));
        LocalTime orgEndTime = LocalTime.parse(workslot.get("endTime"));
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        updateSlotPane.add(new Label("Duration: "), 1, row);
        updateSlotPane.add(new Label(orgStartTime.format(formattedTime) + " - " + orgEndTime.format(formattedTime)), 2, row);
        
        // slot status
        row = 4;
        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("", "Available", "Closed");
        // set value for combo box
        String str = (workslot.get("availabilityStatus").equals("1")) ? "Available" : "Closed"; 
        statusBox.setValue(str);
        // layout
        updateSlotPane.add(new Label("Availability status: "), 1, row);
        updateSlotPane.add(statusBox, 2, row);

        // max staff allowed
        row = 5;
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
        // set max staff value
        maxCashier.setText(workslot.get("maxCashier"));
        maxChef.setText(workslot.get("maxChef"));
        maxWaiter.setText(workslot.get("maxWaiter"));
        // max staff layout
        updateSlotPane.add(new Label("Max Cashier amount: "), 1, row);
        updateSlotPane.add(maxCashier, 2, row);
        updateSlotPane.add(new Label("Max Chef amount: "), 1, row + 1);
        updateSlotPane.add(maxChef, 2, row + 1);
        updateSlotPane.add(new Label("Max Waiter amount: "), 1, row + 2);
        updateSlotPane.add(maxWaiter, 2, row + 2);

        // buttons
        row = 10;
        // cancel button
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        cancelBtn.setPrefWidth(80); 
        cancelBtn.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        updateSlotPane.add(hbox, 2, row);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        //updateSlotPane.add(cancelBtn, 1, row);
        // cancel action
        cancelBtn.setOnAction(event -> {
            // go back to viewing this workslot
            CafeApp.changeScene(new ViewWorkSlotB().getViewASlotScene(workslot));
        });

        // save changes button
        Button saveBtn = new Button("Save");
        saveBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        saveBtn.setPrefWidth(80); 
        saveBtn.setPrefHeight(25);
        updateSlotPane.add(saveBtn, 2, row);
        hbox.getChildren().addAll(cancelBtn, saveBtn);
       // updateSlotPane.add(saveBtn, 2, row);
        // save action
        saveBtn.setOnAction(event -> {
            // confirmation alert
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setHeaderText("Are you sure?");
            // create button type
            ButtonType yesBtn = new ButtonType("Yes");
            confirmation.getButtonTypes().setAll(yesBtn, new ButtonType("No"));
            // show and wait for response
            Optional<ButtonType> result = confirmation.showAndWait();
            // check for user response
            if(result.orElse(null) == yesBtn) {
                // check for invalid values
                if(statusBox.getValue().equals("") || statusBox.getValue() == null) {
                    Alert alert = new Alert(AlertType.ERROR, "Invalid availability status data!");
                    alert.showAndWait();
                    return;
                }
                // try to update the workslot
                UpdateWorkSlotC controller = new UpdateWorkSlotC();
                boolean success = controller.UpdateWorkSlot(workslot.get("date"), workslot.get("startTime"), workslot.get("endTime"), maxCashier.getText(), maxChef.getText(), maxWaiter.getText(), statusBox.getValue());
                // handle the result of the update
                if(success) {
                    System.out.println("Updated the workslot!");
                    // go back to view scene
                    Scene viewSlotScene = new ViewWorkSlotB().getViewASlotScene(workslot.get("date"), workslot.get("startTime"), workslot.get("endTime"));
                    CafeApp.changeScene(viewSlotScene);
                }
            }
        });

        Scene scene = new Scene(updateSlotPane, 500, 500);
        return scene;
    }
}
