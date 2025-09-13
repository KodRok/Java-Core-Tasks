package school.sorokin.javacore.streamapi.project;

import school.sorokin.javacore.streamapi.project.enums.OrderStatus;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Order {
    private final Long id;
    private final LocalDate orderDate;
    private final LocalDate deliveryDate;
    private final OrderStatus status;
    private final Set<Product> products;


    public Order(Long id, LocalDate orderDate, LocalDate deliveryDate, OrderStatus status, Set<Product> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderDate, order.orderDate) && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(status, order.status) && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDate, deliveryDate, status, products);
    }

    @Override
    public String toString() {
        return "Заказ номер " + id +
                ", дата заказа: " + orderDate +
                ", дата доставки: " + deliveryDate +
                ", статус: " + status.getDisplayName() +
                ", товары в заказе: " + products + ".";
    }
}