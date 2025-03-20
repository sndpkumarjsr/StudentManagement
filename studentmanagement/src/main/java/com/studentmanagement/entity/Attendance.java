package com.studentmanagement.entity;


import jakarta.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ATTENDANCE_STATUS isPresent;
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Attendance() {
    }

    public Attendance(ATTENDANCE_STATUS isPresent, String remarks) {
        this.isPresent = isPresent;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ATTENDANCE_STATUS getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(ATTENDANCE_STATUS isPresent) {
        this.isPresent = isPresent;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
