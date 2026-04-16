package webapp.model;

import java.net.URI;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(name, link.name) && Objects.equals(hyperlink, link.hyperlink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hyperlink);
    }

    @Override
    public String toString() {
        return name;
    }
}