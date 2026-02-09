class Main1 {
    public static void main(String[] args) {
        System.out.println("Hello, World!"); 
        int n = 4;

        Data d1 = new Data(n);
        Ops o1 = new Ops(d1);
        T T1 = new T(o1, n, n);

        Data d2 = new Data(n);
        Ops o2 = new Ops(d2);
        T T2 = new T(o2, n, n);

        Data d3 = new Data(n);
        Ops o3 = new Ops(d3);
        T T3 = new T(o3, n, n);

        Data d4 = new Data(n);
        Ops o4 = new Ops(d4);
        T T4 = new T(o4, n, n);

        T1.start();
        T2.start();
        T3.start();
        T4.start();

        try {
            T1.join();
            T2.join();
            T3.join();
            T4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
