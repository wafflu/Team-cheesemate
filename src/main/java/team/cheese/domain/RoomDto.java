package team.cheese.domain;

import java.util.Date;

public class RoomDto {
    Long cr_no;
    Long seller_no;
    String seller_id;
    String seller_nk;
    String buyer_id;
    String buyer_nk;
    String i_cd;
    String seller_state;
    String buyer_state;
    String state;
    String first_id;
    Date last_date;
    String last_id;

    public RoomDto() {
    }

    public Long getCr_no() {
        return cr_no;
    }

    public void setCr_no(Long cr_no) {
        this.cr_no = cr_no;
    }

    public Long getSeller_no() {
        return seller_no;
    }

    public void setSeller_no(Long seller_no) {
        this.seller_no = seller_no;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_nk() {
        return seller_nk;
    }

    public void setSeller_nk(String seller_nk) {
        this.seller_nk = seller_nk;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_nk() {
        return buyer_nk;
    }

    public void setBuyer_nk(String buyer_nk) {
        this.buyer_nk = buyer_nk;
    }

    public String getI_cd() {
        return i_cd;
    }

    public void setI_cd(String i_cd) {
        this.i_cd = i_cd;
    }

    public String getSeller_state() {
        return seller_state;
    }

    public void setSeller_state(String seller_state) {
        this.seller_state = seller_state;
    }

    public String getBuyer_state() {
        return buyer_state;
    }

    public void setBuyer_state(String buyer_state) {
        this.buyer_state = buyer_state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFirst_id() {
        return first_id;
    }

    public void setFirst_id(String first_id) {
        this.first_id = first_id;
    }

    public Date getLast_date() {
        return last_date;
    }

    public void setLast_date(Date last_date) {
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
        return "RoomDto{" +
                "cr_no=" + cr_no +
                ", seller_no=" + seller_no +
                ", seller_id='" + seller_id + '\'' +
                ", seller_nk='" + seller_nk + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", buyer_nk='" + buyer_nk + '\'' +
                ", i_cd='" + i_cd + '\'' +
                ", seller_state='" + seller_state + '\'' +
                ", buyer_state='" + buyer_state + '\'' +
                ", state='" + state + '\'' +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
