package webapp.model;

import java.net.URI;
import java.util.Objects;

public class Link {
    private final String name;
    private final URI url;

    public Link(String name, URI url) {
        Objects.requireNonNull(name, "name of Link must not be null");
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(name, link.name) && Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return name + "\n" + Objects.toString(url, "");
    }
}