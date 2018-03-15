/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Sylvain
 */
public class Grille implements Parametres {

    private final HashSet<Case> grille;
    private int valeurMax = 0;
    private boolean deplacement;
    protected ArrayList<Integer> fibo;
    private Joueur player;

    public Grille(Joueur joueur) {
        this.grille = new HashSet<>();
        this.player=joueur;
        this.fibo = new ArrayList<>();
        for(int i=0; i<19; i++) {
        	this.fibo.add(Fibonacci.fibonacci(i+1));
        }

    }

    @Override
    public String toString() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "\n";
        }
        result+="Score : "+player.getScore();
        return result;
    }
    
    public String toHTML() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "<html>";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "<br/>";
        }
        result += "</html>";
        return result;
    }

    public HashSet<Case> getGrille() {
        return grille;
    }

    public int getValeurMax() {
        return valeurMax;
    }

    public boolean partieFinie() {
    	
        if (this.grille.size() < TAILLE * TAILLE) {
            return false;
        } else {
            for (Case c : this.grille) {
                for (int i = 1; i <= 2; i++) {
                    if (c.getVoisinDirect(i) != null) {
                        if (c.valeurAdjacente(c.getVoisinDirect(i), fibo)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean lanceurDeplacerCases(int direction, boolean inter) {
        Case[] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour v�rifier si on a boug� au moins une case apr�s le d�placement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            switch (direction) {
                case HAUT:
                    this.deplacerCasesRecursif(extremites, i, direction, 0, inter);
                    break;
                case BAS:
                    this.deplacerCasesRecursif(extremites, i, direction, 0, inter);
                    break;
                case GAUCHE:
                    this.deplacerCasesRecursif(extremites, i, direction, 0, inter);
                    break;
                default:
                    this.deplacerCasesRecursif(extremites, i, direction, 0, inter);
                    break;
            }
        }
        return deplacement;
    }

    private int trouverLeSuivant(Case c) {
    	boolean trouve= true; 
    	int grand= 0;
    	int i=0;
    	if(c.getValeur()==2584) {
    		trouve=false;
    	}
    	while(trouve) {
    		grand = fibo.get(i);
    		if(grand<c.getValeur()) {
    			i++;
    		}else {
    			trouve=false; 
    		}
    	}
    	return fibo.get(i+1);
    }
    
    private void changerScore(int valeur) {
    		int score = valeur + player.getScore();
	        player.setScore(score);
    }

    private void fusion(Case extremite,Case voisin, boolean inter) {
    	//System.out.println("fusion");
    	//System.out.println("extremite : "+extremite.getValeur() + "voisin"+ voisin.getValeur());
    	int valeur;
    	if(extremite.getValeur()==1&&voisin.getValeur()==1) {
    		valeur = 2;
    		extremite.setValeur(valeur);
    		if(inter){
    			changerScore(valeur);
    		}
    		

    		
    	}
    	else {
	    	valeur=0;
	    	if(extremite.getValeur()>voisin.getValeur()) {
	    		valeur=trouverLeSuivant(extremite);
	    		//System.out.println("valeur"+valeur);
	    	}else {
	    		valeur=trouverLeSuivant(voisin);
	    		//System.out.println(valeur);
	    	}
	        extremite.setValeur(valeur);
	        if(inter){
	        	changerScore(valeur);
	        }
	        
	        
	        if (this.valeurMax < extremite.getValeur()) {
	            this.valeurMax = extremite.getValeur();
	        }
    	}
        deplacement = true;
    }

    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur, boolean inter) {
        if (extremites[rangee] != null) {
            if ((direction == HAUT && extremites[rangee].getY() != compteur)
                    || (direction == BAS && extremites[rangee].getY() != TAILLE - 1 - compteur)
                    || (direction == GAUCHE && extremites[rangee].getX() != compteur)
                    || (direction == DROITE && extremites[rangee].getX() != TAILLE - 1 - compteur)) {
                this.grille.remove(extremites[rangee]);
                switch (direction) {
                    case HAUT:
                        extremites[rangee].setY(compteur);
                        break;
                    case BAS:
                        extremites[rangee].setY(TAILLE - 1 - compteur);
                        break;
                    case GAUCHE:
                        extremites[rangee].setX(compteur);
                        break;
                    default:
                        extremites[rangee].setX(TAILLE - 1 - compteur);
                        break;
                }
                this.grille.add(extremites[rangee]);
                deplacement = true;
            }
            Case voisin = extremites[rangee].getVoisinDirect(-direction);
            if (voisin != null) {
                if (extremites[rangee].valeurAdjacente(voisin,fibo)) {
                	//System.out.println("bonjour");
                    this.fusion(extremites[rangee],voisin,inter);
                    extremites[rangee] = voisin.getVoisinDirect(-direction);
                    this.grille.remove(voisin);
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1, inter);
                } else {
                	//System.out.println("aurevoir");
                    extremites[rangee] = voisin;
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1, inter);
                }
            }
        }
    }

    /*
    * Si direction = HAUT : retourne les 4 cases qui sont le plus en haut (une pour chaque colonne)
    * Si direction = DROITE : retourne les 4 cases qui sont le plus � droite (une pour chaque ligne)
    * Si direction = BAS : retourne les 4 cases qui sont le plus en bas (une pour chaque colonne)
    * Si direction = GAUCHE : retourne les 4 cases qui sont le plus � gauche (une pour chaque ligne)
    * Attention : le tableau retourn� peut contenir des null si les lignes/colonnes sont vides
     */
    public Case[] getCasesExtremites(int direction) {
        Case[] result = new Case[TAILLE];
        for (Case c : this.grille) {
            switch (direction) {
                case HAUT:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() > c.getY())) { // si on n'avait pas encore de case pour cette rang�e ou si on a trouv� un meilleur candidat
                        result[c.getX()] = c;
                    }
                    break;
                case BAS:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() < c.getY())) {
                        result[c.getX()] = c;
                    }
                    break;
                case GAUCHE:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() > c.getX())) {
                        result[c.getY()] = c;
                    }
                    break;
                default:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() < c.getX())) {
                        result[c.getY()] = c;
                    }
                    break;
            }
        }
        return result;
    }

    public void victory() {
        System.out.println("Bravo ! Vous avez atteint " + this.valeurMax);
        System.exit(0);
    }



    public boolean nouvelleCase() {
        if (this.grille.size() < TAILLE * TAILLE) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            Random ra = new Random();

            int valeur;
            if(ra.nextDouble()<0.75) valeur = 1; else valeur = 2; 
            // on cr�e toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case c = new Case(x, y, valeur);
                    if (!this.grille.contains(c)) { 
                    	// contains utilise la m�thode equals dans Case
                        casesLibres.add(c);
                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute � la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            ajout.setGrille(this);
            this.grille.add(ajout);
            if ((this.grille.size() == 1) || (this.valeurMax == 1 && ajout.getValeur() == 2)) { // Mise � jour de la valeur maximale pr�sente dans la grille si c'est la premi�re case ajout�e ou si on ajoute un 4 et que l'ancien max �tait 2
                this.valeurMax = ajout.getValeur();
            }
            return true;
        } else {
            return false;
        }
    }
}
