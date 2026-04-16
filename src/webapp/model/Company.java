package webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private final Link link;
    private final List<Period> positions;

    public Company(Link link, List<Period> positions) {
        Objects.requireNonNull(link, "link of Link must not be null");
        Objects.requireNonNull(positions, "positions of Link must not be null");
        this.link = link;
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(link, company.link) &&
                Objects.equals(positions, company.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, positions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(link).append("\n");
        for (Period position : positions) {
            sb.append(position).append("\n");
        }
        return sb.toString();
    }
}