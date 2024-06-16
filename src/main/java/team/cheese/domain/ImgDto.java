package team.cheese.domain;

import java.util.Objects;

public class ImgDto {
    private Integer no;
    private String imgtype;
    private String file_rt;
    private String u_name;
    private String o_name;
    private String e_name;
    private Integer w_size;
    private Integer h_size;
    private String r_date;
    private String state;
    private String img_full_rt;
    private Integer group_no;

    private String first_date;
    private String first_id;
    private String last_date;
    private String last_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImgDto imgDto = (ImgDto) o;
        return Objects.equals(imgtype, imgDto.imgtype) && Objects.equals(file_rt, imgDto.file_rt) && Objects.equals(u_name, imgDto.u_name) && Objects.equals(o_name, imgDto.o_name) && Objects.equals(e_name, imgDto.e_name) && Objects.equals(w_size, imgDto.w_size) && Objects.equals(h_size, imgDto.h_size) && Objects.equals(img_full_rt, imgDto.img_full_rt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgtype, file_rt, u_name, o_name, e_name, w_size, h_size, img_full_rt);
    }

    @Override
    public String toString() {
        return "ImgDto{" +
                "no=" + no +
                ", imgtype='" + imgtype + '\'' +
                ", file_rt='" + file_rt + '\'' +
                ", u_name='" + u_name + '\'' +
                ", o_name='" + o_name + '\'' +
                ", e_name='" + e_name + '\'' +
                ", w_size=" + w_size +
                ", h_size=" + h_size +
                ", r_date='" + r_date + '\'' +
                ", state='" + state + '\'' +
                ", img_full_rt='" + img_full_rt + '\'' +
                ", group_no=" + group_no +
                ", first_date='" + first_date + '\'' +
                ", first_id='" + first_id + '\'' +
                ", last_date='" + last_date + '\'' +
                ", last_id='" + last_id + '\'' +
                '}';
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getImgtype() {
        return imgtype;
    }

    public void setImgtype(String imgtype) {
        this.imgtype = imgtype;
    }

    public String getFile_rt() {
        return file_rt;
    }

    public void setFile_rt(String file_rt) {
        this.file_rt = file_rt;
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

    public Integer getW_size() {
        return w_size;
    }

    public void setW_size(Integer w_size) {
        this.w_size = w_size;
    }

    public Integer getH_size() {
        return h_size;
    }

    public void setH_size(Integer h_size) {
        this.h_size = h_size;
    }

    public String getR_date() {
        return r_date;
    }

    public void setR_date(String r_date) {
        this.r_date = r_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImg_full_rt() {
        return img_full_rt;
    }

    public void setImg_full_rt(String img_full_rt) {
        this.img_full_rt = img_full_rt;
    }

    public Integer getGroup_no() {
        return group_no;
    }

    public void setGroup_no(Integer group_no) {
        this.group_no = group_no;
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
}
