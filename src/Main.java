import java.io.IOException;

import Program1.Main1;
import Program2.Main2;
import LabData.Data;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = 20;
        Data data = new Data(n);
        data.initValues();
        Data d1 = new Data(n);
        Data d2 = new Data(n);

        d1.clone(data);
        d1.cloneSync(data);
        
        d2.clone(data);
        d2.cloneSync(data);

        System.out.println("==============START PROGRAM1=================");
        Main1.main(d1, n);
        System.out.println("==============START PROGRAM2=================");
        Main2.main(d2, n);
        System.out.println("================TOTAL TIME===================");
        System.out.printf("Program 1: %d\n", Main1.time);
        System.out.printf("Program 2: %d\n", Main2.time);
    }
}
