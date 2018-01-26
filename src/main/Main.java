package main;

import java.util.Scanner;

public class Main implements Parametres{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grille g = new Grille();
        boolean b = g.nouvelleCase();
        b = g.nouvelleCase();
        System.out.println(g);
        Scanner sc = new Scanner(System.in);
        
        while (!g.partieFinie()) {
            System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");
            String s = sc.nextLine();
            s.toLowerCase();
            if (!(s.equals("d") || s.equals("droite")
                    || s.equals("q") || s.equals("gauche")
                    || s.equals("z") || s.equals("haut")
                    || s.equals("s") || s.equals("bas"))) {
                System.out.println("Vous devez écrire d pour Droite, g pour Gauche, h pour Haut ou b pour Bas");
            } else {
                int direction;
                if (s.equals("d") || s.equals("droite")) {
                    direction = DROITE;
                } else if (s.equals("g") || s.equals("gauche")) {
                    direction = GAUCHE;
                } else if (s.equals("h") || s.equals("haut")) {
                    direction = HAUT;
                } else {
                    direction = BAS;
                }
                boolean b2 = g.lanceurDeplacerCases(direction);
                if (b2) {
                    b = g.nouvelleCase();
                    if (!b) g.gameOver();
                }
                System.out.println(g);
                if (g.getValeurMax()>=OBJECTIF) g.victory();
            }
        }
        g.gameOver();

	}

}
