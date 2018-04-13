package model;

import java.util.Scanner;


public class Partie implements Parametres{
	
	public Joueur joueur1, joueur2;
	private Scanner sc;
	
	/**
	 * constructeur sans interface
	 */
	public Partie() {
		this.joueur1 = saisieJoueur(1);
		this.joueur2 = saisieJoueur(2);	
	}
	
	/**
	 * constructeur avec interface
	 * @param javafx si interface ou non
	 */
	public Partie(boolean javafx){
		this.joueur1 = new Humain();
		this.joueur2 = new Humain();
	}
	
	/**
	 * methode utiliser pour sélectionner les joueurs sans interface
	 * @param x joueur 1 ou 2
	 * @return le joueur créé
	 */
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
	/**
	 * methode utiliser pour sélectionner les joueurs avec l'interface
	 * @param numero du joueur
	 * @param choix (humain, ordinateur IA)
	 */
	public void choixJoueur(int numero, int choix) {
		Joueur joueur;
		switch(choix) {
			case 1 :
				joueur = new Humain();
				break;
			case 2 :
				joueur = new Ordinateur();
				joueur.saisieDplcmt(2);
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
	
	/**
	 * méthode de déroulement de la partie, avec affichage des grilles, choix des directions, création des nouvelles casses sur les grilles
	 */
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

	/**
	 * méthode testant si la partie est finie ou non
	 */
    public void gameOver() {
    	System.out.println(joueur1.grille);
		System.out.println(joueur2.grille);
    	System.out.println("La partie est finie");
    	
    	if(joueur1.grille.partieFinie()){
    		System.out.println("Joueur 1 a perdu !");
    	}else if(joueur2.grille.partieFinie()){
    		System.out.println("Joueur 2 a perdu !");
    	}else if (joueur1.grille.partieFinie() && joueur2.grille.partieFinie()){
    		System.out.println("égalité !");
    	}
    	
    	
    	System.out.println("Le score du joueur 1 est " + this.joueur1.getScore());
    	System.out.println("Le score du joueur 2 est " + this.joueur2.getScore());
        System.exit(1);
    }
	

}
