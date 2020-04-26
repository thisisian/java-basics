import java.sql.Ref;

public class ReferenceTest {
    public int data;

    public ReferenceTest(int data) {
        this.data = data;
    }

    public static boolean isSame(ReferenceTest r1, ReferenceTest r2) {
       return (r1 == r2);
    }

    public static void main(String[] args){
        var rt = new ReferenceTest(10);
        doesRefChange(rt);
        Integer x = 10;
        doesRefChange(x);
    }

    public static void doesRefChange(Object o) {
        var rt = (ReferenceTest)o;
        System.out.println(rt.data);
    }
}
