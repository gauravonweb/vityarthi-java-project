package edu.ccrm.service;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;

import java.util.*;

public class DataStore {
    private static DataStore instance;
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();
    private List<Enrollment> enrollments = new ArrayList<>();

    private DataStore(){}

    public static DataStore getInstance() {
        if (instance == null) instance = new DataStore();
        return instance;
    }

    public Map<String, Student> students() { return students; }
    public Map<String, Course> courses() { return courses; }
    public List<Enrollment> enrollments() { return enrollments; }
}
