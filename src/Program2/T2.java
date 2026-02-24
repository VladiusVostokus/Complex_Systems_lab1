package Program2;
import LabData.Ops;

public class T2 extends Thread {
    private Ops O;
    private int id;

    public T2(Ops o1, int threadId) {
        O = o1;
        id = threadId;
    }

    public double[] returnX(){
        return O.returnX(O.data.M);
    }

    // X = SORT(MC*M+D-C)
    // X1 = MC*M
    // X2 = D - C
    // X = X1 + X2

    // MF = min(M+D)*MC*MZ+MM*(MC+MM)*a +
    // E = M + D +
    // md = min(E) +
    // MF1 = MC*MZ +
    // MF2 = MC+MM +
    // MF2 = MF2 * a +
    // MF1 = MF1 * md +
    // MF = MF1 + MF2 +
    @Override
    public void run() {
        System.out.printf("Thread started: %d %n", id);
        O.multiplyPartOfMatrAndVec(O.data.X1, O.data.M, O.data.MC);
        O.subPartOfTwoVercors(O.data.X2, O.data.D, O.data.C);
        O.addPartOfTwoVercors(O.data.X, O.data.X1, O.data.X2);
        O.sortVec(O.data.X);

        System.out.printf("Thread finished X calculation: %d %n", id);
        O.addPartOfTwoVercors(O.data.E, O.data.M, O.data.D);
        double md = O.findMin(O.data.E);
        synchronized(O.data.lockmd) {
            if (md < O.data.md)
            O.data.md = md;
        }

        O.multiplyPartOfMatrices(O.data.MF1, O.data.MC, O.data.MZ);
        O.addPartOfTwoMatr(O.data.MF2, O.data.MC, O.data.MM);
        O.mulPartOfMatrAndScal(O.data.MF2, O.data.a);
        O.mulPartOfMatrAndScal(O.data.MF1, O.data.md);
        O.addPartOfTwoMatr(O.data.MF, O.data.MF1, O.data.MF2);

        System.out.printf("Thread finished: %d %n", id);
    }
}