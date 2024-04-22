package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class ReviewCommentDTO {
    private Long sn;
    private String sal_id;
    private String buy_id;
    private String buy_nick;
    private String contents;
    private int like_cnt;
    private Timestamp r_date;
    private Timestamp m_date;

    private char ur_state;
    private char ad_state;
    private Timestamp first_r_date;
    private String first_idno;
    private Timestamp last_r_date;
    private String last_idno;
    public ReviewCommentDTO() {}

    public ReviewCommentDTO(String sal_id, String buy_id, String buy_nick, String contents,String first_idno,String last_idno) {
        this.sal_id = sal_id;
        this.buy_id = buy_id;
        this.buy_nick = buy_nick;
        this.contents = contents;
        this.first_idno = first_idno;
        this.last_idno = last_idno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewCommentDTO that = (ReviewCommentDTO) o;
        return like_cnt == that.like_cnt && ur_state == that.ur_state && ad_state == that.ad_state && Objects.equals(sn, that.sn) && Objects.equals(sal_id, that.sal_id) && Objects.equals(buy_id, that.buy_id) && Objects.equals(buy_nick, that.buy_nick) && Objects.equals(contents, that.contents) && Objects.equals(r_date, that.r_date) && Objects.equals(m_date, that.m_date) && Objects.equals(first_r_date, that.first_r_date) && Objects.equals(first_idno, that.first_idno) && Objects.equals(last_r_date, that.last_r_date) && Objects.equals(last_idno, that.last_idno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sn, sal_id, buy_id, buy_nick, contents, like_cnt, r_date, m_date, ur_state, ad_state, first_r_date, first_idno, last_r_date, last_idno);
    }

    @Override
    public String toString() {
        return "ReviewCommentDTO{" +
                "sn=" + sn +
                ", sal_id='" + sal_id + '\'' +
                ", buy_id='" + buy_id + '\'' +
                ", buy_nick='" + buy_nick + '\'' +
                ", contents='" + contents + '\'' +
                ", like_cnt=" + like_cnt +
                ", r_date=" + r_date +
                ", m_date=" + m_date +
                ", ur_state=" + ur_state +
                ", ad_state=" + ad_state +
                ", first_r_date=" + first_r_date +
                ", first_idno='" + first_idno + '\'' +
                ", last_r_date=" + last_r_date +
                ", last_idno='" + last_idno + '\'' +
                '}';
    }

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public String getSal_id() {
        return sal_id;
    }

    public void setSal_id(String sal_id) {
        this.sal_id = sal_id;
    }

    public String getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(String buy_id) {
        this.buy_id = buy_id;
    }

    public String getBuy_nick() {
        return buy_nick;
    }

    public void setBuy_nick(String buy_nick) {
        this.buy_nick = buy_nick;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(int like_cnt) {
        this.like_cnt = like_cnt;
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

    public Timestamp getFirst_r_date() {
        return first_r_date;
    }

    public void setFirst_r_date(Timestamp first_r_date) {
        this.first_r_date = first_r_date;
    }

    public String getFirst_idno() {
        return first_idno;
    }

    public void setFirst_idno(String first_idno) {
        this.first_idno = first_idno;
    }

    public Timestamp getLast_r_date() {
        return last_r_date;
    }

    public void setLast_r_date(Timestamp last_r_date) {
        this.last_r_date = last_r_date;
    }

    public String getLast_idno() {
        return last_idno;
    }

    public void setLast_idno(String last_idno) {
        this.last_idno = last_idno;
    }
}
