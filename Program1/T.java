public class T extends Thread {
    private int h;
    private Ops O;
    private Thread id;

    public T(Ops ops, int n, int p) {
        h = n / p;
        O = ops;
        id = Thread.currentThread();
    }

    @Override
    public void run() {
        System.out.printf("Thread started",id.toString());
        System.out.println("Working...");
        System.out.printf("Thread finished",id.toString());
    }
}
