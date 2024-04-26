package team.cheese.domain;

import java.util.Objects;

public class SaleCategoryDto {
    private String sal_cd;
    private String name;
    private char state;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleCategoryDto that = (SaleCategoryDto) o;
        return state == that.state && Objects.equals(sal_cd, that.sal_cd) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sal_cd, name, state);
    }

    @Override
    public String toString() {
        return "SaleCategoryDto{" +
                "sal_cd='" + sal_cd + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
