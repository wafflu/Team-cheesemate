package team.cheese.domain;

public class ImgDto {
    private Integer no;
    private Integer tb_no;
    private String tb_name;
    private String filert;
    private String u_name;
    private String o_name;
    private String e_name;
    private int w_size;
    private int h_size;
    private String r_date;
    private String state;

    public ImgDto(){}

    public ImgDto(String u_name, String o_name, String e_name){

    }

    @Override
    public String toString() {
        return "ImgVO{" +
                "no=" + no +
                "tb_no=" + tb_no +
                ", tb_name=" + tb_name +
                ", filert='" + filert + '\'' +
                ", u_name='" + u_name + '\'' +
                ", o_name='" + o_name + '\'' +
                ", e_name='" + e_name + '\'' +
                ", w_size=" + w_size +
                ", h_size=" + h_size +
                ", r_date='" + r_date + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public Integer getTb_no() {
        return tb_no;
    }

    public void setTb_no(int tb_no) {
        this.tb_no = tb_no;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
