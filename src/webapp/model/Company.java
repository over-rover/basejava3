package webapp.model;

import static webapp.util.DateUtil.NOW;
import static webapp.util.DateUtil.of;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Link link;
    private final List<Position> positions;

    public Company(Link link, List<Position> positions) {
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
        for (Position position : positions) {
            sb.append(position).append("\n");
        }
        return sb.toString();
    }

    public static class Position implements Serializable {
        private final LocalDate startDate;
        private final LocalDate stopDate;
        private final String title;
        private final String description;

        public Position(int startYear, Month startMonth, String title) {
            this(of(startYear, startMonth), NOW, title, null);
        }

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int stopYear, Month stopMonth, String title) {
            this(of(startYear, startMonth), of(stopYear, stopMonth), title, null);
        }

        public Position(int startYear, Month startMonth, int stopYear, Month stopMonth, String title, String description) {
            this(of(startYear, startMonth), of(stopYear, stopMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate stopDate, String title, String description) {
            Objects.requireNonNull(title, "position of Period must not be null");
            Objects.requireNonNull(startDate, "startDate of Period must not be null");
            Objects.requireNonNull(stopDate, "stopDate of Period must not be null");
            this.startDate = startDate;
            this.stopDate = stopDate;
            this.title = title;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(startDate, position.startDate) &&
                    Objects.equals(stopDate, position.stopDate) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, stopDate, title, description);
        }

        @Override
        public String toString() {
            return startDate + "\n" +
                    stopDate + "\n" +
                    title + "\n" +
                    Objects.toString(description, "");
        }
    }
}