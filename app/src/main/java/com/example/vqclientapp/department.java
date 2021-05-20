package com.example.vqclientapp;


import java.io.Serializable;

public class department implements Serializable {
    String name,descript,maxtokens,st,et,avgt;
    boolean verified;

    public String getMaxtokens() {
        return maxtokens;
    }

    public String getName() {
        return name;
    }

    public String getDescript() {
        return descript;
    }

    public String getSt() {
        return st;
    }

    public String getEt() {
        return et;
    }

    public String getAvgt() {
        return avgt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public void setMaxtokens(String maxtokens) {
        this.maxtokens = maxtokens;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public void setEt(String et) {
        this.et = et;
    }

    public void setAvgt(String avgt) {
        this.avgt = avgt;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
