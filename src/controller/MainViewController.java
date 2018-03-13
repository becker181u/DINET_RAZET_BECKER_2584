package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
	
	private void bougerCases(KeyEvent event) {
		switch (event.getCharacter()) {
		case "z":
			
			break;
		case "d":
			
			break;
		case "q":
			
			break;
		case "s":
			
			break;

		default:
			break;
		}
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
