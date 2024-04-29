package team.cheese.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventDto {
    Long evt_no;
    String evt_cd;
    String title;
    String contents;
    LocalDate s_date;
    LocalDate e_date;
    Timestamp r_date;
    int evt_img_sn;
    String active_s_cd;
    Timestamp m_date;
    String prize;
    String ad_id;
    Timestamp first_r_date;
    String first_id;
    Timestamp last_r_date;
    String last_id;
    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String filert;
    public EventDto() {}
    public EventDto(Long evt_no, String evt_cd, String title, String contents, LocalDate s_date, LocalDate e_date, Timestamp r_date, int evt_img_sn, String active_s_cd, Timestamp m_date, String prize, String ad_id, Timestamp first_r_date, String first_id, Timestamp last_r_date, String last_id) {
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
        this.first_id = first_id;
        this.last_r_date = last_r_date;
        this.last_id = last_id;
    }

    public EventDto(String title, String contents,String ad_id, LocalDate s_date, LocalDate e_date, String evt_cd, String prize) {
        this.title = title;
        this.contents = contents;
        this.s_date = s_date;
        this.e_date = e_date;
        this.evt_cd = evt_cd;
        this.prize = prize;
        this.active_s_cd = "P";
        this.evt_img_sn = 1;
        this.ad_id = ad_id;
        this.first_id = "ghkdwjdgk";
        this.last_id = "ghkdwjdgk";
    }

    public EventDto(String evt_cd, String active_s_cd, String title, String contents, LocalDate s_date, LocalDate e_date, int evt_img_sn, String prize, String ad_id, String first_id, String last_id) {
        this.evt_cd = evt_cd;
        this.active_s_cd = active_s_cd;
        this.title = title;
        this.contents = contents;
        this.s_date = s_date;
        this.e_date = e_date;
        this.evt_img_sn = evt_img_sn;
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

    public LocalDate getS_date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");

        // 형식에 맞게 날짜를 문자열로 변환
        String formattedDate = s_date.format(formatter);
        return this.s_date;
    }

    public void setS_date(LocalDate s_date) {
        this.s_date = s_date;
    }

    public LocalDate getE_date() {
        return this.e_date;
    }

    public void setE_date(LocalDate e_date) {
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

    public String getFilert() {
        return filert;
    }

    public void setFilert(String filert) {
        this.filert = filert;
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
                ", evt_img_sn=" + evt_img_sn +
                ", active_s_cd='" + active_s_cd + '\'' +
                ", m_date=" + m_date +
                ", prize='" + prize + '\'' +
                ", ad_id='" + ad_id + '\'' +
                ", first_r_date=" + first_r_date +
                ", first_id='" + first_id + '\'' +
                ", last_r_date=" + last_r_date +
                ", last_id='" + last_id + '\'' +
                ", transFormat=" + transFormat +
                '}';
    }
}