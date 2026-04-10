package webapp.model;

import java.util.List;

public class CompanyListSection extends Section<List<Company>> {
    private List<Company> sectionContent;

    @Override
    public void setSectionContent(List<Company> sectionContent) {
        this.sectionContent = sectionContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Company company : sectionContent) {
            sb.append(company).append("\n");
        }
        return sb.toString();
    }
}