# Campus Course & Records Manager (CCRM)

This project is a working Java SE for the CCRM assignment. It implements core domain classes, services, a CLI menu, CSV import/export, and a backup utility (NIO.2). It demonstrates many syllabus requirements and includes additional utilities and notes.

## How to install java on windows
To start coding in Java, you first need to install the Java Development Kit (JDK) on your system.

Step 1: Visit the official Oracle website https://www.oracle.com/in/java/. Click on the "Download Java" icon as shown in the below image.

Step 2: Now you can see the latest version is JDK 25 and there are options for Linux, macOS, and Windows. Click on the Windows option and then click the x64 Installer option to download the .exe file for 64-bit Windows OS.
<img width="962" height="437" alt="image" src="https://github.com/user-attachments/assets/cdc62016-14a2-4117-a480-0b3892d0152d" />


 

Step 3: Go to Downloads and double-click on that downloaded jdk-23_windows-x64_bin.exe file. So now Java Installation Wizard get open and then click Next button.

Step 4: Now we will set the environment variables for Windows OS. Open the C drive, go to Program Files > Java > jdk-25 (or your installed version) > bin folder. Now, copy the path and we will use this path when configuring the environment variables.
<img width="795" height="120" alt="image" src="https://github.com/user-attachments/assets/2fd8abc5-a76f-4973-9327-da8c97f1a5bc" />

 

Search for environment variable on your system, then click on the Environment Variables button.
<img width="563" height="416" alt="image" src="https://github.com/user-attachments/assets/98ebfd00-f56e-4727-95fb-4fc7d9793e1b" />

 

In the system variable section, select the path variable and then click the option Edit.
<img width="478" height="531" alt="image" src="https://github.com/user-attachments/assets/b7c73365-4be6-4e3e-9785-2ae0bf2a4356" />


 
Paste the path that copied earlier, and click OK to save the changes.

Step 5: Verify the Installation. Open command prompt and type the below command to verify the Java version that is installed in the system.
Cmd: java --version
<img width="739" height="316" alt="image" src="https://github.com/user-attachments/assets/8492ad37-cee0-41f0-b2fc-f1a546064c5a" />

 


## How to compile & run

step 1: open elcipseIDE or IntelliJ
Step 2: click on open project
<img width="499" height="410" alt="image" src="https://github.com/user-attachments/assets/6705513b-aa84-483f-bbcc-cbe26106f1f6" />

 




Step 3: select the ccrm project folder and click on ok.
<img width="538" height="551" alt="image" src="https://github.com/user-attachments/assets/9860cb0f-ac54-450d-9c6d-9e927a096e01" />

 

Step 4: navigate to edu.ccrm > cli > MainMenu, then double click on it.

Step 5: right click anywhere on the code then choose run “mainmenu.main()”.
<img width="635" height="491" alt="image" src="https://github.com/user-attachments/assets/000993c8-b709-4a25-8086-2490e88fc9be" />

 

result
<img width="962" height="209" alt="image" src="https://github.com/user-attachments/assets/d1f849c6-3950-4edb-b9f9-ec430321329d" />


 


(see USAGE.md for detailed instructions)

## Java evolution (short timeline)
- 1995: Java 1.0 — "Write once, run anywhere" motto, basic language and libraries.
- 1998: Java 2 (J2SE) — major API additions (collections).
- 2004: Java 5 — generics, annotations, enhanced for-loop, enums.
- 2014: Java 8 — lambdas and Streams (important for this project).
- 2017-2021+: Java 9-17 — modularity (JPMS), local-variable type inference (var), performance improvements.
- 2021-2024+: Ongoing: records, pattern matching, performance and API enhancements.

## Java ME vs Java SE vs Java EE (Jakarta EE)
- **Java ME**: Micro Edition for embedded and constrained devices (IoT, phones historically).
- **Java SE**: Standard Edition — desktop and client/server apps, core libraries, what this project uses.
- **Java EE / Jakarta EE**: Enterprise Edition — servlets, JPA, EJBs, for large server-side enterprise applications.

## JDK vs JRE vs JVM
- **JVM**: Java Virtual Machine — runs compiled bytecode.
- **JRE**: Java Runtime Environment — JVM + standard class libraries to run Java apps.
- **JDK**: Java Development Kit — JRE + compiler (`javac`) and development tools.

## Mapping table (syllabus topic -> file/class demonstrating it)
- Encapsulation, getters/setters: `edu.ccrm.domain.Person`, `Student`, `Course`.
- Inheritance & Abstraction: `edu.ccrm.domain.Person` (abstract) -> `Student`, `Instructor`.
- Polymorphism & toString(): `Person#toString`, overrides in `Student`, `Instructor`.
- Enums (Semester, Grade): `edu.ccrm.domain.Semester`, `Grade`.
- Streams & filtering: `edu.ccrm.service.CourseService`, `StudentService`.
- NIO.2 File I/O: `edu.ccrm.io.ImportExportService`.
- Singleton pattern: `edu.ccrm.config.AppConfig`.
- Builder pattern: `edu.ccrm.domain.Course.Builder`.
- Custom Exceptions: `edu.ccrm.domain.DuplicateEnrollmentException`, `MaxCreditLimitExceededException`.
- Assertions: `edu.ccrm.service.EnrollmentService` (enable at runtime with `-ea`).
- Immutable class: `edu.ccrm.util.ImmutableName`.
- Nested classes: `edu.ccrm.domain.NestedDemo` (static nested + inner).
- Interfaces: `edu.ccrm.util.Persistable`, `edu.ccrm.util.Searchable`.
- Anonymous inner class: `edu.ccrm.cli.MainMenu` (Runnable callback).
- Lambdas: used in service filtering and streams across services.

## Enabling assertions
Run the JVM with `-ea` to enable assertions. Example:
```
java -ea -cp out edu.ccrm.cli.MainMenu
```

##Acknowledment
References used: geeksforgeeks, javatpoint.


