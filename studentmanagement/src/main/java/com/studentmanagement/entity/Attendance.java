package com.studentmanagement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @JsonBackReference
    private Student student;

    private LocalDate date;

    @Column(updatable = false)
    private String createdBy;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Column(insertable = false)
    private String modifiedBy;
    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    public Attendance() {
    }

    public Attendance(ATTENDANCE_STATUS isPresent, String remarks, LocalDate date) {
        this.isPresent = isPresent;
        this.remarks = remarks;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
