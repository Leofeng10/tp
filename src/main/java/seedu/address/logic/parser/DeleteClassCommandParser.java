package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteClassCommand object.
 */
public class DeleteClassCommandParser implements Parser<DeleteClassCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public DeleteClassCommand parse(String userInput) throws ParseException {
        try {
            List argList = new ArrayList<String>();
            argList.add(userInput.trim());
            List<Index> students = ParserUtil.parseStudentIndexes(argList);
            return new DeleteClassCommand(students);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE), pe);
        }
    }
}
