package main;

public class Partie {
	
	public Joueur joueur1, joueur2;
	
	public Partie() {
		this.joueur1 = new Humain();
		this.joueur2 = new Ordinateur();
	}

}
