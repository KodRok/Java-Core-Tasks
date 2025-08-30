package school.sorokin.javacore.oop.lesson5;

/*SavingAccount (сберегательный счёт), который наследует Account и реализует withdraw
(например, не даёт уйти в минус).*/
public class SavingAccount extends Account implements Printable {

    public SavingAccount(double balance) {
        super(balance);
    }

    @Override
    public double withdraw(double amount) {
        if (balance < amount) {
            System.out.println("\"Ваш баланс не позволяет эту операцию");
            return balance;
        } else {
            balance -= amount;
            return balance;
        }
    }

    @Override
    public void printInfo() {
        System.out.println("Депозитный счет");
        System.out.println("Ваш баланс: " + balance);
    }
}