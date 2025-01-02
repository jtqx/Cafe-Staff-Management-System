package UpdateBid;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import ViewBid.ViewBidB;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.CafeApp;

public class UpdateBidB {
    
    public Scene getUpdateBidScene(Map<String, String> bid, String date, String startTime, String endTime) {
        GridPane updateBidPane = new GridPane();
		updateBidPane.setAlignment(Pos.CENTER);
		updateBidPane.setHgap(3);
		updateBidPane.setVgap(3);
        updateBidPane.setStyle("-fx-background-color: #DBDBDB;");
        
        Text welcomeText = new Text("Update bid");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		updateBidPane.add(welcomeBox, 2, 0);


        // display date of bid
        int row = 1;
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(bid.get("date"));
        LocalDate newDate = LocalDate.parse(date);
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        updateBidPane.add(new Label("Date: "), 0, row+5);
        updateBidPane.add(new Label("From " + orgDate.format(formattedDate) + " To " + newDate.format(formattedDate)), 2, row + 5);
        //updateBidPane.add(new Label("To " + newDate.format(formattedDate)), 3, row + 1);

        // display time duration of bid
        row = 4;
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(bid.get("startTime"));
        LocalTime orgEndTime = LocalTime.parse(bid.get("endTime"));
        LocalTime newStartTime = LocalTime.parse(startTime);
        LocalTime newEndTime = LocalTime.parse(endTime);
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        updateBidPane.add(new Label("Duration: "), 0, row+5);
        updateBidPane.add(new Label("From " + orgStartTime.format(formattedTime) + " - "  +  orgEndTime.format(formattedTime) + " To " + newStartTime.format(formattedTime) + " - " + newEndTime.format(formattedTime)), 2, row + 5);
        //updateBidPane.add(new Label("To " + newStartTime.format(formattedTime) + " - " + newEndTime.format(formattedTime)), 3, row + 1);

        // display message
        row = 8;
        updateBidPane.add(new Label("Are you sure?"), 2, row+4);

        // buttons
        row = 10;
        // cancel button
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        cancelBtn.setPrefWidth(80); 
        cancelBtn.setPrefHeight(25);
        updateBidPane.add(cancelBtn, 3, row+4);
        // cancel action
        cancelBtn.setOnAction(event -> {
            // go back to viewing this bid
            CafeApp.changeScene(new ViewBidB().getViewABidScene(bid));
        });

        // save changes button
        Button saveBtn = new Button("Save");
        saveBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        saveBtn.setPrefWidth(80); 
        saveBtn.setPrefHeight(25);
        updateBidPane.add(saveBtn, 4, row+4);
        // save action
        saveBtn.setOnAction(event -> {
            // try to update the bid
            UpdateBidC controller = new UpdateBidC();
            boolean success;
            if(controller.checkIfExist(date, startTime, endTime, bid.get("uid"))) {
                Alert alert = new Alert(AlertType.ERROR, "Bid already existed!");
                alert.showAndWait();
                return;
            }
            try {
                success = controller.updateBid(bid.get("date"), bid.get("startTime"), bid.get("endTime"), bid.get("uid"), date, startTime, endTime);
                // handle the result of the update
                if(success) {
                    System.out.println("Updated the bid!");
                    // go back to view bid scene
                    CafeApp.changeScene(new ViewBidB().getViewABidScene(date, startTime, endTime, bid.get("uid")));
                }
                else {
                    Alert alert = new Alert(AlertType.ERROR, "Unable to update the bid!");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(updateBidPane, 500, 500);
        return scene;
    }

    public Scene getApproveBidScene(Map<String, String> bid) {
        GridPane updateBidpane = new GridPane();
        updateBidpane.setAlignment(Pos.CENTER);
		updateBidpane.setHgap(3);
		updateBidpane.setVgap(3);
        updateBidpane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("Pending for confirmation");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		updateBidpane.add(welcomeBox, 2, 0);
        
        // display date of bid
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(bid.get("date"));
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        updateBidpane.add(new Label("Date: "), 1, 1);
        updateBidpane.add(new Label(orgDate.format(formattedDate)), 2, 1);

        // display time duration of bid
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(bid.get("startTime"));
        LocalTime orgEndTime = LocalTime.parse(bid.get("endTime"));
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        updateBidpane.add(new Label("Duration: "), 1, 2);
        updateBidpane.add(new Label(orgStartTime.format(formattedTime) + " - " + orgEndTime.format(formattedTime)), 2, 2);

        // display the creator of the bid
        updateBidpane.add(new Label("Created by: "), 1, 5);
        updateBidpane.add(new Label(bid.get("uid")), 2, 5);
        
        // display the role of the creator
        updateBidpane.add(new Label("Role: "), 1, 6);
        updateBidpane.add(new Label(bid.get("cafeRole")), 2, 6);

        // display the message
        updateBidpane.add(new Label("This action cannot be reversed!"), 2, 8);
        updateBidpane.add(new Label("Approve this bid?"), 2, 9);

        // confirm button
        Button confirmBtn = new Button("Confirm");
        // layout
        updateBidpane.add(confirmBtn, 3, 11);
        confirmBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        confirmBtn.setPrefWidth(80); 
        confirmBtn.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        updateBidpane.add(hbox, 2, 11);
        hbox.setAlignment(Pos.CENTER_RIGHT);   
        GridPane.setHalignment(confirmBtn, HPos.RIGHT);  
       
        // action
        confirmBtn.setOnAction(event -> {
            UpdateBidC controller = new UpdateBidC();
            boolean success = controller.updateBid(bid.get("date"), bid.get("startTime"), bid.get("endTime"), bid.get("uid"), bid.get("cafeRole"), "APPROVED");
            if(success) {
                Alert alert = new Alert(AlertType.INFORMATION, "Successfully approved bid!");
                alert.showAndWait();
                CafeApp.changeScene(CafeApp.getLandingScene());
            }
        });

        // back button
        Button backBtn = new Button("Back");
        // layout
        updateBidpane.add(backBtn, 2, 11);
        backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
         hbox.getChildren().addAll(backBtn, confirmBtn);
        GridPane.setHalignment(backBtn, HPos.RIGHT);

        //action
        backBtn.setOnAction(event -> {
            CafeApp.changeScene(new ViewBidB().getViewABidScene(bid));
        });

        Scene scene = new Scene(updateBidpane, 500, 500);
        return scene;
    }

    public Scene getDenyBidScene(Map<String, String> bid) {
        GridPane updateBidpane = new GridPane();
        updateBidpane.setAlignment(Pos.CENTER);
		updateBidpane.setHgap(3);
		updateBidpane.setVgap(3);
        updateBidpane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("Pending for confirmation");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		updateBidpane.add(welcomeBox, 2, 0);
        
        // display date of bid
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(bid.get("date"));
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        updateBidpane.add(new Label("Date: "), 1, 1);
        updateBidpane.add(new Label(orgDate.format(formattedDate)), 2, 1);

        // display time duration of bid
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(bid.get("startTime"));
        LocalTime orgEndTime = LocalTime.parse(bid.get("endTime"));
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        updateBidpane.add(new Label("Duration: "), 1, 2);
        updateBidpane.add(new Label(orgStartTime.format(formattedTime) + " - " + orgEndTime.format(formattedTime)), 2, 2);

        // display the creator of the bid
        updateBidpane.add(new Label("Created by: "), 1, 5);
        updateBidpane.add(new Label(bid.get("uid")), 2, 5);
        
        // display the role of the creator
        updateBidpane.add(new Label("Role: "), 1, 6);
        updateBidpane.add(new Label(bid.get("cafeRole")), 2, 6);

        // display the message
        updateBidpane.add(new Label("This action cannot be reversed!"), 2, 8);
        updateBidpane.add(new Label("Deny this bid?"), 2, 9);

        // confirm button
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        confirmBtn.setPrefWidth(80); 
        confirmBtn.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        updateBidpane.add(hbox, 2, 11);
        hbox.setAlignment(Pos.CENTER_RIGHT);   
        GridPane.setHalignment(confirmBtn, HPos.RIGHT);  
         
        // layout
        confirmBtn.setOnAction(event -> {
            UpdateBidC controller = new UpdateBidC();
            boolean success = controller.updateBid(bid.get("date"), bid.get("startTime"), bid.get("endTime"), bid.get("uid"), bid.get("cafeRole"), "DENIED");
            if(success) {
                Alert alert = new Alert(AlertType.INFORMATION, "Successfully denied bid!");
                alert.showAndWait();
                CafeApp.changeScene(CafeApp.getLandingScene());
            }
        });
        
        // back button
        Button backBtn = new Button("Back");
        // layout
        updateBidpane.add(backBtn, 1, 11);
        backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
        hbox.getChildren().addAll(backBtn, confirmBtn);
        GridPane.setHalignment(backBtn, HPos.RIGHT);
        //action
        backBtn.setOnAction(event -> {
            CafeApp.changeScene(new ViewBidB().getViewABidScene(bid));
        });

        Scene scene = new Scene(updateBidpane, 500, 500);
        return scene;
    }
}
