package team.cheese.domain;

public class ChatMessageDto {
    private Long cr_no;
    private Long sale_no;
    private String acid;
    private String nick;
    private String message;
    private String r_date;
    private String del_state;
    private String read_state;
    private String first_date;
    private String first_id;
    private String last_date;
    private String last_id;

    private String img_full_rt;

    public ChatMessageDto(){}

    public ChatMessageDto(final String nick, final String message, final String r_date, final String img) {
        this.nick = nick;
        this.message = message;
        this.r_date = r_date;
        this.img_full_rt = img;
    }

    @Override
    public String toString() {
        return "ChatMessageDto{" +
                "cr_no=" + cr_no +
                ", sale_no=" + sale_no +
                ", acid="+acid+
                ", nick='" + nick + '\'' +
                ", message='" + message + '\'' +
                ", r_date='" + r_date + '\'' +
                ", del_state='" + del_state + '\'' +
                ", read_state='" + read_state + '\'' +
                '}';
    }

    public Long getCr_no() {
        return cr_no;
    }

    public void setCr_no(Long cr_no) {
        this.cr_no = cr_no;
    }

    public Long getSale_no() {
        return sale_no;
    }

    public void setSale_no(Long sale_no) {
        this.sale_no = sale_no;
    }

    public String getAcid() {
        return acid;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getR_date() {
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

    public String getDel_state() {
        return del_state;
    }

    public void setDel_state(String del_state) {
        this.del_state = del_state;
    }

    public String getRead_state() {
        return read_state;
    }

    public void setRead_state(String read_state) {
        this.read_state = read_state;
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

    public String getImg_full_rt() {
        return img_full_rt;
    }

    public void setImg_full_rt(String img_full_rt) {
        this.img_full_rt = img_full_rt;
    }
}
