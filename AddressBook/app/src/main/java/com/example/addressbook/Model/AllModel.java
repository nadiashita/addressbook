package com.example.addressbook.Model;

import java.util.List;

public class AllModel {
    private int statusCode;
    private Integer employeeId;
    private Long nim;
    private String nama;
    private String credits;
    private List<EmpModel> employees;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Long getNim() {
        return nim;
    }

    public void setNim(Long nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public List<EmpModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmpModel> employees) {
        this.employees = employees;
    }
}
