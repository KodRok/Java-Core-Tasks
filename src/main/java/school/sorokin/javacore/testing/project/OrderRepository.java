package school.sorokin.javacore.testing.project;

import java.util.Optional;

public interface OrderRepository {
    int saveOrder(Order order);
    Optional<Order> getOrderById(Integer id);
}
