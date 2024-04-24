package team.cheese.Domain.Post;

import java.math.BigInteger;
import java.sql.Timestamp;


public class PostDto {
    private long sn;
    private String urId;
    private String addrCd;
    private long no;
    private String commuCd;
    private String addrName;
    private String title;
    private String contents;
    private String nick;
    private Timestamp r_date;
    private Timestamp m_date;
    private int likeCnt;
    private int viewCnt;
    private char urState;
    private char adState;
    private Timestamp firstDate;
    private String firstId;
    private Timestamp lastDate;
    private String lastId;

    public PostDto(){};

    public PostDto(long sn, String urId, String addrCd, long no, String commuCd, String addrName, String title, String contents, String nick, Timestamp r_date, Timestamp m_date, int likeCnt, int viewCnt, char urState, char adState, Timestamp firstDate, String firstId, Timestamp lastDate, String lastId) {
        this.sn = sn;
        this.urId = urId;
        this.addrCd = addrCd;
        this.no = no;
        this.commuCd = commuCd;
        this.addrName = addrName;
        this.title = title;
        this.contents = contents;
        this.nick = nick;
        this.r_date = r_date;
        this.m_date = m_date;
        this.likeCnt = likeCnt;
        this.viewCnt = viewCnt;
        this.urState = urState;
        this.adState = adState;
        this.firstDate = firstDate;
        this.firstId = firstId;
        this.lastDate = lastDate;
        this.lastId = lastId;
    }

    public PostDto(String urId,String addrCd,long no, String commuCd, String addrName, String title, String contents, String nick){
        this.urId = urId;
        this.addrCd = addrCd;
        this.no = no;
        this.commuCd = commuCd;
        this.addrName = addrName;
        this.title = title;
        this.contents = contents;
        this.nick = nick;
    }


    public long getSn() {
        return sn;
    }

    public void setSn(long sn) {
        this.sn = sn;
    }

    public String getUrId() {
        return urId;
    }

    public void setUrId(String urId) {
        this.urId = urId;
    }

    public String getAddrCd() {
        return addrCd;
    }

    public void setAddrCd(String addrCd) {
        this.addrCd = addrCd;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getCommuCd() {
        return commuCd;
    }

    public void setCommuCd(String commuCd) {
        this.commuCd = commuCd;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
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

    public void setM_date(Timestamp m_date) {
        this.m_date = m_date;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public char getUrState() {
        return urState;
    }

    public void setUrState(char urState) {
        this.urState = urState;
    }

    public char getAdState() {
        return adState;
    }

    public void setAdState(char adState) {
        this.adState = adState;
    }

    public Timestamp getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Timestamp firstDate) {
        this.firstDate = firstDate;
    }

    public String getFirstId() {
        return firstId;
    }

    public void setFirstId(String firstId) {
        this.firstId = firstId;
    }

    public Timestamp getLastDate() {
        return lastDate;
    }

    public void setLastDate(Timestamp lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "sn=" + sn +
                ", urId='" + urId + '\'' +
                ", addrCd='" + addrCd + '\'' +
                ", no=" + no +
                ", commuCd='" + commuCd + '\'' +
                ", addrName='" + addrName + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", nick='" + nick + '\'' +
                ", r_date=" + r_date +
                ", m_date=" + m_date +
                ", likeCnt=" + likeCnt +
                ", viewCnt=" + viewCnt +
                ", urState=" + urState +
                ", adState=" + adState +
                ", firstDate=" + firstDate +
                ", firstId='" + firstId + '\'' +
                ", lastDate=" + lastDate +
                ", lastId='" + lastId + '\'' +
                '}';
    }

}
