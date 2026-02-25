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
        M = new double[N];
        D = new double[N];
        C = new double[N];
        E = new double[N];
        X = new double[N];
        X1 = new double[N];
        X2 = new double[N];

        MC = new double[N][N];
        MZ = new double[N][N];
        MM = new double[N][N];

        MF = new double[N][N];
        MF1 = new double[N][N];
        MF2 = new double[N][N];
    }

    private double generateDouble(Random r, int rangeMin, int rangeMax) {
        double randomDouble = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        return randomDouble;
    }

    public void initValues() {
        a = generateDouble(r, 1, 10);
        lockmd = new Object();
        allTheadsFinishMd = new CyclicBarrier(P);
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
