package main;

public class Partie {
	
	public Joueur joueur1, joueur2;
	
	public Partie() {
		this.joueur1 = new Humain();
		this.joueur2 = new Ordinateur();
	}
	
    public void gameOver() {
    	System.out.println("La partie est finie");
    	System.out.println("Le score du joueur 1 est " + this.joueur1.grille.getValeurMax());
    	System.out.println("Le score du joueur 2 est " + this.joueur1.grille.getValeurMax());
        System.exit(1);
    }
	
	

}
