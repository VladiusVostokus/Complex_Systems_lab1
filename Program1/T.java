public class T extends Thread {
    private int h;
    private Ops O;
    private int id;

    public T(Ops ops, int n, int p, int threadId) {
        h = n / p;
        O = ops;
        id = threadId;
    }

    @Override
    public void run() {
        System.out.println(id);
        
        System.out.println(id);
    }
}
