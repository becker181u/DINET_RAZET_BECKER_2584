package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Partie;

public class MenuJoueurUnViewController implements Initializable {
	
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
		System.out.println("init menu un");
		this.partie = partie;
	}
	
	@FXML
	public void choisirHumain(MouseEvent e) {
		System.out.println("humain");
		this.partie.choixJoueur(1,1);
		mainApp.showMenuJoueurDeuxView();
		this.newStage(e);
	}
	
	@FXML
	public void choisirRandom(MouseEvent e) {
		System.out.println("Random");
		this.partie.choixJoueur(1,2);
		mainApp.showMenuJoueurDeuxView();
		this.newStage(e);
	}
	
	@FXML
	public void choisirIA(MouseEvent e) {
		System.out.println("IA");
		this.partie.choixJoueur(1,3);
		this.newStage(e);
	}
	
	public void newStage(MouseEvent e){
		try {
		Stage nouveauStage;
		FXMLLoader loader = new FXMLLoader();
		
        nouveauStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        loader.setLocation(Mainfx.class.getResource("../view/menuJoueurDeuxView.fxml"));
        AnchorPane menuJoueurDeuxView = (AnchorPane) loader.load();
        
        MenuJoueurDeuxViewController menuDeuxController = loader.getController();
        menuDeuxController.initModel(partie);
        menuDeuxController.setMainApp(mainApp);
        
        Scene scene = new Scene(menuJoueurDeuxView,800, 600);
        nouveauStage.setScene(scene);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void setMainApp(Mainfx mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }

}
