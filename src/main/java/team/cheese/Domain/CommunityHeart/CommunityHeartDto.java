package team.cheese.Domain.CommunityHeart;

public class CommunityHeartDto {
    private Integer like_no;
    private String ur_id;
    private Integer post_no;

    public CommunityHeartDto() {}
    public CommunityHeartDto(Integer like_no, String ur_id, Integer post_no) {
        this.like_no = like_no;
        this.ur_id = ur_id;
        this.post_no = post_no;
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

    @Override
    public String toString() {
        return "CommunityHeartDto{" +
                "like_no=" + like_no +
                ", ur_id='" + ur_id + '\'' +
                ", post_no=" + post_no +
                '}';
    }
}
