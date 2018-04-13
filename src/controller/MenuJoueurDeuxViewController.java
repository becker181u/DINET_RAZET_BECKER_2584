package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Partie;

public class MenuJoueurDeuxViewController implements Initializable {

	@FXML
	private Button btn_choix_humain, btn_choix_random, btn_choix_IA;
	
	private Partie partie;

	private Mainfx mainApp;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public void initModel(Partie partie) {
		if (this.partie != null) {
            throw new IllegalStateException("Partie can only be initialized once");
        }
		this.partie = partie;
	}
	
	@FXML
	public void choisirHumain() {
		this.partie.choixJoueur(2,1);
	}
	
	@FXML
	public void choisirRandom() {
		this.partie.choixJoueur(2,2);
	}
	
	@FXML
	public void choisirIA() {
		this.partie.choixJoueur(2,3);
	}

	public void setMainApp(Mainfx mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }

}
