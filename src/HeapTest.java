import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Peen {
    String identifier;
    int number;


    public Peen(String id, int num) {
        this.identifier = id;
        this.number = num;
    }

    public String toString() {
        String s = "[" + identifier + " " + number + "]";
        return s;
    }
}

class PeenNumberComparator implements Comparator<Peen> {
    public int compare(Peen peen1, Peen peen2) {
        return peen1.number - peen2.number;
    }
}

public class HeapTest {
    public static void main(String args[]) {
        PeenNumberComparator c = new PeenNumberComparator();

        /*ArrayList<Peen> A = new ArrayList<Peen>();
        A.add(null);
        A.add(new Peen("1", 1));
        A.add(new Peen("3", 3));
        A.add(new Peen("5", 5));
        A.add(new Peen("7", 7));
        A.add(new Peen("9", 9));
        A.add(new Peen("12", 12));
        A.add(new Peen("14", 14));
        A.add(new Peen("16", 16));
        PeenNumberComparator c = new PeenNumberComparator();
        Heap<Peen> h = new Heap<Peen>(A, c);
        System.out.println(h.toString());
        h.insert(new Peen("10", 10));
        System.out.println(h.toString());
        h.insert(new Peen("0", 0));
        System.out.println(h.toString());
        h.insert(new Peen("1", 1));
        System.out.println(h.toString());
        h.insert(new Peen("3", 3));
        System.out.println(h.toString());

        System.out.println(h.sort());*/
        ArrayList<Peen> B = new ArrayList<Peen>();
        B.add(new Peen("1", 1));
        B.add(new Peen("3", 3));
        B.add(new Peen("5", 5));
        B.add(new Peen("7", 7));
        B.add(new Peen("9", 9));
        B.add(new Peen("12", 12));
        B.add(new Peen("14", 14));
        B.add(new Peen("16", 16));
        System.out.println(B.get(0));
        Heap<Peen> h2 = new Heap<Peen>(B, c);

        System.out.println(h2.toString());
        h2.remove(3);
        System.out.println(h2.toString());
        h2.remove(1);
        System.out.println(h2.toString());
        h2.remove(2);
        System.out.println(h2.toString());
        System.out.println(h2.getHeapSize());
        System.out.println(h2.sort());

        //System.out.print(h.toString());
    }
}
