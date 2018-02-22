package main;

public abstract class Joueur {

	protected Grille grille;
	
	
	public Joueur() {
		this.grille = new Grille();
        boolean b = grille.nouvelleCase();
        b = grille.nouvelleCase();
        System.out.println(grille);
		
	}
	
	
	
	
	
}
