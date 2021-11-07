package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tuition.UniqueTuitionList;

/**
 * Executes a TimetableCommand to display a timetable on screen.
 */
public class TimetableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";
    public static final String SHORTCUT = "tt";
    public static final String NO_CLASS = "No class found.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": show timetable\n"
            + "Parameters: nil\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (UniqueTuitionList.getMostRecentTuitionClasses().size() == 0) {
            return new CommandResult(NO_CLASS);
        }
        return new CommandResult("Timetable shown.",
                CommandResult.UiAction.SHOW_TIMETABLE);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof TimetableCommand) {
            return true;
        }
        return false;
    }
}
