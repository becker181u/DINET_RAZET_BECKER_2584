package model;

import java.util.Scanner;


public class Partie implements Parametres{
	
	public Joueur joueur1, joueur2;
	private Scanner sc;
	
	//constructeur pour pas d'interface
	public Partie() {
		this.joueur1 = saisieJoueur(1);
		this.joueur2 = saisieJoueur(2);	
	}
	
	//constructeur pour une interface
	public Partie(boolean javafx){
		this.joueur1 = new Humain();
		this.joueur2 = new Humain();
	}
	
	//methode utiliser pour jouer sans interface
	private Joueur saisieJoueur(int x){
		Joueur joueur;
		sc = new Scanner(System.in);
		boolean test = true;
		
		System.out.println("Veuillez sélectionner le type du joueur "+x+" : 'h' pour humain, 'o' pour ordinateur, 'i' pour ia");
		String s = sc.nextLine();
		
		while(test){
			if(!(s.equals("h") || s.equals("o") || s.equals("i"))){
				System.out.println("Mauvaise sélection, recommencez");
				System.out.println("Veuillez sélectionner le type du joueur "+x+" : 'h' pour humain, 'o' pour ordinateur, 'i' pour ia");
				s = sc.nextLine();
			}else{
				test = false;
			}
		}
	
		switch (s) {
		case "h":
			joueur = new Humain();
			break;
		case "o":
			joueur = new Ordinateur();
			break;
		default:
			joueur = new IA();
			break;
		}
		
		return joueur;
	}
	
	//methode utiliser pour l'interface
	public void choixJoueur(int numero, int choix) {
		Joueur joueur;
		switch(choix) {
			case 1 :
				joueur = new Humain();
				break;
			case 2 :
				joueur = new Ordinateur();
				break;
			case 3 : 
				joueur = new IA();
				break;
			default :
				System.out.println("default");
				joueur = new Humain();
				break;
		}
		if(numero == 1)this.joueur1 = joueur;else this.joueur2 = joueur;
	}
	
	
	public void dplcmt(){
		
		while(!joueur1.grille.partieFinie() && !joueur2.grille.partieFinie()){
			
			System.out.println(joueur1.grille);
			System.out.println(joueur2.grille);
			
			this.joueur1.saisieDplcmt(1);
			this.joueur2.saisieDplcmt(2);
			
			this.joueur1.grille.nouvelleCase();
			this.joueur2.grille.nouvelleCase();
		}
		
		gameOver(); 
	}

	
    public void gameOver() {
    	System.out.println("La partie est finie");
    	System.out.println("Le score du joueur 1 est " + this.joueur1.grille.getValeurMax());
    	System.out.println("Le score du joueur 2 est " + this.joueur1.grille.getValeurMax());
        System.exit(1);
    }
	

}
