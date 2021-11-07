package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
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
import seedu.address.model.student.Student;
import seedu.address.model.tuition.TuitionClass;

/**
 * Removes students from an existing tuition class in TutAssistor.
 */
public class RemoveStudentCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    public static final String SHORTCUT = "rm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a student from a tuition class.\n"
            + "Parameters: "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX [STUDENT_INDEX]... "
            + PREFIX_TUITION_CLASS + "CLASS_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT_INDEX + "1 2 " + PREFIX_TUITION_CLASS + "2";

    public static final String MESSAGE_REMOVE_STUDENT_SUCCESS = "Removed students: %1$s from class: %2$s";
    public static final String MESSAGE_REMOVE_STUDENT_FAILURE = "Students: %1$s are not found in class: %2$s";
    private static final Logger logger = LogsCenter.getLogger(RemoveStudentCommand.class);
    private final List<Index> studentIndexes;
    private final Index classIndex;

    /**
     * Constructor for RemoveStudent command using student index and class index.
     *
     * @param studentIndexes Index of the student to be removed from the class.
     * @param classIndex Index of the class.
     */
    public RemoveStudentCommand(List<Index> studentIndexes, Index classIndex) {
        this.studentIndexes = studentIndexes;
        this.classIndex = classIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<String> studentsNotInClass = new ArrayList<>();
        List<String> studentsRemoved = new ArrayList<>();

        if (classIndex.getZeroBased() >= model.getFilteredTuitionList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }
        TuitionClass tuitionClass = model.getFilteredTuitionList().get(classIndex.getZeroBased());
        if (tuitionClass == null) {
            throw new CommandException(String.format(Messages.MESSAGE_CLASS_NOT_FOUND));
        }
        for (Index currIndex : studentIndexes) {
            if (currIndex.getZeroBased() >= model.getFilteredStudentList().size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            Student studentToRemove = model.getFilteredStudentList().get(currIndex.getZeroBased());
            if (studentToRemove == null) {
                throw new CommandException(String.format(Messages.MESSAGE_STUDENT_NOT_FOUND));
            }
            if (tuitionClass.containsStudent(studentToRemove)) {
                TuitionClass updatedClass = tuitionClass.removeStudent(studentToRemove);
                Student updatedStudent = studentToRemove.removeClass(tuitionClass);
                model.setTuition(tuitionClass, updatedClass);
                model.setStudent(studentToRemove, updatedStudent);
                studentsRemoved.add(studentToRemove.getName().fullName);
                logger.info(String.format("Students to be removed %s from class: %s", studentsRemoved, tuitionClass));
            } else {
                studentsNotInClass.add(studentToRemove.getName().fullName);
            }
        }
        String feedback = (!studentsRemoved.isEmpty()
                ? String.format(MESSAGE_REMOVE_STUDENT_SUCCESS + "\n", studentsRemoved, tuitionClass.getName()) : "")
                + (!studentsNotInClass.isEmpty()
                ? String.format(MESSAGE_REMOVE_STUDENT_FAILURE, studentsNotInClass, tuitionClass.getName()) : "");
        return new CommandResult(feedback);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveStudentCommand // instanceof handles nulls
                && classIndex.equals(((RemoveStudentCommand) other).classIndex)
                && studentIndexes.containsAll(((RemoveStudentCommand) other).studentIndexes));
    }
}

