package team.cheese.domain.Comment;

import java.sql.Timestamp;
import java.util.Objects;

public class CommentDto {
    private Integer post_no;
    private Integer no;
    private Integer psn;
    private String nick;
    private String ur_id;
    private String contents;
    private Timestamp r_date;
    private Timestamp m_date;
    private char ur_state;
    private char ad_state;
    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;



    public CommentDto() {};

    public CommentDto(Integer post_no, Integer no, Integer psn, String nick, String ur_id, String contents, Timestamp r_date, Timestamp m_date) {
        this.post_no = post_no;
        this.no = no;
        this.psn = psn;
        this.nick = nick;
        this.ur_id = ur_id;
        this.contents = contents;
        this.r_date = r_date;
        this.m_date = m_date;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(post_no, that.post_no) && Objects.equals(no, that.no) && Objects.equals(psn, that.psn) && Objects.equals(nick, that.nick) && Objects.equals(ur_id, that.ur_id) && Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post_no, no, psn, nick, ur_id, contents);
    }

    public Integer getPost_no() {
        return post_no;
    }

    public void setPost_no(Integer post_no) {
        this.post_no = post_no;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Integer getPsn() {
        return psn;
    }

    public void setPsn(Integer psn) {
        this.psn = psn;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUr_id() {
        return ur_id;
    }

    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Timestamp getR_date() {
        return r_date;
    }

    public void setR_date(Timestamp r_date) {
        this.r_date = r_date;
    }

    public Timestamp getM_date() {
        return m_date;
    }

    public void setM_date(Timestamp m_date) {
        this.m_date = m_date;
    }



    public char getUr_state() {
        return ur_state;
    }

    public void setUr_state(char ur_state) {
        this.ur_state = ur_state;
    }

    public char getAd_state() {
        return ad_state;
    }

    public void setAd_state(char ad_state) {
        this.ad_state = ad_state;
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
    public String toString() {
        return "CommentDto{" +
                "post_no=" + post_no +
                ", no=" + no +
                ", psn=" + psn +
                ", nick='" + nick + '\'' +
                ", ur_id='" + ur_id + '\'' +
                ", contents='" + contents + '\'' +
                ", r_date=" + r_date +
                ", m_date=" + m_date +
                ", ur_state=" + ur_state +
                ", ad_state=" + ad_state +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}