class Main1 {
    public static void main(String[] args) {
        System.out.println("Start main thread"); 
        int n = 4;
        double[] X = new double[n];

        Data d1 = new Data(n);
        Ops o1 = new Ops(d1, 0);
        T T1 = new T(o1, n, n, 0);

        Data d2 = new Data(n);
        Ops o2 = new Ops(d2, 1);
        T T2 = new T(o2, n, n, 1);

        Data d3 = new Data(n);
        Ops o3 = new Ops(d3, 2);
        T T3 = new T(o3, n, n, 2);

        Data d4 = new Data(n);
        Ops o4 = new Ops(d4, 3);
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

            double[] X1 = T1.returnX();
            double[] X2 = T2.returnX();
            double[] X3 = T3.returnX();
            double[] X4 = T4.returnX();


            for(int i = 0; i < n / 4; i++) {
                X[i] = X1[i];
                X[i + 1] = X2[i];
                X[i + 2] = X3[i];
                X[i + 3] = X4[i];
            }

            for (int i = 0; i < n; i++) {
                System.out.println(X[i]);
            }
            System.out.println("Finish main thread"); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
