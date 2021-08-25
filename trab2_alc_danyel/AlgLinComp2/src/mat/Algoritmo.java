package mat;

public class Algoritmo {
	Resposta resp_parcial = new Resposta();
	private double tol;
    private double[][] A;
    private double[][] I;

    public Algoritmo(double tol, double[][] A) {
        this.tol = tol;
        this.A = A;
        gerar_I(A);
    }

    public double[][] getEigenvalue() {
        double[][] M = A;
        double[][] eigenVectors = Utils.copia(I);
        ElementoMatriz max = getMaximo(M);

        while (max.val > tol) {
            double phi = getPhi(M, max.i, max.j);

            double sin = Math.sin(phi);
            double cos = Math.cos(phi);

            double[][] H = generateH(sin, cos, max.i, max.j);

            M = Utils.multiplicacao(Utils.multiplicacao(Utils.transposta(H), M), H);

            max = getMaximo(M);
            eigenVectors = Utils.multiplicacao(eigenVectors, H);
        }

        return M;
    }

    private double[][] generateH(double sin, double cos, int i, int j) {
        double[][] res = Utils.copia(I);

        res[i][i] = cos;
        res[i][j] = -sin;
        res[j][i] = sin;
        res[j][j] = cos;

        return res;
    }

    private double getPhi(double[][] m, int i, int j) {
        double atanArgs = (double) 2 * m[i][j] / (m[i][i] - m[j][j]);
        return Math.atan(atanArgs) / 2;
    }

    private ElementoMatriz getMaximo(double[][] m) {
        ElementoMatriz max = new ElementoMatriz(m[0][1], 0, 1);

        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (Math.abs(max.val) < Math.abs(m[i][j])) {
                    max = new ElementoMatriz(Math.abs(m[i][j]), i, j);
                }
            }
        }

        return max;
    }

    private void gerar_I(double[][] matrix) {
        I = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            I[i][i] = 1;
        }
    }

}
