# Exception Handling

In this version, we implemented policies to prevent students from exceeding their allowed credit hour limit and from registering for courses they are already enrolled in. We also made the program more robust by enabling it to ignore invalid data from the `students_updated.csv` file. Finally, the GUI was updated to display relevant error messages.

## What's New
- We incorporated two policies into the `Student` class. First, a student cannot register for a course they have already enrolled in. Second, a student cannot add a course if it would cause them to exceed their maximum credit hour limit (6 hours for graduate students and 9 hours for undergraduate students).
- We defined a static helper method `create()` within the `Student` class. This method takes in student information and returns an instance of the appropriate subclass, namely `GraduateStudent` or `UndergraduateStudent`.

## Compiling the Project
The `JSONUtility.java` file is intentionally not included. Can you think about why that might be?
