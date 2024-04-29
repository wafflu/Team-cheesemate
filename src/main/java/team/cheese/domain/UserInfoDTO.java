package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class UserInfoDTO {
    private String ur_id;
    private String nick;
    private long img_no;
    private String contents;
    private int view_cnt;
    private int complete_cnt;
    private int rv_cmt_cnt;
    private int like_cnt;
    private int hate_cnt;
    private int rpt_cnt;
    private Timestamp r_date;
    private Timestamp m_date;

    private String filert;

    public Timestamp getM_date() {
        return m_date;
    }

    public void setM_date(Timestamp m_date) {
        this.m_date = m_date;
    }

    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;

    public UserInfoDTO() {}
    public UserInfoDTO(String ur_id, String contents) {
        this.ur_id = ur_id;
        this.nick = nick;
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoDTO that = (UserInfoDTO) o;
        return img_no == that.img_no && view_cnt == that.view_cnt && complete_cnt == that.complete_cnt && rv_cmt_cnt == that.rv_cmt_cnt && like_cnt == that.like_cnt && hate_cnt == that.hate_cnt && rpt_cnt == that.rpt_cnt && Objects.equals(ur_id, that.ur_id) && Objects.equals(nick, that.nick) && Objects.equals(contents, that.contents) && Objects.equals(r_date, that.r_date) && Objects.equals(first_date, that.first_date) && Objects.equals(first_id, that.first_id) && Objects.equals(last_date, that.last_date) && Objects.equals(last_id, that.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ur_id, nick, img_no, contents, view_cnt, complete_cnt, rv_cmt_cnt, like_cnt, hate_cnt, rpt_cnt, r_date, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "ur_id='" + ur_id + '\'' +
                ", nick='" + nick + '\'' +
                ", img_no=" + img_no +
                ", contents='" + contents + '\'' +
                ", view_cnt=" + view_cnt +
                ", complete_cnt=" + complete_cnt +
                ", rv_cmt_cnt=" + rv_cmt_cnt +
                ", like_cnt=" + like_cnt +
                ", hate_cnt=" + hate_cnt +
                ", rpt_cnt=" + rpt_cnt +
                ", r_date=" + r_date +
                ", first_r_date=" + first_date +
                ", first_idno='" + first_id + '\'' +
                ", last_r_date=" + last_date +
                ", last_idno='" + last_id + '\'' +
                '}';
    }

    public String getUr_id() {
        return ur_id;
    }

    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public long getImg_no() {
        return img_no;
    }

    public void setImg_no(long img_no) {
        this.img_no = img_no;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getComplete_cnt() {
        return complete_cnt;
    }

    public void setComplete_cnt(int complete_cnt) {
        this.complete_cnt = complete_cnt;
    }

    public int getRv_cmt_cnt() {
        return rv_cmt_cnt;
    }

    public void setRv_cmt_cnt(int rv_cmt_cnt) {
        this.rv_cmt_cnt = rv_cmt_cnt;
    }

    public int getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(int like_cnt) {
        this.like_cnt = like_cnt;
    }

    public int getHate_cnt() {
        return hate_cnt;
    }

    public void setHate_cnt(int hate_cnt) {
        this.hate_cnt = hate_cnt;
    }

    public int getRpt_cnt() {
        return rpt_cnt;
    }

    public void setRpt_cnt(int rpt_cnt) {
        this.rpt_cnt = rpt_cnt;
    }

    public Timestamp getR_date() {
        return r_date;
    }

    public void setR_date(Timestamp r_date) {
        this.r_date = r_date;
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

    public String getFilert() {
        return filert;
    }

    public void setFilert(String filert) {
        this.filert = filert;
    }
}