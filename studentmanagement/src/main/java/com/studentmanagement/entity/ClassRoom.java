package com.studentmanagement.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClassRoom {
    @Id
    @GeneratedValue
    private Integer id;

    private String year;

    private char section;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String remarks;

    @ManyToMany
    @JoinTable(
            name = "classroom_student",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    public ClassRoom() {
    }

    public ClassRoom(String year, char section, Status status, String remarks) {
        this.year = year;
        this.section = section;
        this.status = status;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public char getSection() {
        return section;
    }

    public void setSection(char section) {
        this.section = section;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
