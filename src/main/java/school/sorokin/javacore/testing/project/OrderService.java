package school.sorokin.javacore.testing.project;

import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        try {
            int savedOrderId = orderRepository.saveOrder(order);
            if (savedOrderId > 0) {
                return "Order processed successfully";
            } else {
                return "Order processing failed";
            }
        } catch (Exception e) {
            return "Order processing failed";
        }
    }

    public double calculateTotal(int id) {
        Optional<Order> orderOptional = orderRepository.getOrderById(id);
        return orderOptional.map(Order::getTotalPrice).orElse(0.0);
    }
}
