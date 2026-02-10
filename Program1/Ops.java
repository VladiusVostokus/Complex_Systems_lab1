import java.util.Arrays;

public class Ops {
    private Data D;
    private int start, end;
    public Ops(Data d, int threadId) {
        D = d;
        start = D.H * threadId;
        end = start + D.H;
    }

    public void mulPartOfMatrAndScal(int[][] M, int c) {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < D.N; j++) {
                M[i][j] *= c;
            }
        }
    }

    public void multiplyPartOfMatrices(int[][] Res, int[][] A, int[][] B) {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < D.N; j++) {
                for (int k = start; k < end; k++) {
                    Res[i][j] =+ A[i][k] * B[k][j];
                }
            }
        }
    }

    public void multiplyPartOfVecAndMatr(int[] Res, int[] A, int[][] B) {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < D.H; j++) {
                Res[i] += A[i] * B[i][j];
            }
        }
    }

    public int multiplyPartOfVecScalar(int[] A, int[] B) {
        int res = 0;
        for (int i = start; i < end; i++) {
            res += A[i] * B[i];
        }
        return res;
    }

    public void multiplyVecAndScalar(int[] A, int b) {
        for (int i = start; i < end; i++) {
           A[i] *= b;
        }
    }

    public void addPartOfTwoVercors(int[] Res, int[] A, int[] B) {
        for (int i = start; i < end; i++) {
           Res[i] += A[i] + B[i];
        }
    }

    public void sortVec(int[] Res) {
        Arrays.sort(Res, start, end);
    }

    public void mergeVec(int[] Res, int leftStart, int rightStart, int rightEnd) {
        int[] merged = new int[rightEnd - leftStart];
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
}
