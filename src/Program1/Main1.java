package Program1;

import LabData.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main1 {
    static int N;
    public static double md = Double.MAX_VALUE;
    public static long time;
    public static void main(Data data, int n) throws IOException {
        System.out.println("Start main thread"); 
        N = n;
        double[] X = new double[N];
        double[][] MF = new double[N][N];

        Data d1 = data;
        Data d2 = new Data(N);
        d2.clone(d1);
        d2.cloneSync(d1);

        Data d3 = new Data(N);
        d3.clone(d1);
        d3.cloneSync(d1);

        Data d4 = new Data(N);
        d4.clone(d1);
        d4.cloneSync(d1);

        Ops o1 = new Ops(d1, 0);
        T1 T_1 = new T1(o1, 0);

        Ops o2 = new Ops(d2, 1);
        T1 T_2 = new T1(o2, 1);

        Ops o3 = new Ops(d3, 2);
        T1 T_3 = new T1(o3, 2);

        Ops o4 = new Ops(d4, 3);
        T1 T_4 = new T1(o4, 3);

        long startTime = System.currentTimeMillis();
        T_1.start();
        T_2.start();
        T_3.start();
        T_4.start();

        try {
            T_1.join();
            T_2.join();
            T_3.join();
            T_4.join();

            double[] X1 = T_1.returnX();
            double[] X2 = T_2.returnX();
            double[] X3 = T_3.returnX();
            double[] X4 = T_4.returnX();

            System.arraycopy(X1, 0, X, o1.start, o1.data.H);
            System.arraycopy(X2, 0, X, o2.start, o2.data.H);
            System.arraycopy(X3, 0, X, o3.start, o3.data.H);
            System.arraycopy(X4, 0, X, o4.start, o4.data.H);

            Arrays.parallelSort(X);
            long endTime = System.currentTimeMillis();
            time = (endTime - startTime);


            double[][] MF1 = T_1.returnMF();
            double[][] MF2 = T_2.returnMF();
            double[][] MF3 = T_3.returnMF();
            double[][] MF4 = T_4.returnMF();

            for (int i = 0; i < N/4; i++) {
                System.arraycopy(MF1[o1.start + i], 0, MF[o1.start + i], 0, N);
                System.arraycopy(MF2[o1.start + i], 0, MF[o2.start + i], 0, N);
                System.arraycopy(MF3[o1.start + i], 0, MF[o3.start + i], 0, N);
                System.arraycopy(MF4[o1.start + i], 0, MF[o4.start + i], 0, N);
            }
            if (N < 10) {
                System.out.println("X value:");
                for (int i = 0; i < N; i++) {
                    System.out.println(X[i]);
                }
                System.out.println("MF value:");
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        System.out.print(MF[i][j]);
                        System.out.printf(" ");
                    }
                    System.out.printf("\n");
                }
                writeToFile("1.txt", X, MF);
            }

            System.out.println("Finish main thread"); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String filename ,double[] Vec, double[][] Mtx) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filename))) {
            w.write("Value of X: ");
            for (int i = 0; i < N; i++) {
                w.write(Double.toString(Vec[i]));
                if (i < N - 1) w.write(" ");
            }
            w.newLine();
            w.write("Value of MF:");
            w.newLine();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    w.write(Double.toString(Mtx[i][j]));
                    if (j < N - 1) w.write(" ");
                }
                w.newLine();
            }
        }
    }
}
