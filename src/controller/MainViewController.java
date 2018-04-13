package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Case;
import model.Parametres;
import model.Partie;

public class MainViewController implements Initializable, Parametres {
	
	@FXML
	private Label score_un, score_deux;
	
	@FXML
	private Button button_annuler_un, button_annuler_deux, button_start, button_reset;
	
	@FXML
	private GridPane grilleJoueur1, grilleJoueur2;
	
	private Mainfx mainApp;
	
	private Partie partie;
	
	public MainViewController(Partie partie) {
		this.partie = partie;
		//generation de la grille
		String valeur = "";
		for(Case c : partie.joueur1.grille.getGrille()) {
			valeur = String.valueOf(c.getValeur());
			Pane pane = new Pane(new Label(valeur));
			grilleJoueur1.add(pane, c.getY(), c.getX());
		}
		for(Case c : partie.joueur2.grille.getGrille()) {
			valeur = String.valueOf(c.getValeur());
			Pane pane = new Pane(new Label(valeur));
			grilleJoueur1.add(pane, c.getY(), c.getX());
		}
	}
	
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
	
	@FXML
	private void bougerCases(KeyEvent event) {
		switch (event.getCharacter()) {
		case "z":
			partie.joueur1.grille.lanceurDeplacerCases(HAUT);
			break;
		case "d":
			partie.joueur1.grille.lanceurDeplacerCases(DROITE);
			break;
		case "q":
			partie.joueur1.grille.lanceurDeplacerCases(GAUCHE);
			break;
		case "s":
			partie.joueur1.grille.lanceurDeplacerCases(BAS);
			break;
		case "i":
			partie.joueur2.grille.lanceurDeplacerCases(HAUT);
			break;
		case "l":
			partie.joueur2.grille.lanceurDeplacerCases(DROITE);
			break;
		case "j":
			partie.joueur2.grille.lanceurDeplacerCases(GAUCHE);
			break;
		case "k":
			partie.joueur2.grille.lanceurDeplacerCases(BAS);
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
