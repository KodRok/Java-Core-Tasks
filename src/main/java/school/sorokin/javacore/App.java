package school.sorokin.javacore;

import school.sorokin.javacore.oop.lesson1.Main;
import school.sorokin.javacore.oop.lesson8.Animal;
import school.sorokin.javacore.oop.lesson8.Dog;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Object o = new String("Test");
        String s = (String)o;

        Animal a = new Dog();
        Dog d = (Dog) a;
        System.out.println();
    }
}
