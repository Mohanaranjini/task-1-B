package com.example.task.B;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String position;
    private String department;

    public int id() {
        return id;
    }

    public Employee setId(int id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String position() {
        return position;
    }

    public Employee setPosition(String position) {
        this.position = position;
        return this;
    }

    public String department() {
        return department;
    }

    public Employee setDepartment(String department) {
        this.department = department;
        return this;
    }
}
