package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.model.tuition.UniqueTuitionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private static final Logger LOGGER = LogsCenter.getLogger(AddressBook.class);

    private final UniqueStudentList students;
    private final UniqueTuitionList tuitions;
    private SortCommandParser.Order order;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        tuitions = new UniqueTuitionList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Students in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setTuition(List<TuitionClass> tuitionClasses) {
        this.tuitions.setTuitions(tuitionClasses);
        if (this.order != null) {
            this.tuitions.sort(order);
        }
    }



    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the address book.
     */
    public void setTuition(TuitionClass target, TuitionClass editedTuition) {
        requireNonNull(editedTuition);
        tuitions.setTuition(target, editedTuition);
        if (this.order != null) {
            this.tuitions.sort(order);
        }
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setTuition(newData.getTuitionList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    public Student getStudent(Index index) {
        requireNonNull(index);
        if (students.getStudentListSize() < index.getOneBased()) {
            return null;
        }
        return students.getStudent(index.getOneBased() - 1);
    }

    public TuitionClass getTuition(Index index) {
        requireNonNull(index);
        if (tuitions.tuitionListSize() < index.getOneBased()) {
            return null;
        }
        return tuitions.getTuitionClass(index.getOneBased() - 1);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same
     * as another existing student in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<TuitionClass> getTuitionList() {
        return tuitions.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && students.equals(((AddressBook) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasTuition(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        return tuitions.contains(tuitionClass);
    }

    /**
     * Adds a tuition class to the address book.
     * The tuition class must not already exist in the address book.
     */
    public void addTuition(TuitionClass t) {
        tuitions.add(t);
        if (this.order != null) {
            this.tuitions.sort(order);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTuition(TuitionClass key) {
        tuitions.remove(key);
    }

    public TuitionClass addToClass(TuitionClass tuitionClass, Student student) {
        return tuitionClass.addStudent(student);
    }

    /**
     * Returns a student with the same name as the input student.
     *
     * @param otherStudent the student to be checked
     * @return the student with the same name as input.
     */
    public Student getSameNameStudent(Student otherStudent) {
        return this.students.getSameNameStudent(otherStudent);
    }

    /**
     * Returns the tuition class with a certain id.
     * @param id the class with this id is to be found.
     * @return the class found if exists, return null otherwise.
     */
    public TuitionClass getClassById(Integer id) {
        for (TuitionClass tuitionClass: tuitions) {
            if (tuitionClass.getId() == id) {
                return tuitionClass;
            }
        }
        return null;
    }

    /**
     * Sorts the tuition class list and memorize the order prefered by the tutor.
     * @param order the order the list is to be sorted with.
     */
    public void sort(SortCommandParser.Order order) {
        this.order = order;
        this.tuitions.sort(order);
    }

    /**
     * Gets today tuition classes
     * @return a list contains today tuition classes
     */
    public ObservableList<TuitionClass> getTodayTuitionList() {
        return tuitions.getTodayTuition();
    }
}
