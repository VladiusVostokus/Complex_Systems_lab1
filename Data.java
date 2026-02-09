import java.util.Random;

public class Data {
    public int N, P, H;
    public double a;
    public double[] M, D, C;
    public double[][] MC, MZ, MM;

    public Data(int n) {
        Random r = new Random();
        N = n;
        P = 4;
        H = N / P;
        a = generateDouble(r, 1, 10000);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; i++) {
                MC[i][j] = generateDouble(r, 1, 10000);
                MZ[i][j] = generateDouble(r, 1, 10000);
                MM[i][j] = generateDouble(r, 1, 10000);
            }
        }
    }

    private double generateDouble(Random r, int rangeMin, int rangeMax) {
        double randomDouble = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return randomDouble;
    }
}