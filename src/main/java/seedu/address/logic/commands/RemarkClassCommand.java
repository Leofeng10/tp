package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUITIONS;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Remark;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.ui.UiManager;

/**
 * Changes the remark of an existing tuition class in the TutAssistor.
 */
public class RemarkClassCommand extends Command {

    public static final String COMMAND_WORD = "remarkclass";
    public static final String SHORTCUT = "rec";

    public static final String MESSAGE_UPDATE_REMARK_SUCCESS = "Remark updated for Tuition Class: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Tuition Class: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the tuition class identified "
            + "by the index number used in the last tuition classes listing.\n"
            + "Parameters: CLASS_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    private static final Logger logger = LogsCenter.getLogger(RemarkClassCommand.class);

    private final Index index;

    /**
     * @param index of the tuition class in the filtered tuition class list to edit the remark
     */
    public RemarkClassCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredTuitionList(PREDICATE_SHOW_ALL_TUITIONS);
        List<TuitionClass> lastShownList = model.getFilteredTuitionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        TuitionClass classToEdit = lastShownList.get(index.getZeroBased());

        String name = String.valueOf(classToEdit.getName());
        String remarkToEdit = String.valueOf(classToEdit.getRemark());
        Remark newRemark = UiManager.showRemarkEditor(name, remarkToEdit);

        TuitionClass editedClass = new TuitionClass(classToEdit.getName(), classToEdit.getLimit(),
                classToEdit.getTimeslot(), classToEdit.getStudentList(), newRemark, classToEdit.getId());

        logger.info("Remarks updated from: [" + remarkToEdit + "] to [" + newRemark.toString() + "]");

        model.setTuition(classToEdit, editedClass);
        model.updateFilteredTuitionList(PREDICATE_SHOW_ALL_TUITIONS);

        return new CommandResult(generateSuccessMessage(editedClass));
    }

    /**
     * Generates the message for the command execution outcome.
     * @param classToEdit The tuition class with the new remark.
     * @return A message that informs the user whether the remark is updated successfully or deleted.
     */
    private String generateSuccessMessage(TuitionClass classToEdit) {
        String remark = String.valueOf(classToEdit.getRemark());
        String message = !remark.isEmpty() ? MESSAGE_UPDATE_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, classToEdit);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkClassCommand)) {
            return false;
        }

        // state check
        RemarkClassCommand e = (RemarkClassCommand) other;
        return index.equals(e.index);
    }
}
