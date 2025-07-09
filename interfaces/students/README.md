# Interface

This version introduces a new abstract class, `BaseList`, which acts as a generic container for storing multiple objects. Unlike the version used in the homework, this `BaseList` also supports sorting its elements.

## What's New
The following files obtained from the polymorphism version were updated:
- `Course.java`: We defined the criteria to use when comparing two Course objects. We also implemented the Searchable interface.
- `Student.java`: We defined the comparison criteria applicable to any object that is an instance of a Student subclass. We also implemented the Searchable interface.
- `GraduateStudent.java`: We extended the definition of isEqual() inherited from Student to account for the thesis topic.
- `UndergraduateStudent.java`: We extended the definition of isEqual() inherited from Student to account for the year level.

## Project Structure
To test the application, we started organizing our code into the following:
- `UI.java`: A generic text-based user interface that will display on the screen the contents of a `BaseList` list.
- `SimpleController.java`: The main controller of the project, which prepares the data used by the UI.
- `MainApp.java`: The entry point of the project.

## Compiling the Project
The files `CourseList.java` and `StudentList.java` are intentionally not included. Can you think about why that might be?
