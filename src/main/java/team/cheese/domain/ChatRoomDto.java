package team.cheese.domain;

public class ChatRoomDto {
    private Long no;
    private Long sale_no;
    private String seller_id;
    private String seller_nk;
    private String seller_state;
    private String buyer_id;
    private String buyer_nk;
    private String buyer_state;
    private String r_date;
    private String first_date;
    private String first_id;
    private String last_date;
    private String last_id;

    @Override
    public String toString() {
        return "ChatRoomDto{" +
                "no=" + no +
                ", sale_no=" + sale_no +
                ", seller_id='" + seller_id + '\'' +
                ", seller_nk='" + seller_nk + '\'' +
                ", seller_state='" + seller_state + '\'' +
                ", buyer_id='" + buyer_id + '\'' +
                ", buyer_nk='" + buyer_nk + '\'' +
                ", buyer_state='" + buyer_state + '\'' +
                ", r_date='" + r_date + '\'' +
                ", first_date='" + first_date + '\'' +
                ", first_id='" + first_id + '\'' +
                ", last_date='" + last_date + '\'' +
                ", last_id='" + last_id + '\'' +
                '}';
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getSale_no() {
        return sale_no;
    }

    public void setSale_no(Long sale_no) {
        this.sale_no = sale_no;
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

    public String getSeller_state() {
        return seller_state;
    }

    public void setSeller_state(String seller_state) {
        this.seller_state = seller_state;
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

    public String getBuyer_state() {
        return buyer_state;
    }

    public void setBuyer_state(String buyer_state) {
        this.buyer_state = buyer_state;
    }

    public String getR_date() {
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

    public String getFirst_date() {
        return first_date;
    }

    public void setFirst_date(String first_date) {
        this.first_date = first_date;
    }

    public String getFirst_id() {
        return first_id;
    }

    public void setFirst_id(String first_id) {
        this.first_id = first_id;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public String getLast_id() {
        return last_id;
    }

    public void setLast_id(String last_id) {
        this.last_id = last_id;
    }
}
