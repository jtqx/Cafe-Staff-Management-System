package ViewWorkSlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Vector;

import javax.swing.text.LabelView;

import CreateBid.CreateBidB;
import DeleteWorkSlot.DeleteWorkSlotB;
import SearchWorkSlot.SearchWorkSlotB;
import UpdateBid.UpdateBidB;
import UpdateWorkSlot.UpdateWorkSlotB;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.CafeApp;

public class ViewWorkSlotB {
    int width = 500;
    int height = 500;

    public Scene getViewASlotScene(Map<String, String> workslot) {
        Scene scene = new Scene(getPageLayout(workslot), width, height);
        return scene;
    }

    public Scene getViewASlotScene(String date, String startTime, String endTime) {
        ViewWorkSlotC controller = new ViewWorkSlotC();
        Scene scene = new Scene(getPageLayout(controller.getWorkSlot(date, startTime, endTime)), width, height);
        return scene;
    }

    public Scene getStaffViewSlotScene(Map<String, String> bid, Map<String, String> workslot) {
        // go back to landing scene
        if(CafeApp.getUserInfo("userProfile") == null && !CafeApp.getUserInfo("userProfile").equals("Cafe Staff")) {
            return CafeApp.getLandingScene();
        }

        GridPane viewSlotPane = new GridPane();
        viewSlotPane.setAlignment(Pos.CENTER);
		viewSlotPane.setHgap(3);
		viewSlotPane.setVgap(3);
        viewSlotPane.setStyle("-fx-background-color: #DBDBDB;");

        Text welcomeText = new Text("Update bid");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		viewSlotPane.add(welcomeBox, 1, 0);

        // display date of workslot
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(workslot.get("date"));
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        viewSlotPane.add(new Label("Date: "), 0, 1);
        viewSlotPane.add(new Label(orgDate.format(formattedDate)), 1, 1);

        // display time duration of workslot
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(workslot.get("startTime"));
        LocalTime orgEndTime = LocalTime.parse(workslot.get("endTime"));
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        viewSlotPane.add(new Label("Duration: "), 0, 2);
        viewSlotPane.add(new Label(orgStartTime.format(formattedTime) + " - " + orgEndTime.format(formattedTime)), 1, 2);

        // display the max staff amount
        // get the current staff allocated
        int currCashier = new ViewWorkSlotC().getAssignedCashierCount(workslot.get("date"), workslot.get("startTime"), workslot.get("endTime"));
        int currChef = new ViewWorkSlotC().getAssignedChefCount(workslot.get("date"), workslot.get("startTime"), workslot.get("endTime"));
        int currWaiter = new ViewWorkSlotC().getAssignedWaiterCount(workslot.get("date"), workslot.get("startTime"), workslot.get("endTime"));
        // layout
        // cashiers
        viewSlotPane.add(new Label("Cashiers: "), 0, 4);
        viewSlotPane.add(new Label(currCashier + " / " + workslot.get("maxCashier")), 1, 4);
        // chefs
        viewSlotPane.add(new Label("Chefs: "), 0, 5);
        viewSlotPane.add(new Label(currChef + " / " + workslot.get("maxChef")), 1, 5);
        // waiters
        viewSlotPane.add(new Label("Waiters: "), 0, 6);
        viewSlotPane.add(new Label(currWaiter + " / " + workslot.get("maxWaiter")), 1, 6);

        // display the current status of the workslot -> available or not
        // get the status string of the workslot
        String statusStr = (workslot.get("availabilityStatus").equals("1")) ? "Available" : "Closed";
        // layout
        viewSlotPane.add(new Label("Status: "), 0, 7);
        viewSlotPane.add(new Label(statusStr), 1, 7);

        // back button
        Button backBtn = new Button("Back");
        // layout
        viewSlotPane.add(backBtn, 1, 11); //11
        backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
        GridPane.setHalignment(backBtn, HPos.RIGHT);
        // button action
        backBtn.setOnAction(event -> {
            // go back to search scene
            CafeApp.changeScene(new SearchWorkSlotB().getSearchWorkSlotScene());
        });
        
        // update button
        Button updateBtn = new Button("Update");
        // layout
        updateBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        updateBtn.setPrefWidth(80); 
        updateBtn.setPrefHeight(25);
        viewSlotPane.add(updateBtn, 2, 11);
        // action
        updateBtn.setOnAction(event -> {
            if(workslot.get("availabilityStatus").equals("1")) {
                CafeApp.changeScene(new UpdateBidB().getUpdateBidScene(bid, workslot.get("date"), workslot.get("startTime"), workslot.get("endTime")));
            }
            else { 
                Alert alert = new Alert(AlertType.ERROR, "This workslot is closed");
                alert.showAndWait();
            }

        });

        Scene scene = new Scene(viewSlotPane, 500, 500);
        return scene;
    }

    private GridPane getPageLayout(Map<String, String> data) {
        GridPane viewSlotPane = new GridPane();
        viewSlotPane.setAlignment(Pos.CENTER);
		viewSlotPane.setHgap(3);
		viewSlotPane.setVgap(3);
        viewSlotPane.setStyle("-fx-background-color: #DBDBDB;");

        
        Text welcomeText = new Text("View workslot");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		viewSlotPane.add(welcomeBox, 1, 0);

        // display date of workslot
        // getting the proper date format
        LocalDate orgDate = LocalDate.parse(data.get("date"));
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // layout
        viewSlotPane.add(new Label("Date: "), 1, 4);
        viewSlotPane.add(new Label(orgDate.format(formattedDate)), 2, 4);

        // display time duration of workslot
        // getting the proper time format
        LocalTime orgStartTime = LocalTime.parse(data.get("startTime"));
        LocalTime orgEndTime = LocalTime.parse(data.get("endTime"));
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm");
        // layout
        viewSlotPane.add(new Label("Duration: "), 1, 6);
        viewSlotPane.add(new Label(orgStartTime.format(formattedTime) + " - " + orgEndTime.format(formattedTime)), 2, 6);

        // display the max staff amount
        // get the current staff allocated
        int currCashier = new ViewWorkSlotC().getAssignedCashierCount(data.get("date"), data.get("startTime"), data.get("endTime"));
        int currChef = new ViewWorkSlotC().getAssignedChefCount(data.get("date"), data.get("startTime"), data.get("endTime"));
        int currWaiter = new ViewWorkSlotC().getAssignedWaiterCount(data.get("date"), data.get("startTime"), data.get("endTime"));
        // layout
        // cashiers
        viewSlotPane.add(new Label("Cashiers: "), 1, 8);
        viewSlotPane.add(new Label(currCashier + " / " + data.get("maxCashier")), 2, 8);
        // chefs
        viewSlotPane.add(new Label("Chefs: "), 1, 9);
        viewSlotPane.add(new Label(currChef + " / " + data.get("maxChef")), 2, 9);
        // waiters
        viewSlotPane.add(new Label("Waiters: "), 1, 10);
        viewSlotPane.add(new Label(currWaiter + " / " + data.get("maxWaiter")),2, 10);

        // display the current status of the workslot -> available or not
        // get the status string of the workslot
        String statusStr = (data.get("availabilityStatus").equals("1")) ? "Available" : "Closed";
        // layout
        viewSlotPane.add(new Label("Status: "), 1, 11);
        viewSlotPane.add(new Label(statusStr),2, 11);

        // adding empty row
        viewSlotPane.add(new Label(""), 3, 12);

        // back button
        Button backBtn = new Button("Back");
        // layout
        backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(25);
        GridPane.setHalignment(backBtn, HPos.RIGHT);  
        viewSlotPane.add(backBtn, 1, 13);

        
        // button action
        backBtn.setOnAction(event -> {
            // go back to search scene
            CafeApp.changeScene(new SearchWorkSlotB().getSearchWorkSlotScene());
        });

        if(CafeApp.getUserInfo("userProfile") != null && CafeApp.getUserInfo("userProfile").equals("Cafe Owner")) {
            // update button
            Button updateBtn = new Button("Edit");
            // layout
            updateBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
            updateBtn.setPrefWidth(80); 
            updateBtn.setPrefHeight(25);
            viewSlotPane.add(updateBtn, 2, 13);
            // button action
            updateBtn.setOnAction(event -> {
                CafeApp.changeScene(new UpdateWorkSlotB().getUpdateWorkSlotScene(data));
            });
            
            // delete button
            Button deleteBtn = new Button("Delete");
            // layout
            deleteBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
            deleteBtn.setPrefWidth(80); 
            deleteBtn.setPrefHeight(25);
            //GridPane.setHalignment(deleteBtn, HPos.LEFT);
            viewSlotPane.add(deleteBtn, 3, 13);
            // button action
            deleteBtn.setOnAction(event -> {
                CafeApp.changeScene(new DeleteWorkSlotB().getDeleteWorkSlotScene(data));
            });
        }
        
        if(CafeApp.getUserInfo("userProfile") != null && CafeApp.getUserInfo("userProfile").equals("Cafe Staff")) {
            if(data.get("availabilityStatus").equals("1")) {
                // create bid button
                Button createBidBtn = new Button("Create Bid");

                // layout
                viewSlotPane.add(createBidBtn, 2, 13);
                createBidBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
                createBidBtn.setPrefWidth(80); 
                createBidBtn.setPrefHeight(25);
                GridPane.setHalignment(createBidBtn, HPos.LEFT);
                // button action
                createBidBtn.setOnAction(event -> {
                    CafeApp.changeScene(new CreateBidB().getCreateBidScene(data.get("date"), data.get("startTime"), data.get("endTime")));
                });
            }
        }

        return viewSlotPane;
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
                    // change to view work slot scene
                    CafeApp.changeScene(getViewASlotScene(rowData));
                }
            });
            return row;
        });
        return tableView;
    }

    // Create TableView with data
    public TableView<Map<String, String>> createUpdateBidTableView(Map<String, String> bid, Vector<Map<String, String>> workslot) {
        // Convert Vector<Map<String, String>> to ObservableList
        ObservableList<Map<String, String>> observableList = FXCollections.observableArrayList(workslot);

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
                    // change to view work slot scene
                    CafeApp.changeScene(getStaffViewSlotScene(bid, rowData));
                }
            });
            return row;
        });
        return tableView;
    }
}
