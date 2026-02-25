package LabData;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Data {
    public int N, P, H;
    public double a, md;
    public double[] M, D, C, X1, X2, X, E;
    public double[][] MC, MZ, MM, MF, MF1, MF2;
    public Object lockmd;
    public CyclicBarrier allTheadsFinishMd;
    private Random r;

    public Data(int n) {
        r = new Random();
        N = n;
        P = 4;
        H = N / P;
        md =  Double.MAX_VALUE;
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
    }

    private double generateDouble(Random r, int rangeMin, int rangeMax) {
        double randomDouble = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return randomDouble;
    }

    public void initValues() {
        a = generateDouble(r, 1, 10);
        lockmd = new Object();
        allTheadsFinishMd = new CyclicBarrier(N);
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

    public void clone(Data sourse) {
        this.a = sourse.a;
        this.M = sourse.M.clone();
        this.D = sourse.D.clone();
        this.C = sourse.C.clone();
        cloneMatrix(sourse.MC, this.MC);
        cloneMatrix(sourse.MZ, this.MZ);
        cloneMatrix(sourse.MM, this.MM);
    }

    public void cloneSync(Data sourse) {
        this.lockmd = sourse.lockmd;
        this.allTheadsFinishMd = sourse.allTheadsFinishMd;
    }

    private void cloneMatrix(double[][] sourse, double[][] destination) {
        for (int i = 0; i < N; i++) {
            destination[i] = sourse[i].clone();
        }
    }
}
