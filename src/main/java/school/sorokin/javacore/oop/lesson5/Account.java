package school.sorokin.javacore.oop.lesson5;
/*Создайте абстрактный класс Account (например, банковский счёт) с полем balance и методами:
Конструктор, устанавливающий balance.
Метод deposit(double amount), который увеличивает баланс.
Абстрактный метод withdraw(double amount).*/

public abstract class Account {
    double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public double deposit(double amount) {
        return balance + amount;
    }

    public abstract double withdraw(double amount);
}
