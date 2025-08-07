package com.example.baoguan.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Baoguan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String day;
    private String boatname;
    private String number;
//    @Column(nullable = true)
//    private String remark; // 備註欄位


    public Baoguan() {}

    // 建構子
    public Baoguan(String day, String number, String boatname) {
        this.day = day;
        this.number = number;
        this.boatname = boatname;
//        this.remark = remark;
    }

    // Getter & Setter
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() { return id; }
    
//    public String getRemark() {
//        return remark;
//    }

//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
    
    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    
    public String getBoatname() { return boatname; }
    public void setBoatname(String boatname) { this.boatname = boatname; }
}
