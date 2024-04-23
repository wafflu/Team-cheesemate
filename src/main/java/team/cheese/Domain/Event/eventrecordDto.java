package team.cheese.Domain.Event;

public class eventrecordDto {
    int evt_no;
    String ur_id;
    char complete;
    char r_s_cd;
    String first_idno;
    String last_idno;

    public eventrecordDto(int evt_no, String ur_id, String first_idno, String last_idno) {
        this.evt_no = evt_no;
        this.ur_id = ur_id;
        this.first_idno = first_idno;
        this.last_idno = last_idno;
    }

    public eventrecordDto(String ur_id, char complete, char r_s_cd) {
        this.ur_id = ur_id;
        this.complete = complete;
        this.r_s_cd = r_s_cd;
    }

    public int getEvt_no() {
        return evt_no;
    }

    public void setEvt_no(int evt_no) {
        this.evt_no = evt_no;
    }

    public String getUr_id() {
        return ur_id;
    }

    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
    }

    public char getComplete() {
        return complete;
    }

    public void setComplete(char complete) {
        this.complete = complete;
    }

    public char getR_s_cd() {
        return r_s_cd;
    }

    public void setR_s_cd(char r_s_cd) {
        this.r_s_cd = r_s_cd;
    }

    public String getFirst_idno() {
        return first_idno;
    }

    public void setFirst_idno(String first_idno) {
        this.first_idno = first_idno;
    }

    public String getLast_idno() {
        return last_idno;
    }

    public void setLast_idno(String last_idno) {
        this.last_idno = last_idno;
    }

    @Override
    public String toString() {
        return "eventrecordDto{" +
                "evt_no=" + evt_no +
                ", ur_id='" + ur_id + '\'' +
                ", complete=" + complete +
                ", r_s_cd=" + r_s_cd +
                ", first_idno='" + first_idno + '\'' +
                ", last_idno='" + last_idno + '\'' +
                '}';
    }
}

