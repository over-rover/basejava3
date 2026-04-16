package webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private LocalDate startDate;
    private LocalDate stopDate;
    private String position = "position is null";
    private String duty = "duty is null";

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) &&
                Objects.equals(stopDate, period.stopDate) &&
                Objects.equals(position, period.position) &&
                Objects.equals(duty, period.duty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, stopDate, position, duty);
    }

    @Override
    public String toString() {
        return startDate + "\n" +
                stopDate + "\n" +
                position + "\n" +
                duty;
    }
}