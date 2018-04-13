package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Partie;

public class Mainfx extends Application {
	
	BorderPane root;
	Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
		
		BorderPane root = new BorderPane();
		

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Mainfx.class.getResource("../view/menuJoueurUnView.fxml"));
        AnchorPane menuJoueurUnView = (AnchorPane) loader.load();
        root.setCenter(menuJoueurUnView);
        MenuJoueurUnViewController menuUnController = loader.getController();
        menuUnController.setMainApp(this);

        Partie partie = new Partie(true);
        menuUnController.initModel(partie);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	public void showMenuJoueurDeuxView() {
		try {
		root = new BorderPane();
		primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		
        loader.setLocation(Mainfx.class.getResource("../view/menuJoueurDeuxView.fxml"));
        AnchorPane menuJoueurDeuxView = (AnchorPane) loader.load();
        root.setCenter(menuJoueurDeuxView);
        MenuJoueurDeuxViewController menuDeuxController = loader.getController();
        menuDeuxController.setMainApp(this);

        Partie partie = new Partie(true);
        menuDeuxController.initModel(partie);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainfx.class.getResource("../view/rootLayout.fxml"));
            root = (BorderPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the mainView inside the root layout.
     */
    public void showMainView() {
        try {
            // Load mainView.
            FXMLLoader loader = new FXMLLoader();
            
            
            //loader.setLocation(Mainfx.class.getResource("../view/mainView.fxml"));
            //AnchorPane mainView = (AnchorPane) loader.load();

            // Set mainView into the center of root layout.
            //rootLayout.setCenter(mainView);
            
         // Give the controller access to the main app.
            
            
            
            loader.setLocation(Mainfx.class.getResource("../view/menuJoueurUnView.fxml"));
            AnchorPane menuJoueurUnView = (AnchorPane) loader.load();
            root.setCenter(menuJoueurUnView);
            MenuJoueurUnViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
