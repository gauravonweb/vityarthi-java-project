package edu.ccrm.domain;

import java.util.Objects;

public class Course {
    private String code;
    private String title;
    private int credits;
    private String instructor;
    private Semester semester;
    private String department;
    private boolean active = true;

    public Course(String code, String title, int credits, String instructor, Semester semester, String department) {
        this.code = code; this.title = title; this.credits = credits; this.instructor = instructor;
        this.semester = semester; this.department = department;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
    public boolean isActive() { return active; }
    public void setActive(boolean a) { this.active = a; }

    @Override
    public String toString() {
        return String.format("Course[code=%s,title=%s,credits=%d,instructor=%s,semester=%s,dept=%s]",
            code,title,credits,instructor,semester,department);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course c = (Course)o;
        return Objects.equals(code, c.code);
    }
    @Override
    public int hashCode() { return Objects.hash(code); }
}
