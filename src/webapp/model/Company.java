package webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private Link link;
    private List<Period> positions;

    public void setLink(Link link) {
        this.link = link;
    }

    public void setPositions(List<Period> positions) {
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(link, company.link) && Objects.equals(positions, company.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, positions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(link).append("\n");
        for (Period period : positions) {
            sb.append(period).append("\n");
        }
        return sb.toString();
    }
}