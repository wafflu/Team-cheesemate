package team.cheese.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class AdminDto {
    private String id;
    private String pw;
    private String name;
    private String phone_num;
    private String email;
    private String role;
    private String dept;
    private char w_cd;
    private Timestamp ent_date;
    private Timestamp res_date;
    private String addr_name;
    private String addr_det;
    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;

    public AdminDto() {
    }

    public AdminDto(String id, String pw, String name, String phone_num, String email, String role, String dept, char w_cd, Timestamp ent_date, Timestamp res_date, String addr_name, String addr_det, Timestamp first_date, String first_id, Timestamp last_date, String last_id) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone_num = phone_num;
        this.email = email;
        this.role = role;
        this.dept = dept;
        this.w_cd = w_cd;
        this.ent_date = ent_date;
        this.res_date = res_date;
        this.addr_name = addr_name;
        this.addr_det = addr_det;
        this.first_date = first_date;
        this.first_id = first_id;
        this.last_date = last_date;
        this.last_id = last_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDto that = (AdminDto) o;
        return w_cd == that.w_cd && Objects.equals(id, that.id) && Objects.equals(pw, that.pw) && Objects.equals(name, that.name) && Objects.equals(phone_num, that.phone_num) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(dept, that.dept) && Objects.equals(ent_date, that.ent_date) && Objects.equals(res_date, that.res_date) && Objects.equals(addr_name, that.addr_name) && Objects.equals(addr_det, that.addr_det) && Objects.equals(first_date, that.first_date) && Objects.equals(first_id, that.first_id) && Objects.equals(last_date, that.last_date) && Objects.equals(last_id, that.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pw, name, phone_num, email, role, dept, w_cd, ent_date, res_date, addr_name, addr_det, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "AdministerDto{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", dept='" + dept + '\'' +
                ", w_cd=" + w_cd +
                ", ent_date=" + ent_date +
                ", res_date=" + res_date +
                ", addr_name='" + addr_name + '\'' +
                ", addr_det='" + addr_det + '\'' +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public char getW_cd() {
        return w_cd;
    }

    public void setW_cd(char w_cd) {
        this.w_cd = w_cd;
    }

    public Timestamp getEnt_date() {
        return ent_date;
    }

    public void setEnt_date(Timestamp ent_date) {
        this.ent_date = ent_date;
    }

    public Timestamp getRes_date() {
        return res_date;
    }

    public void setRes_date(Timestamp res_date) {
        this.res_date = res_date;
    }

    public String getAddr_name() {
        return addr_name;
    }

    public void setAddr_name(String addr_name) {
        this.addr_name = addr_name;
    }

    public String getAddr_det() {
        return addr_det;
    }

    public void setAddr_det(String addr_det) {
        this.addr_det = addr_det;
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
