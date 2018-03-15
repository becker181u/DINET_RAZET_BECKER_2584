package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Partie;

public class MenuViewController implements Initializable {
	
	@FXML
	private Button button_choisir_humain, button_choisir_random, button_choisir_IA;
	
	private Partie partie;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public MenuViewController(){
		
	}
	
	public MenuViewController(Partie partie) {
		partie = new Partie(true);
	}
	
	@FXML
	public void choisirHumain() {
		this.partie.choixJoueur(1,1);
		this.choisirSecondJoueur();
	}
	
	@FXML
	public void choisirRandom() {
		this.partie.choixJoueur(1,2);
		this.choisirSecondJoueur();
	}
	
	@FXML
	public void choisirIA() {
		this.partie.choixJoueur(1,3);
		this.choisirSecondJoueur();
	}
	
	private void choisirSecondJoueur() {
		new MenuViewController(partie);
	}

}
