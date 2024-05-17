package team.cheese.domain;


import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class EventDto {
    Long evt_no;
    String evt_cd;
    String title;
    String contents;
    int group_no;
    String img_full_rt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date s_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date e_date;
    Timestamp r_date;
    String active_s_cd;
    Timestamp m_date;
    String prize;
    String ad_id;
    Timestamp first_r_date;
    String first_id;
    Timestamp last_r_date;
    String last_id;

    public EventDto() {}
    public EventDto(Long evt_no, String evt_cd, String title, String contents,int group_no, String img_full_rt, Date s_date, Date e_date, Timestamp r_date, String active_s_cd, Timestamp m_date, String prize, String ad_id, Timestamp first_r_date, String first_id, Timestamp last_r_date, String last_id) {
        this.evt_no = evt_no;
        this.evt_cd = evt_cd;
        this.title = title;
        this.contents = contents;
        this.img_full_rt = img_full_rt;
        this.group_no = group_no;
        this.s_date = s_date;
        this.e_date = e_date;
        this.r_date = r_date;
        this.active_s_cd = active_s_cd;
        this.m_date = m_date;
        this.prize = prize;
        this.ad_id = ad_id;
        this.first_r_date = first_r_date;
        this.first_id = first_id;
        this.last_r_date = last_r_date;
        this.last_id = last_id;
    }

    public EventDto(String title, String contents, String ad_id, Date s_date, Date e_date,String img_full_rt, String evt_cd, String prize) {
        this.title = title;
        this.contents = contents;
        this.s_date = s_date;
        this.e_date = e_date;
        this.evt_cd = evt_cd;
        this.prize = prize;
        this.active_s_cd = "P";
        this.group_no = 1;
        this.img_full_rt = img_full_rt;
        this.ad_id = ad_id;
        this.first_id = "ghkdwjdgk";
        this.last_id = "ghkdwjdgk";
    }

    public EventDto(String evt_cd, String active_s_cd, String title, String contents, Date s_date, Date e_date, int group_no,String img_full_rt, String prize, String ad_id, String first_id, String last_id) {
        this.evt_cd = evt_cd;
        this.active_s_cd = active_s_cd;
        this.title = title;
        this.contents = contents;
        this.s_date = s_date;
        this.e_date = e_date;
        this.group_no = group_no;
        this.img_full_rt = img_full_rt;
        this.prize = prize;
        this.ad_id = ad_id;
        this.first_id = first_id;
        this.last_id = last_id;
    }




    public Long getEvt_no() {
        return evt_no;
    }

    public void setEvt_no(Long evt_no) {
        this.evt_no = evt_no;
    }

    public String getEvt_cd() {
        return evt_cd;
    }

    public void setEvt_cd(String evt_cd) {
        this.evt_cd = evt_cd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getS_date() {
        return this.s_date;
    }

    public void setS_date(Date s_date) {
        this.s_date = s_date;
    }

    public Date getE_date() {
        return this.e_date;
    }

    public void setE_date(Date e_date) {
        this.e_date = e_date;
    }

    public int getGroup_no() {
        return group_no;
    }

    public void setEvt_img_sn(int group_no) {
        this.group_no = group_no;
    }

    public String getActive_s_cd() {
        return active_s_cd;
    }

    public void setActive_s_cd(String active_s_cd) {
        this.active_s_cd = active_s_cd;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
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

    public Timestamp getM_date() {
        return m_date;
    }

    public void setM_date(Timestamp m_date) {
        this.m_date = m_date;
    }

    public Timestamp getFirst_r_date() {
        return first_r_date;
    }

    public void setFirst_r_date(Timestamp first_r_date) {
        this.first_r_date = first_r_date;
    }

    public Timestamp getLast_r_date() {
        return last_r_date;
    }

    public void setLast_r_date(Timestamp last_r_date) {
        this.last_r_date = last_r_date;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public Timestamp getR_date() {
        return r_date;
    }

    public void setR_date(Timestamp r_date) {
        this.r_date = r_date;
    }

    public String getImg_full_rt() {
        return img_full_rt;
    }

    public void setImg_full_rt(String img_full_rt) {
        this.img_full_rt = img_full_rt;
    }

    public void setGroup_no(int group_no) {
        this.group_no = group_no;
    }

    @Override
    public String toString() {
        return "eventDto{" +
                "evt_no=" + evt_no +
                ", evt_cd='" + evt_cd + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", s_date=" + s_date +
                ", e_date=" + e_date +
                ", r_date=" + r_date +
                ", group_no=" + group_no +
                ", active_s_cd='" + active_s_cd + '\'' +
                ", m_date=" + m_date +
                ", prize='" + prize + '\'' +
                ", ad_id='" + ad_id + '\'' +
                ", first_r_date=" + first_r_date +
                ", first_id='" + first_id + '\'' +
                ", last_r_date=" + last_r_date +
                ", last_id='" + last_id + '\'' +
                ", img_full_rt='" + img_full_rt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDto eventDto = (EventDto) o;
        return Objects.equals(group_no, eventDto.group_no)
                && Objects.equals(evt_cd, eventDto.evt_cd)
                && Objects.equals(title, eventDto.title)
                && Objects.equals(contents, eventDto.contents)
                && Objects.equals(s_date, eventDto.s_date)
                && Objects.equals(e_date, eventDto.e_date)
                && Objects.equals(active_s_cd, eventDto.active_s_cd)
                && Objects.equals(prize, eventDto.prize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evt_cd, title, contents, s_date, e_date, r_date, group_no, active_s_cd, prize);
    }
}
