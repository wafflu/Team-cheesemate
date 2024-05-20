package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class QnaCategoryDto {
    private long que_cd; // 변경된 부분
    private String name;
    private char st;
    private Timestamp firstDate;
    private String firstId;
    private Timestamp lastDate;
    private String lastId;

    public QnaCategoryDto() {}

    public QnaCategoryDto(char st, String name, long que_cd) { // 변경된 부분
        this.st = st;
        this.name = name;
        this.que_cd = que_cd; // 변경된 부분
    }

    public long getQue_cd() { // 변경된 부분
        return que_cd;
    }

    public void setQue_cd(long que_cd) { // 변경된 부분
        this.que_cd = que_cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSt() {
        return st;
    }

    public void setSt(char st) {
        this.st = st;
    }

    public Timestamp getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Timestamp firstDate) {
        this.firstDate = firstDate;
    }

    public String getFirstId() {
        return firstId;
    }

    public void setFirstId(String firstId) {
        this.firstId = firstId;
    }

    public Timestamp getLastDate() {
        return lastDate;
    }

    public void setLastDate(Timestamp lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QnaCategoryDto that = (QnaCategoryDto) o;
        return que_cd == that.que_cd && st == that.st && Objects.equals(name, that.name) && Objects.equals(firstId, that.firstId) && Objects.equals(lastId, that.lastId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(que_cd, name, st, firstId, lastId);
    }

    @Override
    public String toString() {
        return "QnaCategoryDto{" +
                "que_cd=" + que_cd +
                ", name='" + name + '\'' +
                ", st=" + st +
                ", firstId='" + firstId + '\'' +
                ", lastId='" + lastId + '\'' +
                '}';
    }
}
