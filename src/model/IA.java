package model;

public class IA extends Joueur implements Parametres{
	
	private int scoreSec;
	//true = forcer le gauche-droite
	private boolean GDouHB = true;
	//boolean pour forcer l'alternance des coups
	private boolean GouD = false;


	/**
	 * constructeur de la classe IA
	 */
	public IA(){
		super();

	}
	
	/**
	 * get le score secondaire, pour l'ia
	 * @return scoreSec
	 */
	public int getScoreSec(){
		return this.scoreSec;
	}
	
	/**
	 * Set le score secondaire
	 * @param score 2 int scoreSecondaire
	 */
	public void setScoreSec(int score2){
		this.scoreSec = score2;
	}
	
	
	/**
	 * méthode de saisie de déplacement
	 * @param joueur numéro du joueur
	 * @return la direction choisie
	 */
	public int saisieDplcmt(int joueur){
		boolean test, test2;
		boolean boucle = false;
		int direction1 = 0, direction2 = 0;
		/**int scoreMin = 100000000;
		int dirFinal = 0;**/
		
		
		this.GouD = !this.GouD;
		
		
		while(!boucle){
			
			if(this.GDouHB){
				if(this.GouD){
					direction1 = -2;
					direction2 = 2;
				}else{
					direction1 = 2;
					direction2 = -2;
				}
			}else{
				if(this.GouD){
					direction1 = -1;
					direction2 = 1;
				}else{
					direction1 = 1;
					direction2 = -1;
				}
			}
			
			test = this.grille.lanceurDeplacerCasesIa(direction1);
			test2 = this.grille.lanceurDeplacerCasesIa(direction2);
			
			if(test){
				boucle = true;
			}else{
				if(test2){
					this.GouD = !this.GouD;
				}else{
					this.GDouHB = !this.GDouHB;
				}
			}
		}
		
		System.out.println(direction1);
		this.grille.lanceurDeplacerCases(direction1);
		
		return direction1;
		
		
		

		
		
/**
 * Première IA implémentée, deprecated
 */
		
		
		/**for (direction=-2;direction<3;direction++){
			if(direction != 0){
				
				test = this.grille.lanceurDeplacerCasesIa(direction);
				if(scoreSec<scoreMin && test){
					scoreMin = this.scoreSec;
					dirFinal = direction;

		
				}
			}
		}
		this.grille.lanceurDeplacerCases(dirFinal);**/
	}
}
