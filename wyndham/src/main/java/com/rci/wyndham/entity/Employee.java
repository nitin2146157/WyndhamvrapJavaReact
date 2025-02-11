package com.rci.wyndham.entity;
 
import jakarta.persistence.*;
 
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @Column(name = "name", nullable = false)
    private String name;
 
    @Column(name = "salary")
    private double salary;
 
    // Constructors
    public Employee() {}
 
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
 
    // Getters and Setters
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getSalary() {
        return salary;
    }
 
    public void setSalary(double salary) {
        this.salary = salary;
    }
 
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}