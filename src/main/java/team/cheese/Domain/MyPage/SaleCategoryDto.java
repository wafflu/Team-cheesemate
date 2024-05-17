package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class SaleCategoryDto {
    private String sal_cd;
    private String name;
    private char state;

    private Timestamp first_date;

    private String first_id;

    private Timestamp last_date;

    private String last_id;

    public String getSal_cd() {
        return sal_cd;
    }

    public void setSal_cd(String sal_cd) {
        this.sal_cd = sal_cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public Timestamp getFirst_date() {
        return first_date;
    }

    public void setFirst_date(Timestamp first_date) {
        this.first_date = first_date;
    }

    public String getFirst_id() {
        return first_id;
    }

    public void setFirst_id(String first_id) {
        this.first_id = first_id;
    }

    public Timestamp getLast_date() {
        return last_date;
    }

    public void setLast_date(Timestamp last_date) {
        this.last_date = last_date;
    }

    public String getLast_id() {
        return last_id;
    }

    public void setLast_id(String last_id) {
        this.last_id = last_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleCategoryDto that = (SaleCategoryDto) o;
        return state == that.state && Objects.equals(sal_cd, that.sal_cd) && Objects.equals(name, that.name) && Objects.equals(first_date, that.first_date) && Objects.equals(first_id, that.first_id) && Objects.equals(last_date, that.last_date) && Objects.equals(last_id, that.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sal_cd, name, state, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "SaleCategoryDto{" +
                "sal_cd='" + sal_cd + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
