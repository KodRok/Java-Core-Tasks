package school.sorokin.javacore.oop.lesson5;
/*Задача: Попробуйте на практике совместить абстрактные классы и интерфейсы.

Создайте абстрактный класс Account (например, банковский счёт) с полем balance и методами:
Конструктор, устанавливающий balance.
Метод deposit(double amount), который увеличивает баланс.
Абстрактный метод withdraw(double amount).

Создайте интерфейс Printable, в котором объявите метод printInfo().

Создайте конкретные классы:
SavingAccount (сберегательный счёт), который наследует Account и реализует withdraw
(например, не даёт уйти в минус).
CreditAccount (кредитный счёт), тоже наследующий Account — в методе withdraw,
возможно, допускает уход в минус до определённого лимита.
Оба класса реализуют Printable,
переопределяя метод printInfo() для вывода информации о типе счёта и балансе.

Напишите класс Main с main(), где создайте несколько аккаунтов:
SavingAccount, CreditAccount, добавьте им денег, попробуйте снять,
выведите информацию через printInfo() (полиморфно, через переменную типа Printable).

Что проверить:
Убедитесь, что из Account нельзя сделать new Account().
Класс SavingAccount и CreditAccount переопределяют withdraw() и обязательно предоставляют логику.
Метод printInfo() вызывается через интерфейсную ссылку: Printable p = new SavingAccount(...); p.printInfo();*/

public class Main {
    public static void main(String[] args) {
        SavingAccount savingAccount = new SavingAccount(1000);
        savingAccount.withdraw(3000);
        Printable p = new SavingAccount(35);
        p.printInfo();
        CreditAccount creditAccount = new CreditAccount(100);
        creditAccount.withdraw(6000);
        creditAccount.printInfo();
        Printable p1 = new CreditAccount(456);
        p1.printInfo();

    }
}
