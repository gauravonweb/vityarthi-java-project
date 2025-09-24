package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;

import java.util.List;
import java.util.stream.Collectors;

public class CourseService {
    private DataStore ds = DataStore.getInstance();

    public Course addCourse(String code, String title, int credits, String instructor, Semester sem, String dept) {
        Course c = new Course(code,title,credits,instructor,sem,dept);
        ds.courses().put(code, c);
        return c;
    }

    public List<Course> listCourses() { return ds.courses().values().stream().collect(Collectors.toList()); }

    public List<Course> searchByInstructor(String instructor) {
        return ds.courses().values().stream().filter(c -> c.getInstructor().equalsIgnoreCase(instructor)).collect(Collectors.toList());
    }

    public List<Course> filterByDepartment(String dept) {
        return ds.courses().values().stream().filter(c -> c.getDepartment().equalsIgnoreCase(dept)).collect(Collectors.toList());
    }

    public List<Course> filterBySemester(Semester sem) {
        return ds.courses().values().stream().filter(c -> c.getSemester() == sem).collect(Collectors.toList());
    }

    public Course find(String code) { return ds.courses().get(code); }

    public void deactivate(String code) {
        Course c = ds.courses().get(code);
        if (c != null) c.setActive(false);
    }
}
