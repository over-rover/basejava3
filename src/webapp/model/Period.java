package webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final String position;
    private final String duty;
    private final LocalDate startDate;
    private final LocalDate stopDate;

    public Period(String position, LocalDate startDate, LocalDate stopDate) {
        this(position, null, startDate, stopDate);
    }

    public Period(String position, String duty, LocalDate startDate, LocalDate stopDate) {
        Objects.requireNonNull(position, "position of Period must not be null");
        Objects.requireNonNull(startDate, "startDate of Period must not be null");
        Objects.requireNonNull(stopDate, "stopDate of Period must not be null");
        this.position = position;
        this.duty = duty;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(position, period.position) &&
                Objects.equals(duty, period.duty) &&
                Objects.equals(startDate, period.startDate) &&
                Objects.equals(stopDate, period.stopDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, duty, startDate, stopDate);
    }

    @Override
    public String toString() {
        return startDate + "\n" +
                stopDate + "\n" +
                position + "\n" +
                Objects.toString(duty, "");
    }
}