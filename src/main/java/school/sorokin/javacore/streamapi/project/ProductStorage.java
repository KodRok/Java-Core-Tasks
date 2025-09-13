package school.sorokin.javacore.streamapi.project;

import school.sorokin.javacore.streamapi.project.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public class ProductStorage {
    public static List<Product> createProducts() {
        return List.of(
                new Product(1L, "Мастер и Маргарита", Category.BOOKS, new BigDecimal("450.50")),
                new Product(2L, "Война и мир", Category.BOOKS, new BigDecimal("700.00")),
                new Product(3L, "Паззл", Category.TOYS, new BigDecimal("120.00")),
                new Product(4L, "Конструктор", Category.TOYS, new BigDecimal("850.75")),
                new Product(5L, "Духи", Category.PERFUME, new BigDecimal("5000.00")),
                new Product(6L, "Дезодорант", Category.PERFUME, new BigDecimal("6500.00")),
                new Product(7L, "Присыпка", Category.CHILDRENS_PRODUCTS, new BigDecimal("250.00")),
                new Product(8L, "Кубики", Category.CHILDRENS_PRODUCTS, new BigDecimal("180.00")),
                new Product(9L, "Гарри Поттер", Category.BOOKS, new BigDecimal("550.00")),
                new Product(10L, "Машинка", Category.TOYS, new BigDecimal("350.00")),
                new Product(11L, "Памперсы", Category.CHILDRENS_PRODUCTS, new BigDecimal("99.99")),
                new Product(12L, "Одеколон", Category.PERFUME, new BigDecimal("4800.00"))
        );
    }
}
