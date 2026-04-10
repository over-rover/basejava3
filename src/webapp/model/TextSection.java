package webapp.model;

public class TextSection extends Section<String> {
    private String sectionContent;

    @Override
    public void setSectionContent(String stringContent) {
        this.sectionContent = stringContent;
    }

    @Override
    public String toString() {
        return sectionContent + "\n";
    }
}