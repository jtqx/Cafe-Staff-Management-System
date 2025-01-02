package DeleteWorkSlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import SearchWorkSlot.SearchWorkSlotB;
import ViewWorkSlot.ViewWorkSlotB;
import javafx.geometry.HPos;
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

public class DeleteWorkSlotB {

    public Scene getDeleteWorkSlotScene(Map<String, String> workslot) {
        GridPane deleteSlotPane = new GridPane();
		deleteSlotPane.setAlignment(Pos.CENTER);
		deleteSlotPane.setHgap(3);
		deleteSlotPane.setVgap(3);
        deleteSlotPane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("Delete work slot");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		deleteSlotPane.add(welcomeBox, 1, 0);

        
        // display date of workslot
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(workslot.get("date"));
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        deleteSlotPane.add(new Label("Date: "), 1, 5);
        deleteSlotPane.add(new Label(orgDate.format(formattedDate)), 2, 5);

        // display time duration of workslot
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(workslot.get("startTime"));
        LocalTime orgEndTime = LocalTime.parse(workslot.get("endTime"));
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        deleteSlotPane.add(new Label("Duration: "), 1, 6);
        deleteSlotPane.add(new Label(orgStartTime.format(formattedTime) + " - " + orgEndTime.format(formattedTime)), 2, 6);

        // display the max staff amount
        // layout
        // cashiers
        deleteSlotPane.add(new Label("Cashiers: "), 1, 8);
        deleteSlotPane.add(new Label(workslot.get("maxCashier")), 2, 8);
        // chefs
        deleteSlotPane.add(new Label("Chefs: "), 1, 9);
        deleteSlotPane.add(new Label(workslot.get("maxChef")), 2, 9);
        // waiters
        deleteSlotPane.add(new Label("Waiters: "), 1, 10);
        deleteSlotPane.add(new Label(workslot.get("maxWaiter")), 2, 10);

        // display the current status of the workslot -> available or not
        // get the status string of the workslot
        String statusStr = (workslot.get("availabilityStatus").equals("1")) ? "Available" : "Closed";
        // layout
        deleteSlotPane.add(new Label("Status: "), 1, 11);
        deleteSlotPane.add(new Label(statusStr), 2, 11);

        // add empty rows
        deleteSlotPane.add(new Label(""), 1, 12);
        deleteSlotPane.add(new Label(""), 1, 15);
        // display message
        deleteSlotPane.add(new Label("This workslot will be deleted forever!"), 1, 13);
        deleteSlotPane.add(new Label("Are you sure?"), 1, 14);

        // confirmation buttons
        Button confirmBtn = new Button("Confirm");
        deleteSlotPane.add(confirmBtn, 2, 16);
        confirmBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        confirmBtn.setPrefWidth(80); 
        confirmBtn.setPrefHeight(25);
        confirmBtn.setOnAction(event -> {
            // delete the work slot
            DeleteWorkSlotC controller = new DeleteWorkSlotC();
            boolean result = controller.DeleteWorkSlot(workslot.get("date"), workslot.get("startTime"), workslot.get("endTime"));
            if(result) {
                System.out.print("Deleted work slot successfully");
                // go back to search work slot scene
                CafeApp.changeScene(new SearchWorkSlotB().getSearchWorkSlotScene());
            }
            else {
                // show error
                Alert alert = new Alert(AlertType.ERROR, "Something went wrong. Unable to delete workslot!");
                alert.showAndWait();
                // go back to view scene
                CafeApp.changeScene(new ViewWorkSlotB().getViewASlotScene(workslot));
            }
        });
        
        Button cancelBtn = new Button("Cancel");
        deleteSlotPane.add(cancelBtn, 1, 16);
        cancelBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        cancelBtn.setPrefWidth(80); 
        cancelBtn.setPrefHeight(25);
        GridPane.setHalignment(cancelBtn, HPos.RIGHT);
        cancelBtn.setOnAction(event -> {
            // go back to view scene
            CafeApp.changeScene(new ViewWorkSlotB().getViewASlotScene(workslot));
        });

        Scene scene = new Scene(deleteSlotPane, 500, 500);
        return scene;
    }

}
