package webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private List<String> lines;

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lines);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String text : lines) {
            sb.append(text).append("\n");
        }
        return sb.toString();
    }
}