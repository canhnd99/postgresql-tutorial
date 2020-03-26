/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author sunflower
 */
public class Student {
    private Integer id;
    private String studentId;
    private String fullName;
    private String address;
    private Integer age;

    public Student() {
    }

    public Student(Integer id, String studentId, String fullName, String address, Integer age) {
        this.id = id;
        this.studentId = studentId;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
    }
    
    public Student(String studentId, String fullName, String address, Integer age) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Object[] toObject(){
        return new Object[]{id, studentId, fullName, address, age};
    }
}
