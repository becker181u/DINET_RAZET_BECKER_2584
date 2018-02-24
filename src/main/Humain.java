package main;

import java.util.Scanner;

public class Humain extends Joueur implements Parametres{
	
	private Scanner sc;

	/**
	 @override
	 */
	public int saisieDplcmt(int joueur){
		
		sc = new Scanner(System.in);
		int direction = 0;
		
		if(joueur == 1){
			
			System.out.println("Deplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
	        String s = sc.nextLine();
	        s.toLowerCase();
	        
	        if (!(s.equals("d") || s.equals("droite")
	        		
	                || s.equals("q") || s.equals("gauche")
	                || s.equals("z") || s.equals("haut")
	                || s.equals("s") || s.equals("bas"))) {
	            System.out.println("Vous devez écrire d pour Droite, q pour Gauche, z pour Haut ou s pour Bas");
	            
	        } else {
	        	
	            if (s.equals("d") || s.equals("droite")) {
	                direction = DROITE;
	            } else if (s.equals("q") || s.equals("gauche")) {
	                direction = GAUCHE;
	            } else if (s.equals("z") || s.equals("haut")) {
	                direction = HAUT;
	            } else {
	                direction = BAS;
	            }
	        
	        }
	        
		}else{
			
			System.out.println("Deplacer vers la Droite (l), Gauche (j), Haut (i), ou Bas (k) ?");
	        String s = sc.nextLine();
	        s.toLowerCase();
			
			if (!(s.equals("l") || s.equals("droite")
	        		
	                || s.equals("j") || s.equals("gauche")
	                || s.equals("i") || s.equals("haut")
	                || s.equals("k") || s.equals("bas"))) {
	            System.out.println("Vous devez écrire l pour Droite, j pour Gauche, i pour Haut ou k pour Bas");
	            
	        } else {
	        	
	            if (s.equals("l") || s.equals("droite")) {
	                direction = DROITE;
	            } else if (s.equals("j") || s.equals("gauche")) {
	                direction = GAUCHE;
	            } else if (s.equals("i") || s.equals("haut")) {
	                direction = HAUT;
	            } else {
	                direction = BAS;
	            }
	        
	        }
		}
		
		return direction;
	}
	
}
