package school.sorokin.javacore.collections.lesson1;

public class GenericBox<T>{
    private T content;

    public GenericBox(T content) {
        this.content = content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    // Метод для сравнения содержимого с другой коробкой
    public boolean isEqual(GenericBox<T> other) {
        if (this.content == null) {
            return other.getContent() == null;
        }
        return this.content.equals(other.getContent());
    }

    // Метод для обмена содержимым с другой коробкой
    public void swap(GenericBox<T> other) {
        T temp = this.content;
        this.content = other.getContent();
        other.setContent(temp);
    }
}
