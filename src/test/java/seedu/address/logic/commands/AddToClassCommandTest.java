package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureWithoutException;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_TENTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;

public class AddToClassCommandTest {
    private static final String INVALID_NAME = "Nora";
    private static final List<Index> STUDENT_INDEXES = getIndexes();
    private Model model = getNewModel();

    @Test
    public void execute_classDoesNotExist_failure() {
        AddToClassCommand addToClassCommand = new AddToClassCommand(STUDENT_INDEXES, INDEX_TENTH);
        assertCommandFailure(addToClassCommand, model, "This tuition class is not found.");
    }

    @Test
    public void execute_addOneStudentIndex_success() {
        AddToClassCommand addToClassCommand = new AddToClassCommand(convertIndexToList(INDEX_FIRST), INDEX_FIRST);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Student studentToAdd = expectedModel.getStudent(INDEX_FIRST);
        TuitionClass tuitionClass = expectedModel.getTuitionClass(INDEX_FIRST);
        TuitionClass newClass = setNewTuitionClass(tuitionClass, expectedModel);
        addStudentToClass(studentToAdd, newClass, expectedModel);
        ArrayList<String> studentName = new ArrayList<>();
        studentName.add(studentToAdd.getNameString());
        String message = String.format(AddToClassCommand.MESSAGE_SUCCESS, studentName) + "\n";
        assertCommandSuccess(addToClassCommand, model, message, expectedModel);
    }

    @Test
    public void execute_addMultipleStudentIndexes_success() {
        List<Index> indexList = getIndexes();
        AddToClassCommand addToClassCommand = new AddToClassCommand(indexList, INDEX_SECOND);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        TuitionClass tuitionClass = expectedModel.getTuitionClass(INDEX_SECOND);
        ArrayList<String> studentName = new ArrayList<>();
        TuitionClass newTuitionClass = setNewTuitionClass(tuitionClass, expectedModel);
        for (Index i: indexList) {
            Student studentToAdd = expectedModel.getStudent(i);
            addStudentToClass(studentToAdd, newTuitionClass, expectedModel);
            studentName.add(studentToAdd.getNameString());
        }
        String message = String.format(AddToClassCommand.MESSAGE_SUCCESS, studentName) + "\n";
        assertCommandSuccess(addToClassCommand, model, message, expectedModel);
    }

    @Test
    public void execute_addExistingIndexes_failure() {
        AddToClassCommand addToClassCommand = new AddToClassCommand(convertIndexToList(INDEX_SECOND), INDEX_FIRST);
        Student studentToAdd = model.getStudent(INDEX_SECOND);
        TuitionClass tuitionClass = model.getTuitionClass(INDEX_FIRST);
        model.addToClass(tuitionClass, studentToAdd);
        String message = "No student has been added.\n"
                + "Student [" + studentToAdd.getNameString() + "] is already in the class\n";
        assertCommandFailureWithoutException(addToClassCommand, model, message);
    }

    @Test
    public void execute_addInvalidIndexes_failure() {
        AddToClassCommand addToClassCommand = new AddToClassCommand(convertIndexToList(INDEX_TENTH), INDEX_FIRST);
        String message = "No student has been added.\n"
                + "The following students are not found in the address book: [Index 10 ]";
        assertCommandFailureWithoutException(addToClassCommand, model, message);
    }

    @Test
    public void execute_addToFullClass_failure() {
        Student studentToAdd = model.getStudent(INDEX_SECOND);
        model.getTuitionClass(INDEX_FIFTH).addStudent(studentToAdd);
        AddToClassCommand addToClassCommand = new AddToClassCommand(convertIndexToList(INDEX_FIRST), INDEX_FIFTH);
        String message = "These students cannot be added due to class limit. ";
        assertCommandFailure(addToClassCommand, model, message);
    }

    @Test
    public void execute_addOneStudentName_success() {
        ArrayList<String> studentNames = new ArrayList<>();
        Student studentToAdd = model.getStudent(INDEX_THIRD);
        studentNames.add(studentToAdd.getNameString());
        StudentList studentList = new StudentList(studentNames);
        AddToClassCommand addToClassCommand = new AddToClassCommand(studentList, INDEX_FIRST);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        TuitionClass tuitionClass = expectedModel.getTuitionClass(INDEX_FIRST);
        TuitionClass newClass = setNewTuitionClass(tuitionClass, expectedModel);
        addStudentToClass(studentToAdd, newClass, expectedModel);
        ArrayList<String> studentName = new ArrayList<>();
        studentName.add(studentToAdd.getNameString());
        String message = String.format(AddToClassCommand.MESSAGE_SUCCESS, studentName) + "\n";
        assertCommandSuccess(addToClassCommand, model, message, expectedModel);
    }

    @Test
    public void execute_addMultipleStudentName_success() {
        List<Index> indexList = getIndexes();
        ArrayList<String> studentNames = new ArrayList<>();
        for (Index i: indexList) {
            Student studentToAdd = model.getStudent(i);
            studentNames.add(studentToAdd.getNameString());
        }
        StudentList studentList = new StudentList(studentNames);
        AddToClassCommand addToClassCommand = new AddToClassCommand(studentList, INDEX_THIRD);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        TuitionClass tuitionClass = expectedModel.getTuitionClass(INDEX_THIRD);
        TuitionClass newTuitionClass = setNewTuitionClass(tuitionClass, expectedModel);
        for (Index i: indexList) {
            Student studentToAdd = expectedModel.getStudent(i);
            addStudentToClass(studentToAdd, newTuitionClass, expectedModel);
        }
        String message = String.format(AddToClassCommand.MESSAGE_SUCCESS, studentNames) + "\n";
        assertCommandSuccess(addToClassCommand, model, message, expectedModel);
    }

    @Test
    public void execute_addExistingStudentName_failure() {
        ArrayList<String> studentNames = new ArrayList<>();
        Student studentToAdd = model.getStudent(INDEX_FIRST);
        studentNames.add(studentToAdd.getNameString());
        StudentList studentList = new StudentList(studentNames);
        model.getTuitionClass(INDEX_FIRST).addStudent(model.getStudent(INDEX_FIRST));
        AddToClassCommand addToClassCommand = new AddToClassCommand(studentList, INDEX_FIRST);
        String message = "No student has been added.\n"
                + "Student " + studentNames + " is already in the class\n";
        assertCommandFailureWithoutException(addToClassCommand, model, message);
    }

    @Test
    public void execute_addInvalidStudentName_failure() {
        ArrayList<String> invalidStudent = new ArrayList<String>();
        invalidStudent.add(INVALID_NAME);
        StudentList studentList = new StudentList(invalidStudent);
        AddToClassCommand addToClassCommand = new AddToClassCommand(studentList, INDEX_FIRST);
        String message = "No student has been added.\n"
                + "The following students are not found in the address book: [" + INVALID_NAME + "]";
        assertCommandFailureWithoutException(addToClassCommand, model, message);
    }

    @Test
    public void execute_addDuplicateNames_success() {
        ArrayList<String> studentNames = new ArrayList<>();
        Student studentToAdd = model.getStudent(INDEX_FIFTH);
        for (int i = 0; i < 3; i++) {
            studentNames.add(studentToAdd.getNameString());
        }
        StudentList studentList = new StudentList(studentNames);
        AddToClassCommand addToClassCommand = new AddToClassCommand(studentList, INDEX_THIRD);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        TuitionClass tuitionClass = expectedModel.getTuitionClass(INDEX_THIRD);
        TuitionClass newTuitionClass = setNewTuitionClass(tuitionClass, expectedModel);
        addStudentToClass(studentToAdd, newTuitionClass, expectedModel);
        String message = String.format(AddToClassCommand.MESSAGE_SUCCESS,
                "[" + studentToAdd.getNameString() + "]") + "\n";
        assertCommandSuccess(addToClassCommand, model, message, expectedModel);
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withName("Alice").build();
        StudentList aliceList = new StudentList(new ArrayList<>() {{
                add(alice.getNameString()); }
        });
        Student bob = new StudentBuilder().withName("Bob").build();
        StudentList bobList = new StudentList(new ArrayList<>() {{
                add(bob.getNameString()); }
        });
        AddToClassCommand addAliceToFirstClass = new AddToClassCommand(aliceList, INDEX_FIRST);
        AddToClassCommand addBobToFirstClass = new AddToClassCommand(bobList, INDEX_FIRST);
        AddToClassCommand addAliceToSecondClass = new AddToClassCommand(aliceList, INDEX_SECOND);
        AddToClassCommand addIndexToFirstClass = new AddToClassCommand(getIndexes(), INDEX_FIRST);
        AddToClassCommand addIndexToSecondClass = new AddToClassCommand(getIndexes(), INDEX_SECOND);

        // same object -> returns true
        assertTrue(addAliceToFirstClass.equals(addAliceToFirstClass));

        // same values -> returns true
        AddToClassCommand addAliceToFirstClassCopy = new AddToClassCommand(aliceList, INDEX_FIRST);
        assertTrue(addAliceToFirstClass.equals(addAliceToFirstClassCopy));

        // different types -> returns false
        assertFalse(addIndexToFirstClass.equals(1));

        // null -> returns false
        assertFalse(addAliceToFirstClass.equals(null));

        // different student -> returns false
        assertFalse(addAliceToFirstClass.equals(addBobToFirstClass));

        //add index and add student -> return false
        assertFalse(addAliceToFirstClass.equals(addIndexToFirstClass));

        //different tuition classes -> return false
        assertFalse(addAliceToFirstClass.equals(addAliceToSecondClass));
        assertFalse(addIndexToFirstClass.equals(addIndexToSecondClass));

    }


    private static List<Index> getIndexes() {
        List<Index> indexList = new ArrayList<>();
        indexList.add(INDEX_FIRST);
        indexList.add(INDEX_SECOND);
        indexList.add(INDEX_THIRD);
        return indexList;
    }

    private static List<Index> convertIndexToList(Index... indexes) {
        List<Index> indexList = new ArrayList<>();
        for (Index i: indexes) {
            indexList.add(i);
        }
        return indexList;
    }

    private static Model getNewModel() {
        AddressBook abWithStudents = getAddressBookWithTypicalStudents();
        AddressBook abWithClasses = addTypicalClassesToAddressBook(abWithStudents);
        Model model = new ModelManager(abWithClasses, new UserPrefs());
        return model;
    }

    private void addStudentToClass(Student student, TuitionClass tuitionClass, Model expectedModel) {
        StudentBuilder studentInList = new StudentBuilder(student);
        Student newStudent = studentInList.build();
        expectedModel.setStudent(student, newStudent);
        expectedModel.addToClass(tuitionClass, newStudent);
        newStudent.addClass(tuitionClass);
        newStudent.addTag(new Tag(String.format("%s | %s", tuitionClass.getName().getName(),
                tuitionClass.getTimeslot())));
    }

    private TuitionClass setNewTuitionClass(TuitionClass tuitionClass, Model expectedModel) {
        TuitionClassBuilder tuitionClassInList = new TuitionClassBuilder(tuitionClass);
        TuitionClass newTuitionClass = tuitionClassInList.build();
        expectedModel.setTuition(tuitionClass, newTuitionClass);
        return newTuitionClass;
    }
}
