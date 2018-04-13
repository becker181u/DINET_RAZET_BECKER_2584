package model;

public class Fibonacci {
	/**
	 * Génération des nombres présents dans la suite de fibonacci
	 * @param n rang voulu dans la suite
	 * @return le nombre de rang n, de manière récursif
	 */
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }
}