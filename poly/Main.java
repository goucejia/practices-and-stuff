/**
 * Created by fang on 11/30/18.
 */
public class Main {
    public static void main(String[] args) {
//        System.out.print("hello");
        Poly a = new Poly().plus(1, 6).plus(1, 4).plus(1, 3).plus(1, 2).plus(1, 1);
//        Poly b = new Poly().plus(3, 5).plus(-8, 4).plus(-2, 3).plus(5, 2).plus(8, 1);
        Poly c = new Poly();
        System.out.println(a);
//        System.out.println(b);
        System.out.println(a.plus(c));

    }
}
