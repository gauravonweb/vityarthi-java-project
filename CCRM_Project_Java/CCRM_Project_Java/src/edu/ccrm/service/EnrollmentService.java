package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Semester;

import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentService {
    private DataStore ds = DataStore.getInstance();
    private static final int MAX_CREDITS = 18;

    public Enrollment enroll(String studentId, String courseCode) throws Exception {
        Student s = ds.students().get(studentId);
        Course c = ds.courses().get(courseCode);
        if (s == null) throw new Exception("Student not found");
        if (c == null) throw new Exception("Course not found");

        // compute current credits in same semester
        int current = ds.enrollments().stream()
            .filter(e -> e.getStudentId().equals(studentId))
            .map(e -> ds.courses().get(e.getCourseCode()))
            .filter(course -> course != null && course.getSemester() == c.getSemester())
            .mapToInt(Course::getCredits).sum();

        if (current + c.getCredits() > MAX_CREDITS) throw new Exception("Max credits per semester exceeded");

        Enrollment e = new Enrollment(studentId, courseCode);
        ds.enrollments().add(e);
        s.enroll(e);
        return e;
    }

    public void unenroll(String studentId, String courseCode) {
        List<Enrollment> list = ds.enrollments().stream()
            .filter(e -> e.getStudentId().equals(studentId) && e.getCourseCode().equals(courseCode))
            .collect(Collectors.toList());
        list.forEach(e -> { ds.enrollments().remove(e); Student s = ds.students().get(studentId); if (s!=null) s.unenroll(e); });
    }

    public void recordMarks(String studentId, String courseCode, double marks) {
        ds.enrollments().stream().filter(e -> e.getStudentId().equals(studentId) && e.getCourseCode().equals(courseCode))
            .findFirst().ifPresent(e -> e.setMarks(marks));
    }

    public double computeGPA(String studentId) {
        List<Enrollment> list = ds.enrollments().stream().filter(e -> e.getStudentId().equals(studentId)).collect(Collectors.toList());
        if (list.isEmpty()) return 0.0;
        int totalCredits = list.stream().mapToInt(e -> ds.courses().get(e.getCourseCode()).getCredits()).sum();
        int weightedPoints = list.stream().mapToInt(e -> {
            Grade g = e.getGrade().orElse(Grade.I);
            int credits = ds.courses().get(e.getCourseCode()).getCredits();
            return g.getPoints() * credits;
        }).sum();
        if (totalCredits == 0) return 0.0;
        return (double)weightedPoints / totalCredits;
    }
}
