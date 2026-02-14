public class T extends Thread {
    private int h;
    private Ops O;
    private int id;

    public T(Ops ops, int n, int p, int threadId) {
        h = n / p;
        O = ops;
        id = threadId;
    }

    public double[] returnX(){
        return O.returnX(O.data.M);
    }

    // X = SORT(MC*M+D-C)
    // X1 = MC*M
    // X2 = D - C
    // X = X1 + X2

    // MF = min(M+D)*MC*MZ+MM*(MC+MM)*a
    // E = M + D
    // md = min(M+D) - md - спільний ресурс
    // MF1 = MC*MZ
    // MF2 = MC+MM
    // MF2 = MF2 * a
    // MF1 = MF1 * md
    // MF = MF1 + MF2
    @Override
    public void run() {
        System.out.printf("Thread started: %d %n", id);
        O.multiplyPartOfMatrAndVec(O.data.X1, O.data.M, O.data.MC);
        O.subPartOfTwoVercors(O.data.X2, O.data.D, O.data.C);
        O.addPartOfTwoVercors(O.data.X, O.data.X1, O.data.X2);
        O.sortVec(O.data.X);

        System.out.printf("Thread finished X calculation: %d %n", id);
        O.addPartOfTwoVercors(O.data.E, O.data.M, O.data.D);
        O.data.md = O.findMin(O.data.E);

        O.multiplyPartOfMatrices(O.data.MF1, O.data.MC, O.data.MZ);
        O.addPartOfTwoMatr(O.data.MF2, O.data.MC, O.data.MM);
        O.mulPartOfMatrAndScal(O.data.MF2, O.data.a);
        O.mulPartOfMatrAndScal(O.data.MF1, O.data.md);
        O.addPartOfTwoMatr(O.data.MF, O.data.MF1, O.data.MF2);
        System.out.printf("Thread finished: %d %n", id);
    }
}
