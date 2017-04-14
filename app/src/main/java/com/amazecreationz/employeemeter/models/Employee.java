package com.amazecreationz.employeemeter.models;

import com.amazecreationz.employeemeter.services.StringService;

import java.util.UUID;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 14/4/17 4:44 PM.
 */

public class Employee {
    public  String name;
    public  String id;
    public  String designation;
    public  int salaryPerDay;

    public Employee(String id) {
        this.id = id;
    }

    public Employee(String name, String designation, int salaryPerDay) {
        this.name = StringService.getSentenceCase(name);
        this.id = getNewId();
        this.designation = StringService.getSentenceCase(designation);
        this.salaryPerDay = salaryPerDay;
    }

    public Employee(String name, String id, String designation, int salaryPerDay) {
        this.name = StringService.getSentenceCase(name);
        this.id = id;
        this.designation = StringService.getSentenceCase(designation);
        this.salaryPerDay = salaryPerDay;
    }

    private String getNewId() {
        return UUID.randomUUID().toString();
    }
}
