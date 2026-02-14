import java.util.Random;

public class Data2 {
    public int N, P, H;
    public double a, md;
    public double[] M, D, C, X1, X2, X, E;
    public double[][] MC, MZ, MM, MF, MF1, MF2;
    public Object lockM_M = new Object();
    public Object lockmd = new Object();

    public Data2(int n) {
        Random r = new Random();
        N = n;
        P = 4;
        H = N / P;
        a = generateDouble(r, 1, 10000);
        M = new double[n];
        D = new double[n];
        C = new double[n];
        E = new double[n];
        X = new double[n];
        X1 = new double[n];
        X2 = new double[n];

        MC = new double[n][n];
        MZ = new double[n][n];
        MM = new double[n][n];

        MF = new double[n][n];
        MF1 = new double[n][n];
        MF2 = new double[n][n];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                MC[i][j] = generateDouble(r, 1, 10);
                MZ[i][j] = generateDouble(r, 1, 10);
                MM[i][j] = generateDouble(r, 1, 10);
            }
            M[i] = generateDouble(r, 1, 10);
            D[i] = generateDouble(r, 1, 10);
            C[i] = generateDouble(r, 1, 10);
        }
    }

    private double generateDouble(Random r, int rangeMin, int rangeMax) {
        double randomDouble = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return randomDouble;
    }
}
