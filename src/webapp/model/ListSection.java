package webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private List<String> strings;

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(strings);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String text : strings) {
            sb.append(text).append("\n");
        }
        return sb.toString();
    }
}