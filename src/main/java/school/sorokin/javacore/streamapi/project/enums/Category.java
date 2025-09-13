package school.sorokin.javacore.streamapi.project.enums;

public enum Category {
    CHILDRENS_PRODUCTS("Children's products"),
    BOOKS("Books"),
    TOYS("Toys"),
    PERFUME("Perfume");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
