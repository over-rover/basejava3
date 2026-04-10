package webapp.model;

public class ContactSection extends Section<Contact> {
    private Contact sectionContent;

    @Override
    public void setSectionContent(Contact sectionContent) {
        this.sectionContent = sectionContent;
    }

    @Override
    public String toString() {
        return sectionContent.toString() + "\n";
    }
}