package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddToClassCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditClassCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindClassCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListClassCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemarkClassCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.TimetableCommand;
import seedu.address.logic.commands.ViewClassCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewTodayTuitionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
        case AddCommand.SHORTCUT:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
        case EditCommand.SHORTCUT:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
        case DeleteCommand.SHORTCUT:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
        case FindCommand.SHORTCUT:
            return new FindCommandParser().parse(arguments);

        case FindClassCommand.COMMAND_WORD:
        case FindClassCommand.SHORTCUT:
            return new FindClassCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
        case ListCommand.SHORTCUT:
            return new ListCommand();

        case ListClassCommand.COMMAND_WORD:
        case ListClassCommand.SHORTCUT:
            return new ListClassCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
        case HelpCommand.SHORTCUT:
            return new HelpCommand();

        case RemarkCommand.COMMAND_WORD:
        case RemarkCommand.SHORTCUT:
            return new RemarkCommandParser().parse(arguments);

        case RemarkClassCommand.COMMAND_WORD:
        case RemarkClassCommand.SHORTCUT:
            return new RemarkClassCommandParser().parse(arguments);

        case AddClassCommand.COMMAND_WORD:
        case AddClassCommand.SHORTCUT:
            return new AddClassCommandParser().parse(arguments);

        case AddToClassCommand.COMMAND_WORD:
        case AddToClassCommand.SHORTCUT:
            return new AddToClassCommandParser().parse(arguments);

        case DeleteClassCommand.COMMAND_WORD:
        case DeleteClassCommand.SHORTCUT:
            return new DeleteClassCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
        case ViewCommand.SHORTCUT:
            return new ViewCommandParser().parse(arguments);

        case ViewClassCommand.COMMAND_WORD:
        case ViewClassCommand.SHORTCUT:
            return new ViewClassCommandParser().parse(arguments);

        case RemoveStudentCommand.COMMAND_WORD:
        case RemoveStudentCommand.SHORTCUT:
            return new RemoveStudentCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
        case SortCommand.SHORTCUT:
            return new SortCommandParser().parse(arguments);

        case ViewTodayTuitionCommand.COMMAND_WORD:
        case ViewTodayTuitionCommand.SHORTCUT:
            return new ViewTodayTuitionCommandParser().parse(arguments);

        case TimetableCommand.COMMAND_WORD:
        case TimetableCommand.SHORTCUT:
            return new TimetableParser().parse(arguments);

        case EditClassCommand.COMMAND_WORD:
        case EditClassCommand.SHORTCUT:
            return new EditClassCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
