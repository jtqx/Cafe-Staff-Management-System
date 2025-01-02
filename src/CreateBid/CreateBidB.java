package CreateBid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ViewWorkSlot.ViewWorkSlotB;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.CafeApp;

public class CreateBidB {
    public Scene getCreateBidScene(String date, String startTime, String endTime) {
        
        GridPane createBidPane = new GridPane();
		createBidPane.setAlignment(Pos.CENTER);
		createBidPane.setHgap(3);
		createBidPane.setVgap(3);
        createBidPane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("Create Bid");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		createBidPane.add(welcomeBox, 1, 0);


        // display date of workslot
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(date);
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        createBidPane.add(new Label("Date: "), 1, 4);
        createBidPane.add(new Label(orgDate.format(formattedDate)), 2, 4);

        // display time duration of workslot
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(startTime);
        LocalTime orgEndTime = LocalTime.parse(endTime);
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        createBidPane.add(new Label("Duration: "), 1, 6);
        createBidPane.add(new Label(orgStartTime.format(formattedTime) + " - " + orgEndTime.format(formattedTime)), 2, 6);

        // get the staff role
        String roleStr = (CafeApp.getUserInfo("cafeRole") == null) ? "" : CafeApp.getUserInfo("cafeRole");
        // layout
        createBidPane.add(new Label("Your Role: "), 1, 8);
        createBidPane.add(new Label(roleStr), 2,8);

        // create button
        Button createBtn = new Button("Create");
        createBidPane.add(createBtn, 2, 10);
        createBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        createBtn.setPrefWidth(80); 
        createBtn.setPrefHeight(25);
        // create action
        createBtn.setOnAction(event -> {
            // check for cafeRole
            if(roleStr.equals("")) {
                Alert alert = new Alert(AlertType.ERROR, "Please indicate your job role first!");
                alert.showAndWait();
                return;
            }
            CreateBidC controlller = new CreateBidC();
            String uid = CafeApp.getUserInfo("uid");
            // check if the bid created by the same user already existed
            if(controlller.checkIfExist(date, startTime, endTime, uid)) {
                Alert alert = new Alert(AlertType.ERROR, "You have already bid for this slot!");
                alert.showAndWait();
                return;
            }
            // try to create bid
            if(controlller.createBid(date, startTime, endTime, uid, roleStr)) {
                // show successful message
                Alert alert = new Alert(AlertType.INFORMATION, "Bid created successfully!");
                alert.showAndWait();
                // go back to viewing the workslot
                CafeApp.changeScene(new ViewWorkSlotB().getViewASlotScene(date, startTime, endTime));
            }
            else {
                Alert alert = new Alert(AlertType.ERROR, "Unable to create bid!");
                alert.showAndWait();
            }
        });

        // back button
        Button backBtn = new Button("Back");
        createBidPane.add(backBtn, 1, 10);
        backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
        // back action 
        backBtn.setOnAction(event -> {
            // go back to viewing a workslot
            CafeApp.changeScene(new ViewWorkSlotB().getViewASlotScene(date, startTime, endTime));
        });

        Scene scene = new Scene(createBidPane, 500, 500);
        return scene;
    }
}
