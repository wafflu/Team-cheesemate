package team.cheese.domain.img;

public class ImgVO {
    private int sn;
    private String tb_name;
    private String filert;
    private String u_name;
    private String o_name;
    private String e_name;
    private int w_size;
    private int h_size;
    private String r_date;
    private String st;
    private String first_r_date;
    private String first_idno;
    private String last_r_date;
    private String last_idno;

    public ImgVO(){}

    public ImgVO(String u_name, String o_name, String e_name){

    }

    @Override
    public String toString() {
        return "ImgVO{" +
                "sn=" + sn +
                ", tb_name=" + tb_name +
                ", filert='" + filert + '\'' +
                ", u_name='" + u_name + '\'' +
                ", o_name='" + o_name + '\'' +
                ", e_name='" + e_name + '\'' +
                ", w_size=" + w_size +
                ", h_size=" + h_size +
                ", r_date='" + r_date + '\'' +
                ", st='" + st + '\'' +
                ", first_r_date='" + first_r_date + '\'' +
                ", first_idno='" + first_idno + '\'' +
                ", last_r_date='" + last_r_date + '\'' +
                ", last_idno='" + last_idno + '\'' +
                '}';
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getTb_name() {
        return tb_name;
    }

    public void setTb_name(String tb_name) {
        this.tb_name = tb_name;
    }

    public String getFilert() {
        return filert;
    }

    public void setFilert(String filert) {
        this.filert = filert;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getO_name() {
        return o_name;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public int getW_size() {
        return w_size;
    }

    public void setW_size(int w_size) {
        this.w_size = w_size;
    }

    public int getH_size() {
        return h_size;
    }

    public void setH_size(int h_size) {
        this.h_size = h_size;
    }

    public String getR_date() {
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getFirst_r_date() {
        return first_r_date;
    }

    public void setFirst_r_date(String first_r_date) {
        this.first_r_date = first_r_date;
    }

    public String getFirst_idno() {
        return first_idno;
    }

    public void setFirst_idno(String first_idno) {
        this.first_idno = first_idno;
    }

    public String getLast_r_date() {
        return last_r_date;
    }

    public void setLast_r_date(String last_r_date) {
        this.last_r_date = last_r_date;
    }

    public String getLast_idno() {
        return last_idno;
    }

    public void setLast_idno(String last_idno) {
        this.last_idno = last_idno;
    }
}
