package team.cheese.domain.CommunityHeart;

import java.sql.Timestamp;

public class CommunityHeartDto {
    private Integer like_no;
    private String ur_id;
    private Integer post_no;
    private char ur_state;
    private int countLike;

    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;

    public CommunityHeartDto() {}
    public CommunityHeartDto(String ur_id, Integer post_no,Timestamp first_date, String first_id, Timestamp last_date, String last_id) {
        this.ur_id = ur_id;
        this.post_no = post_no;
        this.first_date = first_date;
        this.first_id = first_id;
        this.last_date = last_date;
        this.last_id = last_id;

    }
    public CommunityHeartDto(String ur_id, Integer post_no, char ur_state, int countLike, Timestamp first_date, String first_id, Timestamp last_date, String last_id) {
        this.ur_id = ur_id;
        this.post_no = post_no;
        this.ur_state = ur_state;
        this.countLike = countLike;
        this.first_date = first_date;
        this.first_id = first_id;
        this.last_date = last_date;
        this.last_id = last_id;
    }
    public Integer getLike_no() {
        return like_no;
    }

    public void setLike_no(Integer like_no) {
        this.like_no = like_no;
    }

    public String getUr_id() {
        return ur_id;
    }

    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
    }

    public Integer getPost_no() {
        return post_no;
    }

    public void setPost_no(Integer post_no) {
        this.post_no = post_no;
    }

    public char getUr_state() {
        return ur_state;
    }

    public void setUr_state(char ur_state) {
        this.ur_state = ur_state;
    }

    //countLike추가


    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }


    //시스템컬럼추가

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
    public String toString() {
        return "CommunityHeartDto{" +
                "like_no=" + like_no +
                ", ur_id='" + ur_id + '\'' +
                ", post_no=" + post_no +
                ", ur_state=" + ur_state +
                ", countLike=" + countLike +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
