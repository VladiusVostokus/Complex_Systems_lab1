package Program2;

import LabData.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main2 {
    static int n = 4;
    public static long time;
    public static void main(Data data) throws IOException {
        System.out.println("Start main thread"); 

        Data d = data;
        Ops o1 = new Ops(d, 0);
        Ops o2 = new Ops(d, 1);
        Ops o3 = new Ops(d, 2);
        Ops o4 = new Ops(d, 3);

        T2 T_1 = new T2(o1, 0);
        T2 T_2 = new T2(o2, 1);
        T2 T_3 = new T2(o3, 2);
        T2 T_4 = new T2(o4, 3);

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

            Arrays.parallelSort(d.X);
            long endTime = System.currentTimeMillis();
            time = (endTime - startTime);

            if (n < 10) {
                System.out.println("X value:");
                for (int i = 0; i < n; i++) {
                    System.out.println(d.X[i]);
                }
                System.out.println("MF value:");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(d.MF[i][j]);
                        System.out.printf(" ");
                    }
                    System.out.printf("\n");
                }
                writeToFile("2.txt", d.X, d.MF);
            } 
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
