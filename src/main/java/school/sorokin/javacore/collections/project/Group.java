package school.sorokin.javacore.collections.project;

public enum Group {
    WORK(1),
    FAMILY(2),
    FRIENDS(3);

    private final int value;

    Group(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Group fromValue(int value) {
        for (Group group : Group.values()) {
            if (group.value == value) {
                return group;
            }
        }
        throw new IllegalArgumentException("Invalid group value: " + value);
    }
}