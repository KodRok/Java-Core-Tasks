package school.sorokin.javacore.oop.lesson6;
/*Создать класс GameScore,
который будет хранить общий счёт (количество очков) для всей игры
и позволять увеличивать этот счёт.
При этом у каждого игрока может быть своё имя,
и в конструкторе класса Player мы будем «прибавлять» очки к общему счёту.*/
public class GameScore {
    static int  totalScore = 10;

    static int addPoints(int points){
        totalScore += points;
        return totalScore;
    }
}
