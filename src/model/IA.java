package model;

public class IA extends Joueur implements Parametres{
	
	private int scoreSec;


	public IA(){
		super();

	}
	
	public int getScoreSec(){
		return this.scoreSec;
	}
	
	public void setScoreSec(int score2){
		this.scoreSec = score2;
	}
	
	
	public int saisieDplcmt(int joueur){
		boolean test;
		int direction;
		int scoreMin = 100000000;
		int dirFinal = 0;
		
		for (direction=-2;direction<3;direction++){
			if(direction != 0){
				
				test = this.grille.lanceurDeplacerCasesIa(direction);
				if(scoreSec<scoreMin && test){
					scoreMin = this.scoreSec;
					dirFinal = direction;

		
				}
			}
		}
		this.grille.lanceurDeplacerCases(dirFinal);
		return direction;
	}
}
