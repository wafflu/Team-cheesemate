package team.cheese.domain;

public class ChatDto {
    Long cr_no;
    String nick;
    String content;
    String del_state;
    String read_state;
    String first_id;
    String last_id;

    public ChatDto() {

    }

    public Long getCr_no() {
        return cr_no;
    }

    public void setCr_no(Long cr_no) {
        this.cr_no = cr_no;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getFirst_id() {
        return first_id;
    }

    public void setFirst_id(String first_id) {
        this.first_id = first_id;
    }

    public String getLast_id() {
        return last_id;
    }

    public void setLast_id(String last_id) {
        this.last_id = last_id;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "cr_no=" + cr_no +
                ", nick='" + nick + '\'' +
                ", content='" + content + '\'' +
                ", del_state='" + del_state + '\'' +
                ", read_state='" + read_state + '\'' +
                ", first_id='" + first_id + '\'' +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
