package com.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private int age;
    private LocalDate dateOfBirth;

    @Column(length = 15,unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate dateOfjoining;
    private LocalDateTime lastLogin;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    private String modifiedBy;

    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    // Bidirectional one-to-many relationship (School -> Student)
    @ManyToOne
    @JoinColumn(name = "school_id")
    @JsonBackReference
    private School school;

    // Bidirectional one-to-one relationship (Guardian -> Student)
    @ManyToOne
    @JoinColumn(name = "guardian_id")
    @JsonBackReference
    private Guardian guardian;

    // One-to-many relationship with ExamResult
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ExamResult> examResults;

    // Many-to-many relationship with ClassRoom
    @ManyToMany(mappedBy = "students")
    @JsonManagedReference
    private List<ClassRoom> classRooms;

    // One-to-many relationship with Attendance
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Attendance> attendances;

    @Column(unique = true, updatable = false)
    private String admissionNumber;

    private static int sequenceCounter = 1;


    public Student() {
    }

    public Student(String firstName, String lastName, String email, String password, int age, LocalDate dateOfBirth, String phone, LocalDate dateOfjoining) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.dateOfjoining = dateOfjoining;
    }

    @PrePersist
    public void generateAdmissionNumber() {
        int year = LocalDate.now().getYear();
        synchronized (Student.class) {
            this.admissionNumber = "ADM-" + year + "-" + String.format("%04d", sequenceCounter++);
        }
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfjoining() {
        return dateOfjoining;
    }

    public void setDateOfjoining(LocalDate dateOfjoining) {
        this.dateOfjoining = dateOfjoining;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public List<ExamResult> getExamResults() {
        return examResults;
    }

    public void setExamResults(List<ExamResult> examResults) {
        this.examResults = examResults;
    }

    public List<ClassRoom> getClassRooms() {
        return classRooms;
    }

    public void setClassRooms(List<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }
}
