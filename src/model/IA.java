package model;

public class IA extends Joueur implements Parametres{
	
	private int scoreSec;
	private Grille grilleSec;

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
		
		
		
		int direction = -2;
		int scoreMin = 1000000000;
		int dirChoisi = 0;
		boolean test;
		
		for(direction = -2;direction<3;direction++){
			
			this.grilleSec = this.grille;
			this.scoreSec = this.score;
			
			System.out.println("grille sec"+this.grilleSec + "\n grille générale"+this.grille);
		
			if(direction != 0){
				
				test = this.grilleSec.lanceurDeplacerCases(direction, false);
				
				
				
				if(scoreMin>scoreSec && test){
					scoreMin = this.scoreSec;
					dirChoisi = direction;
				}
			}
			System.out.println("cacahuete");
		}
		System.out.println("vin de dieux");
		
		this.grille.lanceurDeplacerCases(dirChoisi, true);
		
		return direction;
	}
	
}
