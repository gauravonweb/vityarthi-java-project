package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.Optional;

public class Enrollment {
    private String studentId;
    private String courseCode;
    private LocalDateTime enrolledAt;
    private Double marks; // 0-100
    private Grade grade;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.enrolledAt = LocalDateTime.now();
    }

    public String getStudentId(){ return studentId; }
    public String getCourseCode(){ return courseCode; }
    public LocalDateTime getEnrolledAt(){ return enrolledAt; }

    public Optional<Double> getMarks(){ return marks == null ? Optional.empty() : Optional.of(marks); }
    public void setMarks(double m) { this.marks = m; this.grade = Grade.fromPercentage(m); }
    public Optional<Grade> getGrade(){ return grade == null ? Optional.empty() : Optional.of(grade); }

    @Override
    public String toString() {
        return String.format("Enrollment[student=%s, course=%s, at=%s, marks=%s, grade=%s]",
            studentId, courseCode, enrolledAt, marks, grade);
    }
}
