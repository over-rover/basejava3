package webapp.model;

import java.time.LocalDate;

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
    public String toString() {
        return startDate + "\n" +
                stopDate + "\n" +
                position + "\n" +
                duty;
    }
}