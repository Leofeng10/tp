package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.Timeslot;


/**
 * Parses input arguments and creates a new EditClassCommand object.
 */
public class EditClassCommandParser implements Parser<EditClassCommand> {

    private static final Logger logger = LogsCenter.getLogger(EditClassCommandParser.class);

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput The user input to be parsed
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public EditClassCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_LIMIT, PREFIX_TIMESLOT);
        Index index;
        ClassName className;
        ClassLimit limit;
        Timeslot timeslot;
        EditClassCommand.EditClassDescriptor editClassDescriptor = new EditClassCommand.EditClassDescriptor();

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClassCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            className = new ClassName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName);
            editClassDescriptor.setClassName(className);
        }
        if (argMultimap.getValue(PREFIX_LIMIT).isPresent()) {
            limit = ParserUtil.parseLimit(argMultimap.getValue(PREFIX_LIMIT).get());
            editClassDescriptor.setLimit(limit);
        }
        if (argMultimap.getValue(PREFIX_TIMESLOT).isPresent()) {
            timeslot = ParserUtil.parseTimeslot(argMultimap.getValue(PREFIX_TIMESLOT).get());
            editClassDescriptor.setTimeslot(timeslot);
        }
        if (!editClassDescriptor.isAnyFieldEdited()) {
            throw new ParseException(Messages.MESSAGE_NOT_EDITED);
        }
        //throw new ParseException("hell " + editClassDescriptor.getLimit().get().limit);
        return new EditClassCommand(index, editClassDescriptor);
    }
}


