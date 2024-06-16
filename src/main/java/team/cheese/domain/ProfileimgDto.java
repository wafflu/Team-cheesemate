package team.cheese.domain;

public class ProfileimgDto {
    private Long id;
    private String userid;

    private String usernick;

    private String img_full_rt;

    public ProfileimgDto(){}

    public ProfileimgDto(Long id, String userid, String usernick, String img_full_rt){
        this.id = id;
        this.userid = userid;
        this.usernick = usernick;
        this.img_full_rt = img_full_rt;
    }

    @Override
    public String toString() {
        return "ProfileimgDto{" +
                "userid='" + userid + '\'' +
                ", id=" + id +
                ", usernick='" + usernick + '\'' +
                ", img_full_rt='" + img_full_rt + '\'' +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernick() {
        return usernick;
    }

    public void setUsernick(String usernick) {
        this.usernick = usernick;
    }

    public String getImg_full_rt() {
        return img_full_rt;
    }

    public void setImg_full_rt(String img_full_rt) {
        this.img_full_rt = img_full_rt;
    }
}
