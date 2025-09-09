package school.sorokin.javacore.exceptions.project;

public class Product {
    private String name;
    private String producer;
    private int count;

    public Product(String name, String producer, int count) {
        this.name = name;
        this.producer = producer;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public int getCount() {
        return count;
    }

    public void takeCopy() {
        if (count > 0) {
            count--;
        }
    }

    public void returnCopy() {
        count++;
    }

    @Override
    public String toString() {
        return "Название: " + name + ", Производитель: " + producer + ", Доступно: " + count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
