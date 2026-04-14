package webapp.model;

import java.util.List;

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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(link).append("\n");
        for (Period period : periods) {
            sb.append(period).append("\n");
        }
        return sb.toString();
    }
}