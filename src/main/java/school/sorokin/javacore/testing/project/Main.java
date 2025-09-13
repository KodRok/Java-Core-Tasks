package school.sorokin.javacore.testing.project;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepositoryImpl();
        OrderService orderService = new OrderService(orderRepository);

        Order order = new Order(1, "Product1", 4, 1200.0);
        String result1 = orderService.processOrder(order);
        System.out.println(result1);

        double totalPrice = orderService.calculateTotal(1);
        System.out.println("Общая стоимость заказа кол-во 4 и цена за единицу 1200: " + totalPrice);

        double nullOrderTotalPrice = orderService.calculateTotal(100);
        System.out.println("Общая стоимость несуществующего заказа: " + nullOrderTotalPrice);

        Order zeroUnitPriceOrder = new Order(2, "Product2", 1, 0.0);
        orderRepository.saveOrder(zeroUnitPriceOrder);
        double zeroUnitPriceTotalPrice = orderService.calculateTotal(2);
        System.out.println("Общая стоимость заказа с нулевой ценой: " + zeroUnitPriceTotalPrice);

        Order zeroQuantityOrder = new Order(3, "Product3", 0, 50.0);
        orderRepository.saveOrder(zeroQuantityOrder);
        double zeroQuantityTotalPrice= orderService.calculateTotal(3);
        System.out.println("Общая стоимость заказа с нулевым количеством: " + zeroQuantityTotalPrice);
    }
}
