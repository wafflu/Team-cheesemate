package team.cheese.domain;

import java.util.Objects;

public class TagDto {
    private Integer no; // 태그 번호
    private String contents; // 태그 내용
    private char state; // 상태

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
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
        TagDto tagDto = (TagDto) o;
        return state == tagDto.state && Objects.equals(no, tagDto.no) && Objects.equals(contents, tagDto.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, contents, state);
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "no=" + no +
                ", contents='" + contents + '\'' +
                ", state=" + state +
                '}';
    }
}
