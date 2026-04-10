package webapp.model;

import java.util.List;

public class ContactListSection extends Section<List<Contact>> {
    private List<Contact> sectionContent;

    @Override
    public void setSectionContent(List<Contact> sectionContent) {
        this.sectionContent = sectionContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Contact content : sectionContent) {
            sb.append(content).append("\n");
        }
        return sb.toString();
    }
}