package team.cheese.domain.CommunityBoard;

import javax.validation.constraints.*;
import java.sql.Timestamp;

public class CommunityBoardDto {



        private Integer no;

        @NotNull
        @NotEmpty
        private String ur_id;

        @NotNull
        @NotEmpty
        private String addr_cd;

        @NotNull
        private Integer addr_no;

        @NotNull
        @NotEmpty
        private String commu_cd;

        @NotNull
        @NotEmpty
        private String commu_name;

        @NotNull
        @NotEmpty
        private String addr_name;

        @NotNull
        @NotEmpty
        private String title;

        @NotNull
        @NotEmpty
        private String contents;

        @NotNull
        @NotEmpty
        private String nick;

        private Timestamp r_date;
        private Timestamp m_date;


        private int like_cnt;


        private int view_cnt;
        private char ur_state;
        private char ad_state;
        private Timestamp first_date;
        private String first_id;
        private Timestamp last_date;
        private String last_id;
        private String img_full_rt;
        private Integer group_no;

        //추가
       
        private Integer comment_count;

        public CommunityBoardDto() {
        };

        public CommunityBoardDto(Integer no, String ur_id, String addr_cd, Integer addr_no, String commu_cd,String commu_name, String addr_name, String title, String contents, String nick, Timestamp r_date, Timestamp m_date, int like_cnt, int view_cnt, char ur_state, char ad_state, Timestamp first_date, String first_id, Timestamp last_date, String last_id,String img_full_rt, Integer group_no, String name,Integer comment_count) {
            this.no = no;
            this.ur_id = ur_id;
            this.addr_cd = addr_cd;
            this.addr_no = addr_no;
            this.commu_cd = commu_cd;
            this.commu_name = commu_name;
            this.addr_name = addr_name;
            this.title = title;
            this.contents = contents;
            this.nick = nick;
            this.r_date = r_date;
            this.m_date = m_date;
            this.like_cnt = like_cnt;
            this.view_cnt = view_cnt;
            this.ur_state = ur_state;
            this.ad_state = ad_state;
            this.first_date = first_date;
            this.first_id = first_id;
            this.last_date = last_date;
            this.last_id = last_id;
            this.img_full_rt = img_full_rt;
            this.group_no = group_no;
//
            this.comment_count = comment_count;
        }

        public CommunityBoardDto(String ur_id, String addr_cd, Integer addr_no, String commu_cd, String commu_name, String addr_name, String title, String contents, String nick, String img_full_rt, Integer group_no) {
            this.ur_id = ur_id;
            this.addr_cd = addr_cd;
            this.addr_no = addr_no;
            this.commu_cd = commu_cd;
            this.commu_name = commu_name;

            this.addr_name = addr_name;
            this.title = title;
            this.contents = contents;
            this.nick = nick;
            this.img_full_rt = img_full_rt;
            this.group_no = group_no;
        }


        public Integer getno() {
            return no;
        }

        public void setno(Integer no) {
            this.no = no;
        }

        public String getur_id() {
            return ur_id;
        }

        public void setur_id(String ur_id) {
            this.ur_id = ur_id;
        }

        public String getaddr_cd() {
            return addr_cd;
        }

        public void setaddr_cd(String addr_cd) {
            this.addr_cd = addr_cd;
        }

        public Integer getaddr_no() {
            return addr_no;
        }

        public void setaddr_no(Integer addr_no) {
            this.addr_no = addr_no;
        }

        public String getcommu_cd() {
            return commu_cd;
        }

        public void setcommu_cd(String commu_cd) {
            this.commu_cd = commu_cd;
        }

        public String getaddr_name() {
            return addr_name;
        }

        public void setaddr_name(String addr_name) {
            this.addr_name = addr_name;
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

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public Timestamp getR_date() {
            return r_date;
        }

        public void setR_date(Timestamp r_date) {
            this.r_date = r_date;
        }

        public Timestamp getM_date() {
            return m_date;
        }

        public void setM_date() {
            this.m_date = m_date;
        }

        public int getlike_cnt() {
            return like_cnt;
        }

        public void setlike_cnt(int like_cnt) {
            this.like_cnt = like_cnt;
        }

        public int getview_cnt() {
            return view_cnt;
        }

        public void setview_cnt(int view_cnt) {
            this.view_cnt = view_cnt;
        }

        public char getur_state() {
            return ur_state;
        }

        public void setur_state(char ur_state) {
            this.ur_state = ur_state;
        }

        public char getad_state() {
            return ad_state;
        }

        public void setad_state(char ad_state) {
            this.ad_state = ad_state;
        }

        public Timestamp getfirst_date() {
            return first_date;
        }

        public void setfirst_date(Timestamp first_date) {
            this.first_date = first_date;
        }

        public String getfirst_id() {
            return first_id;
        }

        public void setfirst_id(String first_id) {
            this.first_id = first_id;
        }

        public Timestamp getlast_date() {
            return last_date;
        }

        public void setlast_date(Timestamp last_date) {
            this.last_date = last_date;
        }

        public String getlast_id() {
            return last_id;
        }

        public void setlast_id(String last_id) {
            this.last_id = last_id;
        }

        //추가


        //추가


        public String getCommu_name() {
            return commu_name;
        }

        public void setCommu_name(String commu_name) {
            this.commu_name = commu_name;
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


    //추가


    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }


    @Override
        public String toString() {
            return "CommunityBoardDto{" +
                    "no=" + no +
                    ", ur_id='" + ur_id + '\'' +
                    ", addr_cd='" + addr_cd + '\'' +
                    ", addr_no=" + addr_no +
                    ", commu_cd='" + commu_cd + '\'' +
                    ", commu_name='" + commu_name + '\'' +
                    ", addr_name='" + addr_name + '\'' +
                    ", title='" + title + '\'' +
                    ", contents='" + contents + '\'' +
                    ", nick='" + nick + '\'' +
                    ", r_date=" + r_date +
                    ", m_date=" + m_date +
                    ", like_cnt=" + like_cnt +
                    ", view_cnt=" + view_cnt +
                    ", ur_state=" + ur_state +
                    ", ad_state=" + ad_state +
                    ", first_date=" + first_date +
                    ", first_id='" + first_id + '\'' +
                    ", last_date=" + last_date +
                    ", last_id='" + last_id + '\'' +
                    ", img_full_rt='" + img_full_rt + '\'' +
                    ", group_no=" + group_no +
                    ", comment_count=" + comment_count +
                    '}';
        }

    }
