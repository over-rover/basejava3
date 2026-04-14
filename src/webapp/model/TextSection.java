package webapp.model;

public class TextSection extends AbstractSection {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text + "\n";
    }
}