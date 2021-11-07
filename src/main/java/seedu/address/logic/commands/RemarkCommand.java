package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Remark;
import seedu.address.model.student.Student;
import seedu.address.ui.UiManager;

/**
 * Changes the remark of an existing student in the TutAssistor.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";
    public static final String SHORTCUT = "re";

    public static final String MESSAGE_UPDATE_REMARK_SUCCESS = "Remark updated for Student: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Student: %1$s";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the student identified "
            + "by the index number used in the last student listing.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    private static final Logger logger = LogsCenter.getLogger(RemarkCommand.class);

    private final Index index;

    /**
     * @param index of the student in the filtered student list to edit the remark
     * @param remark of the student to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());

        String name = String.valueOf(studentToEdit.getName());
        String remarkToEdit = String.valueOf(studentToEdit.getRemark());
        Remark newRemark = UiManager.showRemarkEditor(name, remarkToEdit);

        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getAddress(), newRemark, studentToEdit.getTags(), studentToEdit.getClasses());

        logger.info("Remark Editor closed.");

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates the message for the command execution outcome.
     * @param studentToEdit The student with the new remark.
     * @return A message that informs the user whether the remark is updated successfully or deleted.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String remark = String.valueOf(studentToEdit.getRemark());
        String message = !remark.isEmpty() ? MESSAGE_UPDATE_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, studentToEdit);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index);
    }
}
