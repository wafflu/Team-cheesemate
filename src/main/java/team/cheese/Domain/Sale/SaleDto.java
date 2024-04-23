package team.cheese.Domain.Sale;

import java.sql.Timestamp;
import java.util.Objects;

public class SaleDto {
    private Integer no;
    private String addr_cd;
    private String addr_name;
    private String seller_id;
    private String seller_nick;
    private String sal_i_cd;

    private String sal_name;
    private char pro_s_cd;
    private char tx_s_cd;
    private char trade_s_cd_1;
    private char trade_s_cd_2;
    private char sal_s_cd;
    private String title;
    private String contents;
    private int price;
    private char bid_cd;
    private String pickup_addr_cd;
    private String pickup_addr_name;
    private String detail_addr;
    private String brand;
    private int reg_price;
    private String buyer_id;
    private  String buyer_nick;
    private int like_cnt;
    private int view_cnt;
    private Timestamp r_date;
    private Timestamp m_date;
    private int hoist_cnt;
    private Timestamp h_date;
    private int bid_cnt;
    private char ur_state;
    private char ad_state;
    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
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

    public String getSeller_nick() {
        return seller_nick;
    }

    public void setSeller_nick(String seller_nick) {
        this.seller_nick = seller_nick;
    }

    public String getSal_i_cd() {
        return sal_i_cd;
    }

    public void setSal_i_cd(String sal_i_cd) {
        this.sal_i_cd = sal_i_cd;
    }

    public String getSal_name() {
        return sal_name;
    }

    public void setSal_name(String sal_name) {
        this.sal_name = sal_name;
    }

    public char getPro_s_cd() {
        return pro_s_cd;
    }

    public void setPro_s_cd(char pro_s_cd) {
        this.pro_s_cd = pro_s_cd;
    }

    public char getTx_s_cd() {
        return tx_s_cd;
    }

    public void setTx_s_cd(char tx_s_cd) {
        this.tx_s_cd = tx_s_cd;
    }

    public char getTrade_s_cd_1() {
        return trade_s_cd_1;
    }

    public void setTrade_s_cd_1(char trade_s_cd_1) {
        this.trade_s_cd_1 = trade_s_cd_1;
    }

    public char getTrade_s_cd_2() {
        return trade_s_cd_2;
    }

    public void setTrade_s_cd_2(char trade_s_cd_2) {
        this.trade_s_cd_2 = trade_s_cd_2;
    }

    public char getSal_s_cd() {
        return sal_s_cd;
    }

    public void setSal_s_cd(char sal_s_cd) {
        this.sal_s_cd = sal_s_cd;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public char getBid_cd() {
        return bid_cd;
    }

    public void setBid_cd(char bid_cd) {
        this.bid_cd = bid_cd;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getReg_price() {
        return reg_price;
    }

    public void setReg_price(int reg_price) {
        this.reg_price = reg_price;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_nick() {
        return buyer_nick;
    }

    public void setBuyer_nick(String buyer_nick) {
        this.buyer_nick = buyer_nick;
    }

    public int getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(int like_cnt) {
        this.like_cnt = like_cnt;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
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

    public int getHoist_cnt() {
        return hoist_cnt;
    }

    public void setHoist_cnt(int hoist_cnt) {
        this.hoist_cnt = hoist_cnt;
    }

    public Timestamp getH_date() {
        return h_date;
    }

    public void setH_date(Timestamp h_date) {
        this.h_date = h_date;
    }

    public int getBid_cnt() {
        return bid_cnt;
    }

    public void setBid_cnt(int bid_cnt) {
        this.bid_cnt = bid_cnt;
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
        SaleDto sale = (SaleDto) o;
        return pro_s_cd == sale.pro_s_cd && tx_s_cd == sale.tx_s_cd && trade_s_cd_1 == sale.trade_s_cd_1 && trade_s_cd_2 == sale.trade_s_cd_2 && sal_s_cd == sale.sal_s_cd && price == sale.price && bid_cd == sale.bid_cd && reg_price == sale.reg_price && like_cnt == sale.like_cnt && view_cnt == sale.view_cnt && hoist_cnt == sale.hoist_cnt && bid_cnt == sale.bid_cnt && ur_state == sale.ur_state && ad_state == sale.ad_state && Objects.equals(no, sale.no) && Objects.equals(addr_cd, sale.addr_cd) && Objects.equals(addr_name, sale.addr_name) && Objects.equals(seller_id, sale.seller_id) && Objects.equals(seller_nick, sale.seller_nick) && Objects.equals(sal_i_cd, sale.sal_i_cd) && Objects.equals(sal_name, sale.sal_name) && Objects.equals(title, sale.title) && Objects.equals(contents, sale.contents) && Objects.equals(pickup_addr_cd, sale.pickup_addr_cd) && Objects.equals(pickup_addr_name, sale.pickup_addr_name) && Objects.equals(detail_addr, sale.detail_addr) && Objects.equals(brand, sale.brand) && Objects.equals(buyer_id, sale.buyer_id) && Objects.equals(buyer_nick, sale.buyer_nick) && Objects.equals(r_date, sale.r_date) && Objects.equals(m_date, sale.m_date) && Objects.equals(h_date, sale.h_date) && Objects.equals(first_date, sale.first_date) && Objects.equals(first_id, sale.first_id) && Objects.equals(last_date, sale.last_date) && Objects.equals(last_id, sale.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, addr_cd, addr_name, seller_id, seller_nick, sal_i_cd, sal_name, pro_s_cd, tx_s_cd, trade_s_cd_1, trade_s_cd_2, sal_s_cd, title, contents, price, bid_cd, pickup_addr_cd, pickup_addr_name, detail_addr, brand, reg_price, buyer_id, buyer_nick, like_cnt, view_cnt, r_date, m_date, hoist_cnt, h_date, bid_cnt, ur_state, ad_state, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "no=" + no +
                ", addr_cd='" + addr_cd + '\'' +
                ", addr_name='" + addr_name + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", seller_nick='" + seller_nick + '\'' +
                ", sal_i_cd='" + sal_i_cd + '\'' +
                ", sal_name='" + sal_name + '\'' +
                ", pro_s_cd=" + pro_s_cd +
                ", tx_s_cd=" + tx_s_cd +
                ", trade_s_cd_1=" + trade_s_cd_1 +
                ", trade_s_cd_2=" + trade_s_cd_2 +
                ", sal_s_cd=" + sal_s_cd +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", price=" + price +
                ", bid_cd=" + bid_cd +
                ", pickup_addr_cd='" + pickup_addr_cd + '\'' +
                ", pickup_addr_name='" + pickup_addr_name + '\'' +
                ", detail_addr='" + detail_addr + '\'' +
                ", brand='" + brand + '\'' +
                ", reg_price=" + reg_price +
                ", buyer_id='" + buyer_id + '\'' +
                ", buyer_nick='" + buyer_nick + '\'' +
                ", like_cnt=" + like_cnt +
                ", view_cnt=" + view_cnt +
                ", r_date=" + r_date +
                ", m_date=" + m_date +
                ", hoist_cnt=" + hoist_cnt +
                ", h_date=" + h_date +
                ", bid_cnt=" + bid_cnt +
                ", ur_state=" + ur_state +
                ", ad_state=" + ad_state +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
