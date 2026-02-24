package Program1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class Main1 {
    static int n = 4;
    static double md;
    public static void main(String[] args) throws IOException {
        System.out.println("Start main thread"); 
        double[] X = new double[n];
        double[][] MF = new double[n][n];

        Data1 d1 = new Data1(n);
        d1.initValues();
        Data1 d2 = new Data1(n);
        d2.clone(d1);
        Data1 d3 = new Data1(n);
        d3.clone(d1);
        Data1 d4 = new Data1(n);
        d4.clone(d1);

        Ops1 o1 = new Ops1(d1, 0);
        T1 T_1 = new T1(o1, n, n, 0);

        Ops1 o2 = new Ops1(d2, 1);
        T1 T_2 = new T1(o2, n, n, 1);

        Ops1 o3 = new Ops1(d3, 2);
        T1 T_3 = new T1(o3, n, n, 2);

        Ops1 o4 = new Ops1(d4, 3);
        T1 T_4 = new T1(o4, n, n, 3);

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

            double[][] MF1 = T_1.returnMF();
            double[][] MF2 = T_2.returnMF();
            double[][] MF3 = T_3.returnMF();
            double[][] MF4 = T_4.returnMF();

            System.arraycopy(X1, 0, X, o1.start, o1.data.H);
            System.arraycopy(X2, 0, X, o2.start, o2.data.H);
            System.arraycopy(X3, 0, X, o3.start, o3.data.H);
            System.arraycopy(X4, 0, X, o4.start, o4.data.H);

            Arrays.parallelSort(X);

            System.arraycopy(MF1[o1.start], 0, MF[o1.start], 0, n);
            System.arraycopy(MF2[o1.start], 0, MF[o2.start], 0, n);
            System.arraycopy(MF3[o1.start], 0, MF[o3.start], 0, n);
            System.arraycopy(MF4[o1.start], 0, MF[o4.start], 0, n);
            if (n < 10) {
                System.out.println("X value:");
                for (int i = 0; i < n; i++) {
                    System.out.println(X[i]);
                }
                System.out.println("MF value:");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
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
            for (int i = 0; i < n; i++) {
                w.write(Double.toString(Vec[i]));
                if (i < n - 1) w.write(" ");
            }
            w.newLine();
            w.write("Value of MF:");
            w.newLine();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    w.write(Double.toString(Mtx[i][j]));
                    if (j < n - 1) w.write(" ");
                }
                w.newLine();
            }
        }
    }
}
