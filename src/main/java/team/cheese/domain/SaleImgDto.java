package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class SaleImgDto {
    private Long sal_no;
    private Long img_no;

    private Timestamp first_date;

    private String first_id;

    private Timestamp last_date;

    private String last_id;

    public Long getSal_no() {
        return sal_no;
    }

    public void setSal_no(Long sal_no) {
        this.sal_no = sal_no;
    }

    public Long getImg_no() {
        return img_no;
    }

    public void setImg_no(Long img_no) {
        this.img_no = img_no;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleImgDto that = (SaleImgDto) o;
        return Objects.equals(sal_no, that.sal_no) && Objects.equals(img_no, that.img_no) && Objects.equals(first_date, that.first_date) && Objects.equals(first_id, that.first_id) && Objects.equals(last_date, that.last_date) && Objects.equals(last_id, that.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sal_no, img_no, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "SaleImgDto{" +
                "sal_no=" + sal_no +
                ", img_no=" + img_no +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
