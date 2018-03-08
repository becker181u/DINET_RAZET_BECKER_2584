package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.events.MouseEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class MainViewController implements Initializable {
	
	@FXML
	private Label score_un, score_deux;
	
	@FXML
	private Button button_annuler_un, button_annuler_deux, button_start, button_reset;
	
	private Mainfx mainApp;
	
	public MainViewController() {}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	@FXML
	private void annuler_coup(MouseEvent event) {
		System.out.println("annuler coup");
	}
	
	@FXML
	private void lancer_partie(MouseEvent event) {
		System.out.println("lancer partie");
	}
	
	@FXML
	private void reset_partie(MouseEvent event) {
		System.out.println("reset_partie");
	}
	
	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Mainfx mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }

}
