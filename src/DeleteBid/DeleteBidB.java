package DeleteBid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import SearchBid.SearchBidB;
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

public class DeleteBidB {
    
    public Scene getDeleteBidScene(Map<String, String> bid) {
        GridPane deleteBidPane = new GridPane();
		deleteBidPane.setAlignment(Pos.CENTER);
		deleteBidPane.setHgap(3);
		deleteBidPane.setVgap(3);
        deleteBidPane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("Delete bid");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		deleteBidPane.add(welcomeBox, 1, 0);
        
        // display date of bid
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(bid.get("date"));
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        deleteBidPane.add(new Label("Date: "), 1, 5);
        deleteBidPane.add(new Label(orgDate.format(formattedDate)), 2, 5);

        // display time duration of bid
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(bid.get("startTime"));
        LocalTime orgEndTime = LocalTime.parse(bid.get("endTime"));
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        deleteBidPane.add(new Label("Duration: "), 1, 6);
        deleteBidPane.add(new Label(orgStartTime.format(formattedTime) + " - " + orgEndTime.format(formattedTime)), 2, 6);

        // display the creator of the bid
        deleteBidPane.add(new Label("Created by: "), 1, 7);
        deleteBidPane.add(new Label(bid.get("uid")), 2, 7);
        
        // display the role of the creator
        deleteBidPane.add(new Label("Role: "), 1, 8);
        deleteBidPane.add(new Label(bid.get("cafeRole")), 2, 8);

        // display the current status of the bid 
        // layout
        deleteBidPane.add(new Label("Status: "), 1, 9);
        deleteBidPane.add(new Label(bid.get("status")), 2, 9);

        // add empty rows
        deleteBidPane.add(new Label(""), 1, 11);
        deleteBidPane.add(new Label(""), 1, 15);
        // display message
        deleteBidPane.add(new Label("This bid will be deleted forever!"), 1, 13);
        deleteBidPane.add(new Label("Are you sure?"), 1, 14);

        // confirmation buttons
        Button confirmBtn = new Button("Confirm");
        deleteBidPane.add(confirmBtn, 2, 16);
        confirmBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        confirmBtn.setPrefWidth(80); 
        confirmBtn.setPrefHeight(25);
        
        confirmBtn.setOnAction(event -> {
            // delete the work slot
            DeleteBidC controller = new DeleteBidC();
            boolean result = controller.DeleteBid(bid.get("date"), bid.get("startTime"), bid.get("endTime"), bid.get("uid"));
            if(result) {
                System.out.print("Deleted work slot successfully");
                // go back to search work slot scene
                CafeApp.changeScene(new SearchBidB().getSearchBidScene());
            }
            else {
                // show error
                Alert alert = new Alert(AlertType.ERROR, "Something went wrong. Unable to delete bid!");
                alert.showAndWait();
                // go back to view scene
                CafeApp.changeScene(new ViewBidB().getViewABidScene(bid));
            }
        });
        
        Button cancelBtn = new Button("Cancel");
        deleteBidPane.add(cancelBtn, 1, 16);
        cancelBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        cancelBtn.setPrefWidth(80); 
        cancelBtn.setPrefHeight(25);
        GridPane.setHalignment(cancelBtn, HPos.RIGHT);
        cancelBtn.setOnAction(event -> {
            // go back to view scene
            CafeApp.changeScene(new ViewBidB().getViewABidScene(bid));
        });

        Scene scene = new Scene(deleteBidPane, 500, 500);
        return scene;
    }
}
