package team.cheese.domain;

public class SearchDto {
    private String cd;
    private String contents;

    public SearchDto() {}
    public SearchDto(String cd, String contents) {
        this.cd = cd;
        this.contents = contents;
    }


    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "searchDto{" +
                "cd='" + cd + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
