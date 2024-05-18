package team.cheese.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

public class QnaDto {
    private Long no;

    @NotNull(message = "사용자 ID는 필수 입력 항목입니다.")
    private String ur_id;

    @NotNull(message = "상세 유형은 필수 선택 항목입니다.")
    private Long que_i_cd;

    @NotNull(message = "상태 코드는 필수 입력 항목입니다.")
    private String q_s_cd;

    @NotNull(message = "제목은 필수 입력 항목입니다.")
    private String title;

    @NotNull(message = "내용은 필수 입력 항목입니다.")
    private String contents;

    private Timestamp r_date;
    private Timestamp m_date;
    private String ad_id;
    private String ad_name;
    private String ans_contents;
    private Timestamp ans_p_date;
    private Timestamp ans_m_date;
    private Integer ans_sat;
    private Character state;
    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;

    private String categoryName; // 문의 유형 이름

    public QnaDto() {}
    public QnaDto(Long no, String ur_id, Long que_i_cd, String q_s_cd, String title, String contents, Timestamp r_date, Timestamp m_date, String ad_id, String ad_name, String ans_contents, Timestamp ans_p_date, Timestamp ans_m_date, Integer ans_sat, Character state, Timestamp first_date, String first_id, Timestamp last_date, String last_id) {
        this.no = no;
        this.ur_id = ur_id;
        this.que_i_cd = que_i_cd;
        this.q_s_cd = q_s_cd;
        this.title = title;
        this.contents = contents;
        this.r_date = r_date;
        this.m_date = m_date;
        this.ad_id = ad_id;
        this.ad_name = ad_name;
        this.ans_contents = ans_contents;
        this.ans_p_date = ans_p_date;
        this.ans_m_date = ans_m_date;
        this.ans_sat = ans_sat;
        this.state = state;
        this.first_date = first_date;
        this.first_id = first_id;
        this.last_date = last_date;
        this.last_id = last_id;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getUr_id() {
        return ur_id;
    }

    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
    }

    public Long getQue_i_cd() {
        return que_i_cd;
    }

    public void setQue_i_cd(Long que_i_cd) {
        this.que_i_cd = que_i_cd;
    }

    public String getQ_s_cd() {
        return q_s_cd;
    }

    public void setQ_s_cd(String q_s_cd) {
        this.q_s_cd = q_s_cd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAns_contents() {
        return ans_contents;
    }

    public void setAns_contents(String ans_contents) {
        this.ans_contents = ans_contents;
    }

    public Timestamp getAns_p_date() {
        return ans_p_date;
    }

    public void setAns_p_date(Timestamp ans_p_date) {
        this.ans_p_date = ans_p_date;
    }

    public Timestamp getAns_m_date() {
        return ans_m_date;
    }

    public void setAns_m_date(Timestamp ans_m_date) {
        this.ans_m_date = ans_m_date;
    }

    public Integer getAns_sat() {
        return ans_sat;
    }

    public void setAns_sat(Integer ans_sat) {
        this.ans_sat = ans_sat;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QnaDto qnaDto = (QnaDto) o;
        return Objects.equals(no, qnaDto.no) && Objects.equals(ur_id, qnaDto.ur_id) && Objects.equals(que_i_cd, qnaDto.que_i_cd) && Objects.equals(q_s_cd, qnaDto.q_s_cd) && Objects.equals(title, qnaDto.title) && Objects.equals(contents, qnaDto.contents) && Objects.equals(r_date, qnaDto.r_date) && Objects.equals(m_date, qnaDto.m_date) && Objects.equals(ad_id, qnaDto.ad_id) && Objects.equals(ad_name, qnaDto.ad_name) && Objects.equals(ans_contents, qnaDto.ans_contents) && Objects.equals(ans_p_date, qnaDto.ans_p_date) && Objects.equals(ans_m_date, qnaDto.ans_m_date) && Objects.equals(ans_sat, qnaDto.ans_sat) && Objects.equals(state, qnaDto.state) && Objects.equals(first_date, qnaDto.first_date) && Objects.equals(first_id, qnaDto.first_id) && Objects.equals(last_date, qnaDto.last_date) && Objects.equals(last_id, qnaDto.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, ur_id, que_i_cd, q_s_cd, title, contents, r_date, m_date, ad_id, ad_name, ans_contents, ans_p_date, ans_m_date, ans_sat, state, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "QnaDto{" +
                "no=" + no +
                ", ur_id='" + ur_id + '\'' +
                ", que_i_cd=" + que_i_cd +
                ", q_s_cd='" + q_s_cd + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", r_date=" + r_date +
                ", m_date=" + m_date +
                ", ad_id='" + ad_id + '\'' +
                ", ad_name='" + ad_name + '\'' +
                ", ans_contents='" + ans_contents + '\'' +
                ", ans_p_date=" + ans_p_date +
                ", ans_m_date=" + ans_m_date +
                ", ans_sat=" + ans_sat +
                ", state=" + state +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
