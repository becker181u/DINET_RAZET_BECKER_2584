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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
	public void choisirHumain(MouseEvent e) {
		this.partie.choixJoueur(2,1);
		this.newStage(e);
	}
	
	@FXML
	public void choisirRandom(MouseEvent e) {
		this.partie.choixJoueur(2,2);
		this.newStage(e);
	}
	
	@FXML
	public void choisirIA(MouseEvent e) {
		this.partie.choixJoueur(2,3);
		this.newStage(e);
	}
	
	public void newStage(MouseEvent e) {
		try {
			Stage nouveauStage;
			FXMLLoader loader = new FXMLLoader();
			
	        nouveauStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	        loader.setLocation(Mainfx.class.getResource("../view/mainView.fxml"));
	        AnchorPane mainView = (AnchorPane) loader.load();
	        
	        MainViewController mainController  = loader.getController();
	        mainController.setMainApp(mainApp);
	        mainController.initModel(partie);
	        
	        Scene scene = new Scene(mainView);
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
