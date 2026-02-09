import java.util.Arrays;

public class Ops extends Data{
    public Ops(int N) {
        super(N);
    }

    public void mulPartOfMatrAndScal(int[][] M, int c, int threadNumber) {
        int start = H * threadNumber;
        int end = start + H;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < N; j++) {
                M[i][j] *= c;
            }
        }
    }

    public void multiplyPartOfMatrices(int[][] Res, int[][] A, int[][] B, int threadNumber) {
        int start = H * threadNumber;
        int end = start + H;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = start; k < end; k++) {
                    Res[i][j] =+ A[i][k] * B[k][j];
                }
            }
        }
    }

    public void multiplyPartOfVecAndMatr(int[] Res, int[] A, int[][] B, int threadNumber) {
        int start = H * threadNumber;
        int end = start + H;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < N; j++) {
                Res[i] += A[i] * B[i][j];
            }
        }
    }

    public int multiplyPartOfVecScalar(int[] A, int[] B, int threadNumber) {
        int res = 0;
        int start = H * threadNumber;
        int end = start + H;
        for (int i = start; i < end; i++) {
            res += A[i] * B[i];
        }
        return res;
    }

    public void multiplyVecAndScalar(int[] A, int b, int threadNumber) {
        int start = H * threadNumber;
        int end = start + H;
        for (int i = start; i < end; i++) {
           A[i] *= b;
        }
    }

    public void addPartOfTwoVercors(int[] Res, int[] A, int[] B, int threadNumber) {
        int start = H * threadNumber;
        int end = start + H;
        for (int i = start; i < end; i++) {
           Res[i] += A[i] + B[i];
        }
    }

    public void sortVec(int[] Res, int threadNumber) {
        int start = H * threadNumber;
        int end = start + H;
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
