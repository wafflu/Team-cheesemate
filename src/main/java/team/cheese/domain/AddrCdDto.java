package team.cheese.domain;

import java.math.BigInteger;
import java.security.Timestamp;
import java.util.Objects;

public class AddrCdDto {
    private long no;
    private String ur_id;
    private String addr_cd;
    private String addr_name;
    private char state;
    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;

    public AddrCdDto() {
    }

        this.no = no;
        this.ur_id = ur_id;
        this.addr_cd = addr_cd;
        this.addr_name = addr_name;
        this.state = state;
        this.first_date = first_date;
        this.first_id = first_id;
        this.last_date = last_date;
        this.last_id = last_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddrCdDto addrCdDto = (AddrCdDto) o;
        return state == addrCdDto.state && Objects.equals(no, addrCdDto.no) && Objects.equals(ur_id, addrCdDto.ur_id) && Objects.equals(addr_cd, addrCdDto.addr_cd) && Objects.equals(addr_name, addrCdDto.addr_name) && Objects.equals(first_date, addrCdDto.first_date) && Objects.equals(first_id, addrCdDto.first_id) && Objects.equals(last_date, addrCdDto.last_date) && Objects.equals(last_id, addrCdDto.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, ur_id, addr_cd, addr_name, state, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "AddrCdDto{" +
                "no=" + no +
                ", ur_id='" + ur_id + '\'' +
                ", addr_cd='" + addr_cd + '\'' +
                ", addr_name='" + addr_name + '\'' +
                ", state=" + state +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getUr_id() {
        return ur_id;
    }

    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
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

    public char getState() {
        return state;
    }

    public void setState(char state) {
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
}
