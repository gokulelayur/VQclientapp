package com.example.vqclientapp;

public class signuphelper {

String uname,cat,company,desc,mapLink,mobNo;

    public signuphelper() {
    }

    public signuphelper(String uname, String cat, String company, String desc, String mapLink, String mobNo) {
        this.uname = uname;
        this.cat = cat;
        this.company = company;
        this.desc = desc;
        this.mapLink = mapLink;
        this.mobNo = mobNo;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
}
