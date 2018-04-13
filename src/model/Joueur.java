package model;

public abstract class Joueur {

	public Grille grille;
	
	private int score;
	
	public Joueur() {
		this.grille = new Grille(this);
        boolean b = grille.nouvelleCase();
        b = grille.nouvelleCase();
        score=0;
	}
	
	public void annulerCoup() {
		
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

	public int getScoreSec() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setScoreSec(int score2) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
