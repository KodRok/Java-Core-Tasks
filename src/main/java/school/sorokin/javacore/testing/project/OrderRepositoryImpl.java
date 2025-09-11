package school.sorokin.javacore.testing.project;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    private final Map<Integer, Order> orders = new HashMap<>();
    private int nextId = 1;

    @Override
    public int saveOrder(Order order) {
        if (order.getId() == 0) {
            order = new Order(nextId++, order.getProductName(), order.getQuantity(), order.getUnitPrice());
        }
        orders.put(order.getId(), order);
        return order.getId();
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        if (orders.containsKey(id)) {
            return Optional.of(orders.get(id));
        }
        return Optional.empty();
    }

}
