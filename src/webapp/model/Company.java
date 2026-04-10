package webapp.model;

import java.time.LocalDate;

public class Company {
    private String companyName;
    private LocalDate startDate;
    private LocalDate stopDate;
    private String position = "position may be null in educational company. null-point exception possible in toString()";
    private String duty;

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

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
        return companyName + "\n" +
                startDate + "\n" +
                stopDate + "\n" +
                position + "\n" +
                duty;
    }
}