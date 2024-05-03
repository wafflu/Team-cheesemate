package team.cheese.domain;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

public class BidingDto {
    private BigInteger no; // 가격제시/나눔신청 번호
    private BigInteger sal_no; // 판매글 번호
    private String addr_cd; // 행정구역 코드
    private String addr_name; // 주소명
    private char bid_cd; // 가격제시/나눔신청 구분
    private String buyer_id; // 신청자(구매자) id
    private int price; // 가격제시일때 제시하는 가격
    private String reason; // 나눔신청 사유
    private char bid_state; // 제시/신청 취소 여부
    private Timestamp bid_date; // 제시/신청 날짜
    private char grant_state; // 승낙 여부
    private Timestamp grant_date; // 승낙일

    private Timestamp first_date;

    private String first_id;

    private Timestamp last_date;

    private String last_id;

    public BigInteger getNo() {
        return no;
    }

    public void setNo(BigInteger no) {
        this.no = no;
    }

    public BigInteger getSal_no() {
        return sal_no;
    }

    public void setSal_no(BigInteger sal_no) {
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

    public char getBid_cd() {
        return bid_cd;
    }

    public void setBid_cd(char bid_cd) {
        this.bid_cd = bid_cd;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public char getBid_state() {
        return bid_state;
    }

    public void setBid_state(char bid_state) {
        this.bid_state = bid_state;
    }

    public Timestamp getBid_date() {
        return bid_date;
    }

    public void setBid_date(Timestamp bid_date) {
        this.bid_date = bid_date;
    }

    public char getGrant_state() {
        return grant_state;
    }

    public void setGrant_state(char grant_state) {
        this.grant_state = grant_state;
    }

    public Timestamp getGrant_date() {
        return grant_date;
    }

    public void setGrant_date(Timestamp grant_date) {
        this.grant_date = grant_date;
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
        BidingDto bidingDto = (BidingDto) o;
        return bid_cd == bidingDto.bid_cd && price == bidingDto.price && bid_state == bidingDto.bid_state && grant_state == bidingDto.grant_state && Objects.equals(no, bidingDto.no) && Objects.equals(sal_no, bidingDto.sal_no) && Objects.equals(addr_cd, bidingDto.addr_cd) && Objects.equals(addr_name, bidingDto.addr_name) && Objects.equals(buyer_id, bidingDto.buyer_id) && Objects.equals(reason, bidingDto.reason) && Objects.equals(bid_date, bidingDto.bid_date) && Objects.equals(grant_date, bidingDto.grant_date) && Objects.equals(first_date, bidingDto.first_date) && Objects.equals(first_id, bidingDto.first_id) && Objects.equals(last_date, bidingDto.last_date) && Objects.equals(last_id, bidingDto.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, sal_no, addr_cd, addr_name, bid_cd, buyer_id, price, reason, bid_state, bid_date, grant_state, grant_date, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "BidingDto{" +
                "no=" + no +
                ", sal_no=" + sal_no +
                ", addr_cd='" + addr_cd + '\'' +
                ", addr_name='" + addr_name + '\'' +
                ", bid_cd=" + bid_cd +
                ", buyer_id='" + buyer_id + '\'' +
                ", price=" + price +
                ", reason='" + reason + '\'' +
                ", bid_state=" + bid_state +
                ", bid_date=" + bid_date +
                ", grant_state=" + grant_state +
                ", grant_date=" + grant_date +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
