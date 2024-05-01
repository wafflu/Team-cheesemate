package team.cheese.domain;

import java.util.Objects;

public class SaleTagDto {
    private String sal_no; // 판매글 번호
    private String tag_no; // 태그 번호
    private char state; // 상태
    private TagDto tagDto; // tag 테이블 정보

    public String getSal_no() {
        return sal_no;
    }

    public void setSal_no(String sal_no) {
        this.sal_no = sal_no;
    }

    public String getTag_no() {
        return tag_no;
    }

    public void setTag_no(String tag_no) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleTagDto that = (SaleTagDto) o;
        return state == that.state && Objects.equals(sal_no, that.sal_no) && Objects.equals(tag_no, that.tag_no) && Objects.equals(tagDto, that.tagDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sal_no, tag_no, state, tagDto);
    }

    @Override
    public String toString() {
        return "SaleTagDto{" +
                "sal_no='" + sal_no + '\'' +
                ", tag_no='" + tag_no + '\'' +
                ", state=" + state +
                ", tagDto=" + tagDto +
                '}';
    }
}
