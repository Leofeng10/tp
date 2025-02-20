package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemarkClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RemarkClassCommandParser implements Parser<RemarkClassCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkClassCommand}
     * and returns a {@code RemarkClassCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkClassCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkClassCommand.MESSAGE_USAGE), ive);
        }

        return new RemarkClassCommand(index);
    }
}
