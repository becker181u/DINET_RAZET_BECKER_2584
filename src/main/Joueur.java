package main;

public abstract class Joueur {

	protected Grille grille;
	
	protected int score;
	
	public Joueur() {
		this.grille = new Grille();
        boolean b = grille.nouvelleCase();
        b = grille.nouvelleCase();
        score=0;
		
	}
	
	public int saisieDplcmt(int joueur){
		return 0;
	}
	
	
	
	
	
}
