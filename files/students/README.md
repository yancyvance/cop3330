# File Handling

In this version, the data storage structure was refactored: the original single data file was split into three distinct CSV files, separating concerns for students, courses, and enrollments. The GUI was modified to support dynamic insertion of new student and course records, as well as the ability to associate (register) a student with an existing course. All modifications made through the interface are now persisted by writing the updated state back to the corresponding CSV files.


## Compiling the Project
The files `CourseList.java` and `StudentList.java` are intentionally not included. Can you think about why that might be?
