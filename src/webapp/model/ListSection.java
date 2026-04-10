package webapp.model;

import java.util.List;

public class ListSection extends Section<List<String>> {
    private List<String> sectionContent;

    @Override
    public void setSectionContent(List<String> sectionContent) {
        this.sectionContent = sectionContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String content : sectionContent) {
            sb.append(content).append("\n");
        }
        return sb.toString();
    }
}