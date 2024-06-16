package team.cheese.domain.MyPage;

import java.sql.Timestamp;
import java.util.Objects;

public class JjimDTO {
    private Long sal_no;
    private String buyer_id;
    private int check_like;
    private Timestamp r_date;

    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;

    public JjimDTO() {}

    public JjimDTO(Long sal_no,String buyer_id,int check_like) {
        this.sal_no =sal_no;
        this.buyer_id = buyer_id;
        this.check_like = check_like;
    }


    @Override
    public String toString() {
        return "JjimDTO{" +
                "sal_no=" + sal_no +
                ", buyer_id='" + buyer_id + '\'' +
                ", check=" + check_like +
                ", r_date=" + r_date +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JjimDTO jjimDTO = (JjimDTO) o;
        return check_like == jjimDTO.check_like && Objects.equals(sal_no, jjimDTO.sal_no) && Objects.equals(buyer_id, jjimDTO.buyer_id) && Objects.equals(r_date, jjimDTO.r_date) && Objects.equals(first_date, jjimDTO.first_date) && Objects.equals(first_id, jjimDTO.first_id) && Objects.equals(last_date, jjimDTO.last_date) && Objects.equals(last_id, jjimDTO.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sal_no, buyer_id, check_like, r_date, first_date, first_id, last_date, last_id);
    }

    public Long getSal_no() {
        return sal_no;
    }

    public void setSal_no(Long sal_no) {
        this.sal_no = sal_no;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getCheck_like() {
        return check_like;
    }

    public void setCheck_like(int check_like) {
        this.check_like = check_like;
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
}
