package team.cheese.domain.MyPage;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;

public class SaleDto {

    @NotBlank
    @NotEmpty
    @NotNull
    private Long no;

    @NotBlank
    @NotEmpty
    @NotNull
    private String addr_cd;

    @NotBlank
    @NotEmpty
    @NotNull
    private String addr_name;

    @NotBlank
    @NotEmpty
    @NotNull
    private String seller_id;

    @NotBlank
    @NotEmpty
    @NotNull
    private String seller_nick;

    @NotBlank
    @NotEmpty
    @NotNull(message = "판매카테고리를 선택해 주세요.")
    private String sal_i_cd;

    @NotBlank
    @NotEmpty
    @NotNull
    private String sal_name;
    private int group_no;
    private String img_full_rt;
    private String pro_s_cd;
    private String tx_s_cd;
    private String trade_s_cd_1;
    private String trade_s_cd_2;
    private String sal_s_cd;

    @NotBlank
    @NotEmpty
    @NotNull(message = "제목을 입력해주세요.")
    @Size(max = 100)
    private String title;

    @NotBlank
    @NotEmpty
    @NotNull(message = "내용을 입력해주세요.")
    @Size(max = 2000)
    private String contents;

    private Integer price;

    @NotBlank
    @NotEmpty
    @NotNull
    private String bid_cd;
    private String pickup_addr_cd;
    private String pickup_addr_name;
    private String detail_addr;
    private String brand;
    private Integer reg_price;
    private String buyer_id;
    private String buyer_nick;
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

    public SaleDto() {
    }

    public SaleDto(String addr_cd, String addr_name) {
        this.addr_cd = addr_cd;
        this.addr_name = addr_name;
    }


    public void setAddrSeller(String seller_id, String seller_nick) {
        this.seller_id = seller_id;
        this.seller_nick = seller_nick;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
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

    public int getGroup_no() {
        return group_no;
    }

    public void setGroup_no(int group_no) {
        this.group_no = group_no;
    }

    public String getImg_full_rt() {
        return img_full_rt;
    }

    public void setImg_full_rt(String img_full_rt) {
        this.img_full_rt = img_full_rt;
    }

    public String getPro_s_cd() {
        return pro_s_cd;
    }

    public void setPro_s_cd(String pro_s_cd) {
        this.pro_s_cd = pro_s_cd;
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

    public String getSal_s_cd() {
        return sal_s_cd;
    }

    public void setSal_s_cd(String sal_s_cd) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBid_cd() {
        return bid_cd;
    }

    public void setBid_cd(String bid_cd) {
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

    public Integer getReg_price() {
        return reg_price;
    }

    public void setReg_price(Integer reg_price) {
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
        SaleDto saleDto = (SaleDto) o;
        return group_no == saleDto.group_no && price == saleDto.price && reg_price == saleDto.reg_price && like_cnt == saleDto.like_cnt && view_cnt == saleDto.view_cnt && hoist_cnt == saleDto.hoist_cnt && bid_cnt == saleDto.bid_cnt && ur_state == saleDto.ur_state && ad_state == saleDto.ad_state && Objects.equals(no, saleDto.no) && Objects.equals(addr_cd, saleDto.addr_cd) && Objects.equals(addr_name, saleDto.addr_name) && Objects.equals(seller_id, saleDto.seller_id) && Objects.equals(seller_nick, saleDto.seller_nick) && Objects.equals(sal_i_cd, saleDto.sal_i_cd) && Objects.equals(sal_name, saleDto.sal_name) && Objects.equals(img_full_rt, saleDto.img_full_rt) && Objects.equals(pro_s_cd, saleDto.pro_s_cd) && Objects.equals(tx_s_cd, saleDto.tx_s_cd) && Objects.equals(trade_s_cd_1, saleDto.trade_s_cd_1) && Objects.equals(trade_s_cd_2, saleDto.trade_s_cd_2) && Objects.equals(sal_s_cd, saleDto.sal_s_cd) && Objects.equals(title, saleDto.title) && Objects.equals(contents, saleDto.contents) && Objects.equals(bid_cd, saleDto.bid_cd) && Objects.equals(pickup_addr_cd, saleDto.pickup_addr_cd) && Objects.equals(pickup_addr_name, saleDto.pickup_addr_name) && Objects.equals(detail_addr, saleDto.detail_addr) && Objects.equals(brand, saleDto.brand) && Objects.equals(buyer_id, saleDto.buyer_id) && Objects.equals(buyer_nick, saleDto.buyer_nick) && Objects.equals(r_date, saleDto.r_date) && Objects.equals(m_date, saleDto.m_date) && Objects.equals(h_date, saleDto.h_date) && Objects.equals(first_date, saleDto.first_date) && Objects.equals(first_id, saleDto.first_id) && Objects.equals(last_date, saleDto.last_date) && Objects.equals(last_id, saleDto.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, addr_cd, addr_name, seller_id, seller_nick, sal_i_cd, sal_name, group_no, img_full_rt, pro_s_cd, tx_s_cd, trade_s_cd_1, trade_s_cd_2, sal_s_cd, title, contents, price, bid_cd, pickup_addr_cd, pickup_addr_name, detail_addr, brand, reg_price, buyer_id, buyer_nick, like_cnt, view_cnt, r_date, m_date, hoist_cnt, h_date, bid_cnt, ur_state, ad_state, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "SaleDto{" +
                "no=" + no +
                ", addr_cd='" + addr_cd + '\'' +
                ", addr_name='" + addr_name + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", seller_nick='" + seller_nick + '\'' +
                ", sal_i_cd='" + sal_i_cd + '\'' +
                ", sal_name='" + sal_name + '\'' +
                ", group_no=" + group_no +
                ", img_full_rt='" + img_full_rt + '\'' +
                ", pro_s_cd='" + pro_s_cd + '\'' +
                ", tx_s_cd='" + tx_s_cd + '\'' +
                ", trade_s_cd_1='" + trade_s_cd_1 + '\'' +
                ", trade_s_cd_2='" + trade_s_cd_2 + '\'' +
                ", sal_s_cd='" + sal_s_cd + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", price=" + price +
                ", bid_cd='" + bid_cd + '\'' +
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