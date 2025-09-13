package school.sorokin.javacore.collections.project;

import java.util.Objects;

public class Contact {
    private String name;
    private String phone;
    private String email;
    private Group group;

    public Contact(String name, String phone, String email, Group group) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public Contact(String name, String phone) {
        this(name, phone, null, null); // Вызов основного конструктора
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) &&
                Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", Телефон: " + phone + ", Группа: " + group;
    }
}

