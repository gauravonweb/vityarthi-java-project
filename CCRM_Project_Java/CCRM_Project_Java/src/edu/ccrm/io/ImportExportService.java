package edu.ccrm.io;

import edu.ccrm.service.DataStore;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class ImportExportService {
    private DataStore ds = DataStore.getInstance();

    public void importStudents(Path csv) throws IOException {
        List<String> lines = Files.readAllLines(csv);
        for (String l : lines.subList(1, lines.size())) { // skip header
            if (l.trim().isEmpty()) continue;
            String[] parts = l.split(",");
            String id = parts[0].trim(), reg=parts[1].trim(), name=parts[2].trim(), email=parts[3].trim();
            ds.students().put(id, new Student(id, reg, name, email));
        }
    }

    public void importCourses(Path csv) throws IOException {
        List<String> lines = Files.readAllLines(csv);
        for (String l : lines.subList(1, lines.size())) {
            if (l.trim().isEmpty()) continue;
            String[] p = l.split(",");
            String code=p[0].trim(), title=p[1].trim(), credits=p[2].trim(), instr=p[3].trim(), sem=p[4].trim(), dept=p[5].trim();
            ds.courses().put(code, new Course(code,title,Integer.parseInt(credits),instr,Semester.valueOf(sem),dept));
        }
    }

    public void exportAll(Path folder) throws IOException {
        Files.createDirectories(folder);
        Path students = folder.resolve("students_export.csv");
        String sHeader = "id,regNo,fullName,email,status,createdAt\n" +
            ds.students().values().stream().map(st -> String.format("%s,%s,%s,%s,%s, %s", st.getId(), st.getRegNo(), st.getFullName(), st.getEmail(), st.getStatus(), st.getCreatedAt())).collect(Collectors.joining("\n"));
        Files.writeString(students, sHeader);

        Path courses = folder.resolve("courses_export.csv");
        String cHeader = "code,title,credits,instructor,semester,department\n" +
            ds.courses().values().stream().map(c -> String.format("%s,%s,%d,%s,%s,%s", c.getCode(), c.getTitle(), c.getCredits(), c.getInstructor(), c.getSemester(), c.getDepartment())).collect(Collectors.joining("\n"));
        Files.writeString(courses, cHeader);
    }
}
