package webapp.model;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Company> companies;

    public CompanySection(List<Company> companies) {
        Objects.requireNonNull(companies, "companies of CompanySection must not be null");
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(companies);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Company company : companies) {
            sb.append(company).append("\n");
        }
        return sb.toString();
    }
}