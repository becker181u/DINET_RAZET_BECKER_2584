package main;

import java.util.Random;

public class Ordinateur extends Joueur implements Parametres{
	
	public int saisieDplcmt(int joueur){
		
		Random ra = new Random();
		int direction = 0;
		
		boolean test = false;
		
		while(!test){
			int x = ra.nextInt(4);
			switch (x) {
			case 0:
				direction = BAS;
				break;
			case 1:
				direction = HAUT;
				break;
			case 2:
				direction = GAUCHE;
				break;
			case 3:
				direction = DROITE;
				break;
			default:
				break;
			}
			
			test = this.grille.lanceurDeplacerCases(direction);
		}
		
	
		return direction;
	}

}
