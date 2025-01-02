package SearchBid;

import java.util.Map;
import java.util.Vector;

import ViewBid.ViewBidB;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.CafeApp;

public class SearchBidB {
    public Scene getSearchBidScene() {
        GridPane searchBidPane = new GridPane();
		searchBidPane.setAlignment(Pos.CENTER);
		searchBidPane.setHgap(3);
		searchBidPane.setVgap(3);
        searchBidPane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("Search bid");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		searchBidPane.add(welcomeBox, 1, 0);

        int row = 4;
        // date
        DatePicker datePicker = new DatePicker();
        // layout
        searchBidPane.add(new Label("Date: "), 1, row);
        searchBidPane.add(datePicker, 2, row);
        
        // start time
        row = 6;
        Spinner startHour = new Spinner<>(0, 23, 0);
        startHour.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        Spinner startMinute = new Spinner<>(0, 59, 0);
        startMinute.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        // start time layout
        HBox startTimeHBox = new HBox(5);
        startTimeHBox.getChildren().addAll(startHour, new Label(":"), startMinute);
        Label startLabel = new Label("Start Time: ");
        searchBidPane.add(startLabel, 1, row);
        searchBidPane.add(startTimeHBox, 2, row);
        
        // end time
        row = 7;
        Spinner endHour = new Spinner<>(0, 23, 0);
        endHour.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        Spinner endMinute = new Spinner<>(0, 59, 0);
        endMinute.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        // end time layout
        HBox endTimeHBox = new HBox(5);
        endTimeHBox.getChildren().addAll(endHour, new Label(":"), endMinute);
        Label endLabel = new Label("End Time: ");
        searchBidPane.add(endLabel, 1, row);
        searchBidPane.add(endTimeHBox, 2, row);
        
        // search by uid
        row = 9;
        // uid textfield
        TextField uidField = new TextField(CafeApp.getUserInfo("uid")); 
        // check for user profile
        if(CafeApp.getUserInfo("userProfile") != null && CafeApp.getUserInfo("userProfile").equals("Cafe Staff")) {
            // uid textfield
            // layout
            searchBidPane.add(new Label("UID: "), 1, row);
            searchBidPane.add(uidField, 2, row);
            
            // "made by me" check box
            row = 8;
            // set the uid field to non-editable
            uidField.setDisable(true);
            // create the check box
            CheckBox uidCheckBox = new CheckBox();
            uidCheckBox.setSelected(true);
            uidCheckBox.setOnAction(event -> {
                uidField.setDisable(!uidField.isDisabled());
                if(uidField.isDisabled()) uidField.setText(CafeApp.getUserInfo("uid"));
            });
            // add to layout
            searchBidPane.add(new Label("Made by me"), 1, row);
            searchBidPane.add(uidCheckBox, 2, row);
        }

        // spinner check box
        row = 5;
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
        searchBidPane.add(new Label("Search by time"), 1, row);
        searchBidPane.add(checkBox, 2, row);

        // slot status
        row = 10;
        ComboBox<String> statusBox = new ComboBox<>();
        statusBox.getItems().addAll("", "APPROVED", "DENIED", "PENDING");
        searchBidPane.add(new Label("Status: "), 1, row);
        searchBidPane.add(statusBox, 2, row);
        
        // search bid button
        row = 14;
        Button searchSlotBtn = new Button("Search");
        //searchBidPane.add(searchSlotBtn, 2, row);
        searchSlotBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        searchSlotBtn.setPrefWidth(80); 
        searchSlotBtn.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        searchBidPane.add(hbox, 2, 11);
        hbox.setAlignment(Pos.CENTER_RIGHT);   
        GridPane.setHalignment(searchSlotBtn, HPos.RIGHT);  
        searchSlotBtn.setOnAction(event -> {
            SearchBidC controller = new SearchBidC();

            // if checkbox is selected set the time strings
            String startTimeStr = (checkBox.isSelected()) ? startHour.getValue().toString() + ":" + startMinute.getValue().toString() + ":00" : "";
            String endTimeStr = (checkBox.isSelected()) ? endHour.getValue().toString() + ":" + endMinute.getValue().toString() + ":00" : "";
            // set the date and status strings
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";
            String status = (statusBox.getValue() != null) ? statusBox.getValue() : "";
            // check if the date is empty while the time is not
            if(date.equals("") && checkBox.isSelected()) {
                Alert alert = new Alert(AlertType.ERROR, "Please select a date!");
                alert.showAndWait();
                return;
            }
            // try to search for bid
            Vector<Map<String, String>> data = new Vector<>();
            if(CafeApp.getUserInfo("userProfile") != null && CafeApp.getUserInfo("userProfile").equals("Cafe Staff")) {
                data = controller.searchBid(date, startTimeStr, endTimeStr, uidField.getText(), status);
            }
            else if(CafeApp.getUserInfo("userProfile") != null && CafeApp.getUserInfo("userProfile").equals("Cafe Manager")) {
                data = controller.searchBid(date, startTimeStr, endTimeStr, "", status);
            }
            else {
                return;
            }
            if(data.size() > 0) {
                // display bids
                TableView<Map<String, String>> tableView = new ViewBidB().createResultTableView(data);
                searchBidPane.add(tableView, 2, 12);
            }
            else {
                Alert alert = new Alert(AlertType.ERROR, "Unable to find that bid!");
                alert.showAndWait();
            }
        });

        // back button
        row = 14;
        Button backBtn = new Button("Back");
        searchBidPane.add(backBtn, 1, row);
        backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
        //searchBidPane.add(backBtn, 2, 11);
        hbox.getChildren().addAll(backBtn, searchSlotBtn);
        GridPane.setHalignment(backBtn, HPos.RIGHT);
        backBtn.setOnAction(event -> {
            CafeApp.changeScene(CafeApp.getLandingScene());
        });

        Scene scene = new Scene(searchBidPane, 500, 500);
        return scene;
    }
}
