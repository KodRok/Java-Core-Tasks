package school.sorokin.javacore.streamapi.project;

import school.sorokin.javacore.streamapi.project.enums.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderStorage {
    public static List<Order> createOrders(List<Product> products) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(101L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 20), OrderStatus.SHIPPED, Set.of(products.get(0))));
        orders.add(new Order(102L, LocalDate.of(2021, 2, 10), LocalDate.of(2021, 2, 15), OrderStatus.PROCESSING, Set.of(products.get(4), products.get(6))));
        orders.add(new Order(103L, LocalDate.of(2021, 2, 25), LocalDate.of(2021, 3, 1), OrderStatus.DELIVERED, Set.of(products.get(1), products.get(3))));
        orders.add(new Order(104L, LocalDate.of(2023, 3, 5), LocalDate.of(2023, 3, 10), OrderStatus.SHIPPED, Set.of(products.get(7), products.get(5))));
        orders.add(new Order(105L, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 3, 25), OrderStatus.CANCELED, Set.of(products.get(8), products.get(9))));

        orders.add(new Order(106L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 4, 15), OrderStatus.SHIPPED, Set.of(products.get(0), products.get(4))));
        orders.add(new Order(107L, LocalDate.of(2023, 4, 10), LocalDate.of(2023, 4, 15), OrderStatus.PROCESSING, Set.of(products.get(6), products.get(7))));
        orders.add(new Order(108L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 25), OrderStatus.DELIVERED, Set.of(products.get(2), products.get(5))));
        orders.add(new Order(109L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 25), OrderStatus.SHIPPED, Set.of(products.get(1), products.get(8))));
        orders.add(new Order(110L, LocalDate.of(2023, 5, 20), LocalDate.of(2023, 5, 25), OrderStatus.CANCELED, Set.of(products.get(3), products.get(11))));

        orders.add(new Order(111L, LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 5), OrderStatus.SHIPPED, Set.of(products.get(0), products.get(2))));
        orders.add(new Order(112L, LocalDate.of(2021, 3, 10), LocalDate.of(2021, 3, 15), OrderStatus.DELIVERED, Set.of(products.get(4), products.get(6))));
        orders.add(new Order(113L, LocalDate.of(2021, 6, 20), LocalDate.of(2021, 6, 25), OrderStatus.SHIPPED, Set.of(products.get(1), products.get(3))));
        orders.add(new Order(114L, LocalDate.of(2021, 2, 1), LocalDate.of(2021, 3, 15), OrderStatus.PROCESSING, Set.of(products.get(8), products.get(9))));
        orders.add(new Order(115L, LocalDate.of(2023, 7, 10), LocalDate.of(2023, 7, 15), OrderStatus.DELIVERED, Set.of(products.get(10), products.get(11))));

        orders.add(new Order(116L, LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 5), OrderStatus.SHIPPED, Set.of(products.get(0), products.get(2))));
        orders.add(new Order(117L, LocalDate.of(2023, 8, 10), LocalDate.of(2023, 8, 15), OrderStatus.SHIPPED, Set.of(products.get(4), products.get(6))));
        orders.add(new Order(118L, LocalDate.of(2023, 8, 20), LocalDate.of(2023, 8, 25), OrderStatus.DELIVERED, Set.of(products.get(1), products.get(3))));
        orders.add(new Order(119L, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 5), OrderStatus.PROCESSING, Set.of(products.get(7), products.get(5))));
        orders.add(new Order(120L, LocalDate.of(2023, 9, 10), LocalDate.of(2023, 9, 15), OrderStatus.DELIVERED, Set.of(products.get(8), products.get(9))));

        orders.add(new Order(121L, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 5), OrderStatus.SHIPPED, Set.of(products.get(10), products.get(11))));
        orders.add(new Order(122L, LocalDate.of(2023, 10, 10), LocalDate.of(2023, 10, 15), OrderStatus.PROCESSING, Set.of(products.get(0), products.get(4))));
        orders.add(new Order(123L, LocalDate.of(2023, 10, 20), LocalDate.of(2023, 10, 25), OrderStatus.DELIVERED, Set.of(products.get(6), products.get(7))));
        orders.add(new Order(124L, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5), OrderStatus.SHIPPED, Set.of(products.get(2), products.get(5))));
        orders.add(new Order(125L, LocalDate.of(2023, 11, 10), LocalDate.of(2023, 11, 15), OrderStatus.DELIVERED, Set.of(products.get(1), products.get(8))));

        return orders;
    }
}
