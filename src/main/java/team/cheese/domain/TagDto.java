package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class TagDto {
    private Long no; // 태그 번호
    private String t_contents; // 태그 내용
    private char state; // 상태

    private Timestamp first_date;

    private String first_id;

    private Timestamp last_date;

    private String last_id;

    public TagDto() {
    }

    public TagDto(String t_contents) {
        this.t_contents = t_contents;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getContents() {
        return t_contents;
    }

    public void setContents(String t_contents) {
        this.t_contents = t_contents;
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
        TagDto tagDto = (TagDto) o;
        return state == tagDto.state && Objects.equals(no, tagDto.no) && Objects.equals(t_contents, tagDto.t_contents) && Objects.equals(first_date, tagDto.first_date) && Objects.equals(first_id, tagDto.first_id) && Objects.equals(last_date, tagDto.last_date) && Objects.equals(last_id, tagDto.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, t_contents, state, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "no=" + no +
                ", t_contents='" + t_contents + '\'' +
                ", state=" + state +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
