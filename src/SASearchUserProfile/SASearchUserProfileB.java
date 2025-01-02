package SASearchUserProfile;

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
import java.util.HashMap;
import java.util.Map;

// Class imports
import SAViewUserProfile.SAViewUserProfileB;
import main.CafeApp;

public class SASearchUserProfileB {

	private static Stage secondaryAppWindow;

	public SASearchUserProfileB() {
		secondaryAppWindow = new Stage();
	}

	public Scene getSASearchUserProfileScene() {
		GridPane saSearchUserProfilePane = new GridPane();
		saSearchUserProfilePane.setAlignment(Pos.CENTER);
	
		saSearchUserProfilePane.setHgap(6);
		saSearchUserProfilePane.setVgap(6);
		saSearchUserProfilePane.setStyle("-fx-background-color: #DBDBDB;");

		Text welcomeText = new Text("Search user profile");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		saSearchUserProfilePane.add(welcomeBox, 1, 0);

		Label profileLabel = new Label("Enter User Profile: ");
		saSearchUserProfilePane.add(profileLabel, 0, 4);

		TextField profileTextField = new TextField();
		saSearchUserProfilePane.add(profileTextField, 1, 4);

		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backButton.setPrefWidth(80); 
        backButton.setPrefHeight(25);
        saSearchUserProfilePane.add(backButton, 0, 8);

		Button searchButton = new Button("Search");
		searchButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        searchButton.setPrefWidth(80); 
        searchButton.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        saSearchUserProfilePane.add(hbox, 1, 8);
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
      			String profile = profileTextField.getText();

      			if (profile.equals("")) {
      				System.out.println("Invalidity detected");
					Alert alert = new Alert(AlertType.WARNING, "No information entered", ButtonType.CLOSE);
					alert.show();
					return;
				}

				// Instantiate a search user profile controller
				SASearchUserProfileC saSearchUserProfileC = new SASearchUserProfileC();
				
				Map<String, String> infoMap = null;

				// Call the only method in the controller, which will return a map of profile information
				infoMap = saSearchUserProfileC.searchUserProfile(profile);

				// Create a vector of maps which will be used in the TableView
				Vector<Map<String, String>> infoMapVector = new Vector<Map<String, String>>();

				infoMapVector.add(infoMap);

				ObservableList<Map<String, String>> data = FXCollections.observableList(infoMapVector);

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

		        TableColumn roleCol = new TableColumn("Role");
		        roleCol.setMinWidth(100);
		        roleCol.setCellValueFactory((Callback) new MapValueFactory("role"));
		 
		        TableColumn descCol = new TableColumn("Description");
		        descCol.setMinWidth(200);
				descCol.setCellValueFactory((Callback) new MapValueFactory("description"));
		 
		        table.setItems(data);
		        table.getColumns().addAll(roleCol, descCol);
		 
		        final VBox vbox = new VBox();
		        vbox.setSpacing(5);
		        vbox.setPadding(new Insets(10, 0, 0, 10));
		        vbox.getChildren().addAll(label,table,backButton);

		        Scene postSearchScene = new Scene(new Group(), 500, 500);
				postSearchScene.setFill(Color.web("#DBDBDB"));

		        ((Group) postSearchScene.getRoot()).getChildren().addAll(vbox);

		        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		        	// Instantiate a view user account boundary
      				SAViewUserProfileB saViewUserProfileB = new SAViewUserProfileB();
      				Scene saViewUserProfileScene = saViewUserProfileB.getSAViewUserProfileScene(table.getSelectionModel().getSelectedItem().get("role").toString());
					CafeApp.changeScene(saViewUserProfileScene);
      				//saViewUserAccountB.start(secondaryAppWindow);
		            /*if (newSelection != null) {
		                tableview2.getSelectionModel().clearSelection();
		            }*/
		        });
		 
		 		CafeApp.changeScene(postSearchScene);
      		}
    	});

    	Scene saSearchUserProfileScene = new Scene(saSearchUserProfilePane, 500, 500);

    	return saSearchUserProfileScene;
	}

	public static void changeSecondaryWindowScene(Scene secondaryWindowScene) {
        secondaryAppWindow.setScene(secondaryWindowScene);
        secondaryAppWindow.show();
    }
}
