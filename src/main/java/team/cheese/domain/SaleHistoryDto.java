package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class SaleHistoryDto {
    private Long no;
    private Long sal_no;
    private String addr_cd;
    private String addr_name;
    private String seller_id;
    private String sale_i_cd;
    private String tx_s_cd;
    private String trade_s_cd_1;
    private String trade_s_cd_2;
    private String stat_s_cd;
    private int price;
    private String pickup_addr_cd;
    private String pickup_addr_name;
    private String detail_addr;
    private String buy_id;
    private String buy_nick;
    private Timestamp r_date;
    private Timestamp m_date;
    private char ur_state;
    private char ad_state;

    private Timestamp first_date;

    private String first_id;

    private Timestamp last_date;

    private String last_id;


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

    public String getSale_i_cd() {
        return sale_i_cd;
    }

    public void setSale_i_cd(String sale_i_cd) {
        this.sale_i_cd = sale_i_cd;
    }

    public String getTx_s_cd() {
        return tx_s_cd;
    }

    public void setTx_s_cd(String tx_s_cd) {
        this.tx_s_cd = tx_s_cd;
    }

    public String getTrade_s_cd_1() {
        return trade_s_cd_1;
    }

    public void setTrade_s_cd_1(String trade_s_cd_1) {
        this.trade_s_cd_1 = trade_s_cd_1;
    }

    public String getTrade_s_cd_2() {
        return trade_s_cd_2;
    }

    public void setTrade_s_cd_2(String trade_s_cd_2) {
        this.trade_s_cd_2 = trade_s_cd_2;
    }

    public String getStat_s_cd() {
        return stat_s_cd;
    }

    public void setStat_s_cd(String stat_s_cd) {
        this.stat_s_cd = stat_s_cd;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPickup_addr_cd() {
        return pickup_addr_cd;
    }

    public void setPickup_addr_cd(String pickup_addr_cd) {
        this.pickup_addr_cd = pickup_addr_cd;
    }

    public String getPickup_addr_name() {
        return pickup_addr_name;
    }

    public void setPickup_addr_name(String pickup_addr_name) {
        this.pickup_addr_name = pickup_addr_name;
    }

    public String getDetail_addr() {
        return detail_addr;
    }

    public void setDetail_addr(String detail_addr) {
        this.detail_addr = detail_addr;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleHistoryDto that = (SaleHistoryDto) o;
        return price == that.price && ur_state == that.ur_state && ad_state == that.ad_state && Objects.equals(no, that.no) && Objects.equals(sal_no, that.sal_no) && Objects.equals(addr_cd, that.addr_cd) && Objects.equals(addr_name, that.addr_name) && Objects.equals(seller_id, that.seller_id) && Objects.equals(sale_i_cd, that.sale_i_cd) && Objects.equals(tx_s_cd, that.tx_s_cd) && Objects.equals(trade_s_cd_1, that.trade_s_cd_1) && Objects.equals(trade_s_cd_2, that.trade_s_cd_2) && Objects.equals(stat_s_cd, that.stat_s_cd) && Objects.equals(pickup_addr_cd, that.pickup_addr_cd) && Objects.equals(pickup_addr_name, that.pickup_addr_name) && Objects.equals(detail_addr, that.detail_addr) && Objects.equals(buy_id, that.buy_id) && Objects.equals(buy_nick, that.buy_nick) && Objects.equals(r_date, that.r_date) && Objects.equals(m_date, that.m_date) && Objects.equals(first_date, that.first_date) && Objects.equals(first_id, that.first_id) && Objects.equals(last_date, that.last_date) && Objects.equals(last_id, that.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, sal_no, addr_cd, addr_name, seller_id, sale_i_cd, tx_s_cd, trade_s_cd_1, trade_s_cd_2, stat_s_cd, price, pickup_addr_cd, pickup_addr_name, detail_addr, buy_id, buy_nick, r_date, m_date, ur_state, ad_state, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "SaleHistoryDto{" +
                "no=" + no +
                ", sal_no=" + sal_no +
                ", addr_cd='" + addr_cd + '\'' +
                ", addr_name='" + addr_name + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", sale_i_cd='" + sale_i_cd + '\'' +
                ", tx_s_cd='" + tx_s_cd + '\'' +
                ", trade_s_cd_1='" + trade_s_cd_1 + '\'' +
                ", trade_s_cd_2='" + trade_s_cd_2 + '\'' +
                ", stat_s_cd='" + stat_s_cd + '\'' +
                ", price=" + price +
                ", pickup_addr_cd='" + pickup_addr_cd + '\'' +
                ", pickup_addr_name='" + pickup_addr_name + '\'' +
                ", detail_addr='" + detail_addr + '\'' +
                ", buy_id='" + buy_id + '\'' +
                ", buy_nick='" + buy_nick + '\'' +
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

