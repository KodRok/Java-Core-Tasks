package school.sorokin.javacore.exceptions.project;

import school.sorokin.javacore.exceptions.project.customExceptions.ItemNotFoundException;
import school.sorokin.javacore.exceptions.project.customExceptions.NegativeNumberException;
import school.sorokin.javacore.exceptions.project.customExceptions.NoAvailableCopiesException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductCatalog {
    private List<Product> catalog;
    private Scanner scanner;

    public ProductCatalog() {
        this.catalog = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Показать все продукты");
            System.out.println("2. Добавить новый продукт");
            System.out.println("3. Взять продукт");
            System.out.println("4. Вернуть продукт");
            System.out.println("5. Выйти");

            System.out.print("Введите цифру: ");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Ошибка: Пожалуйста, введите цифру от 1 до 5");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    getAllProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    if (catalog.isEmpty()) {
                        System.out.println("Каталог пуст.");
                        break;
                    }
                    System.out.print("Введите название продукта, который хотите взять: ");
                    String takeName = scanner.nextLine();
                    System.out.print("Введите его производителя: ");
                    String takeProducer = scanner.nextLine();
                    try {
                        takeProduct(takeName, takeProducer);
                    } catch (ItemNotFoundException e) {
                        System.err.println("Ошибка: " + e.getMessage());
                    } catch (NoAvailableCopiesException e) {
                        System.err.println("Ошибка: " + e.getMessage());
                    }
                    break;
                case 4:
                    if (catalog.isEmpty()) {
                        System.out.println("Каталог пуст.");
                        break;
                    }
                    System.out.print("Введите название продукта, который хотите вернуть: ");
                    String returnName = scanner.nextLine();
                    System.out.print("Введите его производителя: ");
                    String returnProducer = scanner.nextLine();
                    returnProduct(returnName, returnProducer);
                    break;
                case 5:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    public void getAllProducts() {
        if (catalog.isEmpty()) {
            System.out.println("Каталог пуст.");
            return;
        }
        System.out.println("Список всех продуктов:");
        catalog.forEach(System.out::println);
    }

    public void addProduct() {
        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine();
        System.out.print("Введите производителя: ");
        String producer = scanner.nextLine();
        System.out.print("Введите количество: ");
        int count;
        try {
            count = scanner.nextInt();
            scanner.nextLine();
            if (count < 0) {
                System.err.println("Ошибка: Количество товара не может быть отрицательным.");
                return;
            }
            Product newProduct = new Product(name, producer, count);
            Product existProduct = findSameProduct(name, producer);
            if (existProduct != null) {
                existProduct.setCount(existProduct.getCount() + count);
                System.out.println("Теперь продуктов '" + name +
                        "' производителя '" + producer + "' стало больше.");
            } else {
                catalog.add(newProduct);
                System.out.println("Продукт '" + name +
                        "' производителя '" + producer + "' добавлен.");
            }
        } catch (InputMismatchException | NegativeNumberException e) {
            System.err.println("Ошибка: Количество должно быть неотрицательным целым числом.");
            scanner.nextLine();
        }
    }

    public void takeProduct(String name, String producer) throws ItemNotFoundException, NoAvailableCopiesException {
        Product product = findSameProduct(name, producer);
        if (product == null) {
            throw new ItemNotFoundException("Не найдено!");
        }

        if (product.getCount() == 0) {
            throw new NoAvailableCopiesException("Нет доступных копий товара.");
        }

        product.takeCopy();
        System.out.println("Вы взяли продукт '" + name + "' производителя '" + producer + "'.");
    }

    public void returnProduct(String name, String producer) {
        Product product = findSameProduct(name, producer);
        if (product == null) {
            System.err.println("Такого продукта нет!");
        } else {
            product.returnCopy();
            System.out.println("Вы вернули продукт '" + name + "' производителя '" + producer + "'.");
        }
    }

    private Product findSameProduct(String name, String producer) {
        Product findProduct = null;
        for (Product product : catalog) {
            if (product.getName().equalsIgnoreCase(name)
                    && product.getProducer().equalsIgnoreCase(producer)) {
                findProduct = product;
                return findProduct;
            }
        }
        return findProduct;
    }
}
