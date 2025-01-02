package SASearchUserAccount;

import javafx.geometry.HPos;
// JavaFX imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

// Java imports
import java.util.Vector;
import java.util.Map;

// Class imports
import SAViewUserAccount.SAViewUserAccountB;
import main.CafeApp;

public class SASearchUserAccountB {

	private static Stage secondaryAppWindow;

	public SASearchUserAccountB() {
		secondaryAppWindow = new Stage();
	}

	public Scene getSASearchUserAccountScene() {
		GridPane saSearchUserAccountPane = new GridPane();
		saSearchUserAccountPane.setAlignment(Pos.CENTER);
		//pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		saSearchUserAccountPane.setHgap(6);
		saSearchUserAccountPane.setVgap(6);
		saSearchUserAccountPane.setStyle("-fx-background-color: #DBDBDB;");

		Text welcomeText = new Text("Search user account");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		saSearchUserAccountPane.add(welcomeBox, 1, 0);

		Label userIDLabel = new Label("Enter Name: ");
		saSearchUserAccountPane.add(userIDLabel, 0, 4);

		TextField userIDTextField = new TextField();
		saSearchUserAccountPane.add(userIDTextField, 1, 4);

		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backButton.setPrefWidth(80); 
        backButton.setPrefHeight(25);
        saSearchUserAccountPane.add(backButton, 0, 8);

		Button searchButton = new Button("Search");
		searchButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        searchButton.setPrefWidth(80); 
        searchButton.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        saSearchUserAccountPane.add(hbox, 1, 8);
        hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.getChildren().addAll(backButton, searchButton);

		backButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
      		public void handle(ActionEvent e) {
    			CafeApp.changeScene(CafeApp.getLandingScene());
    		}
    	});

		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
      		public void handle(ActionEvent e) {
      			String name = userIDTextField.getText();

      			if (name.equals("")) {
      				System.out.println("Invalidity detected");
					Alert alert = new Alert(AlertType.WARNING, "No information entered", ButtonType.CLOSE);
					alert.show();
					return;
				}

				// Instantiate a search account controller
				SASearchUserAccountC saSearchUserAccountC = new SASearchUserAccountC();
				Vector<Map<String, String>> infoMaps = new Vector<>();
				// Call the only method in the controller, which will return a vector of maps
				infoMaps = saSearchUserAccountC.searchUserAccount(name);

				ObservableList<Map<String, String>> data = FXCollections.observableList(infoMaps);

	        	TableView<Map<String, String>> table = new TableView<Map<String, String>>();
				table.setFocusTraversable(false);
				
		        Label label = new Label("Search Results");
		        label.setFont(new Font("Arial", 20));

		        Button backButton = new Button("Back");
				backButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
				backButton.setPrefWidth(80); 
				backButton.setPrefHeight(25);

		        backButton.setOnAction(new EventHandler<ActionEvent>() {
		    		@Override
		      		public void handle(ActionEvent e) {
		    			CafeApp.changeScene(CafeApp.getLandingScene());
		    		}
		    	});

		        TableColumn uidCol = new TableColumn("User ID");
		        uidCol.setMinWidth(100);
		        uidCol.setCellValueFactory((Callback) new MapValueFactory("uid"));
		 
		        TableColumn nameCol = new TableColumn("Name");
		        nameCol.setMinWidth(200);
		        nameCol.setCellValueFactory((Callback) new MapValueFactory("name"));
		 
		        table.setItems(data);
		        table.getColumns().addAll(uidCol, nameCol);
		 
		        final VBox vbox = new VBox();
		        vbox.setSpacing(5);
		        vbox.setPadding(new Insets(10, 0, 0, 10));
		        vbox.getChildren().addAll(label, table, backButton);

		        Scene postSearchScene = new Scene(new Group(), 500, 500);
				postSearchScene.setFill(Color.web("#DBDBDB"));

		        ((Group) postSearchScene.getRoot()).getChildren().addAll(vbox);

		        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		        	System.out.println(table.getSelectionModel().getSelectedItem().get("uid").toString());
		        	System.out.println(table.getSelectionModel().getSelectedItem().get("name").toString());
		        	// Instantiate a view user account boundary
      				SAViewUserAccountB saViewUserAccountB = new SAViewUserAccountB();
      				Scene saViewUserAccountScene = saViewUserAccountB.getSAViewUserAccountScene(table.getSelectionModel().getSelectedItem().get("uid").toString());
      				CafeApp.changeScene(saViewUserAccountScene);

      				//saViewUserAccountB.start(secondaryAppWindow);
		            /*if (newSelection != null) {
		                tableview2.getSelectionModel().clearSelection();
		            }*/
		        });
		 
		 		CafeApp.changeScene(postSearchScene);
      		}
    	});

    	Scene saSearchUserAccountScene = new Scene(saSearchUserAccountPane, 500, 500);

    	return saSearchUserAccountScene;
	}

	public static void changeSecondaryWindowScene(Scene secondaryWindowScene) {
        secondaryAppWindow.setScene(secondaryWindowScene);
        secondaryAppWindow.show();
    }
}
