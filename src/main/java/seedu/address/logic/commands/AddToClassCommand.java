package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.TuitionClass;

/**
 * Adds students to existing tuition class.
 */
public class AddToClassCommand extends Command {
    public static final String COMMAND_WORD = "addtoclass";
    public static final String SHORTCUT = "atc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add students to existing class. "
            + "\n"
            + "Parameters: "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX [STUDENT_INDEX]..."
            + PREFIX_TUITION_CLASS + "CLASS_INDEX or\n"
            + PREFIX_STUDENT + "NAME[,NAME...] "
            + PREFIX_TUITION_CLASS + "CLASS_INDEX"
            + "\n" + "Example1: " + COMMAND_WORD + " " + PREFIX_STUDENT_INDEX + "1 2 " + PREFIX_TUITION_CLASS + "2"
            + "\n" + "Example2: " + COMMAND_WORD + " " + PREFIX_STUDENT + "Felicia,James " + PREFIX_TUITION_CLASS + "3";
    public static final String MESSAGE_SUCCESS = "New student %1$s added to class.";
    private static final String MESSAGE_STUDENT_EXISTS = "Student %1$s is already in the class";
    private static final String MESSAGE_CLASS_IS_FULL = "The following students are not "
            + "added due to class limit: ";
    private static final String MESSAGE_LIMIT_EXCEEDED = "These students cannot be added due to class limit. ";
    private static final String MESSAGE_STUDENT_NOT_FOUND = "The following students are not "
            + "found in the address book: ";
    private static final String MESSAGE_NO_STUDENT_ADDED = "No student has been added.";
    private static final Logger logger = LogsCenter.getLogger(AddToClassCommand.class);
    private List<Index> studentIndex;
    private Index classIndex;
    private StudentList studentList;
    private boolean isUsingIndex;
    private List<String> unfoundIndex = new ArrayList<>();

    /**
     * Constructor for AddToClass command using student index.
     * @param studentIndex index of student to be added.
     * @param classIndex index of class to be added to.
     */
    public AddToClassCommand(List<Index> studentIndex, Index classIndex) {
        this.classIndex = classIndex;
        this.studentIndex = studentIndex;
        this.isUsingIndex = true;
    }

    /**
     * Constructor for AddToClass command using student name.
     * @param students index of student to be added.
     * @param classIndex index of class to be added to.
     */
    public AddToClassCommand(StudentList students, Index classIndex) {
        this.classIndex = classIndex;
        this.studentList = students;
        this.isUsingIndex = false;
    }

    /**
     * Adds an existing student to an existing class.
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult to be shown to users.
     * @throws CommandException if the student or class is not found, or the student is in the class already.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //checks that the tuition class exists and is not full
        TuitionClass tuitionClass = model.getTuitionClass(classIndex);
        if (tuitionClass == null) {
            throw new CommandException(Messages.MESSAGE_CLASS_NOT_FOUND);
        }
        boolean isClassFull = tuitionClass.isFull();
        if (isClassFull) {
            throw new CommandException(MESSAGE_LIMIT_EXCEEDED);
        }
        //checks whether the command is using index or names
        if (!isUsingIndex) {
            return this.executeStudentName(model, tuitionClass);
        }
        return this.executeStudentIndex(model, tuitionClass);
    }

    private void updateModel(Model model, TuitionClass tuitionClass,
                             TuitionClass modifiedClass, Student studentToAdd, Student studentToChange) {
        model.setStudent(studentToChange, studentToAdd);
        model.setTuition(tuitionClass, modifiedClass);
    }

    /**
     * Categorizes students into not found, already enrolled, added, and not added due to class size limit.
     * @param studentList students to be added.
     * @param model model containing all students and tuition classes.
     * @param tuitionClass the tuition class students are added to.
     * @return an array of arraylists containing students being divided to not found, already enrolled, added,
     * and not added due to class size limit.
     */
    private ArrayList[] categorizeStudents(StudentList studentList, Model model, TuitionClass tuitionClass) {
        ArrayList<String> invalidStudentNames = new ArrayList<>();
        ArrayList<Student> newStudents = new ArrayList<>();
        ArrayList<String> notAdded = new ArrayList<>();
        ArrayList<String> validStudentNames = new ArrayList<>();
        ArrayList<String> existingStudent = new ArrayList<>();
        validStudentNames.addAll(tuitionClass.getStudentList().getStudents());
        int limit = tuitionClass.getLimit().getLimit();
        for (String studentName: studentList.getStudents()) {
            Student student = new Student(new Name(studentName));
            if (!model.hasStudent(student)) {
                if (!invalidStudentNames.contains(studentName)) {
                    invalidStudentNames.add(studentName);
                }
                continue;
            }
            if (validStudentNames.contains(studentName)) {
                if (!existingStudent.contains(studentName)
                        && !newStudents.contains(model.getSameNameStudent(student))) {
                    existingStudent.add(studentName);
                }
                continue;
            }
            if (limit <= validStudentNames.size()) {
                if (!notAdded.contains(studentName)) {
                    notAdded.add(studentName);
                }
                continue;
            }
            if (!newStudents.contains(model.getSameNameStudent(student))) {
                newStudents.add(model.getSameNameStudent(student));
                validStudentNames.add(studentName);
            }
        }
        ArrayList[] returnValue = new ArrayList[]{newStudents, invalidStudentNames,
            validStudentNames, notAdded, existingStudent};
        return returnValue;
    }

    /**
     * Executes the command if students are added using their names.
     * @param model model containing all students and tuition classes.
     * @param tuitionClass the tuition class students are added to.
     * @return a CommandResult of the AddToClass command.
     */
    private CommandResult executeStudentName(Model model, TuitionClass tuitionClass) {
        ArrayList[] students = this.categorizeStudents(studentList, model, tuitionClass);
        ArrayList<Student> newStudents = students[0];

        if (newStudents.size() == 0) {
            return new CommandResult(getMessage(students));
        }
        TuitionClass modifiedClass = null;
        for (Student student: newStudents) {
            modifiedClass = model.addToClass(tuitionClass, student);
        }
        if (modifiedClass == null) {
            return new CommandResult(getMessage(students));
        }

        String logStudentName = updateStudent(newStudents, tuitionClass, modifiedClass, model);
        logger.info("Add students [" + logStudentName + "] to class [" + tuitionClass.getName() + "]");
        return new CommandResult(getMessage(students));
    }

    /**
     * Updates information of new students enrolled in the tuition class.
     * @param newStudents students to be updated.
     * @param tuitionClass the original tuition class.
     * @param modifiedClass the updated tuition class.
     * @param model the model containing all students and tuition classes.
     * @return log information.
     */
    private String updateStudent(ArrayList<Student> newStudents, TuitionClass tuitionClass,
                                TuitionClass modifiedClass, Model model) {
        String logStudentName = "";
        for (Student student : newStudents) {
            Student studentToAdd = student;
            Student studentToChange = student;
            logStudentName += student.getNameString();
            if (!student.equals(newStudents.get(newStudents.size() - 1))) {
                logStudentName += ", ";
            }
            studentToAdd.addClass(modifiedClass);
            studentToAdd.addTag(new Tag(String.format("%s | %s", modifiedClass.getName().getName(),
                    modifiedClass.getTimeslot())));
            updateModel(model, tuitionClass, modifiedClass, studentToAdd, studentToChange);
        }
        return logStudentName;
    }

    /**
     * Executes the command if students are added using their indexes.
     * @param model model containing all students and tuition classes
     * @param tuitionClass the tuition class students are added to
     * @return a CommandResult of the AddToClass command
     */
    private CommandResult executeStudentIndex(Model model, TuitionClass tuitionClass) {
        ArrayList<String> studentNames = new ArrayList<>();
        for (Index index: studentIndex) {
            Student student = model.getStudent(index);
            if (student == null) {
                this.unfoundIndex.add("Index " + index.getOneBased() + " ");
            } else {
                studentNames.add(student.getName().toString());
            }
        }
        this.studentList = new StudentList(studentNames);
        return executeStudentName(model, tuitionClass);
    }

    /**
     * Produces the message to show to user.
     * @param students an array of arraylists containing students being divided to not found, already enrolled, added,
     * and not added due to class size limit
     * @return the message as a String
     */
    private String getMessage(ArrayList[] students) {
        ArrayList<String> invalidStudentNames = students[1];
        ArrayList<Student> newStudents = students[0];
        ArrayList<String> notAdded = students[3];
        boolean limitExceeded = notAdded.size() > 0;
        boolean hasInvalidNames = invalidStudentNames.size() > 0;
        boolean noStudentAdded = newStudents.size() == 0;
        boolean studentExists = students[4].size() > 0;
        String message = "";
        ArrayList<String> studentAdded = new ArrayList<>();
        for (Student student : newStudents) {
            studentAdded.add(student.getName().toString());
        }
        if (noStudentAdded) {
            message += MESSAGE_NO_STUDENT_ADDED + "\n";
        } else {
            message += String.format(MESSAGE_SUCCESS, studentAdded) + "\n";
        }
        if (studentExists) {
            message += String.format(MESSAGE_STUDENT_EXISTS, students[4]) + "\n";
        }
        if (limitExceeded) {
            message += MESSAGE_CLASS_IS_FULL + notAdded + "\n";
        }
        if (hasInvalidNames) {
            message += MESSAGE_STUDENT_NOT_FOUND + invalidStudentNames;
        }
        if (unfoundIndex.size() > 0) {
            message += MESSAGE_STUDENT_NOT_FOUND + unfoundIndex;
        }
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }
        AddToClassCommand that = (AddToClassCommand) o;

        if (isUsingIndex != that.isUsingIndex) {
            return false;
        }
        if (isUsingIndex) {
            if (!studentIndex.equals(that.studentIndex)) {
                return false;
            }
        } else {
            if (!studentList.equals(that.studentList)) {
                return false;
            }
        }
        if (!classIndex.equals(that.classIndex)) {
            return false;
        }
        return true;
    }
}
