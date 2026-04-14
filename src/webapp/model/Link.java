package webapp.model;

import java.net.URI;

public class Link {
    private String name;
    private URI hyperlink;

    public void setName(String name) {
        this.name = name;
    }

    public void setHyperlink(URI hyperlink) {
        this.hyperlink = hyperlink;
    }

    @Override
    public String toString() {
        return name;
    }
}