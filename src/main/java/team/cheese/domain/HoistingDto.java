package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class HoistingDto {
    private Long no;
    private Long sal_no;
    private String addr_cd;
    private String addr_name;
    private String seller_id;
    private String sal_i_cd;
    private String sal_name;
    private Timestamp h_date;
    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;

    public HoistingDto() {
    }

    public HoistingDto(Long sal_no, String addr_cd, String addr_name, String seller_id, String sal_i_cd, String sal_name) {
        this.sal_no = sal_no;
        this.addr_cd = addr_cd;
        this.addr_name = addr_name;
        this.seller_id = seller_id;
        this.sal_i_cd = sal_i_cd;
        this.sal_name = sal_name;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getSal_no() {
        return sal_no;
    }

    public void setSal_no(Long sal_no) {
        this.sal_no = sal_no;
    }

    public String getAddr_cd() {
        return addr_cd;
    }

    public void setAddr_cd(String addr_cd) {
        this.addr_cd = addr_cd;
    }

    public String getAddr_name() {
        return addr_name;
    }

    public void setAddr_name(String addr_name) {
        this.addr_name = addr_name;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSal_id_cd() {
        return sal_i_cd;
    }

    public void setSal_id_cd(String sal_i_cd) {
        this.sal_i_cd = sal_i_cd;
    }

    public String getSal_name() {
        return sal_name;
    }

    public void setSal_name(String sal_name) {
        this.sal_name = sal_name;
    }

    public Timestamp getH_date() {
        return h_date;
    }

    public void setH_date(Timestamp h_date) {
        this.h_date = h_date;
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
        HoistingDto that = (HoistingDto) o;
        return Objects.equals(no, that.no) && Objects.equals(sal_no, that.sal_no) && Objects.equals(addr_cd, that.addr_cd) && Objects.equals(addr_name, that.addr_name) && Objects.equals(seller_id, that.seller_id) && Objects.equals(sal_i_cd, that.sal_i_cd) && Objects.equals(sal_name, that.sal_name) && Objects.equals(h_date, that.h_date) && Objects.equals(first_date, that.first_date) && Objects.equals(first_id, that.first_id) && Objects.equals(last_date, that.last_date) && Objects.equals(last_id, that.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, sal_no, addr_cd, addr_name, seller_id, sal_i_cd, sal_name, h_date, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "HoistingDto{" +
                "no=" + no +
                ", sal_no=" + sal_no +
                ", addr_cd='" + addr_cd + '\'' +
                ", addr_name='" + addr_name + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", sal_id_cd='" + sal_i_cd + '\'' +
                ", sal_name='" + sal_name + '\'' +
                ", h_date=" + h_date +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
