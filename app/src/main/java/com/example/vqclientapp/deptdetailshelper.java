package com.example.vqclientapp;

public class deptdetailshelper {
    String name,descript,maxtokens,st,et,avgt;

    public deptdetailshelper() {
    }

    public deptdetailshelper(String name, String descript, String maxtokens, String st, String et, String avgt) {
        this.name = name;
        this.descript = descript;
        this.maxtokens = maxtokens;
        this.st = st;
        this.et = et;
        this.avgt = avgt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getMaxtokens() {
        return maxtokens;
    }

    public void setMaxtokens(String maxtokens) {
        this.maxtokens = maxtokens;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getEt() {
        return et;
    }

    public void setEt(String et) {
        this.et = et;
    }

    public String getAvgt() {
        return avgt;
    }

    public void setAvgt(String avgt) {
        this.avgt = avgt;
    }
}
