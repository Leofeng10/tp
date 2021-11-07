package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Command to display full information of a student to user.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "student";
    public static final String SHORTCUT = "vs";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows full information on a student of "
            + "a specified index number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewing details of student: ";

    private final Index index;

    /**
     * Constructor for a command to view a student.
     * @param index Index of student to be viewed.
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> studentList = model.getFilteredStudentList();

        if (index.getOneBased() > studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student student = studentList.get(index.getZeroBased());

        Student.setMostRecentTo(student);

        return new CommandResult(MESSAGE_SUCCESS + student.getName(),
                CommandResult.UiAction.SHOW_STUDENT_PAGE);
    }

}
