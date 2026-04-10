package webapp.model;

public enum ContactType {
    TEL("Тел."),
    SKYPE("Skype"),
    EMAIL("E-mail"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title + ": ";
    }
}
