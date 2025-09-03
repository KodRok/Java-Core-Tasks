package school.sorokin.javacore.exceptions.lesson4;

import java.util.ArrayList;
import java.util.List;

/*Напишите класс Hotel с методом bookRoom(int roomNumber), который:
Проверяет, доступен ли номер.
Если номер недоступен, бросает RoomNotAvailableException.
Если доступен, выводит сообщение «Номер забронирован!».*/
public class Hotel {
    private static List<Integer> bookedRooms = new ArrayList<>();
    // Статическая инициализация, чтобы список был заполнен при загрузке класса.
    static {
        bookedRooms.add(101);
        bookedRooms.add(105);
        bookedRooms.add(203);
    }


    public void bookRoom(int roomNumber) throws RoomNotAvailableException,
            InvalidRoomNumberException {
        if (roomNumber < 1 || roomNumber > 1000) {
            throw new InvalidRoomNumberException("Номер " + roomNumber +
                    " недействителен. Номера должны быть от 1 до 1000.");
        }

        if (!isAvailable(roomNumber)) {
            throw new RoomNotAvailableException("Номер " + roomNumber + " недоступен.");
        } else {
            bookedRooms.add(roomNumber);
            System.out.println("Номер " + roomNumber + " забронирован!");
        }
    }

    public static boolean isAvailable(int roomNumber) {
        return !bookedRooms.contains(roomNumber);
    }

    public static List<Integer> getBookedRooms() {
        return bookedRooms;
    }
}
