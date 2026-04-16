package webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private final String text;

    public TextSection(String text) {
        Objects.requireNonNull(text, "text of TextSection must not be null");
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(text);
    }

    @Override
    public String toString() {
        return text + "\n";
    }
}