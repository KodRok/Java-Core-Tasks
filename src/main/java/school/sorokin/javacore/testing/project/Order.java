package school.sorokin.javacore.testing.project;

public class Order {
    private Integer id;
    private String productName;
    private int quantity;
    private double unitPrice;

    public Order(Integer id, String productName, int quantity, double unitPrice) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return quantity * unitPrice;
    }

    public Integer getId() {
        return id;
    }
}
