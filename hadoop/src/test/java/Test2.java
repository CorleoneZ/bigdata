import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class Test2 {

    private static List<> channel;
    private final int i =0 ;

    public static void server(int[] f1, int[] f2) {
        channel = new ArrayList<>();

        List<> c1 = new ArrayList();
        List<> c2 = new ArrayList();


        //接收
        for (int i = 0; i < f1.length;++i) {
            c1.add(f1[i]);
        }
        for (int j = 0; j < f2.length;++j) {
            c2.add(f2[j]);
        }

        sort(c1, c2, channel);

        //channel.sort(Comparable<Integer>);

        send(channel);

    }

    private static void sort(List c1, List c2, List channel) {
        int a = c1.size();
        int b = c2.size();

        while (a >= 0 && b >= 0) {
            if (c1.get(a) == c2.get(b)) {
                channel.add(a);
                channel.add(b);
                a--;
                b--;
            }
            if (c1.get(a) > c2.get(b)) {
                channel.add(c1.get(a));
                a--;
            }
            if (c1.get(a) < c2.get(b)) {
                channel.add(c2.get(b));
                b--;
            }
        }
    }

    public static void send(List<Integer> channel) {

        List<> hdfs = new ArrayList();
    }


    public static void main(String[] args) {
        int [] file1 = new int[] {2,3,5,3,1,7,10};
        int [] file2 = new int[] {2,4,5,3,9,2,10};
        server(file1,file2);
    }
}
