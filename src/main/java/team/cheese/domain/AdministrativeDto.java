package team.cheese.domain;

import java.util.Objects;

public class AdministrativeDto {
    private String addr_cd;
    private String addr_name;
    private char state;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministrativeDto that = (AdministrativeDto) o;
        return state == that.state && Objects.equals(addr_cd, that.addr_cd) && Objects.equals(addr_name, that.addr_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addr_cd, addr_name, state);
    }

    @Override
    public String toString() {
        return "AdministrativeDto{" +
                "addr_cd='" + addr_cd + '\'' +
                ", addr_name='" + addr_name + '\'' +
                ", state=" + state +
                '}';
    }
}
