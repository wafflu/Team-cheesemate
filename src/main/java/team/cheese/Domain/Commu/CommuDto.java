package team.cheese.Domain.Commu;


import java.sql.Timestamp;


public class CommuDto {

    private String commu_cd;
    private String name;
    private Timestamp first_date;
    private String first_id;
    private Timestamp last_date;
    private String last_id;



    public CommuDto(){};
    public CommuDto(String commu_cd, String name, String first_id){
        this.commu_cd = commu_cd;
        this.name = name;
        this.first_id = first_id;
    }
    public CommuDto(String commu_cd, String name, Timestamp first_date, String first_id, Timestamp last_date, String last_id) {
        this.commu_cd = commu_cd;
        this.name = name;
        this.first_date = first_date;
        this.first_id = first_id;
        this.last_date = last_date;
        this.last_id = last_id;
    }


    public String getcommu_cd() {
        return commu_cd;
    }

    public void setcommu_cd(String commu_cd) {
        this.commu_cd = commu_cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    @Override
    public String toString() {
        return "CommuDto{" +
                "commu_cd='" + commu_cd + '\'' +
                ", name='" + name + '\'' +
                ", first_date=" + first_date +
                ", first_id='" + first_id + '\'' +
                ", last_date=" + last_date +
                ", last_id='" + last_id + '\'' +
                '}';
    }
}
