package ViewBid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Vector;

import DeleteBid.DeleteBidB;
import SearchBid.SearchBidB;
import SearchWorkSlot.SearchWorkSlotB;
import UpdateBid.UpdateBidB;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.CafeApp;

public class ViewBidB {
    int width = 500;
    int height = 500;

    public Scene getViewABidScene(Map<String, String> bid) {
        Scene scene = new Scene(getPageLayout(bid), width, height);
        return scene;
    }

    public Scene getViewABidScene(String date, String startTime, String endTime, String uid) {
        ViewBidC controller = new ViewBidC();
        Scene scene = new Scene(getPageLayout(controller.viewBid(date, startTime, endTime, uid)), width, height);
        return scene;
    }

    private GridPane getPageLayout(Map<String, String> data) {
        GridPane viewBidPane = new GridPane();
        viewBidPane.setAlignment(Pos.CENTER);
		viewBidPane.setHgap(3);
		viewBidPane.setVgap(3);
        viewBidPane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("View bid");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		viewBidPane.add(welcomeBox, 1, 0);


        // display date of bid
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(data.get("date"));
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        viewBidPane.add(new Label("Date: "), 1, 4);
        viewBidPane.add(new Label(orgDate.format(formattedDate)), 2, 4);

        // display time duration of bid
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(data.get("startTime"));
        LocalTime orgEndTime = LocalTime.parse(data.get("endTime"));
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        viewBidPane.add(new Label("Duration: "), 1, 6);
        viewBidPane.add(new Label(orgStartTime.format(formattedTime) + " - " + orgEndTime.format(formattedTime)), 2, 6);

        // display the creator of the bid
        viewBidPane.add(new Label("Created by: "), 1, 9);
        viewBidPane.add(new Label(data.get("uid")), 2, 9);
        
        // display the role of the creator
        viewBidPane.add(new Label("Role: "), 1, 10);
        viewBidPane.add(new Label(data.get("cafeRole")), 2, 10);

        // display the current status of the bid
        // layout
        viewBidPane.add(new Label("Status: "), 1, 11);
        viewBidPane.add(new Label(data.get("status")), 2, 11);

        // adding empty row
        viewBidPane.add(new Label(""), 1, 12);

        // back button
        Button backBtn = new Button("Back");
        // layout
        viewBidPane.add(backBtn, 1, 13);
        backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
        GridPane.setHalignment(backBtn, HPos.RIGHT);
        // button action
        backBtn.setOnAction(event -> {
            // go back to search scene
            CafeApp.changeScene(new SearchBidB().getSearchBidScene());
        });

        if(CafeApp.getUserInfo("userProfile") != null && CafeApp.getUserInfo("userProfile").equals("Cafe Staff")) {
            if(CafeApp.getUserInfo("uid") != null && CafeApp.getUserInfo("uid").equals(data.get("uid"))) {
                // update button
                Button updateBtn = new Button("Edit");
                // layout
                viewBidPane.add(updateBtn, 2, 13);
                updateBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
                updateBtn.setPrefWidth(80); 
                updateBtn.setPrefHeight(25);
                // button action
                updateBtn.setOnAction(event -> {
                    CafeApp.changeScene(new SearchWorkSlotB().getSearchWorkSlotUpdateBidScene(data));
                });
                
                // delete button
                Button deleteBtn = new Button("Delete");
                // layout
                viewBidPane.add(deleteBtn, 3, 13);
                deleteBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
                deleteBtn.setPrefWidth(80); 
                deleteBtn.setPrefHeight(25);
                
                // button action
                deleteBtn.setOnAction(event -> {
                    CafeApp.changeScene(new DeleteBidB().getDeleteBidScene(data));
                });
            }
        }
        
        if(CafeApp.getUserInfo("userProfile") != null && CafeApp.getUserInfo("userProfile").equals("Cafe Manager")) {
            if(data.get("status").equals("PENDING")) {
                // approve button
                Button approveBtn = new Button("Approve");
                // layout
                viewBidPane.add(approveBtn, 2, 13);
                approveBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
                approveBtn.setPrefWidth(80); 
                approveBtn.setPrefHeight(25);
                GridPane.setHalignment(approveBtn, HPos.RIGHT);

                //action 
                approveBtn.setOnAction(event -> {
                    CafeApp.changeScene(new UpdateBidB().getApproveBidScene(data));
                });
                
                // deny button
                Button denyBtn = new Button("Deny");
                // layout
                viewBidPane.add(denyBtn, 3, 13);
                denyBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
                denyBtn.setPrefWidth(80); 
                denyBtn.setPrefHeight(25);
                GridPane.setHalignment(denyBtn, HPos.RIGHT);
                //action 
                denyBtn.setOnAction(event -> {
                    CafeApp.changeScene(new UpdateBidB().getDenyBidScene(data));
                });
            }
        }

        return viewBidPane;
    }

    // Create TableView with data
    public TableView<Map<String, String>> createResultTableView(Vector<Map<String, String>> data) {
        // Convert Vector<Map<String, String>> to ObservableList
        ObservableList<Map<String, String>> observableList = FXCollections.observableArrayList(data);

        // Create TableView
        TableView<Map<String, String>> tableView = new TableView<>(observableList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_LAST_COLUMN);

        // Create columns based on the keys in the maps
        TableColumn<Map<String, String>, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().get("date")));
        tableView.getColumns().add(dateCol);

        TableColumn<Map<String, String>, String> startTimeCol = new TableColumn<>("Start Time");
        startTimeCol.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().get("startTime")));
        tableView.getColumns().add(startTimeCol);

        TableColumn<Map<String, String>, String> endTimeCol = new TableColumn<>("End Time");
        endTimeCol.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().get("endTime")));
        tableView.getColumns().add(endTimeCol);

        tableView.setRowFactory(tv -> {
            TableRow<Map<String, String>> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    // Handle row click
                    Map<String, String> rowData = row.getItem();
                    /*
                    // Print out rowData
                    for (Map.Entry<String, String> entry : rowData.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    */
                    // change to view bid scene
                    CafeApp.changeScene(new ViewBidB().getViewABidScene(rowData));
                }
            });
            return row;
        });
        return tableView;
    }
}
