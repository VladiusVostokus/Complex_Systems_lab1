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
    // MF = min(M+D)*MC*MZ+MM*(MC+MM)*a
    @Override
    public void run() {
        System.out.printf("Thread started: %d %n", id);
        O.multiplyPartOfMatrAndVec(O.data.M, O.data.M, O.data.MC);
        O.addPartOfTwoVercors(O.data.M, O.data.M, O.data.D);
        O.subPartOfTwoVercors(O.data.M, O.data.M, O.data.C);
        O.sortVec(O.data.M);
        System.out.printf("Thread finished: %d %n", id);
    }
}