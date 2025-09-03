package school.sorokin.javacore.exceptions.lesson4;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*В методе main напишите код, который:
Создаёт объект Hotel.
Пытается забронировать номер, который «уже занят».
Обрабатывает RoomNotAvailableException и выводит понятное сообщение пользователю.*/
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        try {
            System.out.println("Введите номер комнаты, которую хотите забронировать (от 1 до 1000):");
            int roomToBook = scanner.nextInt();
            hotel.bookRoom(roomToBook);

        } catch (InputMismatchException e) {
            logger.log(Level.SEVERE, "Нужно ввести число!", e);
        } catch (RoomNotAvailableException | InvalidRoomNumberException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            scanner.close();
        }

        System.out.println("Список забронированных комнат: " + Hotel.getBookedRooms());
    }
}
