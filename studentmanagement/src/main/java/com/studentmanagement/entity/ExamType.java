package com.studentmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ExamType {
    @Id
    @GeneratedValue
    private Integer id;

    private String examName;

    private double totalMarks;

    private double passMarks;

    private String description;

    @Column(updatable = false)
    private String CreatedBy;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false)
    private String modifiedBy;

    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    public ExamType() {
    }

    public ExamType(String examName, double totalMarks, double passMarks, String description) {
        this.examName = examName;
        this.totalMarks = totalMarks;
        this.passMarks = passMarks;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public double getPassMarks() {
        return passMarks;
    }

    public void setPassMarks(double passMarks) {
        this.passMarks = passMarks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
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
