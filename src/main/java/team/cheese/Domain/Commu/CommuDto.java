package team.cheese.Domain.Commu;


import java.sql.Timestamp;


public class CommuDto {

    private String commuCd;
    private String name;
    private Timestamp firstDate;
    private String firstId;
    private Timestamp lastDate;
    private String lastId;



    public CommuDto(){};
    public CommuDto(String commuCd, String name, String firstId){
        this.commuCd = commuCd;
        this.name = name;
        this.firstId = firstId;
    }
    public CommuDto(String commuCd, String name, Timestamp firstDate, String firstId, Timestamp lastDate, String lastId) {
        this.commuCd = commuCd;
        this.name = name;
        this.firstDate = firstDate;
        this.firstId = firstId;
        this.lastDate = lastDate;
        this.lastId = lastId;
    }


    public String getCommuCd() {
        return commuCd;
    }

    public void setCommuCd(String commuCd) {
        this.commuCd = commuCd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "CommuDto{" +
                "commuCd='" + commuCd + '\'' +
                ", name='" + name + '\'' +
                ", firstDate=" + firstDate +
                ", firstId='" + firstId + '\'' +
                ", lastDate=" + lastDate +
                ", lastId='" + lastId + '\'' +
                '}';
    }
}
