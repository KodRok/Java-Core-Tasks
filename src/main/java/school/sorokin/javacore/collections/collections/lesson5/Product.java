package school.sorokin.javacore.collections.collections.lesson5;

public class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        // Некорректная реализация equals: сравнивает только по id
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Product)) return false;
            Product other = (Product) obj;
            return this.id == other.id;
        }

        // Некорректная реализация hashCode: всегда возвращает одно и то же значение
        @Override
        public int hashCode() {
            return 42; // Пример константного значения
        }

        @Override
        public String toString() {
            return "Product{id=" + id + ", name='" + name + "', price=" + price + '}';
        }
}
