package LabData;

import java.util.Arrays;

public class Ops {
    public Data data;
    public int start, end;
    public Ops(Data d, int threadId) {
        data = d;
        start = data.H * threadId;
        end = start + data.H;
    }

    public void mulPartOfMatrAndScal(double[][] M, double c) {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < data.N; j++) {
                M[i][j] *= c;
            }
        }
    }

    public void multiplyPartOfMatrices(double[][] Res, double[][] A, double[][] B) {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < data.N; j++) {
                for (int k = 0; k < data.N; k++) {
                    Res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    public void multiplyPartOfMatrAndVec(double[] Res, double[] A, double[][] B) {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < data.N; j++) {
                Res[i] += B[i][j] * A[j];
            }
        }
    }

    public int multiplyPartOfVecScalar(double[] A, double[] B) {
        int res = 0;
        for (int i = start; i < end; i++) {
            res += A[i] * B[i];
        }
        return res;
    }

    public void multiplyVecAndScalar(double[] A, double b) {
        for (int i = start; i < end; i++) {
           A[i] *= b;
        }
    }

    public void addPartOfTwoMatr(double[][] Res, double[][] A, double[][] B) {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < data.N; j++) {
                Res[i][j] = A[i][j] + B[i][j];
            }
        }
    }

    public void addPartOfTwoVercors(double[] Res, double[] A, double[] B) {
        for (int i = start; i < end; i++) {
           Res[i] = A[i] + B[i];
        }
    }

    public void subPartOfTwoVercors(double[] Res, double[] A, double[] B) {
        for (int i = start; i < end; i++) {
           Res[i] = A[i] - B[i];
        }
    }

    public void sortVec(double[] Res) {
        Arrays.sort(Res, start, end);
    }

    public void mergeVec(double[] Res, int leftStart, int rightStart, int rightEnd) {
        double[] merged = new double[rightEnd - leftStart];
        int i = leftStart;
        int j = rightStart;
        int k = 0;

        while (i < rightStart && j < rightEnd) {
            if (Res[i] <= Res[j]) {
                merged[k++] = Res[i++];
            } else {
                merged[k++] = Res[j++];
            }
        }

        while (i < rightStart) {
            merged[k++] = Res[i++];
        }

        while (j < rightEnd) {
            merged[k++] = Res[j++];
        }
        System.arraycopy(merged, 0, Res, leftStart, merged.length);
    }

    public double findMin(double[] M) {
        double min = M[start];
        for (int i = start; i < end; i++) {
            if (min > M[i]) {
                min = M[i];
            }
        }
        return min;
    }

    public double[] returnX(double[] vec) {
        double[] res = new double[data.H];
        int idx = 0;
        for (int i = start; i < end; i++) {
            res[idx] = vec[i];
            idx++;
        }
        return res;
    }

    public double[][] returnMF(double[][] mtr) {
        double[][] res = new double[data.H][data.N];
        int idx = 0;
        for (int i = start; i < end; i++) {
            System.arraycopy(mtr[i], 0, res[idx], 0, data.N);
            idx++;
        }
        return res;
    }
}
