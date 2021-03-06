/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Guilherme
 */
public class Case implements Parametres {

    private int x, y, valeur;
    private Grille grille;

    /**
     * Constructeur de la classe Case
     * @param abs : position en abscisse de la case sur la grille
     * @param ord : position en ordonnée de la case sur la grille
     * @param v : valeur de la case
     */
    public Case(int abs, int ord, int v) {
        this.x = abs;
        this.y = ord;
        this.valeur = v;
    }

    /**
     *  set Grille
     * @param g : Grille
     */
    public void setGrille(Grille g) {
        this.grille = g;
    }
    
    /**
     *  get X
     * @return x: position en abscisse de la case
     */
    public int getX() {
        return this.x;
    }

    /**
     *  get Y
     * @return y: position en ordonnée de la case
     */
    public int getY() {
        return this.y;
    }

    /**
     * set X
     * @param x : set l'abscisse de la case
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * set Y
     * @param y : set l'ordonnée de la case
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * set valeur
     * @param valeur : set la valeur de la case
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * get valeur
     * @return la valeur de la case
     */
    public int getValeur() {
        return this.valeur;
    }

    /**
     * override de la méthode equals pour comparer les doublons des cases
     */
    @Override
    public boolean equals(Object obj) { // la m�thode equals est utilis�e lors de l'ajout d'une case � un ensemble pour v�rifier qu'il n'y a pas de doublons (teste parmi tous les candidats qui ont le m�me hashcode)
        if (obj instanceof Case) {
            Case c = (Case) obj;
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }

    /**
     * détermine le hashcode
     */
    @Override
    public int hashCode() { // d�termine le hashcode
        return this.x * 7 + this.y * 13;
    }
    
    /**
     * permet de retrouver la valeur suivante dans la suite de fibonacci en fonction de la valeur d'une case
     * @param c : case de référence pour trouver la valeur suivante
     * @param fibo : ArrayList contenant les valeurs de fibonacci 
     * @return un boolean pour indiquer si la valeur de la case testée est supérieur à celle de la case courante
     */
    public boolean valeurAdjacente(Case c, ArrayList fibo) {
    	int valeurinf;
    	int valeursup;
    	if (c.getValeur()>this.valeur) {
    		valeurinf=fibo.indexOf(this.valeur);
    		valeursup=fibo.indexOf(c.getValeur());
    	}else {
    		valeurinf=fibo.indexOf(c.getValeur());
    		valeursup=fibo.indexOf(this.valeur);    		
    	}
    	if(valeurinf==valeursup-1 || (valeurinf==0&&valeursup==0) || (valeurinf==0&&valeursup==2)) {
    		return true;
    	}else {
    		return false;
    	}
    }

    /**
     * retourne le voisin direct d'une case en fonction de la direction donnée
     * @param direction à tester
     * @return la case direct en fonction de la direction donnée
     */
    public Case getVoisinDirect(int direction) {
        if (direction == HAUT) {
            for (int i = this.y - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == BAS) {
            for (int i = this.y + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == GAUCHE) {
            for (int i = this.x - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        } else if (direction == DROITE) {
            for (int i = this.x + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * méthode identique à la précédente, sauf que la grille concernée est la grilleIA
     * @param direction à tester
     * @return la case direct en fonction de la direction donnée
     */
    public Case getVoisinDirectIa(int direction) {
        if (direction == HAUT) {
            for (int i = this.y - 1; i >= 0; i--) {
                for (Case c : grille.getGrilleIa()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == BAS) {
            for (int i = this.y + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrilleIa()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == GAUCHE) {
            for (int i = this.x - 1; i >= 0; i--) {
                for (Case c : grille.getGrilleIa()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        } else if (direction == DROITE) {
            for (int i = this.x + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrilleIa()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    /**
     * override de tostring
     */
    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.valeur + ")";
    }
}
