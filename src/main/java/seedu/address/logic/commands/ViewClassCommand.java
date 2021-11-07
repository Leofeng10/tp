package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tuition.TuitionClass;

/**
 * Command to display full information on a Tuition Class to user.
 */
public class ViewClassCommand extends Command {
    public static final String COMMAND_WORD = "class";
    public static final String SHORTCUT = "vc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows full information on a class of "
            + "a specified index number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewing details of class: ";

    private final Index index;

    /**
     * Constructor for a command to view a tuition class
     * @param index Index of tuition class to be viewed.
     */
    public ViewClassCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<TuitionClass> tuitionClassList = model.getFilteredTuitionList();

        if (index.getOneBased() > tuitionClassList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        TuitionClass tuitionClass = tuitionClassList.get(index.getZeroBased());

        TuitionClass.setMostRecentTo(tuitionClass);

        return new CommandResult(MESSAGE_SUCCESS + tuitionClass.getName(),
                CommandResult.UiAction.SHOW_TUITION_PAGE);
    }

}
