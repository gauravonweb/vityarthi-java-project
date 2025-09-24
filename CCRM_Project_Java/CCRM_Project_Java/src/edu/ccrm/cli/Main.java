package edu.ccrm.cli;

import edu.ccrm.service.*;
import edu.ccrm.domain.*;
import edu.ccrm.io.*;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        StudentService ss = new StudentService();
        CourseService cs = new CourseService();
        EnrollmentService es = new EnrollmentService();
        ImportExportService io = new ImportExportService();
        BackupService bs = new BackupService();
        var ds = DataStore.getInstance();

        // seed some data
        ss.addStudent("S1","REG001","abhinav","alice@example.com");
        ss.addStudent("S2","REG002","shiva","bob@example.com");
        cs.addCourse("C101","unity development",4,"Dr. X", Semester.FALL, "CS");
        cs.addCourse("C102","Data Structures and algorithm",4,"Dr. Y", Semester.FALL, "CS");

        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while(loop) {
            System.out.println("\n--- CCRM Menu ---");
            System.out.println("1>Add Student  2>List Students  3>Deactivate Student");
            System.out.println("4>Add Course 5>List Courses 6>Search Courses by Instructor");
            System.out.println("7>Enroll Student 8>Record Marks 9>Print Transcript");
            System.out.println("10>Import Sample CSVs 11>Export Data 12>Backup Exports");
            System.out.println("0>Exit");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());
            switch(choice) {
                case 1 -> { System.out.print("id: "); String id = sc.nextLine(); System.out.print("regNo: "); String reg = sc.nextLine(); System.out.print("name: "); String name = sc.nextLine(); System.out.print("email: "); String email = sc.nextLine(); ss.addStudent(id, reg, name, email); System.out.println("Added."); }
                case 2 -> { List<Student> students = ss.listStudents(); students.forEach(s -> System.out.println(s)); }
                case 3 -> { System.out.print("id to deactivate: "); String id = sc.nextLine(); ss.deactivate(id); System.out.println("Done."); }
                case 4 -> { System.out.print("code: "); String code=sc.nextLine(); System.out.print("title: "); String title=sc.nextLine(); System.out.print("credits: "); int cr=Integer.parseInt(sc.nextLine()); System.out.print("instr: "); String instr=sc.nextLine(); System.out.print("sem (SPRING/SUMMER/FALL): "); Semester sem=Semester.valueOf(sc.nextLine()); System.out.print("dept: "); String dept=sc.nextLine(); cs.addCourse(code,title,cr,instr,sem,dept); System.out.println("Course added."); }
                case 5 -> { cs.listCourses().forEach(c -> System.out.println(c)); }
                case 6 -> { System.out.print("instr: "); String instr=sc.nextLine(); cs.searchByInstructor(instr).forEach(System.out::println); }
                case 7 -> { System.out.print("studentId: "); String sid=sc.nextLine(); System.out.print("courseCode: "); String cc=sc.nextLine(); try { es.enroll(sid, cc); System.out.println("Enrolled"); } catch(Exception e) { System.out.println("Error: " + e.getMessage()); } }
                case 8 -> { System.out.print("studentId: "); String sid=sc.nextLine(); System.out.print("courseCode: "); String cc=sc.nextLine(); System.out.print("marks: "); double m=Double.parseDouble(sc.nextLine()); es.recordMarks(sid,cc,m); System.out.println("Recorded."); }
                case 9 -> { System.out.print("studentId: "); String sid=sc.nextLine(); double gpa = es.computeGPA(sid); System.out.println("GPA="+gpa); ds.students().get(sid).getEnrolledCourses().forEach(System.out::println); }
                case 10 -> { // import sample CSVs placed in test-data
                    var base = Paths.get(System.getProperty("user.dir"), "test-data"); io.importStudents(base.resolve("students.csv")); io.importCourses(base.resolve("courses.csv")); System.out.println("Imported sample CSVs."); }
                case 11 -> {
                    var out = Paths.get(System.getProperty("user.dir"), "exports"); io.exportAll(out); System.out.println("Exported to " + out);
                }
                case 12 -> {
                    var src = Paths.get(System.getProperty("user.dir"), "exports"); var baseBackup = Paths.get(System.getProperty("user.dir"), "backups"); var dest = bs.backupFolder(src, baseBackup); System.out.println("Backup created at " + dest); System.out.println("Backup size(bytes): " + bs.recursiveSize(dest));
                }
                case 0 -> { loop = false; System.out.println("Bye"); }
                default -> System.out.println("Unknown"); 
            }
        }

        // demonstrate labeled jump once (label + break)
        outer: for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (i==1 && j==1) break outer; // labeled break demonstrated
            }
        }
    }
}
