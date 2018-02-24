package main;


public class Partie implements Parametres{
	
	public Joueur joueur1, joueur2;
	
	public Partie() {
		this.joueur1 = new Humain();
		this.joueur2 = new Humain();
		System.out.println(joueur1.grille);
		System.out.println(joueur2.grille);
	}
	
	
	public void dplcmt(){
		this.joueur1.saisieDplcmt(1);
		
        

        
	}

    public void gameOver() {
    	System.out.println("La partie est finie");
    	System.out.println("Le score du joueur 1 est " + this.joueur1.grille.getValeurMax());
    	System.out.println("Le score du joueur 2 est " + this.joueur1.grille.getValeurMax());
        System.exit(1);
    }
	
	


}
