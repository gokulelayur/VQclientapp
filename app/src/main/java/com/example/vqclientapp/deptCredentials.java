package com.example.vqclientapp;

public class deptCredentials {

    String deptUsername,password,deptID,companyID;

    public deptCredentials() {
    }

    public deptCredentials(String deptUsername, String password, String deptID, String companyID) {
        this.deptUsername = deptUsername;
        this.password = password;
        this.deptID = deptID;
        this.companyID = companyID;
    }

    public String getDeptUsername() {
        return deptUsername;
    }

    public void setDeptUsername(String deptUsername) {
        this.deptUsername = deptUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
