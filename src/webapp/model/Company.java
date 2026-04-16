package webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private Link link;
    private List<Period> periods;

    public void setLink(Link link) {
        this.link = link;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(link, company.link) && Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, periods);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(link).append("\n");
        for (Period period : periods) {
            sb.append(period).append("\n");
        }
        return sb.toString();
    }
}