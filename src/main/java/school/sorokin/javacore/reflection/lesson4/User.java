package school.sorokin.javacore.reflection.lesson4;


public class User {
    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;

    private String bio; // Это поле не будет проверяться

    public User(String username, String password, String bio) {
        this.username = username;
        this.password = password;
        this.bio = bio;
    }

    // Геттеры и сеттеры
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }
}

