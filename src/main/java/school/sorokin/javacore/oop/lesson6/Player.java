package school.sorokin.javacore.oop.lesson6;


/*Создайте класс Player с полями String name и int personalScore.
В конструкторе Player(String name, int points)
прибавляйте очки к статическому GameScore и также сохраняйте их в personalScore.*/
public class Player {
    String name;
    int personalScore;


    public Player(String name, int personalScore) {
        this.name = name;
        this.personalScore = personalScore;
        GameScore.addPoints(personalScore);
    }
}
