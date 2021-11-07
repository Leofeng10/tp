package seedu.address.model.tuition;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.Nameable;
import seedu.address.model.student.Remark;
import seedu.address.model.student.Student;

/**
 * Represents a tuition class in TutAssistor.
 */
public class TuitionClass implements Nameable {
    /** Most recently viewed tuition class */
    private static TuitionClass mostRecent;

    private final ClassName name;
    private final ClassLimit limit;
    private final Timeslot timeslot;
    private StudentList studentList;
    private final Remark remark;
    private int id;

    /**
     * Constructor for Tuition Class.
     *
     * @param name The name of the tuition class.
     * @param limit The maximum number of students allowed.
     * @param timeslot The date and time of the tuition.
     * @param studentList The list of students attending the tuition.
     * @param remark Any remarks noted for the tuition class.
     */
    public TuitionClass(ClassName name, ClassLimit limit, Timeslot timeslot, StudentList studentList,
                        Remark remark) {
        this.name = name;
        this.limit = limit;
        this.timeslot = timeslot;
        this.studentList = studentList;
        this.remark = remark;
        this.id = this.hashCode();
        mostRecent = this;
    }

    /**
     * Constructor for Tuition Class used in reading data.
     *
     * @param name The name of the tuition class.
     * @param limit The maximum number of students allowed.
     * @param timeslot The date and time of the tuition.
     * @param studentList The list of students attending the tuition.
     * @param id the auto generated unique id for each tuition class
     */
    public TuitionClass(ClassName name, ClassLimit limit, Timeslot timeslot,
                        StudentList studentList, Remark remark, int id) {
        this.name = name;
        this.limit = limit;
        this.timeslot = timeslot;
        this.studentList = studentList;
        this.remark = remark;
        this.id = id;
        mostRecent = this;
    }

    public ClassName getName() {
        return name;
    }

    public ClassLimit getLimit() {
        return limit;
    }


    public Timeslot getTimeslot() {
        return timeslot;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public Remark getRemark() {
        return remark;
    }

    public int getStudentCount() {
        return studentList.getStudents().size();
    }

    public boolean isFull() {
        return this.getStudentCount() == this.limit.getLimit();
    }

    public int getId() {
        return id;
    }

    /**
     * determine whether this tuition happens today
     * @param weekday
     * @return
     */
    public boolean matchTheDay(int weekday) {
        return weekday == timeslot.getDay().getDay();
    }

    @Override
    public String getNameString() {
        return name.getName();
    }

    /**
     * Returns true if both Tuition have the same time slot.
     * This defines a weaker notion of equality between two tuition classes.
     */
    public boolean isSameTuition(TuitionClass otherTuition) {
        return this.equals(otherTuition);
    }

    //addn/John Doe p/98765432 e/johnd@example.com a/John street, block 123
    //addclass n/cs2100 l/10 c/4 ts/Mon 13:00-14:00 s/John Doe

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Student)) {
            return false;
        }
        TuitionClass otherClass = (TuitionClass) other;
        return otherClass.getTimeslot().equals(getTimeslot());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, limit, timeslot, studentList, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("; Limit: ")
                .append(getLimit())
                .append(" Timeslot: ")
                .append(getTimeslot())
                .append("; Students: ")
                .append(getStudentList())
                .append("; Remark: ")
                .append(getRemark());
        return builder.toString();
    }

    /**
     * Changes the students in this class to the students in the input.
     * @param students a list of students names to be added.
     * @return this TuitionClass
     */
    public TuitionClass changeStudents(ArrayList<String> students) {
        this.studentList = new StudentList(students);
        return this;
    }

    /**
     * Return updated Tuition class after removing student.
     *
     * @param student the student to be removed.
     * @return Updated tuition class.
     */
    public TuitionClass removeStudent(Student student) {
        this.studentList.getStudents().remove(student.getName().fullName);
        return this;
    }

    /**
     * Updates the name of a student in the tuition class.
     *
     * @param student The current student in the class.
     * @param updatedStudent The updated student.
     */
    public void updateStudent(Student student, Student updatedStudent) {
        this.studentList.changeStudentName(student.getNameString(), updatedStudent.getNameString());
    }

    /**
     * Returns true if the student is enrolled in the tuition class and false otherwise.
     *
     * @param student The student to be checked.
     * @return A boolean true if the student is in the class, false otherwise.
     */
    public boolean containsStudent(Student student) {
        return this.studentList.getStudents().contains(student.getName().fullName);
    }

    /**
     * Returns a tuition class after adding a new student to this class, if the student is not already in the class.
     *
     * @param student Student to be added.
     * @return The tuition class after adding the student.
     */
    public TuitionClass addStudent(Student student) {
        ArrayList<String> nowStudents = this.studentList.getStudents();
        String name = student.getName().fullName;
        for (String s: nowStudents) {
            if (s.equals(name)) {
                return null;
            }
        }
        nowStudents.add(student.getName().fullName);
        return this;
    }

    /**
     * Convert students from an arraylist to a string to be displayed in UI.
     * @return a string of all the student names combined into a list.
     */
    public String listStudents() {
        String studentString = "";
        if (this.studentList.isEmpty()) {
            studentString = "No student yet.";
            return studentString;
        }
        String lastStudent = studentList.getStudents().get(studentList.getStudents().size() - 1);
        for (String name: studentList.getStudents()) {
            studentString += name;
            if (!name.equals(lastStudent)) {
                studentString += "\n";
            }
        }
        return studentString;
    }

    /**
     * Sets most recently viewed tuition class to a given TuitionClass.
     * @param tuitionClass Tuition Class to set as most recently looked at.
     */
    public static void setMostRecentTo(TuitionClass tuitionClass) {
        mostRecent = tuitionClass;
    }

    /**
     * Returns the most recently viewed tuition class
     * @return most recently viewed tuition class.
     */
    public static TuitionClass getMostRecent() {
        return mostRecent;
    }

    /**
     * Returns true if the limit, timeslot and names of two classes are identical.
     *
     * @param editedClass The class to compare to.
     * @return boolean true if the limit, timeslot and names of two classes match, false otherwise.
     */
    public boolean sameClassDetails(TuitionClass editedClass) {
        return editedClass.getTimeslot().equals(timeslot)
                && editedClass.getName().equals(name)
                && editedClass.getLimit().equals(this.getLimit());
    }
}

