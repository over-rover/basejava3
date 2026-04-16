package webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private String position = "position is null";
    private String duty = "duty is null";
    private LocalDate startDate;
    private LocalDate stopDate;

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStopDate(LocalDate stopDate) {
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
                duty;
    }
}