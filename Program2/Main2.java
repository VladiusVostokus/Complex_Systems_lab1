class Main2 {
    public static void main(String[] args) {
        System.out.println("Start main thread"); 
        int n = 4;

        Data2 d = new Data2(n);
        Ops2 o1 = new Ops2(d, 0);
        Ops2 o2 = new Ops2(d, 1);
        Ops2 o3 = new Ops2(d, 2);
        Ops2 o4 = new Ops2(d, 3);

        T2 T_1 = new T2(o1, n, n, 0);
        T2 T_2 = new T2(o2, n, n, 1);
        T2 T_3 = new T2(o3, n, n, 2);
        T2 T_4 = new T2(o4, n, n, 3);

        T_1.start();
        T_2.start();
        T_3.start();
        T_4.start();

        try {
            T_1.join();
            T_2.join();
            T_3.join();
            T_4.join();

            for (int i = 0; i < n; i++) {
                System.out.print(d.X[i]);
                System.out.printf(" ");
            }

            System.out.printf("\n");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(d.MF[i][j] + " ");
                }
                System.out.printf("\n");
            }
            System.out.println("Finish main thread"); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
