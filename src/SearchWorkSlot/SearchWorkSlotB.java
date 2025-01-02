package SearchWorkSlot;

import java.util.Map;
import java.util.Vector;

import ViewWorkSlot.ViewWorkSlotB;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.CafeApp;

public class SearchWorkSlotB {

    public Scene getSearchWorkSlotScene() {
        GridPane searchSlotPane = new GridPane();
		searchSlotPane.setAlignment(Pos.CENTER);
		searchSlotPane.setHgap(3);
		searchSlotPane.setVgap(3);
        searchSlotPane.setStyle("-fx-background-color: #DBDBDB;");
        
        Text welcomeText = new Text("Search for a workslot");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		searchSlotPane.add(welcomeBox, 2, 0);
        int row = 0;
        // display message
       // searchSlotPane.add(new Label("Search for a workslot: "), 1, row);

        // date
        row = 4;
        DatePicker datePicker = new DatePicker();
        // layout
        searchSlotPane.add(new Label("Date: "), 1, row);
        searchSlotPane.add(datePicker, 2, row);
        
        // start time
        row = 7;
        Spinner startHour = new Spinner<>(0, 23, 0);
        startHour.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        Spinner startMinute = new Spinner<>(0, 59, 0);
        startMinute.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        // start time layout
        HBox startTimeHBox = new HBox(5);
        startTimeHBox.getChildren().addAll(startHour, new Label(":"), startMinute);
        Label startLabel = new Label("Start Time: ");
        searchSlotPane.add(startLabel, 1, row);
        searchSlotPane.add(startTimeHBox, 2, row);
        
        // end time
        row = 8;
        Spinner endHour = new Spinner<>(0, 23, 0);
        endHour.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        Spinner endMinute = new Spinner<>(0, 59, 0);
        endMinute.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        // end time layout
        HBox endTimeHBox = new HBox(5);
        endTimeHBox.getChildren().addAll(endHour, new Label(":"), endMinute);
        Label endLabel = new Label("End Time: ");
        searchSlotPane.add(endLabel, 1, row);
        searchSlotPane.add(endTimeHBox, 2, row);
        
        // spinner check box
        row = 6;
        // hide the time hbox by default
        startLabel.setVisible(false);
        startTimeHBox.setVisible(false);
        endLabel.setVisible(false);
        endTimeHBox.setVisible(false);
        // create the check box
        CheckBox checkBox = new CheckBox();
        checkBox.setOnAction(event -> {
            startLabel.setVisible(!startLabel.isVisible());
            startTimeHBox.setVisible(!startTimeHBox.isVisible());
            endLabel.setVisible(!endLabel.isVisible());
            endTimeHBox.setVisible(!endTimeHBox.isVisible());
        });
        // add to layout
        searchSlotPane.add(new Label("Search by time: "), 1, row);
        searchSlotPane.add(checkBox, 2, row);

        // slot status
        row = 9;
        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("", "Available", "Closed");
        searchSlotPane.add(new Label("Availability status: "), 1, row);
        searchSlotPane.add(statusBox, 2, row);
        
        // search work slot button
        row = 15;
        Button searchSlotBtn = new Button("Search");
        searchSlotBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        searchSlotBtn.setPrefWidth(80); 
        searchSlotBtn.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        searchSlotPane.add(hbox, 2, 11);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        searchSlotBtn.setOnAction(event -> {
            SearchWorkSlotC controller = new SearchWorkSlotC();

            // if checkbox is selected set the time strings
            String startTimeStr = (checkBox.isSelected()) ? startHour.getValue().toString() + ":" + startMinute.getValue().toString() + ":00" : "";
            String endTimeStr = (checkBox.isSelected()) ? endHour.getValue().toString() + ":" + endMinute.getValue().toString() + ":00" : "";
            // set the date and status strings
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";
            String status = (statusBox.getValue() != null) ? statusBox.getValue() : "";
            // check if both are empty
            if(date.equals("") && status.equals("") && !checkBox.isSelected()) {
                Alert alert = new Alert(AlertType.INFORMATION, "Please enter information to search by!");
                alert.showAndWait();
                return;
            }
            // check if the date is empty while the time is not
            if(date.equals("") && checkBox.isSelected()) {
                Alert alert = new Alert(AlertType.ERROR, "Please select a date!");
                alert.showAndWait();
                return;
            }
            // try to search work slot
            Vector<Map<String, String>> data = controller.searchWorkSlot(date, startTimeStr, endTimeStr, status);
            if(data.size() > 0) {
                // display work slots
                TableView<Map<String, String>> tableView = new ViewWorkSlotB().createResultTableView(data);
                searchSlotPane.add(tableView, 2, 12);
            }
            else {
                Alert alert = new Alert(AlertType.ERROR, "Unable to find that work slot!");
                alert.showAndWait();
            }
        });

        // back button
        row = 15;
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
        searchSlotPane.add(backBtn, 2, 11);
        GridPane.setHalignment(backBtn, HPos.RIGHT);
        hbox.getChildren().addAll(backBtn, searchSlotBtn);
        backBtn.setOnAction(event -> {
            CafeApp.changeScene(CafeApp.getLandingScene());
        });

        Scene scene = new Scene(searchSlotPane, 500, 500);
        return scene;
    }

    public Scene getSearchWorkSlotUpdateBidScene(Map<String, String> bid) {
        GridPane searchSlotPane = new GridPane();
		searchSlotPane.setAlignment(Pos.CENTER);
		searchSlotPane.setHgap(3);
		searchSlotPane.setVgap(3);
        searchSlotPane.setStyle("-fx-background-color: #DBDBDB;");
        
        int row = 1;
        // display message
        Label msgLabel = new Label("Search for the workslot\nyou want to change to: ");

        msgLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        searchSlotPane.add(msgLabel, 1, row);
        
        // date
        row = 4;
        DatePicker datePicker = new DatePicker();
        // layout
        searchSlotPane.add(new Label("Date: "), 1, row);
        searchSlotPane.add(datePicker, 2, row);
        
        // start time
        row = 7;
        Spinner startHour = new Spinner<>(0, 23, 0);
        startHour.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        Spinner startMinute = new Spinner<>(0, 59, 0);
        startMinute.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        // start time layout
        HBox startTimeHBox = new HBox(5);
        startTimeHBox.getChildren().addAll(startHour, new Label(":"), startMinute);
        Label startLabel = new Label("Start Time: ");
        searchSlotPane.add(startLabel, 1, row);
        searchSlotPane.add(startTimeHBox, 2, row);
        
        // end time
        row = 8;
        Spinner endHour = new Spinner<>(0, 23, 0);
        endHour.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        Spinner endMinute = new Spinner<>(0, 59, 0);
        endMinute.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        // end time layout
        HBox endTimeHBox = new HBox(5);
        endTimeHBox.getChildren().addAll(endHour, new Label(":"), endMinute);
        Label endLabel = new Label("End Time: ");
        searchSlotPane.add(endLabel, 1, row);
        searchSlotPane.add(endTimeHBox, 2, row);
        
        // spinner check box
        row = 6;
        // hide the time hbox by default
        startLabel.setVisible(false);
        startTimeHBox.setVisible(false);
        endLabel.setVisible(false);
        endTimeHBox.setVisible(false);
        // create the check box
        CheckBox checkBox = new CheckBox();
        checkBox.setOnAction(event -> {
            startLabel.setVisible(!startLabel.isVisible());
            startTimeHBox.setVisible(!startTimeHBox.isVisible());
            endLabel.setVisible(!endLabel.isVisible());
            endTimeHBox.setVisible(!endTimeHBox.isVisible());
        });
        // add to layout
        searchSlotPane.add(new Label("Search by time: "), 1, row);
        searchSlotPane.add(checkBox, 2, row);

        // slot status
        row = 9;
        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("", "Available", "Closed");
        searchSlotPane.add(new Label("Availability status: "), 1, row);
        searchSlotPane.add(statusBox, 2, row);
        
        // search work slot button
        row = 15;
        Button searchSlotBtn = new Button("Search");
       // searchSlotPane.add(searchSlotBtn, 2, row);
        searchSlotBtn.setPrefWidth(80); 
        searchSlotBtn.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        searchSlotPane.add(hbox, 2, row);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        searchSlotBtn.setOnAction(event -> {
            SearchWorkSlotC controller = new SearchWorkSlotC();

            // if checkbox is selected set the time strings
            String startTimeStr = (checkBox.isSelected()) ? startHour.getValue().toString() + ":" + startMinute.getValue().toString() + ":00" : "";
            String endTimeStr = (checkBox.isSelected()) ? endHour.getValue().toString() + ":" + endMinute.getValue().toString() + ":00" : "";
            // set the date and status strings
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";
            String status = (statusBox.getValue() != null) ? statusBox.getValue() : "";
            // check if both are empty
            if(date.equals("") && status.equals("") && !checkBox.isSelected()) {
                Alert alert = new Alert(AlertType.INFORMATION, "Please enter information to search by!");
                alert.showAndWait();
                return;
            }
            // check if the date is empty while the time is not
            if(date.equals("") && checkBox.isSelected()) {
                Alert alert = new Alert(AlertType.ERROR, "Please select a date!");
                alert.showAndWait();
                return;
            }
            // try to create work slot
            Vector<Map<String, String>> data = controller.searchWorkSlot(date, startTimeStr, endTimeStr, status);
            if(data.size() > 0) {
                // display work slots
                TableView<Map<String, String>> tableView = new ViewWorkSlotB().createUpdateBidTableView(bid, data);
                searchSlotPane.add(tableView, 2, 12);
            }
            else {
                Alert alert = new Alert(AlertType.ERROR, "Unable to find that work slot!");
                alert.showAndWait();
            }
        });

        // back button
        row = 15;
        Button backBtn = new Button("Back");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
        searchSlotPane.add(backBtn, 2, row);
        GridPane.setHalignment(backBtn, HPos.RIGHT);
        hbox.getChildren().addAll(backBtn, searchSlotBtn);
        backBtn.setOnAction(event -> {
            CafeApp.changeScene(CafeApp.getLandingScene());
        });

        Scene scene = new Scene(searchSlotPane, 500, 500);
        return scene;
    }

}
