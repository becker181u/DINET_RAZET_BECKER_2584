package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Mainfx extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Duo 2543");
		
		initRootLayout();
		
		showMainView();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Mainfx.class.getResource("../view/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
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
            loader.setLocation(Mainfx.class.getResource("../view/mainView.fxml"));
            AnchorPane mainView = (AnchorPane) loader.load();

            // Set mainView into the center of root layout.
            rootLayout.setCenter(mainView);
            
         // Give the controller access to the main app.
            MainViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
