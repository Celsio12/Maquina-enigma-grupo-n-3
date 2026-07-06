package model;

import java.math.BigInteger;

public class Plugboard {

    private final int[] map = new int[26];

    public Plugboard(String pairs) {
        for (int i = 0; i < 26; i++) {
            map[i] = i;
        }
        if (pairs == null || pairs.isBlank()) {
            return;
        }
        String[] tokens = pairs.trim().toUpperCase().split("\\s+");
        boolean[] used = new boolean[26];
        for (String tok : tokens) {
            int a = tok.charAt(0) - 'A';
            int b = tok.charAt(1) - 'A';
            used[a] = true;
            used[b] = true;
            map[a] = b;
            map[b] = a;
        }
    }

    public int swap(int c) {
        return map[c];
    }

    public static long numeroConfiguracoes(int n) {
        BigInteger factorial26 = fatorial(26);
        BigInteger factorialResto = fatorial(26 - 2 * n);
        BigInteger factorialN = fatorial(n);
        BigInteger duasPotN = BigInteger.TWO.pow(n);

        BigInteger denominador = factorialResto.multiply(duasPotN).multiply(factorialN);
        return factorial26.divide(denominador).longValueExact();
    }

    private static BigInteger fatorial(int n) {
        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }
}