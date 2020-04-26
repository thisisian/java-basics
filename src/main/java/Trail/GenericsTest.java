package Trail;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {
    public static void printNumber(List<Number> l) {
        for (Number i : l) {
            System.out.println(i.intValue());
        }
    }

    // Will not compile, bounds on variables do not work in method parameters??
    // Bounded types work on classes
    // Bounded wildcards work on variables
    //public static void printNumberTypeVar(List<T extends Number> l) {
    //    //.//
    //}

    public static void printNumberTypeWildCard(List<? extends Number> l) {
        for (Number i : l) {
            System.out.println(i.intValue());
        }
    }

    public static void main(String[] args) {
        List<Number> lNumbers = new ArrayList<>();
        List<Integer> lInts = new ArrayList<>();

        lNumbers.add(1);
        lNumbers.add(2);
        lNumbers.add(3);

        lInts.add(1);
        lInts.add(2);
        lInts.add(3);

        printNumber(lNumbers);
        // printNumber(lInts); // Doesn't work -- expect a List<Numbers>
        printNumberTypeWildCard(lInts);

    }
}
