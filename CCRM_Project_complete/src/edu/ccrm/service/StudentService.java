package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private DataStore ds = DataStore.getInstance();

    public Student addStudent(String id, String regNo, String name, String email) {
        Student s = new Student(id, regNo, name, email);
        ds.students().put(id, s);
        return s;
    }

    public List<Student> listStudents() {
        return ds.students().values().stream().collect(Collectors.toList());
    }

    public Student find(String id) { return ds.students().get(id); }

    public void deactivate(String id) {
        Student s = ds.students().get(id);
        if (s != null) s.setStatus(Student.Status.INACTIVE);
    }
}
