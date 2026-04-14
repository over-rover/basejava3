package webapp.model;

import java.util.List;

public class CompanySection extends AbstractSection {
    private List<Company> companies;

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
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