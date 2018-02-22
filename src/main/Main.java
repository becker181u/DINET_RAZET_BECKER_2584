package main;


public class Main implements Parametres{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grille g = new Grille();
        boolean b = g.nouvelleCase();
        b = g.nouvelleCase();
        System.out.println(g);
        Partie p = new Partie();
        
        int direction = p.saisieDplcmt();
        
        boolean b2 = g.lanceurDeplacerCases(direction);
        if (b2) {
            b = g.nouvelleCase();
            if (!b) p.gameOver();
        }
        System.out.println(g);
        if (g.getValeurMax()>=OBJECTIF) g.victory();
            
        


	}

}
