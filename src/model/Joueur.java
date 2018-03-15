package model;

public abstract class Joueur {

	protected Grille grille;
	
	protected int score;
	
	public Joueur() {
		this.grille = new Grille(this);
        boolean b = grille.nouvelleCase();
        b = grille.nouvelleCase();
        score=0;
	}
	
	public int saisieDplcmt(int joueur){
		return 0;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	
	
}
