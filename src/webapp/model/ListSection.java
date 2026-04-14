package webapp.model;

import java.util.List;

public class ListSection extends AbstractSection {
    private List<String> strings;

    public void setStrings(List<String> strings) {
        this.strings = strings;
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