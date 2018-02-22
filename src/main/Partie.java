package main;

import java.util.Scanner;

public class Partie implements Parametres{
	
	public Joueur joueur1, joueur2;
	
	public Partie() {
		this.joueur1 = new Humain();
		this.joueur2 = new Humain();
	}
	
	
		public int saisieDplcmt(){
			Scanner sc = new Scanner(System.in);
			
	        System.out.println("Deplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
	        String s = sc.nextLine();
	        s.toLowerCase();
	
	        int direction = 0;
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
	        return direction;
	}

    public void gameOver() {
    	System.out.println("La partie est finie");
    	System.out.println("Le score du joueur 1 est " + this.joueur1.grille.getValeurMax());
    	System.out.println("Le score du joueur 2 est " + this.joueur1.grille.getValeurMax());
        System.exit(1);
    }
	
	


}
