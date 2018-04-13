package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
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
	
	public void initModel(Partie partie) {
		this.partie = partie;
		//generation de la grille
		String valeur = "";
		for(Case c : partie.joueur1.grille.getGrille()) {
			valeur = String.valueOf(c.getValeur());
			Pane pane = new Pane(new Label(valeur));
			pane.setMinWidth(10);
			pane.setMinHeight(10);
			pane.setBackground(new Background(new BackgroundFill(Paint.valueOf("red"), null, null)));
			grilleJoueur1.add(pane, c.getY(), c.getX());
		}
		for(Case c : partie.joueur2.grille.getGrille()) {
			valeur = String.valueOf(c.getValeur());
			Pane pane = new Pane(new Label(valeur));
			pane.setBackground(new Background(new BackgroundFill(Paint.valueOf("yellow"), null, null)));
			grilleJoueur2.add(pane, c.getY(), c.getX());
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
		System.out.println("key "+event.getCharacter()+ " code "+ event.getText());
		switch (event.getText()) {
		case "z":
			partie.joueur1.grille.lanceurDeplacerCases(HAUT);
			partie.joueur1.grille.nouvelleCase();
			break;
		case "d":
			partie.joueur1.grille.lanceurDeplacerCases(DROITE);
			partie.joueur1.grille.nouvelleCase();
			break;
		case "q":
			partie.joueur1.grille.lanceurDeplacerCases(GAUCHE);
			partie.joueur1.grille.nouvelleCase();
			break;
		case "s":
			partie.joueur1.grille.lanceurDeplacerCases(BAS);
			partie.joueur1.grille.nouvelleCase();
			break;
		case "i":
			partie.joueur2.grille.lanceurDeplacerCases(HAUT);
			partie.joueur2.grille.nouvelleCase();
			break;
		case "l":
			partie.joueur2.grille.lanceurDeplacerCases(DROITE);
			partie.joueur2.grille.nouvelleCase();
			break;
		case "j":
			partie.joueur2.grille.lanceurDeplacerCases(GAUCHE);
			partie.joueur2.grille.nouvelleCase();
			break;
		case "k":
			partie.joueur2.grille.lanceurDeplacerCases(BAS);
			partie.joueur2.grille.nouvelleCase();
			break;
		default:
			break;
		}
		System.out.println(partie.joueur1.grille.toHTML());
		System.out.println("Update");
		this.update();
		
	}
	
	public void update() {
		String valeur = "";
		this.grilleJoueur1 = new GridPane();
		this.grilleJoueur2 = new GridPane();
		for(Case c : this.partie.joueur1.grille.getGrille()) {
			valeur = String.valueOf(c.getValeur());
			Pane pane = new Pane(new Label(valeur));
			this.grilleJoueur1.add(pane, c.getY(), c.getX());
		}
		for(Case c : this.partie.joueur2.grille.getGrille()) {
			System.out.println("case "+c.getValeur());
			valeur = String.valueOf(c.getValeur());
			Pane pane = new Pane(new Label(valeur));
			System.out.println(pane.getAccessibleText());
			this.grilleJoueur2.add(pane, c.getY(), c.getX());
		}
		this.score_un.setText(String.valueOf(this.partie.joueur1.getScore()));
		this.score_deux.setText(String.valueOf(this.partie.joueur2.getScore()));
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
