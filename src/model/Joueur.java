package model;

public abstract class Joueur {

	public Grille grille;
	
	private int score;
	
	/**
	 * constructeur de la classe Joueur
	 */
	public Joueur() {
		this.grille = new Grille(this);
        boolean b = grille.nouvelleCase();
        b = grille.nouvelleCase();
        score=0;
	}
	
	/**
	 * méthode vide d'annulation de coup
	 */
	public void annulerCoup() {
		
	}
	
	/**
	 * méthode jamais appelée pour la saisie du déplacement
	 * @param joueur numéro du joueur, 1 ou 2
	 * @return la direction choisie
	 */
	public int saisieDplcmt(int joueur){
		return 0;
	}

	/**
	 * get score
	 * @return score du joueur courant 
	 */
	public int getScore() {
		return score;
	}

	/**
	 * set score
	 * @param score du joueur courant
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * get scoresecondaire du joueur courant(variable IA)
	 * @return scoreSec
	 */
	public int getScoreSec() {
		return 0;
	}

	/**
	 * set score secondaire (variable IA)
	 * @param score2 du joueur courant
	 */
	public void setScoreSec(int score2) {
		
	}
	
	
	
	
	
}
