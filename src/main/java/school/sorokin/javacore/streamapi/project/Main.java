package school.sorokin.javacore.streamapi.project;

import school.sorokin.javacore.streamapi.project.enums.Category;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> allProducts = ProductStorage.createProducts();
        List<Order> allOrders = OrderStorage.createOrders(allProducts);
        List<Customer> allCustomers = CustomerStorage.createCustomers(allOrders);

        //Задание 1
        List<Product> booksPriceOver100List = allProducts.stream()
                .filter(product -> product.getCategory() == Category.BOOKS)
                .filter(product -> product.getPrice().compareTo(new BigDecimal("100")) > 0)
                .collect(Collectors.toList());
        System.out.println("Список товаров из категории \"Books\" с ценой более 100:");
        booksPriceOver100List.forEach(System.out::println);
        System.out.println();

        //Задание 2
        List<Order> ordersWithChildrenProductsList = allOrders.stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> product.getCategory() == Category.CHILDRENS_PRODUCTS))
                .collect(Collectors.toList());
        System.out.println("Список заказов с продуктами из категории \"Children's products\":");
        ordersWithChildrenProductsList.forEach(System.out::println);
        System.out.println();

        //Задание 3
        BigDecimal totalDiscountedPrice = allProducts.stream()
                .filter(product -> product.getCategory() == Category.TOYS)
                .map(product -> product.getPrice().multiply(new BigDecimal("0.90")))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Стоимость со скидкой 10% всех продуктов из категории \"Toys\": "
                + totalDiscountedPrice);
        System.out.println();

        //Задание 4
        Set<Product> twoLevelCustomersProducts = allCustomers.stream()
                .filter(customer -> customer.getLevel() == 2L)
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().isAfter
                        (LocalDate.of(2021, 1, 31)) &&
                        order.getOrderDate().isBefore(LocalDate.of(2021, 4, 1)))
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.toSet()); // Используем toSet() для уникальности продуктов
        System.out.println("Список товаров, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021:");
        twoLevelCustomersProducts.forEach(System.out::println);
        System.out.println();

        //Задание 5
        List<Product> twoCheapestBooks = allProducts.stream()
                .filter(product -> product.getCategory() == Category.BOOKS)
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("Топ-2 самых дешевых товаров из категории \"Books\":");
        twoCheapestBooks.forEach(System.out::println);
        System.out.println();

        //Задание 6
        List<Order> tripleLastOrders = allOrders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Три последних заказа");
        tripleLastOrders.forEach(System.out::println);
        System.out.println();

        //Задание 7
        System.out.println("Список ID заказов от 15-марта-2021:");
        List<Product> productsFromOrders = allOrders.stream()
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
                .peek(order -> System.out.println(order.getId()))
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.toList());
        System.out.println("Список товаров из этих заказов от 15-марта-2021: ");
        productsFromOrders.forEach(System.out::println);
        System.out.println();

        //Задание 8
        BigDecimal totalSum = allOrders.stream()
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Общая сумма заказов, сделанных в феврале 2021: " + totalSum);
        System.out.println();

        //Задание 9
        List<Order> ordersByDate = allOrders.stream()
                .filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
                .toList();
        if (ordersByDate.isEmpty()) {
            System.out.println("Заказов на эту дату не найдено.");
            return;
        }

        BigDecimal totalAmountByDate = ordersByDate.stream()
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal averagePaymentByDate = totalAmountByDate.divide(BigDecimal.valueOf(ordersByDate.size()));
        System.out.println("Средний платеж по заказам, сделанным 14-марта-2021: " + averagePaymentByDate);
        System.out.println();

        //Задание 10
        List<Product> books = allProducts.stream()
                .filter(product -> product.getCategory() == Category.BOOKS)
                .toList();
        BigDecimal booksPriceSumma = books.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal booksPriceMin = books.stream()
                .map(Product::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal booksPriceMax = books.stream()
                .map(Product::getPrice)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal booksPriceAvg = BigDecimal.ZERO;
        if (!books.isEmpty()) {
            MathContext mc = new MathContext(4);
            booksPriceAvg = booksPriceSumma.divide(BigDecimal.valueOf(books.size()), mc);
        }
        System.out.println("Статистика по книгам:");
        System.out.println("    Количество товаров категории Books: " + books.size());
        System.out.println("    Суммарная их стоимость: " + booksPriceSumma);
        System.out.println("    Средняя их стоимость: " + booksPriceAvg);
        System.out.println("    Минимальная цена в категории: " + booksPriceMin);
        System.out.println("    Максимальная цена в категории: " + booksPriceMax);
        System.out.println();

        //Задание 11
        Map<Long, Integer> orderIdToProductCountMap = allOrders.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        order -> order.getProducts().size()
                ));

        orderIdToProductCountMap.forEach((orderId, count) ->
                System.out.println("ID заказа: " + orderId + ", количество товаров заказа: " + count)
        );
        System.out.println();

        //Задание 12
        Map<Customer, List<Order>> customerToOrdersMap = allCustomers.stream()
                .collect(Collectors.toMap(
                        customer -> customer,
                        customer -> new ArrayList<>(customer.getOrders())
                ));

        customerToOrdersMap.forEach((customer, orders) -> {
            System.out.println("\nПокупатель: " + customer.getName());
            System.out.println("Заказы:");
            orders.forEach(System.out::println);
        });
        System.out.println();

        //Задание 13
        Map<Order, BigDecimal> orderToTotalAmountMap = allOrders.stream()
                .collect(Collectors.toMap(
                        order -> order,
                        order -> order.getProducts().stream()
                                .map(Product::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ));

        orderToTotalAmountMap.forEach((order, total) ->
                System.out.println("ID заказа: " + order.getId() + ", общая сумма этого заказа: " + total)
        );
        System.out.println();

        //Задание 14
        Map<Category, List<String>> categoryToProductNamesMap = allProducts.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())
                ));

        categoryToProductNamesMap.forEach((category, productNames) -> {
            System.out.println("Категория: " + category);
            System.out.println("  Товары этой категории: " + productNames);
        });
        System.out.println();

        //Задание 15
        Map<Category, Optional<Product>> mostExpensiveProductByCategory = allProducts.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparing(Product::getPrice))
                ));

        mostExpensiveProductByCategory.forEach((category, productOptional) ->
                productOptional.ifPresent(product ->
                        System.out.println("Категория: " + category + ", самый дорогой товар: " + product.getName() + " (" + product.getPrice() + ")")
                )
        );
    }
}


