package school.sorokin.javacore.streamapi.project;

import school.sorokin.javacore.streamapi.project.enums.Category;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final Long id;
    private final String name;
    private final Category category;
    private final BigDecimal price;

    public Product(Long id, String name, Category category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(category, product.category) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, price);
    }

    @Override
    public String toString() {
        return "Товар с номером " + id +
                ", наименование: '" + name + '\'' +
                ", категория: \"" + category.getDisplayName() +
                "\", цена: " + price +
                '.';
    }
}
