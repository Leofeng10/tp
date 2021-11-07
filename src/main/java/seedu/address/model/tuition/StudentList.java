package seedu.address.model.tuition;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents all students who are in a tuition class.
 */
public class StudentList {
    private final ArrayList<String> students;

    /**
     * Constructor for student class.
     *
     * @param students The list of students.
     */
    public StudentList(List<String> students) {
        requireNonNull(students);
        this.students = (ArrayList<String>) students;
    }

    @Override
    public String toString() {
        return students.toString();
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentList // instanceof handles nulls
                && equalClasses(((StudentList) other).students)); // state check
    }

    /**
     * Returns true if both student lists are identical.
     *
     * @param otherStudents The list of students to compare to.
     * @return True if the student lists are equal and false otherwise.
     */
    public boolean equalClasses(ArrayList<String> otherStudents) {
        if (otherStudents.size() != this.students.size()) {
            return false;
        }
        for (int i = 0; i < students.size(); i++) {
            if (!otherStudents.get(i).equals(this.students.get(i))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    /**
     * Returns whether or not Student List is empty.
     * @returns true if there are no students in the list, false otherwise
     */
    public boolean isEmpty() {
        return students.isEmpty();
    }

    /**
     * Updates student name in this student list.
     *
     * @param oldName The original name of the student.
     * @param newName The updated name of the student.
     */
    public void changeStudentName(String oldName, String newName) {
        if (students.contains(oldName)) {
            students.remove(oldName);
            students.add(newName);
        }
    }
}
