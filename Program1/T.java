public class T extends Thread {
    private int h;
    private Ops O;
    private Data D;

    public T(Ops ops, Data data, int n, int p) {
        h = n / p;
        O = ops;
        D = data;
    }

    @Override
    public void run() {
        System.out.printf("Thread started",Thread.currentThread().toString());
    }
}
