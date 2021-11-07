package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Remark;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

public class AddClassCommandParser implements Parser<AddClassCommand> {

    private static final Logger logger = LogsCenter.getLogger(AddClassCommand.class);
    /**
     * Parses the given {@code String} of arguments in the context of the AddClassCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddClassCommand parse(String args) throws ParseException {
        boolean hasStudents = false;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LIMIT, PREFIX_TIMESLOT,
                        PREFIX_STUDENT, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LIMIT, PREFIX_TIMESLOT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddClassCommand.MESSAGE_USAGE));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_STUDENT)) {
            hasStudents = true;
        }
        try {
            ClassName name = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_NAME).get());
            ClassLimit limit = ParserUtil.parseLimit(argMultimap.getValue(PREFIX_LIMIT).get());
            Timeslot timeslot = ParserUtil.parseTimeslot(argMultimap.getValue(PREFIX_TIMESLOT).get());
            StudentList student = hasStudents ? ParserUtil.parseStudent(argMultimap.getAllValues(PREFIX_STUDENT))
                    : new StudentList(new ArrayList<>());
            Remark remark = ParserUtil.parseRemark(argMultimap.getOptionalValue(PREFIX_REMARK).get());
            TuitionClass tuitionClass = new TuitionClass(name, limit, timeslot, student, remark);
            logger.info("AddClassCommandParser " + tuitionClass);
            return new AddClassCommand(tuitionClass);

        } catch (ParseException pe) {
            String displayMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, pe.getMessage())
                    + "\n" + AddClassCommand.MESSAGE_USAGE;
            throw new ParseException(displayMessage);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
