package school.sorokin.javacore.oop.lesson5;

/*CreditAccount (кредитный счёт),
тоже наследующий Account — в методе withdraw,
возможно, допускает уход в минус до определённого лимита.*/
public class CreditAccount extends Account implements Printable{

    public CreditAccount(double balance) {
        super(balance);
    }

    @Override
    public double withdraw(double amount) {
        if (balance - amount < 2000) {
            System.out.println("\"Можно взять в кредит только до 2000 рублей");
            return balance;
        } else {
            balance -= amount;
            return balance;
        }
    }

    @Override
    public void printInfo() {
        System.out.println("Кредитный счет");
        System.out.println("Ваш баланс: " + balance);
    }
}
