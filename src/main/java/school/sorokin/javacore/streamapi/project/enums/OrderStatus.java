package school.sorokin.javacore.streamapi.project.enums;

public enum OrderStatus {
    PROCESSING("Обработка"),
    SHIPPED("Отправлен"),
    DELIVERED("Доставлен"),
    CANCELED("Отменен");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
