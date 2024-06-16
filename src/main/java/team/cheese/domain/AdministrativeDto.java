package team.cheese.domain;

import java.util.Objects;
import java.sql.Timestamp;

public class AdministrativeDto {
    private String addr_cd;
    private String addr_name;
    private char state;
    private Timestamp first_date;

    private String first_id;

    private Timestamp last_date;

    private String last_id;

    public String getAddr_cd() {
        return addr_cd;
    }

    public void setAddr_cd(String addr_cd) {
        this.addr_cd = addr_cd;
    }

    public String getAddr_name() {
        return addr_name;
    }

    public void setAddr_name(String addr_name) {
        this.addr_name = addr_name;
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
        AdministrativeDto that = (AdministrativeDto) o;
        return state == that.state && Objects.equals(addr_cd, that.addr_cd) && Objects.equals(addr_name, that.addr_name) && Objects.equals(first_date, that.first_date) && Objects.equals(first_id, that.first_id) && Objects.equals(last_date, that.last_date) && Objects.equals(last_id, that.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addr_cd, addr_name, state, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "AdministrativeDto{" +
                "addr_cd='" + addr_cd + '\'' +
                ", addr_name='" + addr_name + '\'' +
                ", state=" + state +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
