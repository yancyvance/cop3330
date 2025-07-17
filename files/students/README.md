# File Handling

In this version, the data storage structure was refactored: the original single data file was split into three distinct CSV files, separating concerns for students, courses, and enrollments. The GUI was modified to support dynamic insertion of new student and course records, as well as the ability to associate (register) a student with an existing course. All modifications made through the interface are now persisted by writing the updated state back to the corresponding CSV files.

## What's New
The UI files have been updated to support adding new information. Additionally, the data source for the `JTable` has been changed. They are no longer based on a 2D array of Strings. You don't need to focus on these classes, as they are beyond the scope of this course. However, feel free to explore them if you're interested:
- `BaseListModel.java`: An abstract class that will be used for the `JTable` in which the data is coming from a `BaseList`.
- `StudentListModel.java`: Extends the `BaseListModel` class, which is for a list of students.
- `CourseListModel.java`: Extends the `BaseListModel` class, which is for a list of courses.

## Compiling the Project
The files `CourseList.java` and `StudentList.java` are intentionally not included. Can you think about why that might be?
