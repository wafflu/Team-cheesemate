package team.cheese.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;

public class UserDto {

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$")
    @Size(min = 6, max = 20)
    private String id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z\\d@$!%*?&]{6,16}$")
    @Size(min = 6, max = 16)
    private String pw;

    @NotNull
    @Pattern(regexp = "^[A-Za-z가-힣]+$")
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Pattern(regexp = "[A-Za-z가-힣]{2,}")
    @Size(min = 2, max = 20)
    private String nick;

    @NotNull
    private String birth;

    @NotNull
    private char gender;

    @NotNull
    @Size(min = 11, max = 11)
    private String phone_num;

    private String safe_num;

    @NotNull
    private char foreigner;

    @NotNull
    @Email
    @Size(min = 6, max = 50)
    private String email;

    @NotNull
    private char s_cd;

    @NotNull
    @Size(min = 6, max = 50)
    private String addr_det;

    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;

    public UserDto() {
    }

    public UserDto(String id, String pw, String name, String nick, String birth, char gender, String phone_num, String safe_num, char foreigner, String email, char s_cd, String addr_det, Timestamp first_date, String first_id, Timestamp last_date, String last_id) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nick = nick;
        this.birth = birth;
        this.gender = gender;
        this.phone_num = phone_num;
        this.safe_num = safe_num;
        this.foreigner = foreigner;
        this.email = email;
        this.s_cd = s_cd;
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
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(pw, userDto.pw) && Objects.equals(name, userDto.name) && Objects.equals(nick, userDto.nick) && Objects.equals(birth, userDto.birth) && Objects.equals(gender, userDto.gender) && Objects.equals(phone_num, userDto.phone_num) && Objects.equals(safe_num, userDto.safe_num) && Objects.equals(foreigner, userDto.foreigner) && Objects.equals(email, userDto.email) && Objects.equals(s_cd, userDto.s_cd) && Objects.equals(addr_det, userDto.addr_det) && Objects.equals(first_date, userDto.first_date) && Objects.equals(first_id, userDto.first_id) && Objects.equals(last_date, userDto.last_date) && Objects.equals(last_id, userDto.last_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pw, name, nick, birth, gender, phone_num, safe_num, foreigner, email, s_cd, addr_det, first_date, first_id, last_date, last_id);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", birth='" + birth + '\'' +
                ", gender='" + gender + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", safe_num='" + safe_num + '\'' +
                ", foreigner='" + foreigner + '\'' +
                ", email='" + email + '\'' +
                ", s_cd='" + s_cd + '\'' +
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getSafe_num() {
        return safe_num;
    }

    public void setSafe_num(String safe_num) {
        this.safe_num = safe_num;
    }

    public char getForeigner() {
        return foreigner;
    }

    public void setForeigner(char foreigner) {
        this.foreigner = foreigner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getS_cd() {
        return s_cd;
    }

    public void setS_cd(char s_cd) {
        this.s_cd = s_cd;
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
