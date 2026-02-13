class Main2 {
    public static void main(String[] args) {
        System.out.println("Start main thread"); 
        int n = 4;

        Data d = new Data(n);
        Ops o1 = new Ops(d, 0);
        Ops o2 = new Ops(d, 1);
        Ops o3 = new Ops(d, 2);
        Ops o4 = new Ops(d, 3);

        T T1 = new T(o1, n, n, 0);
        T T2 = new T(o2, n, n, 1);
        T T3 = new T(o3, n, n, 2);
        T T4 = new T(o4, n, n, 3);

        T1.start();
        T2.start();
        T3.start();
        T4.start();

        try {
            T1.join();
            T2.join();
            T3.join();
            T4.join();

            for (int i = 0; i < n; i++) {
                System.out.println(d.X[i]);
            }
            System.out.println("Finish main thread"); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
