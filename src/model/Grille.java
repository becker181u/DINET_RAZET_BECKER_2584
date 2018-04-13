/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * 
 * @author guilherme
 *
 */
public class Grille implements Parametres {

    private HashSet<Case> grille;
    private HashSet<Case> grilleIa;
    private HashSet<Case> grillePrec;
    private int valeurMax = 0;
    private int scorePrec;
    private boolean deplacement;
    protected ArrayList<Integer> fibo;
    private Joueur player;

    /**
     * constructeur de la classe grille
     * @param joueur permet de spécifier de la grille de quelle joueur on parle
     */
    public Grille(Joueur joueur) {
        this.grille = new HashSet<>();
        this.grilleIa = new HashSet<>();
        this.player=joueur;
        this.fibo = new ArrayList<>();
        for(int i=0; i<19; i++) {
        	this.fibo.add(Fibonacci.fibonacci(i+1));
        }

    }

    /**
     * override de la méthode tostring
     */
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
    
    /**
     * méthode permettant de renvoyer un affichage compatible HTML
     * @return une string compatible avec HTML
     */
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

    /**
     * get Grille
     * @return la grille courante, le HashSet
     */
    public HashSet<Case> getGrille() {
        return grille;
    }
    
    /**
     * get grilleIA, renvoie la grille utilisée pour les simulations IA
     * @return la grille IA
     */
    public HashSet<Case> getGrilleIa(){
    	return grilleIa;
    }
    
    /**
     * get grillePrec, renvoie la grille tel qu'elle l'est au tour précédent
     * @return grillePrec
     */
    public HashSet<Case> getGrillePrec(){
    	return grillePrec;
    }
    
    /**
     * set grille
     * @param grille
     */
    public void setGrille(HashSet<Case> grille){
    	
		this.grille.clear();
		for(Case c : grille){
			this.grille.add(c);
		}
    }
    
    /**
     * renvoie le score du tour précédent
     * @return scorePrec
     */
    public int getScorePrec() {
    	return this.scorePrec;
    }

    /**
     * renvoie la valeur max de la grille
     * @return valeur max
     */
    public int getValeurMax() {
        return valeurMax;
    }

    /**
     * Permet de détecter si une partie est finie, si il reste de la place sur la grille ou si la plus haute valeur est 2584
     * @return boolean de partie finie
     */
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
    
    /**
     * Permet de détecter si une partie est finie en se basant sur la grille IA
     * @return boolean de partie finie pour l'ia
     */
    public boolean partieFinieIa() {
    	
        if (this.grilleIa.size() < TAILLE * TAILLE) {
            return false;
        } else {
            for (Case c : this.grilleIa) {
                for (int i = 1; i <= 2; i++) {
                    if (c.getVoisinDirectIa(i) != null) {
                        if (c.valeurAdjacente(c.getVoisinDirectIa(i), fibo)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * fonction récursive pour le déplacement des cases, en fonction de la direction spécifiée
     * @param direction choisie par le joueur
     * @return boolean indiquant si un déplacement à eu lieu ou non
     */
    public boolean lanceurDeplacerCases(int direction) {
    	grillePrec=(HashSet<Case>) grille.clone();
    	this.scorePrec=this.player.getScore();
        Case[] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour v�rifier si on a boug� au moins une case apr�s le d�placement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            switch (direction) {
                case HAUT:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case BAS:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case GAUCHE:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                default:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
            }
        }
        if(this.player instanceof Humain) {
        	((Humain) this.player).setUndo(false);
        }
        return deplacement;
    }
    
    /**
     * exactement même méthode que celle au dessus, sauf qu'elle s'applique à la grille IA
     * @param direction choisie par l'ia 
     * @return un boolean pour indiquer à l'ia si un mouvement aurait eu lieu  
     */
    public boolean lanceurDeplacerCasesIa(int direction) {
    	

    	this.grilleIa.clear();

    	
    	for(Case c : grille){
    		Case c2 = this.cloneCase(c);
    		c2.setGrille(this);
    		this.grilleIa.add(c2);
    	}

    	
        Case[] extremites = this.getCasesExtremitesIA(direction);
        deplacement = false; // pour v�rifier si on a boug� au moins une case apr�s le d�placement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            switch (direction) {
                case HAUT:
                    this.deplacerCasesRecursifIa(extremites, i, direction, 0);
                    break;
                case BAS:
                    this.deplacerCasesRecursifIa(extremites, i, direction, 0);
                    break;
                case GAUCHE:
                    this.deplacerCasesRecursifIa(extremites, i, direction, 0);
                    break;
                default:
                    this.deplacerCasesRecursifIa(extremites, i, direction, 0);
                    break;
            }
        }

        return deplacement;
    }
 
    /**
     * clone la case, permettant de recréer la grilleIA a partir de la grille courante
     * @param caseBase case à cloner
     * @return case clonée
     */
    private Case cloneCase(Case caseBase){
    	
    	Case caseCloned = new Case(caseBase.getX(),caseBase.getY(),caseBase.getValeur());
    	
    	return caseCloned;
    }
    
    /**
     * retourne la valeur suivante dans la suite de fibonacci
     * @param c case à vérifier
     * @return le nombre suivant dans la suite de fibonacci
     */
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
    
    /**
     * change le score en fonction de la valeur passée en paramètres
     * @param valeur à incrémenter pour le score
     */
    private void changerScore(int valeur) {
    		int score = valeur + player.getScore();
	        player.setScore(score);
    }
    
    /**
     * change le score secondaire, pris en compte par l'ia dans le choix du coup
     * @param valeur acquise après le test des coups par l'ia, à ajouter au score secondaire
     */
    private void changerScoreSec(int valeur){
    	int score = valeur + player.getScoreSec();
    	player.setScoreSec(score);
    }

    /**
     * méthode fusionnant les cases adjacentes si elles sont compatibles
     * @param extremite case à l'extremité
     * @param voisin case arrivant sur la case d'extrémité
     */
    private void fusion(Case extremite,Case voisin) {
    	//System.out.println("fusion");
    	//System.out.println("extremite : "+extremite.getValeur() + "voisin"+ voisin.getValeur());
    	int valeur;
    	if(extremite.getValeur()==1&&voisin.getValeur()==1) {
    		valeur = 2;
    		extremite.setValeur(valeur);
    		changerScore(valeur);

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
	        changerScore(valeur);


	        
	        
	        if (this.valeurMax < extremite.getValeur()) {
	            this.valeurMax = extremite.getValeur();
	        }
    	}
        deplacement = true;
    }
    
    /**
     * méthode clone de la précédente, sauf qu'elle s'applique de la grilleIA
     * @param extremite case à l'extremité
     * @param voisin case arrivant sur la case d'extrémité
     */
    private void fusionIa(Case extremite,Case voisin) {
    	//System.out.println("fusion");
    	//System.out.println("extremite : "+extremite.getValeur() + "voisin"+ voisin.getValeur());
    	int valeur;
    	if(extremite.getValeur()==1&&voisin.getValeur()==1) {
    		valeur = 2;
    		extremite.setValeur(valeur);
    		changerScoreSec(valeur); 		
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
	        changerScoreSec(valeur);
	         
	        if (this.valeurMax < extremite.getValeur()) {
	            this.valeurMax = extremite.getValeur();
	        }
    	}
        deplacement = true;
    }

    /**
     * fonction de déplacement des cases, appelée de manière récursive
     * @param extremites tableau des cases d'extremité
     * @param rangee sélecteur de la rangée
     * @param direction choisie par le joueur
     * @param compteur de déplacement par rangée
     */
    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur) {
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
                    this.fusion(extremites[rangee],voisin);
                    extremites[rangee] = voisin.getVoisinDirect(-direction);
                    this.grille.remove(voisin);
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                } else {
                	//System.out.println("aurevoir");
                    extremites[rangee] = voisin;
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                }
            }
        }
    }

    /**
     * méthode identique à la précédente, mais s'applique sur la grille IA
     * @param extremites tableau des cases d'extremité
     * @param rangee sélecteur de la rangée
     * @param direction choisie par le joueur
     * @param compteur de déplacement par rangée
     */
    private void deplacerCasesRecursifIa(Case[] extremites, int rangee, int direction, int compteur) {
        if (extremites[rangee] != null) {
            if ((direction == HAUT && extremites[rangee].getY() != compteur)
                    || (direction == BAS && extremites[rangee].getY() != TAILLE - 1 - compteur)
                    || (direction == GAUCHE && extremites[rangee].getX() != compteur)
                    || (direction == DROITE && extremites[rangee].getX() != TAILLE - 1 - compteur)) {
                this.grilleIa.remove(extremites[rangee]);
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
                this.grilleIa.add(extremites[rangee]);
                deplacement = true;
            }
            Case voisin = extremites[rangee].getVoisinDirectIa(-direction);
            if (voisin != null) {
                if (extremites[rangee].valeurAdjacente(voisin,fibo)) {
                	//System.out.println("bonjour");
                    this.fusionIa(extremites[rangee],voisin);
                    extremites[rangee] = voisin.getVoisinDirectIa(-direction);
                    this.grilleIa.remove(voisin);
                    this.deplacerCasesRecursifIa(extremites, rangee, direction, compteur + 1);
                } else {
                	//System.out.println("aurevoir");
                    extremites[rangee] = voisin;
                    this.deplacerCasesRecursifIa(extremites, rangee, direction, compteur + 1);
                }
            }
        }
    }
    
    /**
     * Si direction = HAUT : retourne les 4 cases qui sont le plus en haut (une pour chaque colonne)
     * Si direction = DROITE : retourne les 4 cases qui sont le plus � droite (une pour chaque ligne)
     * Si direction = BAS : retourne les 4 cases qui sont le plus en bas (une pour chaque colonne)
     * Si direction = GAUCHE : retourne les 4 cases qui sont le plus � gauche (une pour chaque ligne)
     * Attention : le tableau retourn� peut contenir des null si les lignes/colonnes sont vides
     * @param direction choisie par le joueur
     * @return le tableau de cases à l'extrémité en fonction de la direction choisie
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
    
    
    /**
     * méthode clonée de la précédente, appliquée à la grille IA
     * @param direction choisie par le joueur
     * @return le tableau de cases à l'extrémité en fonction de la direction choisie
     */
    public Case[] getCasesExtremitesIA(int direction) {
        Case[] result = new Case[TAILLE];
        for (Case c : this.grilleIa) {
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

    /**
     * méthode de fin de partie
     * @deprecated
     */
    public void victory() {
        System.out.println("Bravo ! Vous avez atteint " + this.valeurMax);
        System.exit(0);
    }



    /**
     * crée une nouvelle case à une position libre aléatoire de la grille
     * @return un boolean pour montrer l'existence de la nouvelle case ou non
     */
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
    
    /**
     * crée une nouvelle case à une position libre aléatoire de la grille
     * @return un boolean pour montrer l'existence de la nouvelle case ou non
     */
    public boolean nouvelleCaseIA() {
        if (this.grilleIa.size() < TAILLE * TAILLE) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            Random ra = new Random();

            int valeur;
            if(ra.nextDouble()<0.75) valeur = 1; else valeur = 2; 
            // on cr�e toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case c = new Case(x, y, valeur);
                    if (!this.grilleIa.contains(c)) { 
                    	// contains utilise la m�thode equals dans Case
                        casesLibres.add(c);
                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute � la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            ajout.setGrille(this);
            this.grilleIa.add(ajout);
            if ((this.grilleIa.size() == 1) || (this.valeurMax == 1 && ajout.getValeur() == 2)) { // Mise � jour de la valeur maximale pr�sente dans la grille si c'est la premi�re case ajout�e ou si on ajoute un 4 et que l'ancien max �tait 2
                this.valeurMax = ajout.getValeur();
            }
            return true;
        } else {
            return false;
        }
    }
    
	
}
