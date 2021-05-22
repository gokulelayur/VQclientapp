package com.example.vqclientapp;


import java.io.Serializable;

public class department implements Serializable {
    String name, descript, st, et;
    int avgt;
    int maxtokens;
    int currenttoken;
    int nextavailabletoken;
    boolean verified;
    boolean activenow;

    public department() {
    }

    public department(String name, String descript, int maxtokens, String st, String et, int avgt) {
        this.activenow = false;
        this.currenttoken = -1;
        this.nextavailabletoken = -1;
        this.name = name;
        this.descript = descript;
        this.st = st;
        this.et = et;
        this.avgt = avgt;
        this.maxtokens = maxtokens;
    }

    public int getMaxtokens() {
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

    public int getAvgt() {
        return avgt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public void setMaxtokens(int maxtokens) {
        this.maxtokens = maxtokens;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public void setEt(String et) {
        this.et = et;
    }

    public void setAvgt(int avgt) {
        this.avgt = avgt;
    }

    public int getCurrenttoken() {
        return currenttoken;
    }

    public void setCurrenttoken(int currenttoken) {
        this.currenttoken = currenttoken;
    }

    public int getNextavailabletoken() {
        return nextavailabletoken;
    }

    public void setNextavailabletoken(int nextavailabletoken) {
        this.nextavailabletoken = nextavailabletoken;
    }

    public boolean isVerified() {
        return verified;
    }

    public boolean isActivenow() {
        return activenow;
    }

    public void setActivenow(boolean activenow) {
        this.activenow = activenow;
    }
}
