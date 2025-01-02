package main;
// java imports
import java.util.HashMap;
import java.util.Map;

import CSUpdateUserAccount.CSUpdateUserAccountB;
// java fx imports
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

// class imports
import LogIn.LoginB;
import LogOut.LogoutB;
import SACreateUserAccount.SACreateUserAccountB;
import SASearchUserAccount.SASearchUserAccountB;
import SACreateUserProfile.SACreateUserProfileB;
import SASearchUserProfile.SASearchUserProfileB;
import CSUpdateUserAccount.CSUpdateUserAccountB;
import SearchBid.SearchBidB;
import SearchWorkSlot.SearchWorkSlotB;
import CreateWorkSlot.CreateWorkSlotB;

public class CafeApp extends Application {
    private static Stage appWindow;

    private static Map<String, String> userInfo = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        appWindow = primaryStage;

        LoginB loginBoundary = new LoginB();

        appWindow.setTitle("Cafe Staff Management System");
		appWindow.setScene(loginBoundary.getLoginScene());
		appWindow.show();
    }

    public static void changeScene(Scene scene) {
        appWindow.setScene(scene);
        appWindow.show();
    }

	public static Scene getLandingScene() {
		GridPane postLoginPane = new GridPane();
		postLoginPane.setAlignment(Pos.CENTER);

		// show the landing page to the user 

		postLoginPane.setHgap(6);
		postLoginPane.setVgap(6);
		postLoginPane.setStyle("-fx-background-color: #DBDBDB;");

		Text welcomeText = new Text("Welcome, ");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		Text uidText = new Text(userInfo.get("uid"));
		uidText.setFont(Font.font("Arial", FontPosture.ITALIC, 24));

		HBox welcomeBox = new HBox(welcomeText, uidText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		postLoginPane.add(welcomeBox, 1, 0);

		Button logOutButton = new Button("Log Out");
		postLoginPane.add(logOutButton, 1, 12);

		GridPane.setHalignment(logOutButton, HPos.CENTER); 
		logOutButton.setStyle("-fx-background-color: #F2CB56; -fx-effect: dropshadow(three-pass-box, #D9B342, 5, 0.5, 0, 0);");
		logOutButton.setPrefWidth(170); 
		logOutButton.setPrefHeight(25);

		if (userInfo.get("userProfile").equals("System Administrator")) {
			Button createUserAccountButton = new Button("Create User Account");
			postLoginPane.add(createUserAccountButton, 1, 2);

			createUserAccountButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			createUserAccountButton.setPrefWidth(170); 
			createUserAccountButton.setPrefHeight(25);

			Button searchUserAccountButton = new Button("Search User Account"); 
			postLoginPane.add(searchUserAccountButton, 1, 4); 

			searchUserAccountButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			searchUserAccountButton.setPrefWidth(170); 
			searchUserAccountButton.setPrefHeight(25);
			
			Button createUserProfileButton = new Button("Create User Profile"); 
			postLoginPane.add(createUserProfileButton, 1, 6);

			createUserProfileButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			createUserProfileButton.setPrefWidth(170); 
			createUserProfileButton.setPrefHeight(25);
			
			Button searchUserProfileButton = new Button("Search User Profile"); 
			postLoginPane.add(searchUserProfileButton, 1, 8);

			searchUserProfileButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			searchUserProfileButton.setPrefWidth(170); 
			searchUserProfileButton.setPrefHeight(25);

			createUserAccountButton.setOnAction(event -> {
					// Instantiate a create user account boundary
					SACreateUserAccountB saCreateUserAccountB = new SACreateUserAccountB();
					Scene saCreateUserAccountScene = saCreateUserAccountB.getSACreateUserAccountScene();
	  				changeScene(saCreateUserAccountScene);
			});

			searchUserAccountButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					// Instantiate a search user account boundary
					SASearchUserAccountB saSearchUserAccountB = new SASearchUserAccountB();
					Scene saSearchUserAccountScene = saSearchUserAccountB.getSASearchUserAccountScene();
	  				changeScene(saSearchUserAccountScene);
				}
			});

			createUserProfileButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Create User Profile Button Pressed");
					// Instantiate a create user profile boundary
					SACreateUserProfileB saCreateUserProfileB = new SACreateUserProfileB();
					Scene saCreateUserProfileScene = saCreateUserProfileB.getSACreateUserProfileScene();
	  				changeScene(saCreateUserProfileScene);
				}
			});

			searchUserProfileButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Search User Profile Button Pressed");
					// Instantiate a search user profile boundary
					SASearchUserProfileB saSearchUserProfileB = new SASearchUserProfileB();
					Scene saSearchUserProfileScene = saSearchUserProfileB.getSASearchUserProfileScene();
	  				changeScene(saSearchUserProfileScene);
				}
			});
		} else if (userInfo.get("userProfile").equals("Cafe Owner")) {
			Button createWorkSlotButton = new Button("Create Work Slot");
			postLoginPane.add(createWorkSlotButton, 1, 2);
			createWorkSlotButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			createWorkSlotButton.setPrefWidth(170); 
			createWorkSlotButton.setPrefHeight(25);

			Button searchWorkSlotBtn = new Button("Search Work Slot");
			postLoginPane.add(searchWorkSlotBtn, 1, 4);
			searchWorkSlotBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			searchWorkSlotBtn.setPrefWidth(170); 
			searchWorkSlotBtn.setPrefHeight(25);

			createWorkSlotButton.setOnAction(event -> {
				// change scene when button is pressed
				changeScene(new CreateWorkSlotB().getCreateWorkSlotScene());
			});

			// change to search work slot scene when button is pressed
			searchWorkSlotBtn.setOnAction(event -> {
				changeScene(new SearchWorkSlotB().getSearchWorkSlotScene());
			});
		} else if (userInfo.get("userProfile").equals("Cafe Manager")) {
				// search for a useraccount
				Button searchUserButton = new Button("Search User");
				postLoginPane.add(searchUserButton, 1, 4);
				searchUserButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
				searchUserButton.setPrefWidth(170); 
				searchUserButton.setPrefHeight(25);
				// change to search bid scene when button is pressed
				searchUserButton.setOnAction(event -> {
					changeScene(new SASearchUserAccountB().getSASearchUserAccountScene());
				});
				// search for a bid
				Button searchWorkslotButton = new Button("Search Work Slot");
				postLoginPane.add(searchWorkslotButton, 1, 6);
				searchWorkslotButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
				searchWorkslotButton.setPrefWidth(170); 
				searchWorkslotButton.setPrefHeight(25);
				// change to search bid scene when button is pressed
				searchWorkslotButton.setOnAction(event -> {
					changeScene(new SearchWorkSlotB().getSearchWorkSlotScene());
				});
				// search for a bid
				Button searchBidButton = new Button("Search Bid");
				postLoginPane.add(searchBidButton, 1, 8);
				searchBidButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
				searchBidButton.setPrefWidth(170); 
				searchBidButton.setPrefHeight(25);
				// change to search bid scene when button is pressed
				searchBidButton.setOnAction(event -> {
					changeScene(new SearchBidB().getSearchBidScene());
				});

		} else if (userInfo.get("userProfile").equals("Cafe Staff")) {
			/* If the cafe staff has yet to indicate a cafe role, force them to do it before
			granting them access to the landing page. Otherwise, display the landing page normally. */
			if (userInfo.get("cafeRole") == null) {
				// Instantiate an indicate cafe role boundary
				CSUpdateUserAccountB csUpdateUserAccountB = new CSUpdateUserAccountB();
				Scene csUpdateUserAccountScene = csUpdateUserAccountB.getCSUpdateUserAccountScene();
				//changeScene(csIndicateCafeRoleScene);
				return csUpdateUserAccountScene;
			} else {
				// search for a workslot
				Button searchWorkSlotBtn = new Button("Search for workslot");
				postLoginPane.add(searchWorkSlotBtn, 1, 4);
				searchWorkSlotBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
				searchWorkSlotBtn.setPrefWidth(170); 
				searchWorkSlotBtn.setPrefHeight(25);
				GridPane.setHalignment(searchWorkSlotBtn, HPos.CENTER); 

				// change to search work slot scene when button is pressed
				searchWorkSlotBtn.setOnAction(event -> {
					changeScene(new SearchWorkSlotB().getSearchWorkSlotScene());
				});

				// search for a bid
				Button searchBidButton = new Button("Search Bid");
				postLoginPane.add(searchBidButton, 1, 6);
				searchBidButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
				searchBidButton.setPrefWidth(170); 
				searchBidButton.setPrefHeight(25);
				GridPane.setHalignment(searchBidButton, HPos.CENTER); 
				// change to search bid scene when button is pressed
				searchBidButton.setOnAction(event -> {
					changeScene(new SearchBidB().getSearchBidScene());
				});

			}

		}

		logOutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// Instantiate a log out boundary
				LogoutB logoutB = new LogoutB();

				// Call the only method in the boundary, which displays the log in page
				logoutB.displayLoginPage();
			}
		});

    	Scene landingScene = new Scene(postLoginPane, 500, 500);

		return landingScene;
	}

    
    public static String getUserInfo(String fieldName) {
        return userInfo.get(fieldName);
    }

    public static void updateUserInfo(String key, String value) {
    	userInfo.replace(key, value);
    }

    public static void addUserInfo(String fieldName, String info) {
    	userInfo.clear();
        userInfo.put(fieldName, info);
    }

    public static void addUserInfo(Map<String, String> map) {
    	userInfo.clear();
        userInfo.putAll(map);
    }
}
