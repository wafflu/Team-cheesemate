package team.cheese.Domain.CommunityHeart;

public class CommunityHeartDto {
    private Integer like_no;
    private String ur_id;
    private Integer post_no;
    private char ur_state;
    private int countLike;

    public CommunityHeartDto() {}
    public CommunityHeartDto(String ur_id, Integer post_no) {
        this.ur_id = ur_id;
        this.post_no = post_no;
    }
    public CommunityHeartDto(String ur_id, Integer post_no, char ur_state, int countLike) {
        this.ur_id = ur_id;
        this.post_no = post_no;
        this.ur_state = ur_state;
        this.countLike = countLike;
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

    @Override
    public String toString() {
        return "CommunityHeartDto{" +
                "like_no=" + like_no +
                ", ur_id='" + ur_id + '\'' +
                ", post_no=" + post_no +
                ", ur_state=" + ur_state +
                ", countLike=" + countLike +
                '}';
    }
}
