package school.sorokin.javacore.oop.lesson8;

public class Main {
    public static void main(String[] args) {
        Object o = new String("Test");
        String s = (String)o;
        System.out.println(s);

        Animal a = new Dog ();
        Dog d = (Dog)a;
        System.out.println(d);

        Object x = 100;
        String str = (String) x; //error here
        System.out.println(str);

        Number num = 100;
        int y = (int) num;
        System.out.println(y);


        System.out.println();
    }
}
