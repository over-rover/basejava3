package webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private URI url;

    public Link() {
    }

    public Link(String name) {
        this(name, null);
    }

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