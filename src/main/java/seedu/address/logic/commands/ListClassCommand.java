package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUITIONS;

import seedu.address.model.Model;

/**
 * Lists all classes in the address book to the user.
 */
public class ListClassCommand extends Command {
    public static final String COMMAND_WORD = "listclass";
    public static final String SHORTCUT = "lc";

    public static final String MESSAGE_SUCCESS = "Listing all classes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTuitionList(PREDICATE_SHOW_ALL_TUITIONS);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.UiAction.SET_TUITIONS_DEFAULT);
    }

}
