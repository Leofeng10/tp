package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.TuitionClass;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<TuitionClass> PREDICATE_SHOW_ALL_TUITIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /** Returns an unmodifiable view of the filtered tuition list */
    ObservableList<TuitionClass> getFilteredTuitionList();

    /** Returns an unmodifiable view of today tuition list */
    ObservableList<TuitionClass> getTodayTuitionList();

    /**
     * Updates the filter of the filtered tuition list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTuitionList(Predicate<TuitionClass> predicate);

    boolean hasTuition(TuitionClass tuitionClass);

    /**
     * Deletes the tuition class given by {@code target}.
     * The class must exist in the address book.
     */
    void deleteTuition(TuitionClass target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addTuition(TuitionClass tuitionClass);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the address book.
     */
    void setTuition(TuitionClass target, TuitionClass editedTuition);

    /**
     * Checks whether the list of students contains the index from input.
     * @param index the index of student to be checked.
     * @return the student if the index is present.
     */
    Student getStudent(Index index);

    /**
     * Checks whether the list of tuition classes contains the index from input.
     * @param index the index of class to be checked.
     * @return the class if the index is present.
     */
    TuitionClass getTuitionClass(Index index);

    /**
     * Adds a new student to an existing class.
     * @param tuitionClass an existing class.
     * @param student an existing student.
     * @return the tuition class after modification.
     */
    TuitionClass addToClass(TuitionClass tuitionClass, Student student);

    /**
     * Returns a student with the same name as the input student.
     * @param otherStudent the student to be checked
     * @return the student with the same name as input.
     */
    Student getSameNameStudent(Student otherStudent);

    /**
     * Checks whether the list of tuition classes contains the id from input.
     * @param id the id of class to be checked.
     * @return the class if the id is present.
     */
    TuitionClass getClassById(Integer id);



    void sort(SortCommandParser.Order order);
}
