package school.sorokin.javacore.collections.collections.lesson3;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Bob");
        names.add("Anna");
        names.add("Jakob");
        names.add("Viktor");
        names.add("Anna");
        names.add("Anna");
        System.out.println(names);

        Set<String>setNames = new HashSet<>(names);
        System.out.println(setNames);

        Set<String>treeSetNames = new TreeSet<>(names);
        System.out.println(treeSetNames);

        Set<String>linkedHashSetNames = new LinkedHashSet<>(names);
        System.out.println(linkedHashSetNames);
    }
}
