package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends Person {
    public enum Status { ACTIVE, INACTIVE }

    private String regNo;
    private Status status = Status.ACTIVE;
    private LocalDateTime createdAt;
    private List<Enrollment> enrolledCourses = new ArrayList<>();

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.createdAt = LocalDateTime.now();
    }

    public String getRegNo() { return regNo; }
    public Status getStatus() { return status; }
    public void setStatus(Status s) { this.status = s; }
    public List<Enrollment> getEnrolledCourses() { return enrolledCourses; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void enroll(Enrollment e) { enrolledCourses.add(e); }
    public void unenroll(Enrollment e) { enrolledCourses.remove(e); }

    @Override
    public String profile() {
        return String.format("Student[id=%s, regNo=%s, name=%s, email=%s, status=%s, created=%s]",
            id, regNo, fullName, email, status, createdAt);
    }

    @Override
    public String toString() {
        return profile();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student)o;
        return Objects.equals(id, s.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}
