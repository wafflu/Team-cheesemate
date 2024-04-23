package team.cheese.Domain.Event;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class eventDto {
    Long evt_no;
    String evt_cd;
    String title;
    String contents;
    Date s_date;
    Date e_date;
    Timestamp r_date;
    int evt_img_sn;
    String active_s_cd;
    Timestamp m_date;
    String prize;
    String ad_id;
    Timestamp first_r_date;
    String first_idno;
    Timestamp last_r_date;
    String last_idno;
    SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
    public eventDto(Long evt_no, String evt_cd, String title, String contents, Date s_date, Date e_date, Timestamp r_date, int evt_img_sn, String active_s_cd, Timestamp m_date, String prize, String ad_id, Timestamp first_r_date, String first_idno, Timestamp last_r_date, String last_idno) {
        this.evt_no = evt_no;
        this.evt_cd = evt_cd;
        this.title = title;
        this.contents = contents;
        this.s_date = s_date;
        this.e_date = e_date;
        this.r_date = r_date;
        this.evt_img_sn = evt_img_sn;
        this.active_s_cd = active_s_cd;
        this.m_date = m_date;
        this.prize = prize;
        this.ad_id = ad_id;
        this.first_r_date = first_r_date;
        this.first_idno = first_idno;
        this.last_r_date = last_r_date;
        this.last_idno = last_idno;
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

    public String getS_date() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 mm월 dd일");
        return formatter.format(this.s_date);
    }

    public void setS_date(Date s_date) {
        this.s_date = s_date;
    }

    public String getE_date() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 mm월 dd일");
        return formatter.format(this.s_date);
    }

    public void setE_date(Date e_date) {
        this.e_date = e_date;
    }

    public int getEvt_img_sn() {
        return evt_img_sn;
    }

    public void setEvt_img_sn(int evt_img_sn) {
        this.evt_img_sn = evt_img_sn;
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

    @Override
    public String toString() {
        return "eventDto{" +
                "evt_no=" + evt_no +
                ", evt_cd='" + evt_cd + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", s_date=" + s_date +
                ", e_date=" + e_date +
                ", evt_img_sn=" + evt_img_sn +
                ", active_s_cd=" + active_s_cd +
                ", prize='" + prize + '\'' +
                ", first_idno='" + first_idno + '\'' +
                ", last_idno='" + last_idno + '\'' +
                '}';
    }

}
