package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.TimetableCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses TimetableCommand and returns a TimetableCommand object.
 */
public class TimetableParser implements Parser<TimetableCommand> {
    @Override
    public TimetableCommand parse(String args) throws ParseException {
        if (args.equals("")) {
            return new TimetableCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableCommand.MESSAGE_USAGE));
        }
    }
}
