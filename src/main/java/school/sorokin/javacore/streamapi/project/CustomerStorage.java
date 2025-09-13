package school.sorokin.javacore.streamapi.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerStorage {
    public static List<Customer> createCustomers(List<Order> orders) {
        List<Customer> customers = new ArrayList<>();

        Set<Order> orders1 = new HashSet<>(orders.subList(0, 5));
        customers.add(new Customer(1L, "Анна", 1L, orders1));

        Set<Order> orders2 = new HashSet<>(orders.subList(5, 11));
        customers.add(new Customer(2L, "Борис", 2L, orders2));

        Set<Order> orders3 = new HashSet<>(orders.subList(11, 16));
        customers.add(new Customer(3L, "Виктория", 1L, orders3));

        Set<Order> orders4 = new HashSet<>(orders.subList(16, 23));
        customers.add(new Customer(4L, "Геннадий", 3L, orders4));

        Set<Order> orders5 = new HashSet<>(orders.subList(20, 25));
        customers.add(new Customer(5L, "Дарья", 2L, orders5));

        return customers;
    }
}
