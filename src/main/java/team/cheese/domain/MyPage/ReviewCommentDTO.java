package team.cheese.domain.MyPage;

import java.sql.Timestamp;
import java.util.Objects;

public class ReviewCommentDTO {
    private Integer no;
    private String sal_id;
    private Long sal_no;
    private String psn;
    private String buy_id;
    private String buy_nick;
    private String contents;
    private int reviewStar;
    private Timestamp r_date;
    private Timestamp m_date;

    private char ur_state;
    private char ad_state;
    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;
    public ReviewCommentDTO() {}

    public ReviewCommentDTO(String sal_id, String buy_id, String buy_nick,String contents,int reviewStar) {
        this.sal_id = sal_id;
        this.buy_id = buy_id;
        this.buy_nick = buy_nick;
        this.contents = contents;
        this.reviewStar = reviewStar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewCommentDTO that = (ReviewCommentDTO) o;
        return reviewStar == that.reviewStar && ur_state == that.ur_state && ad_state == that.ad_state && Objects.equals(no, that.no) && Objects.equals(sal_id, that.sal_id) && Objects.equals(buy_id, that.buy_id) && Objects.equals(buy_nick, that.buy_nick) && Objects.equals(contents, that.contents) && Objects.equals(r_date, that.r_date) && Objects.equals(m_date, that.m_date) && Objects.equals(first_date, that.first_date) && Objects.equals(first_id, that.first_id) && Objects.equals(last_date, that.last_date) && Objects.equals(last_id, that.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, sal_id, buy_id, buy_nick, contents, reviewStar, r_date, m_date, ur_state, ad_state, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "ReviewCommentDTO{" +
                "no=" + no +
                ", sal_id='" + sal_id + '\'' +
                ", buy_id='" + buy_id + '\'' +
                ", buy_nick='" + buy_nick + '\'' +
                ", contents='" + contents + '\'' +
                ", reviewStar=" + reviewStar +
                ", r_date=" + r_date +
                ", m_date=" + m_date +
                ", ur_state=" + ur_state +
                ", ad_state=" + ad_state +
                ", first_r_date=" + first_date +
                ", first_idno='" + first_id + '\'' +
                ", last_r_date=" + last_date +
                ", last_idno='" + last_id + '\'' +
                '}';
    }

    public Long getSal_no() {
        return sal_no;
    }

    public void setSal_no(Long sal_no) {
        this.sal_no = sal_no;
    }

    public String getPsn() {
        return psn;
    }

    public void setPsn(String psn) {
        this.psn = psn;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
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

    public int getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(int reviewStar) {
        this.reviewStar = reviewStar;
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
}
