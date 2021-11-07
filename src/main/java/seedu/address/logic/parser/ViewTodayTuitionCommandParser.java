package seedu.address.logic.parser;

import seedu.address.logic.commands.ViewTodayTuitionCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class ViewTodayTuitionCommandParser implements Parser<ViewTodayTuitionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewTodayTuitionCommand
     * and returns a ViewTodayTuitionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTodayTuitionCommand parse(String args) throws ParseException {
        return new ViewTodayTuitionCommand();
    }
}
