package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveStudentCommand object.
 */
public class RemoveStudentCommandParser implements Parser<RemoveStudentCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public RemoveStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_INDEX, PREFIX_TUITION_CLASS);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_INDEX, PREFIX_TUITION_CLASS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveStudentCommand.MESSAGE_USAGE));
        }
        List<Index> students = ParserUtil.parseStudentIndexes(argMultimap.getAllValues(PREFIX_STUDENT_INDEX));
        Index classIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TUITION_CLASS).get());
        return new RemoveStudentCommand(students, classIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

