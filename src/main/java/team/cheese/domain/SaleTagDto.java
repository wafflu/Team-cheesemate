package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class SaleTagDto {
    private Long sal_no; // 판매글 번호
    private Long tag_no; // 태그 번호
    private char state; // 상태
    private TagDto tagDto; // tag 테이블 정보

    private Timestamp first_date;

    private String first_id;

    private Timestamp last_date;

    private String last_id;

    public SaleTagDto() {
    }

    public SaleTagDto(Long sal_no, Long tag_no, String first_id, String last_id) {
        this.sal_no = sal_no;
        this.tag_no = tag_no;
        this.first_id = first_id;
        this.last_id = last_id;
    }

    public Long getSal_no() {
        return sal_no;
    }

    public void setSal_no(Long sal_no) {
        this.sal_no = sal_no;
    }

    public Long getTag_no() {
        return tag_no;
    }

    public void setTag_no(Long tag_no) {
        this.tag_no = tag_no;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public TagDto getTagDto() {
        return tagDto;
    }

    public void setTagDto(TagDto tagDto) {
        this.tagDto = tagDto;
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
        SaleTagDto that = (SaleTagDto) o;
        return state == that.state && Objects.equals(sal_no, that.sal_no) && Objects.equals(tag_no, that.tag_no) && Objects.equals(tagDto, that.tagDto) && Objects.equals(first_date, that.first_date) && Objects.equals(first_id, that.first_id) && Objects.equals(last_date, that.last_date) && Objects.equals(last_id, that.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sal_no, tag_no, state, tagDto, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "SaleTagDto{" +
                "sal_no=" + sal_no +
                ", tag_no=" + tag_no +
                ", state=" + state +
                ", tagDto=" + tagDto +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
