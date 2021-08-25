package mat;

import java.util.Arrays;

public class Utils {

    public static double[][] transposta(double[][] m) {
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public static double[][] multiplicacao(double[][] A, double[][] B) {
        int a_linhas = A.length;
        int a_colunas = A[0].length;
        int b_colunas = B[0].length;

        double[][] C = new double[a_linhas][b_colunas];

        for (int i = 0; i < a_linhas; i++) {
            for (int j = 0; j < b_colunas; j++) {
                for (int k = 0; k < a_colunas; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static double[][] copia(double[][] original) {
        final double[][] resultado = new double[original.length][];

        for (int i = 0; i < original.length; i++) {
            resultado[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return resultado;
    }
}